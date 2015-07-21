/**
 * (c) copyright 2013 by getit GmbH
 *
 * @author gerald.bornemann, getit GmbH
 */
package de.getit.hybris.klarna.payment.core.service.impl.reserveamount;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import de.hybris.platform.core.PK;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.core.model.order.price.DiscountModel;
import de.hybris.platform.core.model.order.price.TaxModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.deliveryzone.model.ZoneDeliveryModeModel;
import de.hybris.platform.deliveryzone.model.ZoneDeliveryModeValueModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.jalo.order.price.JaloPriceFactoryException;
import de.hybris.platform.order.CalculationService;
import de.hybris.platform.order.CartEntryService;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.DiscountService;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.order.PaymentModeService;
import de.hybris.platform.order.TaxService;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.product.PriceService;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.DiscountValue;
import de.hybris.platform.util.TaxValue;
import de.hybris.platform.voucher.VoucherModelService;
import de.hybris.platform.voucher.VoucherService;
import de.hybris.platform.voucher.model.VoucherModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Ignore;

import au.com.bytecode.opencsv.CSVWriter;

import com.klarna.api.KlarnaException;
import com.klarna.api.KlarnaPClass;

import de.getit.hybris.klarna.payment.constants.KlarnapaymentTestConstants;
import de.getit.hybris.klarna.payment.core.exception.GetitKlarnaException;
import de.getit.hybris.klarna.payment.core.service.KlarnaReservationService;
import de.getit.hybris.klarna.payment.core.strategy.impl.GetitKlarnaGetBaseParameterStrategy;
import de.getit.hybris.klarna.payment.enums.KlarnaStatus;
import de.getit.hybris.klarna.payment.model.KlarnaPaymentInfoModel;

/**
 * Abstract base class for JUnittests.
 * 
 * Technical specification: KLARNA-65
 * 
 * @author gerald.bornemann, getit GmbH
 */
@Ignore
public abstract class AbstractReserveAmountTest {

	private static final Logger LOG = Logger.getLogger(AbstractReserveAmountTest.class);

	public static final String CSV_HEADER_COMMON_DATA = "basestore;country;paymentmode;user;testedPclassid;testedKlarnaStatus;";
	public static final String CSV_HEADER_ENTRY01_DATA = "entry01BasePrice;entry01Quantity;entry01TotalPrice;entry01TaxValue;entry01AppliedTax;";
	public static final String CSV_HEADER_ENTRY01_DISCOUNT01_DATA = "entry01DiscountCode01;entry01DiscountValue01;";
	public static final String CSV_HEADER_ENTRY01_DISCOUNT02_DATA = "entry01DiscountCode02;entry01DiscountValue02;";
	public static final String CSV_HEADER_ENTRY02_DISCOUNT01_DATA = "entry02DiscountCode01;entry02DiscountValue01;";
	public static final String CSV_HEADER_ENTRY02_DISCOUNT02_DATA = "entry02DiscountCode02;entry02DiscountValue02;";
	public static final String CSV_HEADER_ENTRY02_DATA = "entry02BasePrice;entry02Quantity;entry02TotalPrice;entry02TaxValue;entry02AppliedTax;";
	public static final String CSV_HEADER_ENTRY03_DATA = "entry03BasePrice;entry03Quantity;entry03TotalPrice;entry03TaxValue;entry03AppliedTax;";
	public static final String CSV_HEADER_CART_DATA = "cartCode;cartCurrency;cartSubtotal;cartTotalPrice;cartTotalTax;cartTotalDiscount;cartDeliveryCost;";
	public static final String CSV_HEADER_GLOBALDISCOUNT_CART_DATA = "globalDiscount01Code;globalDiscount01Value;globalDiscount02Code;globalDiscount02Value;";
	public static final String CSV_HEADER_ORDER_DATA = "reservationno;returnedKlarnaStatus;returnedPclassid;orderCode;orderCurrency;orderSubtotal;orderTotalPrice;orderTotalTax;orderTotalDiscounts;orderDeliveryCost;";

	private CSVWriter mCSVWriter;
	private StringBuilder csvLogFileHeader = null;
	private StringBuilder csvLogFileLine = null;

	protected KlarnaReservationService mKlarnaReservationService;
	protected OrderService mOrderService;
	protected ModelService mModelService;
	protected CartService mCartService;
	protected CommonI18NService mCommonI18NService;
	protected ConfigurationService mConfigurationService;
	protected ProductService mProductService;
	protected CartEntryService mCartEntryService;
	protected CalculationService mCalculationService;
	protected TaxService mTaxService;
	protected DiscountService mDiscountService;
	protected PriceService mPriceService;
	protected VoucherService mVoucherService;
	protected VoucherModelService mVoucherModelService;
	protected SessionService mSessionService;
	protected PaymentModeService mPaymentModeService;
	protected UserService mUserService;

