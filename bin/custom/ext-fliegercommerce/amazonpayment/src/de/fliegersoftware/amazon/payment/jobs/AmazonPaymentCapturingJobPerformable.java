package de.fliegersoftware.amazon.payment.jobs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.amazonaws.mws.model.FeedSubmissionInfo;
import com.amazonaws.mws.model.SubmitFeedResponse;
import com.amazonaws.mws.model.SubmitFeedResult;
import com.amazonservices.mws.offamazonpayments.model.CaptureRequest;
import com.amazonservices.mws.offamazonpayments.model.Price;

import de.fliegersoftware.amazon.core.services.AmazonConfigService;
import de.fliegersoftware.amazon.payment.model.AmazonPaymentCaptureCronJobModel;
import de.fliegersoftware.amazon.payment.services.MWSAmazonFeedsService;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.store.services.BaseStoreService;

public class AmazonPaymentCapturingJobPerformable extends
		AbstractJobPerformable<AmazonPaymentCaptureCronJobModel> {

	private static final Logger LOG = Logger
			.getLogger(AmazonPaymentCapturingJobPerformable.class);

	@Resource
	private MWSAmazonFeedsService mwsAmazonFeedsService;

	@Resource
	private BaseStoreService baseStoreService;

	@Override
	public PerformResult perform(AmazonPaymentCaptureCronJobModel cronjob) {
		LOG.info("Perform amazon capture process");

		sessionService.setAttribute("currentSite", cronjob.getSite());

		final StringBuilder query = new StringBuilder();
		query.append("select {pk} from {order} where {status} in ") //
				.append("({{") //
				.append("select {pk} from {orderstatus} where {code} in ('PAYMENT_AMOUNT_RESERVED')") //
				.append("}})") //
				.append("and {paymentinfo} in ({{") //
				.append("select {pk} from {AmazonPaymentInfo}") //
				.append("}})");
		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query.toString());
		final SearchResult<OrderModel> result = flexibleSearchService.search(searchQuery);

		LOG.info("Found " + result.getCount() + " order(s) with status: PAYMENT_AMOUNT_RESERVED");

		List<CaptureRequest> requestList = new ArrayList<>();
		for (OrderModel order : result.getResult()) {

			for (final PaymentTransactionModel txn : order.getPaymentTransactions()) {
//				final PaymentTransactionEntryModel txnEntry = amazonPaymentService.capture(txn);
//
//				if(txnEntry != null
//					&& TransactionStatus.ACCEPTED.name().equals(txnEntry.getTransactionStatus())) {
//					if (LOG.isDebugEnabled()) {
//						LOG.debug("The payment transaction has been captured. Order: "
//								+ order.getCode() + ". Txn: " + txn.getCode());
//					}
//					order.setStatus(OrderStatus.PAYMENT_CAPTURED);
//					modelService.save(order);
//				} else {
//					LOG.error("The payment transaction capture has failed. Order: "
//							+ order.getCode() + ". Txn: " + txn.getCode());
//					order.setStatus(OrderStatus.PAYMENT_NOT_CAPTURED);
//					modelService.save(order);
//				}
				for (PaymentTransactionEntryModel auth : txn.getEntries()) {
					if (PaymentTransactionType.AUTHORIZATION.equals(auth.getType())) {
						CaptureRequest captureRequest = new CaptureRequest();
						captureRequest.setAmazonAuthorizationId(auth.getRequestToken());
						captureRequest.setCaptureReferenceId(String.valueOf(System.currentTimeMillis()));

						Price authorizationAmount = new Price();
						authorizationAmount.setAmount(String.valueOf(auth.getAmount()));
						authorizationAmount.setCurrencyCode(auth.getCurrency().getIsocode());
						captureRequest.setCaptureAmount(authorizationAmount);

						requestList.add(captureRequest);
						break;
					}
				}
			}
		}
		if(requestList.size() > 0) {
			LOG.info("Submiting requests for capturing");
			SubmitFeedResponse response = mwsAmazonFeedsService.submitFeed(cronjob.getCode(), requestList);
			dumpResponseToLog(response);
		}

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	private void dumpResponseToLog(SubmitFeedResponse response) {
		if(response == null //
				|| !response.isSetSubmitFeedResult()
				|| !response.getSubmitFeedResult().isSetFeedSubmissionInfo()) {
			LOG.info("Feed response is empty");
			return;
		}

		FeedSubmissionInfo info = response.getSubmitFeedResult().getFeedSubmissionInfo();
		if(info.isSetFeedSubmissionId())
			LOG.info("Feed Submission Id: " + info.getFeedSubmissionId());
		if(info.isSetFeedType())
			LOG.info("Feed Type: " + info.getFeedType());
		if(info.isSetSubmittedDate())
			LOG.info("Feed Submitted Date: " + info.getSubmittedDate());
		if(info.isSetFeedProcessingStatus())
			LOG.info("Feed Processing Status: " + info.getFeedProcessingStatus());
		if(info.isSetStartedProcessingDate())
			LOG.info("Feed Started Processing Date: " + info.getStartedProcessingDate().toGregorianCalendar());
		if(info.isSetCompletedProcessingDate())
			LOG.info("Feed Completed Processing Date: " + info.getCompletedProcessingDate().toGregorianCalendar());
	}
}