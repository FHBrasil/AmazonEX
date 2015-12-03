/**
 * 
 */
package de.fliegersoftware.amazon.logging.appender;

import java.io.IOException;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;


/**
 * @author douglas.canalli
 * 
 */
public class AmazonIPNAppender extends AbstractAmazonAppender
{
	public AmazonIPNAppender()
	{
	}

	public AmazonIPNAppender(final Layout layout, final String filename, final boolean append) throws IOException
	{
		super(layout, filename, append);
	}

	public AmazonIPNAppender(final Layout layout, final String filename) throws IOException
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
		super.append(event);
	}
}
