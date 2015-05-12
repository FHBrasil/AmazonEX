/**
 *
 */
package br.hering.webservices.endpoint;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.product.PriceService;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.product.UnitService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.hering.webservices.message.price.ImportarPrecoProdutoRequest;
import br.hering.webservices.message.price.PrecoProduto;


/**
 * @author ghayashi
 *
 */

@Endpoint
public class InterfaceImportacaoPrecoProdutoEndpoint extends AbstractInterfaceEndpoint<ImportarPrecoProdutoRequest>
{
	@Resource
	private ProductService productService;

	@Resource
	private CommonI18NService commonI18NService;

	@Resource
	private UnitService unitService;

	@Resource
	private PriceService priceService;

	@Override
	@ResponsePayload
	@PayloadRoot(localPart = "importarPrecoProdutoRequest", namespace = namespaceUri)
	public void service(@RequestPayload final ImportarPrecoProdutoRequest envelope)
	{
		try
		{
			LOG.info("Iniciando importacão de preços");

			final List<PrecoProduto> listaPrecos = envelope.getPrecoProduto();

			if (CollectionUtils.isEmpty(listaPrecos))
			{
				LOG.warn("Nenhum dado para importação");
				return;
			}

			final CurrencyModel currency = commonI18NService.getCurrency("BRL");
			final UnitModel pieces = unitService.getUnitForCode("pieces");

			for (final PrecoProduto req : listaPrecos)
			{
				try{
   				prepareCurrentSession(req.getCodigoSite(), false);
//   				CatalogVersionModel catalogVersion = getCatalogVersionByStore(req.getCodigoSite(), false);

   				LOG.info("PRECO - PROCURAR PRODUTO - SKU: " + req.getSku());
   				final ProductModel product = productService.getProductForCode(catalogVersion, req.getSku());
   				LOG.info("PRECO - PRODUTO ENCONTRADO");

   				Collection<PriceRowModel> priceList = new ArrayList<PriceRowModel>();

   				final Date dataAtivacao = null;
   				final Date dataFinal = null;
//   				try
//   				{
//   					if (req.getAtivacao() != null && !req.getAtivacao().equals(""))
//   					{
//   						dataAtivacao = parseDate(req.getAtivacao());
//   						dataFinal = parseDate("30000101 01:01:01");// TODO ENDTIME FIXO
//   					}
//   				}
//   				catch (final ParseException e)
//   				{
//   					LOG.error("error", e);
//   					createErrorMessageList(CHAVE_SKU + "=" + req.getSku(), e.getMessage());
//   				}

   				if (req.getPreco() != null)
   				{
   					final PriceRowModel current = createPriceRow(currency, req.getPreco(), pieces, product, catalogVersion, false,
   							dataAtivacao, dataFinal);
/**
 * FIX EMERGENCIAL DIA 22/01/2015:
 *
 * A hering vai mandar apenas preços com datas null e no hybris todos os preços terão datas null para que não seja
 * considerada a possibilidade de usar datarange para os preços. Se um carrinho é criado e logo após o datarange de um produto
 * que está neste carrinho é alterado para uma data posterior a da criação do carrinho, o recalculo do carrinho quebra
 * isto é um bug no hybris, mas esta é uma solução emergencial.
 * */
//   					if (req.getAtivacao() == null || req.getAtivacao().equals(""))
//   					{
   						priceList = new ArrayList(removeBasePriceRow(product.getEurope1Prices(), current));
//   					}
//   					else
//   					{
//   						priceList = new ArrayList(removePromotionPriceRow(product.getEurope1Prices(), current));
//   					}

   					if (priceList.isEmpty() || (priceList.size() != product.getEurope1Prices().size()))
   					{
   						LOG.info("PRECO - SALVAR");
   						modelService.save(current);
   						modelService.refresh(current);
   						priceList.add(current);
   						LOG.info("PRECO - SALVO");
   					}

   					LOG.info("Preço atual: " + product.getCode() + " preço: " + current.getCurrency().getSymbol() + " "
   							+ current.getPrice());
   				}

   				if (req.getPrecoDe() != null)
   				{
   					product.setOldPrice(req.getPrecoDe());
   					LOG.info("Preço anterior: " + product.getCode() + " preço: " + req.getPrecoDe());
   				}

   				if (!priceList.isEmpty())
   				{
   					try
   					{
   						LOG.info("PRECO - PRODUTO- SALVAR");
   						product.setEurope1Prices(priceList);
   						modelService.save(product);
   						LOG.info("PRECO - PRODUTO- SALVO");
   					}
   					catch (final Exception e)
   					{
   						LOG.error("error", e);
   						createErrorMessageList(CHAVE_SKU + "=" + req.getSku(), e.getMessage());
   					}
   				}
   				LOG.info("FIM IMPORTACAO PRECO");
				}
				catch (final Exception e)
				{
					LOG.error("error", e);
					createErrorMessageList(CHAVE_SKU + "=" + req.getSku(), e.getMessage());
				}
			}

		}
		catch (final Exception e)
		{
			LOG.error("ERROR", e);
		}
	}

	private PriceRowModel createPriceRow(final CurrencyModel currency, final BigDecimal preco, final UnitModel unit,
			final ProductModel product, final CatalogVersionModel catalogVersion, final boolean from, final Date startTime,
			final Date endTime)
	{
		final PriceRowModel priceRow = new PriceRowModel();
		priceRow.setCurrency(currency);
		priceRow.setPrice(Double.valueOf(preco.doubleValue()));
		priceRow.setUnit(unit);
		priceRow.setNet(Boolean.TRUE);
		priceRow.setProduct(product);
		priceRow.setCatalogVersion(catalogVersion);
		priceRow.setStartTime(startTime);
		priceRow.setEndTime(endTime);

		return priceRow;
	}

	private Collection<PriceRowModel> removeBasePriceRow(final Collection<PriceRowModel> priceList, final PriceRowModel newPrice)
	{
		final Collection<PriceRowModel> list = new ArrayList<PriceRowModel>();
		list.addAll(priceList);
		for (final Iterator<PriceRowModel> iterator = priceList.iterator(); iterator.hasNext();)
		{
			final PriceRowModel price = iterator.next();
			if (/*price.getStartTime() == null && */ //FIX EMERGENCIAL DIA 22/01/2015 TODO
					!newPrice.getPrice().equals(price.getPrice()))
			{
				list.remove(price);
			}
		}

		return list;
	}

	private Collection<PriceRowModel> removePromotionPriceRow(final Collection<PriceRowModel> priceList, final PriceRowModel newPrice)
	{
		final Collection<PriceRowModel> list = new ArrayList<PriceRowModel>();
		list.addAll(priceList);
		for (final Iterator<PriceRowModel> iterator = priceList.iterator(); iterator.hasNext();)
		{
			final PriceRowModel price = iterator.next();
			if (price.getStartTime() != null && !newPrice.getPrice().equals(price.getPrice()))
			{
				list.remove(price);
			}
		}

		return list;
	}
}