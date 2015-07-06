/**
 *
 */
package com.paypal.hybris.commands.strategy.impl;

import de.hybris.platform.payment.commands.request.CaptureRequest;

import java.math.BigDecimal;

import com.paypal.hybris.commands.strategy.CaptureStrategy;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
public class DefaultCaptureStrategy implements CaptureStrategy
{

	private BigDecimal orderTotalThreshold;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.paypal.hybris.commands.strategy.CaptureStrategy#allowCapture(de.hybris.platform.payment.commands.request.
	 * CaptureRequest)
	 */
	/**
	 * @return the orderTotalThreshold
	 */
	public BigDecimal getOrderTotalThreshold()
	{
		return orderTotalThreshold;
	}

	/**
	 * @param orderTotalThreshold
	 *           the orderTotalThreshold to set
	 */
	public void setOrderTotalThreshold(final BigDecimal orderTotalThreshold)
	{
		this.orderTotalThreshold = orderTotalThreshold;
	}

	@Override
	public boolean allowCapture(final CaptureRequest request)
	{
		final int res = request.getTotalAmount().compareTo(orderTotalThreshold);

		return res == 1 ? false : true;
	}
}
