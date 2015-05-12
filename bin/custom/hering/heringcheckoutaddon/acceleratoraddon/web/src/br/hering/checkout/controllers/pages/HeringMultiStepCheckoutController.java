/**
 *
 */
package br.hering.checkout.controllers.pages;

import de.hybris.platform.acceleratorfacades.order.AcceleratorCheckoutFacade;
import de.hybris.platform.acceleratorservices.payment.cybersource.data.PaymentInfoData;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.PlaceOrderForm;
import de.hybris.platform.catalog.constants.CatalogConstants.Config;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.address.data.AddressVerificationResult;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.voucher.VoucherFacade;
import de.hybris.platform.commercefacades.voucher.data.VoucherData;
import de.hybris.platform.commerceservices.address.AddressVerificationDecision;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.strategies.CheckoutCustomerStrategy;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.europe1.constants.Europe1Constants.PRICE_ACCURACY_PROPERTY;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.storefront.controllers.pages.checkout.MultiStepCheckoutController;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.hering.checkout.constants.HeringcheckoutaddonWebConstants;
import br.hering.checkout.controllers.HeringcheckoutaddonControllerConstants;
import br.hering.core.customer.impl.KPCustomerAccountService;
import br.hering.core.emailreporter.HeringSendReportEmail;
import br.hering.core.enums.TipoDeEndereco;
import br.hering.facades.checkout.payment.HeringPaymentModeFacade;
import br.hering.facades.facades.order.HeringCheckoutFacade;
import br.hering.facades.facades.order.impl.DefaultHeringCheckoutFacade;
import br.hering.facades.order.data.CustomPaymentInfoData;
import br.hering.facades.order.data.PaymentModeData;
import br.hering.facades.order.data.VoucherPaymentInfoData;
import br.hering.facades.populators.order.BoletoPaymentInfoPopulator;
import br.hering.facades.user.HeringUserFacade;
import br.hering.heringstorefrontcommons.forms.HeringAddressForm;
import br.hering.heringstorefrontcommons.forms.HeringPaymentDetailsForm;
import br.hering.heringstorefrontcommons.validation.HeringAddressValidator;
import br.hering.heringstorefrontcommons.validation.HeringPaymentDetailsValidator;
import br.hering.core.order.impl.HeringCommerceCheckoutService;
import br.hering.core.util.HeringPageType;

import com.flieger.payment.data.BoletoPaymentInfoData;
import com.flieger.payment.data.HeringDebitPaymentInfoData;
import com.flieger.payment.model.HeringDebitPaymentInfoModel;
import com.google.common.base.Strings;
import com.meterware.httpunit.WebResponse;

import groovy.json.JsonBuilder;

/**
 * @author flieger
 * @author jfelipe
 *
 */
@RequestMapping(value = "/checkout/multi")
public class HeringMultiStepCheckoutController extends MultiStepCheckoutController
{
	private static final Logger LOG = Logger
         .getLogger(HeringMultiStepCheckoutController.class);
 private static final String REDIRECT_URL_PREPARE_ONE_CHECKOUT = REDIRECT_PREFIX
         + "/checkout/multi/prepare-checkout";
 private static final String REDIRECT_URL_ADD_DELIVERY_ADDRESS = REDIRECT_PREFIX
         + "/checkout/multi/add-address";
 private static final String REDIRECT_URL_CHOOSE_DELIVERY_METHOD = REDIRECT_PREFIX
         + "/checkout/multi/choose-delivery-method";
 private static final String REDIRECT_URL_ADD_PAYMENT_METHOD = REDIRECT_PREFIX
         + "/checkout/multi/custom-add-payment-method";
 private static final String REDIRECT_URL_CART = REDIRECT_PREFIX + "/cart";
 private static final String REDIRECT_URL_SUMMARY = REDIRECT_PREFIX
         + "/checkout/multi/summary";
 private static final String MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL = "multiStepCheckoutSummary";
 
 private static final String REDIRECT_URL_CHOOSE_DELIVERY_LOCATION = REDIRECT_PREFIX
         + "/checkout/multi/choose-delivery-location";
 
 private static final String REDIRECT_URL_CIELO_LOCATION = REDIRECT_PREFIX
       + "/checkout/multi/redirectToCieloPage";
 
 private static final String VALE_CREDITO = "vc_";
 
 	@Resource
 	protected ServletContext context;
 
	@Resource(name = "userService")
	protected UserService userService;

	@Resource(name = "sessionService")
	protected SessionService sessionService;

	@Resource(name = "paymentModeFacade")
	protected HeringPaymentModeFacade paymentModeFacade;

	@Resource(name = "heringPaymentDetailsValidator")
	protected HeringPaymentDetailsValidator heringPaymentDetailsValidator;

	@Resource(name = "cartService")
	protected CartService cartService;

	@Resource(name = "addressValidator")
	protected HeringAddressValidator addressValidator;

	@Resource(name = "voucherFacade")
	protected VoucherFacade voucherFacade;

	@Resource
	protected PriceDataFactory priceDataFactory;
	
	@Resource(name = "userFacade")
	protected HeringUserFacade userFacade;
	
	@Resource(name = "customerAccountService")
	private KPCustomerAccountService customerAccountService;
	
	@Resource
	protected CartFacade cartFacade; 
	
	@ModelAttribute("instalments")
	public List<SelectOption> getInstalments()
	{
		final List<SelectOption> instalments = new ArrayList<SelectOption>();
		List<String> generatedInstallments = getCheckoutFacade().formatInstallmentsCost(getCartData());
		int count = 1;
		for (String installment : generatedInstallments)
		{
			instalments.add(new SelectOption(new Integer(count).toString(), new Integer(count).toString() + "X " + installment));
			count++;
		}
		return instalments;
	}

	@ModelAttribute("addressTypes")
	public List<SelectOption> getAddressTypes()
	{
		final List<SelectOption> addressTypes = new ArrayList<SelectOption>();
		addressTypes.add(new SelectOption(TipoDeEndereco.RESIDENCIAL.toString(), TipoDeEndereco.RESIDENCIAL.toString()));
		addressTypes.add(new SelectOption(TipoDeEndereco.COMERCIAL.toString(), TipoDeEndereco.COMERCIAL.toString()));
		return addressTypes;
	}

