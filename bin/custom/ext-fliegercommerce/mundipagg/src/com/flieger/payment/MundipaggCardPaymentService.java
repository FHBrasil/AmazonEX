/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.payment;

import com.flieger.payment.commands.request.MundipaggAuthorizationCaptureRequest;
import de.hybris.platform.payment.commands.result.AuthorizationResult;
import de.hybris.platform.payment.methods.CardPaymentService;

/**
 *
 * @author Antony
 */
public interface MundipaggCardPaymentService extends CardPaymentService {

    public AuthorizationResult authorize(MundipaggAuthorizationCaptureRequest request);

    public AuthorizationResult authorizeAndCreateStantBuyKey(MundipaggAuthorizationCaptureRequest request);
			
}
