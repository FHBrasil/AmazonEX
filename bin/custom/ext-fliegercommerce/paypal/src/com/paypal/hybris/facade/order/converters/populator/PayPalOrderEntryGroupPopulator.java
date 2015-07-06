package com.paypal.hybris.facade.order.converters.populator;

import de.hybris.platform.acceleratorfacades.order.data.OrderEntryGroupData;
import de.hybris.platform.acceleratorfacades.order.populators.OrderEntryGroupPopulator;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.util.TaxValue;

import org.apache.commons.lang.BooleanUtils;

import java.math.BigDecimal;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class PayPalOrderEntryGroupPopulator extends OrderEntryGroupPopulator
{

	protected void updateGroupTotalPrice (final AbstractOrderEntryModel entryModel, final OrderEntryGroupData groupData)
	{
		final CurrencyModel currency = entryModel.getOrder().getCurrency();

		final PriceData groupTotalPrice = groupData.getTotalPrice();
		final PriceData entryModelPrice = getPriceDataFactory()
				.create(
						PriceDataType.BUY,
						BigDecimal.valueOf(
								entryModel.getTotalPrice().doubleValue()),
						currency);

		if (groupTotalPrice == null)
		{
			groupData.setTotalPrice(entryModelPrice);
		}
		else
		{
			final PriceData newTotalPrice = getPriceDataFactory().create(PriceDataType.BUY,
					groupTotalPrice.getValue().add(entryModelPrice.getValue()), currency);

			groupData.setTotalPrice(newTotalPrice);
		}
	}

	protected void updateTotalTax(final AbstractOrderEntryModel entryModel, final OrderEntryGroupData groupData)
	{
		final CurrencyModel currency = entryModel.getOrder().getCurrency();

		final PriceData totalTax = groupData.getTotalTax();
		double sumAppliedTax = TaxValue.sumAppliedTaxValues(entryModel.getTaxValues());

		PriceData totalEntryTax = getPriceDataFactory().create(PriceDataType.BUY, BigDecimal.valueOf(sumAppliedTax), currency);

		if (totalTax == null)
		{
			groupData.setTotalTax(totalEntryTax);
		}
		else
		{
			final PriceData newTotalTax = getPriceDataFactory().create(PriceDataType.BUY, totalTax.getValue().add(totalEntryTax.getValue()), currency);
			groupData.setTotalTax(newTotalTax);
		}
	}
	
}
