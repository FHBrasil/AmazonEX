package de.fliegersoftware.amazon.core.services.impl;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

import de.fliegersoftware.amazon.core.services.AmazonEmailService;
import de.hybris.platform.util.localization.Localization;
import de.hybris.platform.util.mail.MailUtils;

public class DefaultAmazonEmailService implements AmazonEmailService {
	private static final Logger LOG = Logger.getLogger(DefaultAmazonEmailService.class);
	public static final String EMAILSERVICE_SEND_ENABLED_CONFIG_KEY = "emailservice.send.enabled";
	
	public void sendEmailDeclined(String toAddressEmail, String error) throws MalformedURLException {
		
		try {
			HtmlEmail htmlEmail = (HtmlEmail) MailUtils.getPreConfiguredEmail();
			
			htmlEmail.addTo(toAddressEmail);
			htmlEmail.setSSL(true);
			
			String strPathUrlLogo = Localization.getLocalizedString("amazon.email.logo");
			URL urlLogo = new URL(strPathUrlLogo);  
	        String cidLogo = htmlEmail.embed(urlLogo, "Logo"); 
			if (error.equals("InvalidPaymentMethod")) {
				sendEmailSoftDeclined(htmlEmail, cidLogo);
			} else if (error.equals("AmazonRejected")) {
				sendEmailHardDeclined(htmlEmail, cidLogo);
			}
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	public void sendEmailSoftDeclined(HtmlEmail htmlEmail, String cidLogo) throws EmailException {
		htmlEmail.setSubject(Localization.getLocalizedString("amazon.email.authorization.soft.decline.subject"));
		
		String strMessage = Localization.getLocalizedString("amazon.email.authorization.decline.greeting");
		strMessage += "<br /><br />";
		strMessage += Localization.getLocalizedString("amazon.email.authorization.soft.decline.thankyou") + " " + Localization.getLocalizedString("amazon.email.shopname");
		strMessage += "<br />";
		strMessage += Localization.getLocalizedString("amazon.email.authorization.soft.decline.paymentdeclined");
		strMessage += "<br />";
		strMessage += Localization.getLocalizedString("amazon.email.authorization.soft.decline.instruction");
		strMessage += "<br /><br />";
		strMessage += Localization.getLocalizedString("amazon.email.authorization.decline.bye");
		strMessage += "<br /><br />";
		strMessage += Localization.getLocalizedString("amazon.email.shopname");
		strMessage += "<br />";
		strMessage += Localization.getLocalizedString("amazon.email.contactdetails");
		strMessage += "<br />";
		
		
		htmlEmail.setHtmlMsg(mountHtmlEmail(cidLogo, strMessage));
		
		htmlEmail.send();
	}
	
	public void sendEmailHardDeclined(HtmlEmail htmlEmail, String cidLogo) throws EmailException, MalformedURLException {
		
		htmlEmail.setSubject(Localization.getLocalizedString("amazon.email.authorization.hard.decline.subject"));
		
		String strMessage = Localization.getLocalizedString("amazon.email.authorization.decline.greeting");
		strMessage += "<br /><br />";
		strMessage += Localization.getLocalizedString("amazon.email.authorization.hard.decline.paymentdeclined");
		strMessage += "<br />";
		strMessage += Localization.getLocalizedString("amazon.email.authorization.hard.decline.instruction");
		strMessage += "<br /><br />";
		strMessage += Localization.getLocalizedString("amazon.email.authorization.decline.bye");
		strMessage += "<br /><br />";
		strMessage += Localization.getLocalizedString("amazon.email.shopname");
		strMessage += "<br />";
		strMessage += Localization.getLocalizedString("amazon.email.contactdetails");
		strMessage += "<br />";
		
		htmlEmail.setHtmlMsg(mountHtmlEmail(cidLogo, strMessage));

		htmlEmail.send();
	}
	
	private String mountHtmlEmail(String cidLogo, String text) {
    	String html = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>"
    			+ "<html xmlns='http://www.w3.org/1999/xhtml'>"
    			+ "<head>"
    			+ "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"
    			+ "</head>"
    			+ "<body>"
    			+ "<div>"
    			+ text
    		/*	+ "<img src=\"cid:"+cidLogo+"\" width='222' height='63' />"*/
    			+ "</div>"
    			+ "</body>"
    			+ "</html>";
    	
    	return html;
    }
	
}
