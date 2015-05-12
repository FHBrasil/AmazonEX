/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.services;

import de.hybris.platform.util.Config;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Stub;
import org.apache.log4j.Logger;

import com.adyen.services.payment.BalanceCheckResult;
import com.adyen.services.payment.DirectDebitResult;
import com.adyen.services.payment.FundTransferResult;
import com.adyen.services.payment.ModificationResult;
import com.adyen.services.payment.PaymentLocator;
import com.adyen.services.payment.PaymentPortType;
import com.adyen.services.payment.PaymentResult;
import com.flieger.main.Credentials;
import com.flieger.utils.ServiceConfig;


/**
 * 
 * @author Fleiger
 */
public class WebServicesConn
{
	private static final Logger LOG = Logger.getLogger(WebServicesConn.class.getName());

	/**
	 * Set the Credentials for authenticating in Adyen WS
	 * 
	 * @param port
	 */
	public void setWebServiceCredentials(Object port)
	{
		LOG.debug("Setting credentials for WebService Authentication.");
		// Basic HTTP Authentication:
		((Stub) port)._setProperty(Call.USERNAME_PROPERTY, Config.getParameter(Credentials.WS_USER));
//		((Stub) port)._setProperty(Call.PASSWORD_PROPERTY, Config.getParameter(Credentials.PWD_USER));
		((Stub) port)._setProperty(Call.PASSWORD_PROPERTY, Credentials.PWD_USER);
	}

	private PaymentPortType getPaymentPortType()
	{
		PaymentPortType ws = null;
		try
		{
			ws = new PaymentLocator().getPaymentHttpPort(new java.net.URL(ServiceConfig.getParameter(Credentials.ADDRESS)));
		}
		catch (MalformedURLException | javax.xml.rpc.ServiceException e)
		{
			e.printStackTrace();
		}
		if (ws == null)
		{
			return null;
		}
		else
		{
			return ws;
		}
	}

	public PaymentResult authorise(com.adyen.services.payment.PaymentRequest paymentRequest) throws RemoteException
	{
		LOG.debug("authorise");
		PaymentPortType port = getPaymentPortType();
		setWebServiceCredentials(port);
		return port.authorise(paymentRequest);
	}

	public PaymentResult authorise3D(com.adyen.services.payment.PaymentRequest3D paymentRequest3D) throws RemoteException
	{
		LOG.debug("authorise3D");
		PaymentPortType port = getPaymentPortType();
		setWebServiceCredentials(port);
		return port.authorise3D(paymentRequest3D);
	}

	public ModificationResult authoriseReferral(com.adyen.services.payment.ModificationRequest modificationRequest)
			throws RemoteException
	{
		LOG.debug("authoriseReferral");
		PaymentPortType port = getPaymentPortType();
		setWebServiceCredentials(port);
		return port.authoriseReferral(modificationRequest);
	}

	public BalanceCheckResult balanceCheck(com.adyen.services.payment.BalanceCheckRequest request) throws RemoteException
	{
		LOG.debug("balanceCheck");
		PaymentPortType port = getPaymentPortType();
		setWebServiceCredentials(port);
		return port.balanceCheck(request);
	}

	public ModificationResult cancel(com.adyen.services.payment.ModificationRequest modificationRequest) throws RemoteException
	{
		LOG.debug("cancel");
		PaymentPortType port = getPaymentPortType();
		setWebServiceCredentials(port);
		return port.cancel(modificationRequest);
	}

	public ModificationResult cancelOrRefund(com.adyen.services.payment.ModificationRequest modificationRequest)
			throws RemoteException
	{
		LOG.debug("cancelOrRefund");
		PaymentPortType port = getPaymentPortType();
		setWebServiceCredentials(port);
		return port.cancelOrRefund(modificationRequest);
	}

	public ModificationResult capture(com.adyen.services.payment.ModificationRequest modificationRequest) throws RemoteException
	{
		LOG.debug("capture");
		PaymentPortType port = getPaymentPortType();
		setWebServiceCredentials(port);
		return port.capture(modificationRequest);
	}

	public PaymentResult checkFraud(com.adyen.services.payment.PaymentRequest paymentRequest) throws RemoteException
	{
		LOG.debug("checkFraud");
		PaymentPortType port = getPaymentPortType();
		setWebServiceCredentials(port);
		return port.checkFraud(paymentRequest);
	}

	public DirectDebitResult directdebit(com.adyen.services.payment.DirectDebitRequest request) throws RemoteException
	{
		LOG.debug("directdebit");
		PaymentPortType port = getPaymentPortType();
		setWebServiceCredentials(port);
		return port.directdebit(request);
	}

	public FundTransferResult fundTransfer(com.adyen.services.payment.FundTransferRequest request) throws RemoteException
	{
		LOG.debug("fundTransfer");
		PaymentPortType port = getPaymentPortType();
		setWebServiceCredentials(port);
		return port.fundTransfer(request);
	}

	public ModificationResult refund(com.adyen.services.payment.ModificationRequest modificationRequest) throws RemoteException
	{
		LOG.debug("refund");
		PaymentPortType port = getPaymentPortType();
		setWebServiceCredentials(port);
		return port.refund(modificationRequest);
	}

	public PaymentResult refundWithData(com.adyen.services.payment.PaymentRequest request) throws RemoteException
	{
		LOG.debug("refundWithData");
		PaymentPortType port = getPaymentPortType();
		setWebServiceCredentials(port);
		return port.refundWithData(request);
	}

}
