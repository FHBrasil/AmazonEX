/**
 * 
 */
package com.flieger.notificationservices.comparators;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.Comparator;

/**
 * @author Vinicius de Souza
 *
 */
public class NotifyMeSimilarProductComparator implements Comparator<ProductModel>
{
	@Override
	public int compare(ProductModel o1, ProductModel o2)
	{
		if(o1 != null && o2 != null)
		{
			return Integer.compare(o1.getSale30(), o2.getSale30());
		}
		
		if(o1 == null)
		{
			return -1;
		}
		
		if(o2 == null)
		{
			return 1;
		}
		
		return 0;
	}
}