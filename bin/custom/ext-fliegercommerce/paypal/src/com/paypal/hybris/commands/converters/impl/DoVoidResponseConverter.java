/**
 *
 */
package com.paypal.hybris.commands.converters.impl;

import de.hybris.platform.converters.impl.AbstractConverter;
import de.hybris.platform.payment.commands.result.VoidResult;

import com.ebay.api.DoVoidResponseType;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
public class DoVoidResponseConverter extends AbstractConverter<DoVoidResponseType, VoidResult>
{

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.converters.impl.AbstractConverter#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final DoVoidResponseType response, final VoidResult result)
	{
		result.setRequestId(response.getAuthorizationID());
	}
	
	protected VoidResult createTarget() {
	    return new VoidResult();
	}

}
