/**
 * 
 */
package br.hering.storefront.forms;

import de.hybris.platform.acceleratorstorefrontcommons.forms.RegisterForm;

/**
 * @author Vinicius de Souza
 *
 */
public class HeringRegisterNewsletterForm extends RegisterForm
{
	private String gender;
	
	/**
	 * @return the gender
	 */
	public String getGender()
	{
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender)
	{
		this.gender = gender;
	}
}
