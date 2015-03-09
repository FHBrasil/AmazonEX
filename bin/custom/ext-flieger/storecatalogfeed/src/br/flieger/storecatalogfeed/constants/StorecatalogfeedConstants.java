/*
 * [y] hybris Platform
 * 
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 */
package br.flieger.storecatalogfeed.constants;

import de.hybris.platform.util.Config;

/**
 * Global class for all Storecatalogfeed constants. You can add global constants for your
 * extension into this class.
 */
public final class StorecatalogfeedConstants extends GeneratedStorecatalogfeedConstants {

    public static final String EXTENSIONNAME = "storecatalogfeed";


    private StorecatalogfeedConstants() {
        // empty to avoid instantiating this constant class
    }

    public static final String FILE_PATH;
    public static final String FILE_PREFIX;
    public static final int PRODUCTS_LIMIT;
    // TODO melhorar: remover essas constantes e add no abstractthread
    static {
        FILE_PATH = Config.getParameter("export.files.path");
        FILE_PREFIX = Config.getParameter("export.files.prefix.name");
        PRODUCTS_LIMIT = Config.getInt("export.products.limit", 1000);
    }
}
