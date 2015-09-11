/**
 * (c) copyright 2013 by getit GmbH
 */
package de.getit.hybris.klarna.payment.core.service.impl;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.util.TaxValue;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.klarna.api.KlarnaException;
import com.klarna.api.KlarnaPClass;

import de.getit.hybris.klarna.payment.AbstractIntegrationTest;
import de.getit.hybris.klarna.payment.constants.KlarnapaymentConstants;
import de.getit.hybris.klarna.payment.constants.KlarnapaymentTestConstants;
import de.getit.hybris.klarna.payment.core.exception.GetitKlarnaException;
import de.getit.hybris.klarna.payment.core.service.KlarnaReservationService;
import de.getit.hybris.klarna.payment.core.strategy.impl.GetitKlarnaGetBaseParameterStrategy;
import de.getit.hybris.klarna.payment.model.KlarnaPaymentInfoModel;

/**
 * Integration test suite for {@link KlarnaReservationService}
 * 
 * Just one puncture test to Klarna-API (One Basestore, one country, one user, one product, one gross cart).
 * 
 * @author getit GmbH
 */
@IntegrationTest
public class GetitKlarnaReservationServiceTest extends AbstractIntegrationTest {

	private KlarnaReservationService klarnaReservationService;

	@Before
	public void setUp() throws Exception {
		// Initialize stuff before every test (this is run twice in this example)
		klarnaReservationService = (KlarnaReservationService) getBean(KlarnaReservationService.BEAN_NAME);
		createDefaultCatalog();
		LOG.info("JaloSession-Catalogversion  --> "
			+ JaloSession.getCurrentSession().getSessionContext().getAttribute("catalogversions"));
	}

	@Test
	public void testReserveAmount_StoreDE_CountryDE() throws KlarnaException, GetitKlarnaException, CalculationException {
		LOG.info("JaloSession-Catalogversion --> "
			+ JaloSession.getCurrentSession().getSessionContext().getAttribute("catalogversions"));
		// setup user
		final UserModel user = getUser(MessageFormat.format(KlarnapaymentTestConstants.USER.APPROVED, "DE"));
		userService.setCurrentUser(user);
		assertNotNull("Test Customer ist null.", userService.getCurrentUser());

		
		final BaseStoreModel baseStore = getBaseStore(KlarnapaymentTestConstants.STORE.DE);
		sessionService.setAttribute("currentSite", baseStore.getCmsSites().iterator().next());
		sessionService.setAttribute(GetitKlarnaGetBaseParameterStrategy.GETITBASESTORESESSIONNAME, baseStore);

		final CountryModel country = getCountry(KlarnapaymentTestConstants.COUNTRY.DE);
		assertNotNull("Test Country is null.", country);

		final CartModel cart = createCart(user);
		cartService.setSessionCart(cart);
		assertNotNull("Test Cart is null", cartService.getSessionCart());

		LOG.info("Catalogversion = " + JaloSession.getCurrentSession().getSessionContext().getAttribute("catalogversions"));

		final ProductModel product = getProduct("testProduct0");
		addToCart(product, false);

		reserveAmount(baseStore, country, user, cart);

	}

	private void reserveAmount(final BaseStoreModel baseStore, final CountryModel country, final UserModel user,
		final CartModel cart)
		throws KlarnaException {

		try {
			klarnaReservationService.reserveAmount(cart, KlarnapaymentTestConstants.CLIENT_IP, "Dies ist ein Kommentar");
		} catch (final GetitKlarnaException e) {
			fail("reserveAmount() failed - Could not reserveAmount for baseStore = " + baseStore.getUid() + ", country = "
				+ country.getIsocode() + " and user = " + user.getUid()
				+ ". ErrorMessage = " + e.getMessage());
		}

		assertNotNull("KlarnaStatus of the Cart is null", cart.getKlarnaStatus());
		assertNotNull("PaymentInfo of the Cart is null", cart.getPaymentInfo());
		if (cart.getPaymentInfo() instanceof KlarnaPaymentInfoModel) {
			final KlarnaPaymentInfoModel paymentInfo = (KlarnaPaymentInfoModel) cart.getPaymentInfo();
			assertTrue("PaymentInfo:MerchantId must not be 0", paymentInfo.getEid() != 0);
			// assertTrue("PaymentInfo:KlarnaPClassId must not be 0", paymentInfo.getKlarnaPClassId() != 0);
			assertNotNull("PaymentInfo:reservationNumber must not be null.", paymentInfo.getRno());
			LOG.info("********************************************");
			LOG.info("********************************************");
			LOG.info("* KLARNA-RESERVATIONNUMBER:    " + paymentInfo.getRno() + "     *");
			LOG.info("********************************************");
			LOG.info("********************************************");
		} else {
			fail("cart.gePaymentInfo() is not an instace of KlanrnaPaymentInfoModel");
		}
		assertNotNull("PaymentInfo of the Cart is null", cart.getPaymentInfo());
	}

