package com.paypal.hybris.commands.impl;


import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.paypal.hybris.service.PaypalService;


/**
 * @author Valentyn Markovych, Gorilla
 * 
 */
@Component
public class AbstractCommandImpl {


protected static final String NOT_SUPPORTED_MESSAGE = "Command is not supported by PayPal extension: ";


@Resource
protected PaypalService paypalService;

@Resource
protected ModelService modelService;


}
