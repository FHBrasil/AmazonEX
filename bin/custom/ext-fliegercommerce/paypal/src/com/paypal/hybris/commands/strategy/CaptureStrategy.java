/**
 *
 */
package com.paypal.hybris.commands.strategy;

import de.hybris.platform.payment.commands.request.CaptureRequest;

/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
public interface CaptureStrategy
{
	public boolean allowCapture(CaptureRequest request);
}
