/**
 * 
 */
package br.hering.core.payment.dto;

import de.hybris.platform.payment.dto.BillingInfo;

/**
 * @author Antony P
 *
 */
public class HeringBillingInfo extends BillingInfo
{

	private String dddPhone;
	private String cellPhone;
	private String dddCellPhone;
	private String regionIso;
	private String district;
	private String reference;
	private String number;
	private String addressType;
	private String complement;
	
	/**
	 * @return the addressType
	 */
	public String getAddressType()
	{
		return addressType;
	}
	/**
	 * @param addressType the addressType to set
	 */
	public void setAddressType(String addressType)
	{
		this.addressType = addressType;
	}
	/**
	 * @return the reference
	 */
	public String getReference()
	{
		return reference;
	}
	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference)
	{
		this.reference = reference;
	}
	/**
	 * @return the number
	 */
	public String getNumber()
	{
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number)
	{
		this.number = number;
	}
	/**
	 * @return the district
	 */
	public String getDistrict()
	{
		return district;
	}
	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district)
	{
		this.district = district;
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
	 * @return the cellPhone
	 */
	public String getCellPhone()
	{
		return cellPhone;
	}
	/**
	 * @param cellPhone the cellPhone to set
	 */
	public void setCellPhone(String cellPhone)
	{
		this.cellPhone = cellPhone;
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
	/**
	 * @return the regionIso
	 */
	public String getRegionIso()
	{
		return regionIso;
	}
	/**
	 * @param regionIso the regionIso to set
	 */
	public void setRegionIso(String regionIso)
	{
		this.regionIso = regionIso;
	}
	/**
	 * @return the complement
	 */
	public String getComplement()
	{
		return complement;
	}
	/**
	 * @param complement the complement to set
	 */
	public void setComplement(String complement)
	{
		this.complement = complement;
	}	
}
