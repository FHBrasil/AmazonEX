/**
 * 
 */
package de.fliegersoftware.amazon.hmc;

import de.hybris.platform.hmc.CustomChip;
import de.hybris.platform.hmc.webchips.Chip;
import de.hybris.platform.hmc.webchips.DisplayState;

import java.util.Map;


/**
 * Panel to execute amazon sandbox simulations
 * 
 * @author douglas.canalli
 */
public class AmazonSandboxSimulationChip extends CustomChip
{

	/**
	 * @param displayState
	 * @param parent
	 * @param attributes
	 * @param jspURI
	 */
	public AmazonSandboxSimulationChip(final DisplayState displayState, final Chip parent, final Map attributes,
			final String jspURI)
	{
		super(displayState, parent, attributes, jspURI);
	}

	private static final String JSP_URI = "/ext/amazonhmcaddon/amazonSandboxSimulationChip.jsp";

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.hmc.webchips.Chip#getJSPURI()
	 */
	@Override
	public String getJSPURI()
	{
		return JSP_URI;
	}

}
