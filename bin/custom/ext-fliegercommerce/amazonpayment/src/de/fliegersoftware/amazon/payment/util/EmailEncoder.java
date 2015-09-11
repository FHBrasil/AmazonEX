package de.fliegersoftware.amazon.payment.util;

import org.apache.commons.codec.net.QuotedPrintableCodec;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class EmailEncoder {

	private static final Logger log = Logger.getLogger(EmailEncoder.class);
	
	public String encodePlainText(final String text) {
		if(StringUtils.isBlank(text)) {
			return "";
		}
		
		QuotedPrintableCodec cod = new QuotedPrintableCodec();
		
		try {
			return new String(cod.encode(text.getBytes()));
		} catch (Exception e) {
			log.error("Error", e);
			return text;
		}
	}
	
	public String encodeHTML(final String text) {
		if(StringUtils.isBlank(text)) {
			return "";
		}
		
		try {
			return StringEscapeUtils.escapeHtml(text);
		} catch (Exception e) {
			log.error("Error", e);
			return text;
		}
	}
}