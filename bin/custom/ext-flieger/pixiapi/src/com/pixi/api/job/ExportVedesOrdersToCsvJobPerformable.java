/**
 * 
 */
package com.pixi.api.job;

import java.io.File;
import java.net.MalformedURLException;

import javax.xml.soap.SOAPException;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import com.pixi.api.exporters.PixiAPIExporter;
import com.pixi.api.ftp.FtpConnector;
import com.pixi.api.importers.PixiApiImporter;
import com.pixi.api.model.ExportVedesOrdersToCsvCronJobModel;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

/**
 * @author jfelipe
 *
 */
public class ExportVedesOrdersToCsvJobPerformable extends
        AbstractJobPerformable<ExportVedesOrdersToCsvCronJobModel> {

    private static final Logger LOG = Logger.getLogger(ExportVedesOrdersToCsvJobPerformable.class
            .getName());
    private PixiApiImporter sOrdersPixiApiImporter;
    private PixiAPIExporter VedeSupplierSOrderLinesCsvPixiAPIExporter;


    /**
     * 
     * 
     */
    @Override
    public PerformResult perform(ExportVedesOrdersToCsvCronJobModel cronJob) {
        LOG.info("Initiated.");
        Node sOrderTags = getsOrdersPixiApiImporter().importXml();
        String filename = null; // PixiapiConstants.PIXI_API_FTP_FILE_VEDE + ".csv";
        File file = getVedeSupplierSOrderLinesCsvPixiAPIExporter().exportData(sOrderTags, filename);
        if (file != null) {
            FtpConnector ftpConnector = new FtpConnector();
            ftpConnector.storeFile(file);
            LOG.info("Finished with status: " + CronJobResult.SUCCESS);
            return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        } else {
            return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
        }
    }


    /**
     * @return the sOrderTagPixiApiImporter
     */
    public PixiApiImporter getsOrdersPixiApiImporter() {
        return sOrdersPixiApiImporter;
    }


    /**
     * @param sOrderTagPixiApiImporter
     *            the sOrderTagPixiApiImporter to set
     */
    public void setsOrdersPixiApiImporter(PixiApiImporter sOrderTagPixiApiImporter) {
        this.sOrdersPixiApiImporter = sOrderTagPixiApiImporter;
    }


    /**
     * @return the vedeSupplierSOrderLinesCsvPixiAPIExporter
     */
    public PixiAPIExporter getVedeSupplierSOrderLinesCsvPixiAPIExporter() {
        return VedeSupplierSOrderLinesCsvPixiAPIExporter;
    }


    /**
     * @param vedeSupplierSOrderLinesCsvPixiAPIExporter
     *            the vedeSupplierSOrderLinesCsvPixiAPIExporter to set
     */
    public void setVedeSupplierSOrderLinesCsvPixiAPIExporter(
            PixiAPIExporter vedeSupplierSOrderLinesCsvPixiAPIExporter) {
        VedeSupplierSOrderLinesCsvPixiAPIExporter = vedeSupplierSOrderLinesCsvPixiAPIExporter;
    }
}
