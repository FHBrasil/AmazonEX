package de.kpfamily.facades;

import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.data.ProductData;


/**
 * 
 * @author jfelipe
 *
 */
public interface KPProductFacade extends ProductFacade {
    
    public ProductData getProductForCode(String code);
}
