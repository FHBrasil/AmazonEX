package br.hering.core.jobs;

import de.hybris.platform.acceleratorcms.enums.NavigationBarMenuLayout;
import de.hybris.platform.acceleratorcms.model.components.NavigationBarComponentModel;
import de.hybris.platform.acceleratorservices.setup.SetupSyncJobService;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.jalo.CatalogManager;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.CategoryService;
import de.hybris.platform.cms2.enums.LinkTargets;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.contents.ContentCatalogModel;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;
import de.hybris.platform.cms2.model.contents.contentslot.ContentSlotModel;
import de.hybris.platform.cms2.model.navigation.CMSNavigationNodeModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSComponentService;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.ProductSearchFacade;
import de.hybris.platform.commercefacades.search.data.SearchQueryData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.facetdata.FacetData;
import de.hybris.platform.commerceservices.search.facetdata.FacetValueData;
import de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.BaseStoreModel;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

import br.hering.core.cms.component.service.HeringCMSNavigationService;
import br.hering.core.cms.component.slot.HeringCMSContentSlotService;
import br.hering.core.model.MenuDefinitionModel;
import br.hering.core.model.jobs.MenuGeneratorJobModel;

/**
 * @author Ezequiel Barboza
 * @since 22/12/2014
 */
public class MenuGeneratorJobPerformable extends AbstractJobPerformable<MenuGeneratorJobModel>
{
	private Logger LOG = Logger.getLogger(MenuGeneratorJobPerformable.class);

	@Resource
	private BaseSiteService baseSiteService;

	@Resource
	private HeringCMSContentSlotService heringCmsContentSlotService;

	@Resource
	private HeringCMSNavigationService heringCmsNavigationService;

	@Resource
	private ProductSearchFacade<ProductData> productSearchFacade;

	@Resource
	private CMSComponentService cmsComponentService;

	@Resource
	private CatalogVersionService catalogVersionService;

	@Resource
	private CategoryService categoryService;
	
	@Resource
	private SetupSyncJobService setupSyncJobService;

	@Override
	public PerformResult perform(final MenuGeneratorJobModel job)
	{
		LOG.info("Inicio da job");
		CronJobResult result = CronJobResult.SUCCESS;

		try
		{
			baseSiteService.setCurrentBaseSite(job.getCmsSite(), true);

			//preparar os inputs
			final CatalogVersionModel stagedContentCatalogVersion = getContentCatalog(job.getCmsSite(),
					CatalogManager.OFFLINE_VERSION);
			final CatalogVersionModel onlineContentCatalogVersion = getContentCatalog(job.getCmsSite(),
					CatalogManager.ONLINE_VERSION);

			final CatalogVersionModel stagedProductCatalogVersion = getProductCatalog(job.getBaseStore(),
					CatalogManager.OFFLINE_VERSION);
			final CatalogVersionModel onlineProductCatalogVersion = getProductCatalog(job.getBaseStore(),
					CatalogManager.ONLINE_VERSION);

			if (stagedContentCatalogVersion == null || onlineContentCatalogVersion == null || stagedProductCatalogVersion == null
					|| onlineProductCatalogVersion == null)
			{
				final String msg = "Job failed. " + "It was not possible to load the properties for given uids. ";
				throw new Exception(msg);
			}

			LOG.info("Starting to process. StagedContentCatalogVersion: " + stagedContentCatalogVersion
					+ "; StagedProductCatalogVersion: " + stagedProductCatalogVersion + "; OnlineContentCatalogVersion: "
					+ onlineContentCatalogVersion + "; OnlineProductCatalogVersion: " + onlineProductCatalogVersion);

			//nao posso setar estes catalogos na sessão porque o solr não existe indice solr nestes catalogos
			catalogVersionService.setSessionCatalogVersions(Arrays.asList(onlineProductCatalogVersion, onlineContentCatalogVersion));

			final List<MenuOption> menuOptionList = new ArrayList<MenuGeneratorJobPerformable.MenuOption>();

			for (MenuDefinitionModel definition : job.getMenuDefinitions())
			{
				final MenuOption menuOption = new MenuOption(definition);

				prepareLinks(stagedContentCatalogVersion, menuOption);

				prepareNavigationMenu(stagedContentCatalogVersion, menuOption);

				prepareNavigationNode(job.getBaseStore().getUid(), stagedContentCatalogVersion, menuOption);

				prepareNavigationBar(stagedContentCatalogVersion, menuOption);

				menuOptionList.add(menuOption);
			}

			for (MenuOption menuOption : menuOptionList)
			{
				for (CMSLinkComponentModel link : menuOption.getLinkList())
				{
					modelService.save(link);
					modelService.refresh(link);
				}
				modelService.save(menuOption.getLinkWithoutCategory());
				modelService.refresh(menuOption.getLinkWithoutCategory());

				modelService.save(menuOption.getNavigationMenu());
				modelService.refresh(menuOption.getNavigationMenu());

				modelService.save(menuOption.getNavigationNode());
				modelService.refresh(menuOption.getNavigationNode());

				modelService.save(menuOption.getNavigationBarComponent());
				modelService.refresh(menuOption.getNavigationBarComponent());
			}
			ContentSlotModel contentSlot = prepareContentSlot(stagedContentCatalogVersion, menuOptionList);

			modelService.save(contentSlot);
			modelService.refresh(contentSlot);

			runSyncJob(stagedContentCatalogVersion.getCatalog().getId(), result);
			
			LOG.info("Fim da Job");
		}
		catch (Exception e)
		{
			LOG.info("Unexpected error: " + e);
			result = CronJobResult.FAILURE;
		}
		
		return new PerformResult(result, CronJobStatus.FINISHED);
	}
	
