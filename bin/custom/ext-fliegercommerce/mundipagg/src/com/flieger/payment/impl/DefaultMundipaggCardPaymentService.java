/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.payment.impl;

import com.flieger.payment.MundipaggCardPaymentService;
import com.flieger.payment.commands.AuthorizationCaptureCommand;
import com.flieger.payment.commands.AuthorizationCaptureCommandWithInstantBuy;
import com.flieger.payment.commands.request.MundipaggAuthorizationCaptureRequest;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.commands.factory.CommandFactory;
import de.hybris.platform.payment.commands.factory.CommandNotSupportedException;
import de.hybris.platform.payment.commands.result.AuthorizationResult;
import de.hybris.platform.payment.methods.impl.DefaultCardPaymentServiceImpl;
/**
 *
 * @author Antony
 */
public class DefaultMundipaggCardPaymentService extends DefaultCardPaymentServiceImpl implements MundipaggCardPaymentService {
    
    
    public AuthorizationResult authorize(MundipaggAuthorizationCaptureRequest request)
			throws AdapterException
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
			AuthorizationCaptureCommandWithInstantBuy command = (AuthorizationCaptureCommandWithInstantBuy) commandFactory.createCommand(AuthorizationCaptureCommandWithInstantBuy.class);
			AuthorizationResult result = (AuthorizationResult) command.perform(request);
			result.setPaymentProvider(commandFactory.getPaymentProvider());

			return result;
		} catch (CommandNotSupportedException e)
		{
			throw new AdapterException(e.getMessage(), e);
		}
	}

   @Override
   public AuthorizationResult authorizeAndCreateStantBuyKey(MundipaggAuthorizationCaptureRequest request)
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
			AuthorizationCaptureCommand command = (AuthorizationCaptureCommand) commandFactory.createCommand(AuthorizationCaptureCommand.class);
			AuthorizationResult result = (AuthorizationResult) command.perform(request);
			result.setPaymentProvider(commandFactory.getPaymentProvider());

			return result;
		} catch (CommandNotSupportedException e)
		{
			throw new AdapterException(e.getMessage(), e);
		}
   }
    
}
