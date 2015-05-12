/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flieger.clearsale.utils.config;

import de.hybris.platform.util.Config;

import org.apache.commons.lang.StringUtils;


/**
 * 
 * @author flieger
 */
public final class ServiceConfig
{

	private static final String CLEARSALEANTIFRAUDE_SERVER = "environment.type";
	private static String server = Config.getParameter(CLEARSALEANTIFRAUDE_SERVER);
	private static boolean live = false;
	static
	{
		if (!StringUtils.isEmpty(server))
		{
			live = true;
		}
	}

	public static String getParameter(final String param)
	{
		if (live)
		{
			return Config.getParameter("prod." + param);
		}
		else
		{
			return Config.getParameter("hom." + param);
		}
	}
}
