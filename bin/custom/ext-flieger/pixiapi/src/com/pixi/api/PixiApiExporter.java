package com.pixi.api;

import java.util.List;

import javax.xml.soap.SOAPException;

import com.pixi.api.core.PixiFunctionParameter;

/**
 * @author jfelipe
 *
 */
public interface PixiApiExporter {

    /**
     * 
     * @param functionParameters
     *
     * @author jfelipe
     */
    void exportData(List<PixiFunctionParameter> functionParameters) throws SOAPException;
}