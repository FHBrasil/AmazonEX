package de.kpfamily.services.pixi.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.pixi.api.PixiApiImporter;
import com.pixi.api.core.PixiFunctionParameter;
import com.pixi.api.core.PixiParameterType;
import com.pixi.api.result.OrderHeaderResult;
import com.pixi.api.result.OrderLineResult;
import com.pixi.core.services.PixiOrderEntryService;

import de.hybris.platform.basecommerce.enums.OrderEntryStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.order.impl.DefaultOrderService;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;
import de.kpfamily.services.order.KPOrderEntryService;
import de.kpfamily.services.strategies.OrderEntryStatusProcessUIDGeneratorStrategy;

public class DefaultPixiOrderEntryService extends AbstractBusinessService
        implements PixiOrderEntryService {
    
    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(DefaultPixiOrderEntryService.class);
    @Resource
    private BusinessProcessService businessProcessService;
    @Resource
    private OrderEntryStatusProcessUIDGeneratorStrategy orderEntryStatusProcessUIDGeneratorStrategy;
    @Resource
    private KPOrderEntryService orderEntryService;
    @Resource
    private PixiApiImporter orderHeaderPixiApiImporter;
    @Resource
    private PixiApiImporter orderLinePixiApiImporter;
    private static final String PROCESS_CODE = "order-entry-status-process";
    
    
    @Override
    public void updateOrderEntryStatus(OrderEntryModel entry, String pixiStatusCode) {
        final OrderEntryStatus oldStatus = entry.getStatus();
        final OrderEntryStatus newStatus =
                getOrderEntryService().findOrderEntryStatusByExternalCode(pixiStatusCode);
        Assert.notNull(newStatus, "Status not found for: " + pixiStatusCode);
        final String uniqueID = getOrderEntryStatusProcessUIDGeneratorStrategy().generateUID(entry);
        // 2015-07-23 - Process not needed
        // final OrderEntryStatusProcessModel process =
        // getBusinessProcessService().createProcess(uniqueID, PROCESS_CODE);
        // process.setOldStatus(oldStatus);
        // process.setNewStatus(newStatus);
        // process.setOrderEntry(entry);
        // getModelService().save(process);
        // getBusinessProcessService().startProcess(process);
        //
        // Must save the entry and update the order status
        getModelService().save(entry);
        updateOrderStatus(entry);
    }
    
    
    /**
     * Updates the parent Order (from the given entry) status.
     * This method is just a workaround until the order entry status process is done.
     * 
     * @param entry
     */
    private void updateOrderStatus(OrderEntryModel entry) {
        // TODO 1: update order lines status using pixi api
    }
    
    
    /**
     * @param order
     */
    private void updatePixiAPIOrderLinesStatus(OrderModel order) {
        List<PixiFunctionParameter> parameters = new ArrayList<PixiFunctionParameter>();
        PixiFunctionParameter orderId = new PixiFunctionParameter();
        orderId.setType(PixiParameterType.ORDER_NUMBER);
        orderId.setValue(order.getCode());
        parameters.add(orderId);
        List<OrderHeaderResult> orders = (List<OrderHeaderResult>)
                getOrderHeaderPixiApiImporter().importData(parameters);
        if (orders.isEmpty()) {
            return;
        }
        List<OrderLineResult> orderLines =
                (List<OrderLineResult>) getOrderLinePixiApiImporter().importData(parameters);
        if (orderLines.isEmpty()) {
            LOG.debug("No order lines to update.");
            return;
        }
        for (AbstractOrderEntryModel orderEntry : order.getEntries()) {
            if (orderEntry.getProduct() == null) {
                LOG.debug("Entry: " + orderEntry.getPk().toString() + "(" + order.getCode()
                        + ") has no product.");
                continue;
            }
            for (OrderLineResult orderLine : orderLines) {
                if (!orderLine.getItemNrInt().equals(orderEntry.getProduct().getCode())) {
                    continue;
                }
                // FIXME: get the right status comming from Pixi API
                orderEntry.setStatus(OrderEntryStatus.SHIPPED);
                // TODO: set reserved account
                //
                // EnumerationValue status = service.getKPOrderEntryStatusByExternalCode(line
                // .getStatus());
                // if (status != null) {
                // item.setAttribute(Attributes.OrderEntry.KPSTATUS, status);
                // entry.setKPOrderEntryStatus(status);
                // } else {
                // LOG.error("Unknown status: " + line.getStatus());
                // }
                // // item
                // item.setAttribute(Attributes.OrderEntry.RESERVEDCOUNT,
                // NumberUtils.toInt(line.getReservedCount(), 0));
                // // bean
                // entry.setReservedStockCount((Integer) item
                // .getAttribute(Attributes.OrderEntry.RESERVEDCOUNT));
                //
            }
        }
    }
    
    
    /**
     * @return the businessProcessService
     */
    public BusinessProcessService getBusinessProcessService() {
        return businessProcessService;
    }
    
    
    /**
     * @return the orderEntryService
     */
    public KPOrderEntryService getOrderEntryService() {
        return orderEntryService;
    }
    
    
    /**
     * @return the orderEntryStatusProcessUIDGeneratorStrategy
     */
    public OrderEntryStatusProcessUIDGeneratorStrategy
            getOrderEntryStatusProcessUIDGeneratorStrategy() {
        return orderEntryStatusProcessUIDGeneratorStrategy;
    }
    
    
    /**
     * @return
     */
    public PixiApiImporter getOrderHeaderPixiApiImporter() {
        return orderHeaderPixiApiImporter;
    }
    
    
    /**
     * @param orderHeaderPixiApiImporter
     */
    public void setOrderHeaderPixiApiImporter(PixiApiImporter orderHeaderPixiApiImporter) {
        this.orderHeaderPixiApiImporter = orderHeaderPixiApiImporter;
    }
    
    
    /**
     * @return
     */
    public PixiApiImporter getOrderLinePixiApiImporter() {
        return orderLinePixiApiImporter;
    }
    
    
    /**
     * @param orderLinePixiApiImporter
     */
    public void setOrderLinePixiApiImporter(PixiApiImporter orderLinePixiApiImporter) {
        this.orderLinePixiApiImporter = orderLinePixiApiImporter;
    }
}