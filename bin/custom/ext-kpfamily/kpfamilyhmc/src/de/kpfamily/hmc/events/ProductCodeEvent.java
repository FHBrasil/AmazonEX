/**
 * 
 */
package de.kpfamily.hmc.events;

import de.hybris.platform.servicelayer.event.events.AbstractEvent;
import de.kpfamily.hmc.model.ProductCodeModel;


/**
 * @author jfelipe
 *
 */
public class ProductCodeEvent extends AbstractEvent {
    
    private final ProductCodeModel model;
    
    
    /**
     * 
     * @param model
     */
    public ProductCodeEvent(final ProductCodeModel model) {
        this.model = model;
    }

    
    /**
     * @return the model
     */
    public ProductCodeModel getModel() {
        return model;
    }
    
}
