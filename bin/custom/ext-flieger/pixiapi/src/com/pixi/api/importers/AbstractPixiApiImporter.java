package com.pixi.api.importers;

import org.apache.log4j.Logger;

import com.pixi.api.AbstractPixiApi;
import com.pixi.api.PixiApiImporter;

/**
 * 
 * 
 * @author jfelipe
 */
public abstract class AbstractPixiApiImporter extends AbstractPixiApi implements PixiApiImporter {

    private static final Logger LOG = Logger.getLogger(AbstractPixiApiImporter.class);
}
