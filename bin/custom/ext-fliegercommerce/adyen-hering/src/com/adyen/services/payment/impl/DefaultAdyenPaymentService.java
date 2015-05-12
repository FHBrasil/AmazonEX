package com.adyen.services.payment.impl;

import de.hybris.platform.core.enums.CreditCardType;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.commands.request.CreateSubscriptionRequest;
import de.hybris.platform.payment.commands.result.SubscriptionResult;
import de.hybris.platform.payment.dto.BillingInfo;
import de.hybris.platform.payment.dto.CardInfo;
import de.hybris.platform.payment.dto.NewSubscription;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.impl.DefaultPaymentServiceImpl;
import de.hybris.platform.payment.methods.CardPaymentService;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.util.Config;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.adyen.services.payment.AdyenAuthorizationRequest;
import com.adyen.services.payment.AdyenAuthorizationResult;
import com.adyen.services.payment.AdyenBoletoAuthorizationRequest;
import com.adyen.services.payment.AdyenBoletoAuthorizationResult;
import com.adyen.services.payment.AdyenCaptureRequest;
import com.adyen.services.payment.AdyenCaptureResult;
import com.adyen.services.payment.AdyenDebitAuthorizationRequest;
import com.adyen.services.payment.AdyenDebitAuthorizationResult;
import com.adyen.services.payment.AdyenPaymentService;
import com.adyen.services.recurring.AdyenRecurringDetailsResult;
import com.adyen.services.recurring.AdyenRecurringListDetailsRequest;
import com.flieger.main.Credentials;
import com.flieger.payment.jalo.AdyenCreateSubscriptionRequest;
import com.flieger.payment.model.HeringDebitPaymentInfoModel;

/**
 * @author flieger
 */
