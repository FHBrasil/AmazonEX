/**
 * 
 */
package com.pixi.api.job;

import org.apache.log4j.Logger;

import com.pixi.api.impl.PixiAPIOrder;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;


/**
 * @author jfelipe
 *
 */
public class PixiGetOrdersJobPerformable extends AbstractJobPerformable {
    
    private static final Logger LOG = Logger.getLogger(PixiGetOrdersJobPerformable.class.getName());


    /**
     * 
     * 
     */
    @Override
    public PerformResult perform(CronJobModel cronJob) {
        LOG.info("Pixi Get Orders Job");
        new PixiAPIOrder().getOrders();
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }
}
