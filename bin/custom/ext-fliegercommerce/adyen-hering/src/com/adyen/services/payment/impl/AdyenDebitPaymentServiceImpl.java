/**
 * 
 */
package com.adyen.services.payment.impl;

import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.commands.CreateSubscriptionCommand;
import de.hybris.platform.payment.commands.factory.CommandFactory;
import de.hybris.platform.payment.commands.factory.CommandFactoryRegistry;
import de.hybris.platform.payment.commands.factory.CommandNotSupportedException;
import de.hybris.platform.payment.commands.request.CreateSubscriptionRequest;
import de.hybris.platform.payment.commands.result.SubscriptionResult;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.util.Config;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.adyen.services.common.Amount;
import com.adyen.services.common.BrowserInfo;
import com.adyen.services.payment.AdyenAuthorizationRequest;
import com.adyen.services.payment.AdyenAuthorizationResult;
import com.adyen.services.payment.AdyenDebitAuthorizationRequest;
import com.adyen.services.payment.AdyenDebitAuthorizationResult;
import com.adyen.services.payment.AnyType2AnyTypeMapEntry;
import com.adyen.services.payment.Card;
import com.adyen.services.payment.PaymentRequest;
import com.adyen.services.payment.PaymentRequest3D;
import com.adyen.services.payment.PaymentResult;
import com.adyen.services.recurring.AdyenRecurringDetailsResult;
import com.adyen.services.recurring.AdyenRecurringListDetailsRequest;
import com.flieger.adyen.command.AdyenDebitAuthorizationCommand;

import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.acceleratorservices.urlresolver.SiteBaseUrlResolutionService;

import com.flieger.constants.AdyenConstants;
import com.flieger.main.Credentials;
import com.flieger.payment.model.HeringDebitPaymentInfoModel;
import com.flieger.services.WebServicesConn;



/**
 * @author franthescollymaneira
 */
public class AdyenDebitPaymentServiceImpl 
extends DefaultAdyenPaymentService 
implements AdyenDebitPaymentService
{
   private CommandFactoryRegistry commandFactoryRegistry;
   
   private static final Logger LOG = Logger.getLogger(AdyenDebitPaymentServiceImpl.class.getName());
	
   @Resource
   private I18NService i18nService;
   
	@Override
	public AdyenDebitAuthorizationResult authorise(final AdyenDebitAuthorizationRequest authorizationRequest)
	{
		try
		{	
			CommandFactory commandFactory = getCommandFactoryRegistry().getFactory("Adyen");
			
			AdyenDebitAuthorizationCommand command = commandFactory.createCommand(AdyenDebitAuthorizationCommand.class);
			
			return command.perform(authorizationRequest);
		}
		catch (CommandNotSupportedException e)
		{
			throw new AdapterException(e.getMessage(), e);
		}
	}
	
	@Override
	public AdyenDebitAuthorizationResult authorize3D(AdyenDebitAuthorizationRequest authorizationRequest)
	{
		try
		{	
			CommandFactory commandFactory = getCommandFactoryRegistry().getFactory("Adyen");
			
			AdyenDebitAuthorizationCommand command = commandFactory.createCommand(AdyenDebitAuthorizationCommand.class);
			
			return command.perform3D(authorizationRequest);
		}
		catch (CommandNotSupportedException e)
		{
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
	
	@Override
	public SubscriptionResult createSubscription( CreateSubscriptionRequest request) 
			throws AdapterException 
	{
      try {
      	CreateSubscriptionCommand command = this.commandFactoryRegistry
      			.getFactory(request.getPaymentProvider()).createCommand(
      					CreateSubscriptionCommand.class);
      	return command.perform(request);
      } catch (CommandNotSupportedException e) {
      	throw new AdapterException(e.getMessage(), e);
      }
   }
}