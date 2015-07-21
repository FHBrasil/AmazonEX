/**
 * (c) copyright 2013 by getit GmbH
 *
 * @author gerald.bornemann, getit GmbH
 */
package de.getit.hybris.klarna.payment.core.service.impl;

import static org.junit.Assert.fail;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.store.BaseStoreModel;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.klarna.api.KlarnaException;

import de.getit.hybris.klarna.payment.AbstractIntegrationTest;
import de.getit.hybris.klarna.payment.constants.KlarnapaymentConstants.KlarnaPaymentModes;
import de.getit.hybris.klarna.payment.constants.KlarnapaymentTestConstants;
import de.getit.hybris.klarna.payment.core.dao.KlarnaDAO;
import de.getit.hybris.klarna.payment.core.exception.GetitKlarnaException;
import de.getit.hybris.klarna.payment.core.service.KlarnaPClassService;
import de.getit.hybris.klarna.payment.core.service.impl.reserveamount.AbstractReserveAmountTest;
import de.getit.hybris.klarna.payment.core.service.impl.reserveamount.GrossCartWithAbsoluteEntryDiscountsTest;
import de.getit.hybris.klarna.payment.core.service.impl.reserveamount.GrossCartWithFreeProductsTest;
import de.getit.hybris.klarna.payment.core.service.impl.reserveamount.GrossCartWithGlobalDiscountAndDiffTaxValuesTest;
import de.getit.hybris.klarna.payment.core.service.impl.reserveamount.GrossCartWithGlobalDiscountTest;
import de.getit.hybris.klarna.payment.core.service.impl.reserveamount.GrossCartWithPercentageEntryDiscountsTest;
import de.getit.hybris.klarna.payment.core.service.impl.reserveamount.GrossCartWithPromotionVoucherTest;
import de.getit.hybris.klarna.payment.core.service.impl.reserveamount.GrossCartWithTwoAbsoluteEntryDiscountsTest;
import de.getit.hybris.klarna.payment.core.service.impl.reserveamount.GrossCartWithTwoDifferentEntryDiscountsTest;
import de.getit.hybris.klarna.payment.core.service.impl.reserveamount.GrossCartWithTwoPercentageEntryDiscountsTest;
import de.getit.hybris.klarna.payment.core.service.impl.reserveamount.PendingStatusTest;
import de.getit.hybris.klarna.payment.core.service.impl.reserveamount.ReserveAmountParams;
import de.getit.hybris.klarna.payment.core.service.impl.reserveamount.StandardGrossCartTest;
import de.getit.hybris.klarna.payment.core.service.impl.reserveamount.StandardGrossCartWithDeliveryCostsAndDiffTaxValuesTest;
import de.getit.hybris.klarna.payment.core.service.impl.reserveamount.StandardGrossCartWithDeliveryWithoutInvoiceFeeTest;
import de.getit.hybris.klarna.payment.core.service.impl.reserveamount.StandardGrossCartWithUpdateKlarnaInvoiceFeeAndDeliveryCostTest;
import de.getit.hybris.klarna.payment.core.service.impl.reserveamount.StandardGrossCartWithUpdateKlarnaInvoiceFeeTest;
import de.getit.hybris.klarna.payment.enums.KlarnaPClassType;
import de.getit.hybris.klarna.payment.enums.KlarnaStatus;
import de.getit.hybris.klarna.payment.model.KlarnaPClassModel;

/**
 * Testcases for GetitKlarnaReservationService
 * 
 * This class defines the allowed testcases for reserveAmount(). <br>
 * For every Basestore+Country - PaymentMode - User combination different cart objects will be created and tested:<br>
 * {@link StandardGrossCartTest}, {@link StandardGrossCartTest}, {@link StandardGrossCartTest}.<br>
 * 
 * BaseStore+Country(DE_DE, AT_AT, NL_NL, DK_DK, FI_FI, SE_SE, NO_NO) <br>
 * PaymentMode (Invoice, Account, Campaign) <br>
 * User (approved, denied) <br>
 * 
 * @author gerald.bornemann, getit GmbH
 */
