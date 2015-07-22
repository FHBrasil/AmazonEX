/**
 * (c) copyright 2013 by getit GmbH
 *
 * @author Sonja Bouwers, getit GmbH
 */
package de.getit.hybris.klarna.payment.core.service.impl;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.fail;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.store.BaseStoreModel;

import java.text.MessageFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.klarna.api.KlarnaException;

import de.getit.hybris.klarna.payment.AbstractIntegrationTest;
import de.getit.hybris.klarna.payment.constants.KlarnapaymentTestConstants;
import de.getit.hybris.klarna.payment.core.exception.GetitKlarnaException;
import de.getit.hybris.klarna.payment.core.exception.GetitKlarnaValidationException;
import de.getit.hybris.klarna.payment.core.service.KlarnaAccountService;
import de.getit.hybris.klarna.payment.core.service.KlarnaReservationService;
import de.getit.hybris.klarna.payment.core.strategy.impl.GetitKlarnaGetBaseParameterStrategy;

/**
 * Integration test suite for {@link KlarnaAccountService}
 * 
 * @author Sonja Bouwers, getit GmbH
 */
@IntegrationTest
public class GetitKlarnaAccountServiceTest extends AbstractIntegrationTest {

	private KlarnaAccountService mKlarnaAccountService;
	private KlarnaReservationService mKlarnaReservationService;

	@Before
	public void setUp() throws Exception {

		mKlarnaAccountService = (KlarnaAccountService) getBean(KlarnaAccountService.BEAN_NAME);
		mKlarnaReservationService = (KlarnaReservationService) getBean(KlarnaReservationService.BEAN_NAME);

	}

	@Test
	public void testHasAccount_DE()
		throws KlarnaException, GetitKlarnaException, GetitKlarnaValidationException {

		final String baseStoreUid = KlarnapaymentTestConstants.STORE.DE;
		final String countryCode = KlarnapaymentTestConstants.COUNTRY.DE;
		final String userUid = MessageFormat.format(KlarnapaymentTestConstants.USER.APPROVED, countryCode);
		final boolean result = testHasAccount(baseStoreUid, countryCode, userUid);
		assertFalse(result);
	}

	@Test
	public void testHasAccount_NL()
		throws KlarnaException, GetitKlarnaException, GetitKlarnaValidationException {

		final String baseStoreUid = KlarnapaymentTestConstants.STORE.NL;
		final String countryCode = KlarnapaymentTestConstants.COUNTRY.NL;
		final String userUid = MessageFormat.format(KlarnapaymentTestConstants.USER.APPROVED, countryCode);
		final boolean result = testHasAccount(baseStoreUid, countryCode, userUid);
		assertFalse(result);
	}

	@Test
	public void testHasAccount_AT()
		throws KlarnaException, GetitKlarnaException, GetitKlarnaValidationException {

		final String baseStoreUid = KlarnapaymentTestConstants.STORE.AT;
		final String countryCode = KlarnapaymentTestConstants.COUNTRY.AT;
		final String userUid = MessageFormat.format(KlarnapaymentTestConstants.USER.APPROVED, countryCode);
		final boolean result = testHasAccount(baseStoreUid, countryCode, userUid);
		assertFalse(result);
	}

	@Test
	public void testHasAccount_FI()
		throws KlarnaException, GetitKlarnaException, GetitKlarnaValidationException {

		final String baseStoreUid = KlarnapaymentTestConstants.STORE.FI;
		final String countryCode = KlarnapaymentTestConstants.COUNTRY.FI;
		final String userUid = MessageFormat.format(KlarnapaymentTestConstants.USER.APPROVED, countryCode);
		final boolean result = testHasAccount(baseStoreUid, countryCode, userUid);
		assertFalse(result);
	}

	@Test
	public void testHasAccount_NO()
		throws KlarnaException, GetitKlarnaException, GetitKlarnaValidationException {

		final String baseStoreUid = KlarnapaymentTestConstants.STORE.NO;
		final String countryCode = KlarnapaymentTestConstants.COUNTRY.NO;
		final String userUid = MessageFormat.format(KlarnapaymentTestConstants.USER.APPROVED, countryCode);
		final boolean result = testHasAccount(baseStoreUid, countryCode, userUid);
		assertFalse(result);
	}

