/**
 * 
 */
package br.flieger.storecatalogfeed.core;

import static br.flieger.storecatalogfeed.constants.StorecatalogfeedConstants.FILE_PATH;
import static br.flieger.storecatalogfeed.constants.StorecatalogfeedConstants.FILE_PREFIX;
import static br.flieger.storecatalogfeed.constants.StorecatalogfeedConstants.PRODUCTS_LIMIT;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.Set;
import java.util.concurrent.CyclicBarrier;

import org.jdom.Document;

import br.flieger.storecatalogfeed.utils.XMLUtils;


/**
 * This thread creates a partial export XML file, finding the products using the search page index.
 * @author franthescolly
 *
 */
public class PartialFeedGeneratorThread extends AbstractFeedThread 
{
	private CyclicBarrier barrier;

	private int threadNumber;

	private boolean finished;
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			super.run();
			
			buildCatalogXML();
			barrier.await();
		} catch (Exception e) {
			LOG.error("Error", e);
		}
	}
	
	private void buildCatalogXML() 
	{
		try 
		{
			Set<ProductModel> result = getStoreFeedDao().findByPaging(getCatalogVersion(), getXmlTemplate(), getStartIndex(), PRODUCTS_LIMIT);

			Document document = createTemplate(getXmlTemplate()).apply(result);

			new XMLUtils().writeXMLFile(document, getPartialFeedFileName());
		} finally {
			finished = true;
		}
	}
	
	private String getPartialFeedFileName() 
	{
		String pathname = new StringBuilder()
		.append(getPathByStore())
		.append(getXmlTemplate()).append("/")
		.append(FILE_PREFIX)
		.append("_")
		.append(threadNumber)
		.append(".xml").toString();
		
		return pathname;
	}

	private int getStartIndex() 
	{
		int start = (threadNumber * PRODUCTS_LIMIT) - PRODUCTS_LIMIT;

		return start;
	}

	public final boolean isFinished() 
	{
		return finished;
	}

	/**
	 * @param threadNumber the threadNumber to set
	 */
	public void setThreadNumber(int threadNumber)
	{
		this.threadNumber = threadNumber;
	}

	/**
	 * @param barrier the barrier to set
	 */
	public void setBarrier(CyclicBarrier barrier)
	{
		this.barrier = barrier;
	}
}