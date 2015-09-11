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

import java.util.Currency;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceClient;
import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceConfig;
import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsResponse;

import de.fliegersoftware.amazon.core.constants.GeneratedAmazoncoreConstants.Enumerations.AmazonConfigCountryEnum;
import de.fliegersoftware.amazon.core.jalo.config.AmazonConfig;


/**
 * Provides for Amazon Control Panel view the action which will be responsible for the connection test
 * 
 * @author douglas.canalli
 */
public class AmazonConnectionTestAction extends ItemAction
{
	private GetOrderReferenceDetailsResponse orderReferenceDetails;

	private static final String ACCESS_KEY_ID = "accessKeyId";
	private static final String CURRENCY = "currency";
	private static final String SECRET_ACCESS_KEY = "secretAccessKey";
	private static final String APPLICATION_NAME = "applicationName";
	private static final String APPLICATION_VERSION = "applicationVersion";
	private static final String SELLER_ID = "sellerId";
	private static final String ENVIRONMENT = "environment";
	private static final String REGION = "region";
	private static final String CERT_CN = "certCN";
	private static final String CLIENT_ID = "clientId";
	private static final String AMAZONWS = "sns.amazonaws.com";
	private static final String SANDBOX = "SANDBOX";
	private static final String LIVE = "LIVE";
	private static final String TEST_ORDER = "S00-0000000-0000000";

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
			errorMessage = e.getLocalizedMessage();
			LOG.error("Error while trying to get the order reference details response.", e);
		}
	}

	/**
	 * Get the country code from amazon configuration
	 * 
	 * @param amazonConfig
	 * @return country code
	 */
	@SuppressWarnings("deprecation")
	private String getCountryCode(final AmazonConfig amazonConfig)
	{
		if (de.fliegersoftware.amazon.core.constants.GeneratedAmazoncoreConstants.Enumerations.AmazonConfigCountryEnum.OTHER
				.equals(amazonConfig.getAmazonConfigCountry()))
		{
			return amazonConfig.getOtherCountry();
		}
		return amazonConfig.getAmazonConfigCountry().getCode();
	}

	/**
	 * @param amazonConfig
	 * @return currency code
	 */
	private String getCurrencyByRegion(final AmazonConfig amazonConfig)
	{
		if (AmazonConfigCountryEnum.OTHER.equals(amazonConfig.getAmazonConfigCountry()))
		{
			return amazonConfig.getOtherCountryCurrency();
		}

		String countryCode = getCountryCode(amazonConfig);
		try
		{
			if ("uk".equalsIgnoreCase(countryCode))
			{
				countryCode = "gb";
			}
			final Locale locale = new Locale("", countryCode);
			final Currency currency = Currency.getInstance(locale);
			return currency.getCurrencyCode();
		}
		catch (final Exception e)
		{
			LOG.error("Currency could not found for region", e);
		}
		return null;
	}

	/**
	 * @param amazonConfig
	 */
	@SuppressWarnings("deprecation")
	private OffAmazonPaymentsServiceClient createOffAmazonPaymentService(final AmazonConfig amazonConfig)
	{
		final Properties properties = new Properties();
		properties.put(AmazonConnectionTestAction.ACCESS_KEY_ID, amazonConfig.getMwsAccessKey());
		properties.put(AmazonConnectionTestAction.SECRET_ACCESS_KEY, amazonConfig.getMwsSecretKey());
		properties.put(AmazonConnectionTestAction.SELLER_ID, amazonConfig.getMerchantId());
		properties.put(AmazonConnectionTestAction.REGION, getCountryCode(amazonConfig));
		properties.put(AmazonConnectionTestAction.CURRENCY, getCurrencyByRegion(amazonConfig));
		properties.put(AmazonConnectionTestAction.APPLICATION_NAME, amazonConfig.getApplicationName());
		properties.put(AmazonConnectionTestAction.APPLICATION_VERSION, amazonConfig.getApplicationVersion());
		properties.put(AmazonConnectionTestAction.CLIENT_ID, amazonConfig.getClientId());
		properties.put(AmazonConnectionTestAction.ENVIRONMENT, getEnvironment(amazonConfig.isSandboxMode()));
		properties.put(AmazonConnectionTestAction.CERT_CN, AMAZONWS);

		final OffAmazonPaymentsServiceConfig config = new OffAmazonPaymentsServiceConfig(properties);
		return new OffAmazonPaymentsServiceClient(config);
	}

	private String getEnvironment(final Boolean sandbox)
	{
		return sandbox != null && sandbox.booleanValue() ? SANDBOX : LIVE;
	}

	/**
	 * @param amazonConfig
	 */
	private GetOrderReferenceDetailsRequest createGetOrderReferenceDetailsRequest(final AmazonConfig amazonConfig)
	{
		final GetOrderReferenceDetailsRequest request = new GetOrderReferenceDetailsRequest();
		request.setSellerId(amazonConfig.getMerchantId());
		//XXX: check why this amazon order reference id doesn't work
		request.setAmazonOrderReferenceId(amazonConfig.getTestOrderReferenceId());
		return request;
	}
}
