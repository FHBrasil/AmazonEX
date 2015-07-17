package com.flieger.clearsale.order.interceptors;

import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.adyen.services.payment.AdyenPaymentService;
import com.flieger.constants.AdyenTransactionStatus;

/**
 * @author flieger
 * @author ezequiel
 */
public class CompleteOrderValueValidator implements PrepareInterceptor
{
    
    protected final Logger LOG = Logger.getLogger(this.getClass());
    @Resource
    private ModelService modelService;
    @Resource
    private CustomerAccountService customerAccountService;
    @Resource
    private CommonI18NService commonI18NService;
    @Resource
    private AdyenPaymentService paymentService;
    private FlexibleSearchService flexibleSearchService;
    private String paymentProvider;
    
    
    /**
     * @return the paymentProvider
     */
    public String getPaymentProvider()
    {
        return paymentProvider;
    }
    
    
    /**
     * @param paymentProvider
     *            the paymentProvider to set
     */
    public void setPaymentProvider(final String paymentProvider)
    {
        this.paymentProvider = paymentProvider;
    }
    
    
    /**
     * @return the customerAccountService
     */
    public CustomerAccountService getCustomerAccountService()
    {
        return customerAccountService;
    }
    
    
    /**
     * @param customerAccountService
     *            the customerAccountService to set
     */
    public void setCustomerAccountService(final CustomerAccountService customerAccountService)
    {
        this.customerAccountService = customerAccountService;
    }
    
    
    /**
     * @return the flexibleSearchService
     */
    public FlexibleSearchService getFlexibleSearchService()
    {
        return flexibleSearchService;
    }
    
    
    /**
     * @param flexibleSearchService
     *            the flexibleSearchService to set
     */
    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
    {
        this.flexibleSearchService = flexibleSearchService;
    }
    
    
    /**
     * @return the commonI18NService
     */
    public CommonI18NService getCommonI18NService()
    {
        return commonI18NService;
    }
    
    
    /**
     * @param commonI18NService
     *            the commonI18NService to set
     */
    public void setCommonI18NService(final CommonI18NService commonI18NService)
    {
        this.commonI18NService = commonI18NService;
    }
    
    
    /**
     * @return the modelService
     */
    public ModelService getModelService()
    {
        return modelService;
    }
    
    
    /**
     * @param modelService
     *            the modelService to set
     */
    public void setModelService(final ModelService modelService)
    {
        this.modelService = modelService;
    }
    
    
    /*
     * (non-Javadoc)
     * @see
     * de.hybris.platform.servicelayer.interceptor.ValidateInterceptor#onValidate(java.lang.Object
     * ,
     * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
     */
    @Override
    public void onPrepare(final Object model, final InterceptorContext ctx)
            throws InterceptorException
    {
        if (!(model instanceof OrderModel))
        {
            return;
        }
        final OrderModel orderModel = (OrderModel) model;
        if (ctx.isModified(orderModel, OrderModel.STATUS))
        {
            LOG.info("Intercepting order status change. order:{code:"
                    + orderModel.getCode()
                    + ", status:"
                    + (orderModel.getStatus() != null ? orderModel.getStatus().getCode() : "null")
                    + "}");
            if (OrderStatus.CAPTURE_PAYMENT.equals(orderModel.getStatus())
                    && PaymentStatus.PENDING_APPROVAL.equals(orderModel.getPaymentStatus()))
            {
                LOG.info(String.format("Trying to capture. order:{code:%s}",
                        orderModel.getCode()));
                final boolean retorno = capturePayment(orderModel);
                LOG.info(String.format("Order: [%s] was captured? [%s]",
                        orderModel.getCode(),
                        String.valueOf(retorno)));
                if (retorno)
                {
                    orderModel.setStatus(OrderStatus.PAYMENT_CAPTURED);
                } else {
                    orderModel.setStatus(OrderStatus.PAYMENT_NOT_CAPTURED);
                }
                getModelService().save(orderModel);
            }
        }
    }
    
    
    // o adyenservice sempre deve devolver sucesso, se o pedido já foi capturado ou pôde ser
    // capturado nesta tentativa
    private boolean capturePayment(final OrderModel order)
    {
        boolean result = true;
        for (final PaymentTransactionModel transaction : order.getPaymentTransactions())
        {
            final PaymentTransactionEntryModel capture = paymentService.capture(transaction);
            // if the capture was received, it means that Adyen received the request for capture,
            // if the capture was accepted, it means that the user account was charged
            result &=
                    capture != null
                            && (AdyenTransactionStatus.CAPTURE_RECEIVED.equals(capture
                                    .getTransactionStatus())
                            || TransactionStatus.ACCEPTED.name().equals(
                                    capture.getTransactionStatus()));
        }
        return result;
    }
}