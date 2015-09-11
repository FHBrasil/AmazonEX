/**
 * (c) copyright 2013 by getit GmbH
 *
 * @author Sonja Bouwers, getit GmbH
 */
package de.getit.hybris.klarna.payment.core.service.impl.reserveamount;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.exceptions.CalculationException;

import org.apache.log4j.Logger;
import org.junit.Ignore;

import de.getit.hybris.klarna.payment.core.service.impl.ParameterizedGetitKlarnaReservationServiceTest;

/**
 * Creates and runs test for standard gross calculated cart objects with two standard cart entries and with two absolute
 * discounts to every cart entry. <br>
 * 
 * <br>
 * This test runs per every allowed Basestore+Country - PaymentMode - User combination defined in
 * {@link ParameterizedGetitKlarnaReservationServiceTest}.
 * 
 * @author Sonja Bouwers, getit GmbH
 */
// Technical specification: KLARNA-81, KLARNA-73, KLARNA-112
@Ignore
public class GrossCartWithTwoAbsoluteEntryDiscountsTest extends AbstractReserveAmountTest {

	private static final Logger LOG = Logger.getLogger(GrossCartWithTwoAbsoluteEntryDiscountsTest.class);

	@Override
	public CartModel createCart(final ReserveAmountParams pReserveAmountParams) throws CalculationException {
		final CartModel cart = createEmptyCart(pReserveAmountParams);
		cart.setNet(false);
		mModelService.saveAll(cart);
		mCartService.setSessionCart(cart);

		// add two absolute discounts to a cart entry
		addProductsCart(pReserveAmountParams, //
			true, // yes, with absolute discount to every entry
			false, // no percentage discount to every entry
			false, // no totally free product (as new entry)
			false, // no 100 percent discount to first entry
			true, // yes, with two equal discounts types (two absolute or relative discount per entry)
			false, false); // no new entry for product with a different taxValue

		assertTrue("cart.getTotalPrice() != 0.0 ", 0.0 != cart.getTotalPrice().doubleValue());
		assertTrue("cart.getTotalTax() != 0.0", 0.0 != cart.getTotalTax().doubleValue());
		assertFalse("cart.getTotalTaxValues().isEmpty() ", cart.getTotalTaxValues().isEmpty());

		logCartInfos();

		return cart;
	}

	@Override
	public void createCSVFileHeader() {
		appendToCSVLogFileHeader(CSV_HEADER_COMMON_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ENTRY01_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ENTRY01_DISCOUNT01_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ENTRY01_DISCOUNT02_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ENTRY02_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ENTRY02_DISCOUNT01_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ENTRY02_DISCOUNT02_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_CART_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ORDER_DATA);

	}

	@Override
	public Logger getLogger() {
		return LOG;
	}

}
