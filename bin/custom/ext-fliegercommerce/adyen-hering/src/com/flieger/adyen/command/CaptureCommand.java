/**
 * 
 */
package com.flieger.adyen.command;

import de.hybris.platform.payment.commands.Command;

import com.adyen.services.payment.AdyenCaptureRequest;
import com.adyen.services.payment.AdyenCaptureResult;

/**
 * @author flieger
 *
 */
public interface CaptureCommand extends Command<AdyenCaptureRequest, AdyenCaptureResult> {
	//
}
