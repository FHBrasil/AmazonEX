package de.fliegersoftware.amazon.payment.commands.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsService;
import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceClient;
import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceConfig;

import de.fliegersoftware.amazon.core.services.AmazonConfigService;


@Component
public class AbstractCommandImpl {

	protected static final String NOT_SUPPORTED_MESSAGE = "Command is not supported by Amazon extension: ";
	private final static Logger LOG = LoggerFactory.getLogger(AbstractCommandImpl.class);
	private BufferedWriter bufferedWriter = null;
	@Resource
	protected AmazonConfigService amazonConfigService;
	
	protected String getSellerId() {
		return amazonConfigService.getSellerId();
	}
	
	protected OffAmazonPaymentsService getOffAmazonPaymentsService() {
		OffAmazonPaymentsServiceConfig config;
		OffAmazonPaymentsService service;
		
		config = new OffAmazonPaymentsServiceConfig(amazonConfigService.getAmazonProperties());
		service = new OffAmazonPaymentsServiceClient(config);
		
		return service;
	}

	protected void logXml(Logger LOG, Object obj) {
		try {
			StringWriter writer = new StringWriter();
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(obj, writer);
			//LOG.info(writer.toString());
			System.out.println(writer.toString());
			//getLogFile(writer.toString());			
		} catch (Exception e) {
			
		}
	}

	protected AmazonConfigService getAmazonConfigService() {
		return amazonConfigService;
	}

	public void setAmazonConfigService(AmazonConfigService defaultAmazonConfigService) {
		this.amazonConfigService = defaultAmazonConfigService;
	}
	
	public void getLogFile(String msg) {
		if (bufferedWriter!= null) { 
			try {
				bufferedWriter.write(msg);
				bufferedWriter.write("\n-------------------\n");
				bufferedWriter.flush();
				System.out.println(msg);
				return;
			} catch (IOException e) {
				LOG.error("IOException",e);
			}
		}		
		String dir = System.getProperty("HYBRIS_LOG_DIR");
		if (dir ==null || "".equals(dir)) {
			dir = "/tmp";
		}
		File logFile = new File(dir,"amazon-soap.log");
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(logFile));
			bufferedWriter.write(msg);
			bufferedWriter.write("\n-------------------\n");
			bufferedWriter.flush();
		} catch (FileNotFoundException e1) {
			LOG.error("FileNotFoundException",e1);
		} catch (IOException e) {
			LOG.error("IOException",e);
		}
		
	}
}
