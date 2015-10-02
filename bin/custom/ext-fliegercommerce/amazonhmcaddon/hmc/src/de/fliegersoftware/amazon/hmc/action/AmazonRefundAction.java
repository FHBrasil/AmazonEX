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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.amazonservices.mws.offamazonpayments.model.Price;
import com.amazonservices.mws.offamazonpayments.model.RefundDetails;
import com.amazonservices.mws.offamazonpayments.model.RefundRequest;
import com.amazonservices.mws.offamazonpayments.model.RefundResponse;

import de.fliegersoftware.amazon.core.jalo.AmazonPaymentPaymentInfo;
import de.fliegersoftware.amazon.core.jalo.AmazonRefund;
import de.fliegersoftware.amazon.core.jalo.AmazoncoreManager;
import de.fliegersoftware.amazon.core.model.AmazonPaymentPaymentInfoModel;
import de.fliegersoftware.amazon.core.model.AmazonRefundModel;
import de.fliegersoftware.amazon.hmc.credentials.AmazonCredentials;
import de.fliegersoftware.amazon.hmc.hmc.util.HMCAmazonButtonsManager;


/**
 * Provides for 'amazon' tab the action which will be responsible for the refund the amount after a capture be completed
 * 
 * @author douglas.canalli
 */
public class AmazonRefundAction extends ItemAction
{
	private static final Logger LOG = Logger.getLogger(AmazonRefundAction.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.hmc.util.action.ItemAction#perform(de.hybris.platform.hmc.util.action.ActionEvent)
	 */
	@Override
	public ActionResult perform(final ActionEvent paramActionEvent) throws JaloBusinessException
	{
		Double refundRequestVal = null;
		try
		{
			final AmazonCredentials credentials = AmazonCredentials.getInstance();

			final PaymentInfo paymentInfo = (PaymentInfo) paramActionEvent.getData();
			refundRequestVal = (Double) paymentInfo.getAttribute("refundRequestAmount");
			paymentInfo.setAttribute("refundRequestAmount", null);

			final RefundRequest request = new RefundRequest();
			request.setAmazonCaptureId((String) paymentInfo.getAttribute("amazonCaptureId"));
			request.setRefundAmount(fillPrice(refundRequestVal, (String) paymentInfo.getAttribute("currencyRefundRequestAmount")));
			request.setRefundReferenceId(String.valueOf(System.currentTimeMillis()));
			request.setSellerId(credentials.getAmazonConfig().getMerchantId());

			final RefundResponse refund = credentials.getService().refund(request);
			final RefundDetails refundDetails = refund.getRefundResult().getRefundDetails();

			final Map<String, Object> attributeValues = new HashMap<>();
			attributeValues.put(AmazonRefundModel.AMAZONREFUNDID, refundDetails.getAmazonRefundId());
			attributeValues.put(AmazonRefundModel.REFUNDSTATUS, refundDetails.getRefundStatus().getState());
			attributeValues.put(AmazonRefundModel.REFUNDAMOUNT, getDoubleValue(refundDetails.getRefundAmount()));
			attributeValues.put(AmazonRefundModel.REFUNDREFERENCEID, refundDetails.getRefundReferenceId());
			attributeValues.put(AmazonRefundModel.REFUNDREASONCODE, refundDetails.getRefundStatus().getReasonCode());
			final AmazonRefund createAmazonRefund = AmazoncoreManager.getInstance().createAmazonRefund(attributeValues);

			final List<AmazonRefund> attribute = new ArrayList<AmazonRefund>(
					(List<AmazonRefund>) paymentInfo.getAttribute(AmazonPaymentPaymentInfoModel.REFUND));
			attribute.add(createAmazonRefund);

			paymentInfo.setAttribute(AmazonPaymentPaymentInfoModel.REFUND, attribute);

			updateInfos(credentials, paymentInfo, (GenericItemChip) paramActionEvent.getSource());

		}
		catch (final Exception e)
		{
			LOG.error("Error while calling the refund", e);
			return new ActionResult(ActionResult.FAILED, e.getLocalizedMessage(), false);
		}
		final String successMessage = Localization.getLocalizedString("msg.refund.success");
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
			final String orderReferenceId = (String) paymentInfo.getAttribute(AmazonPaymentPaymentInfoModel.AMAZONORDERREFERENCEID);
			credentials.populatePaymentInfo(orderReferenceId, paymentInfo);
			HMCAmazonButtonsManager.getInstance((AmazonPaymentPaymentInfo) paymentInfo, genericItemChip).rebuildButtons();
		}
		catch (final Exception e)
		{
			LOG.error("error while trying to populate payment info", e);
		}
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

	/**
	 * convert from refund to double
	 * 
	 * @param refundAmount
	 * @return amount : Double
	 */
	private Double getDoubleValue(final Price refundAmount)
	{
		return Double.valueOf(refundAmount.getAmount());
	}

	/**
	 * create and fill a new price object
	 * 
	 * @param refundRequestVal
	 * @param currency
	 * @return amount : Price
	 * @throws Exception
	 */
	private Price fillPrice(final Double refundRequestVal, final String currency) throws Exception
	{
		final Price price = new Price();
		price.setAmount(String.valueOf(refundRequestVal));
		price.setCurrencyCode(currency);
		return price;
	}

}
