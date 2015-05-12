/**
 * 
 */
package br.hering.fulfilmentprocess.services;

import br.hering.fulfilmentprocess.services.impl.InvalidBoletoException;
import de.hybris.platform.core.model.order.OrderModel;


/**
 * @author guilhermeshayashi
 *
 */
public interface HeringOrderService
{
	public String getBoletoURL(OrderModel order) ;
	
	public boolean boletoGenerator(OrderModel order);

	void validateBoleto(OrderModel order) throws InvalidBoletoException;

	boolean isBoletoExpired(OrderModel order) throws InvalidBoletoException;
}
