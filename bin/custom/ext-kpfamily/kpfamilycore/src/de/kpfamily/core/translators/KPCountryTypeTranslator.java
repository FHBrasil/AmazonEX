package de.kpfamily.core.translators;

import com.google.common.base.Strings;

import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.impex.jalo.translators.AbstractValueTranslator;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

/**
 * Translator used when executing an ImpEx.<br/>
 * "country(isocode)" property translate to its respective country object on
 * target system.
 * 
 * @author jfelipe
 */
public class KPCountryTypeTranslator extends AbstractValueTranslator {

    /**
     * Return the respective country as it is, used for exporting data.
     * 
     * @param input
     *            country exported
     * @return value of input parameter as it is
     * 
     * @author jfelipe
     */
    @Override
    public String exportValue(Object input) throws JaloInvalidParameterException {
	return String.valueOf(input);
    }

    /**
     * Translates a given input parameter to its equivalent on target system.
     * 
     * @param input
     *            - country from source system to be translated
     * @param item
     *            - not used
     * @return country object equivalent to given input parameter
     * 
     * @author jfelipe
     */
    @Override
    public Object importValue(String input, Item item) throws JaloInvalidParameterException {
	final CommonI18NService commonI18NService = (CommonI18NService) Registry
		.getApplicationContext().getBean("commonI18NService");
	input = Strings.isNullOrEmpty(input) ? "de" : input;
	CountryModel country = commonI18NService.getCountry(input.toUpperCase());
	return country.getPk();
    }

}