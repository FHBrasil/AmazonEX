/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package de.fliegersoftware.amazon.payment.test;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.commerceservices.delivery.DeliveryService;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.enums.CreditCardType;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.CalculationService;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.payment.dto.CardInfo;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction.Transition;
import de.hybris.platform.processengine.definition.ProcessDefinitionFactory;
import de.hybris.platform.processengine.impl.DefaultBusinessProcessService;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.task.RetryLaterException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;  import org.slf4j.LoggerFactory;
import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import de.fliegersoftware.amazon.core.model.AmazonPaymentPaymentInfoModel;
import de.fliegersoftware.amazon.payment.services.AmazonCommerceCheckoutService;
import de.fliegersoftware.amazon.payment.services.AmazonPaymentService;
 

@IntegrationTest
public class PaymentIntegrationTest extends ServicelayerTest
{
	private static final Logger LOG = LoggerFactory.getLogger(PaymentIntegrationTest.class);

	@Resource
	protected AmazonCommerceCheckoutService commerceCheckoutService;
	@Resource
	protected CommonI18NService commonI18NService;
	@Resource
	protected AmazonPaymentService paymentService;
	@Resource
	protected CalculationService calculationService;
	@Resource
	protected BaseSiteService baseSiteService;
	@Resource
	protected DeliveryService deliveryService;

	private static DefaultBusinessProcessService processService;

	private static ProductService productService;
	private static CartService cartService;
	private static ModelService modelService;
	private static UserService userService;
	
//	@Mock
//	ModelService modelService;

	@InjectMocks
	private final CheckAuthorizeOrderPaymentAction checkAuthorizeOrderPayment = new CheckAuthorizeOrderPaymentAction();

	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testExecuteActionOK() throws RetryLaterException, Exception
	{
		final OrderProcessModel businessProcessModel = new OrderProcessModel();

		final OrderModel order = new OrderModel();
		final PaymentTransactionModel paymentTransaction = new PaymentTransactionModel();
		final PaymentTransactionEntryModel entry = new PaymentTransactionEntryModel();
		entry.setType(PaymentTransactionType.AUTHORIZATION);
		entry.setTransactionStatus(TransactionStatus.ACCEPTED.name());
		paymentTransaction.setEntries(Arrays.asList(entry));
		businessProcessModel.setOrder(order);
		order.setPaymentTransactions(Arrays.asList(paymentTransaction));

		Assertions.assertThat(checkAuthorizeOrderPayment.executeAction(businessProcessModel)).isEqualTo(Transition.OK);
	}

	@Test
	public void testExecuteActionNOK() throws RetryLaterException, Exception
	{
		final OrderProcessModel businessProcessModel = new OrderProcessModel();

		final OrderModel order = new OrderModel();
		final PaymentTransactionModel paymentTransaction = new PaymentTransactionModel();
		final PaymentTransactionEntryModel entry = new PaymentTransactionEntryModel();
		entry.setType(PaymentTransactionType.AUTHORIZATION);
		entry.setTransactionStatus(TransactionStatus.REJECTED.name());
		paymentTransaction.setEntries(Arrays.asList(entry));
		businessProcessModel.setOrder(order);
		order.setPaymentTransactions(Arrays.asList(paymentTransaction));
		Assertions.assertThat(checkAuthorizeOrderPayment.executeAction(businessProcessModel)).isEqualTo(Transition.NOK);
	}

	
	protected static int codeNo = 1;
	
	protected OrderModel placeTestOrder(final boolean valid) throws InvalidCartException, CalculationException
	{
		final CartModel cart = cartService.getSessionCart();
		final UserModel user = userService.getCurrentUser();
		cartService.addNewEntry(cart, productService.getProductForCode("testProduct1"), 1, null);
		cartService.addNewEntry(cart, productService.getProductForCode("testProduct2"), 2, null);
		cartService.addNewEntry(cart, productService.getProductForCode("testProduct3"), 3, null);

		final AddressModel deliveryAddress = new AddressModel();
		deliveryAddress.setOwner(user);
		deliveryAddress.setFirstname("Der");
		deliveryAddress.setLastname("Buck");
		deliveryAddress.setTown("Muenchen");
		deliveryAddress.setCountry(commonI18NService.getCountry("DE"));
		modelService.save(deliveryAddress);

		final AmazonPaymentPaymentInfoModel paymentInfo = new AmazonPaymentPaymentInfoModel();
		paymentInfo.setOwner(cart);
		paymentInfo.setUser(user);
		paymentInfo.setCode("testPaymentInfo1");
		modelService.save(paymentInfo);

		cart.setDeliveryMode(deliveryService.getDeliveryModeForCode("free"));
		cart.setDeliveryAddress(deliveryAddress);
		cart.setPaymentInfo(paymentInfo);

		final CardInfo card = new CardInfo();
		card.setCardType(CreditCardType.VISA);
		card.setCardNumber("4111111111111111");
		card.setExpirationMonth(Integer.valueOf(12));
		if (valid)
		{
			card.setExpirationYear(Integer.valueOf(Calendar.getInstance().get(Calendar.YEAR) + 2));
		}
		else
		{
			card.setExpirationYear(Integer.valueOf(Calendar.getInstance().get(Calendar.YEAR) - 2));
		}

		final PaymentTransactionModel paymentTransaction = paymentService.authorize(paymentInfo, "code4" + codeNo++, BigDecimal.ONE,
				Currency.getInstance("EUR"), "Amazon").getPaymentTransaction();
		
		cart.setPaymentTransactions(Collections.singletonList(paymentTransaction));
		modelService.save(cart);
		calculationService.calculate(cart);

		return commerceCheckoutService.placeOrder(cart);
	}

	protected OrderProcessModel createProcess(final String processName)
	{
		final String id = "Test" + (new Date()).getTime();
		return processService.startProcess(id, processName);
	}

	protected static Object getBean(final String name)
	{
		return Registry.getApplicationContext().getBean(name);
	}
}
