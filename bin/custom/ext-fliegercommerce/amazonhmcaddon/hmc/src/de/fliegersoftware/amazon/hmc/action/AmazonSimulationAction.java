/**
 * 
 */
package de.fliegersoftware.amazon.hmc.action;

import de.hybris.platform.hmc.util.action.ActionEvent;
import de.hybris.platform.hmc.util.action.ActionResult;
import de.hybris.platform.hmc.util.action.ItemAction;
import de.hybris.platform.jalo.JaloBusinessException;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;
import com.amazonservices.mws.offamazonpayments.model.AuthorizeRequest;
import com.amazonservices.mws.offamazonpayments.model.AuthorizeResponse;
import com.amazonservices.mws.offamazonpayments.model.CaptureRequest;
import com.amazonservices.mws.offamazonpayments.model.CaptureResponse;
import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceResponse;
import com.amazonservices.mws.offamazonpayments.model.Price;
import com.amazonservices.mws.offamazonpayments.model.RefundRequest;
import com.amazonservices.mws.offamazonpayments.model.RefundResponse;

import de.fliegersoftware.amazon.core.jalo.AmazonPaymentPaymentInfo;
import de.fliegersoftware.amazon.hmc.credentials.AmazonCredentials;
import de.fliegersoftware.amazon.hmc.simulation.AmazonSandboxSimulation;


/**
 * Provides for 'amazon' tab the action which will be responsible for the cancel orders
 * 
 * @author douglas.canalli
 */
public class AmazonSimulationAction extends ItemAction
{
	private static final Logger LOG = Logger.getLogger(AmazonSimulationAction.class);

	private static final String SIMULATE_CLOSE_ORDER_ERROR_PARAM = "simulateCloseOrderError";
	private static final String SIMULATE_AUTHORIZE_ERROR_PARAM = "simulateAuthorizeError";
	private static final String SIMULATE_CAPTURE_ERROR_PARAM = "simulateCaptureError";
	private static final String SIMULATE_REFUND_ERROR_PARAM = "simulateRefundError";

