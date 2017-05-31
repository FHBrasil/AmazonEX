package de.fliegersoftware.amazon.payment.jobs;

import static de.fliegersoftware.amazon.payment.util.PaymentTransactionEntryUtil.setPaymentTransactionEntryStatus;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;  import org.slf4j.LoggerFactory;

import com.amazonservices.mws.offamazonpayments.model.AuthorizationDetails;
import com.amazonservices.mws.offamazonpayments.model.CaptureDetails;
import com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsResult;
import com.amazonservices.mws.offamazonpayments.model.GetCaptureDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetCaptureDetailsResult;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsResult;
import com.amazonservices.mws.offamazonpayments.model.GetRefundDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetRefundDetailsResult;
import com.amazonservices.mws.offamazonpayments.model.OrderReferenceDetails;
import com.amazonservices.mws.offamazonpayments.model.OrderTotal;
import com.amazonservices.mws.offamazonpayments.model.RefundDetails;

import de.fliegersoftware.amazon.core.model.AmazonPaymentPaymentInfoModel;
import de.fliegersoftware.amazon.core.model.AmazonRefundModel;
import de.fliegersoftware.amazon.core.services.AmazonEmailService;
import de.fliegersoftware.amazon.payment.dto.AmazonTransactionStatus;
import de.fliegersoftware.amazon.payment.model.AmazonBaseCronJobModel;
import de.fliegersoftware.amazon.payment.services.MWSAmazonPaymentService;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

public class AmazonPollingJobPerformable extends AbstractJobPerformable<AmazonBaseCronJobModel> {

	private static final Logger LOG = LoggerFactory.getLogger(AmazonPollingJobPerformable.class);
	private static final long REQUEST_PERIOD = 2000; // in milliseconds

	@Resource
	private MWSAmazonPaymentService mwsAmazonPaymentService;
	
	@Resource 
	private AmazonEmailService amazonEmailService;

//	@Resource
//	private AmazonPaymentService amazonPaymentService;

	@Resource
	private CommonI18NService commonI18NService;

	@Override
	public PerformResult perform(AmazonBaseCronJobModel cronjob) {
		LOG.info("Perform amazon polling process");

		sessionService.setAttribute("currentSite", cronjob.getSite());

		final StringBuilder query = new StringBuilder();
		query.append("select {info.pk} from {AmazonPaymentPaymentInfo as info ") //
			.append("join Order as o on {o.paymentInfo} = {info.pk} ") //
			.append("} where {o.store} = ?store ") //
			.append("and  ({info.AmazonOrderStatus} is null ") //
			.append("  or  {info.AmazonOrderStatus} <> 'Cancelled' ") //
			.append("  or ({info.AmazonOrderStatus} = 'Closed' ") //
			.append("    and  ({info.AmazonAuthorizationStatus} is null ") //
			.append("    or    {info.AmazonAuthorizationStatus} = 'Open'))) ")
			;

		Map<String, Object> params = new HashMap<>();
		params.put("store", cronjob.getSite().getStores().get(0));
		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query.toString(), params);
		final SearchResult<AmazonPaymentPaymentInfoModel> result = flexibleSearchService.search(searchQuery);

