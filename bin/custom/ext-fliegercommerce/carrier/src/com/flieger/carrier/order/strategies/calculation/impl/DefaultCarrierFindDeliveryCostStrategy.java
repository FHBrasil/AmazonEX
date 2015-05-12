/**
 * 
 */
package com.flieger.carrier.order.strategies.calculation.impl;

import de.hybris.platform.commercefacades.order.data.DeliveryModeData;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.order.strategies.calculation.FindDeliveryCostStrategy;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.util.PriceValue;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.flieger.carrier.dao.CarrierDeliveryModeDAO;
import com.flieger.carrier.model.CarrierZoneDeliveryModeModel;
import com.flieger.carrier.services.CarrierDeliveryService;
import com.flieger.carrier.services.strategies.FindPostalCodeStrategy;


/**
 * @author franthescollymaneira
 * 
 */
public class DefaultCarrierFindDeliveryCostStrategy extends AbstractBusinessService implements FindDeliveryCostStrategy
{
	private static final Logger LOG = Logger.getLogger(DefaultCarrierFindDeliveryCostStrategy.class);

	@Resource
	private CarrierDeliveryService deliveryService;
	
	@Resource
	private FindPostalCodeStrategy findPostalCodeStrategy;
	
	@Resource
	private CarrierDeliveryModeDAO countryZoneDeliveryModeDao;
	
	@Resource
	private BaseStoreService baseStoreService;
	
	@Override
	public PriceValue getDeliveryCost(final AbstractOrderModel order)
	{
		ServicesUtil.validateParameterNotNullStandardMessage("order", order);
		
		CurrencyModel currency = order.getCurrency();
		
		final PriceValue noCost = new PriceValue(currency.getIsocode(), 0D, order.getNet().booleanValue());
		
		try
		{
			if(!(order.getDeliveryMode() instanceof CarrierZoneDeliveryModeModel))
			{
				return noCost;
			}
			
			final String postalCode = findPostalCodeStrategy.findPostalCode(order);
			
			if(StringUtils.isBlank(postalCode))
			{
				return noCost;
			}
			
			double weight = deliveryService.calculateTotalWeight(order);
			double totalCost = order.getSubtotal().doubleValue();
			
			CarrierZoneDeliveryModeModel deliveryMode = (CarrierZoneDeliveryModeModel) order.getDeliveryMode();
			
			DeliveryModeData data = new DeliveryModeData();
			
			PriceValue cost = deliveryService.getZipDeliveryCostAndDays(deliveryMode, totalCost, currency, postalCode, weight, data);
			
			if(cost != null && data.getEstimatedDeliveryDays() != order.getEstimatedDeliveryDays())
			{
				order.setEstimatedDeliveryDays(data.getEstimatedDeliveryDays());
				getModelService().save(order);
			}
			
			if(!isBillable(order))
			{
				final List<CarrierZoneDeliveryModeModel> supportedDeliveryModes = countryZoneDeliveryModeDao.findDeliveryModes(postalCode, weight, totalCost);

				final CarrierZoneDeliveryModeModel cheaper = supportedDeliveryModes.get(0);

				if(cheaper.equals(order.getDeliveryMode()))
				{
					return noCost;
				}
			}
			
			return cost;
		}
		catch (final Exception e)
		{
			LOG.warn("Could not find deliveryCost for order [" + order.getCode() + "] due to : " + e.getMessage() + "... skipping!", e);
			return noCost;
		}
	}

	/**
	 * 
	 * @param order
	 * @return
	 */
	private boolean isBillable(final AbstractOrderModel order)
	{
		if(!(order.getDeliveryMode() instanceof CarrierZoneDeliveryModeModel))
		{
			return false;
		}
		
		if(StringUtils.isBlank(findPostalCodeStrategy.findPostalCode(order)))
		{
			return false;
		}
		
		Double threshold = baseStoreService.getCurrentBaseStore().getFreeDeliveryThreshold();
		
		boolean exceedsThreshold = (threshold != null && order.getSubtotal().doubleValue() > threshold.doubleValue());
		
		return !exceedsThreshold;
	}
}