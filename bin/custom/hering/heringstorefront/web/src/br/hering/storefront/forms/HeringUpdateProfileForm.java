/**
 * 
 */
package br.hering.storefront.forms;

import de.hybris.platform.acceleratorstorefrontcommons.forms.UpdateProfileForm;
import de.hybris.platform.core.enums.Gender;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Classe Form de acesso ao UpdateProfileForm especifico da Hering.
 * 
 * @author Vinicius de Souza
 *
 */
public class HeringUpdateProfileForm extends UpdateProfileForm
{
	
	private Boolean subscribeNewsletter;
	private String cpfcnpj;
	private String rgIe;
	private String ufIe;
	private Gender gender;
	private String dddPhone;
	private String dddCellPhone;
	private String birthday;
	private String baseStore;
	

	/**
	 * @return the subscribeNewsletter
	 */
	public Boolean getSubscribeNewsletter()
	{
		return subscribeNewsletter;
	}

	/**
	 * @param subscribeNewsletter the subscribeNewsletter to set
	 */
	public void setSubscribeNewsletter(Boolean subscribeNewsletter)
	{
		this.subscribeNewsletter = subscribeNewsletter;
	}

	/**
	 * @return the cpfcnpj
	 */
	@NotNull(message = "{profile.cpfCnpj.invalid}")
	@Size(min = 1, max = 255, message = "{profile.cpfCnpj.invalid}")
	public String getCpfcnpj()
	{
		return cpfcnpj;
	}

	/**
	 * @param cpfcnpj the cpfcnpj to set
	 */
	public void setCpfcnpj(String cpfcnpj)
	{
		this.cpfcnpj = cpfcnpj;
	}

	/**
	 * @return the rgIe
	 */
	public String getRgIe()
	{
		return rgIe;
	}

	/**
	 * @param rgIe the rgIe to set
	 */
	public void setRgIe(String rgIe)
	{
		this.rgIe = rgIe;
	}

	/**
	 * @return the ufIe
	 */
	public String getUfIe()
	{
		return ufIe;
	}

	/**
	 * @param ufIe the ufIe to set
	 */
	public void setUfIe(String ufIe)
	{
		this.ufIe = ufIe;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender()
	{
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender)
	{
		this.gender = gender;
	}
	
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender)
	{
		this.gender = Gender.valueOf(gender);
	}

	/**
	 * @return the dddPhone
	 */
	public String getDddPhone()
	{
		return dddPhone;
	}

	/**
	 * @param dddPhone the dddPhone to set
	 */
	public void setDddPhone(String dddPhone)
	{
		this.dddPhone = dddPhone;
	}

	/**
	 * @return the dddCellPhone
	 */
	public String getDddCellPhone()
	{
		return dddCellPhone;
	}

	/**
	 * @param dddCellPhone the dddCellPhone to set
	 */
	public void setDddCellPhone(String dddCellPhone)
	{
		this.dddCellPhone = dddCellPhone;
	}

	public String getBaseStore()
	{
		return baseStore;
	}

	public void setBaseStore(String baseStore)
	{
		this.baseStore = baseStore;
	}

	/**
	 * @return the birthdate
	 */
	public String getBirthday()
	{
		return birthday;
	}

	/**
	 * @param birthdate the birthdate to set
	 */
	public void setBirthday(String birthday)
	{
		this.birthday = birthday;
	}
	
	public void setBirthday(Date birthday) {
		if(birthday !=  null && !birthday.equals("")){
   		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
   		this.birthday = formatter.format(birthday);
		}
	}
	
	public Date getDateBirthday() {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return formatter.parse(this.birthday);
		} catch (Exception e) {
			return null;
		}
	}
	
}