package com.paypal.hybris.cronjobs;


import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.paypal.hybris.model.PaypalOperationModel;


/**
 * @author Valentyn Markovych, Gorilla
 * 
 */
@Component
@Scope("tenant")
public class PaypalOperationsHelper {

private final static Logger LOG = Logger
		.getLogger(PaypalOperationsHelper.class);


private static final int ONE_DAY = 1000 * 60 * 60 * 24;


@Resource
private ModelService modelService;


public int makeRandomOperations() {

	LOG.info("Making random PayPaloperations");

	int i = 0;
	for (; i < 5; i++) {
		final PaypalOperationModel m = modelService
				.create(PaypalOperationModel.class);
		m.setCreationtime(new Date(new Date().getTime() - ONE_DAY * i));
		modelService.save(m);
	}

	return i;
}

}
