/**
 *
 */
package com.fliegersoftware.media.batch;

import de.hybris.platform.acceleratorservices.dataimport.batch.BatchHeader;


/**
 * @author franthescollymaneira
 *
 */
public class MediaImportationBatchHeader extends BatchHeader
{
	private String productCatalogVersion;

	private String contentCatalogVersion;

	private String mediaResource;

	private String conversionGroup;

	private String mime;

	private String folder;

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
	public void setConversionGroup(final String conversionGroup)
	{
		this.conversionGroup = conversionGroup;
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
	public void setFolder(final String folder)
	{
		this.folder = folder;
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
	public void setMime(final String mime)
	{
		this.mime = mime;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MediaImportationBatchHeader [productCatalogVersion=" + productCatalogVersion + ", contentCatalogVersion="
				+ contentCatalogVersion + ", mediaResource=" + mediaResource + ", conversionGroup=" + conversionGroup + ", mime=" + mime + ", folder=" + folder + ", file="
				+ getFile() + "]";
	}
}