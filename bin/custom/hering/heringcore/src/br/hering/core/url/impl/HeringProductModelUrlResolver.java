/**
 * 
 */
package br.hering.core.url.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.url.impl.DefaultProductModelUrlResolver;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.util.Config;

/**
 * @author herbert
 *
 */
public class HeringProductModelUrlResolver extends DefaultProductModelUrlResolver
{
	@Override
	protected String resolveInternal(ProductModel source)
	{
		StringBuilder urlBuilder = new StringBuilder();
		ProductModel productModel = source;
		BaseSiteModel productSite = null;
		for(BaseStoreModel store : productModel.getCatalogVersion().getCatalog().getBaseStores())
		{
			for(BaseSiteModel site : store.getCmsSites())
			{
				productSite = site;
			}
		}
		BaseSiteModel currentBaseSite = getBaseSiteService().getCurrentBaseSite();
		if(currentBaseSite != null && !currentBaseSite.equals(productSite)) {
			urlBuilder.append(Config.getString("website." + productSite.getUid() + ".http", ""));
		}
		return urlBuilder.append(super.resolveInternal(source)).toString();
	}
}