	public AbstractReserveAmountTest() {
		mKlarnaReservationService = (KlarnaReservationService) Registry.getApplicationContext().getBean(KlarnaReservationService.BEAN_NAME);
		mOrderService = (OrderService) Registry.getApplicationContext().getBean("orderService");
		mModelService = (ModelService) Registry.getApplicationContext().getBean("modelService");
		mCartService = (CartService) Registry.getApplicationContext().getBean("cartService");
		mCommonI18NService = (CommonI18NService) Registry.getApplicationContext().getBean("commonI18NService");
		mConfigurationService = (ConfigurationService) Registry.getApplicationContext().getBean("configurationService");
		mProductService = (ProductService) Registry.getApplicationContext().getBean("productService");
		mCartEntryService = (CartEntryService) Registry.getApplicationContext().getBean("cartEntryService");
		mCalculationService = (CalculationService) Registry.getApplicationContext().getBean("calculationService");
		mTaxService = (TaxService) Registry.getApplicationContext().getBean("taxService");
		mDiscountService = (DiscountService) Registry.getApplicationContext().getBean("discountService");
		mPriceService = (PriceService) Registry.getApplicationContext().getBean("priceService");
		mVoucherService = (VoucherService) Registry.getApplicationContext().getBean("voucherService");
		mVoucherModelService = (VoucherModelService) Registry.getApplicationContext().getBean("voucherModelService");
		mSessionService = (SessionService) Registry.getApplicationContext().getBean("sessionService");
		mPaymentModeService = (PaymentModeService) Registry.getApplicationContext().getBean("paymentModeService");
		mUserService = (UserService) Registry.getApplicationContext().getBean("userService");
		mCSVWriter = null;
	}

	abstract public CartModel createCart(final ReserveAmountParams pReserveAmountParams)
		throws CalculationException;

	public void reserveAmount(final ReserveAmountParams pReserveAmountParams)
		throws GetitKlarnaException, InvalidCartException, CalculationException {

		mUserService.setCurrentUser(pReserveAmountParams.getUser());
		mSessionService.setAttribute("currentSite", pReserveAmountParams.getBaseStore().getCmsSites().iterator().next());
		mSessionService.setAttribute(GetitKlarnaGetBaseParameterStrategy.GETITBASESTORESESSIONNAME, pReserveAmountParams.getBaseStore());

		csvLogFileLine = new StringBuilder();
		appendToCurrentCSVLogFileLine(pReserveAmountParams.getBaseStore().getUid());
		appendToCurrentCSVLogFileLine(pReserveAmountParams.getCountry().getIsocode());
		appendToCurrentCSVLogFileLine(pReserveAmountParams.getPaymentMode().getCode());
		appendToCurrentCSVLogFileLine(pReserveAmountParams.getUser().getUid());
		appendToCurrentCSVLogFileLine(pReserveAmountParams.getPClassId());
		appendToCurrentCSVLogFileLine(pReserveAmountParams.getKlarnaStatus().getCode());

		final CartModel cart = createCart(pReserveAmountParams);

		try {
			mKlarnaReservationService.reserveAmount(cart, KlarnapaymentTestConstants.CLIENT_IP, "DIES IST EIN KOMMENTAR!");

			if (pReserveAmountParams.getKlarnaStatus() == KlarnaStatus.KLARNA_DENIED)
				fail("Test should not come to this point");
		} catch (final KlarnaException e) {
			if (pReserveAmountParams.getKlarnaStatus() == KlarnaStatus.KLARNA_DENIED && e.getCode() == 2102)
				return;
			fail("reserveAmount: Unexcepted Exception: " + e.getMessage());
		}

		final OrderModel order = mOrderService.createOrderFromCart(cart);

		assertEquals(((KlarnaPaymentInfoModel) order.getPaymentInfo()).getPclass(), ((KlarnaPaymentInfoModel) cart.getPaymentInfo()).getPclass());
		assertEquals(((KlarnaPaymentInfoModel) order.getPaymentInfo()).getRno(), ((KlarnaPaymentInfoModel) cart.getPaymentInfo()).getRno());

		// attributes for csv file logging see CSV_HEADER_ORDER_DATA
		appendToCurrentCSVLogFileLine(((KlarnaPaymentInfoModel) order.getPaymentInfo()).getRno());
		appendToCurrentCSVLogFileLine(order.getKlarnaStatus().getCode());
		appendToCurrentCSVLogFileLine(((KlarnaPaymentInfoModel) order.getPaymentInfo()).getPclass());
		appendToCurrentCSVLogFileLine(order.getCode());
		appendToCurrentCSVLogFileLine(order.getCurrency().getIsocode());
		appendToCurrentCSVLogFileLine(order.getSubtotal());
		appendToCurrentCSVLogFileLine(order.getTotalPrice());
		appendToCurrentCSVLogFileLine(order.getTotalTax());
		appendToCurrentCSVLogFileLine(order.getTotalDiscounts());
		appendToCurrentCSVLogFileLine(order.getDeliveryCost());

		printCurrentLineToCsvLogFile(csvLogFileLine.toString());

		LOG.info("********** start - reserveAmount() ******************");
		LOG.info("BaseStore: " + pReserveAmountParams.getBaseStore().getUid());
		LOG.info("Reservation: " + ((KlarnaPaymentInfoModel) order.getPaymentInfo()).getRno());
		LOG.info("Cart: " + cart.getCode());
		LOG.info("Order: " + order.getCode());
		LOG.info("********** ende - reserveAmount() ******************");

	}

