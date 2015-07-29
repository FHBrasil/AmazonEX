/******************************************************************************
 *  Copyright 2011 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at:
 *  http://aws.amazon.com/apache2.0
 *  This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 *  CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the
 *  specific language governing permissions and limitations under the
 *  License.
 * ****************************************************************************
 */
package de.fliegersoftware.amazon.payment.util;

import java.util.Properties;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsService;
import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceClient;
import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceConfig;

public class AmazonConfig {

	public static final String ACCESS_KEY_ID = "accessKeyId";
	public static final String CURRENCY = "currency";
	public static final String SECRET_ACCESS_KEY = "secretAccessKey";
	public static final String APPLICATION_NAME = "applicationName";
	public static final String APPLICATION_VERSION = "applicationVersion";
	public static final String SELLER_ID = "sellerId";
	public static final String ENVIRONMENT = "environment";
	public static final String REGION = "region";
	public static final String PLACE_ORDER_URL = "placeOrderUrl";
	public static final String CERT_CN = "certCN";
	
	private enum AMAZON_CREDENTIALS {
		SANDBOX_LOCAL {
			@Override
			public Properties getProperties() {
				Properties properties = new Properties();
				properties.put(ACCESS_KEY_ID, "AKIAIFFB2ZRRS4MGDGXA");
				properties.put(SECRET_ACCESS_KEY, "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");
				properties.put(APPLICATION_NAME, "KPFamily Sandbox");
				properties.put(APPLICATION_VERSION, "");
				properties.put(SELLER_ID, "A2CE40B6BVZ5XY");
				properties.put(CURRENCY, "EUR");
				//IMPORTANT: Use "LIVE" for Production or "SANBOX" for Sandbox use:
				properties.put(ENVIRONMENT, "SANDBOX");
				properties.put(REGION, "DE");
				properties.put(PLACE_ORDER_URL, "http://localhost:9001/");
				properties.put(CERT_CN, "sns.amazonaws.com");
				return properties;
			}
		},
		SANDBOX {
			@Override
			public Properties getProperties() {
				Properties properties = new Properties();
				properties.put(ACCESS_KEY_ID, "AKIAIFFB2ZRRS4MGDGXA");
				properties.put(SECRET_ACCESS_KEY, "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");
				properties.put(APPLICATION_NAME, "KPFamily Sandbox");
				properties.put(APPLICATION_VERSION, "");
				properties.put(SELLER_ID, "A2CE40B6BVZ5XY");
				properties.put(CURRENCY, "EUR");
				//IMPORTANT: Use "LIVE" for Production or "SANBOX" for Sandbox use:
				properties.put(ENVIRONMENT, "SANDBOX");
				properties.put(REGION, "DE");
				properties.put(PLACE_ORDER_URL, "http://www2.babyartikel.de:9001/");
				properties.put(CERT_CN, "sns.amazonaws.com");
				return properties;
			}
		},
		LIVE {
			@Override
			public Properties getProperties() {
				Properties properties = new Properties();
				properties.put(ACCESS_KEY_ID, "AKIAIFFB2ZRRS4MGDGXA");
				properties.put(SECRET_ACCESS_KEY, "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");
				properties.put(APPLICATION_NAME, "");
				properties.put(APPLICATION_VERSION, "");
				properties.put(SELLER_ID, "A2CE40B6BVZ5XY");
				properties.put(CURRENCY, "EUR");
				//IMPORTANT: Use "LIVE" for Production or "SANBOX" for Sandbox use:
				properties.put(ENVIRONMENT, "LIVE");
				properties.put(REGION, "DE");
				properties.put(PLACE_ORDER_URL, "http://www.babyartikel.de/");
				properties.put(CERT_CN, "sns.amazonaws.com");
				return properties;
			}
		};
		public abstract Properties getProperties();
		
		private OffAmazonPaymentsServiceConfig config;
		private OffAmazonPaymentsService service;
		
		private AMAZON_CREDENTIALS() {
			config = new OffAmazonPaymentsServiceConfig(this.getProperties());
			service = new OffAmazonPaymentsServiceClient(config);
		}
		
		public OffAmazonPaymentsServiceConfig getAmazonPaymentConfig() {
			return config;
		}
		
		@SuppressWarnings("unchecked")
		public  <T extends OffAmazonPaymentsService> T getAmazonPaymentService() {
			return (T) service;
		}
		
	}

	public static final AMAZON_CREDENTIALS CREDENTIALS = AMAZON_CREDENTIALS.SANDBOX_LOCAL;
	
	public static OffAmazonPaymentsServiceConfig getAmazonPaymentConfig() {
		return CREDENTIALS.getAmazonPaymentConfig();
	}
	
	public static <T extends OffAmazonPaymentsService> T getAmazonPaymentService() {
		return CREDENTIALS.getAmazonPaymentService();
	}
	
	public static Properties getProperties() {
		return CREDENTIALS.getProperties();
	}
	
	public static String getPlaceOrderUrl() {
		return getProperties().getProperty(PLACE_ORDER_URL);
	}
	
}
