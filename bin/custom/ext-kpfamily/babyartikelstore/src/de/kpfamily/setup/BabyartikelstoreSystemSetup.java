package de.kpfamily.setup;

import de.hybris.platform.acceleratorservices.dataimport.impl.CoreDataImportService;
import de.hybris.platform.acceleratorservices.dataimport.impl.SampleDataImportService;
import de.hybris.platform.acceleratorservices.setup.AbstractSystemSetup;
import de.hybris.platform.acceleratorservices.setup.data.ImportData;
import de.hybris.platform.acceleratorservices.setup.events.CoreDataImportedEvent;
import de.hybris.platform.acceleratorservices.setup.events.SampleDataImportedEvent;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;
import de.hybris.platform.core.initialization.SystemSetupParameterMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import de.kpfamily.constants.BabyartikelstoreConstants;


@SystemSetup(extension = BabyartikelstoreConstants.EXTENSIONNAME)
public class BabyartikelstoreSystemSetup extends AbstractSystemSetup
{
	public static final String BABYARTIKEL = "babyartikel";

	public static final String IMPORT_CORE_DATA = "importCoreData";
	public static final String IMPORT_SAMPLE_DATA = "importSampleData";
	public static final String ACTIVATE_SOLR_CRON_JOBS = "activateSolrCronJobs";

	private CoreDataImportService coreDataImportService;

	private SampleDataImportService sampleDataImportService;

	@Override
	@SystemSetupParameterMethod
	public List<SystemSetupParameter> getInitializationOptions()
	{
		final List<SystemSetupParameter> params = new ArrayList<SystemSetupParameter>();

		params.add(createBooleanSystemSetupParameter(IMPORT_CORE_DATA, "Import Core Data", true));
		params.add(createBooleanSystemSetupParameter(IMPORT_SAMPLE_DATA, "Import Sample Data", true));
		params.add(createBooleanSystemSetupParameter(ACTIVATE_SOLR_CRON_JOBS, "Activate Solr Cron Jobs", true));

		return params;
	}

	/**
	 * This method will be called during the system initialization.
	 *
	 * @param context
	 *           the context provides the selected parameters and values
	 */
	@SystemSetup(type = SystemSetup.Type.PROJECT, process = SystemSetup.Process.UPDATE)
	public void createProjectData(final SystemSetupContext context)
	{
		final List<ImportData> importDataList = new ArrayList<ImportData>();

		final ImportData importData = new ImportData();
		importData.setProductCatalogName(BABYARTIKEL);
		importData.setContentCatalogNames(Arrays.asList(BABYARTIKEL));
		importData.setStoreNames(Arrays.asList(BABYARTIKEL));
		importDataList.add(importData);

		getCoreDataImportService().execute(this, context, importDataList);
		getEventService().publishEvent(new CoreDataImportedEvent(context, importDataList));

		getSampleDataImportService().execute(this, context, importDataList);
		getEventService().publishEvent(new SampleDataImportedEvent(context, importDataList));
	}

	/**
	 * @return the coreDataImportService
	 */
	public CoreDataImportService getCoreDataImportService()
	{
		return coreDataImportService;
	}

	/**
	 * @param coreDataImportService
	 *           the coreDataImportService to set
	 */
	@Required
	public void setCoreDataImportService(final CoreDataImportService coreDataImportService)
	{
		this.coreDataImportService = coreDataImportService;
	}

	/**
	 * @return the sampleDataImportService
	 */
	public SampleDataImportService getSampleDataImportService()
	{
		return sampleDataImportService;
	}

	/**
	 * @param sampleDataImportService
	 *           the sampleDataImportService to set
	 */
	@Required
	public void setSampleDataImportService(final SampleDataImportService sampleDataImportService)
	{
		this.sampleDataImportService = sampleDataImportService;
	}
}