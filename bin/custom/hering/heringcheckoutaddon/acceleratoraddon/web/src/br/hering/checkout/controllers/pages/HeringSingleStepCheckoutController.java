/**
 * 
 */
package br.hering.checkout.controllers.pages;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.noggit.JSONUtil;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.hering.checkout.constants.HeringcheckoutaddonWebConstants;
import br.hering.checkout.controllers.HeringcheckoutaddonControllerConstants;
import br.hering.core.enums.TipoDeEndereco;
import br.hering.core.util.HeringPageType;
import br.hering.facades.order.data.CustomPaymentInfoData;
import br.hering.facades.order.data.PaymentModeData;
import br.hering.facades.order.data.VoucherPaymentInfoData;
import br.hering.facades.voucher.HeringVoucherFacade;
import br.hering.heringstorefrontcommons.forms.HeringAddressForm;
import br.hering.heringstorefrontcommons.forms.HeringPaymentDetailsForm;

import com.flieger.payment.data.AdvancePaymentInfoData;
import com.flieger.payment.data.BoletoPaymentInfoData;
import com.flieger.payment.data.HeringDebitPaymentInfoData;
import com.flieger.payment.model.HeringDebitPaymentInfoModel;
import com.fliegersoftware.addon.paypal.controllers.pages.PaypalPaymentRequestController;
import com.fliegersoftware.addon.paypal.facades.PayPalCheckoutFacade;
import com.google.common.base.Strings;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.PlaceOrderForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.UpdateQuantityForm;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commercefacades.order.data.DeliveryModeData;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.voucher.data.VoucherData;
import de.hybris.platform.commercefacades.voucher.exceptions.VoucherOperationException;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.i18n.L10NService;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.util.Config;

/**
 * @author jfelipe
 */
@RequestMapping(value = "/checkout/single")
public class HeringSingleStepCheckoutController extends HeringMultiStepCheckoutController {
    
