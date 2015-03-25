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
package com.pixi.api.constants;

/**
 * Global class for all Pixiapi constants. You can add global constants for your extension
 * into this class.
 * 
 * @author jfelipe
 *
 */
public final class PixiapiConstants extends GeneratedPixiapiConstants {

    public static final String EXTENSIONNAME = "pixiapi";
    public static final String PROJECT_PROPERTIES = "project.properties";


    private PixiapiConstants() {
        // empty to avoid instantiating this constant class
    }

    // Pixi API functions
    public static final String PIXIAPI_FUNCTION_GET_ORDERS = "pixiGetSOrders";
    public static final String PIXIAPI_FUNCTION_GET_ORDER_LINES = "pixiGetSOrderlines";
    public static final String PIXIAPI_FUNCTION_GET_SUPPLIERS = "pixiGetSuppliers";
    public static final String PIXIAPI_FUNCTION_GET_UPDATED_INVOICES = "pixiGetUpdatedInvoices";
    // FTP properties
    public static final String PIXIAPI_USE_DUMMY_FTP = "pixi.api.ftp.use.dummy";
    public static final String PIXIAPI_FTP_PROFILE_NRZ_ADDRESS = "pixi.api.ftp.hostname";
    public static final String PIXIAPI_FTP_PROFILE_NRZ_PORT = "pixi.api.ftp.port";
    public static final String PIXIAPI_FTP_PROFILE_NRZ_USERNAME = "pixi.api.ftp.auth.username";
    public static final String PIXIAPI_FTP_PROFILE_NRZ_PASSWORD = "pixi.api.ftp.auth.password";
    public static final String PIXIAPI_FTP_PROFILE_SERVER5_ADDRESS = "pixi.api.ftp.hostname";
    public static final String PIXIAPI_FTP_PROFILE_SERVER5_PORT = "pixi.api.ftp.port";
    public static final String PIXIAPI_FTP_PROFILE_SERVER5_USERNAME = "pixi.api.ftp.auth.username";
    public static final String PIXIAPI_FTP_PROFILE_SERVER5_PASSWORD = "pixi.api.ftp.auth.password";
    // FTP Folders
    public static final String PIXIAPI_FTP_FOLDER_CUSTOMERS = "pixi.api.ftp.folder.customers";
    public static final String PIXIAPI_FTP_FOLDER_380856090 = "pixi.api.ftp.folder.380856090";
    public static final String PIXIAPI_FTP_FOLDER_IN = "pixi.api.ftp.folder.in";
    public static final String PIXIAPI_FTP_FOLDER_LIFERANT = "pixi.api.ftp.folder.liferant";
    public static final String PIXIAPI_FTP_FOLDER_VEDES = "pixi.api.ftp.folder.vedes";
    // Files to send to FTP
    public static final String PIXIAPI_FTP_FILE_VEDE = "vede";
    // Response XML nodes / tags
    public static final String PIXIAPI_RESPONSE_TAG_SQLROWSET1 = "SqlRowSet1";
    public static final String PIXIAPI_RESPONSE_TAG_DEFAULT_DELIVERY_DAYS = "DefaultDeliveryDays";
    public static final String PIXIAPI_RESPONSE_TAG_INVOICE_KEY = "InvoiceKey";
    public static final String PIXIAPI_RESPONSE_TAG_INVOICE_NUMBER = "InvoiceNr";
    
    
    
}
