package de.fliegersoftware.amazon.payment.facades;

import com.amazonservices.mws.offamazonpayments.model.AuthorizeRequest;
import com.amazonservices.mws.offamazonpayments.model.CaptureRequest;
import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.RefundRequest;


public interface AmazonSandboxSimulationFacade
{

	boolean isSimulation();

	void decorate(CloseOrderReferenceRequest req);

	void decorate(AuthorizeRequest req);

	void decorate(CaptureRequest req);

	void decorate(RefundRequest req);

	void clearSimulationConfig();

	void setSimulationConfig(AmazonSandboxSimulationConfig config);
}