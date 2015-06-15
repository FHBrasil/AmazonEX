/**
 *
 */
package com.paypal.hybris.jobs;

import br.hering.core.jalo.order.payment.PayPalPaymentInfo;
import br.hering.core.model.order.payment.PayPalPaymentInfoModel;
import com.paypal.hybris.service.OrderCompleteProcessService;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ebay.api.AckCodeType;
import com.ebay.api.BasicAmountType;
import com.ebay.api.CompleteCodeType;
import com.ebay.api.CurrencyCodeType;
import com.ebay.api.DoCaptureReq;
import com.ebay.api.DoCaptureRequestType;
import com.ebay.api.DoCaptureResponseType;
import com.ebay.utils.PaypalStringUtils;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.service.PaypalPaymentService;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
public class PayPalCapturingJob extends AbstractJobPerformable<CronJobModel>
{
	private static final Logger LOG = Logger.getLogger(PayPalCapturingJob.class);

	private ConfigurationService configurationService;
	private PaypalPaymentService paypalPaymentService;
	private ModelService modelService;
	private CommonI18NService commonI18NService;
	private OrderCompleteProcessService orderCompleteProcessService;

	/**
	 * @return the paypalPaymentService
	 */
	public PaypalPaymentService getPaypalPaymentService()
	{
		return paypalPaymentService;
	}

	/**
	 * @param paypalPaymentService
	 *           the paypalPaymentService to set
	 */
	public void setPaypalPaymentService(final PaypalPaymentService paypalPaymentService)
	{
		this.paypalPaymentService = paypalPaymentService;
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
	 *           the modelService to set
	 */
	@Override
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
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
	 *           the commonI18NService to set
	 */
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	/**
	 * @return the configurationService
	 */
	public ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	/**
	 * @param configurationService
	 *           the configurationService to set
	 */
	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.cronjob.model.CronJobModel
	 * )
	 */
	@Override
	public PerformResult perform(final CronJobModel cronJob) {

		LOG.info("Perform PayPal capturing process..");

		final StringBuilder query = new StringBuilder();
		query.append("select {pk} from {order} where {status} in ({{select {pk} from {orderstatus} where {code} like 'PAYMENT_NOT_CAPTURED'}})");
		//query.append("select {pk} from {order} where {status} in ({{select {pk} from {orderstatus} where {code} like 'FRAUD_CHECKED'}})");
		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query.toString());
		final SearchResult result = flexibleSearchService.search(searchQuery);

		final List res = result.getResult();
		for (final Iterator it = res.iterator(); it.hasNext(); ) {

			final OrderModel order = (OrderModel) it.next();
			PaymentInfoModel paymentInfoModel = order.getPaymentInfo();
			if (paymentInfoModel != null && paymentInfoModel instanceof PayPalPaymentInfoModel &&
					!Boolean.TRUE.equals(((PayPalPaymentInfoModel) paymentInfoModel).getUseReferenceTransaction())) {
				final DoCaptureRequestType request = prepareCaptureRequest(order);

				final DoCaptureResponseType response = getPaypalPaymentService().doCapture(request);

				// create payment entry
				if (AckCodeType.SUCCESS.equals(response.getAck())) {
					final String currencyID = order.getCurrency().getIsocode().toString();
					final PaymentTransactionEntryModel transactionEntry = createTransactionEntry(
							order.getPaymentTransactions().get(0), PaymentTransactionType.CAPTURE, TransactionStatus.ACCEPTED,
							TransactionStatusDetails.SUCCESFULL, response.getDoCaptureResponseDetails().getPaymentInfo().getTransactionID(),
							currencyID, order.getTotalPrice().toString(), response.getTimestamp().toGregorianCalendar().getTime());

					for (final PaymentTransactionModel paymentTransactionModel : order.getPaymentTransactions()) {
						if (PaypalConstants.PAYMENT_PROVIDER_NAME.equals(paymentTransactionModel.getPaymentProvider())) {
							final List<PaymentTransactionEntryModel> entries = new ArrayList<>(paymentTransactionModel.getEntries());
							entries.add(transactionEntry);
							paymentTransactionModel.setEntries(entries);
						}
					}

					// update order status
					order.setStatus(OrderStatus.PAYMENT_CAPTURED);

					getModelService().saveAll(transactionEntry, order);
					orderCompleteProcessService.startOrderCompletionProcess(order);

				}
			}

		}


		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	/**
	 * @param order
	 * @return
	 */
	private DoCaptureRequestType prepareCaptureRequest(final OrderModel order)
	{
		final DoCaptureRequestType request = new DoCaptureRequestType();
		request.setVersion(PaypalConstants.SOAP_API_VERSION);

		final CurrencyCodeType currencyCode = CurrencyCodeType.fromValue(order.getCurrency().getIsocode());

		for (final PaymentTransactionModel paymentTransactionModel : order.getPaymentTransactions())
		{
			for (final PaymentTransactionEntryModel paymentTransactionEntryModel : paymentTransactionModel.getEntries())
			{
				if (PaymentTransactionType.AUTHORIZATION.equals(paymentTransactionEntryModel.getType()))
				{
					request.setAuthorizationID(paymentTransactionEntryModel.getRequestId());
				}
			}
		}

		if (StringUtils.isBlank(currencyCode.toString()))
		{
			throw new ConversionException("Missing required currency iso code value");
		}

		final BasicAmountType amount = createBasicAmountType(order.getTotalPrice().doubleValue(), currencyCode);
		request.setAmount(amount);

		// capture all amount at once
		request.setCompleteType(CompleteCodeType.COMPLETE);

		return request;
	}

	private PaymentTransactionEntryModel createTransactionEntry(final PaymentTransactionModel transaction,
			final PaymentTransactionType type, final TransactionStatus status, final TransactionStatusDetails statusDetails,
			final String requestId, final String currencyIsoCode, final String amount, final Date timeStamp)
	{
		final PaymentTransactionEntryModel paymentTransactionEntry = getModelService().create(PaymentTransactionEntryModel.class);

		paymentTransactionEntry.setPaymentTransaction(transaction);
		paymentTransactionEntry.setRequestId(requestId);
		paymentTransactionEntry.setType(type);
		paymentTransactionEntry.setTransactionStatus(status.name());
		paymentTransactionEntry.setTransactionStatusDetails(statusDetails.name());

		final String code = PaypalConstants.PAYMENT_PROVIDER_NAME + "_reauthorization_stamp_" + System.currentTimeMillis();
		paymentTransactionEntry.setCode(code);

		final CurrencyModel currency = getCommonI18NService().getCurrency(currencyIsoCode);
		paymentTransactionEntry.setCurrency(currency);

		final BigDecimal transactionAmount = new BigDecimal(amount.replace(",", ""));
		paymentTransactionEntry.setAmount(transactionAmount);

		paymentTransactionEntry.setTime(timeStamp);

		return paymentTransactionEntry;
	}

	protected BasicAmountType createBasicAmountType(final double amount, final CurrencyCodeType currencyCode)
	{
		final BasicAmountType basicAmount = new BasicAmountType();
		basicAmount.setValue(PaypalStringUtils.formatNumber(amount));
		basicAmount.setCurrencyID(currencyCode);
		return basicAmount;
	}

	public OrderCompleteProcessService getOrderCompleteProcessService() {
		return orderCompleteProcessService;
	}

	public void setOrderCompleteProcessService(OrderCompleteProcessService orderCompleteProcessService) {
		this.orderCompleteProcessService = orderCompleteProcessService;
	}
}
