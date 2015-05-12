/**
 * 
 */
package br.hering.core.cms.component.slot.impl;

import de.hybris.platform.acceleratorcms.component.slot.impl.DefaultCMSPageSlotComponentService;
import de.hybris.platform.acceleratorcms.data.CmsPageRequestContextData;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.cms2.model.contents.contentslot.ContentSlotModel;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

/**
 * @author franthescollymaneira
 *
 */
public class DefaultHeringCMSPageSlotComponentService extends DefaultCMSPageSlotComponentService
{
	@Resource
	private CMSTimeRestrictionComparator cmsTimeRestrictionComparator;
	
	/* (non-Javadoc)
	 * @see de.hybris.platform.acceleratorcms.component.slot.impl.DefaultCMSPageSlotComponentService#getCMSComponentsForContentSlot(de.hybris.platform.acceleratorcms.data.CmsPageRequestContextData, de.hybris.platform.cms2.model.contents.contentslot.ContentSlotModel, boolean, int)
	 */
	@Override
	public List<AbstractCMSComponentModel> getCMSComponentsForContentSlot(CmsPageRequestContextData context, ContentSlotModel slot, boolean evaluate, int limit)
	{
		List<AbstractCMSComponentModel> components = super.getCMSComponentsForContentSlot(context, slot, evaluate, limit);
		
		if(CollectionUtils.isEmpty(components))
		{
			return components;
		}
		
		Collections.sort(components, cmsTimeRestrictionComparator);
		
		return components;
	}
}