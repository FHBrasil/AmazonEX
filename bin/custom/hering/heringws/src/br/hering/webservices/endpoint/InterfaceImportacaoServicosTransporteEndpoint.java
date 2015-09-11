/**
 * 
 */
package br.hering.webservices.endpoint;

import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.util.StandardDateRange;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.Assert;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.hering.webservices.message.delivery.Frete;
import br.hering.webservices.message.delivery.ImportarServicosTransporteRequest;
import br.hering.webservices.message.delivery.ServicoTransporte;

import com.flieger.carrier.dao.CarrierDeliveryModeDAO;
import com.flieger.carrier.model.CarrierZoneDeliveryModeModel;
import com.flieger.carrier.model.CarrierZoneDeliveryModeValueModel;
import com.flieger.carrier.model.CarrierZoneModel;
import com.flieger.carrier.model.OrderWeightRangeModel;
import com.flieger.carrier.services.CarrierZoneDeliveryModeService;



/**
 * @author franthescollymaneira
 *
 */
@Endpoint
public class InterfaceImportacaoServicosTransporteEndpoint extends AbstractInterfaceEndpoint<ImportarServicosTransporteRequest>
{
	@Resource
	private CarrierZoneDeliveryModeService zoneDeliveryModeService;

	@Resource
	private CarrierDeliveryModeDAO countryZoneDeliveryModeDao;

	@Override
	@ResponsePayload
	@PayloadRoot(localPart = "importarServicosTransporteRequest", namespace = namespaceUri)
	public void service(@RequestPayload final ImportarServicosTransporteRequest request)
	{
		try
		{
			LOG.info("importarDadosTransportadora");

			if (request.getServicoTransporte() == null)
			{
				throw new IllegalArgumentException("Servico transporte is empty");
			}

			if (CollectionUtils.isEmpty(request.getFrete()))
			{
				throw new IllegalArgumentException("Frete list is empty");
			}

			prepareCurrentSession(request.getServicoTransporte().getCodigoSite(), false);

			final CarrierZoneDeliveryModeModel mode = findOrCreateCarrier(request.getServicoTransporte());

			final CurrencyModel currency = commonI18NService.getCurrentCurrency();

			final Double min = Double.valueOf(0);

			for (final Frete deliveryFee : request.getFrete())
			{
				final String zipBegin = deliveryFee.getCepInicial();
				final String zipEnd = deliveryFee.getCepFinal();
				validateNumericRange("ZIP", Long.valueOf(zipBegin), Long.valueOf(zipEnd));

				final int weightBegin = deliveryFee.getFaixaPesoInicial();
				final int weightEnd = deliveryFee.getFaixaPesoFinal();
				validateNumericRange("Weight", Integer.valueOf(weightBegin), Integer.valueOf(weightEnd));

				final int deliveryDays = deliveryFee.getPrazoEntrega();
				Assert.isTrue(deliveryDays > 0, "Invallid delivery days: " + deliveryDays);

				final double tollValue = deliveryFee.getValorPedagio();
				final double insuranceValue = deliveryFee.getValorPercentual();
				final double serviceValue = deliveryFee.getValorPeso();

				//				Assert.isTrue(tollValue > 0, "Invallid Toll cost: " + tollValue);
				//				Assert.isTrue(insuranceValue > 0, "Invallid insurance cost: " + insuranceValue);
				Assert.isTrue(serviceValue > 0, "Invallid service cost: " + serviceValue);

				final CarrierZoneModel zone = findOrCreateCarrierZone(zipBegin, zipEnd);

				final OrderWeightRangeModel weightRange = findOrCreateWeightRange(weightBegin, weightEnd);

				final CarrierZoneDeliveryModeValueModel value = findOrderCreateValue(mode, currency, min, zone, weightRange,
						Double.valueOf(serviceValue));

				value.setValue(Double.valueOf(serviceValue));
				value.setTollValue(tollValue);
				value.setInsurancePercentage(insuranceValue);
				value.setEstimatedDeliveryDays(deliveryDays);

				modelService.save(value);
			}
		}
		catch (final Exception e)
		{
			LOG.error("error", e);

			final ServicoTransporte dm = request.getServicoTransporte();
			createErrorMessageList(CHAVE_FRETE + (dm != null ? "=" + dm.getCodigoTransportadora() : ""), e.getMessage());
		}
	}