    private static final Logger LOG = Logger.getLogger(HeringSingleStepCheckoutController.class);
    private static final String TEMPORARY_CARD_NUMBER = "temporaryCardNumber";
    private static final String TEMPORARY_CV2_NUMBER = "temporaryCv2Number";
    private static final String REDIRECT_URL_SINGLE_STEP_CHECKOUT = REDIRECT_PREFIX
            + "/checkout/single/single-step-checkout";
    private static final String REDIRECT_URL_CART = REDIRECT_PREFIX + "/cart";
    private static final String REDIRECT_URL_SUMMARY = REDIRECT_PREFIX + "/checkout/single/summary";
    private static final String MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL = "multiStepCheckoutSummary";
    private static final String VALE_CREDITO = "vc_";
    private static final String VALE_CREDITO_RESERV = "vc_reserv";
    private static final Pattern ADYEN_ERRO_MESSAGE = Pattern
            .compile(" *([0-9]+)[^0-9]+([0-9]+){0,1}.*");
    @Resource
    protected BaseStoreService baseStoreService;
    @Resource
    private I18NService i18nService;
    @Resource
    protected HeringVoucherFacade heringVoucherFacade;
    @Resource
    private L10NService l10nService;
    @Resource
    private PaypalPaymentRequestController paypalPaymentRequestController;
    @Resource
    private PayPalCheckoutFacade paypalCheckoutFacade;
    
    
    /**
     * @return
     */
    @ModelAttribute("isVoucherFreeShipping")
    protected String isVoucherFreeShipping() {
        CartData cartData = getCheckoutFacade().getCheckoutCart();
        Boolean isVoucherFreeShipping = false;
        List<VoucherData> voucherDatas = cartData.getAppliedVouchers();
        if (voucherDatas != null) {
            for (VoucherData voucherData : voucherDatas) {
                isVoucherFreeShipping = voucherData.isFreeShipping();
                if (isVoucherFreeShipping) {
                    break;
                }
            }
        }
        return String.valueOf(isVoucherFreeShipping);
    }
    
    
    /**
     * @return
     */
    @ModelAttribute("cheaperDeliveryMode")
    protected DeliveryModeData getCheaperDeliveryMode() {
        List<DeliveryModeData> deliveryModes =
                (List<DeliveryModeData>) getCheckoutFacade().getSupportedDeliveryModes();
        return deliveryModes.isEmpty() ? null : deliveryModes.get(0);
    }
    
    
    /**
     * 
     */
    // @Override
    // @ModelAttribute("instalmentsCreditCard")
    public List<SelectOption> getOrderInstalments() {
        final List<SelectOption> instalments = new ArrayList<SelectOption>();
        List<String> generatedInstallments =
                getCheckoutFacade().formatInstallmentsCost(getCheckoutFacade().getCheckoutCart());
        int count = 1;
        for (String installment : generatedInstallments) {
            instalments.add(new SelectOption(new Integer(count).toString(), new Integer(count)
                    .toString() + "X " + installment));
            count++;
        }
        return instalments;
    }
    
    
    /**
     * 
     */
    @Override
    @ModelAttribute("checkoutSteps")
    public List<CheckoutSteps> addCheckoutStepsToModel(final HttpServletRequest request) {
        final List<CheckoutSteps> checkoutSteps = new ArrayList<CheckoutSteps>();
        checkoutSteps.add(new CheckoutSteps("single-step-checkout",
                "/checkout/single/single-step-checkout"));
        return checkoutSteps;
    }
    
    
    /**
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequireHardLogIn
    public String gotoFirstStep() {
        if (hasValidCart() && getCheckoutFacade().hasShippingItems()) {
            return REDIRECT_URL_SINGLE_STEP_CHECKOUT;
        }
        return REDIRECT_URL_CART;
    }
    
    
    /**
     * @param model
     * @return
     * @throws CMSItemNotFoundException
     */
    @RequestMapping(value = "/single-step-checkout", method = RequestMethod.GET)
    @RequireHardLogIn
    public String prepareSingleStepCheckout(final Model model) throws CMSItemNotFoundException {
        if (!hasValidCart() && !getCheckoutFacade().hasShippingItems()) {
            return REDIRECT_URL_CART;
        }
        getCheckoutFacade().setDeliveryAddressIfAvailable();
        getCheckoutFacade().setDeliveryModeIfAvailable();
        final CartData cartData = getCheckoutFacade().getCheckoutCart();
        final CustomerData customerData = getUser();
        final AddressData selectedDeliveryAddress = cartData.getDeliveryAddress();
        AddressData selectedBillingAddress = getAddressBilling();
        final HeringPaymentDetailsForm paymentDetailsForm = getPreparedHeringPaymentDetailsForm();
        final HeringAddressForm heringAddressForm = getPreparedAddressForm();
        final HeringAddressForm packstationAddressForm = getPreparedAddressForm();
        if(cartData.getBillingAddress() == null)
        {
        	storeBillingAddressIntoCart(cartData);
        }
        if(selectedBillingAddress == null && cartData.getBillingAddress() != null)
        {
        	selectedBillingAddress = cartData.getBillingAddress();
        }
        model.addAttribute("cartData", cartData);
        createProductList(model);
        model.addAttribute("paymentDetailsForm", paymentDetailsForm);
        // if (!model.containsAttribute("heringAddressForm")) {
        model.addAttribute("heringAddressForm", heringAddressForm);
        // }
        model.addAttribute("packstationAddressForm", packstationAddressForm);
        model.addAttribute("selectedDeliveryAddress", selectedDeliveryAddress);
        model.addAttribute("selectedBillingAddress", selectedBillingAddress);
        model.addAttribute("customer", customerData);
        if (selectedDeliveryAddress != null && selectedBillingAddress != null) {
            model.addAttribute("checkedDifferingBilling",
                    !selectedDeliveryAddress.getId().equals(selectedBillingAddress.getId())
                            ? true : false);
        } else {
            model.addAttribute("checkedDifferingBilling", false);
        }
        model.addAttribute("addressData", getUserFacade().getAddressBook());
        model.addAttribute("noAddress", Boolean.valueOf(hasNoDeliveryAddress()));
        model.addAttribute("showSaveToAddressBook", Boolean.TRUE);
        model.addAttribute("deliveryMethods", getDeliveryModes());
        model.addAttribute("instalments", getOrderInstalments());
        model.addAttribute("placeOrderForm", new PlaceOrderForm());
        model.addAttribute("metaRobots", "no-index,no-follow");
        model.addAttribute("pageType", HeringPageType.SINGLESTEPCHECKOUT.name());
        this.prepareDataForPage(model);
        storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
        setUpMetaDataForContentPage(model,
                getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
        model.addAttribute(WebConstants.BREADCRUMBS_KEY, getResourceBreadcrumbBuilder()
                .getBreadcrumbs("checkout.multi.deliveryAddress.breadcrumb"));
        setupAddPaymentPage(model, null);
        return HeringcheckoutaddonControllerConstants.Views.Pages.SingleStepCheckout.SingleStepCheckoutPage;
    }
    
    
    /***
     * @param paymentDetailsForm
     */
    private void storeBillingAddressIntoCart(final CartData cartData) {
    	// in case the checkout cart does not already have a billing address, we (try to) get
        // the user default billing address
        AddressData billingAddressData = getAddressBilling();
        if (billingAddressData == null) {
        	// in case user does not have a default billing address, we must clone the
            // delivery
            // address
        	if(cartData.getDeliveryAddress() != null)
        	{
        		billingAddressData = (AddressData) SerializationUtils.clone(cartData.getDeliveryAddress());
        		billingAddressData.setBillingAddress(true);
        	}
        }
        // At this point, billingAddressData should not be null, because it is either a
        // already registered billing address, a user default billing address or a cloned
        // delivery address
        if (billingAddressData != null) {
	        cartData.setBillingAddress(billingAddressData);        
	        getCheckoutFacade().saveBillingAddressIntoCart(cartData.getBillingAddress());
        }
    }
    
    
    /**
     * 
     */
    @Override
    @RequestMapping(value = "/select-delivery-address/{selectedAddressCode:.*}",
            method = RequestMethod.GET)
    @RequireHardLogIn
    public String doSelectDeliveryAddress(
            @PathVariable("selectedAddressCode") final String selectedAddressCode) {
        if (!hasValidCart()) {
            return REDIRECT_URL_CART;
        }
        if (!getCheckoutFacade().hasShippingItems()) {
            return REDIRECT_URL_CART;
        }
        if (StringUtils.isNotBlank(selectedAddressCode)) {
            final AddressData selectedAddressData = getCheckoutFacade().getDeliveryAddressForCode(selectedAddressCode);
            final boolean hasSelectedAddressData = selectedAddressData != null;
            if (hasSelectedAddressData) {
                final AddressData cartCheckoutDeliveryAddress = getCheckoutFacade().getCheckoutCart().getDeliveryAddress();
                if (isAddressIdChanged(cartCheckoutDeliveryAddress, selectedAddressData)) {
                	selectedAddressData.setDefaultAddress(true);
                	getUserFacade().editAddress(selectedAddressData);
                    getCheckoutFacade().setDeliveryAddress(selectedAddressData);
                    if (cartCheckoutDeliveryAddress != null && !cartCheckoutDeliveryAddress.isVisibleInAddressBook()) { // temporary
                                                                                        // address
                                                                                        // should
                                                                                        // be
                                                                                        // removed
                        getUserFacade().removeAddress(cartCheckoutDeliveryAddress);
                    }
                }
            }
        }
        return REDIRECT_URL_SINGLE_STEP_CHECKOUT;
    }
    
    
    /**
     * @param deliveryAddressForm
     * @param bindingResult
     * @param model
     * @param redirectModel
     * @return
     * @throws CMSItemNotFoundException
     */
    @RequestMapping(value = "/add-address/{typeAddress}", method = RequestMethod.POST)
    @RequireHardLogIn
    public String addDeliveryAddress(@PathVariable("typeAddress") final String typeAddress,
    		final HeringAddressForm heringAddressForm,
            final BindingResult bindingResult, final Model model,
            final RedirectAttributes redirectModel) throws CMSItemNotFoundException {
    	prepareAddressForm(heringAddressForm);
        getAddressValidator().validate(heringAddressForm, bindingResult, model);
        if (!bindingResult.hasErrors()) {
        	final List<AddressData> addresses = getUserFacade().getAddressBook();
			if(typeAddress.equalsIgnoreCase("delivery"))
			{					
				heringAddressForm.setDefaultAddress(true);
			}
			else if(typeAddress.equalsIgnoreCase("billing"))
			{				
				if(addresses != null)
				{
					for(AddressData address : addresses)
					{
						address.setBillingAddress(false);
						getUserFacade().editAddress(address);
					}
				}
				heringAddressForm.setBillingAddress(true);
			}
            // if we are editing one address, turn it into inactive and create a new one
            // if(StringUtils.isBlank(heringAddressForm.getAddressId())){
            this.saveDeliveryAddress(heringAddressForm);
            /*
             * } else {
             * AddressData addressData = new AddressData();
             * addressData.setId(heringAddressForm.getAddressId());
             * getHeringUserFacade().removeAddress(addressData);
             * heringAddressForm.setAddressId(null);
             * this.saveDeliveryAddress(heringAddressForm);
             * }
             */
        }
        return REDIRECT_URL_SINGLE_STEP_CHECKOUT;
    }
    
    
    /**
     * @param addressCode
     * @param bindingResult
     * @param model
     * @param redirectModel
     * @returnplac
     * @throws CMSItemNotFoundException
     */
    @RequestMapping(value = "/edit-delivery-address", method = RequestMethod.GET)
    @RequireHardLogIn
    public String editDeliveryAddress(@RequestParam("addressCode") final String addressCode,
            final Model model) throws CMSItemNotFoundException {
        HeringAddressForm heringAddressForm = new HeringAddressForm();
        if (!Strings.isNullOrEmpty(addressCode)) {
            heringAddressForm =
                    this.convertAddressDataIntoAddressForm(getCheckoutFacade()
                            .getDeliveryAddressForCode(addressCode));
            prepareDeliveryAddressForm(heringAddressForm);
            model.addAttribute("showEditAddress", Boolean.TRUE);
        }
        model.addAttribute(heringAddressForm);
        return this.prepareSingleStepCheckout(model);
    }
    
    
    /**
     * 
     */
    @RequestMapping(value = "/remove-address", method = RequestMethod.GET)
    @RequireHardLogIn
    public String removeAddress(@RequestParam("addressCode") final String addressCode,
            final RedirectAttributes redirectModel, final Model model)
            throws CMSItemNotFoundException {
        final AddressData addressData = new AddressData();
        addressData.setId(addressCode);
        getHeringUserFacade().removeAddress(addressData);
        getCheckoutFacade().removeDeliveryAddress();
        GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER,
                "account.confirmation.address.removed");
        return REDIRECT_URL_SINGLE_STEP_CHECKOUT;
    }
    
    
    /**
     * Sets default attributes for the AddressForm parameter
     * 
     * @param addressForm
     */
    protected void prepareDeliveryAddressForm(final HeringAddressForm addressForm) {
        if (getCheckoutCustomerStrategy().isAnonymousCheckout()) {
            return;
        }
        final CustomerData userData = getUser();
        addressForm.setTitleCode(userData.getTitleCode());
        addressForm.setFirstName(userData.getFirstName());
        addressForm.setLastName(userData.getLastName());
        addressForm.setReceiver(userData.getName());
        if (addressForm.getAddressType().equalsIgnoreCase("packstation")) {
            addressForm.setCountryIso("DE");
            addressForm.setLine1("Packstation");
        }
        addressForm.setShippingAddress(Boolean.TRUE);
    }
    
