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
package de.fliegersoftware.amazon.hmc.hmc;

import de.hybris.platform.hmc.AbstractEditorMenuChip;
import de.hybris.platform.hmc.AbstractExplorerMenuTreeNodeChip;
import de.hybris.platform.hmc.EditorTabChip;
import de.hybris.platform.hmc.extension.HMCExtension;
import de.hybris.platform.hmc.extension.MenuEntrySlotEntry;
import de.hybris.platform.hmc.generic.ClipChip;
import de.hybris.platform.hmc.generic.GenericItemChip;
import de.hybris.platform.hmc.generic.ToolbarActionChip;
import de.hybris.platform.hmc.util.action.ActionResult;
import de.hybris.platform.hmc.webchips.Chip;
import de.hybris.platform.hmc.webchips.DisplayState;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.media.Media;
import de.hybris.platform.jalo.media.MediaManager;
import de.hybris.platform.jalo.order.payment.PaymentInfo;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.localization.Localization;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import de.fliegersoftware.amazon.core.jalo.AmazonPaymentInfo;
import de.fliegersoftware.amazon.core.jalo.config.AmazonConfig;
import de.fliegersoftware.amazon.hmc.credentials.AmazonCredentials;
import de.fliegersoftware.amazon.hmc.hmc.util.HMCAmazonButtonsManager;


/**
 * Provides necessary meta information about the amazonhmcaddon hmc extension.
 * 
 * 
 * @version ExtGen v5.1.1.7
 * @author douglas.canalli
 */
public class AmazonhmcaddonHMCExtension extends HMCExtension
{
	/** Edit the local|project.properties to change logging behavior (properties log4j.*). */
	private static final Logger LOG = Logger.getLogger(AmazonhmcaddonHMCExtension.class.getName());

	/** Path to the resource bundles. */
	public final static String RESOURCE_PATH = "de.fliegersoftware.amazon.hmc.hmc.locales";

	private EditorTabChip amazonTab;

