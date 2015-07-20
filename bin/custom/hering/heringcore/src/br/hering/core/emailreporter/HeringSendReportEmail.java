/**
 * 
 */
package br.hering.core.emailreporter;

/**
 * @author sejunior
 *
 */

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

public class HeringSendReportEmail
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(HeringSendReportEmail.class.getName());

	
	//	Setting Up server configuration
	private static final String strEmailFrom					 = "hering.hybris.application@gmail.com";
	private static final String strEmailReplyTo	 		    = "hering.hybris.application@gmail.com";
	private static final String strAuthenticationUser		 = "hering.hybris.application@gmail.com";
	private static final String strAuthenticationPassword	 = "Hering@123";
	private static final String strSmtpHostName				 = "smtp.gmail.com";
	//private static final String strEmailTo						 = "sejunior@hering.com.br";
	private static final String strEmailTo						 = "w.moeller@fliegersoftware.de";
	private static final String strDefaultSubject			 = "Hering Hybris Stores - Error Report";
	private static final String strSmtpPort	 				 = "465";
	private static final int intSmtpPort	 				 	 = 465;
	private static final boolean boolEnableSSL				 = true;
	
	public HeringSendReportEmail()
	{

	}
	
public void SendEmailReporting(String paramStrSubject, String paramStrHtmlMessage)
{ 
	LOG.info("Starting Error Email Report");
	//HtmlEmail htmlEmail = (HtmlEmail) MailUtils.getPreConfiguredEmail(); 
	//--> Use if local properties are configured only.
	
	HtmlEmail htmlEmail = new HtmlEmail();

	try
	{
		htmlEmail.setFrom(strEmailFrom);
		htmlEmail.addReplyTo(strEmailReplyTo);
		htmlEmail.setSmtpPort(intSmtpPort);
		htmlEmail.setAuthentication(strAuthenticationUser, strAuthenticationPassword);
		htmlEmail.setHostName(strSmtpHostName);
		htmlEmail.setSslSmtpPort(strSmtpPort);
		htmlEmail.setSSL(boolEnableSSL);
		htmlEmail.setSubject(paramStrSubject);
		htmlEmail.setHtmlMsg(paramStrHtmlMessage);
		htmlEmail.addTo(strEmailTo);
		//htmlEmail.addCc("sejunior@hering.com.br");
	}
	catch (EmailException e1)
	{
		LOG.error("Failed to set up Report Email");
	}
	
	
	try
	{		
		htmlEmail.send();
		LOG.info("Email Report --> sucessfull sent");
	}
	catch (Exception e)
	{
		LOG.error("Email Report --> can not be sent");
	}
	

}

public void SendEmailReporting(String paramStrHtmlMessage) 
{ 
	LOG.info("Starting Error Email Report");
	//HtmlEmail htmlEmail = (HtmlEmail) MailUtils.getPreConfiguredEmail(); 
	//--> Use if local properties are configured only.
	HtmlEmail htmlEmail = new HtmlEmail();
	
	try
	{
   	htmlEmail.setFrom(strEmailFrom);
   	htmlEmail.addReplyTo(strEmailReplyTo);
   	htmlEmail.setSmtpPort(intSmtpPort);
   	htmlEmail.setAuthentication(strAuthenticationUser, strAuthenticationPassword);
   	htmlEmail.setHostName(strSmtpHostName);
   	htmlEmail.setSslSmtpPort(strSmtpPort);
   	htmlEmail.setSSL(boolEnableSSL);
   	htmlEmail.setSubject(strDefaultSubject);
   	htmlEmail.setHtmlMsg(paramStrHtmlMessage);
   	htmlEmail.addTo(strEmailTo);
   	htmlEmail.addCc("sejunior@hering.com.br");
	}
	catch (EmailException e1)
	{
		LOG.error("Failed to set up Report Email");
	}
	
	try
	{		
		htmlEmail.send();
		LOG.info("Email Report --> sucessfull sent");
	}
	catch (Exception e)
	{
		LOG.error("Email Report -->  can not be sent");
	}

}
}
