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

import org.apache.log4j.Logger;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;
import com.amazonservices.mws.offamazonpayments.model.CloseAuthorizationRequest;

import de.fliegersoftware.amazon.core.jalo.AmazonPaymentPaymentInfo;
import de.fliegersoftware.amazon.core.model.AmazonPaymentPaymentInfoModel;
import de.fliegersoftware.amazon.hmc.credentials.AmazonCredentials;
import de.fliegersoftware.amazon.hmc.hmc.util.HMCAmazonButtonsManager;


/**
 * Provides for 'amazon' tab the action which will be responsible for the close authorizations
 * 
 * @author douglas.canalli
 */
public class AmazonCloseAuthorizationAction extends ItemAction
{
	private static final Logger LOG = Logger.getLogger(AmazonCloseAuthorizationAction.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.hmc.util.action.ItemAction#perform(de.hybris.platform.hmc.util.action.ActionEvent)
	 */
	@Override
	public ActionResult perform(final ActionEvent paramActionEvent) throws JaloBusinessException
	{
		final AmazonCredentials credentials = AmazonCredentials.getInstance();
		final PaymentInfo paymentInfo = (PaymentInfo) paramActionEvent.getData();
		try
		{
			credentials.getService().closeAuthorization(createCloseRequest(credentials, paymentInfo));
			updateInfos(credentials, paymentInfo, (GenericItemChip) paramActionEvent.getSource());
			return new ActionResult(ActionResult.OK, "Authorization closed", false);
		}
		catch (final OffAmazonPaymentsServiceException e)
		{
			LOG.error("error while trying to close the authorization reference", e);
		}
		return new ActionResult(ActionResult.FAILED, "Authorization couldn't be closed", false);
	}

	/**
	 * @param data
	 * @return request
	 */
	private CloseAuthorizationRequest createCloseRequest(final AmazonCredentials credentials, final PaymentInfo data)
	{
		final CloseAuthorizationRequest request = new CloseAuthorizationRequest();
		try
		{
			request.setAmazonAuthorizationId((String) data.getAttribute(AmazonPaymentPaymentInfo.AMAZONLASTAUTHORIZATIONID));
		}
		catch (JaloInvalidParameterException | JaloSecurityException e)
		{
			LOG.error("Error while trying to set the authorization reference", e);
		}
		request.setSellerId(credentials.getAmazonConfig().getMerchantId());
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
			final String orderReferenceId = (String) paymentInfo.getAttribute(AmazonPaymentPaymentInfoModel.AMAZONORDERREFERENCEID);
			credentials.populatePaymentInfo(orderReferenceId, paymentInfo);
			HMCAmazonButtonsManager.getInstance((AmazonPaymentPaymentInfo) paymentInfo, genericItemChip).rebuildButtons();
		}
		catch (final Exception e)
		{
			LOG.error("error while trying to populate payment info", e);
		}
	}


}
