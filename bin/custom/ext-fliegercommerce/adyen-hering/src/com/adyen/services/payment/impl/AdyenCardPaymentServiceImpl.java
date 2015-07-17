/**
 * 
 */
package com.adyen.services.payment.impl;

import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.commands.factory.CommandFactory;
import de.hybris.platform.payment.commands.factory.CommandNotSupportedException;
import de.hybris.platform.payment.methods.impl.DefaultCardPaymentServiceImpl;

import com.adyen.services.payment.AdyenAuthorizationRequest;
import com.adyen.services.payment.AdyenAuthorizationResult;
import com.adyen.services.payment.AdyenCaptureRequest;
import com.adyen.services.payment.AdyenCaptureResult;
import com.adyen.services.recurring.AdyenRecurringDetailsResult;
import com.adyen.services.recurring.AdyenRecurringListDetailsRequest;
import com.adyen.services.recurring.RecurringDetailsRequest;
import com.adyen.services.recurring.RecurringDetailsResult;
import com.flieger.adyen.command.AuthorizationCommand;
import com.flieger.adyen.command.CaptureCommand;
import com.flieger.adyen.command.RecurringDetailsCommand;
import com.flieger.adyen.commands.impl.AdyenCaptureCommand;


/**
 * @author flieger
 * 
 */
public class AdyenCardPaymentServiceImpl extends DefaultCardPaymentServiceImpl implements AdyenCardPaymentService
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.adyen.services.payment.impl.AdyenCardPaymentService#authorize()
	 */
	@Override
	public AdyenAuthorizationResult authorise(AdyenAuthorizationRequest request)
	{
		AdyenAuthorizationResult result = null;
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
			AuthorizationCommand authorizationCommand = commandFactory.createCommand(AuthorizationCommand.class);
			result = authorizationCommand.perform(request);
			
		}
		catch (CommandNotSupportedException e)
		{
			throw new AdapterException(e.getMessage(), e);
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see de.hybris.platform.payment.methods.impl.DefaultCardPaymentServiceImpl#capture(de.hybris.platform.payment.commands.request.CaptureRequest)
	 */
	@Override
	public AdyenCaptureResult capture(AdyenCaptureRequest request) throws AdapterException
	{
		AdyenCaptureResult result = null;
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
			CaptureCommand captureCommand = commandFactory.createCommand(AdyenCaptureCommand.class);
			result = captureCommand.perform(request);
			
		}
		catch (CommandNotSupportedException e)
		{
			throw new AdapterException(e.getMessage(), e);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.adyen.services.payment.impl.AdyenCardPaymentService#listRecurringDetails(com.adyen.services.recurring.RecurringDetailsRequest)
	 */
	@Override
	public AdyenRecurringDetailsResult listRecurringDetails(AdyenRecurringListDetailsRequest request)
	{
		AdyenRecurringDetailsResult result = null;
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
			RecurringDetailsCommand recurringCommand = commandFactory.createCommand(RecurringDetailsCommand.class);
			result = recurringCommand.perform(request);
			
		}
		catch (CommandNotSupportedException e)
		{
			throw new AdapterException(e.getMessage(), e);
		}
		return result;
	}
	
}
