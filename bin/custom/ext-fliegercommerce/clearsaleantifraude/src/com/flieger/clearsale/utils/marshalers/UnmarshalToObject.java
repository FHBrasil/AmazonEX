/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.clearsale.utils.marshalers;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;


/**
 * 
 * @author Antony
 */
public class UnmarshalToObject
{

	public static Object unmarshal(final Class clazz, final String stringXml)
	{

		JAXBContext context = null;
		try
		{
			context = JAXBContext.newInstance(clazz);
			final Unmarshaller unmarshaller = context.createUnmarshaller();
			return unmarshaller.unmarshal(new StreamSource(new StringReader(stringXml)));
		}
		catch (final JAXBException e)
		{
			//
		}
		return null;
	}

}
