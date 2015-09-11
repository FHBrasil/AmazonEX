package de.fliegersoftware.amazon.payment.services;

import java.util.List;

import com.amazonaws.mws.model.SubmitFeedRequest;
import com.amazonaws.mws.model.SubmitFeedResponse;
import com.amazonservices.mws.offamazonpayments.model.CaptureRequest;

public interface MWSAmazonFeedsService {

	SubmitFeedResponse submitFeed(String filename, List<CaptureRequest> requestList);

	SubmitFeedResponse submitFeed(SubmitFeedRequest request);

}
