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
 * Creates and runs test for standard gross calculated cart objects with two standard cart entries and with a 100%
 * discounts to the first cart entry and third cart entry with a totally free product (price = 0.00). <br>
 * 
 * <br>
 * This test runs per every allowed Basestore+Country - PaymentMode - User combination defined in
 * {@link ParameterizedGetitKlarnaReservationServiceTest}.
 * 
 * @author Sonja Bouwers, getit GmbH
 */
// Technical specification: KLARNA-81, KLARNA-73, KLARNA-112
@Ignore
public class GrossCartWithFreeProductsTest extends AbstractReserveAmountTest {

	private static final Logger LOG = Logger.getLogger(GrossCartWithFreeProductsTest.class);

	@Override
	public CartModel createCart(final ReserveAmountParams pReserveAmountParams) throws CalculationException {
		final CartModel cart = createEmptyCart(pReserveAmountParams);
		cart.setNet(false);
		mModelService.saveAll(cart);
		mCartService.setSessionCart(cart);

		// add a third cart entry with a totally free product (price = 0.00d)
		// add a 100 % percent discount to first cart entry
		addProductsCart(pReserveAmountParams,
			false, // no absolute discount to every entry
			true, // yes, with percentage discount to every entry
			true, // yes, with totally free product (as new entry)
			true, // yes, with 100 percent discount to first entry
			false, // no two equal discounts types (two absolute or relative discount per entry)
			false, false); // no new entry for product with a different taxValue

		assertTrue("cart.getTotalPrice() != 0.0 ", 0.0 != cart.getTotalPrice().doubleValue());
		assertTrue("cart.getTotalTax() != 0.0", 0.0 != cart.getTotalTax().doubleValue());
		assertFalse("cart.getTotalTaxValues().isEmpty() ", cart.getTotalTaxValues().isEmpty());
		// check if 3 cart entries
		assertEquals("cart.getEntries().size() = ", 3, cart.getEntries().size());

		logCartInfos();

		return cart;
	}

	@Override
	public void createCSVFileHeader() {
		appendToCSVLogFileHeader(CSV_HEADER_COMMON_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ENTRY01_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ENTRY01_DISCOUNT01_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ENTRY02_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ENTRY03_DATA);// entry 03 with totally free product with price 0.00
		appendToCSVLogFileHeader(CSV_HEADER_CART_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ORDER_DATA);
	}

	@Override
	public Logger getLogger() {
		return LOG;
	}

}
