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
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.order.payment.PaymentInfo;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.localization.Localization;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import de.fliegersoftware.amazon.core.jalo.AmazonPaymentPaymentInfo;
import de.fliegersoftware.amazon.core.jalo.AmazoncoreManager;
import de.fliegersoftware.amazon.core.jalo.config.AmazonConfig;
import de.fliegersoftware.amazon.core.jalo.media.AmazonLog;
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
		return Collections.emptyList();
	}

	/**
	 * @see HMCExtension#getMenuEntrySlotEntries(de.hybris.platform.hmc.webchips.DisplayState,
	 *      de.hybris.platform.hmc.webchips.Chip)
	 */
	@Override
	public List<MenuEntrySlotEntry> getMenuEntrySlotEntries(final DisplayState displayState, final Chip parent)
	{
		return Collections.emptyList();
	}

	/**
	 * @see HMCExtension#getSectionChips(de.hybris.platform.hmc.webchips.DisplayState,
	 *      de.hybris.platform.hmc.generic.ClipChip)
	 */
	@Override
	public List<ClipChip> getSectionChips(final DisplayState displayState, final ClipChip parent)
	{
		return Collections.emptyList();
	}

	@Override
	public List<EditorTabChip> getEditorTabChips(final DisplayState displayState, final AbstractEditorMenuChip parent)
	{
		return Collections.emptyList();
	}

	/**
	 * Check if is a Amazon Pay, if it is, then it build the amazon tab in payment info view.
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
				final AmazonConfig config = (AmazonConfig) genericChip.getItem();
				createLogMedia(config);
				setIpnURL(config);
			}
			if ("AmazonPaymentPaymentInfo".equals(genericChip.getItemType().getCode()))
			{
				paymentInfo = (AmazonPaymentPaymentInfo) genericChip.getItem();
				try
				{
					if (paymentInfo != null)
					{
						final String orderReferenceId = (String) paymentInfo.getAttribute(AmazonPaymentPaymentInfo.AMAZONORDERREFERENCEID);
						final String localizedString = Localization.getLocalizedString("tab.payment.amazon.properties");
						final AbstractEditorMenuChip editor = genericChip.getEditor();
						amazonTab = editor.getTabByName(localizedString);
						editor.setActiveTab(amazonTab);
						try
						{
							AmazonCredentials.getInstance().populatePaymentInfo(orderReferenceId, paymentInfo);
							HMCAmazonButtonsManager.getInstance((AmazonPaymentPaymentInfo) paymentInfo, amazonTab).rebuildButtons();
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
		return Collections.emptyList();
	}

	private void setIpnURL(final AmazonConfig config) {

		if (config != null) {
			config.setIPN(Config.getParameter("amazon.url.ipn"));
		}

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
					Logger.getLogger("de.fliegersoftware.amazon.payment.ipn").setLevel(Level.INFO);
					Logger.getLogger("de.fliegersoftware.amazon.payment").setLevel(Level.INFO);
					Logger.getLogger("de.fliegersoftware.amazon.payment.addon.controllers.IpnController").setLevel(Level.INFO);
					Logger.getLogger("de.fliegersoftware.amazon").setLevel(Level.INFO);
				}
				else
				{
					Logger.getLogger("de.fliegersoftware.amazon.payment.ipn").setLevel(Level.OFF);
					Logger.getLogger("de.fliegersoftware.amazon.payment").setLevel(Level.OFF);
					Logger.getLogger("de.fliegersoftware.amazon.payment.addon.controllers.IpnController").setLevel(Level.OFF);
					Logger.getLogger("de.fliegersoftware.amazon").setLevel(Level.OFF);
				}
			}
			if (amazonTab != null && amazonTab.isValid() 
			        && (paymentInfo instanceof PaymentInfo && item instanceof PaymentInfo))
			{
				paymentInfo = (PaymentInfo) item;
				final String orderReferenceId = (String) paymentInfo.getAttribute(AmazonPaymentPaymentInfo.AMAZONORDERREFERENCEID);
				AmazonCredentials.getInstance().populatePaymentInfo(orderReferenceId, paymentInfo);
				HMCAmazonButtonsManager.getInstance((AmazonPaymentPaymentInfo) paymentInfo, amazonTab).rebuildButtons();
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
			fillAmazonApitLog(amazonConfig, "/amazonhmc/log/amazonapi.log", "1", "log4j.appender.AMAZONPAYMENT.File");
			fillAmazonIpnLog(amazonConfig, "/amazonhmc/log/amazonipn.log", "2", "log4j.appender.AMAZONIPN.File");
			fillAmazonLog(amazonConfig, "/amazonhmc/log/amazonlog.log", "3", "log4j.appender.AMAZONLOG.File");
		}
		catch (final Exception e)
		{
			LOG.error("Error while trying to create the amazon log media", e);
		}
	}

	private void fillAmazonApitLog(final AmazonConfig config, final String url, final String number, final String file)
	{
		AmazonLog apiLog = config.getApiLog();
		if (apiLog == null)
		{
			final Map<String, Object> attributeValues = new HashMap<>();
			attributeValues.put(AmazonLog.CODE, "amazonApiLog");
			apiLog = AmazoncoreManager.getInstance().createAmazonLog(attributeValues);
		}
		apiLog = buildMedia(apiLog, url, number, file);
		config.setApiLog(apiLog);
	}

	private void fillAmazonIpnLog(final AmazonConfig config, final String url, final String number, final String file)
	{
		AmazonLog ipnLog = config.getIpnLog();
		if (ipnLog == null)
		{
			final Map<String, Object> attributeValues = new HashMap<>();
			attributeValues.put(AmazonLog.CODE, "amazonIpnLog");
			ipnLog = AmazoncoreManager.getInstance().createAmazonLog(attributeValues);
		}
		ipnLog = buildMedia(ipnLog, url, number, file);
		config.setIpnLog(ipnLog);
	}

	private void fillAmazonLog(final AmazonConfig config, final String url, final String number, final String file)
	{
		AmazonLog amazonLog = config.getAmazonLog();
		if (amazonLog == null)
		{
			final Map<String, Object> attributeValues = new HashMap<>();
			attributeValues.put(AmazonLog.CODE, "amazonLog");
			amazonLog = AmazoncoreManager.getInstance().createAmazonLog(attributeValues);
		}
		amazonLog = buildMedia(amazonLog, url, number, file);
		config.setAmazonLog(amazonLog);
	}

	private AmazonLog buildMedia(final AmazonLog media, final String url, final String number, final String file)
	{
		try
		{
			media.setRealFileName(Config.getParameter("amazon.fileName." + number));
			media.setInternalURL(Config.getParameter(file));
			media.setURL(url);
			media.setURL2(url);
			media.setMime("application/octet-stream");
		}
		catch (final JaloBusinessException e)
		{
			LOG.error("Error while setting infos to media object", e);
		}
		return media;
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
