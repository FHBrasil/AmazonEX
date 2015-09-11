package de.fliegersoftware.amazon.login.addon.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AmazonAutoLoginStrategy {
	
	void login(final String username, String customerId, final HttpServletRequest request, final HttpServletResponse response);
}
