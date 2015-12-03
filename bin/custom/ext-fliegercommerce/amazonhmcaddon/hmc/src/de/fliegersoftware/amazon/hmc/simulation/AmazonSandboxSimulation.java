/**
 * 
 */
package de.fliegersoftware.amazon.hmc.simulation;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceClient;
import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;
import com.amazonservices.mws.offamazonpayments.model.AuthorizeRequest;
import com.amazonservices.mws.offamazonpayments.model.AuthorizeResponse;
import com.amazonservices.mws.offamazonpayments.model.CaptureRequest;
import com.amazonservices.mws.offamazonpayments.model.CaptureResponse;
import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceResponse;
import com.amazonservices.mws.offamazonpayments.model.RefundRequest;
import com.amazonservices.mws.offamazonpayments.model.RefundResponse;

import de.fliegersoftware.amazon.hmc.credentials.AmazonCredentials;
import de.fliegersoftware.amazon.payment.facades.AmazonSandboxSimulationConfig;
import de.fliegersoftware.amazon.payment.facades.AmazonSandboxSimulationFacade;
import de.fliegersoftware.amazon.payment.facades.SimulateAuthorizeError;
import de.fliegersoftware.amazon.payment.facades.SimulateCaptureError;
import de.fliegersoftware.amazon.payment.facades.SimulateCloseOrderError;
import de.fliegersoftware.amazon.payment.facades.SimulateRefundError;
import de.fliegersoftware.amazon.payment.facades.impl.AmazonSandboxSimulationFacadeImpl;


/**
 * @author douglas.canalli
 * 
 */
public class AmazonSandboxSimulation
{
	public static final String CONTENT = "content";

	private static final Logger LOG = Logger.getLogger(AmazonSandboxSimulation.class);

	private AmazonSandboxSimulationFacade amazonSandboxSimulationFacade;

	private final OffAmazonPaymentsServiceClient service = AmazonCredentials.getInstance().getService();

	private static final String SIMULATE_CLOSE_ORDER_ERROR_PARAM = "simulateCloseOrderError";
	private static final String SIMULATE_AUTHORIZE_ERROR_PARAM = "simulateAuthorizeError";
	private static final String SIMULATE_CAPTURE_ERROR_PARAM = "simulateCaptureError";
	private static final String SIMULATE_REFUND_ERROR_PARAM = "simulateRefundError";

	/**
	 * @param requestParams
	 */
	public Object simulate(final Map<String, String> requestParams, final Object request) throws Exception
	{
		if (amazonSandboxSimulationFacade == null)
		{
			amazonSandboxSimulationFacade = new AmazonSandboxSimulationFacadeImpl();
		}
		amazonSandboxSimulationFacade.setSimulationConfig(getConfigFromRequest(requestParams));

		if (StringUtils.isNotBlank(requestParams.get(SIMULATE_CLOSE_ORDER_ERROR_PARAM)))
		{
			final CloseOrderReferenceRequest closeOrderReferenceRequest = (CloseOrderReferenceRequest) request;
			amazonSandboxSimulationFacade.decorate(closeOrderReferenceRequest);
			return closeOrderReference(closeOrderReferenceRequest);
		}
		else if (StringUtils.isNotBlank(requestParams.get(SIMULATE_AUTHORIZE_ERROR_PARAM)))
		{
			final AuthorizeRequest authorizeRequest = (AuthorizeRequest) request;
			amazonSandboxSimulationFacade.decorate(authorizeRequest);
			return authorize(authorizeRequest);
		}
		else if (StringUtils.isNotBlank(requestParams.get(SIMULATE_CAPTURE_ERROR_PARAM)))
		{
			final CaptureRequest captureRequest = (CaptureRequest) request;
			amazonSandboxSimulationFacade.decorate(captureRequest);
			return capture(captureRequest);
		}
		else if (StringUtils.isNotBlank(requestParams.get(SIMULATE_REFUND_ERROR_PARAM)))
		{
			final RefundRequest refundRequest = (RefundRequest) request;
			amazonSandboxSimulationFacade.decorate(refundRequest);
			return refund(refundRequest);
		}
		return null;
	}

