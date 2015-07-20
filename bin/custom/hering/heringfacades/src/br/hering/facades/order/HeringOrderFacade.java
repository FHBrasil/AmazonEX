/**
 * 
 */
package br.hering.facades.order;

import de.hybris.platform.commercefacades.order.OrderFacade;

import br.hering.fulfilmentprocess.services.impl.InvalidBoletoException;

/**
 * @author mjammal
 *
 */
public interface HeringOrderFacade extends OrderFacade
{
	/**
	 * Returns the available link from PI/Linx to Nfe for code.
	 * @param nfeCode The code of the Nfe for which to retrieve the link.
	 * @return The link to Nfe with matching code
	 */
	String getAvailableLinkNfeForCode(final int nfeCode);
	
	String getBoletoURL(String orderCode);

	/**
	 * @param orderCode
	 */
	boolean generateBoleto(String orderCode);
	
	void validateBoleto(String orderCode) throws InvalidBoletoException;
}
