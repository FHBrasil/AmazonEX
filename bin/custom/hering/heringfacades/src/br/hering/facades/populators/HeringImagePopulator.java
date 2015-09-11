package br.hering.facades.populators;

import de.hybris.platform.commercefacades.product.converters.populator.ImagePopulator;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.util.Config;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HeringImagePopulator extends ImagePopulator
{
	protected static final Logger LOG = Logger.getLogger(HeringImagePopulator.class);
			
	@Override
	public void populate(final MediaModel source, final ImageData target)
	{
		super.populate(source, target);
		
		String urlPrefix = Config.getParameter("website.img");

		//LOG.info("TAG IMAGE: "+urlPrefix);
		if(isSecureRequest()) 
		{
			//urlPrefix = "/";
		}
		
		String url = source.getURL();
		//LOG.info("URL SOURCE: "+url);
		target.setUrl(urlPrefix + url);
	}

	/**
	 * @return
	 */
	private boolean isSecureRequest()
	{
		try
		{
			final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			//LOG.info("REQUEST: "+request);
			final String scheme = request.getScheme();
			//LOG.info("SCHEME: "+scheme);
			return scheme.equalsIgnoreCase("https");
		}
		catch (Exception e)
		{
			//
		}
		
		return true;
	}
}