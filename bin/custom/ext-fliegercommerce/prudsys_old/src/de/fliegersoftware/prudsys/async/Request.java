package de.fliegersoftware.prudsys.async;

import java.net.URL;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;


public class Request implements Callable<Response>
{
	private static final Logger LOG = Logger.getLogger(Request.class);
	private URL url;

	public Request(URL url)
	{
		this.url = url;
	}

	@Override
	public Response call() throws Exception
	{
		LOG.debug("Executed called: " + url);
		return new Response(url.openStream());
	}
}