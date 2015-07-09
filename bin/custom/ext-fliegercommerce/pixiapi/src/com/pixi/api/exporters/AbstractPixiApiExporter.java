package com.pixi.api.exporters;

import org.apache.log4j.Logger;

import com.pixi.api.AbstractPixiApi;
import com.pixi.api.PixiApiExporter;

/**
 * @author jfelipe
 *
 */
public abstract class AbstractPixiApiExporter extends AbstractPixiApi implements PixiApiExporter {

    private static final Logger LOG = Logger.getLogger(AbstractPixiApiExporter.class);
}
