package de.kpfamily.core.translators;

import de.hybris.platform.impex.jalo.translators.AbstractValueTranslator;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloInvalidParameterException;

/**
 * @author felipe
 */
public class KPArticleStateTranslator extends AbstractValueTranslator {
    
    /**
     * 
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
                return "A";
            case "x":
            case "X":
                return "X";
            default:
                return "Z";
        }
    }
}
