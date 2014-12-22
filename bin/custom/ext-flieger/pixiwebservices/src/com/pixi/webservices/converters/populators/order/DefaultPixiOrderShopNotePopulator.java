package com.pixi.webservices.converters.populators.order;

import org.apache.log4j.Logger;

import com.pixi.webservices.jaxb.order.export.OrderInfo;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DefaultPixiOrderShopNotePopulator implements Populator<OrderModel, OrderInfo> 
{
	private Logger LOG = Logger.getLogger(DefaultPixiOrderShopNotePopulator.class);

	@Override
	public void populate(OrderModel source, OrderInfo target) throws ConversionException 
	{
		LOG.info("populating");
		
		target.setSHOPNOTE("");
		//TODO integrate serial voucher PRODUCTS API
		
//		try {
//			Collection<KPSerialVoucher> soldSerialVouchers = Webfoundation.getInstance().getServices().getVoucherService().getAllKPSerialVouchersFromOrder(orderToExport);				
//			
//			if(soldSerialVouchers != null && !soldSerialVouchers.isEmpty()) {
//				
//				StringBuilder note = new StringBuilder();
//				
//				note.append("Vouchers buyed:\n");
//				for(KPSerialVoucher voucher : soldSerialVouchers) {
//					note.append(voucher.getSerial()).append("\n");
//				}
//				
//				orderData.setSHOP_NOTE(note.toString());
//			} else {
//				orderData.setSHOP_NOTE("");
//			}
//		} catch(Exception e) {
//			
//			log.debug("Error trying to retrieve order serial vouchers: " + orderToExport.getCode(), e);
//			orderData.setSHOP_NOTE("");
//		}
	}
}