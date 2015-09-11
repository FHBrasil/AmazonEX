/**
 *
 */
package com.fliegersoftware.media.batch.task;

import de.hybris.platform.acceleratorservices.dataimport.batch.BatchHeader;
import de.hybris.platform.acceleratorservices.dataimport.batch.converter.ImpexConverter;
import de.hybris.platform.acceleratorservices.dataimport.batch.task.ImpexTransformerTask;

import java.util.Map;

import org.springframework.util.Assert;

import com.fliegersoftware.media.batch.MediaImportationBatchHeader;


/**
 * @author franthescollymaneira
 *
 */
public class MediaImportationImpexTransformerTask extends ImpexTransformerTask
{
	@Override
	protected void buildReplacementSymbols(final Map<String, String> symbols, final BatchHeader header,
			final ImpexConverter converter)
	{
		Assert.isInstanceOf(MediaImportationBatchHeader.class, header);

		final MediaImportationBatchHeader miHeader = (MediaImportationBatchHeader) header;
		symbols.put("$PRODUCT_CATALOG$", getCatalog(miHeader.getProductCatalogVersion()));
		symbols.put("$PRODUCT_CATALOG_VERSION$", getVersion(miHeader.getProductCatalogVersion()));
		symbols.put("$CONTENT_CATALOG$", getCatalog(miHeader.getContentCatalogVersion()));
		symbols.put("$CONTENT_CATALOG_VERSION$", getVersion(miHeader.getContentCatalogVersion()));
		symbols.put("$MEDIA_RESOURCE$", miHeader.getMediaResource());
		symbols.put("$CONVERSION_GROUP$", miHeader.getConversionGroup());
		symbols.put("$MIME$", miHeader.getMime());
		symbols.put("$FOLDER$", miHeader.getFolder());
	}

	private String getCatalog(final String catalogVersionId)
	{
		Assert.hasText(":", "invalid catalog version setup, desired format: 'catalogId:versionId', received: " + catalogVersionId);
		return catalogVersionId.split(":")[0];
	}

	private String getVersion(final String catalogVersionId)
	{
		Assert.hasText(":", "invalid catalog version setup, desired format: 'catalogId:versionId', received: " + catalogVersionId);
		return catalogVersionId.split(":")[1];
	}
}