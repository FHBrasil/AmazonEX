package com.paypal.hybris.converters.populators;

import com.ebay.api.*;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.data.AbstractRequestData;

import de.hybris.platform.acceleratorfacades.order.data.DeliveryOrderEntryGroupData;
import de.hybris.platform.acceleratorfacades.order.data.OrderEntryGroupData;
import de.hybris.platform.acceleratorfacades.order.data.PickupOrderEntryGroupData;
import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.order.data.PromotionOrderEntryConsumedData;
import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commercefacades.product.data.PromotionResultData;
import de.hybris.platform.commercefacades.storelocator.data.PointOfServiceData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.promotions.result.PromotionOrderEntry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import de.hybris.platform.servicelayer.config.ConfigurationService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;


/**
 * Abstract class to provide basic logic of populating web service call request
 * with payment details data, e.g products details, shipping methods and so on.
 *
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public abstract class AbstractRequestPaymentDetailsPopulator<SOURCE, TARGET> implements Populator<SOURCE, TARGET> {

	private ConfigurationService configurationService;
	private PriceDataFactory priceDataFactory;

	protected List<PaymentDetailsType> createPaymentDetailsList(final AbstractRequestData requestData, final AbstractOrderData cart)
	{
		// support only one currency type for all cart prices
		List<PaymentDetailsType> detailsList = new ArrayList<>();
		AddressData deliveryAddress = cart.getDeliveryAddress();

		List<DeliveryOrderEntryGroupData> deliveryGroups = cart.getDeliveryOrderGroups();
		for (DeliveryOrderEntryGroupData deliveryGroup: deliveryGroups)
		{
			deliveryGroup.setDeliveryAddress(deliveryAddress);
			PaymentDetailsType paymentDetails = createPaymentDetails(cart, deliveryGroup, detailsList.size());
			detailsList.add(paymentDetails);
		}


		List<PickupOrderEntryGroupData> pickupGroups = cart.getPickupOrderGroups();
		for (PickupOrderEntryGroupData pickupGroup: pickupGroups)
		{
			PaymentDetailsType paymentDetails = createPaymentDetails(cart, pickupGroup, detailsList.size());
			detailsList.add(paymentDetails);
		}
		if(BigDecimal.ZERO.compareTo(cart.getOrderDiscounts().getValue()) < 0){
			assignOrderDiscountToPaymentDetails(detailsList, cart);
		}

		return detailsList;
	}

	private void assignOrderDiscountToPaymentDetails(List<PaymentDetailsType> detailsList, final AbstractOrderData cart){
		for(PaymentDetailsType paymentDetails : detailsList){
			BigDecimal orderTotal = BigDecimal.valueOf(Double.valueOf(
					paymentDetails.getOrderTotal().getValue().replaceAll(PaypalConstants.THOUSAND_SEPARATOR, "")));
			BigDecimal itemTotal = BigDecimal.valueOf(
					Double.valueOf(paymentDetails.getItemTotal().getValue().replaceAll(PaypalConstants.THOUSAND_SEPARATOR, "")));
			if(orderTotal.compareTo(cart.getOrderDiscounts().getValue()) > 0){
				orderTotal = orderTotal.subtract(cart.getOrderDiscounts().getValue());
				itemTotal = itemTotal.subtract(cart.getOrderDiscounts().getValue());
				paymentDetails.getOrderTotal().setValue(orderTotal.toString());
				paymentDetails.getItemTotal().setValue(itemTotal.toString());
				paymentDetails.getPaymentDetailsItem().add(createOrderDiscountPaymentItem(cart));
				break;
			}
		}
	}

	private void applyDiscountOnPaymentDetails(AbstractOrderData cart, PaymentDetailsType paymentDetails, OrderEntryData entry, CurrencyCodeType currencyCode) {

		for (PromotionResultData promotionResultData : cart.getAppliedProductPromotions()) {
			for (PromotionOrderEntryConsumedData promotionOrderEntry : promotionResultData.getConsumedEntries()) {
				if (promotionOrderEntry.getOrderEntryNumber().equals(entry.getEntryNumber())) {

					final PaymentDetailsItemType detailsItem = new PaymentDetailsItemType();
					BigDecimal discountValue =  BigDecimal.valueOf(promotionOrderEntry.getAdjustedUnitPrice()).subtract(entry.getBasePrice().getValue());
					detailsItem.setName(entry.getProduct().getName() + " " + PaypalConstants.DISCOUNT);
					detailsItem.setQuantity(BigInteger.valueOf(promotionOrderEntry.getQuantity()));
					detailsItem.setDescription(entry.getProduct().getName() + " " + PaypalConstants.DISCOUNT);

					final BasicAmountType basicAmount = new BasicAmountType();
					basicAmount.setValue(discountValue.toString());
					basicAmount.setCurrencyID(currencyCode);

					detailsItem.setAmount(basicAmount);
					if (StringUtils.isNotBlank(detailsItem.getAmount().getValue())) {
						paymentDetails.getPaymentDetailsItem().add(detailsItem);
					}
				}
			}
		}
	}

	private PaymentDetailsItemType createOrderDiscountPaymentItem(final AbstractOrderData cart) {

		final PaymentDetailsItemType detailsItem = new PaymentDetailsItemType();
		BigDecimal discountValue = cart.getOrderDiscounts().getValue().negate();
		detailsItem.setName(PaypalConstants.ORDER_DISCOUNT);
		detailsItem.setQuantity(BigInteger.valueOf(1));
		detailsItem.setDescription(PaypalConstants.ORDER_DISCOUNT);

		final BasicAmountType basicAmount = new BasicAmountType();
		basicAmount.setValue(discountValue.toString());

		final String currencyIsoCode = cart.getOrderDiscounts().getCurrencyIso();
		final CurrencyCodeType currencyCode = CurrencyCodeType.valueOf(currencyIsoCode);

		basicAmount.setCurrencyID(currencyCode);

		detailsItem.setAmount(basicAmount);
		return detailsItem;
	}

	private PaymentDetailsType createPaymentDetails(AbstractOrderData cart, OrderEntryGroupData entryGroup, int groupNumber)
	{
		PaymentDetailsType paymentDetails = new PaymentDetailsType();
		paymentDetails.setInvoiceID("hybris-" + System.currentTimeMillis() + groupNumber);
		paymentDetails.setSellerDetails(createSellerDetails());

		final String currencyIsoCode = entryGroup.getTotalPriceWithTax().getCurrencyIso();
		final CurrencyCodeType currencyCode = CurrencyCodeType.valueOf(currencyIsoCode);

		paymentDetails.setPaymentRequestID(cart.getCode() + PaypalConstants.PAYMENT_REQUEST_ID_SEPARATOR + groupNumber);

		for (final OrderEntryData entry : entryGroup.getEntries())
		{
			final PaymentDetailsItemType detailsItem = createPaymentDetailsItem(entry, currencyCode);
			paymentDetails.getPaymentDetailsItem().add(detailsItem);
			if( CollectionUtils.isNotEmpty(cart.getAppliedProductPromotions())){
				applyDiscountOnPaymentDetails(cart, paymentDetails, entry, currencyCode);
			}
		}


		// in case of gross amount items total price already includes taxes
		PriceData detailsItemsTotalPriceData = getPriceDataFactory().create(PriceDataType.BUY,
				    	entryGroup.getTotalPrice().getValue() , currencyIsoCode);

		PriceData detailsDeliveryTotalData;
		if (entryGroup instanceof DeliveryOrderEntryGroupData && cart.getDeliveryCost() != null)
		{
			detailsDeliveryTotalData = cart.getDeliveryCost();
		}
		else
		{
			detailsDeliveryTotalData = getPriceDataFactory().create(PriceDataType.BUY, BigDecimal.valueOf(0), currencyIsoCode);
		}

		AddressType address = createAddressForGroup(entryGroup);
		if (address != null)
		{
			paymentDetails.setShipToAddress(address);
		}

		//setting known params
		PriceData detailsTaxTotalData = getPriceDataFactory().create(PriceDataType.BUY, BigDecimal.valueOf(0), currencyIsoCode);
		// in case of net pricing separately calculate taxes otherwise taxes were already included in items total
		if (cart.isNet())
		{
			detailsTaxTotalData = entryGroup.getTotalTax();
		}


		final BasicAmountType itemTotal = createBasicAmountType(detailsItemsTotalPriceData, currencyCode);
		final BasicAmountType shippingTotal = createBasicAmountType(detailsDeliveryTotalData, currencyCode);
		final BasicAmountType taxTotal = createBasicAmountType(detailsTaxTotalData, currencyCode);

		double totalPrice = detailsItemsTotalPriceData.getValue().doubleValue() + detailsDeliveryTotalData.getValue().doubleValue() + detailsTaxTotalData.getValue().doubleValue();
		PriceData detailsOrderTotalData = getPriceDataFactory().create(PriceDataType.BUY, BigDecimal.valueOf(totalPrice), currencyIsoCode);
		final BasicAmountType orderTotal = createBasicAmountType(detailsOrderTotalData, currencyCode);

		paymentDetails.setItemTotal(itemTotal);
		paymentDetails.setShippingTotal(shippingTotal);
		paymentDetails.setTaxTotal(taxTotal);
		paymentDetails.setOrderTotal(orderTotal);

		return paymentDetails;
	}

	private PaymentDetailsItemType createPaymentDetailsItem(OrderEntryData entry, CurrencyCodeType currencyCode)
	{
		//getting item info from entry
		final String name = entry.getProduct().getName();		
		final Long quantity = entry.getQuantity();
		final String description = entry.getProduct().getDescription();
		final PriceData unitPrice = entry.getBasePrice();

		final BasicAmountType amount = createBasicAmountType(unitPrice, currencyCode);

		final PaymentDetailsItemType detailsItem = new PaymentDetailsItemType();
		detailsItem.setName(name);		
		detailsItem.setQuantity(BigInteger.valueOf(quantity));
		detailsItem.setDescription(description);
		detailsItem.setAmount(amount);

		return detailsItem;
	}

	protected BasicAmountType createBasicAmountType(PriceData priceData, CurrencyCodeType currencyCode) {
		final BasicAmountType basicAmount = new BasicAmountType();

		basicAmount.setValue(priceData.getFormattedPriceWithoutCurrencySymbol());

		basicAmount.setCurrencyID(currencyCode);
		return basicAmount;
	}

	private AddressType createAddressForGroup(OrderEntryGroupData entryGroup)
	{
		AddressData addressData = null;
		String addressName = StringUtils.EMPTY;
		if (entryGroup instanceof DeliveryOrderEntryGroupData)
		{
			DeliveryOrderEntryGroupData deliveryEntryGroup = (DeliveryOrderEntryGroupData) entryGroup;
			addressData = deliveryEntryGroup.getDeliveryAddress();

			if (addressData != null)
			{
				String addressTitle = addressData.getTitle();
				StringBuilder addressNameBuilder = new StringBuilder();
				if (addressTitle != null)
				{
					addressNameBuilder.append(addressTitle).append(PaypalConstants.ADDRESS_NAME_SEPARATOR);
				}
				addressNameBuilder.append(addressData.getFirstName()).append(PaypalConstants.ADDRESS_NAME_SEPARATOR);
				addressNameBuilder.append(addressData.getLastName());

				addressName = addressNameBuilder.toString();
			}
		}
		else if (entryGroup instanceof PickupOrderEntryGroupData)
		{
			PickupOrderEntryGroupData pickupEntryGroup = (PickupOrderEntryGroupData) entryGroup;
			PointOfServiceData pointOfService = pickupEntryGroup.getDeliveryPointOfService();
			addressData = pointOfService.getAddress();

			StringBuilder addressNameBuilder = new StringBuilder();
			addressNameBuilder.append(PaypalConstants.S2S_ADDRESS_NAME_PREFIX).append(PaypalConstants.ADDRESS_NAME_SEPARATOR);
			addressNameBuilder.append(pointOfService.getName());
			addressName = addressNameBuilder.toString();
		}


		AddressType address = null;
		if (addressData != null)
		{
			address = new AddressType();
			address.setName(addressName);
			address.setStreet1(addressData.getLine1());
			address.setStreet2(addressData.getLine2());
			address.setCityName(addressData.getTown());
			address.setPostalCode(addressData.getPostalCode());
			if (addressData.getRegion() != null)
			{
				address.setStateOrProvince(addressData.getRegion().getIsocodeShort());
			}
			if (addressData.getCountry() != null)
			{
				address.setCountry(CountryCodeType.fromValue(addressData.getCountry().getIsocode()));
			}
		}

		return address;
	}

	private SellerDetailsType createSellerDetails()
	{
		SellerDetailsType sellerDetails = new SellerDetailsType();
		sellerDetails.setPayPalAccountID(getConfigurationService().getConfiguration().getString(PaypalConstants.PAYPAL_SELLER_EMAIL));
		return sellerDetails;
	}

	protected void setPaymentActionForAllPaymentDetails(final String configuredPaymentAction,
			final List<PaymentDetailsType> paymentDetailsList)
	{
		String paymentAction = configuredPaymentAction;

		// in case of multiple shipping ignore config and set Order payment type
		if (paymentDetailsList.size() > 1)
		{
			paymentAction = PaypalConstants.ORDER_PAYMENT_ACTION_NAME;
		}
		else if (StringUtils.isBlank(configuredPaymentAction))
		{
			paymentAction = getConfigurationService().getConfiguration().getString(PaypalConstants.DEFAULT_PAYMENT_ACTION_NAME);
		}

		// set calculated payment action for all payment details
		for (final PaymentDetailsType paymentDetails : paymentDetailsList)
		{
			paymentDetails.setPaymentAction(PaymentActionCodeType.fromValue(paymentAction));
		}
	}

	public ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}

	public PriceDataFactory getPriceDataFactory()
	{
		return priceDataFactory;
	}

	public void setPriceDataFactory(PriceDataFactory priceDataFactory)
	{
		this.priceDataFactory = priceDataFactory;
	}
}
