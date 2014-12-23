package com.pixi.webservices.converters.populators.order;

import org.apache.log4j.Logger;

import com.pixi.webservices.jaxb.order.export.OrderInfo;
import com.pixi.webservices.jaxb.order.export.Payment;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DefaultPixiOrderPaymentInfoPopulator implements Populator<OrderModel, OrderInfo>
{
	private Logger LOG = Logger.getLogger(DefaultPixiOrderPaymentInfoPopulator.class);
	@Override
	public void populate(OrderModel source, OrderInfo target) throws ConversionException 
	{
		//TODO  INTEGRAR CADA MODO DE PAGAMENTO AQUI
		LOG.info("populating");
		
		final Payment payment = new Payment();
		payment.setPAGAMENTOTEMP("pagamento temporario");
		
		target.setPAYMENT(payment);
	}
}
