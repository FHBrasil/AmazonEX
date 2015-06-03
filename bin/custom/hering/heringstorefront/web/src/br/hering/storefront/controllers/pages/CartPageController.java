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
package br.hering.storefront.controllers.pages;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.hering.facades.facades.order.HeringCheckoutFacade;
import br.hering.facades.flow.impl.SessionOverrideCheckoutFlowFacade;
import br.hering.storefront.controllers.ControllerConstants;
import br.hering.storefront.util.HeringPageType;
import de.hybris.platform.acceleratorfacades.order.AcceleratorCheckoutFacade;
import de.hybris.platform.acceleratorservices.config.SiteConfigService;
import de.hybris.platform.acceleratorservices.enums.CheckoutFlowEnum;
import de.hybris.platform.acceleratorservices.enums.CheckoutPciOptionEnum;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.UpdateQuantityForm;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commercefacades.order.data.CartRestorationData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.voucher.VoucherFacade;
import de.hybris.platform.commercefacades.voucher.data.VoucherData;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.session.SessionService;
import org.springframework.integration.core.MessageSource;



/**
 * Controller for cart page
 */
@Controller
@Scope("tenant")
@RequestMapping(value = "/cart")
public class CartPageController extends AbstractPageController
{
	protected static final Logger LOG = Logger.getLogger(CartPageController.class);

	public static final String SHOW_CHECKOUT_STRATEGY_OPTIONS = "storefront.show.checkout.flows";

	private static final String CART_CMS_PAGE_LABEL = "cart";
	private static final String CONTINUE_URL = "continueUrl";

	@Resource(name = "cartFacade")
	private CartFacade cartFacade;

	@Resource(name = "voucherFacade")
	private VoucherFacade voucherFacade;

	@Resource(name = "siteConfigService")
	private SiteConfigService siteConfigService;

	@Resource(name = "sessionService")
	private SessionService sessionService;

	@Resource(name = "acceleratorCheckoutFacade")
	private AcceleratorCheckoutFacade checkoutFacade;

	@Resource(name = "simpleBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder resourceBreadcrumbBuilder;

	@Resource
	private CartService cartService;
	
	@Resource
	private PriceDataFactory priceDataFactory;
	
	
/*	@Resource
	private MessageSource messageSource;
	
	
	@Resource
	private I18NService i18nService;*/
	

/*	public MessageSource getMessageSource() {
		return messageSource;
	}

	public I18NService getI18nService() {
		return i18nService;
	}
*/
	
	// Public getter used in a test
	@Override
	public SiteConfigService getSiteConfigService()
	{
		return siteConfigService;
	}

	@ModelAttribute("showCheckoutStrategies")
	public boolean isCheckoutStrategyVisible()
	{
		return getSiteConfigService().getBoolean(SHOW_CHECKOUT_STRATEGY_OPTIONS, false);
	}

	/*
	 * Display the cart page
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String showCart(final Model model) throws CMSItemNotFoundException, CommerceCartModificationException
	{
		//checkoutFacade.setDeliveryAddressIfAvailable();
		checkoutFacade.setDeliveryModeIfAvailable();
		prepareDataForPage(model);
		return ControllerConstants.Views.Pages.Cart.CartPage;
	}

	/**
	 * Handle the '/cart/checkout' request url. This method checks to see if the cart is valid before allowing the
	 * checkout to begin. Note that this method does not require the user to be authenticated and therefore allows us to
	 * validate that the cart is valid without first forcing the user to login. The cart will be checked again once the
	 * user has logged in.
	 * 
	 * @return The page to redirect to
	 */
	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	@RequireHardLogIn
	public String cartCheck(final Model model, final RedirectAttributes redirectModel) throws CommerceCartModificationException
	{
		SessionOverrideCheckoutFlowFacade.resetSessionOverrides();

		if (!cartFacade.hasSessionCart() || cartFacade.getSessionCart().getEntries().isEmpty())
		{
			// No session cart or empty session cart. Bounce back to the cart page.
			return REDIRECT_PREFIX + "/cart";
		}


		if (validateCart(redirectModel))
		{
			return REDIRECT_PREFIX + "/cart";
		}

		// Redirect to the start of the checkout flow to begin the checkout process
		// We just redirect to the generic '/checkout' page which will actually select the checkout flow
		// to use. The customer is not necessarily logged in on this request, but will be forced to login
		// when they arrive on the '/checkout' page.
		return REDIRECT_PREFIX + "/checkout";
	}

	// This controller method is used to allow the site to force the visitor through a specified checkout flow.
	// If you only have a static configured checkout flow then you can remove this method.
	@RequestMapping(value = "/checkout/select-flow", method = RequestMethod.GET)
	@RequireHardLogIn
	public String initCheck(final Model model, final RedirectAttributes redirectModel,
			@RequestParam(value = "flow", required = false) final CheckoutFlowEnum checkoutFlow,
			@RequestParam(value = "pci", required = false) final CheckoutPciOptionEnum checkoutPci)
			throws CommerceCartModificationException
	{
		SessionOverrideCheckoutFlowFacade.resetSessionOverrides();

		if (!cartFacade.hasSessionCart() || cartFacade.getSessionCart().getEntries().isEmpty())
		{
			// No session cart or empty session cart. Bounce back to the cart page.
			return REDIRECT_PREFIX + "/cart";
		}

		// Override the Checkout Flow setting in the session
		if (checkoutFlow != null && StringUtils.isNotBlank(checkoutFlow.getCode()))
		{
			SessionOverrideCheckoutFlowFacade.setSessionOverrideCheckoutFlow(checkoutFlow);
		}

		// Override the Checkout PCI setting in the session
		//		if (checkoutPci != null && StringUtils.isNotBlank(checkoutPci.getCode()))
		//		{
		//			SessionOverrideCheckoutFlowFacade.setSessionOverrideSubscriptionPciOption(checkoutPci);
		//		}

		SessionOverrideCheckoutFlowFacade.setSessionOverrideSubscriptionPciOption(checkoutPci.DEFAULT);

		// Redirect to the start of the checkout flow to begin the checkout process
		// We just redirect to the generic '/checkout' page which will actually select the checkout flow
		// to use. The customer is not necessarily logged in on this request, but will be forced to login
		// when they arrive on the '/checkout' page.
		return REDIRECT_PREFIX + "/checkout";
	}

	@RequestMapping(value = "redeemVoucher", method = RequestMethod.GET)
	public String redeemVoucher(@RequestParam(value = "voucherCode") final String voucherCode, RedirectAttributes redirectAttributes)
	{
		final String cartURL = REDIRECT_PREFIX + "/cart";
		
		if(StringUtils.isBlank(voucherCode))
		{
			GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.ERROR_MESSAGES_HOLDER, "voucher.blank");
			return cartURL;
		}
		
		if (!voucherFacade.checkVoucherCode(voucherCode))
		{
			GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.ERROR_MESSAGES_HOLDER, "voucher.invalid");
			return cartURL;
		}
		
