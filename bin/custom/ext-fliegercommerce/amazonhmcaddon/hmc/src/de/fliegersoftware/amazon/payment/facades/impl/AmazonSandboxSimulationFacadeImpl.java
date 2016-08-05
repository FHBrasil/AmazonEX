package de.fliegersoftware.amazon.payment.facades.impl;

import com.amazonservices.mws.offamazonpayments.model.AuthorizeRequest;
import com.amazonservices.mws.offamazonpayments.model.CaptureRequest;
import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.RefundRequest;

import de.fliegersoftware.amazon.hmc.credentials.AmazonCredentials;
import de.fliegersoftware.amazon.payment.facades.AmazonSandboxSimulationConfig;
import de.fliegersoftware.amazon.payment.facades.AmazonSandboxSimulationFacade;


public class AmazonSandboxSimulationFacadeImpl implements AmazonSandboxSimulationFacade
{

	private final ThreadLocal<AmazonSandboxSimulationConfig> currentSessionConfig;

	public AmazonSandboxSimulationFacadeImpl()
	{
		currentSessionConfig = new ThreadLocal<AmazonSandboxSimulationConfig>();
	}

	@Override
	public boolean isSimulation()
	{
		if (AmazonCredentials.getInstance().getAmazonConfig().isSandboxMode())
		{
			final AmazonSandboxSimulationConfig config = currentSessionConfig.get();
			return config != null;
		}
		return false;
	}

	@Override
	public void decorate(final CloseOrderReferenceRequest req)
	{
		final AmazonSandboxSimulationConfig config = currentSessionConfig.get();
		if (config != null)
		{
			switch (config.getSimulateCloseOrderError())
			{
				case AmazonClosed:
					req.setClosureReason("{\"SandboxSimulation\": {\"State\":\"Closed\",\"ReasonCode\":\"AmazonClosed\"}}");
					break;
				default:
					break;
			}
		}
	}

	@Override
	public void decorate(final AuthorizeRequest req)
	{
		final AmazonSandboxSimulationConfig config = currentSessionConfig.get();
		if (config != null)
		{
			switch (config.getSimulateAuthorizeError())
			{
				case InvalidPaymentMethod:
					req.setSellerAuthorizationNote("{\"SandboxSimulation\": {\"State\":\"Declined\",\"ReasonCode\":\"InvalidPaymentMethod\",\"PaymentMethodUpdateTimeInMins\":5}}");
					break;
				case AmazonRejected:
					req.setSellerAuthorizationNote("{\"SandboxSimulation\": {\"State\":\"Declined\",\"ReasonCode\":\"AmazonRejected\"}}");
					break;
				case TransactionTimedOut:
					req.setSellerAuthorizationNote("{\"SandboxSimulation\": {\"State\":\"Declined\",\"ReasonCode\":\"TransactionTimedOut\"}}");
					break;
				case ExpiredUnused:
					req.setSellerAuthorizationNote("{\"SandboxSimulation\": {\"State\":\"Closed\",\"ReasonCode\":\"ExpiredUnused\",\"ExpirationTimeInMins\":1}}");
					break;
				case AmazonClosed:
					req.setSellerAuthorizationNote("{\"SandboxSimulation\": {\"State\":\"Closed\",\"ReasonCode\":\"AmazonClosed\"}}");
					break;
				case None:
				default:
					break;
			}
		}
	}

	public void decorate(final CaptureRequest req)
	{
		final AmazonSandboxSimulationConfig config = currentSessionConfig.get();
		if (config != null)
		{
			switch (config.getSimulateCaptureError())
			{
				case CapturePending:
					req.setSellerCaptureNote("{\"SandboxSimulation\": {\"State\":\"Pending\"}}");
					break;
				case AmazonRejected:
					req.setSellerCaptureNote("{\"SandboxSimulation\": {\"State\":\"Declined\",\"ReasonCode\":\"AmazonRejected\"}}");
					break;
				case AmazonClosed:
					req.setSellerCaptureNote("{\"SandboxSimulation\": {\"State\":\"Closed\",\"ReasonCode\":\"AmazonClosed\"}}");
					break;
				case None:
				default:
					break;
			}
		}
	}

	@Override
	public void decorate(final RefundRequest req)
	{
		final AmazonSandboxSimulationConfig config = currentSessionConfig.get();
		if (config != null)
		{
			switch (config.getSimulateRefundError())
			{
				case AmazonRejected:
					req.setSellerRefundNote("{\"SandboxSimulation\": {\"State\":\"Declined\",\"ReasonCode\":\"AmazonRejected\"}}");
					break;
				case None:
				default:
					break;
			}
		}
	}

	@Override
	public void clearSimulationConfig()
	{
		currentSessionConfig.remove();
	}

	@Override
	public void setSimulationConfig(final AmazonSandboxSimulationConfig config)
	{
		currentSessionConfig.set(config);
	}
}