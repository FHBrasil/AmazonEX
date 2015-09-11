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
 * Creates and runs test for standard gross calculated cart objects with two standard cart entries. Sets a deliveryMode
 * with deliverycosts to the cart and adds a third cart entry with a different taxValue.<br>
 * 
 * <br>
 * This test runs per every allowed Basestore+Country - PaymentMode - User combination defined in
 * {@link ParameterizedGetitKlarnaReservationServiceTest}.
 * 
 * @author Sonja Bouwers, getit GmbH
 */
// Technical specification: KLARNA-16, KLARNA-105, KLARNA-106
@Ignore
public class StandardGrossCartWithDeliveryCostsAndDiffTaxValuesTest extends AbstractReserveAmountTest {

	private static final Logger LOG = Logger.getLogger(StandardGrossCartWithDeliveryCostsAndDiffTaxValuesTest.class);

	@Override
	public CartModel createCart(final ReserveAmountParams pReserveAmountParams) throws CalculationException {
		final CartModel cart = createEmptyCart(pReserveAmountParams);
		cart.setNet(false);
		mModelService.saveAll(cart);
		mCartService.setSessionCart(cart);

		// first set DeliveryMode with costs to cart
		addDeliveryModeToCart(pReserveAmountParams);

		// add a third product to the cart with a different taxValue
		addProductsCart(pReserveAmountParams, //
			false, // no absolute discount to every entry
			false, // no percentage discount to every entry
			false, // no totally free product (as new entry)
			false, // no 100 percent discount to first entry
			false, // no two equal discounts types (two absolute or relative discount per entry)
			true, true); // yes, with new entry for product with a different taxValue

		assertTrue("cart.getTotalPrice() != 0.0 ", 0.0 != cart.getTotalPrice().doubleValue());
		assertTrue("cart.getTotalTax() != 0.0", 0.0 != cart.getTotalTax().doubleValue());
		assertFalse("cart.getTotalTaxValues().isEmpty() ", cart.getTotalTaxValues().isEmpty());
		// check if 3 cart entries
		assertEquals("cart.getEntries().size() = ", 3, cart.getEntries().size());
		// check if deliveryCosts != 0.0d
		assertTrue("cart.getDeliveryCost() != 0.0 ", 0.0 != cart.getDeliveryCost().doubleValue());

		logCartInfos();

		return cart;
	}

	@Override
	public void createCSVFileHeader() {
		appendToCSVLogFileHeader(CSV_HEADER_COMMON_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ENTRY01_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ENTRY02_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ENTRY03_DATA); // third entry with different taxValue
		appendToCSVLogFileHeader(CSV_HEADER_CART_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ORDER_DATA);

	}

	@Override
	public Logger getLogger() {
		return LOG;
	}

}
