/**
 * 
 */
package br.hering.facades.voucher;

import de.hybris.platform.commercefacades.voucher.VoucherFacade;

/**
 * @author jfelipe
 *
 */
public interface HeringVoucherFacade extends VoucherFacade {
	
	public boolean isVoucherReserved(final String voucherCode);
	
	public String appliedValeCredito();		

}
