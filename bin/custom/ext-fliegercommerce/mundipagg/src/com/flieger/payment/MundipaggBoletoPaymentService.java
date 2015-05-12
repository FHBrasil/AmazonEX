/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.payment;

import com.flieger.payment.commands.request.MundipaggBoletoRequest;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.commands.result.AuthorizationResult;

/**
 *
 * @author Antony
 */
public interface MundipaggBoletoPaymentService
{

   public abstract AuthorizationResult authorize(MundipaggBoletoRequest paramAuthorizationRequest)
           throws AdapterException;

//   public abstract CaptureResult capture(CaptureRequest paramCaptureRequest)
//           throws AdapterException;
}
