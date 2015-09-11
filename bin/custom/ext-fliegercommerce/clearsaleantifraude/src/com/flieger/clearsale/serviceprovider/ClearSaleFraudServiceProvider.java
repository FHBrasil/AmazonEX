/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.clearsale.serviceprovider;

import de.hybris.platform.basecommerce.enums.FraudStatus;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.fraud.FraudServiceProvider;
import de.hybris.platform.fraud.impl.FraudServiceResponse;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.flieger.clearsale.utils.SessionIDCreator;
import com.flieger.clearsale.webserviceclient.SOAPservices.ClearSaleSOAPServices;
import com.flieger.clearsale.webserviceclient.SOAPservices.impl.DefaultClearSaleSOAPServices;
import com.flieger.clearsale.webserviceclient.beans.Address;
import com.flieger.clearsale.webserviceclient.beans.BillingData;
import com.flieger.clearsale.webserviceclient.beans.ClearSale;
import com.flieger.clearsale.webserviceclient.beans.FingerPrint;
import com.flieger.clearsale.webserviceclient.beans.Item;
import com.flieger.clearsale.webserviceclient.beans.Order;
import com.flieger.clearsale.webserviceclient.beans.Payment;
import com.flieger.clearsale.webserviceclient.beans.PaymentTypeEnum;
import com.flieger.clearsale.webserviceclient.beans.PersonTypeEnum;
import com.flieger.clearsale.webserviceclient.beans.Phone;
import com.flieger.clearsale.webserviceclient.beans.PhoneTypeEnum;
import com.flieger.clearsale.webserviceclient.beans.ShippingData;
import com.flieger.clearsale.webserviceclient.responsebeans.ClearSaleResponse;
import com.flieger.clearsale.webserviceclient.responsebeans.GeneralOrderStatusResponse;
import com.flieger.clearsale.webserviceclient.responsebeans.PackageStatusResponse;
import com.flieger.clearsale.webserviceclient.responsebeans.StatusEnumOUT;
import com.flieger.model.ClearSaleFraudReportModel;
import com.flieger.payment.model.BoletoPaymentInfoModel;
import com.flieger.payment.model.HeringDebitPaymentInfoModel;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.collections.CollectionUtils;

/**
 *
 * @author Antony
 */
