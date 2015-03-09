/**
 * 
 */
package br.flieger.storecatalogfeed.core;

import static br.flieger.storecatalogfeed.constants.StorecatalogfeedConstants.PRODUCTS_LIMIT;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

import br.flieger.storecatalogfeed.utils.XMLUtils;

/**
 * Creates all threads to generate the export XML files
 * 
 * @author franthescolly
 *
 */
public abstract class FullFeedGeneratorThread extends AbstractFeedThread {

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        super.run();
        // Cleaning the export path to avoid trash archives.
        new XMLUtils().clearDirectory(getExportFilePath());
        // getting the product count to calculate the necessary number of threads.
        int counter = getStoreFeedDao().getCounter(getCatalogVersion(), getXmlTemplate());
        // calculating the necessary number of threads to manipulate the products.
        int totalThreads = calculateTotalThreads(counter);
        LOG.info("Exporting " + counter + " products to " + getXmlTemplate());
        // launching all threads with the specified barrier to synchronize the call back
        // code
        // that must be executed when all threads are finished.
        //
        CyclicBarrier barrier = getBarrier(totalThreads + 1); // total exporter threads
                                                              // plus current thread
        List<PartialFeedGeneratorThread> threads = new ArrayList<PartialFeedGeneratorThread>();
        // this variable decreases accordingly to the created threads, the initial value
        // is the total amount of needed threads
        threadGroup: while (totalThreads > 0) {
            // while there's a need for more threads, and there are less then 6 threads
            // already running, then we create/start more
            while ((threads.size() < 6 && totalThreads > 0)) {
                PartialFeedGeneratorThread thread = createPartialFeedGeneratorThread(barrier,
                        (totalThreads--));
                threads.add(thread);
                thread.start();
            }
            // created all necessary threads?
            if (totalThreads <= 0) {
                break threadGroup;
            }
            // remove finished threads from the list, so we are able to create/start more
            // threads
            for (PartialFeedGeneratorThread thread : new ArrayList<PartialFeedGeneratorThread>(
                    threads)) {
                if (thread.isFinished()) {
                    threads.remove(thread);
                }
            }
        }
        threads = null;
        try {
            // Just finish execution when the catalog is exported.
            barrier.await();
        } catch (Exception e) {
            LOG.error(e);
        }
    }


    private CyclicBarrier getBarrier(final int totalThreads) {
        FeedAggregatorThread collector = createFeedAggregatorThread(getXmlTemplate());
        return new CyclicBarrier(totalThreads, collector);
    }


    /**
     * Calculates the necessary amount of threads to handle this export
     * 
     * @param totalProducts
     *            The counter of all products that will be exported
     * @return The number of necessary threads.
     */
    private int calculateTotalThreads(final int totalProducts) {
        if (totalProducts <= PRODUCTS_LIMIT) {
            return 1;
        }
        double result = (double) totalProducts / (double) PRODUCTS_LIMIT;
        return (int) Math.ceil(result);
    }


    /**
     * @param template
     * @return
     */
    protected FeedAggregatorThread createFeedAggregatorThread(final String template) {
        final FeedAggregatorThread collector = createFeedAggregatorThread();
        collector.setXmlTemplate(template);
        collector.setCatalogVersion(getCatalogVersion());
        return collector;
    }


    /**
     * 
     * @param barrier
     * @param threadNr
     * @return
     */
    protected PartialFeedGeneratorThread createPartialFeedGeneratorThread(CyclicBarrier barrier,
            int threadNr) {
        final PartialFeedGeneratorThread partialFeed = createPartialFeedGeneratorThread();
        partialFeed.setCatalogVersion(getCatalogVersion());
        partialFeed.setXmlTemplate(getXmlTemplate());
        partialFeed.setBarrier(barrier);
        partialFeed.setThreadNumber(threadNr);
        return partialFeed;
    }


    protected abstract FeedAggregatorThread createFeedAggregatorThread();


    protected abstract PartialFeedGeneratorThread createPartialFeedGeneratorThread();
}