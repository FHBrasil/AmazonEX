/**
 * 
 */
package br.hering.storefront.controllers.notification;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.flieger.exacttarget.events.OrderPayedEvent;
import de.hybris.platform.commerceservices.order.CommerceCardTypeService;
import de.hybris.platform.core.enums.CreditCardType;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.payment.dto.CardType;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

/**
 * @author flieger
 */
@Controller
public class NotificationController
{
    
    protected static final Logger LOG = Logger.getLogger(NotificationController.class);
    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;
    @Resource(name = "modelService")
    private ModelService modelService;
    @Resource(name = "commonI18NService")
    private CommonI18NService commonI18NService;
    @Resource
    private EventService eventService;
    @Resource
    private OrderPayedEvent orderPayedEvent;
    @Resource
    private CommerceCardTypeService commerceCardTypeService;
    
    
    @RequestMapping(value = "/payment-notification/adyen_callback", method = RequestMethod.POST)
    public void performJob(ServletRequest request, ServletResponse response)
    {
        LOG.info("Language: [" + commonI18NService.getCurrentLanguage().getName() + "] Currency:["
                + commonI18NService.getCurrentCurrency().getName() + "]");
        String event = request.getParameter(NotificationConstants.EVENT_CODE);
        LOG.info("EventCode: [" + event + "]");
        try
        {
            if (verifyEventCode(event))
            {
                final Notification notification = populateNotificationObject(request);
                switch (event)
                {
                    case NotificationConstants.EVENT_PENDING:
                        handlePending(notification);
                        break;
                    case NotificationConstants.EVENT_AUTHORISATION:
                        handleAuthorization(notification);
                        break;
                    case NotificationConstants.EVENT_CANCELLATION:
                        handleCancellation(notification);
                        break;
                    case NotificationConstants.EVENT_CAPTURE:
                        handleCapture(notification);
                        break;
                    default:
                        LOG.info("Evento nao identificado:[" + event + "]");
                        break;
                }
            }
            else
            {
                LOG.info("EventCode nao tratado: [" + event + "]");
            }
        } catch (Exception e)
        {
            LOG.error("Erro na recepção da Notificação enviada pela Adyen.", e);
        } finally {
            try {
                response.getWriter().println("notificationResponse=[accepted]");
            } catch (Exception e) {
                LOG.error("Error writing the response object...", e);
            }
        }
    }
    
    
    /**
     * @param event
     * @return boolean
     */
    private boolean verifyEventCode(final String event)
    {
        if (event != null)
        {
            switch (event)
            {
                case NotificationConstants.EVENT_PENDING:
                    return true;
                case NotificationConstants.EVENT_AUTHORISATION:
                    return true;
                case NotificationConstants.EVENT_CANCEL_OR_REFUND:
                    return true;
                case NotificationConstants.EVENT_CANCELLATION:
                    return true;
                case NotificationConstants.EVENT_CAPTURE:
                    return true;
                case NotificationConstants.EVENT_CAPTURE_FAILED:
                    return true;
                case NotificationConstants.EVENT_REFUND:
                    return true;
                case NotificationConstants.EVENT_REFUND_FAILED:
                    return true;
                case NotificationConstants.EVENT_REFUNDED_REVERSED:
                    return true;
                case NotificationConstants.EVENT_REQUEST_FOR_INFORMATION:
                    return true;
                case NotificationConstants.EVENT_ADVICE_OF_DEBIT:
                    return true;
                case NotificationConstants.EVENT_NOTIFICATION_OF_CHARGEBACK:
                    return true;
                case NotificationConstants.EVENT_CHARGEBACK:
                    return true;
                case NotificationConstants.EVENT_CHARGEBACK_REVERSED:
                    return true;
            }
        }
        else
        {
            LOG.info("EventCode não informado.");
            return false;
        }
        return false;
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
    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService)
    {
        this.flexibleSearchService = flexibleSearchService;
    }
    
    
    // /*
    // * Get PaymentTransactionEntryModel based on PspReference passed by adyen notification
    // */
    // private PaymentTransactionEntryModel getPaymentTransactionEntryModel(
    // final Notification notification)
    // {
    // if(notification.getPaymentMethod().contains("boleto")){
    // if ( StringUtils.isEmpty(notification.getPspReference())
    // || StringUtils.isEmpty(notification.getMerchantReference()))
    // {
    // return null;
    // }
    // } else {
    // if ( StringUtils.isEmpty(notification.getOriginalReference())
    // || StringUtils.isEmpty(notification.getMerchantReference()))
    // {
    // return null;
    // }
    // }
    // try
    // {
    // final String fsDefault =
    // "SELECT {PK} "
    // + "FROM {PaymentTransactionEntry AS pte} "
    // + "WHERE {pte:" + PaymentTransactionEntryModel.ADYENREFERENCE + "} = ?adyenReference "
    // + "AND {pte:" + PaymentTransactionEntryModel.ADYENMERCHANTREFERENCE +
    // "} = ?merchantReference ";
    //
    // FlexibleSearchQuery query = new FlexibleSearchQuery(fsDefault);
    // if(notification.getPaymentMethod().contains("boleto")){
    // query.addQueryParameter("adyenReference", notification.getPspReference());
    // } else {
    // query.addQueryParameter("adyenReference", notification.getOriginalReference());
    // }
    // query.addQueryParameter("merchantReference", notification.getMerchantReference());
    // SearchResult<PaymentTransactionEntryModel> result =
    // getFlexibleSearchService().search(query);
    // List<PaymentTransactionEntryModel> lista = result.getResult();
    // if (!lista.isEmpty())
    // {
    // return lista.get(0);
    // }
    // else
    // {
    // LOG.info("Registro não encontrado para o adyenReference [" + notification.getPspReference()
    // + "] e MerchantReference [" + notification.getMerchantReference() + "] enviados.");
    // return null;
    // }
    // }
    // catch (Exception e)
    // {
    // LOG.error(e.getMessage(), e);
    // return null;
    // }
    // }
    /*
     * Populate NotificationObject based on parameters sent by adyen
     */
    private Notification populateNotificationObject(final ServletRequest request)
    {
        final Notification notification = new Notification();
        dateParser(request, notification);
        notification.setReason(request.getParameter(NotificationConstants.REASON));
        notification.setOriginalReference(request
                .getParameter(NotificationConstants.ORIGINAL_REFERENCE));
        notification.setMerchantReference(request
                .getParameter(NotificationConstants.MERCHANT_REFERENCE));
        notification.setCurrency(request.getParameter(NotificationConstants.CURRENCY));
        notification.setPspReference(request.getParameter(NotificationConstants.PSP_REFERENCE));
        notification.setMerchantAccountCode(request
                .getParameter(NotificationConstants.MERCHANTE_ACCOUNT_CODE));
        String valor = request.getParameter(NotificationConstants.VALUE);
        if (valor != null && !valor.equals(""))
        {
            BigDecimal bdvalue = new BigDecimal(valor).divide(new BigDecimal("100"));
            notification.setValue(bdvalue);
        }
        else
        {
            notification.setValue(new BigDecimal("0"));
        }
        if (!request.getParameter(NotificationConstants.SUCCESS).isEmpty())
        {
            notification
                    .setSuccess(new Boolean(request.getParameter(NotificationConstants.SUCCESS)));
        }
        notification.setOperations(request.getParameter(NotificationConstants.OPERATIONS));
        notification.setPaymentMethod(request.getParameter(NotificationConstants.PAYMENT_METHOD));
        if (!request.getParameter(NotificationConstants.LIVE).isEmpty())
        {
            notification.setLive(new Boolean(request.getParameter(NotificationConstants.LIVE)));
        }
        notification.setNossoNumero(request.getParameter(NotificationConstants.BOLETO_NOSSONUMERO));
        LOG.info(notification.toString());
        return notification;
    }
    
    
    /*
     * Parse a date in format yyyy-MM-ddTHH:mm:ss.SSZ
     */
    private void dateParser(final ServletRequest request, final Notification notification)
    {
        String orig = request.getParameter(NotificationConstants.EVENT_DATE).replace("%3A", ":");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
        try
        {
            notification.setEventDate(sdf.parse(orig));
        } catch (ParseException e)
        {
            notification.setEventDate(new Date(System.currentTimeMillis()));
        }
    }
    
    
    /**
     * Recebimento da confirmação do pagamento via carta de credito
     */
    private void handleCapture(final Notification notification)
    {
        PaymentTransactionEntryModel entryModel =
                getPaymentTransactionEntry(notification,
                        PaymentTransactionType.CAPTURE);
        if (entryModel == null)
        {
            return;
        }
        if (!notification.getPaymentMethod().contains("boletobancario"))
        {
            OrderModel orderModel = null;
            CartModel cartModel = null;
            AbstractOrderModel model = entryModel.getPaymentTransaction().getOrder();
            if (!notification.getSuccess().booleanValue())
            {
                LOG.info("Order [" + model.getCode() + "] não capturada. Razão ["
                        + notification.getReason() + "]");
                entryModel.setTransactionStatus(TransactionStatus.ERROR.name());
                entryModel.setTransactionStatusDetails(notification.getReason());
                model.setStatus(OrderStatus.PAYMENT_NOT_CAPTURED);
                model.setPaymentStatus(PaymentStatus.NOTPAID);
            }
            else
            {
                entryModel.setTransactionStatus(TransactionStatus.ACCEPTED.name());
                model.setStatus(OrderStatus.COMPLETED);
                model.setPaymentStatus(PaymentStatus.PAID);
                LOG.info("Order [" + model.getCode() + "] capturada via CC.");
            }
            modelService.save(entryModel);
            if (entryModel.getPaymentTransaction().getOrder() instanceof OrderModel) {
                LOG.info("instanceof OrderModel");
                orderModel = (OrderModel) model;
                modelService.save(orderModel);
            } else if (entryModel.getPaymentTransaction().getOrder() instanceof CartModel) {
                LOG.info("instanceof CartModel");
                cartModel = (CartModel) model;
                modelService.save(cartModel);
            }
            if (orderModel != null) {
                orderPayedEvent.setOrder(orderModel);
            }
            LOG.info("DISPARO DE EMAIL NotificationContorller ORDER[" + orderModel.getCode() + "]");
            eventService.publishEvent(orderPayedEvent);
        }
        else
        {
            LOG.info("Notificação de CAPTURE para Cartão de Crédito não reconhecida. ["
                    + notification.toString() + "]");
        }
    }
    
    
    /*
     * Realiza o recebimento do Nosso Numero do Boleto e persistencia
     */
    private void handlePending(final Notification notification)
    {
        PaymentTransactionEntryModel paymentTransactionModel =
                getPaymentTransactionEntry(notification,
                        PaymentTransactionType.AUTHORIZATION);
        if (paymentTransactionModel == null)
        {
            return;
        }
        if (notification.getNossoNumero() != null)
        {
            paymentTransactionModel.setAdyenBoletoNossoNumero(notification.getNossoNumero());
            modelService.save(paymentTransactionModel);
            modelService.refresh(paymentTransactionModel);
            LOG.info("Notification PENDING recebida com o Nosso Número ["
                    + notification.getNossoNumero() + "]");
        }
        else
        {
            LOG.info("PENDING Notification com additionalData.acquirerReference não informado.");
        }
    }
    
    
    /*
     * Realiza o recebimento do informe de pagamento do boleto na instituição financeira
     * Pegar a transação de Authorização e setar ela com o status enviado pela Adyen
     */
    private void handleAuthorization(final Notification notification)
    {
        PaymentTransactionEntryModel entryModel =
                getPaymentTransactionEntry(notification,
                        PaymentTransactionType.AUTHORIZATION);
        if (entryModel == null)
        {
            return;
        }
        AbstractOrderModel orderModel = entryModel.getPaymentTransaction().getOrder();
        if (notification.getPaymentMethod().contains("boletobancario"))
        {
            // if (notification.getSuccess().booleanValue())
            // {
            // entryModel.setTransactionStatus(TransactionStatus.ACCEPTED.name());
            // orderModel.setStatus(OrderStatus.COMPLETED);
            // orderModel.setPaymentStatus(PaymentStatus.PAID);
            // modelService.save(orderModel);
            // LOG.info("Order [" + orderModel.getCode() + "] com boleto [" +
            // entryModel.getAdyenBoletoNossoNumero()
            // + "] compensado.");
            // }
            // else
            // {
            // entryModel.setTransactionStatus(TransactionStatus.ERROR.name());
            // orderModel.setStatus(OrderStatus.PAYMENT_NOT_AUTHORIZED);
            // orderModel.setPaymentStatus(PaymentStatus.NOTPAID);
            // LOG.info("Order [" + orderModel.getCode() + "] Razão: [" + notification.getReason()
            // + "]");
            // }
            // modelService.save(entryModel);
            // if(orderModel instanceof OrderModel) {
            // orderPayedEvent.setOrder((OrderModel)orderModel);
            // }
            // eventService.publishEvent(orderPayedEvent);
            LOG.info("Notificação de pagamento do boleto da Order [" + orderModel.getCode()
                    + "] tratada pelo backend.");
        }
        else
        {
            if (!notification.getSuccess().booleanValue())
            {
                entryModel.setTransactionStatus(TransactionStatus.ERROR.name());
                orderModel.setStatus(OrderStatus.PAYMENT_NOT_AUTHORIZED);
                orderModel.setPaymentStatus(PaymentStatus.NOTPAID);
                LOG.info("Order [" + orderModel.getCode()
                        + "] not authorized. Razao: [" + notification.getReason() + "]");
            }
            else
            {
                if (orderModel instanceof OrderModel) {
                    orderPayedEvent.setOrder((OrderModel) orderModel);
                }
                eventService.publishEvent(orderPayedEvent);
                final CardType cardType =
                        commerceCardTypeService.getCardTypeForCode(notification.getPaymentMethod());
                if (cardType != null
                        && orderModel.getPaymentInfo() instanceof CreditCardPaymentInfoModel)
                {
                    final CreditCardType creditCardType = cardType.getCode();
                    ((CreditCardPaymentInfoModel) orderModel.getPaymentInfo())
                            .setType(creditCardType);
                }
                modelService.save(entryModel);
                modelService.save(orderModel.getPaymentInfo());
            }
        }
    }
    
    
    /*
     * Realiza o recebimento
     */
    private void handleCancellation(final Notification notification)
    {
        PaymentTransactionEntryModel entryModel =
                getPaymentTransactionEntry(notification,
                        PaymentTransactionType.AUTHORIZATION);
        if (entryModel == null)
        {
            return;
        }
        AbstractOrderModel orderModel = entryModel.getPaymentTransaction().getOrder();
        entryModel.setTransactionStatus(TransactionStatus.ACCEPTED.name());
        orderModel.setStatus(OrderStatus.ESTORNO);
        orderModel.setPaymentStatus(PaymentStatus.NOTPAID);
        modelService.save(entryModel);
        modelService.save(orderModel);
        LOG.info("Order [" + orderModel.getCode() + "] Cancelada.");
    }
    
    
    /*
     * Get PaymentTransactionEntryModel based on PspReference passed by adyen notification
     */
    private PaymentTransactionEntryModel getPaymentTransactionEntry(
            final Notification notification,
            final PaymentTransactionType transactionType)
    {
        Assert.notNull(transactionType, "TransactionType cannot be null");
        Assert.notNull(notification, "Notification cannot be null");
        if (notification.getPaymentMethod().contains("boleto")) {
            if (StringUtils.isEmpty(notification.getPspReference())
                    || StringUtils.isEmpty(notification.getMerchantReference()))
            {
                return null;
            }
        } else {
            if (StringUtils.isEmpty(notification.getOriginalReference())
                    || StringUtils.isEmpty(notification.getMerchantReference()))
            {
                return null;
            }
        }
        try
        {
            final String fsDefault =
                    "SELECT {PK} "
                            + "FROM {PaymentTransactionEntry AS pte} "
                            + "WHERE {pte:" + PaymentTransactionEntryModel.ADYENREFERENCE
                            + "} = ?adyenReference "
                            + "AND {pte:"
                            + PaymentTransactionEntryModel.ADYENMERCHANTREFERENCE
                            + "} = ?merchantReference "
                            // esta deveria ser o join com a order, mas calma
                            + "AND {pte:" + PaymentTransactionEntryModel.TYPE
                            + "} = ?paymentTransactionType";
            FlexibleSearchQuery query = new FlexibleSearchQuery(fsDefault);
            // if (notification.getPaymentMethod().contains("boleto")) {
            query.addQueryParameter("adyenReference", notification.getPspReference());
            // } else {
            // query.addQueryParameter("adyenReference", notification.getOriginalReference());
            // }
            query.addQueryParameter("merchantReference", notification.getMerchantReference());
            query.addQueryParameter("paymentTransactionType", transactionType);
            SearchResult<PaymentTransactionEntryModel> result =
                    getFlexibleSearchService().search(query);
            List<PaymentTransactionEntryModel> lista = result.getResult();
            if (!lista.isEmpty())
            {
                return lista.get(0);
            }
            else
            {
                LOG.info("PaymentTransactionEntry of type: " + transactionType
                        + " não encontrado para o adyenReference ["
                        + notification.getPspReference()
                        + "] e MerchantReference ["
                        + notification.getMerchantReference() + "] enviados.");
                return null;
            }
        } catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }
}
