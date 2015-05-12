/**
 * 
 */
package com.paypal.hybris.util;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.paypal.hybris.constants.PaypalConstants;


/**
 * @author Valentyn Markovych, Gorilla
 * 
 */
@Component
public class PaypalAcceleratorHelpers {


	/**
	 * @param req
	 * @return true of request contains PayPal flow cookie
	 */
	public boolean hasPaypalCookie(final HttpServletRequest req) {

		if (getCookie(req) == null) {
			return false;
		} else {
			return true;
		}
	}

	private Cookie getCookie(final HttpServletRequest req) {

		final Cookie[] cookies = req.getCookies();
		for (int i = cookies.length - 1; i >= 0; --i) {
			if (cookies[i].getName().equalsIgnoreCase(
					PaypalConstants.PAYPAL_FLOW_COOKIE_NAME)) {
				return cookies[i];
			}
		}

		return null;
	}

	/**
	 * @param resp
	 * 
	 */
	public void resetPaypalCookie(final HttpServletResponse resp) {

		final Cookie paypalCookie = new Cookie(
				PaypalConstants.PAYPAL_FLOW_COOKIE_NAME, null);
		paypalCookie.setPath("/");
		paypalCookie.setMaxAge(0);

		resp.addCookie(paypalCookie);
	}

	private Cookie getButtonCookie(final HttpServletRequest req) {

		final Cookie[] cookies = req.getCookies();
		for (int i = cookies.length - 1; i >= 0; --i) {
			if (cookies[i].getName().equalsIgnoreCase(
					PaypalConstants.PAYPAL_HIDE_PAYPAL_BUTTON_COOKIE_NAME)) {
				return cookies[i];
			}
		}

		return null;
	}

	public void resetPaypalButtonCookie(final HttpServletResponse resp) {

		final Cookie paypalCookie = new Cookie(
				PaypalConstants.PAYPAL_HIDE_PAYPAL_BUTTON_COOKIE_NAME, null);
		paypalCookie.setPath("/");
		paypalCookie.setMaxAge(0);

		resp.addCookie(paypalCookie);
	}

	public boolean hasPaypalButtonCookie(final HttpServletRequest req) {

		if (getButtonCookie(req) == null) {
			return false;
		} else {
			return true;
		}
	}


}
