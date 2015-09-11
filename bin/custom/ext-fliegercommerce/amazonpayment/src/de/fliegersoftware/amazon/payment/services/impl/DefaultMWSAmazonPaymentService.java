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
import com.amazonservices.mws.offamazonpayments.model.CancelOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.CancelOrderReferenceResult;
import com.amazonservices.mws.offamazonpayments.model.CaptureRequest;
import com.amazonservices.mws.offamazonpayments.model.CaptureResult;
import com.amazonservices.mws.offamazonpayments.model.CloseAuthorizationRequest;
import com.amazonservices.mws.offamazonpayments.model.CloseAuthorizationResult;
import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceResult;
import com.amazonservices.mws.offamazonpayments.model.ConfirmOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsResult;
import com.amazonservices.mws.offamazonpayments.model.GetCaptureDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetCaptureDetailsResult;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsResult;
import com.amazonservices.mws.offamazonpayments.model.GetRefundDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetRefundDetailsResult;
import com.amazonservices.mws.offamazonpayments.model.RefundRequest;
import com.amazonservices.mws.offamazonpayments.model.RefundResult;
import com.amazonservices.mws.offamazonpayments.model.SetOrderReferenceDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.SetOrderReferenceDetailsResult;

import de.fliegersoftware.amazon.payment.commands.AuthorizeCommand;
import de.fliegersoftware.amazon.payment.commands.CancelOrderReferenceCommand;
import de.fliegersoftware.amazon.payment.commands.CaptureCommand;
import de.fliegersoftware.amazon.payment.commands.CloseAuthorizationCommand;
import de.fliegersoftware.amazon.payment.commands.CloseOrderReferenceCommand;
import de.fliegersoftware.amazon.payment.commands.ConfirmOrderReferenceCommand;
import de.fliegersoftware.amazon.payment.commands.GetAuthorizationDetailsCommand;
import de.fliegersoftware.amazon.payment.commands.GetCaptureDetailsCommand;
import de.fliegersoftware.amazon.payment.commands.GetOrderReferenceDetailsCommand;
import de.fliegersoftware.amazon.payment.commands.GetRefundDetailsCommand;
import de.fliegersoftware.amazon.payment.commands.RefundCommand;
import de.fliegersoftware.amazon.payment.commands.SetOrderReferenceDetailsCommand;
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

	/* (non-Javadoc)
	 * @see de.fliegersoftware.amazon.payment.services.MWSAmazonPaymentService#refund(com.amazonservices.mws.offamazonpayments.model.RefundRequest)
	 */
	@Override
	public RefundResult refund(RefundRequest request) throws AdapterException
	{
		try {
			
			CommandFactory commandFactory = this.commandFactoryRegistry.getFactory("Amazon");
			RefundCommand command = commandFactory.createCommand(RefundCommand.class);
			RefundResult result = command.perform(request);

			return result;
			
		} catch (CommandNotSupportedException e) {
			throw new AdapterException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see de.fliegersoftware.amazon.payment.services.MWSAmazonPaymentService#getOrderReferenceDetails(com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsRequest)
	 */
	@Override
	public GetOrderReferenceDetailsResult getOrderReferenceDetails(GetOrderReferenceDetailsRequest request) throws AdapterException
	{
		try {
			
			CommandFactory commandFactory = this.commandFactoryRegistry.getFactory("Amazon");
			GetOrderReferenceDetailsCommand command = commandFactory.createCommand(GetOrderReferenceDetailsCommand.class);
			GetOrderReferenceDetailsResult result = command.perform(request);

			return result;
			
		} catch (CommandNotSupportedException e) {
			throw new AdapterException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see de.fliegersoftware.amazon.payment.services.MWSAmazonPaymentService#setOrderReferenceDetails(com.amazonservices.mws.offamazonpayments.model.SetOrderReferenceDetailsRequest)
	 */
	@Override
	public SetOrderReferenceDetailsResult setOrderReferenceDetails(SetOrderReferenceDetailsRequest request)
			throws AdapterException
	{
		try {
			
			CommandFactory commandFactory = this.commandFactoryRegistry.getFactory("Amazon");
			SetOrderReferenceDetailsCommand command = commandFactory.createCommand(SetOrderReferenceDetailsCommand.class);
			SetOrderReferenceDetailsResult result = command.perform(request);

			return result;
			
		} catch (CommandNotSupportedException e) {
			throw new AdapterException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see de.fliegersoftware.amazon.payment.services.MWSAmazonPaymentService#getCaptureDetails(com.amazonservices.mws.offamazonpayments.model.GetCaptureDetailsRequest)
	 */
	@Override
	public GetCaptureDetailsResult getCaptureDetails(GetCaptureDetailsRequest request) throws AdapterException
	{
		try {
			
			CommandFactory commandFactory = this.commandFactoryRegistry.getFactory("Amazon");
			GetCaptureDetailsCommand command = commandFactory.createCommand(GetCaptureDetailsCommand.class);
			GetCaptureDetailsResult result = command.perform(request);

			return result;
			
		} catch (CommandNotSupportedException e) {
			throw new AdapterException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see de.fliegersoftware.amazon.payment.services.MWSAmazonPaymentService#getAuthorizationDetails(com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsRequest)
	 */
	@Override
	public GetAuthorizationDetailsResult getAuthorizationDetails(GetAuthorizationDetailsRequest request) throws AdapterException
	{
		try {
			
			CommandFactory commandFactory = this.commandFactoryRegistry.getFactory("Amazon");
			GetAuthorizationDetailsCommand command = commandFactory.createCommand(GetAuthorizationDetailsCommand.class);
			GetAuthorizationDetailsResult result = command.perform(request);

			return result;
			
		} catch (CommandNotSupportedException e) {
			throw new AdapterException(e.getMessage(), e);
		}
	}
	
	/* (non-Javadoc)
	 * @see de.fliegersoftware.amazon.payment.services.MWSAmazonPaymentService#cancelOrderReference(com.amazonservices.mws.offamazonpayments.model.CancelOrderReferenceRequest)
	 */
	@Override
	public CancelOrderReferenceResult cancelOrderReference(CancelOrderReferenceRequest request) throws AdapterException
	{
		try {
			
			CommandFactory commandFactory = this.commandFactoryRegistry.getFactory("Amazon");
			CancelOrderReferenceCommand command = commandFactory.createCommand(CancelOrderReferenceCommand.class);
			CancelOrderReferenceResult result = command.perform(request);

			return result;
			
		} catch (CommandNotSupportedException e) {
			throw new AdapterException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see de.fliegersoftware.amazon.payment.services.MWSAmazonPaymentService#closeAuthorization(com.amazonservices.mws.offamazonpayments.model.CloseAuthorizationRequest)
	 */
	@Override
	public CloseAuthorizationResult closeAuthorization(CloseAuthorizationRequest request) throws AdapterException
	{
		try {
			
			CommandFactory commandFactory = this.commandFactoryRegistry.getFactory("Amazon");
			CloseAuthorizationCommand command = commandFactory.createCommand(CloseAuthorizationCommand.class);
			CloseAuthorizationResult result = command.perform(request);

			return result;
			
		} catch (CommandNotSupportedException e) {
			throw new AdapterException(e.getMessage(), e);
		}
	}
	
	/* (non-Javadoc)
	 * @see de.fliegersoftware.amazon.payment.services.MWSAmazonPaymentService#getRefundDetails(com.amazonservices.mws.offamazonpayments.model.GetRefundDetailsRequest)
	 */
	@Override
	public GetRefundDetailsResult getRefundDetails(GetRefundDetailsRequest request) throws AdapterException
	{
		try {
			
			CommandFactory commandFactory = this.commandFactoryRegistry.getFactory("Amazon");
			GetRefundDetailsCommand command = commandFactory.createCommand(GetRefundDetailsCommand.class);
			GetRefundDetailsResult result = command.perform(request);

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

	/* (non-Javadoc)
	 * @see de.fliegersoftware.amazon.payment.services.MWSAmazonPaymentService#closeOrderReference(com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceRequest)
	 */
	@Override
	public CloseOrderReferenceResult closeOrderReference(CloseOrderReferenceRequest request) throws AdapterException
	{
		try {
			
			CommandFactory commandFactory = this.commandFactoryRegistry.getFactory("Amazon");
			CloseOrderReferenceCommand command = commandFactory.createCommand(CloseOrderReferenceCommand.class);
			CloseOrderReferenceResult result = command.perform(request);

			return result;
			
		} catch (CommandNotSupportedException e) {
			throw new AdapterException(e.getMessage(), e);
		}
	}

	@Override
	public void confirmOrderReferenceCommand(ConfirmOrderReferenceRequest request) throws AdapterException {
		try {
			
			CommandFactory commandFactory = this.commandFactoryRegistry.getFactory("Amazon");
			ConfirmOrderReferenceCommand command = commandFactory.createCommand(ConfirmOrderReferenceCommand.class);
			command.perform(request);
			
		} catch (CommandNotSupportedException e) {
			throw new AdapterException(e.getMessage(), e);
		}
	}

}
