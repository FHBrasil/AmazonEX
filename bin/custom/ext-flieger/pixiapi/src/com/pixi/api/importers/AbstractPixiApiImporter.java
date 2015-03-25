package com.pixi.api.importers;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

import org.w3c.dom.Node;

import com.pixi.api.PixiSOAPAPI;

/**
 * @author jfelipe
 *
 */
public abstract class AbstractPixiApiImporter implements PixiApiImporter {

    private PixiSOAPAPI defaultPixiSoapApi;


    /**
     * @return the defaultPixiSoapApi
     */
    public PixiSOAPAPI getDefaultPixiSoapApi() {
        return defaultPixiSoapApi;
    }


    /**
     * @param defaultPixiSoapApi
     *            the defaultPixiSoapApi to set
     */
    public void setDefaultPixiSoapApi(PixiSOAPAPI defaultPixiSoapApi) {
        this.defaultPixiSoapApi = defaultPixiSoapApi;
    }


    /**
     * 
     */
    @Override
    public Node importXml(String... values) {
        return null;
    }


    /**
     * 
     */
    @Override
    public int importInteger(String value) {
        return 0;
    }


    /**
     * 
     */
    @Override
    public List<SimpleEntry<String, String>> importListKeyValuePair(Object parameter) {
        return null;
    }
}
