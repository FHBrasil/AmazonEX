/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mund;

import com.flieger.payment.api.service.Services;
import com.flieger.payment.api.data.schemas.ArrayOfCreditCardTransaction;
import com.flieger.payment.api.data.schemas.BoletoTransaction;
import com.flieger.payment.api.data.schemas.CreateOrderRequest;
import com.flieger.payment.api.data.schemas.CreateOrderResponse;
import com.flieger.payment.api.data.schemas.CreditCardBrandEnum;
import com.flieger.payment.api.data.schemas.CreditCardOperationEnum;
import com.flieger.payment.api.data.schemas.CreditCardTransaction;
import com.flieger.payment.api.data.schemas.CurrencyIsoEnum;
import com.flieger.payment.api.data.schemas.EmailUpdateToBuyerEnum;
import com.flieger.payment.api.data.schemas.ObjectFactory;

/**
 *
 * @author Antony
 */
public class Mund
{

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args)
	{

		CreateOrderRequest createOrderRequest = ObjectFactory.createCreateOrderRequest();

		createOrderRequest.setMerchantKey("0046469b-1dca-4e7d-b0ae-2533148cd2ba");
		createOrderRequest.setCurrencyIsoEnum(CurrencyIsoEnum.BRL);
		createOrderRequest.setOrderReference(ObjectFactory.createCreateOrderRequestOrderReference("02020202"));
		createOrderRequest.setAmountInCents(new Long("9"));
		createOrderRequest.setAmountInCentsToConsiderPaid(new Long("0"));
		createOrderRequest.setEmailUpdateToBuyerEnum(ObjectFactory.createCreateOrderRequestEmailUpdateToBuyerEnum(EmailUpdateToBuyerEnum.NO));

		BoletoTransaction boletoTransaction = ObjectFactory.createBoletoTransaction();
		boletoTransaction.setAmountInCents(new Long("9"));
		boletoTransaction.setBankNumber(ObjectFactory.createBoletoTransactionBankNumber("123"));
		boletoTransaction.setInstructions(ObjectFactory.createBoletoTransactionInstructions("Desconto de 10% até o dia 28 do mês corrente"));
		boletoTransaction.setNossoNumero(ObjectFactory.createBoletoTransactionNossoNumero("34343"));

		CreditCardTransaction creditCardTransaction = ObjectFactory.createCreditCardTransaction();
		creditCardTransaction.setAmountInCents(new Long("9"));
		creditCardTransaction.setCreditCardBrandEnum(CreditCardBrandEnum.MASTERCARD);
		creditCardTransaction.setInstallmentCount(0);
		creditCardTransaction.setCreditCardNumber(ObjectFactory.createCreditCardTransactionCreditCardNumber("518294****4019"));
		creditCardTransaction.setExpMonth(9);
		creditCardTransaction.setExpYear(14);
		creditCardTransaction.setHolderName(ObjectFactory.createCreditCardTransactionHolderName("Rui Barbosa"));
		creditCardTransaction.setSecurityCode(ObjectFactory.createCreditCardTransactionSecurityCode("197"));
		creditCardTransaction.setPaymentMethodCode(1);


		creditCardTransaction.setCreditCardOperationEnum(ObjectFactory.createCreditCardTransactionCreditCardOperationEnum(CreditCardOperationEnum.AUTH_ONLY));


		ArrayOfCreditCardTransaction creditCardTransactionCollection = ObjectFactory.createArrayOfCreditCardTransaction();

		creditCardTransactionCollection.getCreditCardTransaction().add(creditCardTransaction);


		createOrderRequest.setCreditCardTransactionCollection(ObjectFactory.createCreateOrderRequestCreditCardTransactionCollection(creditCardTransactionCollection));
//		
//		ArrayOfBoletoTransaction boleto = ObjectFactory.createArrayOfBoletoTransaction();
//		boleto.getBoletoTransaction().add(boletoTransaction);
//		
//		createOrderRequest.setBoletoTransactionCollection(ObjectFactory.createCreateOrderRequestBoletoTransactionCollection(boleto));
		
		
		CreateOrderResponse createOrderResponse = null;

		Services service = new Services();

		try
		{
			createOrderResponse = service.createOrder(createOrderRequest);
		} catch (Exception e)
		{
			System.out.print(e);
		}

	}

}