	protected CartModel createEmptyCart(final ReserveAmountParams pReserveAmountParams) {

		final CartModel cart = mModelService.create(CartModel.class);
		cart.setStore(pReserveAmountParams.getBaseStore());
		cart.setUser(pReserveAmountParams.getUser());
		cart.setCurrency(pReserveAmountParams.getBaseStore().getDefaultCurrency());
		cart.setDate(new Date());
		cart.setPaymentAddress(pReserveAmountParams.getUser().getDefaultPaymentAddress());
		cart.setDeliveryAddress(pReserveAmountParams.getUser().getDefaultPaymentAddress());
		cart.setPaymentMode(pReserveAmountParams.getPaymentMode());

		final KlarnaPaymentInfoModel klarnaPaymentInfo = mModelService.create(KlarnaPaymentInfoModel.class);
		klarnaPaymentInfo.setCode(PK.createUUIDPK(0).getLongValueAsString());
		klarnaPaymentInfo.setUser(pReserveAmountParams.getUser());
		if (pReserveAmountParams.getPClassId() != null) {
			klarnaPaymentInfo.setPclass(pReserveAmountParams.getPClassId());
		} else {
			klarnaPaymentInfo.setPclass(KlarnaPClass.INVOICE);
		}

		cart.setPaymentInfo(klarnaPaymentInfo);

		mModelService.saveAll(cart, klarnaPaymentInfo);

		return cart;
	}

	public void addDeliveryModeToCart(final ReserveAmountParams pReserveAmountParams) throws CalculationException {

		final CartModel cart = mCartService.getSessionCart();

		final DeliveryModeModel deliveryMode = pReserveAmountParams.getDeliveryMode();
		assertNotNull(deliveryMode);

		ZoneDeliveryModeModel zoneDeliveryMode = null;
		if (deliveryMode instanceof ZoneDeliveryModeModel) {
			zoneDeliveryMode = (ZoneDeliveryModeModel) deliveryMode;
		}

		assertNotNull(zoneDeliveryMode);

		getLogger().info("********** add DeliveryMode to cart ******************");
		getLogger().info("zoneDeliveryMode: " + zoneDeliveryMode.getCode());

		final Collection<PaymentModeModel> paymentModes = zoneDeliveryMode.getSupportedPaymentModes();

		assertFalse(paymentModes.isEmpty());

		for (final PaymentModeModel allowedPaymentMode : paymentModes) {
			getLogger().info("supportedPaymentMode: " + allowedPaymentMode.getCode());
		}

		final Set<ZoneDeliveryModeValueModel> deliveryCosts = zoneDeliveryMode.getValues();

		for (final ZoneDeliveryModeValueModel zoneDeliveryModeValue : deliveryCosts) {
			getLogger().info("deliveryCosts: zone=" + zoneDeliveryModeValue.getZone().getCode() + ", minimum=" + zoneDeliveryModeValue.getMinimum() + ", value=" + zoneDeliveryModeValue.getValue()
				+ ", currency="
				+ zoneDeliveryModeValue.getCurrency().getIsocode());
		}

		assertFalse(deliveryCosts.isEmpty());

		cart.setDeliveryMode(zoneDeliveryMode);

		mModelService.saveAll(cart);
		mCalculationService.calculate(cart);
		assertNotNull(cart.getDeliveryMode());

	}

