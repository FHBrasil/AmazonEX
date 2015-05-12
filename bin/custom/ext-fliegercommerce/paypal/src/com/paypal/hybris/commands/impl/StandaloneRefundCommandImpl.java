/**
 * 
 */
package com.paypal.hybris.commands.impl;


import de.hybris.platform.payment.commands.StandaloneRefundCommand;
import de.hybris.platform.payment.commands.request.StandaloneRefundRequest;
import de.hybris.platform.payment.commands.result.RefundResult;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;

import java.util.Date;

import org.apache.log4j.Logger;


/**
 * @author Valentyn Markovych, Gorilla
 * 
 */
public class StandaloneRefundCommandImpl extends AbstractCommandImpl implements
		StandaloneRefundCommand<StandaloneRefundRequest> {

//FIXME to implement


private final static Logger LOG = Logger
		.getLogger(StandaloneRefundCommandImpl.class);


@Override
public RefundResult perform(final StandaloneRefundRequest req) {

	LOG.info("-----------------------------------------------------");
	LOG.info("StandaloneRefundCommandImpl perform requested");
	LOG.info("-----------------------------------------------------");

	req.getMerchantTransactionCode();

	final RefundResult result = new RefundResult();
	result.setCurrency(req.getCurrency());
	result.setTotalAmount(req.getTotalAmount());
	result.setRequestTime(new Date());
	result.setTransactionStatus(TransactionStatus.ACCEPTED);
	result.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL);

	return result;
}


}
