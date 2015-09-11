package de.kpfamily.services.product.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.Assert;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;
import de.kpfamily.services.daos.product.SerialVoucherProductDao;
import de.kpfamily.services.product.SerialVoucherProductService;

public class DefaultSerialVoucherProductService extends AbstractBusinessService implements SerialVoucherProductService 
{
	@Resource
	private SerialVoucherProductDao serialVoucherProductDao;
	
	@Override
	public List<String> findSoldSerialVoucherCodesByOrder(final OrderModel order) 
	{
		Assert.notNull(order);
		
		return serialVoucherProductDao.findSoldSerialVoucherCodesByOrder(order);
	}

	/**
	 * @return the serialVoucherProductDao
	 */
	public SerialVoucherProductDao getSerialVoucherProductDao() 
	{
		return serialVoucherProductDao;
	}

	/**
	 * @param serialVoucherProductDao the serialVoucherProductDao to set
	 */
	public void setSerialVoucherProductDao(SerialVoucherProductDao serialVoucherProductDao) 
	{
		this.serialVoucherProductDao = serialVoucherProductDao;
	}
}