	/**
	 * Adds two country specific products with country specific TaxValue to standard gross cart. <br>
	 * -> first cartEntry with country specific product and country specific tax <br>
	 * --> product name with country specific umlauts <br>
	 * --> quantiy = 1 <br>
	 * --> low and uneven price <br>
	 * --> optional: with percentage discount (20 % or 100%) <br>
	 * --> optional: with absolute discount <br>
	 * <br>
	 * -> second cartEntry with country specific product and country specific tax <br>
	 * --> product number with country specific umlauts <br>
	 * --> quantiy = 4 <br>
	 * --> high and uneven price <br>
	 * --> optional: with percentage discount (20 % or 100%) <br>
	 * --> optional: with absolute discount <br>
	 * <br>
	 * -> optional: third cartEntry with totally free product (price = 0.00) <br>
	 * --> freeProduct <br>
	 * --> quantiy = 1 <br>
	 * --> price = 0.00d <br>
	 * 
	 * @param pReserveAmountParams
	 *            params of current testcase
	 * @param pWithAbsoluteDiscountToEntry
	 *            with or without absolute discount to every entry
	 * @param pWithPercentageDiscountToEntry
	 *            with or without percentage discount to every entry
	 * @param pAddTotallyFreeProduct
	 *            with or without totally free product (as new entry)
	 * @param pAdd100PercentDiscountToFirstEntry
	 *            with or without 100 percent discount to first entry
	 * @param pAddTwoEqualDiscountTypesToEntry
	 *            with or without two equal discounts types (two absolute or relative discount per entry)
	 * @param pAddProductWithDifferentTaxValues
	 *            with or without new entry with product with different taxValue
	 * @param pUseRandomQuantity
	 *            should cart entries be added with a random quantity
	 */
	protected void addProductsCart(final ReserveAmountParams pReserveAmountParams, final boolean pWithAbsoluteDiscountToEntry, final boolean pWithPercentageDiscountToEntry,
		final boolean pAddTotallyFreeProduct, final boolean pAdd100PercentDiscountToFirstEntry, final boolean pAddTwoEqualDiscountTypesToEntry, final boolean pAddProductWithDifferentTaxValues,
		final boolean pUseRandomQuantity)
		throws CalculationException {

		final CartModel cart = mCartService.getSessionCart();

		// /////////////////////////////////////////////////
		// add first cart entry: product_01_<countryIsoCode>
		// /////////////////////////////////////////////////
		final ProductModel product01 = mProductService.getProductForCode("product_01_" + pReserveAmountParams.getCountry().getIsocode().toUpperCase());
		assertNotNull(product01);
		// quantity = 1
		long quantity = 1;
		if (pUseRandomQuantity) {
			final Double random = (Math.random()) * 10 + 5;
			quantity = random.longValue();
		}
		final CartEntryModel entry01 = mCartService.addNewEntry(cart, product01, quantity, product01.getUnit(), -1, true);
		entry01.setCalculated(false);
		// add price from product_01_<countryIsoCode> PriceRow
		final Collection<PriceRowModel> priceRowModels01 = product01.getEurope1Prices();
		assertNotNull(priceRowModels01);
		final PriceRowModel priceRowModel01 = priceRowModels01.iterator().next();
		final double price01 = priceRowModel01.getPrice();
		entry01.setBasePrice(Double.valueOf(price01));
		mModelService.save(entry01);

		// /////////////////////////////////////////////////
		// add second cart entry: product_02_<countryIsoCode>_<product01.localizedNameWithUmlauts>
		// /////////////////////////////////////////////////
		final ProductModel product02 = mProductService.getProductForCode("product_02_" + pReserveAmountParams.getCountry().getIsocode().toUpperCase() + "_" + product01.getName().trim());
		assertNotNull(product02);
		// quantity = 4
		quantity = 4;
		if (pUseRandomQuantity) {
			final Double random = (Math.random()) * 10 + 5;
			quantity = random.longValue();
		}
		final CartEntryModel entry02 = mCartService.addNewEntry(cart, product02, quantity, product02.getUnit(), -1, true);
		entry02.setCalculated(false);
		// add price from product_02_<countryIsoCode> PriceRow
		final Collection<PriceRowModel> priceRowModels02 = product02.getEurope1Prices();
		assertNotNull(priceRowModels02);
		final PriceRowModel priceRowModel02 = priceRowModels02.iterator().next();
		final double price02 = priceRowModel02.getPrice();
		entry02.setBasePrice(Double.valueOf(price02));
		mModelService.save(entry02);

		LOG.info("********** add products to cart ******************");
		LOG.info("price for product '" + product01.getCode() + "' = " + price01);
		LOG.info("price for product '" + product02.getCode() + "' = " + price02);
		LOG.info("***********************************************");

		// /////////////////////////////////////////////////
		// add country specific Taxes to both entries
		// /////////////////////////////////////////////////
		final TaxModel tax = mTaxService.getTaxForCode(KlarnapaymentTestConstants.getTaxCodeForCountry(pReserveAmountParams.getCountry().getIsocode().toUpperCase()));
		final TaxValue taxValue = new TaxValue(tax.getCode(), tax.getValue(), tax.getAbsolute(), null);
		mCartEntryService.addTaxValue(entry01, taxValue);
		mCartEntryService.addTaxValue(entry02, taxValue);
		mModelService.saveAll(entry01, entry02);

		LOG.info("********** add tax value to cart entries ******************");
		LOG.info("TaxModel.getAbsolute() = " + tax.getAbsolute());
		LOG.info("used taxValue '" + taxValue.getCode() + "' value=" + taxValue.getValue() + ", appliedValue=" + taxValue.getAppliedValue() + ", absolute=" + taxValue.isAbsolute() + ", currency="
			+ taxValue.getCurrencyIsoCode());
		LOG.info("***********************************************");

		// /////////////////////////////////////////////////
		// optional: add third cart entry with totally free product
		// /////////////////////////////////////////////////
		CartEntryModel entry03 = null;
		if (pAddTotallyFreeProduct) {
			final ProductModel product = mProductService.getProductForCode("freeProduct_" + pReserveAmountParams.getCountry().getIsocode().toUpperCase());
			assertNotNull(product);
			entry03 = mCartService.addNewEntry(cart, product, 1, product.getUnit(), -1, true);
			entry03.setCalculated(false);
			entry03.setBasePrice(0.00d);
			mCartEntryService.addTaxValue(entry03, taxValue);
			mModelService.saveAll(entry03);
			LOG.info("********** add totally free product to cart ******************");
			LOG.info("price for free product '" + product.getCode() + "' = " + entry03.getBasePrice());
			LOG.info("***********************************************");
		}

		// /////////////////////////////////////////////////
		// optional: add another cart entry with a product with a different taxValue
		// /////////////////////////////////////////////////
		CartEntryModel entry04 = null;
		TaxModel tax04 = null;
		if (pAddProductWithDifferentTaxValues) {
			final ProductModel product04 = mProductService.getProductForCode("differentTaxProduct_" + cart.getCurrency().getIsocode().toUpperCase());
			assertNotNull(product04);
			quantity = 5;
			if (pUseRandomQuantity) {
				final Double random = (Math.random()) * 10 + 5;
				quantity = random.longValue();
			}
			entry04 = mCartService.addNewEntry(cart, product04, quantity, product04.getUnit(), -1, true);
			entry04.setCalculated(false);
			final Collection<PriceRowModel> priceRowModels04 = product04.getEurope1Prices();
			assertNotNull(priceRowModels04);
			final PriceRowModel priceRowModel04 = priceRowModels04.iterator().next();
			final double price04 = priceRowModel04.getPrice();
			entry04.setBasePrice(Double.valueOf(price04));
			mModelService.save(entry04);

			// add different TaxValue
			tax04 = mTaxService.getTaxForCode(KlarnapaymentTestConstants.getTaxCodeForCountry("DIFF"));
			final TaxValue taxValue04 = new TaxValue(tax04.getCode(), tax04.getValue(), tax04.getAbsolute(), null);
			mCartEntryService.addTaxValue(entry04, taxValue04);
			mModelService.save(entry04);

			LOG.info("****** add product with different TaxValue ******************************");
			LOG.info("price for different tax product '" + product04.getCode() + "' = " + entry04.getBasePrice());
			LOG.info("different tax: taxModel.getAbsolute() = " + tax04.getAbsolute());
			LOG.info("used taxValue '" + taxValue04.getCode() + "' value=" + taxValue04.getValue() + ", appliedValue=" + taxValue04.getAppliedValue() + ", absolute=" + taxValue04.isAbsolute()
				+ ", currency=" + taxValue04.getCurrencyIsoCode());
			LOG.info("***********************************************");

		}

		if (pWithPercentageDiscountToEntry) {
			if (pAdd100PercentDiscountToFirstEntry) {
				addPercentageDiscountToEntry(entry01, KlarnapaymentTestConstants.DISCOUNT.TOTALLY_FREE); // -100%
			} else {
				if (pAddTwoEqualDiscountTypesToEntry) {
					addPercentageDiscountToEntry(entry01, KlarnapaymentTestConstants.DISCOUNT.TWENTY_PERCENT); // -20%
					addPercentageDiscountToEntry(entry01, KlarnapaymentTestConstants.DISCOUNT.TEN_PERCENT); // -10%
					addPercentageDiscountToEntry(entry02, KlarnapaymentTestConstants.DISCOUNT.TWENTY_PERCENT); // -20%
					addPercentageDiscountToEntry(entry02, KlarnapaymentTestConstants.DISCOUNT.TEN_PERCENT); // -10%
				} else {
					addPercentageDiscountToEntry(entry01, KlarnapaymentTestConstants.DISCOUNT.TWENTY_PERCENT); // -20%
					addPercentageDiscountToEntry(entry02, KlarnapaymentTestConstants.DISCOUNT.TEN_PERCENT); // -10%
				}
			}
		}
		if (pWithAbsoluteDiscountToEntry) {
			if (pAddTwoEqualDiscountTypesToEntry) {
				addAbsoluteDiscountToEntry(entry01, KlarnapaymentTestConstants.DISCOUNT.THREE_OFF + "_" + cart.getCurrency().getIsocode().toUpperCase()); // -3.00d
				addAbsoluteDiscountToEntry(entry01, KlarnapaymentTestConstants.DISCOUNT.FOUR_AHALF_OFF + "_" + cart.getCurrency().getIsocode().toUpperCase()); // -4.50d
				addAbsoluteDiscountToEntry(entry02, KlarnapaymentTestConstants.DISCOUNT.THREE_OFF + "_" + cart.getCurrency().getIsocode().toUpperCase()); // -3.00d
				addAbsoluteDiscountToEntry(entry02, KlarnapaymentTestConstants.DISCOUNT.FOUR_AHALF_OFF + "_" + cart.getCurrency().getIsocode().toUpperCase()); // -4.50d

			} else {
				addAbsoluteDiscountToEntry(entry01, KlarnapaymentTestConstants.DISCOUNT.THREE_OFF + "_" + cart.getCurrency().getIsocode().toUpperCase()); // -3.00d
				addAbsoluteDiscountToEntry(entry02, KlarnapaymentTestConstants.DISCOUNT.TEN_OFF + "_" + cart.getCurrency().getIsocode().toUpperCase()); // -10.00d

			}
		}

		// assertFalse(mModelService.isUpToDate(cart));
		mModelService.saveAll(cart, entry01, entry02);
		assertFalse(cart.getCalculated().booleanValue());
		mCalculationService.calculateTotals(cart, true);
		mCalculationService.calculate(cart);

		appendToCurrentCSVLogFileLine(entry01.getBasePrice());
		appendToCurrentCSVLogFileLine(entry01.getQuantity());
		appendToCurrentCSVLogFileLine(entry01.getTotalPrice());
		appendToCurrentCSVLogFileLine(tax.getCode());
		appendToCurrentCSVLogFileLine(entry01.getTaxValues().iterator().next().getAppliedValue());
		if (pWithPercentageDiscountToEntry || pWithAbsoluteDiscountToEntry) {
			for (final Iterator<DiscountValue> it = entry01.getDiscountValues().iterator(); it.hasNext();) {
				final DiscountValue discountValue = it.next();
				appendToCurrentCSVLogFileLine(discountValue.getCode());
				appendToCurrentCSVLogFileLine(new Double(discountValue.getAppliedValue()));
			}
		}

		appendToCurrentCSVLogFileLine(entry02.getBasePrice());
		appendToCurrentCSVLogFileLine(entry02.getQuantity());
		appendToCurrentCSVLogFileLine(entry02.getTotalPrice());
		appendToCurrentCSVLogFileLine(tax.getCode());
		appendToCurrentCSVLogFileLine(entry02.getTaxValues().iterator().next().getAppliedValue());
		if ((pWithPercentageDiscountToEntry && !pAdd100PercentDiscountToFirstEntry) || pWithAbsoluteDiscountToEntry) {
			for (final Iterator<DiscountValue> it = entry02.getDiscountValues().iterator(); it.hasNext();) {
				final DiscountValue discountValue = it.next();
				appendToCurrentCSVLogFileLine(discountValue.getCode());
				appendToCurrentCSVLogFileLine(new Double(discountValue.getAppliedValue()));
			}
		}
		if (pAddTotallyFreeProduct && entry03 != null) {
			appendToCurrentCSVLogFileLine(entry03.getBasePrice());
			appendToCurrentCSVLogFileLine(entry03.getQuantity());
			appendToCurrentCSVLogFileLine(entry03.getTotalPrice());
			appendToCurrentCSVLogFileLine("");
			appendToCurrentCSVLogFileLine("");
		}
		if (pAddProductWithDifferentTaxValues && entry04 != null && tax04 != null) {
			appendToCurrentCSVLogFileLine(entry04.getBasePrice());
			appendToCurrentCSVLogFileLine(entry04.getQuantity());
			appendToCurrentCSVLogFileLine(entry04.getTotalPrice());
			appendToCurrentCSVLogFileLine(tax04.getCode());
			appendToCurrentCSVLogFileLine(entry04.getTaxValues().iterator().next().getAppliedValue());
		}
	}

