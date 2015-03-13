/**
 * 
 */
package de.kpfamily.services.daos.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.kpfamily.core.model.BabyartikelSerialVoucherProductModel;
import de.kpfamily.services.daos.product.SerialVoucherProductDao;

/**
 * @author franthescollymaneira
 *
 */
public class DefaultSerialVoucherProductDao extends DefaultGenericDao<BabyartikelSerialVoucherProductModel> implements SerialVoucherProductDao 
{
	public DefaultSerialVoucherProductDao(String typecode) 
	{
		super(typecode);
	}

	@Override
	public List<String> findSoldSerialVoucherCodesByOrder(final OrderModel order) 
	{
		final StringBuilder query = new StringBuilder();
		query.append("SELECT {h:serialVoucherCode} FROM { ");
		query.append("	BabyartikelSerialVoucherProductSellingHistory AS h ");
		query.append("    JOIN OrderEntry AS e ON {e:PK} = {h:orderEntry} ");
		query.append("} WHERE {e:order} = ?order "); 
		
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("order", order);

		final SearchResult<String> result = getFlexibleSearchService().search(query.toString(), params);

		return result.getResult();
	}
}