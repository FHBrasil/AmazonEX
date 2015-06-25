package br.hering.facades.voucher.impl;

import de.hybris.platform.commercefacades.voucher.data.VoucherData;
import de.hybris.platform.commercefacades.voucher.exceptions.VoucherOperationException;
import de.hybris.platform.commercefacades.voucher.impl.DefaultVoucherFacade;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.voucher.model.VoucherModel;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import br.hering.facades.voucher.HeringVoucherFacade;

/**
 * @author jfelipe
 *
 */
public class DefaultHeringVoucherFacade extends DefaultVoucherFacade implements HeringVoucherFacade {
	
	private static final Logger LOG = Logger.getLogger(DefaultHeringVoucherFacade.class);
	private static final String VALE_CREDITO_RESERV = "vc_reserv";
	private static final String VALE_CREDITO = "vc_";
	
	@Resource
	private CommerceCartService defaultCommerceCartService;
	

	/**
	 * 
	 */
	@Override
	public void applyVoucher(final String voucherCode) throws VoucherOperationException {
		final CartModel sessionCart = getCartService().getSessionCart();
		super.applyVoucher(voucherCode);
		try {
			defaultCommerceCartService.recalculateCart(sessionCart);
		} catch (final CalculationException ex) {
			LOG.error("Failed to recalculate order [" + sessionCart.getCode() + "]", ex);
		}
	}


	/**
	 * 
	 */
	@Override
	public void releaseVoucher(final String voucherCode) throws VoucherOperationException {
		final CartModel sessionCart = getCartService().getSessionCart();
		super.releaseVoucher(voucherCode);
		try {
			defaultCommerceCartService.recalculateCart(sessionCart);
		} catch (final CalculationException ex) {
			LOG.error("Failed to recalculate order [" + sessionCart.getCode() + "]", ex);
		}
	}
	
	@Override
	public boolean isVoucherReserved(final String voucherCode){
	
		if (!voucherCode.toLowerCase().startsWith(VALE_CREDITO_RESERV))
		{
			return false;
		}
		try
		{
			VoucherData voucherData = super.getVoucher(voucherCode);
			if (!voucherData.getValueString().startsWith("100.0%"))
			{
				return false;
			}
		}
		catch (VoucherOperationException voe)
		{
			LOG.error("error: ", voe);
			return false;
		}
		return true;
	}
	
	
	@Override
	public String appliedValeCredito()
	{
		final List<VoucherData> vouchersData = super.getVouchersForCart();
		if (CollectionUtils.isNotEmpty( vouchersData ))
		{
			for (final VoucherData v : vouchersData)
			{
				if (v.getVoucherCode().startsWith(VALE_CREDITO))
				{
					return v.getVoucherCode();
				}
			}
		}
		return "";
	}
	
	@Override
	protected boolean checkVoucherCanBeRedeemed(VoucherModel voucher, String voucherCode) 
	{
		boolean redeemable = super.checkVoucherCanBeRedeemed(voucher, voucherCode);
		boolean notReserved = !isVoucherReserved(voucherCode);
		
		return redeemable && notReserved;
	}
}