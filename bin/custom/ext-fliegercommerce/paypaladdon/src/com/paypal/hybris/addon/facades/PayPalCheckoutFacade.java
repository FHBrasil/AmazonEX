/**
 *
 */
package com.paypal.hybris.addon.facades;

import com.paypal.hybris.facade.impl.PayPalPaymentFacade;
import de.hybris.platform.acceleratorfacades.order.impl.DefaultAcceleratorCheckoutFacade;
import de.hybris.platform.acceleratorservices.uiexperience.UiExperienceService;
import de.hybris.platform.commercefacades.i18n.I18NFacade;
import de.hybris.platform.commercefacades.order.CheckoutFacade;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commerceservices.enums.UiExperienceLevel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.order.CalculationService;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.session.SessionService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.paypal.hybris.addon.constants.PaypaladdonConstants;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.data.AbstractResultData;
import com.paypal.hybris.data.DoAuthorizationRequestData;
import com.paypal.hybris.data.DoAuthorizationResultData;
import com.paypal.hybris.data.DoExpressCheckoutPaymentRequestData;
import com.paypal.hybris.data.DoExpressCheckoutPaymentResultData;
import com.paypal.hybris.data.PaymentInfoData;
import com.paypal.hybris.data.PaymentStatus;
import com.paypal.hybris.data.PendingReason;
import com.paypal.hybris.data.ResultErrorData;
import com.paypal.hybris.model.PaypalPaymentInfoModel;
import com.paypal.hybris.service.PaypalPaymentService;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
@Component
public class PayPalCheckoutFacade extends DefaultAcceleratorCheckoutFacade
{
	private static final Logger LOG = Logger.getLogger(PayPalCheckoutFacade.class);

	@Resource(name = "payPalPaymentFacade")
	private PayPalPaymentFacade payPalPaymentFacade;

	@Resource(name = "calculationService")
	private CalculationService calculationService;

	@Resource(name = "flexibleSearchService")
	private FlexibleSearchService flexibleSearchService;

	@Resource(name = "i18NFacade")
	private I18NFacade i18NFacade;
	private ConfigurationService configurationService;

	@Resource(name = "checkoutFacade")
	private CheckoutFacade checkoutFacade;

	@Resource
	private SessionService sessionService;

	@Resource
	private UiExperienceService uiExperienceService;

