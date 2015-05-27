/**
 * 
 */
package br.hering.core.product.dao.impl;

import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Arrays;
import java.util.Collection;

import br.hering.core.model.HeringProductModel;
import br.hering.core.model.HeringStyleVariantProductModel;
import br.hering.core.product.dao.HeringProductApprovalProcessDao;

/**
 * @author franthescollymaneira
 *
 */
public class DefaultHeringProductApprovalProcessDao extends AbstractItemDao implements HeringProductApprovalProcessDao
{
	@Override
	public SearchResultPage<HeringStyleVariantProductModel> findStyleVariantsToDeactivate(final Collection<CatalogVersionModel> versions, int count)
	{
		final StringBuilder query = new StringBuilder();
		query.append("SELECT DISTINCT tbl.pk FROM  ");
		query.append("({{ ");
		query.append("      SELECT x.pk, SUM(x.stockQuantity) as stockQuantity ");
		query.append("      	FROM ({{ ");
		query.append("      		  SELECT DISTINCT {stylep.PK} AS pk, ");
		query.append("      		  		CASE WHEN ({sl.available} < 0 or {sl.available} IS NULL) THEN 0  ");
		query.append("      		   		ELSE {sl.available} END AS stockQuantity ");
		query.append("      		  FROM { ");
		query.append("      		   	     	HeringStyleVariantProduct AS stylep ");
		query.append("      		   	    	 	JOIN HeringSizeVariantProduct AS sizep ON {sizep.baseProduct} = {stylep.pk} ");
		query.append("      		   	    	 	LEFT OUTER JOIN StockLevel AS sl ON {sl.productcode} = {sizep.code} ");
		query.append("        		} ");
		query.append("      		  WHERE {stylep.approvalStatus} = ?as AND {stylep.catalogVersion} IN (?cvl) ");
		query.append("      	}})  x ");
		query.append("      GROUP BY x.pk HAVING SUM(x.stockQuantity) = 0 ");
		query.append("}} ");
		query.append("UNION ");
		query.append("{{ ");
		query.append("      SELECT DISTINCT {sv:PK}, 1 as stockQuantity ");
		query.append("      FROM {  ");
		query.append("      		HeringStyleVariantProduct! AS sv  ");
		query.append("      } WHERE {sv:picture} IS NULL AND {sv:approvalStatus} = ?as AND {sv:catalogVersion} IN (?cvl)  ");
		query.append("}}) tbl ");
				
		final FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query);
		fsQuery.setStart(0);
		fsQuery.setCount(count);
		fsQuery.setNeedTotal(false);
		fsQuery.setResultClassList(Arrays.asList(HeringStyleVariantProductModel.class));
		fsQuery.addQueryParameter("as", ArticleApprovalStatus.APPROVED);
		fsQuery.addQueryParameter("cvl", versions);
		
		return new SearchResultPage<HeringStyleVariantProductModel>(getFlexibleSearchService(), fsQuery);
	}
	
   @Override
	public SearchResultPage<HeringProductModel> findBaseProductsToDeactivate(final Collection<CatalogVersionModel> versions, int count)
	{
   	final StringBuilder query = new StringBuilder();
   	query.append("SELECT DISTINCT {h.PK}  ");
   	query.append("FROM { ");
   	query.append("		HeringProduct AS h ");
   	query.append("} ");
   	query.append("WHERE {h.pk} NOT IN({{  ");
   	query.append("		SELECT DISTINCT {hs2.baseProduct}  ");
   	query.append("		FROM { ");
   	query.append("			HeringStyleVariantProduct! AS hs2 ");
   	query.append("		} ");
   	query.append("		WHERE {hs2.approvalStatus} = ?as AND {hs2.catalogVersion} IN (?cvl) ");
   	query.append("}}) ");
   	query.append("AND {h.approvalStatus} = ?as AND {h.catalogVersion} IN (?cvl) ");
   			
		final FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query);
		fsQuery.setStart(0);
		fsQuery.setCount(count);
		fsQuery.setNeedTotal(false);
		fsQuery.setResultClassList(Arrays.asList(HeringProductModel.class));
		fsQuery.addQueryParameter("as", ArticleApprovalStatus.APPROVED);
		fsQuery.addQueryParameter("cvl", versions);
		
		return new SearchResultPage<HeringProductModel>(getFlexibleSearchService(), fsQuery);
	}
	
   @Override
	public SearchResultPage<HeringStyleVariantProductModel> findStyleVariantsToActivate(final Collection<CatalogVersionModel> versions, int count)
   {
   	final StringBuilder query = new StringBuilder();
   	query.append("SELECT x.pk ");
   	query.append("	FROM ({{ ");
   	query.append("		  SELECT DISTINCT {stylep.PK} AS pk, ");
   	query.append("		  		CASE WHEN ({sl.available} < 0 or {sl.available} IS NULL) THEN 0  ");
   	query.append("		   		ELSE {sl.available} END AS stockQuantity ");
   	query.append("		  FROM { ");
   	query.append("		   	     	HeringStyleVariantProduct AS stylep ");
   	query.append("		   	    	 	JOIN HeringSizeVariantProduct AS sizep ON {sizep.baseProduct} = {stylep.pk} ");
   	query.append("		   	    	 	LEFT OUTER JOIN StockLevel AS sl ON {sl.productcode} = {sizep.code} ");
   	query.append("  		} ");
   	query.append("		  WHERE {stylep:picture} IS NOT NULL AND {stylep.approvalStatus} = ?cs AND {stylep.catalogVersion} IN (?cvl) ");
   	query.append("	}})  x ");
   	query.append("GROUP BY x.pk HAVING SUM(x.stockQuantity) > 0 ");

		final FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query);
		fsQuery.setStart(0);
		fsQuery.setCount(count);
		fsQuery.setNeedTotal(false);
		fsQuery.setResultClassList(Arrays.asList(HeringProductModel.class));
		fsQuery.addQueryParameter("cs", ArticleApprovalStatus.CHECK);
		fsQuery.addQueryParameter("cvl", versions);
		
		return new SearchResultPage<HeringStyleVariantProductModel>(getFlexibleSearchService(), fsQuery);
   }
}