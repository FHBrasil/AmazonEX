/**
 * (c) copyright 2013 by getit GmbH
 *
 * @author Sonja Bouwers, getit GmbH
 */
package de.getit.hybris.klarna.payment.klarnaapi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;

import com.klarna.api.Klarna;
import com.klarna.api.KlarnaException;
import com.klarna.api.flags.Goods;

import de.getit.hybris.klarna.payment.constants.KlarnapaymentConstants;

/**
 * Abstract-JUnit test class for testing all variants of method Klarna.addArticle() used for AddArticleTests and
 * ReserveAmountTests.
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
@Ignore
 abstract class AbstractKlarnaAddArticleTest extends AbstractKlarnaConfigurationProvider {

	/**
	 * Adds a shipment fee.
	 * 
	 * @param klarna
	 */
	protected void addShippingFeeArticle(final Klarna klarna) {
		Map<String, Object> klarnaArticelMap = new HashMap<String, Object>();
		try {
			klarnaArticelMap = KlarnaFactory.getConfiguredKlarnaArticleForShippingFee();
			assertNotNull(klarnaArticelMap);
			assertFalse(klarnaArticelMap.isEmpty());
			// call klarna.addArticle(quantity, artNumber, title, price, vat);
			klarna.addArticle(
				(Integer) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_QUANTITY),//
				(String) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_ARTNO), //
				(String) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_TITLE), //
				(Double) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_PRICE), //
				(Double) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_VAT));
		} catch (final KlarnaException e) {
			fail("addShippingFeeArticle() failed \nErrorcode: " + e.getCode() + "\nErrormessage: " + e.getMessage());
		}
	}

	/**
	 * Adds a handling fee.
	 * 
	 * @param klarna
	 */
	protected void addHandlingFeeArticle(final Klarna klarna) {
		Map<String, Object> klarnaArticelMap = new HashMap<String, Object>();
		try {
			klarnaArticelMap = KlarnaFactory.getConfiguredKlarnaArticleForHandlingFee();
			assertNotNull(klarnaArticelMap);
			assertFalse(klarnaArticelMap.isEmpty());
			// call klarna.addArticle(quantity, artNumber, title, price, vat);
			klarna.addArticle(
				(Integer) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_QUANTITY),//
				(String) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_ARTNO), //
				(String) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_TITLE), //
				(Double) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_PRICE), //
				(Double) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_VAT));
		} catch (final KlarnaException e) {
			fail("addHandlingFeeArticle() failed \nErrorcode: " + e.getCode() + "\nErrormessage: " + e.getMessage());
		}
	}

	/**
	 * Adds the configured test article "01" incl. VAT = 19 % , quantity = 10, price = 100.0d, discount = 0 %.
	 * 
	 * @param klarna
	 */
	protected void addKlarnaArticleIncVat_01(final Klarna klarna) {
		Map<String, Object> klarnaArticelMap = new HashMap<String, Object>();
		try {
			klarnaArticelMap = KlarnaFactory.getConfiguredKlarnaArticleIncVat("01");
			assertNotNull(klarnaArticelMap);
			assertFalse(klarnaArticelMap.isEmpty());
			// call klarna.addArticle(quantity, artNumber, title, price, vat);
			klarna.addArticle(
				(Integer) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_QUANTITY),//
				(String) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_ARTNO), //
				(String) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_TITLE), //
				(Double) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_PRICE), //
				(Double) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_VAT));
		} catch (final KlarnaException e) {
			fail("addKlarnaArticleIncVat_01() failed \nErrorcode: " + e.getCode() + "\nErrormessage: " + e.getMessage());
		}
	}

	/**
	 * Adds the configured test article "02" incl. VAT = 19 % , quantity = 2, price = 222.d, discount = 20 %.
	 * 
	 * @param klarna
	 */
	protected void addKlarnaArticleIncVat_02(final Klarna klarna) {
		Map<String, Object> klarnaArticelMap = new HashMap<String, Object>();
		try {
			klarnaArticelMap = KlarnaFactory.getConfiguredKlarnaArticleIncVat("02");
			assertNotNull(klarnaArticelMap);
			assertFalse(klarnaArticelMap.isEmpty());
			// call klarna.addArticle(quantity, artNumber, title, price, vat, discount);
			klarna.addArticle(
				(Integer) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_QUANTITY),//
				(String) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_ARTNO), //
				(String) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_TITLE), //
				(Double) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_PRICE), //
				(Double) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_VAT), //
				(Double) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_DISCOUNT));
		} catch (final KlarnaException e) {
			fail("addKlarnaArticleIncVat_02() failed \nErrorcode: " + e.getCode() + "\nErrormessage: " + e.getMessage());
		}
	}

	/**
	 * Adds the configured test article "03" incl. VAT = 19 % , quantity = 30, price = 3.33d, discount = 0 %.
	 * 
	 * @param klarna
	 */
	protected void addKlarnaArticleIncVat_03(final Klarna klarna) {
		Map<String, Object> klarnaArticelMap = new HashMap<String, Object>();
		try {
			klarnaArticelMap = KlarnaFactory.getConfiguredKlarnaArticleIncVat("03");
			assertNotNull(klarnaArticelMap);
			assertFalse(klarnaArticelMap.isEmpty());
			// call klarna.addArticle(quantity, artNumber, title, price, vat, discount, flags);
			klarna.addArticle(
				(Integer) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_QUANTITY),//
				(String) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_ARTNO), //
				(String) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_TITLE), //
				(Double) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_PRICE), //
				(Double) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_VAT), //
				(Double) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_DISCOUNT),
				(EnumSet<Goods>) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_FLAGS));
		} catch (final KlarnaException e) {
			fail("addKlarnaArticleIncVat_03() failed \nErrorcode: " + e.getCode() + "\nErrormessage: " + e.getMessage());
		}
	}

	/**
	 * Adds the configured test article "04" incl. VAT = 19 % , quantity = 4, price = 44.4d, discount = 4.4 %.
	 * 
	 * @param klarna
	 */
	protected void addKlarnaArticleIncVat_04(final Klarna klarna) {
		Map<String, Object> klarnaArticelMap = new HashMap<String, Object>();
		try {
			klarnaArticelMap = KlarnaFactory.getConfiguredKlarnaArticleIncVat("04");
			assertNotNull(klarnaArticelMap);
			assertFalse(klarnaArticelMap.isEmpty());
			// klarna.addArticle(quantity, artNumber, title, price, vat, discount, flag);
			klarna.addArticle(
				(Integer) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_QUANTITY),//
				(String) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_ARTNO), //
				(String) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_TITLE), //
				(Double) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_PRICE), //
				(Double) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_VAT), //
				(Double) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_DISCOUNT),
				((EnumSet<Goods>) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_FLAGS)).iterator().next());
		} catch (final KlarnaException e) {
			fail("addKlarnaArticleIncVat_04() failed \nErrorcode: " + e.getCode() + "\nErrormessage: " + e.getMessage());
		}
	}

	/**
	 * Adds the configured test article "05" incl. VAT = 7 % , quantity = 15, price = 5.d, discount = 0 %.
	 * 
	 * @param klarna
	 */
	protected void addKlarnaArticleIncVat_05(final Klarna klarna) {
		Map<String, Object> klarnaArticelMap = new HashMap<String, Object>();
		try {
			klarnaArticelMap = KlarnaFactory.getConfiguredKlarnaArticleIncVat("05");
			assertNotNull(klarnaArticelMap);
			assertFalse(klarnaArticelMap.isEmpty());
			klarna.addArticle(
				(Integer) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_QUANTITY),//
				(String) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_ARTNO), //
				(String) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_TITLE), //
				(Double) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_PRICE), //
				(Double) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_VAT), //
				(Double) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_DISCOUNT),
				((EnumSet<Goods>) klarnaArticelMap.get(KlarnapaymentConstants.ARTICLE_FLAGS)).iterator().next().toInt());
		} catch (final KlarnaException e) {
			fail("addKlarnaArticleIncVat_05() failed \nErrorcode: " + e.getCode() + "\nErrormessage: " + e.getMessage());
		}
	}
}
