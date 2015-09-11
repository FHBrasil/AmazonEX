/**
 * (c) copyright 2013 by getit GmbH
 *
 * @author gerald.bornemann, getit GmbH
 */
package de.getit.hybris.klarna.payment.klarnaapi;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.klarna.api.Klarna;
import com.klarna.api.KlarnaException;

public class KlarnaCheckOrderStatusTest extends AbstractKlarnaConfigurationProvider {

	@Test
	public void checkOrderStatusSEEX() {
		checkOrderStatustEX("SE", "1234");
	}

	private void checkOrderStatustEX(final String pCountryIsoCode, final String pReservationNo) {

		try {
			final Klarna klarna = createKlarnaConfig(pCountryIsoCode);
			klarna.checkOrderStatus(pReservationNo);
			// assertTrue("Exception expected for " + pCountryIsoCode + " and " + pReservationNo, true);
			fail("Exception expected for " + pCountryIsoCode + " and " + pReservationNo);
		} catch (final KlarnaException e) {
			// assertTrue(true);
			System.out.println(e.getMessage());
		}
	}

}
