/**
 * 
 */
package de.fliegersoftware.amazon.hmc.hmc.util;

import de.hybris.platform.hmc.EditorTabChip;
import de.hybris.platform.hmc.InlineShortcutChip;
import de.hybris.platform.hmc.attribute.AttributeChip;
import de.hybris.platform.hmc.generic.ClipChip;
import de.hybris.platform.hmc.generic.GenericItemChip;
import de.hybris.platform.hmc.generic.GenericSectionChip;
import de.hybris.platform.hmc.generic.ItemActionChip;
import de.hybris.platform.hmc.webchips.AbstractChip;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.util.localization.Localization;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import de.fliegersoftware.amazon.core.jalo.AmazonPaymentPaymentInfo;
import de.fliegersoftware.amazon.core.jalo.config.AmazonConfig;
import de.fliegersoftware.amazon.hmc.AmazonSandboxSimulationChip;
import de.fliegersoftware.amazon.hmc.credentials.AmazonCredentials;


/**
 * Class that will manage all the buttons of the 'amazon' tab from AmazonPaymentPaymentInfo It's resolves which buttons will
 * show or hide, and will be responsible too for some validation, as the refund count, etc.
 * 
 * @author douglas.canalli
 */
public class HMCAmazonButtonsManager
{
	private static final Logger LOG = Logger.getLogger(HMCAmazonButtonsManager.class.getName());

	private static final String HMCAMAZONBUTTONSMANAGERSESSION = "HMCAmazonButtonsManager";

	private AmazonPaymentPaymentInfo paymentInfo;

	private EditorTabChip amazonTab;

	/**
	 * private constructor for the singleton HMCAmazonButtonsManager
	 */
	private HMCAmazonButtonsManager(final AmazonPaymentPaymentInfo paymentInfo, final EditorTabChip amazonTab)
	{
		this.paymentInfo = paymentInfo;
		this.amazonTab = amazonTab;
	}

	/**
	 * return instance of HMCAmazonButtonsManager
	 * 
	 * @param paymentInfo
	 * @param amazonTab
	 * @return HMCAmazonButtonsManager
	 */
	public static HMCAmazonButtonsManager getInstance(final AmazonPaymentPaymentInfo paymentInfo, final EditorTabChip amazonTab)
	{
		HMCAmazonButtonsManager manager = (HMCAmazonButtonsManager) JaloSession.getCurrentSession().getAttribute(
				HMCAMAZONBUTTONSMANAGERSESSION);
		if (manager == null)
		{
			manager = new HMCAmazonButtonsManager(paymentInfo, amazonTab);
			JaloSession.getCurrentSession().setAttribute(HMCAMAZONBUTTONSMANAGERSESSION, manager);
		}
		else
		{
			manager.paymentInfo = paymentInfo;
			manager.amazonTab = amazonTab;
		}
		return manager;
	}

	/**
	 * return instance of HMCAmazonButtonsManager
	 * 
	 * @param paymentInfo
	 * @param genericItemChip
	 * @return HMCAmazonButtonsManager
	 */
	public static HMCAmazonButtonsManager getInstance(final AmazonPaymentPaymentInfo paymentInfo, final GenericItemChip genericItemChip)
	{
		final String localizedString = Localization.getLocalizedString("tab.payment.amazon.properties");
		final EditorTabChip amazonTab = genericItemChip.getEditor().getTabByName(localizedString);
		return getInstance(paymentInfo, amazonTab);
	}

	/**
	 * update and check if buttons can be available
	 */
	public void rebuildButtons()
	{
		final List<ItemActionChip> buttons = getButtons(amazonTab);
		final Map<String, List<ItemActionChip>> actions = createActionMapping(buttons);
		for (final String sectionName : actions.keySet())
		{
			manageButton(sectionName, actions.get(sectionName));
		}
	}

	/**
	 * Delegate the button reference from each section to their specific manager
	 * 
	 * @param sectionName
	 * @param itemActionChip
	 */
	private void manageButton(final String sectionName, final List<ItemActionChip> itemActionChip)
	{
		if (paymentInfo == null)
		{
			return;
		}
		switch (sectionName)
		{
			case "section.payment.amazon.order":
				manageOrderButtons(itemActionChip);
				break;
			case "section.payment.amazon.authorization":
				manageAuthorizationButtons(itemActionChip);
				break;
			case "section.payment.amazon.capture":
				manageCaptureButtons(itemActionChip);
				break;
			case "section.payment.amazon.refund":
				manageRefundButtons(itemActionChip);
				break;
		}
	}

	/**
	 * Refund buttons manager, validate if the button must be available or not
	 * 
	 * @param refundButtons
	 */
	private void manageRefundButtons(final List<ItemActionChip> refundButtons)
	{
		for (final ItemActionChip refundButton : refundButtons)
		{
			if (refundButton.getName().contains("simulate") && refundButton.getName().contains("refund"))
			{
				checkButtonAvailability(refundButton, simulationButtonRules(refundButton));
			}
			else if (refundButton.getName().contains("refund"))
			{
				checkButtonAvailability(refundButton, refundRefundButtonRules(refundButton));
			}
		}
	}

