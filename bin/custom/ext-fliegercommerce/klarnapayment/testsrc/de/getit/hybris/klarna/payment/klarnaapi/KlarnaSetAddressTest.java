/**
 * (c) copyright 2013 by getit GmbH
 *
 * @author Sonja Bouwers, getit GmbH
 */
package de.getit.hybris.klarna.payment.klarnaapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.klarna.api.Klarna;
import com.klarna.api.KlarnaAddr;
import com.klarna.api.KlarnaException;
import com.klarna.api.flags.Address;

/**
 * JUnit test class for testing all variants of method Klarna.setAddress()
 * 
 * Technical specification: KLARNA-5 Implement Unit-Test for Klarna API
 * 
 * see https://integration.klarna.com/de/api/advanced-integration/functions/setaddress
 * 
 * Purpose: setAddress is used to create addresses (i.e. the addr argument to the addTransaction function).
 * 
 * Return value: The argument addr for the addTransaction, reserveAmount and activateReservation function or throws an
 * exception with error code and error message.
 * 
 * @author Sonja Bouwers, getit GmbH
 */
@RunWith(value = Parameterized.class)
public class KlarnaSetAddressTest extends AbstractKlarnaConfigurationProvider {

	private final String countryCode;
	private final boolean isApprovedAddr;
	private static HashMap<String, String> checkCityMap = new HashMap<String, String>();
	static {
		// check city of KlarnaAddr
		checkCityMap.put("AT", "Hausmannstätten");
		checkCityMap.put("DE", "Neuss");
		checkCityMap.put("SE", "Ankeborg");
		checkCityMap.put("NL", "Gravenhage");
		checkCityMap.put("FI", "Pori");
		checkCityMap.put("NO", "Oslo");
		checkCityMap.put("DK", "Varde");
	}

	public KlarnaSetAddressTest(final String countryCode, final boolean isApprovedAddr) {
		this.countryCode = countryCode;
		this.isApprovedAddr = isApprovedAddr;
	}

	/**
	 * Parameterize<br/>
	 * Calls the JUnit-Test setAddress() for approved and denied addresses for all countries.
	 * 
	 * @return Collection<Object[]>
	 */
	@Parameters
	public static Collection<Object[]> generateTestData() {
		final Object[][] data = new Object[][] { { "SE", true }, { "SE", false }, { "DE", true }, { "DE", false }, { "AT", true }, { "AT", false }, { "DK", true }, { "DK", false }, { "NL", true },
			{ "NL", false }, { "FI", true }, { "FI", false }, { "NO", true }, { "NO", false } };
		return Arrays.asList(data);
	}

	/**
	 * For every run (per country and per approved/denied address), the address is set as Billing-Address and
	 * Shipping-Address.
	 * 
	 */
	@Test
	public void setAddress() {

		final Klarna klarna = createKlarnaConfig(countryCode);

		final KlarnaAddr klarnaAddr = KlarnaFactory.getKlarnaAddr(countryCode, isApprovedAddr);
		assertEquals(checkCityMap.get(countryCode), klarnaAddr.getCity());

		boolean klarnaExceptionThrown = false;

		try {
			klarna.setAddress(Address.IS_BILLING, klarnaAddr);
		} catch (final KlarnaException e) {
			fail("setAddress() failed - Could not set IS_BILLING address for " + countryCode + " and approved = " + isApprovedAddr + ". Errorcode = " + e.getCode() + " ErrorMessage = "
				+ e.getMessage());
		}

		try {
			klarna.setAddress(Address.IS_SHIPPING, klarnaAddr);
		} catch (final KlarnaException e) {
			fail("setAddress() failed - Could not set IS_SHIPPING address for " + countryCode + " and approved = " + isApprovedAddr + ". Errorcode = " + e.getCode() + " ErrorMessage = "
				+ e.getMessage());
		}

		try {
			klarna.setAddress(Address.GA_LAST, klarnaAddr);
		} catch (final KlarnaException e) {
			klarnaExceptionThrown = true;
		}
		assertTrue("It is not possible to set a Address.GA_LAST type for address for " + countryCode, klarnaExceptionThrown);

	}
}
