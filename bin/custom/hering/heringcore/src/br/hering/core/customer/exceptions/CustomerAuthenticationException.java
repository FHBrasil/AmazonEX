/**
 * 
 */
package br.hering.core.customer.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * @author flieger
 *
 */
public class CustomerAuthenticationException extends AuthenticationException
{

	/**
	 * @param msg
	 */
	public CustomerAuthenticationException(String msg)
	{
		super(msg);
	}

	/**
	 * @param msg
	 */
	public CustomerAuthenticationException(String msg, Throwable e)
	{
		super(msg, e);
	}
}
