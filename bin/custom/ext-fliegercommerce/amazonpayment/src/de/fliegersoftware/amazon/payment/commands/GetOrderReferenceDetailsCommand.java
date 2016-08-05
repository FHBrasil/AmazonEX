/**
 * 
 */
package de.fliegersoftware.amazon.payment.commands;

import de.hybris.platform.payment.commands.Command;

import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsResult;

/**
 * @author taylor.savegnago
 *
 */
public interface GetOrderReferenceDetailsCommand extends Command<GetOrderReferenceDetailsRequest, GetOrderReferenceDetailsResult> {
	//
}
