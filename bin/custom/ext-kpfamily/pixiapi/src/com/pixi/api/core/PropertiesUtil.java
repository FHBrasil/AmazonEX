package com.pixi.api.core;

import org.apache.log4j.Logger;

import de.hybris.platform.util.Config;

/**
 * This class is a singleton, its means that only one instance per JVM of this class
 * exists.
 * 
 * @author Gustavo Kopper
 * @author jfelipe
 */
public class PropertiesUtil {

    private static final Logger LOG = Logger.getLogger(PropertiesUtil.class);
    private static PropertiesUtil INSTANCE;


    /**
     * Falls the instance not exists this method create one, otherwise just return the
     * actual
     * 
     * @return PropertiesUtil
     */
    public static PropertiesUtil getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PropertiesUtil();
        }
        return INSTANCE;
    }

    private String address = null;              // the webservices address
    private String nameSpace = null;            // the webservices namespace
    private String prefix = null;               // the prefix from the xml elements
    private String stockSupplierDir = null;     // the dir that contain the supplier stock
    private String username = null;             // the webservices username
    private String password = null;             // the webservices password
    private String hybrisFile = null;           // the csv file that contain products from hybris
    private String hybrisFileUrl = null;        // the csv file url to download the file
    private String matchToVerfuegbar = null;    // the regex to validate when is verfuegbar
    private String defaultStockQty = null;      // the csv file url to download the file
    private String servicePath;
    private String domain;


    /**
     * 
     * 
     */
    private PropertiesUtil() {
        try {
            matchToVerfuegbar = Config.getParameter("constant.matchToVerfuegbar");
            defaultStockQty = Config.getParameter("constant.defaultStockQty");
            address = Config.getParameter("ws.address");
            nameSpace = Config.getParameter("ws.namespace");
            prefix = Config.getParameter("ws.prefix");
            stockSupplierDir = Config.getParameter("stockSupplierDir");
            hybrisFile = Config.getParameter("hybrisFile");
            hybrisFileUrl = Config.getParameter("hybrisFileUrl");
            if (!hybrisFileUrl.endsWith(".csv")) {
                if (hybrisFileUrl.endsWith("/")) {
                    hybrisFileUrl += hybrisFile;
                } else {
                    hybrisFileUrl += "/" + hybrisFile;
                }
            }
            username = Config.getParameter("ws.username");
            password = Config.getParameter("ws.password");
            domain = Config.getParameter("ws.domain");
            servicePath = Config.getParameter("ws.service.path");
        } catch (Exception e) {
            LOG.error("Error loading properties", e);
        }
    }


    /**
     * 
     * @return
     */
    public String getAddress() {
        return address;
    }


    /**
     * 
     * @return
     */
    public String getNameSpace() {
        return nameSpace;
    }


    /**
     * 
     * @return
     */
    public String getPrefix() {
        return prefix;
    }


    /**
     * 
     * @return
     */
    public String getUsername() {
        return username;
    }


    /**
     * 
     * @return
     */
    public String getPassword() {
        return password;
    }


    /**
     * 
     * @return
     */
    public static Logger getLog() {
        return LOG;
    }


    /**
     * 
     * @return
     */
    public String getHybrisFile() {
        return hybrisFile;
    }


    /**
     * 
     * @return
     */
    public String getHybrisFileUrl() {
        return hybrisFileUrl;
    }


    /**
     * 
     * @return
     */
    public String getStockSupplierDir() {
        return stockSupplierDir;
    }


    /**
     * 
     * @return
     */
    public String getMatchToVerfuegbar() {
        return matchToVerfuegbar;
    }


    /**
     * 
     * @return
     */
    public String getDefaultStockQty() {
        return defaultStockQty;
    }


    /**
     * 
     * @return
     */
    public String getDomain() {
        return domain;
    }


    /**
     * 
     * @return
     */
    public String getServicePath() {
        return servicePath;
    }
}