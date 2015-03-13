package com.pixi.webservices.controller.auth;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.pixi.webservices.constants.PixiwebservicesConstants;

import de.hybris.platform.acceleratorservices.payment.utils.AcceleratorDigestUtils;

public class PixiTokenUtils 
{
	private static final long EXPIRATION_TIME_MS = 300000l;

	public static final char TOKEN_DELIM = ':';
	
	@Resource(name = "acceleratorDigestUtils")
	private AcceleratorDigestUtils digestUtils;
	
	public boolean validateToken(final String token) throws InvalidKeyException, NoSuchAlgorithmException
	{
		final String[] tokenSlice = StringUtils.split(token, PixiTokenUtils.TOKEN_DELIM);
		final String data = tokenSlice[0] + tokenSlice[1];
		final String signature = tokenSlice[2];

		final String computedToken = digestUtils.getPublicDigest(data, getServerSecret());
		
		if(computedToken.equals(signature))
		{
			return System.currentTimeMillis() <= Long.valueOf(tokenSlice[1]);
		}
		
		return false;
	}

	public String generateToken(String user) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException 
	{
		String expiration = String.valueOf(System.currentTimeMillis() + EXPIRATION_TIME_MS);
		String hash = digestUtils.getPublicDigest(user + expiration, getServerSecret());
		String[] token = {user, expiration, hash};
		
		return URLEncoder.encode(StringUtils.join(token, TOKEN_DELIM), "UTF-8");
	}
	
	public User getTokenUser()
	{
		String user = PixiwebservicesConstants.Pixi.USERNAME;
		String pass = PixiwebservicesConstants.Pixi.PASSWORD;
		
		return new User(user, pass, Collections.singleton(new SimpleGrantedAuthority(PixiwebservicesConstants.Pixi.PIXI_ROLE)));
	}
	
	private String getServerSecret() 
	{
		return PixiwebservicesConstants.Pixi.SECRET;
	}
}