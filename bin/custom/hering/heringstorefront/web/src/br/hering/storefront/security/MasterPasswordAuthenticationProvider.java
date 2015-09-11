/**
 * 
 */
package br.hering.storefront.security;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.user.UserManager;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import javax.annotation.Resource;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * This implementation works as a master-password for support, which let's support users login as any user using a
 * master password.
 * 
 * In practice it authenticates as master-user's username and request's password, and continues using request's
 * username.
 * 
 * @author herbert
 * 
 */
public class MasterPasswordAuthenticationProvider extends AcceleratorAuthenticationProvider
{
	private static final Logger LOG = Logger.getLogger(MasterPasswordAuthenticationProvider.class);
	private static final String MASTER_USERNAME_PROPERTY = "master.username";

	private ConfigurationService configurationService;

	/**
	 * Authenticate as master-user username and request's password, then returns as if user was logged in normally.
	 */
	@Override
	public Authentication authenticate(Authentication originalToken) throws AuthenticationException
	{
		try {
			// if user exists
			if (getUserService().getUserForUID((String) originalToken.getPrincipal()) != null)
			{
				// authenticate as master user
				final String username = getMasterUsername();
				final Object password = originalToken.getCredentials();
				final Object details = originalToken.getDetails();
				final MasterUserTokenWrapper token = new MasterUserTokenWrapper(username, password, originalToken);
				token.setDetails(details);

				Authentication authentication = super.authenticate(token);
				if (authentication != null)
				{
					// but uses originalToken for session data
					LOG.info("Authenticated " + originalToken.getName() + " using master-user credentials");
					JaloSession.getCurrentSession().setUser(UserManager.getInstance().getUserByLogin(originalToken.getName()));
					return authentication;
				}
			}
		} catch (UnknownIdentifierException e) {
			LOG.info(e.getMessage());
		}
		throw new BadCredentialsException(messages.getMessage("CoreAuthenticationProvider.badCredentials", "Bad credentials"));
	}

	/**
	 * Restores original authentication from request
	 */
	@Override
	protected Authentication createSuccessAuthentication(Authentication authentication, UserDetails user)
	{
		// get original authentication data
		Authentication originalToken = ((MasterUserTokenWrapper) authentication).getOriginalToken();
		UserDetails originalUser = retrieveUser(originalToken.getName());
		// returns authentication for originalToken
		return super.createSuccessAuthentication(originalToken, originalUser);
	}

	/**
	 * @return master-user's username from configuration, or default constant "sac@hering.com.br"
	 */
	private String getMasterUsername()
	{
		Configuration c = getConfigurationService().getConfiguration();
		return c.containsKey(MASTER_USERNAME_PROPERTY) ? c.getString(MASTER_USERNAME_PROPERTY) : "sac@hering.com.br";
	}

	/**
	 * Wraps original token, so it can be recovered later
	 * 
	 * @author herbert
	 * 
	 */
	private static class MasterUserTokenWrapper extends UsernamePasswordAuthenticationToken
	{
		private final Authentication originalToken;

		/**
		 * 
		 */
		public MasterUserTokenWrapper(Object principal, Object credentials, Authentication originalToken)
		{
			super(principal, credentials);
			this.originalToken = originalToken;
		}

		/**
		 * @return the originalToken
		 */
		public Authentication getOriginalToken()
		{
			return originalToken;
		}
	}

	/**
	 * @return the configurationService
	 */
	protected ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	/**
	 * @param configurationService
	 *           the configurationService to set
	 */
	public void setConfigurationService(ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}
}