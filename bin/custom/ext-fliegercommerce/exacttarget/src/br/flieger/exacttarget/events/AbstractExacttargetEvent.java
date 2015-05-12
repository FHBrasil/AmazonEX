/**
 * 
 */
package br.flieger.exacttarget.events;

import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;
import de.hybris.platform.util.Config;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import br.flieger.exacttarget.wsdl.api.APIProperty;
import br.flieger.exacttarget.wsdl.api.Attribute;
import br.flieger.exacttarget.wsdl.api.Subscriber;

/**
 * @author Vinicius de Souza
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractExacttargetEvent extends AbstractEvent
{
	protected final DecimalFormat DECIAL_FORMAT = new DecimalFormat( "#,##0.00" );
	protected final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	protected final int MAX_LENGTH = Integer.parseInt(Config.getParameter("exacttarget.max-string"));
	
	protected String baseStore;
	
	private boolean resultOk;
	private String msgRequest;
	
	protected final Logger LOG = Logger.getLogger(AbstractExacttargetEvent.class);
	
	/**
	 * @return the resultOk
	 */
	public boolean isResultOk()
	{
		return resultOk;
	}
	
	/**
	 * @param resultOk the resultOk to set
	 */
	public void setResultOk(boolean resultOk)
	{
		this.resultOk = resultOk;
	}
	
	/**
	 * @return the msgRequest
	 */
	public String getMsgRequest()
	{
		return msgRequest;
	}

	/**
	 * @param msgRequest the msgRequest to set
	 */
	public void setMsgRequest(String msgRequest)
	{
		this.msgRequest = msgRequest;
	}
	
	/**
	 * @param name
	 * @param value
	 * @return Attribute for send.
	 */
	protected Attribute createAttribute(String name, Object value)
	{
		Attribute a = new Attribute();
		a.setName(name);
		
		if(value != null)
		{
			if(value instanceof Double)
			{
				a.setValue(DECIAL_FORMAT.format(value));
			}
			else if(value instanceof Date)
			{
				a.setValue(DATE_FORMAT.format(value));
			}
			else if(value instanceof PaymentInfoModel)
			{
				String strValue = "-";
				if(value instanceof CreditCardPaymentInfoModel)
				{
					strValue = "Cartão de Crédito";
				}
				else
				{
					strValue = "Boleto";
				}
					
				a.setValue(strValue);
			}
			else if(value instanceof DeliveryModeModel)
			{
				final String strValue = ((DeliveryModeModel) value).getName();
				a.setValue(strValue==null?"-":strValue);
			}
			else
			{
				a.setValue(value.toString());
			}
		}
		else
		{
			a.setValue("-");
		}
		
		return a;
	}
	
	protected List<String> splitMaxLength(final String value, List<String> result) 
	{
		if(result == null)
		{
			result = new ArrayList<>();
		}
			
		if(value != null && value.length() <= MAX_LENGTH)
		{
			result.add(value);
			return result;
		}
		else if(value != null)
		{
			result.add(value.substring(0, MAX_LENGTH));
			return splitMaxLength(value.substring(MAX_LENGTH, value.length()), result);
		}		
		return result;
	}
	
	/**
	 * @return the baseStore
	 */
	protected String getBaseStore()
	{
		return baseStore;
	}
	
	/**
	 * @param baseStore the baseStore to set
	 */
	public void setBaseStore(String baseStore)
	{
		this.baseStore = baseStore;
	}

	/**
	 * Create array attributes.
	 * @return Array of Attributes.
	 */
	public abstract Attribute[] getAttributes();
	
	/**
	 * Define array of Subscribers to send.
	 * @return Array of Subscribers
	 */
	public abstract Subscriber[] getSubscribers();
	
	public abstract APIProperty[] getAPIProperty();
	
	/**
	 * Tags
	 * @return String Tag
	 */
	protected abstract String getLinkTag();
	
	/**
	 * 
	 * @return Default Send Trigger.
	 */
	public abstract String getTrigger();
}