	@Test
	public void testHasAccount_DK()
		throws KlarnaException, GetitKlarnaException, GetitKlarnaValidationException {

		final String baseStoreUid = KlarnapaymentTestConstants.STORE.NO;
		final String countryCode = KlarnapaymentTestConstants.COUNTRY.NO;
		final String userUid = MessageFormat.format(KlarnapaymentTestConstants.USER.APPROVED, countryCode);
		final boolean result = testHasAccount(baseStoreUid, countryCode, userUid);
		assertFalse(result);
	}

	@Test
	public void testHasAccount_SE()
		throws KlarnaException, GetitKlarnaException, GetitKlarnaValidationException {

		final String baseStoreUid = KlarnapaymentTestConstants.STORE.SE;
		final String countryCode = KlarnapaymentTestConstants.COUNTRY.SE;
		String userUid = MessageFormat.format(KlarnapaymentTestConstants.USER.APPROVED, countryCode);
		boolean result = testHasAccount(baseStoreUid, countryCode, userUid);
		assertTrue(result);
		userUid = MessageFormat.format(KlarnapaymentTestConstants.USER.DENIED, countryCode);
		result = testHasAccount(baseStoreUid, countryCode, userUid);
		assertFalse(result);
	}

	@Test
	public void testHasAccount_SE_wrong_pno()
		throws KlarnaException, GetitKlarnaException, GetitKlarnaValidationException {

		final String baseStoreUid = KlarnapaymentTestConstants.STORE.SE;
		final String countryCode = KlarnapaymentTestConstants.COUNTRY.SE;
		final String userUid = MessageFormat.format(KlarnapaymentTestConstants.USER.APPROVED, countryCode);
		final String pno = "1234";
		final boolean result = testHasAccount(baseStoreUid, countryCode, userUid, pno);
		assertFalse(result);
	}

	@Test(expected = GetitKlarnaValidationException.class)
	public void testHasAccount_SE_pno_null()
		throws KlarnaException, GetitKlarnaException, GetitKlarnaValidationException {

		final String baseStoreUid = KlarnapaymentTestConstants.STORE.SE;
		final String countryCode = KlarnapaymentTestConstants.COUNTRY.SE;
		final String userUid = MessageFormat.format(KlarnapaymentTestConstants.USER.APPROVED, countryCode);

		final String pno = null;
		final boolean result = testHasAccount(baseStoreUid, countryCode, userUid, pno);
		assertFalse(result);
	}

	@Test(expected = GetitKlarnaValidationException.class)
	public void testHasAccount_SE_pno_empty()
		throws KlarnaException, GetitKlarnaException, GetitKlarnaValidationException {

		final String baseStoreUid = KlarnapaymentTestConstants.STORE.SE;
		final String countryCode = KlarnapaymentTestConstants.COUNTRY.SE;
		final String userUid = MessageFormat.format(KlarnapaymentTestConstants.USER.APPROVED, countryCode);

		final String pno = "";
		final boolean result = testHasAccount(baseStoreUid, countryCode, userUid, pno);
		assertFalse(result);
	}

	private boolean testHasAccount(final String baseStoreUid, final String countryCode, final String userUid) throws GetitKlarnaException, GetitKlarnaValidationException,
		KlarnaException {

		final BaseStoreModel baseStore = getBaseStore(baseStoreUid);
		sessionService.setAttribute("currentStore", baseStore);
		sessionService.setAttribute("currentSite", baseStore.getCmsSites().iterator().next());
		sessionService.setAttribute(GetitKlarnaGetBaseParameterStrategy.GETITBASESTORESESSIONNAME, baseStore);

		commonI18NService.setCurrentCurrency(baseStore.getDefaultCurrency());
		commonI18NService.setCurrentLanguage(baseStore.getDefaultLanguage());

		final CountryModel country = getCountry(countryCode);

		final UserModel user = getUser(userUid);
		userService.setCurrentUser(user);

		final String pno = mKlarnaReservationService.determinePno(user.getDefaultPaymentAddress(), baseStore, country);

		return mKlarnaAccountService.hasAccount(pno, KlarnapaymentTestConstants.CLIENT_IP);
	}

