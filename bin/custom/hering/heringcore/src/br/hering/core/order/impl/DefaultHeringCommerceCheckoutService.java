package br.hering.core.order.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.commerceservices.order.impl.DefaultCommerceCheckoutService;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.stock.exception.InsufficientStockLevelException;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.util.Config;
import de.hybris.platform.voucher.VoucherService;
import de.hybris.platform.wishlist2.model.Wishlist2EntryModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Currency;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import br.hering.core.model.HeringWishlistModel;
import br.hering.core.order.dao.HeringOrderDao;
import br.hering.core.wishlist.HeringWishlistService;

import com.adyen.services.payment.AdyenPaymentService;
import com.flieger.main.Credentials;
import com.flieger.payment.model.BoletoPaymentInfoModel;
import com.flieger.payment.model.HeringDebitPaymentInfoModel;

/**
 * @author franthescollymaneira
 */
public class DefaultHeringCommerceCheckoutService 
extends DefaultCommerceCheckoutService 
implements HeringCommerceCheckoutService
{
	private final Logger LOG = Logger.getLogger(getClass());

	
	private final static String DEFAULT_STORE_UID = "dzarm";
	
	private VoucherService voucherService;
	private SessionService sessionService;
	private HeringWishlistService heringWishlistService;
	private UserService userService;

	@Resource(name = "heringOrderDao")
	private HeringOrderDao heringOrderDao;

	@Resource
	private StockService stockService;

	@Override
	public OrderModel placeOrder(final CartModel cart) throws InvalidCartException
	{
		return placeOrder(cart, SalesApplication.WEB);
	}

	@Override
	public OrderModel placeOrder(final CartModel cartModel, final SalesApplication salesApplication) throws InvalidCartException
	{
		final OrderModel orderModel = super.placeOrder(cartModel, salesApplication);

		postOrderActions(cartModel, orderModel);

		return orderModel;
	}

	@Override
	public boolean setBillingAddress(final CartModel cartModel, final AddressModel addressModel)
	{
		ServicesUtil.validateParameterNotNull(cartModel, "Cart model cannot be null");
		getModelService().refresh(cartModel);

		final UserModel user = cartModel.getUser();
		getModelService().refresh(user);

		cartModel.setPaymentAddress(addressModel);
		getModelService().saveAll(cartModel, addressModel);

		final PaymentInfoModel paymentInfo = cartModel.getPaymentInfo();
		if (paymentInfo != null)
		{
			getModelService().refresh(paymentInfo);
			paymentInfo.setBillingAddress(addressModel);
			getModelService().save(paymentInfo);
		}

		getCommerceCartCalculationStrategy().calculateCart(cartModel);

		getModelService().refresh(cartModel);

		return true;
	}

	//TODO solucao temporaria, precisamos procurar por listeners ou algo do genero para a criacao de pedidos e processamento paralelos
	private void postOrderActions(final CartModel cart, final OrderModel order)
	{
		reserveStock(order);
		updateWishlist(cart);
		createVoucherInvalidations(cart, order);
	}

	/**
	 * @param order
	 */
	private void reserveStock(OrderModel order)
	{
		Assert.notNull(order, "order is null");
		Assert.isTrue(CollectionUtils.isNotEmpty(order.getEntries()), "order is empty");

		WarehouseModel warehouse = order.getStore().getWarehouses().iterator().next();

		for (final AbstractOrderEntryModel entry : order.getEntries())
		{
			final ProductModel product = entry.getProduct();
			final Long quantity = entry.getQuantity();

			try
			{
				stockService.reserve(product, warehouse, quantity.intValue(), "reserved by order: " + order.getCode());
			}
			catch (InsufficientStockLevelException e)
			{
				LOG.error("Error reserving stock:" + " order: " + order.getCode() + " product: " + product.getCode() + " qty: "
						+ quantity.intValue(), e);
			}
		}
	}

	private void createVoucherInvalidations(final CartModel cart, final OrderModel order)
	{
		final Collection<String> appliedVouchers = voucherService.getAppliedVoucherCodes(cart);

		if (CollectionUtils.isNotEmpty(appliedVouchers))
		{
			for (final String code : appliedVouchers)
			{
				voucherService.createVoucherInvalidation(code, order);
			}
		}
	}

	private void updateWishlist(final CartModel cart)
	{

		String wishlistPK;
		HeringWishlistModel wishlist;
		UserModel user;

		if (sessionService.getAttribute("wishlistPK") != null
				&& StringUtils.isNotBlank(sessionService.getAttribute("wishlistPK").toString()))
		{

			wishlistPK = sessionService.getAttribute("wishlistPK").toString();
			wishlist = getHeringWishlistService().getHeringWishlistByPK(PK.parse(wishlistPK));

			if (!wishlist.getUser().getPk().toString().equals(userService.getCurrentUser().getPk().toString()))
			{
				user = wishlist.getUser();
			}
			else
			{
				return;
			}

		}
		else
		{
			return;
		}

		final List<AbstractOrderEntryModel> cartEntries = cart.getEntries();

		for (final AbstractOrderEntryModel orderEntryModel : cartEntries)
		{

			if (getHeringWishlistService().hasWishlisEntryForProduct(orderEntryModel.getProduct(), wishlist))
			{

				for (final Wishlist2EntryModel wishlistEntry : wishlist.getEntries())
				{

					if (orderEntryModel.getProduct().getCode().equals(wishlistEntry.getProduct().getCode()))
					{

						if (wishlistEntry.getReceived() != null)
						{
							wishlistEntry.setReceived(wishlistEntry.getReceived() + orderEntryModel.getQuantity().intValue());
						}
						else
						{
							wishlistEntry.setReceived(0);
							wishlistEntry.setReceived(wishlistEntry.getReceived() + orderEntryModel.getQuantity().intValue());
						}
					}

				}
			}
		}

		final List<Wishlist2EntryModel> entriesModel = new ArrayList<>();
		for (final Wishlist2EntryModel wishlistEntry : wishlist.getEntries())
		{

			final Wishlist2EntryModel entry = new Wishlist2EntryModel();
			entry.setAddedDate(wishlistEntry.getAddedDate());
			entry.setComment(wishlistEntry.getComment());
			entry.setDesired(wishlistEntry.getDesired());
			entry.setPriority(wishlistEntry.getPriority());
			entry.setProduct(wishlistEntry.getProduct());
			entry.setReceived(wishlistEntry.getReceived());
			entry.setWishlist(wishlist);
			entriesModel.add(entry);
		}

		wishlist.setEntries(entriesModel);
		getHeringWishlistService().updateHeringWishlist(wishlist, user);

	}

	@Override
	public boolean setDeliveryMode(final CartModel cartModel, final DeliveryModeModel deliveryModeModel)
	{
		ServicesUtil.validateParameterNotNull(cartModel, "Cart model cannot be null");
		ServicesUtil.validateParameterNotNull(deliveryModeModel, "Delivery mode model cannot be null");
		cartModel.setDeliveryMode(deliveryModeModel);
		getModelService().save(cartModel);
		getCommerceCartCalculationStrategy().calculateCart(cartModel);
		return cartModel.getDeliveryMode() != null;
	}

	@Override
	protected PaymentTransactionEntryModel authorizePaymentAmount(
			final CartModel cartModel, 
			final String securityCode,
			final String paymentProvider, 
			final BigDecimal amount)
	{
		//resolve the merchant account
		String baseStoreUid = DEFAULT_STORE_UID;
		if(cartModel.getStore() != null 
				&& StringUtils.isNotBlank(cartModel.getStore().getUid()))
		{
			baseStoreUid = cartModel.getStore().getUid();
		}	
		final String merchantAccount = Config.getParameter(Credentials.MERCHANT_ACCOUNT + "." + baseStoreUid);
		
		PaymentTransactionEntryModel transactionEntryModel = null;
		PaymentInfoModel paymentInfo = cartModel.getPaymentInfo();
		if (paymentInfo instanceof CreditCardPaymentInfoModel)
		{
			Currency currency = getI18nService().getBestMatchingJavaCurrency(cartModel.getCurrency().getIsocode());
			String merchantTransactionCode = getGenerateMerchantTransactionCodeStrategy().generateCode(cartModel);
			
			transactionEntryModel = getPaymentService().authorize(merchantTransactionCode, amount, currency,
					cartModel.getDeliveryAddress(), 
					((CreditCardPaymentInfoModel) paymentInfo).getSubscriptionId(), 
					paymentProvider, merchantAccount);
			if (transactionEntryModel != null)
			{
				PaymentTransactionModel paymentTransaction = transactionEntryModel.getPaymentTransaction();

				if ((TransactionStatus.ACCEPTED.name().equals(transactionEntryModel.getTransactionStatus()))
						|| (TransactionStatus.REVIEW.name().equals(transactionEntryModel.getTransactionStatus())))
				{
					paymentTransaction.setOrder(cartModel);
					paymentTransaction.setInfo(paymentInfo);
					getModelService().saveAll(new Object[]
					{ cartModel, paymentTransaction });
				}
				else
				{
					getModelService().removeAll(Arrays.asList(new ItemModel[]
					{ paymentTransaction, transactionEntryModel }));
				}
			}
		}
		else if (paymentInfo instanceof BoletoPaymentInfoModel)
		{
			Currency currency = getI18nService().getBestMatchingJavaCurrency(cartModel.getCurrency().getIsocode());
			String merchantTransactionCode = getGenerateMerchantTransactionCodeStrategy().generateCode(cartModel);
			transactionEntryModel = getPaymentService().authorize(
					merchantTransactionCode, amount, currency,
					cartModel.getDeliveryAddress(), paymentProvider, merchantAccount);
			if (transactionEntryModel != null)
			{
				PaymentTransactionModel paymentTransaction = transactionEntryModel.getPaymentTransaction();

				if ((TransactionStatus.ACCEPTED.name().equals(transactionEntryModel.getTransactionStatus()))
						|| (TransactionStatus.REVIEW.name().equals(transactionEntryModel.getTransactionStatus())))
				{
					paymentTransaction.setOrder(cartModel);
					paymentTransaction.setInfo(paymentInfo);
					getModelService().saveAll(new Object[]
					{ cartModel, paymentTransaction });
				}
				else
				{
					getModelService().removeAll(Arrays.asList(new ItemModel[]
					{ paymentTransaction, transactionEntryModel }));
				}
			}
		}
		else if (paymentInfo instanceof HeringDebitPaymentInfoModel)
		{
			final String merchantTransactionCode = getGenerateMerchantTransactionCodeStrategy().generateCode(cartModel);
			((HeringDebitPaymentInfoModel)paymentInfo).setMerchantTransactionCode(merchantTransactionCode);
			
			final Currency currency = getI18nService().getBestMatchingJavaCurrency(cartModel.getCurrency().getIsocode());
			((HeringDebitPaymentInfoModel)paymentInfo).setCurrency(currency.getCurrencyCode());
			
			((HeringDebitPaymentInfoModel)paymentInfo).setAmount(amount);
			
			transactionEntryModel = getPaymentService().authorizeDebit((HeringDebitPaymentInfoModel)paymentInfo,
					paymentProvider, merchantAccount);
			if (transactionEntryModel != null)
			{
				PaymentTransactionModel paymentTransaction = transactionEntryModel.getPaymentTransaction();

				if ((TransactionStatus.ACCEPTED.name().equals(transactionEntryModel.getTransactionStatus()))
						|| (TransactionStatus.REVIEW.name().equals(transactionEntryModel.getTransactionStatus())))
				{
					paymentTransaction.setOrder(cartModel);
					paymentTransaction.setInfo(paymentInfo);
					getModelService().saveAll(new Object[]
					{ cartModel, paymentTransaction });
				}
				else
				{
					getModelService().removeAll(Arrays.asList(new ItemModel[]
					{ paymentTransaction, transactionEntryModel }));
				}
			}
		}
		return transactionEntryModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.hering.core.order.impl.HeringCommerceCheckoutService#setPaymentMode(de.hybris.platform.core.model.order.CartModel
	 * )
	 */
	@Override
	public void setPaymentMode(CartModel cartModel, PaymentModeModel paymentModeModel)
	{
		ServicesUtil.validateParameterNotNull(cartModel, "Cart model cannot be null");
		ServicesUtil.validateParameterNotNull(paymentModeModel, "Peyment mode model cannot be null");
		cartModel.setPaymentMode(paymentModeModel);
		getModelService().save(cartModel);
		getModelService().refresh(cartModel);
		getCommerceCartCalculationStrategy().calculateCart(cartModel);
	}

	public VoucherService getVoucherService()
	{
		return voucherService;
	}

	public void setVoucherService(final VoucherService voucherService)
	{
		this.voucherService = voucherService;
	}

	public SessionService getSessionService()
	{
		return sessionService;
	}

	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

//	public AdyenPaymentService getAdyenPaymentService()
//	{
//		return (AdyenPaymentService) getPaymentService();
//	}

	public HeringWishlistService getHeringWishlistService()
	{
		return heringWishlistService;
	}

	public void setHeringWishlistService(final HeringWishlistService heringWishlistService)
	{
		this.heringWishlistService = heringWishlistService;
	}

	/**
	 * @param cartModel
	 * @param paymentProvider
	 * @return
	 */
	@Override
	public PaymentTransactionEntryModel authorizeDebitPayment(final CartModel cartModel, final String paymentProvider)
	{
		ServicesUtil.validateParameterNotNull(cartModel, "Cart model cannot be null");
		ServicesUtil.validateParameterNotNull(cartModel.getPaymentInfo(), "Payment information on cart cannot be null");

		Double totalPrice = cartModel.getTotalPrice();

		Double totalTax = ((cartModel.getNet().booleanValue()) && (cartModel.getStore() != null) && (cartModel.getStore()
				.getExternalTaxEnabled().booleanValue())) ? cartModel.getTotalTax() : Double.valueOf(0.0D);

		BigDecimal totalPriceWithoutTaxBD = new BigDecimal((totalPrice == null) ? 0.0D : totalPrice.doubleValue()).setScale(2,
				RoundingMode.HALF_EVEN);

		BigDecimal amount = new BigDecimal((totalTax == null) ? 0.0D : totalTax.doubleValue()).setScale(2,
				RoundingMode.HALF_EVEN).add(totalPriceWithoutTaxBD);

		return authorizePaymentAmount(cartModel, null, paymentProvider, amount);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.hering.core.order.impl.HeringCommerceCheckoutService#getOrderStatusByCode(java.lang.String)
	 */
	@Override
	public OrderStatus getOrderStatusByCode(String code)
	{
		return getHeringOrderDao().findOrderStatusByCode(code);
	}

	/**
	 * @return the heringOrderDao
	 */
	public HeringOrderDao getHeringOrderDao()
	{
		return heringOrderDao;
	}

	/**
	 * @param heringOrderDao
	 *           the heringOrderDao to set
	 */
	public void setHeringOrderDao(HeringOrderDao heringOrderDao)
	{
		this.heringOrderDao = heringOrderDao;
	}

	/**
	 * @param cartModel
	 * @param paymentProvider
	 * @return
	 */
	@Override
	public PaymentTransactionEntryModel authorizeDebitPayment3D(
			final HeringDebitPaymentInfoModel debitPaymentInfoModel)
	{
		return getPaymentService().authorizeDebitPayment3D(debitPaymentInfoModel);
	}
	
	/* (non-Javadoc)
	 * @see de.hybris.platform.commerceservices.order.impl.DefaultCommerceCheckoutService#getPaymentService()
	 */
	@Override
	protected AdyenPaymentService getPaymentService()
	{
		return (AdyenPaymentService)super.getPaymentService();
	}
}