	private CartModel createCart(final UserModel user) {

		final CartModel cart = modelService.create(CartModel.class);
		assertNotNull("The cart must not be null.", cart);
		cart.setUser(userService.getCurrentUser());
		cart.setCurrency(commonI18NService.getCurrentCurrency());
		cart.setDate(new Date());
		cart.setNet(Boolean.FALSE);
		cart.setPaymentAddress(user.getDefaultPaymentAddress());
		cart.setDeliveryAddress(user.getDefaultShipmentAddress());
		cart.setPaymentMode(getPaymentMode(KlarnapaymentConstants.KlarnaPaymentModes.KLARNA_INVOICE));

		final KlarnaPaymentInfoModel klarnaPaymentInfo = modelService.create(KlarnaPaymentInfoModel.class);
		klarnaPaymentInfo.setCode(PK.createUUIDPK(0).getLongValueAsString());
		klarnaPaymentInfo.setUser(user);
		klarnaPaymentInfo.setPclass(KlarnaPClass.INVOICE);
		cart.setPaymentInfo(klarnaPaymentInfo);

		modelService.saveAll(cart, klarnaPaymentInfo);
		modelService.refresh(cart);
		modelService.refresh(klarnaPaymentInfo);

		// check cart
		assertEquals("Cart.getUser an current user have got to be the same.", user, cart.getUser());
		assertNotNull("Cart currency have to be nut null.", cart.getCurrency());
		assertEquals("Cart currency have to be nut null.", user.getDefaultShipmentAddress(), cart.getDeliveryAddress());
		assertEquals("Cart currency have to be nut null.", user.getDefaultPaymentAddress(), cart.getPaymentAddress());

		return cart;
	}

	private void addToCart(final ProductModel product, final boolean addDiscountToEntry) throws CalculationException {

		final CartModel cart = cartService.getSessionCart();
		final CartEntryModel entry = cartService.addNewEntry(cart, product, 1, null, -1, true);
		final List<AbstractOrderEntryModel> entries = cart.getEntries();
		assertEquals("Number of entries", 1, entries.size());
		// final AbstractOrderEntryModel entry = entries.iterator().next();

		// setting 100E as a base price manually (when you have set the price in the price factory, you do not need to
		// do it).
		entry.setBasePrice(Double.valueOf(100d));

		// add new entry does not persists or calculates cart/order
		assertTrue(modelService.isNew(entry));
		assertFalse(modelService.isUpToDate(cart));

		// add tax on entry
		final TaxValue taxValue = new TaxValue("VAT FULL", 19.0, false, cart.getCurrency().getIsocode());
		cartEntryService.addTaxValue(entry, taxValue);

		if (addDiscountToEntry) {
			// final DiscountValue discountValue0 = new DiscountValue("testDiscount0", 10d, true, eur.getIsocode());
			// cartEntryService.addDiscountValue(cartEntry0, discountValue0);
		}

		// save cart and entry and refresh. The same could be achieved by cartService.saveOrder(cart)
		modelService.saveAll(cart, entry);
		modelService.refresh(cart);
		modelService.refresh(entry);

		// check calculate flag - before calculate() method
		assertFalse(cart.getCalculated().booleanValue());
		assertFalse(entry.getCalculated().booleanValue());
		// calculateCart - only calculate, do not fetch base prices, discounts and taxes from price factory
		calculationService.calculateTotals(cart, true);

		// check calculate flag - after calculate() method
		assertTrue(cart.getCalculated().booleanValue());
		assertTrue(entry.getCalculated().booleanValue());

		// check totalPrice and totalTax on cart
		assertEquals(100, cart.getTotalPrice().doubleValue(), 0.001);
		assertEquals(15.97, cart.getTotalTax().doubleValue(), 0.001);
		// discoun0 is not order level (global) discount.
		assertEquals(0, cart.getTotalDiscounts().doubleValue(), 0.001);
		// check totalPrice on cartentry
		assertEquals(100, entry.getTotalPrice().doubleValue(), 0.001);
		assertThat(!entry.getTaxValues().isEmpty());

		// save cart them - try the second approach this time
		// cartService.saveOrder(cart);
	}

}
