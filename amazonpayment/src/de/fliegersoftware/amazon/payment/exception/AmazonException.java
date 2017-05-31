package de.fliegersoftware.amazon.payment.exception;


import org.slf4j.Logger;  import org.slf4j.LoggerFactory;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;

/**
 * @author douglas.canalli
 *
 */
public class AmazonException extends Throwable {

	private static final long serialVersionUID = -4637476427587637183L;

	private static final Logger log = LoggerFactory.getLogger(AmazonException.class);
	
	private OffAmazonPaymentsServiceException error;
	
	public AmazonException(OffAmazonPaymentsServiceException e) {
		error = e;
	}
	
	public String getErrorCode() {
		return error.getErrorCode();
	}
	
	public String getMessage() {
		return error.getMessage();
	}
	
	public String getRequestId() {
		return error.getRequestId();
	}
	
	public int getStatusCode() {
		return error.getStatusCode();
	}
	
	public String getXML() {
		return error.getXML();
	}
	
	public String getErrorType() {
		return error.getErrorType();
	}
	
	public void logMessage() {
		StringBuilder message = new StringBuilder();
//		if(ignoreErrors().contains(getErrorCode())) {
//			return;
//		}
		message.append("Error code: ").append(getErrorCode()).append(";\n");
		message.append("Message: ").append(getMessage()).append(";\n");
		message.append("Request Id: ").append(getRequestId()).append(";\n");
		message.append("Status code: ").append(getStatusCode()).append(";\n");
		log.error(message.toString(), this);
	}
//	
//	private List<String> ignoreErrors() {
//		List<String> errorCodes = new ArrayList<String>();
//		errorCodes.add("TransactionAmountExceeded");
//		errorCodes.add("TransactionAmountExceeded;");
//		errorCodes.add("InvalidOrderReferenceId");
//		return errorCodes;
//	}
	
}
