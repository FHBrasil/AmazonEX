/**
 * 
 */
package com.flieger.dao;

import java.util.List;

import com.flieger.model.user.BonusSystemConfigModel;
import com.flieger.model.user.BonusSystemModel;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.internal.dao.Dao;


/**
 * @author franthescollymaneira
 * 
 */
public interface BonusSystemDao extends Dao
{

	CustomerModel getCustomerForBonusSystem(BonusSystemModel system);
	
	List<BonusSystemConfigModel> getConfigurations();
	
}

