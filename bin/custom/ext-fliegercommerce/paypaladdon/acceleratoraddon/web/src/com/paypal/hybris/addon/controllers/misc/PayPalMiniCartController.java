/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.paypal.hybris.addon.controllers.misc;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.AbstractController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.servicelayer.services.CMSComponentService;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paypal.hybris.addon.controllers.PaypaladdonControllerConstants;
import com.paypal.hybris.addon.model.PayPalMiniCartComponentModel;


/**
 * Controller for MiniCart functionality which is not specific to a page.
 *
 * @author Dzmitry_Rasolka (EPAM Systems)
 */
@Controller
@Scope("tenant")
public class PayPalMiniCartController extends AbstractController
{
	protected static final Logger LOG = Logger.getLogger(PayPalMiniCartController.class);
	/**
	 * We use this suffix pattern because of an issue with Spring 3.1 where a Uri value is incorrectly extracted if it
	 * contains on or more '.' characters. Please see https://jira.springsource.org/browse/SPR-6164 for a discussion on
	 * the issue and future resolution.
	 */
	private static final String COMPONENT_UID_PATH_VARIABLE_PATTERN = "{componentUid:.*}";

	@Resource(name = "cartFacade")
	private CartFacade cartFacade;

	@Resource(name = "cmsComponentService")
	private CMSComponentService cmsComponentService;

	/**
	 * Controller that handle minicart popup with embedded PayPal buttons
	 *
	 * @param componentUid
	 * @param model
	 * @return
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(value = "/cartPP/rollover/" + COMPONENT_UID_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	public String rolloverMiniCartPopup(@PathVariable final String componentUid, final Model model)
			throws CMSItemNotFoundException
	{
		final CartData cartData = cartFacade.getSessionCart();
		model.addAttribute("cartData", cartData);

		final PayPalMiniCartComponentModel component = (PayPalMiniCartComponentModel) cmsComponentService
				.getSimpleCMSComponent(componentUid);

		final List entries = cartData.getEntries();
		if (entries != null)
		{
			Collections.reverse(entries);
			model.addAttribute("entries", entries);

			model.addAttribute("numberItemsInCart", Integer.valueOf(entries.size()));
			if (entries.size() < component.getShownProductCount())
			{
				model.addAttribute("numberShowing", Integer.valueOf(entries.size()));
			}
			else
			{
				model.addAttribute("numberShowing", Integer.valueOf(component.getShownProductCount()));
			}
		}
		model.addAttribute("lightboxBannerComponent", component.getLightboxBannerComponent());
		model.addAttribute("payPalExpressCheckoutShortcut", component.getPayPalExpressCheckoutShortcut());
		model.addAttribute("payPalCreditShortcut", component.getPayPalCreditShortcut());

		return PaypaladdonControllerConstants.Views.Fragments.Cart.CartPopup;
	}
}
