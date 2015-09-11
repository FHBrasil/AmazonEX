/**
 * 
 */
package br.hering.core.hmc.util.action;

import de.hybris.platform.acceleratorcms.utils.SpringHelper;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.hmc.FileDownloadWindow;
import de.hybris.platform.hmc.HMCHelper;
import de.hybris.platform.hmc.generic.GenericItemSearchListChip;
import de.hybris.platform.hmc.generic.nodes.AttributeNode;
import de.hybris.platform.hmc.generic.nodes.DefaultReferenceNode;
import de.hybris.platform.hmc.generic.nodes.ItemLayoutNode;
import de.hybris.platform.hmc.generic.nodes.StructureLoader;
import de.hybris.platform.hmc.jalo.AccessManager;
import de.hybris.platform.hmc.jalo.HMCSystemException;
import de.hybris.platform.hmc.util.action.ActionEvent;
import de.hybris.platform.hmc.util.action.ActionResult;
import de.hybris.platform.hmc.util.action.CSVExportAction;
import de.hybris.platform.hmc.webchips.DisplayState;
import de.hybris.platform.hmc.webchips.Window;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloItemNotFoundException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.type.AttributeDescriptor;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.Type;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.stock.StockService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * @author franthescollymaneira
 * 
 */
public class ProductCSVExportAction extends CSVExportAction
{
	private static final Logger LOG = Logger.getLogger(ProductCSVExportAction.class.getName());
	
	private WarehouseService warehouseService;
	private StockService stockService;
	private ModelService modelService;
	private DisplayState displayState;

	private void appendRow(StringBuilder result, List<String> values)
	{
		result.append(StringUtils.join(values, ";"));
		result.append("\n");
	}

	private AttributeDescriptor getAttributeDescriptor(ComposedType itemType, String attributeName)
	{
		try
		{
			return itemType.getAttributeDescriptor(attributeName);
		}
		catch (JaloItemNotFoundException e)
		{
			throw new HMCSystemException(e, "Attribute with name " + attributeName + " not found !");
		}
	}

	private DisplayState getDisplayState(ActionEvent event)
	{
		GenericItemSearchListChip searchListChip = (GenericItemSearchListChip) event.getSource();

		return searchListChip.getDisplayState();
	}

	private List<Item> getItems(ActionEvent event)
	{
		return new ArrayList(super.getAllItems(event));
	}

	private String getValueAsString(Item item, String attributeName, String msgAttrError)
	{
		String value;
		if ((item instanceof Type) && ("name".equals(attributeName)))
		{
			value = HMCHelper.getTypeName((Type) item);
		}
		else if("stockLevels".equals(attributeName))
		{
			value = getStockLevelAvailable(item);
		}
		else
		{
			try
			{
				Object attributeValue = StructureLoader.getLoadAction().loadAttributeValue(item, attributeName);
				value = DefaultReferenceNode.toString(displayState, attributeValue, true);
			}
			catch (JaloSystemException e)
			{
				LOG.warn("Error while getting value for attribute " + attributeName + "!" + e, e);

				value = msgAttrError + ": " + HMCHelper.getCoreExceptionMessage(displayState, e);
			}
			catch (Exception e)
			{
				LOG.error("Error while getting value for attribute " + attributeName + "! " + e, e);

				throw new HMCSystemException(e);
			}
		}
		return value;
	}

	/**
	 * @param item 
	 * @return
	 */
	private String getStockLevelAvailable(Item item)
	{
		try
		{
			ProductModel product = modelService.get(item);
			
			WarehouseModel warehouse = warehouseService.getWarehouseForCode("hering_estoque_default");
			
			StockLevelModel stockLevel = stockService.getStockLevel(product, warehouse);
			
			if(stockLevel != null)
			{
				return String.valueOf(stockLevel.getAvailable());
			}
		}
		catch (Exception e)
		{
			//
		}
		return String.valueOf("0");
	}

	private List<String> getValuesAsList(Item item, ItemLayoutNode itemLayoutNode)
	{
		String msgAttrNotReadable = displayState.getLocalizedString("attributenotreadable");
		String msgAttrError = displayState.getLocalizedString("attributeerror");

		List listEntries = new ArrayList();
		ComposedType itemType = item.getComposedType();
		boolean readAccess = AccessManager.getInstance().canRead(itemType);

		for (AttributeNode an : itemLayoutNode.getAttributeNodes())
		{
			String attributeName = an.getAttributeQualifier();
			String value;
			if (!(readAccess))
			{
				value = msgAttrNotReadable;
			}
			else
			{
				AttributeDescriptor descriptor = getAttributeDescriptor(itemType, attributeName);
				if (!(AccessManager.getInstance().canRead(descriptor)))
				{
					value = msgAttrNotReadable;
				}
				else
				{
					value = getValueAsString(item, attributeName, msgAttrError);
				}
			}
			listEntries.add(value);
		}
		return listEntries;
	}
	
	@Override
	public ActionResult perform(ActionEvent event) throws JaloBusinessException
	{
		ComposedType itemType = (ComposedType) ((Map) event.getData()).get("itemtype");
		
		if(!"HeringSizeVariantProduct".equalsIgnoreCase(itemType.getCode()))
		{
			return super.perform(event);
		}
		
		ItemLayoutNode itemLayoutNode = StructureLoader.getSearchResultItemLayoutNode(itemType, 1);
		displayState = getDisplayState(event);

		initServices();

		StringBuilder result = new StringBuilder();

		appendRow(result, itemLayoutNode.getAttributeNames());

		for (Item item : getItems(event))
		{
			appendRow(result, getValuesAsList(item, itemLayoutNode));
		}

		Window window = new FileDownloadWindow(displayState, HMCHelper.getLocalizedString("action.searchresult.exportcsv.window"),
				result.toString(), "export.csv", "text/csv");
		window.open(300, 200);

		return new ActionResult(0, "Items exported", false, false);
	}

	/**
	 * 
	 */
	private void initServices()
	{
		warehouseService = getService("warehouseService", WarehouseService.class);
		stockService = getService("stockService", StockService.class);
		modelService = getService("modelService", ModelService.class);
	}
	
	private <T> T getService(String beanName, Class<T> beanClass)
	{
		HttpServletRequest request = displayState.getRequest();
		return SpringHelper.getSpringBean(request, beanName, beanClass, true);
	}
}