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

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

/**
 * @author jfelipe
 *
 */
public class ExportVedesOrdersToCsvJobPerformable extends AbstractJobPerformable {

    private static final Logger LOG = Logger.getLogger(ExportVedesOrdersToCsvJobPerformable.class
            .getName());
    private PixiApiImporter sOrderTagPixiApiImporter;
    private PixiAPIExporter VedeSupplierSOrderLinesCsvPixiAPIExporter;


    /**
     * 
     * 
     */
    @Override
    public PerformResult perform(CronJobModel cronJob) {
        try {
            LOG.info("Initiated.");
            Node sOrderTags = getsOrderTagPixiApiImporter().importXml();
            String filename = null; // PixiapiConstants.PIXI_API_FTP_FILE_VEDE + ".csv";
            File file = getVedeSupplierSOrderLinesCsvPixiAPIExporter().exportData(sOrderTags,
                    filename);
            FtpConnector ftpConnector = new FtpConnector();
            ftpConnector.storeFile(file);
            LOG.info("Finished with status: " + CronJobResult.SUCCESS);
            return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        } catch (MalformedURLException me) {
            LOG.error("URL to send a request to Pixi Webservice API malformed: ", me);
            return new PerformResult(CronJobResult.FAILURE, CronJobStatus.ABORTED);
        } catch (SOAPException se) {
            LOG.error("Error creating SOAPMessage: ", se);
            return new PerformResult(CronJobResult.FAILURE, CronJobStatus.ABORTED);
        }
    }


    /**
     * @return the sOrderTagPixiApiImporter
     */
    public PixiApiImporter getsOrderTagPixiApiImporter() {
        return sOrderTagPixiApiImporter;
    }


    /**
     * @param sOrderTagPixiApiImporter
     *            the sOrderTagPixiApiImporter to set
     */
    public void setsOrderTagPixiApiImporter(PixiApiImporter sOrderTagPixiApiImporter) {
        this.sOrderTagPixiApiImporter = sOrderTagPixiApiImporter;
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
