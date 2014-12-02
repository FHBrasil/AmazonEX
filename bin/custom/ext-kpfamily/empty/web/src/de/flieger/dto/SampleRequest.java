/**
 *
 */
package de.flieger.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 */
@XmlRootElement
public class SampleRequest
{
	private String action;

	/**
	 * @return the action
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * @param action
	 *           the action to set
	 */
	@XmlElement
	public void setAction(final String action)
	{
		this.action = action;
	}


}