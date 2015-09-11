/**
 *
 */
package br.hering.heringstorefrontcommons.breadcrumb.impl;

import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.impl.ProductBreadcrumbBuilder;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.enumeration.EnumerationService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

import br.hering.core.model.HeringProductModel;


/**
 * @author franthescollymaneira
 *
 */
public class HeringProductBreadcrumbBuilder extends ProductBreadcrumbBuilder
{
	@Resource
	private EnumerationService enumerationService;

	private final Logger LOG = Logger.getLogger(HeringSearchBreadcrumbBuilder.class);

	private static final String[] ACESSORIOS =
	{ "039", "042", "043", "044", "045", "050", "056", "081", "084", "093", "385" };

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.impl.ProductBreadcrumbBuilder#getBreadcrumbs(de.hybris
	 * .platform.core.model.product.ProductModel)
	 */
	@Override
	public List<Breadcrumb> getBreadcrumbs(final ProductModel productModel) throws IllegalArgumentException
	{
		final List<Breadcrumb> breadcrumbs = new ArrayList<Breadcrumb>();

		final Gender gender = getGender(getBaseProduct(productModel));

		if (gender != null)
		{
			final String genderName = enumerationService.getEnumerationName(gender);
			final String displayName = WordUtils.capitalize(genderName);

			final String link = "/search?q=" + getEncodedUrl(":relevance:gender:" + gender.getCode());

			breadcrumbs.add(new Breadcrumb(link, displayName, ""));
		}

		breadcrumbs.addAll(super.getBreadcrumbs(productModel));

		removeCategoryBreadcrumb(breadcrumbs);

		fixAccessoriesCategory(breadcrumbs);

		return breadcrumbs;
	}

	/**
	 *
	 * @param productModel
	 * @return
	 */
	private Gender getGender(final ProductModel productModel)
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

	protected String getEncodedUrl(final String url)
	{
		try
		{
			return URLEncoder.encode(url, "utf-8");
		}
		catch (final UnsupportedEncodingException e)
		{
			return url;
		}
	}

	/**
	 * Removes the category breadcrumb from breadcrumbs array.
	 *
	 * @param breadcrumbs
	 */
	private void removeCategoryBreadcrumb(final List<Breadcrumb> breadcrumbs)
	{
		if (CollectionUtils.isEmpty(breadcrumbs))
		{
			return;
		}

		try
		{
			final Iterator<Breadcrumb> iterator = breadcrumbs.iterator();
			while (iterator.hasNext())
			{
				if (StringUtils.contains(iterator.next().getUrl(), "/categories"))
				{
					iterator.remove();
				}
			}
		}
		catch (final Exception e)
		{
			LOG.error("error", e);
		}
	}

	/**
	 * Fix for accessories category to include "relevance" in menu items, special case for accessories to not show on
	 * other menu options
	 *
	 * @param breadcrumbs
	 */
	private void fixAccessoriesCategory(final List<Breadcrumb> breadcrumbs)
	{
		if (CollectionUtils.isEmpty(breadcrumbs))
		{
			return;
		}

		for (final Breadcrumb breadcrumb : breadcrumbs)
		{
			for (final String category : ACESSORIOS)
			{
				if (StringUtils.contains(breadcrumb.getUrl(), "/c/" + category))
				{
					breadcrumb.setUrl(breadcrumb.getUrl() + "?q=:relevance:grupo:Acess%C3%B3rios");
					break;
				}
			}
		}
	}
}