package de.kpfamily.core.translators;

import java.util.Map;
import org.apache.commons.lang.StringUtils;
import de.hybris.platform.util.CSVCellDecorator;

/**
 * Decorator used when executing an ImpEx.<br/>
 * "Europe1PriceFactory_PTG" property translate to its respective price tax
 * group object on target system.
 * 
 * @author jfelipe
 */
public class KPProductTaxGroupDecorator implements CSVCellDecorator {

    /**
     * Decorates the ProductTaxGroup value before importing.
     * 
     * @param paramInt
     *            - position of the passed attribute to decorate
     * @param paramMap
     *            - contains (all) the values from the line to be imported.
     * 
     * @author jfelipe
     */
    @Override
    public String decorate(int paramInt, Map<Integer, String> paramMap) {
	String newCode = "";
	String oldCode = paramMap.get(paramInt);
	if (StringUtils.equalsIgnoreCase("Tax_Full", oldCode)) {
	    newCode = "eu-vat-full";
	}
	if (StringUtils.equalsIgnoreCase("Tax_Half", oldCode)) {
	    newCode = "eu-vat-half";
	}
	return newCode;
    }

}
