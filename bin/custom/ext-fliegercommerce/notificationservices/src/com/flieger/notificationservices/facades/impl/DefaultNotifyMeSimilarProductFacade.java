/**
 * 
 */
package com.flieger.notificationservices.facades.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.flieger.notificationservices.data.NotifymeData;
import com.flieger.notificationservices.facades.NotifyMeSimilarProductFacade;
import com.flieger.notificationservices.services.NotifyMeSimilarProductService;

/**
 * @author Vinicius de Souza
 *
 */
public class DefaultNotifyMeSimilarProductFacade implements NotifyMeSimilarProductFacade
{
	@Resource
	private ProductService productService;
	
	@Resource
	private NotifyMeSimilarProductService notifyMeSimilarProductService;
	
	private static final Logger LOG = Logger.getLogger(DefaultNotifyMeSimilarProductFacade.class);

	@Override
	public void notifyMe(NotifymeData notifymeData, Comparator comparator, Integer limit, String siteUrl) throws Exception
	{
		if(StringUtils.isNotEmpty(notifymeData.getCode()))
		{
			List<ProductModel> similars = notifyMeSimilarProductService.getSimilarProduct(notifymeData.getCode());
			
			if(CollectionUtils.isNotEmpty(similars))
			{
				if(comparator != null)
				{
					Collections.sort(similars, comparator);
				}
				
				if(limit != null && limit.intValue() > 0 && similars.size() > limit.intValue())
				{
					similars = similars.subList(0, limit.intValue());
				}
				notifyMeSimilarProductService.notifyMe(similars, notifymeData, siteUrl);
			}
		}
		else
		{
			throw new IllegalArgumentException("Argument cannot empty.");
		}		
	}
}