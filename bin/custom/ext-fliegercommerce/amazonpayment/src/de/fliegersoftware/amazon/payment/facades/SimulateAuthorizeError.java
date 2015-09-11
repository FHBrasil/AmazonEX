package de.fliegersoftware.amazon.payment.facades;

public enum SimulateAuthorizeError {
	None
	, InvalidPaymentMethod, AmazonRejected, TransactionTimedOut, ExpiredUnused, AmazonClosed
}
