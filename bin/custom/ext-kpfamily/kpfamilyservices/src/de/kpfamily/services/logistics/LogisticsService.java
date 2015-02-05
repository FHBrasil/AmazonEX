package de.kpfamily.services.logistics;

import de.kpfamily.core.model.BabyartikelProductModel;
import de.kpfamily.core.model.PackageSizeModel;

public interface LogisticsService 
{
	PackageSizeModel getPackageSizeFromProduct(BabyartikelProductModel product);
}