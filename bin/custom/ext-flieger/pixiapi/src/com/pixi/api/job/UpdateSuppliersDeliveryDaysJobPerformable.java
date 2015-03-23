/**
 * 
 */
package com.pixi.api.job;

import java.util.ArrayList;
import java.util.List;

import com.pixi.api.model.UpdateSupplierDeliveryDaysCronJobModel;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.kpfamily.core.model.ProductSupplierModel;

/**
 * @author jfelipe
 *
 */
public class UpdateSuppliersDeliveryDaysJobPerformable extends
        AbstractJobPerformable<UpdateSupplierDeliveryDaysCronJobModel> {

    /**
     * 
     */
    @Override
    public PerformResult perform(UpdateSupplierDeliveryDaysCronJobModel cronjob) {
        // TODO: buscar todos os suppliers
//        List<ProductSupplierModel> productSuppliers = new ArrayList<ProductSupplierModel>();
//        productSuppliers = 
        // TODO: request para a pixi api para buscar o delivery days
        // TODO: atualizar o delivery days do supplier (productsupplier)
        return new PerformResult(CronJobResult.FAILURE, CronJobStatus.ABORTED);
    }
}
