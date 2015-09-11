/**
 * 
 */
package br.flieger.exacttarget.events;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.util.Config;

import java.util.Set;

import org.apache.commons.lang.StringUtils;

import br.flieger.exacttarget.wsdl.api.APIProperty;
import br.flieger.exacttarget.wsdl.api.Attribute;
import br.flieger.exacttarget.wsdl.api.Subscriber;

import com.flieger.notificationservices.model.NotifymeModel;

/**
 * @author Vinicius de Souza
 *
 */
public class NotifyMeProductEvent extends AbstractExacttargetProductEvent
{	
	private static final String PREFIX_SITE = "exacttarget.site.";
	private Set<NotifymeModel> notifications;
	
	/**
	 * @param notifymeModel
	 * @return
	 * @throws Exception 
	 */
	private Attribute[] getAttributes(NotifymeModel notifymeModel) throws Exception
	{
		final Attribute aNome = createAttribute("Nome", notifymeModel.getName());
		final Attribute aProduto = getProduct(notifymeModel.getProduct());
		final Attribute aPrecoAntigo = createAttribute("PrecoAntigo", getOldPrice(getPrice(notifymeModel.getProduct()), notifymeModel.getProduct()));
		final Attribute aPreco = createAttribute("Preco", getPrice(notifymeModel.getProduct()));
		final Attribute aLink = getLink(notifymeModel);
		
		return new Attribute[]{aNome, aProduto, aPrecoAntigo, aPreco, aLink};
	}

	/**
	 * @param notifymeModel
	 * @return Link Attribute
	 */
	private Attribute getLink(NotifymeModel notifymeModel)
	{
		final String link = Config.getParameter(PREFIX_SITE+notifymeModel.getBaseStore().getUid())+"/p/"+notifymeModel.getProduct().getCode()+getLinkTag();
		
		
		return createAttribute("Link", "<a href=\""+link+"\">");
	}

	/**
	 * @param product
	 * @return
	 * @throws Exception 
	 */
	private Attribute getProduct(ProductModel product) throws Exception
	{
		String p = "<p>"+product.getName()+"</p><p>"+getImage(product)+"</p>";
		
		return createAttribute("Produto", p);
	}
/*
	@Override
	public Attribute[] getAttributes()
	{
		final Attribute aNome = createAttribute("Nome", name);
		final Attribute aPrecoAntigo = createAttribute("PrecoAntigo", getOldPrice(getPrice(product)));
		final Attribute aPreco = createAttribute("Preco", getPrice(product));
		
		Attribute [] attributes = null;
		
		switch (getBaseStore())
		{
			case "hering":
				attributes = getAttributesHering();
				break;
			case "dzarm":
				attributes = getAttributesDzarm();
				break;
			default:
				break;
		}
		
		ArrayUtils.add(attributes, aNome);
		ArrayUtils.add(attributes, aPrecoAntigo);
		ArrayUtils.add(attributes, aPreco);
		
		return attributes;			
	}

	private Attribute[] getAttributesHering()
	{
		final Attribute aLink = createAttribute("Link", getSiteUrl()+getLinkTag());
		Attribute aProduto = null;
		
		try
		{
			aProduto = createAttribute("Produto", "<img src=\""+getImageUrl(product)+"\" />");
		}
		catch (Exception e)
		{
			aProduto = createAttribute("Produto", " ");
		}
		
		return new Attribute[]{aLink, aProduto};
	}

	private Attribute[] getAttributesDzarm()
	{
		final Attribute aProduto = createAttribute("Produto", getProductLink(product));

		Attribute aImg;
		
		try
		{
			aImg = createAttribute("Img", getImageUrl(product));
		}
		catch (Exception e)
		{
			aImg = createAttribute("Img", "#");
		}
		
		final Attribute aLink = createAttribute("Link", "<a href=\""+getSiteUrl()+getLinkTag()+"\">");
		
		return new Attribute[] {aProduto, aImg, aLink};
	}
*/
	/**
	 * @param price 
	 * @return Preço antigo formatado.
	 */
	private String getOldPrice(String price, ProductModel product)
	{
		if(product.getOldPrice() != null && DECIAL_FORMAT.format(product.getOldPrice()).compareTo(price) != 0)
		{
			return "de R$ "+DECIAL_FORMAT.format(product.getOldPrice())+" por R$ ";
		}
		return " R$ ";
	}

	public Subscriber[] getSubscribers()
	{
		int count = 0;
		if(notifications != null || notifications.size() > 0)
		{
		
		final Subscriber [] subscribers = new Subscriber[notifications.size()];
		
		for (NotifymeModel notifymeModel : notifications)
		{
			final Subscriber s = new Subscriber();
			
			s.setEmailAddress(notifymeModel.getEmail());
			s.setSubscriberKey(notifymeModel.getEmail());
			s.setCorrelationID(notifymeModel.getEmail()+"->"+notifymeModel.getProduct());
			
			try
			{
				s.setAttributes(getAttributes(notifymeModel));
				subscribers[count] = s;
			}
			catch (Exception e)
			{
				LOG.error("Product hasn't defined", e);
			}
			count++;
		}
		
		return subscribers;
		}
		return null;
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
		String tag = Config.getParameter("exacttarget.tag.notifyme.product");
		
		if(StringUtils.isEmpty(tag))
		{
			tag = "";
		}
		
		return tag;
	}
	
	@Override
	protected String getProductLink(ProductModel product)
	{
		if(StringUtils.isNotEmpty(getSiteUrl()))
		{
			return "<a href=\""+getSiteUrl()+getLinkTag()+"\">"+product.getName()+"</a>";
		}
		return "<a href=\"#\">"+product.getName()+"</a>";
	}

	@Override
	public String getTrigger()
	{
		return Config.getParameter("exacttarget.notifyme-product.trigger."+getBaseStore());
	}

	/**
	 * @param notifications
	 */
	public void setNotifications(Set<NotifymeModel> notifications)
	{
		this.notifications = notifications;		
	}
	
	/**
	 * @return the notifications
	 */
	public Set<NotifymeModel> getNotifications()
	{
		return notifications;
	}

	@Override
	public Attribute[] getAttributes()
	{
		return null;
	}
}