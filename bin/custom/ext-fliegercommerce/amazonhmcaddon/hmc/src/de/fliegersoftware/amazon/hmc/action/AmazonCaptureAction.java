/**
 * 
 */
package de.fliegersoftware.amazon.hmc.action;

import de.hybris.platform.hmc.generic.GenericItemChip;
import de.hybris.platform.hmc.util.action.ActionEvent;
import de.hybris.platform.hmc.util.action.ActionResult;
import de.hybris.platform.hmc.util.action.ItemAction;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.order.payment.PaymentInfo;
import de.hybris.platform.util.localization.Localization;

import org.apache.log4j.Logger;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceClient;
import com.amazonservices.mws.offamazonpayments.model.AuthorizationDetails;
import com.amazonservices.mws.offamazonpayments.model.CaptureRequest;
import com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsResponse;

import de.fliegersoftware.amazon.core.jalo.AmazonPaymentInfo;
import de.fliegersoftware.amazon.core.model.AmazonPaymentInfoModel;
import de.fliegersoftware.amazon.hmc.credentials.AmazonCredentials;
import de.fliegersoftware.amazon.hmc.hmc.util.HMCAmazonButtonsManager;


/**
 * Provides for 'amazon' tab the action which will be responsible for the capture the amount after an order be
 * authorized
 * 
 * @author douglas.canalli
 */
public class AmazonCaptureAction extends ItemAction
{
	private static final Logger LOG = Logger.getLogger(AmazonCaptureAction.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.hmc.util.action.ItemAction#perform(de.hybris.platform.hmc.util.action.ActionEvent)
	 */
	@Override
	public ActionResult perform(final ActionEvent paramActionEvent) throws JaloBusinessException
	{
		final AmazonCredentials credentials = AmazonCredentials.getInstance();
		final String successMessage = Localization.getLocalizedString("msg.capture.success");

		final PaymentInfo paymentInfo = (PaymentInfo) paramActionEvent.getData();
		final OffAmazonPaymentsServiceClient service = credentials.getService();
		final CaptureRequest request = new CaptureRequest();

		try
		{
			final String id = (String) paymentInfo.getAttribute(AmazonPaymentInfoModel.AMAZONLASTAUTHORIZATIONID);
			final GetAuthorizationDetailsResponse authorizationDetails = service
					.getAuthorizationDetails(getAuthorizationDetailsRequest(credentials, id));
			populateRequest(credentials, authorizationDetails.getGetAuthorizationDetailsResult().getAuthorizationDetails(), request);
			service.capture(request);

			updateInfos(credentials, paymentInfo, (GenericItemChip) paramActionEvent.getSource());
		}
		catch (final Exception e)
		{
			LOG.error("Error while trying to authorize the order.", e);
			return new ActionResult(ActionResult.FAILED, e.getLocalizedMessage(), false);
		}

		return new ActionResult(ActionResult.OK, successMessage, false);
	}

	/**
	 * update all info which will appear in the hmc display
	 * 
	 * @param paymentInfo
	 * @param genericItemChip
	 */
	private void updateInfos(final AmazonCredentials credentials, final PaymentInfo paymentInfo,
			final GenericItemChip genericItemChip)
	{
		try
		{
			final String orderReferenceId = (String) paymentInfo.getAttribute(AmazonPaymentInfoModel.AMAZONORDERREFERENCEID);
			credentials.populatePaymentInfo(orderReferenceId, paymentInfo);
			HMCAmazonButtonsManager.getInstance((AmazonPaymentInfo) paymentInfo, genericItemChip).rebuildButtons();
		}
		catch (final Exception e)
		{
			LOG.error("error while trying to populate payment info", e);
		}
	}

	/**
	 * @return request
	 */
	private GetAuthorizationDetailsRequest getAuthorizationDetailsRequest(final AmazonCredentials credentials,
			final String amazonAuthorizationId)
	{
		final GetAuthorizationDetailsRequest request = new GetAuthorizationDetailsRequest();
		request.setSellerId(credentials.getAmazonConfig().getMerchantId());
		request.setAmazonAuthorizationId(amazonAuthorizationId);
		return request;
	}

	/**
	 * @param authorizationDetails
	 * @param request
	 */
	private void populateRequest(final AmazonCredentials credentials, final AuthorizationDetails authorizationDetails,
			final CaptureRequest request)
	{
		request.setSellerId(credentials.getAmazonConfig().getMerchantId());
		request.setAmazonAuthorizationId(authorizationDetails.getAmazonAuthorizationId());
		request.setCaptureAmount(authorizationDetails.getAuthorizationAmount());
		request.setCaptureReferenceId(String.valueOf(System.currentTimeMillis()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.hmc.util.action.ItemAction#needConfirmation()
	 */
	@Override
	public boolean needConfirmation()
	{
		return true;
	}

}
