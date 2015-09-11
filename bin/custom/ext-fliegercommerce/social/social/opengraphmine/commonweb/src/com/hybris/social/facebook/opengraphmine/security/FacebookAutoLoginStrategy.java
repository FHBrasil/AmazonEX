/**
 * 
 */
package com.hybris.social.facebook.opengraphmine.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.NativeWebRequest;

import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;


/**
 * @author franthescollymaneira
 * 
 */
public interface FacebookAutoLoginStrategy
{
	void login(FacebookUserModel user, NativeWebRequest request, HttpServletResponse response);

	void login(FacebookUserModel user, HttpServletRequest request, HttpServletResponse response);
}