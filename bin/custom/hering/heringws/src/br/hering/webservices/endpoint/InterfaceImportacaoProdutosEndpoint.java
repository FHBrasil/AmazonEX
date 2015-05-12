/**
 *
 */
package br.hering.webservices.endpoint;

import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.catalog.model.classification.ClassificationClassModel;
import de.hybris.platform.catalog.model.classification.ClassificationSystemVersionModel;
import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.classification.ClassificationService;
import de.hybris.platform.classification.ClassificationSystemService;
import de.hybris.platform.classification.features.Feature;
import de.hybris.platform.classification.features.FeatureList;
import de.hybris.platform.classification.features.FeatureValue;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.product.VariantsService;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.variants.model.VariantProductModel;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.hering.core.model.HeringProductModel;
import br.hering.core.model.HeringSizeVariantProductModel;
import br.hering.core.model.HeringStyleVariantProductModel;
import br.hering.webservices.message.product.Cor;
import br.hering.webservices.message.product.ImportarProdutoRequest;
import br.hering.webservices.message.product.Produto;
import br.hering.webservices.message.product.Variacao;


/**
 * @author franthescollymaneira
 * 
 */
@Endpoint
public class InterfaceImportacaoProdutosEndpoint extends AbstractInterfaceEndpoint<ImportarProdutoRequest>
{
	@Resource
	private ProductService productService;

	@Resource
	private CategoryService categoryService;

	@Resource
	private VariantsService variantsService;

	@Resource
	private ClassificationSystemService classificationSystemService;

	@Resource
	private ClassificationService classificationService;

	private final Map<String, Gender> genderMapping;

	{
		genderMapping = new HashMap<String, Gender>();

		genderMapping.put("I", Gender.UNDEFINED);
		genderMapping.put("M", Gender.MALE);
		genderMapping.put("F", Gender.FEMALE);
		genderMapping.put("U", Gender.UNISEX);
	}

	@Override
	@ResponsePayload
	@PayloadRoot(localPart = "importarProdutoRequest", namespace = namespaceUri)
	public void service(@RequestPayload final ImportarProdutoRequest request)
	{
		try
		{
			LOG.info("Iniciando importação de produtos");

			prepareCurrentSession(request.getProduto().getCodigoSite(), false);
			CatalogVersionModel catalogVersion = getCatalogVersionByStore(request.getProduto().getCodigoSite(), false);

			final Produto produtoReq = request.getProduto();

			HeringProductModel baseProduct = getOrCreateBaseProduct(produtoReq, catalogVersion);

			baseProduct.setName(produtoReq.getTitulo());
			baseProduct.setDescription(produtoReq.getDescricaoB2C());
			addGenderInfo(baseProduct, produtoReq);
			addCategoryInfo(baseProduct, catalogVersion, produtoReq.getCodigoGrupo());

			modelService.save(baseProduct);
			modelService.refresh(baseProduct);

			if (CollectionUtils.isEmpty(request.getVariacao()))
			{
				throw new Exception("SKU's list is empty");
			}

			for (final Variacao variacaoReq : request.getVariacao())
			{
				try
				{
					HeringStyleVariantProductModel style = findOrCreateStyleVariant(produtoReq, variacaoReq, baseProduct,
							catalogVersion);

					style.setName(produtoReq.getTitulo());
					style.setDescription(produtoReq.getDescricaoB2C());
					style.setBaseProduct(baseProduct);

					addCategoryInfo(style, catalogVersion, produtoReq.getCodigoGrupo());
					modelService.save(style);
					modelService.refresh(style);

					HeringSizeVariantProductModel size = findOrCreateSizeVariant(produtoReq, variacaoReq, baseProduct, catalogVersion);

					size.setName(produtoReq.getTitulo());
					size.setDescription(produtoReq.getDescricaoB2C());
					size.setSize(variacaoReq.getTamanho());
					size.setBaseProduct(style);
					size.setPosition(variacaoReq.getOrdem());
					size.setOnlineDate(parseDate(variacaoReq.getDataOnline()));
					size.setOfflineDate(parseDate(variacaoReq.getDataOffline()));
					size.setStyle(variacaoReq.getCor().getDescricaoCorBasica());

					addCategoryInfo(size, catalogVersion, produtoReq.getCodigoGrupo());
					addClassificationInfo(size, produtoReq, variacaoReq);

					modelService.save(size);
					modelService.refresh(size);
				}
				catch (final Exception e)
				{
					LOG.error("Error: ", e);
				}
			}

			modelService.refresh(baseProduct);
			setBaseOnlineDate(baseProduct);
			setBaseOfflineDate(baseProduct);
		}
		catch (final Exception e)
		{
			LOG.error("Error: ", e);

			final Produto produtoReq = request.getProduto();
			createErrorMessageList(CHAVE_PRODUTO + "=" + produtoReq.getCodigo(), e.getMessage());
		}
	}

