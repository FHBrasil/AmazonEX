/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.payment.impl;

import com.flieger.payment.MundipaggBoletoPaymentService;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.flieger.payment.MundipaggCardPaymentService;
import com.flieger.payment.MundipaggPaymentService;
import com.flieger.payment.commands.request.MundipaggAuthorizationCaptureRequest;
import com.flieger.payment.commands.request.MundipaggBoletoRequest;
import com.flieger.payment.commands.result.MundipaggAuthorizationResult;
import com.flieger.payment.commands.result.MundipaggBoletoResult;
import com.flieger.payment.model.MundipaggBoletoPaymentInfoModel;

import de.hybris.platform.core.enums.CreditCardType;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.dto.BillingInfo;
import de.hybris.platform.payment.dto.CardInfo;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.impl.DefaultPaymentServiceImpl;
import de.hybris.platform.payment.methods.CardPaymentService;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;

/**
 *
 * @author Antony
 */
public class DefaultMundipaggPaymentService extends DefaultPaymentServiceImpl implements MundipaggPaymentService
{

   @Resource
   private ModelService modelService;
   @Resource
   private CommonI18NService commonI18NService;
   private CartService cartService;
   private SessionService sessionService;
   private MundipaggBoletoPaymentService boletoPaymentService;
   
   public SessionService getSessionService()
   {
      return sessionService;
   }

   public void setSessionService(SessionService sessionService)
   {
      this.sessionService = sessionService;
   }

   public void setModelService(ModelService modelService)
   {
      this.modelService = modelService;
   }

   public void setCommonI18NService(CommonI18NService commonI18NService)
   {
      this.commonI18NService = commonI18NService;
   }

   public CardPaymentService getCardPaymentService()
   {
      return super.getCardPaymentService();
   }

   public CartService getCartService()
   {
      return cartService;
   }

   public void setCartService(CartService cartService)
   {
      this.cartService = cartService;
   }

   public MundipaggBoletoPaymentService getBoletoPaymentService()
   {
      return boletoPaymentService;
   }

   public void setBoletoPaymentService(MundipaggBoletoPaymentService boletoPaymentService)
   {
      this.boletoPaymentService = boletoPaymentService;
   }

