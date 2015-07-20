/**
 * 
 */
package com.flieger.carrier.dao.impl;

import de.hybris.platform.commerceservices.delivery.dao.impl.DefaultCountryZoneDeliveryModeDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.flieger.carrier.dao.CarrierDeliveryModeDAO;
import com.flieger.carrier.jalo.CarrierZoneDeliveryModeValue;
import com.flieger.carrier.model.CarrierZoneDeliveryModeModel;
import com.flieger.carrier.model.CarrierZoneDeliveryModeValueModel;
import com.flieger.carrier.model.CarrierZoneModel;
import com.flieger.carrier.model.OrderWeightRangeModel;


/**
 * @author franthescollymaneira
 * 
 */
public class DefaultCarrierDeliveryModeDAO extends DefaultCountryZoneDeliveryModeDao implements CarrierDeliveryModeDAO
{
	private Logger LOG = Logger.getLogger(DefaultCarrierDeliveryModeDAO.class);
	
	
	@Override
	public List<CarrierZoneDeliveryModeValueModel> findDeliveryValues(
			List<CarrierZoneModel> zones, 
			double amount, 
			CarrierZoneDeliveryModeModel zoneDeliveryMode, 
			List<OrderWeightRangeModel> weightRanges)
	{
		if(zoneDeliveryMode == null)
		{
			return Collections.emptyList();
		}
		
		String costQuery = 
				" SELECT DISTINCT {val:pk} FROM {CarrierZoneDeliveryModeValue AS val} WHERE "
				+ "{val:deliveryMode} = ?me "
				+ "AND {val:zone} IN (?deliveryZones) "
				+ "AND {val:weightRange} IN (?weightRanges) "
				+ "AND {val:minimum} <= ?amount ";
		
		final FlexibleSearchQuery query = new FlexibleSearchQuery(costQuery);
		query.addQueryParameter("deliveryZones", zones);
		query.addQueryParameter("weightRanges", weightRanges);
		query.addQueryParameter("amount", Double.valueOf(amount));
		query.addQueryParameter("me", zoneDeliveryMode);

		List<CarrierZoneDeliveryModeValueModel> result = getFlexibleSearchService().<CarrierZoneDeliveryModeValueModel>search(query).getResult();
		
		return wrapResult(result);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.flieger.carrier.dao.CarrierDeliveryModeDAO#findDeliveryModes(java.lang.String, double, double)
	 */
	@Override
	public List<CarrierZoneDeliveryModeModel> findDeliveryModes(String postalCode, double weight, final double amount)
	{
		if(hasInvalidArguments(postalCode, amount, weight))
		{
			return Collections.emptyList();
		}
		
		List<CarrierZoneModel> zones = getZonesByPostalcode(postalCode);
		
		if(CollectionUtils.isEmpty(zones))
		{
			return Collections.emptyList();
		}

		List<OrderWeightRangeModel> weightRanges = getWeightRangeByTotalWeight(weight);
		
		if(CollectionUtils.isEmpty(weightRanges))
		{
			return Collections.emptyList();
		}
		
		final String plainQuery = "SELECT {zdm:pk} FROM { "
				+ "CarrierZoneDeliveryModeValue AS val "
				+ "JOIN CarrierZoneDeliveryMode AS zdm ON {val:deliveryMode}={zdm:pk}}  "
				+ "WHERE {zdm:active}=?active "
				+ "AND {val:zone} IN (?deliveryZones) "
				+ "AND {val:weightRange} IN (?weightRanges) "
      		+ "AND {val:minimum} <= ?amount ";
		
		final Map params = new HashMap();
		params.put("deliveryZones", zones);
		params.put("weightRanges", weightRanges);
		params.put("amount", Double.valueOf(amount));
		params.put("active", Boolean.TRUE);

		final FlexibleSearchQuery cheaperQuery = new FlexibleSearchQuery(plainQuery + "ORDER BY {val:value}", params);
		cheaperQuery.setCount(1);

		final FlexibleSearchQuery fasterQuery = new FlexibleSearchQuery(plainQuery + "ORDER BY {val:estimatedDeliveryDays}, {val:value}", params);
		fasterQuery.setCount(1);
		
		final List<CarrierZoneDeliveryModeModel> cheaper = getFlexibleSearchService().<CarrierZoneDeliveryModeModel>search(cheaperQuery).getResult();
		final List<CarrierZoneDeliveryModeModel> faster = getFlexibleSearchService().<CarrierZoneDeliveryModeModel>search(fasterQuery).getResult();
		
		final Set<CarrierZoneDeliveryModeModel> result = new LinkedHashSet<CarrierZoneDeliveryModeModel>();
		result.addAll(cheaper);
		result.addAll(faster);
		
		for(final CarrierZoneDeliveryModeModel r : result)
		{
			LOG.debug(r.getCode());
		}
		
		return wrapResult(result);
	}
	
	@Override
	public List<CarrierZoneDeliveryModeValueModel> findDeliveryValues(
			String postalCode,
			double weight,
			double amount, 
			CarrierZoneDeliveryModeModel zoneDeliveryMode)
	{
		if(zoneDeliveryMode == null || hasInvalidArguments(postalCode, amount, weight))
		{
			return Collections.emptyList();
		}
		
		List<CarrierZoneModel> zones = getZonesByPostalcode(postalCode);
		
		if(CollectionUtils.isEmpty(zones))
		{
			return Collections.emptyList();
		}

		List<OrderWeightRangeModel> weightRanges = getWeightRangeByTotalWeight(weight);
		
		if(CollectionUtils.isEmpty(weightRanges))
		{
			return Collections.emptyList();
		}
		
		String costQuery = 
				" SELECT DISTINCT {val:pk} FROM {CarrierZoneDeliveryModeValue AS val} WHERE "
				+ "{val:deliveryMode} = ?me "
				+ "AND {val:zone} IN (?deliveryZones) "
				+ "AND {val:weightRange} IN (?weightRanges) "
				+ "AND {val:minimum} <= ?amount ";
		
		final FlexibleSearchQuery query = new FlexibleSearchQuery(costQuery);
		query.addQueryParameter("deliveryZones", zones);
		query.addQueryParameter("weightRanges", weightRanges);
		query.addQueryParameter("amount", Double.valueOf(amount));
		query.addQueryParameter("me", zoneDeliveryMode);

		List<CarrierZoneDeliveryModeValueModel> result = getFlexibleSearchService().<CarrierZoneDeliveryModeValueModel>search(query).getResult();
		
		return wrapResult(result);
	}

	/* (non-Javadoc)
	 * @see com.flieger.carrier.dao.CarrierDeliveryModeDAO#getWeightRangeByValues(double, double)
	 */
	@Override
	public OrderWeightRangeModel getWeightRangeByValues(double start, double end)
	{
		final String query = "SELECT {p:PK} FROM {OrderWeightRange AS p} WHERE {p:weightBegin} = ?start AND {p:weightEnd} = ?end ";
		
		final FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query);
		fsQuery.addQueryParameter("start", BigDecimal.valueOf(start));
		fsQuery.addQueryParameter("end", BigDecimal.valueOf(end));

		List<OrderWeightRangeModel> result = getFlexibleSearchService().<OrderWeightRangeModel> search(fsQuery).getResult();
		
		if(CollectionUtils.isNotEmpty(result))
		{
			return result.get(0);
		}
		
		return null;
	}
	