@IntegrationTest
public class ParameterizedGetitKlarnaReservationServiceTest
	extends AbstractIntegrationTest {

	private static final Logger LOG = Logger.getLogger(ParameterizedGetitKlarnaReservationServiceTest.class);

	private static final List<AbstractReserveAmountTest> TESTS = new ArrayList<AbstractReserveAmountTest>();

	static {
		try {
			TESTS.add(StandardGrossCartTest.class.newInstance());
			TESTS.add(GrossCartWithGlobalDiscountTest.class.newInstance());
			TESTS.add(GrossCartWithPercentageEntryDiscountsTest.class.newInstance());
			TESTS.add(GrossCartWithAbsoluteEntryDiscountsTest.class.newInstance());
			TESTS.add(GrossCartWithGlobalDiscountAndDiffTaxValuesTest.class.newInstance());
			TESTS.add(GrossCartWithFreeProductsTest.class.newInstance());
			TESTS.add(GrossCartWithPromotionVoucherTest.class.newInstance());
			TESTS.add(GrossCartWithTwoDifferentEntryDiscountsTest.class.newInstance());
			TESTS.add(GrossCartWithTwoPercentageEntryDiscountsTest.class.newInstance());
			TESTS.add(GrossCartWithTwoAbsoluteEntryDiscountsTest.class.newInstance());
			TESTS.add(StandardGrossCartWithDeliveryCostsAndDiffTaxValuesTest.class.newInstance());
			TESTS.add(StandardGrossCartWithUpdateKlarnaInvoiceFeeTest.class.newInstance());
			TESTS.add(StandardGrossCartWithDeliveryWithoutInvoiceFeeTest.class.newInstance());
			TESTS.add(StandardGrossCartWithUpdateKlarnaInvoiceFeeAndDeliveryCostTest.class.newInstance());

			TESTS.add(PendingStatusTest.class.newInstance());

		} catch (final InstantiationException e) {
			e.printStackTrace();
		} catch (final IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Testcase parameter combine: <br>
	 * - BaseStore+Country(DE_DE, AT_AT, NL_NL, DK_DK, FI_FI, SE_SE, NO_NO) <br>
	 * - PaymentMode (Invoice, Account, Campaign) <br>
	 * - User (approved, denied) <br>
	 * 
	 * Allowed PaymentModes according to Hybris Klarna Integration Appendix C - Payment methods per country
	 * 
	 * @return Collection<Object[]>
	 */
	private static Collection<Object[]> testcases() {
		final Object[][] data = new Object[][] {
			{ KlarnapaymentTestConstants.STORE.DE, KlarnaPaymentModes.KLARNA_INVOICE, KlarnaStatus.KLARNA_ACCEPTED },
			{ KlarnapaymentTestConstants.STORE.DE, KlarnaPaymentModes.KLARNA_INVOICE, KlarnaStatus.KLARNA_DENIED },
			{ KlarnapaymentTestConstants.STORE.DE, KlarnaPaymentModes.KLARNA_ACCOUNT, KlarnaStatus.KLARNA_ACCEPTED },
			{ KlarnapaymentTestConstants.STORE.DE, KlarnaPaymentModes.KLARNA_ACCOUNT, KlarnaStatus.KLARNA_DENIED },

			{ KlarnapaymentTestConstants.STORE.AT, KlarnaPaymentModes.KLARNA_INVOICE, KlarnaStatus.KLARNA_ACCEPTED },
			{ KlarnapaymentTestConstants.STORE.AT, KlarnaPaymentModes.KLARNA_INVOICE, KlarnaStatus.KLARNA_DENIED },

			{ KlarnapaymentTestConstants.STORE.DK, KlarnaPaymentModes.KLARNA_INVOICE, KlarnaStatus.KLARNA_ACCEPTED },
			{ KlarnapaymentTestConstants.STORE.DK, KlarnaPaymentModes.KLARNA_INVOICE, KlarnaStatus.KLARNA_DENIED },
			{ KlarnapaymentTestConstants.STORE.DK, KlarnaPaymentModes.KLARNA_ACCOUNT, KlarnaStatus.KLARNA_ACCEPTED },
			{ KlarnapaymentTestConstants.STORE.DK, KlarnaPaymentModes.KLARNA_ACCOUNT, KlarnaStatus.KLARNA_DENIED },

			{ KlarnapaymentTestConstants.STORE.FI, KlarnaPaymentModes.KLARNA_INVOICE, KlarnaStatus.KLARNA_ACCEPTED },
			{ KlarnapaymentTestConstants.STORE.FI, KlarnaPaymentModes.KLARNA_INVOICE, KlarnaStatus.KLARNA_DENIED },
			{ KlarnapaymentTestConstants.STORE.FI, KlarnaPaymentModes.KLARNA_ACCOUNT, KlarnaStatus.KLARNA_ACCEPTED },
			{ KlarnapaymentTestConstants.STORE.FI, KlarnaPaymentModes.KLARNA_ACCOUNT, KlarnaStatus.KLARNA_DENIED },
			{ KlarnapaymentTestConstants.STORE.FI, KlarnaPaymentModes.KLARNA_CAMPAIGN, KlarnaStatus.KLARNA_ACCEPTED },
			{ KlarnapaymentTestConstants.STORE.FI, KlarnaPaymentModes.KLARNA_CAMPAIGN, KlarnaStatus.KLARNA_DENIED },

			{ KlarnapaymentTestConstants.STORE.NL, KlarnaPaymentModes.KLARNA_INVOICE, KlarnaStatus.KLARNA_ACCEPTED },
			{ KlarnapaymentTestConstants.STORE.NL, KlarnaPaymentModes.KLARNA_INVOICE, KlarnaStatus.KLARNA_DENIED },
			{ KlarnapaymentTestConstants.STORE.NL, KlarnaPaymentModes.KLARNA_ACCOUNT, KlarnaStatus.KLARNA_ACCEPTED },
			{ KlarnapaymentTestConstants.STORE.NL, KlarnaPaymentModes.KLARNA_ACCOUNT, KlarnaStatus.KLARNA_DENIED },

			{ KlarnapaymentTestConstants.STORE.NO, KlarnaPaymentModes.KLARNA_INVOICE, KlarnaStatus.KLARNA_ACCEPTED },
			{ KlarnapaymentTestConstants.STORE.NO, KlarnaPaymentModes.KLARNA_INVOICE, KlarnaStatus.KLARNA_DENIED },
			{ KlarnapaymentTestConstants.STORE.NO, KlarnaPaymentModes.KLARNA_ACCOUNT, KlarnaStatus.KLARNA_ACCEPTED },
			{ KlarnapaymentTestConstants.STORE.NO, KlarnaPaymentModes.KLARNA_ACCOUNT, KlarnaStatus.KLARNA_DENIED },
			{ KlarnapaymentTestConstants.STORE.NO, KlarnaPaymentModes.KLARNA_CAMPAIGN, KlarnaStatus.KLARNA_ACCEPTED },
			{ KlarnapaymentTestConstants.STORE.NO, KlarnaPaymentModes.KLARNA_CAMPAIGN, KlarnaStatus.KLARNA_DENIED },

			{ KlarnapaymentTestConstants.STORE.SE, KlarnaPaymentModes.KLARNA_INVOICE, KlarnaStatus.KLARNA_ACCEPTED },
			{ KlarnapaymentTestConstants.STORE.SE, KlarnaPaymentModes.KLARNA_INVOICE, KlarnaStatus.KLARNA_DENIED },
			{ KlarnapaymentTestConstants.STORE.SE, KlarnaPaymentModes.KLARNA_ACCOUNT, KlarnaStatus.KLARNA_ACCEPTED },
			{ KlarnapaymentTestConstants.STORE.SE, KlarnaPaymentModes.KLARNA_ACCOUNT, KlarnaStatus.KLARNA_DENIED },
			{ KlarnapaymentTestConstants.STORE.SE, KlarnaPaymentModes.KLARNA_CAMPAIGN, KlarnaStatus.KLARNA_ACCEPTED },
			{ KlarnapaymentTestConstants.STORE.SE, KlarnaPaymentModes.KLARNA_CAMPAIGN, KlarnaStatus.KLARNA_DENIED },
		};

		return Arrays.asList(data);
	}

	@Test
	public void doTests()
		throws Exception {

		createDefaultCatalog();
		// import klarna specific test data
		importCsv("/klarnapayment/test/klarnaCartEURTestData.csv", "utf-8");
		importCsv("/klarnapayment/test/klarnaCartDKKTestData.csv", "utf-8");
		importCsv("/klarnapayment/test/klarnaCartSEKTestData.csv", "utf-8");
		importCsv("/klarnapayment/test/klarnaCartNOKTestData.csv", "utf-8");

		final Boolean logJUnitTestResults = configurationService.getConfiguration().getBoolean("klarna.junittest.logresult", Boolean.FALSE);
		if (logJUnitTestResults) {
			for (final AbstractReserveAmountTest test : TESTS) {
				LOG.info("******** Creating CSVLogFile ********************");
				test.createCSVLogFile();
				LOG.info("*************************************************");
			}
		}

		try {
			for (final Object[] testcase : testcases()) {
				final BaseStoreModel baseStore = getBaseStore((String) testcase[0]);
				sessionService.setAttribute("currentStore", baseStore);

				commonI18NService.setCurrentCurrency(baseStore.getDefaultCurrency());
				commonI18NService.setCurrentLanguage(baseStore.getDefaultLanguage());

				final PaymentModeModel paymentMode = getPaymentMode((String) testcase[1]);

				final DeliveryModeModel deliveryMode = getDeliveryMode("dhl");

				final String countryCode = baseStore.getUid().substring(baseStore.getUid().indexOf('-') + 1).toUpperCase();
				final CountryModel country = getCountry(countryCode);

				String username = null;
				if (testcase[2] == KlarnaStatus.KLARNA_ACCEPTED) {
					username = MessageFormat.format(KlarnapaymentTestConstants.USER.APPROVED, countryCode);
				} else {
					username = MessageFormat.format(KlarnapaymentTestConstants.USER.DENIED, countryCode);
				}
				final UserModel user = getUser(username);
				userService.setCurrentUser(user);

				final Integer pClassId = getPClassId(baseStore, country, (String) testcase[1]);
				for (final AbstractReserveAmountTest test : TESTS) {

					LOG.info("****************************");
					LOG.info("***** Start testcase ********");
					LOG.info("Basestore: " + baseStore.getUid());
					LOG.info("Country  : " + country.getIsocode());
					LOG.info("Payment  : " + paymentMode.getCode());
					LOG.info("Delivery  : " + deliveryMode.getCode());
					LOG.info("User:    : " + user.getName());
					LOG.info("Testclass: " + test.getClass());

					final ReserveAmountParams params = new ReserveAmountParams();
					params.setBaseStore(baseStore);
					params.setCountry(country);
					params.setUser(user);
					params.setPaymentMode(paymentMode);
					params.setDeliveryMode(deliveryMode);
					params.setPClassId(pClassId);
					params.setKlarnaStatus((KlarnaStatus) testcase[2]);
					test.reserveAmount(params);

					LOG.info("****************************");
					LOG.info("***** Ende testcase ********");
				}
			}
		} catch (final Exception e) {
			throw new Exception(e);
		} finally {
			if (logJUnitTestResults) {
				for (final AbstractReserveAmountTest test : TESTS) {
					LOG.info("******** Closing CSVLogFile ********************");
					test.closeCSVLogFile();
					LOG.info("*************************************************");
				}
			}
		}
	}

	private Integer getPClassId(final BaseStoreModel pBaseStore, final CountryModel pCountry, final String pPayment)
		throws GetitKlarnaException, KlarnaException {

		if ("klarnaInvoice".equals(pPayment)) {
			return null;
		}

		final KlarnaPClassService pClassService = (KlarnaPClassService) Registry.getApplicationContext().getBean("klarnaPClassService");
		pClassService.fetchPClasses(pBaseStore, pCountry, KlarnapaymentTestConstants.CLIENT_IP);

		final KlarnaDAO klarnaDAO = (KlarnaDAO) Registry.getApplicationContext().getBean(KlarnaDAO.BEAN_NAME);
		for (final KlarnaPClassModel klarnaPClass : klarnaDAO.getAllPClassForStoreAndCountry(pBaseStore, pCountry)) {
			if ("klarnaAccount".equals(pPayment) && klarnaPClass.getType() == KlarnaPClassType.ACCOUNT) {
				return klarnaPClass.getId();
			}
			if ("klarnaCampaign".equals(pPayment) && klarnaPClass.getType() == KlarnaPClassType.CAMPAIGN) {
				return klarnaPClass.getId();
			}
		}

		fail("No PClass found for " + pBaseStore.getUid() + ", " + pCountry.getIsocode() + ", " + pPayment);
		return null;
	}

}
