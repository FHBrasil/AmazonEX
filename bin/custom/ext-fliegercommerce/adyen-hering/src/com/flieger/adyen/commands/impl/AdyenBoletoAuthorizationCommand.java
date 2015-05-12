/**
 * 
 */
package com.flieger.adyen.commands.impl;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.payment.dto.AvsStatus;
import de.hybris.platform.payment.dto.CvnStatus;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import de.hybris.platform.util.Config;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.adyen.services.common.Address;
import com.adyen.services.common.Amount;
import com.adyen.services.common.Name;
import com.adyen.services.payment.AdyenBoletoAuthorizationRequest;
import com.adyen.services.payment.AdyenBoletoAuthorizationResult;
import com.adyen.services.payment.AnyType2AnyTypeMapEntry;
import com.adyen.services.payment.PaymentRequest;
import com.adyen.services.payment.PaymentResult;
import com.flieger.adyen.command.BoletoCommand;
import com.flieger.constants.AdyenTransactionStatus;
import com.flieger.main.Credentials;
import com.flieger.services.WebServicesConn;

import de.hybris.platform.store.services.BaseStoreService;

/**
 * @author flieger
 * 
 */
public class AdyenBoletoAuthorizationCommand implements BoletoCommand
{
	
	@Resource
	protected BaseStoreService baseStoreService;

