package com.paypal.hybris.service;

import de.hybris.platform.core.model.order.OrderModel;

/**
 * @author Yehor_Tsebro (EPAM Systems)
 */
public interface OrderCompleteProcessService {

    public void startOrderCompletionProcess(OrderModel order);

}
