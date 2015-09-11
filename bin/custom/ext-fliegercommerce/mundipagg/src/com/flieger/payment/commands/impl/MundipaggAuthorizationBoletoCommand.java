/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.payment.commands.impl;

import com.flieger.payment.api.data.schemas.AddressTypeEnum;
import com.flieger.payment.api.data.schemas.ArrayOfBoletoTransaction;
import com.flieger.payment.api.data.schemas.ArrayOfBoletoTransactionResult;
import com.flieger.payment.api.data.schemas.ArrayOfBuyerAddress;
import com.flieger.payment.api.data.schemas.ArrayOfShoppingCart;
import com.flieger.payment.api.data.schemas.ArrayOfShoppingCartItem;
import com.flieger.payment.api.data.schemas.BoletoTransaction;
import com.flieger.payment.api.data.schemas.BoletoTransactionResult;
import com.flieger.payment.api.data.schemas.BoletoTransactionStatusEnum;
import com.flieger.payment.api.data.schemas.Buyer;
import com.flieger.payment.api.data.schemas.BuyerAddress;
import com.flieger.payment.api.data.schemas.CountryEnum;
import com.flieger.payment.api.data.schemas.CreateOrderRequest;
import com.flieger.payment.api.data.schemas.CreateOrderResponse;
import com.flieger.payment.api.data.schemas.CurrencyIsoEnum;
import com.flieger.payment.api.data.schemas.GenderEnum;
import com.flieger.payment.api.data.schemas.ObjectFactory;
import com.flieger.payment.api.data.schemas.PersonTypeEnum;
import com.flieger.payment.api.data.schemas.ShoppingCart;
import com.flieger.payment.api.data.schemas.ShoppingCartItem;
import com.flieger.payment.api.data.schemas.TaxDocumentTypeEnum;
import com.flieger.payment.api.service.Services;
import com.flieger.payment.commands.AuthorizationBoletoCommand;
import com.flieger.payment.commands.request.MundipaggBoletoRequest;
import com.flieger.payment.commands.result.MundipaggBoletoResult;
import com.flieger.payment.model.MundipaggBoletoPaymentInfoModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.payment.commands.result.AuthorizationResult;
import de.hybris.platform.payment.dto.AvsStatus;
import de.hybris.platform.payment.dto.CvnStatus;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import de.hybris.platform.util.Config;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author Antony
 */
public class MundipaggAuthorizationBoletoCommand extends MundipaggGenericCommand implements AuthorizationBoletoCommand
{

