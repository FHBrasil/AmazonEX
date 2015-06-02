/**
 *
 */
package com.paypal.hybris.addon.controllers.pages;

import com.paypal.hybris.addon.controllers.PaypaladdonControllerConstants;
import com.paypal.hybris.addon.controllers.utils.ControllerUtils;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.data.AbstractResultData;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.security.GUIDCookieStrategy;
import de.hybris.platform.commercefacades.order.CheckoutFacade;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commercefacades.user.data.RegionData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.storefront.controllers.pages.checkout.steps.HopPaymentResponseController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paypal.hybris.facade.impl.PayPalPaymentFacade;
import com.paypal.hybris.data.GetExpressCheckoutDetailsRequestData;
import com.paypal.hybris.data.GetExpressCheckoutDetailsResultData;

import java.util.List;
import java.util.Map;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
@Controller
@RequestMapping(value = "/paypal/checkout/hop")
public class PayPalHopPaymentResponseController extends HopPaymentResponseController
{
	@Resource(name = "payPalPaymentFacade")
	private PayPalPaymentFacade paypalFacade;

	@Resource(name = "checkoutFacade")
	private CheckoutFacade checkoutFacade;

	@Resource(name = "userFacade")
	private UserFacade userFacade;

	@Resource(name = "guidCookieStrategy")
	private GUIDCookieStrategy guidCookieStrategy;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.storefront.controllers.pages.checkout.steps.HopPaymentResponseController#doHandleHopResponse
	 * (javax.servlet.http.HttpServletRequest)
	 */
	@RequestMapping(value = "/response")
	public String doHandleHopResponse(final HttpServletRequest request, final HttpServletResponse response)
	{
		final String token = getSessionService().getAttribute("PAYPAL_TOKEN");
		final GetExpressCheckoutDetailsRequestData requestData = new GetExpressCheckoutDetailsRequestData();
		requestData.setToken(token);
		final GetExpressCheckoutDetailsResultData resultData = paypalFacade.getExpressCheckoutDetails(requestData);

		if (PaypalConstants.STATUS_SUCCESS.equalsIgnoreCase(resultData.getAck()))
		{
			//check if user go to paypal through shortcut and user is anonymous
			if (isPayPalExpressCheckoutShortcut(request) && userFacade.isAnonymousUser())
			{
				try
				{
					//create guest session
					getCustomerFacade().createGuestUserForAnonymousCheckout(resultData.getPayer(),
							getMessageSource().getMessage("text.guest.customer", null, getI18nService().getCurrentLocale()));
					guidCookieStrategy.setCookie(request, response);
					getSessionService().setAttribute(WebConstants.ANONYMOUS_CHECKOUT, Boolean.TRUE);
				}
				catch (DuplicateUidException e)
				{
					LOG.warn("guest registration failed: " + e);
				}
			}

			if (checkoutFacade.getCheckoutCart().getDeliveryAddress() == null)
			{
				final AddressData addressData = new AddressData();
				addressData.setTitle(resultData.getAddressName());
				addressData.setShippingAddress(true);
				addressData.setFirstName(resultData.getPayerFirstName());
				addressData.setLastName(resultData.getPayerLastName());
				addressData.setLine1(resultData.getLine1());
				addressData.setLine2(resultData.getLine2());
				addressData.setPostalCode(resultData.getPostalCode());
				addressData.setTown(resultData.getTown());
				addressData.setVisibleInAddressBook(false);
				addressData.setDefaultAddress(false);

				if (StringUtils.isNotBlank(resultData.getCountryIsoCode()))
				{
					final CountryData countryData = new CountryData();
					countryData.setIsocode(resultData.getCountryIsoCode());
					addressData.setCountry(countryData);
				}

				if (StringUtils.isNotBlank(resultData.getStateOrProvince()) && StringUtils.isNotBlank(resultData.getCountryIsoCode()))
				{
					final RegionData regionData = new RegionData();
					regionData.setIsocode(resultData.getCountryIsoCode() + "-" + resultData.getStateOrProvince());
					addressData.setRegion(regionData);
				}

				userFacade.addAddress(addressData);

				checkoutFacade.setDeliveryAddress(addressData);

				// choose cheapest delivery mode by default
				checkoutFacade.setCheapestDeliveryModeForCheckout();
			}
			LOG.info("PayPal payer ID: " + resultData.getPayerId());

			return REDIRECT_PREFIX + "/checkout/multi/summary/view";
		}
		else
		{
			handleErrors(resultData);
			List<String> errorCodes = ControllerUtils.getErrorCodeList(resultData);
			return REDIRECT_PREFIX + "/paypal/hop/error" + "/?decision=" + resultData.getAck() + "&reasonCodes=" + StringUtils.join(errorCodes, ',');
		}
	}

	private boolean isPayPalExpressCheckoutShortcut(final HttpServletRequest request)
	{
		return BooleanUtils.toBoolean(request.getParameter(PaypaladdonControllerConstants.PAY_PAL_EXPRESS_CHECKOUT_SHORTCUT_PARAM));
	}

	private void handleErrors(AbstractResultData resultData)
	{
		Map<String, String> errorCodeToMsgMap = ControllerUtils.getErrorsCodeToMessageMap(resultData);
		getSessionService().setAttribute(PaypaladdonControllerConstants.PAY_PAL_ERRORS_DETAILS, errorCodeToMsgMap);
		for (String code: errorCodeToMsgMap.keySet())
		{
			LOG.error(code);
			LOG.error(errorCodeToMsgMap.get(code));
		}
	}
}