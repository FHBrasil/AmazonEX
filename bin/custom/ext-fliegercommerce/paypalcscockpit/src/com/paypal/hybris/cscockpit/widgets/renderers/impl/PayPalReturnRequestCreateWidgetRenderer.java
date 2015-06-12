package com.paypal.hybris.cscockpit.widgets.renderers.impl;

import de.hybris.platform.cockpit.model.editor.EditorHelper;
import de.hybris.platform.cockpit.model.meta.PropertyDescriptor;
import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.services.config.EditorRowConfiguration;
import de.hybris.platform.cockpit.services.meta.TypeService;
import de.hybris.platform.cockpit.services.values.ObjectValueContainer;
import de.hybris.platform.cockpit.session.impl.CreateContext;
import de.hybris.platform.cockpit.widgets.Widget;
import de.hybris.platform.cscockpit.widgets.renderers.impl.ReturnRequestCreateWidgetRenderer;
import org.zkoss.zul.Hbox;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class PayPalReturnRequestCreateWidgetRenderer extends ReturnRequestCreateWidgetRenderer
{
	@Override
	protected void renderEditor(org.zkoss.zk.ui.HtmlBasedComponent parent, EditorRowConfiguration rowConfig, TypedObject item, ObjectValueContainer returnEntryValueContainer, PropertyDescriptor propertyDescriptor, Widget widget)
	{
		if ((rowConfig != null) && (rowConfig.isVisible()))
		{
			Hbox hbox = new Hbox();
			hbox.setParent(parent);
			hbox.setWidth("96%");
			hbox.setWidths("9em, none");

			CreateContext ctx = new CreateContext(null, item, propertyDescriptor, null);
			TypeService typeService = getCockpitTypeService();
			ctx.setExcludedTypes(EditorHelper.parseTemplateCodes(rowConfig.getParameter("excludeCreateTypes"), typeService));
			ctx.setAllowedTypes(EditorHelper.parseTemplateCodes(rowConfig.getParameter("restrictToCreateTypes"), typeService));

			Map<String, Object> params = new HashMap();
			params.putAll(rowConfig.getParameters());
			params.put("createContext", ctx);
			params.put("eventSource", widget);


			EditorHelper.createEditor(item, propertyDescriptor, hbox, returnEntryValueContainer, true, rowConfig.getEditor(), params);
		}
	}
}
