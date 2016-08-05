package de.fliegersoftware.amazon.login.addon.security.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import de.fliegersoftware.amazon.core.services.AmazonUserService;
import de.fliegersoftware.amazon.login.addon.security.AmazonAutoLoginStrategy;
import de.fliegersoftware.amazon.login.addon.token.AmazonLoginToken;
import de.hybris.platform.acceleratorstorefrontcommons.security.GUIDCookieStrategy;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.session.SessionService;


public class DefaultAmazonAutoLoginStrategy implements AmazonAutoLoginStrategy
{

	@Resource
	private AuthenticationManager authenticationManager;
	
	@Resource
	private CustomerFacade customerFacade;
	
	@Resource
	private AmazonUserService amazonUserService;
	
	@Resource
	private GUIDCookieStrategy guidCookieStrategy;
	
	@Resource
	private RememberMeServices rememberMeServices;
	
	private SessionService sessionService;
	
	@Override
	public void login(String customerId, final HttpServletRequest request, final HttpServletResponse response)
	{		
		try
		{	
			CustomerModel amazonCustomer = amazonUserService.getAmazonCustomer(customerId);
			
			final AmazonLoginToken credentials = new AmazonLoginToken(amazonCustomer.getUid());
	
			final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(amazonCustomer.getUid(), credentials);
			token.setDetails(new WebAuthenticationDetails(request));
			
			getSessionService().setAttribute("customerId", customerId);
		
			final Authentication authentication = authenticationManager.authenticate(token);

			SecurityContextHolder.getContext().setAuthentication(authentication);
			customerFacade.loginSuccess();
			guidCookieStrategy.setCookie(request, response);
			rememberMeServices.loginSuccess(request, response, token);
		}
		catch (final Exception e)
		{
			SecurityContextHolder.getContext().setAuthentication(null);
			e.printStackTrace();
		}
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public CustomerFacade getCustomerFacade() {
		return customerFacade;
	}

	public void setCustomerFacade(CustomerFacade customerFacade) {
		this.customerFacade = customerFacade;
	}

	public AmazonUserService getAmazonUserService() {
		return amazonUserService;
	}

	public void setAmazonUserService(AmazonUserService amazonUserService) {
		this.amazonUserService = amazonUserService;
	}

	public GUIDCookieStrategy getGuidCookieStrategy() {
		return guidCookieStrategy;
	}

	public void setGuidCookieStrategy(GUIDCookieStrategy guidCookieStrategy) {
		this.guidCookieStrategy = guidCookieStrategy;
	}

	public RememberMeServices getRememberMeServices() {
		return rememberMeServices;
	}

	public void setRememberMeServices(RememberMeServices rememberMeServices) {
		this.rememberMeServices = rememberMeServices;
	}
	
	protected SessionService getSessionService()
	{
		return sessionService;
	}

	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}
}
