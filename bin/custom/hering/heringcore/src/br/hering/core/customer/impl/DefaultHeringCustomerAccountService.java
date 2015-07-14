/**
 * 
 */
package br.hering.core.customer.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.hering.core.customer.HeringCustomerAccountService;
import br.hering.core.customer.dao.HeringCustomerAccountDao;

import com.fliegersoftware.newslettersubscription.exceptions.NewsletterSubscriptionNotFound;
import com.fliegersoftware.newslettersubscription.model.NewsletterSubscriptionModel;
import com.fliegersoftware.newslettersubscription.services.NewsletterSubscriptionService;

import de.hybris.platform.commerceservices.customer.impl.DefaultCustomerAccountService;
import de.hybris.platform.commerceservices.enums.CustomerType;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

/**
 * @author herbert
 *
 */
public class DefaultHeringCustomerAccountService extends DefaultCustomerAccountService implements HeringCustomerAccountService
{
	
	private static final Logger LOG = Logger.getLogger(DefaultHeringCustomerAccountService.class);

	@Resource
	private NewsletterSubscriptionService newsletterSubscriptionService;
	
	@Resource
	private ModelService modelService;
		
	@Override
	public OrderModel getOrderForCode(CustomerModel customerModel, String code)
	{
		ServicesUtil.validateParameterNotNull(customerModel, "Customer model cannot be null"); /* 549 */
		ServicesUtil.validateParameterNotNull(code, "Order code cannot be null");
		return getCustomerAccountDao().findOrderByCustomerAndCode(customerModel, code);
	}

	@Override
	public List<OrderModel> getOrderList(CustomerModel customerModel, OrderStatus[] status)
	{
		ServicesUtil.validateParameterNotNull(customerModel, "Customer model cannot be null");
		return getCustomerAccountDao().findOrdersByCustomer(customerModel, status);
	}

	@Override
	public SearchPageData<OrderModel> getOrderList(CustomerModel customerModel, OrderStatus[] status, PageableData pageableData)
	{
		ServicesUtil.validateParameterNotNull(customerModel, "Customer model cannot be null");
		ServicesUtil.validateParameterNotNull(pageableData, "PageableData must not be null");
		return getCustomerAccountDao().findOrdersByCustomer(customerModel, status, pageableData);
	}

	@Override
	protected HeringCustomerAccountDao getCustomerAccountDao()
	{
		return (HeringCustomerAccountDao) super.getCustomerAccountDao();
	}
	
	
	/**
	 * @author luiza
	 */
	@Override
	public void deleteAccount(CustomerModel customerModel)
	{
			
		final String customerEmail = customerModel.getUid();
		final String guid = UUID.randomUUID().toString();
		
		customerModel.setUid(guid + "|" + customerEmail);
		customerModel.setType(CustomerType.valueOf( CustomerType.GUEST.getCode()));

		final Date currentDate = customerModel.getCurrentDate();
		customerModel.setDeletedAccountDate(currentDate);
		
		final Collection<NewsletterSubscriptionModel> customerNewsletterSubscriptions = customerModel.getNewsletterSubscriptions();

		//deleting subscriptions from customer
		if (! customerNewsletterSubscriptions.isEmpty())
		{
			for (NewsletterSubscriptionModel subscription : customerNewsletterSubscriptions)
			{
				try
				{
					newsletterSubscriptionService.unsubscribe(subscription);				
				}
				catch (NewsletterSubscriptionNotFound e)
				{
					//
				}
				
			}
		}
			
		modelService.save(customerModel);
		modelService.refresh(customerModel);
		
	}

	@Override
	public CustomerModel changePhoneNumber(CustomerModel customerModel, String phone)
	{
				
		customerModel.setDefaultPhoneNumber(phone);
		
		modelService.save(customerModel);
		modelService.refresh(customerModel);

		return customerModel;
	}
	
	
	public CustomerModel updateCustomerSubscriptions(CustomerModel customerModel, Boolean tipsNewsletter, Date dateOfBirth)
	{
		
		customerModel.setYoungestChildDateOfBirth(dateOfBirth);
		
		customerModel.setTipsNewsletterEnabled(tipsNewsletter);
		
		modelService.save(customerModel);
		modelService.refresh(customerModel);

		return customerModel;
		
	}
	
	
}
