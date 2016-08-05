/**
 * 
 */
package de.fliegersoftware.amazon.payment.commands;

import de.hybris.platform.payment.commands.Command;

import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceResult;

/**
 * @author taylor.savegnago
 *
 */
public interface CloseOrderReferenceCommand extends Command<CloseOrderReferenceRequest, CloseOrderReferenceResult> {
	//
}
