/**
 * 
 */
package de.fliegersoftware.amazon.logging.appender;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Layout;
import org.apache.log4j.RollingFileAppender;


/**
 * @author douglas.canalli
 * 
 */
public abstract class AbstractAmazonAppender extends RollingFileAppender
{

	public AbstractAmazonAppender()
	{
	}

	public AbstractAmazonAppender(final Layout layout, final String filename, final boolean append) throws IOException
	{
		super(layout, filename, append);
	}

	public AbstractAmazonAppender(final Layout layout, final String filename) throws IOException
	{
		super(layout, filename);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.log4j.RollingFileAppender#setFile(java.lang.String, boolean, boolean, int)
	 */
	@Override
	public synchronized void setFile(final String fileName, final boolean append, final boolean bufferedIO, final int bufferSize)
			throws IOException
	{
		final File f = new File(fileName);
		final File dir = new File(f.getParent());
		if (!dir.exists())
		{
			dir.mkdirs();
			f.createNewFile();
		}
		super.setFile(fileName, append, bufferedIO, bufferSize);
	}

}