    protected void prepareAddressForm(final HeringAddressForm addressForm) {
        if (getCheckoutCustomerStrategy().isAnonymousCheckout()) {
            return;
        }
        final CustomerData userData = getUser();
        // addressForm.setTitleCode(userData.getTitleCode());
//        addressForm.setFirstName(userData.getFirstName());
//        addressForm.setLastName(userData.getLastName());
//        addressForm.setReceiver(userData.getName());
        if (addressForm.getAddressType().equalsIgnoreCase("packstation")) {
            addressForm.setCountryIso("DE");
            addressForm.setLine1("Packstation");
        }
        addressForm.setShippingAddress(Boolean.TRUE);
    }
    
    
    /**
     * @param deliveryAddressForm
     * @return
     */
    private AddressData saveDeliveryAddress(final HeringAddressForm deliveryAddressForm) {
        AddressData deliveryAddressData = new AddressData();
        deliveryAddressData = convertAddressFormIntoAddressData(deliveryAddressForm);
        deliveryAddressData.setVisibleInAddressBook(true);
        if (getUserFacade().isAddressBookEmpty()) {
        	deliveryAddressData.setShippingAddress(true);
        	deliveryAddressData.setBillingAddress(true);
            deliveryAddressData.setDefaultAddress(true);
        }
        if (getCheckoutCustomerStrategy().isAnonymousCheckout()) {
            deliveryAddressData.setDefaultAddress(true);
            deliveryAddressData.setVisibleInAddressBook(true);
        }
        if((deliveryAddressForm.getDefaultAddress() != null) && deliveryAddressForm.getDefaultAddress()){
        	deliveryAddressData.setDefaultAddress(true);
        }
        getHeringUserFacade().addAddress(deliveryAddressData);
        if (!getUserFacade().isAddressBookEmpty() && !deliveryAddressData.isBillingAddress())
        {
        	getCheckoutFacade().setDeliveryAddress(deliveryAddressData);        	
        }
        else if (!getUserFacade().isAddressBookEmpty() && deliveryAddressData.isBillingAddress())
        {
        	getCheckoutFacade().getCheckoutCart().setBillingAddress(deliveryAddressData);
        	getCheckoutFacade().saveBillingAddressIntoCart(deliveryAddressData);
        }
        return deliveryAddressData;
    }
    
    
    /**
     * @param billingAddressForm
     * @param bindingResult
     * @param model
     * @param redirectModel
     * @return
     */
    @RequestMapping(value = "/add-billing-address", method = RequestMethod.POST)
    @RequireHardLogIn
    public @ResponseBody String doAddBillingAddress(final HeringAddressForm billingAddressForm,
            final BindingResult bindingResult, final Model model,
            final RedirectAttributes redirectModel) {
        this.prepareDeliveryAddressForm(billingAddressForm);
        getAddressValidator().validate(billingAddressForm, bindingResult, model);
        if (bindingResult.hasErrors()) {
            return new JSONUtil().toJSON(bindingResult.getAllErrors());
        }
        final AddressData billingAddressData = convertAddressFormIntoAddressData(billingAddressForm);
        if (billingAddressData != null) {
            billingAddressData.setBillingAddress(Boolean.TRUE.booleanValue());
            billingAddressData.setShippingAddress(Boolean.FALSE.booleanValue());
            getCheckoutFacade().getCheckoutCart().setBillingAddress(billingAddressData);
            if (Boolean.TRUE.equals(billingAddressForm.getSaveInAddressBook())) {
                getHeringUserFacade().addAddress(billingAddressData);
            }
            return Boolean.TRUE.toString();
        }
        return Boolean.FALSE.toString();
    }
    
    
    @RequestMapping(value = "/select-billing-address/{selectedAddressCode:.*}",
            method = RequestMethod.GET)
    @RequireHardLogIn
    public String doSelectBillingAddress(@PathVariable("selectedAddressCode") final String selectedAddressCode) {
        if (!hasValidCart()) {
            return REDIRECT_URL_CART;
        }
        if (!getCheckoutFacade().hasShippingItems()) {
            return REDIRECT_URL_CART;
        }
        if (StringUtils.isNotBlank(selectedAddressCode)) {
            for (final AddressData addressData : getUserFacade().getAddressBook()) {
                if (addressData.getId() != null && addressData.getId().equals(selectedAddressCode)) {
                    addressData.setBillingAddress(true);
                    getUserFacade().editAddress(addressData);
                    getCheckoutFacade().getCheckoutCart().setBillingAddress(addressData);
                    getCheckoutFacade().saveBillingAddressIntoCart(addressData);
                } else {
                    addressData.setBillingAddress(false);
                    getUserFacade().editAddress(addressData);
                }
            }
        }
        return REDIRECT_URL_SINGLE_STEP_CHECKOUT;
    }
    
    
    private AddressData getAddressBilling() {
        final List<AddressData> addresses = getUserFacade().getAddressBook();
        if (addresses != null) {
            for (AddressData address : addresses) {
                if (address.isBillingAddress())
                    return address;
            }
        }
        return null;
    }
    
    
    /**
     * @param model
     * @param selectedDeliveryMethod
     * @return
     */
    // @Override
    @RequestMapping(value = "/select-delivery-method/{selectedDeliveryMethod:.*}",
            method = RequestMethod.POST)
    @RequireHardLogIn
    public String doSelectDeliveryMode(final Model model,
            @PathVariable("selectedDeliveryMethod") final String selectedDeliveryMethod) {
        if (!hasValidCart()) {
            CartData cartData = getCheckoutFacade().getCheckoutCart();
            model.addAttribute("cartData", cartData);
            model.addAttribute("deliveryMethods", getDeliveryModes());
            model.addAttribute("selectedDeliveryAddress", cartData.getDeliveryAddress());
            model.addAttribute("selectedDeliveryMethodId", cartData.getDeliveryMode().getCode());
            model.addAttribute("command", "selectDeliveryMethod");
            model.addAttribute("success", Boolean.FALSE.toString());
            final String success =
                    getMessageSource().getMessage("text.fliegercommerce.texto123", null,
                            getI18nService().getCurrentLocale());
            model.addAttribute("successMessage", success);
            return HeringcheckoutaddonControllerConstants.Views.Pages.SingleStepCheckout.Fragments.CheckoutOrderDetails;
        }
        if (StringUtils.isNotEmpty(selectedDeliveryMethod)) {
            getCheckoutFacade().setDeliveryMode(selectedDeliveryMethod);
            CartData cartData = getCheckoutFacade().getCheckoutCart();
            model.addAttribute("cartData", cartData);
            model.addAttribute("deliveryMethods", getDeliveryModes());
            model.addAttribute("selectedDeliveryAddress", cartData.getDeliveryAddress());
            model.addAttribute("selectedDeliveryMethodId", cartData.getDeliveryMode().getCode());
            model.addAttribute("command", "selectDeliveryMethod");
            model.addAttribute("success", Boolean.TRUE.toString());
            final String success =
                    getMessageSource().getMessage("text.fliegercommerce.texto117", null,
                            getI18nService().getCurrentLocale());
            model.addAttribute("successMessage", success);
        }
        return HeringcheckoutaddonControllerConstants.Views.Pages.SingleStepCheckout.Fragments.CheckoutOrderDetails;
    }
    
    
    /**
     * @param voucherCode
     * @return
     */
    @RequestMapping(value = "/is-voucher-valid", method = RequestMethod.GET)
    protected @ResponseBody String verifyVoucher(final String voucherCode) {
        return String.valueOf(isVoucherValid(voucherCode));
    }
    
    
    /**
     * @param model
     * @param voucherCode
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/apply-vc/{voucherCode:.*}", method = RequestMethod.POST,
            produces = "application/json")
    public String applyVCVoucher(Model model, @PathVariable("voucherCode") final String voucherCode)
            throws Exception {
        VoucherData voucherData = redeemVoucher(voucherCode);
        CartData cartData = getCheckoutFacade().getCheckoutCart();
        if (voucherData != null) {
            model.addAttribute("isVoucherFreeShipping", voucherData.isFreeShipping());
            model.addAttribute("success", Boolean.TRUE.toString());
            model.addAttribute("successMessage", "Vale-Crédito aplicado com sucesso.");
        } else {
            model.addAttribute("isVoucherFreeShipping", Boolean.FALSE.toString());
            model.addAttribute("success", Boolean.FALSE.toString());
            model.addAttribute("isVoucherAmountEqualsOrderAmount", Boolean.FALSE.toString());
            model.addAttribute("successMessage", "Erro ao aplicar o Vale-Crédito.");
        }
        model.addAttribute("cartData", cartData);
        model.addAttribute("deliveryMethods", getDeliveryModes());
        model.addAttribute("selectedDeliveryAddress", cartData.getDeliveryAddress());
        model.addAttribute("selectedDeliveryMethodId", cartData.getDeliveryMode().getCode());
        model.addAttribute("isVoucherAmountEqualsOrderAmount", cartData.getTotalPrice().getValue()
                .compareTo(new BigDecimal(0.0)) == 0);
        model.addAttribute("command", "applyVoucher");
        // final HeringPaymentDetailsForm paymentDetailsForm =
        // getPreparedHeringPaymentDetailsForm();
        // model.addAttribute("paymentDetailsForm", paymentDetailsForm);
        model.addAttribute("instalments", getOrderInstalments());
        return HeringcheckoutaddonControllerConstants.Views.Pages.SingleStepCheckout.Fragments.CheckoutOrderDetails;
    }
    
    
    /**
     * @param model
     * @param voucherCode
     * @return
     */
    @RequestMapping(value = "/remove-vc/{voucherCode:.*}", method = RequestMethod.POST,
            produces = "application/json")
    protected String releaseVCVoucher(Model model,
            @PathVariable("voucherCode") final String voucherCode) {
        try {
            heringVoucherFacade.releaseVoucher(voucherCode);
            model.addAttribute("isVoucherFreeShipping", Boolean.FALSE.toString());
            model.addAttribute("success", Boolean.TRUE.toString());
            model.addAttribute("successMessage", "Vale-Crédito removido com sucesso.");
        } catch (VoucherOperationException e) {
            LOG.error("error", e);
            model.addAttribute("success", Boolean.FALSE.toString());
            model.addAttribute("successMessage", "Erro ao remover o Vale-Crédito.");
            // In case voucher was not released
            try {
                VoucherData voucherData = heringVoucherFacade.getVoucher(voucherCode);
                model.addAttribute("isVoucherFreeShipping", voucherData.isFreeShipping());
            } catch (VoucherOperationException voe) {
                model.addAttribute("isVoucherFreeShipping", Boolean.FALSE.toString());
            }
        }
        CartData cartData = getCheckoutFacade().getCheckoutCart();
        model.addAttribute("cartData", cartData);
        model.addAttribute("deliveryMethods", getDeliveryModes());
        model.addAttribute("selectedDeliveryAddress", cartData.getDeliveryAddress());
        model.addAttribute("selectedDeliveryMethodId", cartData.getDeliveryMode().getCode());
        model.addAttribute("isVoucherAmountEqualsOrderAmount", cartData.getTotalPrice().getValue()
                .compareTo(new BigDecimal(0.0)) == 0);
        model.addAttribute("command", "removeVoucher");
        model.addAttribute("instalments", getOrderInstalments());
        return HeringcheckoutaddonControllerConstants.Views.Pages.SingleStepCheckout.Fragments.CheckoutOrderDetails;
    }
    
    
    /**
     * @param voucherCode
     * @return
     */
    protected boolean isVoucherValid(final String voucherCode) {
        return heringVoucherFacade.checkVoucherCode(voucherCode);
    }
    
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateCartQuantities(@RequestParam("entryNumber") final long entryNumber,
            final Model model, @Valid final UpdateQuantityForm form,
            final BindingResult bindingResult, final HttpServletRequest request,
            final RedirectAttributes redirectModel) throws CMSItemNotFoundException {
        if (bindingResult.hasErrors()) {
            for (final ObjectError error : bindingResult.getAllErrors()) {
                if (error.getCode().equals("typeMismatch")) {
                    GlobalMessages.addErrorMessage(model, "basket.error.quantity.invalid");
                } else {
                    GlobalMessages.addErrorMessage(model, error.getDefaultMessage());
                }
            }
        } else if (cartFacade.getSessionCart().getEntries() != null) {
            try {
                final CartModificationData cartModification =
                        cartFacade.updateCartEntry(entryNumber, form.getQuantity().longValue());
                if (cartModification.getQuantity() == form.getQuantity().longValue()) {
                    // Success
                    if (cartModification.getQuantity() == 0) {
                        // Success in removing entry
                        GlobalMessages.addFlashMessage(redirectModel,
                                GlobalMessages.CONF_MESSAGES_HOLDER, "basket.page.message.remove");
                    } else {
                        // Success in update quantity
                        GlobalMessages.addFlashMessage(redirectModel,
                                GlobalMessages.CONF_MESSAGES_HOLDER, "basket.page.message.update");
                    }
                } else if (cartModification.getQuantity() > 0) {
                    // Less than successful
                    GlobalMessages.addFlashMessage(
                            redirectModel,
                            GlobalMessages.ERROR_MESSAGES_HOLDER,
                            "basket.page.message.update.reducedNumberOfItemsAdded.lowStock",
                            new Object[] {
                                    cartModification.getEntry().getProduct().getName(),
                                    cartModification.getQuantity(),
                                    form.getQuantity(),
                                    request.getRequestURL().append(
                                            cartModification.getEntry().getProduct().getUrl()) });
                } else {
                    // No more stock available
                    GlobalMessages.addFlashMessage(
                            redirectModel,
                            GlobalMessages.ERROR_MESSAGES_HOLDER,
                            "basket.page.message.update.reducedNumberOfItemsAdded.noStock",
                            new Object[] {
                                    cartModification.getEntry().getProduct().getName(),
                                    request.getRequestURL().append(
                                            cartModification.getEntry().getProduct().getUrl()) });
                }
                // Redirect to the cart page on update success so that the browser doesn't re-post
                // again
                final String referer = request.getHeader("Referer");
                if (StringUtils.isNotBlank(referer)) {
                    return REDIRECT_PREFIX + referer;
                }
                return REDIRECT_PREFIX + "/cart";
            } catch (final CommerceCartModificationException ex) {
                LOG.warn("Couldn't update product with the entry number: " + entryNumber + ".", ex);
            }
        }
        prepareDataForPage(model);
        return HeringcheckoutaddonControllerConstants.Views.Pages.SingleStepCheckout.SingleStepCheckoutPage;
    }
    
    
    protected void createProductList(final Model model) {
        final CartData cartData = getCheckoutFacade().getCheckoutCart();
        boolean hasPickUpCartEntries = false;
        if (cartData.getEntries() != null && !cartData.getEntries().isEmpty()) {
            for (final OrderEntryData entry : cartData.getEntries()) {
                if (!hasPickUpCartEntries && entry.getDeliveryPointOfService() != null) {
                    hasPickUpCartEntries = true;
                }
                final UpdateQuantityForm uqf = new UpdateQuantityForm();
                uqf.setQuantity(entry.getQuantity());
                model.addAttribute("updateQuantityForm" + entry.getEntryNumber(), uqf);
            }
        }
    }
    
    
    /**
     * This is needed to override HeringMultiStepCheckout Mapping
     */
    @Override
    @RequestMapping(value = "/multi/placeOrder")
    @RequireHardLogIn
    public String placeOrder(@ModelAttribute("placeOrderForm") final PlaceOrderForm placeOrderForm,
            final Model model, final HttpServletRequest request,
            final RedirectAttributes redirectModel) throws CMSItemNotFoundException,
            InvalidCartException, CommerceCartModificationException {
        return super.placeOrder(placeOrderForm, model, request, redirectModel);
    }
    
    
    /**
     * Redirects to paypal payment mode or to other paymentModes.
     * In case payment mode is 'paypal', we need to redirect to paypal website to complete the
     * order.
     * Otherwise it does the other payment methods implemented in hybris.
     * 
     * @param paymentDetailsForm
     * @param bindingResult
     * @param model
     * @param request
     * @param redirectModel
     * @return
     * @throws CMSItemNotFoundException
     * @throws InvalidCartException
     * @throws CommerceCartModificationException
     */
    @RequestMapping(value = "/placeOrder")
    @RequireHardLogIn
    public String redirectPaymentMode(final HeringPaymentDetailsForm paymentDetailsForm,
            final BindingResult bindingResult, final Model model, final HttpServletRequest request,
            final RedirectAttributes redirectModel) throws CMSItemNotFoundException,
            InvalidCartException, CommerceCartModificationException {
    	boolean isDifferingBillingAddress = "true".equals(request.getParameter("differingBillingAddress"));    	
        CartData cartData = getCheckoutFacade().getCheckoutCart();
        getBillingPaymentDetailsForm(paymentDetailsForm, isDifferingBillingAddress);
        getHeringPaymentDetailsValidator().validate(paymentDetailsForm, bindingResult, model);
        if (bindingResult.hasErrors()) {
            GlobalMessages.addErrorMessage(model, "checkout.error.verifyFields");
            return this.prepareSingleStepCheckout(model);
        }
        if (cartData.getDeliveryAddress() == null) {
            GlobalMessages.addErrorMessage(model, "checkout.error.deliveryAddress.blank");
            return this.prepareSingleStepCheckout(model);
        }
        if (cartData.getDeliveryMode() == null) {
            GlobalMessages.addErrorMessage(model, "checkout.error.deliveryMode.blank");
            return this.prepareSingleStepCheckout(model);
        }
        if (validateCart(redirectModel)) {
            return REDIRECT_PREFIX + "/cart";
        }
        final PaymentModeData paymentMode =
                paymentModeFacade.getPaymentModeForCode(paymentDetailsForm.getPaymentMode());
        if (HeringcheckoutaddonWebConstants.PAYPAL.equalsIgnoreCase(paymentMode.getCode())) {
            return placeOrderWithPayPal(paymentDetailsForm, bindingResult, model, request,
                    redirectModel);
        } else {
            return placeOrder(paymentDetailsForm, bindingResult, model, request, redirectModel);
        }
    }
    
    
    /**
     * Redirects to PayPal login page/payment page and returns with the response.
     * 
     * @param paymentDetailsForm
     * @return true if succedded. false otherwise.
     * @author felipe
     */
    private String placeOrderWithPayPal(final HeringPaymentDetailsForm paymentDetailsForm,
            final BindingResult bindingResult, final Model model, final HttpServletRequest request,
            final RedirectAttributes redirectModel) throws CMSItemNotFoundException { 	
        CartData cartData = getCheckoutFacade().getCheckoutCart();
        getHeringPaymentDetailsValidator().validate(paymentDetailsForm, bindingResult, model);
        if (bindingResult.hasErrors()) {
            GlobalMessages.addErrorMessage(model, "checkout.error.verifyFields");
            return this.prepareSingleStepCheckout(model);
        }
        if (cartData.getDeliveryAddress() == null) {
            GlobalMessages.addErrorMessage(model, "checkout.error.deliveryAddress.blank");
            return this.prepareSingleStepCheckout(model);
        }
        if (cartData.getDeliveryMode() == null) {
            GlobalMessages.addErrorMessage(model, "checkout.error.deliveryMode.blank");
            return this.prepareSingleStepCheckout(model);
        }
        if (validateCart(redirectModel)) {
            return REDIRECT_PREFIX + "/cart";
        }
        savePaypalPaymentMethod(paymentDetailsForm);
        return paypalPaymentRequestController.expressCheckoutMark(model);
    }
    
    
    /**
     * @param paymentDetailsForm
     * @param bindingResult
     * @param model
     * @param request
     * @param redirectModel
     * @return
     * @throws CMSItemNotFoundException
     * @throws InvalidCartException
     * @throws CommerceCartModificationException
     */
    // @RequestMapping(value = "/placeOrder")
    // @RequireHardLogIn
    public String placeOrder(final HeringPaymentDetailsForm paymentDetailsForm,
            final BindingResult bindingResult, final Model model, final HttpServletRequest request,
            final RedirectAttributes redirectModel) throws CMSItemNotFoundException,
            InvalidCartException, CommerceCartModificationException {   	
        CartData cartData = getCheckoutFacade().getCheckoutCart();
        getHeringPaymentDetailsValidator().validate(paymentDetailsForm, bindingResult, model);
        if (bindingResult.hasErrors()) {
            GlobalMessages.addErrorMessage(model, "checkout.error.verifyFields");
            return this.prepareSingleStepCheckout(model);
        }
        if (cartData.getDeliveryAddress() == null) {
            GlobalMessages.addErrorMessage(model, "checkout.error.deliveryAddress.blank");
            return this.prepareSingleStepCheckout(model);
        }
        if (cartData.getDeliveryMode() == null) {
            GlobalMessages.addErrorMessage(model, "checkout.error.deliveryMode.blank");
            return this.prepareSingleStepCheckout(model);
        }
        if (validateCart(redirectModel)) {
            return REDIRECT_PREFIX + "/cart";
        }
        model.addAttribute("cartData", cartData);
        model.addAttribute("paymentId", "" + System.currentTimeMillis());
        
        
        this.savePaymentMethod(paymentDetailsForm, request);
        // authorize, if failure occurs don't allow to place the order
        boolean isPaymentAuthorized = false;
        try {
            cartData = getCheckoutFacade().getCheckoutCart();
            /*
             * if (!Strings.isNullOrEmpty(paymentDetailsForm.getPaymentMode())
             * && HeringcheckoutaddonWebConstants.VOUCHER.equalsIgnoreCase(
             * paymentDetailsForm.getPaymentMode())) {
             */
            if (super.hasAppliedValeCredito()) {
                if (!voucherEqualBuy(cartData)) {
                    isPaymentAuthorized = true;
                    final OrderData orderData = getCheckoutFacade().placeOrder();
                    return redirectToOrderConfirmationPage(orderData);
                }
            }
            final CCPaymentInfoData ccPaymentInfo = cartData.getPaymentInfo();
            if (cartData.getCustomPaymentInfo() != null) {
                if (cartData.getCustomPaymentInfo() instanceof BoletoPaymentInfoData) {
                    isPaymentAuthorized = getCheckoutFacade().authorizeBoleto();
                } else if (cartData.getCustomPaymentInfo() instanceof HeringDebitPaymentInfoData) {
                    if (getCheckoutFacade().authorizeDebitPayment()) {
                        return redirectToCielo(model);
                    } else {
                        GlobalMessages.addErrorMessage(model, "checkout.error.verifyFields");
                        return this.prepareSingleStepCheckout(model);
                    }
                } else if (cartData.getCustomPaymentInfo() instanceof AdvancePaymentInfoData) {
                    isPaymentAuthorized = true;
                }
            } else if (ccPaymentInfo != null) {
                isPaymentAuthorized = getCheckoutFacade().authorizePayment(/*
                                                                            * paymentDetailsForm.
                                                                            * getCv2Number()
                                                                            */);
            }
        } catch (final AdapterException ae) {
            // handle a case where a wrong paymentProvider configurations on
            // the store see
            // getCommerceCheckoutService().getPaymentProvider()
            LOG.error(ae.getMessage(), ae);
        }
        if (!isPaymentAuthorized) {
            GlobalMessages.addErrorMessage(model, getCreditCardErrorMessage());
            return this.prepareSingleStepCheckout(model);
        }
        final OrderData orderData;
        try {
            orderData = getCheckoutFacade().placeOrder();
        } catch (final Exception e) {
            LOG.error("Failed to place Order", e);
            GlobalMessages.addErrorMessage(model, "checkout.placeOrder.failed");
            return this.prepareSingleStepCheckout(model);
        }
        return redirectToOrderConfirmationPage(orderData);
    }
    
