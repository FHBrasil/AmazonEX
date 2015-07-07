package com.paypal.hybris.converters.populators.impl;

import com.ebay.api.DoCaptureResponseDetailsType;
import com.ebay.api.DoCaptureResponseType;
import com.ebay.api.PaymentInfoType;
import com.paypal.hybris.data.DoCaptureResultData;
import com.paypal.hybris.data.PaymentStatus;
import com.paypal.hybris.data.PendingReason;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.lang.math.NumberUtils;

/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class DoCaptureResultDataPopulator implements Populator<DoCaptureResponseType, DoCaptureResultData> {
    /**
     * Populate the target instance with values from the source instance.
     *
     * @param response the source object
     * @param resultData   the target to fill
     * @throws de.hybris.platform.servicelayer.dto.converter.ConversionException if an error occurs
     */
    @Override
    public void populate(DoCaptureResponseType response, DoCaptureResultData resultData) throws ConversionException {
        DoCaptureResponseDetailsType details = response.getDoCaptureResponseDetails();
        resultData.setAuthorizationId(details.getAuthorizationID());

        PaymentInfoType paymentInfo = details.getPaymentInfo();
        PaymentStatus paymentStatus = PaymentStatus.valueOf(paymentInfo.getPaymentStatus().value().toUpperCase());
        resultData.setPaymentStatus(paymentStatus);
        if (PaymentStatus.PENDING == paymentStatus) {
            PendingReason pendingReason = PendingReason.valueOf(paymentInfo.getPendingReason().name());
            resultData.setPendingReason(pendingReason);
        }
        resultData.setTransactionId(paymentInfo.getTransactionID());
        resultData.setParentTransactionId(paymentInfo.getParentTransactionID());
        resultData.setReceiptId(paymentInfo.getReceiptID());
        resultData.setAmount(NumberUtils.toDouble(paymentInfo.getGrossAmount().getValue()));
        resultData.setCurrencyIsoCode(paymentInfo.getGrossAmount().getCurrencyID().value());

        resultData.setPaymentDate(paymentInfo.getPaymentDate().toGregorianCalendar());
    }
}
