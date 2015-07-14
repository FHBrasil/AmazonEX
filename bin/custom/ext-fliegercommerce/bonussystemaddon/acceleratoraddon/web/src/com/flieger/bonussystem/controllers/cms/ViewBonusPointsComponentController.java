/**
 * 
 */
package com.flieger.bonussystem.controllers.cms;

import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flieger.bonussystem.controllers.ControllerConstants;
import com.flieger.bonussystem.facade.BonusSystemFacade;
import com.flieger.bonussystem.model.ViewBonusPointsComponentModel;


/**
 * @author herbert
 * 
 */
@Controller("ViewBonusPointsComponentController")
@Scope("tenant")
@RequestMapping(value = ControllerConstants.Actions.Cms.ViewBonusPointsComponent)
public class ViewBonusPointsComponentController extends AbstractCMSAddOnComponentController<ViewBonusPointsComponentModel>
{
	@Resource
	private BonusSystemFacade bonusSystemFacade;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final ViewBonusPointsComponentModel component)
	{
		model.addAttribute("bonusSystem", bonusSystemFacade.getCurrentUserBonusSystem());
	}
}