	private Converter<PaypalPaymentInfoModel, CCPaymentInfoData> paypalPaymentInfoConverter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.commercefacades.order.impl.DefaultCheckoutFacade#authorizePayment(java.lang.String)
	 */
	@Override
	public boolean authorizePayment(final String securityCode)
	{
		final CartModel cartModel = getCart();
		if (checkIfCurrentUserIsTheCartUser())
		{
			final PaymentInfoModel infoModel = cartModel.getPaymentInfo();
			if (infoModel instanceof PaypalPaymentInfoModel)
			{
				final PaypalPaymentInfoModel paypalModel = (PaypalPaymentInfoModel) cartModel.getPaymentInfo();
				if (paypalModel != null && StringUtils.isNotBlank(paypalModel.getToken()))
				{
					LOG.info("Authorize payment with PayPal");
					final DoExpressCheckoutPaymentRequestData doExprCheckPaymentReqData = new DoExpressCheckoutPaymentRequestData();
					doExprCheckPaymentReqData.setToken(paypalModel.getToken());
					doExprCheckPaymentReqData.setPayerId(paypalModel.getPayerId());
					doExprCheckPaymentReqData.setPaymentAction(getConfigurationService().getConfiguration().getString(
							PaypalConstants.PAYMENT_ACTION));
					doExprCheckPaymentReqData.setSessionCart(checkoutFacade.getCheckoutCart());

					// call web service doExpressCheckoutPayment method
					final DoExpressCheckoutPaymentResultData doExprCheckPaymentResData = payPalPaymentFacade
							.doExpressCheckoutPayment(doExprCheckPaymentReqData);

					// update cart with payment info
					if (PaypalConstants.STATUS_SUCCESS.equals(doExprCheckPaymentResData.getAck()))
					{
						final List<PaymentInfoData> paymentInfoList = doExprCheckPaymentResData.getPaymentInfoList();

						final String paymentAction = StringUtils.capitalize(paypalModel.getPaymentAction().getCode().toLowerCase());

						final List<PaymentTransactionModel> paymentTransactions = new ArrayList<>();
						// TransactionEntry
						for (final PaymentInfoData paymentInfoData : paymentInfoList)
						{
							//transaction id to use in authorization/capture operations
							String transactionId = paymentInfoData.getTransactionId();
							final List<PaymentTransactionEntryModel> paymentTransactionEntries = new ArrayList<>();

							final String currencyIsoCode = paymentInfoData.getCurrencyIsoCode();
							final double amount = paymentInfoData.getGrossAmount();
							final Date paymentDate = paymentInfoData.getPaymentDate().getTime();

							// order payment action is set explicitly or implicitly in case
							// of multiple shipping (e.g. delivery and pickup)
							if (PaypalConstants.ORDER_PAYMENT_ACTION_NAME.equals(paymentAction))
							{
								PaymentTransactionEntryModel orderTransactionEntry = null;
								if (PaymentStatus.PENDING == paymentInfoData.getPaymentStatus()
										&& PendingReason.AUTHORIZATION != paymentInfoData.getPendingReason())
								{
									orderTransactionEntry = createTransactionEntry(PaymentTransactionType.ORDER,
											PaymentStatus.PENDING.name(), paymentInfoData.getPendingReason().name(), transactionId,
											cartModel, currencyIsoCode, amount, paymentDate);
								}
								else
								{
									orderTransactionEntry = createTransactionEntry(PaymentTransactionType.ORDER,
											TransactionStatus.ACCEPTED.name(), TransactionStatusDetails.SUCCESFULL.name(), transactionId,
											cartModel, currencyIsoCode, amount, paymentDate);
								}
								paymentTransactionEntries.add(orderTransactionEntry);

								// do implicit authorization method call
								final DoAuthorizationRequestData doAuthReqData = new DoAuthorizationRequestData();
								doAuthReqData.setTransactionId(transactionId);
								doAuthReqData.setAmount(amount);
								doAuthReqData.setCurrencyIsoCode(currencyIsoCode);

								final DoAuthorizationResultData doAuthResData = payPalPaymentFacade.doAuthorization(doAuthReqData);

								final Date authDate = doAuthResData.getDateTime().getTime();
								final String authCurrencyIsoCode = doAuthResData.getCurrencyIsoCode();
								final double authAmount = doAuthResData.getAmount();
								final String authorizationId = doAuthResData.getTransactionId();

								// result must have pending payment status and pending reason authorization
								// to assume authorization operation successful
								if (PaypalConstants.STATUS_SUCCESS.equals(doAuthResData.getAck())
										&& PaymentStatus.PENDING == doAuthResData.getPaymentStatus()
										&& PendingReason.AUTHORIZATION == doAuthResData.getPendingReason())
								{
									final PaymentTransactionEntryModel authTransactionEntry = createTransactionEntry(
											PaymentTransactionType.AUTHORIZATION, TransactionStatus.ACCEPTED.name(),
											TransactionStatusDetails.SUCCESFULL.name(), authorizationId, cartModel, authCurrencyIsoCode,
											authAmount, authDate);
									paymentTransactionEntries.add(authTransactionEntry);

									// in capture operation authorization id returned by doAuthorize method call
									// will be used
									transactionId = authorizationId;
								}
								else if (PaypalConstants.STATUS_SUCCESS.equals(doAuthResData.getAck())
										&& PaymentStatus.PENDING == doAuthResData.getPaymentStatus()
										&& PendingReason.AUTHORIZATION != doAuthResData.getPendingReason())
								{
									final PaymentTransactionEntryModel authTransactionEntry = createTransactionEntry(
											PaymentTransactionType.AUTHORIZATION, PaymentStatus.PENDING.name(), doAuthResData
													.getPendingReason().name(), authorizationId, cartModel, authCurrencyIsoCode, authAmount,
											authDate);
									paymentTransactionEntries.add(authTransactionEntry);
									// in capture operation authorization id returned by doAuthorize method call
									// will be used
									transactionId = authorizationId;
								}
								else
								{
									LOG.error("DoAuthorization failed");
									handleErrors(doAuthResData, transactionId);
									final PaymentTransactionEntryModel failedAuthTransactionEntry = createTransactionEntry(
											PaymentTransactionType.AUTHORIZATION, TransactionStatus.ERROR.name(),
											TransactionStatusDetails.UNKNOWN_CODE.name(), authorizationId, cartModel, currencyIsoCode,
											amount, authDate);
									paymentTransactionEntries.add(failedAuthTransactionEntry);
									return false;
								}
							}
							else if (getConfigurationService().getConfiguration().getString(PaypalConstants.PAYMENT_ACTION)
									.equals(PaypalConstants.AUTHORIZATION_PAYMENT_ACTION_NAME))
							{
								// in case of AUTHORIZATION payment action, success status of getExpressCheckoutPayment
								// operation mean, that authorization of amount also passed successfully
								PaymentTransactionEntryModel authTransactionEntry = null;
								if (PaymentStatus.PENDING == paymentInfoData.getPaymentStatus()
										&& PendingReason.AUTHORIZATION != paymentInfoData.getPendingReason())
								{
									authTransactionEntry = createTransactionEntry(PaymentTransactionType.AUTHORIZATION,
											PaymentStatus.PENDING.name(), paymentInfoData.getPendingReason().name(), transactionId,
											cartModel, currencyIsoCode, amount, paymentDate);
								}
								else
								{
									authTransactionEntry = createTransactionEntry(PaymentTransactionType.AUTHORIZATION,
											TransactionStatus.ACCEPTED.name(), TransactionStatusDetails.SUCCESFULL.name(), transactionId,
											cartModel, currencyIsoCode, amount, paymentDate);
								}
								paymentTransactionEntries.add(authTransactionEntry);
							} else if(PaypalConstants.SALE_PAYMENT_ACTION_NAME.equalsIgnoreCase(paymentAction)){
								PaymentTransactionEntryModel authTransactionEntry = null;
								if (PaymentStatus.PENDING == paymentInfoData.getPaymentStatus()
										&& PendingReason.AUTHORIZATION != paymentInfoData.getPendingReason()) {
									authTransactionEntry = createTransactionEntry(PaymentTransactionType.SALE,
											PaymentStatus.PENDING.name(), paymentInfoData.getPendingReason().name(), transactionId,
											cartModel, currencyIsoCode, amount, paymentDate);
								} else {
									authTransactionEntry = createTransactionEntry(PaymentTransactionType.SALE,
											TransactionStatus.ACCEPTED.name(), TransactionStatusDetails.SUCCESFULL.name(), transactionId,
											cartModel, currencyIsoCode, amount, paymentDate);

								}
								paymentTransactionEntries.add(authTransactionEntry);
							}
							// Transaction
							final PaymentTransactionModel paymentTransaction = getModelService().create(PaymentTransactionModel.class);
							paymentTransaction.setEntries(paymentTransactionEntries);
							paymentTransaction.setRequestId(transactionId);
							paymentTransaction.setRequestToken(((PaypalPaymentInfoModel) cartModel.getPaymentInfo()).getToken());
							paymentTransaction.setPaymentProvider(PaypalConstants.PAYMENT_PROVIDER_NAME);

							paymentTransactions.add(paymentTransaction);
						}

						cartModel.setPaymentTransactions(paymentTransactions);

						PaymentModeModel paymentMode = new PaymentModeModel();
						paymentMode.setCode("paypal");
						paymentMode = flexibleSearchService.getModelByExample(paymentMode);
						cartModel.setPaymentMode(paymentMode);
						final PaypalPaymentInfoModel paymentInfo = (PaypalPaymentInfoModel) cartModel.getPaymentInfo();
						final String billingAgreementID = doExprCheckPaymentResData.getBillingAgreementID();
						paymentInfo.setBillingAgreementID(billingAgreementID);

						getModelService().saveAll(cartModel);

						try
						{
							calculationService.calculate(cartModel);
						}
						catch (final CalculationException e)
						{
							LOG.error("Error during order recalculation: ", e);
						}

						return PaypalConstants.STATUS_SUCCESS.equals(doExprCheckPaymentResData.getAck());
					}
					else
					{
						LOG.error("DoExpressCheckoutFailed failed");
						handleErrors(doExprCheckPaymentResData, paypalModel.getToken());
						return false;
					}
				}
			}
		}

		return super.authorizePayment(securityCode);
	}

