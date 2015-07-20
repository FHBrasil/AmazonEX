/**
 * 
 */
package com.flieger.carrier.order.interceptors;

import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.order.exceptions.DeliveryModeInterceptorException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import javax.annotation.Resource;

import com.flieger.carrier.model.CarrierZoneDeliveryModeModel;
import com.flieger.carrier.model.CarrierZoneDeliveryModeValueModel;
import com.flieger.carrier.model.CarrierZoneModel;
import com.flieger.carrier.model.OrderWeightRangeModel;
import com.flieger.carrier.services.CarrierZoneDeliveryModeService;


/**
 * @author franthescollymaneira
 * 
 */
public class CarrierZoneDeliveryModeValueValidator implements ValidateInterceptor
{
	@Resource
	private CarrierZoneDeliveryModeService zoneDeliveryModeService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.servicelayer.interceptor.ValidateInterceptor#onValidate(java.lang.Object,
	 * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	@Override
	public void onValidate(final Object model, final InterceptorContext ctx) throws InterceptorException
	{
		if (!(model instanceof CarrierZoneDeliveryModeValueModel))
		{
			return;
		}
		
		// 1. check if zones clash
		final CarrierZoneDeliveryModeValueModel newValue = (CarrierZoneDeliveryModeValueModel) model;
		final CarrierZoneModel carrierZone = (CarrierZoneModel) newValue.getZone();
		final CarrierZoneDeliveryModeModel carrierZoneDeliveryMode = (CarrierZoneDeliveryModeModel) newValue.getDeliveryMode();

		// 2. check if there is already the same value
		final CurrencyModel currency = newValue.getCurrency();
		final double min = newValue.getMinimum().doubleValue();
		
		//3. check weight range
		final OrderWeightRangeModel weightRange = newValue.getWeightRange();
		
		try
		{
			final CarrierZoneDeliveryModeValueModel existing = zoneDeliveryModeService.getDeliveryValue(carrierZone, min, carrierZoneDeliveryMode, weightRange);
			
			if(!existing.equals(newValue))
			{
				throw new DeliveryModeInterceptorException(
						"Illegal value for [" + carrierZoneDeliveryMode.getCode() + "] "
								+ "with zone [" + carrierZone.getCode() + "], "
								+ "currency [" + currency.getIsocode() + "], "
								+ "minimum value [" + min + "] and "
								+ "range [" + weightRange.getWeightBegin() + " - " + weightRange.getWeightEnd() + "] -"
								+ " already got same value in " + existing.getPk());
			}
			
		}
		catch (final UnknownIdentifierException ue)
		{
			//expected: no such ZoneDeliveryModeValueModel exists.
		}
	}
}