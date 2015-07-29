package de.fliegersoftware.amazon.payment.exception;


/**
 * @author taylor.savegnago
 * 
 */
public class AmazonException extends RuntimeException {

private static final long serialVersionUID = 1330643451714996260L;


public AmazonException(final String exceptionText) {

	super(exceptionText);
}


}
