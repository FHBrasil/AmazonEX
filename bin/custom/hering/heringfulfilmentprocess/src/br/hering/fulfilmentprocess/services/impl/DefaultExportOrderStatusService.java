/**
 * 
 */
package br.hering.fulfilmentprocess.services.impl;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.util.Config;
import br.hering.fulfilmentprocess.message.order.status.StatusPedido;
import br.hering.fulfilmentprocess.message.order.status.StatusPedidoRequest;

/**
 * @author ghayashi
 * 
 */
public class DefaultExportOrderStatusService extends AbstractHeringOrderService
{
	private static final String CANCELADO = "CANCELADO";
	
	private static final String RESERVADO = "RESERVADO";
	
	private static final String SUSPENSO = "SUSPENSO";

	private static final String DZARM = "002";

	@Autowired
	private WebServiceTemplate enviarStatusPedidoWSTemplate;

	@Override
	public void exportStatus(final OrderModel order)
	{
		final StatusPedidoRequest request = new StatusPedidoRequest();
		request.getStatusPedido().add(this.populateExportStatus(order));
		enviarStatusPedidoWSTemplate.setDefaultUri(Config.getParameter("enviar.status.pedido.ws.template"));
		final Object response = enviarStatusPedidoWSTemplate.marshalSendAndReceive(request);
		System.out.println(response);
	}

	private StatusPedido populateExportStatus(final OrderModel order)
	{
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		final StatusPedido status = new StatusPedido();
		status.setCodigoSite(DZARM);
		status.setCodigoPedido(order.getCode());		
		status.setStatus(order.getStatusDisplay().toUpperCase());//order.getStatus().getCode()
		//TODO só vai para o Linx quando o produto já estiver com o status pago
		status.setMotivoCancelamento(0);
		status.setData(dateFormat.format(order.getModifiedtime()));
		status.setUsuario(order.getUser().getPk().toString());

		return status;
	}
}
