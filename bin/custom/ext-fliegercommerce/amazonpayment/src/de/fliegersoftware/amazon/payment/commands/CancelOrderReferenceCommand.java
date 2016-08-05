/**
 * 
 */
package de.fliegersoftware.amazon.payment.commands;

import de.hybris.platform.payment.commands.Command;

import com.amazonservices.mws.offamazonpayments.model.CancelOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.CancelOrderReferenceResult;

/**
 * @author taylor.savegnago
 *
 */
public interface CancelOrderReferenceCommand extends Command<CancelOrderReferenceRequest, CancelOrderReferenceResult> {
	//
}
