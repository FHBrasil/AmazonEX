package com.paypal.hybris.converters.populators.impl;

import com.ebay.api.*;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.data.*;
import com.paypal.hybris.data.TransactionType;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commercefacades.user.data.RegionData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import javax.xml.datatype.XMLGregorianCalendar;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class GetTransactionDetailsResultDataPopulator implements Populator<GetTransactionDetailsResponseType, GetTransactionDetailsResultData>
{
	/**
	 * Populate the target instance with values from the source instance.
	 *
	 * @param response the source object
	 * @param resultData   the target to fill
	 * @throws ConversionException if an error occurs
	 */
	@Override public void populate(GetTransactionDetailsResponseType response,
			GetTransactionDetailsResultData resultData) throws ConversionException
	{
		PaymentTransactionType transactionDetails = response.getPaymentTransactionDetails();
		if (transactionDetails != null)
		{
			resultData.setTPLReferenceID(transactionDetails.getTPLReferenceID());

			ReceiverInfoType receiverInfo = transactionDetails.getReceiverInfo();
			populateReceiverInfo(resultData, receiverInfo);

			PayerInfoType payerInfo = transactionDetails.getPayerInfo();
			populatePayerInfo(resultData, payerInfo);

			PaymentInfoType paymentInfo = transactionDetails.getPaymentInfo();
			populatePaymentInfo(resultData, paymentInfo);

		}
	}

	private void populatePaymentInfo(final GetTransactionDetailsResultData resultData, final PaymentInfoType paymentInfo)
	{
		if (paymentInfo != null)
		{
			resultData.setTransactionId(paymentInfo.getTransactionID());
			resultData.setParentTransactionId(paymentInfo.getParentTransactionID());
			resultData.setReceiptId(paymentInfo.getReceiptID());
			resultData.setStoreId(paymentInfo.getStoreID());
			resultData.setSubject(paymentInfo.getSubject());
			resultData.setCurrencyCode(StringUtils.EMPTY);

			PaymentTransactionCodeType transactionType = paymentInfo.getTransactionType();
			if (transactionType != null)
			{
				resultData.setTransactionType(TransactionType.valueOf(transactionType.name()));
			}
			PaymentCodeType paymentType = paymentInfo.getPaymentType();
			if (paymentType != null)
			{
				resultData.setPaymentType(PaymentCode.valueOf(paymentType.value().toUpperCase()));
			}
			XMLGregorianCalendar paymentDate = paymentInfo.getPaymentDate();
			if (paymentDate != null)
			{
				resultData.setPaymentData(paymentDate.toGregorianCalendar());
			}
			PaymentStatusCodeType paymentStatus = paymentInfo.getPaymentStatus();
			if (paymentStatus != null)
			{
				resultData.setPaymentStatus(PaymentStatus.valueOf(paymentStatus.value().toUpperCase()));
			}
			PendingStatusCodeType pendingReason = paymentInfo.getPendingReason();
			if (pendingReason != null)
			{
				resultData.setPendingReason(PendingReason.valueOf(pendingReason.name()));
			}

			BasicAmountType grossAmount = paymentInfo.getGrossAmount();
			if (grossAmount != null)
			{
				resultData.setGrossAmount(NumberUtils.toDouble(grossAmount.getValue(), PaypalConstants.DEFAULT_AMOUNT_VALUE));
				resultData.setCurrencyCode(grossAmount.getCurrencyID().value());
			}
			BasicAmountType feeAmount = paymentInfo.getFeeAmount();
			if (feeAmount != null)
			{
				resultData.setFeeAmount(NumberUtils.toDouble(feeAmount.getValue(), PaypalConstants.DEFAULT_AMOUNT_VALUE));
			}
			BasicAmountType taxAmount = paymentInfo.getTaxAmount();
			if (taxAmount != null)
			{
				resultData.setTaxAmount(NumberUtils.toDouble(taxAmount.getValue(), PaypalConstants.DEFAULT_AMOUNT_VALUE));
			}

		}
	}

	private void populateReceiverInfo(final GetTransactionDetailsResultData resultData, final ReceiverInfoType receiverInfo)
	{
		if (receiverInfo != null)
		{
			resultData.setReceiverEmail(receiverInfo.getReceiver());
			resultData.setReceiverId(receiverInfo.getReceiverID());
		}
	}

	private void populatePayerInfo(final GetTransactionDetailsResultData resultData, final PayerInfoType payerInfo)
	{
		if (payerInfo != null)
		{
			resultData.setPayerEmail(payerInfo.getPayer());
			resultData.setPayerId(payerInfo.getPayerID());
			PersonNameType payerName =payerInfo.getPayerName();
			if (payerName != null)
			{
				resultData.setPayerName(createPayerNameData(payerName));
			}

			CountryCodeType payerCountry = payerInfo.getPayerCountry();
			if (payerCountry != null)
			{
				resultData.setPayerCountryIso(payerCountry.value());
			}

			PayPalUserStatusCodeType payerStatus = payerInfo.getPayerStatus();
			if (payerStatus != null)
			{
				resultData.setPayerStatus(PayerStatus.valueOf(payerStatus.value().toUpperCase()));
			}

			AddressType payerAddress = payerInfo.getAddress();
			if (payerAddress != null)
			{
				resultData.setPayerAddress(createAddressData(payerInfo, payerAddress));
			}
		}
	}

	private AddressData createAddressData(PayerInfoType payerInfo, AddressType address)
	{
		AddressData addressData = new AddressData();

		PersonNameType payerName = payerInfo.getPayerName();
		if (payerName != null)
		{
			addressData.setFirstName(payerName.getFirstName());
			addressData.setLastName(payerName.getLastName());
		}
		addressData.setId(address.getAddressID());
		addressData.setLine1(address.getStreet1());
		addressData.setLine2(address.getStreet2());
		addressData.setTown(address.getCityName());

		String stateOrProvince = address.getStateOrProvince();
		CountryCodeType country = address.getCountry();
		if (StringUtils.isNotBlank(stateOrProvince) && country != null)
		{
			RegionData regionData = new RegionData();
			regionData.setIsocode(country.value() + "-" + stateOrProvince);
			addressData.setRegion(regionData);
		}
		if (country != null)
		{
			CountryData countryData = new CountryData();
			countryData.setName(address.getCountryName());
			countryData.setIsocode(address.getCountry().value());
			addressData.setCountry(countryData);
		}

		return addressData;
	}

	private PayerName createPayerNameData(PersonNameType payerName)
	{
		PayerName payerNameData = new PayerName();
		payerNameData.setFirstName(payerName.getFirstName());
		payerNameData.setLastName(payerName.getLastName());
		payerNameData.setMiddleName(payerName.getMiddleName());
		payerNameData.setSalutation(payerName.getSalutation());
		payerNameData.setSuffix(payerName.getSuffix());

		return payerNameData;
	}
}