	private void runSyncJob(final String catalogId, final CronJobResult result)
	{
		try
		{
			if (CronJobResult.SUCCESS.equals(result))
			{
   			setupSyncJobService.createContentCatalogSyncJob(catalogId);
   			setupSyncJobService.executeCatalogSyncJob(catalogId);
			}
		}
		catch (Exception e)
		{
			LOG.info("Exception" + e);
		}
	}

	private void prepareLinks(final CatalogVersionModel contentCatalogVersion, final MenuOption menuOption)
	{
		try
		{
			final List<CatalogVersionModel> catalogList = Arrays.asList(contentCatalogVersion);

			final String type = removeAcentos(WordUtils.capitalize(menuOption.getName())).replace(" ", "");

			prepareNoCategoryLinks(menuOption, catalogList, type);
			prepareCustomLinks(menuOption, catalogList, type);
			prepareCategoryLinks(menuOption, catalogList, type);
		}
		catch (Exception e)
		{
			LOG.info("Exception preparing links: " + e);
		}
	}

	private void prepareNoCategoryLinks(final MenuOption menuOption, final List<CatalogVersionModel> catalogList, final String type)
	{
		final String uid1 = type + "Link";

		CMSLinkComponentModel linkWithoutCategory = null;
		try
		{
			linkWithoutCategory = cmsComponentService.getAbstractCMSComponent(uid1, catalogList);
		}
		catch (Exception e)
		{
			LOG.info("Exception: " + e + ".Component: " + uid1 + " does not exist. Creating...");
		}
		if (linkWithoutCategory == null)
		{
			linkWithoutCategory = modelService.create(CMSLinkComponentModel.class);
			LOG.info("Component: " + uid1 + " created.");
		}

		linkWithoutCategory.setCatalogVersion(catalogList.get(0));
		linkWithoutCategory.setLinkName(menuOption.getName());
		linkWithoutCategory.setUid(uid1);
		linkWithoutCategory.setName(type + " Link");
		linkWithoutCategory.setUrl("/search?q=:relevance:" + menuOption.getQuery());
		
		menuOption.setLinkWithoutCategory(linkWithoutCategory);
	}

