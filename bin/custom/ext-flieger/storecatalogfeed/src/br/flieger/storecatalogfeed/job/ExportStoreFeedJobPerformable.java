/**
 * 
 */
package br.flieger.storecatalogfeed.job;

import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;

import br.flieger.storecatalogfeed.core.FullFeedGeneratorThread;
import br.flieger.storecatalogfeed.model.job.ExportStoreFeedJobModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.util.Config;

/**
 * @author franthescollymaneira
 *
 */
public abstract class ExportStoreFeedJobPerformable extends AbstractJobPerformable {

    private static final Logger LOG = Logger.getLogger(ExportStoreFeedJobPerformable.class
            .getName());


    /*
     * (non-Javadoc)
     * 
     * @see
     * de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris
     * .platform.cronjob.model.CronJobModel)
     */
    @Override
    public PerformResult perform(CronJobModel cronjob) {
        if (!(cronjob instanceof ExportStoreFeedJobModel)) {
            throw new InvalidParameterException(cronjob.getCode() + " is not valid, class: "
                    + cronjob.getClass().getSimpleName());
        }
        LOG.info("Started:" + new Date());
        Collection<String> templates = Config.getParametersByPattern("export.template.code")
                .values();
        if (!templates.isEmpty()) {
            final CatalogVersionModel cv = getCatalogVersion(cronjob);
            for (final String template : templates) {
                try {
                    FullFeedGeneratorThread handlerThread = createFullFeedGeneratorThread(cv,
                            template);
                    handlerThread.start();
                    handlerThread.join();
                } catch (InterruptedException e) {
                    LOG.error("Error job:" + template, e);
                }
            }
        }
        LOG.info("Finished:" + new Date());
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }


    private CatalogVersionModel getCatalogVersion(CronJobModel cronjob) {
        if (cronjob instanceof ExportStoreFeedJobModel) {
            return ((ExportStoreFeedJobModel) cronjob).getCatalogVersion();
        }
        return null;
    }


    /**
     * 
     * @param cv
     * @param template
     * @return
     */
    private FullFeedGeneratorThread createFullFeedGeneratorThread(final CatalogVersionModel cv,
            final String template) {
        final FullFeedGeneratorThread handlerThread = createFullFeedGeneratorThread();
        handlerThread.setCatalogVersion(cv);
        handlerThread.setXmlTemplate(template);
        return handlerThread;
    }


    // look-up method
    protected abstract FullFeedGeneratorThread createFullFeedGeneratorThread();
}