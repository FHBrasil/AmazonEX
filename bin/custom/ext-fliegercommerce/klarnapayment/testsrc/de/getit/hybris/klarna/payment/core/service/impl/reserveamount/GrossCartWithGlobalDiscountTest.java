/**
 * (c) copyright 2013 by getit GmbH
 *
 * @author Sonja Bouwers, getit GmbH
 */
package de.getit.hybris.klarna.payment.core.service.impl.reserveamount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.exceptions.CalculationException;

import org.apache.log4j.Logger;
import org.junit.Ignore;

import de.getit.hybris.klarna.payment.core.service.impl.ParameterizedGetitKlarnaReservationServiceTest;

/**
 * Creates and runs test for standard gross calculated cart objects with two standard cart entries and two global
 * discounts on cart, one relative percentage discount and one absolute discount. <br>
 * 
 * <br>
 * This test runs per every allowed Basestore+Country - PaymentMode - User combination defined in
 * {@link ParameterizedGetitKlarnaReservationServiceTest}.
 * 
 * @author Sonja Bouwers, getit GmbH
 */
// Technical specification: KLARNA-81, KLARNA-73
@Ignore
public class GrossCartWithGlobalDiscountTest extends AbstractReserveAmountTest {

	private static final Logger LOG = Logger.getLogger(GrossCartWithGlobalDiscountTest.class);

	@Override
	public CartModel createCart(final ReserveAmountParams pReserveAmountParams) throws CalculationException {
		final CartModel cart = createEmptyCart(pReserveAmountParams);
		cart.setNet(false);
		mModelService.saveAll(cart);
		mCartService.setSessionCart(cart);

		// add two standard products to cart
		addProductsCart(pReserveAmountParams,
			false, // no absolute discount to every entry
			false, // no percentage discount to every entry
			false, // no totally free product (as new entry)
			false, // no 100 percent discount to first entry
			false, // no two equal discounts types (two absolute or relative discount per entry)
			false, true); // no new entry for product with a different taxValue

		// first add a global percentage Discount to cart
		addGlobalDiscount(pReserveAmountParams, false);
		// second add a global absolute Discount to cart
		addGlobalDiscount(pReserveAmountParams, true);

		assertTrue("cart.getTotalPrice() != 0.0 ", 0.0 != cart.getTotalPrice().doubleValue());
		assertTrue("cart.getTotalTax() != 0.0", 0.0 != cart.getTotalTax().doubleValue());
		assertFalse("cart.getTotalTaxValues().isEmpty() ", cart.getTotalTaxValues().isEmpty());
		// check if 2 cart entries
		assertEquals("cart.getEntries().size() = ", 2, cart.getEntries().size());
		// check global Discounts
		assertFalse("cart.getGlobalDiscountValues().isEmpty() ", cart.getGlobalDiscountValues().isEmpty());
		assertEquals("cart.getGlobalDiscountValues().size() = ", 2, cart.getGlobalDiscountValues().size());

		logCartInfos();

		// attributes for csv file logging see CSV_HEADER_GLOBALDISCOUNT_CART_DATA
		appendToCurrentCSVLogFileLine(cart.getGlobalDiscountValues().get(0).getCode());
		appendToCurrentCSVLogFileLine(new Double(cart.getGlobalDiscountValues().get(0).getAppliedValue()));
		appendToCurrentCSVLogFileLine(cart.getGlobalDiscountValues().get(1).getCode());
		appendToCurrentCSVLogFileLine(new Double(cart.getGlobalDiscountValues().get(1).getAppliedValue()));

		return cart;
	}

	@Override
	public void createCSVFileHeader() {
		appendToCSVLogFileHeader(CSV_HEADER_COMMON_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ENTRY01_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ENTRY02_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_CART_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_GLOBALDISCOUNT_CART_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ORDER_DATA);
	}

	@Override
	public Logger getLogger() {
		return LOG;
	}
}
