/*
 * 
 */
package com.pixi.api.core;

/**
 * 
 *
 */
public class PixiParameter {
    
    public static final String LOCATION_ID = "LocationId";
    public static final String SHOW_ORDER = "ShowOrd";

    private final String name;
    private final Object value;

    /**
     * 
     * @param name
     * @param value
     */
    public PixiParameter(String name, Object value) {
        super();
        this.name = name;
        this.value = value;
    }

    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    
    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

}