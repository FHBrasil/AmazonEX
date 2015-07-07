/*
herin * [y] hybris Platform
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
package br.hering.storefront.controllers.pages;


import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.i18n.I18NFacade;
import de.hybris.platform.commercefacades.user.data.RegisterData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractLoginPageController;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.security.authentication.DisabledException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.acceleratorstorefrontcommons.forms.LoginForm;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;

import br.hering.core.customer.exceptions.CustomerAuthenticationException;
import br.hering.facades.customer.impl.DefaultHeringCustomerFacade;
import br.hering.heringstorefrontcommons.forms.HeringGuestForm;
import br.hering.storefront.forms.HeringRegisterForm;

import de.hybris.platform.util.Config;

@Controller
public abstract class AbstractHeringLoginController extends AbstractLoginPageController
{
	private static final Logger LOG = Logger.getLogger(AbstractHeringLoginController.class);
	
	
	@Resource(name = "customerFacade")
	private CustomerFacade customerFacade;
	
	@Resource
	protected I18NFacade i18NFacade;
	
	@Resource
	private BaseStoreService baseStoreService;
	
	/* (non-Javadoc)
	 * @see de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractLoginPageController#getDefaultLoginPage(boolean, javax.servlet.http.HttpSession, org.springframework.ui.Model)
	 */
	@Override
	protected String getDefaultLoginPage(boolean loginError, HttpSession session, Model model) throws CMSItemNotFoundException
	{
		final LoginForm loginForm = new LoginForm();
		model.addAttribute(loginForm);
		model.addAttribute(new HeringRegisterForm());
		model.addAttribute(new HeringGuestForm());
		model.addAttribute("basesCode", getBasesCode());
		
		final String username = (String) session.getAttribute(SPRING_SECURITY_LAST_USERNAME);
		if (username != null)
		{
			session.removeAttribute(SPRING_SECURITY_LAST_USERNAME);
		}

		loginForm.setJ_username(username);
		storeCmsPageInModel(model, getCmsPage());
		setUpMetaDataForContentPage(model, (ContentPageModel) getCmsPage());
		model.addAttribute("metaRobots", "index,no-follow");

		final Breadcrumb loginBreadcrumbEntry = new Breadcrumb("#", getMessageSource().getMessage("header.link.login", null,
				getI18nService().getCurrentLocale()), null);
		model.addAttribute("breadcrumbs", Collections.singletonList(loginBreadcrumbEntry));

		if (loginError)
		{
			if(session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") != null && 
					session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") instanceof DisabledException){
				model.addAttribute("loginError", Boolean.valueOf(loginError));
				GlobalMessages.addErrorMessage(model, "login.error.account.disabled");
			}
			else{
				model.addAttribute("loginError", Boolean.valueOf(loginError));
				GlobalMessages.addErrorMessage(model, "login.error.account.not.found.title");
			}
		}

		return getView();
	}	

	/**
	 * @return Retorna os códigos dos Base Store registrados, separados por ",".
	 */
	private String getBasesCode()
	{
		String result = null;
		
		for (BaseStoreModel baseModel : baseStoreService.getAllBaseStores())
		{
			result = (result == null ? baseModel.getUid() : result + "," + baseModel.getUid());
		}
		
		return result;
	}

	/**
	 * Processo de registro de cliente.
	 * @param referer
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @param request
	 * @param response
	 * @param redirectModel
	 * @return Pagina de retorno.
	 * @throws CMSItemNotFoundException
	 */
	protected String processRegisterUserRequest(final String referer, final HeringRegisterForm form, final BindingResult bindingResult,
			final Model model, final HttpServletRequest request, final HttpServletResponse response,
			final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{	
		if (bindingResult.hasErrors())
		{
			model.addAttribute(form);
			model.addAttribute(new LoginForm());
			model.addAttribute(new HeringGuestForm());
			model.addAttribute("regions", i18NFacade.getRegionsForCountryIso("DE"));
			model.addAttribute("pf", Boolean.valueOf(form.getPessoaFisica()));
			model.addAttribute("basesCode", getBasesCode());
			model.addAttribute("basesChecked", form.getBaseStore());
			return handleRegistrationError(model);
		}
		
		final RegisterData data = new RegisterData();
		data.setFirstName(form.getFirstName());
		data.setLastName(form.getLastName());
		String email = form.getEmail();
		data.setLogin(email);
//		data.setGender(form.getGenderType());
		data.setPassword(form.getPwd());
		
//		if (Config.getBoolean("fliegercommerce.feature.enable.cpf", false))
//		{
//			data.setCpfcnpj(form.getCpfcnpj());
//		}
		
//		data.setBirthday(form.getDateBirthday());
//		data.setRgIe(form.getRgIe());
//		data.setUfIe(form.getUfIe());
//		data.setBaseStore(form.getBaseStore());
//		
		try
		{			
			getCustomerFacade().register(data);
			try
			{
				getAutoLoginStrategy().login(email.toLowerCase(), form.getPwd(), request, response);
			}
			catch(CustomerAuthenticationException e)
			{
				LOG.error("Login Error", e);
			}

			
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER,
					"registration.confirmation.message.title");
		}
		catch (final DuplicateUidException e)
		{
			LOG.warn("registration failed: " + e);
			model.addAttribute(form);
			model.addAttribute(new LoginForm());
			model.addAttribute(new HeringGuestForm());
			model.addAttribute("basesCode", getBasesCode());
			model.addAttribute("basesChecked", form.getBaseStore());
			bindingResult.rejectValue("email", "registration.error.account.exists.title");
			GlobalMessages.addErrorMessage(model, "registration.error.account.exists.title");
			return handleRegistrationError(model);
		}
		
		return REDIRECT_PREFIX + getSuccessRedirect(request, response);
		
	}
	
	/**
	 * @param basesStore
	 * @return
	 */
	private List<String> getListBaseStore(String basesStore)
	{
		List<String> bases = new LinkedList<>();
		
		for (String b : basesStore.split(","))
		{
			bases.add(b);
		}
		
		return bases;
	}

	protected String getReturnRedirectUrl(final HttpServletRequest request)
	{
		final String referer = request.getHeader("Referer");
		if (StringUtils.isNotBlank(referer))
		{
			return REDIRECT_PREFIX + referer;
		}
		
		return REDIRECT_PREFIX + '/';
	}
}