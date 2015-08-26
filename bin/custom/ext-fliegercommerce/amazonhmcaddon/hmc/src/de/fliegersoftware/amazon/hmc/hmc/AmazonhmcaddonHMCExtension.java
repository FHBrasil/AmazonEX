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
import de.hybris.platform.hmc.attribute.AttributeChip;
import de.hybris.platform.hmc.extension.HMCExtension;
import de.hybris.platform.hmc.extension.MenuEntrySlotEntry;
import de.hybris.platform.hmc.generic.ClipChip;
import de.hybris.platform.hmc.generic.GenericItemChip;
import de.hybris.platform.hmc.generic.GenericLayoutChip;
import de.hybris.platform.hmc.generic.GenericSectionChip;
import de.hybris.platform.hmc.generic.GenericTabEditorChip;
import de.hybris.platform.hmc.generic.ToolbarActionChip;
import de.hybris.platform.hmc.util.action.ActionResult;
import de.hybris.platform.hmc.webchips.Chip;
import de.hybris.platform.hmc.webchips.DisplayState;
import de.hybris.platform.jalo.flexiblesearch.FlexibleSearch;
import de.hybris.platform.jalo.order.payment.PaymentInfo;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.util.localization.Localization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceClient;
import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceConfig;
import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;
import com.amazonservices.mws.offamazonpayments.model.AuthorizationDetails;
import com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsResponse;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsResponse;
import com.amazonservices.mws.offamazonpayments.model.OrderReferenceDetails;

import de.fliegersoftware.amazon.core.model.AmazonPaymentInfoModel;
import de.fliegersoftware.amazon.hmc.jalo.config.AmazonConfig;


/**
 * Provides necessary meta information about the amazonhmcaddon hmc extension.
 * 
 * 
 * @version ExtGen v5.1.1.7
 */
public class AmazonhmcaddonHMCExtension extends HMCExtension
{
	/** Edit the local|project.properties to change logging behavior (properties log4j.*). */
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(AmazonhmcaddonHMCExtension.class.getName());

