package br.flieger.storecatalogfeedparser.core;

import static br.flieger.storecatalogfeedparser.constants.Constants.PROJECT_PROPERTIES;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import br.flieger.storecatalogfeedparser.ftp.FtpConnector;
import br.flieger.storecatalogfeedparser.parser.ParseableEntry;
import br.flieger.storecatalogfeedparser.parser.Parser;
import br.flieger.storecatalogfeedparser.parser.ParserManager;
import br.flieger.storecatalogfeedparser.parser.XMLUtils;
import br.flieger.storecatalogfeedparser.support.EmailNotifier;


public class FeedGenerator extends EmailNotifier {

	private static final long serialVersionUID = -6877094711482699918L;

	private FtpConnector ftpConnector;

	private Set<String> outputFiles;

	private final Properties properties;
	
	public FeedGenerator() {
		
		outputFiles = new TreeSet<String>();
		ftpConnector = new FtpConnector();
		properties = new Properties();
		
		try {
			properties.load(new FileInputStream(PROJECT_PROPERTIES));
		} catch (Exception e) {
			
			StringBuilder message = new StringBuilder()
			.append("Couldn't load properties, process will exit.")
			.append("\n\n")
			.append("Cause:\n" + getStackTrace(e));
			
			notify(message.toString());
			e.printStackTrace();
			
			System.exit(0);
		}
	}

	/**
	 * generate a single file
	 * 
	 */
	public void process() {
		
		System.out.println("Feed process started");
		
		//Parses all templates and if the processes was successful executed all the files will be sent to FTP
		
		File[] list = new File(properties.getProperty("feed.root.dir")).listFiles();
		
		for(File root : list) {
			System.out.println("root: " + root.getName());
			if(generateOutputFiles(root)) {	
				
				// send to FTP
				uploadAllFilesToFTP();
			}
		}
		
		
		System.out.println("Feed process finished");
	}

	private boolean validateSourceXML(final String sourceXML) {
		File xml = new File(sourceXML);
		
		//If exists
		if(!xml.exists()) {
			
			StringBuilder message = new StringBuilder()
			.append("XML Source invalid").append("\n\n")
			.append("Error detail: ").append("\n")
			.append(sourceXML + " does not exists");
			
			notify(message.toString());
			
			return false;
		}

		//If empty
		if(xml.length() == 0) {
			
			StringBuilder message = new StringBuilder()
			.append("XML Source invalid").append("\n\n")
			.append("Error detail: ").append("\n")
			.append(sourceXML + " is empty");
			
			notify(message.toString());
			
			return false;
		}
		
		//If duplicated product code
		List<String> duplications = XMLUtils.findDuplicatedContent(sourceXML, "code");
		if(!duplications.isEmpty()) {
			
			StringBuilder message = new StringBuilder()
			.append("XML Source invalid").append("\n\n")
			.append("Error detail: ").append("\n")
			.append(sourceXML + " contains duplicated products.").append("\n\n");
			
			for(String duplicated : duplications) {
				message.append(duplicated)
				.append("\n");
			}
			
			notify(message.toString());
			
			return false;
		}
		
		return true;
	}
	
	private boolean generateOutputFiles(final File root) {

		validateSourceXML(new File(root, properties.getProperty("allProducts")).getAbsolutePath());
		validateSourceXML(new File(root, properties.getProperty("baseProducts")).getAbsolutePath());
		
		Iterator<ParseableEntry> iterator = new ParserManager().getParseableEntries(root).iterator();
		
		if(!iterator.hasNext()) {
			String message = "There's no templates configured in 'project.properties', the process will be aborted.";
			
			System.out.println(message);
			
			notify(message);
			
			return false;
		}
		
		for (boolean success = true; iterator.hasNext();) {
			
			ParseableEntry next = iterator.next();
			
			if((success &= parse(next, getXMLSource(root, next.getInput()))) && iterator.hasNext()) {
				continue;
			}
			
			return success;
		}
		
		return false;
	}
	
	private String getXMLSource(File root, final String input) {
		if("chaordic.xsl".equals(input)
			|| "googlemerchant.xsl".equals(input)) {
			return new File(root, properties.getProperty("baseProducts")).getAbsolutePath();
		}
		return new File(root, properties.getProperty("allProducts")).getAbsolutePath();
	}
	
	/**
	 * 
	 * @param parseableEntry
	 * @param xmlSource
	 * @return
	 */
	private boolean parse(ParseableEntry parseableEntry, String xmlSource) {
		
		Parser parser = new Parser();
		
		if(parser.transform(parseableEntry, xmlSource)) {
			System.out.println("[OK] - " + parseableEntry.getOutput());
			outputFiles.add(parseableEntry.getOutput());
			return true;
		}
		
		System.out.println("[ERROR] - " + parseableEntry.getOutput());
		
		return false;
	}
	
	private void uploadAllFilesToFTP() 
	{
		for (String output : outputFiles) 
		{
			ftpConnector.putFile(new File("output/" + output));
		}
	}
	
	private String getStackTrace(final Throwable aThrowable) {
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);

		aThrowable.printStackTrace(printWriter);

		return result.toString();
	}

	public static void main(String[] args) {

		FeedGenerator ur = new FeedGenerator();
		
		ur.process();
	}
}