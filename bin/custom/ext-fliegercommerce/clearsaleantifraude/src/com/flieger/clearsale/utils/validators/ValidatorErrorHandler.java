/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.clearsale.utils.validators;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


/**
 * 
 * @author Antony
 */
public class ValidatorErrorHandler implements ErrorHandler
{

	@Override
	public void warning(final SAXParseException exception) throws SAXException
	{
		//
	}

	@Override
	public void error(final SAXParseException exception) throws SAXException
	{
		//
	}

	@Override
	public void fatalError(final SAXParseException exception) throws SAXException
	{
		//
	}
}
