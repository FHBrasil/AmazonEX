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
public class MundipaggAuthorizationResult extends AuthorizationResult
{
   
   private String mundipaggInstantBuyKey;
   private String mundipaggRequestKey;
   private String mundipaggOrderKey;
   private String mundipaggTransactionKey;
   private String mundipaggTransactionReference;
   private String mundipaggOrderReference;

   public String getMundipaggInstantBuyKey()
   {
      return mundipaggInstantBuyKey;
   }

   public void setMundipaggInstantBuyKey(String MundipaggInstantBuyKey)
   {
      this.mundipaggInstantBuyKey = MundipaggInstantBuyKey;
   }

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

   public String getMundipaggTransactionReference()
   {
      return mundipaggTransactionReference;
   }

   public void setMundipaggTransactionReference(String mundipaggTransactionReference)
   {
      this.mundipaggTransactionReference = mundipaggTransactionReference;
   }

   public String getMundipaggOrderReference()
   {
      return mundipaggOrderReference;
   }

   public void setMundipaggOrderReference(String mundipaggOrderReference)
   {
      this.mundipaggOrderReference = mundipaggOrderReference;
   }
  
}
