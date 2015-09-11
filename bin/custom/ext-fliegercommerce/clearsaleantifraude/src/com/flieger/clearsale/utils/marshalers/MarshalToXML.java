/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.clearsale.utils.marshalers;

import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author Antony
 */
public class MarshalToXML
{

	public static String marshal(Object obj) throws JAXBException
	{

		if (obj == null)
		{
			return "";
		}
		JAXBContext jaxbContext = null;
		// marshal XML
		try
		{
			jaxbContext = JAXBContext.newInstance(obj.getClass());
		}
		catch (final JAXBException e)
		{
			e.printStackTrace();
		}

		// marshal to String
		Marshaller marshaller = null;
		try
		{
			marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		}
		catch (final JAXBException e)
		{
			e.printStackTrace();
		}

		final StreamResult streamResult = new StreamResult(new StringWriter());

		try
		{
			marshaller.marshal(obj, streamResult);
		}
		catch (final JAXBException e)
		{
			e.printStackTrace();
		}

		final StringWriter sw = (StringWriter) streamResult.getWriter();
		final StringBuffer sb = sw.getBuffer();
		final String marshalledObj = sb.toString();
		return marshalledObj;
	}

}
