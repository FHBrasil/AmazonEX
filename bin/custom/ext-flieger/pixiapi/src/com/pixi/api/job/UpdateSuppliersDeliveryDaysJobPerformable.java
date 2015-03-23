/**
 * 
 */
package com.pixi.api.job;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.pixi.api.importers.PixiApiImporter;
import com.pixi.api.model.UpdateSupplierDeliveryDaysCronJobModel;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.kpfamily.core.model.ProductSupplierModel;
import de.kpfamily.core.service.ProductSupplierService;

/**
 * @author jfelipe
 *
 */
public class UpdateSuppliersDeliveryDaysJobPerformable extends
        AbstractJobPerformable<UpdateSupplierDeliveryDaysCronJobModel> {

    private static final Logger LOG = Logger
            .getLogger(UpdateSuppliersDeliveryDaysJobPerformable.class.getName());
    @Resource
    ProductSupplierService defaultProductSupplierService;
    @Resource
    PixiApiImporter supplierDeliveryDaysPixiApiImporter;
    @Resource
    ModelService defaultModelService;


    /**
     * 
     */
    @Override
    public PerformResult perform(UpdateSupplierDeliveryDaysCronJobModel cronjob) {
        List<ProductSupplierModel> productSuppliers = new ArrayList<ProductSupplierModel>();
        productSuppliers = defaultProductSupplierService.findAll();
        for (ProductSupplierModel productSupplier : productSuppliers) {
            int deliveryDays = supplierDeliveryDaysPixiApiImporter.importInteger(productSupplier
                    .getCode());
            if (deliveryDays > 0) {
                try {
                    // FIXME: update the productSupplier delivery Days
                    // productSupplier.setDropShippingDeliveryDays(deliveryDays);
                    // defaultModelService.save(productSupplier);
                    LOG.info("ProductSupplier updated. DeliveryDays set to: "
                            + productSupplier.getDropShippingDeliveryDays());
                } catch (ModelSavingException me) {
                    LOG.error("Error saving ProductSupplierModel. " + productSupplier.getCode(), me);
                }
            }
        }
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }
}
