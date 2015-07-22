package de.kpfamily.core.translators;

import de.hybris.platform.impex.jalo.translators.AbstractValueTranslator;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloInvalidParameterException;

/**
 * @author felipe
 */
public class KPArticleStateTranslator extends AbstractValueTranslator {
    
    /**
     * Return the respective country as it is, used for exporting data.
     * 
     * @param input
     *            country exported
     * @return value of input parameter as it is
     * @author jfelipe
     */
    @Override
    public String exportValue(Object input) throws JaloInvalidParameterException {
        return String.valueOf(input);
    }
    
    
    /**
     * 
     */
    @Override
    public Object importValue(String input, Item item) throws JaloInvalidParameterException {
        switch (input.trim()) {
            case "A":
            case "AF":
            case "AL":
            case "RP":
            case "X":
            case "Z":
            case "ZL":
            case "x":
            case "z":
                return "";
        }
        return "";
    }
}
