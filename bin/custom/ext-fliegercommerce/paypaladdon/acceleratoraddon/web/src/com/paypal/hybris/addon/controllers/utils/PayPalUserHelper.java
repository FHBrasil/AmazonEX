package com.paypal.hybris.addon.controllers.utils;

import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.servicelayer.session.SessionService;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.util.CookieGenerator;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Yehor_Tsebro (EPAM Systems)
 */
@Component
public class PayPalUserHelper {

    @Resource(name = "userFacade")
    private UserFacade userFacade;

    @Resource(name = "sessionService")
    private SessionService sessionService;

    @Resource(name = "guidCookieGenerator")
    private CookieGenerator cookieGenerator;

    public static final String ANONYMOUS_CHECKOUT = "anonymous_checkout";



    public static final String REQUEST_MODEL_ATTRIBUTE_NAME = "request";
    public static final String SECURE_GUID_SESSION_KEY = "acceleratorSecureGUID";

    private boolean isAnonymousCheckout() {
        return BooleanUtils.toBoolean((Boolean) sessionService.getAttribute(ANONYMOUS_CHECKOUT));
    }

    public boolean isHardLogin(final Model model) {

        HttpServletRequest request = (HttpServletRequest) model.asMap().get(REQUEST_MODEL_ATTRIBUTE_NAME);
        return isHardLogin(request, false);
    }


    public boolean isHardLogin(HttpServletRequest request, boolean ignoreCookie) {
        boolean isHardLogin = false;

        final String guid = (String) request.getSession().getAttribute(SECURE_GUID_SESSION_KEY);
        if (((!userFacade.isAnonymousUser() || isAnonymousCheckout()) &&
                (checkForGUIDCookie(request, guid) || ignoreCookie))) {
            isHardLogin = true;
        }
        return isHardLogin;
    }


    protected boolean checkForGUIDCookie(final HttpServletRequest request, final String guid) {
        if (guid != null && request.getCookies() != null) {
            final String guidCookieName = cookieGenerator.getCookieName();
            if (guidCookieName != null) {
                for (final Cookie cookie : request.getCookies()) {
                    if (guidCookieName.equals(cookie.getName())) {
                        if (guid.equals(cookie.getValue())) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }


}