	private void prepareCustomLinks(final MenuOption menuOption, final List<CatalogVersionModel> catalogList, final String type)
	{
		for (Entry<String, String> entry : menuOption.getCustomCategories().entrySet())
		{
			final String linkName = WordUtils.capitalize(entry.getKey().trim());

			final String uid = type + removeAcentos(linkName.replace(" ", "")) + "Link";

			CMSLinkComponentModel customLink = null;
			try
			{
				customLink = cmsComponentService.getAbstractCMSComponent(uid, catalogList);
			}
			catch (Exception e)
			{
				LOG.info("Exception: " + e + ".Component: " + uid + " not found. Creating...");
			}

			if (customLink == null)
			{
				customLink = modelService.create(CMSLinkComponentModel.class);
				LOG.info("Component: " + uid + " created.");
			}

			customLink.setCatalogVersion(catalogList.get(0));
			customLink.setLinkName(linkName);
			customLink.setUid(uid);
			customLink.setName(type + " " + linkName + " Link");
			customLink.setUrl("/search?q=" + entry.getValue());
			customLink.setTarget(LinkTargets.SAMEWINDOW);
			
			menuOption.getLinkList().add(customLink);
		}
	}

	private void prepareCategoryLinks(final MenuOption menuOption, 
			final List<CatalogVersionModel> contentCatalogList,
			final String type)
	{
		try
		{
			List<String> categoryStringList = refineSearch(":name-asc:" + menuOption.getQuery());

			if (categoryStringList.isEmpty())
			{
				LOG.info("No category found for type: " + type);
			}

			for (String categoryLine : categoryStringList)
			{
				try
				{
					LOG.info("Found category: " + type + ":" + categoryLine);

					String categoryName = WordUtils.capitalize(categoryLine.split(";")[0].toLowerCase()).trim();
					String categoryCode = categoryLine.split(";")[1];

					if (menuOption.getNotAllowedCategories().contains(categoryName))
					{
						continue;
					}

					final String uid = type + removeAcentos(categoryName.replace(" ", "")) + categoryCode + "Link";

					CMSLinkComponentModel linkWithCategory = null;
					try
					{
						linkWithCategory = cmsComponentService.getAbstractCMSComponent(uid, contentCatalogList);
					}
					catch (Exception e)
					{
						LOG.info("Exception: " + e + "Component: " + uid + " not found. Creating...");
					}
			
					if (linkWithCategory == null)
					{
						linkWithCategory = modelService.create(CMSLinkComponentModel.class);
						LOG.info("Component: " + uid + " created.");
					}

					linkWithCategory.setCatalogVersion(contentCatalogList.get(0));
					linkWithCategory.setLinkName(categoryName);
					linkWithCategory.setUid(uid);
					linkWithCategory.setName(type + " " + categoryName + " Link");
					linkWithCategory.setUrl("/search?q=:relevance:" + menuOption.getQuery());
					linkWithCategory.setTarget(LinkTargets.SAMEWINDOW);
					linkWithCategory.setCategory(categoryService.getCategoryForCode(categoryCode));
					
					menuOption.getLinkList().add(linkWithCategory);
				}
				catch (Exception e)
				{
					LOG.info("Exception " + e + " while preparing link for category: " + type + ":" + categoryLine);
				}
			}
		}
		catch (Exception e)
		{
			LOG.info("Exception preparing category links: " + e);
		}
	}

