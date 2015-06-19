package com.paypal.hybris.converters.populators.impl;

import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.acceleratorfacades.order.data.DeliveryOrderEntryGroupData;
import de.hybris.platform.acceleratorfacades.order.data.PickupOrderEntryGroupData;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.ebay.api.BillingAgreementDetailsType;
import com.ebay.api.BillingCodeType;
import com.ebay.api.FundingSourceDetailsType;
import com.ebay.api.MerchantPullPaymentCodeType;
import com.ebay.api.PaymentDetailsType;
import com.ebay.api.SetExpressCheckoutRequestDetailsType;
import com.ebay.api.SetExpressCheckoutRequestType;
import com.ebay.api.UserSelectedFundingSourceType;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.converters.populators.AbstractRequestPaymentDetailsPopulator;
import com.paypal.hybris.data.SetExpressCheckoutRequestData;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class SetExprCheckoutReqPaymentDetailsPopulator extends
		AbstractRequestPaymentDetailsPopulator<SetExpressCheckoutRequestData, SetExpressCheckoutRequestType>
{

	/**
	 * Populate the target instance with values from the source instance.
	 *
	 * @param requestData
	 *           the source object
	 * @param request
	 *           the target to fill
	 * @throws de.hybris.platform.servicelayer.dto.converter.ConversionException
	 *            if an error occurs
	 */
	@Override
	public void populate(final SetExpressCheckoutRequestData requestData, final SetExpressCheckoutRequestType request)
			throws ConversionException
	{
		final CartData cart = requestData.getSessionCart();
		final List<PaymentDetailsType> paymentDetailsList = createPaymentDetailsList(requestData, cart);

		SetExpressCheckoutRequestDetailsType requestDetails = request.getSetExpressCheckoutRequestDetails();
		if (requestDetails == null)
		{
			requestDetails = new SetExpressCheckoutRequestDetailsType();
			request.setSetExpressCheckoutRequestDetails(requestDetails);
		}

		requestDetails.getPaymentDetails().addAll(paymentDetailsList);

		// don't display shipping address on PayPal in case of pickup only order
		if (isAllPickup(cart))
		{
			requestDetails.setNoShipping(PaypalConstants.NO_SHIPPING_DOES_NOT_DISPLAY);
		}

		if (Boolean.TRUE.toString().equalsIgnoreCase(
				getConfigurationService().getConfiguration().getString(PaypalConstants.REQUIRE_BILLING_ADDRESS)))
		{
			requestDetails.setReqBillingAddress(PaypalConstants.REQUIRE_BILLING_ADDRESS_OPTION_ON);
		}
		if (!isAllPickup(cart) && cart.getDeliveryAddress() != null)
		{
			requestDetails.setAddressOverride(PaypalConstants.ADDRESS_OVERRIDE_OPTION_ON);
		}

		setPaymentActionForAllPaymentDetails(requestData.getPaymentAction(), requestDetails.getPaymentDetails());

		if (!StringUtils.isEmpty(requestData.getFundingSource()))
		{
			final FundingSourceDetailsType fundingSource = new FundingSourceDetailsType();
			fundingSource.setUserSelectedFundingSource(UserSelectedFundingSourceType.fromValue(requestData.getFundingSource()));
			requestDetails.setFundingSourceDetails(fundingSource);
		}

		if (Boolean.TRUE.toString().equalsIgnoreCase(
				getConfigurationService().getConfiguration().getString(PaypalConstants.USE_REFERENCE_TRANSCATION)))
		{
			setBillingAgreementDetails(requestData, requestDetails);
		}

		requestDetails.setAllowNote(PaypalConstants.ALLOW_NOTE_OPTION_OFF);
	}

	/**
	 * @param requestData
	 */
	private void setBillingAgreementDetails(final SetExpressCheckoutRequestData requestData,
			final SetExpressCheckoutRequestDetailsType requestDetails)
	{
		final BillingAgreementDetailsType billingAgreement = new BillingAgreementDetailsType();
		billingAgreement.setBillingType(BillingCodeType.fromValue(PaypalConstants.SINGLE_BILLING_AGREEMENT));
		billingAgreement.setPaymentType(MerchantPullPaymentCodeType.INSTANT_ONLY);

		final List<BillingAgreementDetailsType> billingAgreementsList = requestDetails.getBillingAgreementDetails();
		billingAgreementsList.add(billingAgreement);
	}

	private boolean isAllPickup(final CartData cart)
	{
		boolean isAllPickup = true;
		final List<DeliveryOrderEntryGroupData> deliveryGroups = cart.getDeliveryOrderGroups();
		final List<PickupOrderEntryGroupData> pickupGroups = cart.getPickupOrderGroups();

		if (CollectionUtils.isNotEmpty(deliveryGroups))
		{
			isAllPickup = false;
		}

		return isAllPickup;
	}

}
