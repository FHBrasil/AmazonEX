/**
 * 
 */
package de.fliegersoftware.amazon.payment.commands;

import de.hybris.platform.payment.commands.Command;

import com.amazonservices.mws.offamazonpayments.model.GetCaptureDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetCaptureDetailsResult;

/**
 * @author taylor.savegnago
 *
 */
public interface GetCaptureDetailsCommand extends Command<GetCaptureDetailsRequest, GetCaptureDetailsResult> {
	//
}
