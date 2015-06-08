package de.kpfamily.core.translators;

import java.io.File;

import org.apache.log4j.Logger;

import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.impex.jalo.header.HeaderDescriptor;
import de.hybris.platform.impex.jalo.imp.DefaultImportProcessor;
import de.hybris.platform.impex.jalo.imp.ExistingItemResolver;
import de.hybris.platform.impex.jalo.imp.ImpExImportReader;
import de.hybris.platform.impex.jalo.imp.ImportProcessor;
import de.hybris.platform.impex.jalo.imp.ValueLine;
import de.hybris.platform.impex.jalo.imp.ValueLineTranslator;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.c2l.Language;

/**
 * Renames the given image name.
 * !!! Important: IT DOES NOT IMPORT (OR PROCESS) THE GIVEN LINE !!!
 * 
 * @author jfelipe
 */
public class KPImageNameProcessor implements ImportProcessor {
    
    private static final Logger LOG = Logger.getLogger(KPImageNameProcessor.class);
    //
    private static final String PREFIX_FILE = File.pathSeparator + "HYBRIS"
            + File.pathSeparator
            + "fliegercommerce"
            + File.pathSeparator
            + "medias"
            + File.pathSeparator
            + "ftp"
            + File.pathSeparator
            + "babyartikel";
    private ImpExImportReader reader = null;
    private Language defaultLanguage = null;
    private ValueLineTranslator valueLineTrans;
    private HeaderDescriptor existingItemResolverHeader;
    private ExistingItemResolver existingItemResolver;
    
    
    /**
     * 
     */
    @Override
    public void init(ImpExImportReader reader) {
        this.reader = reader;
        this.defaultLanguage = JaloSession.getCurrentSession().getSessionContext().getLanguage();
    }
    
    
    /**
     * 
     */
    @Override
    public Item processItemData(ValueLine valueLine) throws ImpExException {
        LOG.info(valueLine.getValueEntry(0));
        return null;
    }
    
    
    /**
     * Translates a given input parameter to its equivalent on target
     * system.
     * 
     * @param input
     *            - country from source system to be translated
     * @param item
     *            - not used
     * @return country object equivalent to given input parameter
     * @author jfelipe
     */
    public boolean renameImageFile(String imageFileName, String productCode)
            throws JaloInvalidParameterException {
        File realImageFile = new File(PREFIX_FILE + File.pathSeparator + imageFileName);
        String newFileName = productCode + "_" + imageFileName;
        File renamedImageFile = new File(newFileName);
        boolean foi = realImageFile.renameTo(renamedImageFile);
        return foi;
    }
}
