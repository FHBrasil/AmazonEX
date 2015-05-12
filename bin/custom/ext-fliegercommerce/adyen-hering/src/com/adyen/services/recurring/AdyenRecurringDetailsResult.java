/**
 * 
 */
package com.adyen.services.recurring;

/**
 * @author flieger
 *
 */
public class AdyenRecurringDetailsResult extends RecurringDetailsResult
{
	private String recurringDetailReference;

	/**
	 * @return the recurringDetailReference
	 */
	public String getRecurringDetailReference()
	{
		return recurringDetailReference;
	}

	/**
	 * @param recurringDetailReference the recurringDetailReference to set
	 */
	public void setRecurringDetailReference(String recurringDetailReference)
	{
		this.recurringDetailReference = recurringDetailReference;
	}
	
}
