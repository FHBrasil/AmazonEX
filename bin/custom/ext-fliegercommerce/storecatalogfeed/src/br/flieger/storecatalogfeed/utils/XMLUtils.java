/**
 * 
 */
package br.flieger.storecatalogfeed.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Content;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


/**
 * @author franthescolly
 * 
 */
public final class XMLUtils
{
	private final Logger LOG = Logger.getLogger(this.getClass());

	/**
	 * 
	 * @param <T>
	 * @param name
	 * @param content
	 * @param prepareDelimiter
	 * @return
	 */
	public <T> Element createElement(final String name, final T content, final boolean prepareDelimiter)
	{

		final Element element = new Element(name);

		if (content != null)
		{
			if (content instanceof Content)
			{
				element.addContent((Content) content);
			}
			else
			{
				final String value = String.valueOf(content);

				element.setText(prepareDelimiter ? prepareDelimiters(value) : value);
			}
		}

		return element;
	}

	private static String prepareDelimiters(final String string)
	{

		if (string == null || string.isEmpty())
		{
			return "";
		}

		//Parsing when working with text, not numbers
		if (!isNumber(string))
		{
			return string.replaceAll("\\|", "-").replaceAll(":", "-").replaceAll(",", "-").replaceAll(";", "-")
					.replaceAll("http-", "http:");
		}

		return string;
	}

	private static boolean isNumber(final String string)
	{
		try
		{
			final DecimalFormat formatter = new DecimalFormat("#####0,00");
			formatter.parse(string);
			return true;
		}
		catch (final ParseException e)
		{
			return false;
		}
	}

	public <T extends Content> Element createElements(final String name, final List<T> content)
	{

		final Element element = new Element(name);

		if (content != null && !content.isEmpty())
		{
			element.addContent(content);
		}

		return element;
	}


	/**
	 * 
	 * @param src
	 * @param destiny
	 * @param detachRoot
	 */
	public void copy(final Document src, final Document destiny, final boolean detachRoot)
	{

		final Element root = destiny.getRootElement();

		if (root != null && !src.getContent().isEmpty())
		{

			final List content = detachRoot ? src.detachRootElement().cloneContent() : src.cloneContent();

			root.addContent(content);
		}
	}

	public static void copyFile(final File sourceFile, final File destFile) throws IOException
	{

		if (!destFile.exists())
		{
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;
		try
		{
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile, true).getChannel();
			destination.transferFrom(source, 0, source.size());
		}
		finally
		{
			if (source != null)
			{
				source.close();
			}
			if (destination != null)
			{
				destination.close();
			}
		}
	}

	/**
	 * Clear the directory files
	 * 
	 * @param path
	 *           Directory path
	 */
	public void clearDirectory(final String path)
	{

		final File file = new File(path);

		if (file.exists() && file.isDirectory())
		{
			for (final File child : file.listFiles())
			{
				child.delete();
			}
		}
	}

	public void writeXMLFile(final Document document, final String fileName)
	{

		FileWriter fileWriter = null;
		try
		{
			LOG.info("generating: " + fileName);
			
			final File xmlFile = new File(fileName);

			xmlFile.getParentFile().mkdirs();

			if (xmlFile.exists())
			{
				xmlFile.delete();
			}

			final XMLOutputter xmlWritter = new XMLOutputter(Format.getPrettyFormat());
			fileWriter = new FileWriter(xmlFile);
			xmlWritter.output(document, fileWriter);

		}
		catch (final Exception e)
		{
			LOG.error("Write error: ", e);
		}
		finally
		{
			if (fileWriter != null)
			{
				try
				{
					fileWriter.close();
				}
				catch (final IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}