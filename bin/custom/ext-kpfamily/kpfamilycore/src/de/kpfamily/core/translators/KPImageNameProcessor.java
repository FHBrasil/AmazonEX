package de.kpfamily.core.translators;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.impex.jalo.imp.ImpExImportReader;
import de.hybris.platform.impex.jalo.imp.ImportProcessor;
import de.hybris.platform.impex.jalo.imp.ValueLine;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloInvalidParameterException;

/**
 * Renames the given image name.
 * !!! Important: IT DOES NOT IMPORT (OR PROCESS) THE GIVEN LINE !!!
 * 
 * @author jfelipe
 */
public class KPImageNameProcessor implements ImportProcessor {
    
    private static final Logger LOG = Logger.getLogger(KPImageNameProcessor.class);
    //
    // www:
    // private static final String FTP_FOLDER =
    // "/HYBRIS/fliegercommerce/medias/ftp/babyartikel";
    // www1:
    private static final String FTP_FOLDER = "/HYBRIS/fliegercommerce/medias/ftp/babyartikel/";
    // local:
    // private static final String FTP_FOLDER = "/workspace/medias/";
    private static final String REGEX_IGNORE_PATTERN =
            "\\_d[0-9]+|\\_t[0-9]+|\\_m|\\_n|\\_t|\\_detail";
    
    
    /**
     * 
     */
    @Override
    public void init(ImpExImportReader reader) {
        //
    }
    
    
    /**
     * 
     */
    @Override
    public Item processItemData(ValueLine valueLine) throws ImpExException {
        String productCode = valueLine.getValueEntry(1).getCellValue();
        String imageFilePath = valueLine.getValueEntry(2).getCellValue();
        // If the impex file comes with more than one detail image, which
        // will probaly occur, we need to get the first image name, which is
        // the base image.
        imageFilePath = imageFilePath.split(",")[0];
        Pattern pattern = Pattern.compile(REGEX_IGNORE_PATTERN);
        // Only move and rename the file if it's a base image.
        if (!pattern.matcher(imageFilePath).matches()) {
            renameImageFile(imageFilePath, productCode);
        }
        return null;
    }
    
    
    /**
     * Renames the given file name to a new name, according to the new image
     * file pattern:
     * productcode_image_name.jpg
     * 
     * @param imageFilePath
     *            the image name that already exists.
     * @param productCode
     *            the product that owns the image (- file name).
     * @return
     *         true if succeded changing the image file name. false
     *         otherwise.
     * @throws JaloInvalidParameterException
     */
    public boolean renameImageFile(String imageFilePath, String productCode)
            throws JaloInvalidParameterException {
        File sourceFile = new File(imageFilePath);
        String fileName = imageFilePath.split("/")[imageFilePath.split("/").length - 1];
        String newFilePath = productCode + "_" + fileName;
        File destinationFile = new File(FTP_FOLDER + newFilePath);
        try {
            FileUtils.moveFile(sourceFile, destinationFile);
            LOG.info("Renamed Image File:" + destinationFile.getAbsolutePath());
            return true;
        } catch (FileExistsException fee) {
            LOG.error(fee);
        } catch (FileNotFoundException fnfe) {
            LOG.error(fnfe);
        } catch (IOException ioe) {
            LOG.error(ioe);
        }
        return false;
    }
}
