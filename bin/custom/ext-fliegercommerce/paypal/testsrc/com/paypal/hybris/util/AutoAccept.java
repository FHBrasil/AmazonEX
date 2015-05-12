/**
 * 
 */
package com.paypal.hybris.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.paypal.hybris.config.PaypalConfigManager;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.parser.XmlParser;

/**
 * @author Valentyn Markovych, Christina Romashchenko Gorilla
 * 
 */
public class AutoAccept {

	private final Logger LOG = Logger.getLogger(AutoAccept.class);

	private final HttpClient httpclient = new HttpClient();

	@Resource
	private PaypalConfigManager paypalConfigManager;
	
	private final String email = paypalConfigManager
			.getProperty("test.autoaccept.email");
	private final String password = paypalConfigManager
			.getProperty("test.autoaccept.password");
	private final String login_email = paypalConfigManager
			.getProperty("test.autoaccept.loginEmail");
	private final String login_password = paypalConfigManager
			.getProperty("test.autoaccept.loginPassword");

	private String form;
	private Cookie[] cookies;
	final HttpState httpState = new HttpState();

	private String token;
	private String url1;
	private String url2;
	private String url3;
	private String url4;
	private String url5;
	private String payerId;

	public AutoAccept() {

		httpclient
				.getParams()
				.setParameter(
						paypalConfigManager
								.getProperty("test.autoaccept.httpUserAgent"),
						paypalConfigManager
								.getProperty("test.autoaccept.webBrowser"));// setConnectionTimeout(30000);
		httpclient.getParams().setCookiePolicy(
				CookiePolicy.BROWSER_COMPATIBILITY);
	}

	public boolean autoAccept(final String _token) throws HttpException,
			IOException, ParserConfigurationException, SAXException {

		boolean result = false;
		token = _token;
		LOG.info("Request to PayPal... ");
		LOG.info("token = " + token);
		final boolean s1 = step1();
		final boolean s2 = step2();
		final boolean s3 = step3();
		final boolean s4 = step4();
		final boolean s5 = step5();
		if (s1 && s2 && s3 && s4 && s5) {
			result = true;
		}
		;
		LOG.info("Request to PayPal has been accepted "
				+ (result ? "succesfully!" : "not succesfully!"));
		return result;
	}

	public String getPayerId() {

		return payerId;
	}

	private boolean step1() throws HttpException, IOException {

		boolean result = false;
		url1 = paypalConfigManager
				.getProperty("test.autoaccept.expressCheckoutUrl")
				+ "&token="
				+ token;

		try {
			final String responseForm = makeGetRequest(url1);
			form = StringEscapeUtils.unescapeHtml(XmlParser.findFormFromXML(
					responseForm, paypalConfigManager
							.getProperty("test.autoaccept.loginForm")));
			if (!(responseForm == null || form == null)) {
				result = true;
			} else {
				throw new IOException("Receiving of login form has been failed");
			}
		} finally {

			LOG.info("Step 1: method Get --> Status: Status: "
					+ (result ? "OK " : "FAILED"));
		}

		return result;
	}

	private boolean step2() throws IOException, ParserConfigurationException,
			SAXException {

		boolean result = false;

		try {
			url2 = XmlParser.getInfFromField(form, paypalConfigManager
					.getProperty("test.autoaccept.actionField"));

			final Map<String, String> map = fillFormInMap(form);
			map.put(paypalConfigManager
					.getProperty("test.autoaccept.loginField"),
					paypalConfigManager
							.getProperty("test.autoaccept.loginValue"));
			if (map.containsKey(login_email) && map.containsKey(login_password)) {
				map.put(login_email, email);
				map.put(login_password, password);
			} else {
				LOG.info("Form has been parsed incorrect!");
				throw new SAXException(
						"LOGIN_PASSWORD or LOGIN_EMAIL fields haven't been found");
			}
			url3 = makePostRequest(cookies, mapToNVP(map), url2, url1);
			if (url3 == null) {
				throw new IOException("Sending of fields has been failed");
			} else {
				result = true;
			}
		} finally {

			LOG.info("Step 2: method Post --> Status: Status: "
					+ (result ? "OK " : "FAILED"));
		}
		return result;
	}

	private boolean step3() throws HttpException, IOException {

		boolean result = false;

		try {
			final String responseForm = makeGetRequest(url3);
			form = StringEscapeUtils.unescapeHtml(XmlParser.findFormFromXML(
					responseForm, paypalConfigManager
							.getProperty("test.autoaccept.reviewForm")));
			if (!(responseForm == null || form == null)) {
				result = true;
			} else {
				throw new IOException(
						"Receiving of review form has been failed");
			}
		} finally {

			LOG.info("Step 3: method Get --> Status: Status:"
					+ (result ? "OK " : "FAILED"));
		}

		return result;
	}

	private boolean step4() throws IOException, ParserConfigurationException,
			SAXException {

		boolean result = false;

		try {
			url4 = XmlParser.getInfFromField(form, paypalConfigManager
					.getProperty("test.autoaccept.actionField"));

			final Map<String, String> map = fillFormInMap(form);
			map.put(paypalConfigManager
					.getProperty("test.autoaccept.continueField"),
					paypalConfigManager
							.getProperty("test.autoaccept.continueValue"));

			url5 = makePostRequest(cookies, mapToNVP(map), url4, url3);
			payerId = XmlParser.getParametrFromUrl(url5, "PayerID");

			if (url5 == null) {
				throw new IOException(
						"Sending of continue form has been failed");
			} else {
				result = true;
			}
		} finally {

			LOG.info("Step 4: method Post --> Status:"
					+ (result ? "OK " : "FAILED"));
		}
		return result;
	}

