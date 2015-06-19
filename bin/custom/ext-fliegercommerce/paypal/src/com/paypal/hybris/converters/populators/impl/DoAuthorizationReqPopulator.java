package com.paypal.hybris.converters.populators.impl;

import com.ebay.api.BasicAmountType;
import com.ebay.api.CurrencyCodeType;
import com.ebay.api.DoAuthorizationRequestType;
import com.ebay.utils.PaypalStringUtils;
import com.paypal.hybris.data.DoAuthorizationRequestData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.lang.StringUtils;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class DoAuthorizationReqPopulator implements Populator<DoAuthorizationRequestData, DoAuthorizationRequestType> {
    /**
     * Populate the target instance with values from the source instance.
     *
     * @param requestData        the source object
     * @param request the target to fill
     * @throws de.hybris.platform.servicelayer.dto.converter.ConversionException if an error occurs
     */
    @Override
    public void populate(DoAuthorizationRequestData requestData, DoAuthorizationRequestType request) throws ConversionException {
        request.setTransactionID(requestData.getTransactionId());

        if (StringUtils.isBlank(requestData.getCurrencyIsoCode())) {
            throw new ConversionException("Missing required currency iso code value");
        }
        CurrencyCodeType currencyCode = CurrencyCodeType.fromValue(requestData.getCurrencyIsoCode());
        BasicAmountType amount = createBasicAmountType(requestData.getAmount(), currencyCode);
        request.setAmount(amount);
    }

    private BasicAmountType createBasicAmountType(double amount, CurrencyCodeType currencyCode) {
        final BasicAmountType basicAmount = new BasicAmountType();
        basicAmount.setValue(PaypalStringUtils.formatNumber(amount));
        basicAmount.setCurrencyID(currencyCode);
        return basicAmount;
    }
}