	@RequestMapping(method = RequestMethod.GET)
   public String gotoFirstStep() {
       if (hasValidCart()) {
           return (getCheckoutFacade().hasShippingItems()) ?
                   REDIRECT_URL_ADD_DELIVERY_ADDRESS
                   : REDIRECT_URL_CHOOSE_DELIVERY_LOCATION;
           /*
            * !return (getCheckoutFacade().hasShippingItems())
            * ? REDIRECT_URL_PREPARE_ONE_CHECKOUT :
            * REDIRECT_URL_CHOOSE_DELIVERY_LOCATION;
            */
       }
       LOG.info("Missing, empty or unsupported cart");
       return REDIRECT_URL_CART;
   }

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.storefront.controllers.pages.checkout.MultiStepCheckoutController#doChooseDeliveryModes(org
	 * .springframework.ui.Model, org.springframework.web.servlet.mvc.support.RedirectAttributes)
	 */
	@Override
	public String doChooseDeliveryModes(Model model, RedirectAttributes redirectAttributes) throws CMSItemNotFoundException
	{
		if (!hasValidCart())
		{
			LOG.info("Missing, empty or unsupported cart");
			return REDIRECT_URL_CART;
		}

		if (!getCheckoutFacade().hasShippingItems())
		{
			return REDIRECT_URL_CHOOSE_DELIVERY_LOCATION;
		}

		if (hasNoDeliveryAddress())
		{
			GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.INFO_MESSAGES_HOLDER,
					"checkout.multi.deliveryAddress.notprovided");
			return REDIRECT_URL_ADD_DELIVERY_ADDRESS;
		}
		String errorCode = getSessionService().getAttribute("adyenErrorCode");

		if (StringUtils.isNotEmpty(errorCode))
		{
			getSessionService().removeAttribute("adyenErroCode");
			if (errorCode.matches("^[0-9]{3}"))
			{
				GlobalMessages.addErrorMessage(model, "checkout.error.authorization." + errorCode);

			}
			else
			{
				GlobalMessages.addErrorMessage(model, errorCode);
			}
			//SPJ 25-06-2014  Sending Email Error Report		
			HeringSendReportEmail reportEmail = new HeringSendReportEmail();
			String strCartInformation = "";
			int intCartTotalItens = getCheckoutFacade().getCheckoutCart().getTotalItems().intValue();
			strCartInformation = strCartInformation + "<br> Cart Value: "
					+ getCheckoutFacade().getCheckoutCart().getTotalPrice().getFormattedValue().toString();
			strCartInformation = strCartInformation + "<br> Delivery Itens Quantity: "
					+ getCheckoutFacade().getCheckoutCart().getDeliveryItemsQuantity().intValue();
			strCartInformation = strCartInformation + "<br> Cart Total Itens: "
					+ getCheckoutFacade().getCheckoutCart().getTotalItems().toString();
			strCartInformation = strCartInformation + "<br> Cart Itens: " + intCartTotalItens;
			//Getting cart itens
			for (int i = 0; i < intCartTotalItens; i++)
			{
				strCartInformation = strCartInformation + "<br> Product " + i + ": "
						+ getCheckoutFacade().getCheckoutCart().getEntries().get(i).getProduct().getCode().toString();
			}
			reportEmail.SendEmailReporting("Adyen Error Mail <br><br>" + "Error Code: " + errorCode + "<br><br>" + "User ID: "
					+ userService.getCurrentUser().getUid().toString() + "<br><br> Cart: <br>" + strCartInformation);
		}

		// Try to set default delivery mode
		getCheckoutFacade().resetDeliveryMode();
		getCheckoutFacade().setDeliveryModeIfAvailable();

		final CartData cartData = getCheckoutFacade().getCheckoutCart();
		model.addAttribute("cartData", cartData);
		model.addAttribute("deliveryMethods", getCheckoutFacade().getSupportedDeliveryModes());
		this.prepareDataForPage(model);
		storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
		model.addAttribute(WebConstants.BREADCRUMBS_KEY,
				getResourceBreadcrumbBuilder().getBreadcrumbs("checkout.multi.deliveryMethod.breadcrumb"));
		model.addAttribute("metaRobots", "no-index,no-follow");
		return HeringcheckoutaddonControllerConstants.Views.Pages.MultiStepCheckout.ChooseDeliveryMethodPage;
	}


	@RequestMapping(value = "/custom-add-payment-method", method = RequestMethod.GET)
	@RequireHardLogIn
	public String customDoAddPaymentMethod(final Model model, final RedirectAttributes redirectAttributes)
			throws CMSItemNotFoundException
	{
		if (!hasValidCart())
		{
			LOG.info("Missing, empty or unsupported cart");
			return REDIRECT_URL_CART;
		}

		if (hasNoDeliveryAddress())
		{
			GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.INFO_MESSAGES_HOLDER,
					"checkout.multi.deliveryAddress.notprovided");
			return REDIRECT_URL_ADD_DELIVERY_ADDRESS;
		}
		if (hasNoDeliveryMode())
		{
			GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.INFO_MESSAGES_HOLDER,
					"checkout.multi.deliveryMethod.notprovided");
			return REDIRECT_URL_CHOOSE_DELIVERY_METHOD;
		}

		String errorCode = getSessionService().getAttribute("adyenErrorCode");
		if (!StringUtils.isEmpty(errorCode))
		{
			getSessionService().removeAttribute("adyenErroCode");
			if (errorCode.matches("^[0-9]{3}"))
			{
				GlobalMessages.addErrorMessage(model, "checkout.error.authorization." + errorCode);
			}
			else
			{
				GlobalMessages.addErrorMessage(model, errorCode);
			}
		}

		setupAddPaymentPage(model, null);

		final HeringPaymentDetailsForm paymentDetailsForm = new HeringPaymentDetailsForm();
		final HeringAddressForm addressForm = getPreparedAddressForm();
		paymentDetailsForm.setBillingAddress(addressForm);
		model.addAttribute(paymentDetailsForm);

		final CartData cartData = getCheckoutFacade().getCheckoutCart();

		model.addAttribute("cartData", cartData);
		return HeringcheckoutaddonControllerConstants.Views.Pages.MultiStepCheckout.AddPaymentMethodPage;
	}


	@RequestMapping(value = { "/custom-add-payment-method" }, method = RequestMethod.POST)
	@RequireHardLogIn
	public String customDoSavePaymentMethod(final Model model, 
			final HeringPaymentDetailsForm paymentDetailsForm,
			final BindingResult bindingResult,
			final HttpServletRequest request) throws CMSItemNotFoundException, Exception
	{
		final CartData cartData = getCheckoutFacade().getCheckoutCart();
		model.addAttribute("cartData", cartData);

		final PaymentModeData paymentMode = paymentModeFacade.getPaymentModeForCode(paymentDetailsForm.getPaymentMode());
		getCheckoutFacade().setPaymentMode(paymentMode);

		if (paymentMode != null && paymentMode.getCode() != null
				&& paymentMode.getCode().equalsIgnoreCase(HeringcheckoutaddonWebConstants.VOUCHER))
		{
			return doSaveVoucherPaymentMethod(model, paymentDetailsForm, bindingResult);
		}
		if (paymentMode != null && paymentMode.getCode() != null
				&& paymentMode.getCode().equalsIgnoreCase(HeringcheckoutaddonWebConstants.BOLETO))
		{
			return doSaveBoletoPaymentMethod(model, paymentDetailsForm, bindingResult);
		}		
		if (paymentMode != null && paymentMode.getCode() != null 
				&& paymentMode.getCode().equalsIgnoreCase(HeringcheckoutaddonWebConstants.DEBIT))
		{
			return doSaveDebitPaymentMethod(model, paymentDetailsForm, bindingResult,
					request);
		}

		return doSaveCreditCardPaymentMethod(model, paymentDetailsForm, bindingResult);
	}	

	/**
	 * @param model
	 * @param paymentDetailsForm
	 * @param bindingResult
	 * @return redirectURL
	 */
	private String doSaveCreditCardPaymentMethod(final Model model, final HeringPaymentDetailsForm paymentDetailsForm,
			final BindingResult bindingResult) throws CMSItemNotFoundException
	{
		//TESTE ENDEREÇO
		getHeringPaymentDetailsValidator().validate(paymentDetailsForm, bindingResult, model);
		setupAddPaymentPage(model, "CreditCard");

		final CartData cartData = getCheckoutFacade().getCheckoutCart();
		model.addAttribute("cartData", cartData);

		if (bindingResult.hasErrors())
		{
			GlobalMessages.addErrorMessage(model, "checkout.error.paymentethod.formentry.invalid");
			return HeringcheckoutaddonControllerConstants.Views.Pages.MultiStepCheckout.AddPaymentMethodPage;
		}

		final CCPaymentInfoData paymentInfoData = new CCPaymentInfoData();
		paymentInfoData.setId(paymentDetailsForm.getPaymentId());
		paymentInfoData.setCardType(paymentDetailsForm.getCardTypeCode());
		paymentInfoData.setAccountHolderName(paymentDetailsForm.getNameOnCard());
		
		//dois valores que não são persistidos no bd, ficam na sessao e são limpos assim que possível
		paymentInfoData.setCardNumber(paymentDetailsForm.getCardNumber());
		paymentInfoData.setCv2Number(paymentDetailsForm.getCv2Number());
		
		paymentInfoData.setStartMonth(paymentDetailsForm.getStartMonth());
		paymentInfoData.setStartYear(paymentDetailsForm.getStartYear());
		paymentInfoData.setExpiryMonth(paymentDetailsForm.getExpiryMonth());
		paymentInfoData.setExpiryYear(paymentDetailsForm.getExpiryYear());
		paymentInfoData.setInstallment(paymentDetailsForm.getInstalment());
		
		String cardBrand = getCardBrand(paymentInfoData.getCardNumber());
		paymentInfoData.setCardBrand(cardBrand);

		if (Boolean.TRUE.equals(paymentDetailsForm.getSaveInAccount()) || getCheckoutCustomerStrategy().isAnonymousCheckout())
		{
			paymentInfoData.setSaved(true);
		}
		paymentInfoData.setIssueNumber(paymentDetailsForm.getIssueNumber());

		final AddressData addressData;
		if (Boolean.TRUE.equals(paymentDetailsForm.getNewBillingAddress()))
		{
			addressData = getCheckoutFacade().getCheckoutCart().getDeliveryAddress();
			if (addressData == null)
			{
				GlobalMessages.addErrorMessage(model,
						"checkout.multi.paymentMethod.createSubscription.billingAddress.noneSelectedMsg");
				return HeringcheckoutaddonControllerConstants.Views.Pages.MultiStepCheckout.AddPaymentMethodPage;
			}

			addressData.setBillingAddress(true); // mark this as billing address
		}
		else
		{
			final HeringAddressForm addressForm = paymentDetailsForm.getBillingAddress();

			addressData = new AddressData();
			if (addressForm != null)
			{
				addressData.setId(addressForm.getAddressId());
				addressData.setFirstName(addressForm.getFirstName());
				addressData.setLastName(addressForm.getLastName());
				addressData.setLine1(addressForm.getLine1());
				addressData.setTown(addressForm.getTownCity());
				addressData.setPostalCode(addressForm.getPostcode());
				addressData.setDistrict(addressForm.getNeighborhood());

				if (StringUtils.isNotBlank(addressForm.getPhone()))
				{
					addressData.setPhone(addressForm.getPhone().substring(2));
					if (addressForm.getPhone().length() > 3)
					{
						addressData.setDddPhone(addressForm.getPhone().substring(0, 2));
					}
				}

				if (StringUtils.isNotBlank(addressForm.getCelPhone()))
				{
					addressData.setCelPhone(addressForm.getCelPhone().substring(2));

					if (addressForm.getCelPhone().length() > 3)
					{
						addressData.setDddCelPhone(addressForm.getCelPhone().substring(0, 2));
					}
				}

				addressData.setNumber(addressForm.getNumber());
				addressData.setComplement(addressForm.getComplement());
				addressData.setReference(addressForm.getReference());
				addressData.setType(TipoDeEndereco.valueOf(addressForm.getAddressType()));
				addressData.setCountry(getI18NFacade().getCountryForIsocode(addressForm.getCountryIso()));
				addressData.setRegion(getI18NFacade().getRegion(addressForm.getCountryIso(), addressForm.getRegionIso()));

				addressData.setShippingAddress(Boolean.TRUE.equals(addressForm.getShippingAddress()));
				addressData.setBillingAddress(Boolean.TRUE.equals(addressForm.getBillingAddress()));
			}
		}

		getAddressVerificationFacade().verifyAddressData(addressData);
		paymentInfoData.setBillingAddress(addressData);

		final CCPaymentInfoData newPaymentSubscription = getCheckoutFacade()
				.createPaymentSubscription(paymentInfoData);
		if (newPaymentSubscription != null && StringUtils.isNotBlank(newPaymentSubscription.getSubscriptionId()))
		{
			if (Boolean.TRUE.equals(paymentDetailsForm.getSaveInAccount()) && getUserFacade().getCCPaymentInfos(true).size() <= 1)
			{
				getUserFacade().setDefaultPaymentInfo(newPaymentSubscription);
			}
			getCheckoutFacade().setPaymentDetails(newPaymentSubscription.getId());
		}
		else
		{
			GlobalMessages.addErrorMessage(model, "checkout.multi.paymentMethod.createSubscription.failedMsg");
			return HeringcheckoutaddonControllerConstants.Views.Pages.MultiStepCheckout.AddPaymentMethodPage;
		}

		model.addAttribute("paymentId", newPaymentSubscription.getId());

		//setting card number before mask for later use
		sessionService.setAttribute("temporaryCardNumber", paymentInfoData.getCardNumber());
		sessionService.setAttribute("temporaryCv2Number", paymentInfoData.getCv2Number());
		return REDIRECT_URL_SUMMARY;
	}

	/**
	 * @param cardNumber
	 * @return card brand
	 */
	protected String getCardBrand(String cardNumber)
	{
		if (cardNumber.matches("^4[0-9]{6,}$"))
		{
			return "VISA";
		}

		if (cardNumber.matches("^3[47][0-9]{5,}$"))
		{
			return "AMEX";
		}

		if (cardNumber.matches("^5[1-5][0-9]{5,}$"))
		{
			return "MASTER";
		}

		if (cardNumber.matches("^3(?:0[0-5]|[68][0-9])[0-9]{4,}$"))
		{
			return "DINERS";
		}
		
//		if(cardNumber.matches("^(636368|438935|504175|451416|636297)(?:[0-9]{10})?|^(5066|5067|4576|4011)(?:[0-9]{12})?"))
//		{
//			return "ELO";
//		}
//		
//		if(cardNumber.matches("^(38|60)(([0-9]{13})|([0-9]{16})|([0-9]{19})?)"))
//		{
//			return "HIPERCARD";
//		}

		return "VISA";
	}

	/**
	 * @param model
	 * @param paymentDetailsForm
	 * @param bindingResult
	 * @return redirectURL
	 */
	private String doSaveVoucherPaymentMethod(final Model model, final HeringPaymentDetailsForm paymentDetailsForm,
			final BindingResult bindingResult) throws CMSItemNotFoundException
	{
		getHeringPaymentDetailsValidator().validate(paymentDetailsForm, bindingResult, model);

		setupAddPaymentPage(model, "Voucher");

		final String voucher = paymentDetailsForm.getVoucher();
		final String removeVoucher = paymentDetailsForm.getRemoveVoucher();
		final HeringAddressForm formAddress = paymentDetailsForm.getBillingAddress();
		final String firstName = formAddress.getFirstName();
		model.addAttribute("voucher", voucher);
		final VoucherPaymentInfoData paymentInfoData = new VoucherPaymentInfoData();

		paymentInfoData.setVoucherCode(voucher);
		if (!Strings.isNullOrEmpty(voucher) && !redeemVoucher(voucher, model, firstName, paymentInfoData))
		{

			return HeringcheckoutaddonControllerConstants.Views.Pages.MultiStepCheckout.AddPaymentMethodPage;

		}
		else if (removeVoucher != null && removeVoucher.equalsIgnoreCase(HeringcheckoutaddonWebConstants.REMOVE_VOUCHER))
		{
			releaseVoucher();
			return REDIRECT_URL_ADD_PAYMENT_METHOD;
		}

		final String paymentMode = paymentDetailsForm.getPaymentMode();
		final CartData cartData = getCheckoutFacade().getCheckoutCart();

		final PaymentModeData mode = new PaymentModeData();
		mode.setCode(paymentMode);
//		cartData.setPaymentMode(mode);
		model.addAttribute("cartData", cartData);

		if (bindingResult.hasErrors())
		{
			return REDIRECT_URL_ADD_PAYMENT_METHOD;
		}

		final AddressData addressData;

		if (Boolean.TRUE.equals(paymentDetailsForm.getNewBillingAddress()))
		{
			addressData = getCheckoutFacade().getCheckoutCart().getDeliveryAddress();
			if (addressData == null)
			{
				GlobalMessages.addErrorMessage(model,
						"checkout.multi.paymentMethod.createSubscription.billingAddress.noneSelectedMsg");
				return HeringcheckoutaddonControllerConstants.Views.Pages.MultiStepCheckout.AddPaymentMethodPage;
			}

			addressData.setBillingAddress(true); // mark this as billing address
		}
		else
		{

			final HeringAddressForm addressForm = paymentDetailsForm.getBillingAddress();
			addressData = new AddressData();
			if (addressForm != null)
			{
				addressData.setId(addressForm.getAddressId());
				addressData.setFirstName(addressForm.getFirstName());
				addressData.setLastName(addressForm.getLastName());
				addressData.setLine1(addressForm.getLine1());
				addressData.setTown(addressForm.getTownCity());
				addressData.setPostalCode(addressForm.getPostcode());
				addressData.setDistrict(addressForm.getNeighborhood());

				if (StringUtils.isNotBlank(addressForm.getPhone()))
				{
					addressData.setPhone(addressForm.getPhone().substring(2));
					if (addressForm.getPhone().length() > 3)
					{
						addressData.setDddPhone(addressForm.getPhone().substring(0, 2));
					}
				}

				if (StringUtils.isNotBlank(addressForm.getCelPhone()))
				{
					addressData.setCelPhone(addressForm.getCelPhone().substring(2));

					if (addressForm.getCelPhone().length() > 3)
					{
						addressData.setDddCelPhone(addressForm.getCelPhone().substring(0, 2));
					}
				}

				addressData.setNumber(addressForm.getNumber());
				addressData.setComplement(addressForm.getComplement());
				addressData.setReference(addressForm.getReference());
				addressData.setType(TipoDeEndereco.valueOf(addressForm.getAddressType()));
				addressData.setCountry(getI18NFacade().getCountryForIsocode(addressForm.getCountryIso()));
				addressData.setRegion(getI18NFacade().getRegion(addressForm.getCountryIso(), addressForm.getRegionIso()));

				addressData.setShippingAddress(Boolean.TRUE.equals(addressForm.getShippingAddress()));
				addressData.setBillingAddress(Boolean.TRUE.equals(addressForm.getBillingAddress()));
			}
		}

		getAddressVerificationFacade().verifyAddressData(addressData);
		paymentInfoData.setBillingAddress(addressData);
		cartData.setVoucherPaymentInfo(paymentInfoData);
		getCheckoutFacade().savePaymentInfoIntoCart(cartData);

		if (voucherEqualBuy(cartData))
		{
			return REDIRECT_URL_ADD_PAYMENT_METHOD;
		}

		return REDIRECT_URL_SUMMARY;
	}

	/**
	 * @param model
	 * @param paymentDetailsForm
	 * @param bindingResult
	 * @return redirectURL
	 */
	private String doSaveBoletoPaymentMethod(final Model model, final HeringPaymentDetailsForm paymentDetailsForm,
			final BindingResult bindingResult) throws CMSItemNotFoundException
	{
		getHeringPaymentDetailsValidator().validate(paymentDetailsForm, bindingResult, model);
		setupAddPaymentPage(model, "Boleto");

		String paymentMode = paymentDetailsForm.getPaymentMode();
		final CartData cartData = getCheckoutFacade().getCheckoutCart();

		if (bindingResult.hasErrors())
		{
			model.addAttribute("checked", Boolean.FALSE);
			//GlobalMessages.addErrorMessage(model, "checkout.error.paymentethod.formentry.invalid");
			return HeringcheckoutaddonControllerConstants.Views.Pages.MultiStepCheckout.AddPaymentMethodPage;
		}

		final PaymentModeData mode = new PaymentModeData();
		mode.setCode(paymentMode);
//		cartData.setPaymentMode(mode);

		final BoletoPaymentInfoData paymentInfoData = new BoletoPaymentInfoData();
		paymentInfoData.setDaysToAddInBoletoExpirationDate("3");
		paymentInfoData.setTransactionReference(cartData.getCode());
		paymentInfoData.setNossoNumero(cartData.getCode() + cartData.getCode());

		paymentInfoData.setCpf(getUser().getCpfcnpj());
		paymentInfoData.setSaved(new Boolean(false));

		model.addAttribute("cartData", cartData);

		final AddressData addressData;
		if (Boolean.TRUE.equals(paymentDetailsForm.getNewBillingAddress()))
		{
			addressData = getCheckoutFacade().getCheckoutCart().getDeliveryAddress();
			if (addressData == null)
			{
				GlobalMessages.addErrorMessage(model,
						"checkout.multi.paymentMethod.createSubscription.billingAddress.noneSelectedMsg");
				return HeringcheckoutaddonControllerConstants.Views.Pages.MultiStepCheckout.AddPaymentMethodPage;
			}

			addressData.setBillingAddress(true); // mark this as billing address
		}
		else
		{
			final HeringAddressForm addressForm = paymentDetailsForm.getBillingAddress();
			addressData = new AddressData();
			if (addressForm != null)
			{
				addressData.setId(addressForm.getAddressId());
				addressData.setFirstName(addressForm.getFirstName());
				addressData.setLastName(addressForm.getLastName());
				addressData.setLine1(addressForm.getLine1());
				addressData.setTown(addressForm.getTownCity());
				addressData.setPostalCode(addressForm.getPostcode());
				addressData.setDistrict(addressForm.getNeighborhood());

				if (StringUtils.isNotBlank(addressForm.getPhone()))
				{
					addressData.setPhone(addressForm.getPhone().substring(2));
					if (addressForm.getPhone().length() > 3)
					{
						addressData.setDddPhone(addressForm.getPhone().substring(0, 2));
					}
				}

				if (StringUtils.isNotBlank(addressForm.getCelPhone()))
				{
					addressData.setCelPhone(addressForm.getCelPhone().substring(2));

					if (addressForm.getCelPhone().length() > 3)
					{
						addressData.setDddCelPhone(addressForm.getCelPhone().substring(0, 2));
					}
				}

				addressData.setNumber(addressForm.getNumber());
				addressData.setComplement(addressForm.getComplement());
				addressData.setReference(addressForm.getReference());
				addressData.setType(TipoDeEndereco.valueOf(addressForm.getAddressType()));
				addressData.setCountry(getI18NFacade().getCountryForIsocode(addressForm.getCountryIso()));
				addressData.setRegion(getI18NFacade().getRegion(addressForm.getCountryIso(), addressForm.getRegionIso()));

				addressData.setShippingAddress(Boolean.TRUE.equals(addressForm.getShippingAddress()));
				addressData.setBillingAddress(Boolean.TRUE.equals(addressForm.getBillingAddress()));
			}
		}

		getAddressVerificationFacade().verifyAddressData(addressData);
		paymentInfoData.setBillingAddress(addressData);
		cartData.setCustomPaymentInfo(paymentInfoData);
		model.addAttribute("paymentId", "" + System.currentTimeMillis());
		getCheckoutFacade().setCustomPaymentInfo(cartData);
		return REDIRECT_URL_SUMMARY;
	}

	@Override
	@RequestMapping(value = "/summary", method = RequestMethod.GET)
	@RequireHardLogIn
	public String checkoutSummary(final Model model, final RedirectAttributes redirectAttributes) throws CMSItemNotFoundException,
			CommerceCartModificationException
	{
		if (!hasValidCart())
		{
			LOG.info("Missing, empty or unsupported cart");
			return REDIRECT_URL_CART;
		}

		if (hasNoDeliveryAddress())
		{
			GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.INFO_MESSAGES_HOLDER,
					"checkout.multi.deliveryAddress.notprovided");
			return REDIRECT_URL_ADD_DELIVERY_ADDRESS;
		}

		if (hasEmptyMandatoryData(redirectAttributes))
		{
			return REDIRECT_URL_ADD_DELIVERY_ADDRESS;
		}

		if (hasNoDeliveryMode())
		{
			GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.INFO_MESSAGES_HOLDER,
					"checkout.multi.deliveryMethod.notprovided");
			return REDIRECT_URL_CHOOSE_DELIVERY_METHOD;
		}
		if (hasNoPaymentInfo())
		{
			GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.INFO_MESSAGES_HOLDER,
					"checkout.multi.paymentDetails.notprovided");
			return REDIRECT_URL_ADD_PAYMENT_METHOD;
		}

		final CartData cartData = getCheckoutFacade().getCheckoutCart();

		if (!getCheckoutFacade().hasShippingItems())
		{
			cartData.setDeliveryAddress(null);
		}
		if (!getCheckoutFacade().hasPickUpItems() && cartData.getDeliveryMode().getCode().equals("pickup"))
		{
			return REDIRECT_URL_CHOOSE_DELIVERY_METHOD;
		}

		if (cartData.getEntries() != null && !cartData.getEntries().isEmpty())
		{
			for (final OrderEntryData entry : cartData.getEntries())
			{
				final String productCode = entry.getProduct().getCode();
				final ProductData product = getProductFacade().getProductForCodeAndOptions(productCode,
						Arrays.asList(ProductOption.BASIC, ProductOption.PRICE));
				entry.setProduct(product);
			}
		}
		model.addAttribute("cartData", cartData);
		model.addAttribute("allItems", cartData.getEntries());
		model.addAttribute("deliveryAddress", cartData.getDeliveryAddress());
		model.addAttribute("deliveryMode", cartData.getDeliveryMode());
		model.addAttribute("paymentInfo", cartData.getPaymentInfo());
		model.addAttribute("customPaymentInfo", cartData.getCustomPaymentInfo());
		model.addAttribute("voucherPaymentInfo", cartData.getVoucherPaymentInfo());

		// Only request the security code if the SubscriptionPciOption is set to Default.
		final boolean requestSecurityCode = false;
		model.addAttribute("requestSecurityCode", Boolean.valueOf(requestSecurityCode));

		model.addAttribute(new PlaceOrderForm());

		storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
		model.addAttribute(WebConstants.BREADCRUMBS_KEY,
				getResourceBreadcrumbBuilder().getBreadcrumbs("checkout.multi.summary.breadcrumb"));
		model.addAttribute("metaRobots", "no-index,no-follow");
		return HeringcheckoutaddonControllerConstants.Views.Pages.MultiStepCheckout.CheckoutSummaryPage;
	}

	@Override
	@RequestMapping(value = "/placeOrder")
	@RequireHardLogIn
	public String placeOrder(	@ModelAttribute("placeOrderForm") final PlaceOrderForm placeOrderForm, 
										final Model model,
										final HttpServletRequest request, 
										final RedirectAttributes redirectModel)
	throws CMSItemNotFoundException, InvalidCartException, CommerceCartModificationException
	{
		if (validateOrderForm(redirectModel, placeOrderForm, model))
		{
			return checkoutSummary(model, redirectModel);
		}

		//Validate the cart
		if (validateCart(redirectModel))
		{
			// Invalid cart. Bounce back to the cart page.
			return REDIRECT_PREFIX + "/cart";
		}

		// authorize, if failure occurs don't allow to place the order
		boolean isPaymentAuthorized = false;

		try
		{
			final CartData cartData = getCheckoutFacade().getCheckoutCart();			
			// se tem custom, é boleto ou debito, ou voucher
			if (cartData.getCustomPaymentInfo() != null)
			{
				if (cartData.getCustomPaymentInfo() 
						instanceof BoletoPaymentInfoData)
				{
					isPaymentAuthorized = getCheckoutFacade().authorizeBoleto();
				}
				else if (cartData.getCustomPaymentInfo() 
						instanceof HeringDebitPaymentInfoData)
				{
					//a finalização da order vai ser feita quando retornar da cielo
					if(getCheckoutFacade().authorizeDebitPayment()){
						return redirectToCielo(model);
					}
				}
			}
			else if (cartData.getPaymentInfo() != null)
			{
				final PaymentModeData paymentModeData = new PaymentModeData();
				paymentModeData.setCode("CreditCard");
				getCheckoutFacade().setPaymentMode(paymentModeData);

				final CCPaymentInfoData ccPaymentInfo = cartData.getPaymentInfo();
				isPaymentAuthorized = getCheckoutFacade().authorizePayment();
				LOG.info(ccPaymentInfo.getId());
			} 
			else if (cartData.getVoucherPaymentInfo() != null )
			{
				if (!voucherEqualBuy(cartData))
				{
					isPaymentAuthorized = true;
					final OrderData orderData = getCheckoutFacade().placeOrder();
					return redirectToOrderConfirmationPage(orderData);
				}
			}
		}
		catch (final AdapterException ae)
		{
			// handle a case where a wrong paymentProvider configurations on the store see getCommerceCheckoutService().getPaymentProvider()
			LOG.error(ae.getMessage(), ae);
		}
		if (!isPaymentAuthorized)
		{
			LOG.error("Payment has not been authorized!");
			GlobalMessages.addErrorMessage(model, "checkout.error.payment.not.accepted");
			return REDIRECT_URL_ADD_PAYMENT_METHOD;
			//			return checkoutSummary(model, redirectModel);
		}

		final OrderData orderData;
		try
		{
			orderData = getCheckoutFacade().placeOrder();
		}
		catch (final Exception e)
		{
			LOG.error("Failed to place Order", e);
			GlobalMessages.addErrorMessage(model, "checkout.placeOrder.failed");
			return checkoutSummary(model, redirectModel);
		}

		return redirectToOrderConfirmationPage(orderData);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractCheckoutController#hasNoPaymentInfo()
	 */
	@Override
	protected boolean hasNoPaymentInfo()
	{
		final CartData cartData = getCheckoutFacade().getCheckoutCart();
		if (cartData != null && cartData.getPaymentInfo() != null)
		{
			return false;
		}
		else if (cartData != null && cartData.getCustomPaymentInfo() != null)
		{
			return false;
		}
		else if (cartData != null && cartData.getVoucherPaymentInfo() != null)
		{
			return false;

		}
		else
		{
			return true;
		}
		//		if (cartData != null && cartData.getPaymentMode() != null
		//				&& cartData.getPaymentMode().equals(HeringcheckoutaddonWebConstants.BOLETO))
		//		{
		//			return (cartData.getCustomPaymentInfo() == null);
		//		}
		//		else
		//		{
		//			return (cartData.getPaymentInfo() == null);
		//		}
	}

	/**
	 * @return the sessionService
	 */
	@Override
	public SessionService getSessionService()
	{
		return sessionService;
	}

	/**
	 * @param sessionService
	 *           the sessionService to set
	 */
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	protected void setupAddPaymentPage(final Model model, final String payMentmodeCode) throws CMSItemNotFoundException
	{
		final List<PaymentModeData> paymentModes = paymentModeFacade.getAllPaymentModes();
		if (CollectionUtils.isNotEmpty(paymentModes))
		{
			model.addAttribute("paymentModes", paymentModes);
			if (StringUtils.isBlank(payMentmodeCode))
			{
				final CustomPaymentInfoData customPaymentInfo = getCheckoutFacade().getCheckoutCart().getCustomPaymentInfo();
			}
			model.addAttribute("selectedPaymentMode", payMentmodeCode);
			model.addAttribute("metaRobots", "no-index,no-follow");
			model.addAttribute("hasNoPaymentInfo", Boolean.valueOf(hasNoPaymentInfo()));
			this.prepareDataForPage(model);
			model.addAttribute(WebConstants.BREADCRUMBS_KEY,
					getResourceBreadcrumbBuilder().getBreadcrumbs("checkout.multi.paymentMethod.breadcrumb"));
			final ContentPageModel contentPage = getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL);
			storeCmsPageInModel(model, contentPage);
			setUpMetaDataForContentPage(model, contentPage);
		}
	}

	/*
	 * !@Override
	 * 
	 * @RequestMapping(value = "/select-delivery-address", method = RequestMethod.GET)
	 * 
	 * @RequireHardLogIn public @ResponseBody String doSelectDeliveryAddress( final String selectedAddressCode) { if
	 * (!hasValidCart()) { LOG.info("Missing, empty or unsupported cart"); return "Missing, empty or unsupported cart"; }
	 * if (!getCheckoutFacade().hasShippingItems()) { return "Missing or empty shipping Items"; } if
	 * (StringUtils.isNotBlank(selectedAddressCode)) { final AddressData selectedAddressData = getCheckoutFacade()
	 * .getDeliveryAddressForCode(selectedAddressCode); if (selectedAddressData != null) { final AddressData
	 * cartCheckoutDeliveryAddress = getCheckoutFacade() .getCheckoutCart().getDeliveryAddress(); if
	 * (isAddressIdChanged(cartCheckoutDeliveryAddress, selectedAddressData)) {
	 * getCheckoutFacade().setDeliveryAddress(selectedAddressData); if (cartCheckoutDeliveryAddress != null &&
	 * !cartCheckoutDeliveryAddress .isVisibleInAddressBook()) { /* temporary address should be removed *
	 * getUserFacade().removeAddress( cartCheckoutDeliveryAddress); } } return "success"; } } return "error"; }
	 */
	@Override
	@RequestMapping(value = "/select-delivery-method", method = RequestMethod.GET)
	@RequireHardLogIn
	public String doSelectDeliveryMode(@RequestParam("delivery_method") final String selectedDeliveryMethod)
	{
		if (!hasValidCart())
		{
			LOG.info("Missing, empty or unsupported cart");
			return REDIRECT_URL_CART;
		}

		if (StringUtils.isNotEmpty(selectedDeliveryMethod))
		{
			getCheckoutFacade().setDeliveryMode(selectedDeliveryMethod);
		}

		if (getCheckoutFacade().hasPickUpItems())
		{
			return REDIRECT_URL_CHOOSE_DELIVERY_LOCATION;
		}
		else
		{
			return REDIRECT_URL_ADD_PAYMENT_METHOD;
		}
	}

	@Override
	@ModelAttribute("checkoutSteps")
	public List<CheckoutSteps> addCheckoutStepsToModel(final HttpServletRequest request)
	{
		final List<CheckoutSteps> checkoutSteps = new ArrayList<CheckoutSteps>();
		checkoutSteps.add(new CheckoutSteps("deliveryAddress", "/checkout/multi/add-address"));
		checkoutSteps.add(new CheckoutSteps("deliveryMethod", "/checkout/multi/choose-delivery-method"));
		checkoutSteps.add(new CheckoutSteps("paymentMethod", "/checkout/multi/custom-add-payment-method"));
		checkoutSteps.add(new CheckoutSteps("confirmOrder", "/checkout/multi/summary"));

		return checkoutSteps;
	}

	protected boolean validateOrderForm(final RedirectAttributes redirectModel, final PlaceOrderForm placeOrderForm,
			final Model model)
	{
		//final String securityCode = placeOrderForm.getSecurityCode();
		boolean invalid = false;

		if (hasNoDeliveryAddress())
		{
			GlobalMessages.addErrorMessage(model, "checkout.deliveryAddress.notSelected");
			invalid = true;
		}

		if (hasNoDeliveryMode())
		{
			GlobalMessages.addErrorMessage(model, "checkout.deliveryMethod.notSelected");
			invalid = true;
		}

		if (hasNoPaymentInfo())
		{
			GlobalMessages.addErrorMessage(model, "checkout.paymentMethod.notSelected");
			invalid = true;
		}

		if (hasEmptyMandatoryData(redirectModel))
		{
			invalid = true;
		}

		if (!placeOrderForm.isTermsCheck())
		{
			GlobalMessages.addErrorMessage(model, "checkout.error.terms.not.accepted");
			invalid = true;
			return invalid;
		}

		final CartData cartData = getCheckoutFacade().getCheckoutCart();

		if (!getCheckoutFacade().containsTaxValues())
		{
			LOG.error(String
					.format(
							"Cart %s does not have any tax values, which means the tax cacluation was not properly done, placement of order can't continue",
							cartData.getCode()));
			GlobalMessages.addErrorMessage(model, "checkout.error.tax.missing");
			invalid = true;
		}

		if (!Boolean.valueOf(cartData.isCalculated()).booleanValue())
		{
			LOG.error(String.format("Cart %s has a calculated flag of FALSE, placement of order can't continue", cartData.getCode()));
			GlobalMessages.addErrorMessage(model, "checkout.error.cart.notcalculated");
			invalid = true;
		}

		return invalid;
	}

	/**
	 * @param model2
	 * @return
	 */
	private boolean hasEmptyMandatoryData(final RedirectAttributes redir)
	{
		final AddressData deliveryAddress = getCheckoutFacade().getCheckoutCart().getDeliveryAddress();

		if (deliveryAddress == null)
		{
			return true;
		}

		boolean invalid = false;

		if (StringUtils.isBlank(deliveryAddress.getFirstName()))
		{
			GlobalMessages.addFlashMessage(redir, GlobalMessages.ERROR_MESSAGES_HOLDER, "address.firstName.invalid");
			invalid = true;
		}

		if (StringUtils.isBlank(deliveryAddress.getLastName()))
		{
			GlobalMessages.addFlashMessage(redir, GlobalMessages.ERROR_MESSAGES_HOLDER, "address.lastName.invalid");
			invalid = true;
		}

		if (StringUtils.isBlank(deliveryAddress.getPhone()))
		{
			GlobalMessages.addFlashMessage(redir, GlobalMessages.ERROR_MESSAGES_HOLDER, "address.phone1.invalid");
			invalid = true;
		}

		if (deliveryAddress.getType() == null)
		{
			GlobalMessages.addFlashMessage(redir, GlobalMessages.ERROR_MESSAGES_HOLDER, "address.addressType.invalid");
			invalid = true;
		}

		if (StringUtils.isBlank(deliveryAddress.getDistrict()))
		{
			GlobalMessages.addFlashMessage(redir, GlobalMessages.ERROR_MESSAGES_HOLDER, "address.district.invalid");
			invalid = true;
		}

		if (deliveryAddress.getRegion() == null)
		{
			GlobalMessages.addFlashMessage(redir, GlobalMessages.ERROR_MESSAGES_HOLDER, "address.regionIso.invalid");
			invalid = true;
		}
		/**
		if (StringUtils.isBlank(deliveryAddress.getReceiver()))
		{
			GlobalMessages.addFlashMessage(redir, GlobalMessages.ERROR_MESSAGES_HOLDER, "address.receiver.invalid");
			invalid = true;
		}
		*/
		if (StringUtils.isBlank(deliveryAddress.getTown()))
		{
			GlobalMessages.addFlashMessage(redir, GlobalMessages.ERROR_MESSAGES_HOLDER, "address.townCity.invalid");
			invalid = true;
		}

		if (StringUtils.isBlank(deliveryAddress.getPostalCode()))
		{
			GlobalMessages.addFlashMessage(redir, GlobalMessages.ERROR_MESSAGES_HOLDER, "address.postcode.invalid");
			invalid = true;
		}

		final CustomerData customer = getCheckoutFacade().getUserForCheckout();
		if (StringUtils.isBlank(customer.getCpfcnpj()))
		{
			GlobalMessages.addFlashMessage(redir, GlobalMessages.ERROR_MESSAGES_HOLDER, "register.cpfcnpj.invalid");
			invalid = true;
		}

		return invalid;
	}

	@RequestMapping(value = "/get-by-zipcode", method = RequestMethod.GET)
	public @ResponseBody String getAddressByZipcode(@RequestParam("zipcode") final String zipcode)
	{

		String serviceUrl = "";
		//String codification = "ISO-8859-1";
		String result = "";

		if (StringUtils.isBlank(zipcode))
		{
			return null;
		}

		// End of service 2014-10-27
      // serviceUrl = "https://www.hering.com.br/smartcheckout/ajax/getAddressByPostcode?zipcode="
      //          + zipcode;
      // In service by 2014-10-28. Accepts "8" CEP format
      // serviceUrl = "http://cep.correiocontrol.com.br/"+zipcode+".json";
      // In service by 2014-10-28. Accpets "5-3" ou "8" CEP format
      // serviceUrl = "viacep.com.br/ws/"+zipcode+"/json/";
      // In service by 2014-10-28. Accepts "8" CEP format. returns JSON or XML
      serviceUrl = "http://api.postmon.com.br/v1/cep/"+zipcode;//+"?format=xml";s

		try
		{

			final URL url = new URL(serviceUrl);

			final InputStream is = url.openStream();
			final InputStreamReader isr = new InputStreamReader(is);
			final BufferedReader br = new BufferedReader(isr);

			result = br.readLine();

		}
		catch (final Exception e)
		{
			LOG.info("Erro ao buscar endereço por cep.", e);
		}
		return result;
	}

	@RequestMapping(value = "/add-address", method = RequestMethod.GET)
	@RequireHardLogIn
	@Override
	public String addDeliveryAddress(final Model model) throws CMSItemNotFoundException
	{
		if (!hasValidCart())
		{
			LOG.info("Missing, empty or unsupported cart");
			return REDIRECT_URL_CART;
		}

		if (!getCheckoutFacade().hasShippingItems())
		{
			return REDIRECT_URL_CHOOSE_DELIVERY_LOCATION;
		}

		getCheckoutFacade().setDeliveryAddressIfAvailable();

		final CartData cartData = getCheckoutFacade().getCheckoutCart();
		model.addAttribute("cartData", cartData);
		model.addAttribute("deliveryAddresses", getDeliveryAddresses(cartData.getDeliveryAddress()));
		model.addAttribute("noAddress", Boolean.valueOf(hasNoDeliveryAddress()));
		model.addAttribute("heringAddressForm", getPreparedAddressForm());
		model.addAttribute("showSaveToAddressBook", Boolean.TRUE);
		this.prepareDataForPage(model);
		storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
		model.addAttribute(WebConstants.BREADCRUMBS_KEY,
				getResourceBreadcrumbBuilder().getBreadcrumbs("checkout.multi.deliveryAddress.breadcrumb"));
		model.addAttribute("metaRobots", "no-index,no-follow");
		return HeringcheckoutaddonControllerConstants.Views.Pages.MultiStepCheckout.AddEditDeliveryAddressPage;
	}
	
	@RequestMapping(value = "/add-address", method = RequestMethod.POST)
	@RequireHardLogIn
	public String addDeliveryAddress(final HeringAddressForm addressForm, final BindingResult bindingResult, final Model model,
			final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{		
		getAddressValidator().validate(addressForm, bindingResult, model);

		final CartData cartData = getCheckoutFacade().getCheckoutCart();
		model.addAttribute("cartData", cartData);
		model.addAttribute("deliveryAddresses", getDeliveryAddresses(cartData.getDeliveryAddress()));
		this.prepareDataForPage(model);
		if (StringUtils.isNotBlank(addressForm.getCountryIso()))
		{
			model.addAttribute("country", addressForm.getCountryIso());
		}

		model.addAttribute("noAddress", Boolean.valueOf(hasNoDeliveryAddress()));
		//Sergio Prado junior 05-06-2014
		model.addAttribute("showSaveToAddressBook", Boolean.TRUE);

		if (bindingResult.hasErrors())
		{
			//GlobalMessages.addErrorMessage(model, "address.error.formentry.invalid");
			storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
			setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
			return HeringcheckoutaddonControllerConstants.Views.Pages.MultiStepCheckout.AddEditDeliveryAddressPage;
		}

		final AddressData addressData = new AddressData();

		addressData.setRegion(getI18NFacade().getRegion(addressForm.getCountryIso(), addressForm.getRegionIso()));
		addressData.setId(addressForm.getAddressId());
		addressData.setLine1(addressForm.getLine1());
		addressData.setTown(addressForm.getTownCity());
		addressData.setPostalCode(addressForm.getPostcode());
		if (StringUtils.isNotBlank(addressForm.getPhone()))
		{
			addressData.setPhone(addressForm.getPhone().substring(2));
			if (addressForm.getPhone().length() > 3)
			{
				addressData.setDddPhone(addressForm.getPhone().substring(0, 2));
			}
		}

		if (StringUtils.isNotBlank(addressForm.getCelPhone()))
		{
			addressData.setCelPhone(addressForm.getCelPhone().substring(2));

			if (addressForm.getCelPhone().length() > 3)
			{
				addressData.setDddCelPhone(addressForm.getCelPhone().substring(0, 2));
			}
		}

		addressData.setNumber(addressForm.getNumber());
		addressData.setComplement(addressForm.getComplement());
		addressData.setReference(addressForm.getReference());
		addressData.setType(TipoDeEndereco.valueOf(addressForm.getAddressType()));
		addressData.setDistrict(addressForm.getNeighborhood());

		addressData.setBillingAddress(false);
		addressData.setShippingAddress(true);

		addressData.setReceiver(addressForm.getReceiver());
		addressData.setFirstName(addressForm.getFirstName());
		addressData.setLastName(addressForm.getLastName());

		addressData.setCountry(getI18NFacade().getCountryForIsocode(addressForm.getCountryIso()));

		//Sergio Prado Junior
		if (addressForm.getSaveInAddressBook() != null)
		//if(true)
		{
			//	addressData.setVisibleInAddressBook(true);
			addressData.setVisibleInAddressBook(addressForm.getSaveInAddressBook().booleanValue());
			if (addressForm.getSaveInAddressBook().booleanValue() && getUserFacade().isAddressBookEmpty())
			//if (getUserFacade().isAddressBookEmpty())
			{
				addressData.setDefaultAddress(true);
			}
		}
		else if (getCheckoutCustomerStrategy().isAnonymousCheckout())
		{
			addressData.setDefaultAddress(true);
			addressData.setVisibleInAddressBook(true);
		}

		// Verify the address data.
		final AddressVerificationResult<AddressVerificationDecision> verificationResult = getAddressVerificationFacade()
				.verifyAddressData(addressData);
		final boolean addressRequiresReview = getAddressVerificationResultHandler().handleResult(verificationResult, addressData,
				model, redirectModel, bindingResult, getAddressVerificationFacade().isCustomerAllowedToIgnoreAddressSuggestions(),
				"checkout.multi.address.updated");

		if (addressRequiresReview)
		{
			storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
			setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
			return HeringcheckoutaddonControllerConstants.Views.Pages.MultiStepCheckout.AddEditDeliveryAddressPage;
		}

		getHeringUserFacade().addAddress(addressData);


		// Set the new address as the selected checkout delivery address
		getCheckoutFacade().setDeliveryAddress(addressData);

		//Sergio Prado Junior
		//		if (previousSelectedAddress != null && !previousSelectedAddress.isVisibleInAddressBook())
		//		{ // temporary address should be removed
		//			getUserFacade().removeAddress(previousSelectedAddress);
		//		}

		// Set the new address as the selected checkout delivery address

		return REDIRECT_URL_CHOOSE_DELIVERY_METHOD;
	}

	@RequestMapping(value = "/remove-address", 
			method = { RequestMethod.GET, RequestMethod.POST })
	@RequireHardLogIn
	public String removeAddress(
			@RequestParam("addressCode") final String addressCode, 
			final RedirectAttributes redirectModel,
			final Model model) throws CMSItemNotFoundException
	{
		final AddressData addressData = new AddressData();
		addressData.setId(addressCode);
		
		//remove the address from the user list of addresses
		getUserFacade().removeAddress(addressData);
		getCheckoutFacade().removeDeliveryAddress(addressData);
		
		GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, "account.confirmation.address.removed");
		storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
		model.addAttribute("heringAddressForm", getPreparedAddressForm());
		return REDIRECT_URL_ADD_DELIVERY_ADDRESS;
	}

	@Override
	@RequestMapping(value = "/edit-delivery-address", method = RequestMethod.GET)
	@RequireHardLogIn
	public String editDeliveryAddress(@RequestParam("editAddressCode") final String editAddressCode, final Model model)
			throws CMSItemNotFoundException
	{
		if (!hasValidCart())
		{
			LOG.info("Missing, empty or unsupported cart");
			return REDIRECT_URL_CART;
		}

		if (!getCheckoutFacade().hasShippingItems())
		{
			return REDIRECT_URL_CHOOSE_DELIVERY_LOCATION;
		}

		AddressData addressData = null;
		if (StringUtils.isNotEmpty(editAddressCode))
		{
			addressData = getCheckoutFacade().getDeliveryAddressForCode(editAddressCode);
		}

		final HeringAddressForm addressForm = new HeringAddressForm();
		final boolean hasAddressData = addressData != null;
		if (hasAddressData)
		{
			addressForm.setAddressId(addressData.getId());
			addressForm.setFirstName(addressData.getFirstName());
			addressForm.setLastName(addressData.getLastName());
			addressForm.setLine1(addressData.getLine1());
			addressForm.setTownCity(addressData.getTown());
			addressForm.setPostcode(addressData.getPostalCode());
			addressForm.setCountryIso(addressData.getCountry().getIsocode());
			addressForm.setSaveInAddressBook(Boolean.valueOf(addressData.isVisibleInAddressBook()));
			addressForm.setShippingAddress(Boolean.valueOf(addressData.isShippingAddress()));
			addressForm.setBillingAddress(Boolean.valueOf(addressData.isBillingAddress()));
			if (addressData.getRegion() != null)
			{
				addressForm.setRegionIso(addressData.getRegion().getIsocode());
			}
			addressForm.setNumber(addressData.getNumber());
			addressForm.setComplement(addressData.getComplement());
			addressForm.setReceiver(addressData.getReceiver());
			addressForm.setReference(addressData.getReference());
			if (addressData.getType() != null)
			{
				addressForm.setAddressType(addressData.getType().getCode());
			}
			addressForm.setNeighborhood(addressData.getDistrict());
			addressForm.setPhone(addressData.getDddPhone() + addressData.getPhone());
			addressForm.setCelPhone(addressData.getDddCelPhone() + addressData.getCelPhone());
		}

		final CartData cartData = getCheckoutFacade().getCheckoutCart();
		model.addAttribute("cartData", cartData);
		model.addAttribute("deliveryAddresses", getDeliveryAddresses(cartData.getDeliveryAddress()));
		if (StringUtils.isNotBlank(addressForm.getCountryIso()))
		{
			model.addAttribute("regions", getI18NFacade().getRegionsForCountryIso(addressForm.getCountryIso()));
			model.addAttribute("country", addressForm.getCountryIso());
		}
		model.addAttribute("noAddress", Boolean.valueOf(hasNoDeliveryAddress()));
		model.addAttribute("edit", Boolean.valueOf(hasAddressData));
		model.addAttribute("heringAddressForm", addressForm);
		if (addressData != null)
		{
			model.addAttribute("showSaveToAddressBook", Boolean.valueOf(!addressData.isVisibleInAddressBook()));
		}
		storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
		model.addAttribute(WebConstants.BREADCRUMBS_KEY,
				getResourceBreadcrumbBuilder().getBreadcrumbs("checkout.multi.deliveryAddress.breadcrumb"));
		model.addAttribute("metaRobots", "no-index,no-follow");
		model.addAttribute("enableEditAddressAnchor", Boolean.TRUE);
		return HeringcheckoutaddonControllerConstants.Views.Pages.MultiStepCheckout.AddEditDeliveryAddressPage;
	}

	@RequestMapping(value = "/edit-delivery-address", 
			params = { "addressType" }, method = RequestMethod.POST)
	public String editDeliveryAddress(@RequestParam final String addressType, final HeringAddressForm addressForm,
			final BindingResult bindingResult, final Model model, final RedirectAttributes redirectModel)
			throws CMSItemNotFoundException
	{
		getAddressValidator().validate(addressForm, bindingResult, model);
		if (StringUtils.isNotBlank(addressForm.getCountryIso()))
		{
			model.addAttribute("regions", getI18NFacade().getRegionsForCountryIso(addressForm.getCountryIso()));
			model.addAttribute("country", addressForm.getCountryIso());
		}
		model.addAttribute("noAddress", Boolean.valueOf(hasNoDeliveryAddress()));

		if (bindingResult.hasErrors())
		{
			GlobalMessages.addErrorMessage(model, "address.error.formentry.invalid");
			storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
			setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
			return HeringcheckoutaddonControllerConstants.Views.Pages.MultiStepCheckout.AddEditDeliveryAddressPage;
		}

		final AddressData newAddress = new AddressData();
		newAddress.setId(addressForm.getAddressId());
		newAddress.setFirstName(addressForm.getFirstName());
		newAddress.setLastName(addressForm.getLastName());
		newAddress.setLine1(addressForm.getLine1());
		newAddress.setTown(addressForm.getTownCity());
		newAddress.setPostalCode(addressForm.getPostcode());
		newAddress.setBillingAddress(false);
		newAddress.setShippingAddress(true);
		newAddress.setComplement(addressForm.getComplement());
		newAddress.setReference(addressForm.getReference());
		newAddress.setReceiver(addressForm.getReceiver());
		newAddress.setNumber(addressForm.getNumber());
		newAddress.setType(TipoDeEndereco.valueOf(addressForm.getAddressType()));
		newAddress.setDistrict(addressForm.getNeighborhood());
		newAddress.setCountry(getI18NFacade().getCountryForIsocode(addressForm.getCountryIso()));
		newAddress.setRegion(getI18NFacade().getRegion(addressForm.getCountryIso(), addressForm.getRegionIso()));

		if (StringUtils.isNotBlank(addressForm.getPhone()))
		{
			newAddress.setPhone(addressForm.getPhone().substring(2));
			if (addressForm.getPhone().length() > 3)
			{
				newAddress.setDddPhone(addressForm.getPhone().substring(0, 2));
			}
		}

		if (StringUtils.isNotBlank(addressForm.getCelPhone()))
		{
			newAddress.setCelPhone(addressForm.getCelPhone().substring(2));

			if (addressForm.getCelPhone().length() > 3)
			{
				newAddress.setDddCelPhone(addressForm.getCelPhone().substring(0, 2));
			}
		}

//		if (addressForm.getSaveInAddressBook() == null)
//		{
		newAddress.setVisibleInAddressBook(true);
//		}
//		else
//		{
//			newAddress.setVisibleInAddressBook(Boolean.TRUE.equals(addressForm.getSaveInAddressBook()));
//		}

		newAddress.setDefaultAddress(getUserFacade().isAddressBookEmpty() || getUserFacade().getAddressBook().size() == 1
				|| Boolean.TRUE.equals(addressForm.getDefaultAddress()));

		// Verify the address data.
		final AddressVerificationResult<AddressVerificationDecision> verificationResult = getAddressVerificationFacade()
				.verifyAddressData(newAddress);
		final boolean addressRequiresReview = getAddressVerificationResultHandler().handleResult(verificationResult, newAddress,
				model, redirectModel, bindingResult, getAddressVerificationFacade().isCustomerAllowedToIgnoreAddressSuggestions(),
				"checkout.multi.address.updated");

		if (addressRequiresReview)
		{
			if (StringUtils.isNotBlank(addressForm.getCountryIso()))
			{
				model.addAttribute("regions", getI18NFacade().getRegionsForCountryIso(addressForm.getCountryIso()));
				model.addAttribute("country", addressForm.getCountryIso());
			}
			storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
			setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));

			if (StringUtils.isNotEmpty(addressForm.getAddressId()))
			{
				final AddressData addressData = getCheckoutFacade().getDeliveryAddressForCode(addressForm.getAddressId());
				if (addressData != null)
				{
					model.addAttribute("showSaveToAddressBook", Boolean.valueOf(!addressData.isVisibleInAddressBook()));
					model.addAttribute("edit", Boolean.TRUE);
				}
			}

			return HeringcheckoutaddonControllerConstants.Views.Pages.MultiStepCheckout.AddEditDeliveryAddressPage;
		}
		
