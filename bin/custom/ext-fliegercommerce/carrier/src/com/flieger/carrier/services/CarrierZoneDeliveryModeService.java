/**
 * 
 */
package com.flieger.carrier.services;

import de.hybris.platform.order.ZoneDeliveryModeService;

import java.util.List;

import com.flieger.carrier.model.CarrierZoneDeliveryModeModel;
import com.flieger.carrier.model.CarrierZoneDeliveryModeValueModel;
import com.flieger.carrier.model.CarrierZoneModel;
import com.flieger.carrier.model.OrderWeightRangeModel;

/**
 * @author franthescollymaneira
 *
 */
public interface CarrierZoneDeliveryModeService extends ZoneDeliveryModeService
{
	CarrierZoneDeliveryModeValueModel getDeliveryValue(String postalCode, double amount, CarrierZoneDeliveryModeModel carrierZoneDeliveryMode, double weight);

	CarrierZoneDeliveryModeValueModel getDeliveryValue(CarrierZoneModel carrierZone, double min, CarrierZoneDeliveryModeModel carrierZoneDeliveryMode, OrderWeightRangeModel weightRange);
	
	List<CarrierZoneDeliveryModeValueModel> getCarrierZoneDeliveryModeValueFromCarrierZoneCode(String code);
}