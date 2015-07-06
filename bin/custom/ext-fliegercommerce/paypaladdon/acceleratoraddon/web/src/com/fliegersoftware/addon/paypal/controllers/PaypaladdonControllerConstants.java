/*
 * [y] hybris Platform
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 */
package com.fliegersoftware.addon.paypal.controllers;

/**
 */
public interface PaypaladdonControllerConstants {
    
    final String ADDON_PREFIX = "addon:/paypaladdon/";
    String PayPalHostedOrderPostPage = ADDON_PREFIX + "pages/checkout/multi/hostedOrderPostPage";
    final String CHECKOUT_PAGE_VIEW_NAME_FRAGMENT = "pages/checkout/";
    final String PAY_PAL_EXPRESS_CHECKOUT_SHORTCUT_PARAM = "shortcut";
    final String PAY_PAL_HOP_URL_ATTR = "payPalHopRequestUrl";
    final String PAY_PAL_ERRORS_DETAILS = "errorsDetails";
    final String PAY_PAL_HOP_EXPRESS_CHECKOUT_SHORTCUT_URL =
            "/paypal/request/expressCheckoutShortcut";
    final String PAY_PAL_HOP_CREDIT_CART_SHORTCUT_URL = "/paypal/request/creditCartShortcut";
    
    interface Actions {
        
        interface Cms {
            
            String _Prefix = "/view/";
            String _Suffix = "Controller";
            // String PayPalMiniCartComponent = _Prefix + PayPalMiniCartComponentModel._TYPECODE
            // + _Suffix;
            // String PayPalExpressCheckoutShortcut = _Prefix
            // + PayPalExpressCheckoutShortcutModel._TYPECODE + _Suffix;
        }
    }
    
    interface Views {
        
        interface Pages {
            
            interface MultiStepCheckout {
                
                String SilentOrderPostPage = ADDON_PREFIX
                        + "pages/checkout/multi/silentOrderPostPage";
                String CheckoutSummaryPage = ADDON_PREFIX
                        + "pages/checkout/multi/checkoutSummaryPage";
                String PayPalHostedOrderPageErrorPage = ADDON_PREFIX
                        + "pages/checkout/multi/payPalErrorPage";
            }
            
            interface Checkout {
                
                String CheckoutConfirmationPage = "pages/checkout/checkoutConfirmationPage";
                String CheckoutLoginPage = "pages/checkout/checkoutLoginPage";
            }
        }
        
        interface Fragments {
            
            interface Cart {
                
                String CartPopup = ADDON_PREFIX + "fragments/cart/cartPopup";
                String AddToCartPopup = ADDON_PREFIX + "fragments/cart/addToCartPopup";
            }
        }
    }
}