	private CurrencyModel getCurrencyForIsoCode(final String currencyIsoCode)
	{
		CurrencyModel currencyModel = new CurrencyModel();
		currencyModel.setIsocode(currencyIsoCode);
		currencyModel = flexibleSearchService.getModelByExample(currencyModel);
		return currencyModel;
	}

	private PaymentTransactionEntryModel createTransactionEntry(final PaymentTransactionType type, final String status,
			final String statusDetails, final String requestId, final CartModel cartModel, final String currencyIsoCode,
			final double amount, final Date timeStamp)
	{
		final PaymentTransactionEntryModel paymentTransactionEntry = getModelService().create(PaymentTransactionEntryModel.class);

		paymentTransactionEntry.setRequestId(requestId);
		paymentTransactionEntry.setType(type);
		paymentTransactionEntry.setTransactionStatus(status);
		paymentTransactionEntry.setTransactionStatusDetails(statusDetails);

		final String code = PaypalConstants.PAYMENT_PROVIDER_NAME + "_cart_" + cartModel.getCode() + "_stamp_"
				+ System.currentTimeMillis();
		paymentTransactionEntry.setCode(code);

		final CurrencyModel currency = getCurrencyForIsoCode(currencyIsoCode);
		paymentTransactionEntry.setCurrency(currency);

		final BigDecimal transactionAmount = BigDecimal.valueOf(amount);
		paymentTransactionEntry.setAmount(transactionAmount);

		paymentTransactionEntry.setTime(timeStamp);

		return paymentTransactionEntry;
	}

