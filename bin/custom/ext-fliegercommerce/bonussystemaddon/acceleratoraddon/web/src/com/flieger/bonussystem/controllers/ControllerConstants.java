/**
 * 
 */
package com.flieger.bonussystem.controllers;

import com.flieger.bonussystem.model.BonusPointsCheckoutComponentModel;
import com.flieger.bonussystem.model.ProductBonusPointsComponentModel;
import com.flieger.bonussystem.model.ViewBonusPointsComponentModel;


/**
 * @author herbert
 * 
 */
public interface ControllerConstants
{

	public interface Actions
	{
		public interface Cms
		{
			String _Prefix = "/view/";
			String _Suffix = "Controller";

			public static final String BonusPointsCheckoutComponent = _Prefix + BonusPointsCheckoutComponentModel._TYPECODE
					+ _Suffix;
			public static final String ProductBonusPointsComponent = _Prefix + ProductBonusPointsComponentModel._TYPECODE + _Suffix;
			public static final String ViewBonusPointsComponent = _Prefix + ViewBonusPointsComponentModel._TYPECODE + _Suffix;
		}
	}
}
