package de.kpfamily.services.pixi.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.pixi.core.services.PixiOrderEntryService;

import de.hybris.platform.basecommerce.enums.OrderEntryStatus;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;
import de.kpfamily.core.model.process.OrderEntryStatusProcessModel;
import de.kpfamily.services.order.KPOrderEntryService;
import de.kpfamily.services.strategies.OrderEntryStatusProcessUIDGeneratorStrategy;

public class DefaultPixiOrderEntryService extends AbstractBusinessService implements PixiOrderEntryService
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(DefaultPixiOrderEntryService.class);
	
	@Resource
	private BusinessProcessService businessProcessService;
	
	@Resource
	private OrderEntryStatusProcessUIDGeneratorStrategy orderEntryStatusProcessUIDGeneratorStrategy;

	@Resource
	private KPOrderEntryService orderEntryService;
	
	private static final String PROCESS_CODE = "order-entry-status-process";
	
	@Override
	public void updateOrderEntryStatus(OrderEntryModel entry, String pixiStatusCode) 
	{
		final OrderEntryStatus oldStatus = entry.getStatus();
		final OrderEntryStatus newStatus = getOrderEntryService().findOrderEntryStatusByExternalCode(pixiStatusCode);
		
		Assert.notNull(newStatus, "Status not found for: " + pixiStatusCode);
		
		final String uniqueID = getOrderEntryStatusProcessUIDGeneratorStrategy().generateUID(entry);
		
	    final OrderEntryStatusProcessModel process = getBusinessProcessService().createProcess(uniqueID, PROCESS_CODE);
	    
	    process.setOldStatus(oldStatus);
	    process.setNewStatus(newStatus);
	    process.setOrderEntry(entry);
	    
	    getModelService().save(process);
	    getBusinessProcessService().startProcess(process);
	}

	/**
	 * @return the businessProcessService
	 */
	public BusinessProcessService getBusinessProcessService() 
	{
		return businessProcessService;
	}

	/**
	 * @return the orderEntryService
	 */
	public KPOrderEntryService getOrderEntryService() 
	{
		return orderEntryService;
	}

	/**
	 * @return the orderEntryStatusProcessUIDGeneratorStrategy
	 */
	public OrderEntryStatusProcessUIDGeneratorStrategy getOrderEntryStatusProcessUIDGeneratorStrategy() 
	{
		return orderEntryStatusProcessUIDGeneratorStrategy;
	}
}