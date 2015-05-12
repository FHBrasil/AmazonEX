/**
 * 
 */
package br.flieger.exacttarget.events;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.util.Config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.flieger.exacttarget.wsdl.api.APIProperty;
import br.flieger.exacttarget.wsdl.api.Attribute;
import br.flieger.exacttarget.wsdl.api.Subscriber;

import com.flieger.notificationservices.data.NotifymeData;

/**
 * @author Vinicius de Souza
 *
 */
@SuppressWarnings("serial")
public class NotifyMeSimilarProductEvent extends AbstractExacttargetProductEvent
{
	@Resource
	protected CommonI18NService commonI18NService;
	
	private NotifymeData notifymeData;
	private List<ProductModel> similars;
	private ProductModel product;
	
	private final int QTD_ATTRIBUTES_ADDICIONAL = 2;

	private static final Logger LOG = Logger.getLogger(NotifyMeSimilarProductEvent.class);
	
	@Override
	public Attribute[] getAttributes()
	{
		final Attribute name = createAttribute("Nome", notifymeData.getName());
		final Attribute produto = createAttribute("Produto", product.getName());
		
		
		final List<Attribute> listGridSimilar = createGridSimilar();
		
		final Attribute[] arrayAttr = new Attribute[listGridSimilar.size() + QTD_ATTRIBUTES_ADDICIONAL];
		
		arrayAttr[0] = name;
		arrayAttr[1] = produto;
		
		for (int i = QTD_ATTRIBUTES_ADDICIONAL; i < arrayAttr.length; i++)
		{
			arrayAttr[i] = listGridSimilar.get(i - QTD_ATTRIBUTES_ADDICIONAL);
		}
		
		return arrayAttr;
	}

	private List<Attribute> createGridSimilar()
	{
		commonI18NService.setCurrentLanguage(commonI18NService.getLanguage("pt"));
		commonI18NService.setCurrentCurrency(commonI18NService.getCurrency("BRL"));
		
		final int qtdGridColum = Integer.parseInt(Config.getParameter("exacttarget.qtd-grid-colum"));
		int count = 0;
		
		final StringBuilder grid = new StringBuilder();
		
		grid.append("<table width=\"600\" align=\"center\">\n");
		grid.append("<tr>\n");
		
		for (ProductModel product : similars)
		{
			if(count < qtdGridColum)
			{
				grid.append("<td>"+ createProductGrid(product) +"</td>\n");
			}
			else
			{
				grid.append("</tr>\n");
				grid.append("<tr>\n");
				grid.append("<td>"+ createProductGrid(product) +"</td>\n");
				count = 0;
			}
			
			count++;
		}
		
		for (;count < qtdGridColum; count++)
		{
			grid.append("<td> </td>\n");
		}
		
		grid.append("</tr>\n");
		grid.append("</table>\n");

		LOG.info(grid.length());
		
		final List<Attribute> gSimilar = new ArrayList<Attribute>();

		int cont = 1;
		for (String value : splitMaxLength(grid.toString(), null))
		{
			gSimilar.add(new Attribute("GridSimilar"+cont, value, null));
			cont++;
		}
		
		return gSimilar;
	}

	/**
	 * Creates a html table of product.
	 * @param product Product.
	 * @return Formated string.
	 */
	private String createProductGrid(ProductModel product)
	{
		if(product != null)
		{
			try
			{
				return "<table align=\"center\">"
						+ "<tr>"
						+ "<th>"+product.getName()+"</th>"
						+ "</tr>"
						+ "<tr>"
						+ "<td>"+product.getCode()+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td>"+getImageProductLink(product)+"</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td>"+getPrice(product)+"</td>"
						+ "</tr>"
						+ "</table>";
			}
			catch (Exception e)
			{
				LOG.error("Table's product has crashed!", e);
			}
		}
		return "";
	}

	@Override
	public Subscriber[] getSubscribers()
	{
		final Subscriber s = new Subscriber();
		
		s.setEmailAddress(notifymeData.getEmail());
		s.setSubscriberKey(notifymeData.getEmail());
		
		s.setAttributes(getAttributes());
		
		return new Subscriber[] {s};
	}

	@Override
	public APIProperty[] getAPIProperty()
	{
		return null;
	}
	
	/**
	 * @param notifymeData the notifymeData to set
	 */
	public void setNotifymeData(NotifymeData notifymeData)
	{
		this.notifymeData = notifymeData;
		this.baseStore = notifymeData.getBaseStore();
	}
	
	/**
	 * @param similars the similars to set
	 */
	public void setSimilars(List<ProductModel> similars)
	{
		this.similars = similars;
	}

	@Override
	protected String getLinkTag()
	{
		String tag = Config.getParameter("exacttarget.tag.notifyme.similar-product");
		
		if(StringUtils.isEmpty(tag))
		{
			tag = "";
		}
		
		return tag;
	}

	@Override
	public String getTrigger()
	{
		return Config.getParameter("exacttarget.notifyme-similar-product.trigger."+baseStore);
	}
	
	public void setProduct(ProductModel product)
	{
		this.product = product;				
	}
}