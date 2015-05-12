/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.payment.commands.impl;

import com.flieger.payment.commands.AuthorizationCaptureCommand;
import com.flieger.payment.commands.request.MundipaggAuthorizationCaptureRequest;
import com.flieger.payment.api.data.schemas.*;
import com.flieger.payment.api.service.Services;
import com.flieger.payment.commands.result.MundipaggAuthorizationResult;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.payment.commands.result.AuthorizationResult;
import de.hybris.platform.payment.dto.AvsStatus;
import de.hybris.platform.payment.dto.CvnStatus;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import de.hybris.platform.util.Config;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Antony
 */
public class MundipaggAuthorizationCaptureCommand extends MundipaggGenericCommand implements AuthorizationCaptureCommand
{

	@Override
	public AuthorizationResult perform(MundipaggAuthorizationCaptureRequest request)
	{
		MundipaggAuthorizationResult result = new MundipaggAuthorizationResult();

		CartModel cart = request.getCart();
		CustomerModel user = (CustomerModel) cart.getUser();
		
		AddressModel deliveryAddress = cart.getDeliveryAddress();
		List<AbstractOrderEntryModel> cartEntries = cart.getEntries();

		result.setCurrency(request.getCurrency());
		result.setTotalAmount(request.getTotalAmount());

		result.setAvsStatus(AvsStatus.NO_RESULT);
		result.setCvnStatus(CvnStatus.MATCHED);
		deliveryAddress.getGender();
		result.setAuthorizationTime(new Date());

		String cardBrand = request.getCard().getCardType().toString();

		Long amountInCents = new Long(request.getTotalAmount().longValue() * 100);

		CreateOrderRequest createOrderRequest = ObjectFactory.createCreateOrderRequest();

		Buyer buyer = ObjectFactory.createBuyer();

		buyer.setTaxDocumentNumber(ObjectFactory.createBuyerTaxDocumentNumber(request.getCpfForMundipagg()));
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

		CreditCardTransaction creditCardTransaction = ObjectFactory.createCreditCardTransaction();
		creditCardTransaction.setAmountInCents(amountInCents);
		creditCardTransaction.setCreditCardBrandEnum(CreditCardBrandEnum.valueOf(cardBrand));
		creditCardTransaction.setInstallmentCount(0);
		if (StringUtils.isNotBlank(request.getCard().getCardNumber()))
		{
			creditCardTransaction.setCreditCardNumber(ObjectFactory.createCreditCardTransactionCreditCardNumber(request.getCard().getCardNumber()));
		}
		creditCardTransaction.setExpMonth(request.getCard().getExpirationMonth());
		creditCardTransaction.setExpYear(request.getCard().getExpirationYear());
		creditCardTransaction.setHolderName(ObjectFactory.createCreditCardTransactionHolderName(request.getCard().getCardHolderFullName()));
		creditCardTransaction.setSecurityCode(ObjectFactory.createCreditCardTransactionSecurityCode(request.getCv2()));
		creditCardTransaction.setPaymentMethodCode(1);
		creditCardTransaction.setCreditCardOperationEnum(ObjectFactory.createCreditCardTransactionCreditCardOperationEnum(CreditCardOperationEnum.AUTH_AND_CAPTURE));
		ArrayOfCreditCardTransaction creditCardTransactionCollection = ObjectFactory.createArrayOfCreditCardTransaction();

		creditCardTransactionCollection.getCreditCardTransaction().add(creditCardTransaction);

		createOrderRequest.setCreditCardTransactionCollection(ObjectFactory.createCreateOrderRequestCreditCardTransactionCollection(creditCardTransactionCollection));

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
                
                
		ArrayOfCreditCardTransactionResult cardTransaction = mundiResponse.getCreditCardTransactionResultCollection().getValue();
                
                if(!cardTransaction.getCreditCardTransactionResult().isEmpty() && mundiResponse != null)
                {
                
                     cardTransaction = mundiResponse.getCreditCardTransactionResultCollection().getValue();
                   
                     CreditCardTransactionResult ccTransactionResult = cardTransaction.getCreditCardTransactionResult().get(0);
                     String transactionStatusDetail = ccTransactionResult.getCreditCardTransactionStatusEnum().value();
                     boolean transactionsStatus = mundiResponse.isSuccess();
                     String instantBuyKey = cardTransaction.getCreditCardTransactionResult().get(0).getInstantBuyKey();
                     String transactionKey = cardTransaction.getCreditCardTransactionResult().get(0).getTransactionKey();
                     String orderKey = mundiResponse.getOrderKey();
                     String requestKey = mundiResponse.getRequestKey();
                     String orderReference = mundiResponse.getOrderReference().getValue();
                     String transactionReference = ccTransactionResult.getTransactionReference().getValue();
                
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
                     } else if (transactionStatusDetail.equals(CreditCardTransactionStatusEnum.AUTHORIZED_PENDING_CAPTURE.value()) && transactionsStatus)
                     {
			result.setTransactionStatus(TransactionStatus.REVIEW);
			result.setTransactionStatusDetails(TransactionStatusDetails.REVIEW_NEEDED);
                        result.setMundipaggInstantBuyKey(instantBuyKey);
                        result.setMundipaggOrderKey(orderKey);
                        result.setMundipaggRequestKey(requestKey);
                        result.setMundipaggTransactionKey(transactionKey);
                        result.setMundipaggOrderReference(orderReference);
                        result.setMundipaggTransactionReference(transactionReference);
                     } else if (transactionStatusDetail.equals(CreditCardTransactionStatusEnum.CAPTURED.value()) && transactionsStatus)
                     {
			result.setTransactionStatus(TransactionStatus.ACCEPTED);
			result.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL);
                        result.setMundipaggInstantBuyKey(instantBuyKey);
                        result.setMundipaggOrderKey(orderKey);
                        result.setMundipaggRequestKey(requestKey);
                        result.setMundipaggTransactionKey(transactionKey);
                        result.setMundipaggOrderReference(orderReference);
                        result.setMundipaggTransactionReference(transactionReference);
                     } else
                     {
			result.setTransactionStatus(TransactionStatus.REJECTED);
			result.setTransactionStatusDetails(TransactionStatusDetails.AUTHORIZATION_REJECTED_BY_PSP);

                     }
                } else
		{
			result.setTransactionStatus(TransactionStatus.ERROR);
			result.setTransactionStatusDetails(TransactionStatusDetails.COMMUNICATION_PROBLEM);

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