	/** Path to the resource bundles. */
	public final static String RESOURCE_PATH = "de.fliegersoftware.amazon.hmc.hmc.locales";

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
	 * @see HMCExtension#getToolbarActionChips(de.hybris.platform.hmc.webchips.DisplayState,
	 *      de.hybris.platform.hmc.webchips.Chip)
	 */
	@Override
	public List<ToolbarActionChip> getToolbarActionChips(final DisplayState displayState, final Chip parent)
	{
		if (parent.getParent() instanceof GenericItemChip)
		{
			final GenericItemChip genericChip = (GenericItemChip) parent.getParent();
			if ("AmazonPaymentInfo".equals(genericChip.getItemType().getCode()))
			{
				final PaymentInfo paymentInfo = (PaymentInfo) genericChip.getItem();
				try
				{
					final String orderReferenceId = (String) paymentInfo.getAttribute(AmazonPaymentInfoModel.AMAZONORDERREFERENCEID);
					final String localizedString = Localization.getLocalizedString("tab.payment.amazon.properties");
					final AbstractEditorMenuChip editor = genericChip.getEditor();
					final EditorTabChip tabByName = editor.getTabByName(localizedString);
					populatePaymentInfo(orderReferenceId, paymentInfo);
					editor.setActiveTab(tabByName);
					editor.setVisible(tabByName, true);

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
				editor.setVisible(tabByName, false);
			}
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @param tabByName
	 * @param orderReferenceId
	 * @param paymentInfo
	 * @throws OffAmazonPaymentsServiceException
	 */
	private void populatePaymentInfo(final String orderReferenceId, final PaymentInfo paymentInfo)
			throws OffAmazonPaymentsServiceException
	{
		final List<AmazonConfig> result = FlexibleSearch.getInstance()
				.search("SELECT {pk} FROM {AmazonConfig}", AmazonConfig.class).getResult();
		if (CollectionUtils.isNotEmpty(result))
		{
			final AmazonConfig next = result.iterator().next();

			final OffAmazonPaymentsServiceClient service = createOffAmazonPaymentService(next);

			final GetOrderReferenceDetailsResponse orderReferenceDetails = service
					.getOrderReferenceDetails(getOrderReferenceDetailsRequest(next, orderReferenceId));

			final OrderReferenceDetails details = orderReferenceDetails.getGetOrderReferenceDetailsResult()
					.getOrderReferenceDetails();

			final GetAuthorizationDetailsResponse authorizationDetails = service
					.getAuthorizationDetails(getAuthorizationDetailsRequest(next, details));

			final AuthorizationDetails authorizationDetails2 = authorizationDetails.getGetAuthorizationDetailsResult()
					.getAuthorizationDetails();

			try
			{
				paymentInfo.setAttribute(AmazonPaymentInfoModel.AMAZONORDERREFERENCEID, details.getAmazonOrderReferenceId());
				paymentInfo.setAttribute(AmazonPaymentInfoModel.AMAZONORDERSTATUS, details.getOrderReferenceStatus().getState());
				paymentInfo.setAttribute(AmazonPaymentInfoModel.AMAZONLASTAUTHORIZATIONID,
						authorizationDetails2.getAmazonAuthorizationId());
				paymentInfo.setAttribute(AmazonPaymentInfoModel.AMAZONAUTHORIZATIONSTATUS, authorizationDetails2
						.getAuthorizationStatus().getState());
			}
			catch (final Exception e)
			{
				LOG.error("error while load the amazon infos", e);
			}

		}
	}


	/**
	 * @param next
	 * @param details
	 * @return
	 */
	private GetAuthorizationDetailsRequest getAuthorizationDetailsRequest(final AmazonConfig next,
			final OrderReferenceDetails details)
	{
		final GetAuthorizationDetailsRequest request = new GetAuthorizationDetailsRequest();
		request.setAmazonAuthorizationId(details.getIdList().getMember().get(0));
		request.setSellerId(next.getMerchantId());
		return request;
	}

	/**
	 * @param next
	 * @param orderReferenceId
	 * @return
	 */
	private GetOrderReferenceDetailsRequest getOrderReferenceDetailsRequest(final AmazonConfig next, final String orderReferenceId)
	{
		final GetOrderReferenceDetailsRequest request = new GetOrderReferenceDetailsRequest();
		request.setAmazonOrderReferenceId(orderReferenceId);
		request.setSellerId(next.getMerchantId());
		return request;
	}

	/**
	 * @param from
	 *           - administration
	 * @param to
	 *           - tabByName
	 */
	private void changeAmazonInformationPlace(final EditorTabChip from, final EditorTabChip to)
	{
		final List<GenericSectionChip> fromSections = ((GenericTabEditorChip) from.getClipChip()).getChildren();
		final List<GenericSectionChip> toSections = ((GenericTabEditorChip) to.getClipChip()).getChildren();

		final List<AttributeChip> amazonAttributes = new ArrayList<>();

		fillAttributeList(fromSections, amazonAttributes);

		setOnAmazonTab(toSections, amazonAttributes);

	}

	/**
	 * @param to
	 * @param amazonAttributes
	 */
	private void setOnAmazonTab(final List<GenericSectionChip> sections, final List<AttributeChip> amazonAttributes)
	{
		for (final GenericSectionChip section : sections)
		{
			final List<GenericLayoutChip> layouts = section.getChildren();
			for (final GenericLayoutChip layout : layouts)
			{
				for (final AttributeChip attribute : amazonAttributes)
				{
					layout.addChild(attribute);
				}
			}
		}
	}

	private void fillAttributeList(final List<GenericSectionChip> sections, final List<AttributeChip> amazonAttributes)
	{
		for (final GenericSectionChip section : sections)
		{
			final List<GenericLayoutChip> layouts = section.getChildren();
			for (final GenericLayoutChip layout : layouts)
			{
				final List<AttributeChip> attributes = layout.getChildren();
				for (final AttributeChip attribute : attributes)
				{
					if ("amazonOrderReferenceId".equals(attribute.getAttributeQualifier())
							|| "amazonLastAuthorizationId".equals(attribute.getAttributeQualifier())
							|| "amazonOrderReferenceId".equals(attribute.getAttributeQualifier()))
					{
						amazonAttributes.add(attribute);
					}
				}
			}
		}
	}

	/**
	 * @param amazonConfig
	 */
	private OffAmazonPaymentsServiceClient createOffAmazonPaymentService(final AmazonConfig amazonConfig)
	{
		final Properties properties = new Properties();
		properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.ACCESS_KEY_ID, amazonConfig.getMwsAccessKey());
		properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.SECRET_ACCESS_KEY, amazonConfig.getMwsSecretKey());
		properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.APPLICATION_VERSION, "");
		properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.SELLER_ID, amazonConfig.getMerchantId());
		properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.REGION, amazonConfig.getAmazonConfigCountry().getCode());
		properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.CURRENCY, "EUR");
		properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.ENVIRONMENT, "");
		properties.put("clientId", amazonConfig.getClientId());

		if (amazonConfig.isSandboxMode() != null && amazonConfig.isSandboxMode().booleanValue())
		{
			properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.APPLICATION_NAME, "KPFamily Sandbox");
			properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.ENVIRONMENT, "SANDBOX");
			properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.PLACE_ORDER_URL, "http://localhost:9001/");
		}
		else
		{
			properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.ENVIRONMENT, "LIVE");
			properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.PLACE_ORDER_URL, "http://www.babyartikel.de/");
		}
		properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.CERT_CN, "sns.amazonaws.com");

		final OffAmazonPaymentsServiceConfig config = new OffAmazonPaymentsServiceConfig(properties);
		return new OffAmazonPaymentsServiceClient(config);
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
