package de.kpfamily.services.strategies.pixi.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.pixi.core.strategies.PixiOrderGetShopNoteStrategy;

import de.hybris.platform.core.model.order.OrderModel;
import de.kpfamily.services.products.SerialVoucherProductService;

public class DefaultPixiOrderGetShopNoteStrategy implements PixiOrderGetShopNoteStrategy 
{
	@Resource
	private SerialVoucherProductService serialVoucherProductService;
	
	@Override
	public String getShopNote(OrderModel order) 
	{
		final List<String> soldSerialvouchers = serialVoucherProductService.findSerialVouchersByOrder(order);
		
		if(CollectionUtils.isEmpty(soldSerialvouchers))
		{
			return null;
		}
		
		final StringBuilder note = new StringBuilder("Vouchers bought:\n");
		
		for(final String serialVoucherCode : soldSerialvouchers)
		{
			note.append(serialVoucherCode).append("\n");
		}
		
		return note.toString().trim();
	}
}