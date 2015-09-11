/**
 * 
 */
package de.fliegersoftware.amazon.hmc.action;

import de.hybris.platform.hmc.generic.GenericItemChip;
import de.hybris.platform.hmc.util.action.ActionEvent;
import de.hybris.platform.hmc.util.action.ActionResult;
import de.hybris.platform.hmc.util.action.ItemAction;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.order.payment.PaymentInfo;
import de.hybris.platform.jalo.security.JaloSecurityException;
import de.hybris.platform.util.localization.Localization;

import org.apache.log4j.Logger;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceClient;
import com.amazonservices.mws.offamazonpayments.model.AuthorizationDetails;
import com.amazonservices.mws.offamazonpayments.model.AuthorizeRequest;
import com.amazonservices.mws.offamazonpayments.model.AuthorizeResponse;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsResponse;
import com.amazonservices.mws.offamazonpayments.model.OrderReferenceDetails;
import com.amazonservices.mws.offamazonpayments.model.OrderTotal;
import com.amazonservices.mws.offamazonpayments.model.Price;

import de.fliegersoftware.amazon.core.jalo.AmazonPaymentInfo;
import de.fliegersoftware.amazon.core.model.AmazonPaymentInfoModel;
import de.fliegersoftware.amazon.hmc.credentials.AmazonCredentials;
import de.fliegersoftware.amazon.hmc.hmc.util.HMCAmazonButtonsManager;


/**
 * Provides for 'amazon' tab the action which will be responsible for the authorize open orders
 * 
 * @author douglas.canalli
 */
public class AmazonAuthorizeAction extends ItemAction
{
	private static final Logger LOG = Logger.getLogger(AmazonAuthorizeAction.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.hmc.util.action.ItemAction#perform(de.hybris.platform.hmc.util.action.ActionEvent)
	 */
	@Override
	public ActionResult perform(final ActionEvent paramActionEvent) throws JaloBusinessException
	{
		final AmazonCredentials credentials = AmazonCredentials.getInstance();
		final String successMessage = Localization.getLocalizedString("msg.authorized.success");

		final PaymentInfo paymentInfo = (PaymentInfo) paramActionEvent.getData();
		final OffAmazonPaymentsServiceClient service = credentials.getService();
		final AuthorizeRequest request = new AuthorizeRequest();

		try
		{
			final String id = (String) paymentInfo.getAttribute(AmazonPaymentInfoModel.AMAZONORDERREFERENCEID);
			final GetOrderReferenceDetailsResponse orderReferenceDetails = service
					.getOrderReferenceDetails(getOrderReferenceDetailsRequest(credentials, id));
			populateRequest(credentials, orderReferenceDetails.getGetOrderReferenceDetailsResult().getOrderReferenceDetails(),
					request);
			final AuthorizeResponse authorize = service.authorize(request);
			populatePaymentInfo(paymentInfo, authorize.getAuthorizeResult().getAuthorizationDetails());
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
	 * @param credentials
	 * @return request
	 */
	private GetOrderReferenceDetailsRequest getOrderReferenceDetailsRequest(final AmazonCredentials credentials,
			final String amazonOrderReferenceId)
	{
		final GetOrderReferenceDetailsRequest request = new GetOrderReferenceDetailsRequest();
		request.setSellerId(credentials.getAmazonConfig().getMerchantId());
		request.setAmazonOrderReferenceId(amazonOrderReferenceId);
		return request;
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
	 * @param paymentInfo
	 * @param authorizationDetails
	 * @throws Exception
	 */
	private void populatePaymentInfo(final PaymentInfo paymentInfo, final AuthorizationDetails authorizationDetails)
			throws JaloInvalidParameterException, JaloSecurityException, Exception
	{
		paymentInfo.setAttribute(AmazonPaymentInfoModel.AMAZONLASTAUTHORIZATIONID, authorizationDetails.getAmazonAuthorizationId());
		paymentInfo.setAttribute(AmazonPaymentInfoModel.AMAZONAUTHORIZATIONSTATUS, authorizationDetails.getAuthorizationStatus()
				.getState());
		paymentInfo.setAttribute(AmazonPaymentInfoModel.AMAZONAUTHORIZATIONREASONCODE, authorizationDetails
				.getAuthorizationStatus().getReasonCode());
	}

	/**
	 * @param orderReferenceDetails
	 * @param authorizeRequest
	 */
	private void populateRequest(final AmazonCredentials credentials, final OrderReferenceDetails orderReferenceDetails,
			final AuthorizeRequest authorizeRequest)
	{
		authorizeRequest.setSellerId(credentials.getAmazonConfig().getMerchantId());
		authorizeRequest.setAuthorizationAmount(populateAmount(orderReferenceDetails.getOrderTotal()));
		authorizeRequest.setAmazonOrderReferenceId(orderReferenceDetails.getAmazonOrderReferenceId());
		authorizeRequest.setAuthorizationReferenceId(String.valueOf(System.currentTimeMillis()));
	}

	/**
	 * @param orderTotal
	 * @return Price
	 */
	private Price populateAmount(final OrderTotal orderTotal)
	{
		final Price price = new Price();
		price.setAmount(orderTotal.getAmount());
		price.setCurrencyCode(orderTotal.getCurrencyCode());
		return price;
	}

}
