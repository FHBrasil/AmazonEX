package de.fliegersoftware.amazon.login.addon.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import de.fliegersoftware.amazon.core.facades.AmazonUserFacade;
import de.hybris.platform.core.Registry;
import de.hybris.platform.jalo.JaloConnection;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.jalo.user.UserManager;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.spring.security.CoreAuthenticationProvider;

public class AmazonAuthenticationProvider extends CoreAuthenticationProvider {

	private static final Logger LOG = Logger.getLogger(AmazonAuthenticationProvider.class.getName());

	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	
	private SessionService sessionService;
	
	private AmazonUserFacade amazonUserFacade;

	public Authentication authenticate(Authentication authentication) throws AuthenticationException 
	{
		try
		{
			UserDetails userDetails = null;
			String customerId = getSessionService().getAttribute("customerId");
			getSessionService().removeAttribute("customerId");
			if(customerId != null)
			{
				if(getAmazonUserFacade().isAmazonCustomerExisting(customerId))
				{				
					if ((Registry.hasCurrentTenant()) && (JaloConnection.getInstance().isSystemInitialized())) 
					{
						LOG.info("authenticating through Amazon provider");
						
						userDetails = retrieveCustomer(authentication.getName());

						User user = UserManager.getInstance().getUserByLogin(authentication.getName());
		
						JaloSession.getCurrentSession().setUser(user);
						return createSuccessAuthentication(authentication, userDetails);
					}
				}
			}
		} 
		catch (UnknownIdentifierException e) 
		{
			LOG.warn(e.getMessage());
			throw new BadCredentialsException(messages.getMessage("this customer has not amazonId", "Bad credentials"));
		}
		return null;
	}
	
	  protected final UserDetails retrieveCustomer(String username) throws AuthenticationException
	  {
		UserDetails loadedUser = null;
		try
		{
		  loadedUser = getUserDetailsService().loadUserByUsername(username);
		}
		catch (DataAccessException repositoryProblem)
		{
		  throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
		}
		return loadedUser;
	  }

	protected Authentication createSuccessAuthentication(Authentication authentication, UserDetails user) 
	{
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(user.getUsername(), authentication.getCredentials(), user.getAuthorities());
		result.setDetails(authentication.getDetails());

		return result;
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
	
	public AmazonUserFacade getAmazonUserFacade() {
		return amazonUserFacade;
	}
	
	public void setAmazonUserFacade(AmazonUserFacade amazonUserFacade) {
		this.amazonUserFacade = amazonUserFacade;
	}
}
