/**
 * 
 */
package br.hering.storefront.util;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.AbstractController;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * @author herbert
 *
 */
public final class RequestUtil
{
	private RequestUtil() {
	}

	public static String getReturnRedirectUrl(final HttpServletRequest request)
	{
		final String referer = getReferer(request);
		if (StringUtils.isNotBlank(referer))
		{
			return AbstractController.REDIRECT_PREFIX + referer;
		}
		
		return AbstractController.REDIRECT_PREFIX + '/';
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getReferer(final HttpServletRequest request)
	{
		return request.getHeader("Referer");
	}
}
