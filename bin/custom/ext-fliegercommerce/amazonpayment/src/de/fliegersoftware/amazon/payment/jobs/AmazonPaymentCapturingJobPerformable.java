package de.fliegersoftware.amazon.payment.jobs;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import de.fliegersoftware.amazon.payment.services.AmazonPaymentService;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

public class AmazonPaymentCapturingJobPerformable extends
		AbstractJobPerformable<CronJobModel> {

	private static final Logger LOG = Logger
			.getLogger(AmazonPaymentCapturingJobPerformable.class);

	@Resource
	private AmazonPaymentService amazonPaymentService;

	@Override
	public PerformResult perform(CronJobModel cronjob) {
		LOG.info("Perform amazon capture process");

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

		final List<OrderModel> res = result.getResult();
		for (final Iterator<OrderModel> it = res.iterator(); it.hasNext();) {
			final OrderModel order = it.next();

			for (final PaymentTransactionModel txn : order.getPaymentTransactions()) {
				final PaymentTransactionEntryModel txnEntry = amazonPaymentService.capture(txn);

				if(txnEntry != null
					&& TransactionStatus.ACCEPTED.name().equals(txnEntry.getTransactionStatus())) {
					if (LOG.isDebugEnabled()) {
						LOG.debug("The payment transaction has been captured. Order: "
								+ order.getCode() + ". Txn: " + txn.getCode());
					}
					order.setStatus(OrderStatus.PAYMENT_CAPTURED);
					modelService.save(order);
				} else {
					LOG.error("The payment transaction capture has failed. Order: "
							+ order.getCode() + ". Txn: " + txn.getCode());
					order.setStatus(OrderStatus.PAYMENT_NOT_CAPTURED);
					modelService.save(order);
				}
			}
		}

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}
}