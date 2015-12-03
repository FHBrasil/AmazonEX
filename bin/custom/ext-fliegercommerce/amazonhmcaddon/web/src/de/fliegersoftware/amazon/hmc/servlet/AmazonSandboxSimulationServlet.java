/**
 * 
 */
package de.fliegersoftware.amazon.hmc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;

import de.fliegersoftware.amazon.core.services.AmazonConfigService;
import de.fliegersoftware.amazon.payment.facades.AmazonSandboxSimulationConfig;
import de.fliegersoftware.amazon.payment.facades.AmazonSandboxSimulationFacade;


/**
 * @author douglas.canalli
 * 
 */
public class AmazonSandboxSimulationServlet extends HttpServlet
{
	public static final String CONTENT = "content";

	private AmazonConfigService amazonConfigService;
	private AmazonSandboxSimulationFacade amazonSandboxSimulationFacade;

	private static final String SIMULATION_CODE_PARAM = "amazonSimulationCode";

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
			IOException
	{
		final String parameter = request.getParameter(SIMULATION_CODE_PARAM);

		getAmazonSandboxSimulationFacade().setSimulationConfig(getConfigFromRequest(request));
		getAmazonSandboxSimulationFacade().clearSimulationConfig();

		if (parameter != null)
		{
			switch (parameter)
			{
				case "simulateOrderClosed":
					break;
				case "simulateInvalidPayment":
					break;
				case "simulateAuthorizeRejected":
					break;
				case "simulateAuthorizeTransactionTimedOut":
					break;
				case "simulateAuthorizeExpiredUnused":
					break;
				case "simulateAuthorizeClosed":
					break;
				case "simulateCapturePending":
					break;
				case "simulateCaptureRejected":
					break;
				case "simulateCaptureClosed":
					break;
				case "simulateRefundRejected":
					break;
			}
		}
		response.sendRedirect(request.getHeader("referer"));
	}

	@Override
	public String getServletInfo()
	{
		return "Amazon Sandbox Simulation Servlet";
	}


	private AmazonSandboxSimulationConfig getConfigFromRequest(final HttpServletRequest request)
	{
		final String parameter = request.getParameter(SIMULATION_CODE_PARAM);

		final AmazonSandboxSimulationConfig config = new AmazonSandboxSimulationConfig();
		//		config.setSimulateCloseOrderError(parseNumberAndGetEnumValue(simulateCloseOrderError, SimulateCloseOrderError.values()));
		//		config.setSimulateAuthorizeError(parseNumberAndGetEnumValue(simulateAuthorizeError, SimulateAuthorizeError.values()));
		//		config.setSimulateCaptureError(parseNumberAndGetEnumValue(simulateCaptureError, SimulateCaptureError.values()));
		//		config.setSimulateRefundError(parseNumberAndGetEnumValue(simulateRefundError, SimulateRefundError.values()));

		return config;
	}

	private <T> T parseNumberAndGetEnumValue(final String value, final T[] values)
	{
		try
		{
			final int index = Integer.parseInt(value);
			return values[index];
		}
		catch (final NumberFormatException e)
		{
			return values[0];
		}
	}

	protected AmazonSandboxSimulationFacade getAmazonSandboxSimulationFacade()
	{
		return amazonSandboxSimulationFacade;
	}

	@Required
	public void setAmazonSandboxSimulationFacade(final AmazonSandboxSimulationFacade amazonSandboxSimulationFacade)
	{
		this.amazonSandboxSimulationFacade = amazonSandboxSimulationFacade;
	}

	protected AmazonConfigService getAmazonConfigService()
	{
		return amazonConfigService;
	}

	@Required
	public void setAmazonConfigService(final AmazonConfigService amazonConfigService)
	{
		this.amazonConfigService = amazonConfigService;
	}

}
