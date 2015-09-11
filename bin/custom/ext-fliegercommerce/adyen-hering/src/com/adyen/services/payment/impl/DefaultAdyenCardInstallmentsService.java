/**
 * 
 */
package com.adyen.services.payment.impl;

import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.util.PriceValue;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;


/**
 * @author franthescollymaneira
 *
 */
public class DefaultAdyenCardInstallmentsService implements AdyenCardInstallmentsService
{
	private static final int PRICE_SCALE = 2;

	private static final int PRICE_ROUND_MODE = BigDecimal.ROUND_DOWN;
	
	protected final DecimalFormat DECIMAL_FORMAT = new DecimalFormat( "#,##0.00" );

	@Resource
	protected BaseStoreService baseStoreService;

	@Resource
	protected CommonI18NService commonI18NService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.hering.core.order.HeringCCPaymentService#formatInstallmentsCost(de.hybris.platform.util.PriceValue)
	 */
	@Override
	public List<String> formatInstallmentsCost(PriceValue productPrice)
	{
		final List<String> installments = new ArrayList<String>();
		
		final BigDecimal total = new BigDecimal(String.valueOf(productPrice.getValue()));
		installments.add(DECIMAL_FORMAT.format(total.doubleValue()));

		int availableInstallments = calculateAvailableInstallments(productPrice);
		
		for(int i = 2; i <= availableInstallments; i++) 
		{
			installments.add(DECIMAL_FORMAT.format(getCostFromInstallment(i, total)));
		}
		
		return installments;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.hering.core.order.HeringCCPaymentService#formatInstallmentsCost(de.hybris.platform.europe1.model.PriceRowModel)
	 */
	@Override
	public List<String> formatInstallmentsCost(PriceRowModel productPrice)
	{
		return formatInstallmentsCost(getPriceValueFromModel(productPrice));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.hering.core.order.HeringCCPaymentService#calculateAvailableInstallments(de.hybris.platform.util.PriceValue)
	 */
	@Override
	public int calculateAvailableInstallments(PriceValue productPrice)
	{
		double price = productPrice.getValue();

		if (price <= 0)
		{
			return 0;
		}

		if (price < 60)
		{
			return 1;
		}

		if (price >= 60.00 && price < 90.00)
		{
			return 2;
		}

		if (price >= 90.00 && price < 120.00)
		{
			return 3;
		}

		if (price >= 120.00 && price < 150.00)
		{
			return 4;
		}

		return 5;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.hering.core.order.HeringCCPaymentService#calculateInstallmentCost(de.hybris.platform.util.PriceValue)
	 */
	@Override
	public PriceValue calculateInstallmentCost(PriceValue productPrice)
	{
		double price = productPrice.getValue();

		int installments = calculateAvailableInstallments(productPrice);

		if (installments > 1 && price > 0)
		{
			price = BigDecimal.valueOf(price).divide(BigDecimal.valueOf(installments), PRICE_SCALE, PRICE_ROUND_MODE).doubleValue();
		}

		return createPriceValue(price);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.hering.core.order.HeringCCPaymentService#calculateInstallmentCost(de.hybris.platform.europe1.model.PriceRowModel
	 * )
	 */
	@Override
	public PriceValue calculateInstallmentCost(PriceRowModel productPrice)
	{
		return calculateInstallmentCost(getPriceValueFromModel(productPrice));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.hering.core.order.HeringCCPaymentService#calculateAvailableInstallments(de.hybris.platform.europe1.model.
	 * PriceRowModel)
	 */
	@Override
	public int calculateAvailableInstallments(PriceRowModel productPrice)
	{
		return calculateAvailableInstallments(getPriceValueFromModel(productPrice));
	}

	/**
	 * @param productPrice
	 * @return
	 */
	private PriceValue getPriceValueFromModel(PriceRowModel productPrice)
	{
		double price = productPrice.getPrice().doubleValue();
		String currencyIso = productPrice.getCurrency().getIsocode();
		boolean netto = productPrice.getNet().booleanValue();

		return new PriceValue(currencyIso, price, netto);
	}

	/**
	 * 
	 * @param price
	 * @return
	 */
	private PriceValue createPriceValue(final double price)
	{
		String currencyIso = commonI18NService.getCurrentCurrency().getIsocode();
		boolean net = baseStoreService.getCurrentBaseStore().isNet();

		return new PriceValue(currencyIso, price, net);
	}
	
	/**
	 * 
	 * @param parcel
	 * @param price
	 * @return
	 */
	private double getCostFromInstallment(int parcel, BigDecimal price)
	{
		final BigDecimal cost = price.divide(BigDecimal.valueOf(parcel), PRICE_SCALE, PRICE_ROUND_MODE);
		return cost.doubleValue();
	}
}