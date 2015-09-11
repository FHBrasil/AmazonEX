/**
 * 
 */
package br.hering.core.checkout.dao.impl;

import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.order.daos.impl.DefaultPaymentModeDao;
import de.hybris.platform.servicelayer.internal.dao.SortParameters;
import de.hybris.platform.servicelayer.internal.dao.SortParameters.SortOrder;

import java.util.List;

/**
 * @author ezequiel
 *
 */
public class DefaultHeringPaymentModeDao extends DefaultPaymentModeDao
{
	@Override
	public List<PaymentModeModel> findAllPaymentModes()
	{
		final SortParameters sortParameters = new SortParameters();
		sortParameters.addSortParameter(PaymentModeModel.PK, SortOrder.ASCENDING);

		return find(sortParameters);
	}

}
