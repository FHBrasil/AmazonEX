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
import de.hybris.platform.stock.exception.InsufficientStockLevelException;
import de.hybris.platform.stock.model.StockLevelHistoryEntryModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.hering.webservices.message.stock.EstoqueProduto;
import br.hering.webservices.message.stock.ImportarEstoqueProdutoRequest;


/**
 * @author ghayashi
 *
 */
@Endpoint
public class InterfaceImportacaoEstoqueProdutoEndpoint extends AbstractInterfaceEndpoint<ImportarEstoqueProdutoRequest>
{
	@Resource
	private ProductService productService;

	@Resource
	private WarehouseService warehouseService;

	@Resource
	private StockService stockService;

	@Override
	@ResponsePayload
	@PayloadRoot(localPart = "importarEstoqueProdutoRequest", namespace = namespaceUri)
	public void service(@RequestPayload final ImportarEstoqueProdutoRequest request)
	{
		try
		{
			//TODO não tem codigoMovimentacao
			//TODO não tem estoqueVirtual
			//TODO não tem permiteVirtual
			//TODO não tem tempoEntregaVirtual
			LOG.info("Iniciando importação de estoque");

			final List<EstoqueProduto> estoqueRequest = request.getEstoqueProduto();

			if (CollectionUtils.isEmpty(estoqueRequest))
			{
				LOG.error("Nenhum dado para importação");
				return;
			}


			final WarehouseModel warehouse = getHeringWarehouse();

			for (final EstoqueProduto saldo : estoqueRequest)
			{
				try
				{
					LOG.info("IMPORTAÇÃO DE ESTOQUE SALDO - INICIO - PRODUTO: " + saldo.getSku());

					prepareCurrentSession(saldo.getCodigoSite(), false);

					final ProductModel product = productService.getProductForCode(catalogVersion, saldo.getSku());
					final int quantity = saldo.getEstoque();

					LOG.info("WarehouseModel: " + warehouse.getCode() + " product: " + product.getCode() + " qty: " + quantity);

					createStockLevel(product, warehouse, quantity);
				}
				catch (final InsufficientStockLevelException e)
				{
					LOG.error("Error", e);
					createErrorMessageList(CHAVE_SKU + "=" + saldo.getSku(), e.getMessage());
				}
			}
		}
		catch (final Exception e)
		{
			LOG.error("ERROR", e);
		}
	}

	/**
	 * @return
	 */
	private WarehouseModel getHeringWarehouse()
	{
		return warehouseService.getWarehouseForCode("hering_estoque_default");
	}

	private void createStockLevel(final ProductModel product, final WarehouseModel warehouse, final int quantity)
			throws InsufficientStockLevelException
	{
		try
		{
			final StockLevelModel stockLevel = stockService.getStockLevel(product, warehouse);

			if (stockLevel == null)
			{
				createNewStockLevel(product, warehouse, quantity);
				return;
			}

			LOG.info("Atual: " + stockLevel.getAvailable());
			LOG.info("Qty: " + quantity);

			final int available = stockLevel.getAvailable();
			//			stockService.updateActualStockLevel(product, warehouse, (available + quantity), "reserved");
			stockLevel.setAvailable((available + quantity));

			modelService.save(stockLevel);
			modelService.refresh(stockLevel);
			
			createStockLevelHistoryEntry(stockLevel, StockLevelUpdateType.WAREHOUSE, stockLevel.getAvailable(),
					"Atualização de estoque - Movimentação");

			LOG.info("Atualizado: " + stockLevel.getAvailable());
		}
		catch (final Exception e)
		{
			LOG.error("error", e);
		}
	}

	private StockLevelModel createNewStockLevel(final ProductModel product, final WarehouseModel warehouse, final int available)
	{

		LOG.info("Creating new stock level");

		final StockLevelModel stockLevel = modelService.create(StockLevelModel.class);
		stockLevel.setAvailable(available);
		stockLevel.setProductCode(product.getCode());
		stockLevel.setWarehouse(warehouse);
		stockLevel.setOverSelling(0);
		stockLevel.setReserved(0);
		stockLevel.setInStockStatus(InStockStatus.NOTSPECIFIED);
		stockLevel.setMaxStockLevelHistoryCount(-1);
		stockLevel.setTreatNegativeAsZero(true);

		final StockLevelHistoryEntryModel entry = createHistoryEntry(stockLevel, available, StockLevelUpdateType.WAREHOUSE,
				"Importação inicial - Inteface Estoque Movimentação");

		final ArrayList<StockLevelHistoryEntryModel> histories = new ArrayList<StockLevelHistoryEntryModel>(
				Collections.singleton(entry));

		stockLevel.setStockLevelHistoryEntries(histories);

		try
		{
			modelService.save(stockLevel);
			modelService.refresh(stockLevel);

		}
		catch (final Exception e)
		{
			LOG.error("error", e);
		}
		return stockLevel;
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
		}

		return historyEntry;
	}
}