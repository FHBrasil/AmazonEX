package com.pixi.api;

import java.util.Collection;
import java.util.List;

import com.pixi.api.core.PixiFunctionParameter;
import com.pixi.api.result.PixiApiResult;

/**
 * @author jfelipe
 */
public interface PixiApiImporter {
    
    /**
     * @param functionParameters
     * @author jfelipe
     */
    Collection<? extends PixiApiResult> importData(List<PixiFunctionParameter> functionParameters);
}