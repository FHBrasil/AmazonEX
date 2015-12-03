/**
 * 
 */
package de.fliegersoftware.amazon.hmc.servlet;

import de.hybris.platform.jalo.media.Media;
import de.hybris.platform.jalo.media.MediaManager;
import de.hybris.platform.util.Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;


/**
 * @author douglas.canalli
 * 
 */
public class AmazonLogDownloadServlet extends HttpServlet
{
	public static final String CONTENT = "content";

	protected void processRequest(final HttpServletRequest request, final HttpServletResponse response) throws IOException
	{
		try
		{
			final String media = request.getParameter("code");
			final Collection<Media> mediaByCode = MediaManager.getInstance().getMediaByCode(media);
			if (mediaByCode != null)
			{
				final Media downloadMedia = new ArrayList<>(mediaByCode).iterator().next();
				final File f = new File(Config.getParameter("amazon.filesLocation") + "/" + downloadMedia.getRealFileName());
				final byte[] byteArray = IOUtils.toByteArray(new FileInputStream(f));
				response.setHeader("Content-Disposition", "attachment;filename=" + downloadMedia.getRealFileName());
				response.setContentType(downloadMedia.getMime());
				IOUtils.write(byteArray, response.getOutputStream());
				response.flushBuffer();
			}
		}
		catch (final Exception localException)
		{
			response.setContentType("text/html");
			final PrintWriter out = response.getWriter();
			response.setStatus(404);
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Error</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("The document you requested has not been found.");
			out.println("</body>");
			out.println("</html>");
			out.close();
		}
	}

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
			IOException
	{
		processRequest(request, response);
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
			IOException
	{
		processRequest(request, response);
	}

	@Override
	public String getServletInfo()
	{
		return "File Download Servlet";
	}

}