   @Override
   public PaymentTransactionEntryModel authorize(String merchantTransactionCode, BigDecimal amount, Currency currency, 
   AddressModel deliveryAddress, String subscriptionID, String cv2, String paymentProvider)
           throws AdapterException
   {

      if (!paymentProvider.equals("Mundipagg"))
      {
         return super.authorize(merchantTransactionCode, amount, currency, deliveryAddress, subscriptionID, cv2, paymentProvider);
      }

      BillingInfo shippingInfo = createBillingInfo(deliveryAddress);

      PaymentTransactionModel transaction = (PaymentTransactionModel) modelService.create(PaymentTransactionModel.class);
      transaction.setCode(merchantTransactionCode);

      return authorizeInternalMundipagg(transaction, amount, currency, shippingInfo, null, subscriptionID, cv2, paymentProvider);

   }
   
   
   @Override
   public PaymentTransactionEntryModel authorize(String merchantTransactionCode, BigDecimal paramBigDecimal, 
   Currency paramCurrency, AddressModel paramAddressModel, String paymentProvider)
   {
      PaymentTransactionModel transaction = (PaymentTransactionModel) modelService.create(PaymentTransactionModel.class);
      transaction.setCode(merchantTransactionCode);

      return authorizeInternalMundipaggBoleto(transaction, paymentProvider);
   }
   
   
   private PaymentTransactionEntryModel authorizeInternalMundipaggBoleto (PaymentTransactionModel transaction, String paymentProvider)
   {
      
      String newEntryCode = getNewEntryCode(transaction);
      MundipaggBoletoResult result;
      MundipaggBoletoPaymentService boletoPaymentService = this.getBoletoPaymentService();
	      
      
      CartModel cartModel = getCartService().getSessionCart();
      
      result = (MundipaggBoletoResult) boletoPaymentService.authorize(new MundipaggBoletoRequest(paymentProvider, 
              cartModel, newEntryCode, (MundipaggBoletoPaymentInfoModel)cartModel.getPaymentInfo()));

      transaction.setRequestId(result.getMundipaggRequestKey());
      transaction.setRequestToken(result.getMundipaggRequestKey());
      transaction.setPaymentProvider(paymentProvider);
      this.modelService.save(transaction);

      PaymentTransactionEntryModel entry = (PaymentTransactionEntryModel) this.modelService.create(PaymentTransactionEntryModel.class);
      entry.setAmount(result.getTotalAmount());
      if (result.getCurrency() != null)
      {
         entry.setCurrency(this.commonI18NService.getCurrency(result.getCurrency().getCurrencyCode()));
      }
      
      if (result.getTransactionStatusDetails().equals(TransactionStatusDetails.REVIEW_NEEDED))
      {
         entry.setType(PaymentTransactionType.AUTHORIZATION);
      } else if (result.getTransactionStatusDetails().equals(TransactionStatusDetails.SUCCESFULL))
      {
         entry.setType(PaymentTransactionType.CAPTURE);
      }
      entry.setTime(result.getAuthorizationTime() == null ? new Date() : result.getAuthorizationTime());
      entry.setPaymentTransaction(transaction);
      entry.setRequestId(result.getMundipaggRequestKey());
      entry.setRequestToken(result.getMundipaggRequestKey());
      entry.setTransactionStatus(result.getTransactionStatus().toString());
      entry.setTransactionStatusDetails(result.getTransactionStatusDetails().toString());
      entry.setCode(newEntryCode);
      entry.setMundipaggOrderKey(result.getMundipaggOrderKey());
      entry.setMundipaggRequestKey(result.getMundipaggRequestKey());
      entry.setMundipaggTransactionKey(result.getMundipaggTransactionKey());
      entry.setMundipaggBoletoBarCode(result.getMundipaggBoletoBarCode());
      entry.setMundipaggBoletoNossoNumero(result.getMundipaggBoletoNossoNumero());
      entry.setMundipaggBoletoStatus(result.getMundipaggBoletoStatus());
      entry.setMundipaggBoletoUrl(result.getMundipaggBoletoURL());
      entry.setMundipaggOrderReference(result.getMundipaggOrderReference());
      entry.setMundipaggTransactionReference(result.getMundipaggTransactionReference());
  
      this.modelService.save(entry);
      this.modelService.refresh(transaction);
      
      return entry;
   }
   
