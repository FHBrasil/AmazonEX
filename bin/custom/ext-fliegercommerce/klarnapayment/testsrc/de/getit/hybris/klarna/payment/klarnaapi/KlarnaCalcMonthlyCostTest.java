/**
 * (c) copyright 2013 by getit GmbH
 *
 * @author thomas.pflug, getit GmbH
 */
package de.getit.hybris.klarna.payment.klarnaapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.klarna.api.Klarna;
import com.klarna.api.KlarnaCalc;
import com.klarna.api.KlarnaException;
import com.klarna.api.KlarnaPClass;
import com.klarna.api.flags.Page;

/**
 * KlarnaCalcMonthlyCost<br/>
 * Technical specification: KLARNA-5 Implement Unit-Test for Klarna API
 * 
 * 
 * <JUnit test for klarna api call calcMonthlyCost(). This class contains a
 * JUnit Test for every supported country. The monthly cost will be tested for
 * product page and for the checkout-page.
 * 
 * @author thomas.pflug, getit GmbH
 */
@RunWith(value = Parameterized.class)
public class KlarnaCalcMonthlyCostTest extends
		AbstractKlarnaConfigurationProvider {

	static public Integer PCLASS_ID_DE = 3113;
	static public Integer PCLASS_ID_SE = 100;
	static public Integer PCLASS_ID_NL = 3114;
	static public Integer PCLASS_ID_FI = 3093;
	static public Integer PCLASS_ID_NO = 3100;
	static public Integer PCLASS_ID_DK = 3107;

	final private static HashMap<String, Double> calcMonthlyCostProductMap = new HashMap<String, Double>();
	final private static HashMap<String, Double> calcMonthlyCostCheckoutMap = new HashMap<String, Double>();
	final private static HashMap<String, Integer> pclassesMap = new HashMap<String, Integer>();
	private final String countryCode;

	static {

		pclassesMap.put("DE", PCLASS_ID_DE);
		pclassesMap.put("SE", PCLASS_ID_SE);
		pclassesMap.put("NL", PCLASS_ID_NL);
		pclassesMap.put("FI", PCLASS_ID_FI);
		pclassesMap.put("NO", PCLASS_ID_NO);
		pclassesMap.put("DK", PCLASS_ID_DK);

		calcMonthlyCostProductMap.put("DE", 8.1d);
		calcMonthlyCostProductMap.put("SE", 9.0d);
		calcMonthlyCostProductMap.put("NL", 8.1d);
		calcMonthlyCostProductMap.put("FI", 6.2d); // TODO vs. old 8.7
		calcMonthlyCostProductMap.put("NO", 9.0d);
		calcMonthlyCostProductMap.put("DK", 9.0d);

		calcMonthlyCostCheckoutMap.put("DE", 10.0d);
		calcMonthlyCostCheckoutMap.put("SE", 50.d);
		calcMonthlyCostCheckoutMap.put("NL", 8.0d); // TODO vs. old 10.0
		calcMonthlyCostCheckoutMap.put("FI", 9.d); // TODO vs. old 12.0
		calcMonthlyCostCheckoutMap.put("NO", 95.d);
		calcMonthlyCostCheckoutMap.put("DK", 89.0d);
	}

	public KlarnaCalcMonthlyCostTest(final String countryCode) {
		this.countryCode = countryCode;
	}

	@Parameters
	public static Collection<Object[]> data() {
		final Object[][] data = new Object[][] { { "SE" }, { "DE" }, { "DK" },
				{ "NL" }, { "FI" }, { "NO" } };
		return Arrays.asList(data);
	}

	/**
	 * Test calculate monthly cost for product detail page and checkout page.
	 * 
	 */
	@Test
	public void getMonthlyCost() {
		try {
			final double sum = 149.99;

			// calculate monthly cost for product
			KlarnaPClass pclass = getCheapestPClassForProduct(countryCode, sum);
			double value = 0.0;
			if (pclass == null) {
				assertNotNull("getCheapestPClass returned null - test failed",
						pclass);
			} else {
				value = KlarnaCalc.calcMonthlyCost(sum, pclass,
						Page.PRODUCT_PAGE);
			}

			assertEquals(
					"Expected PClass ID for country '"
							+ countryCode
							+ "' is '"
							+ pclassesMap.get(countryCode)
							+ "'. Current ID does not match expected PClass ID! pclass: '"
							+ pclass + "'", pclassesMap.get(countryCode),
					pclass.getId(), 0);
			assertEquals("country '" + countryCode + "' failed! pclass='"
					+ pclass + "', sum: " + sum + ", page: "
					+ Page.PRODUCT_PAGE,
					calcMonthlyCostProductMap.get(countryCode), value, 0);

			// calculate monthly cost for cart summary page
			pclass = getCheapestPClassForCheckout(countryCode, sum);
			if (pclass == null) {
				assertNotNull("getCheapestPClass returned null - test failed",
						pclass);
			} else {
				value = KlarnaCalc.calcMonthlyCost(sum, pclass,
						Page.CHECKOUT_PAGE);
			}

			assertEquals("country '" + countryCode + "' failed! pclass='"
					+ pclass + "'", pclassesMap.get(countryCode),
					pclass.getId(), 0);
			assertEquals("country '" + countryCode + "' failed!",
					calcMonthlyCostCheckoutMap.get(countryCode), value, 0);
		} catch (final KlarnaException e) {
			fail("getMonthlyCost() failed (" + countryCode + ") \nErrorcode: "
					+ e.getCode() + "\nErrormessage: " + e.getMessage());
		}
	}

	/**
	 * 
	 * getCheepestPClassForProduct<br>
	 * returns the cheepes pClass for viewing on the product page.
	 * 
	 * @param country
	 *            (String - Country ISOCODE)
	 * @param sum
	 *            (double - product price)
	 * @return KlarnaPClass (cheapest PClass)
	 */
	private KlarnaPClass getCheapestPClassForProduct(final String country,
			final double sum) throws KlarnaException {
		final Klarna klarna = createKlarnaConfig(countryCode);
		klarna.clearPClasses();
		klarna.fetchPClasses(klarna.getCountry(), klarna.getLanguage(),
				klarna.getCurrency());
		assertNotNull("Klarna configuration-object is null - test failed ",
				klarna);
		return klarna.getCheapestPClass(sum, Page.PRODUCT_PAGE);
	}

	/**
	 * 
	 * getCheapestPClassForCheckout<br>
	 * returns the cheepes pClass for viewing on the checkout page.
	 * 
	 * @param country
	 *            (String - Country ISOCODE)
	 * @param sum
	 *            (double - cart value)
	 * @return KlarnaPClass (cheapest PClass)
	 */
	private KlarnaPClass getCheapestPClassForCheckout(final String country,
			final double sum) throws KlarnaException {
		final Klarna klarna = createKlarnaConfig(countryCode);
		klarna.clearPClasses();
		klarna.fetchPClasses(klarna.getCountry(), klarna.getLanguage(),
				klarna.getCurrency());
		assertNotNull("Klarna configuration-object is null - test failed ",
				klarna);
		return klarna.getCheapestPClass(sum, Page.CHECKOUT_PAGE);
	}
}
