/**
 * 
 */
package br.hering.fulfilmentprocess.services.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.strategies.CheckoutCustomerStrategy;
import de.hybris.platform.order.daos.OrderDao;
import de.hybris.platform.order.impl.DefaultOrderService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

//import br.hering.core.


import javax.annotation.Resource;

import org.apache.log4j.Logger;

import br.hering.fulfilmentprocess.services.HeringOrderService;

/**
 * @author guilhermeshayashi
 *
 */
public class DefaultHeringOrderService extends DefaultOrderService implements HeringOrderService
{

	private static final String BOLETO_GENERATOR_COMMAND = "/HYBRIS/skripte/gen_pdf_to_html.sh";
	
	protected static final Logger LOG = Logger.getLogger(DefaultHeringOrderService.class);
	
	@Resource
	private UserService userService;
	
	@Resource
	private CustomerAccountService customerAccountService;
	
	@Resource
	private BaseStoreService baseStoreService;
	
	@Resource
	private CheckoutCustomerStrategy checkoutCustomerStrategy;

	@Override
	public String getBoletoURL(OrderModel order)
	{
		PaymentTransactionEntryModel transactionEntry = getBoleto(order);
		String boletoURL = transactionEntry.getAdyenBoletoUrl();

		return boletoURL;
	}

	@Override
	public void validateBoleto(OrderModel order) throws InvalidBoletoException {
		PaymentTransactionEntryModel transactionEntry = getBoleto(order);
		if(isBoletoExpired(transactionEntry)) {
			throw new InvalidBoletoException("Boleto expirado");
		}
	}

	@Override
	public boolean isBoletoExpired(OrderModel order) throws InvalidBoletoException {
		return isBoletoExpired(getBoleto(order));
	}

	protected boolean isBoletoExpired(PaymentTransactionEntryModel transactionEntry) throws InvalidBoletoException {
		try {
			// Compares expiration date and today (both at 00:00:00 hours)
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String todayText = dateFormat.format(Calendar.getInstance().getTime());
			Date today = dateFormat.parse(todayText);

			String expirationText = transactionEntry.getAdyenBoletoExpirationDate();
			Date expiration = expirationText != null ? dateFormat.parse(expirationText) : today;
			return expiration.compareTo(today) < 0;
		} catch (ParseException e) {
			throw new InvalidBoletoException("Data inválida");
		}
	}

	private PaymentTransactionEntryModel getBoleto(OrderModel order) {
		PaymentTransactionModel paymenTtransaction = order.getPaymentTransactions().get(0);
		return paymenTtransaction.getEntries().get(0);
	}
	
	@Override
	public boolean boletoGenerator(OrderModel orderModel){
		return scriptGenerator( orderModel, getBoletoURL(orderModel));
	}
	
	private boolean scriptGenerator(OrderModel orderModel, String url)
	{
		Process process = null;

		try
		{
			LOG.info("Executing boleto generator commmand...");

			Runtime runtime = Runtime.getRuntime();

			process = runtime.exec(BOLETO_GENERATOR_COMMAND + " " + orderModel.getCode() + " " + url);
		}
		catch (IOException e)
		{
			LOG.error("Error generator command: " + BOLETO_GENERATOR_COMMAND, e);

			return false;
		}

		try
		{
			int commandReturnCode = process.waitFor();

			if (commandReturnCode == 0)
			{
				LOG.info("Boleto Generator command successfully executed");
				return true;
			}
			else
			{
				LOG.info("Couldn't execute the boleto generator command, return code: " + commandReturnCode);
				return false;
			}
		}
		catch (InterruptedException e)
		{
			LOG.error("Error getting process return code", e);
			return false;
		}
	}

	protected OrderDao getOrderDao()
	{
		return null;
//		return (HeringOrderDao) super.getOrderDao();
	}
}
