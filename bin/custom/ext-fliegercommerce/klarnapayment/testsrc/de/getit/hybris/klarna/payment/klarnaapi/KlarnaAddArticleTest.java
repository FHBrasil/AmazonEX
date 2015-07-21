/**
 * (c) copyright 2013 by getit GmbH
 *
 * @author Sonja Bouwers, getit GmbH
 */
package de.getit.hybris.klarna.payment.klarnaapi;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.klarna.api.Klarna;

/**
 * JUnit test class for testing all variants of method Klarna.addArticle()
 * 
 * Technical specification: KLARNA-5 Implement Unit-Test for Klarna API
 * 
 * see https://integration.klarna.com/de/api/advanced-integration/functions/addarticle
 * 
 * Purpose: Used to create an item for the goodsList.
 * 
 * Return value: An array containing information about the specified item. Used in the goodsList array for the
 * addTransaction, reserveAmount and activateReservation function or throws an exception with error code and error
 * message.
 * 
 * @author Sonja Bouwers, getit GmbH
 */
@RunWith(value = Parameterized.class)
public class KlarnaAddArticleTest extends AbstractKlarnaAddArticleTest {

	private final String countryCode;

	public KlarnaAddArticleTest(final String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * Parameterize<br/>
	 * Calls the JUnit-Test addArticle() for all countries.
	 * 
	 * @return Collection<Object[]>
	 */
	@Parameters
	public static Collection<Object[]> generateTestData() {
		final Object[][] data = new Object[][] { { "SE" }, { "DE" }, { "AT" }, { "DK" }, { "NL" }, { "FI" }, { "NO" } };
		return Arrays.asList(data);
	}

	/**
	 * Test methode - adds the five configured test articles, adds an shipping fee article and adds an handling fee
	 * article.
	 * 
	 */
	@Test
	public void addArticle() {

		final Klarna klarna = createKlarnaConfig(countryCode);
		// add configured article "01"
		addKlarnaArticleIncVat_01(klarna);
		// add configured article "02"
		addKlarnaArticleIncVat_02(klarna);
		addKlarnaArticleIncVat_03(klarna);
		addKlarnaArticleIncVat_04(klarna);
		addKlarnaArticleIncVat_05(klarna);

		addShippingFeeArticle(klarna);
		addHandlingFeeArticle(klarna);

	}

}
