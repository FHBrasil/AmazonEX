package de.kpfamily.services.pixi.impl;

import java.util.ArrayList;
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
import com.pixi.core.services.PixiOrderService;

import de.hybris.platform.basecommerce.enums.OrderEntryStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.OrderService;
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
    private PixiOrderService pixiOrderService;
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
        updateOrderEntryStatus(entry);
    }
    
    
    /**
     * Updates the parent Order (from the given entry) status.
     * This method is just a workaround until the order entry status process is done.
     * 
     * @param entry
     */
    private void updateOrderEntryStatus(OrderEntryModel entry) {
        List<PixiFunctionParameter> parameters = new ArrayList<PixiFunctionParameter>();
        PixiFunctionParameter orderId = new PixiFunctionParameter();
        orderId.setType(PixiParameterType.ORDER_NUMBER);
        orderId.setValue(entry.getOrder().getPk().toString());
        parameters.add(orderId);
        // FIXME: Isso foi mockado porque não temos conexão com a Pixi API ainda.
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
        OrderModel order = entry.getOrder();
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
                OrderEntryStatus pixiOrderEntryStatus = getOrderEntryService()
                        .findOrderEntryStatusByExternalCode(orderLine.getStatus());
                if (pixiOrderEntryStatus != null) {
                    orderEntry.setStatus(pixiOrderEntryStatus);
                } else {
                    LOG.error("Unknown order entry status:" + orderLine.getStatus());
                }
                // FIXME: ver como setar o reserved stock count.
                // FIXME: ver o que quer dizer esse "item" da versão 4.
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


    
    public PixiOrderService getPixiOrderService() {
        return pixiOrderService;
    }


    
    public void setPixiOrderService(PixiOrderService pixiOrderService) {
        this.pixiOrderService = pixiOrderService;
    }
    
    
}