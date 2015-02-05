package de.kpfamily.services.products;

import java.util.List;

import de.hybris.platform.core.model.order.OrderModel;

public interface SerialVoucherProductService 
{
	List<String> findSerialVouchersByOrder(OrderModel order);
}