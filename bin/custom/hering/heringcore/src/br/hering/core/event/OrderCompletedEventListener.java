/*
 * [y] hybris Platform
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 */
package br.hering.core.event;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.event.AbstractSiteEventListener;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.fraud.model.FraudReportModel;
import de.hybris.platform.orderprocessing.events.OrderCompletedEvent;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.ArrayList;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.flieger.model.ClearSaleFraudReportModel;
import com.flieger.payment.model.BoletoPaymentInfoModel;

/**
 * Listener for order confirmation events. Code for sending an email is commented out. Add this
 * process and uncomment to
 * use this.
 */
public class OrderCompletedEventListener extends AbstractSiteEventListener<OrderCompletedEvent>
{
    
    private static final Logger LOG = Logger.getLogger(OrderCompletedEventListener.class);
    private ModelService modelService;
    private BusinessProcessService businessProcessService;
    
    
    /**
     * @return the businessProcessService
     */
    protected BusinessProcessService getBusinessProcessService()
    {
        return businessProcessService;
    }
    
    
    /**
     * @param businessProcessService
     *            the businessProcessService to set
     */
    @Required
    public void setBusinessProcessService(final BusinessProcessService businessProcessService)
    {
        this.businessProcessService = businessProcessService;
    }
    
    
    /**
     * @return the modelService
     */
    protected ModelService getModelService()
    {
        return modelService;
    }
    
    
    /**
     * @param modelService
     *            the modelService to set
     */
    @Required
    public void setModelService(final ModelService modelService)
    {
        this.modelService = modelService;
    }
    
    
    @Override
    protected void onSiteEvent(final OrderCompletedEvent orderCompletedEvent)
    {
        final OrderModel orderModel = orderCompletedEvent.getProcess().getOrder();
        LOG.info("Running OrderCompletedEventListener for order:{code:" + orderModel.getCode() + "}");
        ServicesUtil.validateParameterNotNullStandardMessage("payment info",
                orderModel.getPaymentInfo());
        // boleto is captured by linx
        if (orderModel.getPaymentInfo() instanceof BoletoPaymentInfoModel) {
            orderModel.setStatus(OrderStatus.WAITING_PAYMENT_NOTIFICATION);
            orderModel.setPaymentStatus(PaymentStatus.WAITING_PAYMENT_NOTIFICATION);
        } else if (orderModel.getPaymentInfo() instanceof CreditCardPaymentInfoModel) {
            // creditcard is captured by Adyen
            orderModel.setStatus(OrderStatus.PENDING_APPROVAL);
            orderModel.setPaymentStatus(PaymentStatus.PENDING_APPROVAL);
            // test if the payment is approved
//            final ArrayList<FraudReportModel> fraudReports =
//                    new ArrayList<FraudReportModel>(orderModel.getFraudReports());
//            ClearSaleFraudReportModel approvedFraudReport = null;
//            for (FraudReportModel aux : fraudReports)
//            {
//                final String statusCode = ((ClearSaleFraudReportModel) aux).getStatusCode();
//                if ("APROVACAO_AUTOMATICA".equals(statusCode)
//                        || "APROVACAO_MANUAL".equals(statusCode))
//                {
//                    approvedFraudReport = (ClearSaleFraudReportModel) aux;
//                    break;
//                }
//            }
//            if (approvedFraudReport != null)
//            {
                LOG.info(String.format(
                        "order:{%s}; fraudReport:{statusCode:{%s}. Is ready for capture",
                        orderModel.getCode(), "Fraud checked not needed"));
//                        approvedFraudReport.getStatusCode()));
                orderModel.setStatus(OrderStatus.CAPTURE_PAYMENT);
//            } else if (CollectionUtils.isNotEmpty(fraudReports)) {
//                LOG.info(String.format(
//                        "order:{code:%s}; fraudReport:{statusCode:%s}. Is NOT ready for capture",
//                        orderModel.getCode(),
//                        ((ClearSaleFraudReportModel) fraudReports.get(0)).getStatusCode()));
//            }
        }
        LOG.info(String.format("order:{code:%s, paymentInfo:%s, status:%s, paymentStatus:%s}",
                orderModel.getCode(),
                orderModel.getPaymentInfo().getClass().getSimpleName(),
                orderModel.getStatus().getCode(),
                orderModel.getPaymentStatus().getCode()));
        getModelService().save(orderModel);
    }
    
    
    @Override
    protected boolean shouldHandleEvent(final OrderCompletedEvent event)
    {
        final OrderModel order = event.getProcess().getOrder();
        ServicesUtil.validateParameterNotNullStandardMessage("event.order", order);
        final BaseSiteModel site = order.getSite();
        ServicesUtil.validateParameterNotNullStandardMessage("event.order.site", site);
        boolean should = SiteChannel.B2C.equals(site.getChannel());
        LOG.info(String.format("Order: [%s] Site: [%s] shouldHandleEvent? [%s]", order.getCode(),
                site.getUid(), String.valueOf(should)));
        return should;
    }
}