	private void setBaseOnlineDate(final ProductModel product)
	{
		if (CollectionUtils.isEmpty(product.getVariants()))
		{
			return;
		}

		if (product instanceof HeringProductModel)
		{
			for (final VariantProductModel variant : product.getVariants())
			{
				setBaseOnlineDate(variant);
			}
		}

		final Date minOnlineDate = getMinOnlineDate(product.getVariants());
		product.setOnlineDate(minOnlineDate);

		LOG.info("result: " + product.getCode() + " : ON " + minOnlineDate);

		modelService.save(product);
		modelService.refresh(product);
	}

	private void setBaseOfflineDate(final ProductModel product)
	{
		if (CollectionUtils.isEmpty(product.getVariants()))
		{
			return;
		}

		if (product instanceof HeringProductModel)
		{
			for (final VariantProductModel variant : product.getVariants())
			{
				setBaseOfflineDate(variant);
			}
		}

		final Date maxOfflineDate = getMaxOfflineDate(product.getVariants());
		product.setOfflineDate(maxOfflineDate);

		LOG.info("result: " + product.getCode() + " : OFF " + maxOfflineDate);

		modelService.save(product);
		modelService.refresh(product);
	}

	/**
	 * @param variants
	 * @return
	 */
	private Date getMinOnlineDate(final Collection<VariantProductModel> variants)
	{
		if (CollectionUtils.isEmpty(variants))
		{
			return null;
		}

		Date min = null;
		for (final VariantProductModel variant : variants)
		{
			final Date onlineDate = variant.getOnlineDate();

			LOG.info(variant.getCode() + " : ON " + onlineDate);

			if (onlineDate == null)
			{
				return null;
			}

			if (min == null || onlineDate.before(min))
			{
				min = onlineDate;
			}
		}

		return min;
	}

	/**
	 * @param variants
	 * @return
	 */
	private Date getMaxOfflineDate(final Collection<VariantProductModel> variants)
	{
		if (CollectionUtils.isEmpty(variants))
		{
			return null;
		}

		Date max = null;
		for (final VariantProductModel variant : variants)
		{
			final Date offlineDate = variant.getOfflineDate();
			LOG.info(variant.getCode() + " : OFF " + offlineDate);

			if (offlineDate == null)
			{
				return null;
			}

			if (max == null || offlineDate.after(max))
			{
				max = offlineDate;
			}
		}

		return max;
	}

	/**
	 * @param baseProduct
	 * @param produtoReq
	 */
	private void addGenderInfo(final HeringProductModel baseProduct, final Produto produtoReq)
	{
		final Set<Gender> genders = new HashSet<Gender>();
		if (baseProduct.getGenders() != null)
		{
			genders.addAll(baseProduct.getGenders());
		}

		genders.add(genderMapping.get(produtoReq.getSexo()));

		baseProduct.setGenders(new ArrayList<Gender>(genders));
	}

	/**
	 * 
	 * @param produtoRequest
	 * @param catalogVersion
	 * @return
	 */
	private HeringProductModel getOrCreateBaseProduct(final Produto produtoRequest, final CatalogVersionModel catalogVersion)
	{
		try
		{
			return (HeringProductModel) productService.getProductForCode(catalogVersion, produtoRequest.getCodigo());
		}
		catch (final UnknownIdentifierException e)
		{
			synchronized (this)
			{
				try
				{
					return (HeringProductModel) productService.getProductForCode(catalogVersion, produtoRequest.getCodigo());
				}
				catch (final UnknownIdentifierException ie)
				{
					LOG.info("Creating new base product for code: " + produtoRequest.getCodigo());

					final HeringProductModel baseProduct = modelService.create(HeringProductModel.class);
					baseProduct.setCode(produtoRequest.getCodigo());
					baseProduct.setCatalogVersion(catalogVersion);
					baseProduct.setApprovalStatus(ArticleApprovalStatus.APPROVED);
					baseProduct.setVariantType(variantsService.getVariantTypeForCode("HeringStyleVariantProduct"));

					modelService.save(baseProduct);
					modelService.refresh(baseProduct);

					return baseProduct;
				}
			}
		}
	}

