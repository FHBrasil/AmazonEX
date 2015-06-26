/**
 * 
 */
package br.hering.facades.populators;

import de.hybris.platform.commercefacades.order.converters.populator.OrderHistoryPopulator;
import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.commercefacades.order.data.OrderHistoryData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Required;

import br.hering.fulfilmentprocess.model.OrderInvoiceModel;
import br.hering.fulfilmentprocess.services.HeringOrderService;
import br.hering.fulfilmentprocess.services.impl.InvalidBoletoException;

/**
 * @author diego
 *
 */ 
public class HeringOrderHistoryPopulator extends OrderHistoryPopulator 
{
	private HeringOrderService heringOrderService;
	private Converter<AbstractOrderEntryModel, OrderEntryData> orderEntryConverter;

	/* (non-Javadoc)
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(OrderModel source, OrderHistoryData target) throws ConversionException
	{
		LinkedList<ConsignmentModel> consignments = new LinkedList<>(source.getConsignments());
		if(!consignments.isEmpty())
		{
			String trackingID = consignments.getLast().getTrackingID();	
			OrderInvoiceModel invoice = consignments.getLast().getInvoice();
		
			target.setDeliveryMode(source.getDeliveryMode().getName());
			target.setTrackingID(trackingID);
			if(invoice != null){
				String chaveNfe = invoice.getKey();
				target.setChaveNfe(chaveNfe);
			}
		}
		
		if(source.getPaymentMode() != null)
		{
			target.setPaymentMode(source.getPaymentMode().getCode());
		}
		target.setBoletoExpired(true);
		if (source.getPaymentMode() != null && "Boleto".equals(source.getPaymentMode().getCode()))
		{
			try
			{
				target.setBoletoExpired(heringOrderService.isBoletoExpired(source));
			}
			catch (InvalidBoletoException e)
			{
				e.printStackTrace();
			}
		}
		
		addEntries(source, target);
		
		super.populate(source, target);
	}

	protected void addEntries(final AbstractOrderModel source, final OrderHistoryData prototype)
	{
		prototype.setPreviewEntries(Converters.convertAll(source.getEntries(), getOrderEntryConverter()));
	}
	
	protected HeringOrderService getHeringOrderService()
	{
		return heringOrderService;
	}

	@Required
	public void setHeringOrderService(HeringOrderService heringOrderService)
	{
		this.heringOrderService = heringOrderService;
	}
	
	@Required
	public void setOrderEntryConverter(final Converter<AbstractOrderEntryModel, OrderEntryData> converter)
	{
		this.orderEntryConverter = converter;
	}
	
	protected Converter<AbstractOrderEntryModel, OrderEntryData> getOrderEntryConverter()
	{
		return this.orderEntryConverter;
	}
}