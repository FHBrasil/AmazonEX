/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.payment.commands.result;

import de.hybris.platform.payment.commands.result.AuthorizationResult;

/**
 *
 * @author Antony
 */
public class MundipaggBoletoResult extends AuthorizationResult
{
   
   private String mundipaggRequestKey;
   private String mundipaggOrderKey;
   private String mundipaggTransactionKey;
   private String mundipaggBoletoURL;
   private String mundipagTransactionReference;
   private String mundipaggOrderReference;
   private String mundipaggBoletoBarCode;
   private String mundipaggBoletoStatus;
   private String mundipaggBoletoNossoNumero;
   private String mundipaggTransactionReference;

   public String getMundipaggRequestKey()
   {
      return mundipaggRequestKey;
   }

   public void setMundipaggRequestKey(String mundipaggRequestKey)
   {
      this.mundipaggRequestKey = mundipaggRequestKey;
   }

   public String getMundipaggOrderKey()
   {
      return mundipaggOrderKey;
   }

   public void setMundipaggOrderKey(String mundipaggOrderKey)
   {
      this.mundipaggOrderKey = mundipaggOrderKey;
   }

   public String getMundipaggTransactionKey()
   {
      return mundipaggTransactionKey;
   }

   public void setMundipaggTransactionKey(String mundipaggTransactionKey)
   {
      this.mundipaggTransactionKey = mundipaggTransactionKey;
   }

   public String getMundipaggBoletoURL()
   {
      return mundipaggBoletoURL;
   }

   public void setMundipaggBoletoURL(String mundipaggBoletoURL)
   {
      this.mundipaggBoletoURL = mundipaggBoletoURL;
   }

   public String getMundipagTransactionReference()
   {
      return mundipagTransactionReference;
   }

   public void setMundipagTransactionReference(String mundipagTransactionReference)
   {
      this.mundipagTransactionReference = mundipagTransactionReference;
   }

   public String getMundipaggOrderReference()
   {
      return mundipaggOrderReference;
   }

   public void setMundipaggOrderReference(String mundipaggOrderReference)
   {
      this.mundipaggOrderReference = mundipaggOrderReference;
   }

   public String getMundipaggBoletoBarCode()
   {
      return mundipaggBoletoBarCode;
   }

   public void setMundipaggBoletoBarCode(String mundipaggBoletoBarCode)
   {
      this.mundipaggBoletoBarCode = mundipaggBoletoBarCode;
   }

   public String getMundipaggBoletoStatus()
   {
      return mundipaggBoletoStatus;
   }

   public void setMundipaggBoletoStatus(String mundipaggBoletoStatus)
   {
      this.mundipaggBoletoStatus = mundipaggBoletoStatus;
   }

   public String getMundipaggBoletoNossoNumero()
   {
      return mundipaggBoletoNossoNumero;
   }

   public void setMundipaggBoletoNossoNumero(String mundipaggBoletoNossoNumero)
   {
      this.mundipaggBoletoNossoNumero = mundipaggBoletoNossoNumero;
   }

   public String getMundipaggTransactionReference()
   {
      return mundipaggTransactionReference;
   }

   public void setMundipaggTransactionReference(String mundipaggTransactionReference)
   {
      this.mundipaggTransactionReference = mundipaggTransactionReference;
   }

}
