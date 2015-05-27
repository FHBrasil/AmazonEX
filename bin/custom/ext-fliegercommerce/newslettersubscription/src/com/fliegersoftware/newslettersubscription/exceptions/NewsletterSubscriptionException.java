/**
 * 
 */
package com.fliegersoftware.newslettersubscription.exceptions;

/**
 * @author luiza
 *
 */
public abstract class NewsletterSubscriptionException extends Exception
{

	public NewsletterSubscriptionException()
	{
		super();
	}

	public NewsletterSubscriptionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NewsletterSubscriptionException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public NewsletterSubscriptionException(String message)
	{
		super(message);
	}

	public NewsletterSubscriptionException(Throwable cause)
	{
		super(cause);
	}
}