	private void prepareNavigationMenu(final CatalogVersionModel contentCatalogVersion, final MenuOption menuOption)
	{
		try
		{
			final String type = removeAcentos(WordUtils.capitalize(menuOption.getName())).replace(" ", "");

			final String uid = type + "NavNodeMenu";

			CMSNavigationNodeModel navigationMenu = null;

			try
			{
				navigationMenu = heringCmsNavigationService.getNavigationNodeForId(uid, Arrays.asList(contentCatalogVersion));
			}
			catch (Exception e)
			{
				LOG.info("Exception: " + e + "NavigationNode: " + uid + " not found. Creating...");
			}

			if (navigationMenu == null)
			{
				navigationMenu = modelService.create(CMSNavigationNodeModel.class);
				LOG.info("NavigationNode: " + uid + " created...");
			}
			
			navigationMenu.setUid(uid);
			navigationMenu.setCatalogVersion(contentCatalogVersion);
			navigationMenu.setName(type + " Menu");

			if (navigationMenu.getLinks() != null)
			{
				for (CMSLinkComponentModel aux : navigationMenu.getLinks())
				{
					if (aux.getUid().contains("Fixed") && !menuOption.getLinkList().contains(aux))
					{
						LOG.info("Adding fixed link to menu. Link: " + aux.getUid() + ";Menu: " + uid);
						menuOption.getLinkList().add(aux);
					}
				}
			}
			navigationMenu.setLinks(menuOption.getLinkList());

			menuOption.setNavigationMenu(navigationMenu);
		}
		catch (Exception e)
		{
			LOG.error("Error while preparing NavigationMenu: " + e);
		}
	}

	private void prepareNavigationNode(final String baseStoreUid, 
			final CatalogVersionModel contentCatalogVersion,
			final MenuOption menuOption)
	{
		try
		{
			final Collection<CatalogVersionModel> catalogList = Arrays.asList(contentCatalogVersion);

			//o parent já tem que existir... assumimos que ele existe
			final CMSNavigationNodeModel parent = heringCmsNavigationService.getNavigationNodeForId(
					WordUtils.capitalize(baseStoreUid) + "NavNode", catalogList);

			final String type = removeAcentos(WordUtils.capitalize(menuOption.getName())).replace(" ", "");

			final String uid = type + "NavigationNode";

			CMSNavigationNodeModel navigationNode = null;

			try
			{
				navigationNode = heringCmsNavigationService.getNavigationNodeForId(uid, catalogList);
			}
			catch (Exception e)
			{
				LOG.info("Exception: " + e + "NavigationNode: " + uid + " not found. Creating...");
			}

			if (navigationNode == null)
			{
				navigationNode = modelService.create(CMSNavigationNodeModel.class);
				LOG.info("NavigationNode: " + uid + " created...");
			}
			
			navigationNode.setUid(uid);
			navigationNode.setCatalogVersion(contentCatalogVersion);
			navigationNode.setName(type + " Navigation Node");
			navigationNode.setParent(parent);
			navigationNode.setChildren(Arrays.asList(menuOption.getNavigationMenu()));

			menuOption.setNavigationNode(navigationNode);
			menuOption.getNavigationMenu().setParent(navigationNode);
		}
		catch (Exception e)
		{
			LOG.info("Exception while preparing navigationNode: " + e);
		}
	}

	private void prepareNavigationBar(final CatalogVersionModel contentCatalogVersion, final MenuOption menuOption)
	{
		try
		{
			final Collection<CatalogVersionModel> catalogList = Arrays.asList(contentCatalogVersion);

			final String type = removeAcentos(WordUtils.capitalize(menuOption.getName())).replace(" ", "");

			final String uid1 = type + "BarComponent";

			NavigationBarComponentModel navigationBarComponent = null;

			try
			{
				navigationBarComponent = cmsComponentService.getAbstractCMSComponent(uid1, catalogList);
			}
			catch (CMSItemNotFoundException e)
			{
				LOG.info("Exception: " + e + ".NavigationBarComponent: " + uid1 + " not found. Creating...");
			}
			//NavigationBarComponent;$contentCV[unique=true];uid[unique=true]	 ;name					 ;wrapAfter;link(uid, $contentCV);styleClass;navigationNode(uid,$contentCV);dropDownLayout(code)[default='AUTO']
			//								;								;FemaleBarComponent;Female Menu Bar Item;10		  ;FemininoLink			;			  ;FemaleNavigationNode;
			if (navigationBarComponent == null)
			{
				navigationBarComponent = modelService.create(NavigationBarComponentModel.class);
				LOG.info("NavigationBarComponent:" + uid1 + " created");
			}
			navigationBarComponent.setCatalogVersion(contentCatalogVersion);
			navigationBarComponent.setUid(uid1);
			navigationBarComponent.setName(type + " Menu Bar Item");
			navigationBarComponent.setWrapAfter(10);
			navigationBarComponent.setLink(menuOption.getLinkWithoutCategory());
			navigationBarComponent.setNavigationNode(menuOption.getNavigationNode());
			navigationBarComponent.setDropDownLayout(NavigationBarMenuLayout.AUTO);
			
			menuOption.setNavigationBarComponent(navigationBarComponent);
		}
		catch (Exception e)
		{
			LOG.info("Error preparing NavigationBarComponent " + e);
		}
	}

