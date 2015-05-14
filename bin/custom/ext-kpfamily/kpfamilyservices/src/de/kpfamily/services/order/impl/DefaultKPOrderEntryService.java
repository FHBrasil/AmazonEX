package de.kpfamily.services.order.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import de.hybris.platform.basecommerce.enums.OrderEntryStatus;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.order.impl.DefaultOrderEntryService;
import de.kpfamily.services.order.KPOrderEntryService;

@SuppressWarnings("deprecation")
public class DefaultKPOrderEntryService extends DefaultOrderEntryService implements KPOrderEntryService 
{
	private static final Logger LOG = Logger.getLogger(DefaultKPOrderEntryService.class);

	@Resource
	private EnumerationService enumerationService;
	
	@Override
	public OrderEntryStatus findOrderEntryStatusByExternalCode(final String externalCode) 
	{
		Assert.notNull(externalCode, "External code is null");
		
		List<OrderEntryStatus> values = getEnumerationService().getEnumerationValues(OrderEntryStatus.class);
		
		for(final OrderEntryStatus enumValue : values)
		{
			if(StringUtils.equalsIgnoreCase(externalCode, getExternalCode(enumValue)))
			{
				return enumValue;
			}
		}
		
		return null;
	}

	private String getExternalCode(final OrderEntryStatus enumValue) 
	{
		Assert.notNull(enumValue);
		
		try 
		{
			EnumerationValue ev = getModelService().getSource(enumValue);
			
			String externalCode = (String) ev.getAttribute("externalCode");
			
			return externalCode;
		} 
		catch (final Exception e) 
		{
			LOG.debug("Error finding enum by externalCode for " + enumValue.getCode(), e);
			return null;
		}
	}

	/**
	 * @return the enumerationService
	 */
	public EnumerationService getEnumerationService() 
	{
		return enumerationService;
	}
}