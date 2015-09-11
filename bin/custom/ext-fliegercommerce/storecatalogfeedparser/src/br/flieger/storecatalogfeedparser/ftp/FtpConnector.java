package br.flieger.storecatalogfeedparser.ftp;
import static br.flieger.storecatalogfeedparser.constants.Constants.FTP_ADDRESS;
import static br.flieger.storecatalogfeedparser.constants.Constants.FTP_USER_NAME;
import static br.flieger.storecatalogfeedparser.constants.Constants.FTP_USER_PASSWORD;
import static br.flieger.storecatalogfeedparser.constants.Constants.GOOGLE_FTP_ADDRESS;
import static br.flieger.storecatalogfeedparser.constants.Constants.GOOGLE_FTP_USER_NAME;
import static br.flieger.storecatalogfeedparser.constants.Constants.GOOGLE_FTP_USER_PASSWORD;
import static br.flieger.storecatalogfeedparser.constants.Constants.PROJECT_PROPERTIES;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;

import br.flieger.storecatalogfeedparser.constants.Constants;

/**
 * 
 * @author franthescolly
 *
 */
public class FtpConnector {	

	/**
	 * Project properties
	 */
	private final Properties properties;
	
	/**
	 * FTP client
	 */
	private FTPClient fClient;

	/**
	 * Default constructor
	 */
	public FtpConnector() {
		
		this.properties = new Properties();

		try {
			properties.load(new FileInputStream(PROJECT_PROPERTIES));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private boolean connect(FtpProfile profile) {
		
		try {
			if(profile == null) {
				throw new Exception("profile is null!");
			}

			disconnect();
							
			fClient = new FTPClient();
			fClient.connect(profile.getFtpAddress(), 21);
			fClient.enterLocalPassiveMode();
			
			fClient.login(profile.getFtpUserName(), profile.getFtpUserPassword());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if(fClient.isConnected())	 {
			System.out.println("Connected to " + profile.getFtpAddress());
		} else {
			System.out.println("Couldn't connect to " + profile.getFtpAddress());
		}
		
		return fClient.isConnected();
	}
	
	/**
	 * 
	 */
	private void disconnect() {
		
		if (fClient != null && fClient.isConnected()) {			
			try {
				fClient.disconnect();
			} catch (Exception e) {}
		}
	}
	
	public void openDir(String... dir) throws Exception {
		if(dir != null && dir.length > 0) {		
			for(String folder : dir) {					
				fClient.cwd(folder);
			}
		}
	}

	public boolean putFile(File file) {
		
		if(true) {
			System.out.println("Fake FTP upload: " + file.getAbsolutePath());
			return true;
		}
		
		try {
			List<FtpProfile> ftpProfileChain = getFTPProfileChain(file);
			
			if(ftpProfileChain == null || ftpProfileChain.isEmpty()) {
				System.out.println("Couldn't find a valid profile chain for: " + file.getName());
				return false;
			}
				
			for(FtpProfile profile : ftpProfileChain) {
				
				System.out.println(profile);
				
				if(!connect(profile)) {
					continue;
				}
				
				fClient.setFileType(FTPClient.BINARY_FILE_TYPE);
				openDir(profile.getFolder());
				
				System.out.println("The file " + file.getName() + " was stored? " + fClient.storeFile(file.getName(), new FileInputStream(file)));
				System.out.println("FTP Reply string storing file " + file.getName() + " on " + profile.getFtpAddress() + " : " + fClient.getReplyString());
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return false;
	}
	
	private List<FtpProfile> getFTPProfileChain(File file) {
		
		if(file == null) {
			throw new InvalidParameterException("file is null");
		}
		
		List<FtpProfile> profiles = new ArrayList<FtpProfile>();
		
		//by default, every file goes to babyartikel shopfeed folder
		//
		FtpProfile profile = new FtpProfile();
		profile.setFtpAddress(properties.getProperty(FTP_ADDRESS));
		profile.setFtpUserName(properties.getProperty(FTP_USER_NAME));
		profile.setFtpUserPassword(properties.getProperty(FTP_USER_PASSWORD));
		profile.setFolder(new String[]{"shopfeed"});
		
		profiles.add(profile);

		if(file.getName().equalsIgnoreCase(Constants.GOOGLE_CATALOG_FILE_NAME)) {
			profile = new FtpProfile();
			profile.setFtpAddress(properties.getProperty(GOOGLE_FTP_ADDRESS));
			profile.setFtpUserName(properties.getProperty(GOOGLE_FTP_USER_NAME));
			profile.setFtpUserPassword(properties.getProperty(GOOGLE_FTP_USER_PASSWORD));
			
			profiles.add(profile);
		} 
		
		return profiles;
	}
}