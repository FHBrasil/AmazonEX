/**
 * 
 */
package com.adyen.services.payment.impl;

import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.commands.factory.CommandFactory;
import de.hybris.platform.payment.commands.factory.CommandFactoryRegistry;
import de.hybris.platform.payment.commands.factory.CommandNotSupportedException;

import com.adyen.services.payment.AdyenBoletoAuthorizationRequest;
import com.adyen.services.payment.AdyenBoletoAuthorizationResult;
import com.flieger.adyen.command.BoletoCommand;

/**
 * @author flieger
 *
 */
public class AdyenBoletoPaymentServiceImpl implements AdyenBoletoPaymentService
{
   private CommandFactoryRegistry commandFactoryRegistry;

	public void setCommandFactoryRegistry(CommandFactoryRegistry commandFactoryRegistry)
	{
		this.commandFactoryRegistry = commandFactoryRegistry;
	}

	public CommandFactoryRegistry getCommandFactoryRegistry()
	{
		return this.commandFactoryRegistry;
	}
	/* (non-Javadoc)
	 * @see com.adyen.services.payment.impl.AdyenBoletoPaymentService#authorise(com.adyen.services.payment.AdyenAuthorizationRequest)
	 */
	@Override
	public AdyenBoletoAuthorizationResult authorise(AdyenBoletoAuthorizationRequest request)
	{
		AdyenBoletoAuthorizationResult result = null;
		try
		{
			CommandFactory commandFactory;

			if (request.getPaymentProvider() == null)
			{
				commandFactory = getCommandFactoryRegistry().getFactory(null, false);
			}
			else
			{
				commandFactory = getCommandFactoryRegistry().getFactory(request.getPaymentProvider());
			}
			BoletoCommand boletoCommand = commandFactory.createCommand(BoletoCommand.class);
			result = boletoCommand.perform(request);
			
		}
		catch (CommandNotSupportedException e)
		{
			throw new AdapterException(e.getMessage(), e);
		}
		return result;
	}

}