	private PaymentInfo paymentInfo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.hmc.extension.HMCExtension#beforeCreate(de.hybris.platform.jalo.type.ComposedType,
	 * de.hybris.platform.hmc.webchips.DisplayState, java.util.Map)
	 */
	@Override
	public ActionResult beforeCreate(final ComposedType itemType, final DisplayState displayState, final Map initialValues)
	{
		return super.beforeCreate(itemType, displayState, initialValues);
	}

	/**
	 * @see HMCExtension#getTreeNodeChips(de.hybris.platform.hmc.webchips.DisplayState,
	 *      de.hybris.platform.hmc.webchips.Chip)
	 */
	@Override
	public List<AbstractExplorerMenuTreeNodeChip> getTreeNodeChips(final DisplayState displayState, final Chip parent)
	{
		return Collections.EMPTY_LIST;
	}

	/**
	 * @see HMCExtension#getMenuEntrySlotEntries(de.hybris.platform.hmc.webchips.DisplayState,
	 *      de.hybris.platform.hmc.webchips.Chip)
	 */
	@Override
	public List<MenuEntrySlotEntry> getMenuEntrySlotEntries(final DisplayState displayState, final Chip parent)
	{
		return Collections.EMPTY_LIST;
	}

	/**
	 * @see HMCExtension#getSectionChips(de.hybris.platform.hmc.webchips.DisplayState,
	 *      de.hybris.platform.hmc.generic.ClipChip)
	 */
	@Override
	public List<ClipChip> getSectionChips(final DisplayState displayState, final ClipChip parent)
	{
		return Collections.EMPTY_LIST;
	}

	@Override
	public List<EditorTabChip> getEditorTabChips(final DisplayState displayState, final AbstractEditorMenuChip parent)
	{
		return Collections.EMPTY_LIST;
	}

	/**
	 * Check if is a amazon payment, if it is, then it build the amazon tab in payment info view.
	 * 
	 * @see HMCExtension#getToolbarActionChips(de.hybris.platform.hmc.webchips.DisplayState,
	 *      de.hybris.platform.hmc.webchips.Chip)
	 */
	@Override
	public List<ToolbarActionChip> getToolbarActionChips(final DisplayState displayState, final Chip parent)
	{
		if (parent.getParent() instanceof GenericItemChip)
		{
			final GenericItemChip genericChip = (GenericItemChip) parent.getParent();
			if ("AmazonConfig".equals(genericChip.getItemType().getCode()))
			{
				createLogMedia((AmazonConfig) genericChip.getItem());
			}
			if ("AmazonPaymentInfo".equals(genericChip.getItemType().getCode()))
			{
				paymentInfo = (AmazonPaymentInfo) genericChip.getItem();
				try
				{
					if (paymentInfo != null)
					{
						final String orderReferenceId = (String) paymentInfo.getAttribute(AmazonPaymentInfo.AMAZONORDERREFERENCEID);
						final String localizedString = Localization.getLocalizedString("tab.payment.amazon.properties");
						final AbstractEditorMenuChip editor = genericChip.getEditor();
						amazonTab = editor.getTabByName(localizedString);
						editor.setActiveTab(amazonTab);
						try
						{
							AmazonCredentials.getInstance().populatePaymentInfo(orderReferenceId, paymentInfo);
							HMCAmazonButtonsManager.getInstance((AmazonPaymentInfo) paymentInfo, amazonTab).rebuildButtons();
						}
						catch (final Exception e)
						{
							LOG.error("Please check the Amazon credentials!", e);
							amazonTab.getDisplayState().addErrorMessage(
									"We couldn't retrieve the order infos, please check the Amazon credentials!");
						}
						editor.setVisible(amazonTab, true);
					}
				}
				catch (final Exception e)
				{
					LOG.error("tab can not be activated", e);
				}

			}
			else if ("PaymentInfo".equals(genericChip.getItemType().getSuperType().getCode()))
			{
				final String localizedString = Localization.getLocalizedString("tab.payment.amazon.properties");
				final AbstractEditorMenuChip editor = genericChip.getEditor();
				final EditorTabChip tabByName = editor.getTabByName(localizedString);
				if (tabByName != null)
				{
					editor.setVisible(tabByName, false);
				}
			}
		}
		return Collections.EMPTY_LIST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.hmc.extension.HMCExtension#afterSave(de.hybris.platform.jalo.Item,
	 * de.hybris.platform.hmc.webchips.DisplayState, java.util.Map, de.hybris.platform.hmc.util.action.ActionResult)
	 */
	@Override
	public ActionResult afterSave(final Item item, final DisplayState displayState, final Map currentValues,
			final ActionResult actionResult)
	{
		try
		{
			if (item instanceof AmazonConfig)
			{
				final AmazonConfig config = (AmazonConfig) item;
				if (Boolean.valueOf(config.isEnableLog()))
				{
					Logger.getLogger("de.fliegersoftware.amazon").setLevel(Level.INFO);
				}
				else
				{
					Logger.getLogger("de.fliegersoftware.amazon").setLevel(Level.OFF);
				}
			}
			if (amazonTab != null && amazonTab.isValid())
			{
				paymentInfo = (PaymentInfo) item;
				final String orderReferenceId = (String) paymentInfo.getAttribute(AmazonPaymentInfo.AMAZONORDERREFERENCEID);
				AmazonCredentials.getInstance().populatePaymentInfo(orderReferenceId, paymentInfo);
				HMCAmazonButtonsManager.getInstance((AmazonPaymentInfo) paymentInfo, amazonTab).rebuildButtons();
			}
		}
		catch (final Exception e)
		{
			LOG.error("error while populate infos", e);
		}
		return actionResult;
	}

	private void createLogMedia(final AmazonConfig amazonConfig)
	{
		mediaUpdate: try
		{
			if (amazonConfig == null)
			{
				break mediaUpdate;
			}
			Media media = amazonConfig.getLog();
			if (media == null)
			{
				media = MediaManager.getInstance().createMedia("logAmazon");
			}
			media.setRealFileName(Config.getParameter("amazon.fileName"));
			//			media.setFile(new File(Config.getParameter("log4j.appender.AMAZON.File")));
			media.setInternalURL(Config.getParameter("log4j.appender.AMAZON.File"));
			media.setURL("/amazonhmc/log/amazon");
			media.setURL2("/amazonhmc/log/amazon");
			media.setMime("text/plain");
			amazonConfig.setLog(media);
		}
		catch (final Exception e)
		{
			LOG.error("Error while trying to create the amazon log media", e);
		}
	}

	@Override
	public ResourceBundle getLocalizeResourceBundle(final Locale locale)
	{
		return null;
	}

	@Override
	public String getResourcePath()
	{
		return RESOURCE_PATH;
	}
}
