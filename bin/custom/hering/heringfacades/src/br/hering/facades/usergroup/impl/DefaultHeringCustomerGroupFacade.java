/**
 * 
 */
package br.hering.facades.usergroup.impl;

import de.hybris.platform.commercefacades.customergroups.impl.DefaultCustomerGroupFacade;
import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.core.model.user.UserModel;

import java.util.HashSet;
import java.util.Set;

import org.springframework.util.Assert;

import br.hering.facades.usergroup.HeringCustomerGroupFacade;

/**
 * 
 */
public class DefaultHeringCustomerGroupFacade
extends DefaultCustomerGroupFacade
implements HeringCustomerGroupFacade
{
	
	@Override
	public void removeUserFromCustomerGroup(final String customerGroupId, final String userId)
	{
		Assert.notNull(customerGroupId);
		Assert.notNull(userId);
		final UserModel user = getUserById(userId);
		final UserGroupModel group = getCustomerGroupById(customerGroupId);

		final Set<PrincipalModel> members = new HashSet<PrincipalModel>(group.getMembers());
		final HashSet<PrincipalModel> modifiedMembers = new HashSet<PrincipalModel>(members);
		modifiedMembers.remove(user);
		group.setMembers(modifiedMembers);
		getModelService().save(group);
		getModelService().refresh(user);
	}
}
