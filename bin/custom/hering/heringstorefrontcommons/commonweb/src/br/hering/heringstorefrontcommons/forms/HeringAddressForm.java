/**
 * 
 */
package br.hering.heringstorefrontcommons.forms;
import de.hybris.platform.acceleratorstorefrontcommons.forms.AddressForm;


/**
 * @author douglas.canalli
 * 
 */
public class HeringAddressForm extends AddressForm
{
	private String complement;
	private String reference;
	private String receiver;
	private String addressType;
	private String number;
	private String phone;
	private String neighborhood;
	private String celPhone;
	private boolean billing;

	/**
	 * @return the phone
	 */
	public String getPhone()
	{
		if (phone != null)
		{
			return phone.replaceAll("[^\\d.]", "");
		}
		else
		{
			return phone;
		}

	}

	/**
	 * @param phone
	 *           the phone to set
	 */
	public void setPhone(final String phone)
	{
		this.phone = phone;
	}

	/**
	 * @return the neighborhood
	 */
	public String getNeighborhood()
	{
		return neighborhood;
	}

	/**
	 * @param neighborhood
	 *           the neighborhood to set
	 */
	public void setNeighborhood(final String neighborhood)
	{
		this.neighborhood = neighborhood;
	}

	/**
	 * @return the number
	 */
	public String getNumber()
	{
		return number;
	}

	/**
	 * @param number
	 *           the number to set
	 */
	public void setNumber(final String number)
	{
		this.number = number;
	}

	/**
	 * @return the complement
	 */
	public String getComplement()
	{
		return complement;
	}

	/**
	 * @param complement
	 *           the complement to set
	 */
	public void setComplement(final String complement)
	{
		this.complement = complement;
	}

	/**
	 * @return the reference
	 */
	public String getReference()
	{
		return reference;
	}

	/**
	 * @param reference
	 *           the reference to set
	 */
	public void setReference(final String reference)
	{
		this.reference = reference;
	}

	/**
	 * @return the receiver
	 */
	public String getReceiver()
	{
		return receiver;
	}

	/**
	 * @param receiver
	 *           the receiver to set
	 */
	public void setReceiver(final String receiver)
	{
		this.receiver = receiver;
	}

	/**
	 * @return the addressType
	 */
	public String getAddressType()
	{
		return addressType;
	}

	/**
	 * @param addressType
	 *           the addressType to set
	 */
	public void setAddressType(final String addressType)
	{
		this.addressType = addressType;
	}

	/**
	 * @return the celPhone
	 */
	public String getCelPhone()
	{
		if (celPhone != null)
		{
			return celPhone.replaceAll("[^\\d.]", "");
		}
		else
		{
			return celPhone;
		}
	}

	/**
	 * @param celPhone
	 *           the celPhone to set
	 */
	public void setCelPhone(final String celPhone)
	{
		this.celPhone = celPhone;
	}

	/**
	 * @return the billing
	 */
	public boolean isBilling()
	{
		return billing;
	}

	/**
	 * @param billing
	 *           the billing to set
	 */
	public void setBilling(final boolean billing)
	{
		this.billing = billing;
	}
}