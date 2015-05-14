package de.kpfamily.services.product;

import java.util.List;

import de.hybris.platform.core.model.order.OrderModel;

public interface SerialVoucherProductService 
{
	List<String> findSoldSerialVoucherCodesByOrder(OrderModel order);
}