/*
 * 
 */
package com.pixi.api.core;

/**
 * 
 *
 */
public enum PixiParameter {
    
    LOCATION_ID         ("LocationId")
  , SHOW_ORDER          ("ShowOrd")
  , OPEN_ORDER_LINES    ("OpenOrderlines")
  , S_ORDER_REFERENCE   ("SOrderRef")
  , SUPPLIER_NUMBER     ("SupplNr")
  ;

    private final String value;
    
    private PixiParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}