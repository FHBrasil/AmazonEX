package de.kpfamily.core.translators;

import org.apache.commons.lang.StringUtils;

import de.hybris.platform.basecommerce.jalo.externaltax.ProductTaxCode;
import de.hybris.platform.europe1.enums.ProductTaxGroup;
import de.hybris.platform.impex.jalo.translators.AbstractValueTranslator;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloInvalidParameterException;


/**
 * Translator used when executing an ImpEx.<br/>
 * "Europe1PriceFactory_PTG" property translate to its respective price tax
 * group object on target system.
 * 
 * @author jfelipe
 */
public class KPProductTaxGroupTranslator extends AbstractValueTranslator {

	
	/**
	 * Return the respective property value as it is, used for exporting data.
	 * 
	 * @param input country exported
	 * @return value of input parameter as it is
	 * 
	 * @author jfelipe
	 */
	@Override
	public String exportValue(Object propertyValue) throws JaloInvalidParameterException {
		return String.valueOf(propertyValue);
	}

	
	/**
	 * Translates a given input parameter to its equivalent on target system.
	 * 
	 * @param input - priceTaxGroup from source system to be translated
	 * @param item - not used
	 * @return priceTaxGroup object equivalent to given input parameter
	 * 
	 * @author jfelipe
	 */
	@Override
	public Object importValue(String propertyValue, Item item)
			throws JaloInvalidParameterException {
		if(StringUtils.equalsIgnoreCase(propertyValue, "Tax_Full")) {
			return ProductTaxGroup.valueOf("eu-vat-full");
		}
		if(StringUtils.equalsIgnoreCase(propertyValue, "Tax_Half")) {
			return ProductTaxGroup.valueOf("eu-vat-half");
		}
		return null;
	}

}