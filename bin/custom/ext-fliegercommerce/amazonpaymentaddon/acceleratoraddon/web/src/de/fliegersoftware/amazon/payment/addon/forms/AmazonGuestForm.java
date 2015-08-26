package de.fliegersoftware.amazon.payment.addon.forms;

public class AmazonGuestForm {

	private String amazonOrderReferenceId;
	private String amazonGuestId;
	private String amazonGuestName;
	private String amazonGuestEmail;

	public AmazonGuestForm() {
	}

	public String getAmazonOrderReferenceId() {
		return amazonOrderReferenceId;
	}

	public void setAmazonOrderReferenceId(String amazonOrderReferenceId) {
		this.amazonOrderReferenceId = amazonOrderReferenceId;
	}

	public String getAmazonGuestId() {
		return amazonGuestId;
	}

	public void setAmazonGuestId(String amazonGuestId) {
		this.amazonGuestId = amazonGuestId;
	}

	public String getAmazonGuestName() {
		return amazonGuestName;
	}

	public void setAmazonGuestName(String amazonGuestName) {
		this.amazonGuestName = amazonGuestName;
	}

	public String getAmazonGuestEmail() {
		return amazonGuestEmail;
	}

	public void setAmazonGuestEmail(String amazonGuestEmail) {
		this.amazonGuestEmail = amazonGuestEmail;
	}
}