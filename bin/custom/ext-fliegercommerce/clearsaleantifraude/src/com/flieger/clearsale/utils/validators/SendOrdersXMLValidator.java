/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.clearsale.utils.validators;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Antony
 */
public class SendOrdersXMLValidator
{

	public static void validator(String xsdFile, Object object)
	{
		try
		{

			JAXBContext jc = JAXBContext.newInstance(object.getClass());
			JAXBSource source = new JAXBSource(jc, object);

			SchemaFactory sf = SchemaFactory.newInstance(
					XMLConstants.W3C_XML_SCHEMA_NS_URI
			);
			Schema schema = sf.newSchema(new File(xsdFile));

			javax.xml.validation.Validator validator = schema.newValidator();
			validator.setErrorHandler(new ValidatorErrorHandler());
			validator.validate(source);

			System.out.println("foi");
		} catch (SAXException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (JAXBException e)
		{
			e.printStackTrace();
		}
	}

}
