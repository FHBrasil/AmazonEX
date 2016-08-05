package de.fliegersoftware.amazon.payment.addon.facades;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import de.fliegersoftware.amazon.core.constants.AmazoncoreConstants;
import de.fliegersoftware.amazon.core.model.AmazonPaymentPaymentInfoModel;
import de.fliegersoftware.amazon.payment.services.AmazonCommerceCheckoutService;
import de.hybris.platform.acceleratorfacades.order.impl.DefaultAcceleratorCheckoutFacade;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commerceservices.enums.CustomerType;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.order.PaymentModeService;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.servicelayer.model.ModelService;


/**
 * @author taylor.savegnago
 *
 */
public class AmazonCheckoutFacade extends DefaultAcceleratorCheckoutFacade
{
	private static final Logger LOG = Logger.getLogger(AmazonCheckoutFacade.class);

	private OrderService orderService;
	private PaymentModeService paymentModeService;
	private ModelService modelService;
	
	@Resource
	private Populator<AddressData, AddressModel> amazonAddressReversePopulator;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.commercefacades.order.impl.DefaultCheckoutFacade#authorizePayment(java.lang.String)
	 */
	@Override
	public boolean authorizePayment(final String securityCode)
	{
		final CartModel cartModel = getCart();
		if (checkIfCurrentUserIsTheCartUser())
		{
			if(cartModel.getPaymentInfo() instanceof AmazonPaymentPaymentInfoModel
					&& StringUtils.isNotBlank(((AmazonPaymentPaymentInfoModel)cartModel.getPaymentInfo()).getAmazonOrderReferenceId())) {
					final PaymentTransactionEntryModel paymentTransactionEntryModel = getCommerceCheckoutService().authorizeAmazonPayment(cartModel, AmazoncoreConstants.PAYMENT_PROVIDER_NAME);
	
					return paymentTransactionEntryModel != null
							&& TransactionStatus.ACCEPTED.name().equals(paymentTransactionEntryModel.getTransactionStatus());
			}
		}
		return super.authorizePayment(securityCode);
	}

	@Override
	public boolean setPaymentDetails(final String paymentInfoId)
	{
		validateParameterNotNullStandardMessage("paymentInfoId", paymentInfoId);

		final CartModel cartModel = getCart();
		if (checkIfCurrentUserIsTheCartUser())
		{
			if (StringUtils.isNotBlank(paymentInfoId))
			{
				final AmazonPaymentPaymentInfoModel amazonPaymentPaymentInfoModel = getModelService().create(AmazonPaymentPaymentInfoModel.class);
				amazonPaymentPaymentInfoModel.setAmazonOrderReferenceId(paymentInfoId);
				amazonPaymentPaymentInfoModel.setCode(paymentInfoId);
				amazonPaymentPaymentInfoModel.setUser(getCurrentUserForCheckout());
				PaymentModeModel paymentMode = getPaymentModeService().getPaymentModeForCode("amazon");
				cartModel.setPaymentMode(paymentMode);
				return getCommerceCheckoutService().setPaymentInfo(cartModel, amazonPaymentPaymentInfoModel);
			}
		}

		return false;
	}

