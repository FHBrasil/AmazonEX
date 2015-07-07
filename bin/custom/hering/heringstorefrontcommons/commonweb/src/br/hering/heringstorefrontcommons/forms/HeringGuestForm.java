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
	
	private String country;
	
	private String complement;
	private String street;
	private String number;
	private String zipCode;
	private String place;
	private String telephone;
	private String titleCode;
	private String firstName;
	private String lastName;
	private String pwd;
	private String checkPwd;

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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTitleCode() {
		return titleCode;
	}

	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getCheckPwd() {
		return checkPwd;
	}

	public void setCheckPwd(String checkPwd) {
		this.checkPwd = checkPwd;
	}
}