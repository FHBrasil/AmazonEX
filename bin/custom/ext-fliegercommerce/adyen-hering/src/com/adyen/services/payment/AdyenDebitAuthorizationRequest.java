/**
 * 
 */
package com.adyen.services.payment;

import com.flieger.payment.model.HeringDebitPaymentInfoModel;

/**
 * @author franthescollymaneira
 * 
 */
public class AdyenDebitAuthorizationRequest extends AbstractAdyenRequest
{
	private String userUid;
	private String buyerEmail;
	private String reference;
	private String selectedBrand;
	
	private HeringDebitPaymentInfoModel heringDebitPaymentInfoModel;

	public AdyenDebitAuthorizationRequest(final String reference, 
			final String selectedBrand, 
			final String userUid, 
			final String buyerEmail, 
			final HeringDebitPaymentInfoModel heringDebitPaymentInfoModel,
			final String merchantAccount)
	{
		super(heringDebitPaymentInfoModel.getMerchantTransactionCode(),
				merchantAccount);
		this.reference = reference;
		this.selectedBrand = selectedBrand;
		this.userUid = userUid;
		this.buyerEmail = buyerEmail;
		this.heringDebitPaymentInfoModel = heringDebitPaymentInfoModel;
	}
	
	/**
	 * 
	 */
	public AdyenDebitAuthorizationRequest(
			final HeringDebitPaymentInfoModel heringDebitPaymentInfoModel,
			final String merchantAccount)
	{
		super(heringDebitPaymentInfoModel.getMerchantTransactionCode(), 
				merchantAccount);
		this.heringDebitPaymentInfoModel = heringDebitPaymentInfoModel;
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
	 * @return the buyerEmail
	 */
	public String getBuyerEmail()
	{
		return buyerEmail;
	}

	/**
	 * @param buyerEmail the buyerEmail to set
	 */
	public void setBuyerEmail(String buyerEmail)
	{
		this.buyerEmail = buyerEmail;
	}
	
	/**
	 * @return the userUid
	 */
	public String getUserUid()
	{
		return userUid;
	}

	/**
	 * @param userUid the userUid to set
	 */
	public void setUserUid(String userUid)
	{
		this.userUid = userUid;
	}

	/**
	 * @return the heringDebitPaymentInfo
	 */
	public HeringDebitPaymentInfoModel getHeringDebitPaymentInfoModel()
	{
		return heringDebitPaymentInfoModel;
	}

	public void setHeringDebitPaymentInfoModel(HeringDebitPaymentInfoModel heringDebitPaymentInfoModel)
	{
		this.heringDebitPaymentInfoModel = heringDebitPaymentInfoModel;
	}
	
	public void setSelectedBrand(final String selectedBrand){
		this.selectedBrand = selectedBrand;
	}
	
	public String getSelectedBrand(){
		return this.selectedBrand;
	}
}
