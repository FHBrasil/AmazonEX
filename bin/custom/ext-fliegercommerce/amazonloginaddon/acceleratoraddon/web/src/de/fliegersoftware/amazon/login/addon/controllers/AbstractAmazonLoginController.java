/*
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
package de.fliegersoftware.amazon.login.addon.controllers;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import de.fliegersoftware.amazon.core.facades.AmazonCustomerFacade;
import de.fliegersoftware.amazon.core.facades.AmazonUserFacade;
import de.fliegersoftware.amazon.core.services.AmazonConfigService;
import de.fliegersoftware.amazon.login.addon.constants.AmazonLoginAddonConstants;
import de.fliegersoftware.amazon.login.addon.forms.AmazonLoginForm;
import de.fliegersoftware.amazon.login.addon.security.AmazonAutoLoginStrategy;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.yacceleratorstorefront.controllers.pages.AbstractLoginPageController;
import de.hybris.platform.yacceleratorstorefront.security.AutoLoginStrategy;

@Controller
public abstract class AbstractAmazonLoginController extends AbstractLoginPageController
{
	
	private static final String AMAZON_ACCOUNT_CONFIRMATION_PAGE = "amazonAccountConfirmation";
	
	@Resource(name = "amazonCustomerFacade")
	private AmazonCustomerFacade amazonCustomerFacade;
	
	@Resource(name = "amazonUserFacade")
	private AmazonUserFacade amazonUserFacade;
	
	@Resource(name = "autoLoginStrategy")
	private AutoLoginStrategy autoLoginStrategy;
	
	@Resource
	protected AmazonConfigService amazonConfigService;
	
	@Resource
	protected AmazonAutoLoginStrategy amazonAutoLoginStrategy;
	
	
	protected String prepareAssociateAccount(Model model, String name, String email, String customerId)
	{
		AmazonLoginForm amazonLoginForm = new AmazonLoginForm();

		model.addAttribute("amazonLoginForm", amazonLoginForm);
		amazonLoginForm.setName(name);
		amazonLoginForm.setEmail(email);
		amazonLoginForm.setAmazonId(customerId);
		
		try 
		{
			storeCmsPageInModel(model, getContentPageForLabelOrId(AMAZON_ACCOUNT_CONFIRMATION_PAGE));
		} 
		catch (CMSItemNotFoundException e) 
		{
			LOG.warn(e.getMessage());
		}
		return AmazonLoginAddonConstants.Views.Pages.AmazonConfirmAccountPage;
	}
	
	protected static String getFirstNameByName(String name) 
	{
		String fn[] = name.split(" ", 2);
		
		return fn[0];
	}

	protected static String getLastNameByName(String name) 
	{
		String ln[] = name.split(" ", 2);
		try 
		{
			return ln[1];
		} 
		catch (ArrayIndexOutOfBoundsException e) 
		{
			//
		}
		return "(Surname)";
	}

	@Override
	protected AmazonCustomerFacade getCustomerFacade() {
		return (AmazonCustomerFacade) amazonCustomerFacade;
	}

	@Override
	public AmazonUserFacade getUserFacade() {
		return (AmazonUserFacade) amazonUserFacade;
	}
}