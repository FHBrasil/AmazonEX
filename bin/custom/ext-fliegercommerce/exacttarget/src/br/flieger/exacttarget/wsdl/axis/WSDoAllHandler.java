// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   WSDoAllHandler.java

package br.flieger.exacttarget.wsdl.axis;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axis.AxisFault;
import org.apache.axis.Handler;
import org.apache.axis.MessageContext;
import org.apache.axis.components.logger.LogFactory;
import org.apache.axis.utils.LockableHashtable;
import org.apache.commons.logging.Log;
import org.apache.ws.security.handler.WSHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public abstract class WSDoAllHandler extends WSHandler implements Handler
{

	public WSDoAllHandler()
	{
		/* 45 */makeLockable = false;
	}

	protected void setOptionsLockable(final boolean makeLockable)
	{
		/* 54 */this.makeLockable = makeLockable;
	}

	protected void initHashtable()
	{
		/* 58 */if (makeLockable)
		{
			/* 59 */options = new LockableHashtable();
		}
		else
		{
			/* 61 */options = new Hashtable();
		}
	}

	public void init()
	{
	}

	public void cleanup()
	{
	}

	public boolean canHandleBlock(final QName qname)
	{
		/* 77 */return false;
	}

	public void onFault(final MessageContext messagecontext)
	{
	}

	public void setOption(final String name, final Object value)
	{
		/* 87 */if (options == null)
		{
			/* 87 */initHashtable();
		}
		/* 88 */options.put(name, value);
	}

	public boolean setOptionDefault(final String name, final Object value)
	{
		/* 102 */final boolean val = (options == null || options.get(name) == null) && value != null;
		/* 103 */if (val)
		{
			/* 104 */setOption(name, value);
		}
		/* 106 */return val;
	}

	@Override
	public Object getOption(final String name)
	{
		/* 117 */if (name == null)
		{
			/* 118 */throw new IllegalArgumentException("name cannot be null");
		}
		/* 120 */if (options == null)
		{
			/* 121 */return null;
		}
		else
		{
			/* 123 */return options.get(name);
		}
	}

	public Hashtable getOptions()
	{
		/* 130 */return options;
	}

	public void setOptions(final Hashtable opts)
	{
		/* 134 */options = opts;
	}

	public void setName(final String name)
	{
		/* 141 */this.name = name;
	}

	public String getName()
	{
		/* 148 */return name;
	}

	public Element getDeploymentData(final Document doc)
	{
		/* 152 */log.debug("Enter: BasicHandler::getDeploymentData");
		/* 154 */final Element root = doc.createElementNS("", "handler");
		/* 156 */root.setAttribute("class", getClass().getName());
		/* 157 */options = getOptions();
		/* 158 */if (options != null)
		{
			Element e1;
			/* 159 */for (final Enumeration e = options.keys(); e.hasMoreElements(); root.appendChild(e1))
			{
				/* 161 */final String k = (String) e.nextElement();
				/* 162 */final Object v = options.get(k);
				/* 163 */e1 = doc.createElementNS("", "option");
				/* 164 */e1.setAttribute("name", k);
				/* 165 */e1.setAttribute("value", v.toString());
			}

		}
		/* 169 */log.debug("Exit: WSDoAllHandler::getDeploymentData");
		/* 170 */return root;
	}

	public void generateWSDL(final MessageContext messagecontext) throws AxisFault
	{
	}

	public List getUnderstoodHeaders()
	{
		/* 182 */return null;
	}

	@Override
	public Object getProperty(final Object msgContext, final String key)
	{
		/* 186 */return ((MessageContext) msgContext).getProperty(key);
	}

	@Override
	public void setProperty(final Object msgContext, final String key, final Object value)
	{
		/* 190 */((MessageContext) msgContext).setProperty(key, value);
	}

	@Override
	public String getPassword(final Object msgContext)
	{
		/* 194 */return ((MessageContext) msgContext).getPassword();
	}

	@Override
	public void setPassword(final Object msgContext, final String password)
	{
		/* 198 */((MessageContext) msgContext).setPassword(password);
	}

	static Class _mthclass$(final String x0) throws ClassNotFoundException
	{
		/* 43 */return Class.forName(x0);
	}

	private static Log log;
	protected boolean makeLockable;
	protected Hashtable options;
	protected String name;

	static
	{
		/* 42 */log = LogFactory.getLog((WSDoAllHandler.class).getName());
	}
}
