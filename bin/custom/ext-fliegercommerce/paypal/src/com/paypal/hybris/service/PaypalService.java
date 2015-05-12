package com.paypal.hybris.service;


import com.paypal.hybris.soap.params.impl.DoAuthorizationParams;
import com.paypal.hybris.soap.params.impl.DoCaptureParams;
import com.paypal.hybris.soap.params.impl.DoExpressCheckoutPaymentParams;
import com.paypal.hybris.soap.params.impl.DoReauthorizationParams;
import com.paypal.hybris.soap.params.impl.DoVoidParams;
import com.paypal.hybris.soap.params.impl.GetExpressCheckoutDetailsParams;
import com.paypal.hybris.soap.params.impl.GetTransactionDetailsParams;
import com.paypal.hybris.soap.params.impl.RefundTransactionParams;
import com.paypal.hybris.soap.params.impl.SetExpressCheckoutParams;
import com.paypal.hybris.soap.params.impl.TransactionSearchParams;


/**
 * 
 * 
 * 
 * @author Valentyn Markovych, Gorilla
 * 
 */
public interface PaypalService {


/**
 * Configures PayPal to receive the forthcoming payment.
 * 
 * @param params
 *          Parameters set.
 */
public void setExpressCheckout(SetExpressCheckoutParams params);


/**
 * Gets ExpressCheckout customer details after he/she accepted a payment.
 * 
 * @param params
 *          Parameters set
 */
public void getExpressCheckoutDetails(GetExpressCheckoutDetailsParams params);


/**
 * Executes payment, that was created earlier and bounded to current session.
 * 
 * @param params
 */
public void doExpressCheckoutPayment(DoExpressCheckoutPaymentParams params);


/**
 * Executes full transaction refund.
 * 
 * @param params
 *          parameters for the operation.
 */
public void refundTransaction(RefundTransactionParams params);


public void doAuthorization(DoAuthorizationParams params);


public void doReauthorization(DoReauthorizationParams params);


/**
 * @param params
 */
public void getTransactionDetails(GetTransactionDetailsParams params);


/**
 * @param params
 */
public void transactionSearch(TransactionSearchParams params);


public void doVoid(DoVoidParams params);


public void doCapture(DoCaptureParams params);

}
