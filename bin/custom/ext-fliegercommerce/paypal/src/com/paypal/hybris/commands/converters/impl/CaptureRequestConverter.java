package com.paypal.hybris.commands.converters.impl;

import com.ebay.api.*;
import com.ebay.utils.PaypalStringUtils;
import com.paypal.hybris.constants.PaypalConstants;
import de.hybris.platform.payment.commands.request.CaptureRequest;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.commons.lang.StringUtils;

/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class CaptureRequestConverter implements Converter<CaptureRequest, DoCaptureRequestType>
{

    /**
     * Convert data from source object to target
     *
     * @param captureRequest the source instance
     * @return converted item
     */
    @Override
    public DoCaptureRequestType convert(CaptureRequest captureRequest) throws ConversionException
    {
        DoCaptureRequestType doCaptureRequestType = new DoCaptureRequestType();
        return convert(captureRequest, doCaptureRequestType);
    }

    /**
     * Convert data from source object to target. Deprecated user {@link this.convert(de.hybris.platform.payment.commands.request.CaptureRequest)}
     * @param captureRequest        source object
     * @param doCaptureRequestPrototype prototype object
     * @return conv
     * @throws ConversionException
     */
    @Override
    @Deprecated
    public DoCaptureRequestType convert(CaptureRequest captureRequest, DoCaptureRequestType doCaptureRequestPrototype) throws ConversionException
    {
		  doCaptureRequestPrototype.setVersion(PaypalConstants.SOAP_API_VERSION);

		  doCaptureRequestPrototype.setAuthorizationID(captureRequest.getRequestId());
        if (StringUtils.isBlank(captureRequest.getCurrency().getCurrencyCode())) {
            throw new ConversionException("Missing required currency iso code value");
        }
        CurrencyCodeType currencyCode = CurrencyCodeType.fromValue(captureRequest.getCurrency().getCurrencyCode());
        BasicAmountType amount = createBasicAmountType(captureRequest.getTotalAmount().doubleValue(), currencyCode);
		  doCaptureRequestPrototype.setAmount(amount);

        // capture all amount at once
		  doCaptureRequestPrototype.setCompleteType(CompleteCodeType.COMPLETE);
        return doCaptureRequestPrototype;
    }

    protected BasicAmountType createBasicAmountType(double amount, CurrencyCodeType currencyCode) {
        final BasicAmountType basicAmount = new BasicAmountType();
        basicAmount.setValue(PaypalStringUtils.formatNumber(amount));
        basicAmount.setCurrencyID(currencyCode);
        return basicAmount;
    }
}
