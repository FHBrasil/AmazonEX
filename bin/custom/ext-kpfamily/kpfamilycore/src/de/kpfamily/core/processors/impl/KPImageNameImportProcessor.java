package de.kpfamily.core.processors.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.impex.jalo.imp.ImpExImportReader;
import de.hybris.platform.impex.jalo.imp.MultiThreadedImportProcessor;
import de.hybris.platform.impex.jalo.imp.ValueLine;
import de.hybris.platform.jalo.Item;

/**
 * Renames the given image name.
 * !!! Important: IT DOES NOT IMPORT (OR PROCESS) THE GIVEN IMPEX FILE !!!
 * The impex file to use with this class:
 * First we need to export, in hybris 4, the Products with the detail
 * picture which is not
 * in the correct naming pattern:
 * 
 * <pre>
 * #-----------------------------------------------------------
 * $version=Staged
 * $catalog=babyartikel
 * "#% impex.setTargetFile(""Product.csv"");"
 * INSERT_UPDATE Product;code[unique=true];detail(url);
 * "#% impex.exportItems(""SELECT {p:pk} FROM {Product AS p JOIN CatalogVersion AS cv ON {cv:pk} = {p:catalogVersion} AND {cv:version} = '$version' JOIN Catalog AS c ON {c:pk} = {cv:catalog} AND {c:id} = '$catalog' }"", Collections.EMPTY_MAP, Collections.singletonList(Item.class), true, true, -1, -1);"
 * #-----------------------------------------------------------
 * </pre>
 * 
 * And then reimport in hybris 5:
 * 
 * <pre>
 * #-----------------------------------------------------------
 * INSERT_UPDATE Product[processor=de.kpfamily.core.processors.impl.KPImageNameImportProcessor];code[unique=true];detail(url);
 * "#% impex.includeExternalDataMedia(""Product.csv"", ""UTF-8"", ';', 1, -1);"
 * #-----------------------------------------------------------
 * </pre>
 * 
 * @author jfelipe
 */
public class KPImageNameImportProcessor extends MultiThreadedImportProcessor {
    
    private static final Logger LOG = Logger.getLogger(KPImageNameImportProcessor.class);
    //
    // production / acceptance
    private static final String DESTINATION_FOLDER =
            "/HYBRIS/fliegercommerce/medias/ftp/babyartikel/";
    // local:
    // private static final String DESTINATION_FOLDER = "/workspace/medias/ftp/babyartikel/";
    // private static final String REGEX_IGNORE_PATTERN =
    // "\\_(d[0-9]+|t[0-9]+|m|n|t|detail)\\.jpg";
    private ImpExImportReader reader = null;
    
    
    /**
     * 
     */
    @Override
    public void init(ImpExImportReader reader) {
        this.reader = reader;
    }
    
    
    /**
     * 
     */
    @Override
    public Item processItemData(ValueLine valueLine) throws ImpExException {
        if (reader.isSecondPass()) {
            LOG.info("Second pass, ignoring items...");
            reader.discardNextLine();
            return null;
        }
        String productCode = valueLine.getValueEntry(1).getCellValue();
        String imageFilePath = valueLine.getValueEntry(2).getCellValue();
        String[] sourceFiles = imageFilePath.split(",");
        for (int i = 0; i < sourceFiles.length; i++) {
            String filename = sourceFiles[i];
            File sourceFile = new File(filename);
            File destinationFile = renameImageName(sourceFile, productCode);
            moveFile(sourceFile, destinationFile);
        }
        return null;
    }
    
    
    /**
     * Renames the given file name to a new name, if necessary, according to
     * the new image file pattern: productcode_image_name.jpg
     * 
     * @param sourceFilePath
     *            the image name that already exists.
     * @param productCode
     *            the product that owns the image (- file name).
     * @return
     *         true if succeeded changing the image file name. false
     *         otherwise.
     */
    public File renameImageName(File sourceFile, String productCode) {
        String fileAbsolutePath = sourceFile.getAbsolutePath();
        String sourceFilename = fileAbsolutePath.split("/")[fileAbsolutePath.split("/").length - 1];
        String productCodeInFileName = sourceFilename.split("_")[0];
        // If the image file name does not have a product code, renames the
        // file to "productcode_name.jpg". Otherwise it just modify the
        // image folder.
        String destinationFilename = (productCode.equals(productCodeInFileName) ? ""
                : productCode + "_") + sourceFilename;
        File destinationFile = new File(DESTINATION_FOLDER + destinationFilename);
        return destinationFile;
    }
    
    
    /**
     * Move a file from source to destination.
     * 
     * @param source
     *            file to be moved
     * @param destination
     *            the destination file absolute path
     * @return true if succeeded, false otherwise
     */
    private boolean moveFile(File source, File destination) {
        try {
            FileUtils.moveFile(source, destination);
            LOG.info("Renamed image file:" + destination.getAbsolutePath());
            return true;
        } catch (FileExistsException fee) {
            // Nothing to do, just log the file name.
            LOG.error("Image file NOT renamed:" + source.getAbsolutePath(), fee);
        } catch (FileNotFoundException fnfe) {
            // Nothing to do, just log the file name.
            LOG.error("Image file NOT renamed:" + source.getAbsolutePath(), fnfe);
        } catch (IOException ioe) {
            // Maybe in this case we need to review source and destination
            // folders permissions on server.
            LOG.error("Image file NOT renamed:" + source.getAbsolutePath(), ioe);
        }
        return false;
    }
    
    
    public ImpExImportReader getReader() {
        return this.reader;
    }
}
