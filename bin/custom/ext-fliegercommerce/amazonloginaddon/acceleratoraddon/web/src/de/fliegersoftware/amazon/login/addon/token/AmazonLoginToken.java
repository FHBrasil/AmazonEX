package de.fliegersoftware.amazon.login.addon.token;

import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.jalo.user.LoginToken;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.jalo.user.UserManager;

public class AmazonLoginToken implements LoginToken {
	
	private User user;
	
	public AmazonLoginToken(String email)
	{
		super();
		init(email);
	}
	
	private void init(String email)
	{
		user = UserManager.getInstance().getUserByLogin(email);
	}

	@Override
	public Language getLanguage()
	{
		return this.user.getSessionLanguage();
	}

	@Override
	public String getPassword()
	{
		return this.user.getEncodedPassword();
	}

	@Override
	public User getUser()
	{
		return this.user;
	}

	@Override
	public String getValue()
	{
		return null;
	}

}
