/**
 * 
 */
package br.hering.facades.customer;

import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Date;

import com.flieger.facades.checkout.data.GuestRegisterData;

import de.hybris.platform.core.enums.Gender;



/**
 * @author Antony P
 *
 */
public interface HeringCustomerFacade extends CustomerFacade
{

	public CustomerModel cpfCnpjAlreadyExists(String cpfCnpj);

	public void createGuestUserForAnonymousCheckout(String email, String cpfcnpj, String name, Date birthday, Gender gender) throws DuplicateUidException;

	public void updatePasswordWithSalt(String email, String password);
	
	public void deleteAccount();
	
	public CustomerData changePhoneNumber(String phone);
	
	public CustomerData updateCustomerSubscriptions(Boolean tipsNewsletter, Date dateOfBirth);
	
	public void registerGuest(GuestRegisterData guestData) throws DuplicateUidException;

}
