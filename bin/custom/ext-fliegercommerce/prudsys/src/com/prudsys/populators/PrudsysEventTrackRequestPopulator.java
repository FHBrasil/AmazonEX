/**
 *
 */
package com.prudsys.populators;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.flieger.eventtracking.data.EventTrackRequest;
import com.prudsys.data.PrudsysBasketEventTrackRequest;
import com.prudsys.data.PrudsysCategoryViewEventTrackRequest;
import com.prudsys.data.PrudsysClickEventTrackRequest;
import com.prudsys.data.PrudsysEventTrackRequest;
import com.prudsys.data.PrudsysOrderEventTrackRequest;
import com.prudsys.data.PrudsysProductViewEventTrackRequest;
import com.prudsys.data.PrudsysUserToSessionEventTrackRequest;


/**
 * @author franthescollymaneira
 *
 */
public class PrudsysEventTrackRequestPopulator implements Populator<EventTrackRequest, PrudsysEventTrackRequest>
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final EventTrackRequest source, final PrudsysEventTrackRequest target) throws ConversionException
	{
		Assert.notNull(source);
		Assert.notNull(target);
		Assert.notEmpty(source.getValues());

		target.setSessionId(this.<String> getValue(source, "sessionId"));

		if (target instanceof PrudsysProductViewEventTrackRequest)
		{
			final ProductModel productModel = this.<ProductModel> getValue(source, "product");
			((PrudsysProductViewEventTrackRequest) target).setProductId(productModel.getCode());
		}

		if (target instanceof PrudsysCategoryViewEventTrackRequest)
		{
			final CategoryModel categoryModel = this.<CategoryModel> getValue(source, "category");
			((PrudsysCategoryViewEventTrackRequest) target).setCategoryId(categoryModel.getCode());
		}

		if (target instanceof PrudsysClickEventTrackRequest)
		{
			((PrudsysClickEventTrackRequest) target).setBox(this.<String> getValue(source, "box"));
			((PrudsysClickEventTrackRequest) target).setTemplate(this.<String> getValue(source, "template"));
			((PrudsysClickEventTrackRequest) target).setItemId(this.<String> getValue(source, "itemId"));
		}

		if (target instanceof PrudsysBasketEventTrackRequest)
		{
			final List<String> itemids = new ArrayList<String>();
			final List<String> quantities = new ArrayList<String>();

			final CartModel cartModel = this.<CartModel> getValue(source, "basket");

			for (final AbstractOrderEntryModel entry : cartModel.getEntries())
			{
				itemids.add(entry.getProduct().getCode());
				quantities.add(String.valueOf(entry.getQuantity()));
			}

			((PrudsysBasketEventTrackRequest) target).setItemids(itemids);
			((PrudsysBasketEventTrackRequest) target).setQuantities(quantities);
		}

		if (target instanceof PrudsysOrderEventTrackRequest)
		{
			final List<String> itemids = new ArrayList<String>();
			final List<String> quantities = new ArrayList<String>();
			final List<String> utp = new ArrayList<String>();

			final OrderModel orderModel = this.<OrderModel> getValue(source, "order");

			for (final AbstractOrderEntryModel entry : orderModel.getEntries())
			{
				itemids.add(entry.getProduct().getCode());
				quantities.add(String.valueOf(entry.getQuantity()));
				utp.add(String.valueOf(entry.getTotalPrice()));
			}

			((PrudsysOrderEventTrackRequest) target).setItemids(itemids);
			((PrudsysOrderEventTrackRequest) target).setQuantities(quantities);
			((PrudsysOrderEventTrackRequest) target).setUtp(utp);
		}

		if (target instanceof PrudsysUserToSessionEventTrackRequest)
		{
			final CustomerModel customerModel = this.<CustomerModel> getValue(source, "customer");
			((PrudsysUserToSessionEventTrackRequest) target).setUserId(customerModel.getContactEmail());
		}
	}

	private <T> T getValue(final EventTrackRequest source, final String key)
	{
		return (T) source.getValues().get(key);
	}
}