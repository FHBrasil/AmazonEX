/**
 * 
 */
package br.hering.core.customer.impl;

import org.springframework.security.web.authentication.logout.LogoutFilter;
import de.hybris.platform.commerceservices.customer.dao.CustomerAccountDao;
import de.hybris.platform.commerceservices.customer.impl.DefaultCustomerAccountService;
import de.hybris.platform.commerceservices.enums.CustomerType;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.security.auth.AuthenticationService;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.fliegersoftware.newslettersubscription.exceptions.NewsletterSubscriptionNotFound;
import com.fliegersoftware.newslettersubscription.model.NewsletterSubscriptionModel;
import com.fliegersoftware.newslettersubscription.services.NewsletterSubscriptionService;

import br.hering.core.customer.HeringCustomerAccountService;
import br.hering.core.customer.dao.HeringCustomerAccountDao;

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
	private AuthenticationService authenticationService;
	
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
	
	@Override
	public void DeleteAccount(CustomerModel customerModel)
	{

		final String customerEmail = customerModel.getUid();
		final String guid = UUID.randomUUID().toString();
		
		customerModel.setUid(guid + "|" + customerEmail);
		customerModel.setType(CustomerType.valueOf( CustomerType.GUEST.getCode()));

		final String customerUid = customerModel.getUid();
		LOG.info("Customer uid: " + customerUid);
		
		final Date currentDate = customerModel.getCurrentDate();
		LOG.info("current date: " + currentDate);
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
		
/*		try
		{

			authenticationService.logout();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}*/
		

		//redirect to login page
		//message account deleted
		
	}
	
}
