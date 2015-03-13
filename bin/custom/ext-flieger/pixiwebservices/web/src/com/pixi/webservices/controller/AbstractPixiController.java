package com.pixi.webservices.controller;

import java.io.StringReader;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pixi.webservices.jaxb.auth.AuthenticationResponse;
import com.pixi.webservices.jaxb.factory.JaxbContextFactory;
import com.pixi.webservices.jaxb.response.GlobalResponse;

import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;

@RequestMapping("/pixi")
public abstract class AbstractPixiController 
{
	protected final Logger LOG = Logger.getLogger(this.getClass());
	
	@Resource
	private JaxbContextFactory jaxbContextFactory;
	
	@Resource
	private ModelService modelService;
	
	@Resource
	private BaseSiteService siteService;
	
	@Resource
	private BaseStoreService baseStoreService;
	
	protected GlobalResponse createGlobalResponse(boolean success, final String msg) 
	{
		final String status = success ? "OK" : "FAILURE";
		
		final GlobalResponse response = new GlobalResponse();
		response.setCode(status);
		response.setMessage(msg);
		
		return response;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T convert(Class<T> type,  final String data) throws JAXBException
	{
		final JAXBContext jaxbContext = getJaxbContextFactory().createJaxbContext(type);
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

	/**
	 * @return the modelService
	 */
	public ModelService getModelService() 
	{
		return modelService;
	}

	/**
	 * @return the siteService
	 */
	public BaseSiteService getSiteService() 
	{
		return siteService;
	}

	/**
	 * @return the baseStoreService
	 */
	public BaseStoreService getBaseStoreService() 
	{
		return baseStoreService;
	}

	/**
	 * @return the jaxbContextFactory
	 */
	public JaxbContextFactory getJaxbContextFactory() 
	{
		return jaxbContextFactory;
	}
}
