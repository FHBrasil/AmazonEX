/**
 *
 */
package com.fliegersoftware.media.strategies.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Required;

import com.fliegersoftware.media.model.jobs.MediaImportationCsvGeneratorJobModel;
import com.fliegersoftware.media.strategies.FindImageFilesToImportStrategy;


/**
 * @author franthescollymaneira
 *
 */
public class DefaultFindImageFilesToImportStrategy implements FindImageFilesToImportStrategy
{
	private List<String> acceptedFileFormatList;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.fliegersoftware.media.strategies.FindImageFilesToImportStrategy#findImageFilesToImport(com.fliegersoftware
	 * .media.model.jobs.MediaImportationCsvGeneratorJobModel)
	 */
	@Override
	public String[] findImageFilesToImport(final MediaImportationCsvGeneratorJobModel cronjob)
	{
		final Date lastImportationDate = cronjob.getLastImportationDate();

		final File imagesDirPath = new File(cronjob.getImagesDirPath());

		final String[] listImages = imagesDirPath.list(new FilenameFilter()
		{
			@Override
			public boolean accept(final File dir, final String name)
			{
				final String extension = FilenameUtils.getExtension(name);

				final boolean isAcceptedExtesion = getAcceptedFileFormatList().contains(extension);

				if (lastImportationDate == null)
				{
					return isAcceptedExtesion;
				}

				final long lastModified = new File(dir, name).lastModified();

				final boolean hasChanged = new Date(lastModified).after(lastImportationDate);

				return isAcceptedExtesion && hasChanged;
			}
		});

		Arrays.sort(listImages, new Comparator<String>()
		{
			@Override
			public int compare(final String o1, final String o2)
			{
				return o1.compareTo(o2);
			}
		});

		return listImages;
	}

	/**
	 * @return the acceptedFileFormatList
	 */
	public List<String> getAcceptedFileFormatList()
	{
		return acceptedFileFormatList;
	}

	/**
	 * @param acceptedFileFormatList
	 *           the acceptedFileFormatList to set
	 */
	@Required
	public void setAcceptedFileFormatList(final List<String> acceptedFileFormatList)
	{
		this.acceptedFileFormatList = acceptedFileFormatList;
	}
}