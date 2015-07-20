/**
 *
 */
package com.flieger.eventtracking.facades.impl;

import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.flieger.eventtracking.data.EventTrackRequest;
import com.flieger.eventtracking.facade.EventTrackerFacade;
import com.flieger.eventtracking.services.EventTrackerService;
import com.flieger.eventtracking.strategies.FindCustomerUUIDForEventTracking;


/**
 * @author franthescollymaneira
 *
 */
public class DefaultEventTrackerFacade implements EventTrackerFacade
{
	private EventTrackerService eventTrackerService;

	private ProductService productService;

	private CategoryService categoryService;

	private CartService cartService;

	private OrderService orderService;

	private UserService userService;

	private FindCustomerUUIDForEventTracking findCustomerUUIDForEventTracking;

	@Override
	public void trackProductView(final ProductData product)
	{
		Assert.notNull(product);

		final ProductModel productModel = getProductService().getProductForCode(product.getCode());
		final EventTrackRequest eventTrackRequest = createEventTrackRequest();

		eventTrackRequest.getValues().put("product", productModel);

		getEventTrackerService().trackProductView(eventTrackRequest);
	}

	@Override
	public void trackCategoryView(final CategoryData category)
	{
		Assert.notNull(category);

		final CategoryModel categoryModel = categoryService.getCategoryForCode(category.getCode());
		final EventTrackRequest eventTrackRequest = createEventTrackRequest();

		eventTrackRequest.getValues().put("category", categoryModel);

		getEventTrackerService().trackCategoryView(eventTrackRequest);
	}

	@Override
	public void trackClickEvent(final Map<String, Object> clickData)
	{
		final EventTrackRequest eventTrackRequest = createEventTrackRequest();
		eventTrackRequest.getValues().putAll(clickData);

		getEventTrackerService().trackClickEvent(eventTrackRequest);
	}

	@Override
	public void trackBasketEvent()
	{
		final CartModel cartModel = getCartService().getSessionCart();
		final EventTrackRequest eventTrackRequest = createEventTrackRequest();

		eventTrackRequest.getValues().put("basket", cartModel);

		getEventTrackerService().trackBasketEvent(eventTrackRequest);
	}

	@Override
	public void trackOrderEvent(final OrderData order)
	{
		final OrderModel orderModel = new OrderModel();//TODO precisa recuperar o pedido aqui
		final EventTrackRequest eventTrackRequest = createEventTrackRequest();

		eventTrackRequest.getValues().put("order", orderModel);

		getEventTrackerService().trackOrderEvent(eventTrackRequest);
	}

	@Override
	public void trackUserToSessionEvent(final CustomerData customer)
	{
		final CustomerModel customerModel = (CustomerModel) userService.getUserForUID(customer.getUid());
		final EventTrackRequest eventTrackRequest = createEventTrackRequest();

		eventTrackRequest.getValues().put("customer", customerModel);

		getEventTrackerService().trackUserToSessionEvent(eventTrackRequest);
	}

	private EventTrackRequest createEventTrackRequest()
	{
		final CustomerModel customer = (CustomerModel) getUserService().getCurrentUser();

		final EventTrackRequest request = new EventTrackRequest();
		request.setValues(new HashMap<String, Object>());
		request.getValues().put("sessionId", getFindCustomerUUIDForEventTracking().findCustomerUUID(customer));

		return request;
	}

	public EventTrackerService getEventTrackerService()
	{
		return eventTrackerService;
	}

	@Required
	public void setEventTrackerService(final EventTrackerService eventTrackerService)
	{
		this.eventTrackerService = eventTrackerService;
	}

	public ProductService getProductService()
	{
		return productService;
	}

	@Required
	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}

	public CategoryService getCategoryService()
	{
		return categoryService;
	}

	@Required
	public void setCategoryService(final CategoryService categoryService)
	{
		this.categoryService = categoryService;
	}

	public CartService getCartService()
	{
		return cartService;
	}

	@Required
	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
	}

	public UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	public OrderService getOrderService()
	{
		return orderService;
	}

	@Required
	public void setOrderService(final OrderService orderService)
	{
		this.orderService = orderService;
	}

	public FindCustomerUUIDForEventTracking getFindCustomerUUIDForEventTracking()
	{
		return findCustomerUUIDForEventTracking;
	}

	@Required
	public void setFindCustomerUUIDForEventTracking(final FindCustomerUUIDForEventTracking findCustomerUUIDForEventTracking)
	{
		this.findCustomerUUIDForEventTracking = findCustomerUUIDForEventTracking;
	}
}