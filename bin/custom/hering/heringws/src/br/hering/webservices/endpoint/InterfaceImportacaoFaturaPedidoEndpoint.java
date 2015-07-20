/**
 *
 */
package br.hering.webservices.endpoint;

import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.basecommerce.enums.StockLevelUpdateType;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.enums.ExportStatus;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.ordersplitting.ConsignmentCreationException;
import de.hybris.platform.ordersplitting.ConsignmentService;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.stock.exception.InsufficientStockLevelException;
import de.hybris.platform.store.BaseStoreModel;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.flieger.exacttarget.events.OrderCancelledEvent;
import br.flieger.exacttarget.events.OrderDeliveredEvent;
import br.flieger.exacttarget.events.OrderDispatchedEvent;
import br.flieger.exacttarget.events.OrderInvoiceEvent;
import br.flieger.exacttarget.events.OrderPayedEvent;
import br.hering.fulfilmentprocess.model.OrderInvoiceModel;
import br.hering.webservices.message.order.balance.FaturaPedido;
import br.hering.webservices.message.order.balance.ImportarFaturaPedidoRequest;


/**
 * @author ghayashi
 * @author Vinicius de Souza
 * 
 */
@Endpoint
public class InterfaceImportacaoFaturaPedidoEndpoint extends AbstractInterfaceEndpoint<ImportarFaturaPedidoRequest>
{
	private static final String PEDIDO_RECEBIDO = "PEDIDO RECEBIDO";

	private static final String PEDIDO_FATURADO = "PEDIDO FATURADO";

	private static final String PEDIDO_DESPACHADO = "PEDIDO DESPACHADO";

	private static final String PEDIDO_ENTREGUE = "PEDIDO ENTREGUE";

	private static final String CANCELADO = "CANCELADO";

	private static final String RESERVADO = "RESERVADO";

	private static final String SUSPENSO = "SUSPENSO";

	private static final String PAGO = "PEDIDO PAGO";

	private static final String STATUS = "STATUS";

	@Resource
	private UserService userService;

	@Resource
	private CustomerAccountService customerAccountService;

	@Resource
	private ConsignmentService consignmentService;

	@Resource
	private WarehouseService warehouseService;

	@Resource
	private EventService eventService;

	@Resource
	private OrderCancelledEvent orderCancelledEvent;

	@Resource
	private OrderDispatchedEvent orderDispatchedEvent;

	@Resource
	private OrderInvoiceEvent orderInvoiceEvent;

	@Resource
	private OrderPayedEvent orderPayedEvent;

	@Resource
	private StockService stockService;

	@Override
	@ResponsePayload
	@PayloadRoot(localPart = "importarFaturaPedidoRequest", namespace = namespaceUri)
	public void service(@RequestPayload final ImportarFaturaPedidoRequest request)
	{
		try
		{
			LOG.info("Iniciando importação de fatura de pedidos");

			final List<FaturaPedido> listaFatura = request.getFaturaPedido();

			if (CollectionUtils.isEmpty(listaFatura))
			{
				LOG.warn("Nenhum dado para importação");
				return;
			}

			for (final FaturaPedido fatura : listaFatura)
			{
				//prepareCurrentSession(fatura.getCodigoSite());

				try
				{
					final String customerCode = fatura.getCodigoCliente(); //TODO
					final String orderCode = fatura.getCodigoPedido();

					LOG.info("Iniciando a importação do pedido: " + orderCode);

					final OrderModel orderModel = findOrderByCode(customerCode, orderCode, fatura.getCodigoSite()); //TODO nao pode ficar fixo

					if (orderModel == null)
					{
						LOG.error("Order with orderGUID " + orderCode + " not found");
						createErrorMessageList(CHAVE_SITE + "=" + fatura.getCodigoSite() + "#" + CHAVE_PEDIDO + "=" + orderCode,
								"Pedido não encontrado");
						continue;
					}

					updateShippingInfo(orderModel, fatura);
					updateStatus(orderModel, fatura);
					updateBillingInfo(orderModel, fatura);

					LOG.info("Finalizando a importação do pedido: " + orderCode);
				}
				catch (final Exception e)
				{
					LOG.error("Error processing order with code: " + fatura.getCodigoPedido(), e);
					createErrorMessageList(
							CHAVE_SITE + "=" + fatura.getCodigoSite() + "#" + CHAVE_PEDIDO + "=" + fatura.getCodigoPedido() + "#"
									+ STATUS + "=" + fatura.getStatusPedido(),
							e.getMessage() != null && !e.getMessage().equals("") ? e.getMessage() : "Não gerou mensagem de erro.");
				}
			}
		}
		catch (final Exception e)
		{
			LOG.error("ERROR", e);
		}
	}

