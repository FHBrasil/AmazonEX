package de.kpfamily.services;

import java.util.List;

import org.apache.log4j.Logger;

import de.hybris.platform.acceleratorservices.dataimport.impl.CoreDataImportService;
import de.hybris.platform.acceleratorservices.setup.AbstractSystemSetup;
import de.hybris.platform.acceleratorservices.setup.data.ImportData;
import de.hybris.platform.core.initialization.SystemSetup.Process;
import de.hybris.platform.core.initialization.SystemSetupContext;

public class BabyartikelCoreDataImportService extends CoreDataImportService 
{
	private Logger LOG = Logger.getLogger(BabyartikelCoreDataImportService.class);
	
	private AbstractSystemSetup systemSetup;

	private SystemSetupContext systemSetupContext;
	
	@Override
	public void execute(AbstractSystemSetup systemSetup, SystemSetupContext context, List<ImportData> importData) 
	{
		this.systemSetup = systemSetup;
		this.systemSetupContext = context;
		
		super.execute(systemSetup, context, importData);
	}
	
	@Override
	protected void importSolrIndex(String extensionName, String storeName) 
	{
		LOG.info("process: " + getSystemSetupContext().getProcess());
		boolean isInitializeProcess = getSystemSetupContext().getProcess().equals(Process.INIT);
		
		boolean importCoreData = getSystemSetup().getBooleanSystemSetupParameter(getSystemSetupContext(), "importSolrConfig");

		if(isInitializeProcess || importCoreData)
		{
			LOG.info("Importing solr-config.impex");
			
			getSetupImpexService().importImpexFile(
					String.format("/%s/import/coredata/stores/%s/solr-config.impex",
							new Object[] { extensionName, storeName }), false);	
		}
		
		super.importSolrIndex(extensionName, storeName);
	}

	/**
	 * @return the systemSetupContext
	 */
	public SystemSetupContext getSystemSetupContext() 
	{
		return systemSetupContext;
	}

	/**
	 * @param systemSetupContext the systemSetupContext to set
	 */
	public void setSystemSetupContext(SystemSetupContext systemSetupContext) 
	{
		this.systemSetupContext = systemSetupContext;
	}

	/**
	 * @return the systemSetup
	 */
	public AbstractSystemSetup getSystemSetup() 
	{
		return systemSetup;
	}

	/**
	 * @param systemSetup the systemSetup to set
	 */
	public void setSystemSetup(AbstractSystemSetup systemSetup) 
	{
		this.systemSetup = systemSetup;
	}
}