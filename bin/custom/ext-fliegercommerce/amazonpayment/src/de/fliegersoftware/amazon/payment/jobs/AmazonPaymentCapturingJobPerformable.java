package de.fliegersoftware.amazon.payment.jobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.amazonaws.mws.model.FeedSubmissionInfo;
import com.amazonaws.mws.model.SubmitFeedResponse;
import com.amazonservices.mws.offamazonpayments.model.CaptureRequest;
import com.amazonservices.mws.offamazonpayments.model.Price;

import de.fliegersoftware.amazon.core.enums.CaptureModeEnum;
import de.fliegersoftware.amazon.core.services.AmazonConfigService;
import de.fliegersoftware.amazon.payment.dto.AmazonTransactionStatus;
import de.fliegersoftware.amazon.payment.model.AmazonBaseCronJobModel;
import de.fliegersoftware.amazon.payment.services.MWSAmazonFeedsService;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

public class AmazonPaymentCapturingJobPerformable extends
		AbstractJobPerformable<AmazonBaseCronJobModel> {

	private static final Logger LOG = Logger
			.getLogger(AmazonPaymentCapturingJobPerformable.class);

	@Resource
	private MWSAmazonFeedsService mwsAmazonFeedsService;

	@Resource
	private AmazonConfigService amazonConfigService;

	@Override
	public PerformResult perform(AmazonBaseCronJobModel cronjob) {
		LOG.info("Perform amazon capture process");

		sessionService.setAttribute("currentSite", cronjob.getSite());

		if(!CaptureModeEnum.SHIPMENT.equals(amazonConfigService.getCaptureMode())) {
			LOG.warn("This job can only run if CaptureMode is SHIPMENT");
			return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
		}
		String statusId = amazonConfigService.getOrderStatusOnSuccessfullShipping();
		OrderStatus status = null;
		try {
			status = OrderStatus.valueOf(statusId);
		} catch (IllegalArgumentException e) {
			LOG.error("Invalid order status: " + statusId);
			return new PerformResult(CronJobResult.ERROR, CronJobStatus.FINISHED);
		}

		final StringBuilder query = new StringBuilder();
		query.append("select {o.pk} from {Order as o ") //
				.append("join AmazonPaymentInfo as info on {info.pk} = {o.paymentInfo} ") //
				.append("} where {o.store} = ?store ") //
				.append("and {o.status} = ?orderstatus ") //
				;
		Map<String, Object> params = new HashMap<>();
		params.put("orderstatus", status);
		params.put("store", cronjob.getSite().getStores().get(0));
		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query.toString(), params);
		final SearchResult<OrderModel> result = flexibleSearchService.search(searchQuery);

		LOG.info("Found " + result.getCount() + " order(s) with status: " + statusId);

		List<CaptureRequest> requestList = new ArrayList<>();
		for (OrderModel order : result.getResult()) {

			for (final PaymentTransactionModel txn : order.getPaymentTransactions()) {
				for (PaymentTransactionEntryModel auth : txn.getEntries()) {
					if (PaymentTransactionType.AUTHORIZATION.equals(auth.getType())
						&& AmazonTransactionStatus.Open.name().equals(auth.getTransactionStatusDetails())) {

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