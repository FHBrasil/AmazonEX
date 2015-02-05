package de.kpfamily.services.products.impl;

import java.util.List;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;
import de.kpfamily.services.products.SerialVoucherProductService;

public class DefaultSerialVoucherProductService extends AbstractBusinessService implements SerialVoucherProductService 
{
	@Override
	public List<String> findSerialVouchersByOrder(final OrderModel order) 
	{
		return null;
	}
}