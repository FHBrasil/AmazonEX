/**
 * (c) copyright 2013 by getit GmbH
 *
 * @author gerald.bornemann, getit GmbH
 */
package de.getit.hybris.klarna.payment.core.service.impl;

import static org.junit.Assert.assertEquals;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.store.BaseStoreModel;

import org.junit.Before;
import org.junit.Test;

import com.klarna.api.KlarnaException;

import de.getit.hybris.klarna.payment.AbstractIntegrationTest;
import de.getit.hybris.klarna.payment.constants.KlarnapaymentTestConstants;
import de.getit.hybris.klarna.payment.core.dao.KlarnaDAO;
import de.getit.hybris.klarna.payment.core.exception.GetitKlarnaException;
import de.getit.hybris.klarna.payment.core.service.KlarnaPClassService;

/**
 * Integration test suite for {@link KlarnaPClassService}
 * 
 * @author gerald.bornemann, getit GmbH
 */
@IntegrationTest
public class GetitKlarnaPClassServiceTest
	extends AbstractIntegrationTest {

	private KlarnaPClassService klarnaPClassService;
	private KlarnaDAO klarnaDAO;

	@Before
	public void setUp()
		throws Exception {

		klarnaDAO = (KlarnaDAO) getBean(KlarnaDAO.BEAN_NAME);
		klarnaPClassService = (KlarnaPClassService) getBean(KlarnaPClassService.BEAN_NAME);
	}

	@Test
	public void testfetchPClasses_StoreDE_CountryDE() throws KlarnaException {
		fetchPClasses(getBaseStore(KlarnapaymentTestConstants.STORE.DE), getCountry(KlarnapaymentTestConstants.COUNTRY.DE), 1);
	}

	@Test
	public void testfetchPClasses_StoreSE_CountrySE() throws KlarnaException {
		fetchPClasses(getBaseStore(KlarnapaymentTestConstants.STORE.SE), getCountry(KlarnapaymentTestConstants.COUNTRY.SE), 2);
	}

	private void fetchPClasses(final BaseStoreModel baseStore, final CountryModel country, final int size)
		throws KlarnaException {

		try {
			klarnaPClassService.fetchPClasses(baseStore, country, KlarnapaymentTestConstants.CLIENT_IP);
		} catch (final GetitKlarnaException e) {
			e.printStackTrace();
		}

		assertEquals(klarnaDAO.getAllPClassForStoreAndCountry(baseStore, country).size(), size);
	}
}