	private String confirmationMessage;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.hmc.util.action.ItemAction#getConfirmationMessage()
	 */
	@Override
	public String getConfirmationMessage()
	{
		return confirmationMessage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.hmc.util.action.ItemAction#perform(de.hybris.platform.hmc.util.action.ActionEvent)
	 */
	@Override
	public ActionResult perform(final ActionEvent paramActionEvent) throws JaloBusinessException
	{
		//		final AmazonCredentials credentials = AmazonCredentials.getInstance();
		//		final PaymentInfo paymentInfo = (PaymentInfo) paramActionEvent.getData();

		final AmazonSandboxSimulation simulation = new AmazonSandboxSimulation();

		final Map<String, String> nodeParams = getNodeParams();

		try
		{
			final Object simulate = simulation.simulate(nodeParams,
					getAmazonRequestObject(nodeParams, (AmazonPaymentPaymentInfo) paramActionEvent.getData()));
			confirmationMessage = getSimulationResult(simulate);
			return new ActionResult(ActionResult.OK, confirmationMessage, false);
		}
		catch (final Exception e)
		{
			confirmationMessage = e.getLocalizedMessage();
			return new ActionResult(ActionResult.FAILED, confirmationMessage, false);
		}
	}

	/**
	 * @param simulate
	 * @return confirmationMessage
	 */
	private String getSimulationResult(final Object simulate)
	{
		if (simulate instanceof CloseOrderReferenceResponse)
		{
			final CloseOrderReferenceResponse response = (CloseOrderReferenceResponse) simulate;
			try
			{
				return response.getResponseMetadata().toXMLFragment();
			}
			catch (final OffAmazonPaymentsServiceException e)
			{
				e.printStackTrace();
			}
		}
		else if (simulate instanceof AuthorizeResponse)
		{
			final AuthorizeResponse response = (AuthorizeResponse) simulate;
			try
			{
				return response.getResponseMetadata().toXMLFragment();
			}
			catch (final OffAmazonPaymentsServiceException e)
			{
				e.printStackTrace();
			}
		}
		else if (simulate instanceof CaptureResponse)
		{
			final CaptureResponse response = (CaptureResponse) simulate;
			try
			{
				return response.getResponseMetadata().toXMLFragment();
			}
			catch (final OffAmazonPaymentsServiceException e)
			{
				e.printStackTrace();
			}
		}
		else if (simulate instanceof RefundResponse)
		{
			final RefundResponse response = (RefundResponse) simulate;
			try
			{
				return response.getResponseMetadata().toXMLFragment();
			}
			catch (final OffAmazonPaymentsServiceException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * @param nodeParams
	 * @param amazonPaymentPaymentInfo
	 * @return amazonzRequestObject
	 */
	private Object getAmazonRequestObject(final Map<String, String> nodeParams, final AmazonPaymentPaymentInfo amazonPaymentPaymentInfo)
	{
		final String order = nodeParams.get(SIMULATE_CLOSE_ORDER_ERROR_PARAM);
		final String authorization = nodeParams.get(SIMULATE_AUTHORIZE_ERROR_PARAM);
		final String capture = nodeParams.get(SIMULATE_CAPTURE_ERROR_PARAM);
		final String refund = nodeParams.get(SIMULATE_REFUND_ERROR_PARAM);

		if (StringUtils.isNotBlank(order))
		{
			return orderRequest(amazonPaymentPaymentInfo);
		}
		else if (StringUtils.isNotBlank(authorization))
		{
			return authorizationRequest(amazonPaymentPaymentInfo);
		}
		else if (StringUtils.isNotBlank(capture))
		{
			return captureRequest(amazonPaymentPaymentInfo);
		}
		else if (StringUtils.isNotBlank(refund))
		{
			return refundRequest(amazonPaymentPaymentInfo);
		}

		return null;
	}

	/**
	 * @param amazonPaymentPaymentInfo
	 * @return refundRequest
	 */
	private RefundRequest refundRequest(final AmazonPaymentPaymentInfo amazonPaymentPaymentInfo)
	{
		final String currency = amazonPaymentPaymentInfo.getCurrencyRefundRequestAmount();
		final Double amount = amazonPaymentPaymentInfo.getRefundRequestAmount();
		final String captureId = amazonPaymentPaymentInfo.getAmazonCaptureId();
		try
		{
			return AmazonCredentials.getInstance().getRefundRequest(captureId, amount, currency);
		}
		catch (final Exception e)
		{
			LOG.error("Error while creating the request to simulate the refund", e);
			return null;
		}
	}

	/**
	 * @param amazonPaymentPaymentInfo
	 * @return captureRequest
	 */
	private CaptureRequest captureRequest(final AmazonPaymentPaymentInfo amazonPaymentPaymentInfo)
	{
		final String authorizationId = amazonPaymentPaymentInfo.getAmazonLastAuthorizationId();
		final Price amount = new Price();
		if (amazonPaymentPaymentInfo.getAmazonOrderAmount() != null)
		{
			amount.setAmount(amazonPaymentPaymentInfo.getAmazonOrderAmount().toString());
			amount.setCurrencyCode(amazonPaymentPaymentInfo.getCurrencyRefundRequestAmount());
		}
		return AmazonCredentials.getInstance().getCaptureRequest(authorizationId, amount);
	}

	/**
	 * @param amazonPaymentPaymentInfo
	 * @return authorizationRequest
	 */
	private AuthorizeRequest authorizationRequest(final AmazonPaymentPaymentInfo amazonPaymentPaymentInfo)
	{
		final String orderReferenceId = amazonPaymentPaymentInfo.getAmazonOrderReferenceId();
		final Price amount = new Price();
		if (amazonPaymentPaymentInfo.getAmazonOrderAmount() != null)
		{
			amount.setAmount(amazonPaymentPaymentInfo.getAmazonOrderAmount().toString());
			amount.setCurrencyCode(amazonPaymentPaymentInfo.getCurrencyRefundRequestAmount());
		}
		return AmazonCredentials.getInstance().getAuthorizeRequest(orderReferenceId, amount);
	}

	/**
	 * @param amazonPaymentPaymentInfo
	 * @return orderRequest
	 */
	private CloseOrderReferenceRequest orderRequest(final AmazonPaymentPaymentInfo amazonPaymentPaymentInfo)
	{
		final String orderReferenceId = amazonPaymentPaymentInfo.getAmazonOrderReferenceId();
		return AmazonCredentials.getInstance().getCloseOrderReferenceRequest(orderReferenceId);
	}
}
