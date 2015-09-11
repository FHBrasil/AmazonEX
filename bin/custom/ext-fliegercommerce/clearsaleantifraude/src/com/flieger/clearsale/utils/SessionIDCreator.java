/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.clearsale.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;


/**
 * 
 * @author Antony
 */
public class SessionIDCreator
{

	public static String createSessionID(String orderGUID)
	{

		return md5(orderGUID, "");

	}

	private static String md5(String sessionID, final String data)
	{

		if (sessionID.length() < 128)
		{

			MessageDigest md = null;
			try
			{
				md = MessageDigest.getInstance("MD5");
			}
			catch (final NoSuchAlgorithmException e)
			{
				//
			}
			final BigInteger hash = new BigInteger(1, md.digest(sessionID.getBytes()));
			sessionID = hash.toString(16) + data;

			return md5(sessionID, sessionID);

		}
		else
		{

			return sessionID;

		}

	}

}
