package com.hybris.addon.common.url.impl;

import org.springframework.beans.factory.annotation.Required;

import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

public class CategoryFrontendPathMatcherUrlDecoder extends BaseFrontendPathMatcherUrlDecoder<CategoryModel>{

	private CategoryService categoryService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.showcase.url.impl.impl.BaseRegexUrlDecoder#translateId(java.lang.String)
	 */
	@Override
	protected CategoryModel translateId(final String id)
	{
		try
		{
			return getCategoryService().getCategoryForCode(id);
		}
		catch (ModelNotFoundException|UnknownIdentifierException e)
		{
			return null;
		}
	}

	@Required
	public void setCategoryService(final CategoryService categoryService)
	{
		this.categoryService = categoryService;
	}

	public CategoryService getCategoryService()
	{
		return this.categoryService;
	}

}
