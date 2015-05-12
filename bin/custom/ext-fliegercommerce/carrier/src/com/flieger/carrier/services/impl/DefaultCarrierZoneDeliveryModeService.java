/**
 * 
 */
package com.flieger.carrier.services.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.order.impl.DefaultZoneDeliveryModeService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.flieger.carrier.dao.CarrierDeliveryModeDAO;
import com.flieger.carrier.jalo.CarrierZoneDeliveryModeValue;
import com.flieger.carrier.model.CarrierZoneDeliveryModeModel;
import com.flieger.carrier.model.CarrierZoneDeliveryModeValueModel;
import com.flieger.carrier.model.CarrierZoneModel;
import com.flieger.carrier.model.OrderWeightRangeModel;
import com.flieger.carrier.services.CarrierZoneDeliveryModeService;

/**
 * @author franthescollymaneira
 *
 */
public class DefaultCarrierZoneDeliveryModeService extends DefaultZoneDeliveryModeService implements CarrierZoneDeliveryModeService
{
	@Resource
	private CarrierDeliveryModeDAO countryZoneDeliveryModeDao;
	
	/* (non-Javadoc)
	 * @see com.flieger.carrier.services.CarrierZoneDeliveryModeService#getDeliveryValue(com.flieger.carrier.model.CarrierZoneModel, de.hybris.platform.core.model.c2l.CurrencyModel, java.lang.Double, com.flieger.carrier.model.CarrierZoneDeliveryModeModel, com.flieger.carrier.model.OrderWeightRangeModel)
	 */
	@Override
	public CarrierZoneDeliveryModeValueModel getDeliveryValue(String postalCode, double amount, CarrierZoneDeliveryModeModel zoneDeliveryMode, double weight)
	{
		validateParameterNotNullStandardMessage("postalCode", postalCode);
		validateParameterNotNullStandardMessage("zoneDeliveryMode", zoneDeliveryMode);
		
		final List<CarrierZoneDeliveryModeValueModel> modes = countryZoneDeliveryModeDao.findDeliveryValues(postalCode, weight, amount, zoneDeliveryMode);
		
		if(CollectionUtils.isEmpty(modes))
		{
			throw new UnknownIdentifierException(
					"ZoneDeliveryModeValue in the zone [ " + postalCode + "], "
					+ " minimum value [" + amount + "], "
					+ " weightRange [" + weight + "]"
					+ " and zoneDeliveryMode [" + zoneDeliveryMode.getCode() + "] not found!");
		}
		else if (modes.size() > 1)
		{
			throw new AmbiguousIdentifierException("ZoneDeliveryModeValue in the zone [ " + postalCode + "], "
					+ " minimum value [" + amount + "], "
					+ " weightRange [" + weight + "]"
					+ " and zoneDeliveryMode [" + zoneDeliveryMode.getCode() + "] "
					+ " is not unique, " + modes.size() + " values found!");
		}
		
		return modes.iterator().next();
	}
	
	/* (non-Javadoc)
	 * @see com.flieger.carrier.services.CarrierZoneDeliveryModeService#getDeliveryValue(com.flieger.carrier.model.CarrierZoneModel, de.hybris.platform.core.model.c2l.CurrencyModel, java.lang.Double, com.flieger.carrier.model.CarrierZoneDeliveryModeModel, com.flieger.carrier.model.OrderWeightRangeModel)
	 */
	@Override
	public CarrierZoneDeliveryModeValueModel getDeliveryValue(CarrierZoneModel zone, double amount, CarrierZoneDeliveryModeModel zoneDeliveryMode, OrderWeightRangeModel weightRange)
	{
		validateParameterNotNullStandardMessage("zone", zone);
		validateParameterNotNullStandardMessage("zoneDeliveryMode", zoneDeliveryMode);
		validateParameterNotNullStandardMessage("weightRange", weightRange);
		
		final List<CarrierZoneDeliveryModeValueModel> modes = countryZoneDeliveryModeDao.findDeliveryValues(Arrays.asList(zone), amount, zoneDeliveryMode, Arrays.asList(weightRange));
		
		if(CollectionUtils.isEmpty(modes))
		{
			throw new UnknownIdentifierException(
					"ZoneDeliveryModeValue in the zone [ " + zone.getCode() + "], "
					+ " minimum value [" + amount + "], "
					+ " weightRange [" + weightRange.getWeightBegin() + " - " + weightRange.getWeightEnd() + "]"
					+ " and zoneDeliveryMode [" + zoneDeliveryMode.getCode() + "] not found!");
		}
		else if (modes.size() > 1)
		{
			throw new AmbiguousIdentifierException("ZoneDeliveryModeValue in the zone [ " + zone.getCode() + "], "
					+ " minimum value [" + amount + "], "
					+ " weightRange [" + weightRange.getWeightBegin() + " - " + weightRange.getWeightEnd() + "]"
					+ " and zoneDeliveryMode [" + zoneDeliveryMode.getCode() + "] "
					+ " is not unique, " + modes.size() + " values found!");
		}
		
		return modes.iterator().next();
	}
	
	public List<CarrierZoneDeliveryModeValueModel> getCarrierZoneDeliveryModeValueFromCarrierZoneCode(String code){
		return countryZoneDeliveryModeDao.getCarrierZoneDeliveryModeValueFromCarrierZoneCode(code);
	}
}
