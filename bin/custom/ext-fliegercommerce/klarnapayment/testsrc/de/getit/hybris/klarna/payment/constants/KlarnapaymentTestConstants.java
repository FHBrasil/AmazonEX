/**
 * (c) copyright 2013 by getit GmbH
 *
 * @author Sonja Bouwers, getit GmbH
 */
package de.getit.hybris.klarna.payment.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Constants for JUnit Tests.
 * 
 * @author Sonja Bouwers, getit GmbH
 */
public class KlarnapaymentTestConstants {

	public static final String CLIENT_IP = "192.0" + ".2.1";

	public static final class STORE {
		public static final String DE = "klarna-de";
		public static final String AT = "klarna-at";
		public static final String DK = "klarna-dk";
		public static final String FI = "klarna-fi";
		public static final String NL = "klarna-nl";
		public static final String NO = "klarna-no";
		public static final String SE = "klarna-se";
	}

	public static final class COUNTRY {
		public static final String DE = "DE";
		public static final String AT = "AT";
		public static final String DK = "DK";
		public static final String FI = "FI";
		public static final String NL = "NL";
		public static final String NO = "NO";
		public static final String SE = "SE";
	}

	public static final class USER {

		// KlarnaPaymentTestUser_DE_Approved@test.com
		// KlarnaPaymentTestUser_DE_Denied@test.com
		// KlarnaPaymentTestUser_AT_Approved@test.com";
		// usw.

		public static final String APPROVED = "KlarnaPaymentTestUser_{0}_Approved@test.com";
		public static final String DENIED = "KlarnaPaymentTestUser_{0}_Denied@test.com";

	}

	private static final Map<String, String> taxConstants = new HashMap<String, String>();
	static {
		taxConstants.put("DE", "19_percent");
		taxConstants.put("AT", "20_percent");
		taxConstants.put("NL", "21_percent");
		taxConstants.put("DK", "25_percent");
		taxConstants.put("FI", "23_percent");
		taxConstants.put("NO", "25_percent");
		taxConstants.put("SE", "25_percent");
		taxConstants.put("DIFF", "7_percent");
	}

	public static String getTaxCodeForCountry(final String countryIsoCode) {
		return taxConstants.get(countryIsoCode.toUpperCase());
	}

	public static final class DISCOUNT {
		public static final String TOTALLY_FREE = "totally_free";
		public static final String TEN_PERCENT = "ten_percent";
		public static final String TWENTY_PERCENT = "twenty_percent";
		public static final String FIFTY_PERCENT = "fifty_percent";
		public static final String THREE_OFF = "3_off";
		public static final String FOUR_AHALF_OFF = "4_ahalf_off";
		public static final String TEN_OFF = "10_off";
		public static final String GLOBAL_5_OFF = "global_5_off";
		public static final String GLOBAL_THIRTY_PERCENT = "global_thirty_percent";

	}
}
