/**
 *
 */
package com.prudsys.communicator;

import de.hybris.platform.util.Config;


/**
 *
 * @author franthescollymaneira
 *
 */
public enum PrudsysConfiguration
{
	/** Enables some developer only options like debug query parameters */
	prudsys_host("prudsys_host"), prudsys_port("prudsys_port"), prudsys_reid("prudsys_reid"), track_event_globTops(
			"track_event_globTops"), track_event_catTops("track_event_catTops"), track_event_productview("track_event_productview"), track_event_productPage(
			"track_event_productPage"), track_event_basketpage("track_event_basketpage"), track_event_basket("track_event_basket"), track_event_order(
			"track_event_order"), track_event_categoryview("track_event_categoryview"), track_event_click("track_event_click"), event_ping(
			"event_ping"), event_savemodel("event_savemodel"), event_setcontrolgroup("event_setcontrolgroup"), event_getcontrolgroup(
			"event_getcontrolgroup"), event_usertosession("event_usertosession"), prudsys_proxy("prudsys_proxy"), track_event_noResultPage(
			"track_event_noResultPage"), track_event_homePage("track_event_homePage"), track_event_brandPage("track_event_brandPage"), track_event_errorPage(
			"track_event_errorPage");

	private String key = null;
	private String def = null;

	private PrudsysConfiguration(final String key)
	{
		this.key = key;
	}

	private PrudsysConfiguration(final String key, final String def)
	{
		this.key = key;
		this.def = def;
	}

	public String getString()
	{
		return Config.getString(key, def);
	}

}
