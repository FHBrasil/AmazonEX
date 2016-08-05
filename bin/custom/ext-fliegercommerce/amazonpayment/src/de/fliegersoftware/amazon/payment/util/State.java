/**
 * 
 */
package de.fliegersoftware.amazon.payment.util;


/**
 * @author douglas.canalli
 *
 */
public enum State {
	OPEN,
	PENDING,
	DECLINED,
	CLOSED,
	CANCELLED;
	
	public static State getValue(String text) {
		return State.valueOf(text.toUpperCase());
	}
	
}

