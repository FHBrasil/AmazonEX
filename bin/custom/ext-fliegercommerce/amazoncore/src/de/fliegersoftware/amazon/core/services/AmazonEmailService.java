package de.fliegersoftware.amazon.core.services;

import java.net.MalformedURLException;


public interface AmazonEmailService {
	
	void sendEmailDeclined(String toAddressEmail, String error) throws MalformedURLException;
	
}
