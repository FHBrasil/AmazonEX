/**
 * 
 */
package com.pixi.api.exporters;

import java.io.File;

import org.w3c.dom.Node;

/**
 * @author jfelipe
 *
 */
public interface PixiAPIExporter {

    /**
     * Exports the given XML to a file.
     * 
     * @param source
     *            The XML to be exported to a file
     * @param targetName
     *            The name of the target file
     * @return
     *         File
     * @author jfelipe
     */
    public File exportData(Node source, String targetName);
}
