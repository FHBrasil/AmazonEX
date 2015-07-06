package com.paypal.hybris.converters.populators.impl;

import com.ebay.api.*;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.data.PaymentCode;
import com.paypal.hybris.data.PaymentStatus;
import com.paypal.hybris.data.PendingReason;
import com.paypal.hybris.data.RefundTransactionResultData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.lang.math.NumberUtils;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class RefundTransactionResultDataPopulator implements Populator<RefundTransactionResponseType, RefundTransactionResultData>
{
	/**
	 * Populate the target instance with values from the source instance.
	 *
	 * @param response the source object
	 * @param resultData   the target to fill
	 * @throws de.hybris.platform.servicelayer.dto.converter.ConversionException if an error occurs
	 */
	@Override public void populate(RefundTransactionResponseType response,
			RefundTransactionResultData resultData) throws ConversionException
	{
		if (response.getRefundTransactionID() != null)
		{
			resultData.setRefundTransactionId(response.getRefundTransactionID().getValue());
		}

		BasicAmountType grossRefundAmount = response.getGrossRefundAmount();
		if (grossRefundAmount != null)
		{
			resultData.setGrossRefundAmount(NumberUtils.toDouble(grossRefundAmount.getValue(), PaypalConstants.DEFAULT_AMOUNT_VALUE));
		}
		BasicAmountType feeRefundAmount = response.getFeeRefundAmount();
		if (feeRefundAmount != null)
		{
			resultData.setFeeRefundAmount(NumberUtils.toDouble(feeRefundAmount.getValue(), PaypalConstants.DEFAULT_AMOUNT_VALUE));
		}
		BasicAmountType netRefundAmount = response.getNetRefundAmount();
		if (netRefundAmount != null)
		{
			resultData.setNetRefundAmount(NumberUtils.toDouble(netRefundAmount.getValue(), PaypalConstants.DEFAULT_AMOUNT_VALUE));
		}
		BasicAmountType totalRefundAmount = response.getTotalRefundedAmount();
		if (totalRefundAmount != null)
		{
			resultData.setTotalRefundedAmount(NumberUtils.toDouble(totalRefundAmount.getValue(), PaypalConstants.DEFAULT_AMOUNT_VALUE));
		}

		RefundInfoType refundInfo = response.getRefundInfo();
		if (refundInfo != null)
		{
			PaymentStatusCodeType refundStatus = refundInfo.getRefundStatus();
			if (refundStatus != null)
			{
				resultData.setRefundStatus(PaymentCode.valueOf(refundStatus.value().toUpperCase()));
			}
			else
			{
				resultData.setRefundStatus(PaymentCode.NONE);
			}

			PendingStatusCodeType pendingReason = refundInfo.getPendingReason();
			if (pendingReason != null)
			{
				resultData.setPendingReason(PendingReason.valueOf(pendingReason.value().toUpperCase().replace('-', '_')));
			}
		}



	}
}