	/**
	 * Adds a percentage discount to a cart entry.
	 * 
	 * @param entry
	 * @param discountCode
	 */
	protected void addPercentageDiscountToEntry(final CartEntryModel entry, final String discountCode) {
		DiscountValue discountValue = null;
		final DiscountModel entryDiscount = mDiscountService.getDiscountForCode(discountCode);
		assertNotNull(entryDiscount);
		discountValue = new DiscountValue(entryDiscount.getCode(), entryDiscount.getValue(), false, null);
		assertNotNull(discountValue);
		mCartEntryService.addDiscountValue(entry, discountValue);
		assertFalse(mModelService.isUpToDate(entry));
		mModelService.save(entry);
		LOG.info("********** add percentage discount to cart entry ****************************");
		LOG.info("add percentage discount '" + entryDiscount.getCode() + "' = " + entryDiscount.getValue());
		LOG.info("*****************************************************************************");

	}

	/**
	 * Adds an absolute discount to a cart entry.
	 * 
	 * @param entry
	 * @param discountCode
	 */
	protected void addAbsoluteDiscountToEntry(final CartEntryModel entry, final String discountCode) {
		DiscountValue discountValue = null;
		final CartModel cart = mCartService.getSessionCart();
		final DiscountModel entryDiscount = mDiscountService.getDiscountForCode(discountCode);
		discountValue = new DiscountValue(entryDiscount.getCode(), entryDiscount.getValue(), true, cart.getCurrency().getIsocode());
		assertNotNull(discountValue);
		mCartEntryService.addDiscountValue(entry, discountValue);
		assertFalse(mModelService.isUpToDate(entry));
		mModelService.save(entry);
		LOG.info("********** add absolute discount to cart entry ****************************");
		LOG.info("add absolute discount '" + entryDiscount.getCode() + "' = " + entryDiscount.getValue());
		LOG.info("***************************************************************************");
	}

