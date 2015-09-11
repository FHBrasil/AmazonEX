package com.hybris.addon.common.setup.impl;

import org.apache.commons.lang.StringUtils;

import com.hybris.addon.impex.ImpexMacroParameterData;

import de.hybris.platform.acceleratorservices.enums.UiExperienceLevel;
import de.hybris.platform.addonsupport.setup.impl.DefaultAddOnConfigDataImportService;

public class AddOnCommonConfigDataImportService extends DefaultAddOnConfigDataImportService {

	@Override
	protected boolean executeCMSImport(final String extensionName, final ImpexMacroParameterData macroParameters)
	{
		final String path = "/" + extensionName + "/import/contentCatalogs/template/";
		boolean imported = false;
		imported |= getSetupImpexService().importImpexFile(path + "catalog.impex", macroParameters, false, false);
		
		for (UiExperienceLevel level : macroParameters.getSupportedUiExperienceLevels())
		{
			String suffix = StringUtils.EMPTY;
			if (!level.equals(UiExperienceLevel.DESKTOP))
			{
				suffix = "-" + StringUtils.lowerCase(level.getCode());
			}
			
			imported |= getSetupImpexService().importImpexFile(path + "cms-content" + suffix + ".impex", macroParameters, false, false);
		}
		
		imported |= getSetupImpexService().importImpexFile(path + "email-content.impex", macroParameters, false, false);
		
		return imported;
	}
}
