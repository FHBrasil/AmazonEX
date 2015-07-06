/*
 * [y] hybris Platform
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 */
package de.hybris.platform.addonsupport.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

import com.hybris.addon.common.config.javascript.BeforeViewJsPropsHandlerAdaptee;

public class ExportJsPropertiesBeforeViewHandler extends BeforeViewJsPropsHandlerAdaptee {
    
    @Override
    public String beforeViewJsProps(final HttpServletRequest request,
            final HttpServletResponse response, final ModelMap model, final String viewName) {
        return viewName;
    }
}