	/**
	 * Add a global discount (absolute or percentage) to the cart. <br>
	 * If absolute = true, the absolute discount "global_thirty_percent" is used. <br>
	 * If absolute = false, the percentage discount "global_5_off" is used. <br>
	 * 
	 * @param pReserveAmountParams
	 * @param absolute
	 */
	protected void addGlobalDiscount(final ReserveAmountParams pReserveAmountParams, final boolean absolute)
		throws CalculationException {

		final CartModel cart = mCartService.getSessionCart();

		DiscountModel globalDiscount = null;
		if (!absolute) {
			// add global discount in percent
			globalDiscount = mDiscountService.getDiscountForCode(KlarnapaymentTestConstants.DISCOUNT.GLOBAL_THIRTY_PERCENT);
			assertNotNull(globalDiscount);
			mCartService.addGlobalDiscountValue(cart, new DiscountValue(globalDiscount.getCode(), globalDiscount.getValue(), false, null));
			LOG.info("********** add relative global discount to cart ******************");
			LOG.info("used globalDiscount '" + globalDiscount.getCode() + "' = " + globalDiscount.getValue());
			LOG.info("*********************************************************");
		} else {
			// add absolute global discount
			globalDiscount = mDiscountService.getDiscountForCode(KlarnapaymentTestConstants.DISCOUNT.GLOBAL_5_OFF + "_" + cart.getCurrency().getIsocode().toUpperCase());
			assertNotNull(globalDiscount);
			mCartService.addGlobalDiscountValue(cart, new DiscountValue(globalDiscount.getCode(), globalDiscount.getValue(), true, cart.getCurrency().getIsocode()));
			LOG.info("********** add absolute global discount to cart ******************");
			LOG.info("used globalDiscount '" + globalDiscount.getCode() + "' = " + globalDiscount.getValue());
			LOG.info("*********************************************************");
		}

		assertFalse(mModelService.isUpToDate(cart));
		mModelService.save(cart);
		assertFalse(cart.getCalculated().booleanValue());
		mCalculationService.calculateTotals(cart, true);
	}

