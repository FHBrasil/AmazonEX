/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package br.hering.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.core.HybrisEnumValue;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractFacetValueDisplayNameProvider;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import de.hybris.platform.util.localization.Localization;

import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import br.hering.core.strategies.impl.HeringKidsClothingFlagStrategy;


public class GenderFacetDisplayNameProvider extends AbstractFacetValueDisplayNameProvider
{
	private EnumerationService enumerationService;
	private I18NService i18nService;
	
	private CommonI18NService commonI18NService;
	
	@Resource
	private HeringKidsClothingFlagStrategy heringKidsClothingFlagStrategy;

	@Override
	public String getDisplayName(final SearchQuery query, final IndexedProperty property, final String facetValue)
	{
		if (facetValue == null)
		{
			return "";
		}

		final HybrisEnumValue genderEnumValue = getEnumerationService().getEnumerationValue(Gender.class, facetValue);

		Locale queryLocale = null;
		if (query == null || query.getLanguage() == null || query.getLanguage().isEmpty())
		{
			queryLocale = getI18nService().getCurrentLocale();
		}

		if (queryLocale == null && query != null)
		{
			queryLocale = getCommonI18NService().getLocaleForLanguage(getCommonI18NService().getLanguage(query.getLanguage()));
		}

		return getDisplayName(genderEnumValue, queryLocale, facetValue);
	}

	/**
	 * @param genderEnumValue
	 * @param queryLocale
	 * @param facetValue
	 * @return
	 */
	private String getDisplayName(HybrisEnumValue genderEnumValue, Locale queryLocale, String facetValue)
	{
		boolean isKidsOrBaby = facetValue.startsWith(GenderValueProvider.KIDS_GENDER_PREFIX) || facetValue.startsWith(GenderValueProvider.BABY_GENDER_PREFIX);
		
		if(isKidsOrBaby) 
		{
			return Localization.getLocalizedString(facetValue);
		}
		
		final String genderName = getEnumerationService().getEnumerationName(genderEnumValue, queryLocale);
		
		if (StringUtils.isNotBlank(genderName))
		{
			return genderName;
		}
		
		return facetValue;
	}

	protected EnumerationService getEnumerationService()
	{
		return enumerationService;
	}

	@Required
	public void setEnumerationService(final EnumerationService enumerationService)
	{
		this.enumerationService = enumerationService;
	}

	protected I18NService getI18nService()
	{
		return i18nService;
	}

	@Required
	public void setI18nService(final I18NService i18nService)
	{
		this.i18nService = i18nService;
	}

	protected CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	@Required
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}
}