	/**
	 * @param orderModel
	 * @param fatura
	 * @throws InsufficientStockLevelException
	 */
	private void updateStatus(final OrderModel orderModel, final FaturaPedido fatura) throws InsufficientStockLevelException
	{
		LOG.info("Atualizando status do pedido - INICIO");
		if (fatura.getStatusPedido().toUpperCase().equals(PEDIDO_RECEBIDO))
		{
			LOG.info("Atualizando status do pedido: " + fatura.getCodigoPedido());
			orderModel.setExportStatus(ExportStatus.EXPORTED);
			modelService.save(orderModel);
			modelService.refresh(orderModel);
		}
		else if (orderModel.getStatus() != null && !orderModel.getStatus().equals(""))
		{
			if (!orderModel.getStatus().equals(OrderStatus.INVOICE)
					&& fatura.getStatusPedido().toUpperCase().equals(PEDIDO_FATURADO))
			{
				LOG.info("Atualizando status do pedido: " + fatura.getCodigoPedido());
				orderModel.setStatus(OrderStatus.INVOICE);
				modelService.save(orderModel);
				modelService.refresh(orderModel);
				orderInvoiceEvent.setOrder(orderModel);
				eventService.publishEvent(orderInvoiceEvent);

				releaseStock(orderModel, "released reserved - billed - by order:", true);
			}
			else if (!orderModel.getStatus().equals(OrderStatus.DISPATCHED)
					&& fatura.getStatusPedido().toUpperCase().equals(PEDIDO_DESPACHADO))
			{
				LOG.info("Atualizando status do pedido: " + fatura.getCodigoPedido());
				LOG.info("Pedido: " + fatura.getCodigoPedido() + " de Status: " + orderModel.getStatus());
				orderModel.setStatus(OrderStatus.DISPATCHED);
				modelService.save(orderModel);
				modelService.refresh(orderModel);
				synchronized (this)
				{
					LOG.info("Pedido: " + fatura.getCodigoPedido() + " para Status: " + orderModel.getStatus());
					orderDispatchedEvent.setOrder(orderModel);
					eventService.publishEvent(orderDispatchedEvent);
				}
			}
			else if (!orderModel.getStatus().equals(OrderStatus.CANCELLED)
					&& fatura.getStatusPedido().toUpperCase().equals(CANCELADO))
			{
				if (orderModel.getStatus().equals(OrderStatus.COMPLETED))
				{
					LOG.info("Atualizando status do pedido: " + fatura.getCodigoPedido());
					orderModel.setStatus(OrderStatus.CANCELLED);
					modelService.save(orderModel);
					modelService.refresh(orderModel);
					orderCancelledEvent.setOrder(orderModel);
					eventService.publishEvent(orderCancelledEvent);
				}
				else
				{
					LOG.info("Atualizando status do pedido: " + fatura.getCodigoPedido());
					orderModel.setStatus(OrderStatus.CANCELLED);
					modelService.save(orderModel);
					modelService.refresh(orderModel);
					orderCancelledEvent.setOrder(orderModel);
					eventService.publishEvent(orderCancelledEvent);

					releaseStock(orderModel, "released - canceled - by order: ", false);
				}
			}

			else if (!orderModel.getStatus().equals(OrderStatus.RESERVED)
					&& fatura.getStatusPedido().toUpperCase().equals(RESERVADO))
			{
				LOG.info("Atualizando status do pedido: " + fatura.getCodigoPedido());
				orderModel.setStatus(OrderStatus.RESERVED);
				modelService.save(orderModel);
				modelService.refresh(orderModel);
			}
			else if (!orderModel.getStatus().equals(OrderStatus.SUSPENDED)
					&& fatura.getStatusPedido().toUpperCase().equals(SUSPENSO))
			{
				LOG.info("Atualizando status do pedido: " + fatura.getCodigoPedido());
				orderModel.setStatus(OrderStatus.SUSPENDED);
				modelService.save(orderModel);
				modelService.refresh(orderModel);
			}
			else if (!orderModel.getStatus().equals(OrderStatus.COMPLETED) && fatura.getStatusPedido().toUpperCase().equals(PAGO))
			{
				LOG.info("Atualizando status do pedido: " + fatura.getCodigoPedido());
				orderModel.setStatus(OrderStatus.COMPLETED);
				modelService.save(orderModel);
				modelService.refresh(orderModel);
				orderPayedEvent.setOrder(orderModel);
				eventService.publishEvent(orderPayedEvent);
			}

			else if (!orderModel.getStatus().equals(OrderStatus.DELIVERED)
					&& fatura.getStatusPedido().toUpperCase().equals(PEDIDO_ENTREGUE))
			{
				LOG.info("Atualizando status do pedido: " + fatura.getCodigoPedido());
				orderModel.setStatus(OrderStatus.DELIVERED);
				modelService.save(orderModel);
				modelService.refresh(orderModel);

				//Disparo do e-mail transacional.
				final OrderDeliveredEvent event = new OrderDeliveredEvent();
				event.setOrder(orderModel);
				eventService.publishEvent(event);
			}
		}
		else
		{
			throw new NullPointerException("Unknown status for order with code: " + fatura.getCodigoPedido());
		}
		LOG.info("Atualizando status do pedido - FIM");
	}

