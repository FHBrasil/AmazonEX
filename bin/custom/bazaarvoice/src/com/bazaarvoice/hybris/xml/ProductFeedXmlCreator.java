/**
 *
 */
package com.bazaarvoice.hybris.xml;

import de.hybris.platform.acceleratorservices.urlencoder.attributes.impl.DefaultLanguageAttributeManager;
import de.hybris.platform.acceleratorservices.urlresolver.SiteBaseUrlResolutionService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2lib.model.components.ProductDetailComponentModel;
import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.variants.model.VariantProductModel;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bazaarvoice.hybris.xml.tags.Attribute;
import com.bazaarvoice.hybris.xml.tags.BrandTag;
import com.bazaarvoice.hybris.xml.tags.CategoryTag;
import com.bazaarvoice.hybris.xml.tags.Description;
import com.bazaarvoice.hybris.xml.tags.LocalizedName;
import com.bazaarvoice.hybris.xml.tags.ProductFeed;
import com.bazaarvoice.hybris.xml.tags.ProductTag;
import com.sun.xml.bind.marshaller.CharacterEscapeHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

import static com.bazaarvoice.hybris.utils.BazaarVoiceUtils.ReplaceUnsupportedCharacters;

/**
 * @author christina romashchenko
 */
@Scope("prototype")
@Component
public class ProductFeedXmlCreator
{
	// constants used to declare families for products (used with hybris product variants)
	private static final String BV_FAMILY_NAME_ATTRIBUTE_ID = "BV_FE_FAMILY";
	private static final String BV_FAMILY_EXPAND = "BV_FE_EXPAND";

	@Resource(name = "categoryModelUrlResolver")
	private UrlResolver<CategoryModel> categoryModelUrlResolver;

	@Resource(name = "productModelUrlResolver")
	private UrlResolver<ProductModel> productModelUrlResolver;

	@Resource(name = "siteBaseUrlResolutionService")
	private SiteBaseUrlResolutionService siteBaseUrlResolutionService;

	@Resource(name = "languageAttributeManager")
	private DefaultLanguageAttributeManager languageAttributeManager;

	private static final Logger LOG = Logger.getLogger(ProductFeedXmlCreatorTest.class.getName());
	//private String dateCounter;
	private int fileCounter = 1;
	private List<CategoryModel> brands;

	//	private String host_url;
	private String store_webroot;
	private CMSSiteModel cmsSiteModel;

    private String upcMethodName;

    public void setUpcMethodName(String upcMethodName) {
        this.upcMethodName = upcMethodName;
    }
	/**
	 * @return the brands
	 */
	public List<CategoryModel> getBrands()
	{
		return brands;
	}

	/**
	 * @param brands
	 *           the brands to set
	 */
	public void setBrands(final List<CategoryModel> brands)
	{
		this.brands = brands;
	}

	public void setCMSSiteModel(final CMSSiteModel cmsSiteModel)
	{
		this.cmsSiteModel = cmsSiteModel;
	}

	public void writeXmlToFile(final File file, final ProductFeed productFeed)
	{

		LOG.debug("===========Marshalling Product Fedd to file " + file.getAbsolutePath() + "===================");

		try
		{
			final JAXBContext jaxbContext = JAXBContext.newInstance(ProductFeed.class);
			final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			jaxbMarshaller.setProperty(CharacterEscapeHandler.class.getName(),
					new CharacterEscapeHandler() {
						@Override
						public void escape(char[] ac, int i, int j, boolean flag,
										   Writer writer) throws IOException {
							writer.write(ac, i, j);
						}
					});
			jaxbMarshaller.marshal(productFeed, file);

			
		}
		catch (final JAXBException e)
		{
			LOG.error(e.getStackTrace());
		}

	}


