/**
 *
 */
package com.fliegersoftware.media.strategies;

import com.fliegersoftware.media.model.jobs.MediaImportationCsvGeneratorJobModel;


/**
 * @author franthescollymaneira
 *
 */
public interface FindImageFilesToImportStrategy
{
	String[] findImageFilesToImport(final MediaImportationCsvGeneratorJobModel cronjob);
}