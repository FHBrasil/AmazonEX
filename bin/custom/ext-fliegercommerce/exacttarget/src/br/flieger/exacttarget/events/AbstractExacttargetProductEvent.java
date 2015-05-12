/**
 * 
 */
package br.flieger.exacttarget.events;

import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.util.Config;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import br.hering.core.model.HeringSizeVariantProductModel;
import br.hering.core.model.HeringStyleVariantProductModel;


/**
 * @author Vinicius de Souza
 *
 */
public abstract class AbstractExacttargetProductEvent extends AbstractExacttargetEvent
{
	private String siteUrl;
	
	protected String getPrice(ProductModel product)
	{
		Double currentPrice = null;
		for (PriceRowModel priceModel : product.getEurope1Prices())
		{
			if(priceModel.getDateRange().encloses(new Date(System.currentTimeMillis())))
			{
				if(currentPrice == null || priceModel.getPrice().doubleValue() < currentPrice.doubleValue())
				{
					currentPrice = priceModel.getPrice();
				}
			}
		}
		return  DECIAL_FORMAT.format(currentPrice.doubleValue());
	}
	
	protected String getProductLink(final ProductModel product)
	{
		if(StringUtils.isNotEmpty(siteUrl))
		{
			return "<a href=\""+siteUrl+"p/"+product.getCode()+getLinkTag()+"\">"+product.getName()+"</a>";
		}
		return "<a href=\"#\">"+product.getName()+"</a>";
	}
	
	protected String getImageProductLink(final ProductModel product) throws Exception
	{
		if(StringUtils.isNotEmpty(siteUrl))
		{
			return "<a href=\""+siteUrl+"p/"+product.getCode()+getLinkTag()+"\">"+getImage(product)+"</a>";
		}
		return "<a href=\"#\">"+getImage(product)+"</a>";
	}
	
	protected String getImage(ProductModel product) throws Exception
	{
		return "<img src=\""+getImageUrl(product)+"\" />";
	}

	protected String getImageUrl(final ProductModel product) throws Exception
	{
		final MediaModel mediaModel = product.getPicture();
		
		if(mediaModel == null)
		{
			try
			{
   			ProductModel base = ((VariantProductModel) product).getBaseProduct();
   			if(base != null)
   			{
   				return getImageUrl(base);
   			}
			}
			catch(ClassCastException e)
			{
				throw new Exception("Image not found! ("+product.getCode()+")");
			}
		}
		
		return getImageUrl(mediaModel);
	}
	
	protected String getImageUrl(final MediaModel mediaModel)
	{
		if (mediaModel == null)
		{
			return null;
		}
		final String urlPrefix = Config.getParameter("website.img");
		final String urlForSite = urlPrefix + mediaModel.getURL();
		return urlForSite;
	}

	/**
	 * @return the siteUrl
	 */
	public String getSiteUrl()
	{
		return siteUrl;
	}
	
	/**
	 * @param siteUrl the siteUrl to set
	 */
	public void setSiteUrl(String siteUrl)
	{
		this.siteUrl = siteUrl;
	}
}