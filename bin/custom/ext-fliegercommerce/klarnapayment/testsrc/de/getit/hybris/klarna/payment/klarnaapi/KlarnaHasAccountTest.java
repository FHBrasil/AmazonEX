/**
 * (c) copyright 2013 by getit GmbH
 *
 * @author gerald.bornemann, getit GmbH
 */
package de.getit.hybris.klarna.payment.klarnaapi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.klarna.api.Klarna;
import com.klarna.api.KlarnaException;

/**
 * 
 * Calls the JUnit-Test for klarna api call hasAccount' This api call is valid only for Sweden at this time.
 * 
 * Technical specification: KLARNA-5 Implement Unit-Test for Klarna API
 * 
 * @author gerald.bornemann, getit GmbH
 */
public class KlarnaHasAccountTest extends AbstractKlarnaConfigurationProvider {

	@Test(expected = KlarnaException.class)
	// Wrong Country
	public void hasAccountDEEX()
		throws KlarnaException {

		hasAccountEX("DE", "1234");
	}

	@Test(expected = KlarnaException.class)
	// Wrong Country
	public void hasAccountATEX()
		throws KlarnaException {

		hasAccountEX("AT", "1234");
	}

	@Test(expected = KlarnaException.class)
	// Wrong Country
	public void hasAccountNLEX()
		throws KlarnaException {

		hasAccountEX("NL", "1234");
	}

	@Test
	public void hasAccountSE() {

		try {
			hasAccount("SE", "19410321-9202", "19410321-9203");
		} catch (final KlarnaException e) {
			fail("hasAccountSE() failed - " + e.getCode() + " / " + e.getMessage());
		}
	}

	private void hasAccount(final String pCountryIsoCode, final String pGoodPno, final String pBadPno)
		throws KlarnaException {

		final Klarna klarna = createKlarnaConfig(pCountryIsoCode);
		assertTrue("True expected for " + pCountryIsoCode + " pGoodPno " + pGoodPno, klarna.hasAccount(pGoodPno));
		// Wrong PNO, disabled as Klarna API > 2.2 tries to  wr
		//assertFalse("False expected for " + pCountryIsoCode + " pGoodPno " + pBadPno, klarna.hasAccount(pBadPno));

	}

	private void hasAccountEX(final String pCountryIsoCode, final String pPno)
		throws KlarnaException {

		final Klarna klarna = createKlarnaConfig(pCountryIsoCode);
		assertTrue("Exception expected for " + pCountryIsoCode + " and " + pPno, klarna.hasAccount(pPno));
	}

	/*
	 * Die anderen nordischen Länder liefern trotz der Sozialversicherungsnummer aus den Testdaten 'false' zurück
	 */
}
