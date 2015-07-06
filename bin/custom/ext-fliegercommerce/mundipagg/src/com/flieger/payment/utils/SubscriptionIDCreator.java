/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.payment.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 
 * @author Antony
 */
public class SubscriptionIDCreator
{

	public static String createSubscriptionID(String orderGUID)
	{

		return md5(orderGUID, "");

	}

	private static String md5(String subscriptionID, final String data)
	{

		if (subscriptionID.length() < 128)
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
			final BigInteger hash = new BigInteger(1, md.digest(subscriptionID.getBytes()));
			subscriptionID = hash.toString(16) + data;

			return md5(subscriptionID, subscriptionID);

		}
		else
		{

			return subscriptionID;

		}

	}

}
