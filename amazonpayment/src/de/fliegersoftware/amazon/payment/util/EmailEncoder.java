package de.fliegersoftware.amazon.payment.util;

import org.apache.commons.codec.net.QuotedPrintableCodec;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;  import org.slf4j.LoggerFactory;

public class EmailEncoder {

	private static final Logger log = LoggerFactory.getLogger(EmailEncoder.class);
	
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