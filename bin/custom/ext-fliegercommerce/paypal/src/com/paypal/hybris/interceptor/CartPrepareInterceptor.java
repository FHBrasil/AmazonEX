package com.paypal.hybris.interceptor;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.paypal.hybris.constants.PaypalConstants;

/**
 * @author Valentyn Markovych, Gorilla
 * 
 */
@Component
@Scope("tenant")
public class CartPrepareInterceptor implements PrepareInterceptor {

	private static final double NEAR_ZERO = 0.0001;

	private final Logger LOG = Logger.getLogger(CartPrepareInterceptor.class);

	/**
	 * 
	 * @see de.hybris.platform.servicelayer.interceptor.PrepareInterceptor#onPrepare(java.lang.Object,
	 *      de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	@Override
	public void onPrepare(final Object model, final InterceptorContext context)
			throws InterceptorException {

		final CartModel cart = (CartModel) model;
		final JaloSession session = getSession();

		LOG.info(" - - - Cart is preparing now - - - ");
		LOG.info("Cart:             " + cart);
		LOG.info("session ID:       " + session);
		LOG.info("PaymentMode:      " + (cart.getPaymentMode() != null ? cart.getPaymentMode().getCode() : null));
		LOG.info("PaymentInfo:      " + cart.getPaymentInfo());
		LOG.info("Calculated:       " + cart.getCalculated());
		
		final List<AbstractOrderEntryModel> entries = cart.getEntries();
		double itemTotalPriceBase = 0;
		if (entries != null) {
			for (final AbstractOrderEntryModel entry : entries) {
				final Long quantity = entry.getQuantity();
				final Double unitPrice = entry.getBasePrice();
				itemTotalPriceBase += unitPrice * quantity;
			}
		}
		
		Double newPrice = itemTotalPriceBase;
		Double oldPrice = (Double) getSession().getAttribute("oldPrice");
		
		LOG.info("Old price is " + oldPrice);
		LOG.info("New price is " + newPrice);
		LOG.info("Calculated        " + cart.getCalculated());

		if (oldPrice == null) {
			oldPrice = 0.;
			getSession().setAttribute("oldPrice", 0.);
		}
		if (Math.abs(newPrice - oldPrice) > NEAR_ZERO) {
			LOG.info("Price is changed, paypal button is enabled");
			session.setAttribute(PaypalConstants.PAYPAL_DISABLE_BUTTON, false);
			cart.setPaymentInfo(null);
		}
		session.setAttribute("oldPrice", newPrice);
	}

	public JaloSession getSession() {

		return JaloSession.getCurrentSession();
	}

}
