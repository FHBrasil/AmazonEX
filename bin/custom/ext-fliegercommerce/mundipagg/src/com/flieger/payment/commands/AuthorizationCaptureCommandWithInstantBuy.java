/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.payment.commands;

import com.flieger.payment.commands.request.MundipaggAuthorizationCaptureRequest;
import de.hybris.platform.payment.commands.Command;
import de.hybris.platform.payment.commands.result.AuthorizationResult;


/**
 *
 * @author Antony
 */
public interface AuthorizationCaptureCommandWithInstantBuy extends Command<MundipaggAuthorizationCaptureRequest, AuthorizationResult>{   
}
   

