/**
 * 
 */
package de.fliegersoftware.amazon.payment.commands;

import de.hybris.platform.payment.commands.Command;

import com.amazonservices.mws.offamazonpayments.model.GetRefundDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetRefundDetailsResult;

/**
 * @author taylor.savegnago
 *
 */
public interface GetRefundDetailsCommand extends Command<GetRefundDetailsRequest, GetRefundDetailsResult> {
	//
}
