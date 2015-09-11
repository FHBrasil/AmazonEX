/**
 *
 */
package com.fliegersoftware.media.jobs.performable;

import de.hybris.platform.acceleratorservices.dataimport.batch.converter.mapping.ConverterMapping;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.util.CSVWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.fliegersoftware.media.model.jobs.MediaImportationCsvGeneratorJobModel;
import com.fliegersoftware.media.strategies.FindImageFilesToImportStrategy;
import com.fliegersoftware.media.strategies.FindMediaFormatFromImageStrategy;
import com.fliegersoftware.media.strategies.FindProductCodeFromImageStrategy;


/**
 * @author franthescollymaneira
 *
 */
public class MediaImportationCsvGeneratorJobPerformable extends AbstractJobPerformable<MediaImportationCsvGeneratorJobModel>
{
	private final Logger LOG = Logger.getLogger(MediaImportationCsvGeneratorJobPerformable.class);

	private ConverterMapping batchImportationMediaConverterMapping;

	private ConverterMapping batchImportationMediaContainerConverterMapping;

	private FindProductCodeFromImageStrategy findProductCodeFromImageStrategy;

	private FindMediaFormatFromImageStrategy findMediaFormatFromImageStrategy;

	private FindImageFilesToImportStrategy findImageFilesToImportStrategy;

