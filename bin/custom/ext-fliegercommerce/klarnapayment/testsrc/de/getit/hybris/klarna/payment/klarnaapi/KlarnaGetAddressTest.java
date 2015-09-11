/**
 * (c) copyright 2013 by getit GmbH
 *
 * @author gerald.bornemann, getit GmbH
 */
package de.getit.hybris.klarna.payment.klarnaapi;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.klarna.api.Klarna;
import com.klarna.api.KlarnaAddr;
import com.klarna.api.KlarnaException;

/**
 * 
 * Calls the JUnit-Test for klarna api call 'fetchPClasses' for all countries except AT
 * 
 * Technical specification: KLARNA-5 Implement Unit-Test for Klarna API
 * 
 * @author gerald.bornemann, getit GmbH
 */
public class KlarnaGetAddressTest extends AbstractKlarnaConfigurationProvider {

	@Test
	public void getAddressSEOK()
		throws KlarnaException {

		final Klarna klarna = createKlarnaConfig("SE");

		// Attempts to get the address(es) associated with the SSN/PNO.
		final List<KlarnaAddr> addrs = klarna.getAddresses("410321-9202");
		assertEquals(addrs.size(), 1);

		final KlarnaAddr addr = addrs.get(0);
		assertEquals(addr.getFirstName(), "Testperson-se");
		assertEquals(addr.getLastName(), "Approved");
	}

	@Test(expected = KlarnaException.class)
	// Wrong PNO
	public void getAddressSEEX()
		throws KlarnaException {

		final Klarna klarna = createKlarnaConfig("SE");

		// Attempts to get the address(es) associated with the SSN/PNO.
		klarna.getAddresses("19410321-9201");
	}

	@Test(expected = KlarnaException.class)
	// Wrong Country
	public void getAddressDEEX()
		throws KlarnaException {

		final Klarna klarna = createKlarnaConfig("DE");

		// Attempts to get the address(es) associated with the SSN/PNO.
		klarna.getAddresses("19410321-9202");
	}
}
