package com.ebay.logging;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.log4j.Logger;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class SOAPLoggingHandler implements SOAPHandler<SOAPMessageContext>
{
	private static final Logger LOG = Logger.getLogger(SOAPLoggingHandler.class);

	public static final String REQUEST_MSG = "REQUEST";
	public static final String RESPONSE_MSG = "RESPONSE";

	/**
	 * Gets the header blocks that can be processed by this Handler instance.
	 *
	 * @return Set of <code>QNames</code> of header blocks processed by this handler instance. <code>QName</code> is the
	 *         qualified name of the outermost element of the Header block.
	 */
	@Override
	public Set<QName> getHeaders()
	{
		return null;
	}

	/**
	 * The <code>handleMessage</code> method is invoked for normal processing of inbound and outbound messages. Refer to
	 * the description of the handler framework in the JAX-WS specification for full details.
	 *
	 * @param context
	 *           the message context.
	 * @return An indication of whether handler processing should continue for the current message
	 *         <ul>
	 *         <li>Return <code>true</code> to continue processing.</li>
	 *         <li>Return <code>false</code> to block processing.</li>
	 *         </ul>
	 * @throws RuntimeException
	 *            Causes the JAX-WS runtime to cease handler processing and generate a fault.
	 * @throws ProtocolException
	 *            Causes the JAX-WS runtime to switch to fault message processing.
	 */
	@Override
	public boolean handleMessage(final SOAPMessageContext context)
	{
		return logMessage(context, false);
	}

	/**
	 * The <code>handleFault</code> method is invoked for fault message processing. Refer to the description of the
	 * handler framework in the JAX-WS specification for full details.
	 *
	 * @param context
	 *           the message context
	 * @return An indication of whether handler fault processing should continue for the current message
	 *         <ul>
	 *         <li>Return <code>true</code> to continue processing.</li>
	 *         <li>Return <code>false</code> to block processing.</li>
	 *         </ul>
	 * @throws RuntimeException
	 *            Causes the JAX-WS runtime to cease handler fault processing and dispatch the fault.
	 * @throws ProtocolException
	 *            Causes the JAX-WS runtime to cease handler fault processing and dispatch the fault.
	 */
	@Override
	public boolean handleFault(final SOAPMessageContext context)
	{
		return logMessage(context, true);
	}

	protected boolean logMessage(final SOAPMessageContext context, final boolean isFaultMessage)
	{
		final SOAPMessage message = context.getMessage();

		final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try
		{
			message.writeTo(byteArrayOutputStream);
		}
		catch (final SOAPException e)
		{
			LOG.error(e);
		}
		catch (final IOException e)
		{
			LOG.error(e);
		}

		final boolean isRequest = ((Boolean) context.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY)).booleanValue();
		final String messageAsStr = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.ISO_8859_1);
		if (isFaultMessage)
		{

			LOG.error(isRequest ? REQUEST_MSG : RESPONSE_MSG);
			LOG.error("\n");
			LOG.error(new XmlFormatter().format(messageAsStr));
		}
		else
		{
			LOG.debug(isRequest ? REQUEST_MSG : RESPONSE_MSG);
			LOG.debug("\n");
			LOG.debug(new XmlFormatter().format(messageAsStr));
		}
		return true;
	}



	/**
	 * Called at the conclusion of a message exchange pattern just prior to the JAX-WS runtime disptaching a message,
	 * fault or exception. Refer to the description of the handler framework in the JAX-WS specification for full
	 * details.
	 *
	 * @param context
	 *           the message context
	 */
	@Override
	public void close(final MessageContext context)
	{
		//
	}
}
