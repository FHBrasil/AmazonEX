/**
 * 
 */
package com.paypal.hybris.service;


import static org.junit.Assert.assertNotNull;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;

import javax.annotation.Resource;

import org.junit.Test;


/**
 * @author Valentyn Markovych, Gorilla
 * 
 */
@IntegrationTest
public class PaypalServiceIntegrationTest extends ServicelayerTransactionalTest {

@Resource
private PaypalService paypalService;


@Test
public void serviceAvailable() {

	assertNotNull(paypalService);
}


}
