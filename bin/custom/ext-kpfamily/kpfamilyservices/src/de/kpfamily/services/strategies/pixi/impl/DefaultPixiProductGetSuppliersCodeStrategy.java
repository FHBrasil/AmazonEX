package de.kpfamily.services.strategies.pixi.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.Assert;

import com.pixi.core.strategies.PixiProductGetSuppliersCodeStrategy;

import de.hybris.platform.core.model.product.ProductModel;
import de.kpfamily.core.model.BabyartikelProductModel;
import de.kpfamily.core.model.ProductSupplierModel;

public class DefaultPixiProductGetSuppliersCodeStrategy implements PixiProductGetSuppliersCodeStrategy 
{
	@Override
	public List<String> getSuppliersCode(final ProductModel product) 
	{
		Assert.notNull(product);
		Assert.isInstanceOf(BabyartikelProductModel.class, product);
		
		BabyartikelProductModel babyartikelProduct = (BabyartikelProductModel) product;
		
		List<ProductSupplierModel> suppliers = babyartikelProduct.getSuppliers();
		
		if(CollectionUtils.isEmpty(suppliers))
		{
			return Collections.emptyList();
		}
		
		final List<String> supplierCodeList = new ArrayList<String>();
		
		for(final ProductSupplierModel supplier :  suppliers)
		{
			supplierCodeList.add(supplier.getCode());
		}
		
		return supplierCodeList;
	}
}