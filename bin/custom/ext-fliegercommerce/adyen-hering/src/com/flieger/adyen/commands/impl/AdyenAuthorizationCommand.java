/**
 * 
 */
package com.flieger.adyen.commands.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.adyen.services.common.Amount;
import com.adyen.services.common.Installments;
import com.adyen.services.payment.AdyenAuthorizationRequest;
import com.adyen.services.payment.AdyenAuthorizationResult;
import com.adyen.services.payment.AnyType2AnyTypeMapEntry;
import com.adyen.services.payment.Card;
import com.adyen.services.payment.PaymentRequest;
import com.adyen.services.payment.PaymentResult;
import com.flieger.adyen.command.AuthorizationCommand;
import com.flieger.constants.AdyenTransactionStatus;
import com.flieger.services.WebServicesConn;

import de.hybris.platform.payment.dto.AvsStatus;
import de.hybris.platform.payment.dto.CvnStatus;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;

/**
 * @author flieger
 */
public class AdyenAuthorizationCommand implements AuthorizationCommand
{
    
    private static final String ECOMMERCE = "Ecommerce";
    private static final String ONECLICK = "ONECLICK";
    private static final Logger LOG = Logger.getLogger(AdyenAuthorizationCommand.class.getName());
    
    
    /*
     * (non-Javadoc)
     * @see com.adyen.services.payment.impl.AdyenCardPaymentService#authorize()
     */
    @Override
    public AdyenAuthorizationResult perform(AdyenAuthorizationRequest request)
    {
        PaymentResult payResult = null;
        AdyenAuthorizationResult result = new AdyenAuthorizationResult();
        try
        {
            result.setCurrency(request.getCurrency());
            result.setTotalAmount(request.getValue());
            result.setAvsStatus(AvsStatus.NO_RESULT);
            result.setCvnStatus(CvnStatus.MATCHED);
            result.setAuthorizationTime(new Date());
            result.setAdyenUniqueCode(request.getReference());
            // Payment data
            PaymentRequest payRequest = new PaymentRequest();
            payRequest.setMerchantAccount(request.getMerchantAccount());
            Amount amount = new Amount();
            amount.setCurrency(request.getCurrency().getCurrencyCode());
            amount.setValue(request.getValue().multiply(new BigDecimal("100")).longValueExact());
            payRequest.setAmount(amount);
            payRequest.setReference(request.getReference());
            if (request.getBuyerEmail().contains("|"))
            {
                payRequest.setShopperEmail(request.getBuyerEmail().split("\\|")[1]);
                payRequest.setShopperReference(request.getUserUid().split("\\|")[1]);
            }
            else
            {
                 payRequest.setShopperReference(request.getUserUid());
                 payRequest.setShopperEmail(request.getBuyerEmail());
            }
            // Recurring recurring = new Recurring(ONECLICK, payRequest.getOrderReference());
            // payRequest.setRecurring(recurring);
            Installments installment = new Installments(request.getInstallments());
            payRequest.setInstallments(installment);
            Card card = new Card();
            card.setHolderName(request.getCard().getCardHolderFullName());
            card.setCvc(request.getCard().getCv2Number());
            String month = request.getCard().getExpirationMonth().toString();
            if (month.length() < 2) {
                month = "0" + month;
            }
            card.setExpiryMonth(month);
            card.setExpiryYear(request.getCard().getExpirationYear().toString());
            card.setNumber(request.getCard().getCardNumber());
            payRequest.setCard(card);
            payRequest.setShopperInteraction(ECOMMERCE);
            /*
             * Parte com recurring
             */
            // if(request.getStantBuyKey() != null) {
            // payRequest.setSelectedRecurringDetailReference(request.getStantBuyKey());
            // }
            try
            {
                LOG.info("Adyen payment request\n" + payRequest);
                payResult = new WebServicesConn().authorise(payRequest);
            } catch (Exception e)
            {
                result.setTransactionStatus(TransactionStatus.ERROR);
                result.setTransactionStatusDetails(TransactionStatusDetails.COMMUNICATION_PROBLEM);
                LOG.error("Error: [" + e.getMessage() + "]", e);
                return result;
            }
            result.setAdyenReference(payResult.getPspReference());
            result.setAuthorizationCode(payResult.getAuthCode());
            if (!StringUtils.isEmpty(payResult.getRefusalReason())
                    && payResult.getRefusalReason().matches("(^[0-9]{3}).*")) {
                result.setErrorCode(payResult.getRefusalReason().substring(0, 3));
            }
            LOG.info("------------------------------");
            LOG.info("PaymentResult");
            LOG.info("MerchantReference: " + request.getReference());
            LOG.info("PspReference: " + payResult.getPspReference());
            LOG.info("ResultCode: " + payResult.getResultCode());
            LOG.info("AuthCode: " + payResult.getAuthCode());
            LOG.info("RefusalReason: " + payResult.getRefusalReason());
            // LOG.info(payResult.getDccAmount());
            // LOG.info(payResult.getDccSignature());
            // LOG.info(payResult.getFraudResult());
            // LOG.info(payResult.getIssuerUrl());
            // LOG.info(payResult.getMd());
            // LOG.info(payResult.getPaRequest());
            if (payResult.getAdditionalData() != null) {
                LOG.info("Entrys...");
                for (AnyType2AnyTypeMapEntry entry : payResult.getAdditionalData()) {
                    if (!StringUtils.isEmpty(entry.getKey().toString())
                            && entry.getKey().equals("refusalReasonRaw")) {
                        result.setErrorCode(entry.getValue().toString());
                    }
                    LOG.info("Key: [" + entry.getKey() + "] Value:[" + entry.getValue() + "]");
                }
            }
            Calendar today = Calendar.getInstance();
            if (today.get(1) > request.getCard().getExpirationYear().intValue())
            {
                result.setTransactionStatus(TransactionStatus.REJECTED);
                result.setTransactionStatusDetails(TransactionStatusDetails.INVALID_CARD_EXPIRATION_DATE);
            } else if ((today.get(1) == request.getCard().getExpirationYear().intValue())
                    && (today.get(2) > request.getCard().getExpirationMonth().intValue()))
            {
                result.setTransactionStatus(TransactionStatus.REJECTED);
                result.setTransactionStatusDetails(TransactionStatusDetails.INVALID_CARD_EXPIRATION_DATE);
            } else if (payResult.getResultCode() != null
                    && payResult.getResultCode().equals(AdyenTransactionStatus.AUTHORIZED))
            {
                result.setTransactionStatus(TransactionStatus.ACCEPTED);
                result.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL);
            } else if (payResult.getResultCode() != null
                    && payResult.getResultCode().equals(AdyenTransactionStatus.RECEIVED))
            {
                result.setTransactionStatus(TransactionStatus.ACCEPTED);
                result.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL);
            } else
            {
                result.setTransactionStatus(TransactionStatus.REJECTED);
                result.setTransactionStatusDetails(TransactionStatusDetails.AUTHORIZATION_REJECTED_BY_PSP);
            }
        } catch (Exception ex)
        {
            result.setErrorCode("00");
            LOG.error("Ocorreu um erro na autorização do cartão pelo Adyen [" + ex.getMessage()
                    + "]", ex);
            result.setTransactionStatus(TransactionStatus.ERROR);
            result.setTransactionStatusDetails(TransactionStatusDetails.UNKNOWN_CODE);
        }
        result.setMerchantTransactionCode(request.getMerchantTransactionCode());
        result.setHeringAdyenErrorCode(payResult.getRefusalReason());
        return result;
    }
}
