/**
 * 
 */
package br.hering.core.customer.impl;

import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.customer.impl.DefaultCustomerAccountService;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.dto.BillingInfo;
import de.hybris.platform.payment.dto.CardInfo;
import de.hybris.platform.payment.dto.NewSubscription;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.hering.core.customer.dao.HeringCustomerAccountDao;
import br.hering.core.enums.TipoDeEndereco;
import br.hering.core.payment.dto.HeringBillingInfo;
import br.hering.core.payment.dto.KpCardInfo;

import com.adyen.services.payment.AdyenPaymentService;
import com.flieger.payment.model.HeringDebitPaymentInfoModel;

import de.hybris.platform.util.Config;

/**
 * @author franthescollymaneira
 * @author Antony P
 * 
 */
public class DefaultKPCustomerAccountService extends DefaultCustomerAccountService implements KPCustomerAccountService
{
	private final Logger LOG = Logger.getLogger(getClass());
	private CartService cartService;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.commerceservices.customer.impl.DefaultCustomerAccountService#register(de.hybris.platform.core
	 * .model.user.CustomerModel, java.lang.String)
	 */
	public void updateProfile(CustomerModel customerModel, String titleCode, String name, String login, String cpfcnpj)
			throws DuplicateUidException
	{
		super.updateProfile(customerModel, titleCode, name, login);
		
		if (Config.getBoolean("fliegercommerce.feature.enable.cpf", false))
		{
			customerModel.setCpfcnpj(cpfcnpj);
		}
				
		getModelService().save(customerModel);
	}


	//	@Override
	//	public void register(final CustomerModel customerModel, final String password) throws DuplicateUidException
	//	{
	//		try
	//		{
	//			Date date = DateUtils.parseDate("18/09/1989", new String[]{"dd/MM/yyyy"}); 
	//			customerModel.setBirthday(date);
	//		
	//		}
	//		catch (ParseException e)
	//		{
	//			LOG.error(e);
	//		}
	//		
	//		super.register(customerModel, password);
	//	}

	@Override
	public CreditCardPaymentInfoModel createPaymentSubscription(final CustomerModel customerModel, final CardInfo cardInfo,
			final BillingInfo billing, final String titleCode, final String paymentProvider, final boolean saveInAccount)
	{

		ServicesUtil.validateParameterNotNull(customerModel, "Customer cannot be null");
		ServicesUtil.validateParameterNotNull(cardInfo, "CardInfo cannot be null");
		ServicesUtil.validateParameterNotNull(billing, "billingInfo cannot be null");
		ServicesUtil.validateParameterNotNull(paymentProvider, "PaymentProvider cannot be null");
		final CurrencyModel currencyModel = getCurrency(customerModel);
		ServicesUtil.validateParameterNotNull(currencyModel, "Customer session currency cannot be null");

		HeringBillingInfo billingInfo = (HeringBillingInfo) billing;

		final Currency currency = getI18nService().getBestMatchingJavaCurrency(currencyModel.getIsocode());

		final AddressModel billingAddress = (AddressModel) getModelService().create(AddressModel.class);
		if (StringUtils.isNotBlank(titleCode))
		{
			final TitleModel title = new TitleModel();
			title.setCode(titleCode);
			billingAddress.setTitle(getFlexibleSearchService().getModelByExample(title));
		}
		billingAddress.setFirstname(billingInfo.getFirstName());
		billingAddress.setLastname(billingInfo.getLastName());
		billingAddress.setLine1(billingInfo.getStreet1());
		billingAddress.setLine2(billingInfo.getStreet2());
		billingAddress.setTown(billingInfo.getCity());
		billingAddress.setPostalcode(billingInfo.getPostalCode());
		billingAddress.setCountry(getCommonI18NService().getCountry(billingInfo.getCountry()));
		billingAddress.setPhone1(billingInfo.getPhoneNumber());
		billingAddress.setDddPhone(billingInfo.getDddPhone());
		//billingAddress.setRegion(getCommonI18NService().getRegion(billingAddress.getCountry(), billingInfo.getRegionIso()));
		billingAddress.setDistrict(billingInfo.getDistrict());
		billingAddress.setStreetnumber(billingInfo.getNumber());
		billingAddress.setPontoDeReferencia(billingInfo.getReference());
		billingAddress.setTipoDeEndereco(TipoDeEndereco.valueOf(billingInfo.getAddressType()));
		billingAddress.setComplemento(billingInfo.getComplement());

		final String email = getCustomerEmailResolutionService().getEmailForCustomer(customerModel);
		billingAddress.setEmail(email);

		final String merchantTransactionCode = customerModel.getUid() + "-" + UUID.randomUUID();
		try
		{
			final NewSubscription subscription = getPaymentService().createSubscription(merchantTransactionCode, paymentProvider,
					currency, billingAddress, cardInfo);

			if (StringUtils.isNotBlank(subscription.getSubscriptionID()))
			{
				final CreditCardPaymentInfoModel cardPaymentInfoModel = (CreditCardPaymentInfoModel) getModelService().create(
						CreditCardPaymentInfoModel.class);
				cardPaymentInfoModel.setCode(customerModel.getUid() + "_" + UUID.randomUUID());
				cardPaymentInfoModel.setUser(customerModel);
				cardPaymentInfoModel.setSubscriptionId(subscription.getSubscriptionID());
				cardPaymentInfoModel.setNumber(getMaskedCardNumber(cardInfo.getCardNumber()));
				cardPaymentInfoModel.setType(cardInfo.getCardType());
				cardPaymentInfoModel.setCcOwner(cardInfo.getCardHolderFullName());
				cardPaymentInfoModel.setValidToMonth(String.format("%02d", new Object[]
				{ cardInfo.getExpirationMonth() }));

				final KpCardInfo card = (KpCardInfo) cardInfo;
				//cardPaymentInfoModel.setInstallment(new Integer(card.getInstallments()));
				cardPaymentInfoModel.setInstallment(1);
				cardPaymentInfoModel.setValidToYear(String.valueOf(cardInfo.getExpirationYear()));
				if (cardInfo.getIssueMonth() != null)
				{
					cardPaymentInfoModel.setValidFromMonth(String.valueOf(cardInfo.getIssueMonth()));
				}
				if (cardInfo.getIssueYear() != null)
				{
					cardPaymentInfoModel.setValidFromYear(String.valueOf(cardInfo.getIssueYear()));
				}

				cardPaymentInfoModel.setSubscriptionId(subscription.getSubscriptionID());
				cardPaymentInfoModel.setSaved(saveInAccount);
				if (!StringUtils.isEmpty(cardInfo.getIssueNumber()))
				{
					cardPaymentInfoModel.setIssueNumber(Integer.valueOf(cardInfo.getIssueNumber()));
				}

				billingAddress.setOwner(cardPaymentInfoModel);
				cardPaymentInfoModel.setBillingAddress(billingAddress);

				getModelService().saveAll(new Object[]
				{ billingAddress, cardPaymentInfoModel });
				getModelService().refresh(customerModel);

				addPaymentInfo(customerModel, cardPaymentInfoModel);

				return cardPaymentInfoModel;
			}
		}
		catch (final AdapterException ae)
		{
			LOG.error(String.format("Failed to create subscription for customer %s due to error of [%s]", new Object[]
			{ customerModel.getUid(), ae.getMessage() }));

			return null;
		}

		return null;

	}

	@Override
	public HeringDebitPaymentInfoModel createPaymentSubscription(final CustomerModel customerModel, 
			final HeringDebitPaymentInfoModel paymentModel,
			final BillingInfo billing, 
			final String titleCode, 
			final String paymentProvider, 
			final boolean saveInAccount)
	{
		ServicesUtil.validateParameterNotNull(customerModel, "Customer cannot be null");
		ServicesUtil.validateParameterNotNull(paymentModel, "HeringDebitPaymentInfoModel cannot be null");
		ServicesUtil.validateParameterNotNull(billing, "billingInfo cannot be null");
		ServicesUtil.validateParameterNotNull(paymentProvider, "PaymentProvider cannot be null");
		
		final CurrencyModel currencyModel = getCurrency(customerModel);
		ServicesUtil.validateParameterNotNull(currencyModel, "Customer session currency cannot be null");

		HeringBillingInfo billingInfo = (HeringBillingInfo) billing;

		final Currency currency = getI18nService().getBestMatchingJavaCurrency(currencyModel.getIsocode());

		final AddressModel billingAddress = (AddressModel) getModelService().create(AddressModel.class);
		if (StringUtils.isNotBlank(titleCode))
		{
			final TitleModel title = new TitleModel();
			title.setCode(titleCode);
			billingAddress.setTitle(getFlexibleSearchService().getModelByExample(title));
		}
		billingAddress.setFirstname(billingInfo.getFirstName());
		billingAddress.setLastname(billingInfo.getLastName());
		billingAddress.setLine1(billingInfo.getStreet1());
		billingAddress.setLine2(billingInfo.getStreet2());
		billingAddress.setTown(billingInfo.getCity());
		billingAddress.setPostalcode(billingInfo.getPostalCode());
		billingAddress.setCountry(getCommonI18NService().getCountry(billingInfo.getCountry()));
		billingAddress.setPhone1(billingInfo.getPhoneNumber());
		billingAddress.setDddPhone(billingInfo.getDddPhone());
		billingAddress.setRegion(getCommonI18NService().getRegion(billingAddress.getCountry(), billingInfo.getRegionIso()));
		billingAddress.setDistrict(billingInfo.getDistrict());
		billingAddress.setStreetnumber(billingInfo.getNumber());
		billingAddress.setPontoDeReferencia(billingInfo.getReference());
		billingAddress.setTipoDeEndereco(TipoDeEndereco.valueOf(billingInfo.getAddressType()));
		
		final String email = getCustomerEmailResolutionService().getEmailForCustomer(customerModel);
		billingAddress.setEmail(email);

		final String merchantTransactionCode = customerModel.getUid() + "_" + UUID.randomUUID();
		try
		{
			final NewSubscription subscription = getPaymentService().createSubscription(merchantTransactionCode, 
					paymentProvider, currency, billingAddress, paymentModel);

			if (StringUtils.isNotBlank(subscription.getSubscriptionID()))
			{
				paymentModel.setCode(merchantTransactionCode);
				paymentModel.setMerchantTransactionCode(merchantTransactionCode);
				paymentModel.setUser(customerModel);
				paymentModel.setSubscriptionId(subscription.getSubscriptionID());
				paymentModel.setSaved(saveInAccount);
				
				billingAddress.setOwner(paymentModel);
				paymentModel.setBillingAddress(billingAddress);

				getModelService().saveAll(new Object[]
				{ billingAddress, paymentModel});
				
				getModelService().refresh(billingAddress);
				getModelService().refresh(customerModel);
				getModelService().refresh(paymentModel);

				addPaymentInfo(customerModel, paymentModel);

				return paymentModel;
			}
		}
		catch (final AdapterException ae)
		{
			LOG.error(String.format("Failed to create subscription for customer %s due to error of [%s]", new Object[]
			{ customerModel.getUid(), ae.getMessage() }));

			return null;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.hering.core.customer.impl.KPCustomerAccountService#getCustomerByCpfCnpj(java.lang.String)
	 */
	@Override
	public CustomerModel getCustomerByCpfCnpj(String cpfCnpj)
	{
		HeringCustomerAccountDao customerDao = (HeringCustomerAccountDao) getCustomerAccountDao();
		return customerDao.getCustomerByCpf(cpfCnpj);
	}

	public CartService getCartService()
	{
		return cartService;
	}

	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
	}

	@Override
	protected AdyenPaymentService getPaymentService()
	{
		return (AdyenPaymentService) super.getPaymentService();
	}

	@Override
	public PaymentInfoModel getPaymentInfoForCode(CustomerModel customerModel, String code)
	{
		ServicesUtil.validateParameterNotNull(customerModel, "Customer model cannot be null");
		return ((HeringCustomerAccountDao)getCustomerAccountDao()).findPaymentInfoByCustomer(customerModel, code);
	}


	/* (non-Javadoc)
	 * @see br.hering.core.customer.impl.KPCustomerAccountService#getOrderListByCustomerDateAndStatus(de.hybris.platform.core.model.user.CustomerModel, java.util.Date, java.util.Date, de.hybris.platform.core.enums.OrderStatus[])
	 */
	@Override
	public List<OrderModel> getOrderListByCustomerAndDateAndStatus(final UserModel userModel, 
																						final Date initDate, 
																						final Date endDate,
																						final OrderStatus... statuses)
	{
		return ((HeringCustomerAccountDao)getCustomerAccountDao()).findOrdersByCustomerAndDateAndStatus(userModel, initDate, endDate, statuses);
	}
}