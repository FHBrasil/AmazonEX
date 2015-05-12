package br.hering.core.cms.component.slot.impl;

import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.cms2.model.restrictions.AbstractRestrictionModel;
import de.hybris.platform.cms2.model.restrictions.CMSTimeRestrictionModel;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;


public class CMSTimeRestrictionComparator implements Comparator<AbstractCMSComponentModel>
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(AbstractCMSComponentModel o1, AbstractCMSComponentModel o2)
	{
		CMSTimeRestrictionModel timeRestriction1 = getTimeRestriction(o1);
		CMSTimeRestrictionModel timeRestriction2 = getTimeRestriction(o2);

		if (timeRestriction1 != null && timeRestriction2 != null)
		{
			Date activeUntil1 = timeRestriction1.getActiveUntil();
			Date activeUntil2 = timeRestriction2.getActiveUntil();
			
			return activeUntil1.compareTo(activeUntil2);
		}

		if (timeRestriction1 != null)
		{
			return -1;
		}

		if (timeRestriction2 != null)
		{
			return 1;
		}

		return 0;
	}

	private CMSTimeRestrictionModel getTimeRestriction(final AbstractCMSComponentModel cmsComponent)
	{
		final List<AbstractRestrictionModel> restrictions = cmsComponent.getRestrictions();

		if (CollectionUtils.isEmpty(restrictions))
		{
			return null;
		}

		for (AbstractRestrictionModel restriction : restrictions)
		{
			if (restriction instanceof CMSTimeRestrictionModel 
					&& ((CMSTimeRestrictionModel) restriction).getActiveUntil() != null)
			{
				return (CMSTimeRestrictionModel) restriction;
			}
		}

		return null;
	}
}