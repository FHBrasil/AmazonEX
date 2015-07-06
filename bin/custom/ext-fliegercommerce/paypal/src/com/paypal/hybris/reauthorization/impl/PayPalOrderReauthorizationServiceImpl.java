package com.paypal.hybris.reauthorization.impl;

import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import br.hering.core.model.order.payment.PaypalPaymentInfoModel;

import com.ebay.api.AckCodeType;
import com.ebay.api.BasicAmountType;
import com.ebay.api.CurrencyCodeType;
import com.ebay.api.DoReauthorizationRequestType;
import com.ebay.api.DoReauthorizationResponseType;
import com.ebay.api.PaymentStatusCodeType;
import com.ebay.api.PendingStatusCodeType;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.data.PaymentStatus;
import com.paypal.hybris.enums.PaymentActionType;
import com.paypal.hybris.reauthorization.PayPalOrderReauthorizationService;
import com.paypal.hybris.service.PaypalPaymentService;


/**
 * @author Dzmitry_Rasolka (EPAM Systems)
 */
public class PayPalOrderReauthorizationServiceImpl implements PayPalOrderReauthorizationService
{

	private static final int HONOR_PERIOD_IN_DAYS = 3;
	private static final int VALID_PERIOD_IN_DAYS = 29;
	private static final TimeZone TIME_ZONE = TimeZone.getTimeZone("America/Los_Angeles");

