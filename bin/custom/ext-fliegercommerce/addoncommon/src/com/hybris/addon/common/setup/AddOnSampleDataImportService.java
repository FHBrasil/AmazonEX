/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 * 
 */
package com.hybris.addon.common.setup;

import java.util.List;

import de.hybris.platform.acceleratorservices.setup.data.ImportData;
import de.hybris.platform.core.initialization.SystemSetupContext;

public interface AddOnSampleDataImportService {
	void importSampleData(final String extensionName, final SystemSetupContext context, final List<ImportData> importData, boolean solrReindex);
}
