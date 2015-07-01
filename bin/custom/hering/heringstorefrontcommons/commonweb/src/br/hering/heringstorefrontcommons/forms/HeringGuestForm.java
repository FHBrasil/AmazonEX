/**
 * 
 */
package br.hering.heringstorefrontcommons.forms;

import de.hybris.platform.acceleratorstorefrontcommons.forms.GuestForm;
import de.hybris.platform.core.enums.Gender;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author franthescollymaneira
 * 
 */
public class HeringGuestForm extends GuestForm
{
	private String cpfcnpj;

	private String birthday;

	private Gender gender;

	/**
	 * @return the cpfcnpj
	 */
	public String getCpfcnpj()
	{
		return cpfcnpj;
	}

	/**
	 * @param cpfcnpj
	 *           the cpfcnpj to set
	 */
	public void setCpfcnpj(final String cpfcnpj)
	{
		this.cpfcnpj = cpfcnpj;
	}

	/**
	 * @return the birthdate
	 */
	public String getBirthday()
	{
		return birthday;
	}

	public void setBirthday(String birthday)
	{
		this.birthday = birthday;
	}

	public void setBirthday(Date birthday)
	{
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		this.birthday = formatter.format(birthday);
	}

	public Date getDateBirthday()
	{
		final DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try
		{
			return formatter.parse(this.birthday);
		}
		catch (final Exception e)
		{
			return null;
		}
	}

	/**
	 * @return the gender
	 */
	public Gender getGender()
	{
		return gender;
	}

	/**
	 * @param gender
	 *           the gender to set
	 */
	public void setGender(Gender gender)
	{
		this.gender = gender;
	}
}