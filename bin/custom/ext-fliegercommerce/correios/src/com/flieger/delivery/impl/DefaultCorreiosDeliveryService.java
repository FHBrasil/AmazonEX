/**
 * 
 */
package com.flieger.delivery.impl;

import de.hybris.platform.core.model.enumeration.EnumerationValueModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.util.PriceValue;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.flieger.data.CorreiosPropertiesData;
import com.flieger.delivery.CorreiosDeliveryService;
import com.flieger.enums.CorreiosCodigoServico;
import com.flieger.enums.FormatoDaEncomenda;
import com.flieger.enums.MaoPropriaAvisoRecebimento;
import com.flieger.model.CorreiosDeliveryModeModel;
import com.flieger.webservice.CalculoFreteCorreio;


/**
 * @author alexandresantos
 * 
 */
public class DefaultCorreiosDeliveryService implements CorreiosDeliveryService
{
	protected static final Logger LOG = Logger.getLogger(DefaultCorreiosDeliveryService.class);

	private CartService cartService;
	private CorreiosDeliveryModeModel correiosDeliveryModeModel;
	private TypeService typeService;
	private ModelService modelService;

	@SuppressWarnings(
	{ "deprecation", "null" })
	@Override
	public PriceValue calculateDeliveryCosts(final AbstractOrderModel order, final double peso) throws ModelSavingException
	{
		correiosDeliveryModeModel = (CorreiosDeliveryModeModel) order.getDeliveryMode();
		final CorreiosCodigoServico servico = correiosDeliveryModeModel.getServico();
		final EnumerationValueModel model = typeService.getEnumerationValue(servico);
		final String codigo = model.getName();

		if (order != null && order.getDeliveryAddress() != null)
		{
			Assert.notNull(order.getDeliveryAddress().getPostalcode(), "Zip code of the recipient not found");

			CorreiosPropertiesData correio = new CorreiosPropertiesData();

			correio.setNCdEmpresa("");
			correio.setSDsSenha("");
			correio.setNCdServico(codigo);
			correio.setSCepOrigem("80250070");
			correio.setSCepDestino(order.getDeliveryAddress().getPostalcode());
			correio.setNVlPeso(String.valueOf(peso));
			correio.setNCdFormato(FormatoDaEncomenda.FORMATO_CAIXA_PACOTE.getValor());
			correio.setNVlComprimento("20");
			correio.setNVlAltura("5");
			correio.setNVlLargura("15");
			correio.setNVlDiametro("0");
			correio.setSCdMaoPropria(MaoPropriaAvisoRecebimento.NAO.getCodigo());
			correio.setNVlValorDeclarado("200");
			correio.setSCdAvisoRecebimento(MaoPropriaAvisoRecebimento.NAO.getCodigo());

			logCorreios("Correios request", correio);

			final CalculoFreteCorreio calculo = new CalculoFreteCorreio();
			correio = calculo.calcular(correio);

			logCorreios("Correios response", correio);

			try
			{
				if (correio != null)
				{
					final Number valor = parseValue(correio.getStrRetorno().get("Valor"));
					if (valor.doubleValue() > 0)
					{
						return new PriceValue("EUR", valor.doubleValue(), false);
					}
				}
			}
			catch (final Exception e)
			{
				LOG.error("error at correios", e);
			}

			return cleanDeliveryInfo(order);
		}

		return null;
	}

	private Number parseValue(final String value) throws ParseException
	{
		final Locale LOCAL = new Locale("pt", "BR");
		final DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(LOCAL));
		return df.parse(value);
	}

	/**
	 * 
	 */
	private PriceValue cleanDeliveryInfo(final AbstractOrderModel order)
	{
		order.setDeliveryMode(null);
		modelService.save(order);
		return null;
	}

	/**
	 * 
	 */
	private void logCorreios(final String label, final CorreiosPropertiesData correio)
	{
		if (correio == null)
		{
			LOG.error("correios response is null");
			return;
		}

		MapUtils.verbosePrint(System.out, label, correio.getStrRetorno());
	}

	/**
	 * @return the cartService
	 */
	public CartService getCartService()
	{
		return cartService;
	}

	/**
	 * @param cartService
	 *           the cartService to set
	 */
	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
	}

	/**
	 * @return the typeService
	 */
	public TypeService getTypeService()
	{
		return typeService;
	}

	/**
	 * @param typeService
	 *           the typeService to set
	 */
	public void setTypeService(final TypeService typeService)
	{
		this.typeService = typeService;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}
}
