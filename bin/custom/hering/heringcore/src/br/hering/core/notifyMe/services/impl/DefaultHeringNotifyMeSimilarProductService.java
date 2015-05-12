/**
 * 
 */
package br.hering.core.notifyMe.services.impl;

import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.hering.core.model.HeringProductModel;
import br.hering.core.model.HeringSizeVariantProductModel;
import br.hering.core.model.HeringStyleVariantProductModel;
import br.hering.core.notifyMe.dao.HeringNotifyMeSimilarProductDao;
import br.hering.core.util.MccSiteUrlHelper;

import com.flieger.notificationservices.data.NotifymeData;
import com.flieger.notificationservices.facades.impl.DefaultNotifyMeSimilarProductFacade;
import com.flieger.notificationservices.services.impl.DefaultNotifyMeSimilarProductService;

/**
 * @author Vinicius de Souza
 *
 */
public class DefaultHeringNotifyMeSimilarProductService extends DefaultNotifyMeSimilarProductService
{
	@Resource
	private CMSSiteService cmsSiteService;
	
	@Resource
	private MccSiteUrlHelper mccSiteUrlHelper;
	
	private static final Logger LOG = Logger.getLogger(DefaultHeringNotifyMeSimilarProductService.class);
	
	@Override
	public boolean isSimilarProduct(ProductModel product, ProductModel similar) throws Exception
	{
		try
		{
			boolean isSimilar = super.isSimilarProduct(product, similar);
			boolean isSize = isSize(product, similar);
			boolean isGender = isGender(product, similar);
			
			return isSimilar && isSize && isGender;
		}
		catch(IllegalArgumentException e)
		{
			LOG.error("Product: "+product.getCode()+", Similar: "+similar.getCode(), e);
		}
		return false;
	}
	
	@Override
	public void notifyMe(List<ProductModel> similars, NotifymeData notifymeData, String siteUrl)
	{
		if(StringUtils.isEmpty(siteUrl))
		{
			siteUrl = getSiteUrl();
		}
		
		super.notifyMe(similars, notifymeData, siteUrl);
	}

	/**
	 * @return
	 */
	private String getSiteUrl()
	{
		final String name = cmsSiteService.getCurrentSite().getName();
		final String siteURL = mccSiteUrlHelper.getSitesAndUrls().get(name);
		
		return siteURL;
	}

	/**
	 * @param product
	 * @param similar
	 * @return
	 * @throws Exception 
	 */
	private boolean isGender(ProductModel product, ProductModel similar) throws Exception
	{
		if(product instanceof HeringProductModel)
		{
			HeringProductModel hp = (HeringProductModel) product;
	
			if(similar instanceof HeringProductModel)
			{
				HeringProductModel hs = (HeringProductModel) similar;
				
				if(hp.getGenders() != null && hs.getGenders() != null)
				{
					return hp.getGenders().containsAll(hs.getGenders()); 
				}
			}
			else if(similar instanceof HeringSizeVariantProductModel)
			{
				return isGender(hp, ((HeringSizeVariantProductModel)similar).getBaseProduct());
			}
			else if(similar instanceof HeringStyleVariantProductModel)
			{
				return isGender(hp, ((HeringStyleVariantProductModel)similar).getBaseProduct());
			}
		}
		else if(product instanceof HeringSizeVariantProductModel)
		{
			return isGender(((HeringSizeVariantProductModel)product).getBaseProduct(), similar);
		}
		else if(product instanceof HeringStyleVariantProductModel)
		{
			return isGender(((HeringStyleVariantProductModel)product).getBaseProduct(), similar);
		}
		return false;
	}

	/**
	 * @param product
	 * @param similar
	 * @return 
	 */
	private boolean isSize(ProductModel product, ProductModel similar)
	{
		if(product instanceof HeringSizeVariantProductModel && similar instanceof HeringSizeVariantProductModel)
		{
			return ((HeringSizeVariantProductModel) product).getSize().compareTo(((HeringSizeVariantProductModel) similar).getSize()) == 0;
		}
		return false;
	}
}