		long lastRequestTime = 0;
		for (AmazonPaymentPaymentInfoModel info : result.getResult()) {
			ItemModel owner = info.getOwner();
			if(owner instanceof OrderModel) {
				OrderModel order = (OrderModel)owner;
				if(StringUtils.isEmpty(info.getAmazonOrderStatus())
					|| AmazonTransactionStatus.Draft.name().equals(info.getAmazonOrderStatus())
					|| AmazonTransactionStatus.Open.name().equals(info.getAmazonOrderStatus())) {
					lastRequestTime = updateOrder(lastRequestTime, order, info);
				}
				boolean hasAuthorization = false;
				boolean hasCapture = false;
				int refundsCount = 0;
				for(PaymentTransactionModel transaction : order.getPaymentTransactions()) {
					for(PaymentTransactionEntryModel entry : transaction.getEntries()) {
						if(PaymentTransactionType.AUTHORIZATION.equals(entry.getType())) {
							hasAuthorization = true;
							if(entry.getTransactionStatusDetails() == null
								|| AmazonTransactionStatus.Pending.name().equals(entry.getTransactionStatusDetails())
								|| AmazonTransactionStatus.Open.name().equals(entry.getTransactionStatusDetails())
							) {
								lastRequestTime = updateAuthorization(lastRequestTime, order, info, transaction, entry);
							}
							continue;
						}
						if(PaymentTransactionType.CAPTURE.equals(entry.getType())) {
							hasCapture = true;
							if(entry.getTransactionStatusDetails() == null
								|| AmazonTransactionStatus.Pending.name().equals(entry.getTransactionStatusDetails())
							) {
								lastRequestTime = updateCapture(lastRequestTime, order, info, transaction, entry);
							}
							continue;
						}
						if(PaymentTransactionType.REFUND.equals(entry.getType())) {
							refundsCount++;
							if(entry.getTransactionStatusDetails() == null
								|| AmazonTransactionStatus.Pending.name().equals(entry.getTransactionStatusDetails())
							) {
								lastRequestTime = updateRefund(lastRequestTime, order, info, transaction, entry);
							}
							continue;
						}
					}
				}
				if(!hasAuthorization && !StringUtils.isEmpty(info.getAmazonLastAuthorizationId())) {
					LOG.info("Entry for authorization not found. Order: " + info.getAmazonOrderReferenceId());
				}
				if(!hasCapture && !StringUtils.isEmpty(info.getAmazonCaptureId())) {
					LOG.info("Entry for capture not found. Order: " + info.getAmazonOrderReferenceId());
				}
				int refundCountExpected = CollectionUtils.isEmpty(info.getRefund()) ? 0 : info.getRefund().size();
				if(refundsCount != refundCountExpected) {
					LOG.info("Refund entry count doesn't match. Found " + refundsCount + ", expected " + refundCountExpected + ". Order: " + info.getAmazonOrderReferenceId());
				}
			}
		}

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	private long updateOrder(long lastRequestTime, OrderModel order, AmazonPaymentPaymentInfoModel paymentInfo) {
		cooldown(lastRequestTime);
		GetOrderReferenceDetailsRequest request = new GetOrderReferenceDetailsRequest();
		request.setAmazonOrderReferenceId(paymentInfo.getAmazonOrderReferenceId());
		GetOrderReferenceDetailsResult result = mwsAmazonPaymentService.getOrderReferenceDetails(request);
		lastRequestTime = System.currentTimeMillis();
		if(result == null)
			return lastRequestTime;

		OrderReferenceDetails details = result.getOrderReferenceDetails();
		paymentInfo.setAmazonOrderReferenceId(details.getAmazonOrderReferenceId());
		paymentInfo.setAmazonOrderStatus(details.getOrderReferenceStatus().getState());
		paymentInfo.setAmazonOrderReasonCode(details.getOrderReferenceStatus().getReasonCode());
		paymentInfo.setAmazonOrderAmount(getDoubleValue(details.getOrderTotal()));
		paymentInfo.setCurrencyRefundRequestAmount(details.getOrderTotal().getCurrencyCode());

		getModelService().save(paymentInfo);

		return lastRequestTime;
	}