	private CategoryTag getCategoryTag(final CategoryModel model, final Map<Locale, String> locales)
	{
		final CategoryTag resultTag = new CategoryTag();

		/* Required */

		//ExternalId
		resultTag.setExternalId((model.getCode()==null || StringEscapeUtils.escapeXml(model.getCode()).isEmpty())? null : StringEscapeUtils.escapeXml(model.getCode()));
		//Name
		resultTag.setName((model.getName()==null || model.getName().isEmpty()) ? null : model.getName());
		//Localized category names
		final List<LocalizedName> names = new ArrayList<LocalizedName>();
		for (final Locale locale : locales.keySet())
		{
			try
			{
				final String localName = model.getName(locale);
				final String normalizedLocale = locales.get(locale);
				if (localName != null && normalizedLocale != null)
				{
					final LocalizedName lname = new LocalizedName(normalizedLocale, localName);
					names.add(lname);
				}
			}
			catch (final Exception ex)
			{
				//LOG.debug(ex.getMessage());
				continue;
			}

		}
		resultTag.setNames(names.isEmpty() ? null : names);

		//CategoryPageUrl
		//		resultTag.setCategoryPageUrl(normalizeText(host_url + categoryModelUrlResolver.resolve(model)));

		final String categoryPageUrl = siteBaseUrlResolutionService.getWebsiteUrlForSite(cmsSiteModel, false,
				categoryModelUrlResolver.resolve(model));
		resultTag.setCategoryPageUrl((categoryPageUrl==null || categoryPageUrl.isEmpty()) ? null : categoryPageUrl);

		/*
		 * Not required parameters
		 */

		/* CategoryPageUrls */
		//		final List<CMSLinkComponentModel> pageUrls = model.getLinkComponents();
		//		if (pageUrls != null)
		//		{
		//
		//		}

		/* ParentExternalId */
		String parentExternalId = null;
		final List<CategoryModel> supercategories = model.getSupercategories();

		/*
		 * supercategories contain several types of elements: 1. a single CategoryModel type element that is our
		 * supercategory 2. ClassificationClassModel that is for classification system usage. So at the moment we use the
		 * only instance of CategoryModel as a parent Category
		 */
		for (final CategoryModel supercategory : supercategories)
		{
			if (supercategory.getClass().equals(CategoryModel.class))
			{
				parentExternalId = StringEscapeUtils.escapeXml(supercategory.getCode());
			}

			// TODO: think of using classification categories
		}
		if (!(parentExternalId.equals("1") || parentExternalId.contains("/") || parentExternalId.equals("collections")))
		{
			resultTag.setParentExternalId((parentExternalId==null || StringEscapeUtils.escapeXml(parentExternalId).isEmpty()) ? null : StringEscapeUtils.escapeXml(parentExternalId));
		}

		//ImageUrl
		final MediaModel media = model.getPicture();
		final String imageUrl = (media != null) ? siteBaseUrlResolutionService.getMediaUrlForSite(cmsSiteModel, false,
				media.getDownloadURL()) : null;
		resultTag.setImageUrl((imageUrl==null || imageUrl.isEmpty()) ? null : imageUrl);

		//ImageUrls
		//
		//		List<LocalizedImageUrl> imageUrls = new ArrayList<>();
		//		 resultTag.setImageUrls(imageUrls);

		return resultTag;
	}

	public List<CategoryTag> getCategoryTagsFromCollection(final Collection<CategoryModel> categories,
			final Map<Locale, String> locales)
	{
		final List<CategoryTag> result = new ArrayList<CategoryTag>();
		for (final CategoryModel model : categories)
		{
			final CategoryTag tag = getCategoryTag(model, locales);
			result.add(tag);
		}
		return result;
	}

	public List<BrandTag> getBrandTagsFromCollection(final Collection<CategoryModel> brandModels, final Map<Locale, String> locales)
	{
		final List<BrandTag> result = new ArrayList<BrandTag>();
		for (final CategoryModel model : brandModels)
		{
			final BrandTag tag = getBrandTag(model, locales);
			result.add(tag);
		}
		return result;
	}

	public List<ProductTag> getProductTagsFromCollection(final Collection<ProductModel> productModels,
			final Map<Locale, String> locales)
	{
		return getProductTagsFromCollection(productModels, locales, false);
	}

