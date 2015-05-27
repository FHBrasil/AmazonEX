/**
 * 
 */
package br.hering.facades.facades.order.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.hering.core.customer.impl.KPCustomerAccountService;
import br.hering.core.model.order.payment.VoucherPaymentInfoModel;
import br.hering.core.order.impl.DefaultHeringCommerceCheckoutService;
import br.hering.core.payment.dto.HeringBillingInfo;
import br.hering.core.payment.dto.KpCardInfo;
import br.hering.facades.facades.order.HeringCheckoutFacade;
import br.hering.facades.order.data.CustomPaymentInfoData;
import br.hering.facades.order.data.PaymentModeData;
import br.hering.facades.order.data.VoucherPaymentInfoData;
import br.hering.facades.populators.CartPaymentModePopulator;
import br.hering.facades.populators.DebitPaymentInfoReversePopulator;
import br.hering.facades.populators.HeringCustomerPopulator;
import br.hering.facades.populators.PaymentModePopulator;
import br.hering.facades.populators.VoucherPaymentInfoPopulator;
import br.hering.facades.populators.VoucherPaymentInfoReversePopulator;
import br.hering.facades.populators.order.BoletoPaymentInfoPopulator;
import br.hering.facades.populators.order.BoletoPaymentInfoReversePopulator;

import com.adyen.services.payment.impl.AdyenCardInstallmentsService;
import com.flieger.payment.data.BoletoPaymentInfoData;
import com.flieger.payment.data.HeringDebitPaymentInfoData;
import com.flieger.payment.model.BoletoPaymentInfoModel;
import com.flieger.payment.model.HeringDebitPaymentInfoModel;

import de.hybris.platform.acceleratorfacades.order.impl.DefaultAcceleratorCheckoutFacade;
import de.hybris.platform.acceleratorservices.payment.cybersource.data.PaymentInfoData;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.DeliveryModeData;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.voucher.data.VoucherData;
import de.hybris.platform.commerceservices.order.CommerceCartCalculationStrategy;
import de.hybris.platform.core.enums.CreditCardType;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.PaymentModeService;
import de.hybris.platform.payment.dto.CardType;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.util.PriceValue;

/**
 * @author Vinicius de Souza
 * 
 * @version 1.1 - Removed clause <code>checkIfCurrentUserIsTheCartUser()</code> from method <code>createPaymentSubscription</code>.
 * 
 */
