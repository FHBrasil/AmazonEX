/**
 * 
 */
package br.hering.core.jobs;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import br.hering.core.model.HeringProductModel;
import br.hering.core.model.HeringStyleVariantProductModel;
import br.hering.core.model.jobs.ControlProductVisibilityJobModel;
import br.hering.core.product.dao.HeringProductApprovalProcessDao;
import br.hering.core.product.dao.impl.SearchResultPage;
import br.hering.core.strategies.HeringApproveProductStrategy;
import br.hering.core.strategies.HeringCheckProductStrategy;

/**
 * @author franthescollymaneira
 *
 */
public class ControlProductVisibilityJobPerformable extends AbstractJobPerformable<ControlProductVisibilityJobModel>
{
	private Logger LOG = Logger.getLogger(ControlProductVisibilityJobPerformable.class);
	
	@Resource(name = "heringProductApprovalProcessDao")
	private HeringProductApprovalProcessDao productApprovalDao;
	
	@Resource(name = "heringCheckProductStrategy")
	private HeringCheckProductStrategy checkStrategy;
	
	@Resource(name = "heringApproveProductStrategy")
	private HeringApproveProductStrategy approveStrategy;
	
	/* (non-Javadoc)
	 * @see de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.cronjob.model.CronJobModel)
	 */
	@Override
	public PerformResult perform(final ControlProductVisibilityJobModel job)
	{
		final Collection<CatalogVersionModel> versions = job.getProductCatalogVersions();
		final int numberOfRows = job.getNumberOfRows();
		
		Assert.notEmpty(versions, "productCatalogVersions is empty");
		Assert.isTrue(numberOfRows > 0, "numberOfRows must be greater than zero");
		String msg = "Starting the job for the parameters: NumberOfRows: " + numberOfRows + " Catalogs: " ;
		for (CatalogVersionModel catalogVersionModel : versions)
		{
			msg += catalogVersionModel;
		}
		LOG.info(msg);

		boolean success = true;
		try
		{
			startDeactivationProcess(versions, numberOfRows);
		}
		catch (Exception e)
		{
			LOG.info("Error during deactivation process: " + e);
			success = false;
		}
		
		try
		{
			startActivationProcess(versions, numberOfRows);
		}
		catch (Exception e)
		{
			LOG.info("Error during activation process: " +  e);
			success = false;
		}

		LOG.info("Job Finished");
		
		final CronJobResult cronJobResult = success ? CronJobResult.SUCCESS : CronJobResult.ERROR;
		return new PerformResult(cronJobResult, CronJobStatus.FINISHED);
	}

	private void startDeactivationProcess(final Collection<CatalogVersionModel> versions, int numberOfRows)
	{
		LOG.info("Executing deactivation for variants...");
		
		final SearchResultPage<HeringStyleVariantProductModel> searchResult = productApprovalDao.findStyleVariantsToDeactivate(versions, numberOfRows);
		
		final Closure check = new Closure() {
			public @Override void execute(Object obj)
			{
				LOG.info("Deactivating : " + obj);
				checkStrategy.check((ProductModel) obj, true);
			}
		};
				
		Collection<HeringStyleVariantProductModel> products = searchResult.nextPage();
		
		do 
		{
			LOG.info("Executing for " + products.size() + " variant products");
			CollectionUtils.forAllDo(products, check);
		}
		while(CollectionUtils.isNotEmpty(products = searchResult.nextPage()));
		
		deactivateBaseProduct(versions, numberOfRows);
	}
	
	private void deactivateBaseProduct(final Collection<CatalogVersionModel> versions, int numberOfRows)
	{
		SearchResultPage<HeringProductModel> searchResult = productApprovalDao.findBaseProductsToDeactivate(versions, numberOfRows);
		
		final Closure check = new Closure() {
			public @Override void execute(Object obj)
			{
				LOG.info("Deactivating : " + obj);
				checkStrategy.check((ProductModel) obj, false);
			}
		};
				
		Collection<HeringProductModel> products = searchResult.nextPage();

		do 
		{
			LOG.info("Executing deactivation for " + products.size() + " base products");
			CollectionUtils.forAllDo(products, check);
		}
		while(CollectionUtils.isNotEmpty(products = searchResult.nextPage()));
	}
	
	private void startActivationProcess(final Collection<CatalogVersionModel> versions, int numberOfRows)
	{
		LOG.info("Executing activation of products...");
		
		final Closure approve = new Closure() {
			public @Override void execute(Object obj)
			{
				LOG.info("Approving : " + obj);
				approveStrategy.approve((ProductModel) obj, true);
			}
		};

		final SearchResultPage<HeringStyleVariantProductModel> searchResult = productApprovalDao.findStyleVariantsToActivate(versions, numberOfRows);
		
		Collection<HeringStyleVariantProductModel> products = searchResult.nextPage();

		do 
		{
			LOG.info("Executing activation for " + products.size() + " variant products");
			CollectionUtils.forAllDo(products, approve);
		}
		while(CollectionUtils.isNotEmpty(products = searchResult.nextPage()));
	}
}