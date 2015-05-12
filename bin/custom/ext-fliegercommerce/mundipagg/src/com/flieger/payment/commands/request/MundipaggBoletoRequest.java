/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.payment.commands.request;

import com.flieger.payment.model.MundipaggBoletoPaymentInfoModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.payment.commands.request.AbstractRequest;
import de.hybris.platform.payment.dto.BillingInfo;
import de.hybris.platform.payment.dto.CardInfo;
import java.math.BigDecimal;
import java.util.Currency;

/**
 *
 * @author Antony
 */
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.payment.commands.request.AbstractRequest;
import de.hybris.platform.payment.dto.BillingInfo;
import de.hybris.platform.payment.dto.CardInfo;
import java.math.BigDecimal;
import java.util.Currency;

public class MundipaggBoletoRequest extends AbstractRequest
{

   
   private final String paymentProvider;
   private CartModel cart;
   private final String merchantTransactionCode;
   private MundipaggBoletoPaymentInfoModel paymetInfo;

   public MundipaggBoletoRequest(String paymentProvider, 
           CartModel cart, String merchantTransactionCode, MundipaggBoletoPaymentInfoModel paymetInfo)
   {
      super(merchantTransactionCode);
      
      this.paymentProvider = paymentProvider;
      this.cart = cart;
      this.merchantTransactionCode = merchantTransactionCode;
      this.paymetInfo = paymetInfo;
   }

   public CartModel getCart()
   {
      return cart;
   }

   public void setCart(CartModel cart)
   {
      this.cart = cart;
   }

   public String getPaymentProvider()
   {
      return paymentProvider;
   }

   public MundipaggBoletoPaymentInfoModel getPaymetInfo()
   {
      return paymetInfo;
   }

   public void setPaymetInfo(MundipaggBoletoPaymentInfoModel paymetInfo)
   {
      this.paymetInfo = paymetInfo;
   }

}
