package com.pixi.webservices.controller.auth;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.pixi.webservices.constants.PixiwebservicesConstants;

public class PixiTokenUtils 
{
	private static final long EXPIRATION_TIME_MS = 300000l;

	public static final char TOKEN_DELIM = ':';
	
	public boolean validateToken(final String token) throws InvalidKeyException, NoSuchAlgorithmException
	{
		final String[] tokenSlice = StringUtils.split(token, PixiTokenUtils.TOKEN_DELIM);
		final String data = tokenSlice[0] + tokenSlice[1];
		final String signature = tokenSlice[2];

		final String computedToken = getPublicDigest(data, getServerSecret());
		
		if(computedToken.equals(signature))
		{
			return System.currentTimeMillis() <= Long.valueOf(tokenSlice[1]);
		}
		
		return false;
	}
	
	public static String getPublicDigest(final String customValues,
			final String key) throws NoSuchAlgorithmException,
			InvalidKeyException {
		final Base64 encoder = new Base64();
		final Mac sha1Mac = Mac.getInstance("HmacSHA1");
		final SecretKeySpec publicKeySpec = new SecretKeySpec(key.getBytes(),
				"HmacSHA1");
		sha1Mac.init(publicKeySpec);

		final byte[] publicBytes = sha1Mac.doFinal(customValues.getBytes());
		final String publicDigest = new String(encoder.encode(publicBytes));

		return publicDigest.replaceAll("\n", "");
	}

	public String generateToken(String user) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException 
	{
		String expiration = String.valueOf(System.currentTimeMillis() + EXPIRATION_TIME_MS);
		String hash = getPublicDigest(user + expiration, getServerSecret());
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