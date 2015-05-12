/**
 * 
 */
package com.flieger.adyen.command;

import de.hybris.platform.payment.commands.Command;

import com.adyen.services.recurring.AdyenRecurringDetailsResult;
import com.adyen.services.recurring.AdyenRecurringListDetailsRequest;

/**
 * @author flieger
 *
 */
public interface RecurringDetailsCommand extends Command<AdyenRecurringListDetailsRequest, AdyenRecurringDetailsResult> {
	//
}
