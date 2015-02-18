package de.kpfamily.services.strategies.pixi.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.Assert;

import com.pixi.core.strategies.PixiProductGetItemTagsStrategy;

import de.hybris.platform.core.model.product.ProductModel;
import de.kpfamily.core.model.BabyartikelProductModel;
import de.kpfamily.core.model.ProductPackageModel;

public class DefaultPixiProductGetItemTagsStrategy implements PixiProductGetItemTagsStrategy 
{
	/* (non-Javadoc)
	 * @see com.pixi.core.strategies.PixiProductGetItemTagsStrategy#getItemTagsByProduct(de.hybris.platform.core.model.product.ProductModel)
	 */
	@Override
	public List<String> getItemTagsByProduct(final ProductModel product) 
	{
		Assert.notNull(product);
		Assert.isInstanceOf(BabyartikelProductModel.class, product);
		
		final BabyartikelProductModel babyProduct = (BabyartikelProductModel) product;
		final List<String> itemTags = new ArrayList<String>();
		
		addPackageSizeTag(babyProduct, itemTags);
		
		return itemTags;
	}

	private void addPackageSizeTag(final BabyartikelProductModel product, final List<String> itemTags) 
	{
		if(product.getLogisticData() == null)
		{
			return;
		}
		
		CollectionUtils.forAllDo(product.getLogisticData().getPackages(), new Closure() 
		{
			@Override
			public void execute(Object pack) 
			{
				itemTags.add(((ProductPackageModel) pack).getTagCode());
			}
		});
	}
}