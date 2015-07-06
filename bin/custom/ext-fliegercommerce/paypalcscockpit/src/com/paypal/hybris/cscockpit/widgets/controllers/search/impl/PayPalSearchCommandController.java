/**
 *
 */
package com.paypal.hybris.cscockpit.widgets.controllers.search.impl;

import de.hybris.platform.cscockpit.services.search.CsSearchResult;
import de.hybris.platform.cscockpit.widgets.controllers.search.impl.DefaultSearchCommandController;

import java.util.Collections;
import java.util.List;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
public class PayPalSearchCommandController extends DefaultSearchCommandController
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.cscockpit.widgets.controllers.search.impl.DefaultSearchCommandController#getCurrentPageResults
	 * ()
	 */
	@Override
	public List getCurrentPageResults()
	{
		final CsSearchResult result = this.getSearchResult();
		if (result != null)
		{
			final List items = result.getResult();

			return items;
		}


		return Collections.emptyList();
	}

}
