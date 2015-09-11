/**
 * 
 */
package com.flieger.adyen.command;

import de.hybris.platform.payment.commands.Command;

import com.adyen.services.payment.AdyenAuthorizationRequest;
import com.adyen.services.payment.AdyenAuthorizationResult;

/**
 * @author flieger
 *
 */
public interface AuthorizationCommand extends Command<AdyenAuthorizationRequest, AdyenAuthorizationResult> 
{
	public static final String DEFAULT_STORE_UID = "dzarm";
}
