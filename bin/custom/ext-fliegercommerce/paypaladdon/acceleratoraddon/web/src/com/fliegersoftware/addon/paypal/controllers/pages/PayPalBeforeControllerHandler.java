package com.fliegersoftware.addon.paypal.controllers.pages;

import com.fliegersoftware.addon.paypal.controllers.PaypaladdonControllerConstants;

import com.hybris.addon.common.interceptors.BeforeControllerHandlerAdaptee;
import de.hybris.platform.servicelayer.session.SessionService;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Yehor_Tsebro (EPAM Systems)
 */
public class PayPalBeforeControllerHandler implements BeforeControllerHandlerAdaptee {
    
    private SessionService sessionService;
    public static final String CART_CHECKOUT = "cart/checkout";
    
    
    /**
     * 
     */
    @Override
    public boolean beforeController(HttpServletRequest request, HttpServletResponse response,
            HandlerMethod handler) throws Exception {
        if (StringUtils.contains(request.getRequestURI(), CART_CHECKOUT)) {
            getSessionService().removeAttribute(PaypaladdonControllerConstants.PAY_PAL_HOP_URL_ATTR);
        }
        return true;
    }
    
    
    public SessionService getSessionService() {
        return sessionService;
    }
    
    
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }
}
