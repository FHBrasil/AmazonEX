package com.paypal.hybris.soap.params.impl;

import java.math.BigInteger;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.paypal.hybris.config.PaypalConfigManager;
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

import de.hybris.platform.payment.commands.request.SubscriptionAuthorizationRequest;

/**
 * @author Valentyn Markovych, Gorilla
 * 
 * 
 */
public class SetExpressCheckoutParams
		extends
		AbstractPaypalPaymentOperationParams<SetExpressCheckoutRequestType, SetExpressCheckoutResponseType> {

	{
		OPERATION_NAME = "SetExpressCheckout";
	}

	private SolutionTypeType solutionType;
	private AddressType addr;
	private PaymentActionCodeType paymentAction;
	private PaymentDetailsType details;
	private String returnUrl;
	private String cancelUrl;

	@Resource
	private PaypalConfigManager paypalConfigManager;
	
	/**
 * 
 */
	public SetExpressCheckoutParams() {

		super();
	}
	
	@PostConstruct
	protected void init()
	{
		returnUrl = paypalConfigManager.getProperty(PaypalConstants.SETT_RETURN_URL_SINGLE_DESKTOP);
		cancelUrl = paypalConfigManager.getProperty(PaypalConstants.SETT_CANCEL_URL_SINGLE_DESKTOP);
		solutionType = SolutionTypeType.valueOf(paypalConfigManager.getProperty(PaypalConstants.PARAM_SOLUTION_TYPE));
	}

	/**
	 * Unique order id
	 */
	private String uid;

	/**
	 * @param addr
	 *            the addr to set
	 */
	public void setAddr(final AddressType addr) {

		this.addr = addr;
	}

	/**
	 * @return the returnUrl
	 */
	public String getReturnUrl() {

		return returnUrl;
	}

	/**
	 * @param returnUrl
	 *            the returnUrl to set
	 */
	public void setReturnUrl(final String returnUrl) {

		this.returnUrl = returnUrl;
	}

	/**
	 * @return the cancelUrl
	 */
	public String getCancelUrl() {

		return cancelUrl;
	}

	/**
	 * @param cancelUrl
	 *            the cancelUrl to set
	 */
	public void setCancelUrl(final String cancelUrl) {

		this.cancelUrl = cancelUrl;
	}

	/**
	 * @see com.paypal.hybris.soap.params.impl.AbstractPaypalOperationParams#setParamsFromMap(java.util.Map)
	 */
	@Override
	public void setParamsFromMap(final Map<String, String[]> params) {

		final CurrencyCodeType currencyId = CurrencyCodeType.valueOf(StrUtil
				.getPar(params, PaypalConstants.PARAM_CURRENCY));

		final PaymentDetailsType det = new PaymentDetailsType();

		double orderTotalAmount = 0;
		orderTotalAmount += StrUtil.toDouble(
				StrUtil.getPar(params, PaypalConstants.PARAM_SUBTOTAL), 0.0);
		orderTotalAmount += StrUtil.toDouble(
				StrUtil.getPar(params, PaypalConstants.PARAM_TAX), 0.0);
		orderTotalAmount += StrUtil.toDouble(
				StrUtil.getPar(params, PaypalConstants.PARAM_SHIPPING), 0.0);

		final BasicAmountType amount = new BasicAmountType();
		amount.setValue(StrUtil.formatNumber(StrUtil.toDouble(
				StrUtil.getPar(params, PaypalConstants.PARAM_SUBTOTAL), 0.0)));
		amount.setCurrencyID(currencyId);

		final PaymentDetailsItemType item = new PaymentDetailsItemType();
		item.setName(StrUtil.getPar(params, PaypalConstants.PARAM_ITEM_NAME,
				"undefinedItemName"));
		item.setNumber(StrUtil.getPar(params,
				PaypalConstants.PARAM_ITEM_NUMBER, "undefinedItemNumber"));
		item.setQuantity(BigInteger.valueOf(1L));
		item.setDescription(StrUtil.getPar(params,
				PaypalConstants.PARAM_ITEM_DESCRIPTION, "description"));
		item.setAmount(amount);

		det.getPaymentDetailsItem().add(item);

		final BasicAmountType itemTotal = new BasicAmountType();
		itemTotal.setValue(StrUtil.formatNumber(StrUtil.toDouble(
				StrUtil.getPar(params, PaypalConstants.PARAM_SUBTOTAL), 0.0)));
		itemTotal.setCurrencyID(currencyId);

		final BasicAmountType taxTotal = new BasicAmountType();
		taxTotal.setValue(StrUtil.formatNumber(StrUtil.toDouble(
				StrUtil.getPar(params, PaypalConstants.PARAM_TAX), 0.0)));
		taxTotal.setCurrencyID(currencyId);

		final BasicAmountType shippingTotal = new BasicAmountType();
		shippingTotal.setValue(StrUtil.formatNumber(StrUtil.toDouble(
				StrUtil.getPar(params, PaypalConstants.PARAM_SHIPPING), 0.0)));
		shippingTotal.setCurrencyID(currencyId);

		final BasicAmountType shippingDiscount = new BasicAmountType();
		shippingDiscount.setValue(StrUtil.formatNumber(0.0));
		shippingDiscount.setCurrencyID(currencyId);

		final BasicAmountType handlingTotal = new BasicAmountType();
		handlingTotal.setValue(StrUtil.formatNumber(0.0));
		handlingTotal.setCurrencyID(currencyId);

		final BasicAmountType insuranceTotal = new BasicAmountType();
		insuranceTotal.setValue(StrUtil.formatNumber(0.0));
		insuranceTotal.setCurrencyID(currencyId);

		final BasicAmountType orderTotal = new BasicAmountType();
		orderTotal.setValue(StrUtil.formatNumber(orderTotalAmount));
		orderTotal.setCurrencyID(currencyId);
		final String allowNote = "1";

		det.setItemTotal(itemTotal);
		det.setTaxTotal(taxTotal);
		det.setShippingTotal(shippingTotal);
		det.setShippingDiscount(shippingDiscount);
		det.setHandlingTotal(handlingTotal);
		det.setInsuranceTotal(insuranceTotal);
		det.setOrderTotal(orderTotal);
		det.setNoteText(allowNote);

		// Account optional
		String solutionTypeString = StrUtil.getPar(params,
				PaypalConstants.PARAM_SOLUTION_TYPE);
		if (solutionTypeString == null) {
			solutionTypeString = paypalConfigManager
					.getProperty(PaypalConstants.PARAM_SOLUTION_TYPE);
		}
		setSolutionType(SolutionTypeType.valueOf(solutionTypeString));

		// Unique order ID
		setUid(UUID.randomUUID().toString().replaceAll("-", ""));

		// Payment action
		String paymentActionString = StrUtil.getPar(params,
				PaypalConstants.PARAM_PAYMENT_ACTION);
		if (paymentActionString == null) {
			paymentActionString = paypalConfigManager
					.getProperty(PaypalConstants.PARAM_PAYMENT_ACTION);
		}
		setPaymentAction(PaymentActionCodeType.valueOf(paymentActionString));

		// Order total
		// final double total = 0;
		// final BasicAmountType orderTotal = new BasicAmountType();
		//
		// final String totalStr = StrUtil.getPar(params,
		// PaypalConstants.PARAM_TOTAL_HOP);
		// if (totalStr != null) { // We got HOP parameters
		// orderTotal.setCurrencyID(CurrencyCodeType.valueOf(StrUtil.getPar(params,
		// PaypalConstants.PARAM_CURRENCY_HOP)));
		// orderTotal.setValue(StrUtil.formatNumber(StrUtil.toDouble(totalStr)));
		// } else { // We got console parameters
		// orderTotal.setCurrencyID(CurrencyCodeType.valueOf(StrUtil.getPar(params,
		// PaypalConstants.PARAM_CURRENCY)));
		// total += StrUtil.toDouble(
		// StrUtil.getPar(params, PaypalConstants.PARAM_SUBTOTAL), 0.0);
		// total += StrUtil.toDouble(
		// StrUtil.getPar(params, PaypalConstants.PARAM_TAX), 0.0);
		// total += StrUtil.toDouble(
		// StrUtil.getPar(params, PaypalConstants.PARAM_SHIPPING), 0.0);
		// orderTotal.setValue(StrUtil.formatNumber(total));
		// }

		// final PaymentDetailsType det = new PaymentDetailsType();
		det.setOrderTotal(orderTotal);
		setDetails(det);
	}

	/**
	 * @param req
	 */
	public void setParamsFromRequest(final SubscriptionAuthorizationRequest req) {

		final PaymentDetailsType det = new PaymentDetailsType();

		// orderTotal
		final BasicAmountType orderTotal = new BasicAmountType();
		orderTotal.setCurrencyID(CurrencyCodeType.valueOf(req.getCurrency()
				.getCurrencyCode()));
		orderTotal.setValue(StrUtil.formatNumber(req.getTotalAmount()));
		det.setOrderTotal(orderTotal);

		setDetails(det);
	}

	/**
	 * @return the solutionType
	 */
	public SolutionTypeType getSolutionType() {

		return solutionType;
	}

	/**
	 * @param solutionType
	 *            the solutionType to set
	 */
	public void setSolutionType(final SolutionTypeType solutionType) {

		this.solutionType = solutionType;
	}

	/**
	 * @return customer address
	 */
	public AddressType getAddr() {

		return this.addr;
	}

	/**
	 * @return the uid
	 */
	public String getUid() {

		return uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(final String uid) {

		this.uid = uid;
	}

	/**
	 * @return the paymentAction
	 */
	public PaymentActionCodeType getPaymentAction() {

		return paymentAction;
	}

	/**
	 * @param paymentAction
	 *            the paymentAction to set
	 */
	public void setPaymentAction(final PaymentActionCodeType paymentAction) {

		this.paymentAction = paymentAction;
	}

}
