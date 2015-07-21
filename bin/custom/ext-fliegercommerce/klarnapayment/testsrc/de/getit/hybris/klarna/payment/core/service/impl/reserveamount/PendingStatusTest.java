/**
 * (c) copyright 2013 by getit GmbH
 *
 * @author gerald.bornemann, getit GmbH
 */
package de.getit.hybris.klarna.payment.core.service.impl.reserveamount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.order.exceptions.CalculationException;

import org.apache.log4j.Logger;
import org.junit.Ignore;

import com.klarna.api.KlarnaException;

import de.getit.hybris.klarna.payment.constants.KlarnapaymentTestConstants;
import de.getit.hybris.klarna.payment.core.exception.GetitKlarnaException;
import de.getit.hybris.klarna.payment.core.strategy.impl.GetitKlarnaGetBaseParameterStrategy;
import de.getit.hybris.klarna.payment.enums.KlarnaStatus;

/**
 * 
 * Test for the method KlarnaReservationService#updateKlarnaStatus
 * 
 * @author gerald.bornemann, getit GmbH
 */
// Technical specification: KLARNA-65
@Ignore
public class PendingStatusTest
	extends AbstractReserveAmountTest {

	private static final Logger LOG = Logger.getLogger(PendingStatusTest.class);

	@Override
	public CartModel createCart(final ReserveAmountParams pReserveAmountParams)
		throws CalculationException {

		final CartModel cart = createEmptyCart(pReserveAmountParams);
		cart.setNet(false);
		mModelService.saveAll(cart);
		mCartService.setSessionCart(cart);

		final ProductModel product = mProductService.getProductForCode("testProduct0");
		addToCart(product, false);

		return cart;
	}

	@Override
	public void reserveAmount(final ReserveAmountParams pReserveAmountParams)
		throws GetitKlarnaException, InvalidCartException, CalculationException {

		mUserService.setCurrentUser(pReserveAmountParams.getUser());
		mSessionService.setAttribute("currentSite", pReserveAmountParams.getBaseStore().getCmsSites().iterator().next());
		mSessionService.setAttribute(GetitKlarnaGetBaseParameterStrategy.GETITBASESTORESESSIONNAME, pReserveAmountParams.getBaseStore());

		if (!pReserveAmountParams.getCountry().getIsocode().equals("DE"))
			return;

		if (!pReserveAmountParams.getPaymentMode().getCode().equals("klarnaInvoice"))
			return;

		final CartModel cart = createCart(pReserveAmountParams);

		final AddressModel testaddress = mModelService.clone(cart.getPaymentAddress());
		testaddress.setLastname("Approved");
		if (pReserveAmountParams.getKlarnaStatus() == KlarnaStatus.KLARNA_ACCEPTED)
			testaddress.setEmail("pending_accepted@yourdomain.com");
		else
			testaddress.setEmail("pending_denied@yourdomain.com ");

		cart.setPaymentAddress(testaddress);
		cart.setDeliveryAddress(testaddress);
		mModelService.saveAll(cart, testaddress);

		try {
			mKlarnaReservationService.reserveAmount(cart, KlarnapaymentTestConstants.CLIENT_IP, "DIES IST EIN KOMMENTAR!");
			assertEquals(cart.getKlarnaStatus(), KlarnaStatus.KLARNA_PENDING);

			final OrderModel order = mOrderService.createOrderFromCart(cart);

			for (int idx = 0; idx < 9; idx++) {
				try {
					Thread.sleep(20000);
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}

				mKlarnaReservationService.updateKlarnaStatus(order, pReserveAmountParams.getBaseStore(), pReserveAmountParams.getCountry(), KlarnapaymentTestConstants.CLIENT_IP);
				LOG.info(order.getKlarnaStatus());
				if (order.getKlarnaStatus() == KlarnaStatus.KLARNA_PENDING)
					continue;

				assertTrue(idx > 3);
				if (pReserveAmountParams.getKlarnaStatus() == KlarnaStatus.KLARNA_ACCEPTED)
					assertEquals(order.getKlarnaStatus(), KlarnaStatus.KLARNA_ACCEPTED);
				else
					assertEquals(order.getKlarnaStatus(), KlarnaStatus.KLARNA_DENIED);

				return;
			}
		} catch (final KlarnaException e) {
			LOG.info("************************************");
			LOG.info("KlarnaException occured: Errorcode =  " + e.getCode() + ", ErrorMessage = " + e.getMessage());
			LOG.info("************************************");
			fail(e.getMessage());
		}
	}

	@Override
	public void createCSVFileHeader() {
		// do nothing
		// appendToCSVLogFileHeader(CSV_HEADER_COMMON_DATA);
		// appendToCSVLogFileHeader(CSV_HEADER_ORDER_DATA);
	}

	@Override
	public Logger getLogger() {
		return LOG;
	}
}
