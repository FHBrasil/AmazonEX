package com.pixi.webservices.util;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.pixi.webservices.jaxb.adapter.BooleanAdapter;
import com.pixi.webservices.jaxb.adapter.DateAdapter;
import com.pixi.webservices.jaxb.adapter.StringAdapter;
import com.pixi.webservices.jaxb.factory.MoxyJaxbContextFactoryImpl;

public class PixiXMLUtil 
{
	public static void printXML(Object obj)
	{
		System.out.println(getXML(obj));
	}
	
	public static String getXML(Object obj)
	{
		final StringWriter writerOutput = new StringWriter();
		
		try 
		{
			MoxyJaxbContextFactoryImpl jaxbContextFactory = getFactory();
			
			final JAXBContext jaxbContext = jaxbContextFactory.createJaxbContext(obj.getClass());
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			marshaller.marshal(obj, writerOutput);
			
			return writerOutput.toString();
		} 
		catch (JAXBException e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}

	public static MoxyJaxbContextFactoryImpl getFactory() 
	{
		List<Class> typeAdapters = new ArrayList<Class>();
		typeAdapters.add(DateAdapter.class);
		typeAdapters.add(StringAdapter.class);
		typeAdapters.add(BooleanAdapter.class);
		
		MoxyJaxbContextFactoryImpl jaxbContextFactory = new MoxyJaxbContextFactoryImpl();
		jaxbContextFactory.setWrapCollections(false);
		jaxbContextFactory.setTypeAdapters(typeAdapters );
		
		return jaxbContextFactory;
	}
}
