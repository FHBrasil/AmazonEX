/**
 *
 */
package com.pixi.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 */
@XmlRootElement(name = "response")
public class SampleResponse implements Serializable
{
	private String action;
	private Date date;
	private String uid;

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

	/**
	 * @return the date
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * @param date
	 *           the date to set
	 */
	@XmlElement
	public void setDate(final Date date)
	{
		this.date = date;
	}

	/**
	 * @return the uid
	 */
	public String getUid()
	{
		return uid;
	}

	/**
	 * @param uid
	 *           the uid to set
	 */
	@XmlElement
	public void setUid(final String uid)
	{
		this.uid = uid;
	}
}