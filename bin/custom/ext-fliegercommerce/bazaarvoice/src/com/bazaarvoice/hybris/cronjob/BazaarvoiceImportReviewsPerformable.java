package com.bazaarvoice.hybris.cronjob;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import de.hybris.platform.catalog.jalo.CatalogManager;

import org.apache.commons.lang.math.NumberUtils;

import com.bazaarvoice.hybris.constants.BazaarvoiceConstants;
import com.bazaarvoice.hybris.exception.BazaarvoiceImportReviewsException;
import com.bazaarvoice.hybris.model.BazaarvoiceConfigModel;
import com.bazaarvoice.hybris.model.BazaarvoiceProductFeedExportCronJobModel;
import com.bazaarvoice.hybris.model.BazaarvoiceProductFeedImportCronJobModel;
import com.bazaarvoice.hybris.utils.BazaarvoiceFileUtils;
import com.bazaarvoice.hybris.xml.BazaarXMLImport;
import com.lowagie.text.pdf.codec.Base64.OutputStream;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.Utilities;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import org.jdom.Element;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;




@Component("bazaarvoiceImportReviewsPerformable")
@Scope("prototype")
public class BazaarvoiceImportReviewsPerformable extends AbstractJobPerformable<BazaarvoiceProductFeedImportCronJobModel>
{
	private static final int MINIMAL_PRODUCT_REVIEWS = 5000;
	private static final String PROJECT_ROOT_PATH = Utilities.getExtensionInfo(BazaarvoiceConstants.EXTENSIONNAME)
			.getExtensionDirectory().getAbsolutePath()
			+ File.separator + "resources" + File.separator + "feed";
	private static final Logger LOG = Logger.getLogger(BazaarvoiceProductFeedPerformable.class);
	private CMSSiteModel cmsSiteModel;
	private BazaarvoiceConfigModel bazaarvoiceConfig;
	private String ftpServerDirectory;
	private String ftpImportDirectory;
	private String ftpImportFile;
	private String ftpServerPort;
	private String xmlBaseFileName;
	private String localXmlOutputDirectory;
	private String localXmlArchiveDirectory;
	private String localDirectoryToDownload;
	private String fileBazaarRating;
	

	@Resource
	private ProductService productService;

	private ProductModel productModel;
	
	@Resource
	private CatalogVersionService catalogVersionService;
	private CatalogVersionModel catalogVersionModel;
	

