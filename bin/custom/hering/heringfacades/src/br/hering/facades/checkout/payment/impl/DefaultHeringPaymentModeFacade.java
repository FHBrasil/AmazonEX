/**
 * 
 */
package br.hering.facades.checkout.payment.impl;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.order.CalculationService;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.PaymentModeService;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import br.hering.facades.checkout.payment.HeringPaymentModeFacade;
import br.hering.facades.order.data.PaymentModeData;


/**
 * @author Antony P
 *
 */
public class DefaultHeringPaymentModeFacade implements HeringPaymentModeFacade
{
	protected static final Logger LOG = Logger.getLogger(DefaultHeringPaymentModeFacade.class);
	
	private ModelService modelService;
	
	private CartService cartService;
	
	private PaymentModeService paymentModeService;
	
	private Converter<PaymentModeModel, PaymentModeData> paymentModeConverter;
	
	private CalculationService calculationService;
	
	@Override
	public PaymentModeData getPaymentModeForCode(String code) 
	{
		Assert.notNull(code, "code is null");
		
		final PaymentModeModel paymentMode = paymentModeService.getPaymentModeForCode(code);
		
		if (paymentMode == null)
		{
			return null;
		}
		return paymentModeConverter.convert(paymentMode);
	}

	@Override
	public List<PaymentModeData> getAllPaymentModes() 
	{
		final List<PaymentModeModel> allPaymentModes = paymentModeService.getAllPaymentModes();
		
		if (CollectionUtils.isEmpty(allPaymentModes))
		{
			return Collections.emptyList();
		}

		final List<PaymentModeData> dataList = new ArrayList<PaymentModeData>();

		for (final PaymentModeModel model : allPaymentModes)
		{
			dataList.add(paymentModeConverter.convert(model));
		}

		return dataList;
	}

	@Override
	public void setPaymentMode(PaymentModeData paymentModeData) 
	{
		Assert.notNull(paymentModeData, "paymentModeData is null");
		
		final CartModel cart = cartService.getSessionCart();
		
		if (cart == null)
		{
			return;
		}
		
		final PaymentModeModel paymentMode = paymentModeService.getPaymentModeForCode(paymentModeData.getCode());
		if (paymentMode != null && !paymentMode.equals(cart.getPaymentMode()))
		{
			cart.setPaymentInfo(null);
			cart.setPaymentMode(paymentMode);
			try {
				calculationService.recalculate(cart);
			} catch (CalculationException e) {
				LOG.error("Error recalculating cart: " + cart.getCode(), e);
			}
			modelService.save(cart);
		}
	}

	public PaymentModeService getPaymentModeService() 
	{
		return paymentModeService;
	}

	public void setPaymentModeService(PaymentModeService paymentModeService) 
	{
		this.paymentModeService = paymentModeService;
	}

	public Converter<PaymentModeModel, PaymentModeData> getPaymentModeConverter() 
	{
		return paymentModeConverter;
	}

	public void setPaymentModeConverter(Converter<PaymentModeModel, PaymentModeData> paymentModeConverter) 
	{
		this.paymentModeConverter = paymentModeConverter;
	}

	public ModelService getModelService() 
	{
		return modelService;
	}

	public void setModelService(ModelService modelService) 
	{
		this.modelService = modelService;
	}

	public CartService getCartService() 
	{
		return cartService;
	}

	public void setCartService(CartService cartService) 
	{
		this.cartService = cartService;
	}

	public CalculationService getCalculationService() 
	{
		return calculationService;
	}

	public void setCalculationService(CalculationService calculationService) 
	{
		this.calculationService = calculationService;
	}
}