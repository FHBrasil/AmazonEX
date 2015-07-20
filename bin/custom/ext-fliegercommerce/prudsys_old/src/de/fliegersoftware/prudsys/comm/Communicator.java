/**
 * 
 */
package de.fliegersoftware.prudsys.comm;

import de.hybris.platform.util.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.fliegersoftware.prudsys.CONFIGURATION;
import de.fliegersoftware.prudsys.async.Request;


/**
 * @author gustavok
 * 
 */
public class Communicator
{

	/**
	 * Logger
	 */
	protected static Logger LOG = Logger.getLogger(Communicator.class);

	private static Communicator instance = null;

	public static Communicator getInstance()
	{
		if (instance == null)
		{
			instance = new Communicator();
		}
		return instance;
	}

	private static String host = CONFIGURATION.prudsys_host.getString();
	private static String port = CONFIGURATION.prudsys_port.getString();
	private static String reid = CONFIGURATION.prudsys_reid.getString();

	private static final String CONSTANT_HOST = "<HOST>";
	private static final String CONSTANT_PORT = "<PORT>";
	private static final String CONSTANT_RE_ID = "<RE_ID>";
	private static final String CONSTANT_SESSION_ID = "<SESSION_ID>";
	private static final String CONSTANT_VALUE = "<VALUE>";
	private static final String CONSTANT_QUANTITY = "<QUANTITY>";
	private static final String CONSTANT_VALUES = "<ITEMS>";
	private static final String CONSTANT_BLACKLIST_VALUES = "<BLACKLIST>";
	private static final String CONSTANT_USER_ID = "<USER_ID>";

	private static final String CONSTANT_BOX = "<BOX>";
	private static final String CONSTANT_TEMPLATE = "<TEMPLATE>";
	private static final String CONSTANT_UTP = "<UTP>";

	public List<String> globTops(final String sessionId)
	{
		List<String> listProduct = null;
		String link = CONFIGURATION.track_event_globTops.getString();
		link = link.replaceAll(CONSTANT_HOST, host);
		link = link.replaceAll(CONSTANT_PORT, port);
		link = link.replaceAll(CONSTANT_RE_ID, reid);
		link = link.replaceAll(CONSTANT_SESSION_ID, sessionId);

		String response = callRecommendationEngine(link);

		if (StringUtils.isBlank(response))
		{
			return null;
		}

		JSONArray obj = null;
		try
		{
			obj = new JSONArray(response).getJSONObject(0).getJSONArray("box1");
			listProduct = new ArrayList<String>();
			for (int i = 0; i < obj.length(); i++)
			{
				try
				{
					JSONObject obj2 = obj.getJSONObject(i);
					listProduct.add(obj2.get("product_nr") + "");
				}
				catch (JSONException e)
				{
					LOG.debug("No result returned from prudsys!");
				}
			}
		}
		catch (JSONException e)
		{
			LOG.debug("No result returned from prudsys!");
		}

		return listProduct;
	}

	public List<String> catTops(final String sessionId, final String value)
	{
		String categoryTrack = CONFIGURATION.track_event_categoryview.getString();
		categoryTrack = categoryTrack.replaceAll(CONSTANT_HOST, host);
		categoryTrack = categoryTrack.replaceAll(CONSTANT_PORT, port);
		categoryTrack = categoryTrack.replaceAll(CONSTANT_RE_ID, reid);
		categoryTrack = categoryTrack.replaceAll(CONSTANT_SESSION_ID, sessionId);
		categoryTrack = categoryTrack.replaceAll(CONSTANT_VALUE, value);

		callTrackEventHandler(categoryTrack);

		List<String> listProduct = null;
		String link = CONFIGURATION.track_event_catTops.getString();
		link = link.replaceAll(CONSTANT_HOST, host);
		link = link.replaceAll(CONSTANT_PORT, port);
		link = link.replaceAll(CONSTANT_RE_ID, reid);
		link = link.replaceAll(CONSTANT_SESSION_ID, sessionId);
		link = link.replaceAll(CONSTANT_VALUE, value);

		String response = callRecommendationEngine(link);

		if (StringUtils.isBlank(response))
		{
			return null;
		}

		JSONArray obj = null;
		try
		{
			obj = new JSONArray(response).getJSONObject(0).getJSONArray("box1");
			listProduct = new ArrayList<String>();
			for (int i = 0; i < obj.length(); i++)
			{
				try
				{
					JSONObject obj2 = obj.getJSONObject(i);
					listProduct.add(obj2.get("product_nr") + "");
				}
				catch (JSONException e)
				{
					LOG.debug("No result returned from prudsys!");
				}
			}
		}
		catch (JSONException e)
		{
			LOG.debug("No result returned from prudsys!");
		}

		return listProduct;
	}

