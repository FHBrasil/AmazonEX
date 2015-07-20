/**
 * 
 */
package br.hering.facades.order.impl;

import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.data.OrderHistoryData;
import de.hybris.platform.commercefacades.order.impl.DefaultOrderFacade;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import br.hering.core.customer.HeringCustomerAccountService;
import br.hering.facades.order.HeringOrderFacade;
import br.hering.fulfilmentprocess.services.HeringOrderService;
import br.hering.fulfilmentprocess.services.impl.InvalidBoletoException;

/**
 * @author mjammal
 *
 */
public class DefaultHeringOrderFacade extends DefaultOrderFacade implements HeringOrderFacade
{
	private HeringOrderService orderService;

	@Override
	public String getAvailableLinkNfeForCode(final int nfeCode)
	{
	   // FIXME Implement  the available link from PI/Linx to Nfe for code.
		final String availableLinkToNfe = "http://hering.fliegersoftware.de/hmc/hybris";
		if (availableLinkToNfe == null)
		{
			throw new UnknownIdentifierException("Nfe with code " + nfeCode + " not found for current order in current PI/Linx");
		}
		return availableLinkToNfe;
	}
	
	@Override
	public String getBoletoURL(String code) 
	{
		return getOrderService().getBoletoURL(getOrder(code));
	}

	/**
	 * Custom implementation for hering that returns all orders, independent of store or site
	 */
	@Override
	public List<OrderHistoryData> getOrderHistoryForStatuses(OrderStatus... statuses)
	{
		final CustomerModel currentCustomer = (CustomerModel) getUserService().getCurrentUser();
		final List<OrderModel> orderList = getCustomerAccountService().getOrderList(currentCustomer, statuses);
		return Converters.convertAll(orderList, getOrderHistoryConverter());
	}

	/**
	 * Custom implementation for hering that returns all orders, independent of store or site
	 */
	@Override
	public SearchPageData<OrderHistoryData> getPagedOrderHistoryForStatuses(PageableData pageableData, OrderStatus... statuses)
	{
		final CustomerModel currentCustomer = (CustomerModel) getUserService().getCurrentUser();
		final SearchPageData<OrderModel> orderResults = getCustomerAccountService().getOrderList(currentCustomer, statuses, pageableData);

		return convertPageData(orderResults, getOrderHistoryConverter());
	}

	/**
	 * Custom implementation for hering that return an order, independent of store or site
	 */
	@Override
	public OrderData getOrderDetailsForCode(String code)
	{
		return getOrderConverter().convert(getOrder(code));
	}

	@Override
	public boolean generateBoleto(String code)
	{
		return getOrderService().boletoGenerator(getOrder(code));
	}

	@Override
	public void validateBoleto(String code) throws InvalidBoletoException {
		getOrderService().validateBoleto(getOrder(code));
	}

	protected OrderModel getOrder(String code) {
		final BaseStoreModel baseStoreModel = getBaseStoreService().getCurrentBaseStore();
		final OrderModel orderModel = getCheckoutCustomerStrategy().isAnonymousCheckout()?
				getCustomerAccountService().getOrderDetailsForGUID(code,baseStoreModel):
				getCustomerAccountService().getOrderForCode((CustomerModel)getUserService().getCurrentUser(), code);
		if (orderModel == null)
		{
			throw new UnknownIdentifierException("Order with orderGUID " + code + " not found for current user in current BaseStore");
		}
		return orderModel;
	}

	@Override
	protected HeringCustomerAccountService getCustomerAccountService()
	{
		return super.getCustomerAccountService();
	}

	protected HeringOrderService getOrderService()
	{
		return orderService;
	}

	@Required
	public void setOrderService(HeringOrderService orderService)
	{
		this.orderService = orderService;
	}
}