	private void releaseStock(final OrderModel order, final String comment, final boolean fromCompleted)
	{
		Assert.notNull(order, "order is null");
		Assert.isTrue(CollectionUtils.isNotEmpty(order.getEntries()), "order is empty");

		final WarehouseModel warehouse = warehouseService.getWarehouseForCode("hering_estoque_default");

		for (final AbstractOrderEntryModel entry : order.getEntries())
		{
			final ProductModel product = entry.getProduct();
			final int quantity = entry.getQuantity().intValue();

			LOG.info("Released stock:" + " order: " + order.getCode() + " product: " + product.getCode() + " qty: " + quantity);

			final StockLevelModel stockLevel = stockService.getStockLevel(product, warehouse);

			//validation necessary to avoid negative values, otherwise we have an error in the available amount
			if (stockLevel.getReserved() >= quantity && stockLevel.getReserved() > 0)
			{
				stockService.release(product, warehouse, quantity, comment + order.getCode());
			}

			if (fromCompleted)
			{
				final int available = stockLevel.getAvailable();
				//			stockService.updateActualStockLevel(product, warehouse, (available + quantity), "reserved");
				stockLevel.setAvailable((available - quantity));

				modelService.save(stockLevel);
				modelService.refresh(stockLevel);

				createStockLevelHistoryEntry(stockLevel, StockLevelUpdateType.WAREHOUSE, stockLevel.getReserved() + quantity,
						"released available - billed - by order:");
			}
		}
	}

