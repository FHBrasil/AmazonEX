package br.flieger.storecatalogfeedparser.parser;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import br.flieger.storecatalogfeedparser.support.EmailNotifier;


public class Parser extends EmailNotifier 
{
	private static final long serialVersionUID = 7566420099453550089L;
	
	public boolean transform(final ParseableEntry parseableEntry, final String xmlFile) 
	{
		try 
		{
			//Parse the xml
			parseXML(xmlFile, parseableEntry);
						
			//Read in the bytes into a byte array, insert the missing UTF8 BOM if needed and re-save
			File outputFile = new File("output/" + parseableEntry.getOutput());
			writeResult(outputFile, readBytes(outputFile), parseableEntry);
			
			return true;
		} 
		catch (Exception e) 
		{
			String message = e.getMessage();

			notify(prepareEmail(message));
			
			e.printStackTrace();
			
			return false;
		}
	}

	/**
	 * 
	 * @param body
	 * @return
	 */
	private String prepareEmail(final String body) 
	{
		StringBuilder email = new StringBuilder()
		.append("Product feed tool").append(getLineBreak(3))
		.append("Errors ocurred while executing parser process, consult details below: ").append(getLineBreak(2))
		.append(body);
		
		return email.toString();
	}
	
	/**
	 * 
	 * @param sourceFileName
	 * @param parseableEntry 
	 * @throws Exception
	 */
	private void parseXML(final String sourceFileName, ParseableEntry parseableEntry) throws Exception 
	{
		OutputStream outputStream = null;
		
		try 
		{
			outputStream = new FileOutputStream("output/" + parseableEntry.getOutput());
			
			StreamSource source = new StreamSource(sourceFileName);
			
			StreamResult result = new StreamResult(new OutputStreamWriter(outputStream, "UTF-8"));
			
			getTransformerFactory(parseableEntry.getInput()).transform(source, result);
		} 
		catch (Exception cause) 
		{
			StringBuilder message = new StringBuilder()
			.append("Error parsing CSV")
			.append(getLineBreak(2))
			.append("Xsl: ").append(parseableEntry.getInput())
			.append(getLineBreak(1))
			.append("XML: ").append(sourceFileName)
			.append(getLineBreak(1))
			.append("Result: ").append(parseableEntry.getOutput())
			.append(getLineBreak(1))
			.append("Cause:").append(cause.getMessage())
			.append(getLineBreak(2));
			
			throw new Exception(message.toString(), cause);
		} 
		finally 
		{
			close(outputStream);
		}
	}
	
	/**
	 * 
	 * @param outputFile
	 * @return
	 * @throws IOException
	 */
	private byte[] readBytes(File outputFile) throws Exception 
	{
		InputStream encodedFirstPass = null;
		
		try {
			if(!outputFile.exists() || outputFile.length() <= 0) {
				throw new Exception("Output file is empty or doesn't exists.");
			}
			
			// Reopen the file
			encodedFirstPass = new FileInputStream(outputFile);
						
			// Create the byte array to hold the data
			byte[] fileBytes = new byte[(int) outputFile.length()];

			int offset = 0, numRead = 0;
			
			while ((offset < fileBytes.length) && 
					(numRead = encodedFirstPass.read(fileBytes, offset, fileBytes.length - offset)) >= 0) {
				offset += numRead;
			}
			
			// Ensure all the bytes have been read in
			if (offset < fileBytes.length) {
				
				StringBuilder message = new StringBuilder()
				.append("Could not completely read file")
				.append(getLineBreak(2))
				.append("Expected size: ").append(fileBytes.length)
				.append(getLineBreak(1))
				.append("Actual size: ").append(offset)
				.append(getLineBreak(2));
				
				throw new IOException(message.toString());
			}
			
			return fileBytes;
		
		} catch (Exception cause) {
			
			StringBuilder message = new StringBuilder()
			.append("Error reading file bytes")
			.append(getLineBreak(2))
			.append("Outputfile: ").append(outputFile.getName())
			.append(getLineBreak(1))
			.append("Cause: ").append(cause.getMessage())
			.append(getLineBreak(2));
			
			throw new Exception(message.toString(), cause);
		
		} finally {
			close(encodedFirstPass);
		}
	}

	/**
	 * 
	 * @param xslFile
	 * @param outputFile
	 * @param fileBytes
	 * @param parseableEntry 
	 * @param requiresBOM 
	 * @throws IOException 
	 */
	private void writeResult(File outputFile, byte[] fileBytes, ParseableEntry parseableEntry) throws Exception {
		
		byte[] outbytes = null;
		
		FileOutputStream finalOutputStream = null;
		
		try {
			
			//Verifying if the file needs the byte order mark
			if(!parseableEntry.isBOMRequired()) {
				
				//Clone the original file
				outbytes = fileBytes;
			} else {
				
				// create the utf8 byte order mark
				byte[] utf8Bom = {
						(byte) 0xEF, (byte) 0xBB, (byte) 0xBF 
				};
				
				outbytes = new byte[fileBytes.length + utf8Bom.length];
				
				//Copying byte order mark
				System.arraycopy(utf8Bom, 0, outbytes, 0, utf8Bom.length);
				
				//Copying original file
				System.arraycopy(fileBytes, 0, outbytes, utf8Bom.length, fileBytes.length);
			}
		
			finalOutputStream = new FileOutputStream(outputFile);
			
			finalOutputStream.write(outbytes);
			
		} catch (Exception cause) {
			
			StringBuilder message = new StringBuilder()
			.append("Error writing file bytes")
			.append(getLineBreak(2))
			.append("Outputfile: ").append(outputFile.getName())
			.append(getLineBreak(1))
			.append("Cause:").append(cause.getMessage())
			.append(getLineBreak(2));
			
			throw new Exception(message.toString(), cause);
			
		} finally {
			
			close(finalOutputStream);
		}
	}

	/**
	 * @param xslFile
	 * @return
	 * @throws TransformerConfigurationException
	 * @throws TransformerFactoryConfigurationError
	 */
	private Transformer getTransformerFactory(String xslFile) throws TransformerConfigurationException, TransformerFactoryConfigurationError {
		
		StreamSource source = new StreamSource(new File("input/" + xslFile));
		
		Transformer transformerFactory = TransformerFactory.newInstance().newTransformer(source);
		transformerFactory.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

		return transformerFactory;
	}
	
	/**
	 * Closes closeable instances
	 * @param closeable The instance to close
	 */
	private void close(Closeable closeable) {
		if(closeable != null) {
			try {
				closeable.close();
			} catch (Exception e) {}
		}
	}
}