		if(voucherCode.startsWith("vc_")){
			GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.ERROR_MESSAGES_HOLDER, "voucher.invalid");
			return cartURL;
		}
		
		try
		{
			voucherFacade.applyVoucher(voucherCode);
		}
		catch (Exception e)
		{
			GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.ERROR_MESSAGES_HOLDER, "voucher.error");
			LOG.error("error", e);
		}
		
		return cartURL;
	}

	@ModelAttribute("hasAppliedVoucher")
	public boolean hasAppliedVoucher()
	{
		return CollectionUtils.isNotEmpty(voucherFacade.getVouchersForCart());
	}

	@RequestMapping(value = "releaseVoucher", method = RequestMethod.GET)
	public String releaseVoucher()
	{
		for (VoucherData voucher : voucherFacade.getVouchersForCart())
		{
			try
			{
				voucherFacade.releaseVoucher(voucher.getVoucherCode());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return REDIRECT_PREFIX + "/cart";
	}

//	XXX remo��o de dependencia com extension carrier
//	@RequestMapping(value = "/calculateDelivery", method = RequestMethod.POST)
//	public String calculateDelivery(@RequestParam final String postalCode, final RedirectAttributes redirectModel) throws Exception 
//	{
//		final CartData cartData = cartFacade.getSessionCart();
//		final double weight = deliveryService.calculateTotalWeight(cartService.getSessionCart());
//		final double totalCost = cartData.getTotalPrice().getValue().doubleValue();
//		boolean existDelivery = calculateDeliveryFacade.setCheaperDeliveryMode(postalCode, weight, totalCost);
//
//		if(!existDelivery){
//			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, "basket.page.shipping.postcode.notfound");
//		}
//		
//		return REDIRECT_PREFIX + "/cart";
//	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateCartQuantities(@RequestParam("entryNumber") final long entryNumber, final Model model,
			@Valid final UpdateQuantityForm form, final BindingResult bindingResult, final HttpServletRequest request,
			final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		if (bindingResult.hasErrors())
		{
			for (final ObjectError error : bindingResult.getAllErrors())
			{
				if (error.getCode().equals("typeMismatch"))
				{
					GlobalMessages.addErrorMessage(model, "basket.error.quantity.invalid");
				}
				else
				{
					GlobalMessages.addErrorMessage(model, error.getDefaultMessage());
				}
			}
		}
		else if (cartFacade.getSessionCart().getEntries() != null)
		{
			try
			{
				final CartModificationData cartModification = cartFacade.updateCartEntry(entryNumber, form.getQuantity().longValue());
				if (cartModification.getQuantity() == form.getQuantity().longValue())
				{
					// Success

					if (cartModification.getQuantity() == 0)
					{
						// Success in removing entry
						GlobalMessages
								.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, "basket.page.message.remove");
					}
					else
					{
						// Success in update quantity
						GlobalMessages
								.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, "basket.page.message.update");
					}
				}
				else if (cartModification.getQuantity() > 0)
				{
					// Less than successful
					GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,
							"basket.page.message.update.reducedNumberOfItemsAdded.lowStock", new Object[]
							{ cartModification.getEntry().getProduct().getName(), cartModification.getQuantity(), form.getQuantity(),
									request.getRequestURL().append(cartModification.getEntry().getProduct().getUrl()) });
				}
				else
				{
					// No more stock available
					GlobalMessages.addFlashMessage(
							redirectModel,
							GlobalMessages.ERROR_MESSAGES_HOLDER,
							"basket.page.message.update.reducedNumberOfItemsAdded.noStock",
							new Object[]
							{ cartModification.getEntry().getProduct().getName(),
									request.getRequestURL().append(cartModification.getEntry().getProduct().getUrl()) });
				}

				// Redirect to the cart page on update success so that the browser doesn't re-post again

				final String referer = request.getHeader("Referer");
				  if (StringUtils.isNotBlank(referer))
				  {
				   return REDIRECT_PREFIX + referer;
				  }
				
				return REDIRECT_PREFIX + "/cart";
			}
			catch (final CommerceCartModificationException ex)
			{
				LOG.warn("Couldn't update product with the entry number: " + entryNumber + ".", ex);
			}
		}

		prepareDataForPage(model);
		return ControllerConstants.Views.Pages.Cart.CartPage;
	}

	protected void createProductList(final Model model) throws CMSItemNotFoundException
	{
		final CartData cartData = cartFacade.getSessionCartWithEntryOrdering(true);
		boolean hasPickUpCartEntries = false;
		if (cartData.getEntries() != null && !cartData.getEntries().isEmpty())
		{
			for (final OrderEntryData entry : cartData.getEntries())
			{
				if (!hasPickUpCartEntries && entry.getDeliveryPointOfService() != null)
				{
					hasPickUpCartEntries = true;
				}
				final UpdateQuantityForm uqf = new UpdateQuantityForm();
				uqf.setQuantity(entry.getQuantity());
				model.addAttribute("updateQuantityForm" + entry.getEntryNumber(), uqf);
			}
		}

		model.addAttribute("cartData", cartData);
		model.addAttribute("hasPickUpCartEntries", Boolean.valueOf(hasPickUpCartEntries));

		storeCmsPageInModel(model, getContentPageForLabelOrId(CART_CMS_PAGE_LABEL));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(CART_CMS_PAGE_LABEL));

		model.addAttribute(WebConstants.BREADCRUMBS_KEY, resourceBreadcrumbBuilder.getBreadcrumbs("breadcrumb.cart"));
		model.addAttribute("pageType", HeringPageType.CART.name());
	}

	protected void prepareDataForPage(final Model model) throws CMSItemNotFoundException
	{
		final String continueUrl = (String) sessionService.getAttribute(WebConstants.CONTINUE_URL);
		model.addAttribute(CONTINUE_URL, (continueUrl != null && !continueUrl.isEmpty()) ? continueUrl : ROOT);

		createProductList(model);

		final CartRestorationData restorationData = (CartRestorationData) sessionService
				.getAttribute(WebConstants.CART_RESTORATION);
		model.addAttribute("restorationData", restorationData);
		model.addAttribute("isOmsEnabled", Boolean.valueOf(getSiteConfigService().getBoolean("oms.enabled", false)));
		model.addAttribute("supportedCountries", cartFacade.getDeliveryCountries());
		model.addAttribute("expressCheckoutAllowed", Boolean.valueOf(checkoutFacade.isExpressCheckoutAllowedForCart()));
		model.addAttribute("taxEstimationEnabled", Boolean.valueOf(checkoutFacade.isTaxEstimationEnabledForCart()));
		model.addAttribute("cartData", checkoutFacade.getCheckoutCart());
	}

	protected boolean validateCart(final RedirectAttributes redirectModel)
	{
		//Validate the cart
		List<CartModificationData> modifications = new ArrayList<>();
		try
		{
			modifications = cartFacade.validateCartData();
		}
		catch (CommerceCartModificationException e)
		{
			LOG.error("Failed to validate cart", e);
		}
		if (!modifications.isEmpty())
		{
			LOG.info("errors");
			
			for(CartModificationData d : modifications)
			{
				LOG.info("e: " + d.toString());
			}
			
			redirectModel.addFlashAttribute("validationData", modifications);

			// Invalid cart. Bounce back to the cart page.
			return true;
		}
		
		return false;
	}
	
	@ModelAttribute("instalments")
	public String getInstalments()
	{
		List<String> generatedInstallments = ((HeringCheckoutFacade) checkoutFacade).formatInstallmentsCost(getCartData());
		
		if(CollectionUtils.isNotEmpty(generatedInstallments) && generatedInstallments.size() > 1)
		{
			int size = generatedInstallments.size();
			String last = generatedInstallments.get(size-1);
			
			//final String de = getMessageSource().getMessage("text.fliegercommerce.texto117", null, getI18nService().getCurrentLocale());
			//final String semjuros = getMessageSource().getMessage("text.fliegercommerce.texto117", null, getI18nService().getCurrentLocale());	
			
			return "<b>" + size + "x</b> de R$ " + last + " sem juros";
		}
		
		return "";
	}
	
	
	
	
}
