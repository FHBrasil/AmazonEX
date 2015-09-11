/**
 * 
 */
package br.hering.facades.bonussystem.strategies;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.flieger.bonussystem.BonusSystemCreationStrategy;
import com.flieger.dao.BonusSystemDao;
import com.flieger.model.user.BonusSystemConfigModel;
import com.flieger.services.BonusSystemService;


/**
 * @author herbert
 * 
 */
public class DefaultHeringBonusSystemCreationStrategy implements BonusSystemCreationStrategy
{
	private BonusSystemDao bonusSystemDao;
	private BonusSystemService bonusSystemService;
	private UserService userService;

	@Override
	public BonusSystemConfigModel getDefaultBonusSystemConfiguration()
	{
		List<BonusSystemConfigModel> configurations = getBonusSystemDao().getConfigurations();
		if (CollectionUtils.isNotEmpty(configurations))
		{
			return configurations.get(0);
		}
		return null;
	}

	@Override
	public void createBonusSystemForCustomer(CustomerModel customer)
	{
		if(getUserService().isAnonymousUser(customer)) {
			return;
		}
		BonusSystemConfigModel configuration = getDefaultBonusSystemConfiguration();
		if (configuration != null)
		{
			getBonusSystemService().createBonusSystem(customer, configuration);
		}
	}

	protected BonusSystemDao getBonusSystemDao()
	{
		return bonusSystemDao;
	}

	@Required
	public void setBonusSystemDao(BonusSystemDao bonusSystemDao)
	{
		this.bonusSystemDao = bonusSystemDao;
	}
	
	protected BonusSystemService getBonusSystemService()
	{
		return bonusSystemService;
	}

	@Required
	public void setBonusSystemService(BonusSystemService bonusSystemService)
	{
		this.bonusSystemService = bonusSystemService;
	}

	protected UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}
}
