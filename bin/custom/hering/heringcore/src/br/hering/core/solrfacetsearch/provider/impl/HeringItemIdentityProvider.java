/**
 * 
 */
package br.hering.core.solrfacetsearch.provider.impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.provider.impl.ItemIdentityProvider;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.Iterator;
import java.util.Set;

/**
 * @author flieger
 *
 */
public class HeringItemIdentityProvider extends ItemIdentityProvider
{
	
	private ProductModel getFirstBaseProduct(ProductModel product)
	{
		if (product instanceof VariantProductModel && ((VariantProductModel)product).getBaseProduct() instanceof VariantProductModel)
		{
			return getFirstBaseProduct(((VariantProductModel)product).getBaseProduct());
		}
		else 
		{
			return ((VariantProductModel)product).getBaseProduct();
		}
	}

	@Override
	public String getIdentifier(IndexConfig indexConfig, ItemModel item) {
		String identifier;
		if (item instanceof ProductModel) {
			ProductModel product = getFirstBaseProduct((ProductModel)item);
			
			CatalogVersionModel catalogVersion = product.getCatalogVersion();
			String code = product.getCode();
			identifier = catalogVersion.getCatalog().getId() + "/"
					+ catalogVersion.getVersion() + "/" + code;
		} 
		else {
			if (super.getCatalogTypeService().isCatalogVersionAwareModel(item)) {
				identifier = prepareCatalogAwareItemIdentifier(item);
			} else {
				identifier = item.getPk().getLongValueAsString();
			}
		}
		return identifier;
	}
	
	private String prepareCatalogAwareItemIdentifier(ItemModel item) {
		Set<String> catalogVersionUniqueKeyAttributes = super.getCatalogTypeService()
				.getCatalogVersionUniqueKeyAttribute(item.getItemtype());
		String catalogVersionContainerAttribute = super.getCatalogTypeService()
				.getCatalogVersionContainerAttribute(item.getItemtype());

		CatalogVersionModel catalogVersion = (CatalogVersionModel) super.getModelService()
				.getAttributeValue(item, catalogVersionContainerAttribute);

		Iterator<String> catalogVersionUniqueKeyIterator = catalogVersionUniqueKeyAttributes
				.iterator();
		String itemKey = "";
		while (catalogVersionUniqueKeyIterator.hasNext()) {
			String codePart = catalogVersionUniqueKeyIterator.next();
			itemKey = itemKey + "/"
					+ super.getModelService().getAttributeValue(item, codePart);
		}

		return catalogVersion.getCatalog().getId() + "/"
				+ catalogVersion.getVersion() + itemKey;
	}
	
}
