/**
 * 
 */
package br.flieger.exacttarget.events;

import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.util.Config;

import java.util.LinkedList;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.flieger.exacttarget.wsdl.api.APIProperty;
import br.flieger.exacttarget.wsdl.api.Attribute;
import br.hering.fulfilmentprocess.model.OrderInvoiceModel;

import com.flieger.carrier.services.CarrierZoneDeliveryModeService;

/**
 * @author Vinicius de Souza
 *
 */
@SuppressWarnings("serial")
public class OrderDispatchedEvent extends AbstractExacttargetOrderEvent
{
	@Resource
	protected CarrierZoneDeliveryModeService zoneDeliveryModeService;
	
	@Resource
	protected CommonI18NService commonI18NService;
	
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(OrderDispatchedEvent.class);
	
	@Override
	public Attribute[] getAttributes()
	{
		commonI18NService.setCurrentLanguage(commonI18NService.getLanguage("pt"));
		commonI18NService.setCurrentCurrency(commonI18NService.getCurrency("BRL"));
		
		final Attribute att1 = createAttribute("Nome", getOrder().getUser().getName());
		final Attribute att2 = createAttribute("Pedido", getOrder().getCode());
		final Attribute att3 = createAttribute("DataPedido", getOrder().getDate());
		final Attribute att4 = createAttribute("TotalPedido", getOrder().getTotalPrice());
		final Attribute att7 = createLinkKeyNFe();
		final Attribute att8 = createLinkTracking();
		
		return new Attribute[] {att1, att2, att3, att4, att7, att8};
	}
	
	private Attribute createLinkTracking()
	{
		String link = "<a href=\"";
		String tracking = null;
		
			for (ConsignmentModel consignmentModel : getOrder().getConsignments())
			{
				if(consignmentModel.getTrackingID() != null)
				{
					tracking = consignmentModel.getTrackingID();
				}
			}

		if(StringUtils.isEmpty(tracking))
		{
			return new Attribute("LinkTracking", "Nenhum código de rastreio", null);
		}
		
		final String urlTracking = Config.getParameter("exacttarget.delivery."+getOrder().getDeliveryMode().getName());
		
		if(StringUtils.isEmpty(urlTracking))
		{
			LOG.error("Erro ao gerar url de rastreio para a ordem ["+getOrder().getCode()+"]");
			return new Attribute("LinkTracking", "Nenhum código de rastreio", null);
		}
		
		link += urlTracking.replaceAll("%%TRACKINGID%%", tracking) + "\" target=\"_blank\">";
		
		link += "Clique aqui para rastrear seu pedido. <b>("+tracking+")</b></a>";

		return new Attribute("LinkTracking", link, null);
	}

	@Override
	public APIProperty[] getAPIProperty()
	{
		return null;
	}
	
	private Attribute createLinkKeyNFe()
	{
		String link = null;
		
		final String urlNFe = Config.getParameter("exacttarget.nfe");
		
		for (ConsignmentModel consignment : getOrder().getConsignments())
		{
			if(consignment.getInvoice() != null)
			{
				OrderInvoiceModel invoice = consignment.getInvoice();
				
				if(invoice != null){
					String chaveNfe = invoice.getKey();
					link = "<a target=\"_blank\" href=\""+urlNFe+"\">Clique aqui para visualizar NFe</a>. Utilize a chave NFe: <b>"+ chaveNfe +"</b>";
				}
				break;
			}
		}
		
		return new Attribute("LinkKeyNFe", link != null ? link : " ", null);		
	}

	/* (non-Javadoc)
	 * @see br.flieger.exacttarget.events.AbstractExacttargetEvent#getLinkTag()
	 */
	@Override
	protected String getLinkTag()
	{
		return "";
	}
}