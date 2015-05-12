/**
 * 
 */
package br.flieger.exacttarget.events;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import br.flieger.exacttarget.wsdl.api.APIProperty;
import br.flieger.exacttarget.wsdl.api.Attribute;

/**
 * @author Vinicius de Souza
 *
 */
@SuppressWarnings("serial")
public class OrderPayedEvent extends AbstractExacttargetOrderEvent
{
	@Resource
	protected CommonI18NService commonI18NService;

	@Override
	public Attribute[] getAttributes()
	{
		final List<Attribute> attrs = new ArrayList<>();
		attrs.add(createAttribute("Nome", 						getOrder().getUser().getName()));
		attrs.add(createAttribute("Pedido", 					getOrder().getCode()));
		attrs.add(createAttribute("Dias", 						getOrder().getEstimatedDeliveryDays()));
		attrs.add(createAttribute("TotalPedido",				getOrder().getTotalPrice()));
		attrs.add(createAttribute("DataPedido",				getOrder().getDate()));
		attrs.add(createAttribute("InformacoesPagamento",	getOrder().getPaymentInfo()));
		attrs.add(createAttribute("ModoEntrega",				getOrder().getDeliveryMode()));
		attrs.add(createAttribute("QuantidadeParcelas",		getQuantityParc()));
		attrs.add(createAttribute("ValorParcelas",			getPriceParc()));
		attrs.add(createAttribute("ValorFrete",				getOrder().getDeliveryCost()));
		//Atributo abaixo ainda não contemplado.
		attrs.add(createAttribute("ValorPacote",				0d));
		attrs.add(createAttribute("TotalDesconto",			getOrder().getTotalDiscounts()));
		attrs.add(createAttribute("EndNome",					getOrder().getDeliveryAddress().getFirstname()+" "+getOrder().getDeliveryAddress().getLastname()));
		attrs.add(createAttribute("EndRua",						getOrder().getDeliveryAddress().getStreetname()));
		final String apt = getOrder().getDeliveryAddress().getAppartment();
		attrs.add(createAttribute("EndNumero",					getOrder().getDeliveryAddress().getStreetnumber()+" "+(apt==null?"":"Ap: "+apt)));
		attrs.add(createAttribute("EndComplemento",			getOrder().getDeliveryAddress().getComplemento()));
		attrs.add(createAttribute("EndBairro",					getOrder().getDeliveryAddress().getDistrict()));
		attrs.add(createAttribute("EndCidade",					getOrder().getDeliveryAddress().getTown()));
		attrs.add(createAttribute("EndCep",						getOrder().getDeliveryAddress().getPostalcode()));
		attrs.add(createAttribute("EndReferencia",			getOrder().getDeliveryAddress().getPontoDeReferencia()));
		attrs.add(createAttribute("TotalProdutos",			getTotalProdutos()));
		
		attrs.addAll(getAttributesEntries());
		
		Attribute[] attributes = new Attribute[attrs.size()];
		
		int count = 0;
		for (Attribute attribute : attrs)
		{
			attributes[count] = attribute;
			count++;
		}	
		
		return attributes;
	}

	private List<Attribute> getAttributesEntries()
	{
		final List<Attribute> attrs = new ArrayList<>();
		
		final String strTable = getProductTable();
		
		int count = 1;
		for(String value : splitMaxLength(strTable, null))
		{
			attrs.add(createAttribute("Produtos"+count, value));
			count++;
		}
		
		return attrs;
	}

	private String getProductTable()
	{
		commonI18NService.setCurrentLanguage(commonI18NService.getLanguage("pt"));
		commonI18NService.setCurrentCurrency(commonI18NService.getCurrency("BRL"));
		
		StringBuilder strHtml = new StringBuilder();
		
		int count = 1;
		for (AbstractOrderEntryModel entry : getOrder().getEntries())
		{
			strHtml.append("<tr>\n");
			strHtml.append("		<td style=\"border:1px solid #DDD\" align=\"right\">" +count+ "</td>");
			strHtml.append("		<td style=\"border:1px solid #DDD\"> " + entry.getProduct().getName() + "</td>");
			strHtml.append("		<td style=\"border:1px solid #DDD\" align=\"right\"> " + entry.getQuantity() + "</td>");
			strHtml.append("		<td style=\"border:1px solid #DDD\" align=\"center\"> Não </td>");
			strHtml.append("		<td style=\"border:1px solid #DDD\" align=\"right\"> " + DECIAL_FORMAT.format(entry.getBasePrice()) + "</td>");
			strHtml.append("		<td style=\"border:1px solid #DDD\" align=\"right\"> " + DECIAL_FORMAT.format(entry.getTotalPrice()) + "</td>");
			strHtml.append("\n</tr>");
			count++;
		}
		
		return strHtml.toString();
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