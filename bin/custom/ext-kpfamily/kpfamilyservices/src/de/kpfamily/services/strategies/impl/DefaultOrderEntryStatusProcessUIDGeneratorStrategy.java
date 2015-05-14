package de.kpfamily.services.strategies.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import de.hybris.platform.core.model.order.OrderEntryModel;
import de.kpfamily.services.strategies.OrderEntryStatusProcessUIDGeneratorStrategy;

public class DefaultOrderEntryStatusProcessUIDGeneratorStrategy implements OrderEntryStatusProcessUIDGeneratorStrategy 
{
	@Override
	public String generateUID(final OrderEntryModel orderEntry) 
	{
		Assert.notNull(orderEntry, "orderEntry is null");
		
		final String orderCode = orderEntry.getOrder().getCode();
		
		final long now = System.currentTimeMillis();
		
		final Object[] parts = {orderCode, orderEntry.getPk(), now};
		
		return StringUtils.join(parts , "-").toLowerCase();
	}
}