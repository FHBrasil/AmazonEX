/**
 *
 */
package br.hering.heringstorefrontcommons.forms;


/**
 * @author ezequiel
 */
public class DebitCardForm
{
	private String accountNumber;
	private String bank;
	private String baOwner;
	private String bankIDNumber;
	private String cardAccountHolderName;
	private String cardCvNumber;
	private Integer cardExpirationMonth;
	private Integer CardExpirationYear;

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber()
	{
		return accountNumber;
	}

	/**
	 * @param accountNumber
	 *           the accountNumber to set
	 */
	public void setAccountNumber(final String accountNumber)
	{
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the bank
	 */
	public String getBank()
	{
		return bank;
	}

	/**
	 * @param bank
	 *           the bank to set
	 */
	public void setBank(final String bank)
	{
		this.bank = bank;
	}

	/**
	 * @return the baOwner
	 */
	public String getBaOwner()
	{
		return baOwner;
	}

	/**
	 * @param baOwner
	 *           the baOwner to set
	 */
	public void setBaOwner(final String baOwner)
	{
		this.baOwner = baOwner;
	}

	/**
	 * @return the bankIDNumber
	 */
	public String getBankIDNumber()
	{
		return bankIDNumber;
	}

	/**
	 * @param bankIDNumber
	 *           the bankIDNumber to set
	 */
	public void setBankIDNumber(final String bankIDNumber)
	{
		this.bankIDNumber = bankIDNumber;
	}

	/**
	 * @return the cardAccountHolderName
	 */
	public String getCardAccountHolderName()
	{
		return cardAccountHolderName;
	}

	/**
	 * @param cardAccountHolderName
	 *           the cardAccountHolderName to set
	 */
	public void setCardAccountHolderName(final String cardAccountHolderName)
	{
		this.cardAccountHolderName = cardAccountHolderName;
	}

	/**
	 * @return the cardCvNumber
	 */
	public String getCardCvNumber()
	{
		return cardCvNumber;
	}

	/**
	 * @param cardCvNumber
	 *           the cardCvNumber to set
	 */
	public void setCardCvNumber(final String cardCvNumber)
	{
		this.cardCvNumber = cardCvNumber;
	}

	/**
	 * @return the cardExpirationMonth
	 */
	public Integer getCardExpirationMonth()
	{
		return cardExpirationMonth;
	}

	/**
	 * @param cardExpirationMonth
	 *           the cardExpirationMonth to set
	 */
	public void setCardExpirationMonth(final Integer cardExpirationMonth)
	{
		this.cardExpirationMonth = cardExpirationMonth;
	}

	/**
	 * @return the cardExpirationYear
	 */
	public Integer getCardExpirationYear()
	{
		return CardExpirationYear;
	}

	/**
	 * @param cardExpirationYear
	 *           the cardExpirationYear to set
	 */
	public void setCardExpirationYear(final Integer cardExpirationYear)
	{
		CardExpirationYear = cardExpirationYear;
	}
}
