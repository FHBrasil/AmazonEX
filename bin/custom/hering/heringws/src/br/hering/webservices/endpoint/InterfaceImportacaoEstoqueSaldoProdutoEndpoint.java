/**
 *
 */
package br.hering.webservices.endpoint;

import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.basecommerce.enums.StockLevelUpdateType;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.stock.model.StockLevelHistoryEntryModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.hering.webservices.message.stock.level.EstoqueSaldoProduto;
import br.hering.webservices.message.stock.level.ImportarEstoqueSaldoProdutoRequest;


/**
 * @author ghayashi
 * @author franthescollymaneira
 *
 */

@Endpoint
public class InterfaceImportacaoEstoqueSaldoProdutoEndpoint extends AbstractInterfaceEndpoint<ImportarEstoqueSaldoProdutoRequest>
{
	@Resource
	private ProductService productService;

	@Resource
	private WarehouseService warehouseService;

	@Resource
	private StockService stockService;

	@Override
	@ResponsePayload
	@PayloadRoot(localPart = "importarEstoqueSaldoProdutoRequest", namespace = namespaceUri)
	public void service(@RequestPayload final ImportarEstoqueSaldoProdutoRequest request)
	{
		try
		{
			LOG.info("Iniciando importação de saldo de estoque  ");

			final List<EstoqueSaldoProduto> estoqueRequest = request.getEstoqueSaldoProduto();

			if (CollectionUtils.isEmpty(estoqueRequest))
			{
				LOG.error("Nenhum dado para importaçãoo");
				return;
			}

			final WarehouseModel warehouse = getHeringWarehouse();

			for (final EstoqueSaldoProduto saldo : estoqueRequest)
			{
				LOG.info("IMPORTAÇÃO DE ESTOQUE SALDO - INICIO - PRODUTO: " + saldo.getSku());
				prepareCurrentSession(saldo.getCodigoSite(), false);

				final ProductModel product = productService.getProductForCode(catalogVersion, saldo.getSku());
				final int available = saldo.getEstoque();

				LOG.info("WarehouseModel: " + warehouse.getCode() + " product: " + product.getCode() + " qty available: " + available);

				createStockLevel(product, warehouse, available);
			}
		}
		catch (final Exception e)
		{
			LOG.error("ERROR", e);
		}
	}

	private StockLevelModel createStockLevel(final ProductModel product, final WarehouseModel warehouse, final int available)
	{
		try
		{
			StockLevelModel stockLevel = stockService.getStockLevel(product, warehouse);
			String comment;
			final List<StockLevelHistoryEntryModel> historyEntries = new ArrayList<StockLevelHistoryEntryModel>();

			if (stockLevel != null)
			{
				comment = "Atualização de estoque - Saldo";

				historyEntries.addAll(stockLevel.getStockLevelHistoryEntries());
			}
			else
			{
				comment = "Importação inicial - Saldo";

				stockLevel = modelService.create(StockLevelModel.class);
				stockLevel.setProductCode(product.getCode());
				stockLevel.setWarehouse(warehouse);
				stockLevel.setOverSelling(0);
				stockLevel.setReserved(0);
				stockLevel.setInStockStatus(InStockStatus.NOTSPECIFIED);
				stockLevel.setMaxStockLevelHistoryCount(-1);
				stockLevel.setTreatNegativeAsZero(true);
			}

			stockLevel.setAvailable(available);

			final StockLevelHistoryEntryModel entry = createHistoryEntry(stockLevel, available, StockLevelUpdateType.WAREHOUSE,
					comment);

			historyEntries.add(entry);

			stockLevel.setStockLevelHistoryEntries(historyEntries);

			modelService.save(stockLevel);

			return stockLevel;
		}
		catch (final Exception e)
		{
			LOG.error("error", e);
			createErrorMessageList(CHAVE_SKU + "=" + product.getCode(), e.getMessage());
		}

		return null;
	}

	private StockLevelHistoryEntryModel createHistoryEntry(final StockLevelModel stockLevel, final int actual,
			final StockLevelUpdateType updateType, final String comment)
	{

		final StockLevelHistoryEntryModel historyEntry = modelService.create(StockLevelHistoryEntryModel.class);
		historyEntry.setStockLevel(stockLevel);
		historyEntry.setActual(actual);
		historyEntry.setUpdateType(updateType);
		historyEntry.setComment(comment);
		historyEntry.setUpdateDate(new Date());

		try
		{
			modelService.save(historyEntry);
		}
		catch (final Exception e)
		{
			LOG.error("error", e);
			createErrorMessageList(CHAVE_SKU + "=" + stockLevel.getProductCode(), e.getMessage());
		}
		return historyEntry;
	}

	/**
	 * @return
	 */
	private WarehouseModel getHeringWarehouse()
	{
		return warehouseService.getWarehouseForCode("hering_estoque_default");
	}
}