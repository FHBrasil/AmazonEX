package de.kpfamily.services.strategies;

import de.hybris.platform.core.model.order.OrderEntryModel;

public interface OrderEntryStatusProcessUIDGeneratorStrategy 
{
	String generateUID(OrderEntryModel orderEntry);
}