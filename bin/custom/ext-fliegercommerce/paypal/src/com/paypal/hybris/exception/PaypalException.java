package com.paypal.hybris.exception;


/**
 * @author Christina Romashchenko
 * 
 */
public class PaypalException extends RuntimeException {

private static final long serialVersionUID = 1330643451714996260L;


public PaypalException(final String exceptionText) {

	super(exceptionText);
}


}
