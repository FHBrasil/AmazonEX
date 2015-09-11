/**
 * 
 */
package de.fliegersoftware.amazon.payment.commands;

import de.hybris.platform.payment.commands.Command;

import com.amazonservices.mws.offamazonpayments.model.CaptureRequest;
import com.amazonservices.mws.offamazonpayments.model.CaptureResult;

/**
 * @author taylor.savegnago
 *
 */
public interface CaptureCommand extends Command<CaptureRequest, CaptureResult> {
	//
}
