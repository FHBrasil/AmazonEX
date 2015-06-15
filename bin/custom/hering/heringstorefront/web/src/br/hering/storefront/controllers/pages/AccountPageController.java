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

import de.hybris.platform.acceleratorservices.payment.cybersource.data.PaymentInfoData;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.UpdateEmailForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.UpdatePasswordForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.AddressValidator;
import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.EmailValidator;
import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.PasswordValidator;
import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.ProfileValidator;
import de.hybris.platform.acceleratorstorefrontcommons.forms.verification.AddressVerificationResultHandler;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.address.AddressVerificationFacade;
import de.hybris.platform.commercefacades.address.data.AddressVerificationResult;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.i18n.I18NFacade;
import de.hybris.platform.commercefacades.order.CheckoutFacade;
import de.hybris.platform.commercefacades.order.OrderFacade;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.data.OrderHistoryData;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.data.RegionData;
import de.hybris.platform.commercefacades.user.data.TitleData;
import de.hybris.platform.commercefacades.user.exceptions.PasswordMismatchException;
import de.hybris.platform.commerceservices.address.AddressVerificationDecision;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.commerceservices.strategies.CheckoutCustomerStrategy;
import de.hybris.platform.commerceservices.search.pagedata.PaginationData;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.jalo.order.payment.PaymentMode;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.util.Config;






import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Dictionary;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.hering.core.enums.TipoDeEndereco;
import br.hering.core.util.SelectOption;
import br.hering.facades.checkout.payment.HeringPaymentModeFacade;
import br.hering.facades.customer.impl.DefaultHeringCustomerFacade;
import br.hering.facades.facades.order.impl.DefaultHeringCheckoutFacade;
import br.hering.facades.order.HeringOrderFacade;
import br.hering.facades.order.data.PaymentModeData;
import br.hering.facades.product.data.AddressTypeData;
import br.hering.facades.user.HeringUserFacade;
import br.hering.heringstorefrontcommons.forms.HeringAddressForm;
import br.hering.heringstorefrontcommons.forms.HeringPaymentDetailsForm;
import br.hering.heringstorefrontcommons.validation.HeringAddressValidator;
import br.hering.storefront.controllers.ControllerConstants;
import br.hering.storefront.forms.HeringUpdateProfileForm;
import br.hering.storefront.forms.validation.HeringEmailValidator;
import br.hering.storefront.forms.validation.HeringPasswordValidator;
import br.hering.storefront.forms.validation.HeringProfileValidator;
import br.hering.storefront.forms.validation.WishlistValidator;
import br.hering.facades.wishlist.data.HeringWishlistData;
import br.hering.facades.wishlist.data.HeringWishlistEntryData;
import br.hering.facades.wishlist.impl.DefaultHeringWishlistFacade;
import br.hering.storefront.forms.UpdateWishlistForm;
import br.hering.storefront.util.HeringPageType;




/**
 * Controller for home page.
 * 
 * @author Vinicius de Souza
 * 
 * @version 1.1 - Adjustment in the method <code>editPaymentMethod</code> to create Subscription of Payment.
 * 
 */
@Controller
@Scope("tenant")
@RequestMapping("/my-account")
public class AccountPageController extends AbstractSearchPageController
{
	// Internal Redirects
	private static final String REDIRECT_MY_ACCOUNT = REDIRECT_PREFIX + "/my-account";
	private static final String REDIRECT_TO_ADDRESS_BOOK_PAGE = REDIRECT_PREFIX + "/my-account/address-book";
	private static final String REDIRECT_TO_PAYMENT_INFO_PAGE = REDIRECT_PREFIX + "/my-account/payment-details";
	private static final String REDIRECT_TO_EDIT_PAYMENT_METHOD_PAGE = REDIRECT_PREFIX + "/edit-payment-details";
	private static final String REDIRECT_TO_PROFILE_PAGE = REDIRECT_PREFIX + "/my-account/profile";
	private static final String WISHLIST_PUBLIC_URL = REDIRECT_PREFIX + "/w/";
	/**
	 * We use this suffix pattern because of an issue with Spring 3.1 where a Uri value is incorrectly extracted if it
	 * contains on or more '.' characters. Please see https://jira.springsource.org/browse/SPR-6164 for a discussion on
	 * the issue and future resolution.
	 */
	private static final String ORDER_CODE_PATH_VARIABLE_PATTERN = "{orderCode:.*}";
	private static final String ADDRESS_CODE_PATH_VARIABLE_PATTERN = "{addressCode:.*}";

	// CMS Pages
	private static final String ACCOUNT_CMS_PAGE = "account";
	private static final String PROFILE_CMS_PAGE = "profile";
	private static final String ADDRESS_BOOK_CMS_PAGE = "address-book";
	private static final String ADD_EDIT_ADDRESS_CMS_PAGE = "add-edit-address";
	private static final String PAYMENT_DETAILS_CMS_PAGE = "payment-details";
	private static final String EDIT_PAYMENT_DETAILS_CMS_PAGE = "edit-payment-details";
	private static final String ORDER_HISTORY_CMS_PAGE = "orders";
	private static final String ORDER_DETAIL_CMS_PAGE = "order";
	private static final String WISHLIST_ENTRIES_CMS_PAGE = "my-wishlist";

	private static final Logger LOG = Logger.getLogger(AccountPageController.class);

