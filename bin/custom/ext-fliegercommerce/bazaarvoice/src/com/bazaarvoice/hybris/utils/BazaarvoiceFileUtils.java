package com.bazaarvoice.hybris.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;


public class BazaarvoiceFileUtils {

	private static final Logger LOG = Logger.getLogger(BazaarvoiceFileUtils.class);
	
	 public void unzip(String fileZip, String destination) throws FileNotFoundException, IOException, ArchiveException {
		 
	        File inputFile = new File(fileZip);
	 
	        InputStream is = new FileInputStream(inputFile);
	        ArchiveInputStream ais = new ArchiveStreamFactory().createArchiveInputStream("zip", is);
	 
	        ZipArchiveEntry entry = null;
	 
	        while ((entry = (ZipArchiveEntry) ais.getNextEntry()) != null) {
	            File outFile = new File(destination + File.separator + entry.getName());
	            if (!outFile.exists()) {
	                outFile.mkdirs();
	                continue;
	            }
	            if (outFile.isDirectory()) {
	                continue;
	            }
	 
	            if (outFile.exists()) {
	                continue;
	            }
	 
	            FileUtils.copyInputStreamToFile(ais, outFile);
	 
	        }
	 }
	 
	 public boolean descompressGZip(String fileGZ, String destination){

		 File gz = new File(fileGZ);
		 Double sizeBuffer = new Double(gz.length());
		 
		 byte[] buffer = new byte[sizeBuffer.intValue()];
		 
	     try{
	 
	    	 GZIPInputStream gzis = 
	    		new GZIPInputStream(new FileInputStream(gz));
	 
	    	 FileOutputStream out = 
	            new FileOutputStream(destination);
	 
	        int len;
	        while ((len = gzis.read(buffer)) > 0) {
	        	out.write(buffer, 0, len);
	        }

	        gzis.close();
	    	out.close();
	 
	    }catch(IOException ex){
	       ex.printStackTrace();
	       return false;
	    }
	     return true;
	 }
	 
}
