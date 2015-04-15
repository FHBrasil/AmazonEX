/**
 * 
 */
package br.flieger.storecatalogfeed.dao;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import br.flieger.storecatalogfeed.xml.template.BaseProductCatalogTemplate;
import br.flieger.storecatalogfeed.xml.template.DefaultProductCatalogTemplate;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

/**
 * @author franthescolly
 *
 */
public final class DefaultStoreFeedDao extends AbstractItemDao {
    
    private static final Logger LOG = Logger.getLogger(DefaultStoreFeedDao.class.getName());

    /**
     * 
     * @param catalogVersion
     * @param xmlTemplate
     *
     * @author franthescolly
     */
    public int getCounter(CatalogVersionModel catalogVersion, String xmlTemplate) {
        String query = null;
        if (xmlTemplate.equals(BaseProductCatalogTemplate.CODE)) {
            query = getAllProductsQuery(true);
        } else if (xmlTemplate.equals(DefaultProductCatalogTemplate.CODE)) {
            query = getBaseProductsQuery(true);
        }
        FlexibleSearchQuery fsQuery = getDefaultSearchQuery(catalogVersion, query);
        return search(fsQuery).getTotalCount();
    }


    /**
     * 
     * @param catalogVersion
     * @param xmlTemplate
     * @param beginindex
     * @param total
     *
     * @author franthescolly
     */
    public Set<ProductModel> findByPaging(CatalogVersionModel catalogVersion, String xmlTemplate,
            int beginindex, int total) {
        String query = null;
        if (xmlTemplate.equals(BaseProductCatalogTemplate.CODE)) {
            query = getAllProductsQuery(true);
        } else if (xmlTemplate.equals(DefaultProductCatalogTemplate.CODE)) {
            query = getBaseProductsQuery(true);
        }
        return paginatedSearch(catalogVersion, query, beginindex, total);
    }


    /**
     * 
     * @param order
     *
     * @author franthescolly
     */
    private String getAllProductsQuery(boolean order) {
        StringBuilder query = new StringBuilder()
                .append(" SELECT DISTINCT {p:PK} ")
                .append(" FROM {Product as p} ")
                .append(" WHERE {p:catalogVersion} = ?cv ")
                .append(" AND {p:approvalStatus} = ?as ");
        if (order) {
            query.append(" ORDER BY {p:PK} ASC ");
        }
        return query.toString();
    }


    /**
     * 
     * @param order
     *
     * @author franthescolly
     */
    private String getBaseProductsQuery(boolean order) {
        StringBuilder query = new StringBuilder()
                .append(" SELECT DISTINCT {p:pk} ")
                .append(" FROM {Product AS p} ")
                .append(" WHERE {p:catalogVersion} = ?cv ")
                .append(" AND {p:approvalStatus} = ?as ")
                .append(" AND {p:pk} NOT IN( {{ ")
                    .append(" SELECT {b:pk} ")
                    .append(" FROM {BabyartikelSizeVariantProduct AS b} ")
                    .append(" WHERE {b:catalogVersion} = ?cv }} )");
        if (order) {
            query.append(" ORDER BY {p:PK} ASC ");
        }
        return query.toString();
    }


    /**
     * 
     * @param catalogVersion
     * @param query
     * @param beginindex
     * @param total
     *
     * @author franthescolly
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
     * 
     * @param catalogVersion
     * @param query
     *
     * @author franthescolly
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