	/**
	 * @param refundRequest
	 */
	private RefundResponse refund(final RefundRequest refundRequest) throws Exception
	{
		try
		{
			return service.refund(refundRequest);
		}
		catch (final OffAmazonPaymentsServiceException e)
		{
			LOG.error("Error while trying to simulate the refund", e);
			throw e;
		}
		finally
		{
			amazonSandboxSimulationFacade.clearSimulationConfig();
		}
	}

	/**
	 * @param captureRequest
	 */
	private CaptureResponse capture(final CaptureRequest captureRequest) throws Exception
	{
		try
		{
			return service.capture(captureRequest);
		}
		catch (final OffAmazonPaymentsServiceException e)
		{
			LOG.error("Error while trying to simulate the capture", e);
			throw e;
		}
		finally
		{
			amazonSandboxSimulationFacade.clearSimulationConfig();
		}
	}

	/**
	 * @param authorizeRequest
	 */
	private AuthorizeResponse authorize(final AuthorizeRequest authorizeRequest) throws Exception
	{
		try
		{
			return service.authorize(authorizeRequest);
		}
		catch (final OffAmazonPaymentsServiceException e)
		{
			LOG.error("Error while trying to simulate the authorize", e);
			throw e;
		}
		finally
		{
			amazonSandboxSimulationFacade.clearSimulationConfig();
		}
	}

	private CloseOrderReferenceResponse closeOrderReference(final CloseOrderReferenceRequest closeOrderReferenceRequest)
			throws Exception
	{
		try
		{
			return service.closeOrderReference(closeOrderReferenceRequest);
		}
		catch (final OffAmazonPaymentsServiceException e)
		{
			LOG.error("Error while trying to simulate the order close", e);
			throw e;
		}
		finally
		{
			amazonSandboxSimulationFacade.clearSimulationConfig();
		}
	}

	public String getServletInfo()
	{
		return "Amazon Sandbox Simulation Servlet";
	}


	private AmazonSandboxSimulationConfig getConfigFromRequest(final Map<String, String> requestParams)
	{

		final String simulateCloseOrderError = StringUtils.isNotBlank(requestParams.get(SIMULATE_CLOSE_ORDER_ERROR_PARAM)) ? requestParams
				.get(SIMULATE_CLOSE_ORDER_ERROR_PARAM) : "0";
		final String simulateAuthorizeError = StringUtils.isNotBlank(requestParams.get(SIMULATE_AUTHORIZE_ERROR_PARAM)) ? requestParams
				.get(SIMULATE_AUTHORIZE_ERROR_PARAM) : "0";
		final String simulateCaptureError = StringUtils.isNotBlank(requestParams.get(SIMULATE_CAPTURE_ERROR_PARAM)) ? requestParams
				.get(SIMULATE_CAPTURE_ERROR_PARAM) : "0";
		final String simulateRefundError = StringUtils.isNotBlank(requestParams.get(SIMULATE_REFUND_ERROR_PARAM)) ? requestParams
				.get(SIMULATE_REFUND_ERROR_PARAM) : "0";

		final AmazonSandboxSimulationConfig config = new AmazonSandboxSimulationConfig();
		config.setSimulateCloseOrderError(parseNumberAndGetEnumValue(simulateCloseOrderError, SimulateCloseOrderError.values()));
		config.setSimulateAuthorizeError(parseNumberAndGetEnumValue(simulateAuthorizeError, SimulateAuthorizeError.values()));
		config.setSimulateCaptureError(parseNumberAndGetEnumValue(simulateCaptureError, SimulateCaptureError.values()));
		config.setSimulateRefundError(parseNumberAndGetEnumValue(simulateRefundError, SimulateRefundError.values()));
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

}
