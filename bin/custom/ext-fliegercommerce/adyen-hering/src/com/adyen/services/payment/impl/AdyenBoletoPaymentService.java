/**
 * 
 */
package com.adyen.services.payment.impl;

import com.adyen.services.payment.AdyenBoletoAuthorizationRequest;
import com.adyen.services.payment.AdyenBoletoAuthorizationResult;

/**
 * @author flieger
 *
 */
public interface AdyenBoletoPaymentService
{
	/**
	 * @param request
	 * @return AdyenBoletoAuthorizationResult
	 */
	public AdyenBoletoAuthorizationResult authorise(AdyenBoletoAuthorizationRequest request);
}
