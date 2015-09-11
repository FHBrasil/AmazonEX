/**
 * 
 */
package de.fliegersoftware.amazon.payment.commands;

import de.hybris.platform.payment.commands.Command;

import com.amazonservices.mws.offamazonpayments.model.CloseAuthorizationRequest;
import com.amazonservices.mws.offamazonpayments.model.CloseAuthorizationResult;

/**
 * @author taylor.savegnago
 *
 */
public interface CloseAuthorizationCommand extends Command<CloseAuthorizationRequest, CloseAuthorizationResult> {
	//
}
