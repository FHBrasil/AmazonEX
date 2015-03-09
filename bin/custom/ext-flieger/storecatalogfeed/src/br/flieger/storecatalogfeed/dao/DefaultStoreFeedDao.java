/**
 * 
 */
package br.flieger.storecatalogfeed.dao;

import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import br.flieger.storecatalogfeed.xml.template.AllProductsFromCatalogTemplate;
import br.flieger.storecatalogfeed.xml.template.BaseProductsFromCatalogTemplate;

/**
 * @author franthescolly
 *
 */
public final class DefaultStoreFeedDao extends AbstractItemDao {

    /*
     * (non-Javadoc)
     * 
     * @see de.fliegersoftware.catalog.dao.CatalogDAO#getCounter(java.lang.Class)
     */
    public int getCounter(CatalogVersionModel catalogVersion, String xmlTemplate) {
        String query = null;
        if (xmlTemplate.equals(AllProductsFromCatalogTemplate.CODE)) {
            query = getAllProductsQuery(true);
        } else if (xmlTemplate.equals(BaseProductsFromCatalogTemplate.CODE)) {
            query = getBaseProductsQuery(true);
        }
        FlexibleSearchQuery fsQuery = getDefaultSearchQuery(catalogVersion, query);
        return search(fsQuery).getTotalCount();
    }


    public Set<ProductModel> findByPaging(CatalogVersionModel catalogVersion, String xmlTemplate,
            int beginindex, int total) {
        String query = null;
        if (xmlTemplate.equals(AllProductsFromCatalogTemplate.CODE)) {
            query = getAllProductsQuery(true);
        } else if (xmlTemplate.equals(BaseProductsFromCatalogTemplate.CODE)) {
            query = getBaseProductsQuery(true);
        }
        return paginatedSearch(catalogVersion, query, beginindex, total);
    }


    /**
     * 
     * @param order
     * @return
     */
    private String getAllProductsQuery(boolean order) {
        StringBuilder query = new StringBuilder()
                .append(" SELECT DISTINCT {p:PK} FROM {Product as p} WHERE {p:catalogVersion} = ?cv AND {p:approvalStatus} = ?as");
        if (order) {
            query.append(" ORDER BY {p:PK} ASC ");
        }
        return query.toString();
    }


    /**
     * 
     * @param order
     * @return
     */
    private String getBaseProductsQuery(boolean order) {
        StringBuilder query = new StringBuilder().append(" SELECT DISTINCT {p:PK} ")
                .append(" FROM {HeringProduct AS p} ").append(" WHERE {p:catalogVersion} = ?cv ")
                .append(" AND {p:approvalStatus} = ?as");
        if (order) {
            query.append(" ORDER BY {p:PK} ASC ");
        }
        return query.toString();
    }


    /*
     * (non-Javadoc)
     * 
     * @see de.fliegersoftware.catalog.dao.CatalogDAO#paginatedSearch(java.lang.Class,
     * java.lang.String, java.util.Map, int, int)
     */
    private Set<ProductModel> paginatedSearch(CatalogVersionModel catalogVersion, String query,
            int beginindex, int total) {
        FlexibleSearchQuery searchQuery = getDefaultSearchQuery(catalogVersion, query);
        searchQuery.setNeedTotal(true);
        searchQuery.setStart(beginindex);
        searchQuery.setCount(total);
        SearchResult<ProductModel> search = search(searchQuery);
        return new LinkedHashSet<ProductModel>(search.getResult());
    }


    /**
     * @param catalogVersion
     * @param query
     * @return
     */
    private FlexibleSearchQuery getDefaultSearchQuery(CatalogVersionModel catalogVersion,
            String query) {
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
        // searchQuery.setDisableSearchRestrictions(true);
        searchQuery.setResultClassList(Arrays.asList(ProductModel.class));
        searchQuery.setCatalogVersions(catalogVersion);
        searchQuery.addQueryParameter("cv", catalogVersion);
        searchQuery.addQueryParameter("as", ArticleApprovalStatus.APPROVED);
        return searchQuery;
    }
}