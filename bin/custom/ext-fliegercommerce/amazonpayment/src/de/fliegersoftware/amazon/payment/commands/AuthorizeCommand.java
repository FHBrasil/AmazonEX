/**
 * 
 */
package de.fliegersoftware.amazon.payment.commands;

import de.hybris.platform.payment.commands.Command;

import com.amazonservices.mws.offamazonpayments.model.AuthorizeRequest;
import com.amazonservices.mws.offamazonpayments.model.AuthorizeResult;

/**
 * @author taylor.savegnago
 *
 */
public interface AuthorizeCommand extends Command<AuthorizeRequest, AuthorizeResult> {
	//
}
