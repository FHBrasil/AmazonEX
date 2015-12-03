/**
 * 
 */
package de.fliegersoftware.amazon.payment.commands;

import de.hybris.platform.payment.commands.Command;

import com.amazonservices.mws.offamazonpayments.model.SetOrderReferenceDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.SetOrderReferenceDetailsResult;

/**
 * @author taylor.savegnago
 *
 */
public interface SetOrderReferenceDetailsCommand extends Command<SetOrderReferenceDetailsRequest, SetOrderReferenceDetailsResult> {
	//
}
