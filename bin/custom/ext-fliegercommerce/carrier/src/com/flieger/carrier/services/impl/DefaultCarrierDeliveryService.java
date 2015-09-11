package com.flieger.carrier.services.impl;

import de.hybris.platform.classification.ClassificationService;
import de.hybris.platform.commercefacades.order.data.DeliveryModeData;
import de.hybris.platform.commerceservices.delivery.impl.DefaultDeliveryService;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.util.PriceValue;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import br.hering.core.model.HeringSizeVariantProductModel;

import com.flieger.carrier.dao.CarrierDeliveryModeDAO;
import com.flieger.carrier.model.CarrierZoneDeliveryModeModel;
import com.flieger.carrier.model.CarrierZoneDeliveryModeValueModel;
import com.flieger.carrier.services.CarrierDeliveryService;


/**
 * {@link CarrierDeliveryService} default implementation
 * 
 * @author franthescollymaneira
 * 
 */
public class DefaultCarrierDeliveryService extends DefaultDeliveryService implements CarrierDeliveryService
{
	private static final Logger LOG = Logger.getLogger(DefaultCarrierDeliveryService.class);

	@Resource
	private CarrierDeliveryModeDAO countryZoneDeliveryModeDao;

	@Resource
	private ClassificationService classificationService;
	
	/*
	 * (non-Javadoc)
	 * @see com.flieger.carrier.services.CarrierDeliveryService#getZipDeliveryCostAndDays(de.hybris.platform.core.model.order.delivery.DeliveryModeModel, double, de.hybris.platform.core.model.c2l.CurrencyModel, java.util.List, java.util.List, de.hybris.platform.commercefacades.order.data.DeliveryModeData)
	 */
	@Override
	public PriceValue getZipDeliveryCostAndDays(
			final CarrierZoneDeliveryModeModel deliveryMode, 
			double amount, 
			CurrencyModel currency,
			final String postalCode, 
			final double weight, 
			final DeliveryModeData data)
	{
		final List<CarrierZoneDeliveryModeValueModel> listCost = countryZoneDeliveryModeDao.findDeliveryValues(postalCode, weight, amount, deliveryMode);

		if (CollectionUtils.isEmpty(listCost))
		{
			return null;
		}
		
		final CarrierZoneDeliveryModeValueModel bestMatch = listCost.get(0);

		if (data != null)
		{
			//set days to deliver
			data.setEstimatedDeliveryDays(bestMatch.getEstimatedDeliveryDays());
		}

		double totalCosts = calculateTotalDeliveryCost(amount, bestMatch);

		return new PriceValue(currency.getIsocode(), totalCosts, deliveryMode.getNet().booleanValue());
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flieger.carrier.services.CarrierDeliveryService#getWeightRangesByOrderWeight(de.hybris.platform.
	 * core.model.order.AbstractOrderModel)
	 */
	@Override
	public double calculateTotalWeight(final AbstractOrderModel abstractOrder)
	{
		ServicesUtil.validateParameterNotNull(abstractOrder, "abstractOrder model cannot be null");
		
		if(CollectionUtils.isEmpty(abstractOrder.getEntries()))
		{
			return 0d;
		}
	
		BigDecimal orderWeight = new BigDecimal("0");
		for (AbstractOrderEntryModel entry : abstractOrder.getEntries())
		{
			if(!(entry.getProduct() instanceof HeringSizeVariantProductModel))
			{
				LOG.warn(entry.getProduct().getCode() + " is not a size variant");
				continue;
			}
			
			HeringSizeVariantProductModel product = (HeringSizeVariantProductModel) entry.getProduct();
			
			BigDecimal singleWeight = BigDecimal.valueOf(product.getWeight());
			BigDecimal weightByQuantity = singleWeight.multiply(BigDecimal.valueOf(entry.getQuantity().longValue()));
			
			orderWeight = orderWeight.add(weightByQuantity);
		}
		
		return orderWeight.doubleValue();
	}

	/**
	 * 
	 * @param amount
	 * @param carrierValue
	 * @return
	 */
	private double calculateTotalDeliveryCost(final double amount, final CarrierZoneDeliveryModeValueModel carrierValue)
	{
		Assert.notNull(carrierValue);

		BigDecimal value = BigDecimal.valueOf(carrierValue.getValue().doubleValue());
		BigDecimal toll = BigDecimal.valueOf(carrierValue.getTollValue());
		BigDecimal percentage = BigDecimal.valueOf(carrierValue.getInsurancePercentage());
		BigDecimal orderSubTotal = BigDecimal.valueOf(amount);

		BigDecimal transportCost = value.add(toll);
		BigDecimal insuranceCost = BigDecimal.ZERO;

		if (percentage.doubleValue() > 0)
		{
			insuranceCost = orderSubTotal.multiply(percentage.divide(BigDecimal.valueOf(100)));
		}

		BigDecimal totalCosts = transportCost.add(insuranceCost).setScale(2, BigDecimal.ROUND_UP);

		LOG.debug(" totalCosts: " + totalCosts
				+ " [value: " + value 
				+ " toll: " + toll 
				+ " percentage: " + percentage
				+ " orderSubTotal: " + orderSubTotal 
				+ " days:" + carrierValue.getEstimatedDeliveryDays() + "]");

		return totalCosts.doubleValue();
	}
}