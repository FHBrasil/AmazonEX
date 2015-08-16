package de.fliegersoftware.amazon.payment.facades;

public enum SimulateAuthorizeError {
	InvalidPaymentMethod, AmazonRejected, TransactionTimedOut, ExpiredUnused, AmazonClosed
	, None

}