	@Override
	public PerformResult perform(BazaarvoiceProductFeedImportCronJobModel paramT){
		LOG.debug("BazaarvoiceImportReviewsPerformable perform");
		
		cmsSiteModel = paramT.getBazaarvoiceStorefront();
		bazaarvoiceConfig = cmsSiteModel.getBvConfig();
		ftpServerDirectory = Config.getParameter(BazaarvoiceConstants.FTP_SERVER_DIRECTORY);
		ftpImportDirectory = Config.getParameter(BazaarvoiceConstants.FTP_IMPORT_DIRECTORY);
		ftpImportFile = Config.getParameter(BazaarvoiceConstants.FTP_IMPORT_FILE);
		ftpServerPort = Config.getParameter(BazaarvoiceConstants.FTP_SERVER_PORT);
		xmlBaseFileName = Config.getParameter(BazaarvoiceConstants.XML_BASE_FILE_NAME);
		localDirectoryToDownload = Config.getParameter(BazaarvoiceConstants.LOCAL_DIRECTORY_TO_DOWNLOAD);
		fileBazaarRating = Config.getParameter(BazaarvoiceConstants.FILE_BAZAAR_RATING);
		

		String server = bazaarvoiceConfig.getFtpServer();

		final File dirDonwload = new File(PROJECT_ROOT_PATH + (localDirectoryToDownload == null ? "" : localDirectoryToDownload));
		if (!dirDonwload.exists())
		{
			dirDonwload.mkdirs();
		}

		String destinyFile = dirDonwload + File.separator + ftpImportFile;
		boolean isFileDownloaded = importFilefromSFTPServer(server, destinyFile);
		
		if (!isFileDownloaded){
			return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
		}

		String descrompressFile =  dirDonwload + fileBazaarRating;
		BazaarvoiceFileUtils fileUtil = new BazaarvoiceFileUtils();
		
		try{
			boolean isUnziped = fileUtil.descompressGZip(destinyFile, descrompressFile);
			if (!isUnziped)
				return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			parseXML(new File(descrompressFile));
		}catch(Exception e){
			e.printStackTrace();
		}
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}
	
	
	public boolean importFilefromSFTPServer(String server, String destinyFile){

		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;
		Integer port = null;
		LOG.info("preparing the host information for sftp.");
		try {
			port = getPort(server, port);
			JSch jsch = new JSch();
			session = jsch.getSession(bazaarvoiceConfig.getFtpUserName(), bazaarvoiceConfig.getFtpServer(), port);
			session.setPassword(bazaarvoiceConfig.getFtpPassword());
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			LOG.info("Host connected.");
			channel = session.openChannel("sftp");
			channel.connect();
			LOG.info("sftp channel opened and connected.");
			channelSftp = (ChannelSftp) channel;
			channelSftp.cd(ftpImportDirectory);
			
			channelSftp.get(ftpImportFile, destinyFile); 
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private Integer getPort(String server, Integer port) {
		if(ftpServerPort==null || ftpServerPort.isEmpty()){
            if(server!=null && !server.isEmpty()) {
                port = (server.toLowerCase().substring(0,4).equals("sftp") ? Integer.valueOf(22) : Integer.valueOf(21));
            }
        } else {
            try{
                port = Integer.valueOf(ftpServerPort);
            } catch (Exception e) {
                port = Integer.valueOf(21);
            }
        }
		return port;
	}
	
	private void parseXML(File file) throws FileNotFoundException,BazaarvoiceImportReviewsException {
		
		if(!file.exists()) {
			throw new FileNotFoundException("There's no XML file to parse");
		}
		
		final BazaarXMLImport xml = new BazaarXMLImport(file, false);
		
		List<Element> products = xml.findElementsByName("Product");

		if(products == null || products.isEmpty()) {
			LOG.info("Empty XML, exiting process now");
			return;
		}
		
		if (products.size() < MINIMAL_PRODUCT_REVIEWS){
			LOG.info("Number of the products for clean reviewed is low");
			throw new BazaarvoiceImportReviewsException("Number of the products for clean reviewed is low: " + products.size());
		}

		LOG.info("Number of products reviewed:" + products.size());
		catalogVersionModel = catalogVersionService.getCatalogVersion("babyartikelProductCatalog", CatalogManager.OFFLINE_VERSION);


		for(Element productElement : products) {
			Element codeElement = xml.findElementByName(productElement, "ExternalId");
			try{
				productModel = productService.getProductForCode(catalogVersionModel, codeElement.getValue());
			}
			catch(AmbiguousIdentifierException e){
				LOG.info("There's more than one Product with the specified code ("+ codeElement.getValue()+ ") and Catalog (" +CatalogVersionModel.CATALOG + ")" );
				e.printStackTrace();
				continue;
			}
			catch(UnknownIdentifierException e){
				LOG.info("There's no Product with the specified code ("+ codeElement.getValue()+ ") and Catalog (" +CatalogVersionModel.CATALOG + ")" );
				continue;
			}
			
			Element reviewGroup = xml.findElementByName(productElement, "ReviewStatistics");

			if(reviewGroup == null) {
				LOG.info("There's no review to: ".concat(codeElement.getValue()));
				continue;
			}
			try {
				
				Element review = xml.findElementByName(reviewGroup, "AverageOverallRating");
				if(review != null) {
					productModel.setReviewsAverageRating(NumberUtils.toDouble(review.getValue()));
				}
				
				Element count = xml.findElementByName(productElement, "NumReviews");
				if(count != null) {
					productModel.setReviewsTotalCount(NumberUtils.toInt(count.getValue()));
				}
				 
			} catch (Exception e) {
				e.printStackTrace();
			}
			modelService.save(productModel);
			LOG.info("Product modified: " +productModel.getCode());
		}
	}
}
