/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package com.pixi.webservices.constants;

import de.hybris.platform.util.Config;


/**
 * Global class for all Pixiwebservices constants. You can add global constants for your extension into this class.
 */
@SuppressWarnings("deprecation")
public final class PixiwebservicesConstants extends GeneratedPixiwebservicesConstants
{
	public static final String EXTENSIONNAME = "pixiwebservices";

	private PixiwebservicesConstants()
	{
		//empty to avoid instantiating this constant class
	}
	
	public static interface Pixi
	{
		String SHOP_ID = Config.getParameter("pixiwebservices.pixi.shop.id");
		String USERNAME = Config.getParameter("pixiwebservices.pixi.auth.username");
		String PASSWORD = Config.getParameter("pixiwebservices.pixi.auth.password");
		String SECRET = Config.getParameter("pixiwebservices.pixi.auth.secret");
		String DATABASE = Config.getParameter("pixiwebservices.pixi.database");
		String COMPANY = Config.getParameter("pixiwebservices.pixi.user.company");
		String BATCHSIZE = Config.getParameter("pixiwebservices.pixi.catalog.export.batchsize");
		String GUEST_ROLE = Config.getParameter("pixiwebservices.pixi.guest.role");
		String PIXI_ROLE = Config.getParameter("pixiwebservices.pixi.auth.role");
	}

	public static interface Product
	{
		String GENERATOR_INFO = Config.getParameter("pixiwebservices.product.export.generator.info");
		double VERSION = Config.getDouble("pixiwebservices.product.export.version", 1d);
		String MIME_SOURCE_FORMAT = Config.getParameter("pixiwebservices.product.export.picture.source");
		String PRICE_TYPE_NET = Config.getParameter("pixiwebservices.product.export.price.type.net");
		String PRICE_TYPE_GROS = Config.getParameter("pixiwebservices.product.export.price.type.gros");
	}
	
	public static interface Order
	{
		String GENERATOR_INFO = Config.getParameter("pixiwebservices.order.export.generator.info");
		double VERSION = Config.getDouble("pixiwebservices.order.version", 1d);
		String TYPE = Config.getParameter("pixiwebservices.order.type");
		String ADRESS_TYPE = Config.getParameter("pixiwebservices.order.address.party.type");
		String PRICE_TYPE = Config.getParameter("pixiwebservices.order.item.price.type");
	}
}