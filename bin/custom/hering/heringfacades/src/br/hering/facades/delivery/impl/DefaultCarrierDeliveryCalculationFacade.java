/**
 * 
 */
package br.hering.facades.delivery.impl;

import de.hybris.platform.acceleratorfacades.order.AcceleratorCheckoutFacade;
import de.hybris.platform.commercefacades.order.data.DeliveryModeData;
import de.hybris.platform.commercefacades.order.data.ZoneDeliveryModeData;
import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.deliveryzone.model.ZoneDeliveryModeModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.util.PriceValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import br.hering.facades.delivery.CarrierDeliveryCalculationFacade;

import com.flieger.carrier.constants.CarrierConstants;
import com.flieger.carrier.dao.CarrierDeliveryModeDAO;
import com.flieger.carrier.model.CarrierZoneDeliveryModeModel;
import com.flieger.carrier.services.CarrierDeliveryService;

/**
 * 
 * @author franthescollymaneira
 *
 */
public class DefaultCarrierDeliveryCalculationFacade implements CarrierDeliveryCalculationFacade
{
	private final Logger LOG = Logger.getLogger(DefaultCarrierDeliveryCalculationFacade.class);
	
	@Resource
	private CarrierDeliveryService deliveryService;
	
	@Resource
	private Converter<ZoneDeliveryModeModel, ZoneDeliveryModeData> zoneDeliveryModeConverter;
	
	@Resource
	private PriceDataFactory priceDataFactory;

	@Resource
	protected BaseStoreService baseStoreService;
	
	@Resource
	protected CommonI18NService commonI18NService;
	
	@Resource
	private CarrierDeliveryModeDAO countryZoneDeliveryModeDao;
	
	@Resource
	private SessionService sessionService;
	
	@Resource(name = "acceleratorCheckoutFacade")
	private AcceleratorCheckoutFacade checkoutFacade; 

	/**
	 * 
	 * @param postalCode
	 * @param weight
	 * @param amount
	 * @return
	 */
	private List<? extends DeliveryModeData> getSupportedDeliveryModes(String postalCode, double weight, double amount)
	{
		try
		{
			List<CarrierZoneDeliveryModeModel> modes = countryZoneDeliveryModeDao.findDeliveryModes(postalCode, weight, amount);
			
			if(CollectionUtils.isEmpty(modes))
			{
				return Collections.emptyList();
			}
			
			final List<DeliveryModeData> result = new ArrayList<DeliveryModeData>();
			
			for (final CarrierZoneDeliveryModeModel mode : modes)
			{
				result.add(convert(mode, postalCode, weight, amount));
			}

			return result;
		}
		catch (Exception e)
		{
			LOG.error("error", e);
			return Collections.emptyList();
		}
	}

	/* (non-Javadoc)
	 * @see br.hering.facades.delivery.CarrierDeliveryCalculationFacade#setCheaperDeliveryMode(java.lang.String, double, double)
	 */
	@Override
	public boolean setCheaperDeliveryMode(String postalCode, double weight, double itemsTotalCost)
	{
		final DeliveryModeData deliveryMode = getCheaperDeliveryMode(postalCode, weight, itemsTotalCost);

		if(deliveryMode != null) 
		{
			sessionService.setAttribute(CarrierConstants.SESSION_ATTR_POSTALCODE, postalCode);
			checkoutFacade.setDeliveryMode(deliveryMode.getCode());
			checkoutFacade.setDeliveryAddress(null);
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see br.hering.facades.delivery.CarrierDeliveryCalculationFacade#getCheaperDeliveryMode(java.lang.String, double, double)
	 */
	@Override
	public DeliveryModeData getCheaperDeliveryMode(String postalCode, double weight, double itemsTotalCost)
	{
		LOG.debug("postalcode: " + postalCode + " weight: " + weight + " itemsTotalCost: " + itemsTotalCost);
		
		postalCode = postalCode.replaceAll("-", "");

		List<? extends DeliveryModeData> result = getSupportedDeliveryModes(postalCode, weight, itemsTotalCost);
		
		DeliveryModeData cheaper = getCheaper(result);
		
		if(cheaper == null)
		{
			return null;
		}
		
		checkFreeDeliveryRules(cheaper, itemsTotalCost);
		
		return cheaper;
	}

	/**
	 * 
	 * @param cheaper
	 */
	private void checkFreeDeliveryRules(DeliveryModeData cheaper, double cartSubtotal)
	{
		try
		{
			Double threshold = baseStoreService.getCurrentBaseStore().getFreeDeliveryThreshold();
			
			if(threshold != null && cartSubtotal >= threshold.doubleValue())
			{
				PriceData freeValue = priceDataFactory.create(PriceDataType.BUY, BigDecimal.valueOf(0), cheaper.getDeliveryCost().getCurrencyIso());
				freeValue.setFormattedValue("Gr&aacute;tis");
				
				cheaper.setDeliveryCost(freeValue);
			}
		}
		catch (Exception e)
		{
			LOG.error("error", e);
		}
	}

	/**
	 * @param result
	 * @return 
	 */
	private DeliveryModeData getCheaper(List<? extends DeliveryModeData> result)
	{
		if(CollectionUtils.isEmpty(result))
		{
			return null;
		}
		
		try
		{
			Collections.sort(result, new Comparator<DeliveryModeData>()
			{
				@Override
				public int compare(DeliveryModeData m1, DeliveryModeData m2)
				{
					return m1.getDeliveryCost().getValue().compareTo(m2.getDeliveryCost().getValue());
				}
			});
		}
		catch (Exception e)
		{
			LOG.error("error", e);
		}
		
		return result.iterator().next();
	}
	
	private DeliveryModeData convert(CarrierZoneDeliveryModeModel mode, String postalCode, double weight, double amount)
	{
		final CurrencyModel currency = commonI18NService.getCurrentCurrency();
		final DeliveryModeData data = zoneDeliveryModeConverter.convert(mode);
		
		final PriceValue deliveryCost = deliveryService.getZipDeliveryCostAndDays(mode, amount, currency, postalCode, weight, data);
		
		if (deliveryCost != null)
		{
			PriceData price = priceDataFactory.create(PriceDataType.BUY, BigDecimal.valueOf(deliveryCost.getValue()), deliveryCost.getCurrencyIso());
			data.setDeliveryCost(price);
		}

		return data;
	}
}