   @Override
   public AuthorizationResult perform(MundipaggBoletoRequest request)
   {

      MundipaggBoletoResult result = new MundipaggBoletoResult();
      MundipaggBoletoPaymentInfoModel paymentInfo = request.getPaymetInfo();

      CartModel cart = request.getCart();
      CustomerModel user = (CustomerModel) cart.getUser();

      AddressModel deliveryAddress = cart.getDeliveryAddress();
      List<AbstractOrderEntryModel> cartEntries = cart.getEntries();


      BigDecimal totalAmount = new BigDecimal(cart.getTotalPrice().doubleValue());
      result.setTotalAmount(totalAmount);

      result.setAvsStatus(AvsStatus.NO_RESULT);
      result.setCvnStatus(CvnStatus.MATCHED);

      result.setAuthorizationTime(new Date());

      Long amountInCents = new Long(totalAmount.longValue() * 100);

      CreateOrderRequest createOrderRequest = ObjectFactory.createCreateOrderRequest();

      Buyer buyer = ObjectFactory.createBuyer();

      buyer.setTaxDocumentNumber(ObjectFactory.createBuyerTaxDocumentNumber(request.getPaymetInfo().getCpfForMundipagg()));
      buyer.setTaxDocumentTypeEnum(ObjectFactory.createBuyerTaxDocumentTypeEnum(TaxDocumentTypeEnum.CPF));
      buyer.setHomePhone(ObjectFactory.createBuyerHomePhone(deliveryAddress.getPhone1()));
      buyer.setEmail(ObjectFactory.createBuyerEmail(user.getUid()));
      buyer.setBuyerAddressCollection(createBuyerAddressCollection(cart));
      buyer.setPersonTypeEnum(ObjectFactory.createBuyerPersonTypeEnum(PersonTypeEnum.PERSON));
      buyer.setGenderEnum(getGender(user));

      createOrderRequest.setBuyer(ObjectFactory.createBuyer(buyer));

      createOrderRequest.setMerchantKey(Config.getParameter("mundipagg.merchantkey"));
      createOrderRequest.setCurrencyIsoEnum(CurrencyIsoEnum.BRL);
      createOrderRequest.setAmountInCents(amountInCents);
      createOrderRequest.setShoppingCartCollection(createShoppingCart(cart));
      createOrderRequest.setOrderReference(ObjectFactory.createOrderDataOrderReference(cart.getCode()));


      BoletoTransaction boletoTransaction = ObjectFactory.createBoletoTransaction();
      boletoTransaction.setAmountInCents(amountInCents);
      //boletoTransaction.setDaysToAddInBoletoExpirationDate(ObjectFactory.createBoletoTransactionDaysToAddInBoletoExpirationDate(new Integer(paymentInfo.getDaysToAddInBoletoExpirationDate())));
      boletoTransaction.setNossoNumero(ObjectFactory.createBoletoTransactionNossoNumero(paymentInfo.getNossoNumero()));
      boletoTransaction.setTransactionReference(ObjectFactory.createBoletoTransactionResultTransactionReference(paymentInfo.getTransactionReference()));

      ArrayOfBoletoTransaction boleto = ObjectFactory.createArrayOfBoletoTransaction();
      boleto.getBoletoTransaction().add(boletoTransaction);

      createOrderRequest.setBoletoTransactionCollection(ObjectFactory.createCreateOrderRequestBoletoTransactionCollection(boleto));


      CreateOrderResponse mundiResponse = null;

      Services service = new Services();

      if (service != null)
      {
         try
         {

            mundiResponse = service.createOrder(createOrderRequest);

         } catch (Exception e)
         {
            
            result.setTransactionStatus(TransactionStatus.ERROR);
            result.setTransactionStatusDetails(TransactionStatusDetails.COMMUNICATION_PROBLEM);
            genericPerform(request, result);
            return result;
         }
      } else
      {
         result.setTransactionStatus(TransactionStatus.ERROR);
         result.setTransactionStatusDetails(TransactionStatusDetails.COMMUNICATION_PROBLEM);

      }

      ArrayOfBoletoTransactionResult boletoTransactionResult = mundiResponse.getBoletoTransactionResultCollection().getValue();

      if (!boletoTransactionResult.getBoletoTransactionResult().isEmpty() && mundiResponse != null)
      {

         mundiResponse.getBoletoTransactionResultCollection().getValue();
         BoletoTransactionResult bltTransactionResult = boletoTransactionResult.getBoletoTransactionResult().get(0);
         String transactionStatusDetail = bltTransactionResult.getBoletoTransactionStatusEnum().value();
         boolean transactionsStatus = mundiResponse.isSuccess();
         String transactionKey = bltTransactionResult.getTransactionKey();
         String orderKey = mundiResponse.getOrderKey();
         String requestKey = mundiResponse.getRequestKey();
         String orderReference = mundiResponse.getOrderReference().getValue();
         String transactionReference = bltTransactionResult.getTransactionReference().getValue();
         String boletoBarCode = bltTransactionResult.getBarcode().getValue();
         String boletoURL = bltTransactionResult.getBoletoUrl().getValue();
         String boletoNossoNumero = bltTransactionResult.getNossoNumero().getValue();

         if (!transactionsStatus)
         {
            result.setTransactionStatus(TransactionStatus.REJECTED);
            result.setTransactionStatusDetails(TransactionStatusDetails.UNKNOWN_CODE);
         } else if (transactionsStatus && transactionStatusDetail.equals(BoletoTransactionStatusEnum.WITH_ERROR.value()))
         {
            result.setTransactionStatus(TransactionStatus.ERROR);
            result.setTransactionStatusDetails(TransactionStatusDetails.UNKNOWN_CODE);
         } else if (transactionStatusDetail.equals(BoletoTransactionStatusEnum.GENERATED.value()) && transactionsStatus)
         {
            result.setTransactionStatus(TransactionStatus.REVIEW);
            result.setTransactionStatusDetails(TransactionStatusDetails.REVIEW_NEEDED);
            result.setMundipaggOrderKey(orderKey);
            result.setMundipaggRequestKey(requestKey);
            result.setMundipaggTransactionKey(transactionKey);
            result.setMundipaggOrderReference(orderReference);
            result.setMundipagTransactionReference(transactionReference);
            result.setMundipaggBoletoBarCode(boletoBarCode);
            result.setMundipaggBoletoNossoNumero(boletoNossoNumero);
            result.setMundipaggBoletoURL(boletoURL);
            result.setMundipaggBoletoStatus(transactionStatusDetail);

         } else
         {
            result.setTransactionStatus(TransactionStatus.REJECTED);
            result.setTransactionStatusDetails(TransactionStatusDetails.AUTHORIZATION_REJECTED_BY_PSP);

         }
      } else
      {
         result.setTransactionStatus(TransactionStatus.REJECTED);
         result.setTransactionStatusDetails(TransactionStatusDetails.AUTHORIZATION_REJECTED_BY_PSP);

      }
      genericPerform(request, result);

      return result;

   }

