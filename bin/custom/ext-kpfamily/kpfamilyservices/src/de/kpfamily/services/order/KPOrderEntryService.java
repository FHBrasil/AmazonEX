package de.kpfamily.services.order;

import de.hybris.platform.basecommerce.enums.OrderEntryStatus;
import de.hybris.platform.order.OrderEntryService;

public interface KPOrderEntryService extends OrderEntryService
{
	OrderEntryStatus findOrderEntryStatusByExternalCode(final String externalCode);
}