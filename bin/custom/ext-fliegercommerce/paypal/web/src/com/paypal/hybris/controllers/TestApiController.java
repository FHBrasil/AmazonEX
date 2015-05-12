package com.paypal.hybris.controllers;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paypal.hybris.facades.PaypalTestFacade;


/**
 * Controller for the paypal extension web-testing. MUST BE DISABLED OR REMOVED
 * on production servers!
 * 
 * This controller gets a command and parameters from the web-console. Then, if
 * it can recognize such command, redirects it to paypal testing facade, waits
 * for a result and sends it back to browser.
 * 
 * Incoming command and parameters should be in a standard "POST" form. Returned
 * result usually is in JSON format, but it depends on the facade.
 * 
 * @author Valentyn Markovych, Gorilla
 * 
 */
@Controller
@RequestMapping(value = "/test")
public class TestApiController {

//private static final String API_MIME_TYPE = "application/json"; // MIME for API commands
private static final Logger LOG = Logger.getLogger("TestApiServlet");


@Autowired
private PaypalTestFacade paypalTestFacade;


public TestApiController() {

}


@RequestMapping(value = "/console")
public String console() {

	return "test/console";
}


@RequestMapping(value = "/accepted")
public String accepted() {

	return "test/accepted";
}


@RequestMapping(value = "/denied")
public String denied() {

	return "test/denied";
}


@RequestMapping(value = "/api")
public @ResponseBody
Object doCommand(final HttpServletRequest request) {

	@SuppressWarnings("unchecked")
	final Map<String, String[]> params = request.getParameterMap();

	logRequest(params);

	// Delegate the execution of the command and return the result to user
	return paypalTestFacade.doCommand(params);
}


/**
 * @param params
 *          Parameters map.
 */
private void logRequest(final Map<String, String[]> params) {

	// Out requested params to log
	final StringBuilder reqStr = new StringBuilder();
	for (final Map.Entry<String, String[]> param : params.entrySet()) {
		final String key = param.getKey();
		reqStr.append(' ').append(key).append('=');
		if (key != null && key.toLowerCase().startsWith("pass")) { // Do not log passwords, even for test.
			reqStr.append("******");
		} else {
			reqStr.append(param.getValue()[0]);
		}
		reqStr.append('\n');
	}
	LOG.info("TestApiServlet.logRequest: API command: \n" + reqStr.toString());
}

}