	protected void addPromotionVoucherToCart(final ReserveAmountParams pReserveAmountParams) throws CalculationException {

		final CartModel cart = mCartService.getSessionCart();
		final String voucherCode = "voucher_01_" + cart.getCurrency().getIsocode().toUpperCase();
		final VoucherModel voucher = mVoucherService.getVoucher(voucherCode);
		// final Collection<PromotionVoucherModel> vouchers = mVoucherService.getPromotionVouchers(voucherCode);
		// LOG.info("vouchers.size() = " + vouchers.size());
		// final boolean result = mVoucherModelService.isApplicable(vouchers.iterator().next(), cart);

		final boolean result = mVoucherModelService.isApplicable(voucher, cart);
		assertTrue("voucher should be applicable", result);
		boolean redeemed = false;
		try {
			redeemed = mVoucherService.redeemVoucher(voucherCode, cart);
		} catch (final JaloPriceFactoryException e) {
			LOG.warn("addPromotionVoucherToCart() failed ", e);
		}

		assertTrue(redeemed);

		LOG.info("********** add promotion voucher to cart ******************");
		LOG.info("used promotion voucher '" + voucher.getCode() + "' = " + voucher.getValue());
		// LOG.info("voucher.currency = '" + voucher.getCurrency().getIsocode());

		assertFalse(mModelService.isUpToDate(cart));
		mModelService.save(cart);
		mModelService.refresh(cart);
		mCalculationService.calculateTotals(cart, true);

		final Collection<DiscountModel> appliedVouchers = mVoucherService.getAppliedVouchers(cart);
		for (final Iterator<DiscountModel> it = appliedVouchers.iterator(); it.hasNext();) {
			final DiscountModel discountModel = it.next();
			LOG.info("discountModel.code = '" + discountModel.getCode());
			LOG.info("discountModel.value = '" + discountModel.getValue());
			LOG.info("discountModel.absolute = '" + discountModel.getAbsolute());
			LOG.info("discountModel.global = '" + discountModel.getGlobal());
		}

		final Collection<String> appliedVoucherCodes = mVoucherService.getAppliedVoucherCodes(cart);

		LOG.info("appliedVouchers = '" + appliedVouchers);
		LOG.info("appliedVoucherCodes = '" + appliedVoucherCodes);
		LOG.info("*********************************************************");

	}

	protected void addToCart(final ProductModel product, final boolean addDiscountToEntry)
		throws CalculationException {

		final CartModel cart = mCartService.getSessionCart();
		final CartEntryModel entry = mCartService.addNewEntry(cart, product, 1, null, -1, true);

		entry.setBasePrice(Double.valueOf(2119d));

		final TaxValue taxValue = new TaxValue("VAT FULL", 19.0, false, cart.getCurrency().getIsocode());
		mCartEntryService.addTaxValue(entry, taxValue);

		mModelService.saveAll(cart, entry);
		mCalculationService.calculateTotals(cart, true);
	}

	abstract public void createCSVFileHeader();

	public String getCSVFileHeader() {
		return csvLogFileHeader.toString();
	}

	protected void printCurrentLineToCsvLogFile(final String currentLine) {

		if (mCSVWriter != null && !currentLine.isEmpty()) {
			final String[] splittedLine = currentLine.split(";");
			mCSVWriter.writeNext(splittedLine);
		}
	}

