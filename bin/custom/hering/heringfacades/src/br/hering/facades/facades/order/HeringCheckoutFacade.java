/**
 * 
 */
package br.hering.facades.facades.order;

import de.hybris.platform.acceleratorfacades.order.AcceleratorCheckoutFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

import br.hering.facades.order.data.PaymentModeData;

import com.flieger.payment.data.HeringDebitPaymentInfoData;

/**
 * @author flieger
 * @author franthescollymaneira
 */
public interface HeringCheckoutFacade extends AcceleratorCheckoutFacade {
    
    void setCustomPaymentInfo(CartData cartData);
    
    
    /** PORQUE TEM QUE SETAR A MESMA COISA DUAS VEZES? */
    public void savePaymentInfoIntoCart(CartData cartData);
    
    
    // boolean setPaymentDetails(final HeringDebitPaymentInfoData debitPaymentInfoData);
    boolean authorizeDebitPayment();
    
    
    boolean authorizeDebitPayment3D(final String paymentInfoPK);
    
    
    void setPaymentMode(PaymentModeData paymentModeData);
    
    
    CartData getCheckoutCart();
    
    
    CustomerData getUserForCheckout();
    
    
    void resetDeliveryMode();
    
    
    List<String> formatInstallmentsCost(final CartData cartData);
    
    
    HeringDebitPaymentInfoData createPaymentSubscription(
            final HeringDebitPaymentInfoData paymentInfoData);
    
    
    boolean authorizeBoleto();
    
    
    /**
     * Autoriza pagamento com cartão de crédito.
     * Sobrescreve authorizePayment(String securityCode) porque o securityCode
     * fica na sessão, junto com o número do cartão de crédito
     */
    boolean authorizePayment();
    
    
    CustomerModel getCurrentUserForCheckout();
    
    
    void removeDeliveryAddress(final AddressData addressData);
    
    /**
     * 
     * @param cartData
     */
    void saveBillingAddressIntoCart(AddressData addressData);
}