	/**
	 * 
	 * @param variacaoRequest
	 * @param catalogVersion
	 * @return
	 */
	private HeringStyleVariantProductModel findOrCreateStyleVariant(final Produto productRequest, final Variacao variacaoRequest,
			final HeringProductModel baseProduct, final CatalogVersionModel catalogVersion)
	{
		final String code = productRequest.getCodigo() + "_" + variacaoRequest.getCor().getCodigo();

		try
		{
			final HeringStyleVariantProductModel styleVariant = (HeringStyleVariantProductModel) productService.getProductForCode(
					catalogVersion, code);
			if (!styleVariant.getStyle().equals(variacaoRequest.getCor().getDescricaoCorBasica()))
			{
				styleVariant.setStyle(variacaoRequest.getCor().getDescricaoCorBasica());
				modelService.save(styleVariant);
				modelService.refresh(styleVariant);
			}
			return styleVariant;
		}
		catch (final UnknownIdentifierException e)
		{
			synchronized (this)
			{
				try
				{
					final HeringStyleVariantProductModel styleVariant = (HeringStyleVariantProductModel) productService
							.getProductForCode(catalogVersion, code);
					if (!styleVariant.getStyle().equals(variacaoRequest.getCor().getDescricaoCorBasica()))
					{
						styleVariant.setStyle(variacaoRequest.getCor().getDescricaoCorBasica());
						modelService.save(styleVariant);
						modelService.refresh(styleVariant);
					}
					return styleVariant;
				}
				catch (final UnknownIdentifierException ie)
				{
					LOG.info("Creating new style variant for code: " + code);

					final HeringStyleVariantProductModel styleVariant = modelService.create(HeringStyleVariantProductModel.class);
					styleVariant.setCode(code);
					styleVariant.setCatalogVersion(catalogVersion);
					styleVariant.setApprovalStatus(ArticleApprovalStatus.APPROVED);
					styleVariant.setVariantType(variantsService.getVariantTypeForCode("HeringSizeVariantProduct"));
					styleVariant.setStyle(variacaoRequest.getCor().getDescricaoCorBasica());
					styleVariant.setBaseProduct(baseProduct);
					modelService.save(styleVariant);
					modelService.refresh(styleVariant);

					return styleVariant;
				}
			}
		}
	}

	/**
	 * @param produtoRequest
	 * @param variacaoRequest
	 * @param catalogVersion
	 * @return
	 */
	private HeringSizeVariantProductModel findOrCreateSizeVariant(final Produto produtoRequest, final Variacao variacaoRequest,
			final HeringProductModel baseProduct, final CatalogVersionModel catalogVersion)
	{
		final String code = variacaoRequest.getSku();

		try
		{
			return (HeringSizeVariantProductModel) productService.getProductForCode(catalogVersion, code);
		}
		catch (final UnknownIdentifierException e)
		{
			synchronized (this)
			{
				try
				{
					return (HeringSizeVariantProductModel) productService.getProductForCode(catalogVersion, code);
				}
				catch (final UnknownIdentifierException ie)
				{
					LOG.info("Creating new size variant for code: " + code);

					final HeringSizeVariantProductModel sizeVariant = modelService.create(HeringSizeVariantProductModel.class);
					sizeVariant.setCode(code);
					sizeVariant.setEan(variacaoRequest.getEan());
					sizeVariant.setCatalogVersion(catalogVersion);
					sizeVariant.setApprovalStatus(ArticleApprovalStatus.APPROVED);
					sizeVariant.setBaseProduct(baseProduct);

					modelService.save(sizeVariant);
					modelService.refresh(sizeVariant);

					return sizeVariant;
				}
			}
		}
	}

	/**
	 * 
	 * @param size
	 * @param codes
	 */
	private void addCategoryInfo(final ProductModel size, CatalogVersionModel catalogVersion, final String... codes)
	{
		final Set<CategoryModel> categories = new LinkedHashSet<CategoryModel>();

		if (CollectionUtils.isNotEmpty(size.getSupercategories()))
		{
			for (final CategoryModel c : size.getSupercategories())
			{
				if (c instanceof ClassificationClassModel)
				{
					categories.add(c);
				}
			}
		}

		for (final String categoryCode : codes)
		{
			addNewAndNotNull(categories, categoryCode, catalogVersion);
		}

		final ArrayList<CategoryModel> categoriesList = new ArrayList<CategoryModel>(categories);
		Collections.reverse(categoriesList);

		size.setSupercategories(categoriesList);
	}

