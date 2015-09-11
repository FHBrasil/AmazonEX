/**
 * 
 */
package com.adyen.services.payment.service;

/**
 * @author ezequiel
 *
 */

import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.PaymentService;
import de.hybris.platform.payment.commands.request.AuthorizationRequest;
import de.hybris.platform.payment.commands.request.CaptureRequest;
import de.hybris.platform.payment.commands.request.CreateSubscriptionRequest;
import de.hybris.platform.payment.commands.request.DeleteSubscriptionRequest;
import de.hybris.platform.payment.commands.request.EnrollmentCheckRequest;
import de.hybris.platform.payment.commands.request.FollowOnRefundRequest;
import de.hybris.platform.payment.commands.request.PartialCaptureRequest;
import de.hybris.platform.payment.commands.request.StandaloneRefundRequest;
import de.hybris.platform.payment.commands.request.SubscriptionAuthorizationRequest;
import de.hybris.platform.payment.commands.request.SubscriptionDataRequest;
import de.hybris.platform.payment.commands.request.UpdateSubscriptionRequest;
import de.hybris.platform.payment.commands.request.VoidRequest;
import de.hybris.platform.payment.commands.result.AuthorizationResult;
import de.hybris.platform.payment.commands.result.CaptureResult;
import de.hybris.platform.payment.commands.result.EnrollmentCheckResult;
import de.hybris.platform.payment.commands.result.RefundResult;
import de.hybris.platform.payment.commands.result.SubscriptionDataResult;
import de.hybris.platform.payment.commands.result.SubscriptionResult;
import de.hybris.platform.payment.commands.result.VoidResult;
import de.hybris.platform.payment.methods.PaymentMethod;

public interface DebitPaymentService extends PaymentService{
//	public abstract AuthorizationResult authorize(
//			AuthorizationRequest paramAuthorizationRequest)
//			throws AdapterException;
//
//	public abstract AuthorizationResult authorize(
//			SubscriptionAuthorizationRequest paramSubscriptionAuthorizationRequest)
//			throws AdapterException;
//
//	public abstract CaptureResult capture(CaptureRequest paramCaptureRequest)
//			throws AdapterException;
//
//	public abstract CaptureResult partialCapture(
//			PartialCaptureRequest paramPartialCaptureRequest)
//			throws AdapterException;
//
//	public abstract EnrollmentCheckResult enrollmentCheck(
//			EnrollmentCheckRequest paramEnrollmentCheckRequest)
//			throws AdapterException;
//
//	public abstract VoidResult voidCreditOrCapture(VoidRequest paramVoidRequest)
//			throws AdapterException;
//
//	public abstract RefundResult refundStandalone(
//			StandaloneRefundRequest paramStandaloneRefundRequest)
//			throws AdapterException;
//
//	public abstract RefundResult refundFollowOn(
//			FollowOnRefundRequest paramFollowOnRefundRequest)
//			throws AdapterException;
//
	public abstract SubscriptionResult createSubscription(
			CreateSubscriptionRequest paramCreateSubscriptionRequest)
			throws AdapterException;
//
//	public abstract SubscriptionResult updateSubscription(
//			UpdateSubscriptionRequest paramUpdateSubscriptionRequest)
//			throws AdapterException;
//
//	public abstract SubscriptionDataResult getSubscriptionData(
//			SubscriptionDataRequest paramSubscriptionDataRequest)
//			throws AdapterException;
//
//	public abstract SubscriptionResult deleteSubscription(
//			DeleteSubscriptionRequest paramDeleteSubscriptionRequest)
//			throws AdapterException;
}