	public String createOrderCodeFromCart() {
		try {
			CartModel cartModel = getCart();
			OrderModel orderModel = getOrderService().createOrderFromCart(cartModel);
			cartModel.setPreCreatedOrderCode(orderModel.getCode());
			getModelService().save(cartModel);
			return orderModel.getCode();
		} catch (InvalidCartException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setSellerOrderId(String orderId) {
		CartModel cartModel = getCart();
		cartModel.setPreCreatedOrderCode(orderId);
		getModelService().save(cartModel);
	}
	
	public boolean isDeliveryCountrySupported(CountryData deliveryCountry) {
		if (deliveryCountry != null) {
			List<CountryData> deliveryCountriesSupported = super.getDeliveryCountries();
			for (CountryData countryData : deliveryCountriesSupported) {
				if (countryData.getIsocode().equalsIgnoreCase(deliveryCountry.getIsocode())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean setDeliveryAddress(AddressData addressData) {
		// if address isn't in the address book, it doesn't validate
		CustomerModel currentCustomer = getCheckoutCustomerStrategy().getCurrentUserForCheckout();
		if(!getCheckoutCustomerStrategy().isAnonymousCheckout()
				|| !getUserService().getAnonymousUser().equals(currentCustomer)) {
			// Create the new address model
			AddressModel address = getModelService().create(AddressModel.class);
			getAmazonAddressReversePopulator().populate(addressData, address);

			// Store the address against the user
			address = saveDeliveryAddressIfNew(currentCustomer, address);

			// Update the address ID in the newly created address
			if(address.getPk() != null)
				addressData.setId(address.getPk().toString());
		}
		return super.setDeliveryAddress(addressData);
	}

	@Override
	protected void afterPlaceOrder(CartModel cartModel, OrderModel orderModel) {
		super.afterPlaceOrder(cartModel, orderModel);
		if (cartModel.getUser() instanceof CustomerModel) {
			CustomerModel customer = (CustomerModel) cartModel.getUser();
			if(!CustomerType.GUEST.equals(customer.getType())) {
				saveDeliveryAddressIfNew(customer, orderModel.getDeliveryAddress());
				saveBillingAddressIfNew(customer, orderModel.getPaymentInfo().getBillingAddress());
			}
		}
	}

	private AddressModel saveDeliveryAddressIfNew(CustomerModel customer, AddressModel address) {
		return saveAddressIfNew(customer, address, false);
	}
	private AddressModel saveBillingAddressIfNew(CustomerModel customer, AddressModel address) {
		return saveAddressIfNew(customer, address, true);
	}
	private AddressModel saveAddressIfNew(CustomerModel customer, AddressModel address, boolean isBillingAddress) {
		if(address == null)
			return null;

		for(AddressModel existingAddress : getCustomerAccountService().getAddressBookEntries(customer)) {
			if(StringUtils.equalsIgnoreCase(address.getLine1(), existingAddress.getLine1())
				&& StringUtils.equalsIgnoreCase(address.getLine2(), existingAddress.getLine2())
				&& StringUtils.equalsIgnoreCase(address.getFirstname(), existingAddress.getFirstname())
				&& StringUtils.equalsIgnoreCase(address.getTown(), existingAddress.getTown())
				&& StringUtils.equalsIgnoreCase(address.getDistrict(), existingAddress.getDistrict())
				&& StringUtils.equalsIgnoreCase(address.getCompany(), existingAddress.getCompany())
				&& StringUtils.equalsIgnoreCase(address.getPobox(), existingAddress.getPobox())
				&& ((address.getCountry() == null && existingAddress.getCountry() == null)
					|| (address.getCountry() != null && existingAddress.getCountry() != null && ObjectUtils.equals(address.getCountry().getPk(), existingAddress.getCountry().getPk()))
					)
				&& StringUtils.equalsIgnoreCase(address.getPostalcode(), existingAddress.getPostalcode())
				&& StringUtils.equalsIgnoreCase(address.getPhone1(), existingAddress.getPhone1())
				&& customer.equals(existingAddress.getOwner())) {
				return existingAddress;
			}
		}

		AddressData addressData = getAddressConverter().convert(address);
		addressData.setBillingAddress(isBillingAddress);
		addressData.setPobox(address.getPobox());
		AddressModel addressModel = getModelService().create(AddressModel.class);
		getAmazonAddressReversePopulator().populate(addressData, addressModel);
		addressModel.setVisibleInAddressBook(true);
		addressModel.setOwner(customer);
		getCustomerAccountService().saveAddressEntry(customer, addressModel);
		return addressModel;
	}

	protected AmazonCommerceCheckoutService getCommerceCheckoutService()
	{
		return (AmazonCommerceCheckoutService) super.getCommerceCheckoutService();
	}

	protected OrderService getOrderService() {
		return orderService;
	}

	@Required
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	protected PaymentModeService getPaymentModeService() {
		return paymentModeService;
	}

	@Required
	public void setPaymentModeService(PaymentModeService paymentModeService) {
		this.paymentModeService = paymentModeService;
	}

	protected ModelService getModelService() {
		return modelService;
	}

	@Required
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	public Populator<AddressData, AddressModel> getAmazonAddressReversePopulator() {
		return amazonAddressReversePopulator;
	}

	public void setAmazonAddressReversePopulator(
			Populator<AddressData, AddressModel> amazonAddressReversePopulator) {
		this.amazonAddressReversePopulator = amazonAddressReversePopulator;
	}

	
}
