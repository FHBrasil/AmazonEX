/**
 * 
 */
package de.fliegersoftware.amazon.hmc.action;

import de.hybris.platform.hmc.generic.AbstractActionChip;
import de.hybris.platform.hmc.util.action.AbstractActionDialogChip;
import de.hybris.platform.hmc.util.action.ActionEvent;
import de.hybris.platform.hmc.util.action.ActionResult;
import de.hybris.platform.hmc.util.action.ItemAction;
import de.hybris.platform.hmc.webchips.Chip;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.util.localization.Localization;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceClient;
import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceConfig;
import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsResponse;

import de.fliegersoftware.amazon.hmc.jalo.config.AmazonConfig;


/**
 * @author douglas.canalli
 * 
 */
public class AmazonConnectionTestAction extends ItemAction
{

	private GetOrderReferenceDetailsResponse orderReferenceDetails;

	private String errorMessage;

	private static final Logger LOG = Logger.getLogger(AmazonConnectionTestAction.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.hmc.util.action.HMCAction#createDialogChip(de.hybris.platform.hmc.webchips.Chip,
	 * de.hybris.platform.hmc.generic.AbstractActionChip)
	 */
	@Override
	public AbstractActionDialogChip createDialogChip(final Chip chip, final AbstractActionChip abstractActionChip)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.hmc.util.action.HMCAction#getConfirmationMessage()
	 */
	@Override
	public String getConfirmationMessage()
	{
		return orderReferenceDetails.getGetOrderReferenceDetailsResult().getOrderReferenceDetails().getOrderReferenceStatus()
				.getState();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.hmc.util.action.HMCAction#hasDialogChip(de.hybris.platform.hmc.generic.AbstractActionChip)
	 */
	@Override
	public boolean hasDialogChip(final AbstractActionChip arg0)
	{
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.hmc.util.action.HMCAction#needConfirmation()
	 */
	@Override
	public boolean needConfirmation()
	{
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.hmc.util.action.HMCAction#perform(de.hybris.platform.hmc.util.action.ActionEvent)
	 */
	@Override
	public ActionResult perform(final ActionEvent actionEvent) throws JaloBusinessException
	{
		if (StringUtils.isNotBlank(errorMessage))
		{
			errorMessage = null;
		}

		final AmazonConfig amazonConfig = (AmazonConfig) actionEvent.getData();

		final GetOrderReferenceDetailsRequest request = createGetOrderReferenceDetailsRequest(amazonConfig);
		final OffAmazonPaymentsServiceClient service = createOffAmazonPaymentService(amazonConfig);
		callOrderReferenceDetails(service, request);

		if (StringUtils.isNotBlank(errorMessage))
		{
			return new ActionResult(ActionResult.FAILED, errorMessage, false);
		}

		final String successMessage = Localization.getLocalizedString("msg.connection.test.success");

		return new ActionResult(ActionResult.OK, successMessage, false);
	}

	/**
	 * @param service
	 * @param request
	 */
	private void callOrderReferenceDetails(final OffAmazonPaymentsServiceClient service,
			final GetOrderReferenceDetailsRequest request)
	{
		try
		{
			orderReferenceDetails = service.getOrderReferenceDetails(request);
		}
		catch (final OffAmazonPaymentsServiceException e)
		{
			errorMessage = e.getMessage();
			LOG.error("Error while trying to get the order reference details response.", e);
		}
	}

	/**
	 * @param amazonConfig
	 */
	private OffAmazonPaymentsServiceClient createOffAmazonPaymentService(final AmazonConfig amazonConfig)
	{
		final Properties properties = new Properties();
		properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.ACCESS_KEY_ID, amazonConfig.getMwsAccessKey());
		properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.SECRET_ACCESS_KEY, amazonConfig.getMwsSecretKey());
		properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.APPLICATION_VERSION, "");
		properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.SELLER_ID, amazonConfig.getMerchantId());
		properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.REGION, amazonConfig.getAmazonConfigCountry().getCode());
		properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.CURRENCY, "EUR");
		properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.ENVIRONMENT, "");
		properties.put("clientId", amazonConfig.getClientId());

		if (amazonConfig.isSandboxMode() != null && amazonConfig.isSandboxMode().booleanValue())
		{
			properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.APPLICATION_NAME, "KPFamily Sandbox");
			properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.ENVIRONMENT, "SANDBOX");
			properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.PLACE_ORDER_URL, "http://localhost:9001/");
		}
		else
		{
			properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.ENVIRONMENT, "LIVE");
			properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.PLACE_ORDER_URL, "http://www.babyartikel.de/");
		}
		properties.put(de.fliegersoftware.amazon.payment.util.AmazonConfig.CERT_CN, "sns.amazonaws.com");

		final OffAmazonPaymentsServiceConfig config = new OffAmazonPaymentsServiceConfig(properties);
		return new OffAmazonPaymentsServiceClient(config);
	}

	/**
	 * @param amazonConfig
	 */
	private GetOrderReferenceDetailsRequest createGetOrderReferenceDetailsRequest(final AmazonConfig amazonConfig)
	{
		final GetOrderReferenceDetailsRequest request = new GetOrderReferenceDetailsRequest();
		request.setSellerId(amazonConfig.getMerchantId());
		request.setAmazonOrderReferenceId("S02-1043731-5256796");
		return request;
	}
}
