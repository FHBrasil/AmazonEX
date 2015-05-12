package com.paypal.hybris.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.paypal.hybris.dao.PaypalConfigPropertyDao;
import com.paypal.hybris.model.PaypalConfigPropertyModel;

public final class PaypalConfigManager {

	private boolean isInit = false;

	private static final Logger LOG = Logger.getLogger(PaypalConfigManager.class);

	private static final String PAYPAL_PROP_FILE_NAME = "/paypal.properties";

	private static Properties properties;

	@Resource
	private PaypalConfigPropertyDao paypalConfigPropertyDao;

	public PaypalConfigManager() {
		// Protected constructor for testing capabilities.
	}

	private static void loadSDKProperties(final String filename) {

		try {
			final InputStream inputStream = PaypalConfigManager.class
					.getResourceAsStream(filename);
			properties = new Properties();
			properties.load(inputStream);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}

	}

	public String getProperty(final String key) {

		return getProperty(key, null);
	}

	public String getProperty(final String key, final String defaultValue) {

		if (!isInit) {
			initProperties();
			isInit = true;
		}

		final PaypalConfigPropertyModel prop = paypalConfigPropertyDao.getPropertyByKey(key);
		if (prop != null) {
			return prop.getValue();
		} else {
			return defaultValue;
		}
	}

	private List<PaypalConfigPropertyModel> getConfigPropertiesFromDB() {
		return paypalConfigPropertyDao.getListOfProperties();
	}

	private void overrideByPropertiesFromDB(final Properties props,
			final List<PaypalConfigPropertyModel> list) {

		for (final PaypalConfigPropertyModel pair : list) {
			props.setProperty(pair.getKey(), pair.getValue());
		}
	}

	private void saveNewPropertiesToDB(final Properties props,
			final List<PaypalConfigPropertyModel> propertiesFromDB) {

		final Set<String> keysFromDB = new HashSet<String>();
		for (final PaypalConfigPropertyModel model : propertiesFromDB) {
			keysFromDB.add(model.getKey());
		}
		final List<PaypalConfigPropertyModel> listOfNewProps = new ArrayList<PaypalConfigPropertyModel>();
		for (final Object keyObj : props.keySet()) {
			final String key = keyObj.toString();
			if (!keysFromDB.contains(key)) {
				final PaypalConfigPropertyModel m = new PaypalConfigPropertyModel();
				m.setKey(key);
				m.setValue(props.getProperty(key));
				listOfNewProps.add(m);
				if (LOG.isDebugEnabled()) {
					LOG.debug("new config property is adding to DB -- (" + key
							+ "," + props.get(key) + ")");
				}
			}
		}
		paypalConfigPropertyDao.addProperties(listOfNewProps);
	}

	private void initProperties() {

		LOG.debug("PaypalConfigManager.initProperties");
		loadSDKProperties(PaypalConfigManager.PAYPAL_PROP_FILE_NAME);

		final List<PaypalConfigPropertyModel> propertiesFromDB = getConfigPropertiesFromDB();
		if (propertiesFromDB != null) {
			saveNewPropertiesToDB(properties, propertiesFromDB);
			overrideByPropertiesFromDB(properties, propertiesFromDB);
		}

	}

	// refresh old properties in DB by properties from paypal.properties file
	// and add new properties
	public void updateDbProperties() {

		// LOG.info("PaypalConfigManager.updatePropertiesFromDB");
		loadSDKProperties(PaypalConfigManager.PAYPAL_PROP_FILE_NAME);
		final List<PaypalConfigPropertyModel> propsFromDB = getConfigPropertiesFromDB();
		final Map<String, String> mainProps = new HashMap<String, String>();
		for (final PaypalConfigPropertyModel prop : propsFromDB) {
			mainProps.put(prop.getKey(), prop.getValue());
		}
		for (final Object keyObj : properties.keySet()) {
			final String key = keyObj.toString();
			final String value = properties.getProperty(key);
			mainProps.put(key, value);
		}
		final List<PaypalConfigPropertyModel> mainList = new ArrayList<PaypalConfigPropertyModel>();
		for (final String key : mainProps.keySet()) {
			final PaypalConfigPropertyModel m = new PaypalConfigPropertyModel();
			m.setKey(key);
			m.setValue(mainProps.get(key));
			mainList.add(m);
		}

		paypalConfigPropertyDao.removeAll();
		paypalConfigPropertyDao.addProperties(mainList);

	}

	// clear all properties from DB and put all properties from
	// paypal.properties file to DB
	public void rewriteDbProperties() {

		LOG.debug("PaypalConfigManager.rewritePropertiesFromDB");

		paypalConfigPropertyDao.removeAll();
		updateDbProperties();

	}

	public String getPaypalPropFileName() {
		return PAYPAL_PROP_FILE_NAME;
	}
	
	public void setPaypalConfigPropertyDao(final PaypalConfigPropertyDao paypalConfigPropertyDao) {
		this.paypalConfigPropertyDao = paypalConfigPropertyDao;
	}

	protected PaypalConfigPropertyDao getPaypalConfigPropertyDao() {
		return paypalConfigPropertyDao;
	}


}
