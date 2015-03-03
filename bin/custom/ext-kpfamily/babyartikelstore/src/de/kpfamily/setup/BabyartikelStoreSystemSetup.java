/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package de.kpfamily.setup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.commerceservices.setup.data.ImportData;
import de.hybris.platform.commerceservices.setup.events.CoreDataImportedEvent;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;
import de.hybris.platform.core.initialization.SystemSetupParameterMethod;
import de.kpfamily.constants.BabyartikelstoreConstants;
import de.kpfamily.services.BabyartikelCoreDataImportService;


@SystemSetup(extension = BabyartikelstoreConstants.EXTENSIONNAME)
public class BabyartikelStoreSystemSetup extends AbstractSystemSetup
{
	public static final String BABYARTIKEL = "babyartikel";

	private static final String IMPORT_CORE_DATA = "importCoreData";
	private static final String ACTIVATE_SOLR_CRON_JOBS = "activateSolrCronJobs";
	private static final String IMPORT_SOLR_CONFIG = "importSolrConfig";

	private BabyartikelCoreDataImportService coreDataImportService;

	@SystemSetupParameterMethod
	@Override
	public List<SystemSetupParameter> getInitializationOptions()
	{
		final List<SystemSetupParameter> params = new ArrayList<SystemSetupParameter>();

		params.add(createBooleanSystemSetupParameter(IMPORT_CORE_DATA, "Import Core Data", true));
		params.add(createBooleanSystemSetupParameter(IMPORT_SOLR_CONFIG, "Import solr server configuration", false));
		params.add(createBooleanSystemSetupParameter(ACTIVATE_SOLR_CRON_JOBS, "Activate Solr Cron Jobs", true));

		return params;
	}
	
	@SystemSetup(type = SystemSetup.Type.PROJECT, process = SystemSetup.Process.ALL)
	public void createProjectData(final SystemSetupContext context)
	{
		final List<ImportData> importData = new ArrayList<ImportData>();

		final ImportData babyartikelImportData = new ImportData();
		babyartikelImportData.setProductCatalogName(BABYARTIKEL);
		babyartikelImportData.setContentCatalogNames(Arrays.asList(BABYARTIKEL));
		babyartikelImportData.setStoreNames(Arrays.asList(BABYARTIKEL));
		importData.add(babyartikelImportData);

		getCoreDataImportService().execute(this, context, importData);
		getEventService().publishEvent(new CoreDataImportedEvent(context, importData));
		
		executeCatalogSyncJob(context, "babyartikelContentCatalog");
	}

	public BabyartikelCoreDataImportService getCoreDataImportService()
	{
		return coreDataImportService;
	}

	@Required
	public void setCoreDataImportService(final BabyartikelCoreDataImportService coreDataImportService)
	{
		this.coreDataImportService = coreDataImportService;
	}
}
