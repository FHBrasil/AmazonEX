/**
 * 
 */
package com.flieger.notificationservices.facades;

import java.util.Comparator;

import com.flieger.notificationservices.data.NotifymeData;

/**
 * @author Vinicius de Souza
 *
 */
public interface NotifyMeSimilarProductFacade
{	
	void notifyMe(NotifymeData notifymeData, final Comparator comparator, final Integer limit, final String siteUrl) throws Exception;
}