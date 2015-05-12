/**
 * 
 */
package com.flieger.adyen.command;

import de.hybris.platform.payment.commands.Command;

import com.adyen.services.payment.AdyenDebitAuthorizationRequest;
import com.adyen.services.payment.AdyenDebitAuthorizationResult;

/**
 * @author flieger
 *
 */
public interface AdyenDebitAuthorizationCommand extends Command<AdyenDebitAuthorizationRequest, AdyenDebitAuthorizationResult>
{
	AdyenDebitAuthorizationResult perform3D(AdyenDebitAuthorizationRequest authorizationRequest);
}
