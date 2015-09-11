/**
 * 
 */
package br.flieger.storecatalogfeedparser.support;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author franthescolly
 *
 */
public abstract class EmailNotifier implements Notifiable {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 2057110387345160039L;

	/**
	 * E-mail recipients
	 */
	private final Set<InternetAddress> recipients;
	
	/**
	 * Default constructor
	 */
	public EmailNotifier() {
		recipients = new LinkedHashSet<InternetAddress>();
		addRecipient("franthescolly.maneira@gmail.com");
	}

	/* (non-Javadoc)
	 * @see br.flieger.storecatalogfeedparser.support.Notifiable#notify(java.lang.String)
	 */
	@Override
	public void notify(final String message) 
	{
		try
		{
			System.out.println(message);
			
			// create a message
			Message msg = prepareEmail(); 

			// set the to address
			msg.setRecipients(Message.RecipientType.TO, recipients.toArray(new Address[recipients.size()]));

			// Setting the Subject and Content Type
			msg.setSubject("Product feed status");
			msg.addHeader("Content-Type", "text/plain; charset=ISO-8859-15");
			msg.setContent(message, "text/plain; charset=ISO-8859-15");
			msg.saveChanges();
			
			Transport.send(msg);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}	
	}
	
	protected MimeMessage prepareEmail()
	{
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
//		props.put("mail.smtp.server", "smtp.gmail.com");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.mime.charset", "ISO-8859-15");
//		props.put("mail.use.tls", "true");
		
        props.put("mail.smtp.auth", "true");  
		props.put("mail.smtp.starttls.enable", "true");  
        props.put("mail.smtp.socketFactory.port", "465");  
        props.put("mail.smtp.socketFactory.fallback", "false");  
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		Session session = Session.getDefaultInstance(props, new GMailAuthenticator("email.server.teste.local@gmail.com", "33933393"));
		MimeMessage msg = new MimeMessage(session); 
		
		try {
			msg.setFrom(new InternetAddress("f.maneira@fliegersoftware.de", "Support Team"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}
	
	/**
	 * 
	 * @param recipientToAdd
	 */
	protected void addRecipient(final String... recipientsToAdd) {
		
		for(String recipient : recipientsToAdd) {			
			try {
				recipients.add(new InternetAddress(recipient));
			} catch (AddressException e) { 
				System.out.println("Error adding recipient " + recipient); 
			}
		}
	}
	
	/**
	 * 
	 * @param recipientToRemove
	 */
	protected void removeRecipient(final String... recipientsToRemove) {
		
		outer: 
		for(String recipient : recipientsToRemove) {
			
			for(Iterator<InternetAddress> i = recipients.iterator(); i.hasNext();) {
				
				if(i.next().getAddress().equals(recipient)) {
					recipients.remove(recipient); 
					continue outer;
				}
			}
		}
	}
	
	protected String getLineBreak(final int total) 
	{
		StringBuilder breaks = new StringBuilder();
		
		for (int i = 0; i < total; i++) 
		{
			breaks.append("\n");
		}
		
		return breaks.toString();
	}
	
	class GMailAuthenticator extends Authenticator {
	     String user;
	     String pw;
	     public GMailAuthenticator (String username, String password)
	     {
	        super();
	        this.user = username;
	        this.pw = password;
	     }
	    public PasswordAuthentication getPasswordAuthentication()
	    {
	       return new PasswordAuthentication(user, pw);
	    }
	}
}