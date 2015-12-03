package de.fliegersoftware.amazon.login.addon.token;

import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.jalo.user.LoginToken;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.jalo.user.UserManager;

public class AmazonLoginToken implements LoginToken {
	
	private User user;
	
	public AmazonLoginToken(String uid)
	{
		super();
		init(uid);
	}
	
	private void init(String uid)
	{
		user = UserManager.getInstance().getUserByLogin(uid);
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