public class ClearSaleFraudServiceProvider 
implements FraudServiceProvider
{
	private static final Logger LOG = Logger.getLogger(ClearSaleFraudServiceProvider.class);

	private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("([0-9]{8,9})$");
	
	private static final Pattern PHONE_DDD_PATTERN = Pattern.compile(".*([0-9]{2})$");
	
	private static final String DEFAULT_STORE_UID = "dzarm";

	private String providerName;

	@Resource
	private ModelService modelService;

	@Resource
	protected CommonI18NService commonI18NService;

	@Override
	public FraudServiceResponse recognizeOrderFraudSymptoms(final AbstractOrderModel order)
	{
		//resolve the baseStoreUid where the order as made - this should not be necessary if we were sure that the order was properly setup
		String baseStoreUid = DEFAULT_STORE_UID;
		if (order.getStore() != null && StringUtils.isNotBlank(order.getStore().getUid()))
		{
			baseStoreUid = order.getStore().getUid();
		}

		final ClearSaleSOAPServices clearSaleSOAPServices = 
				getClearSaleSOAPService(baseStoreUid);

		// setting values into beans that are been used system widely - this is not right
		//TODO - código que só funciona no brasil
		commonI18NService.setCurrentLanguage(commonI18NService.getLanguage("pt"));
		commonI18NService.setCurrentCurrency(commonI18NService.getCurrency("BRL"));
		LOG.info(commonI18NService.getCurrentLanguage().getName() + " " 
				+ commonI18NService.getCurrentCurrency().getName());
		
		final ClearSale clearsale = createClearSaleObj(order);

		PackageStatusResponse response = null;
		String operationResult = null;
		
		final ClearSaleFraudReportModel fraudReportModel = 
				modelService.create(ClearSaleFraudReportModel.class);
				
		try
		{	
			//if the order is authorized, send it to the clearsale
			if (isOrderAuthorized(order))
			{
				response = clearSaleSOAPServices.sendOrders(clearsale);
			} else {
				throw new Exception("Order: [" + order.getCode()
						+"] is not authorized. Cannot continue");
			}
			//nesta resposta já pode haver uma posição da clearsale sobre o pedido,
			//senão, temos que pegar um status atualizado - na dúvida, sempre pegamos
			//o status atualizado
			if (		response.getStatusCode().equalsIgnoreCase("00")
					|| response.getStatusCode().equalsIgnoreCase("05")) {
				
				final StatusEnumOUT status = getUpdatedStatus(order.getCode(), 
						clearSaleSOAPServices);
				
				LOG.info("Successfully checked order status at ClearSale. Order:[" 
						+ order.getCode() 
						+ "]. Status:[" + status.name() + "]. Response: ["
						+ response.getStatusCode() + "]");
				
				fraudReportModel.setTransactionID(response.getTransactionID());
				fraudReportModel.setStatusCode(status.name());
				
				if(CollectionUtils.isNotEmpty(response.getOrders())){
					fraudReportModel.setMessage(response.getOrders().get(0).getMessage());
					fraudReportModel.setOrderScore(response.getOrders().get(0).getScoreOrder());
				}
				
				fraudReportModel.setTimestamp(new Date());
				fraudReportModel.setCode(order.getCode() + "_FR");
				fraudReportModel.setOrder((OrderModel)order);
				fraudReportModel.setStatus(FraudStatus.OK);
				
				operationResult = "OK";
			}  else {//status é diferente de ok e de já está lá
				LOG.info("Failed checking order status at ClearSale. Order:[" 
						+ order.getCode() 
						+ "]. Response:[" + response.getMessage() 
						+ "]. StatusCode:[" + response.getStatusCode() + "]");
				
				fraudReportModel.setTransactionID(response.getTransactionID());
				fraudReportModel.setStatusCode(response.getStatusCode());
				
				String msg = response.getMessage();
				int maxLength = (msg.length() < 255) ? msg.length() : 255;
				msg = msg.substring(0, maxLength);
				fraudReportModel.setMessage(response.getMessage());
				
				fraudReportModel.setOrderScore("0");
				fraudReportModel.setTimestamp(new Date());
				fraudReportModel.setCode(order.getCode() + "_FR");
				fraudReportModel.setOrder((OrderModel)order);
				fraudReportModel.setStatus(FraudStatus.NOTSENT);
				
				operationResult = "NOT_OK";
			}
		}
		catch (final Exception e)
		{
			String msg = "Unexpected Error:Failed sending orders:{Order[" 
					+ order.getCode() 
					+ "];Exception[" + e + "\n" + e.getMessage() + "]";
			LOG.info(msg);
			e.printStackTrace();
			
			int maxLength = (msg.length() < 255) ? msg.length() : 255;
			msg = msg.substring(0, maxLength);
						
			fraudReportModel.setStatusCode(StatusEnumOUT.ERRO.name());
			fraudReportModel.setMessage(msg);
			fraudReportModel.setOrderScore("0");
			fraudReportModel.setTimestamp(new Date());
			fraudReportModel.setCode(order.getCode() + "_FR");
			fraudReportModel.setOrder((OrderModel)order);
			fraudReportModel.setStatus(FraudStatus.NOTSENT);
			
			operationResult = "NOT_OK";
		}
		
		order.setStatus("OK".equals(operationResult) 
				? OrderStatus.FRAUD_CHECKED : OrderStatus.ON_VALIDATION);
		
//		operação não suportada para este set
//		...((OrderModel)order).getFraudReports().add(fraudReportModel);
		
		modelService.save(fraudReportModel);
		modelService.save(order);
		
		return new FraudServiceResponse(operationResult, getProviderName());
	}

	/**
	 * Verifica se a order esta autorizada e sem impedimento
	 *
	 * @param arg0
	 */
	private boolean isOrderAuthorized(final AbstractOrderModel arg0)
	{
		if(		arg0 == null
				|| CollectionUtils.isEmpty(arg0.getPaymentTransactions())
				|| CollectionUtils.isEmpty(
						arg0.getPaymentTransactions().get(0).getEntries())){
			
			LOG.info("Error: Order: " + arg0.getCode() 
					+ " does not have any PaymentTransactionEntry");
			return false;
		}
		final List<PaymentTransactionEntryModel> list = 
				arg0.getPaymentTransactions().get(0).getEntries();
		
		final PaymentTransactionEntryModel entry = list.get(0);
		
		if (PaymentTransactionType.AUTHORIZATION.equals(entry.getType()))
		{
			if (TransactionStatus.ACCEPTED.name().equals(
					entry.getTransactionStatus()))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public FraudServiceResponse recognizeUserActivitySymptoms(final UserModel arg0)
	{
		return null;
	}

	@Override
	public String getProviderName()
	{
		return this.providerName;
	}

	public void setProviderName(final String providerName)
	{
		this.providerName = providerName;
	}

//	public PackageStatusResponse sendOrdersResponse(final AbstractOrderModel arg0)
//	{
//		commonI18NService.setCurrentLanguage(commonI18NService.getLanguage("pt"));
//		commonI18NService.setCurrentCurrency(commonI18NService.getCurrency("BRL"));
//		LOG.info(commonI18NService.getCurrentLanguage().getName() + " " + commonI18NService.getCurrentCurrency().getName());
//		final ClearSale clearSale = createClearSaleObj(arg0);
//		return getDefaultClearSaleSOAPServices().sendOrders(clearSale);
//
//	}

	private ClearSale createClearSaleObj(final AbstractOrderModel arg0)
	{

		final ClearSale clearSale = new ClearSale();
		clearSale.getOrders().add(createClearSaleOrder(arg0));
		return clearSale;

	}

	private Order createClearSaleOrder(final AbstractOrderModel arg0)
	{

		final Order order = new Order();

		final String currDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date(System.currentTimeMillis()));

		final FingerPrint fingerPrint = new FingerPrint();
		fingerPrint.setSessionID(SessionIDCreator.createSessionID(arg0.getGuid()));

		order.setFingerPrint(fingerPrint);
		order.setID(arg0.getCode());
		order.setDate(currDate);
		if (arg0.getUser().getUid().contains("|"))
		{
			order.setEmail(arg0.getUser().getUid().split("\\|")[1]);
		}
		else
		{
			order.setEmail(arg0.getUser().getUid());
		}
		order.setTotalItemsValue(arg0.getSubtotal());
		order.setTotalOrderValue(arg0.getTotalPrice());
		if (arg0.getPaymentInfo() instanceof CreditCardPaymentInfoModel)
		{
			order.setQtyInstallments(((CreditCardPaymentInfoModel) arg0.getPaymentInfo()).getInstallment());
		}
		else
		{
			order.setQtyInstallments(new Integer(1));
		}
		order.setBillingData(createClearSaleBillingData(arg0));
		order.setShippingPrice(arg0.getDeliveryCost());
		order.setShippingData(createClearSaleShippingData(arg0));
		order.getPayments().add(createClearSalePayment(arg0));
		order.setQtyPaymentTypes(new Integer(order.getPayments().size()));
		order.setItems(createClearSaleItem(arg0));
		order.setQtyItems(new Integer(order.getItems().size()));
		LOG.info(order.toString());
		return order;
	}

	private BillingData createClearSaleBillingData(final AbstractOrderModel arg0)
	{
		final BillingData billingData = new BillingData();

		billingData.setID(arg0.getUser().getUid());

		UserModel user = arg0.getUser();
		if (user instanceof CustomerModel)
		{
			String cpfCnpj = ((CustomerModel) user).getCpfcnpj();
			if (StringUtils.isNotBlank(cpfCnpj) && cpfCnpj.length() < 14)
			{
				billingData.setType(PersonTypeEnum.PESSOA_FISICA);
			}
			else
			{
				billingData.setType(PersonTypeEnum.PESSOA_JURIDICA);
			}
		}
		else
		{
			billingData.setType(PersonTypeEnum.PESSOA_FISICA);
		}

		billingData.setLegalDocument1(((CustomerModel) arg0.getUser()).getCpfcnpj());
		billingData.setName(arg0.getPaymentInfo().getBillingAddress().getFirstname() + " "
				+ arg0.getPaymentInfo().getBillingAddress().getLastname());
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		if (((CustomerModel) arg0.getUser()).getBirthday() != null)
		{
			billingData.setBirthDate(sdf.format(((CustomerModel) arg0.getUser()).getBirthday()));
		}
		final Address address = new Address();

		address.setStreet(arg0.getPaymentInfo().getBillingAddress().getLine1());
		address.setCity(arg0.getPaymentInfo().getBillingAddress().getTown());
		address.setNumber(arg0.getPaymentInfo().getBillingAddress().getStreetnumber());
		address.setCounty(arg0.getPaymentInfo().getBillingAddress().getDistrict());
		address.setState(arg0.getPaymentInfo().getBillingAddress().getRegion().getIsocodeShort());
		address.setZipCode(new Integer(arg0.getPaymentInfo().getBillingAddress().getPostalcode()));
		
		billingData.setAddress(address);
		
		final Phone phone = resolvePhone(arg0.getCode(),
				arg0.getPaymentInfo().getBillingAddress(),
				PhoneTypeEnum.COBRANCA);
		if (phone == null)
		{
			LOG.error("Cliente sem telefone cadastrado.");
		}
		else
		{
			billingData.getPhones().add(phone);
		}
		return billingData;
	}

	/**
	 * @param arg0
	 * @return Phone
	 */
	private Phone resolvePhone(final String orderCode,
			final AddressModel address,
			final PhoneTypeEnum phoneType)
	{
		boolean phoneBook = false;
		final Phone phone = new Phone();
		phone.setType(phoneType);
		
		if (StringUtils.isNotBlank(address.getPhone1()))
		{
			if (StringUtils.isBlank(address.getDddPhone()))
			{
				LOG.info("Cliente sem DDD cadastrado para o telefone fixo do PaymentInfo.BillingAddress.");
				phoneBook = false;
			} else {
				phone.setType(PhoneTypeEnum.RESIDENCIAL);
				
				final String[] phoneInformation = applyNumberPattern(
						address.getPhone1(),
						PHONE_NUMBER_PATTERN);
				//check the values returned before continue to avoid exceptions
				if(		phoneInformation != null 
						&& phoneInformation.length == 2
						&& StringUtils.isNotBlank(phoneInformation[1])){
					phone.setNumber(phoneInformation[1]);
					phoneBook = true;
				} else {
					LOG.info("Telefone invalido: " + address.getPhone1()
							+ "/" + phoneInformation + ". Order: " + orderCode);
					phoneBook = false;
				}
				
				final String[] dddNumber = applyNumberPattern(address.getDddPhone(),
						PHONE_DDD_PATTERN); 

				if(		dddNumber != null 
						&& dddNumber.length == 2
						&& StringUtils.isNotBlank(dddNumber[1])){
					phone.setDDD(dddNumber[1]);
				} else {
					LOG.info("DDD invalido: " + address.getDddPhone() 
							+ "/" + dddNumber + ". Order: " + orderCode);
					phoneBook = false;
				}
			}
		}
		
		if (		!phoneBook
				&& StringUtils.isNotBlank(address.getCellphone()))
		{
			if (StringUtils.isBlank(address.getDddCellPhone()))
			{
				LOG.error("Cliente sem DDD cadastrado para telefone celular do Address:" 
						+ address.getPk());
				phoneBook = false;
			} else {
				final String[] phoneNumber = applyNumberPattern(
						address.getCellphone(),
						PHONE_NUMBER_PATTERN);
				if(		phoneNumber != null 
						&& phoneNumber.length == 2
						&& StringUtils.isNotBlank(phoneNumber[1])){
					phone.setNumber(phoneNumber[1]);
					phoneBook = true;
				} else {
					LOG.info("Celular invalido: " 
							+ address.getCellphone() 
							+ "/" + phoneNumber + ". Order: " + orderCode);
					phoneBook = false;
				}
				
				final String[] dddNumber = applyNumberPattern(
						address.getDddCellPhone(), 
						PHONE_DDD_PATTERN); 

				if(		dddNumber != null 
						&& dddNumber.length == 2
						&& StringUtils.isNotBlank(dddNumber[1])){
					phone.setDDD(dddNumber[1]);
					phoneBook = true;
				}  else {
					LOG.info("DDD invalido: " 
							+ address.getDddCellPhone()
							+ "/" + dddNumber + ". Order: " + orderCode);
					phoneBook = false;
				}
				phone.setType(PhoneTypeEnum.CELULAR);
			}
		}
		
		return phoneBook ? phone : null;
	}

	/**
	 * @param phone1
	 * @return
	 * Applies a number pattern to a numeric string and returns the groups found 
	 * in the numeric string. If the numeric string is null, returns null
	 */
	private String[] applyNumberPattern(final String number, final Pattern pattern)
	{
		if(number == null){
			return null;
		} 
		
		final Matcher matcher = pattern.matcher(number.replaceAll("[^\\d.]", ""));
		
		if(matcher.matches()){
			String[] result = new String[matcher.groupCount()+1]; 
			for (int i = 0; i<= matcher.groupCount(); i++)
			{
				result[i] = matcher.group(i);
			}
			return result;
		} 		
		return null;
	}

	private ShippingData createClearSaleShippingData(final AbstractOrderModel arg0)
	{
		final ShippingData shippingData = new ShippingData();

		shippingData.setID(arg0.getUser().getUid());
		
		UserModel user = arg0.getUser();
		if (user instanceof CustomerModel)
		{
			String cpfCnpj = ((CustomerModel) user).getCpfcnpj();
			if (StringUtils.isNotBlank(cpfCnpj) && cpfCnpj.length() < 14)
			{
				shippingData.setType(PersonTypeEnum.PESSOA_FISICA);
			}
			else
			{
				shippingData.setType(PersonTypeEnum.PESSOA_JURIDICA);
			}
		}
		else
		{
			shippingData.setType(PersonTypeEnum.PESSOA_FISICA);
		}
		
		shippingData.setLegalDocument1(((CustomerModel) arg0.getUser()).getCpfcnpj());
		shippingData.setName(arg0.getDeliveryAddress().getFirstname() + " " + arg0.getDeliveryAddress().getLastname());
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		if (((CustomerModel) arg0.getUser()).getBirthday() != null)
		{
			shippingData.setBirthDate(sdf.format(((CustomerModel) arg0.getUser()).getBirthday()));
		}
		final Address address = new Address();

		address.setStreet(arg0.getDeliveryAddress().getLine1());
		address.setNumber(arg0.getDeliveryAddress().getStreetnumber());
		address.setCounty(arg0.getDeliveryAddress().getDistrict());
		address.setState(arg0.getDeliveryAddress().getRegion().getIsocodeShort());
		address.setCity(arg0.getDeliveryAddress().getTown());
		address.setZipCode(new Integer(arg0.getDeliveryAddress().getPostalcode()));
		address.setComp(arg0.getDeliveryAddress().getComplemento());

		shippingData.setAddress(address);

		final Phone phone = resolvePhone(arg0.getCode(), 
				arg0.getDeliveryAddress(), PhoneTypeEnum.COBRANCA);
		
		if (phone == null)
		{
			LOG.error("Cliente sem shippingData.telefone cadastrado.");
		}
		else
		{
			shippingData.getPhones().add(phone);
		}

		return shippingData;

	}

	private Payment createClearSalePayment(final AbstractOrderModel arg0)
	{
		final String date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(arg0.getPaymentInfo().getCreationtime());

		final Payment payment = new Payment();

		payment.setDate(date);
		payment.setAmount(arg0.getTotalPrice());
		if (arg0.getPaymentInfo() instanceof CreditCardPaymentInfoModel)
		{
			CreditCardPaymentInfoModel paymentInfo = (CreditCardPaymentInfoModel)arg0.getPaymentInfo();
			
			payment.setQtyInstallments(paymentInfo.getInstallment());
			payment.setPaymentTypeID(PaymentTypeEnum.CARTAO_DE_CREDITO);
			payment.setCardExpirationDate(paymentInfo.getValidToMonth()+"/"+paymentInfo.getValidToYear());
			payment.setCardEndNumber(paymentInfo.getNumber());
			payment.setName(paymentInfo.getUser().getDisplayName());
			payment.setLegalDocument(paymentInfo.getCpf());
		}
		else
		//if (arg0.getCustomPaymentInfo() != null)
		{
			payment.setQtyInstallments(new Integer(1));
			if (arg0.getPaymentInfo() instanceof BoletoPaymentInfoModel)
			{
				BoletoPaymentInfoModel paymentInfo = (BoletoPaymentInfoModel)arg0.getPaymentInfo();
				payment.setPaymentTypeID(PaymentTypeEnum.BOLETO_BANCARIO);
				payment.setLegalDocument(paymentInfo.getCpf());
				payment.setName(paymentInfo.getUser().getDisplayName());
			}
			else if (arg0.getPaymentInfo() instanceof HeringDebitPaymentInfoModel)
			{
				payment.setPaymentTypeID(PaymentTypeEnum.DEBITO_BANCARIO);
			}
		}
		
		payment.setCurrency(new Integer(986));//Codigo referente ao BRL
		return payment;
	}

	private List<Item> createClearSaleItem(final AbstractOrderModel arg0)
	{
		final List<Item> items = new ArrayList<>();

		for (final AbstractOrderEntryModel entry : arg0.getEntries())
		{
			final Item item = new Item();
			item.setID(entry.getProduct().getCode());
			item.setName(entry.getProduct().getName());
			item.setItemValue(entry.getBasePrice());
			item.setQty(new Integer(entry.getQuantity().intValue()));

			items.add(item);
		}
		return items;
	}

	private StatusEnumOUT getUpdatedStatus(final String orderId, 
			final ClearSaleSOAPServices clearSaleSOAPServices)
	{
		ClearSaleResponse retorno = null;
		try
		{
			retorno = clearSaleSOAPServices.getOrderStatus(orderId);
			final Collection<GeneralOrderStatusResponse> orders = retorno.getOrders();
			return orders.iterator().next().getStatusOrder();
		}
		catch (final Exception e)
		{
			e.printStackTrace();
			return StatusEnumOUT.ERRO;
		}
	}
	
	private ClearSaleSOAPServices getClearSaleSOAPService(final String baseStoreUID) throws RuntimeException{
		if(StringUtils.isEmpty(baseStoreUID)){
			throw new RuntimeException("baseStoreUID cannot be empty/null");
		}
		try
		{
			return new DefaultClearSaleSOAPServices(baseStoreUID);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
