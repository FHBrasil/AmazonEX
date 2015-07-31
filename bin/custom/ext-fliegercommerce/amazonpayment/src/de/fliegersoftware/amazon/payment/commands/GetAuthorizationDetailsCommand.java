/**
 * 
 */
package de.fliegersoftware.amazon.payment.commands;

import de.hybris.platform.payment.commands.Command;

import com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsResult;

/**
 * @author taylor.savegnago
 *
 */
public interface GetAuthorizationDetailsCommand extends Command<GetAuthorizationDetailsRequest, GetAuthorizationDetailsResult> {
	//
}
