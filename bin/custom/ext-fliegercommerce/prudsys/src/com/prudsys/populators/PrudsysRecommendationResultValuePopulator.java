/**
 *
 */
package com.prudsys.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;

import com.flieger.recommendation.data.RecommendationResultValueData;
import com.prudsys.data.PrudsysRecommendationResponse;


/**
 * @author franthescollymaneira
 *
 */
public class PrudsysRecommendationResultValuePopulator implements
		Populator<PrudsysRecommendationResponse, RecommendationResultValueData>
{
	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final PrudsysRecommendationResponse source, final RecommendationResultValueData target)
			throws ConversionException
	{
		Assert.notNull(source);
		Assert.notNull(target);

		final Map<String, Object> values = new HashMap<String, Object>();

		values.put("template", source.getTemplate());
		values.put("boxId", source.getBoxId());
		values.put("netUnitPrice", source.getNetUnitPrice());
		values.put("onlineFlag", Boolean.valueOf(source.isOnlineFlag()));
		values.put("reward", source.getReward());
		values.put("reason", source.getReason());
		values.put("sku", source.getSku());
		values.put("strikeOutPrice", source.getStrikeOutPrice());
		values.put("productNr", source.getProductNr());
		values.put("netPurchasePrice", source.getNetPurchasePrice());
		values.put("quantityUnit", source.getQuantityUnit());
		values.put("uid", source.getUid());
		values.put("description", source.getDescription());
		values.put("manufacturer", source.getManufacturer());
		values.put("name", source.getName());
		values.put("brand", source.getBrand());
		values.put("url", source.getUrl());
		values.put("imageUrl", source.getImageUrl());

		target.setValues(values);
	}
}