	@Resource(name = "heringWishlistFacade")
	private DefaultHeringWishlistFacade heringWishlistFacade;
	@Resource(name = "wishlistValidator")
	private WishlistValidator wishlistValidator;
	@Resource(name = "heringOrderFacade")
	private HeringOrderFacade orderFacade;
	@Resource(name = "acceleratorCheckoutFacade")
	private CheckoutFacade checkoutFacade;
	@Resource(name = "userFacade")
	protected HeringUserFacade userFacade;
	@Resource(name = "customerFacade")
	protected CustomerFacade customerFacade;
	@Resource(name = "accountBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder accountBreadcrumbBuilder;
	@Resource(name = "passwordValidator")
	private HeringPasswordValidator passwordValidator;
	@Resource(name = "addressValidator")
	private HeringAddressValidator addressValidator;
	@Resource(name = "heringProfileValidator")
	private HeringProfileValidator profileValidator;
	@Resource(name = "emailValidator")
	private HeringEmailValidator emailValidator;
	@Resource(name = "i18NFacade")
	private I18NFacade i18NFacade;
	@Resource(name = "addressVerificationFacade")
	private AddressVerificationFacade addressVerificationFacade;
	@Resource(name = "addressVerificationResultHandler")
	private AddressVerificationResultHandler addressVerificationResultHandler;
	@Resource
	private OrderService orderService;
	@Resource
	private BaseStoreService baseStoreService;
	@Resource
	private CheckoutCustomerStrategy checkoutCustomerStrategy;
	@Resource
	private CustomerAccountService customerAccountService;
	@Resource
	private UserService userService;
	@Resource
   private HeringPaymentModeFacade paymentModeFacade;
	
	@Resource
	private TypeService typeService;

	
	@Resource(name = "defaultHeringCheckoutFacade")
   private DefaultHeringCheckoutFacade defaultHeringCheckoutFacade;

	protected WishlistValidator getWishlistValidator()
	{
		return wishlistValidator;
	}


	protected HeringPasswordValidator getPasswordValidator()
	{
		return passwordValidator;
	}

	protected HeringAddressValidator getAddressValidator()
	{
		return addressValidator;
	}

	protected HeringProfileValidator getProfileValidator()
	{
		return profileValidator;
	}

	protected HeringEmailValidator getEmailValidator()
	{
		return emailValidator;
	}

	protected I18NFacade getI18NFacade()
	{
		return i18NFacade;
	}

	protected AddressVerificationFacade getAddressVerificationFacade()
	{
		return addressVerificationFacade;
	}

	protected AddressVerificationResultHandler getAddressVerificationResultHandler()
	{
		return addressVerificationResultHandler;
	}



	@ModelAttribute("countries")
	public Collection<CountryData> getCountries()
	{
		return checkoutFacade.getDeliveryCountries();
	}

	@ModelAttribute("titles")
	public Collection<TitleData> getTitles()
	{
		return userFacade.getTitles();
	}

	@ModelAttribute("countryDataMap")
	public Map<String, CountryData> getCountryDataMap()
	{
		final Map<String, CountryData> countryDataMap = new HashMap<>();
		for (final CountryData countryData : getCountries())
		{
			countryDataMap.put(countryData.getIsocode(), countryData);
		}
		return countryDataMap;
	}
	
	@ModelAttribute("addressTypes")
	public List<SelectOption> getAddressTypes()
	{
		final List<SelectOption> addressTypes = new ArrayList<SelectOption>();
		
		final TipoDeEndereco enderecoResidencial = TipoDeEndereco.RESIDENCIAL;
		final TipoDeEndereco enderecoComercial = TipoDeEndereco.COMERCIAL;
		
		final String residencialCode = enderecoResidencial.getCode();
		final String residencialName = getTypeService().getEnumerationValue(enderecoResidencial).getName();
		
		final String comercialCode = enderecoComercial.getCode();
		final String comercialName = getTypeService().getEnumerationValue(enderecoComercial).getName();
				
		addressTypes.add(new SelectOption(residencialCode, residencialName));
		addressTypes.add(new SelectOption(comercialCode, comercialName));
	
		
		return addressTypes;
	}

	@RequestMapping(value = "/addressform", method = RequestMethod.GET)
	public String getCountryAddressForm(@RequestParam("addressCode") final String addressCode,
			@RequestParam("countryIsoCode") final String countryIsoCode, final Model model)
	{
		model.addAttribute("supportedCountries", getCountries());
		model.addAttribute("regions", getI18NFacade().getRegionsForCountryIso(countryIsoCode));
		model.addAttribute("country", countryIsoCode);
		model.addAttribute("pageType", HeringPageType.ACCOUNTPAGE.name());


		final HeringAddressForm addressForm = getPreparedAddressForm();
		model.addAttribute("heringAddressForm", addressForm);
		for (final AddressData addressData : userFacade.getAddressBook())
		{
			if (addressData.getId() != null && addressData.getId().equals(addressCode)
					&& countryIsoCode.equals(addressData.getCountry().getIsocode()))
			{

				model.addAttribute("addressData", addressData);
				addressForm.setAddressId(addressData.getId());

				if (StringUtils.isNotBlank(addressData.getFirstName()))
				{
					addressForm.setFirstName(addressData.getFirstName());
				}

				if (StringUtils.isNotBlank(addressData.getLastName()))
				{
					addressForm.setLastName(addressData.getLastName());
				}


				if (StringUtils.isNotBlank(addressData.getPhone()))
				{
					addressForm.setPhone(concatenPhone(addressData.getPhone(), addressData.getDddPhone()));
				}

				if (StringUtils.isNotBlank(addressData.getCelPhone()))
				{
					addressForm.setCelPhone(concatenPhone(addressData.getCelPhone(), addressData.getDddCelPhone()));
				}

				if (StringUtils.isNotBlank(addressData.getReceiver()))
				{
					addressForm.setReceiver(addressData.getReceiver());
				}

				addressForm.setTitleCode(addressData.getTitleCode());
				addressForm.setPostcode(addressData.getPostalCode());
				addressForm.setLine1(addressData.getLine1());
				addressForm.setNumber(addressData.getNumber());
				addressForm.setComplement(addressData.getComplement());
				addressForm.setNeighborhood(addressData.getDistrict());
				addressForm.setTownCity(addressData.getTown());
				addressForm.setRegionIso(addressData.getRegion().getIsocode());

				if (StringUtils.isNotBlank(addressData.getReference()))
				{
					addressForm.setReference(addressData.getReference());
				}

				if (addressData.getType() != null)
				{
					addressForm.setAddressType(addressData.getType().getCode());
				}

				break;
			}
		}
		return ControllerConstants.Views.Fragments.Account.CountryAddressForm;
	}

	@RequestMapping(method = RequestMethod.GET)
	@RequireHardLogIn
	public String account(final Model model) throws CMSItemNotFoundException
	{
		storeCmsPageInModel(model, getContentPageForLabelOrId(ACCOUNT_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(ACCOUNT_CMS_PAGE));
		
		final PageableData pageableData = createPageableData(0, 3, null, ShowMode.Page);
		final SearchPageData<OrderHistoryData> searchPageData = orderFacade.getPagedOrderHistoryForStatuses(pageableData);
		
		model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs(null));
		model.addAttribute("metaRobots", "no-index,no-follow");
		model.addAttribute("customerData", customerFacade.getCurrentCustomer());
		model.addAttribute("addressData", userFacade.getAddressBook());
		//model.addAttribute("orderData", getOrderData());
		model.addAttribute("pageType", HeringPageType.ACCOUNTPAGE.name());
		model.addAttribute("orderHistoryPreview",  searchPageData.getResults());
		
		return ControllerConstants.Views.Pages.Account.AccountHomePage;
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	@RequireHardLogIn
	public String orders(@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "sort", required = false) final String sortCode, final Model model)
			throws CMSItemNotFoundException
	{
		// Handle paged search results
		final PageableData pageableData = createPageableData(page, 5, sortCode, showMode);
		final SearchPageData<OrderHistoryData> searchPageData = orderFacade.getPagedOrderHistoryForStatuses(pageableData);
		populateModel(model, searchPageData, showMode);

		storeCmsPageInModel(model, getContentPageForLabelOrId(ORDER_HISTORY_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(ORDER_HISTORY_CMS_PAGE));
		model.addAttribute("orderData", getOrderData());
		model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.orderHistory"));
		model.addAttribute("metaRobots", "no-index,no-follow");
		model.addAttribute("pageType", HeringPageType.ACCOUNTPAGE.name());
		return ControllerConstants.Views.Pages.Account.AccountOrderHistoryPage;
	}
	
	/**
	 * @author Rafael Saltore
	 * @param orderSearch
	 * @param model
	 * @return orderFind
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(value = "/orders", method = RequestMethod.POST)
	@RequireHardLogIn
	public String ordersPost(@RequestParam final String orderSearch, final Model model) throws CMSItemNotFoundException
	{
		final List<OrderHistoryData> orderHistory = orderFacade.getOrderHistoryForStatuses();
		boolean found = false;
		if(orderSearch != null){
			if(orderHistory != null){
				for(OrderHistoryData order : orderHistory){
					if(order.getCode().equals(orderSearch)){
						model.addAttribute("orderFind", order);
						found = true;
						break;
					}
				}
			}
		}
		if(!found){
			model.addAttribute("orderFind", "notFound");
			GlobalMessages.addErrorMessage(model, "text.account.orderHistory.searchOrderNotFound");			
		}
		
		storeCmsPageInModel(model, getContentPageForLabelOrId(ORDER_HISTORY_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(ORDER_HISTORY_CMS_PAGE));
		model.addAttribute("orderData", getOrderData());
		model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.orderHistory"));
		model.addAttribute("metaRobots", "no-index,no-follow");
		model.addAttribute("pageType", HeringPageType.ACCOUNTPAGE.name());
		return ControllerConstants.Views.Pages.Account.AccountOrderHistoryPage;
	}

	@RequestMapping(value = "/order/" + ORDER_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	@RequireHardLogIn
	public String order(final HttpServletRequest request, final HttpServletResponse response,
			@PathVariable("orderCode") final String orderCode, final Model model) throws CMSItemNotFoundException
	{
		try
		{
			final OrderData orderDetails = orderFacade.getOrderDetailsForCode(orderCode);
			model.addAttribute("orderData", orderDetails);

			final int nfeCode = 0;
			final String availableLinkToNfe = orderFacade.getAvailableLinkNfeForCode(nfeCode);
			model.addAttribute("availableLinkToNfe", availableLinkToNfe);
			
			final PageableData pageableData = createPageableData(0, 100, null, ShowMode.Page);
			final SearchPageData<OrderHistoryData> searchPageData = orderFacade.getPagedOrderHistoryForStatuses(pageableData);

			final List<Breadcrumb> breadcrumbs = accountBreadcrumbBuilder.getBreadcrumbs(null);
			breadcrumbs.add(new Breadcrumb("/my-account/orders", getMessageSource().getMessage("text.account.orderHistory", null,
					getI18nService().getCurrentLocale()), null));
			breadcrumbs.add(new Breadcrumb("#", getMessageSource().getMessage("text.account.order.orderBreadcrumb", new Object[]
			{ orderDetails.getCode() }, "Order {0}", getI18nService().getCurrentLocale()), null));
			model.addAttribute("boletoUrl", request.getContextPath() + "/boleto/" + orderCode);
			model.addAttribute("breadcrumbs", breadcrumbs);
			model.addAttribute("orderHistoryPreview",  searchPageData.getResults());

		}
		catch (final UnknownIdentifierException e)
		{
			LOG.warn("Attempted to load a order that does not exist or is not visible", e);
			return REDIRECT_MY_ACCOUNT;
		}
		storeCmsPageInModel(model, getContentPageForLabelOrId(ORDER_DETAIL_CMS_PAGE));
		model.addAttribute("metaRobots", "no-index,no-follow");
		model.addAttribute("pageType", HeringPageType.ACCOUNTPAGE.name());
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(ORDER_DETAIL_CMS_PAGE));
		return ControllerConstants.Views.Pages.Account.AccountOrderPage;
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	@RequireHardLogIn
	public String profile(final Model model) throws CMSItemNotFoundException
	{
		final List<TitleData> titles = userFacade.getTitles();

		final CustomerData customerData = customerFacade.getCurrentCustomer();


		if (customerData.getTitleCode() != null)
		{
			model.addAttribute("title", findTitleForCode(titles, customerData.getTitleCode()));
		}

		
		model.addAttribute("customerData", customerData);
		model.addAttribute("pf", customerData.getCpfcnpj().length()==14?Boolean.FALSE:Boolean.TRUE);

		storeCmsPageInModel(model, getContentPageForLabelOrId(PROFILE_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(PROFILE_CMS_PAGE));
		model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.profile"));
		model.addAttribute("metaRobots", "no-index,no-follow");
		model.addAttribute("addressData", userFacade.getAddressBook());
		model.addAttribute("pageType", HeringPageType.ACCOUNTPAGE.name());
		return ControllerConstants.Views.Pages.Account.AccountProfilePage;
	}

	protected TitleData findTitleForCode(final List<TitleData> titles, final String code)
	{
		if (code != null && !code.isEmpty() && titles != null && !titles.isEmpty())
		{
			for (final TitleData title : titles)
			{
				if (code.equals(title.getCode()))
				{
					return title;
				}
			}
		}
		return null;
	}
	
	
	public List<OrderData> getOrderData()
	{
		List<OrderData> orderData = new ArrayList<OrderData>();
		UserModel user = userService.getCurrentUser();
		for(OrderModel order : user.getOrders())
			orderData.add(orderFacade.getOrderDetailsForCode(order.getCode()));
		
		return orderData;
	}

	@RequestMapping(value = "/update-email", method = RequestMethod.GET)
	@RequireHardLogIn
	public String editEmail(final Model model) throws CMSItemNotFoundException
	{
		final CustomerData customerData = customerFacade.getCurrentCustomer();
		final UpdateEmailForm updateEmailForm = new UpdateEmailForm();

		// SPJ 17-07-2014 disable email filling sougth by Hering client Francine
		//updateEmailForm.setEmail(customerData.getDisplayUid());

		model.addAttribute("updateEmailForm", updateEmailForm);

		storeCmsPageInModel(model, getContentPageForLabelOrId(PROFILE_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(PROFILE_CMS_PAGE));
		model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.profile"));
		model.addAttribute("metaRobots", "no-index,no-follow");
		model.addAttribute("pageType", HeringPageType.ACCOUNTPAGE.name());
		return ControllerConstants.Views.Pages.Account.AccountProfileEmailEditPage;
	}

	@RequestMapping(value = "/update-email", method = RequestMethod.POST)
	@RequireHardLogIn
	public String updateEmail(final UpdateEmailForm updateEmailForm, final BindingResult bindingResult, final Model model,
			final RedirectAttributes redirectAttributes) throws CMSItemNotFoundException
	{
		getEmailValidator().validate(updateEmailForm, bindingResult, model);

		String returnAction = REDIRECT_TO_PROFILE_PAGE;

		if (!bindingResult.hasErrors() && !updateEmailForm.getEmail().equals(updateEmailForm.getChkEmail()))
		{
			bindingResult.rejectValue("chkEmail", "validation.checkEmail.equals", new Object[] {}, "validation.checkEmail.equals");
		}

		if (bindingResult.hasErrors())
		{
			returnAction = errorUpdatingEmail(model, "form.global.error");
		}
		else
		{
			try
			{
				String oldEmail = customerFacade.getCurrentCustomer().getUid();
				customerFacade.changeUid(updateEmailForm.getEmail(), updateEmailForm.getPassword());
				GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.CONF_MESSAGES_HOLDER,
						"text.account.profile.confirmationUpdated", null);



				// Replace the spring security authentication with the new UID
				final String newUid = customerFacade.getCurrentCustomer().getUid().toLowerCase();
				final Authentication oldAuthentication = SecurityContextHolder.getContext().getAuthentication();
				final UsernamePasswordAuthenticationToken newAuthentication = new UsernamePasswordAuthenticationToken(newUid, null,
						oldAuthentication.getAuthorities());
				newAuthentication.setDetails(oldAuthentication.getDetails());
				SecurityContextHolder.getContext().setAuthentication(newAuthentication);



			}
			catch (final DuplicateUidException e)
			{
				bindingResult.rejectValue("email", "profile.email.unique");
				returnAction = errorUpdatingEmail(model, "profile.email.unique");
			}
			catch (final PasswordMismatchException passwordMismatchException)
			{
				bindingResult.rejectValue("password", "profile.currentPassword.invalid");
				returnAction = errorUpdatingEmail(model, "profile.currentPassword.invalid");
			}
			catch (Exception e)
			{
				//
			}
		}

		return returnAction;
	}

	protected String errorUpdatingEmail(final Model model, final String msg) throws CMSItemNotFoundException
	{
		final String returnAction;
		GlobalMessages.addErrorMessage(model, msg);
		storeCmsPageInModel(model, getContentPageForLabelOrId(PROFILE_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(PROFILE_CMS_PAGE));
		model.addAttribute("pageType", HeringPageType.ACCOUNTPAGE.name());
		model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.profile"));
		returnAction = ControllerConstants.Views.Pages.Account.AccountProfileEmailEditPage;
		return returnAction;
	}

	@RequestMapping(value = "/update-profile", method = RequestMethod.GET)
	@RequireHardLogIn
	public String editProfile(final Model model) throws CMSItemNotFoundException
	{
		model.addAttribute("titleData", userFacade.getTitles());
		model.addAttribute("addressTypes", getAddressTypes());
		model.addAttribute("basesCode", getBasesCode());

		final CustomerData customerData = customerFacade.getCurrentCustomer();
		final HeringUpdateProfileForm updateProfileForm = new HeringUpdateProfileForm();
		
		model.addAttribute("checked", Boolean.FALSE);
		


		updateProfileForm.setTitleCode(customerData.getTitleCode());
		updateProfileForm.setFirstName(customerData.getFirstName());
		updateProfileForm.setLastName(customerData.getLastName());
		
		if(customerData.getRgIe() != null && customerData.getUfIe() != null){
			updateProfileForm.setRgIe(customerData.getRgIe());
			updateProfileForm.setUfIe(customerData.getUfIe());
			model.addAttribute("pf", Boolean.FALSE);
		}
		else
		{
			model.addAttribute("pf", Boolean.TRUE);
		}
		
		if (customerData.getBirthday() != null)
		{
			updateProfileForm.setBirthday(customerData.getBirthday());
		}

		
		updateProfileForm.setCpfcnpj(customerData.getCpfcnpj());
		updateProfileForm.setGender(customerData.getGender());
		model.addAttribute("updateProfileForm", updateProfileForm);
		model.addAttribute("regions", i18NFacade.getRegionsForCountryIso("DE"));

		storeCmsPageInModel(model, getContentPageForLabelOrId(PROFILE_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(PROFILE_CMS_PAGE));

		model.addAttribute("heringUpdateProfileForm", updateProfileForm);
		model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.profile"));
		model.addAttribute("metaRobots", "no-index,no-follow");
		model.addAttribute("pageType", HeringPageType.ACCOUNTPAGE.name());
		return ControllerConstants.Views.Pages.Account.AccountProfileEditPage;
	}


	/**
	 * @return Retorna os c��digos dos Base Store registrados, separados por ",".
	 */
	private String getBasesCode()
	{
		String result = null;
		
		List<String> auxBase = new LinkedList<>();
		
		for (BaseStoreModel baseModel : baseStoreService.getAllBaseStores())
		{
			if(!auxBase.contains(baseModel.getUid())){
				result = (result == null ? baseModel.getUid() : result + "," + baseModel.getUid());
				auxBase.add(baseModel.getUid());
			}
		}
		
		return result;
	}

	@RequestMapping(value = "/update-profile", method = RequestMethod.POST)
	@RequireHardLogIn
	public String updateProfile(final HeringUpdateProfileForm updateProfileForm, final BindingResult bindingResult,
			final Model model, final RedirectAttributes redirectAttributes) throws CMSItemNotFoundException
	{
		getProfileValidator().validate(updateProfileForm, bindingResult, model);

		String returnAction = ControllerConstants.Views.Pages.Account.AccountProfileEditPage;
		final CustomerData currentCustomerData = customerFacade.getCurrentCustomer();

		final CustomerData customerData = new CustomerData();
		customerData.setTitleCode(updateProfileForm.getTitleCode());
		customerData.setFirstName(updateProfileForm.getFirstName());
		customerData.setLastName(updateProfileForm.getLastName());
		customerData.setBirthday(updateProfileForm.getDateBirthday());
		customerData.setUid(currentCustomerData.getUid());
		customerData.setDisplayUid(currentCustomerData.getDisplayUid());
		
		if(StringUtils.isNotEmpty(updateProfileForm.getCpfcnpj()) && 
				updateProfileForm.getCpfcnpj().length() == 14)
		{
			customerData.setGender(Gender.UNDEFINED);
		}
		else
		{
			customerData.setGender(updateProfileForm.getGender());
		}

		if (getDefaultHeringCustomerFacade().cpfCnpjAlreadyExists(updateProfileForm.getCpfcnpj()) != null)
		{
			String cpf = getDefaultHeringCustomerFacade().cpfCnpjAlreadyExists(updateProfileForm.getCpfcnpj()).getCpfcnpj();
			String cpfAtual = checkoutCustomerStrategy.getCurrentUserForCheckout().getCpfcnpj();
			if (!cpf.equals(cpfAtual))
			{
				bindingResult.rejectValue("cpfcnpj", "");
				GlobalMessages.addErrorMessage(model, "register.cpfexists");
			}
		}
		customerData.setCpfcnpj(updateProfileForm.getCpfcnpj());
		customerData.setRgIe(updateProfileForm.getRgIe());
		customerData.setUfIe(updateProfileForm.getUfIe());

		
		if(customerData.getRgIe() != null && customerData.getUfIe() != null){
			model.addAttribute("pf", Boolean.FALSE);
		}
		else
		{
			model.addAttribute("pf", Boolean.TRUE);
		}
		
		model.addAttribute("addressTypes", getAddressTypes());
		model.addAttribute("heringUpdateProfileForm", updateProfileForm);

		//		if (bindingResult.hasErrors())
		//		{
		//			GlobalMessages.addErrorMessage(model, "form.global.error");
		//		}
		//		else
		if (!bindingResult.hasErrors())
		{
			try
			{
				customerFacade.updateProfile(customerData);

				GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.CONF_MESSAGES_HOLDER,
						"text.account.profile.confirmationUpdated", null);
				returnAction = REDIRECT_TO_PROFILE_PAGE;
			}
			catch (final DuplicateUidException e)
			{
				bindingResult.rejectValue("email", "registration.error.account.exists.title");
				GlobalMessages.addErrorMessage(model, "form.global.error");
			}
		}
		else
		{
			model.addAttribute("regions", i18NFacade.getRegionsForCountryIso("DE"));
		}

		storeCmsPageInModel(model, getContentPageForLabelOrId(PROFILE_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(PROFILE_CMS_PAGE));
		model.addAttribute("basesCode", getBasesCode());
		model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.profile"));
		model.addAttribute("pageType", HeringPageType.ACCOUNTPAGE.name());
		return returnAction;
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
			if(StringUtils.isNotBlank(b))
			{
				bases.add(b);
			}
		}
		
		return bases;
	}

	@RequestMapping(value = "/update-password", method = RequestMethod.GET)
	@RequireHardLogIn
	public String updatePassword(final Model model) throws CMSItemNotFoundException
	{
		final UpdatePasswordForm updatePasswordForm = new UpdatePasswordForm();

		model.addAttribute("updatePasswordForm", updatePasswordForm);

		storeCmsPageInModel(model, getContentPageForLabelOrId(PROFILE_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(PROFILE_CMS_PAGE));

		model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.profile.updatePasswordForm"));
		model.addAttribute("metaRobots", "no-index,no-follow");
		model.addAttribute("pageType", HeringPageType.ACCOUNTPAGE.name());
		return ControllerConstants.Views.Pages.Account.AccountChangePasswordPage;
	}

	@RequestMapping(value = "/update-password", method = RequestMethod.POST)
	@RequireHardLogIn
	public String updatePassword(final UpdatePasswordForm updatePasswordForm, final BindingResult bindingResult,
			final Model model, final RedirectAttributes redirectAttributes) throws CMSItemNotFoundException
	{
		getPasswordValidator().validate(updatePasswordForm, bindingResult, model);
		if (!bindingResult.hasErrors())
		{
			if (updatePasswordForm.getNewPassword().equals(updatePasswordForm.getCheckNewPassword()))
			{
				try
				{
					customerFacade.changePassword(updatePasswordForm.getCurrentPassword(), updatePasswordForm.getNewPassword());
				}
				catch (final PasswordMismatchException localException)
				{
					GlobalMessages.addErrorMessage(model, "profile.currentPassword.invalid");
					bindingResult.rejectValue("currentPassword", "profile.currentPassword.invalid", new Object[] {},
							"profile.currentPassword.invalid");
				}
			}
			else
			{
				bindingResult.rejectValue("checkNewPassword", "validation.checkPwd.equals", new Object[] {},
						"validation.checkPwd.equals");
			}
		}

		if (bindingResult.hasErrors())
		{
			//			GlobalMessages.addErrorMessage(model, "form.global.error");
			storeCmsPageInModel(model, getContentPageForLabelOrId(PROFILE_CMS_PAGE));
			setUpMetaDataForContentPage(model, getContentPageForLabelOrId(PROFILE_CMS_PAGE));

			model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.profile.updatePasswordForm"));
			model.addAttribute("pageType", HeringPageType.ACCOUNTPAGE.name());
			return ControllerConstants.Views.Pages.Account.AccountChangePasswordPage;
		}
		else
		{
			GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.CONF_MESSAGES_HOLDER,
					"text.account.confirmation.password.updated", null);
			return REDIRECT_TO_PROFILE_PAGE;
		}
	}

	@RequestMapping(value = "/address-book", method = RequestMethod.GET)
	@RequireHardLogIn
	public String getAddressBook(final Model model) throws CMSItemNotFoundException
	{
		model.addAttribute("addressData", userFacade.getAddressBook());

		storeCmsPageInModel(model, getContentPageForLabelOrId(ADDRESS_BOOK_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(ADDRESS_BOOK_CMS_PAGE));
		model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.addressBook"));
		model.addAttribute("metaRobots", "no-index,no-follow");
		model.addAttribute("pageType", HeringPageType.ACCOUNTPAGE.name());
		return ControllerConstants.Views.Pages.Account.AccountAddressBookPage;
	}

	@RequestMapping(value = "/add-address", method = RequestMethod.GET)
	@RequireHardLogIn
	public String addAddress(final Model model) throws CMSItemNotFoundException
	{
		model.addAttribute("countryData", checkoutFacade.getDeliveryCountries());
		model.addAttribute("titleData", userFacade.getTitles());
		model.addAttribute("addressTypes", getAddressTypes());
		model.addAttribute("regions", getI18NFacade().getRegionsForCountryIso("DE"));
		final HeringAddressForm addressForm = getPreparedAddressForm();
		model.addAttribute("heringAddressForm", addressForm);
		model.addAttribute("addressBookEmpty", Boolean.valueOf(userFacade.isAddressBookEmpty()));
		model.addAttribute("isDefaultAddress", Boolean.FALSE);
		storeCmsPageInModel(model, getContentPageForLabelOrId(ADD_EDIT_ADDRESS_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(ADD_EDIT_ADDRESS_CMS_PAGE));

		final List<Breadcrumb> breadcrumbs = accountBreadcrumbBuilder.getBreadcrumbs(null);
		breadcrumbs.add(new Breadcrumb("/my-account/address-book", getMessageSource().getMessage("text.account.addressBook", null,
				getI18nService().getCurrentLocale()), null));
		breadcrumbs.add(new Breadcrumb("#", getMessageSource().getMessage("text.account.addressBook.addEditAddress", null,
				getI18nService().getCurrentLocale()), null));
		model.addAttribute("breadcrumbs", breadcrumbs);
		model.addAttribute("metaRobots", "no-index,no-follow");
		model.addAttribute("pageType", HeringPageType.ACCOUNTPAGE.name());
		return ControllerConstants.Views.Pages.Account.AccountEditAddressPage;
	}

	protected HeringAddressForm getPreparedAddressForm()
	{
		final CustomerData currentCustomerData = customerFacade.getCurrentCustomer();
		final HeringAddressForm addressForm = new HeringAddressForm();

		addressForm.setFirstName(currentCustomerData.getFirstName());
		addressForm.setLastName(currentCustomerData.getLastName());
		addressForm.setTitleCode(currentCustomerData.getTitleCode());
		addressForm.setCountryIso("DE");
		addressForm.setAddressType("RESIDENCIAL");
		
		/*final AddressData defaultAddress = getDefaultHeringCustomerFacade().getCurrentCustomer().getDefaultBillingAddress();
		if(defaultAddress != null)
		{
			addressForm.setPostcode(defaultAddress.getPostalCode());
			addressForm.setTownCity(defaultAddress.getTown());
			addressForm.setNumber(defaultAddress.getNumber());
			addressForm.setReference(defaultAddress.getReference());
			addressForm.setComplement(defaultAddress.getComplement());
			addressForm.setNeighborhood(defaultAddress.getDistrict());
			addressForm.setRegionIso(defaultAddress.getRegion().getIsocodeShort());
			addressForm.setLine1(defaultAddress.getLine1());
			addressForm.setPhone(defaultAddress.getDddPhone()+" "+defaultAddress.getPhone());
		}*/
		
		String first = StringUtils.defaultIfBlank(currentCustomerData.getFirstName(), "");
		String last = StringUtils.defaultIfBlank(currentCustomerData.getLastName(), "");
		addressForm.setReceiver((first + " " + last).trim());

		return addressForm;
	}

	@RequestMapping(value = "/add-address", method = RequestMethod.POST)
	@RequireHardLogIn
	public String addAddress(final HeringAddressForm addressForm, final BindingResult bindingResult, final Model model,
			final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		getAddressValidator().validate(addressForm, bindingResult, model);
		if (bindingResult.hasErrors())
		{
			//GlobalMessages.addErrorMessage(model, "form.global.error");
			storeCmsPageInModel(model, getContentPageForLabelOrId(ADD_EDIT_ADDRESS_CMS_PAGE));
			setUpMetaDataForContentPage(model, getContentPageForLabelOrId(ADD_EDIT_ADDRESS_CMS_PAGE));
			setUpAddressFormAfterError(addressForm, model);
			model.addAttribute("pageType", HeringPageType.ACCOUNTPAGE.name());
			return ControllerConstants.Views.Pages.Account.AccountEditAddressPage;
		}

		final AddressData newAddress = new AddressData();
		newAddress.setTitleCode(addressForm.getTitleCode());
		newAddress.setId(addressForm.getAddressId());
		newAddress.setFirstName(addressForm.getFirstName());
		newAddress.setLastName(addressForm.getLastName());
		newAddress.setLine1(addressForm.getLine1());
		newAddress.setTown(addressForm.getTownCity());
		newAddress.setPostalCode(addressForm.getPostcode());
		newAddress.setBillingAddress(addressForm.isBilling());
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

		newAddress.setVisibleInAddressBook(true);
		if (userFacade.isAddressBookEmpty())
		{
			newAddress.setDefaultAddress(true);
		}
		else
		{
			newAddress.setDefaultAddress(addressForm.getDefaultAddress() != null && addressForm.getDefaultAddress().booleanValue());
		}

		final AddressVerificationResult<AddressVerificationDecision> verificationResult = getAddressVerificationFacade()
				.verifyAddressData(newAddress);
		final boolean addressRequiresReview = getAddressVerificationResultHandler().handleResult(verificationResult, newAddress,
				model, redirectModel, bindingResult, getAddressVerificationFacade().isCustomerAllowedToIgnoreAddressSuggestions(),
				"checkout.multi.address.added");

		if (addressRequiresReview)
		{
			model.addAttribute("regions", getI18NFacade().getRegionsForCountryIso(addressForm.getCountryIso()));
			model.addAttribute("country", addressForm.getCountryIso());
			model.addAttribute("pageType", HeringPageType.ACCOUNTPAGE.name());
			storeCmsPageInModel(model, getContentPageForLabelOrId(ADD_EDIT_ADDRESS_CMS_PAGE));
			setUpMetaDataForContentPage(model, getContentPageForLabelOrId(ADD_EDIT_ADDRESS_CMS_PAGE));
			return ControllerConstants.Views.Pages.Account.AccountEditAddressPage;
		}

		userFacade.addAddress(newAddress);

		if (newAddress.isBillingAddress())
		{
			changeBillingAddress(newAddress.getId());
		}

		return REDIRECT_TO_ADDRESS_BOOK_PAGE;
	}

	protected void setUpAddressFormAfterError(final HeringAddressForm addressForm, final Model model)
	{
		model.addAttribute("countryData", checkoutFacade.getDeliveryCountries());
		model.addAttribute("titleData", userFacade.getTitles());
		model.addAttribute("addressTypes", getAddressTypes());
		model.addAttribute("addressBookEmpty", Boolean.valueOf(userFacade.isAddressBookEmpty()));
		model.addAttribute("isDefaultAddress", Boolean.valueOf(isDefaultAddress(addressForm.getAddressId())));
		if (addressForm.getCountryIso() != null)
		{
			model.addAttribute("regions", getI18NFacade().getRegionsForCountryIso(addressForm.getCountryIso()));
			model.addAttribute("country", addressForm.getCountryIso());
		}
	}

	@RequestMapping(value = "/edit-address/" + ADDRESS_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	@RequireHardLogIn
	public String editAddress(@PathVariable("addressCode") final String addressCode, final Model model)
			throws CMSItemNotFoundException
	{
		final HeringAddressForm addressForm = new HeringAddressForm();
		model.addAttribute("countryData", checkoutFacade.getDeliveryCountries());
		model.addAttribute("titleData", userFacade.getTitles());
		model.addAttribute("addressTypes", getAddressTypes());
		model.addAttribute("heringAddressForm", addressForm);
		model.addAttribute("addressBookEmpty", Boolean.valueOf(userFacade.isAddressBookEmpty()));

		for (final AddressData addressData : userFacade.getAddressBook())
		{
			if (addressData.getId() != null && addressData.getId().equals(addressCode))
			{
				model.addAttribute("regions", getI18NFacade().getRegionsForCountryIso(addressData.getCountry().getIsocode()));
				model.addAttribute("selectedRegion", addressData.getRegion());
				model.addAttribute("country", addressData.getCountry().getIsocode());
				model.addAttribute("addressData", addressData);

				addressForm.setAddressId(addressData.getId());
				if (StringUtils.isNotBlank(addressData.getFirstName()))
				{
					addressForm.setFirstName(addressData.getFirstName());
				}

				if (StringUtils.isNotBlank(addressData.getLastName()))
				{
					addressForm.setLastName(addressData.getLastName());
				}


				if (StringUtils.isNotBlank(addressData.getPhone()))
				{
					addressForm.setPhone(concatenPhone(addressData.getPhone(), addressData.getDddPhone()));
				}

				if (StringUtils.isNotBlank(addressData.getCelPhone()))
				{
					addressForm.setCelPhone(concatenPhone(addressData.getCelPhone(), addressData.getDddCelPhone()));
				}

				if (StringUtils.isNotBlank(addressData.getReceiver()))
				{
					addressForm.setReceiver(addressData.getReceiver());
				}

				addressForm.setPostcode(addressData.getPostalCode());
				addressForm.setTitleCode(addressData.getTitleCode());
				addressForm.setLine1(addressData.getLine1());
				addressForm.setNumber(addressData.getNumber());
				addressForm.setComplement(addressData.getComplement());
				addressForm.setNeighborhood(addressData.getDistrict());
				addressForm.setTownCity(addressData.getTown());
				addressForm.setRegionIso(addressData.getRegion().getIsocode());
				addressForm.setBilling(addressData.isBillingAddress());

				if (StringUtils.isNotBlank(addressData.getReference()))
				{
					addressForm.setReference(addressData.getReference());
				}

				if (addressData.getType() != null)
				{
					addressForm.setAddressType(addressData.getType().getCode());
				}

				addressForm.setCountryIso(addressData.getCountry().getIsocode());

				if (isDefaultAddress(addressData.getId()))
				{
					addressForm.setDefaultAddress(Boolean.TRUE);
					model.addAttribute("isDefaultAddress", Boolean.TRUE);
				}
				else
				{
					addressForm.setDefaultAddress(Boolean.FALSE);
					model.addAttribute("isDefaultAddress", Boolean.FALSE);
				}
				break;
			}
		}

		storeCmsPageInModel(model, getContentPageForLabelOrId(ADD_EDIT_ADDRESS_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(ADD_EDIT_ADDRESS_CMS_PAGE));

		final List<Breadcrumb> breadcrumbs = accountBreadcrumbBuilder.getBreadcrumbs(null);
		breadcrumbs.add(new Breadcrumb("/my-account/address-book", getMessageSource().getMessage("text.account.addressBook", null,
				getI18nService().getCurrentLocale()), null));
		breadcrumbs.add(new Breadcrumb("#", getMessageSource().getMessage("text.account.addressBook.addEditAddress", null,
				getI18nService().getCurrentLocale()), null));
		model.addAttribute("breadcrumbs", breadcrumbs);
		model.addAttribute("metaRobots", "no-index,no-follow");
		model.addAttribute("edit", Boolean.TRUE);
		model.addAttribute("pageType", HeringPageType.ACCOUNTPAGE.name());
		return ControllerConstants.Views.Pages.Account.AccountEditAddressPage;
	}

	/**
	 * Method checks if address is set as default
	 * 
	 * @param addressId
	 *           - identifier for address to check
	 * @return true if address is default, false if address is not default
	 */
	protected boolean isDefaultAddress(final String addressId)
	{
		final AddressData defaultAddress = userFacade.getDefaultAddress();
		return (defaultAddress != null && defaultAddress.getId() != null && defaultAddress.getId().equals(addressId));
	}

	@RequestMapping(value = "/edit-address/" + ADDRESS_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.POST)
	@RequireHardLogIn
	public String editAddress(final HeringAddressForm addressForm, final BindingResult bindingResult, final Model model,
			final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		getAddressValidator().validate(addressForm, bindingResult, model);
		if (bindingResult.hasErrors())
		{
			//GlobalMessages.addErrorMessage(model, "form.global.error");
			storeCmsPageInModel(model, getContentPageForLabelOrId(ADD_EDIT_ADDRESS_CMS_PAGE));
			setUpMetaDataForContentPage(model, getContentPageForLabelOrId(ADD_EDIT_ADDRESS_CMS_PAGE));
			setUpAddressFormAfterError(addressForm, model);
			return ControllerConstants.Views.Pages.Account.AccountEditAddressPage;
		}

		model.addAttribute("metaRobots", "no-index,no-follow");

		final AddressData newAddress = new AddressData();
		newAddress.setTitleCode(addressForm.getTitleCode());
		newAddress.setId(addressForm.getAddressId());
		newAddress.setFirstName(addressForm.getFirstName());
		newAddress.setLastName(addressForm.getLastName());
		newAddress.setLine1(addressForm.getLine1());
		newAddress.setTown(addressForm.getTownCity());
		newAddress.setPostalCode(addressForm.getPostcode());
		newAddress.setBillingAddress(addressForm.isBilling());
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

		//newAddress.setBillingAddress(false);

		newAddress.setShippingAddress(true);
		newAddress.setVisibleInAddressBook(true);

		if (Boolean.TRUE.equals(addressForm.getDefaultAddress()) || userFacade.getAddressBook().size() <= 1)
		{
			newAddress.setDefaultAddress(true);
			newAddress.setVisibleInAddressBook(true);
		}

		final AddressVerificationResult<AddressVerificationDecision> verificationResult = getAddressVerificationFacade()
				.verifyAddressData(newAddress);
		final boolean addressRequiresReview = getAddressVerificationResultHandler().handleResult(verificationResult, newAddress,
				model, redirectModel, bindingResult, getAddressVerificationFacade().isCustomerAllowedToIgnoreAddressSuggestions(),
				"checkout.multi.address.updated");

		if (addressRequiresReview)
		{
			model.addAttribute("regions", getI18NFacade().getRegionsForCountryIso(addressForm.getCountryIso()));
			model.addAttribute("country", addressForm.getCountryIso());
			model.addAttribute("edit", Boolean.TRUE);
			model.addAttribute("pageType", HeringPageType.ACCOUNTPAGE.name());
			storeCmsPageInModel(model, getContentPageForLabelOrId(ADD_EDIT_ADDRESS_CMS_PAGE));
			setUpMetaDataForContentPage(model, getContentPageForLabelOrId(ADD_EDIT_ADDRESS_CMS_PAGE));
			return ControllerConstants.Views.Pages.Account.AccountEditAddressPage;
		}
		
		AddressData toRemove = new AddressData();
		toRemove.setId(newAddress.getId());
		userFacade.removeAddress(toRemove);
		
		userFacade.addAddress(newAddress);
		
//		userFacade.editAddress(newAddress);

		if (newAddress.isBillingAddress())
		{
			changeBillingAddress(newAddress.getId());
		}

		return REDIRECT_TO_ADDRESS_BOOK_PAGE;
	}

	@RequestMapping(value = "/select-suggested-address", method = RequestMethod.POST)
	public String doSelectSuggestedAddress(final HeringAddressForm addressForm, final RedirectAttributes redirectModel)
	{
		final Set<String> resolveCountryRegions = org.springframework.util.StringUtils.commaDelimitedListToSet(Config
				.getParameter("resolve.country.regions"));

		final AddressData selectedAddress = new AddressData();
		selectedAddress.setTitleCode(addressForm.getTitleCode());
		selectedAddress.setId(addressForm.getAddressId());
		selectedAddress.setFirstName(addressForm.getFirstName());
		selectedAddress.setLastName(addressForm.getLastName());
		selectedAddress.setLine1(addressForm.getLine1());
		selectedAddress.setLine2(addressForm.getLine2());
		selectedAddress.setTown(addressForm.getTownCity());
		selectedAddress.setPostalCode(addressForm.getPostcode());
		selectedAddress.setBillingAddress(false);
		selectedAddress.setShippingAddress(true);
		selectedAddress.setVisibleInAddressBook(true);

		final CountryData countryData = i18NFacade.getCountryForIsocode(addressForm.getCountryIso());
		selectedAddress.setCountry(countryData);

		if (resolveCountryRegions.contains(countryData.getIsocode()))
		{
			if (addressForm.getRegionIso() != null && !StringUtils.isEmpty(addressForm.getRegionIso()))
			{
				final RegionData regionData = getI18NFacade().getRegion(addressForm.getCountryIso(), addressForm.getRegionIso());
				selectedAddress.setRegion(regionData);
			}
		}

		if (resolveCountryRegions.contains(countryData.getIsocode()))
		{
			if (addressForm.getRegionIso() != null && !StringUtils.isEmpty(addressForm.getRegionIso()))
			{
				final RegionData regionData = getI18NFacade().getRegion(addressForm.getCountryIso(), addressForm.getRegionIso());
				selectedAddress.setRegion(regionData);
			}
		}

		if (Boolean.TRUE.equals(addressForm.getEditAddress()))
		{
			selectedAddress.setDefaultAddress(Boolean.TRUE.equals(addressForm.getDefaultAddress())
					|| userFacade.getAddressBook().size() <= 1);
			userFacade.editAddress(selectedAddress);
		}
		else
		{
			selectedAddress.setDefaultAddress(Boolean.TRUE.equals(addressForm.getDefaultAddress())
					|| userFacade.isAddressBookEmpty());
			userFacade.addAddress(selectedAddress);
		}

		GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, "account.confirmation.address.added");

		return REDIRECT_TO_ADDRESS_BOOK_PAGE;
	}

	@RequestMapping(value = "/remove-address/" + ADDRESS_CODE_PATH_VARIABLE_PATTERN, method =
	{ RequestMethod.GET, RequestMethod.POST })
	@RequireHardLogIn
	public String removeAddress(@PathVariable("addressCode") final String addressCode, final RedirectAttributes redirectModel)
	{
		final AddressData addressData = new AddressData();
		addressData.setId(addressCode);
		userFacade.removeAddress(addressData);

		GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, "account.confirmation.address.removed");
		return REDIRECT_TO_ADDRESS_BOOK_PAGE;
	}

	@RequestMapping(value = "/set-default-address/" + ADDRESS_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	@RequireHardLogIn
	public String setDefaultAddress(@PathVariable("addressCode") final String addressCode, final RedirectAttributes redirectModel)
	{
		final AddressData addressData = new AddressData();
		addressData.setDefaultAddress(true);
		addressData.setVisibleInAddressBook(true);
		addressData.setId(addressCode);
		userFacade.setDefaultAddress(addressData);
		GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER,
				"account.confirmation.default.address.changed");
		return REDIRECT_TO_ADDRESS_BOOK_PAGE;
	}

	@RequestMapping(value = "/set-default-billing-address/" + ADDRESS_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	@RequireHardLogIn
	public String setDefaultBillingAddress(@PathVariable("addressCode") final String addressCode,
			final RedirectAttributes redirectModel)
	{
		if (changeBillingAddress(addressCode))
		{
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER,
					"account.confirmation.billing.address.changed");
		}

		return REDIRECT_TO_ADDRESS_BOOK_PAGE;
	}

	/**
	 * @param addressCode
	 * @param redirectModel
	 */
	private boolean changeBillingAddress(final String addressCode)
	{
		boolean result = false;
		for (final AddressData addressData : userFacade.getAddressBook())
		{
			if (addressData.getId() != null && addressData.getId().equals(addressCode) && !addressData.isBillingAddress())
			{
				addressData.setBillingAddress(true);
				addressData.setId(addressCode);
				userFacade.editAddress(addressData);

				result = true;
			}
			else if (addressData.getId() != null && !addressData.getId().equals(addressCode) && addressData.isBillingAddress())
			{
				addressData.setBillingAddress(false);
				addressData.setId(addressData.getId());
				userFacade.editAddress(addressData);
			}
		}
		return result;
	}

	
	@RequestMapping(value = "/payment-details", method = RequestMethod.GET)
	@RequireHardLogIn
	public String paymentDetails(final Model model) 
			throws CMSItemNotFoundException {
		model.addAttribute("customerData", customerFacade.getCurrentCustomer());
		model.addAttribute("paymentInfoData", userFacade.getCCPaymentInfos(false));
		storeCmsPageInModel(model, getContentPageForLabelOrId(
				PAYMENT_DETAILS_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(
				ADD_EDIT_ADDRESS_CMS_PAGE));
		model.addAttribute("breadcrumbs", accountBreadcrumbBuilder
				.getBreadcrumbs("text.account.paymentDetails"));
		model.addAttribute("metaRobots", "no-index,no-follow");
		model.addAttribute("pageType", HeringPageType.ACCOUNTPAGE.name());
		return ControllerConstants.Views.Pages.Account.AccountPaymentInfoPage;
	}

	
	@RequestMapping(value = "/set-default-payment-details",
			method = RequestMethod.POST)
	@RequireHardLogIn
	public String setDefaultPaymentDetails(
			@RequestParam final String paymentInfoId)	{
		CCPaymentInfoData paymentInfoData = null;
		if (StringUtils.isNotBlank(paymentInfoId)) {
			paymentInfoData = userFacade.getCCPaymentInfoForCode(paymentInfoId);
		}
		userFacade.setDefaultPaymentInfo(paymentInfoData);
		return REDIRECT_TO_PAYMENT_INFO_PAGE;
	}

	
	@RequestMapping(value = "/edit-payment-details", method = RequestMethod.GET)
	@RequireHardLogIn
	public String addPaymentMethod(final Model model,
			@RequestParam(value = "heringPaymentDetailsForm", required = false)
					HeringPaymentDetailsForm heringPaymentDetailsForm,
			final RedirectAttributes redirectAttributes)
			throws CMSItemNotFoundException {
		if(heringPaymentDetailsForm == null) 
		{
			heringPaymentDetailsForm = getPreparedHeringPaymentDetailsForm();
		}
		model.addAttribute("customerData", customerFacade.getCurrentCustomer());
		model.addAttribute("heringPaymentDetailsForm", heringPaymentDetailsForm);
		model.addAttribute("regions", getI18NFacade()
				.getRegionsForCountryIso("DE"));
		storeCmsPageInModel(model, getContentPageForLabelOrId(
				PAYMENT_DETAILS_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(
				PAYMENT_DETAILS_CMS_PAGE));
		model.addAttribute("breadcrumbs", accountBreadcrumbBuilder
				.getBreadcrumbs("text.account.paymentDetails"));
		model.addAttribute("metaRobots", "no-index,no-follow");
		model.addAttribute("pageType", HeringPageType.ACCOUNTPAGE.name());
		model.addAttribute("expiryMonths", getExpiryMonths());
		model.addAttribute("expiryYears", getExpiryYears());
		return ControllerConstants.Views.Pages.Account.AccountPaymentInfoEditPage;
	}
	
	
	@RequestMapping(value="/edit-payment-details", method=RequestMethod.POST)
	@RequireHardLogIn
	public String editPaymentMethod(final HeringPaymentDetailsForm heringPaymentDetailsForm, 	final BindingResult bindingResult, final Model model, final RedirectAttributes redirectAttributes) throws CMSItemNotFoundException
	{
		if(bindingResult.hasFieldErrors()) 
		{
			storeCmsPageInModel(model, getContentPageForLabelOrId(EDIT_PAYMENT_DETAILS_CMS_PAGE));
			setUpMetaDataForContentPage(model, getContentPageForLabelOrId(EDIT_PAYMENT_DETAILS_CMS_PAGE));
			model.addAttribute("heringPaymentDetailsForm",	heringPaymentDetailsForm);
			model.addAttribute("customerData", customerFacade.getCurrentCustomer());
			model.addAttribute("heringPaymentDetailsForm",	heringPaymentDetailsForm);
			model.addAttribute("regions", getI18NFacade().getRegionsForCountryIso("DE"));
			model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.paymentDetails"));
			model.addAttribute("metaRobots", "no-index,no-follow");
			model.addAttribute("pageType", HeringPageType.ACCOUNTPAGE.name());
			GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.CONF_MESSAGES_HOLDER, "account.error.paymentDetails.added");
			return ControllerConstants.Views.Pages.Account.AccountPaymentInfoEditPage;
		}
		heringPaymentDetailsForm.setSaveInAccount(Boolean.TRUE);
		final HeringAddressForm addressForm = heringPaymentDetailsForm.getBillingAddress();
		final CCPaymentInfoData newPaymentInfo = new CCPaymentInfoData();
		
		final AddressData newBillingAddress = new AddressData();
		RegionData region = new RegionData();
		region.setIsocodeShort(addressForm.getRegionIso());
		region.setIsocode(getCountryData().getIsocode()+"-"+addressForm.getRegionIso());
		newBillingAddress.setLine1(addressForm.getLine1());
		newBillingAddress.setNumber(addressForm.getNumber());
		newBillingAddress.setTown(addressForm.getTownCity());
		newBillingAddress.setPostalCode(addressForm.getPostcode());
		newBillingAddress.setCountry(getCountryData());
		newBillingAddress.setType(TipoDeEndereco.RESIDENCIAL);
		
		String ddd, phone;
		if(StringUtils.isNotEmpty(addressForm.getPhone()))
		{ 
			ddd = addressForm.getPhone().split(" ")[0];
			phone = addressForm.getPhone().split(" ")[1];
			
			newBillingAddress.setDddPhone(ddd);
			newBillingAddress.setPhone(phone);
		}
		newBillingAddress.setCelPhone(addressForm.getCelPhone());
		newBillingAddress.setComplement(addressForm.getComplement());
		newBillingAddress.setDistrict(addressForm.getNeighborhood());
		newBillingAddress.setRegion(region);
		
		newPaymentInfo.setAccountHolderName(heringPaymentDetailsForm.getNameOnCard());
		newPaymentInfo.setCardBrand(heringPaymentDetailsForm.getCardBrand());
		newPaymentInfo.setCardNumber(heringPaymentDetailsForm.getCardNumber());
		newPaymentInfo.setCardType(heringPaymentDetailsForm.getCardTypeCode());
		newPaymentInfo.setCv2Number(heringPaymentDetailsForm.getCv2Number());
		newPaymentInfo.setExpiryMonth(heringPaymentDetailsForm.getExpiryMonth());
		newPaymentInfo.setExpiryYear(heringPaymentDetailsForm.getExpiryYear());
		newPaymentInfo.setInstallment(heringPaymentDetailsForm.getInstalment());
		newPaymentInfo.setIssueNumber(heringPaymentDetailsForm.getIssueNumber());
		newPaymentInfo.setBillingAddress(newBillingAddress);
		//userFacade.registerCustomerPaymentInfo(newPaymentInfo);
		
		model.addAttribute("regions", getI18NFacade().getRegionsForCountryIso("DE"));
		storeCmsPageInModel(model, getContentPageForLabelOrId(PAYMENT_DETAILS_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(PAYMENT_DETAILS_CMS_PAGE));
		model.addAttribute("customerData", customerFacade.getCurrentCustomer());
		model.addAttribute("heringPaymentDetailsForm", heringPaymentDetailsForm);
		model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.paymentDetails"));
		model.addAttribute("metaRobots", "no-index,no-follow");
		model.addAttribute("pageType", HeringPageType.ACCOUNTPAGE.name());
		
		final CCPaymentInfoData newPaymentSubscription = getDefaultHeringCheckoutFacade().createPaymentSubscription(newPaymentInfo);
		if (newPaymentSubscription != null && StringUtils.isNotBlank(newPaymentSubscription.getSubscriptionId()))
		{
			if (Boolean.TRUE.equals(heringPaymentDetailsForm.getSaveInAccount()) && getUserFacade().getCCPaymentInfos(true).size() <= 1)
			{
				getUserFacade().setDefaultPaymentInfo(newPaymentSubscription);
			}
			getDefaultHeringCheckoutFacade().setPaymentDetails(newPaymentSubscription.getId());
		}
		else
		{
			GlobalMessages.addErrorMessage(model, "checkout.multi.paymentMethod.createSubscription.failedMsg");
			return ControllerConstants.Views.Pages.Account.AccountPaymentInfoEditPage;
		}
		model.addAttribute("paymentId", newPaymentSubscription.getId());
		
		String paymentMode = "CreditCard";
		final CartData cartData = getDefaultHeringCheckoutFacade().getCheckoutCart();
		
		CCPaymentInfoData paymentInfoData = new CCPaymentInfoData();
		
		cartData.setPaymentInfo(paymentInfoData);
		model.addAttribute("cartData", cartData);
		
		GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.CONF_MESSAGES_HOLDER, "account.confirmation.paymentDetails.added");
		return REDIRECT_TO_PAYMENT_INFO_PAGE;
	}
	
	/**
	 * @return the userFacade
	 */
	public HeringUserFacade getUserFacade()
	{
		return userFacade;
	}
	
	/**
	 * Define a <code>CountryData</code> object.
	 * @return
	 */
	private CountryData getCountryData()
	{
		final CountryData data = new CountryData();
		data.setIsocode("DE");
		data.setName("Brasil");
		return data;
	}
	
	
	@RequestMapping(value = "/remove-payment-method", method = RequestMethod.POST)
	@RequireHardLogIn
	public String removePaymentMethod(final Model model,
			@RequestParam(value = "paymentInfoId") final String paymentMethodId,
			final RedirectAttributes redirectAttributes) 
			throws CMSItemNotFoundException {
		userFacade.unlinkCCPaymentInfo(paymentMethodId);
		GlobalMessages.addFlashMessage(redirectAttributes,
				GlobalMessages.CONF_MESSAGES_HOLDER,
				"text.account.profile.paymentCart.removed");
		return REDIRECT_TO_PAYMENT_INFO_PAGE;
	}

	
	private String concatenPhone(String phone, String ddd)
	{
		return ddd + phone;
	}

	
	private DefaultHeringCustomerFacade getDefaultHeringCustomerFacade()
	{
		return (DefaultHeringCustomerFacade) customerFacade;
	}

	// wishlist entries
	@RequestMapping(value = "/my-wishlist", method = RequestMethod.GET)
	@RequireHardLogIn
	public String wishlistEntries(@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "sort", required = false) final String sortCode, final Model model,
			final HttpServletRequest request) throws CMSItemNotFoundException
	{

		// Handle paged search results
		final PaginationData pageableData = createEmptyPagination();
		final SearchPageData<HeringWishlistEntryData> searchPageData = heringWishlistFacade.getPagedWishlistEntries();
		final HeringWishlistData wishlist = heringWishlistFacade.getDefaultWishlist();

		pageableData.setTotalNumberOfResults(searchPageData.getResults().size());
		searchPageData.setPagination(pageableData);

		final UpdateWishlistForm updateWishlistForm = new UpdateWishlistForm();
		updateWishlistForm.setDescription(wishlist.getDescription());
		updateWishlistForm.setName(wishlist.getName());
		updateWishlistForm.setPublicName(wishlist.getPublicName());
		updateWishlistForm.setEntriesList(searchPageData.getResults());
		updateWishlistForm.entryToDesireds(searchPageData.getResults());
		updateWishlistForm.setBirthday(customerFacade.getCurrentCustomer().getBirthday());
		final String urlPublicWishlistShare = request.getScheme() + ":" + request.getLocalName() + ":" + request.getLocalPort()
				+ request.getContextPath() + "/w/" + heringWishlistFacade.getWishlistPK();
		populateModel(model, searchPageData, showMode);
		final String urlPublicWishlist = heringWishlistFacade.getWishlistPK();

		storeCmsPageInModel(model, getContentPageForLabelOrId(WISHLIST_ENTRIES_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(WISHLIST_ENTRIES_CMS_PAGE));
		model.addAttribute("updateWishlistForm", updateWishlistForm);
		model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.wishlist"));
		model.addAttribute("metaRobots", "no-index,no-follow");
		model.addAttribute("urlPublicWishlist", urlPublicWishlist);
		model.addAttribute("urlPublicWishlistShare", urlPublicWishlistShare);
		model.addAttribute("pageType", HeringPageType.ACCOUNTPAGE.name());
		return ControllerConstants.Views.Pages.Account.AccountWishlistEntriesPage;
	}

	// end wishlist entries
	@RequestMapping(value = "/update-wishlist", method = RequestMethod.POST)
	@RequireHardLogIn
	public String updateWishlist(@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "sort", required = false) final String sortCode, final Model model,
			final HttpServletRequest request, final UpdateWishlistForm updateWishlistForm, final BindingResult bindingResult)
			throws UnsupportedEncodingException, CMSItemNotFoundException, DuplicateUidException
	{

		if (updateWishlistForm.getBirthday() == null)
		{
			updateWishlistForm.setBirthday(customerFacade.getCurrentCustomer().getBirthday());
		}
		getWishlistValidator().validate(updateWishlistForm, bindingResult);

		if (bindingResult.hasErrors())
		{
			GlobalMessages.addErrorMessage(model, "form.global.error");

			final PaginationData pageableData = createEmptyPagination();
			final SearchPageData<HeringWishlistEntryData> searchPageData = heringWishlistFacade.getPagedWishlistEntries();

			pageableData.setTotalNumberOfResults(searchPageData.getResults().size());
			searchPageData.setPagination(pageableData);


			populateModel(model, searchPageData, showMode);

			model.addAttribute("updateWishlistForm", updateWishlistForm);
			storeCmsPageInModel(model, getContentPageForLabelOrId(WISHLIST_ENTRIES_CMS_PAGE));
			setUpMetaDataForContentPage(model, getContentPageForLabelOrId(WISHLIST_ENTRIES_CMS_PAGE));
			model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.wishlist"));
			model.addAttribute("pageType", HeringPageType.ACCOUNTPAGE.name());
			return ControllerConstants.Views.Pages.Account.AccountWishlistEntriesPage;
		}

		updateWishlistForm.desiredsToEntry();
		final HeringWishlistData wishData = new HeringWishlistData();
		wishData.setDescription(updateWishlistForm.getDescription());
		wishData.setName(updateWishlistForm.getName());
		wishData.setPublicName(updateWishlistForm.getPublicName());
		wishData.setEntries(updateWishlistForm.getEntriesList());



		heringWishlistFacade.updateWishlist(wishData);
		final CustomerData customer = customerFacade.getCurrentCustomer();
		customer.setBirthday(updateWishlistForm.getBirthday());
		customerFacade.updateProfile(customer);

		return REDIRECT_MY_ACCOUNT + "/my-wishlist";
	}
	
	
	private HeringPaymentDetailsForm getPreparedHeringPaymentDetailsForm() { 
			HeringPaymentDetailsForm paymentDetailsForm = 
				new HeringPaymentDetailsForm();
		final CustomerData currentCustomerData = customerFacade.getCurrentCustomer();
		
		final HeringAddressForm addressForm = getPreparedAddressForm();
		paymentDetailsForm.setNameOnCard(currentCustomerData.getFirstName() + " "
				+ currentCustomerData.getLastName());
		paymentDetailsForm.setCardNumber(null);
		paymentDetailsForm.setCv2Number(null);
		paymentDetailsForm.setIssueNumber(null);
		paymentDetailsForm.setExpiryMonth(null);
		paymentDetailsForm.setExpiryYear(null);
		paymentDetailsForm.setInstalment("1");
		paymentDetailsForm.setCardTypeCode(null);
		paymentDetailsForm.setBillingAddress(addressForm);
		paymentDetailsForm.setNewBillingAddress(false);
		paymentDetailsForm.setSaveInAccount(Boolean.TRUE);
		return paymentDetailsForm;
	}
	
	
    public Collection<SelectOption> getExpiryMonths() {
        final Collection<SelectOption> months = new ArrayList<SelectOption>();
        for(int i = 1; i < 13; i++) {
            months.add(new SelectOption(String.valueOf(i), String.valueOf(i)));
        }
        return months;
    }
	
	
    public Collection<SelectOption> getExpiryYears() {
        final Collection<SelectOption> years = new ArrayList<SelectOption>();
        final Integer currentYear = new GregorianCalendar().get(Calendar.YEAR);
        for (int i = currentYear; i < (currentYear + 11); i++) {
            years.add(new SelectOption(String.valueOf(i), String.valueOf(i)));
        }
        return years;
    }
    
    
    @RequestMapping(value = "/get-by-zipcode", method = RequestMethod.GET)
    public @ResponseBody String getAddressByZipcode(
            @RequestParam("zipcode") final String zipcode) {
        String serviceUrl = "";
        String result ="";
        if(StringUtils.isBlank(zipcode)) {
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
        serviceUrl = "http://api.postmon.com.br/v1/cep/"+zipcode;//+"?format=xml";
        try {
            URL url = new URL(serviceUrl);
            InputStream inputStream = url.openStream();
            InputStreamReader inputStreamReader = new InputStreamReader(
                    inputStream);
            BufferedReader buferReader = new BufferedReader(inputStreamReader);
            result = buferReader.readLine();
        } catch (Exception e) {
            LOG.info("Erro ao buscar endere��o por cep.", e);
        }
        return result;
    }
    
    /**
	 * @return the defaultHeringCheckoutFacade
	 */
	public DefaultHeringCheckoutFacade getDefaultHeringCheckoutFacade()
	{
		return defaultHeringCheckoutFacade;
	}
	
	public TypeService getTypeService() {
		return typeService;
	}

}