	public List<ProductTag> getProductTagsFromCollection(final Collection<ProductModel> productModels,
			final Map<Locale, String> locales, final boolean isFamilyEnabled)
	{
		final List<ProductTag> result = new ArrayList<ProductTag>();
        Field[] fields = ProductModel.class.getDeclaredFields();
        boolean flag = false;
        if(upcMethodName!=null && !upcMethodName.isEmpty())
        {
            for(Field f:fields){
                f.setAccessible(true);
                if(f.getName().equalsIgnoreCase(upcMethodName)){
                    try {
                        flag=true;
                        upcMethodName = ((String)ProductModel.class.getField(f.getName()).get(f.getName())).substring(0, 1).toUpperCase() +
                                ((String)ProductModel.class.getField(f.getName()).get(f.getName())).substring(1);
                    } catch (NoSuchFieldException e) {
                        LOG.debug("UPC field not configured correctly. No such field exists. Please check the field. UPC will not print.");
                        e.printStackTrace();
                        upcMethodName=null;
                    } catch (IllegalAccessException e) {
                        LOG.debug("Unable to access UPC attribute. Please check UPC field in config.");
                        e.printStackTrace();
                        upcMethodName=null;
                    }
                    break;
                }
            }
        }
        if(!flag)
        {
            upcMethodName=null;
        }
		for (final ProductModel model : productModels)
		{
			final ProductTag tag = getProductTag(model, locales, isFamilyEnabled);
			result.add(tag);
		}
		return result;
	}

	private BrandTag getBrandTag(final CategoryModel model, final Map<Locale, String> locales)
	{

		final BrandTag tag = new BrandTag();
		tag.setName((model.getName()==null || model.getName().isEmpty())? null : model.getName());
		tag.setExternalId((model.getCode()==null || StringEscapeUtils.escapeXml(model.getCode()).isEmpty())? null : StringEscapeUtils.escapeXml(model.getCode()));

		//Localized brand names
		final List<LocalizedName> names = new ArrayList<LocalizedName>();
		for (final Locale locale : locales.keySet())
		{
			try
			{
				final String localName = model.getName(locale);
				final String normalizedLocale = locales.get(locale);
				if (localName != null && normalizedLocale != null)
				{
					final LocalizedName lname = new LocalizedName(normalizedLocale, localName);
					names.add(lname);
				}
			}
			catch (final Exception ex)
			{
				//LOG.debug(ex.getMessage());
				continue;
			}

		}
		tag.setNames(names.isEmpty() ? null : names);
		return tag;
	}

	private ProductTag getProductTag(final ProductModel model, final Map<Locale, String> locales)
	{
		return getProductTag(model, locales, false);
	}

