package de.fliegersoftware.amazon.payment.addon.controllers.pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import de.fliegersoftware.amazon.payment.facades.AmazonSandboxSimulationConfig;
import de.fliegersoftware.amazon.payment.facades.AmazonSandboxSimulationFacade;
import de.fliegersoftware.amazon.payment.facades.SimulateAuthorizeError;
import de.fliegersoftware.amazon.payment.facades.SimulateCaptureError;
import de.fliegersoftware.amazon.payment.facades.SimulateCloseOrderError;
import de.fliegersoftware.amazon.payment.facades.SimulateRefundError;

public class AmazonSandboxModeHandlerInterceptor extends HandlerInterceptorAdapter {

	private static final String SANDBOXFORM_SIMULATECLOSEORDERERROR_PARAM = "simulateCloseOrderError";
	private static final String SANDBOXFORM_SIMULATEAUTHORIZEERROR_PARAM = "simulateAuthorizeError";
	private static final String SANDBOXFORM_SIMULATECAPTUREERROR_PARAM = "simulateCaptureError";
	private static final String SANDBOXFORM_SIMULATEREFUNDERROR_PARAM = "simulateRefundError";

	private AmazonSandboxSimulationFacade amazonSandboxSimulationFacade;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(shouldConfigSandboxMode(request, response, (HandlerMethod)handler)) {
			getAmazonSandboxSimulationFacade().setSimulationConfig(getConfigFromRequest(request));
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(getAmazonSandboxSimulationFacade().isSimulation()) {
			getAmazonSandboxSimulationFacade().clearSimulationConfig();
		}
	}

	private boolean shouldConfigSandboxMode(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) {
		return ("POST").equalsIgnoreCase(request.getMethod())
				&& handler.getBeanType() == AmazonCheckoutPageController.class
				&& getAmazonSandboxSimulationFacade().isSimulation();
	}

	private AmazonSandboxSimulationConfig getConfigFromRequest(HttpServletRequest request) {
		String simulateCloseOrderError = request.getParameter(SANDBOXFORM_SIMULATECLOSEORDERERROR_PARAM);
		String simulateAuthorizeError = request.getParameter(SANDBOXFORM_SIMULATEAUTHORIZEERROR_PARAM);
		String simulateCaptureError = request.getParameter(SANDBOXFORM_SIMULATECAPTUREERROR_PARAM);
		String simulateRefundError = request.getParameter(SANDBOXFORM_SIMULATEREFUNDERROR_PARAM);

		AmazonSandboxSimulationConfig config = new AmazonSandboxSimulationConfig();
		config.setSimulateCloseOrderError(parseNumberAndGetEnumValue(simulateCloseOrderError, SimulateCloseOrderError.values()));
		config.setSimulateAuthorizeError(parseNumberAndGetEnumValue(simulateAuthorizeError, SimulateAuthorizeError.values()));
		config.setSimulateCaptureError(parseNumberAndGetEnumValue(simulateCaptureError, SimulateCaptureError.values()));
		config.setSimulateRefundError(parseNumberAndGetEnumValue(simulateRefundError, SimulateRefundError.values()));

		return config;
	}

	private <T> T parseNumberAndGetEnumValue(String value, T[] values) {
		try {
			int index = Integer.parseInt(value);
			return values[index];
		} catch(NumberFormatException e) {
			return values[0];
		}
	}

	protected AmazonSandboxSimulationFacade getAmazonSandboxSimulationFacade() {
		return amazonSandboxSimulationFacade;
	}

	@Required
	public void setAmazonSandboxSimulationFacade(AmazonSandboxSimulationFacade amazonSandboxSimulationFacade) {
		this.amazonSandboxSimulationFacade = amazonSandboxSimulationFacade;
	}
}
