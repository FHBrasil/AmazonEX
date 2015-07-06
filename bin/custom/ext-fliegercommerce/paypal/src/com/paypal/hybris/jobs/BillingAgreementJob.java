/**
 *
 */
package com.paypal.hybris.jobs;

import br.hering.core.model.order.payment.PaypalPaymentInfoModel;

import com.ebay.api.*;
import com.paypal.hybris.converters.impl.PayPalRequestDataConverter;
import com.paypal.hybris.converters.impl.PayPalResponseConverter;
import com.paypal.hybris.data.DoReferenceTransactionRequestData;
import com.paypal.hybris.data.DoReferenceTransactionResultData;
import com.paypal.hybris.data.PendingReason;
import com.paypal.hybris.facade.impl.PayPalPaymentFacade;
import com.paypal.hybris.service.OrderCompleteProcessService;

import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import de.hybris.platform.site.BaseSiteService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.data.PaymentStatus;
import com.paypal.hybris.service.PaypalPaymentService;

/**
 * @author Aliaksei_Sery (EPAM Systems)
 */
public class BillingAgreementJob extends AbstractJobPerformable<CronJobModel> {
    
    private static final Logger LOG = Logger.getLogger(BillingAgreementJob.class);
    private ConfigurationService configurationService;
    private PaypalPaymentService paypalPaymentService;
    private ModelService modelService;
    private CommonI18NService commonI18NService;
    private Converter<OrderModel, OrderData> orderConverter;
    private PayPalPaymentFacade paypalPaymentFacade;
    private OrderCompleteProcessService orderCompleteProcessService;
    private BaseSiteService baseSiteService;
    
    
    /**
     * @return the modelService
     */
    public ModelService getModelService() {
        return modelService;
    }
    
    
    /**
     * @param modelService
     *            the modelService to set
     */
    @Override
    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }
    
    
    /**
     * @return the commonI18NService
     */
    public CommonI18NService getCommonI18NService() {
        return commonI18NService;
    }
    
    
    /**
     * @param commonI18NService
     *            the commonI18NService to set
     */
    public void setCommonI18NService(final CommonI18NService commonI18NService) {
        this.commonI18NService = commonI18NService;
    }
    
    
    /**
     * @return the configurationService
     */
    public ConfigurationService getConfigurationService() {
        return configurationService;
    }
    
    
    /**
     * @param configurationService
     *            the configurationService to set
     */
    @Required
    public void setConfigurationService(final ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
    
    
    /**
     * @return the paypalPaymentService
     */
    public PaypalPaymentService getPaypalPaymentService() {
        return paypalPaymentService;
    }
    
    
    /**
     * @param paypalPaymentService
     *            the paypalPaymentService to set
     */
    @Required
    public void setPaypalPaymentService(final PaypalPaymentService paypalPaymentService) {
        this.paypalPaymentService = paypalPaymentService;
    }
    
    
    /**
     * @return the order converter
     */
    public Converter<OrderModel, OrderData> getOrderConverter() {
        return orderConverter;
    }
    
    
    /**
     * @param orderConverter
     *            the order converter to set
     */
    @Required
    public void setOrderConverter(Converter<OrderModel, OrderData> orderConverter) {
        this.orderConverter = orderConverter;
    }
    
    
    /**
     * @return the paypal payment facade
     */
    public PayPalPaymentFacade getPaypalPaymentFacade() {
        return paypalPaymentFacade;
    }
    
    
    /**
     * @param paypalPaymentFacade
     *            payment facade to set
     */
    public void setPaypalPaymentFacade(PayPalPaymentFacade paypalPaymentFacade) {
        this.paypalPaymentFacade = paypalPaymentFacade;
    }
    
    
    public BaseSiteService getBaseSiteService() {
        return baseSiteService;
    }
    
    
    public void setBaseSiteService(BaseSiteService baseSiteService) {
        this.baseSiteService = baseSiteService;
    }
    
    
    /*
     * (non-Javadoc)
     * @see
     * de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.
     * cronjob.model.CronJobModel
     * )
     */
    @Override
    public PerformResult perform(final CronJobModel cronJob) {
        if (Boolean.TRUE.toString().equalsIgnoreCase(
                getConfigurationService().getConfiguration().getString(
                        PaypalConstants.USE_REFERENCE_TRANSCATION))) {
            LOG.info("Perform PayPal billing agreement payment process..");
            final StringBuilder query = new StringBuilder();
            query.append("select {pk} from {order} where {status} in ({{select {pk} from {orderstatus} where {code} like 'PAYMENT_NOT_CAPTURED'}})");
            final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query.toString());
            final SearchResult result = flexibleSearchService.search(searchQuery);
            final List res = result.getResult();
            for (final Iterator it = res.iterator(); it.hasNext();) {
                final OrderModel order = (OrderModel) it.next();
                getBaseSiteService().setCurrentBaseSite(order.getSite(), false);
                PaymentInfoModel paymentInfoModel = order.getPaymentInfo();
                if (paymentInfoModel instanceof PaypalPaymentInfoModel
                        && Boolean.TRUE.equals(((PaypalPaymentInfoModel) paymentInfoModel)
                                .getUseReferenceTransaction())) {
                    PaypalPaymentInfoModel payPalPaymentInfoModel =
                            (PaypalPaymentInfoModel) paymentInfoModel;
                    final OrderData orderData = getOrderConverter().convert(order);
                    DoReferenceTransactionRequestData requestData =
                            new DoReferenceTransactionRequestData();
                    requestData.setOrderData(orderData);
                    requestData.setReferenceId(payPalPaymentInfoModel.getBillingAgreementID());
                    String code = payPalPaymentInfoModel.getCode();
                    if (PaypalConstants.PAYPAL_CREDIT_PAYMENT_INFO_CODE.equals(code)) {
                        requestData.setCredit(true);
                    } else {
                        requestData.setCredit(false);
                    }
                    try {
                        DoReferenceTransactionResultData resultData =
                                getPaypalPaymentFacade().doReferenceTransaction(requestData);
                        // create payment entry
                        PaymentTransactionEntryModel transactionEntry;
                        if (PaypalConstants.STATUS_SUCCESS.equals(resultData.getAck())) {
                            final String currencyID = order.getCurrency().getIsocode().toString();
                            if (PaymentStatus.PENDING == resultData.getPaymentStatus()
                                    && PendingReason.AUTHORIZATION != resultData.getPendingReason()) {
                                transactionEntry =
                                        createTransactionEntry(
                                                order.getPaymentTransactions().get(0),
                                                PaymentTransactionType.CAPTURE,
                                                PaymentStatus.PENDING.name(), resultData
                                                        .getPendingReason().name(),
                                                resultData.getTransactionId(), currencyID, order
                                                        .getTotalPrice().toString(), resultData
                                                        .getDateTime().getTime());
                            } else {
                                transactionEntry =
                                        createTransactionEntry(
                                                order.getPaymentTransactions().get(0),
                                                PaymentTransactionType.CAPTURE,
                                                TransactionStatus.ACCEPTED.name(),
                                                TransactionStatusDetails.SUCCESFULL.name(),
                                                resultData.getTransactionId(), currencyID, order
                                                        .getTotalPrice().toString(), resultData
                                                        .getDateTime().getTime());
                            }
                            for (final PaymentTransactionModel paymentTransactionModel : order
                                    .getPaymentTransactions()) {
                                if (PaypalConstants.PAYMENT_PROVIDER_NAME
                                        .equals(paymentTransactionModel.getPaymentProvider())) {
                                    final List<PaymentTransactionEntryModel> entries =
                                            new ArrayList<>(paymentTransactionModel.getEntries());
                                    entries.add(transactionEntry);
                                    paymentTransactionModel.setEntries(entries);
                                }
                            }
                            // update order status
                            order.setStatus(OrderStatus.PAYMENT_CAPTURED);
                            getModelService().saveAll(transactionEntry, order);
                            orderCompleteProcessService.startOrderCompletionProcess(order);
                        } else {
                            LOG.error("Errors during doReferenceTransaction call: "
                                    + StringUtils.join(resultData.getErrorMessagesList(), "; "));
                        }
                    } catch (Exception e) {
                        LOG.error("DoReferenceTransaction call is impassible.", e);
                    }
                }
            }
        }
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }
    
    
    /**
     * @return
     */
    /*
     * protected DoReferenceTransactionRequestType prepareReferenceTransactionRequest(final
     * OrderModel order)
     * {
     * final DoReferenceTransactionRequestType request = new DoReferenceTransactionRequestType();
     * final DoReferenceTransactionRequestDetailsType requestDetails = new
     * DoReferenceTransactionRequestDetailsType();
     * request.setVersion(PaypalConstants.SOAP_API_VERSION);
     * String billingAgreementID = null;
     * if (order.getPaymentInfo() instanceof PaypalPaymentInfoModel)
     * {
     * billingAgreementID = ((PaypalPaymentInfoModel)
     * order.getPaymentInfo()).getBillingAgreementID();
     * }
     * requestDetails.setReferenceID(billingAgreementID);
     * requestDetails.setPaymentAction(PaymentActionCodeType.SALE);
     * final PaymentDetailsType paymentDetails = new PaymentDetailsType();
     * final CurrencyCodeType currencyCode =
     * CurrencyCodeType.fromValue(order.getCurrency().getIsocode());
     * final BasicAmountType amount = createBasicAmountType(order.getTotalPrice().doubleValue(),
     * currencyCode);
     * paymentDetails.setOrderTotal(amount);
     * requestDetails.setPaymentDetails(paymentDetails);
     * paymentDetails.setInvoiceID("hybris-" + System.currentTimeMillis());
     * request.setDoReferenceTransactionRequestDetails(requestDetails);
     * return request;
     * }
     */
    /*
     * protected BasicAmountType createBasicAmountType(final double amount, final CurrencyCodeType
     * currencyCode)
     * {
     * final BasicAmountType basicAmount = new BasicAmountType();
     * basicAmount.setValue(PaypalStringUtils.formatNumber(amount));
     * basicAmount.setCurrencyID(currencyCode);
     * return basicAmount;
     * }
     */
    private PaymentTransactionEntryModel createTransactionEntry(
            final PaymentTransactionModel transaction, final PaymentTransactionType type,
            final String status, final String statusDetails, final String requestId,
            final String currencyIsoCode, final String amount, final Date timeStamp) {
        final PaymentTransactionEntryModel paymentTransactionEntry =
                getModelService().create(PaymentTransactionEntryModel.class);
        paymentTransactionEntry.setPaymentTransaction(transaction);
        paymentTransactionEntry.setRequestId(requestId);
        paymentTransactionEntry.setType(type);
        paymentTransactionEntry.setTransactionStatus(status);
        paymentTransactionEntry.setTransactionStatusDetails(statusDetails);
        final String code = PaypalConstants.PAYMENT_PROVIDER_NAME
                + "_reauthorization_stamp_" + System.currentTimeMillis();
        paymentTransactionEntry.setCode(code);
        final CurrencyModel currency = getCommonI18NService().getCurrency(currencyIsoCode);
        paymentTransactionEntry.setCurrency(currency);
        final BigDecimal transactionAmount = new BigDecimal(amount.replace(",", ""));
        paymentTransactionEntry.setAmount(transactionAmount);
        paymentTransactionEntry.setTime(timeStamp);
        return paymentTransactionEntry;
    }
    
    
    public OrderCompleteProcessService getOrderCompleteProcessService() {
        return orderCompleteProcessService;
    }
    
    
    public void setOrderCompleteProcessService(
            OrderCompleteProcessService orderCompleteProcessService) {
        this.orderCompleteProcessService = orderCompleteProcessService;
    }
}
