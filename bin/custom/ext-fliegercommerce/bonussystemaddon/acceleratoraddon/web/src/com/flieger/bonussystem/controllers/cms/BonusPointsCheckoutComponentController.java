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
import com.flieger.bonussystem.model.BonusPointsCheckoutComponentModel;


/**
 * @author herbert
 * 
 */
@Controller("BonusPointsCheckoutComponentController")
@Scope("tenant")
@RequestMapping(value = ControllerConstants.Actions.Cms.BonusPointsCheckoutComponent)
public class BonusPointsCheckoutComponentController extends
		AbstractCMSAddOnComponentController<BonusPointsCheckoutComponentModel>
{
	@Resource
	private BonusSystemFacade bonusSystemFacade;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final BonusPointsCheckoutComponentModel component)
	{
		model.addAttribute("discount", bonusSystemFacade.getCartDiscount());
		model.addAttribute("cartPoints", bonusSystemFacade.getCartPoints());
	}
}