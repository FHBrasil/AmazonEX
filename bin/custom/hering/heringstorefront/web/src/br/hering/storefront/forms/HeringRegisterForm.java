/**
 * 
 */
package br.hering.storefront.forms;

import de.hybris.platform.acceleratorstorefrontcommons.forms.RegisterForm;
import de.hybris.platform.core.enums.Gender;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author rcsilva
 *
 */
public class HeringRegisterForm extends RegisterForm
{

	private String gender;

	private String cpfcnpj;
	private String birthday;
	
	private String rgIe;
	private String ufIe;
	
	private String pessoaFisica;
	
	private String baseStore;

	public String getPessoaFisica()
	{
		return pessoaFisica;
	}

	public void setPessoaFisica(String pessoaFisica)
	{
		this.pessoaFisica = pessoaFisica;
	}


	/**
	 * @return the cpfcnpj
	 */
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
	 * @return the gender
	 */
	public String getGender()
	{
		return gender;
	}
	
	/**
	 * @return the gender
	 */
	public Gender getGenderType()
	{
		return Gender.valueOf(gender);
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender)
	{
		this.gender = gender;
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
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		this.birthday = formatter.format(birthday);
	}
	
	public Date getDateBirthday() {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return formatter.parse(this.birthday);
		} catch (Exception e) {
			return null;
		}
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

	public String getBaseStore()
	{
		return baseStore;
	}

	public void setBaseStore(String baseStore)
	{
		this.baseStore = baseStore;
	}
}