	private void handleErrors(final AbstractResultData responseData, final String transactionId)
	{
		final List<ResultErrorData> errorDataList = responseData.getErrors();
		if (CollectionUtils.isNotEmpty(errorDataList))
		{
			final Iterator<ResultErrorData> errorIterator = errorDataList.iterator();
			while (errorIterator.hasNext())
			{
				final ResultErrorData errorData = errorIterator.next();

				final String errorCode = errorData.getErrorCode();
				LOG.error("Error code: " + errorCode);
				LOG.error(errorData.getShortMessage() + " " + errorData.getLongMessage());

				String redirectUrl = StringUtils.EMPTY;
				if (PaypalConstants.ERROR_CODE_10486.equals(errorCode))
				{
					final UiExperienceLevel uiExperienceLevel = uiExperienceService.getUiExperienceLevel();

					if (responseData instanceof DoExpressCheckoutPaymentResultData)
					{
						if (uiExperienceLevel == UiExperienceLevel.DESKTOP)
						{
							redirectUrl = getConfigurationService().getConfiguration().getString(
									PaypalConstants.SETT_REDIRECT_URL_DESKTOP)
									+ transactionId;
						}
						else
						{
							redirectUrl = getConfigurationService().getConfiguration().getString(
									PaypalConstants.SETT_REDIRECT_URL_MOBILE)
									+ transactionId;
						}
					}
					else if (responseData instanceof DoAuthorizationResultData)
					{
						if (uiExperienceLevel == UiExperienceLevel.DESKTOP)
						{
							redirectUrl = getConfigurationService().getConfiguration().getString(
									PaypalConstants.SETT_REDIRECT_REPEAT_ORDER_URL_DESKTOP)
									+ transactionId;
						}
						else
						{
							redirectUrl = getConfigurationService().getConfiguration().getString(
									PaypalConstants.SETT_REDIRECT_REPEAT_ORDER_URL_MOBILE)
									+ transactionId;
						}
					}

					if (StringUtils.isNotEmpty(redirectUrl))
					{
						getSessionService().setAttribute(PaypaladdonConstants.PAY_PAL_REPEAT_REDIRECT_URL, redirectUrl);
					}
				}
			}
		}
	}

	@Override
	protected CCPaymentInfoData getPaymentDetails()
	{
		final CartModel cart = getCart();
		if (cart != null)
		{
			final PaymentInfoModel paymentInfo = cart.getPaymentInfo();
			if (paymentInfo instanceof PaypalPaymentInfoModel)
			{
				return paypalPaymentInfoConverter.convert((PaypalPaymentInfoModel) paymentInfo);
			}
			else
			{
				return super.getPaymentDetails();
			}
		}

		return null;
	}

	public void setPayPalPaymentFacade(final PayPalPaymentFacade payPalPaymentFacade)
	{
		this.payPalPaymentFacade = payPalPaymentFacade;
	}

	/**
	 * @return the paypalPaymentInfoConverter
	 */
	public Converter<PaypalPaymentInfoModel, CCPaymentInfoData> getPaypalPaymentInfoConverter()
	{
		return paypalPaymentInfoConverter;
	}

	/**
	 * @param paypalPaymentInfoConverter
	 *           the paypalPaymentInfoConverter to set
	 */
	public void setPaypalPaymentInfoConverter(final Converter<PaypalPaymentInfoModel, CCPaymentInfoData> paypalPaymentInfoConverter)
	{
		this.paypalPaymentInfoConverter = paypalPaymentInfoConverter;
	}

	public void setPayPalPaymentService(final PaypalPaymentService payPalPaymentService)
	{
		payPalPaymentFacade.setPaypalPaymentService(payPalPaymentService);
	}

	public ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}

	public SessionService getSessionService()
	{
		return sessionService;
	}

	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	@Override
	public UiExperienceService getUiExperienceService()
	{
		return uiExperienceService;
	}

	@Override
	public void setUiExperienceService(final UiExperienceService uiExperienceService)
	{
		this.uiExperienceService = uiExperienceService;
	}
}
