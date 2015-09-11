/**
 * 
 */
package com.flieger.clearsale.jobs;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.hering.core.model.order.payment.VoucherPaymentInfoModel;

import com.flieger.clearsale.webserviceclient.SOAPservices.ClearSaleSOAPServices;
import com.flieger.clearsale.webserviceclient.SOAPservices.impl.DefaultClearSaleSOAPServices;
import com.flieger.clearsale.webserviceclient.responsebeans.ClearSaleResponse;
import com.flieger.clearsale.webserviceclient.responsebeans.StatusEnumOUT;
import com.flieger.model.ClearSaleFraudReportModel;

/**
 * @author flieger
 * @author ezequiel
 */
public class ClearsaleStatusPerformable extends AbstractJobPerformable<CronJobModel>
{
	protected final Logger LOG = Logger.getLogger(this.getClass());

	private static final String DEFAULT_STORE_UID = "dzarm";
	
	@Resource
	private ModelService modelService;

	/**TODO There should exist one service for each store, since this is what distinguish one service from the other*/
	private ClearSaleSOAPServices defaultClearSaleSOAPServices;
	
	private String providerName;

	/**
	 * @return the providerName
	 */
	public String getProviderName()
	{
		return providerName;
	}

	/**
	 * @param providerName
	 *           the providerName to set
	 */
	public void setProviderName(final String providerName)
	{
		this.providerName = providerName;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.cronjob.model.CronJobModel
	 * )
	 */
	@Override
	public PerformResult perform(final CronJobModel cronjobModel)
	{
		final List<Object> lista = searchClearsaleStatus();
		StatusEnumOUT statusNovo = null;
		for (final Iterator<Object> it = lista.iterator(); it.hasNext();)
		{
			final ClearSaleFraudReportModel fraudModel = (ClearSaleFraudReportModel) it.next();

			final OrderModel orderModel = fraudModel.getOrder();
			
			String baseStoreUid = DEFAULT_STORE_UID;
			if(orderModel == null)
			{
   			LOG.info(
   					String.format(
   							"There is a bug around here: Invalid data. "
   							+ "FraudReport has no order associated. "
   							+ "fraudReport:{PK:%s, code:%s}",
   							fraudModel.getPk(), fraudModel.getCode()));
				continue;
			} 
			else if(	orderModel.getStore() != null
					&& StringUtils.isNotBlank(orderModel.getStore().getUid()))
			{
				baseStoreUid = orderModel.getStore().getUid();
			}
			
			LOG.info("Checking ClearSale order status for Order: " + orderModel.getCode());
			
			statusNovo = getUpdatedStatus(orderModel.getCode(), baseStoreUid);
			
			if (		statusNovo == null 
					|| statusNovo.name() == null
					|| StatusEnumOUT.ERRO.equals(statusNovo))
			{
				LOG.info("Order: [" + orderModel.getCode() 
						+ "] not found at ClearSale");
				continue;
			}
			
			PaymentInfoModel paymentInfo = orderModel.getPaymentInfo();
			
			if(		paymentInfo == null
					|| paymentInfo instanceof VoucherPaymentInfoModel)
			{
				LOG.info("Invalid PaymentInfo:[" + paymentInfo + "] for Order:[" + orderModel.getCode()+"]");
				continue;
			}
			
			//status são diferentes
			if (!statusNovo.name().equals(fraudModel.getStatusCode()))
			{
				LOG.info("FraudStatus for Order [" + orderModel.getCode() 
						+ "] changed. Old status: [" + fraudModel.getStatusCode() 
						+ "] New status [" + statusNovo + "]");				
				switch(statusNovo)
				{
					case APROVACAO_MANUAL:
					{
						/*
						 * Setando OrderStatus.CAPTURE_PAYMENT o pagamento sera capturado pelo CompleteOrderValueValidator
						 */
						orderModel.setStatus(OrderStatus.CAPTURE_PAYMENT);
						modelService.save(orderModel);
						fraudModel.setStatusCode(statusNovo.name());
						modelService.save(fraudModel);	
					}
					break;
					case CANCELADO_PELO_CLIENTE: 
					case FRAUDE_CONFIRMADA:
					case REPROVACAO_AUTOMATICA:
					case REPROVADO_SEM_SUSPEITA: 
					case SUSPENSAO_MANUAL:
					{
						/*
						 * Cancelamento da order devido a REPROVAÇÃO ou CANCELAMENTO da clearsale
						 */
						orderModel.setStatus(OrderStatus.ESTORNO);
						orderModel.setPaymentStatus(PaymentStatus.NOTPAID);
						modelService.save(orderModel);
						fraudModel.setStatusCode(statusNovo.name());
						modelService.save(fraudModel);						
					}
					break;
					case APROVACAO_AUTOMATICA:
					{
						/*
						 * MUDOU, GERALMENTE DE STATUS=NOVO PARA APROVACAO AUTOMATICA
						 */
						if(OrderStatus.PENDING_APPROVAL.equals(orderModel.getStatus())){
							orderModel.setStatus(OrderStatus.CAPTURE_PAYMENT);
							modelService.save(orderModel);							
						}
						fraudModel.setStatusCode(statusNovo.name());
						modelService.save(fraudModel);
					}
					break;
					case ANALISE_MANUAL:
					{
						//mudou de NOVO para ANALISE_MANUAL
						orderModel.setStatus(OrderStatus.PENDING_APPROVAL);
						modelService.save(orderModel);
						fraudModel.setStatusCode(statusNovo.name());
						modelService.save(fraudModel);
					}
					break;
				} 
			} else {
				LOG.info("Status nao modificado no Clearsale:{Order:[" + orderModel.getCode() 
						+ "]; ClearsaleStatus [" + statusNovo
						+ "]; FraudModelStatus [" + fraudModel.getStatusCode() + "]}");
			}
		}
		LOG.info("JOB [updateClearsaleStatus] Finalizado.");
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	/*
	 * Realiza a pesquisa de registros com status pendente de confirmação do clearsale na base do hybris
	 */
	private List<Object> searchClearsaleStatus()
	{
		final String query = "select {csfr.PK} from {ClearSaleFraudReport AS csfr} "
				+ "WHERE {csfr.statusCode} IN (?fraudStatus1, ?fraudStatus2)";

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
		searchQuery.addQueryParameter("fraudStatus1", StatusEnumOUT.ANALISE_MANUAL.name());
		searchQuery.addQueryParameter("fraudStatus2", StatusEnumOUT.NOVO.name());
//		System.out.println("FlexibleSearchQuery: [" + searchQuery.getQuery() + "]");
		final List<Object> models = flexibleSearchService.search(searchQuery).getResult();
		return models;
	}

	/*
	 * Pesquisa no clearsale o status da order passada
	 */
	private StatusEnumOUT getUpdatedStatus(final String orderId, 
			final String baseStoreUid)
	{
		ClearSaleResponse retorno = null;
		try
		{
			defaultClearSaleSOAPServices = new DefaultClearSaleSOAPServices(baseStoreUid);
			retorno = defaultClearSaleSOAPServices.getOrderStatus(orderId);
			
			if (	retorno == null
					|| retorno.getOrders() == null
					|| retorno.getOrders().isEmpty()
					||	retorno.getOrders().iterator().next().getStatusOrder() == null
					|| retorno.getOrders().iterator().next().getStatusOrder().name() == null)
			{
				LOG.info(
						String.format(
								"No return obtained from clearsale: order:{code:%s, store:%s}; orderStatus:%s ",
								orderId, baseStoreUid, retorno));
				return null;
			}
			else
			{
				return retorno.getOrders().iterator().next().getStatusOrder();
			}
		}
		catch (final Exception e)
		{
			LOG.info("Unexpected exception: " + e + " : " + e.getMessage());
			return StatusEnumOUT.ERRO;
		}
	}
}
