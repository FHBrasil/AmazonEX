package de.kpfamily.hmc.events.interceptors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Strings;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;
import de.kpfamily.hmc.events.ProductCodeEvent;
import de.kpfamily.hmc.exceptions.InvalidProductCodeException;
import de.kpfamily.hmc.model.ProductCodeModel;
import de.kpfamily.hmc.productcode.ProductCodeGenerator;

/**
 * @author jfelipe
 *
 */
public class ProductCodeEventInterceptor implements PrepareInterceptor, ValidateInterceptor {

    private static final Logger LOG = Logger.getLogger(ProductCodeEventInterceptor.class.getName());
    @Autowired
    private EventService eventService;


    /**
     * Modifies any possible or necessary property on the ProductCode created by the user.
     * Note: Runs before onValidate
     * 
     * @author jfelipe
     */
    @Override
    public void onPrepare(Object model, InterceptorContext context) throws InterceptorException {
        if (model instanceof ProductCodeModel) {
            final ProductCodeGenerator generator = new ProductCodeGenerator();
            final ProductCodeModel productCode = (ProductCodeModel) model;
            productCode.setCode(generator.getFormattedNextProductCode());
            eventService.publishEvent(new ProductCodeEvent(productCode));
        }
    }


    /**
     * Validates the new ProductCode registered by the user.
     * If the new ProductCode has invalid values, stops the event bubbling.
     * Note: Runs after onPrepare
     * 
     * @author jfelipe
     */
    @Override
    public void onValidate(Object model, InterceptorContext context) throws InterceptorException {
        if (model instanceof ProductCodeModel) {
            final ProductCodeModel productCode = (ProductCodeModel) model;
            final ProductCodeGenerator generator = new ProductCodeGenerator();
            if (Strings.isNullOrEmpty(productCode.getCode())) {
                throw new InvalidProductCodeException("[Property " + ProductCodeModel._TYPECODE
                        + "." + ProductCodeModel.CODE + " should not be null.");
            }
            if (Strings.isNullOrEmpty(productCode.getName())) {
                throw new InvalidProductCodeException("[Property " + ProductCodeModel._TYPECODE
                        + "." + ProductCodeModel.NAME + " should not be null. " + "["
                        + ProductModel.CODE + ": " + productCode.getCode() + "]");
            }
            if (!generator.isValidEAN13(productCode.getEan())) {
                throw new InvalidProductCodeException("[Property " + ProductCodeModel._TYPECODE
                        + "." + ProductCodeModel.EAN + " is invalid. " + "[" + ProductModel.CODE
                        + ": " + productCode.getCode() + "]");
            }
        }
    }
}
