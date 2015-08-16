package de.fliegersoftware.amazon.payment.facades;

public class AmazonSandboxSimulationConfig {

	private SimulateCloseOrderError simulateCloseOrderError;
	private SimulateAuthorizeError simulateAuthorizeError;
	private SimulateCaptureError simulateCaptureError;
	private SimulateRefundError simulateRefundError;

	public AmazonSandboxSimulationConfig() {
		simulateCloseOrderError = SimulateCloseOrderError.None;
		simulateAuthorizeError = SimulateAuthorizeError.None;
		simulateCaptureError = SimulateCaptureError.None;
		simulateRefundError = SimulateRefundError.None;
	}

	public SimulateCloseOrderError getSimulateCloseOrderError() {
		return simulateCloseOrderError;
	}

	public void setSimulateCloseOrderError(SimulateCloseOrderError e) {
		this.simulateCloseOrderError = e != null ? e : SimulateCloseOrderError.None;
	}

	public SimulateAuthorizeError getSimulateAuthorizeError() {
		return simulateAuthorizeError;
	}

	public void setSimulateAuthorizeError(SimulateAuthorizeError e) {
		this.simulateAuthorizeError = e != null ? e : SimulateAuthorizeError.None;
	}

	public SimulateCaptureError getSimulateCaptureError() {
		return simulateCaptureError;
	}

	public void setSimulateCaptureError(SimulateCaptureError e) {
		this.simulateCaptureError = e != null ? e : SimulateCaptureError.None;
	}

	public SimulateRefundError getSimulateRefundError() {
		return simulateRefundError;
	}

	public void setSimulateRefundError(SimulateRefundError e) {
		this.simulateRefundError = e ? != null ? e : SimulateRefundError.None;
	}
}
