/**
 * 
 */
package br.flieger.exacttarget.events;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.PriceValue;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import br.flieger.exacttarget.wsdl.api.APIProperty;
import br.flieger.exacttarget.wsdl.api.Attribute;

import com.adyen.services.payment.impl.AdyenCardInstallmentsService;
import com.flieger.carrier.services.CarrierZoneDeliveryModeService;

/**
 * @author Vinicius de Souza
 * 
 * Event change to <code>OrderConfirmedEvent</code>.
 *
 */
@Deprecated
public class OrderSendEmailEvent extends AbstractExacttargetOrderEvent
{	
	@Resource
	protected CommonI18NService commonI18NService;
	
	@Resource
	protected AdyenCardInstallmentsService adyenCardInstallmentsService;
	
	@Resource
	protected CarrierZoneDeliveryModeService zoneDeliveryModeService;
	
	private static final Logger LOG = Logger.getLogger(OrderSendEmailEvent.class);

	private final int MAX = Integer.parseInt(Config.getParameter("exacttarget.max-string"));

	//Sync
	private Double paymentCost = 0d;

	/**
	 * Format the order products to <code>table - html</code>.
	 * @return List of html limited for max size difined for exacttarget.
	 */
	private List<String> getProductFormatHtmlTable ()
	{
		commonI18NService.setCurrentLanguage(commonI18NService.getLanguage("pt"));
		commonI18NService.setCurrentCurrency(commonI18NService.getCurrency("BRL"));

		StringBuilder strHtml = new StringBuilder();
		if(getOrder() != null)
		{
			int count = 1;
			paymentCost = 0d;
			for (AbstractOrderEntryModel entry : getOrder().getEntries())
			{
				strHtml.append("	<tr>");
				strHtml.append("		<td style=\"border:1px solid #DDD\" align=\"center\">" +count+ "</td>");
				strHtml.append("		<td style=\"border:1px solid #DDD\"> " + entry.getProduct().getName() + "</td>");
				strHtml.append("		<td style=\"border:1px solid #DDD\" align=\"center\"> " + entry.getQuantity() + "</td>");
				strHtml.append("		<td style=\"border:1px solid #DDD\" align=\"center\"> Não </td>");
				strHtml.append("		<td style=\"border:1px solid #DDD\" align=\"center\"> " + DECIAL_FORMAT.format(entry.getBasePrice()) + "</td>");
				strHtml.append("		<td style=\"border:1px solid #DDD\" align=\"center\"> " + DECIAL_FORMAT.format(entry.getTotalPrice()) + "</td>");
				strHtml.append("	</tr>");
				paymentCost  += entry.getTotalPrice();
				count++;
			}			
			//LOG.info(strHtml);
			
			return splitStringHtml(strHtml.toString(), new LinkedList<String>());
		}
		return null;
	}

	@Deprecated
	private List<String> splitStringHtml(String str, List<String> listHtml)
	{		
		if(str != null)
		{
			if(str.length() <= MAX)
			{
				listHtml.add(str);
			}
			else
			{
				listHtml.add(str.substring(0, MAX));
				return splitStringHtml(str.substring(MAX-1, str.length()), listHtml);
			}
		}		
		return listHtml;
	}
	
	@Override
	public Attribute[] getAttributes()
	{
		//22
		final List<Attribute> att = new LinkedList<Attribute>();
		//Inclusão da tabela de produtos
		int count = 0;
		Attribute aHtml;
		
		for (String html : getProductFormatHtmlTable())
		{
			aHtml = createAttribute("HtmlEntries"+count, html);
			att.add(aHtml);
			count++;
		}		
		
		final Attribute aCode = createAttribute("Code", getOrder().getCode());
		att.add(aCode);
		
		final Attribute aName = createAttribute("Name", getOrder().getUser().getName());
		att.add(aName);
		
		final Attribute aDate = createAttribute("Date", getOrder().getDate());
		att.add(aDate);
		
		final Attribute aPaymentInfo = createAttribute("PaymentInfo", getPaymentMethod());
		att.add(aPaymentInfo);
		
		PaymentModeModel pmm = getOrder().getPaymentMode();
		final Attribute aPaymentMode = createAttribute("PaymentMode", pmm!=null?pmm.getName():" ");
		att.add(aPaymentMode);
		
		DeliveryModeModel dmm = getOrder().getDeliveryMode();
		final Attribute aDeliveryMode = createAttribute("DeliveryMode", dmm!=null?dmm.getName():" ");
		att.add(aDeliveryMode);
		
		final Attribute aDeliveryDeadline = createAttribute("DeliveryDeadline", getOrder().getEstimatedDeliveryDays() +" dia(s)");
		att.add(aDeliveryDeadline);
		
		final Attribute aQuantityParc = createAttribute("QuantityParc", getQuantityParc());
		att.add(aQuantityParc);
		
		final Attribute aPriceParc = createAttribute("PriceParc", getPriceParc());
		att.add(aPriceParc);
		
		final Attribute aDeliveryCost = createAttribute("DeliveryCost", getOrder().getDeliveryCost());
		att.add(aDeliveryCost);
		
		final Attribute aPackingCost = createAttribute("PackingCost", 0d);
		att.add(aPackingCost);
		
		final Attribute aTotalDiscounts = createAttribute("TotalDiscounts", getOrder().getTotalDiscounts());
		att.add(aTotalDiscounts);
		
		final Attribute aAddressDelivery = createAttribute("AddressDelivery", getOrder().getDeliveryAddress().getStreetname());
		att.add(aAddressDelivery);
		
		final Attribute aNeighborhood = createAttribute("Neighborhood", getOrder().getDeliveryAddress().getDistrict());
		att.add(aNeighborhood);
		
		final Attribute aCity = createAttribute("City", getOrder().getDeliveryAddress().getTown());
		att.add(aCity);
		
		final Attribute aPostalCode = createAttribute("PostalCode", getOrder().getDeliveryAddress().getPostalcode());
		att.add(aPostalCode);
		
		final Attribute aAddressComp = createAttribute("AddressComp", getOrder().getDeliveryAddress().getComplemento());
		att.add(aAddressComp);
		
		final Attribute aAppartment = createAttribute("Appartment", getOrder().getDeliveryAddress().getAppartment());
		att.add(aAppartment);
		
		final Attribute aAddressReference = createAttribute("AddressReference", getOrder().getDeliveryAddress().getPontoDeReferencia());
		att.add(aAddressReference);
		
		final Attribute aAddressNumber = createAttribute("AddressNumber", getOrder().getDeliveryAddress().getStreetnumber());
		att.add(aAddressNumber);
		
		final Attribute aTotalPrice = createAttribute("TotalPrice", getOrder().getTotalPrice());
		att.add(aTotalPrice);	
		
		final Attribute aPaymentCost = createAttribute("PaymentCost", paymentCost);
		att.add(aPaymentCost);
		
		final Attribute aTotalOrder = createAttribute("TotalOrder", getOrder().getTotalPrice());
		att.add(aTotalOrder);	
		
		final Attribute aTotalTax = createAttribute("TotalTax", getOrder().getTotalTax());
		att.add(aTotalTax);
		
		Attribute[] arrayAtt = new Attribute[att.size()];
		count = 0;
		for (Attribute attribute : att)
		{
			arrayAtt[count] = new Attribute(attribute.getName(), attribute.getValue(), null);
			count++;
			//LOG.info("\nParametro::"+attribute.getName()+"::"+attribute.getValue());
		}
		
		return arrayAtt;
	}

	@Override
	public APIProperty[] getAPIProperty()
	{
		return null;
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