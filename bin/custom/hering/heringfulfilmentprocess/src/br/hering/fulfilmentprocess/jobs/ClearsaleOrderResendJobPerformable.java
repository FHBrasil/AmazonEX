package br.hering.fulfilmentprocess.jobs;

import de.hybris.platform.basecommerce.enums.FraudStatus;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.fraud.impl.FraudServiceResponse;
import de.hybris.platform.fraud.model.FraudReportModel;
import de.hybris.platform.processengine.enums.ProcessState;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.flieger.clearsale.serviceprovider.ClearSaleFraudServiceProvider;
import com.flieger.model.ClearSaleFraudReportModel;

/**
 * Job alterada para que todos os pedidos que não tenham sido enviados para 
 * a clearsale sejam enviados
 * 
 * O teste para isso é: se tiver algum relatório de OrderProcess com erro, temos que 
 * verificar se ele foi sent para clearsale. O job vai rodar 1x ao dia para fazer
 * o resend. Se um pedido que cair aqui não puder ser reenviado, tem que avisar 
 * por email
 * 
 * Se os pedidos não estiverem 
 * 
 * **/
public class ClearsaleOrderResendJobPerformable  
extends AbstractJobPerformable<CronJobModel>
{	
	private static final Logger LOG = Logger.getLogger(ClearsaleOrderResendJobPerformable.class);
	
	@Resource
	private FlexibleSearchService flexibleSearchService;
	
	@Resource
	private ClearSaleFraudServiceProvider clearSaleFraudServiceProvider;

	@Override
	public PerformResult perform(final CronJobModel cronJob)
	{
		JobLogListener logs = new JobLogListener();
		logs.startListening();
		try {
			LOG.info("Preparing job to resend orders to clearsale");
			List<OrderModel> list = findOrdersToResend();
			LOG.info("Found: " + list.size() + " to be sent to Clearsale");

			boolean success = true;

			for(OrderModel model : list)
			{	
				try
				{
					LOG.info(
							String.format(
									"Started Processing Order. order:{code:%s}",
									model.getCode()));

					final OrderStatus orderStatus = model.getStatus();

					final FraudServiceResponse response = 
							clearSaleFraudServiceProvider.recognizeOrderFraudSymptoms(model);

					final boolean ok = "OK".equals(response.getDescription());

					success &= ok;

					final List<FraudReportModel> fraudReports = new ArrayList<>(model.getFraudReports());

					if (ok)
					{
						boolean suspended = false, approved = false, manual = false;

						for (final FraudReportModel aux : fraudReports)
						{
							final String fraudStatus = ((ClearSaleFraudReportModel)aux).getStatusCode();
							switch (fraudStatus)
							{
								case "CANCELADO_PELO_CLIENTE": 
								case "FRAUDE_CONFIRMADA":
								case "REPROVACAO_AUTOMATICA":
								case "REPROVADO_SEM_SUSPEITA": 
								case "SUSPENSAO_MANUAL":
								{
									suspended = true;
								}
								break;
								case "APROVACAO_AUTOMATICA": 
								case "APROVACAO_MANUAL":
								{
									approved = true;
								}
								break;
								case "ANALISE_MANUAL": 
								{
									manual = true;
								}
							}
						}

						if( suspended ){
							LOG.info("Changing order status from:" 
									+ orderStatus.getCode() + " to ESTORNO" );
							model.setStatus(OrderStatus.ESTORNO);
							model.setPaymentStatus(PaymentStatus.NOTPAID);
						}
						else if(approved)
						{
							if(	OrderStatus.PENDING_APPROVAL.equals(orderStatus)
									|| OrderStatus.PAYMENT_AMOUNT_RESERVED.equals(orderStatus)
									){
								LOG.info("Changing order status from:" 
										+ orderStatus.getCode() + " to CAPTURE_PAYMENT" );
								model.setStatus(OrderStatus.CAPTURE_PAYMENT);
								model.setPaymentStatus(PaymentStatus.PENDING_APPROVAL);
							} else {
								model.setStatus(orderStatus);//TODO isto deveria tratar o boleto, mas quando o re-envio de boleto quebra mais que 1 vez, o status fica sendo ON_VALIDATION e o boleto não é enviado para o linx
							}
						}
						else if (manual){
							model.setStatus(OrderStatus.PENDING_APPROVAL);
						}
						else 
						{
							model.setStatus(orderStatus);
						}
						modelService.save(model);
					}

					LOG.info(
							String.format(
									"Finished processing Order. order:{code:%s}. Result:%s",
									model.getCode(),
									response.getDescription()));
				} catch (Exception e) {
					success = false;
					LOG.info("Unexpected error processing Order. order:{code:" + model.getCode() 
							+ "}. Exception: " + e.getMessage());
				}
			}
			
			final CronJobResult cronJobResult = 
					success ? CronJobResult.SUCCESS : CronJobResult.ERROR;

			LOG.info("Finished job for resending Orders: " + (success?"SUCCESSFULLY":"FAILED"));

			return new PerformResult(cronJobResult, CronJobStatus.FINISHED);
		} finally {
			logs.stopListening();
			sessionService.getCurrentSession().setAttribute(CustomCronJobNotificationContext.LOG_TEXT, logs.getLogs());
		}
	}

	private List<OrderModel> findOrdersToResend() 
	{		
		final String sql = "SELECT tbl.PK FROM ("
				+ "{{	select distinct {o.PK} "
				+ "	from {Order as o "
				+ "	join ClearSaleFraudReport as csfr on {csfr.order} = {o.PK}"
				+ "	join PaymentMode as pm on {pm.PK} = {o.paymentMode}}"
				+ "	where {csfr.status} = ?fraudStatusNotSent "
				+ "	AND {pm.code} <> 'Voucher'"
				+ "	and {o.PK} not in ("
				+ "	{{"
					+ "	select {o1.PK} "
					+ "	from {Order as o1 "
					+ "	join ClearSaleFraudReport as csfr1 on {csfr1.order} = {o1.PK}}"
					+ "	where {csfr1.status} = ?fraudStatusOK"
				+ "	}}"
				+ "	) or {o.status} = ?orderStatusOnValidation"
				+ "}}"
				+ "	UNION	"
				+ "{{ SELECT distinct {o2.PK}"
				+ "	FROM {Order as o2"
				+ "	join PaymentMode as pm2 on {pm2.PK} = {o2.paymentMode}"
				+ "	join OrderProcess as op2 on {op2.order} = {o2.PK}"
				+ "	left join ClearSaleFraudReport as csfr2 on {csfr2.order} = {o2.PK}}"
				+ "	WHERE {pm2.code} <> 'Voucher'"
				+ "	AND {op2.state} = ?errorProcessState"
				+ "	AND {op2.processDefinitionName} = 'order-process'"
				+ "	AND {csfr2.PK} IS NULL"
				+ "}}) tbl";
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("fraudStatusNotSent", FraudStatus.NOTSENT);
		params.put("fraudStatusOK", FraudStatus.OK);
		params.put("orderStatusOnValidation", OrderStatus.ON_VALIDATION);
		params.put("errorProcessState", ProcessState.ERROR);

		return flexibleSearchService.<OrderModel>search(sql, params).getResult();
	}
}
