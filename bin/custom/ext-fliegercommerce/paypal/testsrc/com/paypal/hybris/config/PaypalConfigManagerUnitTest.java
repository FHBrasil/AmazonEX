package com.paypal.hybris.config;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.annotation.Resource;

import de.hybris.bootstrap.annotations.UnitTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.dao.PaypalConfigPropertyDao;
import com.paypal.hybris.model.PaypalConfigPropertyModel;


/**
 * WARNING! Due to static access to methods, this test is not truly isolated.
 * 
 * @author Valentyn Markovych, Gorilla
 * 
 */
@UnitTest
public class PaypalConfigManagerUnitTest {


private static final String PROP_NOT_FOUND = "Property not found";
private static final String PROP_UNKNOWN_ERROR = "Unknown property ";

private PaypalConfigPropertyDao oldPaypalConfigPropertyDao;

@Resource
private PaypalConfigManager paypalConfigManager;

@Mock
private PaypalConfigPropertyDao mockedDao;


@Before
public void before() {

	oldPaypalConfigPropertyDao = paypalConfigManager.getPaypalConfigPropertyDao();

	mockedDao = mock(PaypalConfigPropertyDao.class);

	final PaypalConfigPropertyModel propUser = new PaypalConfigPropertyModel();
	propUser.setKey(PaypalConstants.SETT_USERNAME);
	propUser.setValue(PaypalConstants.SETT_USERNAME);

	when(mockedDao.getPropertyByKey(PaypalConstants.SETT_USERNAME)).thenReturn(
			propUser);

	paypalConfigManager.setPaypalConfigPropertyDao(mockedDao);
}


@After
public void after() {

	paypalConfigManager.setPaypalConfigPropertyDao(oldPaypalConfigPropertyDao);
}


@Test
public void getPropertyTest() {

	// get common properties
	assertNotNull(PROP_NOT_FOUND + ": " + PaypalConstants.SETT_USERNAME,
			paypalConfigManager.getProperty(PaypalConstants.SETT_USERNAME));

	// get unknown property
	assertEquals(PROP_UNKNOWN_ERROR, "defaultValue",
			paypalConfigManager.getProperty("asldfjasldfjasfd", "defaultValue"));
}


@Test
public void getConfigPropertiesFromDBTest() {

	// get any property
	assertNotNull(PROP_NOT_FOUND + ": " + PaypalConstants.SETT_USERNAME,
			paypalConfigManager.getProperty(PaypalConstants.SETT_USERNAME));

	// ensure properties loaded from DB
	verify(mockedDao).getListOfProperties();
}


@Test
public void saveNewPropertiesToDBTest() {

	// get any property
	assertNotNull(PROP_NOT_FOUND + ": " + PaypalConstants.SETT_USERNAME,
			paypalConfigManager.getProperty(PaypalConstants.SETT_USERNAME));

	// ensure properties attempt to save to DB
	verify(mockedDao).addProperties(anyList());
}

}
