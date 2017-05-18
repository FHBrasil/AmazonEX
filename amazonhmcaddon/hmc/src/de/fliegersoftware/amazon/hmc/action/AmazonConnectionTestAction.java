/**
 * 
 */
package de.fliegersoftware.amazon.hmc.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.XML;

import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsResponse;

import de.fliegersoftware.amazon.core.jalo.config.AmazonConfig;
import de.hybris.platform.hmc.generic.AbstractActionChip;
import de.hybris.platform.hmc.util.action.AbstractActionDialogChip;
import de.hybris.platform.hmc.util.action.ActionEvent;
import de.hybris.platform.hmc.util.action.ActionResult;
import de.hybris.platform.hmc.util.action.ItemAction;
import de.hybris.platform.hmc.webchips.Chip;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.util.localization.Localization;




/**
 * Provides for Amazon Control Panel view the action which will be responsible for the connection test
 * 
 * @author douglas.canalli
 */
public class AmazonConnectionTestAction extends ItemAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String USER_AGENT = "Mozilla/5.0";

	private GetOrderReferenceDetailsResponse orderReferenceDetails;
	
	private static final String ISO_8601 = "yyyy-MM-dd'T'HH:mm:ssZ";
	private static final String API_VERSION = "2009-01-01";
	private static final String VERSION_SIGNATURE_VALUE = "2";
	private static final String HASH_ALGORITHM = "HmacSHA256";
	private static final String AMAZONWS = "https://mws.amazonservices.com";
	private static final String ACCESS_KEY_ID = "AWSAccessKeyId";
	private static final String ACTION = "Action";
	private static final String SELLER_ID = "SellerId";
	private static final String SIGNATURE_METHOD = "SignatureMethod";
	private static final String SIGNATURE_VERSION = "SignatureVersion";
	private static final String TIMESTAMP = "Timestamp";
	private static final String VERSION = "Version";
	private static final String SIGNATURE = "Signature";
	private static final String SERVICE_STATUS = "GetServiceStatus";
	private static final String GREEN_STATUS = "GREEN";

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
	

		final AmazonConfig amazonConfig = (AmazonConfig) actionEvent.getData();

		LOG.info("CallingGetAmazonStatus");
		String amazonStatus = null;
		try {
			amazonStatus = sendPost(amazonConfig);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error("Get amazon status", e);
		}
		
		final String[] messageStatus =  new String[]{ amazonStatus };
		if (!GREEN_STATUS.equalsIgnoreCase(amazonStatus))
		{
			LOG.info(errorMessage);
			return new ActionResult(ActionResult.FAILED, Localization.getLocalizedString("msg.connection.test.error", messageStatus ), false);
		}
		final String successMessage = Localization.getLocalizedString("msg.connection.test.success", messageStatus);

		return new ActionResult(ActionResult.OK, successMessage, false);
	}
	
	private String sendPost(final AmazonConfig amazonConfig) throws Exception {

		final String url = AMAZONWS;

		HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(url);
		
		// add header
		post.setHeader("User-Agent", USER_AGENT);
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair(ACCESS_KEY_ID, amazonConfig.getMwsAccessKey()));
		urlParameters.add(new BasicNameValuePair(ACTION, SERVICE_STATUS));
		urlParameters.add(new BasicNameValuePair(SELLER_ID, amazonConfig.getMerchantId()));
		urlParameters.add(new BasicNameValuePair(SIGNATURE_METHOD, HASH_ALGORITHM));
		urlParameters.add(new BasicNameValuePair(SIGNATURE_VERSION, VERSION_SIGNATURE_VALUE));
		urlParameters.add(new BasicNameValuePair(TIMESTAMP, getTimeStamp()));
		urlParameters.add(new BasicNameValuePair(VERSION, API_VERSION));
		urlParameters.add(new BasicNameValuePair(SIGNATURE,  amazonConfig.getMwsSecretKey()));

		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		
		LOG.info("\nSending 'POST' request to URL : " + url);
		LOG.info("Post parameters : " + post.getEntity());
		LOG.info("Response Code : " +
                                    response.getStatusLine().getStatusCode());
		
		if( response.getStatusLine().getStatusCode() == 200){
			
			final String json = EntityUtils.toString(response.getEntity());
			final JSONObject jsonObj = XML.toJSONObject(json);
			final String statusAmazon = jsonObj.getJSONObject("GetServiceStatusResponse").getJSONObject("GetServiceStatusResult").getString("Status");
			LOG.info("Amazon Status = " +statusAmazon);
			
			return statusAmazon;
			
		}
		return "";
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
		if ("Other".equalsIgnoreCase(amazonConfig.getAmazonConfigCountry().getCode()))
		{
			return amazonConfig.getOtherCountry();
		}
		return amazonConfig.getAmazonConfigCountry().getCode();
	}
	
	private static String getTimeStamp() {
		
		final Calendar calendar = GregorianCalendar.getInstance();
        Date date = calendar.getTime();
        String formatted = new SimpleDateFormat(ISO_8601).format(date);
        return formatted.substring(0, 22) + ":" + formatted.substring(22);
    }
}
