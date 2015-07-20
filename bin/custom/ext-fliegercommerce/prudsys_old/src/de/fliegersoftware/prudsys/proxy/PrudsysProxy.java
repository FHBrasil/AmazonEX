/**
 * 
 */
package de.fliegersoftware.prudsys.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


/**
 * @author gustavok
 * 
 */
public class PrudsysProxy extends HttpServlet
{
	/**
	 * Logger
	 */
	private static Logger log = Logger.getLogger(PrudsysProxy.class);

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{

		StringBuffer sb = new StringBuffer();

		if ("GET".equals(req.getMethod()))
		{
			URL url = new URL(req.getParameter("link"));
			URLConnection conn = url.openConnection();
			conn.connect();

			try
			{
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String dataFromUrl = "";
				while ((dataFromUrl = br.readLine()) != null)
				{
					sb.append(dataFromUrl + '\n');
				}
			}
			catch (final Exception e)
			{
				log.error(e);
			}

			resp.getWriter().write(sb.toString());
		}
	}

}
