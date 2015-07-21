/**
 *
 */
package com.pixi.core.daos.impl;

import de.hybris.platform.core.enums.ExportStatus;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.jalo.user.UserManager;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Arrays;
import java.util.List;

import com.pixi.core.daos.PixiOrderDao;

/**
 * @author franthescollymaneira
 * @author felipe
 */
public class DefaultPixiOrderDao extends DefaultGenericDao<OrderModel> implements PixiOrderDao
{
    
    /**
     * @param typecode
     */
    public DefaultPixiOrderDao(final String typecode)
    {
        super(typecode);
    }
    
    
    /*
     * (non-Javadoc)
     * @see
     * com.pixi.core.daos.PixiOrderDao#findOrdersToExport(de.hybris.platform.store.BaseStoreModel)
     */
    @Override
    public List<OrderModel> findOrdersToExport(final BaseStoreModel store)
    {
        final StringBuilder query = new StringBuilder();
        query.append(" SELECT DISTINCT({o:pk}) FROM {Order AS o ");
        query.append("  JOIN PaymentStatus AS ps ON {ps:pk} = {o:paymentStatus} ");
        query.append("  JOIN PaymentMode AS pm ON {pm:pk} = {o:paymentMode}  ");
        query.append("  JOIN Address AS a ON ({a:pk} = {o:paymentAddress} OR {a:pk} = {o:deliveryAddress})} ");
        query.append(" WHERE  ");
        query.append(" {o:store} = ?store AND ");
        query.append(" {a:owner} <> ?anonymous AND ");
        query.append(" ({o:exportstatus} IS null OR {o:exportstatus} = ?notExported) AND ");
        query.append(" (({pm:code} = 'paypal' AND {ps:code} = ?paid) OR  ");
        query.append(" ({pm:code} = 'amazon' AND {ps:code} = ?paid) OR  ");
        query.append(" ({pm:code} = 'creditcard' AND {ps:code} = ?paid) OR  ");
        query.append(" ({pm:code} = 'klarna' AND {ps:code} = ?paid) OR  ");
        query.append(" ({pm:code} = 'klarnaFinancing' AND {ps:code} = ?paid) OR  ");
        query.append(" ({pm:code} = 'debitByCall' AND {ps:code} = ?paid) OR ");
        query.append(" ({pm:code} <> 'paypal' AND {pm:code} <> 'amazon' AND {pm:code} <> 'debitByCall'");
        query.append(" AND {pm:code} <> 'creditcard' AND {pm:code} <> 'klarna'");
        query.append(" AND {pm:code} <> 'klarnaFinancing')) ");
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
        searchQuery.setResultClassList(Arrays.asList(OrderModel.class));
        searchQuery.setCount(100);
        searchQuery.addQueryParameter("anonymous", UserManager.getInstance().getAnonymousCustomer());
        searchQuery.addQueryParameter("notExported", ExportStatus.NOTEXPORTED);
        searchQuery.addQueryParameter("paid", PaymentStatus.PAID.getCode());
        searchQuery.addQueryParameter("store", store);
        final SearchResult<OrderModel> search = getFlexibleSearchService().search(searchQuery);
        return search.getResult();
    }
    
    
    /**
     * 
     */
    @Override
    public OrderModel getOrderForCodeAndStore(String code, BaseStoreModel store) {
        final StringBuilder query = new StringBuilder();
        query.append(" SELECT {o:pk} ");
        query.append(" FROM {Order AS o }");
        query.append(" WHERE  {o:code} = ?code ");
        query.append(" AND {o:store} = ?store ");
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
        searchQuery.setResultClassList(Arrays.asList(OrderModel.class));
        searchQuery.addQueryParameter("code", code);
        searchQuery.addQueryParameter("store", store);
        final SearchResult<OrderModel> search = getFlexibleSearchService().search(searchQuery);
        return search.getResult().get(0);
    }
}