	private boolean step5() {

		return url5 != null
				&& url5.contains(paypalConfigManager
						.getProperty(PaypalConstants.TEST_RETURN_URL));
	}

	private Map<String, String> fillFormInMap(final String str) {

		final Map<String, String> map = new HashMap<String, String>();
		final List<String> inputs = XmlParser.findInputFieldsFromXML(str);
		for (int i = 0; i < inputs.size(); i++) {
			final String input = inputs.get(i);
			if (!XmlParser.getInfFromField(input, "type").contains("submit")) {
				map.put(XmlParser.getInfFromField(input, "name"),
						XmlParser.getInfFromField(input, "value"));
			}
		}
		return map;
	}

	private NameValuePair[] mapToNVP(final Map<String, String> map) {

		final NameValuePair[] nvp = new NameValuePair[map.size()];
		final Set<String> keySet = map.keySet();
		int i = 0;
		for (final String key : keySet) {
			nvp[i] = new NameValuePair(key, map.get(key));
			i++;
		}

		return nvp;
	}

	private String getUpdateCookiesString() {

		refreshCookies();
		final StringBuilder cookStr = new StringBuilder();

		for (final Cookie cookie : cookies) {
			cookStr.append(cookie.getName()).append('=')
					.append(cookie.getValue()).append(';');
		}
		final int last = cookStr.length() - 1;
		if (last >= 0) {
			cookStr.deleteCharAt(last);
		}
		return cookStr.toString();

	}

	private void refreshCookies() {

		final Cookie[] newCookies = httpclient.getState().getCookies();
		if (cookies == null) {
			cookies = newCookies;
			return;
		}
		final Map<String, String> cookieMap = new HashMap<String, String>();
		for (final Cookie c : cookies) {
			cookieMap.put(c.getName(), c.getValue());
		}
		for (final Cookie c : newCookies) {
			cookieMap.put(c.getName(), c.getValue());
		}
		cookies = new Cookie[cookieMap.size()];
		int k = 0;
		for (final String name : cookieMap.keySet()) {
			cookies[k] = new Cookie();
			cookies[k].setName(name);
			cookies[k].setValue(cookieMap.get(name));
			k++;
		}
	}

	private String makePostRequest(final Cookie[] cs, final NameValuePair[] ps,
			final String url, final String referer) throws IOException,
			ParserConfigurationException, SAXException {

		String redirectUrl = null;
		int state = 0;

		final PostMethod postMethod = new PostMethod(url);

		try {
			postMethod.addRequestHeader("Referer", paypalConfigManager
					.getProperty("test.autoaccept.refererValue"));
			postMethod.addRequestHeader("Accept", paypalConfigManager
					.getProperty("test.autoaccept.acceptValue"));
			postMethod.addRequestHeader("Accept-Encoding", paypalConfigManager
					.getProperty("test.autoaccept.acceptEncodingValue"));
			postMethod.addRequestHeader("Accept-Language", paypalConfigManager
					.getProperty("test.autoaccept.acceptLanguageValue"));
			postMethod.addRequestHeader("Connection", paypalConfigManager
					.getProperty("test.autoaccept.connectionValue"));
			postMethod.addParameters(ps);
			postMethod.addRequestHeader("Cookie", getUpdateCookiesString());

			try {
				state = httpclient.executeMethod(postMethod);

			} catch (final HttpException httpe) {
				LOG.info("HttpException");
				LOG.info(httpe.getMessage());
				httpe.printStackTrace();
			} catch (final IOException ioe) {
				LOG.info("IOException");
				LOG.info(ioe.getMessage());
				ioe.printStackTrace();
			}
			// final byte[] respBytes = postMethod.getResponseBody();
			// final String responseBody = new String(respBytes,
			// postMethod.getResponseCharSet());
			// FileUtils.writeStringToFile(new
			// File(paypalConfigManager.getProperty("test.autoaccept.fileName")),
			// responseBody);

			if (state == HttpStatus.SC_MOVED_TEMPORARILY
					|| state == HttpStatus.SC_MOVED_PERMANENTLY) {
				redirectUrl = postMethod.getResponseHeader(
						paypalConfigManager
								.getProperty("test.autoaccept.redirectUrl"))
						.getValue();
			}

		} finally {
			postMethod.releaseConnection();
		}
		return redirectUrl;
	}

	private String makeGetRequest(final String url) throws HttpException,
			IOException {

		String responseForm = null;
		final GetMethod httpget = new GetMethod(url);
		httpget.addRequestHeader("Cookie", getUpdateCookiesString());
		try {
			final int state = httpclient.executeMethod(httpget);
			if (state == HttpStatus.SC_OK) {
				final byte[] respBytes = httpget.getResponseBody();
				responseForm = new String(respBytes,
						httpget.getResponseCharSet());
				// FileUtils.writeStringToFile(new
				// File(paypalConfigManager.getProperty("test.autoaccept.fileName")),
				// responseForm);
			}
		} finally {
			httpget.releaseConnection();
		}
		return responseForm;
	}

}