	private boolean testHasAccount(final String baseStoreUid, final String countryCode, final String userUid, final String pno) throws GetitKlarnaException, GetitKlarnaValidationException,
		KlarnaException {

		final BaseStoreModel baseStore = getBaseStore(baseStoreUid);
		sessionService.setAttribute("currentStore", baseStore);
		sessionService.setAttribute("currentSite", baseStore.getCmsSites().iterator().next());
		sessionService.setAttribute(GetitKlarnaGetBaseParameterStrategy.GETITBASESTORESESSIONNAME, baseStore);

		commonI18NService.setCurrentCurrency(baseStore.getDefaultCurrency());
		commonI18NService.setCurrentLanguage(baseStore.getDefaultLanguage());

		final UserModel user = getUser(userUid);
		userService.setCurrentUser(user);

		return mKlarnaAccountService.hasAccount(pno, KlarnapaymentTestConstants.CLIENT_IP);

	}

	@Test
	public void testGetAddresses_DE()
		throws KlarnaException, GetitKlarnaException, GetitKlarnaValidationException {

		final String baseStoreUid = KlarnapaymentTestConstants.STORE.DE;
		final String countryCode = KlarnapaymentTestConstants.COUNTRY.DE;
		final String userUid = MessageFormat.format(KlarnapaymentTestConstants.USER.APPROVED, countryCode);
		final List<AddressModel> result = testGetAddresses(baseStoreUid, countryCode, userUid);
		assertTrue(result.isEmpty());
	}

	@Test
	public void testGetAddresses_NL()
		throws KlarnaException, GetitKlarnaException, GetitKlarnaValidationException {

		final String baseStoreUid = KlarnapaymentTestConstants.STORE.NL;
		final String countryCode = KlarnapaymentTestConstants.COUNTRY.NL;
		final String userUid = MessageFormat.format(KlarnapaymentTestConstants.USER.APPROVED, countryCode);
		final List<AddressModel> result = testGetAddresses(baseStoreUid, countryCode, userUid);
		assertTrue(result.isEmpty());
	}

	@Test
	public void testGetAddresses_AT()
		throws KlarnaException, GetitKlarnaException, GetitKlarnaValidationException {

		final String baseStoreUid = KlarnapaymentTestConstants.STORE.AT;
		final String countryCode = KlarnapaymentTestConstants.COUNTRY.AT;
		final String userUid = MessageFormat.format(KlarnapaymentTestConstants.USER.APPROVED, countryCode);
		final List<AddressModel> result = testGetAddresses(baseStoreUid, countryCode, userUid);
		assertTrue(result.isEmpty());
	}

	@Test
	public void testGetAddresses_FI()
		throws KlarnaException, GetitKlarnaException, GetitKlarnaValidationException {

		final String baseStoreUid = KlarnapaymentTestConstants.STORE.FI;
		final String countryCode = KlarnapaymentTestConstants.COUNTRY.FI;
		final String userUid = MessageFormat.format(KlarnapaymentTestConstants.USER.APPROVED, countryCode);
		final List<AddressModel> result = testGetAddresses(baseStoreUid, countryCode, userUid);
		assertTrue(result.isEmpty());
	}

	@Test
	public void testGetAddresses_NO()
		throws KlarnaException, GetitKlarnaException, GetitKlarnaValidationException {

		final String baseStoreUid = KlarnapaymentTestConstants.STORE.NO;
		final String countryCode = KlarnapaymentTestConstants.COUNTRY.NO;
		final String userUid = MessageFormat.format(KlarnapaymentTestConstants.USER.APPROVED, countryCode);
		final List<AddressModel> result = testGetAddresses(baseStoreUid, countryCode, userUid);
		assertTrue(result.isEmpty());
	}

	@Test
	public void testGetAddresses_DK()
		throws KlarnaException, GetitKlarnaException, GetitKlarnaValidationException {

		final String baseStoreUid = KlarnapaymentTestConstants.STORE.DK;
		final String countryCode = KlarnapaymentTestConstants.COUNTRY.DK;
		final String userUid = MessageFormat.format(KlarnapaymentTestConstants.USER.APPROVED, countryCode);
		final List<AddressModel> result = testGetAddresses(baseStoreUid, countryCode, userUid);
		assertTrue(result.isEmpty());
	}

