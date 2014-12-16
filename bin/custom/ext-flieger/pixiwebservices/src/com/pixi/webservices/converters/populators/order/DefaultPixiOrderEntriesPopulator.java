package com.pixi.webservices.converters.populators.order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.pixi.webservices.jaxb.order.export.Order;
import com.pixi.webservices.jaxb.order.export.OrderEntryPrice;
import com.pixi.webservices.jaxb.order.export.OrderItem;

import de.hybris.platform.catalog.enums.ArticleStatus;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.util.Config;

public class DefaultPixiOrderEntriesPopulator implements Populator<OrderModel, Order>
{
	private Logger LOG = Logger.getLogger(DefaultPixiOrderEntriesPopulator.class);
	
	@Override
	public void populate(OrderModel source, Order target) throws ConversionException 
	{
		LOG.info("populating");
		
		List<OrderItem> orderitems = target.getORDERITEM();

		for(AbstractOrderEntryModel entry : source.getEntries())
		{
			final OrderItem orderItem = new OrderItem();
			orderItem.setDESCRIPTIONSHORT(entry.getProduct().getName());
			orderItem.setLINEITEMID(entry.getPk().getLongValueAsString());
			orderItem.setORDERUNIT(entry.getUnit().getName());
			orderItem.setQUANTITY(entry.getQuantity().intValue());
			orderItem.setSUPPLIERAID(entry.getProduct().getCode());
			orderItem.setITEMNOTE(getItemNote(entry.getProduct()));
			orderItem.setARTICLEPRICE(createArticlePrice(entry));
			
			orderitems.add(orderItem);
		}
	}
	
	private String getItemNote(ProductModel product) 
	{
		StringBuilder note = new StringBuilder(product.getCode());
		
		Map<ArticleStatus, String> articleStatus = product.getArticleStatus();
		
		if(MapUtils.isNotEmpty(articleStatus))
		{
			note.append("-").append(StringUtils.join(articleStatus.values(), "-"));
		}
		
		//TODO integrate shipping method in the future
//		String dfVerfahren = (String)entry.getProduct().getAttribute("shippingMethod");
//		if (dfVerfahren != null && !"".equals(dfVerfahren)) {
//			infoModified += "-" + dfVerfahren;
//		}
		
		return note.toString();
	}

	private OrderEntryPrice createArticlePrice(AbstractOrderEntryModel entry) 
	{
		final OrderEntryPrice articlePrice = new OrderEntryPrice();
		articlePrice.setDISCOUNTPERC(BigDecimal.ZERO);
		articlePrice.setDISCOUNTVALUE(BigDecimal.ZERO);
		articlePrice.setFULLPRICE(getFullPrice(entry));
		articlePrice.setPRICEAMOUNT(getBasePrice(entry));
		articlePrice.setType(Config.getParameter("pixiwebservices.order.item.price.type"));
		
		return articlePrice;
	}

	private BigDecimal getBasePrice(AbstractOrderEntryModel entry) 
	{
		if(BooleanUtils.isTrue(entry.getGiveAway()))
		{
			return BigDecimal.ZERO;
		}
		
		return new BigDecimal(entry.getBasePrice().toString());
	}	

	private BigDecimal getFullPrice(AbstractOrderEntryModel entry) 
	{
		if(BooleanUtils.isTrue(entry.getGiveAway()))
		{
			return BigDecimal.ZERO;
		}
		
		return new BigDecimal(entry.getTotalPrice().toString());
	}	
}