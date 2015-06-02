/**
 *
 */
package com.paypal.hybris.addon.controllers.cms;

import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.product.data.PriceData;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paypal.hybris.addon.controllers.PaypaladdonControllerConstants;
import com.paypal.hybris.addon.model.PayPalMiniCartComponentModel;


/**
 * @author Dzmitry_Rasolka (EPAM Systems)
 */
@Controller("PayPalMiniCartComponentController")
@Scope("tenant")
@RequestMapping(value = PaypaladdonControllerConstants.Actions.Cms.PayPalMiniCartComponent)
public class PayPalMiniCartComponentController extends AbstractCMSAddOnComponentController<PayPalMiniCartComponentModel>
{
	public static final String TOTAL_PRICE = "totalPrice";
	public static final String TOTAL_ITEMS = "totalItems";
	public static final String TOTAL_DISPLAY = "totalDisplay";
	public static final String TOTAL_NO_DELIVERY = "totalNoDelivery";
	public static final String SUB_TOTAL = "subTotal";

	@Resource(name = "cartFacade")
	private CartFacade cartFacade;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController#fillModel(javax.servlet.http
	 * .HttpServletRequest, org.springframework.ui.Model,
	 * de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel)
	 */
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final PayPalMiniCartComponentModel component)
	{
		final CartData cartData = cartFacade.getMiniCart();
		model.addAttribute(SUB_TOTAL, cartData.getSubTotal());
		if (cartData.getDeliveryCost() != null)
		{
			final PriceData withoutDelivery = cartData.getDeliveryCost();
			withoutDelivery.setValue(cartData.getTotalPrice().getValue().subtract(cartData.getDeliveryCost().getValue()));
			model.addAttribute(TOTAL_NO_DELIVERY, withoutDelivery);
		}
		else
		{
			model.addAttribute(TOTAL_NO_DELIVERY, cartData.getTotalPrice());
		}
		model.addAttribute(TOTAL_PRICE, cartData.getTotalPrice());
		model.addAttribute(TOTAL_DISPLAY, component.getTotalDisplay());
		model.addAttribute(TOTAL_ITEMS, cartData.getTotalUnitCount());
	}
}
