package de.kpfamily.hmc.events.listeners;

import org.apache.log4j.Logger;

import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;
import de.kpfamily.hmc.events.ProductCodeEvent;
import de.kpfamily.hmc.model.ProductCodeModel;

/**
 * @author jfelipe
 *
 */
public class ProductCodeListener extends AbstractEventListener<ProductCodeEvent> {

    private static final Logger LOG = Logger.getLogger(ProductCodeListener.class.getName());
    private ModelService modelService;


    /**
     * 
     * @author jfelipe
     */
    @Override
    protected void onEvent(ProductCodeEvent event) {
        LOG.debug("### event handle enter");
        final ProductCodeModel productCode = event.getModel();
        modelService.save(productCode);
        LOG.debug("### ProductCode created with code: " + productCode.getCode());
        LOG.debug("### event handler left");
    }


    /**
     * @param modelService
     *            the modelService to set
     */
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
}
