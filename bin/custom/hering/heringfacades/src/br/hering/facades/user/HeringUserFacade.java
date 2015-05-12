/**
 * 
 */
package br.hering.facades.user;


import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.acceleratorservices.payment.cybersource.data.PaymentInfoData;
/**
 * @author Antony P
 *
 */
public interface HeringUserFacade extends UserFacade {

	void setCnpjCpfForCheckout(String cpf);
	String getCpfCnpjIfExiss();
	void registerCustomerPaymentInfo(CCPaymentInfoData paymentInfoData);
	void setDefaultPaymentInfo(final PaymentInfoData paymentInfoData);
	
	@Override
	void removeAddress(final AddressData addressData);
}