	private ProductTag getProductTag(final ProductModel model, final Map<Locale, String> locales, final boolean isFamilyEnabled)
	{
		final ProductTag tag = new ProductTag();
		final String name = model.getName();
		tag.setName(name == null || name.isEmpty() ? model.getCode() : name);
		tag.setExternalId((model.getCode()==null || model.getCode().isEmpty()) ?  null : StringEscapeUtils.escapeXml(model.getCode()));

		//Localized brand names
		final List<LocalizedName> names = new ArrayList<LocalizedName>();
		for (final Locale locale : locales.keySet())
		{
			try
			{
				final String localName = model.getName(locale);
				final String normalizedLocale = locales.get(locale);
				if (localName != null && normalizedLocale != null)
				{
					final LocalizedName lname = new LocalizedName(normalizedLocale, localName);
					names.add(lname);
				}
			}
			catch (final Exception ex)
			{
				//LOG.debug(ex.getMessage());
				continue;
			}
		}
		tag.setNames(names.isEmpty() ? null : names);

		String brandExternalId = null;
		String categoryExternalId = null;
		final Collection<CategoryModel> superCategories = model.getSupercategories();
		for (final CategoryModel category : superCategories)
		{
			if (brands.contains(category))
			{
				brandExternalId = StringEscapeUtils.escapeXml(category.getCode());
			}
			else
			{
				categoryExternalId = StringEscapeUtils.escapeXml(category.getCode());
			}
		}
		tag.setBrandExternalId((brandExternalId==null || StringEscapeUtils.escapeXml(brandExternalId).isEmpty()) ? null : StringEscapeUtils.escapeXml(brandExternalId));
		tag.setCategoryExternalId((categoryExternalId==null || StringEscapeUtils.escapeXml(categoryExternalId).isEmpty())? null : StringEscapeUtils.escapeXml(categoryExternalId));
		//		final List<ProductReferenceModel> refs = (List<ProductReferenceModel>) model.getProductReferences();
		//		final String productPageUrl = (refs != null && !refs.isEmpty()) ? refs.get(0).toString() : null;
		//		tag.setProductPageUrl(normalizeText(host_url + productModelUrlResolver.resolve(model)));

		//Localized product page urls
		final Collection<String> availableValues = languageAttributeManager.getAllAvailableValues();
		final List<LocalizedName> productPageUrls = new ArrayList<LocalizedName>();
		//		LOG.debug("******************************************************");
		for (final String value : availableValues)
		{
			//			LOG.debug("languageAttributeManager value: " + value);
			try
			{
				final String normalizedLocale = locales.get(Locale.forLanguageTag(value));
				final String localizedProductPageUrl = siteBaseUrlResolutionService.getWebsiteUrlForSite(cmsSiteModel, "/" + value,
						false, productModelUrlResolver.resolve(model));
				if (localizedProductPageUrl != null && normalizedLocale != null)
				{
					final LocalizedName localizedUrl = new LocalizedName(normalizedLocale, localizedProductPageUrl);
					//LOG.debug("added localized productPageUrl: " + localizedProductPageUrl);
					productPageUrls.add(localizedUrl);
				}
			}
			catch (final Exception ex)
			{
				//LOG.debug(ex.getMessage());
				continue;
			}
		}
		tag.setProductPageUrls(productPageUrls.isEmpty() ? null : productPageUrls);

		final String productPageUrl = siteBaseUrlResolutionService.getWebsiteUrlForSite(cmsSiteModel, false,
				productModelUrlResolver.resolve(model));
		tag.setProductPageUrl((productPageUrl==null || productPageUrl.isEmpty())? null : productPageUrl);

		//CatalogVersionModel catalogVersionModel = model.getCatalogVersion();

		String imageUrlString = null;
		MediaModel media;
		media = model.getPicture();
		imageUrlString = (media != null) ? siteBaseUrlResolutionService.getMediaUrlForSite(cmsSiteModel, false, media.getURL())
				: null;

		/*
		 * if a variant product does not have an imageUrl - set it's parent's image url
		 */
		if ((imageUrlString == null || imageUrlString.isEmpty()) && model instanceof VariantProductModel)
		{
			ProductModel rootProduct = model;
			while (rootProduct instanceof VariantProductModel)
			{
				if (rootProduct.getPicture() != null)
				{
					media = rootProduct.getPicture();
					imageUrlString = (media != null) ? siteBaseUrlResolutionService.getMediaUrlForSite(cmsSiteModel, false,
							media.getURL()) : null;
					break;
				}
				rootProduct = ((VariantProductModel) rootProduct).getBaseProduct();
			}
		}
		tag.setImageUrl((imageUrlString==null || imageUrlString.isEmpty())? null : imageUrlString);

        final List<String> eans = new ArrayList<String>();
        if(model.getEan()!=null) {
            eans.add(StringEscapeUtils.escapeXml(model.getEan()));
        }
        tag.setEans(eans.isEmpty() ? null : eans);

        final List<String> manufacturerPartNumbers = new ArrayList<String>();
        if(model.getManufacturerAID()!=null) {
            manufacturerPartNumbers.add(StringEscapeUtils.escapeXml(model.getManufacturerAID()));
        }
        tag.setManufacturerPartNumbers(manufacturerPartNumbers.isEmpty() ? null : manufacturerPartNumbers);

        final List<String> upcs = new ArrayList<String>();
        if(upcMethodName!=null && !upcMethodName.isEmpty())
        {
            String upc = null;
            Method method = null;
            try {

                if(model.getClass().equals(ProductModel.class))
                {
                    method = model.getClass().getDeclaredMethod("get"+upcMethodName, null);
                    upc = (String)method.invoke(model, null);
                }
                else
                {
                    method = model.getClass().forName("de.hybris.platform.core.model.product.ProductModel").getDeclaredMethod("get" + upcMethodName, null);
                    upc = (String)method.invoke(model, null);
                }

            } catch (NoSuchMethodException e) {
                LOG.debug("No such method exists. Please ensure that attribute name is spelled correctly. Could not find: get" + upcMethodName);
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                LOG.debug("Invocation Error on: get" + upcMethodName);
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                LOG.debug("Unable to access: get" + upcMethodName);
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                LOG.debug("Please ensure that the model is part of de.hybris.platform.core.model.product.ProductModel hierarchy.");
                e.printStackTrace();
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
            if(upc!=null)
                upcs.add(StringEscapeUtils.escapeXml(upc));
        }

        tag.setUpcs(upcs.isEmpty() ? null : upcs);
		//TODO
		//		final String isbn = "unknown yet";
		//tag.setIsbn(isbn);

		String description = model.getDescription();
		if (description == null || description.isEmpty())
		{
			description = brandExternalId != null ? brandExternalId + " brand" : "empty description";
		}
		tag.setDescription((description==null || description.isEmpty())? null: description);

		final List<Description> descriptions = new ArrayList<Description>();
		for (final Locale loc : locales.keySet())
		{
			try
			{
				final String locName = model.getDescription(loc);
				final String normalizedLocale = locales.get(loc);
				if (locName != null && normalizedLocale != null)
				{
					final Description lname = new Description(normalizedLocale, locName);
					descriptions.add(lname);
				}
			}
			catch (final Exception ex)
			{
				continue;
			}
		}
		tag.setDescriptions(descriptions.isEmpty() ? null : descriptions);

		final List<String> isbns = new ArrayList<String>();
		tag.setIsbns(isbns.isEmpty() ? null : isbns);

		final List<String> modelNumbers = new ArrayList<String>();
		tag.setModelNumbers(modelNumbers.isEmpty() ? null : modelNumbers);

		final List<ProductDetailComponentModel> details = model.getProductDetailComponents();
		final List<Attribute> attributes = new ArrayList<>();
		for (final ProductDetailComponentModel detail : details)
		{
			attributes.add(new Attribute(detail.getName(), detail.getProduct().getDescription()));
		}

		// product families
		if (isFamilyEnabled)
		{
			// final List<String> list = new ArrayList<String>(); // old implementation
			ProductModel productModel = model;
			while (productModel instanceof VariantProductModel)
			{
				// list.add(productModel.getCode().replace(" ", "_")); // old implementation
				productModel = ((VariantProductModel) productModel).getBaseProduct();
			}
			final String familyName = productModel.getCode().replace(" ", "_");

			// Old implementation
			/*
			 * for (int rate = 0; rate < list.size(); rate++) { final StringBuilder value = new StringBuilder(); final int
			 * controlValue = list.size() - 1 - rate; for (int i = controlValue; i >= 0; i--) { if (list.size() > 1 && i !=
			 * controlValue) { value.append("_"); } value.append(list.get(i)); } attributes.add(new
			 * Attribute(BV_FAMILY_NAME_ATTRIBUTE_ID, value.toString())); }
			 */
			attributes.add(new Attribute(BV_FAMILY_NAME_ATTRIBUTE_ID, familyName));
			attributes.add(new Attribute(BV_FAMILY_EXPAND, BV_FAMILY_NAME_ATTRIBUTE_ID + ":" + familyName));
		}

		tag.setAttributes(attributes.isEmpty() ? null : attributes);
		return tag;
	}

	public String generateTodayFilename(final String filePrefix, final String dateNumber)
	{

		final String name = (filePrefix == null ? "product_feed" : filePrefix) + fileCounter + "_" + dateNumber;
		fileCounter++;
		return name;
	}

	public String gerenateDate()
	{
		final Date date = new Date(); // your date
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		final NumberFormat formatter = NumberFormat.getNumberInstance();
		formatter.setMinimumIntegerDigits(2);
		final String year = "" + cal.get(Calendar.YEAR);
		final String month = formatter.format(cal.get(Calendar.MONTH) + 1);
		final String day = formatter.format(cal.get(Calendar.DAY_OF_MONTH));
		final String hour = formatter.format(cal.get(Calendar.HOUR_OF_DAY));
		final String minute = formatter.format(cal.get(Calendar.MINUTE));
		final String secund = formatter.format(cal.get(Calendar.SECOND));
		final String dateNumber = year + month + day + hour + minute + secund;
		return dateNumber;
	}
}
