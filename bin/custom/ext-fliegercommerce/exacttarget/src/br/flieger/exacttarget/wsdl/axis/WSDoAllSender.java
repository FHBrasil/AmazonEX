// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   WSDoAllSender.java

package br.flieger.exacttarget.wsdl.axis;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

import org.apache.axis.AxisFault;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.SOAPPart;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.handler.RequestData;
import org.apache.ws.security.util.WSSecurityUtil;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;


public class WSDoAllSender extends WSDoAllHandler
{

	public WSDoAllSender()
	{
	}

	public void invoke(final MessageContext mc) throws AxisFault
	{
		boolean doDebug;
		long t0;
		long t1;
		long t2;
		RequestData reqData;
		/* 56 */doDebug = log.isDebugEnabled();
		/* 58 */t0 = 0L;
		/* 58 */t1 = 0L;
		/* 58 */t2 = 0L;
		/* 58 */final long t3 = 0L;
		/* 59 */if (tlog.isDebugEnabled())
		{
			t0 = System.currentTimeMillis();
		}
		/* 63 */if (doDebug && mc.getCurrentMessage() != null && mc.getCurrentMessage().getMessageType() != null)
		{
			log.debug("WSDoAllSender: enter invoke() with msg type: " + mc.getCurrentMessage().getMessageType());
		}
		/* 69 */reqData = new RequestData();
		/* 71 */reqData.setMsgContext(mc);
		Vector actions;
		int doAction = 0;
		/* 80 */actions = new Vector();
		/* 81 */final String action = getString("action", mc);
		/* 82 */if (action == null)
		{
			throw new AxisFault("WSDoAllSender: No action defined");
		}
		/* 85 */
		try
		{
			doAction = WSSecurityUtil.decodeAction(action, actions);
		}
		catch (final WSSecurityException e1)
		{
			// YTODO Auto-generated catch block
			e1.printStackTrace();
		}
		/* 86 */if (doAction == 0)
		{
			/* 225 */reqData.clear();
			/* 226 */reqData = null;
			/* 226 */return;
		}
		Message message;
		/* 94 */reqData.setUsername((String) getOption("user"));
		/* 95 */if (reqData.getUsername() == null || reqData.getUsername().equals(""))
		{
			/* 96 */final String username = (String) getProperty(reqData.getMsgContext(), "user");
			/* 97 */if (username != null)
			{
				/* 98 */reqData.setUsername(username);
			}
			else
			{
				/* 100 */reqData.setUsername(((MessageContext) reqData.getMsgContext()).getUsername());
				/* 101 */((MessageContext) reqData.getMsgContext()).setUsername(null);
			}
		}
		/* 109 */if ((doAction & 0x43) != 0 && (reqData.getUsername() == null || reqData.getUsername().equals("")))
		{
			throw new AxisFault("WSDoAllSender: Empty username for specified action");
		}
		/* 119 */if (doDebug)
		{
			/* 120 */log.debug("Action: " + doAction);
			/* 121 */log.debug("Actor: " + reqData.getActor());
		}
		Document doc = null;
		/* 140 */message = mc.getCurrentMessage();
		/* 146 */if (message == null)
		{
			/* 225 */reqData.clear();
			/* 226 */reqData = null;
			/* 226 */return;
		}
		/* 154 */try
		{
			/* 154 */final SOAPPart sPart = (SOAPPart) message.getSOAPPart();
			{
				try
				{
					/* 158 */doc = ((SOAPEnvelope) sPart.getEnvelope()).getAsDocument();
				}
				/* 160 */catch (final Exception e)
				{
					/* 161 */throw new AxisFault("WSDoAllSender: cannot get SOAP envlope from message" + e);
				}
			}
			/* 166 */if (tlog.isDebugEnabled())
			{
				t1 = System.currentTimeMillis();
			}
			/* 170 */doSenderAction(doAction, doc, reqData, actions, !mc.getPastPivot());
			/* 172 */if (tlog.isDebugEnabled())
			{
				t2 = System.currentTimeMillis();
			}
			/* 190 */if (reqData.isNoSerialization())
			{
				/* 191 */((MessageContext) reqData.getMsgContext()).setProperty("SND_SECURITY", doc);
			}
			else
			{
				/* 194 */final ByteArrayOutputStream os = new ByteArrayOutputStream();
				/* 195 */XMLUtils.outputDOM(doc, os, true);
				/* 196 */sPart.setCurrentMessage(os.toByteArray(), 4);
				/* 197 */if (doDebug)
				{
					/* 198 */String osStr = null;
					/* 200 */try
					{
						/* 200 */osStr = os.toString("UTF-8");
					}
					/* 201 */catch (final UnsupportedEncodingException e)
					{
						/* 202 */osStr = os.toString();
					}
					/* 204 */log.debug("Send request:");
					/* 205 */log.debug(osStr);
				}
				/* 207 */((MessageContext) reqData.getMsgContext()).setProperty("SND_SECURITY", null);
			}
			/* 210 */if (tlog.isDebugEnabled())
			{
				/* 212 */tlog.debug("Send request: total= " + (t3 - t0) + " request preparation= " + (t1 - t0)
						+ " request processing= " + (t2 - t1) + " request to Axis= " + (t3 - t2) + "\n");
			}
			/* 219 */if (doDebug)
			{
				log.debug("WSDoAllSender: exit invoke()");
			}
		}
		/* 222 */catch (final WSSecurityException e)
		{
			/* 223 */throw new AxisFault(e.getMessage(), e);
		}
		/* 225 */reqData.clear();
		/* 226 */reqData = null;
		final Exception exception;
	}

	static Class _mthclass$(final String x0) throws ClassNotFoundException
	{
		/* 44 */return Class.forName(x0);
	}

	protected static Log log;
	private static Log tlog = LogFactory.getLog("org.apache.ws.security.TIME");

	static
	{
		/* 44 */log = LogFactory.getLog((WSDoAllSender.class).getName());
	}
}