	private long updateAuthorization(long lastRequestTime, OrderModel order, AmazonPaymentPaymentInfoModel paymentInfo, PaymentTransactionModel transaction, PaymentTransactionEntryModel entry) {
		cooldown(lastRequestTime);
		GetAuthorizationDetailsRequest request = new GetAuthorizationDetailsRequest();
		request.setAmazonAuthorizationId(entry.getRequestId());
		GetAuthorizationDetailsResult result = mwsAmazonPaymentService.getAuthorizationDetails(request);
		lastRequestTime = System.currentTimeMillis();
		if(result == null)
			return lastRequestTime;

		AuthorizationDetails details = result.getAuthorizationDetails();
		if (details.getAuthorizationAmount() != null) {
			entry.setAmount(BigDecimal.valueOf(Double.parseDouble(details.getAuthorizationAmount().getAmount())));
			entry.setCurrency(getCommonI18NService().getCurrency(details.getAuthorizationAmount().getCurrencyCode()));
		}
		entry.setType(PaymentTransactionType.AUTHORIZATION);
		entry.setTime((details.getAuthorizationStatus().getLastUpdateTimestamp() == null) ? new Date()
				: details.getAuthorizationStatus().getLastUpdateTimestamp().toGregorianCalendar().getTime());
		entry.setRequestId(details.getAuthorizationReferenceId());
		entry.setRequestToken(details.getAmazonAuthorizationId());

		setPaymentTransactionEntryStatus(entry, AmazonTransactionStatus.valueOf(details.getAuthorizationStatus().getState()), details.getAuthorizationStatus().getReasonCode());

		getModelService().save(entry);

		if(details.isCaptureNow()) {
			// TODO: capture now not handled
		}

		paymentInfo.setAmazonLastAuthorizationId(details.getAuthorizationReferenceId());
		paymentInfo.setAmazonAuthorizationStatus(details.getAuthorizationStatus().getState());
		paymentInfo.setAmazonAuthorizationReasonCode(details.getAuthorizationStatus().getReasonCode());
		
		if (details.getAuthorizationStatus().getReasonCode() != null && (details.getAuthorizationStatus().getReasonCode().equals("AmazonRejected") || 
				details.getAuthorizationStatus().getReasonCode().equals("InvalidPaymentMethod"))) {
			final GetOrderReferenceDetailsRequest orderReferenceDetailsRequest = new GetOrderReferenceDetailsRequest();
			orderReferenceDetailsRequest.setAmazonOrderReferenceId(paymentInfo.getAmazonOrderReferenceId());
			orderReferenceDetailsRequest.setAddressConsentToken(null);

			GetOrderReferenceDetailsResult orderReferenceDetailsResult = mwsAmazonPaymentService.getOrderReferenceDetails(orderReferenceDetailsRequest);
			if(orderReferenceDetailsResult != null) {
				OrderReferenceDetails orderReferenceDetails = orderReferenceDetailsResult.getOrderReferenceDetails();
				orderReferenceDetails.getSellerOrderAttributes().getStoreName();
				try {
					amazonEmailService.sendEmailDeclined(orderReferenceDetails.getBuyer().getEmail(), details.getAuthorizationStatus().getReasonCode());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				
			}
		}

		getModelService().save(paymentInfo);

		return lastRequestTime;
	}

	private long updateCapture(long lastRequestTime, OrderModel order, AmazonPaymentPaymentInfoModel paymentInfo, PaymentTransactionModel transaction, PaymentTransactionEntryModel entry) {
		cooldown(lastRequestTime);
		GetCaptureDetailsRequest request = new GetCaptureDetailsRequest();
		request.setAmazonCaptureId(entry.getRequestId());
		GetCaptureDetailsResult result = mwsAmazonPaymentService.getCaptureDetails(request);
		lastRequestTime = System.currentTimeMillis();
		if(result == null)
			return lastRequestTime;

		CaptureDetails details = result.getCaptureDetails();
		entry.setAmount(BigDecimal.valueOf(Double.parseDouble(details.getCaptureAmount().getAmount())));
		if (details.getCaptureAmount() != null) {
			entry.setCurrency(getCommonI18NService().getCurrency(details.getCaptureAmount().getCurrencyCode()));
		}
		entry.setType(PaymentTransactionType.CAPTURE);
		entry.setTime((details.getCaptureStatus().getLastUpdateTimestamp() == null) ? new Date()
				: details.getCaptureStatus().getLastUpdateTimestamp().toGregorianCalendar().getTime());
		entry.setRequestId(details.getCaptureReferenceId());
		entry.setRequestToken(details.getAmazonCaptureId());

		setPaymentTransactionEntryStatus(entry, AmazonTransactionStatus.valueOf(details.getCaptureStatus().getState()), details.getCaptureStatus().getReasonCode());

		getModelService().save(entry);

		paymentInfo.setAmazonCaptureId(details.getAmazonCaptureId());
		paymentInfo.setAmazonCaptureReasonCode(details.getCaptureStatus().getReasonCode());
		paymentInfo.setAmazonCaptureRefundedAmount(Double.parseDouble(details.getRefundedAmount().getAmount()));
		paymentInfo.setAmazonCaptureStatus(details.getCaptureStatus().getState());

		getModelService().save(paymentInfo);

		if(OrderStatus.PAYMENT_AMOUNT_RESERVED.equals(order.getStatus())
				&& AmazonTransactionStatus.Completed.name().equals(details.getCaptureStatus().getState())) {
			// update order status
			order.setStatus(OrderStatus.PAYMENT_CAPTURED);
			order.setPaymentStatus(PaymentStatus.PAID);
			getModelService().save(order);
		}

		return lastRequestTime;
	}

	private long updateRefund(long lastRequestTime, OrderModel order, AmazonPaymentPaymentInfoModel paymentInfo, PaymentTransactionModel transaction, PaymentTransactionEntryModel entry) {
		cooldown(lastRequestTime);
		GetRefundDetailsRequest request = new GetRefundDetailsRequest();
		request.setAmazonRefundId(entry.getRequestId());
		GetRefundDetailsResult result = mwsAmazonPaymentService.getRefundDetails(request);
		lastRequestTime = System.currentTimeMillis();
		if(result == null)
			return lastRequestTime;

		RefundDetails details = result.getRefundDetails();
		entry.setAmount(BigDecimal.valueOf(Double.parseDouble(details.getRefundAmount().getAmount())));
		if (details.getRefundAmount() != null) {
			entry.setCurrency(getCommonI18NService().getCurrency(details.getRefundAmount().getCurrencyCode()));
		}
		entry.setType(PaymentTransactionType.REFUND);
		entry.setTime((details.getRefundStatus().getLastUpdateTimestamp() == null) ? new Date()
				: details.getRefundStatus().getLastUpdateTimestamp().toGregorianCalendar().getTime());
		entry.setRequestId(details.getRefundReferenceId());
		entry.setRequestToken(details.getAmazonRefundId());

		setPaymentTransactionEntryStatus(entry, AmazonTransactionStatus.valueOf(details.getRefundStatus().getState()), details.getRefundStatus().getReasonCode());

		getModelService().save(entry);

		AmazonRefundModel refund = null;
		for(AmazonRefundModel r : paymentInfo.getRefund()) {
			if(r.getRefundReferenceId().equals(details.getRefundReferenceId())) {
				refund = r;
				break;
			}
		}
		if(refund == null) {
			refund = getModelService().create(AmazonRefundModel.class);
			refund.setAmazonPaymentPaymentInfo(paymentInfo);
		}
		refund.setAmazonRefundId(details.getAmazonRefundId());
		refund.setRefundStatus(details.getRefundStatus().getState());
		refund.setRefundAmount(Double.parseDouble(details.getRefundAmount().getAmount()));
		refund.setRefundReferenceId(details.getRefundReferenceId());
		refund.setRefundReasonCode(details.getRefundStatus().getReasonCode());

		getModelService().save(refund);

		return lastRequestTime;
	}

	private Double getDoubleValue(final OrderTotal orderTotal)
	{
		if (NumberUtils.isNumber(orderTotal.getAmount()))
		{
			return Double.valueOf(orderTotal.getAmount());
		}
		return null;
	}

	/**
	 * Prevents throttling by reducing the number of requests per second.
	 * @param lastRequestTime
	 */
	private void cooldown(long lastRequestTime) {
		long now = System.currentTimeMillis();
		if(now - lastRequestTime < REQUEST_PERIOD) {
			try {
				Thread.sleep(REQUEST_PERIOD - (now - lastRequestTime));
			} catch (InterruptedException e) {
				// do nothing
			}
		}
	}

	protected ModelService getModelService() {
		return modelService;
	}

	protected CommonI18NService getCommonI18NService() {
		return commonI18NService;
	}

	public AmazonEmailService getAmazonEmailService() {
		return amazonEmailService;
	}

	public void setAmazonEmailService(AmazonEmailService amazonEmailService) {
		this.amazonEmailService = amazonEmailService;
	}
}