	private List<CarrierZoneModel> getZonesByPostalcode(final String cep)
	{
		final String queryString = "SELECT {p:PK} FROM {CarrierZone AS p} WHERE {p:zipCodeBegin} <= ?cep AND {p:zipCodeEnd} >= ?cep ";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("cep", cep);

		List<CarrierZoneModel> result = getFlexibleSearchService().<CarrierZoneModel> search(query).getResult();
		
		return wrapResult(result);
	}

	private List<OrderWeightRangeModel> getWeightRangeByTotalWeight(final double totalWeight)
	{
		final String queryString = "SELECT {p:PK} FROM {OrderWeightRange AS p} WHERE {p:weightBegin} <= ?weight AND {p:weightEnd} >= ?weight ";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("weight", Double.valueOf(totalWeight));
 
		List<OrderWeightRangeModel> result = getFlexibleSearchService().<OrderWeightRangeModel> search(query).getResult();
		
		if(CollectionUtils.isNotEmpty(result) && result.size() > 1)
		{
			LOG.debug("size: " + result.size() + " weight: " + totalWeight);
			for(OrderWeightRangeModel range : result)
			{
				LOG.debug(range.getPk() + " " + range.getWeightBegin() + " - " + range.getWeightEnd());
			}
		}
		
		return wrapResult(result);
	}
	
	private <T> List<T> wrapResult(Collection<T> result)
	{
		return new ArrayList<T>(result);
	}
	
	private boolean hasInvalidArguments(String postalCode, double amount, double weight)
	{
		if(StringUtils.isBlank(postalCode))
		{
			return true;
		}
		
		if(weight < 0)
		{
			return true;
		}
		
		if(amount <= 0)
		{
			return true;
		}
		
		return false;
	}	
	
	public List<CarrierZoneDeliveryModeValueModel> getCarrierZoneDeliveryModeValueFromCarrierZoneCode(final String code)
	{
		final StringBuilder sb = new StringBuilder("SELECT {czdmv:pk} "
                        				+ "FROM {CarrierZoneDeliveryModeValue AS czdmv "
                        						+ "JOIN CarrierZoneDeliveryMode AS czdm on {czdm:pk} = {czdmv:deliveryMode}"
                        				+ "}"
                        				+ "WHERE {czdm.code} = ?code");

		final FlexibleSearchQuery query = new FlexibleSearchQuery(sb.toString());
		query.addQueryParameter("code", code);
 
		List<CarrierZoneDeliveryModeValueModel> result = getFlexibleSearchService().<CarrierZoneDeliveryModeValueModel> search(query).getResult();
		
		
		return result;
	}
}