	/**
	 * @param simulationButton
	 * @return simulation availability
	 */
	private boolean simulationButtonRules(final ItemActionChip simulationButton)
	{
		final AmazonConfig amazonConfig = AmazonCredentials.getInstance().getAmazonConfig();
		return amazonConfig.isSandboxMode() && amazonConfig.isSandboxSimulate();
	}

	/**
	 * @param captureButtons
	 */
	private void manageCaptureButtons(final List<ItemActionChip> captureButtons)
	{
		for (final ItemActionChip captureButton : captureButtons)
		{
			if (captureButton.getName().contains("simulate") && captureButton.getName().contains("capture"))
			{
				checkButtonAvailability(captureButton, simulationButtonRules(captureButton));
			}
			else if (captureButton.getName().contains("capture"))
			{
				checkButtonAvailability(captureButton, captureCaptureButtonRules(captureButton));
			}
		}
	}

	/**
	 * Authorization buttons manager, validate if the button must be available or not
	 * 
	 * @param authorizationButtons
	 */
	private void manageAuthorizationButtons(final List<ItemActionChip> authorizationButtons)
	{
		for (final ItemActionChip authorizationButton : authorizationButtons)
		{
			if (authorizationButton.getName().contains("simulate") && authorizationButton.getName().contains("authorize"))
			{
				checkButtonAvailability(authorizationButton, simulationButtonRules(authorizationButton));
			}
			else
			{
				if (authorizationButton.getName().contains("close"))
				{
					checkButtonAvailability(authorizationButton, authorizationCloseButtonRules(authorizationButton));
				}
				if (authorizationButton.getName().contains("authorize"))
				{
					checkButtonAvailability(authorizationButton, authorizationAuthorizeButtonRules(authorizationButton));
				}
			}
		}
	}

	/**
	 * Order buttons manager, validate if the button must be available or not
	 * 
	 * @param orderButtons
	 */
	private void manageOrderButtons(final List<ItemActionChip> orderButtons)
	{
		for (final ItemActionChip orderButton : orderButtons)
		{
			if (orderButton.getName().contains("simulate") && orderButton.getName().contains("order"))
			{
				checkButtonAvailability(orderButton, simulationButtonRules(orderButton));
			}
			else
			{
				if (orderButton.getName().contains("cancel"))
				{
					checkButtonAvailability(orderButton, orderCancelButtonRules(orderButton));
				}
				if (orderButton.getName().contains("close"))
				{
					checkButtonAvailability(orderButton, orderCloseButtonRules(orderButton));
				}
			}
		}
	}

	/**
	 * show or hide the button (first parameter) based in the second parameter
	 * 
	 * @param button
	 * @param show
	 */
	private void checkButtonAvailability(final ItemActionChip button, final boolean show)
	{
		button.setHideButton(!show);
	}

	/**
	 * Order must has Open or Suspended status. You cannot request new authorizations when the order reference is in the
	 * Closed state. Captures on existing authorizations are still allowed. Refunds on captures are also still allowed.
	 * 
	 * @param orderButton
	 */
	private boolean orderCloseButtonRules(final ItemActionChip orderButton)
	{
		return equalsIgnoreCaseAny(paymentInfo.getAmazonOrderStatus(), "Open", "Suspended");
	}

	/**
	 * Capture must not has the Pending, Completed or Closed status, Order must has Open or Suspended status. You can
	 * cancel an order only if no money has been captured or charged on the order reference. Any pending authorizations
	 * will be canceled and no new payment operations will be allowed in the future.
	 * 
	 * @param orderButton
	 */
	private boolean orderCancelButtonRules(final ItemActionChip orderButton)
	{
		final boolean rightOrderStatus = equalsIgnoreCaseAny(paymentInfo.getAmazonOrderStatus(), "Open", "Suspended");
		final boolean rightCaptureStatus = !equalsIgnoreCaseAny(paymentInfo.getAmazonCaptureStatus(), "Pending", "Completed",
				"Closed");
		return rightOrderStatus && rightCaptureStatus;
	}

	/**
	 * Reserves a specified amount against the payment methods stored in the order reference.
	 * 
	 * @param authorizationButton
	 */
	private boolean authorizationAuthorizeButtonRules(final ItemActionChip authorizationButton)
	{
		return equalsIgnoreCaseAny(paymentInfo.getAmazonOrderStatus(), "Open");
	}

	/**
	 * The authorization can be closed by calling the CloseAuthorization operation, by calling the CancelOrderReference
	 * operation, or it can be closed by Amazon.
	 * 
	 * You cannot request captures against an authorization that is in the Closed state.
	 * 
	 * In the event of a partial capture, the remaining amount will be credited back to the order reference.
	 * 
	 * @param authorizationButton
	 */
	private boolean authorizationCloseButtonRules(final ItemActionChip authorizationButton)
	{
		return equalsIgnoreCaseAny(paymentInfo.getAmazonAuthorizationStatus(), "Pending", "Open");
	}