//		getUserFacade().editAddress(newAddress);
//XGH DESCARADO
		final AddressData toRemove = new AddressData();
		toRemove.setId(addressForm.getAddressId());
		getUserFacade().removeAddress(toRemove);

		addressForm.setAddressId(null);
		getUserFacade().addAddress(newAddress);
		
		getCheckoutFacade().setDeliveryModeIfAvailable();
		getCheckoutFacade().setDeliveryAddress(newAddress);
		return REDIRECT_URL_CHOOSE_DELIVERY_METHOD;
	}

	@RequestMapping(value = "/prepare-checkout", method = RequestMethod.GET)
	public String doPrepareOneStepCheckout(final Model model) throws CMSItemNotFoundException
	{
		if (!hasValidCart())
		{
			LOG.info("Missing, empty or unsupported cart");
			return REDIRECT_URL_CART;
		}
		if (!getCheckoutFacade().hasShippingItems())
		{
			return REDIRECT_URL_CHOOSE_DELIVERY_LOCATION;
		}
		getCheckoutFacade().setDeliveryAddressIfAvailable();
		final CartData cartData = getCheckoutFacade().getCheckoutCart();
		final HeringPaymentDetailsForm paymentDetailsForm = new HeringPaymentDetailsForm();
		final HeringAddressForm addressForm = getPreparedAddressForm();
		paymentDetailsForm.setBillingAddress(addressForm);
		model.addAttribute(paymentDetailsForm);
		model.addAttribute("cartData", cartData);
		model.addAttribute("deliveryAddresses", getDeliveryAddresses(cartData.getDeliveryAddress()));
		model.addAttribute("noAddress", Boolean.valueOf(hasNoDeliveryAddress()));
		model.addAttribute("heringAddressForm", getPreparedAddressForm());
		model.addAttribute("showSaveToAddressBook", Boolean.TRUE);
		model.addAttribute("deliveryMethods", getCheckoutFacade().getSupportedDeliveryModes());
		this.prepareDataForPage(model);
		storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
		model.addAttribute(WebConstants.BREADCRUMBS_KEY,
				getResourceBreadcrumbBuilder().getBreadcrumbs("checkout.multi.deliveryAddress.breadcrumb"));
		model.addAttribute("metaRobots", "no-index,no-follow");
		model.addAttribute("pageType", HeringPageType.ORDERCONFIRMATION);
		setupAddPaymentPage(model, null);
		// return HeringcheckoutaddonControllerConstants.Views.Pages
		// .OneStepCheckout.OneStepCheckoutPage;
		return "";
	}

	protected HeringAddressForm getPreparedAddressForm()
	{
		final CustomerData currentCustomerData = getUser();

		final HeringAddressForm addressForm = new HeringAddressForm();

		addressForm.setCountryIso("BR");

		if (getCheckoutCustomerStrategy().isAnonymousCheckout())
		{
			return addressForm;
		}

		final String first = StringUtils.defaultIfBlank(currentCustomerData.getFirstName(), "");
		final String last = StringUtils.defaultIfBlank(currentCustomerData.getLastName(), "");

		addressForm.setReceiver((first + " " + last).trim());
		addressForm.setFirstName(currentCustomerData.getFirstName());
		addressForm.setLastName(currentCustomerData.getLastName());

		return addressForm;
	}

	private boolean redeemVoucher(final String voucherCode, final Model model, final String name,
			final VoucherPaymentInfoData paymentInfoData)
	{

		if (StringUtils.isBlank(voucherCode) || voucherCode.equalsIgnoreCase("DIGITE AQUI SEU CUPOM DE DESCONTO/VALE CREDITO"))
		{
			GlobalMessages.addErrorMessage(model, "voucher.blank");
			return false;
		}

		if (!voucherFacade.checkVoucherCode(voucherCode))
		{
			GlobalMessages.addErrorMessage(model, "voucher.invalid");
			return false;
		}
		
		try
		{
			voucherFacade.applyVoucher(voucherCode);
			paymentInfoData.setValue(voucherFacade.getVoucher(voucherCode).getValue());
			model.addAttribute("voucher", voucherCode);
		}
		catch (final Exception e)
		{
			GlobalMessages.addErrorMessage(model, "voucher.error");
			LOG.error("error", e);
			return false;
		}

		return true;
	}

	private void releaseVoucher()
	{
		for (final VoucherData voucher : voucherFacade.getVouchersForCart())
		{
			try
			{
				voucherFacade.releaseVoucher(voucher.getVoucherCode());
			}
			catch (final Exception e)
			{
				e.printStackTrace();
			}
		}

	}
	
	protected boolean voucherEqualBuy(final CartData cartData)
	{
		if(cartData.getTotalPriceWithTax() != null){
			return cartData.getTotalPriceWithTax().getValue().doubleValue() > 0;
		}
		return true;			
		    	
	}
	
	protected boolean isVoucherCreditValid(final String voucherCode)
	{
		try
		{
			if (!voucherCode.substring(0, 3).equalsIgnoreCase(VALE_CREDITO))
			{
				return false;
			}

		}
		catch (final StringIndexOutOfBoundsException e)
		{
			return false;
		}

		return true;
	}	
	
	@Override
	protected void prepareDataForPage(final Model model) throws CMSItemNotFoundException
	{
		model.addAttribute("isOmsEnabled", Boolean.valueOf(getSiteConfigService().getBoolean("oms.enabled", false)));
		model.addAttribute("supportedCountries", getCheckoutFacade().getDeliveryCountries());
		model.addAttribute("expressCheckoutAllowed", Boolean.valueOf(getCheckoutFacade().isExpressCheckoutAllowedForCart()));
		model.addAttribute("taxEstimationEnabled", Boolean.valueOf(getCheckoutFacade().isTaxEstimationEnabledForCart()));

		model.addAttribute("regions", getI18NFacade().getRegionsForCountryIso("BR"));
	}

	@ModelAttribute("hasAppliedVoucher")
	public boolean hasAppliedVoucher()
	{
		return CollectionUtils.isNotEmpty(voucherFacade.getVouchersForCart());
	}

	@ModelAttribute("hasAppliedValeCredito")
	public boolean hasAppliedValeCredito()
	{
		if (CollectionUtils.isNotEmpty(voucherFacade.getVouchersForCart()))
		{
			for (final VoucherData v : voucherFacade.getVouchersForCart())
			{
				if (v.getVoucherCode().startsWith(VALE_CREDITO))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	@ModelAttribute("appliedValeCreditoCode")
	public String getAppliedValeCreditoCode()
	{
		if (CollectionUtils.isNotEmpty(voucherFacade.getVouchersForCart()))
		{
			for (final VoucherData v : voucherFacade.getVouchersForCart())
			{
				if (v.getVoucherCode().startsWith(VALE_CREDITO))
				{
					return v.getVoucherCode();
				}
			}
		}
		return "";
	}

	@ModelAttribute("valeCreditoValue")
	public PriceData getValeCreditoValue()
	{
		final CartData cartData = getCheckoutFacade().getCheckoutCart();
		if (CollectionUtils.isNotEmpty(voucherFacade.getVouchersForCart()))
		{
			for (final VoucherData v : voucherFacade.getVouchersForCart())
			{
				if (v.getVoucherCode().startsWith(VALE_CREDITO))
				{
					final PriceData price = priceDataFactory.create(PriceDataType.BUY, BigDecimal.valueOf(v.getValue()), cartData.getTotalPrice().getCurrencyIso());
					return price;
				}
			}
		}
		return new PriceData();
	}

	@ModelAttribute("totalDiscounts")
	public PriceData getTotalDiscounts()
	{
		final CartData cartData = getCheckoutFacade().getCheckoutCart();

		if (CollectionUtils.isNotEmpty(voucherFacade.getVouchersForCart()))
		{
			for (final VoucherData v : voucherFacade.getVouchersForCart())
			{
				if (v.getVoucherCode().startsWith(VALE_CREDITO))
				{
					final BigDecimal totalDiscounts = cartData.getTotalDiscounts().getValue()
							.subtract(BigDecimal.valueOf(v.getValue()));
					final PriceData price = priceDataFactory.create(PriceDataType.BUY, totalDiscounts, cartData.getTotalPrice().getCurrencyIso());
					return price;
				}
			}
		}
		return new PriceData();
	}
	
	/**
	 * @return the heringPaymentDetailsValidator
	 */
	public HeringPaymentDetailsValidator getHeringPaymentDetailsValidator()
	{
		return heringPaymentDetailsValidator;
	}

	/**
	 * @param heringPaymentDetailsValidator
	 *           the heringPaymentDetailsValidator to set
	 */
	public void setHeringPaymentDetailsValidator(final HeringPaymentDetailsValidator heringPaymentDetailsValidator)
	{
		this.heringPaymentDetailsValidator = heringPaymentDetailsValidator;
	}

	protected HeringUserFacade getHeringUserFacade()
	{
		return (HeringUserFacade) getUserFacade();
	}

	/**
	 * @return the addressValidator
	 */
	public HeringAddressValidator getAddressValidator()
	{
		return addressValidator;
	}

	/**
	 * @param addressValidator
	 *           the addressValidator to set
	 */
	public void setAddressValidator(final HeringAddressValidator addressValidator)
	{
		this.addressValidator = addressValidator;
	}
	
	
	/**
	 * @param model
	 * @param paymentDetailsForm
	 * @param bindingResult
	 * @return redirectURL
	 */
	protected String doSaveDebitPaymentMethod(final Model model, 
			final HeringPaymentDetailsForm paymentDetailsForm,
			final BindingResult bindingResult,
			final HttpServletRequest request) throws CMSItemNotFoundException
	{
		//validate the payment
		getHeringPaymentDetailsValidator().validate(paymentDetailsForm, bindingResult, model);
		setupAddPaymentPage(model, HeringcheckoutaddonWebConstants.DEBIT);

		//refresh the cartdata
		final CartData cartData = getCheckoutFacade().getCheckoutCart();
		model.addAttribute("cartData", cartData);

		final AddressData addressData;
		if (Boolean.TRUE.equals(paymentDetailsForm.getNewBillingAddress()))
		{
			addressData = getCheckoutFacade().getCheckoutCart().getDeliveryAddress();
			if (addressData == null)
			{
				GlobalMessages.addErrorMessage(model,
						"checkout.multi.paymentMethod.createSubscription.billingAddress.noneSelectedMsg");
				return HeringcheckoutaddonControllerConstants.Views.Pages.MultiStepCheckout.AddPaymentMethodPage;
			}
			addressData.setBillingAddress(true); // mark this as billing address
		}
		else
		{
			addressData = transformHeringAddressForm2AddressData(paymentDetailsForm.getBillingAddress());
		}
		
		if (bindingResult.hasErrors())
		{
			model.addAttribute("checked", Boolean.TRUE);//TODO is this checked value the right for the onlinedebit payment?
			return HeringcheckoutaddonControllerConstants.Views.Pages.MultiStepCheckout.AddPaymentMethodPage;
		}
		if (StringUtils.isNotBlank(paymentDetailsForm.getSelectedPaymentId()))
		{
			//TODO watch out!!! this facade only accepts creditcardpaymentinfo
			getCheckoutFacade().setPaymentDetails(paymentDetailsForm.getSelectedPaymentId());
		}

		HeringDebitPaymentInfoData debitPaymentInfoData = new HeringDebitPaymentInfoData();
		
		debitPaymentInfoData.setBillingAddress(addressData);
		debitPaymentInfoData.setUserAgent(request.getHeader("user-agent"));
		debitPaymentInfoData.setAccept(request.getHeader("accept"));
		String host = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/store"));
		debitPaymentInfoData.setReturnUrl(host+context.getContextPath() + "/checkout/multi/redirectFromCielo");
		
		String shopperIP = request.getHeader("X-FORWARDED-FOR");  
	   if (shopperIP == null) {  
		   shopperIP = request.getRemoteAddr();  
	   }
	   debitPaymentInfoData.setShopperIP(shopperIP);
		
		/** TODO these should come from the screen*/
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
				&& StringUtils.isNotBlank(debitPaymentInfoData.getSubscriptionId()))
		{
//			if (Boolean.TRUE.equals(paymentDetailsForm.getSaveInAccount()) && getUserFacade().getCCPaymentInfos(true).size() <= 1)
//			{
//				//TODO type invalid
//				LOG.error(savedDebitPaymentInfoData);
//				//				getHeringUserFacade().setDefaultPaymentInfo(savedDebitPaymentInfoData);
//			}
			/**este cara deve estar dentro do savepaymentinfointocart*/
			cartData.setCustomPaymentInfo(debitPaymentInfoData);
			getCheckoutFacade().savePaymentInfoIntoCart(cartData);
			model.addAttribute("paymentId", "" + debitPaymentInfoData.getPK());
		}
		else
		{
			GlobalMessages.addErrorMessage(model, "checkout.multi.paymentMethod.createSubscription.failedMsg");
			return HeringcheckoutaddonControllerConstants.Views.Pages.MultiStepCheckout.AddPaymentMethodPage;
		}
		
		return REDIRECT_URL_SUMMARY;
	}

	private AddressData transformHeringAddressForm2AddressData(final HeringAddressForm addressForm)
	{
		final AddressData addressData = new AddressData();
		if (addressForm != null)
		{
			addressData.setId(addressForm.getAddressId());
			addressData.setFirstName(addressForm.getFirstName());
			addressData.setLastName(addressForm.getLastName());
			addressData.setLine1(addressForm.getLine1());
			addressData.setTown(addressForm.getTownCity());
			addressData.setPostalCode(addressForm.getPostcode());
			addressData.setDistrict(addressForm.getNeighborhood());

			if (StringUtils.isNotBlank(addressForm.getPhone()))
			{
				addressData.setPhone(addressForm.getPhone().substring(2));
				if (addressForm.getPhone().length() > 3)
				{
					addressData.setDddPhone(addressForm.getPhone().substring(0, 2));
				}
			}

			if (StringUtils.isNotBlank(addressForm.getCelPhone()))
			{
				addressData.setCelPhone(addressForm.getCelPhone().substring(2));

				if (addressForm.getCelPhone().length() > 3)
				{
					addressData.setDddCelPhone(addressForm.getCelPhone().substring(0, 2));
				}
			}

			addressData.setNumber(addressForm.getNumber());
			addressData.setComplement(addressForm.getComplement());
			addressData.setReference(addressForm.getReference());
			addressData.setType(TipoDeEndereco.valueOf(addressForm.getAddressType()));
			addressData.setCountry(getI18NFacade().getCountryForIsocode(addressForm.getCountryIso()));
			addressData.setRegion(getI18NFacade().getRegion(addressForm.getCountryIso(), addressForm.getRegionIso()));

			addressData.setShippingAddress(Boolean.TRUE.equals(addressForm.getShippingAddress()));
			addressData.setBillingAddress(Boolean.TRUE.equals(addressForm.getBillingAddress()));
		}
		getAddressVerificationFacade().verifyAddressData(addressData);
		return addressData;
	}
	
	protected String redirectToCielo(final Model model){
		PaymentInfoData paymentInfoData = getCheckoutFacade().getCheckoutCart().getCustomPaymentInfo();
		if (paymentInfoData instanceof HeringDebitPaymentInfoData)
		{
			HeringDebitPaymentInfoData debitPaymentInfoData = (HeringDebitPaymentInfoData)paymentInfoData;
			
			model.addAttribute("issuerUrl", debitPaymentInfoData.getIssuerUrl());
			model.addAttribute("paRequest", debitPaymentInfoData.getPaRequest());
			model.addAttribute("termUrl", debitPaymentInfoData.getReturnUrl());
			model.addAttribute("md", debitPaymentInfoData.getMd());
		}
		return HeringcheckoutaddonControllerConstants.Views.Pages.MultiStepCheckout.RedirectToCieloPage;
	}
	
	@Override
	protected HeringCheckoutFacade getCheckoutFacade()
	{
		return (HeringCheckoutFacade)super.getCheckoutFacade();
	}

	/**
	 * @return the customerAccountService
	 */
	public KPCustomerAccountService getCustomerAccountService()
	{
		return customerAccountService;
	}
}
