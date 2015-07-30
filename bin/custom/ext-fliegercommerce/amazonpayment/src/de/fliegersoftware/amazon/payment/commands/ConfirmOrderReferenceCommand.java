/**
 * 
 */
package de.fliegersoftware.amazon.payment.commands;

import com.amazonservices.mws.offamazonpayments.model.ConfirmOrderReferenceRequest;

import de.hybris.platform.payment.commands.Command;
import de.hybris.platform.payment.commands.result.AbstractResult;

/**
 * @author taylor.savegnago
 *
 */
public interface ConfirmOrderReferenceCommand extends Command<ConfirmOrderReferenceRequest, AbstractResult> {
	//
}
