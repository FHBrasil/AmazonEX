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
package br.hering.core.event;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.event.AbstractSiteEventListener;
import de.hybris.platform.commerceservices.event.ForgottenPwdEvent;
import de.hybris.platform.commerceservices.model.process.ForgottenPasswordProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.store.services.BaseStoreService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import br.flieger.exacttarget.events.ForgotPasswordEvent;
import br.hering.core.util.MccSiteUrlHelper;


/**
 * Listener for "forgotten password" functionality event.
 */
public class ForgottenPasswordEventListener extends AbstractSiteEventListener<ForgottenPwdEvent>
{

	private ModelService modelService;
	private BusinessProcessService businessProcessService;
	@Resource
	private MccSiteUrlHelper mccSiteUrlHelper;
	
	@Resource
	private EventService eventService;
	
	@Resource
	private CMSSiteService cmsSiteService;
	
	private final Logger LOG = Logger.getLogger(ForgottenPasswordEventListener.class);


	protected BusinessProcessService getBusinessProcessService()
	{
		return businessProcessService;
	}

	@Required
	public void setBusinessProcessService(final BusinessProcessService businessProcessService)
	{
		this.businessProcessService = businessProcessService;
	}

	/**
	 * @return the modelService
	 */
	protected ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	@Override
	protected void onSiteEvent(final ForgottenPwdEvent event)
	{
		String uid = event.getCustomer().getUid();
		final ForgottenPasswordProcessModel model = (ForgottenPasswordProcessModel) getBusinessProcessService() .createProcess("forgottenPassword-" + uid + "-" + System.currentTimeMillis(), "forgottenPasswordEmailProcess");
		model.setSite(event.getSite());
		model.setCustomer(event.getCustomer());
		model.setToken(event.getToken());
		model.setLanguage(event.getLanguage());
		model.setCurrency(event.getCurrency());
		model.setStore(event.getBaseStore());
		getModelService().save(model);

		try
		{
			final String link = buildLink(model.getToken());
			eventService.publishEvent(new ForgotPasswordEvent(uid, event.getCustomer().getName(),link, event.getBaseStore().getUid()));
		}
		catch (UnsupportedEncodingException e)
		{
			LOG.error(e);
		}
		
		
	}

	/**
	 * @param token
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private String buildLink(final String token) throws UnsupportedEncodingException
	{
		final String name = cmsSiteService.getCurrentSite().getName();
		final String siteURL = mccSiteUrlHelper.getSitesAndUrls().get(name);
		final String encodedToken = URLEncoder.encode(token, "UTF-8");
		
		return "<a href=\"" + siteURL + "login/pw/change?token=" + encodedToken + "\" target=\"	_blank\" style=\"color:#000\" class=\"token\">";
	}

	@Override
	protected boolean shouldHandleEvent(final ForgottenPwdEvent event)
	{
		final BaseSiteModel site = event.getSite();
		ServicesUtil.validateParameterNotNullStandardMessage("event.site", site);
		return SiteChannel.B2C.equals(site.getChannel());
	}
}
