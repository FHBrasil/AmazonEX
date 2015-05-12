/**
 * 
 */
package com.paypal.hybris.parser;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


/**
 * @author Christina Romashchenko
 * 
 */
public class XmlParser {

private static final Logger LOG = Logger.getLogger("XmlParser");


public static String findFormFromXML(final String str, final String nameOfForm) {


	String form = null;


	final String pattern = "(?ims)<form[^>]*name=\"" + nameOfForm
			+ "\"[^>]*>(.*?)</form>";

	// Create a Pattern object
	final Pattern r = Pattern.compile(pattern);

	// Now create matcher object.
	final Matcher m = r.matcher(str);
	if (m.find()) {
		form = m.group();
	} else {
		LOG.info("Xml file wasn't parsed correct!!!");
	}

	return form;
}


public static List<String> findInputFieldsFromXML(final String str) {

	final List<String> list = new ArrayList<String>();


	final String pattern = "(?ims)<input[^>](.*?)>";

	// Create a Pattern object
	final Pattern r = Pattern.compile(pattern);

	// Now create matcher object.
	final Matcher m = r.matcher(str);
	while (m.find()) {
		final String line = m.group();
		list.add(line);
	}

	return list;
}


public static String getInfFromField(final String str, final String field) {

	String name = "";


	final String pattern = "(?ims)" + field + "=\"[^>](.*?)\"";

	// Create a Pattern object
	final Pattern r = Pattern.compile(pattern);

	// Now create matcher object.
	final Matcher m = r.matcher(str);
	if (m.find()) {
		final String line = m.group().replaceAll(field + "=\"", "");
		name = line.replaceAll("\"", "");
	}

	return name;
}


public static String getParametrFromUrl(final String query, final String par) {

	final String[] params = query.split("&");
	final Map<String, String> map = new HashMap<String, String>();
	for (final String param : params) {
		final String[] spl = param.split("=");
		final String name = spl[0];
		final String value = spl[1];
		map.put(name, value);
	}
	return map.get(par);
}


}
