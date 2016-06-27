/**
 * 
 */
package de.fliegersoftware.amazon.payment.commands;

import de.hybris.platform.payment.commands.Command;

import com.amazonservices.mws.offamazonpayments.model.RefundRequest;
import com.amazonservices.mws.offamazonpayments.model.RefundResult;

/**
 * @author taylor.savegnago
 *
 */
public interface RefundCommand extends Command<RefundRequest, RefundResult> {
	//
}