	/**
	 * Captures funds from an authorized payment instrument.
	 * 
	 * @param captureButton
	 */
	private boolean captureCaptureButtonRules(final ItemActionChip captureButton)
	{
		return equalsIgnoreCaseAny(paymentInfo.getAmazonAuthorizationStatus(), "Open");
	}

	/**
	 * Refunds a previously captured amount.
	 * 
	 * @param refundButton
	 */
	private boolean refundRefundButtonRules(final ItemActionChip refundButton)
	{
		boolean availableToRefund = equalsIgnoreCaseAny(paymentInfo.getAmazonCaptureStatus(), "Completed");

		refundRules: if (CollectionUtils.isNotEmpty(paymentInfo.getRefund()))
		{
			if (paymentInfo.getRefund().size() == 9)
			{
				manageNextRefund();
				break refundRules;
			}
			availableToRefund &= paymentInfo.getRefund().size() < 10;
		}
		return availableToRefund;
	}

	/**
	 * function with must refund all the amount of the order
	 */
	private void manageNextRefund()
	{
		//TODO 10th refund, refund the full value... check if it will really be implemented and if there a easy way to do this (to avoid manual calculation)
	}

	/**
	 * @param buttons
	 * @return actions
	 */
	private Map<String, List<ItemActionChip>> createActionMapping(final List<ItemActionChip> buttons)
	{
		final Map<String, List<ItemActionChip>> actions = new HashMap<>();
		for (final ItemActionChip button : buttons)
		{
			final String name = findSectionName(button);
			if (name == null)
			{
				continue;
			}
			if (CollectionUtils.isEmpty(actions.get(name)))
			{
				actions.put(name, new ArrayList<ItemActionChip>());
			}
			actions.get(name).add(button);
		}
		return actions;
	}

	/**
	 * @param button
	 * @return sectionName
	 */
	private String findSectionName(final ItemActionChip button)
	{
		final GenericSectionChip section = findSection(button);
		return section != null ? section.getName() : null;
	}

	/**
	 * @param chip
	 * @return sectionChip
	 */
	private GenericSectionChip findSection(final AbstractChip chip)
	{
		if (chip == null)
		{
			return null;
		}
		if (chip instanceof GenericSectionChip)
		{
			return (GenericSectionChip) chip;
		}
		return findSection((AbstractChip) chip.getParent());
	}

	/**
	 * find for all buttons in the tab
	 * 
	 * @return itemActionChip
	 */
	private List<ItemActionChip> getButtons(final AbstractChip abstractChip)
	{
		final ClipChip clipChip = getClipChips(abstractChip);

		final Object chipObject = clipChip != null ? clipChip : abstractChip;

		if (chipObject instanceof ItemActionChip)
		{
			return Collections.singletonList((ItemActionChip) chipObject);
		}
		else if (chipObject instanceof AttributeChip || chipObject instanceof InlineShortcutChip
				|| chipObject instanceof AmazonSandboxSimulationChip)
		{
			return Collections.EMPTY_LIST;
		}

		final List<AbstractChip> abstractChips = getChildren(chipObject);

		final List<ItemActionChip> values = new ArrayList<ItemActionChip>();

		for (final AbstractChip chip : abstractChips)
		{
			values.addAll(getButtons(chip));
		}
		return values;
	}

	/**
	 * @param chip
	 * @return abstractChips
	 */
	private List<AbstractChip> getChildren(final Object chip)
	{
		try
		{
			final Method getChildren = chip.getClass().getMethod("getChildren");
			final List<AbstractChip> chips = (List<AbstractChip>) getChildren.invoke(chip);
			return chips;
		}
		catch (final Exception e)
		{
			LOG.error("children not found", e);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @param abstractChip
	 * @return ClipChip
	 */
	private ClipChip getClipChips(final AbstractChip abstractChip)
	{
		try
		{
			final Method getClipChip = abstractChip.getClass().getMethod("getClipChip");
			final ClipChip clipChip = (ClipChip) getClipChip.invoke(abstractChip);
			return clipChip;
		}
		catch (final Exception e)
		{
			LOG.debug("there's no clipchip in the abstract chip", e);
		}
		return null;
	}

	/**
	 * check if first parameter is equals to the one of the array
	 * 
	 * @param string
	 * @param values
	 * @return isEquals
	 */
	private boolean equalsIgnoreCaseAny(final String string, final String... values)
	{
		boolean equals = false;
		if (StringUtils.isNotEmpty(string))
		{
			for (final String value : values)
			{
				if (equals |= string.equalsIgnoreCase(value))
				{
					return equals;
				}
			}
		}
		return equals;
	}

}
