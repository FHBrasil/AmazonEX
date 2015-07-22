/**
 * (c) copyright 2013 by getit GmbH
 *
 * @author getit GmbH
 */
package de.getit.hybris.klarna.payment.core.service.impl.reserveamount;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.exceptions.CalculationException;

import org.apache.log4j.Logger;
import org.junit.Ignore;

/**
 * @author getit GmbH
 */
// Technical specification: KLARNA-106
@Ignore
public class StandardGrossCartWithDeliveryWithoutInvoiceFeeTest
	extends AbstractReserveAmountTest {

	private static final Logger LOG = Logger.getLogger(StandardGrossCartWithDeliveryWithoutInvoiceFeeTest.class);

	@Override
	public CartModel createCart(final ReserveAmountParams pReserveAmountParams) throws CalculationException {
		final CartModel cart = createEmptyCart(pReserveAmountParams);
		cart.setNet(false);
		mModelService.saveAll(cart);
		mCartService.setSessionCart(cart);

		// first set DeliveryMode with costs to cart
		addDeliveryModeToCart(pReserveAmountParams);

		// add two standard products to cart
		addProductsCart(pReserveAmountParams,
			false, // no absolute discount to every entry
			false, // no percentage discount to every entry
			false, // no totally free product (as new entry)
			false, // no 100 percent discount to first entry
			false, // no two equal discounts types (two absolute or relative discount per entry)
			false, false); // no new entry for product with a different taxValue

		assertTrue("cart.getTotalPrice() != 0.0 ", 0.0 != cart.getTotalPrice().doubleValue());
		assertTrue("cart.getTotalTax() != 0.0", 0.0 != cart.getTotalTax().doubleValue());
		assertFalse("cart.getTotalTaxValues().isEmpty() ", cart.getTotalTaxValues().isEmpty());
		assertTrue("cart.getDeliveryCost() != 0.0 ", 0.0 != cart.getDeliveryCost().doubleValue());

		logCartInfos();

		return cart;
	}

	@Override
	public void createCSVFileHeader() {
		appendToCSVLogFileHeader(CSV_HEADER_COMMON_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ENTRY01_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ENTRY02_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_CART_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ORDER_DATA);
	}

	@Override
	public Logger getLogger() {
		return LOG;
	}
}
