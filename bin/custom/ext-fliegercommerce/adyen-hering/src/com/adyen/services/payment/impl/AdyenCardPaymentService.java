/**
 * 
 */
package com.adyen.services.payment.impl;

import de.hybris.platform.payment.methods.CardPaymentService;

import com.adyen.services.payment.AdyenAuthorizationRequest;
import com.adyen.services.payment.AdyenAuthorizationResult;
import com.adyen.services.payment.AdyenCaptureRequest;
import com.adyen.services.payment.AdyenCaptureResult;
import com.adyen.services.recurring.AdyenRecurringDetailsResult;
import com.adyen.services.recurring.AdyenRecurringListDetailsRequest;

/**
 * @author flieger
 *
 */
public interface AdyenCardPaymentService extends CardPaymentService
{
	public AdyenAuthorizationResult authorise(AdyenAuthorizationRequest request);
	public AdyenCaptureResult capture(AdyenCaptureRequest request);
	public AdyenRecurringDetailsResult listRecurringDetails(AdyenRecurringListDetailsRequest request);
}
