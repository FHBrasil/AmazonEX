package com.pixi.api;

import java.util.List;

import javax.xml.soap.SOAPException;

import com.pixi.api.core.PixiFunctionParameter;
import com.pixi.api.exceptions.SOAPResponseErrorException;

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
    void exportData(List<PixiFunctionParameter> functionParameters)
            throws SOAPResponseErrorException, SOAPException;
}