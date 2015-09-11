package com.hybris.addon.common.setup;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

import de.hybris.platform.catalog.jalo.Catalog;
import de.hybris.platform.catalog.jalo.CatalogManager;
import de.hybris.platform.catalog.jalo.CatalogVersion;
import de.hybris.platform.catalog.jalo.SyncAttributeDescriptorConfig;
import de.hybris.platform.catalog.jalo.SyncItemJob;
import de.hybris.platform.jalo.JaloItemNotFoundException;
import de.hybris.platform.jalo.type.AttributeDescriptor;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.TypeManager;

public class SyncJobSetupAdvice 
{
	private List<String> rootTypeCodes = new LinkedList<>();
	private Map<Class<?>, List<com.hybris.addon.common.setup.sync.EditSyncAttributeDescriptorData>> editedSyncDescriptors = new LinkedHashMap<>();
	
	private static final Logger LOG = Logger.getLogger(SyncJobSetupAdvice.class);
	
	public void setup(JoinPoint jp)  {
		
		final String catalogId = (String)jp.getArgs()[0];
		if (!getRootTypeCodes().isEmpty() || !getEditedSyncDescriptors().isEmpty())
		{
			final SyncItemJob job = getCatalogSyncJob(catalogId);
			if (job != null)
			{
				processRootTypes(job, catalogId);
				processEditedSyncDescriptors(job, catalogId);
			}
		}
		
	}
	
	protected void processRootTypes(SyncItemJob job, final String catalogId)
	{
		if (!getRootTypeCodes().isEmpty())
		{
			final List<ComposedType> newRootTypes = new LinkedList<ComposedType>(job.getRootTypes());
			for (String rootType : getRootTypeCodes())
			{
				final ComposedType ct = tryGetComposedType(rootType);
				if (ct != null && !newRootTypes.contains(ct))
				{
					LOG.info("adding Root Type [" + ct.getCode() + "] to Sync Job for Catalog [" + catalogId + "]");
					newRootTypes.add(ct);
				}
			}
			job.setRootTypes(newRootTypes);
		}
	}
	
	protected void processEditedSyncDescriptors(SyncItemJob job, final String catalogId)
	{
		if (!getEditedSyncDescriptors().isEmpty())
		{
			for (Class<?> clazz : getEditedSyncDescriptors().keySet())
			{
				for (com.hybris.addon.common.setup.sync.EditSyncAttributeDescriptorData descriptor : getEditedSyncDescriptors().get(clazz))
				{
				
					processEditedSyncDescriptor(job, clazz, descriptor);
				}
			}
		}
	}
	
	/**
	 * Removes an attribute from synchronization
	 * 
	 * @param syncJob
	 *           the synchronization job
	 * @param clazz
	 *           the class
	 * @param attribute
	 *           the attribute, that should be removed
	 */
	protected void processEditedSyncDescriptor(final SyncItemJob syncJob, final Class<?> clazz, final com.hybris.addon.common.setup.sync.EditSyncAttributeDescriptorData descriptor)
	{
		
		final ComposedType composedType = tryGetComposedType(clazz);
		if (composedType != null)
		{
			final AttributeDescriptor attributeDesc = composedType.getDeclaredAttributeDescriptor(descriptor.getQualifier());
			final SyncAttributeDescriptorConfig cfg = syncJob.getConfigFor(attributeDesc, true);
			if (cfg != null && Boolean.TRUE.equals(cfg.isIncludedInSync()))
			{
				LOG.info("Editing [" + composedType.getCode() + "] attribute [" + attributeDesc.getQualifier() + "] in sync job ["
							+ syncJob.getCode() + "]");
				
				if (descriptor.getIncludeInSync() != null)
				{
					cfg.setIncludedInSync(descriptor.getIncludeInSync());
				} 
				else if (descriptor.getCopyByValue() != null)
				{
					cfg.setCopyByValue(descriptor.getCopyByValue());
				}
				else if (descriptor.getUntranslatable() != null)
				{
					cfg.setUntranslatable(descriptor.getUntranslatable());
				}
			}
		}
		
	}
	
	protected ComposedType tryGetComposedType(final Class<?> clazz)
	{
		try
		{
			return TypeManager.getInstance().getComposedType(clazz);
		}
		catch (JaloItemNotFoundException jinfe)
		{
			LOG.warn("unable to resolve typecode for class " + clazz);
		}
		return null;

	}
	
	protected ComposedType tryGetComposedType(final String typeCode)
	{
		try
		{
			return TypeManager.getInstance().getComposedType(typeCode);
		}
		catch (JaloItemNotFoundException jinfe)
		{
			LOG.warn("unable to resolve typecode " + typeCode);
		}
		return null;

	}
	
	protected SyncItemJob getCatalogSyncJob(final String catalogId)
	{
		// Lookup the catalog name
		final Catalog catalog = CatalogManager.getInstance().getCatalog(catalogId);
		if (catalog != null)
		{
			final CatalogVersion source = catalog.getCatalogVersion(CatalogManager.OFFLINE_VERSION);
			final CatalogVersion target = catalog.getCatalogVersion(CatalogManager.ONLINE_VERSION);

			if (source != null && target != null)
			{
				return CatalogManager.getInstance().getSyncJob(source, target);
			}
		}
		return null;
	}

	public List<String> getRootTypeCodes() {
		return rootTypeCodes;
	}

	public void setRootTypeCodes(List<String> rootTypeCodes) {
		this.rootTypeCodes = rootTypeCodes;
	}

	public Map<Class<?>, List<com.hybris.addon.common.setup.sync.EditSyncAttributeDescriptorData>> getEditedSyncDescriptors() {
		return editedSyncDescriptors;
	}

	public void setEditedSyncDescriptors(Map<Class<?>, List<com.hybris.addon.common.setup.sync.EditSyncAttributeDescriptorData>> editedSyncDescriptors) {
		this.editedSyncDescriptors = editedSyncDescriptors;
	}
	
}
