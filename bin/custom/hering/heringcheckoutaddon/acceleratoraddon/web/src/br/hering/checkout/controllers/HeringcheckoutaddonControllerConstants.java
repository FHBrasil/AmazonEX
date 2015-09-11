/**
 * 
 */
package br.hering.checkout.controllers;

/**
 * @author Antony P
 *
 */
public interface HeringcheckoutaddonControllerConstants
{
	final String ADDON_PREFIX = "addon:/heringcheckoutaddon/";

	/**
	 * Class with view name constants
	 */
	interface Views
	{
		interface Pages
		{
			interface MultiStepCheckout
			{	
			    String AddPaymentMethodPage = ADDON_PREFIX + "pages/checkout/multi/customAddPaymentMethodPage";
                String CheckoutSummaryPage = ADDON_PREFIX + "pages/checkout/multi/customCheckoutSummaryPage";
                String ChooseDeliveryMethodPage = ADDON_PREFIX + "pages/checkout/multi/chooseDeliveryMethodPage";
                String AddEditDeliveryAddressPage = ADDON_PREFIX + "pages/checkout/multi/addEditDeliveryAddressPage";
                String RedirectToCieloPage = ADDON_PREFIX + "pages/checkout/multi/redirectToCieloPage";
            }
			
			interface SingleStepCheckout
			{
				String SingleStepCheckoutPage = ADDON_PREFIX 
				        + "pages/checkout/single/singleStepCheckoutPage";
				
				interface Fragments {
					String CheckoutOrderDetails = ADDON_PREFIX + "fragments/checkoutOrderDetailsFragment";
				}
				
			}

		}
	}
}