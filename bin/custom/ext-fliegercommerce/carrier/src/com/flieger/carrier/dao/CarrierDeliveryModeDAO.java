/**
 * 
 */
package com.flieger.carrier.dao;

import de.hybris.platform.commerceservices.delivery.dao.CountryZoneDeliveryModeDao;

import java.util.List;

import com.flieger.carrier.model.CarrierZoneDeliveryModeModel;
import com.flieger.carrier.model.CarrierZoneDeliveryModeValueModel;
import com.flieger.carrier.model.CarrierZoneModel;
import com.flieger.carrier.model.OrderWeightRangeModel;

/**
 * 
 * @author franthescollymaneira
 *
 */
public interface CarrierDeliveryModeDAO extends CountryZoneDeliveryModeDao
{
	OrderWeightRangeModel getWeightRangeByValues(double start, double end);

	List<CarrierZoneDeliveryModeModel> findDeliveryModes(String postalCode, double weight, double amount);

	List<CarrierZoneDeliveryModeValueModel> findDeliveryValues(String postalCode, double weight, double amount, CarrierZoneDeliveryModeModel zoneDeliveryMode);

	/**
	 * @param asList
	 * @param amount
	 * @param zoneDeliveryMode
	 * @param asList2
	 * @return
	 */
	List<CarrierZoneDeliveryModeValueModel> findDeliveryValues(List<CarrierZoneModel> asList, double amount, CarrierZoneDeliveryModeModel zoneDeliveryMode, List<OrderWeightRangeModel> asList2);
	
	List<CarrierZoneDeliveryModeValueModel> getCarrierZoneDeliveryModeValueFromCarrierZoneCode(final String code);
}