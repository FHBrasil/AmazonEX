/**
 * 
 */
package com.hybris.social.facebook.opengraphmine.jalo.user;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.jalo.user.LoginToken;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.jalo.user.UserManager;

import org.springframework.util.Assert;

import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;


/**
 * @author franthescollymaneira
 * 
 */
//XXX FB-HRG
public class FacebookLoginToken implements LoginToken
{
	private User user;

	/**
	 * @param facebookUser
	 */
	public FacebookLoginToken(final FacebookUserModel facebookUser)
	{
		super();

		Assert.notNull(facebookUser);
		Assert.notNull(facebookUser.getLinkedCustomer());

		init(facebookUser);
	}

	/**
	 * @param facebookUser
	 * 
	 */
	private void init(final FacebookUserModel facebookUser)
	{
		final CustomerModel linkedCustomer = facebookUser.getLinkedCustomer();
		user = UserManager.getInstance().getUserByLogin(linkedCustomer.getUid());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.jalo.user.LoginToken#getLanguage()
	 */
	@Override
	public Language getLanguage()
	{
		return this.user.getSessionLanguage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.jalo.user.LoginToken#getPassword()
	 */
	@Override
	public String getPassword()
	{
		return this.user.getEncodedPassword();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.jalo.user.LoginToken#getUser()
	 */
	@Override
	public User getUser()
	{
		return this.user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.jalo.user.LoginToken#getValue()
	 */
	@Override
	public String getValue()
	{
		return null;
	}
}