/**
 * 
 */
package br.hering.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.config.SolrConfig;
import de.hybris.platform.solrfacetsearch.config.SolrServerMode;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.indexer.exceptions.IndexerException;
import de.hybris.platform.solrfacetsearch.indexer.impl.DefaultIndexer;
import de.hybris.platform.solrfacetsearch.indexer.spi.Exporter;
import de.hybris.platform.solrfacetsearch.indexer.spi.SolrDocumentFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.solr.common.SolrInputDocument;

import br.hering.core.model.HeringProductModel;

/**
 * @author franthescollymaneira
 *
 */
public class DefaultHeringSolrIndexer extends DefaultIndexer
{
	private static final Logger LOG = Logger.getLogger(DefaultHeringSolrIndexer.class);
	
	private SolrDocumentFactory solrDocumentFactory;
	
	@Resource
	private VariantsUtils variantsUtils;
	
	/* (non-Javadoc)
	 * @see de.hybris.platform.solrfacetsearch.indexer.impl.DefaultIndexer#indexItems(java.util.Collection, de.hybris.platform.solrfacetsearch.config.IndexConfig, de.hybris.platform.solrfacetsearch.config.IndexedType, de.hybris.platform.solrfacetsearch.config.SolrConfig)
	 */
	@Override
	public Collection<SolrInputDocument> indexItems(Collection<ItemModel> items, IndexConfig indexConfig, IndexedType indexedType, SolrConfig solrConfig) throws IndexerException
	{
		if(!"Product".equals(indexedType.getUniqueIndexedTypeCode()))
		{
			return super.indexItems(items, indexConfig, indexedType, solrConfig);
		}
		
		if (items == null) 
		{
			return Collections.EMPTY_LIST;
		}

		Collection documents = new ArrayList(items.size());

		for (ItemModel itemModel : items) 
		{
			String originalCode = ((ProductModel)itemModel).getCode();
			if (LOG.isDebugEnabled()) 
			{
				LOG.debug("Indexing product with code " + originalCode);
			}
			
			if(itemModel instanceof HeringProductModel)
			{
				itemModel = variantsUtils.getFirstAvailableSizeVariant((HeringProductModel) itemModel);
				
				if(itemModel == null)
				{
					LOG.debug("null, skipping: " + originalCode);
					continue;
				}
				
				LOG.debug("swap to the first available size variant item with code " + ((ProductModel)itemModel).getCode());
			}
			
			try 
			{
				SolrInputDocument solrDocument = solrDocumentFactory.createInputDocument(itemModel, indexConfig, indexedType);
				documents.add(solrDocument);
			} 
			catch (FieldValueProviderException e) 
			{
				LOG.error("error", e);
				throw new IndexerException(e.getMessage(), e);
			}
		}
		
		if(CollectionUtils.isEmpty(documents))
		{
			return Collections.EMPTY_LIST; 
		}

		SolrServerMode serverMode = solrConfig.getMode();
		
		Exporter exporter = getExporter(serverMode);
		exporter.exportToUpdateIndex(documents, indexConfig, solrConfig, indexedType);
		
		return documents;
	}
	
	@Override
	public void setSolrDocumentFactory(final SolrDocumentFactory solrDocumentFactory) 
	{
		super.setSolrDocumentFactory(solrDocumentFactory);
		this.solrDocumentFactory = solrDocumentFactory;
	}
}