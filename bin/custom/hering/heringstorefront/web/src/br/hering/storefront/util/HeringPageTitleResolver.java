/**
 * 
 */
package br.hering.storefront.util;

import de.hybris.platform.acceleratorservices.storefront.util.PageTitleResolver;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.commerceservices.search.facetdata.BreadcrumbData;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

import br.hering.core.model.HeringProductModel;

/**
 * @author herbert
 *
 */
public class HeringPageTitleResolver extends PageTitleResolver
{
	@Resource
	private EnumerationService enumerationService;

	@Override
	public <STATE> String resolveCategoryPageTitle(final CategoryModel category, final List<BreadcrumbData<STATE>> appliedFacets)
	{
		if(CollectionUtils.isEmpty(appliedFacets)) {
			return super.resolveCategoryPageTitle(category);
		} else {
			return new StringBuilder().append(category.getName()).append(TITLE_WORD_SEPARATOR).append(super.resolveCategoryPageTitle(category, appliedFacets)).toString();
		}
	}

	@Override
	public String resolveProductPageTitle(final ProductModel product)
	{
		final CMSSiteModel currentSite = getCmsSiteService().getCurrentSite();
		final CategoryModel category = getPrimaryCategoryForProduct(product);
		final Gender gender = getGender(getBaseProduct(product));

		final String identifier = product.getName();
		final String articleNumber = product.getCode();
		final String productName = StringUtils.isEmpty(identifier) ? articleNumber : identifier;
		final StringBuilder builder = new StringBuilder(productName);

		if(category != null)
			builder.append(TITLE_WORD_SEPARATOR).append(category.getName());
		if(gender != null)
			builder.append(TITLE_WORD_SEPARATOR).append(WordUtils.capitalize(enumerationService.getEnumerationName(gender)));
		if (currentSite != null)
			builder.append(TITLE_WORD_SEPARATOR).append(currentSite.getName());

		return builder.toString();
	}

	protected ProductModel getBaseProduct(ProductModel product)
	{
		while(product instanceof VariantProductModel)
		{
			product = ((VariantProductModel) product).getBaseProduct();
		}
		return product;
	}

	protected Gender getGender(final ProductModel productModel)
	{
		if (productModel == null || !(productModel instanceof HeringProductModel))
		{
			return null;
		}

		final HeringProductModel heringProductModel = (HeringProductModel) productModel;

		if (CollectionUtils.isEmpty(heringProductModel.getGenders()))
		{
			return null;
		}

		return heringProductModel.getGenders().iterator().next();
	}
}
