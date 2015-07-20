/**
 * 
 */
package de.fliegersoftware.prudsys.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.fliegersoftware.prudsys.comm.Communicator;


/**
 * @author gustavok
 * 
 */
public class TestPrudsysServlet extends HttpServlet
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.getWriter().write(Communicator.getInstance().ping() + "");
	}

}