	private ContentSlotModel prepareContentSlot(final CatalogVersionModel contentCatalogVersion,
			final List<MenuOption> menuOptionList)
	{
		ContentSlotModel contentSlot = null;
		try
		{
			final List<CatalogVersionModel> catalogList = Arrays.asList(new CatalogVersionModel[]
			{ contentCatalogVersion });

			final String uid = "NavigationBarSlot";

			try
			{
				contentSlot = heringCmsContentSlotService.getContentSlotForId(uid, catalogList);
			}
			catch (Exception e)
			{
				LOG.info("Exception: " + e + "ContentSlot: " + uid + " not found. Creating");
			}
			if (contentSlot == null)
			{
				contentSlot = modelService.create(ContentSlotModel.class);
				contentSlot.setUid(uid);
				LOG.info("ContentSlot: " + uid + " created...");
			}
			final List<AbstractCMSComponentModel> list = new ArrayList<>();
			for (final MenuOption menuOption : menuOptionList)
			{
				list.add(menuOption.getNavigationBarComponent());
			}
			contentSlot.setCmsComponents(list);
		}
		catch (Exception e)
		{
			LOG.info("Exception while preparing ContentSlot: " + e);
		}
		return contentSlot;
	}

	private String removeAcentos(String str)
	{
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = str.replaceAll("[^\\p{ASCII}]", "");
		return str;
	}

	private class MenuOption
	{

		private String name;
		private String query;
		private Collection<String> notAllowedCategories;
		private Map<String, String> customCategories;

		private CMSNavigationNodeModel navigationMenu;
		private CMSNavigationNodeModel navigationNode;

		private NavigationBarComponentModel navigationBarComponent;

		private CMSLinkComponentModel linkWithoutCategory;
		private List<CMSLinkComponentModel> linkList;

		public MenuOption(final MenuDefinitionModel menuDefinition)
		{
			this.setLinkList(new ArrayList<CMSLinkComponentModel>());
			this.setNotAllowedCategories(menuDefinition.getNotAllowedCategories());
			this.setName(menuDefinition.getName());
			this.setQuery(menuDefinition.getQuery());
			this.setCustomCategories(menuDefinition.getCustomCategories());
		}

		/**
		 * @return the navigationMenu
		 */
		public CMSNavigationNodeModel getNavigationMenu()
		{
			return navigationMenu;
		}

		/**
		 * @param navigationMenu
		 *           the navigationMenu to set
		 */
		public void setNavigationMenu(CMSNavigationNodeModel navigationMenu)
		{
			this.navigationMenu = navigationMenu;
		}

		/**
		 * @return the linkWithoutCategory
		 */
		public CMSLinkComponentModel getLinkWithoutCategory()
		{
			return linkWithoutCategory;
		}


		/**
		 * @param linkWithoutCategory
		 *           the linkWithoutCategory to set
		 */
		public void setLinkWithoutCategory(CMSLinkComponentModel linkWithoutCategory)
		{
			this.linkWithoutCategory = linkWithoutCategory;
		}

		/**
		 * @return the navigationBarComponent
		 */
		public NavigationBarComponentModel getNavigationBarComponent()
		{
			return navigationBarComponent;
		}

		/**
		 * @param navigationBarComponent
		 *           the navigationBarComponent to set
		 */
		public void setNavigationBarComponent(NavigationBarComponentModel navigationBarComponent)
		{
			this.navigationBarComponent = navigationBarComponent;
		}

