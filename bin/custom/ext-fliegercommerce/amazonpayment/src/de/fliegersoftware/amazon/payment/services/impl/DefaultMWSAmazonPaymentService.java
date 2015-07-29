/**
 * 
 */
package de.fliegersoftware.amazon.payment.services.impl;

import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.commands.factory.CommandFactory;
import de.hybris.platform.payment.commands.factory.CommandFactoryRegistry;
import de.hybris.platform.payment.commands.factory.CommandNotSupportedException;
import de.hybris.platform.payment.impl.DefaultPaymentServiceImpl;

import com.amazonservices.mws.offamazonpayments.model.AuthorizeRequest;
import com.amazonservices.mws.offamazonpayments.model.AuthorizeResult;
import com.amazonservices.mws.offamazonpayments.model.CaptureRequest;
import com.amazonservices.mws.offamazonpayments.model.CaptureResult;

import de.fliegersoftware.amazon.payment.commands.AuthorizeCommand;
import de.fliegersoftware.amazon.payment.commands.CaptureCommand;
import de.fliegersoftware.amazon.payment.services.MWSAmazonPaymentService;

/**
 * @author taylor.savegnago
 *
 */
public class DefaultMWSAmazonPaymentService extends DefaultPaymentServiceImpl implements MWSAmazonPaymentService
{
	private CommandFactoryRegistry commandFactoryRegistry;
	
	/* (non-Javadoc)
	 * @see de.fliegersoftware.amazon.services.AmazonPaymentService#authorize(com.amazonservices.mws.offamazonpayments.model.AuthorizeRequest)
	 */
	@Override
	public AuthorizeResult authorize(AuthorizeRequest request) throws AdapterException {
		try {
			
			CommandFactory commandFactory = this.commandFactoryRegistry.getFactory("Amazon");
			AuthorizeCommand command = commandFactory.createCommand(AuthorizeCommand.class);
			AuthorizeResult result = command.perform(request);

			return result;
			
		} catch (CommandNotSupportedException e) {
			throw new AdapterException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see de.fliegersoftware.amazon.services.AmazonPaymentService#capture(com.amazonservices.mws.offamazonpayments.model.CaptureRequest)
	 */
	@Override
	public CaptureResult capture(CaptureRequest request) throws AdapterException
	{
		try {
			
			CommandFactory commandFactory = this.commandFactoryRegistry.getFactory("Amazon");
			CaptureCommand command = commandFactory.createCommand(CaptureCommand.class);
			CaptureResult result = command.perform(request);

			return result;
			
		} catch (CommandNotSupportedException e) {
			throw new AdapterException(e.getMessage(), e);
		}
	}
	
	public void setCommandFactoryRegistry(CommandFactoryRegistry commandFactoryRegistry)
	{
		this.commandFactoryRegistry = commandFactoryRegistry;
	}

	public CommandFactoryRegistry getCommandFactoryRegistry()
	{
		return this.commandFactoryRegistry;
	}


}