	private String tempDir;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.cronjob.model.CronJobModel
	 * )
	 */
	@Override
	public PerformResult perform(final MediaImportationCsvGeneratorJobModel job)
	{
		try
		{
			LOG.info("Started media CSV generation");

			final Date now = new Date();
			final String[] imageList = getFindImageFilesToImportStrategy().findImageFilesToImport(job);

			if (ArrayUtils.isNotEmpty(imageList))
			{
				final String mediaCSVPath = createMediaCSV(imageList);
				final String containerCSVPath = createMediaContainerCSV(imageList);

				moveFile(mediaCSVPath, job.getHotFolderDirPath());
				moveFile(containerCSVPath, job.getHotFolderDirPath());

				setLastImportationDate(job, now);
			}
		}
		catch (final Exception e)
		{
			LOG.error("error", e);

			return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
		}

		LOG.info("Finished media CSV generation");

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	/**
	 * @param job
	 * @param now
	 */
	private void setLastImportationDate(final MediaImportationCsvGeneratorJobModel job, final Date now)
	{
		LOG.info(String.format("Last importation date set to %s", now));

		job.setLastImportationDate(now);
		modelService.save(job);
	}

	/**
	 *
	 * @param srcFile
	 * @param destinationDir
	 */
	protected void moveFile(final String srcFile, final String destinationDir)
	{
		LOG.info(String.format("Moving file %s to dir %s", srcFile, destinationDir));

		final File file = new File(srcFile);
		final File directory = new File(destinationDir);

		try
		{
			FileUtils.moveFileToDirectory(file, directory, false);
		}
		catch (final IOException e)
		{
			LOG.error("error", e);
		}
	}

	protected String createMediaCSV(final String[] images) throws Exception
	{
		LOG.info("creating Media CSV");

		final List<Map<Integer, String>> lines = new ArrayList<Map<Integer, String>>();
		String format = null;

		for (final String img : images)
		{
			format = getFindMediaFormatFromImageStrategy().findMediaFormatFromImage(img);
			lines.add(createMediaLineMap(img, format));
		}

		final String mediasFileNamePrefix = getBatchImportationMediaConverterMapping().getMapping();
		final String mediasFileName = generateFileName(getTempDir(), mediasFileNamePrefix);

		final CSVWriter mediasCsvWriter = new CSVWriter(mediasFileName, "UTF-8");
		writeCsvContent(mediasCsvWriter, lines);

		return mediasFileName;
	}

	protected String createMediaContainerCSV(final String[] images) throws Exception
	{
		LOG.info("creating Media Container CSV");

		final List<Map<Integer, String>> lines = new ArrayList<Map<Integer, String>>();
		String code = null;

		for (final String img : images)
		{
			code = getFindProductCodeFromImageStrategy().findProductCodeFromImage(img);
			lines.add(createContainerLineMap(code, img));
		}

		final String containerFilePrefix = getBatchImportationMediaContainerConverterMapping().getMapping();
		final String containerFileName = generateFileName(getTempDir(), containerFilePrefix);

		final CSVWriter containerCsvWriter = new CSVWriter(containerFileName, "UTF-8");
		writeCsvContent(containerCsvWriter, lines);

		return containerFileName;
	}

	protected String generateFileName(final String parentFile, final String mediasFilePrefix)
	{
		final long currentTimeMillis = System.currentTimeMillis();
		final String uuid = String.valueOf(currentTimeMillis);

		return parentFile + "/" + mediasFilePrefix + "-" + uuid + ".csv";
	}

	/**
	 * @param img
	 * @param mediaFormat
	 * @return
	 */
	protected Map<Integer, String> createMediaLineMap(final String img, final String mediaFormat)
	{
		final Map mediaLineMap = new HashMap();
		mediaLineMap.put(Integer.valueOf(0), img);
		mediaLineMap.put(Integer.valueOf(1), mediaFormat);

		return mediaLineMap;
	}

	/**
	 *
	 * @param productCode
	 * @param medias
	 * @return
	 */
	protected Map<Integer, String> createContainerLineMap(final String productCode, final String... medias)
	{
		final Map containerLineMap = new HashMap();
		containerLineMap.put(Integer.valueOf(0), productCode);
		containerLineMap.put(Integer.valueOf(1), StringUtils.join(medias, ","));

		return containerLineMap;
	}

	/**
	 * @param mediasWriter
	 * @param lines
	 * @throws IOException
	 */
	protected void writeCsvContent(final CSVWriter mediasWriter, final List<Map<Integer, String>> lines) throws IOException
	{
		mediasWriter.write(lines);
		mediasWriter.closeQuietly();
	}

	/**
	 * @return the batchImportationMediaConverterMapping
	 */
	public ConverterMapping getBatchImportationMediaConverterMapping()
	{
		return batchImportationMediaConverterMapping;
	}

	/**
	 * @param batchImportationMediaConverterMapping
	 *           the batchImportationMediaConverterMapping to set
	 */
	@Required
	public void setBatchImportationMediaConverterMapping(final ConverterMapping batchImportationMediaConverterMapping)
	{
		this.batchImportationMediaConverterMapping = batchImportationMediaConverterMapping;
	}

	/**
	 * @return the batchImportationMediaContainerConverterMapping
	 */
	public ConverterMapping getBatchImportationMediaContainerConverterMapping()
	{
		return batchImportationMediaContainerConverterMapping;
	}

	/**
	 * @param batchImportationMediaContainerConverterMapping
	 *           the batchImportationMediaContainerConverterMapping to set
	 */
	@Required
	public void setBatchImportationMediaContainerConverterMapping(
			final ConverterMapping batchImportationMediaContainerConverterMapping)
	{
		this.batchImportationMediaContainerConverterMapping = batchImportationMediaContainerConverterMapping;
	}

	/**
	 * @return the findProductCodeFromImageStrategy
	 */
	public FindProductCodeFromImageStrategy getFindProductCodeFromImageStrategy()
	{
		return findProductCodeFromImageStrategy;
	}

	/**
	 * @param findProductCodeFromImageStrategy
	 *           the findProductCodeFromImageStrategy to set
	 */
	@Required
	public void setFindProductCodeFromImageStrategy(final FindProductCodeFromImageStrategy findProductCodeFromImageStrategy)
	{
		this.findProductCodeFromImageStrategy = findProductCodeFromImageStrategy;
	}

	/**
	 * @return the findMediaFormatFromImageStrategy
	 */
	public FindMediaFormatFromImageStrategy getFindMediaFormatFromImageStrategy()
	{
		return findMediaFormatFromImageStrategy;
	}

	/**
	 * @param findMediaFormatFromImageStrategy
	 *           the findMediaFormatFromImageStrategy to set
	 */
	@Required
	public void setFindMediaFormatFromImageStrategy(final FindMediaFormatFromImageStrategy findMediaFormatFromImageStrategy)
	{
		this.findMediaFormatFromImageStrategy = findMediaFormatFromImageStrategy;
	}

	/**
	 * @return the findImageFilesToImportStrategy
	 */
	public FindImageFilesToImportStrategy getFindImageFilesToImportStrategy()
	{
		return findImageFilesToImportStrategy;
	}

	/**
	 * @param findImageFilesToImportStrategy
	 *           the findImageFilesToImportStrategy to set
	 */
	@Required
	public void setFindImageFilesToImportStrategy(final FindImageFilesToImportStrategy findImageFilesToImportStrategy)
	{
		this.findImageFilesToImportStrategy = findImageFilesToImportStrategy;
	}

	/**
	 * @return the tempDir
	 */
	public String getTempDir()
	{
		return tempDir;
	}

	/**
	 * @param tempDir
	 *           the tempDir to set
	 */
	@Required
	public void setTempDir(final String tempDir)
	{
		this.tempDir = tempDir;
	}
}