public class DefaultHeringCheckoutFacade 
extends DefaultAcceleratorCheckoutFacade 
implements HeringCheckoutFacade
{
	private static final Logger LOG = Logger.getLogger(DefaultHeringCheckoutFacade.class);
	
	@Resource
	private BoletoPaymentInfoPopulator boletoPaymentInfoPopulator;
	
	@Resource(name = "boletoPaymentInfoReversePopulator")
	private BoletoPaymentInfoReversePopulator boletoPaymentInfoReversePopulator;

	@Resource(name = "cartPaymentModePopulator")
	private CartPaymentModePopulator cartPaymentModePopulator;
	
	@Resource(name = "voucherPaymentInfoReversePopulator")
	private VoucherPaymentInfoReversePopulator voucherPaymentInfoReversePopulator; 
	
	@Resource(name = "voucherPaymentInfoPopulator")
	private VoucherPaymentInfoPopulator voucherPaymentInfoPopulator;

	@Resource(name = "debitPaymentInfoReversePopulator")
	private DebitPaymentInfoReversePopulator debitPaymentInfoReversePopulator;

	@Resource(name = "sessionService")
	private SessionService sessionService;

	@Resource
	protected BaseStoreService baseStoreService;
	
	private PaymentModeService paymentModeService;
	
	private HeringCustomerPopulator customerPopulator;
	
	@Resource
	private CommerceCartCalculationStrategy commerceCartCalculationStrategy;
	
	@Resource
	private AdyenCardInstallmentsService adyenCardInstallmentsService;
	
	@Resource
	private Converter<HeringDebitPaymentInfoModel, HeringDebitPaymentInfoData> debitPaymentInfoConverter;
	
	/**
	 * @return the customerPopulator
	 */
	public HeringCustomerPopulator getCustomerPopulator()
	{
		return customerPopulator;
	}

	/**
	 * @param customerPopulator the customerPopulator to set
	 */
	public void setCustomerPopulator(HeringCustomerPopulator customerPopulator)
	{
		this.customerPopulator = customerPopulator;
	}

	/**
	 * @return the paymentModeService
	 */
	public PaymentModeService getPaymentModeService()
	{
		return paymentModeService;
	}

	/**
	 * @param paymentModeService the paymentModeService to set
	 */
	public void setPaymentModeService(PaymentModeService paymentModeService)
	{
		this.paymentModeService = paymentModeService;
	}

	/**
	 * @return the cartPaymentModePopulator
	 */
	public CartPaymentModePopulator getCartPaymentModePopulator()
	{
		return cartPaymentModePopulator;
	}

	/**
	 * @param cartPaymentModePopulator
	 *           the cartPaymentModePopulator to set
	 */
	public void setCartPaymentModePopulator(CartPaymentModePopulator cartPaymentModePopulator)
	{
		this.cartPaymentModePopulator = cartPaymentModePopulator;
	}

	/**
	 * @return the sessionService
	 */
	public SessionService getSessionService()
	{
		return sessionService;
	}

	/**
	 * @param sessionService
	 *           the sessionService to set
	 */
	public void setSessionService(SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	/**
	 * @return the boletoPaymentInfoReversePopulator
	 */
	public BoletoPaymentInfoReversePopulator getBoletoPaymentInfoReversePopulator()
	{
		return boletoPaymentInfoReversePopulator;
	}

	/**
	 * 
	 * @return the boletoPaymentInfoPopulator
	 */
	public BoletoPaymentInfoPopulator getBoletoPaymentInfoPopulator() {
	    return boletoPaymentInfoPopulator;
	}
	
	
	/**
	 * @param boletoPaymentInfoReversePopulator
	 *           the boletoPaymentInfoReversePopulator to set
	 */
	public void setBoletoPaymentInfoReversePopulator(BoletoPaymentInfoReversePopulator boletoPaymentInfoReversePopulator)
	{
		this.boletoPaymentInfoReversePopulator = boletoPaymentInfoReversePopulator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.hering.facades.facades.order.HeringCheckoutFacade#setCustomPaymentInfo(com.flieger.payment.data.
	 * BoletoPaymentInfoData)
	 */
	@Override
	public void setCustomPaymentInfo(CartData cartData)
	{
		final CartModel cart = getCart();
		final BoletoPaymentInfoModel boletoPaymentInfoModel = getModelService().create(BoletoPaymentInfoModel.class);
		boletoPaymentInfoModel.setUser(getCurrentUserForCheckout());
		boletoPaymentInfoModel.setCode(getCurrentUserForCheckout().getUid() + "_" + UUID.randomUUID());
		AddressModel addressModel = getModelService().create(AddressModel.class);
		boletoPaymentInfoModel.setBillingAddress(addressModel);
		getBoletoPaymentInfoReversePopulator().populate((BoletoPaymentInfoData) cartData.getCustomPaymentInfo(),
				boletoPaymentInfoModel);

		cart.setPaymentInfo(boletoPaymentInfoModel);

		getModelService().save(boletoPaymentInfoModel);
		getModelService().save(cart);
	}
	
	private void saveVoucherPaymentInfoIntoCart(CartData cartData)
	{
		final CartModel cart = getCart();
		
		final VoucherPaymentInfoModel voucherPaymentInfoModel = getModelService().create(VoucherPaymentInfoModel.class);
		voucherPaymentInfoModel.setUser(getCurrentUserForCheckout());
		voucherPaymentInfoModel.setCode(getCurrentUserForCheckout().getUid() + "_" + UUID.randomUUID());
		
		if(CollectionUtils.isNotEmpty(cartData.getAppliedVouchers())){			
			for(VoucherData voucherData: cartData.getAppliedVouchers()){
				if(voucherData.getVoucherCode().startsWith("vc_")){
					voucherPaymentInfoModel.setVoucher(voucherData.getVoucherCode());
				}
			}
		}
		
		AddressModel addressModel = getModelService().create(AddressModel.class);
		
		voucherPaymentInfoModel.setBillingAddress(addressModel);
		getVoucherPaymentInfoReversePopulator().populate(cartData.getVoucherPaymentInfo(),
				voucherPaymentInfoModel);
		cart.setPaymentInfo(voucherPaymentInfoModel);
		getModelService().save(voucherPaymentInfoModel);
		getModelService().save(cart);
	}
	
	private void saveDebitPaymentInfoIntoCart(final HeringDebitPaymentInfoData debitPaymentInfoData)
	{
		final CartModel cartModel = getCart();
	
		HeringDebitPaymentInfoModel debitPaymentInfoModel = getModelService().create(HeringDebitPaymentInfoModel.class);
		
		getDebitPaymentInfoReversePopulator().populate(debitPaymentInfoData, debitPaymentInfoModel);
		
		if (checkIfCurrentUserIsTheCartUser())
		{
			if (StringUtils.isNotBlank(debitPaymentInfoData.getPK()))
			{
				debitPaymentInfoModel.setUser(getCurrentUserForCheckout());
				cartModel.setPaymentInfo(debitPaymentInfoModel);
//				getModelService().save(debitPaymentInfoModel);
//				getModelService().save(cartModel);	
				getCommerceCheckoutService().setPaymentInfo(cartModel, debitPaymentInfoModel);				
			}
		}
	}

//	@Override
//	public boolean setPaymentDetails(final HeringDebitPaymentInfoData debitPaymentInfoData)
//	{
//		validateParameterNotNullStandardMessage("debitPaymentInfoData", debitPaymentInfoData);
//
//		final CartModel cartModel = getCart();
//		if (checkIfCurrentUserIsTheCartUser())
//		{
//			if (StringUtils.isNotBlank(debitPaymentInfoData.getId()))
//			{
//				final PaymentInfoModel paymentInfoModel = getCustomerAccountService().getPaymentInfoForCode(
//						getCurrentUserForCheckout(), debitPaymentInfoData.getId());
//				if (paymentInfoModel != null)
//				{
//					return getCommerceCheckoutService().setPaymentInfo(cartModel, paymentInfoModel);
//				}
//			}
//		}
//		return false;
//	}


	@Override
	public void savePaymentInfoIntoCart(CartData cartData)
	{	
//		final CartData cartData = getCheckoutCart();
		
		final PaymentInfoData paymentInfoData = cartData.getCustomPaymentInfo();
		
		final PaymentInfoData voucherInfoData = cartData.getVoucherPaymentInfo();
		
		if(paymentInfoData instanceof HeringDebitPaymentInfoData)
		{
			saveDebitPaymentInfoIntoCart((HeringDebitPaymentInfoData)paymentInfoData);
		}
		else if(voucherInfoData instanceof VoucherPaymentInfoData){
			saveVoucherPaymentInfoIntoCart(cartData);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.commercefacades.order.impl.DefaultCheckoutFacade#createPaymentSubscription(de.hybris.platform
	 * .commercefacades.order.data.CCPaymentInfoData)
	 */
	@Override
	public CCPaymentInfoData createPaymentSubscription(
			final CCPaymentInfoData paymentInfoData)
	{
		validateParameterNotNullStandardMessage("paymentInfoData", paymentInfoData);
		final AddressData billingAddressData = paymentInfoData.getBillingAddress();
		validateParameterNotNullStandardMessage("billingAddress", billingAddressData);

		/**
		 * FIXME: Validate if removed.
		 */
		//if (checkIfCurrentUserIsTheCartUser())
		//{
		final KpCardInfo cardInfo = new KpCardInfo();
		cardInfo.setCardHolderFullName(paymentInfoData.getAccountHolderName());
		cardInfo.setInstallments(paymentInfoData.getInstallment());
		//dois parametros que n√£o s√£o salvos no BD, mas tem que ser mantido na sess√£o at√© o user finalizar a compra
		cardInfo.setCardNumber(paymentInfoData.getCardNumber());
		cardInfo.setCv2Number(paymentInfoData.getCv2Number());
		cardInfo.setExpirationMonth(Integer.valueOf(paymentInfoData.getExpiryMonth()));
		cardInfo.setExpirationYear(Integer.valueOf(paymentInfoData.getExpiryYear()));
		cardInfo.setIssueNumber(paymentInfoData.getIssueNumber());
		
		final CardType cardType = getCommerceCardTypeService().getCardTypeForCode(paymentInfoData.getCardType());
		final CreditCardType creditCardType = (cardType == null ? CreditCardType.VISA : cardType.getCode());
		cardInfo.setCardType(creditCardType);

		final HeringBillingInfo billingInfo = new HeringBillingInfo();
		billingInfo.setCity(billingAddressData.getTown());
		if (billingAddressData.getCountry() != null)
		{
			billingInfo.setCountry(billingAddressData.getCountry().getIsocode());
		}
		billingInfo.setFirstName(billingAddressData.getFirstName());
		billingInfo.setLastName(billingAddressData.getLastName());
		billingInfo.setEmail(billingAddressData.getEmail());
		billingInfo.setPhoneNumber(billingAddressData.getPhone());
		billingInfo.setPostalCode(billingAddressData.getPostalCode());
		billingInfo.setStreet1(billingAddressData.getLine1());
		billingInfo.setStreet2(billingAddressData.getLine2());
		billingInfo.setRegionIso(billingAddressData.getRegion().getIsocode());
		billingInfo.setDddPhone(billingAddressData.getDddPhone());
		billingInfo.setDistrict(billingAddressData.getDistrict());
		billingInfo.setNumber(billingAddressData.getNumber());
		billingInfo.setReference(billingAddressData.getReference());
		billingInfo.setAddressType(billingAddressData.getType().toString());
		billingInfo.setComplement(billingAddressData.getComplement());

		final CustomerModel customerModel = getCurrentUserForCheckout();

		final CreditCardPaymentInfoModel ccPaymentInfoModel = getCustomerAccountService().createPaymentSubscription(
				customerModel, cardInfo, billingInfo, billingAddressData.getTitleCode(), getPaymentProvider(),
				paymentInfoData.isSaved());
		if (ccPaymentInfoModel != null)
		{
			CartModel cmodel = getCart();
			if(cmodel != null)
			{
				cmodel.setPaymentInfo(ccPaymentInfoModel);
				getModelService().save(cmodel);
				getModelService().refresh(cmodel);
				getCartService().setSessionCart(cmodel);
			}
			return getCreditCardPaymentInfoConverter().convert(ccPaymentInfoModel);
		}
		//}

		return null;
	}

	@Override
	public CustomerData getUserForCheckout()
	{
		CustomerModel customerModel = getCurrentUserForCheckout();
		CustomerData customerData = new CustomerData();
		customerPopulator.populate(customerModel, customerData);
		return customerData;
	}
	
	//
	// Delivery
	//
	
	/* (non-Javadoc)
	 * @see br.hering.facades.facades.order.HeringCheckoutFacade#resetDeliveryMode()
	 */
	@Override
	public void resetDeliveryMode()
	{
//		XXX remoÁ„o de dependencia com extension carrier
//		if(getSessionService().getAttribute(CarrierConstants.SESSION_ATTR_POSTALCODE) == null)
//		{
//			return;
//		}
		
		final CartModel cartModel = super.getCart();
		cartModel.setDeliveryMode(null);
		getModelService().save(cartModel);
		getModelService().refresh(cartModel);
		
		commerceCartCalculationStrategy.calculateCart(cartModel);
	}
	
	/**
	 * @return List<? extends DeliveryModeData>
	 */
	@Override
	public List<? extends DeliveryModeData> getSupportedDeliveryModes()
	{
//		XXX remoÁ„o de dependencia com extension carrier
//		boolean isCarrierDeliveryModeEnabled = true;
//		
//		if(isCarrierDeliveryModeEnabled)
//		{
//			final List<DeliveryModeData> result = carrierDeliveryCalculationFacade.getSupportedDeliveryModes(getCart());
//			
//			if(CollectionUtils.isEmpty(result))
//			{
//				return Collections.emptyList();
//			}
//			
//			prepareCheaper(result);
//			
//			prepareFaster(result);
//			
//			for(final DeliveryModeData r : result)
//			{
//				LOG.debug(r.getCode() + " - " + r.getDeliveryCost().getFormattedValue() + " - " + r.getEstimatedDeliveryDays());
//			}
//			
//			return result;
//		}
		
		return super.getSupportedDeliveryModes();
	}

	/**
	 * 
	 * @param result
	 */
	private void prepareCheaper(List<DeliveryModeData> result)
	{
		if(CollectionUtils.isEmpty(result))
		{
			return;
		}
		
		final DeliveryModeData cheaper = result.get(0);
		cheaper.setName("Rodovi√°ria");
		
		final Double threshold = baseStoreService.getCurrentBaseStore().getFreeDeliveryThreshold();
		
		if(threshold != null && getCart().getSubtotal().doubleValue() >= threshold.doubleValue())
		{
			final PriceData freeValue = getPriceDataFactory().create(PriceDataType.BUY, BigDecimal.valueOf(0), cheaper.getDeliveryCost().getCurrencyIso());
			freeValue.setFormattedValue("Gr&aacute;tis");
			
			cheaper.setDeliveryCost(freeValue);
		}
	}
	
	/**
	 * 
	 * @param result
	 */
	private void prepareFaster(List<DeliveryModeData> result)
	{
		if(CollectionUtils.isEmpty(result) || result.size() < 2)
		{
			return;
		}
		
		result.get(1).setName("Expresso");
	}
	
	@Override
	protected DeliveryModeData convert(final DeliveryModeModel deliveryModeModel)
	{
		return super.convert(deliveryModeModel);
//		XXX remoÁ„o de dependencia com extension carrier
//		if (!(deliveryModeModel instanceof CarrierZoneDeliveryModeModel))
//		{
//			return super.convert(deliveryModeModel);
//		}
//		
//		return carrierDeliveryCalculationFacade.convert((CarrierZoneDeliveryModeModel) deliveryModeModel, getCart());
	}
	
	/* (non-Javadoc)
	 * @see de.hybris.platform.commercefacades.order.impl.DefaultCheckoutFacade#containsTaxValues()
	 */
	@Override
	public boolean containsTaxValues()
	{
		return true;
	}
	
	/* (non-Javadoc)
	 * @see br.hering.facades.facades.order.HeringCheckoutFacade#setPaymentMode(br.hering.facades.order.data.PaymentModeData)
	 */
	@Override
	public void setPaymentMode(PaymentModeData paymentModeData)
	{
		final CartModel cartModel = getCart();
		final PaymentModeModel paymentModeModel = paymentModeService.getPaymentModeForCode(paymentModeData.getCode());
		getHeringCheckoutService().setPaymentMode(cartModel, paymentModeModel);
	}
	
	public boolean authorizeBoleto()
	{
		final CartModel cartModel = getCart();
		if (checkIfCurrentUserIsTheCartUser())
		{		
				final PaymentTransactionEntryModel paymentTransactionEntryModel = getHeringCheckoutService().authorizePayment(
						cartModel, null, getPaymentProvider());

				return paymentTransactionEntryModel != null
						&& (TransactionStatus.ACCEPTED.name().equals(paymentTransactionEntryModel.getTransactionStatus()) || TransactionStatus.REVIEW
								.name().equals(paymentTransactionEntryModel.getTransactionStatus()));
			
		}
		return false;
	}
	
	@Override
	public boolean authorizePayment()
	{
		final CartModel cartModel = getCart();
		if (checkIfCurrentUserIsTheCartUser())
		{
			final CreditCardPaymentInfoModel creditCardPaymentInfoModel = (CreditCardPaymentInfoModel) cartModel.getPaymentInfo();
			if (creditCardPaymentInfoModel != null && StringUtils.isNotBlank(creditCardPaymentInfoModel.getSubscriptionId()))
			{
				final PaymentTransactionEntryModel paymentTransactionEntryModel = getHeringCheckoutService().authorizePayment(
						cartModel, null, getPaymentProvider());

				return paymentTransactionEntryModel != null
						&& (TransactionStatus.ACCEPTED.name().equals(paymentTransactionEntryModel.getTransactionStatus()) || TransactionStatus.REVIEW
								.name().equals(paymentTransactionEntryModel.getTransactionStatus()));
			}
		}
		return false;
	}
	
	public boolean hasSubscriptionId()
	{
		final CartModel cartModel = getCart();
		if (checkIfCurrentUserIsTheCartUser())
		{
			final CreditCardPaymentInfoModel creditCardPaymentInfoModel = (CreditCardPaymentInfoModel) cartModel.getPaymentInfo();
			return StringUtils.isNotBlank(creditCardPaymentInfoModel.getSubscriptionId());
		}
		return false;
	}
	
	protected DefaultHeringCommerceCheckoutService getHeringCheckoutService()
	{
		return (DefaultHeringCommerceCheckoutService) getCommerceCheckoutService();
	}
	
	/**
	 * @return the voucherPaymentInfoReversePopulator
	 */
	public VoucherPaymentInfoReversePopulator getVoucherPaymentInfoReversePopulator()
	{
		return voucherPaymentInfoReversePopulator;
	}

	/**
	 * @param voucherPaymentInfoReversePopulator the voucherPaymentInfoReversePopulator to set
	 */
	public void setVoucherPaymentInfoReversePopulator(VoucherPaymentInfoReversePopulator voucherPaymentInfoReversePopulator)
	{
		this.voucherPaymentInfoReversePopulator = voucherPaymentInfoReversePopulator;
	}
	
	public DebitPaymentInfoReversePopulator getDebitPaymentInfoReversePopulator()
	{
		return debitPaymentInfoReversePopulator;
	}

	public void setDebitPaymentInfoReversePopulator(DebitPaymentInfoReversePopulator debitPaymentInfoReversePopulator)
	{
		this.debitPaymentInfoReversePopulator = debitPaymentInfoReversePopulator;
	}
	
	
	@Override
	public CartData getCheckoutCart()
	{
		final CartData cartData = getCartFacade().getSessionCart();
		if (cartData != null)
		{
			cartData.setDeliveryAddress(getDeliveryAddress());
			cartData.setDeliveryMode(getDeliveryMode());
			cartData.setPaymentInfo(getPaymentDetails());
			cartData.setVoucherPaymentInfo(getVoucherPaymentDetails());
//			cartData.setPaymentMode(getPaymentMode());
			cartData.setCustomPaymentInfo(getCustomPaymentInfo());
		}
		return cartData;
	}
	
	/**
	 * @return
	 */
	private PaymentModeData getPaymentMode() {
		final CartModel cart = getCart();
		if (cart != null) {
			final PaymentModeModel paymentModeModel = cart.getPaymentMode();
			PaymentModeData paymentModeData = new PaymentModeData();
			if (paymentModeModel != null) {
			    new PaymentModePopulator().populate(paymentModeModel,
                        paymentModeData);
				return paymentModeData;
			}
		}
		return null;
	}
	

	protected VoucherPaymentInfoData getVoucherPaymentDetails()
	{
		final CartModel cart = getCart();
		if (cart != null)
		{
			final PaymentInfoModel paymentInfo = cart.getPaymentInfo();
			if (paymentInfo instanceof VoucherPaymentInfoModel)
				
			{
//				getVoucherPaymentInfoReversePopulator().getVoucherPaymentInfoConverter().convert( (VoucherPaymentInfoModel)  paymentInfo);
				
				return getVoucherPaymentInfoPopulator().getVoucherPaymentInfoConverter().convert((VoucherPaymentInfoModel)  paymentInfo);
				
// 			   return getVoucherPaymentInfoReversePopulator().getVoucherPaymentInfoConverter().convert((VoucherPaymentInfoModel) paymentInfo);
//				return getVoucherPaymentInfoConverter().convert( (VoucherPaymentInfoModel) paymentInfo);
//				return getCreditCardPaymentInfoConverter().convert((CreditCardPaymentInfoModel) paymentInfo);
			}
		}

		return null;
	}
	
	protected CustomPaymentInfoData getCustomPaymentInfo() {
		final CartModel cartModel = getCart();
		if (cartModel != null) {
			final PaymentInfoModel paymentInfoModel = cartModel.getPaymentInfo();
			if (paymentInfoModel instanceof HeringDebitPaymentInfoModel) {
				return debitPaymentInfoConverter.convert(
				        (HeringDebitPaymentInfoModel) paymentInfoModel);
			} else if(paymentInfoModel instanceof BoletoPaymentInfoModel){
			    BoletoPaymentInfoData boletoPaymentInfoData = new BoletoPaymentInfoData();
			    getBoletoPaymentInfoPopulator().populate(
			            (BoletoPaymentInfoModel) paymentInfoModel,
			            boletoPaymentInfoData);
			    return boletoPaymentInfoData;
			} else if(paymentInfoModel instanceof VoucherPaymentInfoModel){
				VoucherPaymentInfoData voucherPaymentInfoData = new VoucherPaymentInfoData();
			    getVoucherPaymentInfoPopulator().populate(
			            (VoucherPaymentInfoModel) paymentInfoModel,
			            voucherPaymentInfoData);
			    return voucherPaymentInfoData;
			}
		}
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see br.hering.facades.facades.order.HeringCheckoutFacade#formatInstallmentsCost(de.hybris.platform.commercefacades.order.data.CartData)
	 */
	@Override
	public List<String> formatInstallmentsCost(CartData cartData)
	{
		final BigDecimal priceValue = cartData.getTotalPrice().getValue();
		final String currencyIso = cartData.getTotalPrice().getCurrencyIso();
		final boolean net = cartData.isNet();
		final PriceValue productPrice = new PriceValue(currencyIso, priceValue.doubleValue(), net);
		
		return adyenCardInstallmentsService.formatInstallmentsCost(productPrice);
	}
	
	/**
	 * @return the voucherPaymentInfoPopulator
	 */
	public VoucherPaymentInfoPopulator getVoucherPaymentInfoPopulator()
	{
		return voucherPaymentInfoPopulator;
	}

	/**
	 * @param voucherPaymentInfoPopulator the voucherPaymentInfoPopulator to set
	 */
	public void setVoucherPaymentInfoPopulator(VoucherPaymentInfoPopulator voucherPaymentInfoPopulator)
	{
		this.voucherPaymentInfoPopulator = voucherPaymentInfoPopulator;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.commercefacades.order.impl.DefaultCheckoutFacade#createPaymentSubscription(de.hybris.platform
	 * .commercefacades.order.data.CCPaymentInfoData)
	 */
	@Override
	public HeringDebitPaymentInfoData createPaymentSubscription(final HeringDebitPaymentInfoData heringDebitPaymentInfoData)
	{
		validateParameterNotNullStandardMessage("heringDebitPaymentInfoData", heringDebitPaymentInfoData);
		final AddressData billingAddressData = heringDebitPaymentInfoData.getBillingAddress();
		validateParameterNotNullStandardMessage("billingAddress", billingAddressData);

		if (checkIfCurrentUserIsTheCartUser())
		{	
			final HeringBillingInfo billingInfo = new HeringBillingInfo();
			billingInfo.setCity(billingAddressData.getTown());
			if (billingAddressData.getCountry() != null)
			{
				billingInfo.setCountry(billingAddressData.getCountry().getIsocode());
			}
			billingInfo.setFirstName(billingAddressData.getFirstName());
			billingInfo.setLastName(billingAddressData.getLastName());
			billingInfo.setEmail(billingAddressData.getEmail());
			billingInfo.setPhoneNumber(billingAddressData.getPhone());
			billingInfo.setPostalCode(billingAddressData.getPostalCode());
			billingInfo.setStreet1(billingAddressData.getLine1());
			billingInfo.setStreet2(billingAddressData.getLine2());
			billingInfo.setRegionIso(billingAddressData.getRegion().getIsocode());
			billingInfo.setDddPhone(billingAddressData.getDddPhone());
			billingInfo.setDistrict(billingAddressData.getDistrict());
			billingInfo.setNumber(billingAddressData.getNumber());
			billingInfo.setReference(billingAddressData.getReference());
			billingInfo.setAddressType(billingAddressData.getType().toString());
			
			final CustomerModel customerModel = getCurrentUserForCheckout();
			
			HeringDebitPaymentInfoModel model = getModelService().create(HeringDebitPaymentInfoModel.class);
			getDebitPaymentInfoReversePopulator().populate(heringDebitPaymentInfoData, model);
			
			//create the model
			final HeringDebitPaymentInfoModel resultModel = 
					getCustomerAccountService().createPaymentSubscription(customerModel, 
							model, billingInfo, billingAddressData.getTitleCode(), 
							getPaymentProvider(), heringDebitPaymentInfoData.isSaved());
			if (resultModel != null)
			{
				return debitPaymentInfoConverter.convert(resultModel);
			}
		}
		return null;
	}

	@Override
	public boolean authorizeDebitPayment()
	{	
		final CartModel cartModel = getCart();
		final HeringDebitPaymentInfoModel debitPaymentInfoModel = (HeringDebitPaymentInfoModel)cartModel.getPaymentInfo();
		
		if (checkIfCurrentUserIsTheCartUser())
		{
			if (		debitPaymentInfoModel != null 
					&& StringUtils.isNotBlank(debitPaymentInfoModel.getSubscriptionId()))
			{
				final PaymentTransactionEntryModel paymentTransactionEntryModel = 
						getHeringCheckoutService().authorizeDebitPayment(cartModel, getPaymentProvider());

				return paymentTransactionEntryModel != null
						&& (TransactionStatus.ACCEPTED.name().equals(paymentTransactionEntryModel.getTransactionStatus()) || TransactionStatus.REVIEW
								.name().equals(paymentTransactionEntryModel.getTransactionStatus()));
			}
		}
		return false;
	}
	
	@Override
	public boolean authorizeDebitPayment3D(final String paymentInfoPK)
	{	
		PaymentInfoModel paymentInfoModel = 
				getCustomerAccountService().getPaymentInfoForCode(getCurrentUserForCheckout(), paymentInfoPK);
		
		if(		paymentInfoModel != null 
				&& paymentInfoModel instanceof HeringDebitPaymentInfoModel)
		{
   		if (checkIfCurrentUserIsTheCartUser())
   		{
   			if (StringUtils.isNotBlank(paymentInfoModel.getPk().toString()))
   			{
   				final PaymentTransactionEntryModel paymentTransactionEntryModel = 
   						getHeringCheckoutService().authorizeDebitPayment3D(
   								(HeringDebitPaymentInfoModel)paymentInfoModel);
   				
   				return paymentTransactionEntryModel != null
   						&& (TransactionStatus.ACCEPTED.name().equals(paymentTransactionEntryModel.getTransactionStatus()) || TransactionStatus.REVIEW
   								.name().equals(paymentTransactionEntryModel.getTransactionStatus()));
   			}
   		}
		}
		return false;
	}
	
	@Override
	protected KPCustomerAccountService getCustomerAccountService()
	{
		return (KPCustomerAccountService) super.getCustomerAccountService();
	}
			
	/* (non-Javadoc)
	 * @see de.hybris.platform.commercefacades.order.impl.DefaultCheckoutFacade#getCurrentUserForCheckout()
	 */
	@Override
	public CustomerModel getCurrentUserForCheckout()
	{
		return super.getCurrentUserForCheckout();
	}

	/* (non-Javadoc)
	 * @see br.hering.facades.facades.order.HeringCheckoutFacade#removeAddress(de.hybris.platform.commercefacades.user.data.AddressData)
	 */
	@Override
	public void removeDeliveryAddress(final AddressData addressData)
	{
		validateParameterNotNullStandardMessage("addressData", addressData);
		
		if(hasCheckoutCart()){
			if(getCheckoutCart().getDeliveryAddress() != null 
					&& getCheckoutCart().getDeliveryAddress().getId().equals(addressData.getId())){
				removeDeliveryAddress();
			}			
		}
	}
	
	@Override
	protected DeliveryModeData getDeliveryMode()
	{
		final CartModel cart = getCart();
		if (cart != null)
		{
			final DeliveryModeModel deliveryModeModel = cart.getDeliveryMode();
			if (deliveryModeModel != null)
			{
				return convert(deliveryModeModel);
			}
		}

		return null;
	}
	
	
	
}