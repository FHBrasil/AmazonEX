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
package com.hybris.addon.common.url.impl;


import org.springframework.beans.factory.annotation.Required;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;


/**
 * @author rmcotton
 * 
 */
public class ProductFrontendPathMatcherUrlDecoder extends BaseFrontendPathMatcherUrlDecoder<ProductModel>
{

	private ProductService productService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.showcase.url.impl.impl.BaseRegexUrlDecoder#translateId(java.lang.String)
	 */
	@Override
	protected ProductModel translateId(final String id)
	{
		try
		{
			return getProductService().getProductForCode(id);
		}
		catch (ModelNotFoundException|UnknownIdentifierException e)
		{
			return null;
		}
	}

	@Required
	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}

	protected ProductService getProductService()
	{
		return this.productService;
	}

}
