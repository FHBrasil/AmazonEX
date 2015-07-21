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
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.util.TaxValue;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.junit.Ignore;

import de.getit.hybris.klarna.payment.constants.KlarnapaymentConstants;
import de.getit.hybris.klarna.payment.core.service.impl.ParameterizedGetitKlarnaReservationServiceTest;

/**
 * Creates and runs test for standard gross calculated cart objects with two standard cart entries. Adds the
 * "klarnaInvoiceFeeProduct" to the cart, if paymentMode "klarnaInvoice" have been activated. This InvoiceFee
 * (HandlingFee) will be submitted to klarna-API.<br>
 * 
 * <br>
 * This test runs per every allowed Basestore+Country - PaymentMode - User combination defined in
 * {@link ParameterizedGetitKlarnaReservationServiceTest}.
 * 
 * @author Sonja Bouwers, getit GmbH
 */
// Technical specification: KLARNA-16, KLARNA-105, KLARNA-106
@Ignore
public class StandardGrossCartWithUpdateKlarnaInvoiceFeeAndDeliveryCostTest extends AbstractReserveAmountTest {

	private static final Logger LOG = Logger.getLogger(StandardGrossCartWithUpdateKlarnaInvoiceFeeAndDeliveryCostTest.class);

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
			false, true); // no new entry for product with a different taxValue

		assertTrue("cart.getTotalPrice() != 0.0 ", 0.0 != cart.getTotalPrice().doubleValue());
		assertTrue("cart.getTotalTax() != 0.0", 0.0 != cart.getTotalTax().doubleValue());
		assertFalse("cart.getTotalTaxValues().isEmpty() ", cart.getTotalTaxValues().isEmpty());

		logCartInfos();

		// change PaymentMode from currentPaymentMode to null and then back to currentPaymentMode
		final PaymentModeModel currentPaymentMode = cart.getPaymentMode();

		LOG.info("********************* start change PaymentMode *****************");
		LOG.info("cart.getEntries().size() before setting paymentMode to null : " + cart.getEntries().size());
		LOG.info("current cart.paymentMode : " + currentPaymentMode.getCode());

		cart.setPaymentMode(null);
		mModelService.saveAll(cart);
		mCartService.setSessionCart(cart);
		// mCalculationService.calculate(cart);

		assertEquals("cart.getEntries().size() = ", 2, cart.getEntries().size());

		LOG.info("cart.getEntries().size() after setting paymentMode to null: " + cart.getEntries().size());

		cart.setPaymentMode(currentPaymentMode);
		mModelService.saveAll(cart);
		mCartService.setSessionCart(cart);
		mCalculationService.calculate(cart);

		LOG.info("cart.getEntries().size() after setting paymentMode back to '" + currentPaymentMode.getCode() + "': " + cart.getEntries().size());

		if (currentPaymentMode.getCode().equals(KlarnapaymentConstants.KlarnaPaymentModes.KLARNA_INVOICE)) {

			// check if 3 cart entries
			assertEquals("cart.getEntries().size() = ", 3, cart.getEntries().size());

			LOG.info("klarnaInvoiceFeeEntry.getBasePrice(): " + cart.getEntries().get(2).getBasePrice());
			LOG.info("klarnaInvoiceFeeEntry.getQuantity(): " + cart.getEntries().get(2).getQuantity());
			LOG.info("klarnaInvoiceFeeEntry.getTotalPrice(): " + cart.getEntries().get(2).getTotalPrice());
			LOG.info("klarnaInvoiceFeeEntry.getCalculated(): " + cart.getEntries().get(2).getCalculated());

			appendToCurrentCSVLogFileLine(cart.getEntries().get(2).getBasePrice());
			appendToCurrentCSVLogFileLine(cart.getEntries().get(2).getQuantity());
			appendToCurrentCSVLogFileLine(cart.getEntries().get(2).getTotalPrice());

			for (final Iterator<TaxValue> it = cart.getEntries().get(2).getTaxValues().iterator(); it.hasNext();) {
				final TaxValue taxValue = it.next();
				LOG.info("klarnaInvoiceFeeEntry.getTaxValues(): appliedValue=" + taxValue.getAppliedValue() + ", value=" + taxValue.getValue() + ", absolute=" + taxValue.isAbsolute() + ", currency="
					+ taxValue.getCurrencyIsoCode());
				appendToCurrentCSVLogFileLine(taxValue.getCode());
				appendToCurrentCSVLogFileLine(taxValue.getAppliedValue());
			}
		} else {
			appendToCurrentCSVLogFileLine("");
			appendToCurrentCSVLogFileLine("");
			appendToCurrentCSVLogFileLine("");
			appendToCurrentCSVLogFileLine("");
			appendToCurrentCSVLogFileLine("");
		}
		LOG.info("********************* ende change PaymentMode *****************");

		return cart;
	}

	@Override
	public void createCSVFileHeader() {
		appendToCSVLogFileHeader(CSV_HEADER_COMMON_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ENTRY01_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ENTRY02_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ENTRY03_DATA); // entry 03 with klarnaInvoiceFee Product
		appendToCSVLogFileHeader(CSV_HEADER_CART_DATA);
		appendToCSVLogFileHeader(CSV_HEADER_ORDER_DATA);
	}

	@Override
	public Logger getLogger() {
		return LOG;
	}

}
