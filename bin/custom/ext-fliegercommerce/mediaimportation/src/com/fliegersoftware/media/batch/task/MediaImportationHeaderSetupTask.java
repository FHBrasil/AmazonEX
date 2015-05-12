/**
 *
 */
package com.fliegersoftware.media.batch.task;

import java.io.File;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.fliegersoftware.media.batch.MediaImportationBatchHeader;


/**
 * @author franthescollymaneira
 *
 */
public class MediaImportationHeaderSetupTask
{
	private String productCatalogVersion;

	private String contentCatalogVersion;

	private String mediaResource;

	private String conversionGroup;

	private String storeBaseDirectory;

	private String mime;

	private String folder;

	/**
	 * Initially creates the header.
	 *
	 * @param file
	 * @return the header
	 */
	public MediaImportationBatchHeader execute(final File file)
	{
		Assert.notNull(file);

		final MediaImportationBatchHeader header = new MediaImportationBatchHeader();
		header.setFile(file);
		header.setProductCatalogVersion(productCatalogVersion);
		header.setContentCatalogVersion(contentCatalogVersion);
		header.setMediaResource(mediaResource);
		header.setConversionGroup(conversionGroup);
		header.setStoreBaseDirectory(storeBaseDirectory);
		header.setMime(mime);
		header.setFolder(folder);

		return header;
	}

	/**
	 * @return the productCatalogVersion
	 */
	public String getProductCatalogVersion()
	{
		return productCatalogVersion;
	}

	/**
	 * @param productCatalogVersion
	 *           the productCatalogVersion to set
	 */
	@Required
	public void setProductCatalogVersion(final String productCatalogVersion)
	{
		this.productCatalogVersion = productCatalogVersion;
	}

	/**
	 * @return the contentCatalogVersion
	 */
	public String getContentCatalogVersion()
	{
		return contentCatalogVersion;
	}

	/**
	 * @param contentCatalogVersion
	 *           the contentCatalogVersion to set
	 */
	@Required
	public void setContentCatalogVersion(final String contentCatalogVersion)
	{
		this.contentCatalogVersion = contentCatalogVersion;
	}

	/**
	 * @return the mediaResource
	 */
	public String getMediaResource()
	{
		return mediaResource;
	}

	/**
	 * @param mediaResource
	 *           the mediaResource to set
	 */
	@Required
	public void setMediaResource(final String mediaResource)
	{
		this.mediaResource = mediaResource;
	}
	
	/**
	 * @return the conversionGroup
	 */
	public String getConversionGroup()
	{
		return conversionGroup;
	}

	/**
	 * @param conversionGroup
	 *           the conversionGroup to set
	 */
	@Required
	public void setConversionGroup(final String conversionGroup)
	{
		this.conversionGroup = conversionGroup;
	}

	/**
	 * @return the storeBaseDirectory
	 */
	public String getStoreBaseDirectory()
	{
		return storeBaseDirectory;
	}

	/**
	 * @param storeBaseDirectory
	 *           the storeBaseDirectory to set
	 */
	@Required
	public void setStoreBaseDirectory(final String storeBaseDirectory)
	{
		this.storeBaseDirectory = storeBaseDirectory;
	}

	/**
	 * @return the mime
	 */
	public String getMime()
	{
		return mime;
	}

	/**
	 * @param mime
	 *           the mime to set
	 */
	@Required
	public void setMime(final String mime)
	{
		this.mime = mime;
	}

	/**
	 * @return the folder
	 */
	public String getFolder()
	{
		return folder;
	}

	/**
	 * @param folder
	 *           the folder to set
	 */
	@Required
	public void setFolder(final String folder)
	{
		this.folder = folder;
	}
}