	private ModelService modelService;
	private PaypalPaymentService paypalPaymentService;
	private CommonI18NService commonI18NService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.paypal.hybris.reauthorization.PayPalOrderReauthorizationService#isReauthorizationPossible(de.hybris.platform
	 * .core.model.order.OrderModel)
	 */
	@Override
	public boolean isReauthorizationPossible(final OrderModel orderModel)
	{
		if (!(orderModel.getPaymentInfo() instanceof PaypalPaymentInfoModel))
		{
			return false;
		}
		if (!PaymentActionType.AUTHORIZATION.equals(((PaypalPaymentInfoModel) orderModel.getPaymentInfo()).getPaymentAction()))
		{
			return false;
		}
		Date authorizationTime = null;
		for (final PaymentTransactionModel paymentTransactionModel : orderModel.getPaymentTransactions())
		{
			for (final PaymentTransactionEntryModel paymentTransactionEntryModel : paymentTransactionModel.getEntries())
			{
				if (PaymentTransactionType.CAPTURE.equals(paymentTransactionEntryModel.getType())
						|| PaymentTransactionType.REAUTHORIZATION.equals(paymentTransactionEntryModel.getType()))
				{
					return false;
				}
				else if (PaymentTransactionType.AUTHORIZATION.equals(paymentTransactionEntryModel.getType()))
				{
					authorizationTime = paymentTransactionEntryModel.getTime();
				}
			}
		}
		if (!isDateBeforeExpiration(authorizationTime, VALID_PERIOD_IN_DAYS)
				|| isDateBeforeExpiration(authorizationTime, HONOR_PERIOD_IN_DAYS))
		{
			return false;
		}
		return true;
	}

	private boolean isDateBeforeExpiration(final Date date, final int expirationInDays)
	{
		final Calendar now = Calendar.getInstance(TIME_ZONE);
		final Calendar targetDate = Calendar.getInstance();
		targetDate.setTime(date);
		targetDate.setTimeZone(TIME_ZONE);
		targetDate.add(Calendar.DAY_OF_YEAR, expirationInDays);
		targetDate.set(targetDate.get(Calendar.YEAR), targetDate.get(Calendar.MONTH), targetDate.get(Calendar.DATE), 23, 50, 0);
		if (now.before(targetDate))
		{
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.paypal.hybris.reauthorization.PayPalOrderReauthorizationService#reauthorize(de.hybris.platform.core.model.
	 * order.OrderModel, java.lang.String)
	 */
	@Override
	public OrderModel reauthorize(final OrderModel orderModel, final String amount)
	{
		final String currencyID = orderModel.getCurrency().getIsocode().toString();

		final DoReauthorizationRequestType doReauthorizationRequest = new DoReauthorizationRequestType();
		doReauthorizationRequest.setVersion(PaypalConstants.SOAP_API_VERSION);
		final BasicAmountType amountType = new BasicAmountType();
		amountType.setValue(amount);
		amountType.setCurrencyID(CurrencyCodeType.fromValue(currencyID));
		doReauthorizationRequest.setAmount(amountType);
		for (final PaymentTransactionModel paymentTransactionModel : orderModel.getPaymentTransactions())
		{
			for (final PaymentTransactionEntryModel paymentTransactionEntryModel : paymentTransactionModel.getEntries())
			{
				if (PaymentTransactionType.AUTHORIZATION.equals(paymentTransactionEntryModel.getType()))
				{
					doReauthorizationRequest.setAuthorizationID(paymentTransactionEntryModel.getRequestId());
				}
			}
		}
		final DoReauthorizationResponseType doReauthorizationResponseType = getPaypalPaymentService().doReauthorization(
				doReauthorizationRequest);
		PaymentTransactionEntryModel reauthTransactionEntry;
		if (AckCodeType.SUCCESS.equals(doReauthorizationResponseType.getAck()))
		{
			if (PaymentStatusCodeType.PENDING == doReauthorizationResponseType.getAuthorizationInfo().getPaymentStatus()
					&& PendingStatusCodeType.AUTHORIZATION != doReauthorizationResponseType.getAuthorizationInfo().getPendingReason())
			{
				reauthTransactionEntry = createTransactionEntry(orderModel.getPaymentTransactions().get(0),
						PaymentTransactionType.REAUTHORIZATION, PaymentStatus.PENDING.name(), doReauthorizationResponseType
								.getAuthorizationInfo().getPendingReason().name(), doReauthorizationResponseType.getAuthorizationID(),
						currencyID, amount, doReauthorizationResponseType.getTimestamp().toGregorianCalendar().getTime());
			}
			else
			{
				reauthTransactionEntry = createTransactionEntry(orderModel.getPaymentTransactions().get(0),
						PaymentTransactionType.REAUTHORIZATION, TransactionStatus.ACCEPTED.name(),
						TransactionStatusDetails.SUCCESFULL.name(), doReauthorizationResponseType.getAuthorizationID(), currencyID,
						amount, doReauthorizationResponseType.getTimestamp().toGregorianCalendar().getTime());
			}
		}
		else
		{
			reauthTransactionEntry = createTransactionEntry(orderModel.getPaymentTransactions().get(0),
					PaymentTransactionType.REAUTHORIZATION, TransactionStatus.ERROR.name(),
					TransactionStatusDetails.UNKNOWN_CODE.name(), doReauthorizationResponseType.getAuthorizationID(), currencyID,
					amount, doReauthorizationResponseType.getTimestamp().toGregorianCalendar().getTime());
		}
		for (final PaymentTransactionModel paymentTransactionModel : orderModel.getPaymentTransactions())
		{
			if (PaypalConstants.PAYMENT_PROVIDER_NAME.equals(paymentTransactionModel.getPaymentProvider()))
			{
				final List<PaymentTransactionEntryModel> entries = new ArrayList<>(paymentTransactionModel.getEntries());
				entries.add(reauthTransactionEntry);
				paymentTransactionModel.setEntries(entries);
			}
		}
		getModelService().saveAll(reauthTransactionEntry, orderModel);
		return orderModel;
	}

	private PaymentTransactionEntryModel createTransactionEntry(final PaymentTransactionModel transaction,
			final PaymentTransactionType type, final String status, final String statusDetails, final String requestId,
			final String currencyIsoCode, final String amount, final Date timeStamp)
	{
		final PaymentTransactionEntryModel paymentTransactionEntry = getModelService().create(PaymentTransactionEntryModel.class);

		paymentTransactionEntry.setPaymentTransaction(transaction);
		paymentTransactionEntry.setRequestId(requestId);
		paymentTransactionEntry.setType(type);
		paymentTransactionEntry.setTransactionStatus(status);
		paymentTransactionEntry.setTransactionStatusDetails(statusDetails);

		final String code = PaypalConstants.PAYMENT_PROVIDER_NAME + "_reauthorization_stamp_" + System.currentTimeMillis();
		paymentTransactionEntry.setCode(code);

		final CurrencyModel currency = getCommonI18NService().getCurrency(currencyIsoCode);
		paymentTransactionEntry.setCurrency(currency);

		final BigDecimal transactionAmount = new BigDecimal(amount.replace(",", ""));
		paymentTransactionEntry.setAmount(transactionAmount);

		paymentTransactionEntry.setTime(timeStamp);

		return paymentTransactionEntry;
	}


	public ModelService getModelService()
	{
		return modelService;
	}


	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public PaypalPaymentService getPaypalPaymentService()
	{
		return paypalPaymentService;
	}

	public void setPaypalPaymentService(final PaypalPaymentService paypalPaymentService)
	{
		this.paypalPaymentService = paypalPaymentService;
	}

	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

}
