/**
 * 
 */
package de.kpfamily.core.dao.impl;

import java.util.Collections;
import java.util.List;

import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.kpfamily.core.dao.ProductSupplierDao;
import de.kpfamily.core.model.ProductSupplierModel;

/**
 * @author jfelipe
 *
 */
public class DefaultProductSupplierDao extends DefaultGenericDao<ProductSupplierModel> implements
        ProductSupplierDao {

    /**
     * @param typecode
     */
    public DefaultProductSupplierDao(String typecode) {
        super(typecode);
    }


    /**
     * 
     */
    @Override
    public List<ProductSupplierModel> getAllProductSuppliers() {
        final String query = "SELECT DISTINCT {p:code} " + " FROM {ProductSupplier as p} ";
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
        final SearchResult<ProductSupplierModel> search = getFlexibleSearchService().search(
                searchQuery);
        List<ProductSupplierModel> productSuppliers = search.getResult();
        return productSuppliers != null ? productSuppliers : Collections
                .<ProductSupplierModel> emptyList();
    }


    /**
     * 
     */
    @Override
    public ProductSupplierModel getProductSupplierByCode(String code)
            throws AmbiguousIdentifierException {
        final String query = "SELECT DISTINCT {p:code} " + " FROM {ProductSupplier as p} ";
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
        final SearchResult<ProductSupplierModel> search = getFlexibleSearchService().search(
                searchQuery);
        List<ProductSupplierModel> productSuppliers = search.getResult();
        if (productSuppliers.size() > 1) {
            throw new AmbiguousIdentifierException(
                    "There is more than one ProductSupplier with the same come: " + code);
        }
        return productSuppliers.get(0);
    }


    /**
     * 
     */
    @Override
    public List<ProductSupplierModel> getProductSuppliersByName(String name) {
        final String query = "SELECT DISTINCT {p:code} " + " FROM {ProductSupplier as p} "
                + " WHERE {p:name} LIKE '%?name%' ";
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
        searchQuery.addQueryParameter("name", name);
        final SearchResult<ProductSupplierModel> search = getFlexibleSearchService().search(
                searchQuery);
        List<ProductSupplierModel> productSuppliers = search.getResult();
        return productSuppliers != null ? productSuppliers : Collections
                .<ProductSupplierModel> emptyList();
    }
}
