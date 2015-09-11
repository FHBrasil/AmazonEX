/**
 * 
 */
package com.adyen.services.payment.impl;

import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.util.PriceValue;

import java.util.List;

/**
 * @author franthescollymaneira
 *
 */
public interface AdyenCardInstallmentsService
{
	/**
	 * 
	 * @return
	 */
	List<String> formatInstallmentsCost(final PriceValue productPrice);
	
	/**
	 * 
	 * @param productPrice
	 * @return
	 */
	List<String> formatInstallmentsCost(final PriceRowModel productPrice);
	
	/**
	 * 
	 * @param productPrice
	 * @return
	 */
	int calculateAvailableInstallments(final PriceValue productPrice);
	
	/**
	 * 
	 * @param productPrice
	 * @return
	 */
	PriceValue calculateInstallmentCost(final PriceValue productPrice);
	
	/**
	 * 
	 * @param productPrice
	 * @return
	 */
	int calculateAvailableInstallments(final PriceRowModel productPrice);
	
	/**
	 * 
	 * @param productPrice
	 * @return
	 */
	PriceValue calculateInstallmentCost(final PriceRowModel productPrice);
}