	private static final Logger LOG = Logger.getLogger(AdyenBoletoAuthorizationCommand.class.getName());
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.adyen.services.payment.impl.AdyenCardPaymentService#authorize()
	 */
	@Override
	public AdyenBoletoAuthorizationResult perform(AdyenBoletoAuthorizationRequest request)
	{
		PaymentResult payResult = null;
		AdyenBoletoAuthorizationResult result = new AdyenBoletoAuthorizationResult();
		
		try
		{
			CartModel cartModel = request.getCart();
			CustomerModel customer = (CustomerModel) cartModel.getUser();
			
			result.setCurrency(request.getCurrency());
			result.setTotalAmount(new BigDecimal(String.valueOf(request.getCart().getTotalPrice().doubleValue())));
			result.setAvsStatus(AvsStatus.NO_RESULT);
			result.setCvnStatus(CvnStatus.MATCHED);
			result.setAuthorizationTime(new Date());
			result.setAdyenUniqueCode(request.getReference());
			
			PaymentRequest payRequest = new PaymentRequest();
			payRequest.setMerchantAccount(request.getMerchantAccount());
			Amount amount = new Amount();
			amount.setCurrency(Credentials.CURRENCY);
			Double d = new Double(BigDecimal.valueOf(request.getCart().getTotalPrice().doubleValue()).multiply(new BigDecimal(100)).doubleValue());
			amount.setValue(d.longValue());
			payRequest.setAmount(amount);
			payRequest.setReference(request.getReference());
			if (customer.getUid().contains("|"))
			{
				payRequest.setShopperEmail(customer.getUid().split("\\|")[1]);
				payRequest.setShopperReference(customer.getUid().split("\\|")[1]);
			}
			else
			{
				payRequest.setShopperEmail(customer.getUid());
				payRequest.setShopperReference(customer.getUid());
			}
			
			payRequest.setSelectedBrand(Config.getParameter(Credentials.BOLETO_BRAND));
			Address address = new Address();
			address.setCity(request.getAddress().getTown());
			address.setCountry(request.getAddress().getCountry().getIsocode());
			
			String streetnumber = request.getAddress().getStreetnumber();
			address.setHouseNumberOrName(streetnumber);

			address.setPostalCode(request.getAddress().getPostalcode());
			address.setStateOrProvince(request.getAddress().getRegion().getIsocodeShort());
			
			address.setStreet(request.getAddress().getLine1());
			payRequest.setBillingAddress(address);
			
			String[] customerName;
		   String firstName = "";
		   String lastName = "";
		   
			
			if (StringUtils.isNotBlank(request.getAddress().getFirstname())
					&& StringUtils.isNotBlank(request.getAddress().getLastname()))
			{
				firstName = request.getAddress().getFirstname();
				lastName = request.getAddress().getLastname();

			}
			else if (StringUtils.isNotBlank(customer.getDisplayName()))
				{
					customerName = customer.getDisplayName().split(" ");
					firstName = customerName[0];
					lastName = customer.getDisplayName().split(firstName + " ")[1];
				}
			else if (StringUtils.isNotBlank(request.getAddress().getReceiver()))
			{
				customerName = customer.getDisplayName().split(" ");
				firstName = customerName[0];
				if (customer.getDisplayName().split(firstName + " ").length > 0)
				{
					lastName = customer.getDisplayName().split(firstName + " ")[1];
				}
				else
				{
					lastName = firstName;
				}
			}
			
			Name name = new Name();
			name.setFirstName(firstName);
			name.setLastName(lastName);
			payRequest.setShopperName(name);
			payRequest.setShopperStatement("Não aceitar pagamento após o Vencimento. \nNão cobrar juros. \nNão aceitar o pagamento com cheque.");
			payRequest.setSocialSecurityNumber(request.getCpf());
			payRequest.setDeliveryDate(getDeliveryDate());
			

			try
			{
				payResult = new WebServicesConn().authorise(payRequest);
			}
			catch (Exception e)
			{
				result.setTransactionStatus(TransactionStatus.ERROR);
				result.setTransactionStatusDetails(TransactionStatusDetails.COMMUNICATION_PROBLEM);
				// SPJ adicionado informa��o da linha street do endere�o no log 16/06/2014
				//LOG.error("Error: [" + e.getMessage() + "]", e);
				LOG.error("Error: [" + e.getMessage() + "Linha do endere�o --> " + request.getAddress().getLine1()+ "]", e);
				return result;
			}
			
			result.setAdyenReference(payResult.getPspReference());
			if(!StringUtils.isEmpty(payResult.getRefusalReason()) && payResult.getRefusalReason().matches("(^[0-9]{3}).*")) {
				result.setErrorCode(payResult.getRefusalReason().substring(0, 3));
			}
			LOG.info("------------------------------");
			LOG.info("PaymentResult");
			LOG.info("MerchantReference: " + request.getReference());
			LOG.info("PspReference: " + payResult.getPspReference());
			LOG.info("ResultCode: " + payResult.getResultCode());
			LOG.info("AuthCode: " + payResult.getAuthCode());
			LOG.info("RefusalReason: " + payResult.getRefusalReason());
			if(payResult.getAdditionalData() != null) {
				LOG.info("Entrys...");
				for(AnyType2AnyTypeMapEntry entry : payResult.getAdditionalData()){
					if(entry.getKey() != null && entry.getKey().equals(Credentials.BOLETO_URL)) {
						result.setBoletoBancarioUrl(entry.getValue().toString());
					}
					if(entry.getKey() != null && entry.getKey().equals(Credentials.BOLETO_NOSSONUMERO)) {
						result.setBoletoBancarioNossoNumero(entry.getValue().toString());
					}
					if(entry.getKey() != null && entry.getKey().equals(Credentials.BOLETO_EXPIRATIONDATE)) {
						result.setBoletoBancarioExpirationDate(entry.getValue().toString());
					}
					if("refusalReasonRaw".equals(entry.getKey())) {
						result.setErrorCode(entry.getValue().toString());
					}
					LOG.info("Key: ["+entry.getKey()+"] Value:["+entry.getValue()+"]");
				}
			}
			
			if (payResult.getResultCode() != null && payResult.getResultCode().equals(AdyenTransactionStatus.RECEIVED)) {
				result.setTransactionStatus(TransactionStatus.ACCEPTED);
				result.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL);
			} else {
				result.setTransactionStatus(TransactionStatus.REJECTED);
				result.setTransactionStatusDetails(TransactionStatusDetails.AUTHORIZATION_REJECTED_BY_PSP);
			}
		
			result.setMerchantTransactionCode(request.getMerchantTransactionCode());
			result.setHeringAdyenErrorCode(payResult.getRefusalReason());
		}
		catch (Exception ex)
		{
			LOG.info("Unexpected Exception: " + ex);
			ex.printStackTrace();
		}
		return result;
	}
	
	private Calendar getDeliveryDate()
	{
		
		Date currentDate = new Date(System.currentTimeMillis()); 
		GregorianCalendar expirationDate = (GregorianCalendar) Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));  
		  		
		expirationDate.setTime(currentDate); 

		int dayOfPurchase = expirationDate.get(Calendar.DAY_OF_WEEK);
		
		if(dayOfPurchase == 7)
		{
			expirationDate.add(Calendar.DAY_OF_MONTH, 4);
		} else if(dayOfPurchase == 6)
		{
			expirationDate.add(Calendar.DAY_OF_MONTH, 5);
		} else if(dayOfPurchase == 5)
		{
			expirationDate.add(Calendar.DAY_OF_MONTH, 5);
		} else if(dayOfPurchase == 4)
		{
			expirationDate.add(Calendar.DAY_OF_MONTH, 5);
		}else
		{
			expirationDate.add(Calendar.DAY_OF_MONTH, 3);  
		}
		
		
		
      int day = expirationDate.get(Calendar.DAY_OF_WEEK);
		                
		if(day == 1)
      {
           expirationDate.add(Calendar.DAY_OF_MONTH, 1);
      } else if (day == 7)
      {
          expirationDate.add(Calendar.DAY_OF_MONTH, 2);
      }

		return expirationDate;
	}

}
