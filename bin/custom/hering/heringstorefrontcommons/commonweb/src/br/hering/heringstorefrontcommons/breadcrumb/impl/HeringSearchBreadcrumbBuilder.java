/**
 *
 */
package br.hering.heringstorefrontcommons.breadcrumb.impl;

import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.impl.SearchBreadcrumbBuilder;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData;
import de.hybris.platform.core.HybrisEnumValue;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.enumeration.EnumerationService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;


/**
 * @author franthescollymaneira
 *
 */
public class HeringSearchBreadcrumbBuilder extends SearchBreadcrumbBuilder
{
	private final Logger LOG = Logger.getLogger(HeringSearchBreadcrumbBuilder.class);

	@Resource
	private EnumerationService enumerationService;

	@Override
	public List<Breadcrumb> getBreadcrumbs(final String categoryCode,
			final ProductSearchPageData<SearchStateData, ProductData> searchPageData) throws IllegalArgumentException
	{
		final boolean emptyBreadcrumbs = CollectionUtils.isEmpty(searchPageData.getBreadcrumbs());
		final String searchText = searchPageData.getFreeTextSearch();
		final SearchStateData currentQuery = searchPageData.getCurrentQuery();

		return getBreadcrumbs(categoryCode, searchText, emptyBreadcrumbs, currentQuery.getUrl());
	}

	public List<Breadcrumb> getBreadcrumbs(final String categoryCode, final String searchText, final boolean emptyBreadcrumbs,
			final String url) throws IllegalArgumentException
	{
		final List<Breadcrumb> breadcrumbs = new ArrayList<Breadcrumb>();

		final Breadcrumb breadcrumb = getBreadcrumbFromURL(url);
		if (breadcrumb != null)
		{
			breadcrumbs.add(breadcrumb);
		}

		breadcrumbs.addAll(super.getBreadcrumbs(categoryCode, searchText, emptyBreadcrumbs));

		removeEmptyBreadcrumbs(breadcrumbs);
		removeCategoryBreadcrumb(breadcrumbs);

		return breadcrumbs;
	}

	/**
	 * @param breadcrumbs
	 */
	private void removeEmptyBreadcrumbs(final List<Breadcrumb> breadcrumbs)
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
				if (StringUtils.isBlank(iterator.next().getName()))
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
	 *
	 * @param url
	 * @return
	 */
	private Breadcrumb getBreadcrumbFromURL(final String url)
	{
		if (StringUtils.isBlank(url) || !url.contains("relevance"))
		{
			return null;
		}

		try
		{
			final String sanitizedURL = URLDecoder.decode(url, "utf-8");
			final int beginIndex = sanitizedURL.indexOf("relevance") + 9;
			final StringTokenizer tokenizer = new StringTokenizer(sanitizedURL.substring(beginIndex), ":");

			if (!tokenizer.hasMoreElements())
			{
				return null;
			}

			final String link = "/search?q=";
			final String token = tokenizer.nextToken(":");

			if (token.equals("gender"))
			{
				String genderCode = tokenizer.nextToken();

				final HybrisEnumValue gender = enumerationService.getEnumerationValue(Gender.class,genderCode.contains(".") == true ? genderCode.split("\\.")[1] : genderCode);
				final String name = enumerationService.getEnumerationName(gender);

				return createBreadcrumbBean(link, token, name, genderCode);

			}

			if (token.equals("grupo"))
			{
				return createBreadcrumbBean(link, token, null, tokenizer.nextToken());
			}

			if (token.equals("jeans"))
			{
				return createBreadcrumbBean(link, token, "Jeans", tokenizer.nextToken());
			}

			if (token.equals("sales"))
			{
				return createBreadcrumbBean(link, token, "Sales", tokenizer.nextToken());
			}
		}
		catch (final UnsupportedEncodingException e)
		{
			//
		}

		return null;
	}

	private Breadcrumb createBreadcrumbBean(final String link, final String filter, final String name, final String tokenVal)
	{
		final String url = link + getEncodedUrl(":relevance:" + filter + ":" + tokenVal);
		final String displayName = StringUtils.isBlank(name) ? WordUtils.capitalize(tokenVal.toLowerCase()) : name;

		return new Breadcrumb(url, displayName, "");
	}
}