   private JAXBElement<ArrayOfBuyerAddress> createBuyerAddressCollection(CartModel cart)
   {
      AddressModel billingAddress = cart.getPaymentInfo().getBillingAddress();
      AddressModel deliveryAddress = cart.getDeliveryAddress();

      BuyerAddress buyerBillingAddress = createBillingAddress(billingAddress);
      BuyerAddress buyerDeliveryAddress = createDeliveryAddress(deliveryAddress);

      ArrayOfBuyerAddress arrayOfBuyerAddress = ObjectFactory.createArrayOfBuyerAddress();

      arrayOfBuyerAddress.getBuyerAddress().add(buyerBillingAddress);
      arrayOfBuyerAddress.getBuyerAddress().add(buyerDeliveryAddress);

      return ObjectFactory.createBuyerBuyerAddressCollection(arrayOfBuyerAddress);

   }

   private BuyerAddress createBillingAddress(AddressModel billingAddress)
   {
      BuyerAddress buyerBillingAddress = ObjectFactory.createBuyerAddress();
      buyerBillingAddress.setAddressTypeEnum(AddressTypeEnum.BILLING);
      buyerBillingAddress.setCity(ObjectFactory.createBuyerAddressCity(billingAddress.getTown()));
      buyerBillingAddress.setCountryEnum(CountryEnum.BRAZIL);
      buyerBillingAddress.setStreet(ObjectFactory.createBuyerAddressStreet(billingAddress.getLine1()));
      buyerBillingAddress.setZipCode(ObjectFactory.createBuyerAddressZipCode(billingAddress.getPostalcode()));
      buyerBillingAddress.setDistrict(ObjectFactory.createBuyerAddressDistrict("Cidade Industrial de Curitiba"));
      buyerBillingAddress.setNumber(ObjectFactory.createBuyerAddressNumber("349"));
      buyerBillingAddress.setState(ObjectFactory.createBuyerAddressState("PR"));

      return buyerBillingAddress;
   }

   private BuyerAddress createDeliveryAddress(AddressModel deliveryAddress)
   {
      BuyerAddress buyerDeliveryAddress = ObjectFactory.createBuyerAddress();
      buyerDeliveryAddress.setAddressTypeEnum(AddressTypeEnum.SHIPPING);
      buyerDeliveryAddress.setCity(ObjectFactory.createBuyerAddressCity(deliveryAddress.getTown()));
      buyerDeliveryAddress.setCountryEnum(CountryEnum.BRAZIL);
      buyerDeliveryAddress.setStreet(ObjectFactory.createBuyerAddressStreet(deliveryAddress.getLine1()));
      buyerDeliveryAddress.setZipCode(ObjectFactory.createBuyerAddressZipCode(deliveryAddress.getPostalcode()));
      buyerDeliveryAddress.setDistrict(ObjectFactory.createBuyerAddressDistrict("Cidade Industrial de Curitiba"));
      buyerDeliveryAddress.setNumber(ObjectFactory.createBuyerAddressNumber("349"));
      buyerDeliveryAddress.setState(ObjectFactory.createBuyerAddressState("PR"));

      return buyerDeliveryAddress;
   }

   private JAXBElement<ArrayOfShoppingCart> createShoppingCart(CartModel cart)
   {

      ShoppingCart shoppingCart = ObjectFactory.createShoppingCart();

      ArrayOfShoppingCartItem arrayOfCartItems = ObjectFactory.createArrayOfShoppingCartItem();

      List<AbstractOrderEntryModel> cartEntries = cart.getEntries();

      for (AbstractOrderEntryModel entry : cartEntries)
      {
         ShoppingCartItem cartItem = ObjectFactory.createShoppingCartItem();
         cartItem.setDescription(ObjectFactory.createShoppingCartItemDescription(entry.getProduct().getDescription()));
         cartItem.setItemReference(ObjectFactory.createShoppingCartItemItemReference(entry.getProduct().getCode()));
         cartItem.setName(ObjectFactory.createShoppingCartItemName(entry.getProduct().getName()));
         cartItem.setQuantity(entry.getQuantity().intValue());

         int totalInCents = (int) (entry.getBasePrice() * 100 * entry.getQuantity().intValue());
         int unitCostInCents = (int) (entry.getBasePrice() * 100);
         cartItem.setTotalCostInCents(totalInCents);
         cartItem.setUnitCostInCents(unitCostInCents);
         arrayOfCartItems.getShoppingCartItem().add(cartItem);
      }

      shoppingCart.setShoppingCartItemCollection(ObjectFactory.createShoppingCartShoppingCartItemCollection(arrayOfCartItems));

      ArrayOfShoppingCart arrayShoppingCart = ObjectFactory.createArrayOfShoppingCart();
      arrayShoppingCart.getShoppingCart().add(shoppingCart);

      return ObjectFactory.createCreateOrderRequestShoppingCartCollection(arrayShoppingCart);

   }

   private JAXBElement<GenderEnum> getGender(CustomerModel user)
   {

      String title = user.getTitle().getCode();

      if (title.equalsIgnoreCase("mr"))
      {
         return ObjectFactory.createBuyerGenderEnum(GenderEnum.M);
      } else
      {
         return ObjectFactory.createBuyerGenderEnum(GenderEnum.F);
      }
   }
}
