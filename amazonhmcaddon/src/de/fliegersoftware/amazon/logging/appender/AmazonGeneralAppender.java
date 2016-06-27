/**
 * 
 */
package de.fliegersoftware.amazon.logging.appender;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;


/**
 * @author douglas.canalli
 * 
 */
public class AmazonGeneralAppender extends AbstractAmazonAppender
{
	public AmazonGeneralAppender()
	{
	}

	public AmazonGeneralAppender(final Layout layout, final String filename, final boolean append) throws IOException
	{
		super(layout, filename, append);
	}

	public AmazonGeneralAppender(final Layout layout, final String filename) throws IOException
	{
		super(layout, filename);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.log4j.WriterAppender#append(org.apache.log4j.spi.LoggingEvent)
	 */
	@Override
	public void append(final LoggingEvent event)
	{
		final boolean hasClassName = event != null && event.getLocationInformation() != null
				&& StringUtils.isNotBlank(event.getLocationInformation().getClassName());
		if (!hasClassName)
		{
			return;
		}
		final String className = event.getLocationInformation().getClassName();
		if (className.startsWith("de.fliegersoftware.amazon.payment"))
		{
			return;
		}
		super.append(event);
	}

}
