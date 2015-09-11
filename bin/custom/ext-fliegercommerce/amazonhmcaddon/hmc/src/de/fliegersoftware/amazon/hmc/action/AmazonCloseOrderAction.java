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
import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceRequest;

import de.fliegersoftware.amazon.core.jalo.AmazonPaymentInfo;
import de.fliegersoftware.amazon.core.model.AmazonPaymentInfoModel;
import de.fliegersoftware.amazon.hmc.credentials.AmazonCredentials;
import de.fliegersoftware.amazon.hmc.hmc.util.HMCAmazonButtonsManager;


/**
 * Provides for 'amazon' tab the action which will be responsible for the close orders
 * 
 * @author douglas.canalli
 */
public class AmazonCloseOrderAction extends ItemAction
{
	private static final Logger LOG = Logger.getLogger(AmazonCloseOrderAction.class);

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
			credentials.getService().closeOrderReference(createCloseRequest(credentials, paymentInfo));
			updateInfos(credentials, paymentInfo, (GenericItemChip) paramActionEvent.getSource());
			return new ActionResult(ActionResult.OK, "Order closed", false);
		}
		catch (final OffAmazonPaymentsServiceException e)
		{
			LOG.error("error while trying to cancel the order reference", e);
			return new ActionResult(ActionResult.FAILED, e.getLocalizedMessage(), false);
		}
	}

	/**
	 * @param data
	 * @return request
	 */
	private CloseOrderReferenceRequest createCloseRequest(final AmazonCredentials credentials, final PaymentInfo data)
	{
		final CloseOrderReferenceRequest request = new CloseOrderReferenceRequest();
		try
		{
			request.setAmazonOrderReferenceId((String) data.getAttribute(AmazonPaymentInfo.AMAZONORDERREFERENCEID));
		}
		catch (JaloInvalidParameterException | JaloSecurityException e)
		{
			LOG.error("Error while trying to set the order reference", e);
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
			final String orderReferenceId = (String) paymentInfo.getAttribute(AmazonPaymentInfoModel.AMAZONORDERREFERENCEID);
			credentials.populatePaymentInfo(orderReferenceId, paymentInfo);
			HMCAmazonButtonsManager.getInstance((AmazonPaymentInfo) paymentInfo, genericItemChip).rebuildButtons();
		}
		catch (final Exception e)
		{
			LOG.error("error while trying to populate payment info", e);
		}
	}

}
