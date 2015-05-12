/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.payment.impl;

import com.flieger.payment.MundipaggBoletoPaymentService;
import com.flieger.payment.commands.AuthorizationBoletoCommand;
import com.flieger.payment.commands.request.MundipaggBoletoRequest;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.commands.factory.CommandFactory;
import de.hybris.platform.payment.commands.factory.CommandFactoryRegistry;
import de.hybris.platform.payment.commands.factory.CommandNotSupportedException;
import de.hybris.platform.payment.commands.result.AuthorizationResult;

/**
 *
 * @author Antony
 */
public class DefaultMundipaggBoletoPaymentService implements MundipaggBoletoPaymentService
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
   
   @Override
   public AuthorizationResult authorize(MundipaggBoletoRequest request) throws AdapterException
   {
      try
		{
			CommandFactory commandFactory;

			if (request.getPaymentProvider() == null)
			{
				commandFactory = getCommandFactoryRegistry().getFactory(null, false);
			} else 
			{
				commandFactory = getCommandFactoryRegistry().getFactory(request.getPaymentProvider());
			}
			AuthorizationBoletoCommand command = (AuthorizationBoletoCommand) commandFactory.createCommand(AuthorizationBoletoCommand.class);
			AuthorizationResult result = (AuthorizationResult) command.perform(request);
			result.setPaymentProvider(commandFactory.getPaymentProvider());

			return result;
		} catch (CommandNotSupportedException e)
		{
			throw new AdapterException(e.getMessage(), e);
		}
   }
   
}
