/**
 * 
 */
package com.paypal.hybris.soap.params.impl;


import com.paypal.hybris.soap.gen.AbstractRequestType;
import com.paypal.hybris.soap.gen.AbstractResponseType;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.payment.commands.request.SubscriptionAuthorizationRequest;
import de.hybris.platform.util.DiscountValue;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.soap.gen.AddressType;
import com.paypal.hybris.soap.gen.BasicAmountType;
import com.paypal.hybris.soap.gen.CurrencyCodeType;
import com.paypal.hybris.soap.gen.PaymentActionCodeType;
import com.paypal.hybris.soap.gen.PaymentDetailsItemType;
import com.paypal.hybris.soap.gen.PaymentDetailsType;
import com.paypal.hybris.soap.gen.SetExpressCheckoutRequestType;
import com.paypal.hybris.soap.gen.SetExpressCheckoutResponseType;
import com.paypal.hybris.soap.gen.SolutionTypeType;
import com.paypal.hybris.util.StrUtil;


/**
 * @author Valentyn Markovych, Gorilla
 * 
 */
public abstract class AbstractPaypalPaymentOperationParams<TI extends AbstractRequestType, TO extends AbstractResponseType>
		extends AbstractPaypalOperationParams<TI, TO> {


private PaymentDetailsType details;


/**
 * Gets cart and sets parameters from it.
 * 
 * @param cart
 *          cart item to set parameters from.
 */
public void setParamsFromCart(final CartModel cart) {

	final List<AbstractOrderEntryModel> entries = cart.getEntries();
	final PaymentDetailsType det = new PaymentDetailsType();
	double totalDiscount = 0;
	double itemTotalPriceBase = 0;

	for (final AbstractOrderEntryModel entry : entries) {
		//getting item info from entry
		final String name = entry.getProduct().getName();
		final String number = entry.getProduct().getCode();
		final Long quantity = entry.getQuantity();
		final String description = entry.getInfo();
		final Double unitPrice = entry.getBasePrice();
		itemTotalPriceBase += unitPrice * quantity;
		final BasicAmountType amount = new BasicAmountType();
		amount.setValue(StrUtil.formatNumber(unitPrice));
		//setting to  PaymentDetailsItemType
		final PaymentDetailsItemType item = new PaymentDetailsItemType();
		item.setName(name);
		item.setNumber(number);
		item.setQuantity(BigInteger.valueOf(quantity));
		item.setDescription(description);
		item.setAmount(amount);
		//adding item to list of items
		det.getPaymentDetailsItem().add(item);
		final List<DiscountValue> discounts = entry.getDiscountValues();
		if (discounts != null && !discounts.isEmpty()) {
			totalDiscount += discounts.get(0).getAppliedValue();
		}
	}
	final String codeString = cart.getCurrency().getIsocode();
	final CurrencyCodeType code = CurrencyCodeType.valueOf(codeString);
	//setting known params
	final BasicAmountType itemTotal = new BasicAmountType();
	itemTotal.setValue(StrUtil.formatNumber(itemTotalPriceBase));
	itemTotal.setCurrencyID(code);
	final BasicAmountType orderTotal = new BasicAmountType();
	orderTotal.setCurrencyID(code);
	orderTotal.setValue(StrUtil.formatNumber(cart.getTotalPrice()));
	final String allowNote = "1";


	// Tax excluded according to
	// https://wiki.hybris.com/display/forum/Total+Price+doesn%27t+include+tax+when+using+Net+pricing
	//	final BasicAmountType taxTotal = new BasicAmountType();
	//	taxTotal.setValue(StrUtil.formatNumber(cart.getTotalTax()) );
	//	taxTotal.setCurrencyID(code);

	final double deliveryCost = cart.getDeliveryCost();
	final BasicAmountType shippingTotal = new BasicAmountType();
	shippingTotal.setValue(StrUtil.formatNumber(deliveryCost));
	shippingTotal.setCurrencyID(code);
	//shippingDiscount has to be checked on correct fillment
	final BasicAmountType shippingDiscount = new BasicAmountType();
	shippingDiscount.setValue(StrUtil.formatNumber(-totalDiscount));
	shippingDiscount.setCurrencyID(code);
	//handlingTotal isn't set yet
	final BasicAmountType handlingTotal = new BasicAmountType();
	handlingTotal.setValue(StrUtil.formatNumber(0));
	handlingTotal.setCurrencyID(code);
	//insuranceTotal isn't set yet
	final BasicAmountType insuranceTotal = new BasicAmountType();
	insuranceTotal.setValue(StrUtil.formatNumber(0));
	insuranceTotal.setCurrencyID(code);

	det.setItemTotal(itemTotal);
	//	det.setTaxTotal(taxTotal);
	det.setShippingTotal(shippingTotal);
	det.setShippingDiscount(shippingDiscount);
	det.setHandlingTotal(handlingTotal);
	det.setInsuranceTotal(insuranceTotal);
	det.setOrderTotal(orderTotal);
	det.setNoteText(allowNote);
	setDetails(det);
}


/**
 * @return the details
 */
public PaymentDetailsType getDetails() {

	return details;
}


/**
 * @param details
 *          the details to set
 */
public void setDetails(final PaymentDetailsType details) {

	this.details = details;
}


}
