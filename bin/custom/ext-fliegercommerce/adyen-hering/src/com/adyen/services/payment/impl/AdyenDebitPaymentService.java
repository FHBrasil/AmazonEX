/**
 * 
 */
package com.adyen.services.payment.impl;

import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.commands.request.CreateSubscriptionRequest;
import de.hybris.platform.payment.commands.result.SubscriptionResult;

import com.adyen.services.payment.AdyenDebitAuthorizationRequest;
import com.adyen.services.payment.AdyenDebitAuthorizationResult;
import com.adyen.services.payment.AdyenPaymentService;

/**
 * @author franthescollymaneira
 */
public interface AdyenDebitPaymentService 
extends AdyenPaymentService
{
	//passo 1
	AdyenDebitAuthorizationResult authorise(	final AdyenDebitAuthorizationRequest authorizationRequest );
	//passo 2
	AdyenDebitAuthorizationResult authorize3D(	final AdyenDebitAuthorizationRequest authorizationRequest);
//			,
//      													final BrowserInfo browserInfo, 
//      													final String selectedBrand, 
//      													final String subscriptionID) throws AdapterException;
	
	SubscriptionResult createSubscription( CreateSubscriptionRequest paramCreateSubscriptionRequest) throws AdapterException;
}