	public List<String> productPage(final String sessionId, final String value, final String blacklist)
	{
		String productTrack = CONFIGURATION.track_event_productview.getString();
		productTrack = productTrack.replaceAll(CONSTANT_HOST, host);
		productTrack = productTrack.replaceAll(CONSTANT_PORT, port);
		productTrack = productTrack.replaceAll(CONSTANT_RE_ID, reid);
		productTrack = productTrack.replaceAll(CONSTANT_SESSION_ID, sessionId);
		productTrack = productTrack.replaceAll(CONSTANT_VALUE, value);

		callTrackEventHandler(productTrack);

		List<String> listProduct = null;
		String link = CONFIGURATION.track_event_productPage.getString();
		link = link.replaceAll(CONSTANT_HOST, host);
		link = link.replaceAll(CONSTANT_PORT, port);
		link = link.replaceAll(CONSTANT_RE_ID, reid);
		link = link.replaceAll(CONSTANT_SESSION_ID, sessionId);
		link = link.replaceAll(CONSTANT_VALUE, value);
		link = link.replaceAll(CONSTANT_BLACKLIST_VALUES, blacklist);

		String response = callRecommendationEngine(link);
		if (StringUtils.isBlank(response))
		{
			return null;
		}

		JSONArray obj = null;
		try
		{
			obj = new JSONArray(response).getJSONObject(0).getJSONArray("box1");
			listProduct = new ArrayList<String>();
			for (int i = 0; i < obj.length(); i++)
			{
				try
				{
					JSONObject obj2 = obj.getJSONObject(i);
					listProduct.add(obj2.get("product_nr") + "");
				}
				catch (JSONException e)
				{
					LOG.debug("error", e);
				}
			}
			obj = new JSONArray(response).getJSONObject(1).getJSONArray("box2");
			for (int i = 0; i < obj.length(); i++)
			{
				try
				{
					JSONObject obj2 = obj.getJSONObject(i);
					listProduct.add(obj2.get("product_nr") + "");
				}
				catch (JSONException e)
				{
					LOG.debug("error", e);
				}
			}
		}
		catch (JSONException e)
		{
			LOG.debug("No result returned from prudsys!", e);
		}
		return listProduct;
	}

	public List<String> basketpage(final String sessionId, final String value)
	{
		List<String> listProduct = null;
		String link = CONFIGURATION.track_event_basketpage.getString();
		link = link.replaceAll(CONSTANT_HOST, host);
		link = link.replaceAll(CONSTANT_PORT, port);
		link = link.replaceAll(CONSTANT_RE_ID, reid);
		link = link.replaceAll(CONSTANT_SESSION_ID, sessionId);
		link = link.replaceAll(CONSTANT_VALUE, value);

		String response = callRecommendationEngine(link);

		if (StringUtils.isBlank(response))
		{
			return null;
		}

		JSONArray obj = null;
		try
		{
			obj = new JSONArray(response).getJSONObject(0).getJSONArray("box1");
			listProduct = new ArrayList<String>();
			for (int i = 0; i < obj.length(); i++)
			{
				try
				{
					JSONObject obj2 = obj.getJSONObject(i);
					listProduct.add(obj2.get("product_nr") + "");
				}
				catch (JSONException e)
				{
					LOG.error("No result returned from prudsys!", e);
				}
			}
		}
		catch (JSONException e)
		{
			LOG.error("No result returned from prudsys!", e);
		}

		return listProduct;
	}

	public List<String> homePage(final String sessionId)
	{
		List<String> listProduct = null;
		String link = CONFIGURATION.track_event_homePage.getString();
		link = link.replaceAll(CONSTANT_HOST, host);
		link = link.replaceAll(CONSTANT_PORT, port);
		link = link.replaceAll(CONSTANT_RE_ID, reid);
		link = link.replaceAll(CONSTANT_SESSION_ID, sessionId);

		String response = callRecommendationEngine(link);

		if (StringUtils.isBlank(response))
		{
			return null;
		}

		JSONArray obj = null;
		try
		{
			obj = new JSONArray(response).getJSONObject(0).getJSONArray("box1");
			listProduct = new ArrayList<String>();
			for (int i = 0; i < obj.length(); i++)
			{
				try
				{
					JSONObject obj2 = obj.getJSONObject(i);
					listProduct.add(obj2.get("product_nr") + "");
				}
				catch (JSONException e)
				{
					LOG.debug("No result returned from prudsys!");
				}
			}
		}
		catch (JSONException e)
		{
			LOG.debug("No result returned from prudsys!");
		}

		return listProduct;
	}

