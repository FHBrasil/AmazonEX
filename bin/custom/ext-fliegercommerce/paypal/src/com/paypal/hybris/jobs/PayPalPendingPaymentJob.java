package com.paypal.hybris.jobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.ebay.api.AckCodeType;
import com.paypal.hybris.data.GetTransactionDetailsResultData;
import com.paypal.hybris.data.PaymentStatus;
import com.paypal.hybris.data.PendingReason;
import com.paypal.hybris.transaction.TransactionDetailsService;


/**
 * @author Dzmitry_Rasolka (EPAM Systems)
 */
public class PayPalPendingPaymentJob extends AbstractJobPerformable<CronJobModel>
{

	private static final Logger LOG = Logger.getLogger(PayPalPendingPaymentJob.class);

	private TransactionDetailsService transactionDetailsService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.cronjob.model.CronJobModel
	 * )
	 */
	@Override
	public PerformResult perform(final CronJobModel paramT)
	{
		LOG.info("Retrieve PayPal pending payments information...");

		final StringBuilder query = new StringBuilder();
		query.append("select {pk} from {PaymentTransactionEntry} where {transactionStatusDetails} in (?transactionStatusDetails) and {transactionStatus}=?transactionStatus");
		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query.toString());
		searchQuery.addQueryParameter("transactionStatusDetails",
				Arrays.asList(PendingReason.PAYMENT_REVIEW.name(), PendingReason.ORDER.name()));
		searchQuery.addQueryParameter("transactionStatus", PaymentStatus.PENDING.name());
		final SearchResult result = flexibleSearchService.search(searchQuery);

		final List<PaymentTransactionEntryModel> transactionEntryModels = result.getResult();
		for (final PaymentTransactionEntryModel paymentTransactionEntryModel : transactionEntryModels)
		{
			final GetTransactionDetailsResultData transactionDetails = getTransactionDetailsService().getTransactionDetails(
					paymentTransactionEntryModel.getRequestId());
			if (AckCodeType.SUCCESS.name().equalsIgnoreCase(transactionDetails.getAck()))
			{
				if (!paymentTransactionEntryModel.getTransactionStatus().equalsIgnoreCase(
						transactionDetails.getPaymentStatus().name()))
				{

					if (PaymentStatus.COMPLETED == transactionDetails.getPaymentStatus())
					{
						paymentTransactionEntryModel.setTransactionStatus(TransactionStatus.ACCEPTED.name());
						paymentTransactionEntryModel.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL.name());
					}
					else if (PaymentStatus.REVERSED == transactionDetails.getPaymentStatus())
					{
						paymentTransactionEntryModel.setTransactionStatus(TransactionStatus.REJECTED.name());
						paymentTransactionEntryModel.setTransactionStatusDetails(TransactionStatusDetails.UNKNOWN_CODE.name());
					}
					modelService.save(paymentTransactionEntryModel);
				}
			}
		}
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	public TransactionDetailsService getTransactionDetailsService()
	{
		return transactionDetailsService;
	}

	public void setTransactionDetailsService(final TransactionDetailsService transactionDetailsService)
	{
		this.transactionDetailsService = transactionDetailsService;
	}

}
