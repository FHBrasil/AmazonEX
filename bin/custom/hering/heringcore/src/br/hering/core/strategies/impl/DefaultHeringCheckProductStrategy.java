/**
 * 
 */
package br.hering.core.strategies.impl;

import static de.hybris.platform.catalog.enums.ArticleApprovalStatus.APPROVED;
import static de.hybris.platform.catalog.enums.ArticleApprovalStatus.CHECK;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.variants.model.VariantProductModel;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import br.hering.core.strategies.HeringCheckProductStrategy;

/**
 * @author franthescollymaneira
 *
 */
public class DefaultHeringCheckProductStrategy implements HeringCheckProductStrategy
{
	private Logger LOG = Logger.getLogger(DefaultHeringCheckProductStrategy.class);
	
	@Resource
	private ModelService modelService;
	
	/* (non-Javadoc)
	 * @see br.hering.core.strategies.HeringCheckProductStrategy#check(de.hybris.platform.core.model.product.ProductModel, boolean)
	 */
	@Override
	public void check(ProductModel product, boolean deactivateChildren)
	{
		if(APPROVED.equals(product.getApprovalStatus()))
		{
			LOG.debug("checking product " + product.getCode());
			
			product.setApprovalStatus(CHECK);
			modelService.save(product);
		}
		
		if(deactivateChildren && CollectionUtils.isNotEmpty(product.getVariants()))
		{
			for(VariantProductModel variant : product.getVariants())
			{
				check(variant, true);
			}
		}
	}
}