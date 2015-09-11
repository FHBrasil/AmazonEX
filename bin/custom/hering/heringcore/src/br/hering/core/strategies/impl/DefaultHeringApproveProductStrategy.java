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

import br.hering.core.model.HeringStyleVariantProductModel;
import br.hering.core.strategies.HeringApproveProductStrategy;

/**
 * @author franthescollymaneira
 *
 */
public class DefaultHeringApproveProductStrategy implements HeringApproveProductStrategy
{
	private Logger LOG = Logger.getLogger(DefaultHeringApproveProductStrategy.class);
	
	@Resource
	private ModelService modelService;
	
	/* (non-Javadoc)
	 * @see br.hering.core.strategies.HeringApproveProductStrategy#approve(de.hybris.platform.core.model.product.ProductModel, boolean)
	 */
	@Override
	public void approve(ProductModel product, boolean activateChildren)
	{
		if(product instanceof HeringStyleVariantProductModel && product.getVariantType() != null)
		{
			approve(((HeringStyleVariantProductModel) product).getBaseProduct(), false);
		}
		
		if(CHECK.equals(product.getApprovalStatus()))
		{
			LOG.debug("approving product " + product.getCode());
			
			product.setApprovalStatus(APPROVED);
			modelService.save(product);
		}
		
		if(activateChildren && CollectionUtils.isNotEmpty(product.getVariants()))
		{
			for(VariantProductModel variant : product.getVariants())
			{
				approve(variant, true);
			}
		}
	}
}