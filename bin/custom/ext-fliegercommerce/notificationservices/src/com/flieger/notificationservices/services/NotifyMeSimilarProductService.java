/**
 * 
 */
package com.flieger.notificationservices.services;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;
import java.util.Set;

import com.flieger.notificationservices.data.NotifymeData;

/**
 * @author Vinicius de Souza
 *
 */
public interface NotifyMeSimilarProductService
{
	List<ProductModel> getSimilarProduct(final ProductModel product) throws Exception;
	/**
	 * Busco os produtos similares de um determinado produto.
	 * @param product Produto de referência.
	 * @return Lista de produtos similares.
	 * @throws Exception É lançado quando ocorre um erro na execução.
	 */
	List<ProductModel> getSimilarProduct(final ProductModel product, final CategoryModel superCategoryModel) throws Exception;
	
	List<ProductModel> getSimilarProduct(final String productCode, final String superCategoryCode) throws Exception;
	
	boolean isSimilarProduct(final ProductModel product, final ProductModel similar) throws Exception;

	List<ProductModel> getSimilarProduct(String productCode) throws Exception;
	
	void notifyMe(List<ProductModel> similars, NotifymeData notifymeData, final String siteUrl) throws Exception;
}