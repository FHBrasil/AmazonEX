package com.paypal.hybris.commands.converters.impl;

import com.ebay.api.DoCaptureResponseDetailsType;
import com.ebay.api.DoCaptureResponseType;
import com.ebay.api.PaymentInfoType;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.data.PaymentStatus;
import de.hybris.platform.converters.impl.AbstractConverter;
import de.hybris.platform.payment.commands.result.CaptureResult;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import org.apache.commons.lang.math.NumberUtils;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class DoCaptureResponseConverter extends
		AbstractConverter<DoCaptureResponseType, CaptureResult> {
	
	/**
	 * Override this method to populate the target from the source
	 *
	 * @param doCaptureResponse
	 *            the source instance
	 * @param captureResult
	 *            the target instance to fill
	 * @see #setTargetClass(Class)
	 */
	@Override
	public void populate(DoCaptureResponseType doCaptureResponse,
			CaptureResult captureResult) {
		DoCaptureResponseDetailsType details = doCaptureResponse.getDoCaptureResponseDetails();
		PaymentInfoType paymentInfo = details.getPaymentInfo();
		PaymentStatus paymentStatus = PaymentStatus.valueOf(paymentInfo.getPaymentStatus()
				.value()
				.toUpperCase());
		if (PaypalConstants.STATUS_SUCCESS.equals(doCaptureResponse.getAck()
				.value())) {
			if (PaymentStatus.COMPLETED == paymentStatus) {
				captureResult.setTransactionStatus(TransactionStatus.ACCEPTED);
				captureResult.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL);
			} else if (PaymentStatus.DENIED == paymentStatus) {
				captureResult.setTransactionStatus(TransactionStatus.REJECTED);
				captureResult.setTransactionStatusDetails(TransactionStatusDetails.UNKNOWN_CODE);
			} else {
				captureResult.setTransactionStatus(TransactionStatus.REVIEW);
				captureResult.setTransactionStatusDetails(TransactionStatusDetails.UNKNOWN_CODE);
			}
			captureResult.setTotalAmount(BigDecimal.valueOf(NumberUtils.toDouble(paymentInfo.getGrossAmount()
					.getValue())));
			captureResult.setCurrency(Currency.getInstance(paymentInfo.getGrossAmount()
					.getCurrencyID()
					.value()));
		} else {
			captureResult.setTransactionStatus(TransactionStatus.ERROR);
			captureResult.setTransactionStatusDetails(TransactionStatusDetails.UNKNOWN_CODE);
		}
		captureResult.setRequestTime(doCaptureResponse.getTimestamp()
				.toGregorianCalendar()
				.getTime());
		captureResult.setRequestId(paymentInfo.getTransactionID());
		captureResult.setRequestToken(details.getAuthorizationID());
	}
	
	
	@Override
	protected CaptureResult createTarget() {
		return new CaptureResult();
	}
}
