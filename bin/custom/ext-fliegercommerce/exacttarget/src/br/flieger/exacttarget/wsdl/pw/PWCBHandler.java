/*
 * Copyright 2004,2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.flieger.exacttarget.wsdl.pw;

import de.hybris.platform.util.Config;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.log4j.Logger;
import org.apache.ws.security.WSPasswordCallback;


/**
 * Modified WSS4J callback handler to accept user and password information from properties file via ClientTest. The
 * original class from Apache required hard-coded values for this information. This class may be further modified for
 * production usage so that this information is fetched from any external store, such as a database.
 * 
 * @author Erik Gfesser
 */
public class PWCBHandler implements CallbackHandler
{
	public static String user = Config.getParameter("exacttarget.user");
	public static String password = Config.getParameter("exacttarget.password");

	private static final Logger LOG = Logger.getLogger(PWCBHandler.class.getName());

	public void handle(final Callback[] callbacks) throws IOException, UnsupportedCallbackException
	{
		for (int i = 0; i < callbacks.length; i++)
		{
			if (callbacks[i] instanceof WSPasswordCallback)
			{
				final WSPasswordCallback pwcb = (WSPasswordCallback) callbacks[i];
				final String id = pwcb.getIdentifier();
				if (user.equals(id))
				{
					pwcb.setPassword(password);
				}
			}
			else
			{
				throw new UnsupportedCallbackException(callbacks[i], "Unrecognized Callback");
			}
		}
	} //end method handle
} //end class PWCBHandler