	public void createCSVLogFile() {
		try {
			final String fileDirectory = mConfigurationService.getConfiguration().getString("klarna.junittest.result.directory");
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
			final String timeStamp = sdf.format(new Date());
			// currentDateTime.
			final File csvFile = new File(fileDirectory + "ResultJUnitTest_" + this.getClass().getSimpleName() + "_" + timeStamp + ".csv");
			if (csvFile != null) {
				final BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "UTF-8"));
				mCSVWriter = new CSVWriter(buffWriter, ';');
				// feed in your array (or convert your data to an array)
				csvLogFileHeader = new StringBuilder();
				createCSVFileHeader();
				final String[] splittedCsvLogFileHeader = getCSVFileHeader().split(";");
				mCSVWriter.writeNext(splittedCsvLogFileHeader);
				LOG.info("createCSVLogFile() " + csvFile.toString());
			}
		} catch (final IOException e) {
			LOG.warn("createCSVLogFile() failed " + e.getMessage(), e);
		}
	}

	public void closeCSVLogFile() {
		if (mCSVWriter != null) {
			try {
				mCSVWriter.close();
			} catch (final IOException e) {
				LOG.warn("closeCSVLogFile() failed " + e.getMessage(), e);
			}
		}
	}

	public void appendToCurrentCSVLogFileLine(final Object field) {
		if (csvLogFileLine != null) {
			csvLogFileLine.append(field + ";");
		}

	}

	public void appendToCSVLogFileHeader(final String appendLogFileHeader) {
		if (csvLogFileHeader != null && appendLogFileHeader != null) {
			csvLogFileHeader.append(appendLogFileHeader);
		}
	}

	public abstract Logger getLogger();

	/**
	 * Cart info logging to console log and to csv file.
	 */
	public void logCartInfos() {

		final CartModel cart = mCartService.getSessionCart();

		getLogger().info("********** start - logCartInfos() ******************");
		getLogger().info("BaseStore: " + cart.getStore().getUid());
		getLogger().info("Cart: " + cart.getCode());
		getLogger().info("Cart.getSubtotal(): " + cart.getSubtotal());
		getLogger().info("Cart.getTotalPrice(): " + cart.getTotalPrice());
		getLogger().info("Cart.getTotalTax(): " + cart.getTotalTax());
		for (final Iterator<TaxValue> it = cart.getTotalTaxValues().iterator(); it.hasNext();) {
			final TaxValue taxValue = it.next();
			LOG.info("-> cart.getTaxValues(): appliedValue=" + taxValue.getAppliedValue() + ", value=" + taxValue.getValue() + ", absolute=" + taxValue.isAbsolute() + ", currency="
				+ taxValue.getCurrencyIsoCode());
		}
		getLogger().info("Cart.getTotalDiscounts(): " + cart.getTotalDiscounts());
		getLogger().info("Cart.getDeliveryCost(): " + cart.getDeliveryCost());
		getLogger().info("Cart.getPaymentCost(): " + cart.getPaymentCost());
		getLogger().info("Cart.getCalculated(): " + cart.getCalculated());
		for (final AbstractOrderEntryModel entry : cart.getEntries()) {
			getLogger().info("-> entry.getBasePrice(): " + entry.getBasePrice());
			getLogger().info("-> entry.getQuantity(): " + entry.getQuantity());
			getLogger().info("-> entry.getTotalPrice(): " + entry.getTotalPrice());
			getLogger().info("-> entry.getCalculated(): " + entry.getCalculated());
			for (final Iterator<DiscountValue> it = entry.getDiscountValues().iterator(); it.hasNext();) {
				final DiscountValue discountValue = it.next();
				getLogger().info("-> entry.getDiscountValue(): appliedValue=" + discountValue.getAppliedValue() + ", value=" + discountValue.getValue() + ", absolute=" + discountValue.isAbsolute());
			}
			for (final Iterator<TaxValue> it = entry.getTaxValues().iterator(); it.hasNext();) {
				final TaxValue taxValue = it.next();
				getLogger().info("-> entry.getTaxValues(): appliedValue=" + taxValue.getAppliedValue() + ", value=" + taxValue.getValue() + ", absolute=" + taxValue.isAbsolute() + ", currency="
					+ taxValue.getCurrencyIsoCode());
			}
		}
		getLogger().info("********** ende - logCartInfos() ******************");

		// attributes for csv file logging see CSV_HEADER_CART_DATA
		appendToCurrentCSVLogFileLine(cart.getCode());
		appendToCurrentCSVLogFileLine(cart.getCurrency().getIsocode());
		appendToCurrentCSVLogFileLine(cart.getSubtotal());
		appendToCurrentCSVLogFileLine(cart.getTotalPrice());
		appendToCurrentCSVLogFileLine(cart.getTotalTax());
		appendToCurrentCSVLogFileLine(cart.getTotalDiscounts());
		appendToCurrentCSVLogFileLine(cart.getDeliveryCost());

	}

}
