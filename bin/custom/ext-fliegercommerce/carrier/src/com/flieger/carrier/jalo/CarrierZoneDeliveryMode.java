package com.flieger.carrier.jalo;

import de.hybris.platform.deliveryzone.jalo.Zone;
import de.hybris.platform.deliveryzone.jalo.ZoneDeliveryModeValue;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.Country;
import de.hybris.platform.jalo.c2l.Currency;
import de.hybris.platform.jalo.type.ComposedType;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;


public class CarrierZoneDeliveryMode extends GeneratedCarrierZoneDeliveryMode
{
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger(CarrierZoneDeliveryMode.class.getName());

	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes)
			throws JaloBusinessException
	{
		// business code placed here will be executed before the item is created
		// then create the item
		final Item item = super.createItem(ctx, type, allAttributes);
		// business code placed here will be executed after the item was created
		// and return the item
		return item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.deliveryzone.jalo.ZoneDeliveryMode#getAmbigousZones(java.util.Set)
	 */
	@Override
	protected Map<Country, Set<Zone>> getAmbigousZones(Set<Zone> zones)
	{
		return Collections.EMPTY_MAP;
	}
	
	/* (non-Javadoc)
	 * @see de.hybris.platform.deliveryzone.jalo.ZoneDeliveryMode#getValue(de.hybris.platform.deliveryzone.jalo.Zone, de.hybris.platform.jalo.c2l.Currency, double)
	 */
	@Override
	public ZoneDeliveryModeValue getValue(Zone zone, Currency currency, double min)
	{
		return null;
	}
}