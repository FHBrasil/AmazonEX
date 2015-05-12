package com.flieger.payment.commands.impl;

import com.flieger.payment.api.data.schemas.*;
import com.flieger.payment.api.service.Services;
import de.hybris.platform.payment.commands.AuthorizationCommand;
import de.hybris.platform.payment.commands.request.AuthorizationRequest;
import de.hybris.platform.payment.commands.result.AuthorizationResult;
import de.hybris.platform.payment.dto.AvsStatus;
import de.hybris.platform.payment.dto.CvnStatus;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import de.hybris.platform.util.Config;
import java.util.Calendar;
import java.util.Date;

public class MundipaggAuthorizationCommand extends MundipaggGenericCommand
		implements AuthorizationCommand
{

	public static final long REVIEW_AMOUNT = 5000L;

	@Override
	public AuthorizationResult perform(AuthorizationRequest request)
	{
		AuthorizationResult result = new AuthorizationResult();

		result.setCurrency(request.getCurrency());
		result.setTotalAmount(request.getTotalAmount());

		result.setAvsStatus(AvsStatus.NO_RESULT);
		result.setCvnStatus(CvnStatus.NOT_PROCESSED);

		result.setAuthorizationTime(new Date());

		String cardBrand = request.getCard().getCardType().getType();

		Long amountInCents = new Long(request.getTotalAmount().longValue());

		CreateOrderRequest createOrderRequest = ObjectFactory.createCreateOrderRequest();

		createOrderRequest.setMerchantKey(Config.getParameter("mundipagg.merchantkey"));
		createOrderRequest.setCurrencyIsoEnum(CurrencyIsoEnum.EUR);
		createOrderRequest.setAmountInCents(amountInCents);

		CreditCardTransaction creditCardTransaction = ObjectFactory.createCreditCardTransaction();
		creditCardTransaction.setAmountInCents(amountInCents);
		creditCardTransaction.setCreditCardBrandEnum(CreditCardBrandEnum.valueOf(cardBrand));
		creditCardTransaction.setInstallmentCount(0);
		creditCardTransaction.setCreditCardNumber(ObjectFactory.createCreditCardTransactionCreditCardNumber(request.getCard().getIssueNumber()));
		creditCardTransaction.setExpMonth(request.getCard().getIssueMonth());
		creditCardTransaction.setExpYear(request.getCard().getIssueYear());
		creditCardTransaction.setHolderName(ObjectFactory.createCreditCardTransactionHolderName(request.getCard().getCardHolderFullName()));
		creditCardTransaction.setSecurityCode(ObjectFactory.createCreditCardTransactionSecurityCode(request.getCard().getCv2Number()));
		creditCardTransaction.setPaymentMethodCode(1);
		creditCardTransaction.setCreditCardOperationEnum(ObjectFactory.createCreditCardTransactionCreditCardOperationEnum(CreditCardOperationEnum.AUTH_AND_CAPTURE));
		ArrayOfCreditCardTransaction creditCardTransactionCollection = ObjectFactory.createArrayOfCreditCardTransaction();

		creditCardTransactionCollection.getCreditCardTransaction().add(creditCardTransaction);

		createOrderRequest.setCreditCardTransactionCollection(ObjectFactory.createCreateOrderRequestCreditCardTransactionCollection(creditCardTransactionCollection));

		CreateOrderResponse mundiResponse = null;
		ArrayOfCreditCardTransactionResult cardTransaction = mundiResponse.getCreditCardTransactionResultCollection().getValue();

		String transactionStatus = cardTransaction.getCreditCardTransactionResult().get(0).getCreditCardTransactionStatusEnum().value();

		Services service = new Services();

		try
		{
			mundiResponse = service.createOrder(createOrderRequest);
		} catch (Exception e)
		{
			System.out.print(e);
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
		} else if (transactionStatus.equals(CreditCardTransactionStatusEnum.AUTHORIZED_PENDING_CAPTURE.value()))
		{
			result.setTransactionStatus(TransactionStatus.REVIEW);
			result.setTransactionStatusDetails(TransactionStatusDetails.REVIEW_NEEDED);
		} else if (transactionStatus.equals(CreditCardTransactionStatusEnum.CAPTURED.value()))
		{
			result.setTransactionStatus(TransactionStatus.ACCEPTED);
			result.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL);
		} else
		{
			result.setTransactionStatus(TransactionStatus.REJECTED);
			result.setTransactionStatusDetails(TransactionStatusDetails.AUTHORIZATION_REJECTED_BY_PSP);

		}

		genericPerform(request, result);

		return result;
	}
}