//TODO esta service funciona como uma facade
public class DefaultAdyenPaymentService 
extends DefaultPaymentServiceImpl 
implements AdyenPaymentService
{	
	/**
	 * card number in session
	 */
	private static final String TEMPORARY_CARD_NUMBER = "temporaryCardNumber";
	private static final String TEMPORARY_CV2_NUMBER = "temporaryCv2Number";
	
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(DefaultAdyenPaymentService.class.getName());
	
	@Resource
	private ModelService modelService;
	
	@Resource
	private CommonI18NService commonI18NService;
	private CartService cartService;
	private SessionService sessionService;
	
	@Resource
	private AdyenBoletoPaymentService boletoPaymentService;
	
	@Resource
	private AdyenDebitPaymentService adyenDebitPaymentService;

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Override
	public void setModelService(ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the commonI18NService
	 */
	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	/**
	 * @param commonI18NService
	 *           the commonI18NService to set
	 */
	@Override
	public void setCommonI18NService(CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	/**
	 * @return the cartService
	 */
	public CartService getCartService()
	{
		return cartService;
	}

	/**
	 * @param cartService
	 *           the cartService to set
	 */
	public void setCartService(CartService cartService)
	{
		this.cartService = cartService;
	}

	/* (non-Javadoc)
	 * @see de.hybris.platform.payment.impl.DefaultPaymentServiceImpl#getCardPaymentService()
	 */
	@Override
	public CardPaymentService getCardPaymentService()
	{
		return super.getCardPaymentService();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.payment.impl.DefaultPaymentServiceImpl#capture(de.hybris.platform.payment.model.
	 * PaymentTransactionModel)
	 */
	@Override
	public PaymentTransactionEntryModel capture(PaymentTransactionModel transaction) throws AdapterException
	{
		String newEntryCode = getNewEntryCode(transaction);
		
		AdyenCardPaymentService cardPaymentService = (AdyenCardPaymentService) this.getCardPaymentService();
		
		PaymentTransactionEntryModel auth = null;
		for (PaymentTransactionEntryModel model : transaction.getEntries())
		{
			if (model.getType().equals(PaymentTransactionType.AUTHORIZATION))
			{
				auth = model;
				break;
			}
		}

		String baseStoreUid = DEFAULT_STORE_UID;
		if(transaction.getOrder() != null 
				&& transaction.getOrder().getStore() != null 
				&& StringUtils.isNotBlank(transaction.getOrder().getStore().getUid()))
		{
			baseStoreUid = transaction.getOrder().getStore().getUid();
		}		
		//chega aqui
		AdyenCaptureResult result = 
				cardPaymentService.capture(
						new AdyenCaptureRequest(transaction.getPaymentProvider(), newEntryCode,
				getParam(Credentials.MERCHANT_ACCOUNT + "." + baseStoreUid), Currency.getInstance(auth.getCurrency().getIsocode()), auth.getAmount(), 
				auth.getAdyenReference(), auth.getAdyenAuthorizationCode()));
		
		transaction.setRequestId(result.getRequestId());
		transaction.setRequestToken(result.getRequestToken());

		this.modelService.save(transaction);
		
		PaymentTransactionEntryModel entry = (PaymentTransactionEntryModel) this.modelService
				.create(PaymentTransactionEntryModel.class);
		entry.setAmount(result.getTotalAmount());
		
		if(result.getTransactionStatusDetails().equals(TransactionStatusDetails.SUCCESFULL)) 
		{
			if (result.getCurrency() != null) {
				entry.setCurrency(this.commonI18NService.getCurrency(result.getCurrency().getCurrencyCode()));
			}
			entry.setType(PaymentTransactionType.CAPTURE);
			entry.setRequestId(result.getRequestId());
			entry.setRequestToken(result.getRequestToken());
			entry.setTime(result.getRequestTime() == null ? new Date() : result.getRequestTime());
			entry.setPaymentTransaction(transaction);
			entry.setTransactionStatus(result.getTransactionStatus().toString());
			entry.setTransactionStatusDetails(result.getTransactionStatusDetails().toString());
			entry.setCode(newEntryCode);
			entry.setAdyenReference(result.getAdyenReference());
			entry.setAdyenMerchantReference(auth.getAdyenMerchantReference());
		}
		this.modelService.save(entry);
		this.modelService.refresh(transaction);
		return entry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.payment.impl.DefaultPaymentServiceImpl#authorize(java.lang.String, java.math.BigDecimal,
	 * java.util.Currency, de.hybris.platform.core.model.user.AddressModel, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public PaymentTransactionEntryModel authorize(
			final String merchantTransactionCode, 
			final BigDecimal amount, Currency currency,
			final AddressModel deliveryAddress, 
			final String subscriptionID, 
			final String paymentProvider, 
			final String merchantAccount) throws AdapterException
	{
		BillingInfo shippingInfo = createBillingInfo(deliveryAddress);

		PaymentTransactionModel transaction = (PaymentTransactionModel) modelService.create(PaymentTransactionModel.class);
		transaction.setCode(merchantTransactionCode);

		return authorizeCardInternal(transaction, amount, currency, shippingInfo, subscriptionID, paymentProvider, merchantAccount);
	}

	/**
	 * @param shippingInfo  
	 */
	private PaymentTransactionEntryModel authorizeCardInternal(
			final PaymentTransactionModel transaction, 
			final BigDecimal amount, 
			final Currency currency, 
			final BillingInfo shippingInfo,  
			final String subscriptionID, 
			final String paymentProvider, 
			final String merchantAccount)
			throws AdapterException
	{
		String newEntryCode = getNewEntryCode(transaction);
		AdyenAuthorizationResult result = null;
		
		CartModel cart = getCartService().getSessionCart();
		CustomerModel user = (CustomerModel) cart.getUser();		
		AdyenCardPaymentService cardPaymentService = (AdyenCardPaymentService) this.getCardPaymentService();
		
		CardInfo card = createCardInfo();
		if(getSessionService().getAttribute(TEMPORARY_CARD_NUMBER) != null) {
   		card.setCardNumber( (String) getSessionService().getAttribute(TEMPORARY_CARD_NUMBER));
   		/*
   		 * Clean the card number in session
   		 */
   		getSessionService().removeAttribute(TEMPORARY_CARD_NUMBER);
		}
		if(getSessionService().getAttribute(TEMPORARY_CV2_NUMBER) != null) {
   		card.setCv2Number( (String) getSessionService().getAttribute(TEMPORARY_CV2_NUMBER));
   		/*
   		 * Clean the card number in session
   		 */
   		getSessionService().removeAttribute(TEMPORARY_CV2_NUMBER);
		}
		
		//TODO recuperar o numero de parcelas da tela
		Short installment = new Short(((CreditCardPaymentInfoModel)cart.getPaymentInfo()).getInstallment().toString());
		if(getPaymentInfo().getStantBuyKey() == null) {
   		result = cardPaymentService.authorise(new AdyenAuthorizationRequest(
   				getPaymentInfo().getStantBuyKey(), 
   				user.getUid(), 
   				paymentProvider, 
   				newEntryCode,
   				merchantAccount, 
   				currency, 
   				amount, 
   				cart.getCode(), 
   				card, cart, user.getUid(), installment));
   		AdyenRecurringDetailsResult detailsResult = cardPaymentService.listRecurringDetails(
   				new AdyenRecurringListDetailsRequest(paymentProvider, newEntryCode, 
   						user.getUid(), user.getUid(), merchantAccount));
   		
   		if(	getPaymentInfo().isSaved() 
   				&& detailsResult != null 
   				&& detailsResult.getRecurringDetailReference() != null) {
     			CreditCardPaymentInfoModel paymentInfo = getPaymentInfo();
            paymentInfo.setStantBuyKey(detailsResult.getRecurringDetailReference());
            this.modelService.save(paymentInfo);
   		}
		} else {
   		result = cardPaymentService.authorise(
   				new AdyenAuthorizationRequest(getPaymentInfo().getStantBuyKey(), 
   						user.getUid(), 
   						paymentProvider, 
   						newEntryCode, 
   						merchantAccount, 
   						currency, 
   						amount, 
   						getAdyenCode(), 
   						card, 
   						cart, 
   						user.getUid(), 
   						installment));
		}
   	
		transaction.setRequestId(result.getRequestId());
		transaction.setRequestToken(result.getRequestToken());
		transaction.setPaymentProvider(result.getPaymentProvider());
		
		this.modelService.save(transaction);
		
		PaymentTransactionEntryModel entry = (PaymentTransactionEntryModel) this.modelService
				.create(PaymentTransactionEntryModel.class);
		entry.setAmount(result.getTotalAmount());
		
		if (TransactionStatusDetails.SUCCESFULL.equals(result.getTransactionStatusDetails())) {
      		if (result.getCurrency() != null) {
      			entry.setCurrency(this.commonI18NService.getCurrency(result.getCurrency().getCurrencyCode()));
      		}
   			entry.setType(PaymentTransactionType.AUTHORIZATION);
   			entry.setTime(result.getAuthorizationTime() == null ? new Date() : result.getAuthorizationTime());
   			entry.setPaymentTransaction(transaction);
   			entry.setRequestId(result.getRequestId());
   			entry.setRequestToken(result.getRequestToken());
   			entry.setTransactionStatus(result.getTransactionStatus().toString());
   			entry.setTransactionStatusDetails(result.getTransactionStatusDetails().toString());
   			entry.setCode(newEntryCode);
   			entry.setAdyenReference(result.getAdyenReference());
   			entry.setAdyenMerchantReference(result.getAdyenUniqueCode());
   			entry.setAdyenAuthorizationCode(result.getAuthorizationCode());
		}
		else
		{
			sessionService.setAttribute("heringAdyenErrorCode", result.getHeringAdyenErrorCode());
			sessionService.setAttribute("adyenErrorCode", result.getErrorCode());
			//SPJ Acrescentada informa��o ao log para mapeamento do erro emprodu��o 16-06-2014
			//LOG.error("Status desconhecido ao autorizar o cartão pelo Adyen.");
			LOG.info("Status desconhecido ao autorizar o cartão pelo Adyen. Status --> " + result.getTransactionStatusDetails());
			return null;
		}
		
		if (subscriptionID != null)
		{
			entry.setSubscriptionID(subscriptionID);
		}
		this.modelService.save(entry);
		this.modelService.refresh(transaction);
		return entry;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.hybris.platform.payment.impl.DefaultPaymentServiceImpl#authorize(java.lang.String, java.math.BigDecimal, java.util.Currency, de.hybris.platform.core.model.user.AddressModel, java.lang.String)
	 */
   @Override
   public PaymentTransactionEntryModel authorize(	final String merchantTransactionCode, 
   																final BigDecimal paramBigDecimal,
   																final Currency paramCurrency, 
   																final AddressModel paramAddressModel, 
   																final String paymentProvider,
   																final String merchantAccount)
   {
      PaymentTransactionModel transaction = (PaymentTransactionModel) modelService.create(PaymentTransactionModel.class);
      transaction.setCode(merchantTransactionCode);

      return authorizeBoletoInternal(transaction, paymentProvider, paramCurrency, merchantAccount);
   }
	
	/*
	 * Realiza a autorizacao para pagamento via boleto
	 */
	private PaymentTransactionEntryModel authorizeBoletoInternal(
			PaymentTransactionModel transaction, 
			String paymentProvider, 
			Currency currency,
			final String merchantAccount)
			throws AdapterException
	{
		final String newEntryCode = getNewEntryCode(transaction);
		
		final CartModel cartModel = getCartService().getSessionCart();
		
		final AdyenBoletoAuthorizationRequest authorizationRequest = 
				new AdyenBoletoAuthorizationRequest(paymentProvider, newEntryCode, 
						cartModel.getCode(), cartModel.getPaymentInfo().getBillingAddress(), 
						cartModel, ((CustomerModel)cartModel.getUser()).getCpfcnpj(),
						currency, merchantAccount);
		
		final AdyenBoletoAuthorizationResult result = 
				boletoPaymentService.authorise(authorizationRequest);
		
		transaction.setRequestId(result.getRequestId());
		transaction.setRequestToken(result.getRequestToken());
		transaction.setPaymentProvider(result.getPaymentProvider());
		
		this.modelService.save(transaction);
		
		final PaymentTransactionEntryModel entry = 
				(PaymentTransactionEntryModel) this.modelService.create(PaymentTransactionEntryModel.class);
		entry.setAmount(result.getTotalAmount());
		
		if (TransactionStatusDetails.SUCCESFULL.equals(result.getTransactionStatusDetails())) 
		{
   		if (result.getCurrency() != null) {
   			entry.setCurrency(this.commonI18NService.getCurrency(result.getCurrency().getCurrencyCode()));
   		}
         entry.setType(PaymentTransactionType.AUTHORIZATION);
   		entry.setTime(result.getAuthorizationTime() == null ? new Date() : result.getAuthorizationTime());
   		entry.setPaymentTransaction(transaction);
   		entry.setRequestId(result.getRequestId());
   		entry.setRequestToken(result.getRequestToken());
   		entry.setTransactionStatus(result.getTransactionStatus().toString());
   		entry.setTransactionStatusDetails(result.getTransactionStatusDetails().toString());
   		entry.setCode(newEntryCode);
   		entry.setAdyenReference(result.getAdyenReference());
   		entry.setAdyenMerchantReference(result.getAdyenUniqueCode());
   		entry.setAdyenBoletoUrl(result.getBoletoBancarioUrl());
   		entry.setAdyenBoletoNossoNumero(result.getBoletoBancarioNossoNumero());
   		entry.setAdyenBoletoExpirationDate(result.getBoletoBancarioExpirationDate());
		}
		else
		{
			sessionService.setAttribute("heringAdyenErrorCode", result.getHeringAdyenErrorCode());
			sessionService.setAttribute("adyenErrorCode", result.getErrorCode());
			//SPJ Acrescentada informa��o ao log para mapeamento do erro emprodu��o 16-06-2014
//			LOG.error("Status desconhecido ao autorizar o boleto pelo Adyen.");
			LOG.info("Status desconhecido ao autorizar o boleto pelo Adyen. Status retornado -->" + result.getTransactionStatusDetails());
			return null;
		}
		
		this.modelService.save(entry);
		this.modelService.refresh(transaction);
		return entry;
	}
	

	/* (non-Javadoc)
	 * @see com.adyen.services.payment.AdyenPaymentService#authorizeDebit(com.flieger.payment.model.HeringDebitPaymentInfoModel, java.lang.String)
	 */
	@Override
	public PaymentTransactionEntryModel authorizeDebit(HeringDebitPaymentInfoModel debitPaymentInfoModel,
			final String paymentProvider, final String merchantAccount)
	{	
//		BillingInfo shippingInfo = createBillingInfo(cartModel.getDeliveryAddress());

		PaymentTransactionModel transaction = (PaymentTransactionModel) modelService.create(PaymentTransactionModel.class);
		transaction.setCode(debitPaymentInfoModel.getMerchantTransactionCode());
		
//		HeringDebitPaymentInfoModel debitPaymentInfoModel = (HeringDebitPaymentInfoModel)cartModel.getPaymentInfo();
		debitPaymentInfoModel.setReturnUrl(debitPaymentInfoModel.getReturnUrl()+"?paymentInfoPK="+debitPaymentInfoModel.getPk());
		
		return authorizeDebitInternal(transaction, paymentProvider, 
				debitPaymentInfoModel, merchantAccount);
	}
	
	private PaymentTransactionEntryModel authorizeDebitInternal(
			final PaymentTransactionModel transaction, 
			final String paymentProvider, 
			final HeringDebitPaymentInfoModel debitPaymentInfo, 
			final String merchantAccount) 
	{
		String newEntryCode = getNewEntryCode(transaction);
		
		AdyenDebitAuthorizationResult result = null;
		
		CartModel cartModel = getCartService().getSessionCart();
		
		final String reference = getAdyenCode();
		
		final String selectedBrand = "electron";

		final String userUid = cartModel.getUser().getUid();
		
		AdyenDebitAuthorizationRequest authorizationRequest = 
				new AdyenDebitAuthorizationRequest(reference, selectedBrand, userUid, userUid, 
						debitPaymentInfo, merchantAccount);
		
		result = adyenDebitPaymentService.authorise(authorizationRequest);
		
		transaction.setRequestId(result.getRequestId());
		transaction.setRequestToken(result.getRequestToken());
		transaction.setPaymentProvider(result.getPaymentProvider());
		
		this.modelService.save(transaction);
		
		PaymentTransactionEntryModel transactionEntry = (PaymentTransactionEntryModel) this.modelService
				.create(PaymentTransactionEntryModel.class);
		
		if (TransactionStatusDetails.SUCCESFULL.equals(result.getTransactionStatusDetails())) 
		{
   		if (result.getCurrency() != null) {
   			transactionEntry.setCurrency(this.commonI18NService.getCurrency(result.getCurrency().getCurrencyCode()));
   		}
   		transactionEntry.setAmount(result.getTotalAmount());
   		transactionEntry.setType(PaymentTransactionType.AUTHORIZATION);
         transactionEntry.setTime(result.getAuthorizationTime() == null ? new Date() : result.getAuthorizationTime());
   		transactionEntry.setPaymentTransaction(transaction);
   		transactionEntry.setRequestId(result.getRequestId());
   		transactionEntry.setRequestToken(result.getRequestToken());
   		transactionEntry.setTransactionStatus(result.getTransactionStatus().toString());
   		transactionEntry.setTransactionStatusDetails(result.getTransactionStatusDetails().toString());
   		transactionEntry.setCode(newEntryCode);
   		transactionEntry.setAdyenReference(result.getAdyenReference());
   		transactionEntry.setAdyenMerchantReference(result.getAdyenUniqueCode());

   		debitPaymentInfo.setIssuerUrl(result.getIssuerUrl());
   		debitPaymentInfo.setPaRequest(result.getPaRequest());
   		debitPaymentInfo.setMd(result.getMd());
   		debitPaymentInfo.setMpiImplementationType(result.getMpiImplementationType());
		}
		else
		{
			sessionService.setAttribute("adyenErrorCode", result.getErrorCode());
			//SPJ Acrescentada informa��o ao log para mapeamento do erro emprodu��o 16-06-2014
//			LOG.error("Status desconhecido ao autorizar o boleto pelo Adyen.");
			LOG.info("Status desconhecido ao autorizar o debito pelo Adyen. Status retornado -->" + result.getTransactionStatusDetails());
			return null;
		}
		
		this.modelService.save(debitPaymentInfo);
		this.modelService.save(transactionEntry);
		this.modelService.refresh(transaction);
		return transactionEntry;
	}
	
	@Override
	public PaymentTransactionEntryModel authorizeDebitPayment3D(
			final HeringDebitPaymentInfoModel debitPaymentInfoModel) 
	{	
		CartModel cartModel = getCartService().getSessionCart();
		PaymentTransactionModel transaction = null;
		if(cartModel.getPaymentTransactions().isEmpty()){
			return null;//DEU ERRO, TODO temque jogar uma exception
		} else {
			transaction = cartModel.getPaymentTransactions().get(0);
		}
		
		String baseStoreUid = DEFAULT_STORE_UID;
		if(transaction.getOrder() != null 
				&& transaction.getOrder().getStore() != null 
				&& StringUtils.isNotBlank(transaction.getOrder().getStore().getUid()))
		{
			baseStoreUid = transaction.getOrder().getStore().getUid();
		}	
		
		String newEntryCode = getNewEntryCode(transaction);
		
		AdyenDebitAuthorizationResult result = null;
		
		AdyenDebitAuthorizationRequest authorizationRequest = 
				new AdyenDebitAuthorizationRequest(debitPaymentInfoModel,
						getParam(Credentials.MERCHANT_ACCOUNT + "." + baseStoreUid));
		
		result = adyenDebitPaymentService.authorize3D(authorizationRequest);
		
		transaction.setRequestId(result.getRequestId());
		transaction.setRequestToken(result.getRequestToken());
		transaction.setPaymentProvider(result.getPaymentProvider());
		
		this.modelService.save(transaction);
		
		PaymentTransactionEntryModel transactionEntry = (PaymentTransactionEntryModel) this.modelService
				.create(PaymentTransactionEntryModel.class);
		
		if (TransactionStatusDetails.SUCCESFULL.equals(result.getTransactionStatusDetails())) 
		{
   		if (result.getCurrency() != null) {
   			transactionEntry.setCurrency(this.commonI18NService.getCurrency(result.getCurrency().getCurrencyCode()));
   		}
   		transactionEntry.setAmount(result.getTotalAmount());
   		transactionEntry.setType(PaymentTransactionType.AUTHORIZATION3D);
         transactionEntry.setTime(result.getAuthorizationTime() == null ? new Date() : result.getAuthorizationTime());
   		transactionEntry.setPaymentTransaction(transaction);
   		transactionEntry.setRequestId(result.getRequestId());
   		transactionEntry.setRequestToken(result.getRequestToken());
   		transactionEntry.setTransactionStatus(result.getTransactionStatus().toString());
   		transactionEntry.setTransactionStatusDetails(result.getTransactionStatusDetails().toString());
   		transactionEntry.setCode(newEntryCode);
   		transactionEntry.setAdyenReference(result.getAdyenReference());
   		transactionEntry.setAdyenMerchantReference(result.getAdyenUniqueCode());
		}
		else
		{
			sessionService.setAttribute("adyenErrorCode", result.getErrorCode());
			LOG.info("Status desconhecido ao autorizar o debito pelo Adyen. Status retornado -->" + result.getTransactionStatusDetails());
			return null;
		}
		
		this.modelService.save(debitPaymentInfoModel);
		this.modelService.save(transactionEntry);
		this.modelService.refresh(transaction);
		return transactionEntry;
	}
	
	private String getAdyenCode()
	{
		return ""+System.currentTimeMillis();
	}

	private BillingInfo createBillingInfo(AddressModel address)
	{
		if (address == null)
		{
			return null;
		}

		BillingInfo billingInfo = new BillingInfo();

		billingInfo.setCity(address.getTown());
		if (address.getCountry() != null)
		{
			billingInfo.setCountry(address.getCountry().getIsocode());
		}
		billingInfo.setEmail(address.getEmail());
		billingInfo.setFirstName(address.getFirstname());
		billingInfo.setLastName(address.getLastname());
		billingInfo.setPhoneNumber(address.getPhone1());
		billingInfo.setPostalCode(address.getPostalcode());
		if (address.getRegion() != null)
		{
			billingInfo.setState(address.getRegion().getName());
		}
		billingInfo.setStreet1(address.getStreetname());
		billingInfo.setStreet2(address.getStreetnumber());

		return billingInfo;
	}
	
	private CardInfo createCardInfo()
	{
		CardInfo cardInfo = new CardInfo();
		CreditCardPaymentInfoModel paymentInfo = (CreditCardPaymentInfoModel) getCartService().getSessionCart().getPaymentInfo();
		cardInfo.setCardHolderFullName(paymentInfo.getCcOwner());
		cardInfo.setCardNumber(paymentInfo.getNumber());
		CreditCardType creditCardType = paymentInfo.getType();
		cardInfo.setCardType(creditCardType);
		cardInfo.setExpirationMonth(Integer.valueOf(paymentInfo.getValidToMonth()));
		cardInfo.setExpirationYear(Integer.valueOf(paymentInfo.getValidToYear()));
		if (paymentInfo.getIssueNumber() != null)
		{
			cardInfo.setIssueNumber(paymentInfo.getIssueNumber().toString());
		}
		return cardInfo;
	}

	/*
	 * Return parameter value for the given name
	 * @param paramName
	 */
	private String getParam(String paramName) {
		return Config.getParameter(paramName);
	}
	
   private CreditCardPaymentInfoModel getPaymentInfo()
   {
      return (CreditCardPaymentInfoModel) getCartService().getSessionCart().getPaymentInfo();
   }
	
	/**
	 * Create a subscription for the debit operation
	 * */
	@Override
	public NewSubscription createSubscription(final String merchantTransactionCode, 
															final String paymentProvider, 
															final Currency currency,
															final AddressModel paymentAddress, 
															final HeringDebitPaymentInfoModel debitPaymentInfo) throws AdapterException
	{
		CreateSubscriptionRequest request = new AdyenCreateSubscriptionRequest(
				merchantTransactionCode, createBillingInfo(paymentAddress),
				currency, null, null, null, paymentProvider, debitPaymentInfo);
		
		PaymentTransactionModel transaction = (PaymentTransactionModel) this.modelService
				.create(PaymentTransactionModel.class);
		transaction.setCode(merchantTransactionCode);
		
		SubscriptionResult result = adyenDebitPaymentService.createSubscription(request);
		transaction.setRequestId(result.getRequestId());
		transaction.setRequestToken(result.getRequestToken());
		transaction.setPaymentProvider(paymentProvider);
		this.modelService.save(transaction);

		PaymentTransactionEntryModel entry = (PaymentTransactionEntryModel) this.modelService
				.create(PaymentTransactionEntryModel.class);
		entry.setType(PaymentTransactionType.CREATE_SUBSCRIPTION);
		entry.setRequestId(result.getRequestId());
		entry.setRequestToken(result.getRequestToken());
		entry.setTime(new Date());
		entry.setPaymentTransaction(transaction);
		entry.setTransactionStatus(result.getTransactionStatus().toString());
		if (result.getTransactionStatusDetails() != null) {
			entry.setTransactionStatusDetails(result
					.getTransactionStatusDetails().toString());
		}
		entry.setCode(getNewEntryCode(transaction));
		this.modelService.save(entry);

		NewSubscription newSubscription = new NewSubscription();
		newSubscription.setTransactionEntry(entry);
		newSubscription.setSubscriptionID(result.getSubscriptionID());

		return newSubscription;
	}
}
