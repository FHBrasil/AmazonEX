package de.fliegersoftware.amazon.payment.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.CollectionUtils;

import com.amazonaws.mws.MarketplaceWebService;
import com.amazonaws.mws.MarketplaceWebServiceClient;
import com.amazonaws.mws.MarketplaceWebServiceConfig;
import com.amazonaws.mws.MarketplaceWebServiceException;
import com.amazonaws.mws.model.IdList;
import com.amazonaws.mws.model.SubmitFeedRequest;
import com.amazonaws.mws.model.SubmitFeedResponse;
import com.amazonservices.mws.offamazonpayments.model.CaptureRequest;
import com.google.common.base.Preconditions;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;
import com.sun.xml.bind.marshaller.CharacterEscapeHandler;

import de.fliegersoftware.amazon.core.services.AmazonConfigService;
import de.fliegersoftware.amazon.payment.model.BatchRequest;
import de.fliegersoftware.amazon.payment.model.Connection;
import de.fliegersoftware.amazon.payment.model.Request;
import de.fliegersoftware.amazon.payment.model.RequestList;
import de.fliegersoftware.amazon.payment.services.MWSAmazonFeedsService;
import de.hybris.platform.util.Config;

public class DefaultMWSAmazonFeedsService implements MWSAmazonFeedsService {

	protected AmazonConfigService amazonConfigService;

	protected MarketplaceWebService getMarketplaceWebService() {
		Properties props = getAmazonConfigService().getAmazonProperties();
		final String accessKeyId = props.getProperty(AmazonConfigService.ACCESS_KEY_ID);
		final String secretAccessKey = props.getProperty(AmazonConfigService.SECRET_ACCESS_KEY);

		final String appName = props.getProperty(AmazonConfigService.APPLICATION_NAME);
		final String appVersion = props.getProperty(AmazonConfigService.APPLICATION_VERSION);

		Map<String, String> serviceUrls = new HashMap<>();
		serviceUrls.put("US", "https://mws.amazonservices.com");
		serviceUrls.put("UK", "https://mws.amazonservices.co.uk");
		serviceUrls.put("DE", "https://mws.amazonservices.de");
		serviceUrls.put("FR", "https://mws.amazonservices.fr");
		serviceUrls.put("IT", "https://mws.amazonservices.it");
		serviceUrls.put("JP", "https://mws.amazonservices.jp");
		serviceUrls.put("CN", "https://mws.amazonservices.com.cn");
		serviceUrls.put("CA", "https://mws.amazonservices.ca");
		serviceUrls.put("IN", "https://mws.amazonservices.in");
		serviceUrls.put("EU", "https://mws-eu.amazonservices.com");

		MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig()
			.withServiceURL(serviceUrls.get(props.get(AmazonConfigService.REGION)));

		MarketplaceWebService service = new MarketplaceWebServiceClient(
				accessKeyId, secretAccessKey, appName, appVersion, config);

		return service;
	}

	protected IdList getMarketplaceIdList() {
		Map<String, String> marketplaceIds = new HashMap<>();
		marketplaceIds.put("US-Sandbox", "A3BXB0YN3XH17H");
		marketplaceIds.put("US-Live", "AGWSWK15IEJJ7");
		marketplaceIds.put("UK-Sandbox", "A3M3RRFO9XDT2G");
		marketplaceIds.put("UK-Live", "AZAJMM36N6WQL");
		marketplaceIds.put("DE-Sandbox", "A1G8446IYHA4MR");
		marketplaceIds.put("DE-Live", "A53RDEWN57UU5");

		String key = amazonConfigService.getRegion() + "-" + (amazonConfigService.isSandboxMode() ? "Sandbox" : "Live");
		List<String> idList = new ArrayList<>();
		idList.add(marketplaceIds.get(key));

		return new IdList(idList);
	}

	protected String getFileMD5(String path) throws FileNotFoundException, IOException {
		InputStream stream = new FileInputStream(path);
		// content is a passed in InputStream
        byte[] resultByte = DigestUtils.md5(stream);
        String streamMD5 = new String(Base64.encodeBase64(resultByte));
		stream.close();
		return streamMD5;
	}

	@Override
	public SubmitFeedResponse submitFeed(String filename, List<CaptureRequest> requestList) {
		Preconditions.checkNotNull(filename, "filename cannot be null");
		Preconditions.checkArgument(!CollectionUtils.isEmpty(requestList), "requestList cannot be empty");
		Preconditions.checkArgument(requestList.size() <= 60000, "requestList maximum size is 60000");

		String path = Config.getParameter("amazonpayment.submit.feeds.path");
		if(path != null) {
			path = path + "/" + (filename.endsWith(".xml") ? filename : filename + ".xml");
			File file = new File(path);
			if(!file.exists()) {
				try {
					file.getParentFile().mkdirs();
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			BatchRequest batch = new BatchRequest();
			batch.setProduct("Amazon Payments");
			Connection connection = new Connection();
			connection.setVersion("2013-01-01");
			batch.setConnection(connection);
			List<Request> requests = new ArrayList<Request>();
			int id = 0;
			for(CaptureRequest raw : requestList) {
				raw.setSellerId(getAmazonConfigService().getSellerId());
				Request r = new Request();
				r.setSellerRequestId(String.valueOf(++id));
				r.setRequestData(raw);
				requests.add(r);
			}
			batch.setRequests(new RequestList(requests));

			try {
				JAXBContext context = JAXBContext.newInstance(BatchRequest.class);
				Marshaller marshaller = context.createMarshaller();
				// required to not escape CDATA
				marshaller.setProperty(CharacterEscapeHandler.class.getName(),
						new CharacterEscapeHandler() {
							@Override
							public void escape(char[] ac, int i, int j, boolean flag,
									Writer writer) throws IOException {
								writer.write(ac, i, j);
							}
						});
				marshaller.marshal(batch, file);
				SubmitFeedRequest request = new SubmitFeedRequest();
				request.setMerchant(getAmazonConfigService().getSellerId());
				request.setMarketplaceIdList(getMarketplaceIdList());
				request.setFeedType("_POST_TRANSACTION_PAYMENTS_BATCH_DATA_");
				request.setContentMD5(getFileMD5(path));
				request.setFeedContent(new FileInputStream(path));
				return submitFeed(request);
			} catch (JAXBException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Do not mix multiple API calls in a single batch.
	 * @param request
	 * @return
	 */
	@Override
	public SubmitFeedResponse submitFeed(SubmitFeedRequest request) {
		try {
			return getMarketplaceWebService().submitFeed(request);
		} catch (MarketplaceWebServiceException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected AmazonConfigService getAmazonConfigService() {
		return amazonConfigService;
	}

	@Required
	public void setAmazonConfigService(AmazonConfigService amazonConfigService) {
		this.amazonConfigService = amazonConfigService;
	}
}