    private void getBillingPaymentDetailsForm(final HeringPaymentDetailsForm paymentDetailsForm,
    		final boolean isDifferingDeliveryAddress)
    {  	
        AddressData billingAddressData = getAddressBilling();
        AddressData deliveryAddressData = getCheckoutFacade().getCheckoutCart().getDeliveryAddress();
        
        if (billingAddressData != null)
        {
        	if(!isDifferingDeliveryAddress)
        	{
                if (isAddressIdChanged(deliveryAddressData, billingAddressData)) 
                {
                	deliveryAddressData = (AddressData) SerializationUtils.clone(billingAddressData);
                    getCheckoutFacade().setDeliveryAddress(deliveryAddressData);    
                    getCheckoutFacade().setDeliveryAddressIfAvailable();
                    getCheckoutFacade().setDeliveryModeIfAvailable();
                }
        	}        		
        	getCheckoutFacade().getCheckoutCart().setBillingAddress(billingAddressData);             
        }
        else if(billingAddressData == null)
        {
        	getCheckoutFacade().getCheckoutCart().setBillingAddress(getCheckoutFacade().getCheckoutCart().getDeliveryAddress());
        }
        getCheckoutFacade().saveBillingAddressIntoCart(getCheckoutFacade().getCheckoutCart().getBillingAddress());
        paymentDetailsForm.setBillingAddress(this.convertAddressDataIntoAddressForm(getCheckoutFacade().getCheckoutCart().getBillingAddress()));
    }
    
