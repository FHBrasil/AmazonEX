/**
 * 
 */
package com.flieger.adyen.command;

import de.hybris.platform.payment.commands.Command;

import com.adyen.services.payment.AdyenBoletoAuthorizationRequest;
import com.adyen.services.payment.AdyenBoletoAuthorizationResult;

/**
 * @author flieger
 *
 */
public interface BoletoCommand extends Command<AdyenBoletoAuthorizationRequest, AdyenBoletoAuthorizationResult>
{
//
}
