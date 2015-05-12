/**
 * 
 */
package br.hering.storefront.interceptors.beforeview;

import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.util.Config;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import br.hering.storefront.interceptors.BeforeViewHandler;
import de.hybris.platform.acceleratorstorefrontcommons.forms.LoginForm;

/**
 * @author Vinicius de Souza
 *
 */
public class LoginHeaderBeforeViewHandler implements BeforeViewHandler
{
	private final String URL_HTTPS_LOGIN_HEADER = "urlHttpsLoginHeader";

	@Resource(name = "baseSiteService")
	protected BaseSiteService baseSiteService;
	
	@Override
	public void beforeView(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Exception
	{
		
		final String siteURL = Config.getParameter("website." + baseSiteService.getCurrentBaseSite().getUid() + ".https");
		LoginForm loginForm = new LoginForm();
		modelAndView.addObject("loginForm", loginForm);
		request.setAttribute(URL_HTTPS_LOGIN_HEADER, siteURL + "/pt/login/loginOver");
		
	}
}