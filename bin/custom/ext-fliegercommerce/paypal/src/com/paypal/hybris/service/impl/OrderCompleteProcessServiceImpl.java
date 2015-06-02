package com.paypal.hybris.service.impl;

import com.paypal.hybris.service.OrderCompleteProcessService;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;

/**
 * @author Yehor_Tsebro (EPAM Systems)
 */
public class OrderCompleteProcessServiceImpl implements OrderCompleteProcessService {


    private BusinessProcessService businessProcessService;
    private ModelService modelService;

    @Override
    public void startOrderCompletionProcess(OrderModel order) {
        OrderProcessModel orderProcessModel = businessProcessService.createProcess("order-completion-" + order.getCode(), "order-completion");
        if (orderProcessModel != null) {
            orderProcessModel.setOrder(order);
            modelService.save(orderProcessModel);
            businessProcessService.startProcess(orderProcessModel);
        }

    }

    public BusinessProcessService getBusinessProcessService() {
        return businessProcessService;
    }

    public void setBusinessProcessService(BusinessProcessService businessProcessService) {
        this.businessProcessService = businessProcessService;
    }

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
}
