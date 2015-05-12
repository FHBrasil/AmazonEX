/**
 *
 */
package br.hering.heringstorefrontcommons.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


/**
 * @author Antony P
 */
public class HeringPaymentDetailsForm
{
	private String instalment;
	private String paymentMode;
	private String paymentId;
	private String cardTypeCode;
	private String nameOnCard;
	private String cardNumber;
	private String startMonth;
	private String startYear;
	private String expiryMonth;
	private String expiryYear;
	private String issueNumber;
	private String cv2Number;
	private String cardBrand;
	private String voucher;
	private String removeVoucher;
	private Boolean saveInAccount;
	private Boolean newBillingAddress;
	private HeringAddressForm billingAddress;
	private DebitCardForm debitCardForm;
	private String selectedPaymentId;
	private boolean isVoucherSelected;

	
	/**
	 * @return the cv2Number
	 */
	@NotNull(message = "{payment.cv2Number.invalid}")
	@Size(min = 3, max = 4, message = "{payment.cv2Number.invalid}")
	@Pattern(regexp = "(^[0-9]{1,4})", message = "{payment.cv2Number.invalid}")
	public String getCv2Number()
	{
		return cv2Number;
	}

	/**
	 * @param cv2Number
	 *           the cv2Number to set
	 */
	public void setCv2Number(final String cv2Number)
	{
		this.cv2Number = cv2Number;
	}

	/**
	 * @return the instalment
	 */
	public String getInstalment()
	{
		return instalment;
	}

	/**
	 * @param instalments
	 *           the instalments to set
	 */
	public void setInstalment(final String instalment)
	{
		this.instalment = instalment;
	}

	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode()
	{
		return paymentMode;
	}

	/**
	 * @param paymentMode
	 *           the paymentMode to set
	 */
	public void setPaymentMode(final String paymentMode)
	{
		this.paymentMode = paymentMode;
	}

	public String getPaymentId()
	{
		return paymentId;
	}

	public void setPaymentId(final String paymentId)
	{
		this.paymentId = paymentId;
	}

	@NotNull(message = "{payment.cardType.invalid}")
	@Size(min = 1, max = 255, message = "{payment.cardType.invalid}")
	public String getCardTypeCode()
	{
		return cardTypeCode;
	}

	public void setCardTypeCode(final String cardTypeCode)
	{
		this.cardTypeCode = cardTypeCode;
	}

	@NotNull(message = "{payment.nameOnCard.invalid}")
	@Size(min = 1, max = 255, message = "{payment.nameOnCard.invalid}")
	public String getNameOnCard()
	{
		return nameOnCard;
	}

	public void setNameOnCard(final String nameOnCard)
	{
		this.nameOnCard = nameOnCard;
	}

	@NotNull(message = "{payment.cardNumber.invalid}")
	@Size(min = 14, max = 16, message = "{payment.cardNumber.invalid}")
	@Pattern(regexp = "(^$|^?\\d*$)", message = "{payment.cardNumber.invalid}")
	public String getCardNumber()
	{
		return cardNumber;
	}

	public void setCardNumber(final String cardNumber)
	{
		this.cardNumber = cardNumber;
	}

	public String getStartMonth()
	{
		return startMonth;
	}

	public void setStartMonth(final String startMonth)
	{
		this.startMonth = startMonth;
	}

	public String getStartYear()
	{
		return startYear;
	}

	public void setStartYear(final String startYear)
	{
		this.startYear = startYear;
	}

	@NotNull(message = "{payment.expiryMonth.invalid}")
	@Pattern(regexp = "^(1[0-2]|[1-9])$", message = "{payment.expiryMonth.invalid}")
	public String getExpiryMonth()
	{
		return expiryMonth;
	}

	public void setExpiryMonth(final String expiryMonth)
	{
		this.expiryMonth = expiryMonth;
	}

	@NotNull(message = "{payment.expiryYear.invalid}")
	@Pattern(regexp = "^(201[5-9]|20[2-9][0-9]|2[1-9][0-9]{2}|[3-9][0-9]{3})$",  message = "{payment.expiryYear.invalid}")
	public String getExpiryYear()
	{
		return expiryYear;
	}

	public void setExpiryYear(final String expiryYear)
	{
		this.expiryYear = expiryYear;
	}

	@Pattern(regexp = "(^$|^?\\d*$)", message = "{payment.issueNumber.invalid}")
	@Size(min = 0, max = 16, message = "{payment.issueNumber.toolong}")
	public String getIssueNumber()
	{
		return issueNumber;
	}

	public void setIssueNumber(final String issueNumber)
	{
		this.issueNumber = issueNumber;
	}

	public Boolean getSaveInAccount()
	{
		return saveInAccount;
	}

	public void setSaveInAccount(final Boolean saveInAccount)
	{
		this.saveInAccount = saveInAccount;
	}

	public Boolean getNewBillingAddress()
	{
		return newBillingAddress;
	}

	public void setNewBillingAddress(final Boolean newBillingAddress)
	{
		this.newBillingAddress = newBillingAddress;
	}

	//	@Valid
	public HeringAddressForm getBillingAddress()
	{
		return billingAddress;
	}

	public void setBillingAddress(final HeringAddressForm billingAddress)
	{
		this.billingAddress = billingAddress;
	}

	public String getCardBrand()
	{
		return cardBrand;
	}

	public void setCardBrand(final String cardBrand)
	{
		this.cardBrand = cardBrand;
	}

	/**
	 * @return the voucher
	 */
	public String getVoucher()
	{
		return voucher;
	}

	/**
	 * @param voucher
	 *           the voucher to set
	 */
	public void setVoucher(final String voucher)
	{
		this.voucher = voucher;
	}

	/**
	 * @return the removeVoucher
	 */
	public String getRemoveVoucher()
	{
		return removeVoucher;
	}

	/**
	 * @param removeVoucher
	 *           the removeVoucher to set
	 */
	public void setRemoveVoucher(final String removeVoucher)
	{
		this.removeVoucher = removeVoucher;
	}

	/**
	 * @return the selectedPaymentId
	 */
	public String getSelectedPaymentId()
	{
		return selectedPaymentId;
	}

	/**
	 * @param selectedPaymentId
	 *           the selectedPaymentId to set
	 */
	public void setSelectedPaymentId(final String selectedPaymentId)
	{
		this.selectedPaymentId = selectedPaymentId;
	}

	/**
	 * @return the debitCardForm
	 */
	public DebitCardForm getDebitCardForm()
	{
		return debitCardForm;
	}

	/**
	 * @param debitCardForm the debitCardForm to set
	 */
	public void setDebitCardForm(DebitCardForm debitCardForm)
	{
		this.debitCardForm = debitCardForm;
	}
	
	/**
	 * @return the isVoucherSelected
	 */
	public boolean getIsVoucherSelected() {
		return isVoucherSelected;
	}

	
	/**
	 * @param isVoucherSelected the isVoucherSelected to set
	 */
	public void setIsVoucherSelected(final boolean isVoucherSelected) {
		this.isVoucherSelected = isVoucherSelected;
	}
}
