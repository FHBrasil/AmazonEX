/**
 * 
 */
package de.fliegersoftware.amazon.hmc.credentials;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.flexiblesearch.FlexibleSearch;
import de.hybris.platform.jalo.order.payment.PaymentInfo;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceClient;
import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceConfig;
import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;
import com.amazonservices.mws.offamazonpayments.model.AuthorizationDetails;
import com.amazonservices.mws.offamazonpayments.model.CaptureDetails;
import com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsResponse;
import com.amazonservices.mws.offamazonpayments.model.GetCaptureDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsResponse;
import com.amazonservices.mws.offamazonpayments.model.GetRefundDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.OrderReferenceDetails;
import com.amazonservices.mws.offamazonpayments.model.OrderTotal;
import com.amazonservices.mws.offamazonpayments.model.Price;
import com.amazonservices.mws.offamazonpayments.model.RefundDetails;

import de.fliegersoftware.amazon.core.jalo.AmazonPaymentPaymentInfo;
import de.fliegersoftware.amazon.core.jalo.AmazonRefund;
import de.fliegersoftware.amazon.core.jalo.AmazoncoreManager;
import de.fliegersoftware.amazon.core.jalo.config.AmazonConfig;



/**
 * Class which manage the amazon client service use this singleton to get the service in the actions, managers, etc...
 * 
 * @author douglas.canalli
 */
public class AmazonCredentials
{

	private static final Logger LOG = Logger.getLogger(AmazonCredentials.class);

	private AmazonConfig amazonConfig;
	private Properties properties;
	private OffAmazonPaymentsServiceClient service;

	private static final String AMAZONCREDENTIALSSESSION = "AmazonCredentials";
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

	/**
	 * singleton constructor
	 */
	private AmazonCredentials()
	{
		init();
	}

	/**
	 * initialize objects
	 */
	private void init()
	{
		createAmazonConfig();
		buildProperties();
		buildService();
	}

	/**
	 * create a instance for amazon service client
	 */
	private void buildService()
	{
		final OffAmazonPaymentsServiceConfig config = new OffAmazonPaymentsServiceConfig(properties);
		service = new OffAmazonPaymentsServiceClient(config);
	}

	/**
	 * fill properties which will be used for amazon client service instance
	 */
	@SuppressWarnings("deprecation")
	private void buildProperties()
	{
		if (properties == null)
		{
			properties = new Properties();
			properties.put(AmazonCredentials.ACCESS_KEY_ID, amazonConfig.getMwsAccessKey());
			properties.put(AmazonCredentials.SECRET_ACCESS_KEY, amazonConfig.getMwsSecretKey());
			properties.put(AmazonCredentials.SELLER_ID, amazonConfig.getMerchantId());
			properties.put(AmazonCredentials.REGION, getCountryCode(amazonConfig));
			properties.put(AmazonCredentials.CURRENCY, getCurrencyByRegion(amazonConfig));
			properties.put(AmazonCredentials.APPLICATION_NAME, StringUtils.defaultString(amazonConfig.getApplicationName()));
			properties.put(AmazonCredentials.APPLICATION_VERSION, StringUtils.defaultString(amazonConfig.getApplicationVersion()));
			properties.put(AmazonCredentials.CLIENT_ID, amazonConfig.getClientId());
			properties.put(AmazonCredentials.ENVIRONMENT, getEnvironment(amazonConfig.isSandboxMode()));
			properties.put(AmazonCredentials.CERT_CN, AMAZONWS);
		}
	}

