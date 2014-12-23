package com.pixi.webservices.controller;

import java.io.StringReader;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.pixi.webservices.dto.SampleResponse;
import com.pixi.webservices.jaxb.auth.AuthenticationResponse;
import com.pixi.webservices.jaxb.factory.JaxbContextFactory;

@RequestMapping("/pixi")
public abstract class AbstractPixiController 
{
	@Resource
	private JaxbContextFactory jaxbContextFactory;
	
	/**
	 * @param action
	 * @return
	 */
	protected SampleResponse getResponse(final String action)
	{
		final SampleResponse sr = new SampleResponse();
		sr.setAction(this.getClass().getSimpleName() + " - " + action);
		sr.setDate(new Date());
		sr.setUid(UUID.randomUUID().toString());

		return sr;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T convert(Class<T> type,  final String data) throws JAXBException
	{
		final JAXBContext jaxbContext = jaxbContextFactory.createJaxbContext(type);
		final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		
		return (T) jaxbUnmarshaller.unmarshal(new StringReader(data));
	}
	
	protected AuthenticationResponse getUnknownActionResponse()
	{
		final AuthenticationResponse response = new AuthenticationResponse();
		response.setSTATUS("Error");
		response.setDESCRIPTION("unknown action defined");

		return response;
	}
}