	@Test
	public void testGetAddresses_SE()
		throws KlarnaException, GetitKlarnaException, GetitKlarnaValidationException {

		final String baseStoreUid = KlarnapaymentTestConstants.STORE.SE;
		final String countryCode = KlarnapaymentTestConstants.COUNTRY.SE;
		final String userUid = MessageFormat.format(KlarnapaymentTestConstants.USER.APPROVED, countryCode);
		final List<AddressModel> result = testGetAddresses(baseStoreUid, countryCode, userUid);
		assertFalse(result.isEmpty());
	}

	@Test(expected = KlarnaException.class)
	public void testGetAddresses_SE_wrong_pno()
		throws KlarnaException, GetitKlarnaException, GetitKlarnaValidationException {

		final String baseStoreUid = KlarnapaymentTestConstants.STORE.SE;
		final String countryCode = KlarnapaymentTestConstants.COUNTRY.SE;
		final String userUid = MessageFormat.format(KlarnapaymentTestConstants.USER.APPROVED, countryCode);
		testGetAddresses(baseStoreUid, countryCode, userUid, "1234");
		fail("Exception expected");
	}

	@Test(expected = KlarnaException.class)
	public void testGetAddresses_SE_pno_null()
		throws KlarnaException, GetitKlarnaException, GetitKlarnaValidationException {

		final String baseStoreUid = KlarnapaymentTestConstants.STORE.SE;
		final String countryCode = KlarnapaymentTestConstants.COUNTRY.SE;
		final String userUid = MessageFormat.format(KlarnapaymentTestConstants.USER.APPROVED, countryCode);
		testGetAddresses(baseStoreUid, countryCode, userUid, null);
		fail("Exception expected");
	}

	@Test(expected = KlarnaException.class)
	public void testGetAddresses_SE_pno_empty()
		throws KlarnaException, GetitKlarnaException, GetitKlarnaValidationException {

		final String baseStoreUid = KlarnapaymentTestConstants.STORE.SE;
		final String countryCode = KlarnapaymentTestConstants.COUNTRY.SE;
		final String userUid = MessageFormat.format(KlarnapaymentTestConstants.USER.APPROVED, countryCode);
		testGetAddresses(baseStoreUid, countryCode, userUid, "");
		fail("Exception expected");
	}

	private List<AddressModel> testGetAddresses(final String baseStoreUid, final String countryCode, final String userUid) throws GetitKlarnaException, GetitKlarnaValidationException,
		KlarnaException {

		final BaseStoreModel baseStore = getBaseStore(baseStoreUid);
		sessionService.setAttribute("currentStore", baseStore);
		sessionService.setAttribute("currentSite", baseStore.getCmsSites().iterator().next());
		sessionService.setAttribute(GetitKlarnaGetBaseParameterStrategy.GETITBASESTORESESSIONNAME, baseStore);

		commonI18NService.setCurrentCurrency(baseStore.getDefaultCurrency());
		commonI18NService.setCurrentLanguage(baseStore.getDefaultLanguage());

		final CountryModel country = getCountry(countryCode);

		final UserModel user = getUser(userUid);
		userService.setCurrentUser(user);

		final String pno = mKlarnaReservationService.determinePno(user.getDefaultPaymentAddress(), baseStore, country);

		return mKlarnaAccountService.getAddresses(pno, KlarnapaymentTestConstants.CLIENT_IP);
	}

	private List<AddressModel> testGetAddresses(final String baseStoreUid, final String countryCode, final String userUid, final String pno)
		throws GetitKlarnaException, GetitKlarnaValidationException, KlarnaException {

		final BaseStoreModel baseStore = getBaseStore(baseStoreUid);
		sessionService.setAttribute("currentStore", baseStore);
		sessionService.setAttribute("currentSite", baseStore.getCmsSites().iterator().next());
		sessionService.setAttribute(GetitKlarnaGetBaseParameterStrategy.GETITBASESTORESESSIONNAME, baseStore);

		commonI18NService.setCurrentCurrency(baseStore.getDefaultCurrency());
		commonI18NService.setCurrentLanguage(baseStore.getDefaultLanguage());

		final CountryModel country = getCountry(countryCode);

		final UserModel user = getUser(userUid);
		userService.setCurrentUser(user);

		return mKlarnaAccountService.getAddresses(pno, KlarnapaymentTestConstants.CLIENT_IP);
	}
}