	/**
	 * Get the country code from amazon configuration
	 * 
	 * @param amazonConfig
	 * @return country code
	 */
	private String getCountryCode(final AmazonConfig amazonConfig)
	{
		if ("Other".equalsIgnoreCase(amazonConfig.getAmazonConfigCountry().getCode()))
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
		if ("Other".equals(amazonConfig.getAmazonConfigCountry().getCode()))
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

	private String getEnvironment(final Boolean sandbox)
	{
		return sandbox != null && sandbox.booleanValue() ? SANDBOX : LIVE;
	}

	/**
	 * update the payment info registry based in amazon information
	 * 
	 * @param orderReferenceId
	 * @param paymentInfo
	 * @throws OffAmazonPaymentsServiceException
	 */
	@SuppressWarnings("deprecation")
	public void populatePaymentInfo(final String orderReferenceId, final PaymentInfo paymentInfo)
			throws OffAmazonPaymentsServiceException
	{
		final AmazonConfig amazonConfig = getAmazonConfig();
		final OrderReferenceDetails orderDetails = callAndFillOrderDetails(paymentInfo, service, amazonConfig, orderReferenceId);
		final AuthorizationDetails authDetails = callAndFillAuthorizationDetails(paymentInfo, service, amazonConfig, orderDetails);
		final CaptureDetails captureDetails = callAndFillCaptureDetails(paymentInfo, service, amazonConfig, authDetails);
		callAndFillRefundDetails(paymentInfo, service, amazonConfig, captureDetails);
	}

	/**
	 * fills order information via amazon client service
	 * 
	 * @param paymentInfo
	 * @param service
	 * @param next
	 * @param orderReferenceId
	 * @return OrderReferenceDetails
	 */
	private OrderReferenceDetails callAndFillOrderDetails(final PaymentInfo paymentInfo,
			final OffAmazonPaymentsServiceClient service, final AmazonConfig next, final String orderReferenceId)
	{
		populate: if (StringUtils.isNotBlank(orderReferenceId))
		{
			final GetOrderReferenceDetailsRequest request = new GetOrderReferenceDetailsRequest();
			request.setAmazonOrderReferenceId(orderReferenceId);
			request.setSellerId(next.getMerchantId());
			try
			{

				final GetOrderReferenceDetailsResponse orderRefDetails = service.getOrderReferenceDetails(request);
				final OrderReferenceDetails details = orderRefDetails.getGetOrderReferenceDetailsResult().getOrderReferenceDetails();
				paymentInfo.setAttribute(AmazonPaymentPaymentInfo.AMAZONORDERREFERENCEID, details.getAmazonOrderReferenceId());
				paymentInfo.setAttribute(AmazonPaymentPaymentInfo.AMAZONORDERSTATUS, details.getOrderReferenceStatus().getState());
				paymentInfo.setAttribute(AmazonPaymentPaymentInfo.AMAZONORDERREASONCODE, details.getOrderReferenceStatus().getReasonCode());
				paymentInfo.setAttribute(AmazonPaymentPaymentInfo.AMAZONORDERAMOUNT, getDoubleValue(details.getOrderTotal()));
				paymentInfo.setAttribute(AmazonPaymentPaymentInfo.CURRENCYREFUNDREQUESTAMOUNT, details.getOrderTotal().getCurrencyCode());
				return details;
			}
			catch (final OffAmazonPaymentsServiceException e)
			{
				break populate;
			}
			catch (final Exception e)
			{
				LOG.error("Error while getting the order details", e);
			}
		}
		else
		{
			try
			{
				paymentInfo.setAttribute(AmazonPaymentPaymentInfo.AMAZONORDERSTATUS, "");
				paymentInfo.setAttribute(AmazonPaymentPaymentInfo.AMAZONORDERREASONCODE, "");
				paymentInfo.setAttribute(AmazonPaymentPaymentInfo.AMAZONORDERAMOUNT, "");
				paymentInfo.setAttribute(AmazonPaymentPaymentInfo.CURRENCYREFUNDREQUESTAMOUNT, 0);
			}
			catch (final Exception e)
			{
				LOG.error("Error while reset authorization", e);
			}
		}
		return null;
	}

	/**
	 * fills authorization information via amazon client service
	 * 
	 * @param paymentInfo
	 * @param service
	 * @param next
	 * @param orderDetails
	 * @return AuthorizationDetails
	 */
	private AuthorizationDetails callAndFillAuthorizationDetails(final PaymentInfo paymentInfo,
			final OffAmazonPaymentsServiceClient service, final AmazonConfig next, final OrderReferenceDetails orderDetails)
	{

		populate: if (orderDetails != null && orderDetails.getIdList() != null
				&& CollectionUtils.isNotEmpty(orderDetails.getIdList().getMember()))
		{
			final GetAuthorizationDetailsRequest request = new GetAuthorizationDetailsRequest();

			request.setAmazonAuthorizationId(orderDetails.getIdList().getMember().get(0));
			request.setSellerId(next.getMerchantId());

			GetAuthorizationDetailsResponse authorizationDetails = null;
			try
			{
				authorizationDetails = service.getAuthorizationDetails(request);

				final AuthorizationDetails authDetails = authorizationDetails.getGetAuthorizationDetailsResult()
						.getAuthorizationDetails();

				paymentInfo.setAttribute(AmazonPaymentPaymentInfo.AMAZONLASTAUTHORIZATIONID, authDetails.getAmazonAuthorizationId());
				paymentInfo
						.setAttribute(AmazonPaymentPaymentInfo.AMAZONAUTHORIZATIONSTATUS, authDetails.getAuthorizationStatus().getState());
				paymentInfo.setAttribute(AmazonPaymentPaymentInfo.AMAZONAUTHORIZATIONREASONCODE, authDetails.getAuthorizationStatus()
						.getReasonCode());

				return authDetails;
			}
			catch (final OffAmazonPaymentsServiceException e)
			{
				break populate;
			}
			catch (final Exception e)
			{
				LOG.error("Error while fill authorization", e);
			}

		}
		else
		{
			try
			{
				paymentInfo.setAttribute(AmazonPaymentPaymentInfo.AMAZONLASTAUTHORIZATIONID, "");
				paymentInfo.setAttribute(AmazonPaymentPaymentInfo.AMAZONAUTHORIZATIONSTATUS, "");
				paymentInfo.setAttribute(AmazonPaymentPaymentInfo.AMAZONAUTHORIZATIONREASONCODE, "");
			}
			catch (final Exception e)
			{
				LOG.error("Error while reset authorization", e);
			}
		}
		return null;
	}


	/**
	 * fills capture information via amazon client service
	 * 
	 * @param paymentInfo
	 * @param service
	 * @param next
	 * @param authDetails
	 * @return CaptureDetails
	 */
	private CaptureDetails callAndFillCaptureDetails(final PaymentInfo paymentInfo, final OffAmazonPaymentsServiceClient service,
			final AmazonConfig next, final AuthorizationDetails authDetails)
	{
		populate: if (authDetails != null && authDetails.getIdList() != null
				&& CollectionUtils.isNotEmpty(authDetails.getIdList().getMember()))
		{
			final GetCaptureDetailsRequest request = new GetCaptureDetailsRequest();

			request.setAmazonCaptureId(authDetails.getIdList().getMember().get(0));
			request.setSellerId(next.getMerchantId());

			try
			{
				final CaptureDetails captureDetails = service.getCaptureDetails(request).getGetCaptureDetailsResult()
						.getCaptureDetails();
				paymentInfo.setAttribute(AmazonPaymentPaymentInfo.AMAZONCAPTUREID, captureDetails.getAmazonCaptureId());
				paymentInfo.setAttribute(AmazonPaymentPaymentInfo.AMAZONCAPTURESTATUS, captureDetails.getCaptureStatus().getState());
				paymentInfo
						.setAttribute(AmazonPaymentPaymentInfo.AMAZONCAPTUREREASONCODE, captureDetails.getCaptureStatus().getReasonCode());
				paymentInfo.setAttribute(AmazonPaymentPaymentInfo.AMAZONCAPTUREREFUNDEDAMOUNT,
						getDoubleValue(captureDetails.getRefundedAmount()));
				return captureDetails;
			}
			catch (final OffAmazonPaymentsServiceException e)
			{
				break populate;
			}
			catch (final Exception e)
			{
				LOG.error("Error during get capture details", e);
			}
		}
		else
		{
			try
			{
				paymentInfo.setAttribute(AmazonPaymentPaymentInfo.AMAZONCAPTUREID, "");
				paymentInfo.setAttribute(AmazonPaymentPaymentInfo.AMAZONCAPTURESTATUS, "");
				paymentInfo.setAttribute(AmazonPaymentPaymentInfo.AMAZONCAPTUREREASONCODE, "");
				paymentInfo.setAttribute(AmazonPaymentPaymentInfo.AMAZONCAPTUREREFUNDEDAMOUNT, 0d);
			}
			catch (final Exception e)
			{
				LOG.error("Error during capture reset", e);
			}
		}
		return null;
	}

	/**
	 * fills refund information via amazon client service
	 * 
	 * @param paymentInfo
	 * @param service
	 * @param next
	 * @param captureDetails
	 */
	private void callAndFillRefundDetails(final PaymentInfo paymentInfo, final OffAmazonPaymentsServiceClient service,
			final AmazonConfig next, final CaptureDetails captureDetails)
	{
		if (captureDetails != null && captureDetails.getIdList() != null
				&& CollectionUtils.isNotEmpty(captureDetails.getIdList().getMember()))
		{
			final List<AmazonRefund> amazonRefundList = new ArrayList<>();

			//empty refunds
			try
			{
				final List<AmazonRefund> refunds = (List<AmazonRefund>) paymentInfo.getAttribute(AmazonPaymentPaymentInfo.REFUND);
				paymentInfo.setAttribute(AmazonPaymentPaymentInfo.REFUND, null);
				for (final AmazonRefund amazonRefund : refunds)
				{
					amazonRefund.remove();
				}
			}
			catch (final Exception e)
			{
				LOG.error("Error empty the refunds", e);
			}

			populate: for (final String id : captureDetails.getIdList().getMember())
			{
				final GetRefundDetailsRequest request = new GetRefundDetailsRequest();
				request.setAmazonRefundId(id);
				request.setSellerId(next.getMerchantId());
				try
				{
					final RefundDetails refundDetails = service.getRefundDetails(request).getGetRefundDetailsResult()
							.getRefundDetails();

					final Map<String, Object> attributeValues = new HashMap<>();
					attributeValues.put(AmazonRefund.AMAZONREFUNDID, refundDetails.getAmazonRefundId());
					attributeValues.put(AmazonRefund.REFUNDSTATUS, refundDetails.getRefundStatus().getState());
					attributeValues.put(AmazonRefund.REFUNDAMOUNT, getDoubleValue(refundDetails.getRefundAmount()));
					attributeValues.put(AmazonRefund.REFUNDREFERENCEID, refundDetails.getRefundReferenceId());
					amazonRefundList.add(AmazoncoreManager.getInstance().createAmazonRefund(attributeValues));

				}
				catch (final OffAmazonPaymentsServiceException e)
				{
					continue populate;
				}
				catch (final Exception e)
				{
					LOG.error("Error while getting the refund information details");
				}
			}
			try
			{
				paymentInfo.setAttribute(AmazonPaymentPaymentInfo.REFUND, amazonRefundList);
			}
			catch (final Exception e)
			{
				LOG.error("Error while setting refund objects in the payment info", e);
			}
		}
		else
		{
			try
			{
				paymentInfo.setAttribute(AmazonPaymentPaymentInfo.REFUND, null);
			}
			catch (final Exception e)
			{
				LOG.error("Error while removing refund objects in the payment info", e);
			}
		}
	}

	/**
	 * convert order total to double
	 * 
	 * @param orderTotal
	 * @return order total
	 */
	private Double getDoubleValue(final OrderTotal orderTotal)
	{
		if (NumberUtils.isNumber(orderTotal.getAmount()))
		{
			return Double.valueOf(orderTotal.getAmount());
		}
		return null;
	}

	/**
	 * convert refunded amount to double
	 * 
	 * @param refundedAmount
	 * @return getDoubleValue
	 */
	private Double getDoubleValue(final Price refundedAmount)
	{
		if (NumberUtils.isNumber(refundedAmount.getAmount()))
		{
			return Double.valueOf(refundedAmount.getAmount());
		}
		return null;
	}

	/**
	 * search and fill amazon configuration
	 */
	@SuppressWarnings(
	{ "deprecation", "unchecked" })
	private void createAmazonConfig()
	{
		if (amazonConfig == null)
		{
			final List<AmazonConfig> result = FlexibleSearch.getInstance()
					.search("SELECT {pk} FROM {AmazonConfig}", AmazonConfig.class).getResult();
			if (CollectionUtils.isNotEmpty(result))
			{
				amazonConfig = result.iterator().next();
			}
		}
	}

	/**
	 * @return this object instance to be used in Actions, Managers, etc. every time which a connection with amazon
	 *         client service has needed
	 */
	public static AmazonCredentials getInstance()
	{
		AmazonCredentials credentials = (AmazonCredentials) JaloSession.getCurrentSession().getAttribute(AMAZONCREDENTIALSSESSION);
		if (credentials == null)
		{
			credentials = new AmazonCredentials();
			JaloSession.getCurrentSession().setAttribute(AMAZONCREDENTIALSSESSION, credentials);
		}
		return credentials;
	}

	/**
	 * get amazon configurations
	 * 
	 * @return the amazonConfig
	 */
	public AmazonConfig getAmazonConfig()
	{
		return amazonConfig;
	}

	/**
	 * get amazon service client instance
	 * 
	 * @return the service
	 */
	public OffAmazonPaymentsServiceClient getService()
	{
		return service;
	}

}
