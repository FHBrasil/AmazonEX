/**
 * (c) copyright 2013 by getit GmbH
 *
 * @author gerald.bornemann, getit GmbH
 */
package de.getit.hybris.klarna.payment.klarnaapi;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.klarna.api.Klarna;
import com.klarna.api.KlarnaException;
import com.klarna.api.KlarnaPClass;
import com.klarna.api.flags.PClass;

/**
 * 
 * Calls the JUnit-Test for klarna api call 'fetchPClasses' for all countries except AT
 * 
 * Technical specification: KLARNA-5 Implement Unit-Test for Klarna API
 * 
 * @author gerald.bornemann, getit GmbH
 */
@RunWith(value = Parameterized.class)
public class KlarnaFetchPClassesTest extends AbstractKlarnaConfigurationProvider {

	private final String mCountryCode;
	private final boolean mHasCampaign;

	/**
	 * 
	 * Parameterize<br/>
	 * 
	 * doesn't work for AT because of error in Testdata (PClasses of AT are equal to DE)
	 * 
	 * @return Collection<Object[]>
	 */
	@Parameters
	public static Collection<Object[]> data() {
		final Object[][] data = new Object[][] { { "SE", true }, { "DE", false }, { "DK", true }, { "NL", false }, { "FI", true }, { "NO", true } };
		return Arrays.asList(data);
	}

	public KlarnaFetchPClassesTest(final String pCountryCode, final boolean pHasCampaign) {
		mCountryCode = pCountryCode;
		mHasCampaign = pHasCampaign;
	}

	@Test
	public void fetchPClasses()
		throws KlarnaException {

		checkAllClasses(mCountryCode);
		checkAccount(mCountryCode);
		checkCampaign(mCountryCode, mHasCampaign);
		checkSpecial(mCountryCode);
	}

	private void checkAllClasses(final String pCountryCode)
		throws KlarnaException {

		final Klarna klarna = createKlarnaConfig(pCountryCode);
		klarna.clearPClasses();

		klarna.fetchPClasses(klarna.getCountry(), klarna.getLanguage(), klarna.getCurrency());
		final Map<Integer, List<KlarnaPClass>> allPClasses = klarna.getAllPClasses();
		assertEquals(allPClasses.size(), 1);
	}

	private void checkAccount(final String pCountryCode)
		throws KlarnaException {

		final Klarna klarna = createKlarnaConfig(pCountryCode);

		final List<KlarnaPClass> pClasses = klarna.getPClasses(PClass.ACCOUNT);
		assertEquals(pClasses.size(), 1);
		final KlarnaPClass pClass = pClasses.get(0);
		assertEquals(pClass.getMonths(), 24);
	}

	private void checkCampaign(final String pCountryCode, final boolean pHasCampaign)
		throws KlarnaException {

		final Klarna klarna = createKlarnaConfig(pCountryCode);

		final List<KlarnaPClass> pClasses = klarna.getPClasses(PClass.CAMPAIGN);
		if (pHasCampaign) {
			assertEquals(pClasses.size(), 1);
			final KlarnaPClass pClass = pClasses.get(0);
			assertEquals(pClass.getMonths(), 3);
		} else {
			assertEquals(pClasses.size(), 0);
		}
	}

	private void checkSpecial(final String pCountryCode)
		throws KlarnaException {

		final Klarna klarna = createKlarnaConfig(pCountryCode);

		final List<KlarnaPClass> pClasses = klarna.getPClasses(PClass.SPECIAL);
		assertEquals(pClasses.size(), 0);
	}
}
