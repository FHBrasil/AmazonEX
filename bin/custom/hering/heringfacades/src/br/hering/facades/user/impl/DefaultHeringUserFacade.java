/**
 * 
 */
package br.hering.facades.user.impl;

import de.hybris.platform.acceleratorservices.payment.cybersource.data.PaymentInfoData;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.impl.DefaultUserFacade;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import br.hering.facades.populators.HeringCreditCardPaymentInfoReversePopulator;
import br.hering.facades.user.HeringUserFacade;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

/**
 * @author Antony P
 *
 */
public class DefaultHeringUserFacade 
extends DefaultUserFacade 
implements HeringUserFacade {
	
	@Resource
	HeringCreditCardPaymentInfoReversePopulator defaultHeringCreditCardPaymentInfoReversePopulator;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see br.hering.facades.user.HeringUserFacade#setCnpjCpfForCheckout(java.lang.String)
	 */
	@Override
	public void setCnpjCpfForCheckout(String cpfCnpj) {
		CustomerModel customer = (CustomerModel) getUserService().getCurrentUser();
		customer.setCpfcnpj(cpfCnpj);
		getModelService().save(customer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.hering.facades.user.HeringUserFacade#hasCpfCnpj()
	 */
	@Override
	public String getCpfCnpjIfExiss() {
		CustomerModel customer = (CustomerModel) getUserService().getCurrentUser();
		if (StringUtils.isNotBlank(customer.getCpfcnpj()))
		{
			return customer.getCpfcnpj();
		}
		return "";
	}


	/**
	 * 
	 */
	@Override
	public void registerCustomerPaymentInfo(CCPaymentInfoData paymentInfoData) {
		CreditCardPaymentInfoModel paymentInfoModel = getModelService().create(
				CreditCardPaymentInfoModel.class);
		CustomerModel customerModel = (CustomerModel) getUserService()
				.getCurrentUser();
		defaultHeringCreditCardPaymentInfoReversePopulator.populate(
				paymentInfoData, paymentInfoModel);
		paymentInfoModel.setCode(customerModel.getUid() + "_" + UUID.randomUUID());
		paymentInfoModel.setUser(customerModel);
		paymentInfoModel.setSaved(true);
		getModelService().save(paymentInfoModel);
	}
	
	
	@Override
	public List<CCPaymentInfoData> getCCPaymentInfos(final boolean saved) {
		final CustomerModel currentCustomer = getCurrentUserForCheckout();
		final List<CreditCardPaymentInfoModel> creditCards = 
				getCustomerAccountService().getCreditCardPaymentInfos(
						currentCustomer, saved);
		final List<CCPaymentInfoData> ccPaymentInfos = 
				new ArrayList<CCPaymentInfoData>();
		final PaymentInfoModel defaultPaymentInfoModel = currentCustomer
				.getDefaultPaymentInfo();
		for (final CreditCardPaymentInfoModel ccPaymentInfoModel : creditCards) {
			final CCPaymentInfoData defaultPaymentInfoData = 
					getCreditCardPaymentInfoConverter().convert(ccPaymentInfoModel);
			if (ccPaymentInfoModel.equals(defaultPaymentInfoModel)) {
				defaultPaymentInfoData.setDefaultPaymentInfo(true);
			}
			ccPaymentInfos.add(defaultPaymentInfoData);
		}
		return ccPaymentInfos;
	}
	
	@Override
	public void setDefaultPaymentInfo(final PaymentInfoData paymentInfoData)
	{
		validateParameterNotNullStandardMessage("paymentInfoData", paymentInfoData);
		final CustomerModel currentCustomer = getCurrentUserForCheckout();
		final PaymentInfoModel paymentInfoModel = getCustomerAccountService().getCreditCardPaymentInfoForCode(currentCustomer,
				paymentInfoData.getPK());
		if (paymentInfoModel != null)
		{
			getCustomerAccountService().setDefaultPaymentInfo(currentCustomer, paymentInfoModel);
		}
	}
	
	/**
	 * Overriding of this method because the hering store will not delete 
	 * 
	 * addresses but will just inactivate them
	 * 
	 * */
	@Override
	public void removeAddress(final AddressData addressData)
	{
		validateParameterNotNullStandardMessage("addressData", addressData);
		final CustomerModel currentCustomer = getCurrentUserForCheckout();
		
		final AddressModel addressModel = 
				getCustomerAccountService().getAddressForCode(
						currentCustomer, 
						addressData.getId());
		
		validateParameterNotNullStandardMessage("addressModel", addressModel);
		
		addressModel.setVisibleInAddressBook(Boolean.FALSE);
		
		getCustomerAccountService().saveAddressEntry(currentCustomer, addressModel);
		
		if (addressModel.equals(currentCustomer.getDefaultShipmentAddress()))
		{
			getCustomerAccountService().clearDefaultAddressEntry(currentCustomer);
		}
	}
}