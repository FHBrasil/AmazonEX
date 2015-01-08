package de.kpfamily.services.logistics.impl;

import de.kpfamily.core.model.BabyartikelProductModel;
import de.kpfamily.core.model.PackageSizeModel;
import de.kpfamily.services.logistics.LogisticsService;

public class DefaultLogisticsService implements LogisticsService 
{
	@Override
	public PackageSizeModel getPackageSizeFromProduct(BabyartikelProductModel product) 
	{
		PackageSizeModel dummy = new PackageSizeModel();
		dummy.setTagCode("kleines produkt");
		
		return dummy;
	}
}
