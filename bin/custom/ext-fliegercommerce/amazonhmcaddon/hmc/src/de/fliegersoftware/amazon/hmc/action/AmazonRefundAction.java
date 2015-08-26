/**
 * 
 */
package de.fliegersoftware.amazon.hmc.action;

import de.hybris.platform.hmc.util.action.ActionEvent;
import de.hybris.platform.hmc.util.action.ActionResult;
import de.hybris.platform.hmc.util.action.ItemAction;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.util.localization.Localization;

import org.apache.log4j.Logger;


/**
 * @author douglas.canalli
 * 
 */
public class AmazonRefundAction extends ItemAction
{
	private String errorMessage;

	private static final Logger LOG = Logger.getLogger(AmazonRefundAction.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.hmc.util.action.ItemAction#perform(de.hybris.platform.hmc.util.action.ActionEvent)
	 */
	@Override
	public ActionResult perform(final ActionEvent paramActionEvent) throws JaloBusinessException
	{
		final String successMessage = Localization.getLocalizedString("msg.connection.test.success");

		return new ActionResult(ActionResult.OK, successMessage, false);
	}

}