	/**
	 * @param mode
	 * @param currency
	 * @param min
	 * @param zone
	 * @param weightRange
	 * @param double1
	 * @return
	 */
	private synchronized CarrierZoneDeliveryModeValueModel findOrderCreateValue(final CarrierZoneDeliveryModeModel mode,
			final CurrencyModel currency, final Double min, final CarrierZoneModel zone, final OrderWeightRangeModel weightRange,
			final Double serviceValue)
	{
		try
		{
			return zoneDeliveryModeService.getDeliveryValue(zone, min.doubleValue(), mode, weightRange);
		}
		catch (final UnknownIdentifierException e)
		{
			LOG.info("criando novo value");

			final CarrierZoneDeliveryModeValueModel value = modelService.create(CarrierZoneDeliveryModeValueModel.class);
			value.setZone(zone);
			value.setCurrency(currency);
			value.setMinimum(min);
			value.setDeliveryMode(mode);
			value.setWeightRange(weightRange);
			value.setValue(serviceValue);

			modelService.save(value);

			return value;
		}
	}

	/**
	 *
	 * @param zipBegin
	 * @param zipEnd
	 * @return
	 */
	private synchronized CarrierZoneModel findOrCreateCarrierZone(final String zipBegin, final String zipEnd)
	{
		try
		{
			return (CarrierZoneModel) zoneDeliveryModeService.getZoneForCode(zipBegin + zipEnd);
		}
		catch (final UnknownIdentifierException e)
		{
			final Set<CountryModel> countries = new HashSet<CountryModel>(getCurrentBaseStore().getDeliveryCountries());

			final CarrierZoneModel carrierZone = modelService.create(CarrierZoneModel.class);
			carrierZone.setCode(zipBegin + zipEnd);
			carrierZone.setCountries(countries);
			carrierZone.setZipCodeBegin(zipBegin);
			carrierZone.setZipCodeEnd(zipEnd);

			modelService.save(carrierZone);

			return carrierZone;
		}
	}

	/**
	 *
	 * @param weightBegin
	 * @param weightEnd
	 * @return
	 */
	private synchronized OrderWeightRangeModel findOrCreateWeightRange(final int weightBegin, final int weightEnd)
	{
		final int convertedWeightBegin = weightBegin / 10;
		final int convertedWeightEnd = weightEnd / 10;

		OrderWeightRangeModel range = countryZoneDeliveryModeDao.getWeightRangeByValues(convertedWeightBegin, convertedWeightEnd);

		if (range != null)
		{
			return range;
		}

		range = modelService.create(OrderWeightRangeModel.class);
		range.setWeightBegin(BigDecimal.valueOf(convertedWeightBegin));
		range.setWeightEnd(BigDecimal.valueOf(convertedWeightEnd));

		modelService.save(range);

		return range;
	}

	/**
	 *
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	private synchronized CarrierZoneDeliveryModeModel findOrCreateCarrier(final ServicoTransporte request) throws ParseException
	{
		CarrierZoneDeliveryModeModel carrierMode = null;

		final String codigo = request.getCodigoTransportadora();

		try
		{
			carrierMode = (CarrierZoneDeliveryModeModel) zoneDeliveryModeService.getDeliveryModeForCode(codigo);

			if (!carrierMode.getValues().isEmpty())
			{
				LOG.info("Inicio - Remove CarrierZoneDeliveryModeValue - code: " + codigo);
				List<CarrierZoneDeliveryModeValueModel> list = zoneDeliveryModeService.getCarrierZoneDeliveryModeValueFromCarrierZoneCode(codigo);
				modelService.removeAll(list);
				LOG.info("Fim - Remove CarrierZoneDeliveryModeValue - code: " + codigo);
			}
		}
		catch (final UnknownIdentifierException e)
		{

			carrierMode = modelService.create(CarrierZoneDeliveryModeModel.class);
			carrierMode.setCode(codigo);
			carrierMode.setActive(Boolean.TRUE);
			carrierMode.setNet(Boolean.FALSE);
		}

		carrierMode.setName(request.getNomeTransportadora());
		carrierMode.setStores(Collections.singleton(getCurrentBaseStore()));
		carrierMode.setTransportMode(request.getFormaEnvio());
		carrierMode.setDateRange(getDateRange(request));

		modelService.save(carrierMode);

		return carrierMode;
	}

	/**
	 * @param deliveryCompany
	 * @return
	 * @throws ParseException
	 */
	private StandardDateRange getDateRange(final ServicoTransporte deliveryCompany) throws ParseException
	{
		final Date startTime = parseDate(deliveryCompany.getDataInicialVigencia());
		final Date endTime = parseDate(deliveryCompany.getDataFinalVigencia());

		return new StandardDateRange(startTime, endTime);
	}

	/**
	 *
	 * @param rangeName
	 * @param begin
	 * @param end
	 */
	private void validateNumericRange(final String rangeName, final Number begin, final Number end)
	{
		if ((begin == null) || (end == null) || (end.doubleValue() < begin.doubleValue()))
		{
			throw new IllegalArgumentException("Not a valid " + rangeName + " range: from " + begin + " to " + end);
		}
	}
}