	public List<String> brandPage(final String sessionId, final String value)
	{
		List<String> listProduct = null;
		String link = CONFIGURATION.track_event_brandPage.getString();
		link = link.replaceAll(CONSTANT_HOST, host);
		link = link.replaceAll(CONSTANT_PORT, port);
		link = link.replaceAll(CONSTANT_RE_ID, reid);
		link = link.replaceAll(CONSTANT_SESSION_ID, sessionId);
		link = link.replaceAll(CONSTANT_VALUE, value);

		String response = callRecommendationEngine(link);

		if (StringUtils.isBlank(response))
		{
			return null;
		}

		JSONArray obj = null;
		try
		{
			obj = new JSONArray(response).getJSONObject(0).getJSONArray("box1");
			listProduct = new ArrayList<String>();
			for (int i = 0; i < obj.length(); i++)
			{
				try
				{
					JSONObject obj2 = obj.getJSONObject(i);
					listProduct.add(obj2.get("product_nr") + "");
				}
				catch (JSONException e)
				{
					LOG.debug("No result returned from prudsys!");
				}
			}
		}
		catch (JSONException e)
		{
			LOG.debug("No result returned from prudsys!");
		}

		return listProduct;
	}

	public List<String> errorPage(final String sessionId)
	{
		List<String> listProduct = null;
		String link = CONFIGURATION.track_event_errorPage.getString();
		link = link.replaceAll(CONSTANT_HOST, host);
		link = link.replaceAll(CONSTANT_PORT, port);
		link = link.replaceAll(CONSTANT_RE_ID, reid);
		link = link.replaceAll(CONSTANT_SESSION_ID, sessionId);

		String response = callRecommendationEngine(link);

		if (StringUtils.isBlank(response))
		{
			return null;
		}

		JSONArray obj = null;
		try
		{
			obj = new JSONArray(response).getJSONObject(0).getJSONArray("box1");
			listProduct = new ArrayList<String>();
			for (int i = 0; i < obj.length(); i++)
			{
				try
				{
					JSONObject obj2 = obj.getJSONObject(i);
					listProduct.add(obj2.get("product_nr") + "");
				}
				catch (JSONException e)
				{
					LOG.debug("No result returned from prudsys!");
				}
			}
		}
		catch (JSONException e)
		{
			LOG.debug("No result returned from prudsys!");
		}

		return listProduct;
	}

	public List<String> noResultPage(final String sessionId)
	{
		List<String> listProduct = null;
		String link = CONFIGURATION.track_event_noResultPage.getString();
		link = link.replaceAll(CONSTANT_HOST, host);
		link = link.replaceAll(CONSTANT_PORT, port);
		link = link.replaceAll(CONSTANT_RE_ID, reid);
		link = link.replaceAll(CONSTANT_SESSION_ID, sessionId);

		String response = callRecommendationEngine(link);

		if (StringUtils.isBlank(response))
		{
			return null;
		}

		JSONArray obj = null;
		try
		{
			obj = new JSONArray(response).getJSONObject(0).getJSONArray("box1");
			listProduct = new ArrayList<String>();
			for (int i = 0; i < obj.length(); i++)
			{
				try
				{
					JSONObject obj2 = obj.getJSONObject(i);
					listProduct.add(obj2.get("product_nr") + "");
				}
				catch (JSONException e)
				{
					LOG.debug("No result returned from prudsys!");
				}
			}
		}
		catch (JSONException e)
		{
			LOG.debug("No result returned from prudsys!");
		}

		return listProduct;
	}

	public void trackEventUserToSession(final String sessionId, final String customerId)
	{
		String userSessionTrack = CONFIGURATION.event_usertosession.getString();
		userSessionTrack = userSessionTrack.replaceAll(CONSTANT_HOST, host);
		userSessionTrack = userSessionTrack.replaceAll(CONSTANT_PORT, port);
		userSessionTrack = userSessionTrack.replaceAll(CONSTANT_RE_ID, reid);
		userSessionTrack = userSessionTrack.replaceAll(CONSTANT_SESSION_ID, sessionId);
		userSessionTrack = userSessionTrack.replaceAll(CONSTANT_USER_ID, customerId);

		callTrackEventHandler(userSessionTrack);
	}

	public void trackEventClick(final String sessionId, final String value, final String template, final String box)
	{
		String eventClick = CONFIGURATION.track_event_click.getString();
		eventClick = eventClick.replaceAll(CONSTANT_HOST, host);
		eventClick = eventClick.replaceAll(CONSTANT_PORT, port);
		eventClick = eventClick.replaceAll(CONSTANT_RE_ID, reid);
		eventClick = eventClick.replaceAll(CONSTANT_SESSION_ID, sessionId);
		eventClick = eventClick.replaceAll(CONSTANT_VALUE, value);
		eventClick = eventClick.replaceAll(CONSTANT_TEMPLATE, template);
		eventClick = eventClick.replaceAll(CONSTANT_BOX, box);

		callTrackEventHandler(eventClick);
	}

