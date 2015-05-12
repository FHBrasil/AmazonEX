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
package com.hybris.addon.cockpits.components.liveedit;

import de.hybris.platform.acceleratorservices.enums.UiExperienceLevel;
import de.hybris.platform.cms2.model.preview.PreviewDataModel;
import de.hybris.platform.cmscockpit.components.liveedit.LiveEditPopupEditDialog;
import de.hybris.platform.cmscockpit.components.liveedit.impl.DefaultLiveEditViewModel;
import de.hybris.platform.cmscockpit.events.impl.CmsUrlChangeEvent;
import de.hybris.platform.cmscockpit.session.impl.LiveEditBrowserArea;
import de.hybris.platform.cockpit.components.notifier.Notification;
import de.hybris.platform.cockpit.session.UIBrowserArea;
import de.hybris.platform.cockpit.session.UICockpitPerspective;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.zkoss.spring.SpringUtil;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;

import br.hering.cockpits.cmscockpit.session.impl.DefaultLiveEditBrowserArea;
import br.hering.cockpits.components.liveedit.DefaultLiveEditPopupEditDialog;
import br.hering.cockpits.components.liveedit.DefaultLiveEditView;


/**
 * @author rmcotton
 * 
 */
public class AddOnLiveEditView<M extends DefaultLiveEditViewModel, D extends DefaultLiveEditPopupEditDialog> extends
		DefaultLiveEditView
{
	private static final Logger LOG = Logger.getLogger(AddOnLiveEditView.class);

	/**
	 * @param model
	 */
	public AddOnLiveEditView(final M model)
	{
		super(model);
	}

	public AddOnLiveEditView(final M model, final Div welcomePanel)
	{
		super(model, welcomePanel);
	}

	@Override
	protected void refreshContentFrame()
	{
		getContentFrame().setVisible(getModel().isContentVisible());
		if (getModel().isContentVisible())
		{
			final String generatedUrl = getModel().computeFinalUrl();
			if (getModel().getSite() != null && StringUtils.isBlank(getModel().getSite().getPreviewURL())
					|| StringUtils.isBlank(generatedUrl))
			{
				try
				{
					Messagebox.show(Labels.getLabel("site_url_empty"), Labels.getLabel("general.warning"), Messagebox.OK,
							Messagebox.EXCLAMATION);
				}
				catch (final InterruptedException e)
				{
					if (LOG.isDebugEnabled())
					{
						LOG.debug("Errors occured while showing message box!", e);
					}
				}
			}
			else
			{
				getContentFrame().setSrc(generatedUrl);

				RefreshContentHandlerRegistry refreshContentHandlerRegistry = (RefreshContentHandlerRegistry) SpringUtil.getBean(
						"liveEditRefreshContentHandlerRegistry", RefreshContentHandlerRegistry.class);

				List<RefreshContentHandler<AddOnLiveEditView>> refreshHandlers = refreshContentHandlerRegistry
						.getRefreshContentHandlers();
				for (RefreshContentHandler handler : refreshHandlers)
				{
					handler.onRefresh(AddOnLiveEditView.this);
				}

				Events.echoEvent(ON_INVALIDATE_LATER_EVENT, getContentFrame(), null);
				if (LOG.isDebugEnabled())
				{
					LOG.debug("Current url : " + getModel().getCurrentUrl());
				}
			}
		}
	}

	public static class DesktopRefreshContentHandler implements RefreshContentHandler<AddOnLiveEditView>
	{
		@Override
		public void onRefresh(final AddOnLiveEditView view)
		{
			if (view.getModel() != null)
			{
				final PreviewDataModel previewDataModel = view.getModel().getCurrentPreviewData();
				if (previewDataModel != null && previewDataModel.getUiExperience() != null)
				{
					if (UiExperienceLevel.DESKTOP.getCode().equalsIgnoreCase(previewDataModel.getUiExperience().getCode()))
					{
						view.getContentFrame().setWidth("100%");
						view.getContentFrame().setHeight("100%");
						HtmlBasedComponent parent = (HtmlBasedComponent) view.getContentFrame().getParent();
						parent.setSclass("liveEditWrapper liveEditBrowser-desktop");
					}
				}
			}
		}
	}

	public static class MobileRefreshContentHandler implements RefreshContentHandler<AddOnLiveEditView>
	{
		@Override
		public void onRefresh(final AddOnLiveEditView view)
		{
			if (view.getModel() != null)
			{
				final PreviewDataModel previewDataModel = view.getModel().getCurrentPreviewData();
				if (previewDataModel != null && previewDataModel.getUiExperience() != null)
				{
					if (UiExperienceLevel.MOBILE.getCode().equalsIgnoreCase(previewDataModel.getUiExperience().getCode()))
					{
						view.getContentFrame().setWidth("320px%");
						view.getContentFrame().setHeight("100%");
						HtmlBasedComponent parent = (HtmlBasedComponent) view.getContentFrame().getParent();
						parent.setSclass("liveEditWrapper liveEditBrowser-mobile");
					}
				}
			}
		}
	}


	@Override
	protected EventListener getUserEventListener()
	{
		return new EventListener()
		{
			@Override
			public void onEvent(final Event event) throws Exception //NOPMD:ZK specific
			{
				//early exit when prerequisites aren't matched
				final String passedAttributes[];
				if (!(event.getData() instanceof String[]))
				{
					passedAttributes = event.getData() == null ? null : new String[]
					{ String.valueOf(event.getData()) };
				}
				else
				{
					passedAttributes = (String[]) event.getData();
				}


				final CallbackEventHandlerRegistry eventHandlers = (CallbackEventHandlerRegistry) SpringUtil.getBean(
						"liveEditCallbackEventHandlerRegistry", CallbackEventHandlerRegistry.class);

				final CallbackEventHandler<AddOnLiveEditView> handler = eventHandlers.getHandlerById(passedAttributes[0]);
				if (handler == null)
				{
					throw new IllegalStateException("unexepected event type [" + passedAttributes[0]
							+ "]. Please ensure a CallbackEventHandler has been configured");
				}

				if (handler instanceof LockAwareEventHandler)
				{
					((LockAwareEventHandler) handler).onLockCallbackEvent(AddOnLiveEditView.this, passedAttributes);
				}
				else
				{
					handler.onCallbackEvent(AddOnLiveEditView.this, passedAttributes);
				}
			}
		};

	}

	public void onSimpleEditCallbackEvent(final String[] attributes) throws InterruptedException
	{
		final LiveEditPopupEditDialog popupEditorDialog = createLiveEditPopupDialog(attributes);
		getViewComponent().appendChild(popupEditorDialog);
	}

	public static class Click2EditCallbackEventHandler implements CallbackEventHandler<AddOnLiveEditView>
	{
		/*
		 * (non-Javadoc)
		 * 
		 * @see com.hybris.addon.cockpits.components.liveedit.CallbackEventHandler#getEventId()
		 */
		@Override
		public String getEventId()
		{
			return CALLBACK_EVENT;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.hybris.addon.cockpits.components.liveedit.CallbackEventHandler#onCallbackEvent(de.hybris.platform.cmscockpit
		 * .components.liveedit.LiveEditView, java.lang.String[])
		 */
		@Override
		public void onCallbackEvent(final AddOnLiveEditView view, final String[] passedAttributes) throws InterruptedException
		{
			view.onCallbackEvent(passedAttributes);
		}

	}

	public static class UrlChangeCallbackEventHandler implements CallbackEventHandler<AddOnLiveEditView>
	{

		private Converter<String[], CmsUrlChangeEvent> urlChangeEventConverter;

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.hybris.addon.cockpits.components.liveedit.CallbackEventHandler#getEventId()
		 */
		@Override
		public String getEventId()
		{
			return URL_CHANGE_EVENT;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.hybris.addon.cockpits.components.liveedit.CallbackEventHandler#onCallbackEvent(de.hybris.platform.cmscockpit
		 * .components.liveedit.LiveEditView, java.lang.String[])
		 */
		@Override
		public void onCallbackEvent(final AddOnLiveEditView view, final String[] passedAttributes) throws Exception
		{
			view.getContentFrame().setVisible(true);
			final UICockpitPerspective currentPerspective = UISessionUtils.getCurrentSession().getCurrentPerspective();
			if (!view.getModel().isPreviewDataValid())
			{
				final Notification notification = new Notification(Labels.getLabel("cmscockpit.liveditsession.expired"),
						Labels.getLabel("cmscockpit.liveditsession.expired.description"));
				currentPerspective.getNotifier().setNotification(notification);

				final UIBrowserArea currentBrowserArea = currentPerspective.getBrowserArea();
				if (currentBrowserArea instanceof DefaultLiveEditBrowserArea)
				{
					final LiveEditBrowserArea liveEditBrowserArea = ((LiveEditBrowserArea) currentBrowserArea);
					liveEditBrowserArea.fireModeChange(false);
				}
			}

			final CmsUrlChangeEvent cmsUrlChangeEvent = getUrlChangeEventConverter().convert(passedAttributes);
			UISessionUtils.getCurrentSession().sendGlobalEvent(cmsUrlChangeEvent);
		}

		public Converter<String[], CmsUrlChangeEvent> getUrlChangeEventConverter()
		{
			return urlChangeEventConverter;
		}

		@Required
		public void setUrlChangeEventConverter(final Converter<String[], CmsUrlChangeEvent> urlChangeEventConverter)
		{
			this.urlChangeEventConverter = urlChangeEventConverter;
		}


	}

}