	/**
	 * 
	 * @param product
	 * @param prodReq
	 * @param variacaoReq
	 */
	private void addClassificationInfo(final ProductModel product, final Produto prodReq, final Variacao variacaoReq)
	{
		final ClassificationSystemVersionModel version = classificationSystemService.getSystemVersion("HeringCoClassification",
				"1.0");
		final String colecao = prodReq.getColecao() != null ? prodReq.getColecao().getDescricao() : null;
		final String peso = variacaoReq != null ? String.valueOf(variacaoReq.getPeso()) : null;

		final Map<String, String> grouping = new HashMap<String, String>();
		grouping.put("Grupo", prodReq.getCodigoCategoria());
		grouping.put("Subgrupo", prodReq.getCodigoSubCategoria());
		grouping.put("Griffe", prodReq.getGriffe());
		grouping.put("Linha", prodReq.getLinha());
		grouping.put("Subcategoria", prodReq.getCodigoSubGrupo());

		final Map<String, String> materials = new HashMap<String, String>();
		materials.put("Tipo tecido", prodReq.getTipoTecido());
		materials.put("Composição", prodReq.getComposicao());
		materials.put("Parte", prodReq.getParte());

		final Map<String, String> colors = new HashMap<String, String>();
		if (variacaoReq != null)
		{
			final Cor cor = variacaoReq.getCor();
			colors.put("Código cor", cor.getCodigo());
			colors.put("Descrição cor", cor.getDescricao());
			colors.put("Descrição cor básica", cor.getDescricaoCorBasica());
			colors.put("RGB", cor.getRgb());
		}

		//		final Map<String, String> ocasiao = new HashMap<String, String>();
		//		ocasiao.put("Ocasiao", prodReq.getOcasiao());
		//
		//		final Map<String, String> tendencia = new HashMap<String, String>();
		//		tendencia.put("Tendencia", prodReq.getTendencia());
		//
		//		final Map<String, String> tipoMaterial = new HashMap<String, String>();
		//		tipoMaterial.put("TipoMaterial", prodReq.getTipoMaterial());


		final Map<String, Map<String, String>> class2features = new HashMap<String, Map<String, String>>();
		class2features.put("0001", grouping);
		class2features.put("0002", Collections.singletonMap("Coleção", colecao));
		class2features.put("0003", materials);
		class2features.put("0004", Collections.singletonMap("Peso", peso));
		class2features.put("0005", Collections.singletonMap("Tag", prodReq.getTags()));
		class2features.put("0006", colors);
		//		class2features.put("0007", ocasiao);
		//		class2features.put("0008", tendencia);
		//		class2features.put("0009", tipoMaterial);

		for (final String code : class2features.keySet())
		{
			addClassificationClass(product, version, code);

			final Map<String, String> features = class2features.get(code);

			for (final String name : features.keySet())
			{
				addFeatureValue(product, name, features.get(name));
			}
		}
	}

	/**
	 * @param product
	 * @param version
	 * @param classCode
	 */
	private void addClassificationClass(final ProductModel product, final ClassificationSystemVersionModel version,
			final String classCode)
	{
		try
		{
			final ClassificationClassModel classificationClass = classificationSystemService.getClassForCode(version, classCode);

			final List<ClassificationClassModel> classes = product.getClassificationClasses();

			if (CollectionUtils.isEmpty(classes) || !classes.contains(classificationClass))
			{
				LOG.info("Adding " + classificationClass.getCode() + " to " + product.getCode());

				final Set<CategoryModel> categories = new LinkedHashSet<CategoryModel>();

				if (CollectionUtils.isNotEmpty(product.getSupercategories()))
				{
					categories.addAll(product.getSupercategories());
				}

				categories.add(classificationClass);

				product.setSupercategories(categories);

				modelService.save(product);
				modelService.refresh(product);
			}
		}
		catch (final ModelSavingException e)
		{
			LOG.error("error", e);
			createErrorMessageList(CHAVE_PRODUTO + "=" + product.getCode(), e.getMessage());
		}
	}

	/**
	 * 
	 * @param product
	 * @param featureName
	 * @param value
	 */
	private void addFeatureValue(final ProductModel product, final String featureName, final String value)
	{
		final FeatureList featureList = classificationService.getFeatures(product);

		final Feature feature = featureList.getFeatureByName(featureName);

		if (feature == null)
		{
			throw new InvalidParameterException(featureName + " not found");
		}

		if (StringUtils.isBlank(value))
		{
			return;
			//throw new InvalidParameterException(featureName + " value is empty");
		}

		final FeatureValue featureValue = feature.getValue();

		if (featureValue == null || value.compareTo(featureValue.getValue().toString()) != 0)
		{
			LOG.info(product.getCode() + " updating " + featureName + " to " + value);

			if (featureValue == null)
			{
				feature.addValue(new FeatureValue(value));
			}
			else
			{
				featureValue.setValue(value);
			}

			classificationService.setFeatures(product, featureList);
		}
	}

	private void addNewAndNotNull(final Set<CategoryModel> categories, final String categoryCode,
			CatalogVersionModel catalogVersion)
	{
		if (StringUtils.isBlank(categoryCode))
		{
			LOG.info("categoryCode is empty");
			return;
		}

		try
		{
			final CategoryModel category = categoryService.getCategoryForCode(catalogVersion, categoryCode);

			if (!categories.contains(category))
			{
				categories.add(category);
			}
		}
		catch (final UnknownIdentifierException e)
		{
			throw new UnknownIdentifierException("category not found: " + categoryCode, e);
		}
	}
}