/**
 * 
 */
package com.flieger.notificationservices.services.impl;

import java.util.Date;

import com.flieger.notificationservices.constants.NotificationservicesConstants;

/**
 * @author Vinicius de Souza
 *
 */
public abstract class AbstractNotifyMe
{			
	protected Date[] createDateFuture(final int days)
	{
		final long miliDays = days * NotificationservicesConstants.DAYINMILIS;
		
		final Date from = new Date(System.currentTimeMillis());
		final Date until = new Date(System.currentTimeMillis() + miliDays);
		
		return new Date[]{from, until};
	}
	
	protected Date[] createDatePast(final int days)
	{
		final long miliDays = days * NotificationservicesConstants.DAYINMILIS;
		
		final Date from = new Date(System.currentTimeMillis() - miliDays);
		final Date until = new Date(System.currentTimeMillis());
		
		return new Date[]{from, until};
	}
}