    /**
     * @param paymentDetailsForm
     * @param cartData
     * @return
     */
    private boolean savePaymentMethod(final HeringPaymentDetailsForm paymentDetailsForm,
            final HttpServletRequest request) {
        boolean success = true;
        if (hasAppliedValeCredito()) {
            success = this.saveVoucherPaymentMethod(paymentDetailsForm);
            CartData cartData = getCheckoutFacade().getCheckoutCart();
            if (success && !voucherEqualBuy(cartData)) {
                return success;
            }
        }
        if (success) {
            final PaymentModeData paymentMode =
                    paymentModeFacade.getPaymentModeForCode(paymentDetailsForm.getPaymentMode());
            if (HeringcheckoutaddonWebConstants.BOLETO.equalsIgnoreCase(paymentMode.getCode())) {
                success = this.saveBoletoPaymentMethod(paymentDetailsForm);
            } else if (HeringcheckoutaddonWebConstants.CREDITCARD.equalsIgnoreCase(paymentMode
                    .getCode())) {
                success = this.saveCreditCardPaymentMethod(paymentDetailsForm);
            } else if (HeringcheckoutaddonWebConstants.DEBIT.equalsIgnoreCase(paymentMode.getCode())) {
                success = this.saveDebitPaymentMethod(paymentDetailsForm, request);
            } else if (HeringcheckoutaddonWebConstants.ADVANCE.equalsIgnoreCase(paymentMode
                    .getCode())) {
                success = this.saveAdvancePaymentMethod(paymentDetailsForm);
            } else {
                success = false;
            }
            return success;
        }
        return Boolean.FALSE.booleanValue();
    }
    
    
    /**
     * @param voucherCode
     * @return
     */
    private VoucherData redeemVoucher(final String voucherCode) {
        try {
            heringVoucherFacade.applyVoucher(voucherCode);
            return heringVoucherFacade.getVoucher(voucherCode);
        } catch (VoucherOperationException voe) {
            LOG.error("error: ", voe);
        }
        return null;
    }
    
    
    /**
     * @param paymentDetailsForm
     * @param cartData
     * @return
     */
    private boolean saveVoucherPaymentMethod(final HeringPaymentDetailsForm paymentDetailsForm) {
        final CartData cartData = getCheckoutFacade().getCheckoutCart();
        final String voucher = heringVoucherFacade.appliedValeCredito();
        final HeringAddressForm formAddress = paymentDetailsForm.getBillingAddress();
        final VoucherPaymentInfoData paymentInfoData = new VoucherPaymentInfoData();
        paymentInfoData.setVoucherCode(voucher);
        final PaymentModeData paymentModeData = new PaymentModeData();
        paymentModeData.setCode(HeringcheckoutaddonWebConstants.VOUCHER);
        AddressData addressData = this.convertAddressFormIntoAddressData(formAddress);
        paymentInfoData.setBillingAddress(addressData);
        cartData.setVoucherPaymentInfo(paymentInfoData);
        getCheckoutFacade().setPaymentMode(paymentModeData);
        getCheckoutFacade().savePaymentInfoIntoCart(cartData);
        return Boolean.TRUE.booleanValue();
    }
    
    
    /**
     * @param paymentDetailsForm
     * @param cartData
     * @return
     */
    private boolean saveBoletoPaymentMethod(final HeringPaymentDetailsForm paymentDetailsForm) {
        final CartData cartData = getCheckoutFacade().getCheckoutCart();
        String paymentMode = paymentDetailsForm.getPaymentMode();
        final HeringAddressForm formBillingAddress = paymentDetailsForm.getBillingAddress();
        final PaymentModeData paymentModeData = new PaymentModeData();
        paymentModeData.setCode(paymentMode);
        final BoletoPaymentInfoData paymentInfoData = new BoletoPaymentInfoData();
        paymentInfoData.setDaysToAddInBoletoExpirationDate("3");
        paymentInfoData.setTransactionReference(cartData.getCode());
        paymentInfoData.setNossoNumero(cartData.getCode() + cartData.getCode());
        if (Config.getBoolean("fliegercommerce.feature.enable.cpf", false)) {
            paymentInfoData.setCpf(getUser().getCpfcnpj());
        }
        paymentInfoData.setSaved(new Boolean(false));
        AddressData addressData = this.convertAddressFormIntoAddressData(formBillingAddress);
        getAddressVerificationFacade().verifyAddressData(addressData);
        cartData.setBillingAddress(addressData);
        paymentInfoData.setBillingAddress(addressData);
        cartData.setCustomPaymentInfo(paymentInfoData);
        getCheckoutFacade().setPaymentMode(paymentModeData);
        getCheckoutFacade().setCustomPaymentInfo(cartData);
        return Boolean.TRUE.booleanValue();
    }
    
    
    private boolean saveAdvancePaymentMethod(final HeringPaymentDetailsForm paymentDetailsForm) {
        final CartData cartData = getCheckoutFacade().getCheckoutCart();
        String paymentMode = paymentDetailsForm.getPaymentMode();
        final HeringAddressForm formBillingAddress = paymentDetailsForm.getBillingAddress();
        final PaymentModeData paymentModeData = new PaymentModeData();
        paymentModeData.setCode(paymentMode);
        final AdvancePaymentInfoData paymentInfoData = new AdvancePaymentInfoData();
        final AddressData addressData = this.convertAddressFormIntoAddressData(formBillingAddress);
        getAddressVerificationFacade().verifyAddressData(addressData);
        cartData.setBillingAddress(addressData);
        paymentInfoData.setBillingAddress(addressData);
        cartData.setCustomPaymentInfo(paymentInfoData);
        getCheckoutFacade().setPaymentMode(paymentModeData);
        getCheckoutFacade().setCustomPaymentInfo(cartData);
        return Boolean.TRUE.booleanValue();
    }
    
    
    /**
     * @param paymentDetailsForm
     * @param cartData
     * @return
     */
    private boolean saveCreditCardPaymentMethod(final HeringPaymentDetailsForm paymentDetailsForm) {
        final CartData cartData = getCheckoutFacade().getCheckoutCart();
        final HeringAddressForm formBillingAddress = paymentDetailsForm.getBillingAddress();
        final CCPaymentInfoData paymentInfoData = new CCPaymentInfoData();
        // final PaymentModeData paymentModeData = paymentModeFacade
        // .getPaymentModeForCode(paymentDetailsForm.getPaymentMode());
        paymentInfoData.setId(paymentDetailsForm.getPaymentId());
        paymentInfoData.setCardType(paymentDetailsForm.getCardTypeCode());
        paymentInfoData.setAccountHolderName(paymentDetailsForm.getNameOnCard());
        paymentInfoData.setCardNumber(paymentDetailsForm.getCardNumber());
        paymentInfoData.setStartMonth(paymentDetailsForm.getStartMonth());
        paymentInfoData.setStartYear(paymentDetailsForm.getStartYear());
        paymentInfoData.setExpiryMonth(paymentDetailsForm.getExpiryMonth());
        paymentInfoData.setExpiryYear(paymentDetailsForm.getExpiryYear());
        paymentInfoData.setInstallment(paymentDetailsForm.getInstalment());
        paymentInfoData.setCv2Number(paymentDetailsForm.getCv2Number());
        sessionService.setAttribute(TEMPORARY_CARD_NUMBER, paymentInfoData.getCardNumber());
        sessionService.setAttribute(TEMPORARY_CV2_NUMBER, paymentInfoData.getCv2Number());
        String cardBrand = getCardBrand(paymentInfoData.getCardNumber());
        paymentInfoData.setCardBrand(cardBrand);
        if (Boolean.TRUE.equals(paymentDetailsForm.getSaveInAccount())
                || getCheckoutCustomerStrategy().isAnonymousCheckout()) {
            paymentInfoData.setSaved(true);
        }
        paymentInfoData.setIssueNumber(paymentDetailsForm.getIssueNumber());
        AddressData addressData = this.convertAddressFormIntoAddressData(formBillingAddress);
        getAddressVerificationFacade().verifyAddressData(addressData);
        cartData.setBillingAddress(addressData);
        paymentInfoData.setBillingAddress(addressData);
        final CCPaymentInfoData newPaymentSubscription =
                getCheckoutFacade().createPaymentSubscription(paymentInfoData);
        if (newPaymentSubscription != null
                && StringUtils.isNotBlank(newPaymentSubscription.getSubscriptionId())) {
            if (Boolean.TRUE.equals(paymentDetailsForm.getSaveInAccount())
                    && getUserFacade().getCCPaymentInfos(true).size() <= 1) {
                getUserFacade().setDefaultPaymentInfo(newPaymentSubscription);
            }
            getCheckoutFacade().setPaymentDetails(newPaymentSubscription.getId());
            cartData.setPaymentInfo(paymentInfoData);
            // cartData.setPaymentMode(paymentModeData);
            getCheckoutFacade().setPaymentMode(
                    paymentModeFacade
                            .getPaymentModeForCode(HeringcheckoutaddonWebConstants.CREDITCARD));
            return Boolean.TRUE.booleanValue();
        }
        return Boolean.FALSE.booleanValue();
    }
    
    
    /**
     * @param paymentDetailsForm
     * @param request
     * @return
     */
    private boolean saveDebitPaymentMethod(final HeringPaymentDetailsForm paymentDetailsForm,
            final HttpServletRequest request) {
        final CartData cartData = getCheckoutFacade().getCheckoutCart();
        final HeringAddressForm formBillingAddress = paymentDetailsForm.getBillingAddress();
        /** assemble de data */
        HeringDebitPaymentInfoData debitPaymentInfoData = new HeringDebitPaymentInfoData();
        AddressData addressData = this.convertAddressFormIntoAddressData(formBillingAddress);
        debitPaymentInfoData.setBillingAddress(addressData);
        debitPaymentInfoData.setUserAgent(request.getHeader("user-agent"));
        debitPaymentInfoData.setAccept(request.getHeader("accept"));
        String host =
                request.getRequestURL().substring(0, request.getRequestURL().indexOf("/store"));
        debitPaymentInfoData.setReturnUrl(host + context.getContextPath()
                + "/checkout/multi/redirectFromCielo");
        String shopperIP = request.getHeader("X-FORWARDED-FOR");
        if (shopperIP == null) {
            shopperIP = request.getRemoteAddr();
        }
        debitPaymentInfoData.setShopperIP(shopperIP);
        /** TODO these should come from the screen */
        debitPaymentInfoData.setAccountNumber("4400000000000008");
        debitPaymentInfoData.setBank("electron");
        debitPaymentInfoData.setBaOwner("Test Card Number");
        debitPaymentInfoData.setBankIDNumber("001");
        debitPaymentInfoData.setCardAccountHolderName("Test Card Number");
        debitPaymentInfoData.setCardCvNumber("737");
        debitPaymentInfoData.setCardExpirationMonth(6);
        debitPaymentInfoData.setCardExpirationYear(2016);
        debitPaymentInfoData = getCheckoutFacade().createPaymentSubscription(debitPaymentInfoData);
        if (debitPaymentInfoData != null
                && StringUtils.isNotBlank(debitPaymentInfoData.getSubscriptionId())) {
            cartData.setCustomPaymentInfo(debitPaymentInfoData);
            getCheckoutFacade().savePaymentInfoIntoCart(cartData);
            return Boolean.TRUE.booleanValue();
        }
        return Boolean.FALSE.booleanValue();
    }
    
    
    /**
     * Saves the PayPal payment method and the billing address in order to retrieve these
     * information when PayPal returns from its billing process.
     * 
     * @param paymentDetailsForm
     * @return
     */
    private boolean savePaypalPaymentMethod(final HeringPaymentDetailsForm paymentDetailsForm) {
        final CartData cartData = getCheckoutFacade().getCheckoutCart();
        final CCPaymentInfoData paymentInfoData = new CCPaymentInfoData();
        paymentInfoData.setCardType(paymentDetailsForm.getCardTypeCode());
        getAddressVerificationFacade().verifyAddressData(cartData.getBillingAddress());
        paymentInfoData.setBillingAddress(cartData.getBillingAddress());
        cartData.setPaymentInfo(paymentInfoData);
        getCheckoutFacade().setPaymentMode(paymentModeFacade.getPaymentModeForCode(
                HeringcheckoutaddonWebConstants.PAYPAL));
        return Boolean.TRUE.booleanValue();
    }
    
    
    /**
     * @param addressForm
     * @return
     */
    private AddressData convertAddressFormIntoAddressData(final HeringAddressForm addressForm) {
        final AddressData addressData = new AddressData();
        try {
            addressData.setId(addressForm.getAddressId());
            addressData.setFirstName(addressForm.getFirstName());
            addressData.setLastName(addressForm.getLastName());
            addressData.setReceiver(addressForm.getReceiver());
            addressData.setLine1(addressForm.getLine1());
            addressData.setTown(addressForm.getTownCity());
            addressData.setPostalCode(addressForm.getPostcode());
            addressData.setDistrict(addressForm.getNeighborhood());
            if (StringUtils.isNotBlank(addressForm.getPhone())) {
                addressData.setPhone(addressForm.getPhone().substring(2));
                if (addressForm.getPhone().length() > 3) {
                    addressData.setDddPhone(addressForm.getPhone().substring(0, 2));
                }
            }
            if (StringUtils.isNotBlank(addressForm.getCelPhone())) {
                addressData.setCelPhone(addressForm.getCelPhone().substring(2));
                if (addressForm.getCelPhone().length() > 3) {
                    addressData.setDddCelPhone(addressForm.getCelPhone().substring(0, 2));
                }
            }
            addressData.setNumber(addressForm.getNumber());
            addressData.setComplement(addressForm.getComplement());
            addressData.setReference(addressForm.getReference());
            addressData.setType(TipoDeEndereco.valueOf(addressForm.getAddressType()));
            addressData
                    .setCountry(getI18NFacade().getCountryForIsocode(addressForm.getCountryIso()));
            // addressData.setRegion(getI18NFacade().getRegion(addressForm.getCountryIso(),
            // addressForm.getRegionIso()));
            addressData.setShippingAddress(Boolean.TRUE.equals(addressForm.getShippingAddress()));
            addressData.setBillingAddress(Boolean.TRUE.equals(addressForm.getBillingAddress()));
        } catch (NullPointerException npe) {
            LOG.error("error", npe);
        }
        return addressData;
    }
    
    
    /**
     * @param addressData
     * @return
     */
    private HeringAddressForm convertAddressDataIntoAddressForm(final AddressData addressData) {
        final HeringAddressForm addressForm = new HeringAddressForm();
        try {
            addressForm.setAddressId(addressData.getId());
            addressForm.setFirstName(addressData.getFirstName());
            addressForm.setLastName(addressData.getLastName());
            addressForm.setLine1(addressData.getLine1());
            addressForm.setTownCity(addressData.getTown());
            addressForm.setPostcode(addressData.getPostalCode());
            addressForm.setNeighborhood(addressData.getDistrict());
            addressForm.setPhone(new StringBuilder().append(addressData.getDddPhone())
                    .append(addressData.getPhone()).toString());
            addressForm.setCelPhone(new StringBuilder().append(addressData.getDddCelPhone())
                    .append(addressData.getCelPhone()).toString());
            addressForm.setNumber(addressData.getNumber());
            addressForm.setComplement(addressData.getComplement());
            addressForm.setReference(addressData.getReference());
            addressForm.setCountryIso(addressData.getCountry().getIsocode());
            // addressForm.setRegionIso(addressData.getRegion().getIsocode());
            addressForm.setAddressType(addressData.getType().getCode());
            addressForm.setShippingAddress(Boolean.TRUE.equals(addressData.isShippingAddress()));
            addressForm.setBillingAddress(Boolean.TRUE.equals(addressData.isBillingAddress()));
        } catch (NullPointerException e) {
        }
        return addressForm;
    }
    
    
    /**
     * @return
     */
    private HeringPaymentDetailsForm getPreparedHeringPaymentDetailsForm() {
        final CartData cartData = getCheckoutFacade().getCheckoutCart();
        final HeringPaymentDetailsForm paymentDetailsForm = new HeringPaymentDetailsForm();
        if (cartData.getPaymentInfo() != null
                && !cartData.getPaymentInfo().getCardType().equalsIgnoreCase("paypal")) {
            // Credit Card
            CCPaymentInfoData paymentInfo = cartData.getPaymentInfo();
            paymentDetailsForm.setBillingAddress(convertAddressDataIntoAddressForm(paymentInfo
                    .getBillingAddress()));
            paymentDetailsForm.setCardBrand(paymentInfo.getCardBrand());
            if (sessionService.getAttribute(TEMPORARY_CARD_NUMBER) != null) {
                paymentDetailsForm.setCardNumber((String) getSessionService().getAttribute(
                        TEMPORARY_CARD_NUMBER));
            }
            paymentDetailsForm.setCardTypeCode(paymentInfo.getCardTypeData().getCode());
            paymentDetailsForm.setCv2Number(paymentInfo.getCv2Number());
            paymentDetailsForm.setExpiryMonth(paymentInfo.getExpiryMonth());
            paymentDetailsForm.setExpiryYear(paymentInfo.getExpiryYear());
            paymentDetailsForm.setInstalment(paymentInfo.getInstallment());
            paymentDetailsForm.setNameOnCard(paymentInfo.getCardAccountHolderName());
            paymentDetailsForm.setPaymentMode(HeringcheckoutaddonWebConstants.CREDITCARD);
        } else if (cartData.getCustomPaymentInfo() != null) {
            // Boleto or Debit card
            CustomPaymentInfoData paymentInfo = cartData.getCustomPaymentInfo();
            paymentDetailsForm.setBillingAddress(convertAddressDataIntoAddressForm(paymentInfo
                    .getBillingAddress()));
            if (cartData.getCustomPaymentInfo() instanceof BoletoPaymentInfoData) {
                paymentDetailsForm.setPaymentMode(HeringcheckoutaddonWebConstants.BOLETO);
            } else {
                paymentDetailsForm.setPaymentMode(HeringcheckoutaddonWebConstants.DEBIT);
            }
        }
        if (cartData.getVoucherPaymentInfo() != null
                && !Strings.isNullOrEmpty(cartData.getVoucherPaymentInfo().getVoucherCode())) {
            // Voucher or vale-credito
            VoucherPaymentInfoData paymentInfo = cartData.getVoucherPaymentInfo();
            paymentDetailsForm.setVoucher(paymentInfo.getVoucherCode());
            paymentDetailsForm.setPaymentMode(HeringcheckoutaddonWebConstants.VOUCHER);
        }
        return paymentDetailsForm;
    }
    
    
    /**
     * 
     */
    @Override
    protected HeringAddressForm getPreparedAddressForm() {
        final CustomerData currentCustomerData = getUser();
        final HeringAddressForm addressForm = new HeringAddressForm();
        addressForm.setCountryIso("DE");
        if (getCheckoutCustomerStrategy().isAnonymousCheckout()) {
            return addressForm;
        }
        String first = StringUtils.defaultIfBlank(currentCustomerData.getFirstName(), "");
        String last = StringUtils.defaultIfBlank(currentCustomerData.getLastName(), "");
        addressForm.setReceiver((first + " " + last).trim());
        addressForm.setFirstName(currentCustomerData.getFirstName());
        addressForm.setLastName(currentCustomerData.getLastName());
        return addressForm;
    }
    
    
    /**
     * @param model
     * @param redirectModel
     * @param paymentInfoPK
     * @return
     * @throws CMSItemNotFoundException
     * @throws InvalidCartException
     * @throws CommerceCartModificationException
     */
    // @Override
    @RequestMapping(value = "/redirectFromCielo")
    @RequireHardLogIn
    public String redirectFromCielo(final Model model, final RedirectAttributes redirectModel,
            @RequestParam(required = true) final String paymentInfoPK)
            throws CMSItemNotFoundException, InvalidCartException, CommerceCartModificationException {
        CustomerModel customerModel = getCheckoutFacade().getCurrentUserForCheckout();
        PaymentInfoModel paymentInfoModel =
                getCustomerAccountService().getPaymentInfoForCode(customerModel, paymentInfoPK);
        System.out.println(paymentInfoModel);
        if (paymentInfoModel instanceof HeringDebitPaymentInfoModel) {
            if (!getCheckoutFacade().authorizeDebitPayment3D(paymentInfoPK)) {
                LOG.error("Payment has not been authorized!");
                GlobalMessages.addErrorMessage(model, "checkout.error.payment.not.accepted");
                return prepareSingleStepCheckout(model);
                // return REDIRECT_URL_ADD_PAYMENT_METHOD;
            }
        }
        final OrderData orderData;
        try {
            orderData = getCheckoutFacade().placeOrder();
        } catch (final Exception e) {
            LOG.error("Failed to place Order", e);
            GlobalMessages.addErrorMessage(model, "checkout.placeOrder.failed");
            return checkoutSummary(model, redirectModel);
        }
        return redirectToOrderConfirmationPage(orderData);
    }
    
    
    /**
     * @return
     */
    @Override
    @ModelAttribute("months")
    public List<SelectOption> getMonths() {
        final List<SelectOption> months = new ArrayList<SelectOption>();
//        final String mes =
//                getMessageSource().getMessage("text.fliegercommerce.texto118", null,
//                        getI18nService().getCurrentLocale());
//        months.add(new SelectOption("0", mes));
        for(int count = 1; count < 13; count++){
        	String message = "text.fliegercommerce.texto" + String.valueOf(132+count);
        	months.add(new SelectOption(String.valueOf(count), 
        			getMessageSource().getMessage(message, null,
        					getI18nService().getCurrentLocale())));
        }
        return months;
    }
    
    
    /**
     * @return
     */
    @Override
    @ModelAttribute("expiryYears")
    public List<SelectOption> getExpiryYears() {
        final List<SelectOption> expiryYears = new ArrayList<SelectOption>();
        final Calendar calender = new GregorianCalendar();
//        final String ano =
//                getMessageSource().getMessage("text.fliegercommerce.texto119", null,
//                        getI18nService().getCurrentLocale());
//        expiryYears.add(new SelectOption("0", ano));
        for (int i = calender.get(Calendar.YEAR); i < (calender.get(Calendar.YEAR) + 11); i++) {
            expiryYears.add(new SelectOption(String.valueOf(i), String.valueOf(i)));
        }
        return expiryYears;
    }
    
    
    /**
     * @param selectedDeliveryModeCode
     * @return
     */
    private DeliveryModeData getSelectedDeliveryMode(String selectedDeliveryModeCode) {
        List<DeliveryModeData> supportedDeliveryModes =
                (List<DeliveryModeData>) getCheckoutFacade().getSupportedDeliveryModes();
        for (DeliveryModeData deliveryMode : supportedDeliveryModes) {
            if (deliveryMode.getCode().equals(selectedDeliveryModeCode)) {
                return deliveryMode;
            }
        }
        return null;
    }
    
    
    /**
     * @return
     */
    private String getCreditCardErrorMessage() {
        String error = "checkout.adyen.error.message.";
        String heringAdyenErrorCode = sessionService.getAttribute("heringAdyenErrorCode");
        Matcher matcher =
                ADYEN_ERRO_MESSAGE.matcher(StringUtils.isBlank(heringAdyenErrorCode) ? ""
                        : heringAdyenErrorCode);
        String codigo = "";
        if (matcher.matches()) {
            for (int i = 1; i < matcher.groupCount(); i++) {
                if (i > 1) {
                    codigo += "." + matcher.group(i);
                } else {
                    codigo += matcher.group(i);
                }
            }
        } else {
            return "checkout.error.payment.not.accepted";
        }
        switch (codigo) {
            case "5.1":
            case "5.14":
            case "5.51":
            case "5.57":
            case "5.60":
            case "101":
            case "103":
            case "5.04":
            case "5.15":
                error += codigo;
                break;
            default:
                error = "checkout.error.payment.not.accepted";
                break;
        }
        LOG.info(error);
        return error;
    }
    
    
    protected List<DeliveryModeData> getDeliveryModes() {
        List<DeliveryModeData> deliveryModes =
                (List<DeliveryModeData>) getCheckoutFacade().getSupportedDeliveryModes();
        if (Boolean.valueOf(isVoucherFreeShipping())) {
            deliveryModes = new ArrayList<DeliveryModeData>();
            deliveryModes.add(getCheaperDeliveryMode());
        }
        return deliveryModes;
    }
    
    
    public PaypalPaymentRequestController getPaypalPaymentRequestController() {
        return paypalPaymentRequestController;
    }
    
    
    public void setPaypalPaymentRequestController(
            PaypalPaymentRequestController paypalPaymentRequestController) {
        this.paypalPaymentRequestController = paypalPaymentRequestController;
    }
}