	/**
	 * @param orderModel
	 * @param fatura
	 * @throws ConsignmentCreationException
	 * @throws ParseException
	 */
	private void updateBillingInfo(final OrderModel orderModel, final FaturaPedido fatura) throws ConsignmentCreationException,
			ParseException
	{
		try
		{
			if (StringUtils.isBlank(fatura.getChaveNotaFiscal()) || StringUtils.isBlank(fatura.getNumeroNotaFiscal()))
			{
				return;
			}

			final ConsignmentModel consignment = getOrderConsignment(orderModel);

			if (consignment.getInvoice() != null)
			{
				LOG.info("Invoice already exists, skiping");
				return;
			}

			final OrderInvoiceModel invoice = modelService.create(OrderInvoiceModel.class);
			invoice.setKey(fatura.getChaveNotaFiscal());
			invoice.setNumber(fatura.getNumeroNotaFiscal());
			invoice.setIssueDate(parseDate(fatura.getDataFaturamento()));

			consignment.setInvoice(invoice);

			modelService.saveAll(invoice, consignment);
			modelService.refresh(orderModel);
		}
		catch (final Exception e)
		{
			LOG.error("Error processing order with code: " + orderModel.getCode(), e);
			createErrorMessageList(CHAVE_SITE + "=" + fatura.getCodigoSite() + "#" + CHAVE_PEDIDO + "=" + fatura.getCodigoPedido(),
					e.getMessage());
		}
	}

	/**
	 * @param orderModel
	 * @param fatura
	 * @throws ConsignmentCreationException
	 * @throws ParseException
	 */
	private void updateShippingInfo(final OrderModel orderModel, final FaturaPedido fatura) throws ConsignmentCreationException,
			ParseException
	{
		try
		{
			if (StringUtils.isBlank(fatura.getCodigoRastreio()))
			{
				return;
			}

			final ConsignmentModel consignment = getOrderConsignment(orderModel);

			consignment.setStatus(ConsignmentStatus.SHIPPED);
			consignment.setShippingDate(parseDate(fatura.getDataFaturamento()));
			consignment.setTrackingID(fatura.getCodigoRastreio());

			modelService.save(consignment);
			modelService.refresh(orderModel);
		}
		catch (final Exception e)
		{
			LOG.error("Error processing order with code: " + orderModel.getCode(), e);
			createErrorMessageList(CHAVE_SITE + "=" + fatura.getCodigoSite() + "#" + CHAVE_PEDIDO + "=" + fatura.getCodigoPedido(),
					e.getMessage());
		}
	}

	/**
	 * @param orderModel
	 * @return
	 * @throws ConsignmentCreationException
	 */
	private ConsignmentModel getOrderConsignment(final OrderModel orderModel) throws ConsignmentCreationException
	{

		final Set<ConsignmentModel> consignments = orderModel.getConsignments();

		if (CollectionUtils.isNotEmpty(consignments))
		{
			LOG.info("Using existing consignment");
			return consignments.iterator().next();
		}

		LOG.info("Creating new consignment");

		final String consignmentCode = orderModel.getCode();
		final ConsignmentModel consignment = consignmentService.createConsignment(orderModel, consignmentCode,
				orderModel.getEntries());

		consignment.setShippingAddress(orderModel.getDeliveryAddress());
		consignment.setWarehouse(warehouseService.getWarehouses(orderModel.getEntries()).iterator().next());
		try
		{
			modelService.save(consignment);
		}
		catch (final Exception e)
		{
			LOG.error("Error processing order with code: " + orderModel.getCode(), e);
			createErrorMessageList(CHAVE_SITE + "=" + orderModel.getSite() + "#" + CHAVE_PEDIDO + "=" + orderModel.getCode(),
					e.getMessage());
		}

		return consignment;
	}

	private OrderModel findOrderByCode(final String customerCode, final String orderCode, final String storeCode)
	{
		LOG.info("FindOrderByCode customerCode:" + customerCode);

		final Integer storeCodeAsNumber = Integer.valueOf(storeCode);
		if (!getStoreCodeMapping().containsKey(storeCodeAsNumber))
		{
			throw new IllegalArgumentException("Unknown store with code: string: " + currentStoreId + " int: " + storeCodeAsNumber);
		}

		final BaseStoreModel store = baseStoreService.getBaseStoreForUid(getStoreCodeMapping().get(storeCodeAsNumber));
		final CustomerModel customer = modelService.get(PK.parse(customerCode));//userService.getUserForUID(customerCode, CustomerModel.class);

		final OrderModel orderModel = customerAccountService.getOrderForCode(customer, orderCode, store);

		return orderModel;
	}
}