   private PaymentTransactionEntryModel authorizeInternalMundipagg(PaymentTransactionModel transaction, BigDecimal amount, Currency currency, BillingInfo shippingInfo, CardInfo card, String subscriptionID, String cv2, String paymentProvider)
           throws AdapterException
   {
      String newEntryCode = getNewEntryCode(transaction);
      MundipaggAuthorizationResult result;

	  CartModel cart = getCartService().getSessionCart();
      card = createCardInfo();

      MundipaggCardPaymentService cardPaymentService = (MundipaggCardPaymentService) this.getCardPaymentService();
	if (!hasInstantBuyKey())
      {
         
         CreditCardPaymentInfoModel paymentInfo = getPaymentInfo();
         card.setCardNumber( (String) getSessionService().getAttribute("temporaryCardNumber"));
         result = (MundipaggAuthorizationResult) cardPaymentService.authorizeAndCreateStantBuyKey(new MundipaggAuthorizationCaptureRequest(newEntryCode, subscriptionID, currency,
         amount, shippingInfo, cv2, paymentProvider, card, null,paymentInfo.getCpfForMundipagg(), cart));
         
         if(paymentInfo.isSaved()){
            paymentInfo = getPaymentInfo();
            paymentInfo.setMundipaggStantBuyKey(result.getMundipaggInstantBuyKey());
            this.modelService.save(paymentInfo);
            
         }
      }else{
      
         result = (MundipaggAuthorizationResult) cardPaymentService.authorize(new MundipaggAuthorizationCaptureRequest(newEntryCode, subscriptionID, currency,
              amount, shippingInfo, paymentProvider, card, getPaymentInfo().getMundipaggStantBuyKey(), cart, getPaymentInfo().getCpfForMundipagg()));
      
      }

      transaction.setRequestId(result.getRequestId());
      transaction.setRequestToken(result.getRequestToken());
      transaction.setPaymentProvider(result.getPaymentProvider());
      this.modelService.save(transaction);

      PaymentTransactionEntryModel entry = (PaymentTransactionEntryModel) this.modelService.create(PaymentTransactionEntryModel.class);
      entry.setAmount(result.getTotalAmount());
      if (result.getCurrency() != null)
      {
         entry.setCurrency(this.commonI18NService.getCurrency(result.getCurrency().getCurrencyCode()));
      }
      
      if (result.getTransactionStatusDetails().equals(TransactionStatusDetails.REVIEW_NEEDED))
      {
         entry.setType(PaymentTransactionType.AUTHORIZATION);
      } else if (result.getTransactionStatusDetails().equals(TransactionStatusDetails.SUCCESFULL))
      {
         entry.setType(PaymentTransactionType.CAPTURE);
      }
      entry.setTime(result.getAuthorizationTime() == null ? new Date() : result.getAuthorizationTime());
      entry.setPaymentTransaction(transaction);
      entry.setRequestId(result.getRequestId());
      entry.setRequestToken(result.getRequestToken());
      entry.setTransactionStatus(result.getTransactionStatus().toString());
      entry.setTransactionStatusDetails(result.getTransactionStatusDetails().toString());
      entry.setCode(newEntryCode);
      entry.setMundipaggOrderKey(result.getMundipaggOrderKey());
      entry.setMundipaggRequestKey(result.getMundipaggRequestKey());
      entry.setMundipaggTransactionKey(result.getMundipaggTransactionKey());
      entry.setMundipaggOrderReference(result.getMundipaggOrderReference());
      entry.setMundipaggTransactionReference(result.getMundipaggTransactionReference());
      
      if (subscriptionID != null)
      {
         entry.setSubscriptionID(subscriptionID);
      }
      this.modelService.save(entry);
      this.modelService.refresh(transaction);
      return entry;
   }

   private BillingInfo createBillingInfo(AddressModel address)
   {
      if (address == null)
      {
         return null;
      }

      BillingInfo billingInfo = new BillingInfo();

      billingInfo.setCity(address.getTown());
      if (address.getCountry() != null)
      {
         billingInfo.setCountry(address.getCountry().getIsocode());
      }
      billingInfo.setEmail(address.getEmail());
      billingInfo.setFirstName(address.getFirstname());
      billingInfo.setLastName(address.getLastname());
      billingInfo.setPhoneNumber(address.getPhone1());
      billingInfo.setPostalCode(address.getPostalcode());
      if (address.getRegion() != null)
      {
         billingInfo.setState(address.getRegion().getName());
      }
      billingInfo.setStreet1(address.getStreetname());
      billingInfo.setStreet2(address.getStreetnumber());

      return billingInfo;
   }

   private CardInfo createCardInfo()
   {

      CardInfo cardInfo = new CardInfo();
      CreditCardPaymentInfoModel paymentInfo = (CreditCardPaymentInfoModel) getCartService().getSessionCart().getPaymentInfo();
      cardInfo.setCardHolderFullName(paymentInfo.getCcOwner());
      cardInfo.setCardNumber(paymentInfo.getNumber());
      CreditCardType creditCardType = paymentInfo.getType();
      cardInfo.setCardType(creditCardType);
      cardInfo.setExpirationMonth(Integer.valueOf(paymentInfo.getValidToMonth()));
      cardInfo.setExpirationYear(Integer.valueOf(paymentInfo.getValidToYear()));
      if (paymentInfo.getIssueNumber() != null)
      {
         cardInfo.setIssueNumber(paymentInfo.getIssueNumber().toString());
      }
      return cardInfo;
   }

   private boolean hasInstantBuyKey()
   {
      
      return StringUtils.isNotBlank(getPaymentInfo().getMundipaggStantBuyKey());
   }
   
   private CreditCardPaymentInfoModel getPaymentInfo()
   {
      return (CreditCardPaymentInfoModel) getCartService().getSessionCart().getPaymentInfo();
   }
}
