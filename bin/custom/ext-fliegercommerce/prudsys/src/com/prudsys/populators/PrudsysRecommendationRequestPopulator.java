/**
 *
 */
package com.prudsys.populators;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.Assert;

import com.flieger.recommendation.data.RecommendationRequest;
import com.prudsys.data.PrudsysBasketRecommendationRequest;
import com.prudsys.data.PrudsysBrandRecommendationRequest;
import com.prudsys.data.PrudsysCategoryRecommendationRequest;
import com.prudsys.data.PrudsysHomePageRecommendationRequest;
import com.prudsys.data.PrudsysProductRecommendationRequest;
import com.prudsys.data.PrudsysRecommendationRequest;


/**
 * @author franthescollymaneira
 *
 */
public class PrudsysRecommendationRequestPopulator implements Populator<RecommendationRequest, PrudsysRecommendationRequest>
{
	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final RecommendationRequest source, final PrudsysRecommendationRequest target) throws ConversionException
	{
		Assert.notNull(source);
		Assert.notNull(target);
		Assert.notEmpty(source.getValues());

		target.setSessionId(this.<String> getValue(source, "sessionId"));

		if (target instanceof PrudsysBrandRecommendationRequest)
		{
			final CategoryModel brandModel = this.<CategoryModel> getValue(source, "brand");
			((PrudsysBrandRecommendationRequest) target).setBrandId(brandModel.getCode());
		}

		if (target instanceof PrudsysCategoryRecommendationRequest)
		{
			final CategoryModel categoryModel = this.<CategoryModel> getValue(source, "category");
			((PrudsysCategoryRecommendationRequest) target).setCategoryId(categoryModel.getCode());
		}

		if (target instanceof PrudsysProductRecommendationRequest)
		{
			final ProductModel productModel = this.<ProductModel> getValue(source, "product");
			final List<ProductModel> blacklist = this.<List<ProductModel>> getValue(source, "blacklist");
			final List<String> codeList = extractProductCodeFromProducts(blacklist);

			((PrudsysProductRecommendationRequest) target).setBlackList(codeList);
			((PrudsysProductRecommendationRequest) target).setProductId(productModel.getCode());
		}

		if (target instanceof PrudsysBasketRecommendationRequest)
		{
			final List<OrderEntryModel> basketEntries = this.<List<OrderEntryModel>> getValue(source, "basketEntries");
			final List<String> codeList = extractProductCodeFromOrderEntries(basketEntries);

			((PrudsysBasketRecommendationRequest) target).setProductList(codeList);
		}

		if (target instanceof PrudsysHomePageRecommendationRequest)
		{
			final List<ProductModel> blacklist = this.<List<ProductModel>> getValue(source, "blacklist");
			final List<String> codeList = extractProductCodeFromProducts(blacklist);

			((PrudsysHomePageRecommendationRequest) target).setBlackList(codeList);
		}
	}

	private List<String> extractProductCodeFromOrderEntries(final List<OrderEntryModel> entries)
	{
		if (CollectionUtils.isEmpty(entries))
		{
			return Collections.emptyList();
		}

		final List<String> codeList = new ArrayList<String>();
		for (final OrderEntryModel entry : entries)
		{
			codeList.add(entry.getProduct().getCode());
		}
		return codeList;
	}

	private List<String> extractProductCodeFromProducts(final List<ProductModel> products)
	{
		if (CollectionUtils.isEmpty(products))
		{
			return Collections.emptyList();
		}

		final List<String> codeList = new ArrayList<String>();
		for (final ProductModel product : products)
		{
			codeList.add(product.getCode());
		}
		return codeList;
	}

	private <T> T getValue(final RecommendationRequest source, final String key)
	{
		return (T) source.getValues().get(key);
	}
}