	public void basket(final String sessionId, final List<String> items, final List<String> qty)
	{
		String link = CONFIGURATION.track_event_basket.getString();
		link = link.replaceAll(CONSTANT_HOST, host);
		link = link.replaceAll(CONSTANT_PORT, port);
		link = link.replaceAll(CONSTANT_RE_ID, reid);
		link = link.replaceAll(CONSTANT_SESSION_ID, sessionId);

		String sItems = "";
		String sQty = "";
		for (int i = 0; i < items.size(); i++)
		{
			sItems += items.get(i);
			sQty += qty.get(i);

			if (i != items.size() - 1)
			{
				sItems += ",";
				sQty += ",";
			}
		}

		link = link.replaceAll(CONSTANT_VALUES, sItems);
		link = link.replaceAll(CONSTANT_QUANTITY, sQty);

		callTrackEventHandler(link);
	}

	public void order(final String sessionId, final List<String> items, final List<String> qty, final List<String> utp)
	{
		String link = CONFIGURATION.track_event_order.getString();
		link = link.replaceAll(CONSTANT_HOST, host);
		link = link.replaceAll(CONSTANT_PORT, port);
		link = link.replaceAll(CONSTANT_RE_ID, reid);
		link = link.replaceAll(CONSTANT_SESSION_ID, sessionId);

		String sItems = "";
		String sQty = "";
		String sUtp = "";
		for (int i = 0; i < items.size(); i++)
		{
			sItems += items.get(i);
			sQty += qty.get(i);
			sUtp += utp.get(i);

			if (i != items.size() - 1)
			{
				sItems += ",";
				sQty += ",";
				sUtp += ",";
			}
		}

		link = link.replaceAll(CONSTANT_VALUES, sItems);
		link = link.replaceAll(CONSTANT_QUANTITY, sQty);
		link = link.replaceAll(CONSTANT_UTP, sUtp);
		callTrackEventHandler(link);
	}

	/*
	 * EVENTS
	 */
	public boolean ping()
	{
		String link = CONFIGURATION.event_ping.getString();
		link = link.replaceAll(CONSTANT_HOST, host);
		link = link.replaceAll(CONSTANT_PORT, port);
		link = link.replaceAll(CONSTANT_RE_ID, reid);

		String response = callRecommendationEngine(link);

		if (!"".equals(response))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private void callTrackEventHandler(final String link)
	{
		if (!isPrudsysEnabled())
		{
			LOG.debug("prudsys deactivated!!!");
		}

		ExecutorService executor = null;
		try
		{
			executor = Executors.newFixedThreadPool(1);

			URL url = new URL(createConnectionLink(link));
			LOG.debug("callTrackEventHandler: " + url);
			executor.submit(new Request(url));
		}
		catch (final Throwable e)
		{
			LOG.error("Prudsys Error", e);
		}
		finally
		{
			executor.shutdown();
		}
	}

	private String callRecommendationEngine(final String link)
	{
		if (!isPrudsysEnabled())
		{
			LOG.debug("prudsys deactivated!!!");
			return "";
		}

		HttpURLConnection conn = null;
		StringBuffer sb = new StringBuffer();
		try
		{
			conn = buildHttpConnection(link);

			LOG.debug("callRecommendationEngine: " + conn.getURL());

			int responseCode = conn.getResponseCode();

			if (!(responseCode == HttpURLConnection.HTTP_OK))
			{
				LOG.error("Response code: " + responseCode + " " + HttpStatus.getStatusText(responseCode));
				return "";
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String dataFromUrl = "";
			while ((dataFromUrl = br.readLine()) != null)
			{
				sb.append(dataFromUrl);
			}
		}
		catch (final Throwable e)
		{
			LOG.error("Prudsys Error", e);
		}
		finally
		{
			closeConnection(conn);
		}
		return sb.toString();
	}

	private HttpURLConnection buildHttpConnection(final String link) throws IOException
	{
		URL url = new URL(createConnectionLink(link));

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(500);
		conn.setReadTimeout(1000);

		return conn;
	}

	private void closeConnection(HttpURLConnection conn)
	{
		if (conn == null)
		{
			return;
		}

		try
		{
			conn.disconnect();
		}
		catch (Exception e)
		{
			LOG.error("error closing connection: ", e);
		}
	}

	private String createConnectionLink(final String link)
	{
		String proxy = CONFIGURATION.prudsys_proxy.getString();
		if (proxy == null)
			proxy = "";

		String connectionLink = proxy + link;

		return connectionLink;
	}

	private boolean isPrudsysEnabled()
	{
		return Config.getBoolean("prudsys.tracking.enabled", true);
	}
}