		/**
		 * @return the navigationNode
		 */
		public CMSNavigationNodeModel getNavigationNode()
		{
			return navigationNode;
		}

		/**
		 * @param navigationNode
		 *           the navigationNode to set
		 */
		public void setNavigationNode(CMSNavigationNodeModel navigationNode)
		{
			this.navigationNode = navigationNode;
		}

		/**
		 * @return the name
		 */
		public String getName()
		{
			return name;
		}

		/**
		 * @param name
		 *           the name to set
		 */
		public void setName(String name)
		{
			this.name = name;
		}

		/**
		 * @return the query
		 */
		public String getQuery()
		{
			return query;
		}

		/**
		 * @param query
		 *           the query to set
		 */
		public void setQuery(String query)
		{
			this.query = query;
		}

		/**
		 * @return the notAllowedCategories
		 */
		public Collection<String> getNotAllowedCategories()
		{
			return notAllowedCategories;
		}

		/**
		 * @param notAllowedCategories
		 *           the notAllowedCategories to set
		 */
		public void setNotAllowedCategories(Collection<String> notAllowedCategories)
		{
			this.notAllowedCategories = notAllowedCategories;
		}

		/**
		 * @return the customCategories
		 */
		public Map<String, String> getCustomCategories()
		{
			return customCategories;
		}

		/**
		 * @param customCategories
		 *           the customCategories to set
		 */
		public void setCustomCategories(Map<String, String> customCategories)
		{
			this.customCategories = customCategories;
		}

		/**
		 * @return the linkList
		 */
		public List<CMSLinkComponentModel> getLinkList()
		{
			return linkList;
		}

		/**
		 * @param linkList
		 *           the linkList to set
		 */
		public void setLinkList(List<CMSLinkComponentModel> linkList)
		{
			this.linkList = linkList;
		}
	}

	private List<String> refineSearch(final String searchQuery)
	{
		final PageableData pageableData = createPageableData();

		final SearchQueryData searchQueryData = new SearchQueryData();
		searchQueryData.setValue(searchQuery);

		final SearchStateData searchState = new SearchStateData();
		searchState.setQuery(searchQueryData);

		ProductSearchPageData<SearchStateData, ProductData> searchPageData = productSearchFacade.textSearch(searchState,
				pageableData);

		if (searchPageData.getPagination().getTotalNumberOfResults() == 0)
		{
			return Collections.emptyList();
		}

		for (FacetData<SearchStateData> facet : searchPageData.getFacets())
		{
			if ("category".equalsIgnoreCase(facet.getCode()))
			{
				List<String> result = new ArrayList<String>();

				for (FacetValueData<SearchStateData> category : facet.getValues())
				{
					result.add(category.getName() + ";" + category.getCode());
				}

				return result;
			}
		}
		return Collections.emptyList();
	}

	private PageableData createPageableData()
	{
		final PageableData pageableData = new PageableData();
		pageableData.setCurrentPage(0);
		pageableData.setSort(null);
		pageableData.setPageSize(100);
		return pageableData;
	}

	private CatalogVersionModel getContentCatalog(final CMSSiteModel cmsSite, final String version)
	{
		try
		{
			ContentCatalogModel contentCatalog = cmsSite.getContentCatalogs().iterator().next();

			String contentCatalogId = contentCatalog.getId();

			return catalogVersionService.getCatalogVersion(contentCatalogId, version);
		}
		catch (Exception e)
		{
			LOG.info("Exception getting ContentCatalog:" + e);
			return null;
		}
	}

	private CatalogVersionModel getProductCatalog(final BaseStoreModel baseStore, final String version)
	{
		try
		{
			CatalogModel productCatalog = baseStore.getCatalogs().iterator().next();

			String productCatalogId = productCatalog.getId();

			return catalogVersionService.getCatalogVersion(productCatalogId, version);
		}
		catch (Exception e)
		{
			LOG.info("Exception getting ProductCatalog:" + e);
			return null;
		}
	}
}
