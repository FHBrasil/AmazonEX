package de.kpfamily.hmc.events.interceptors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Strings;

import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;
import de.kpfamily.hmc.events.ProductCodeEvent;
import de.kpfamily.hmc.exceptions.InvalidProductCodeException;
import de.kpfamily.hmc.model.ProductCodeModel;
import de.kpfamily.hmc.productcodegenerator.ProductCodeGenerator;

/**
 * @author jfelipe
 *
 */
public class ProductCodeEventInterceptor implements PrepareInterceptor, ValidateInterceptor {

    private static final Logger LOG = Logger.getLogger(ProductCodeEventInterceptor.class.getName());
    @Autowired
    private EventService eventService;


    /**
     * 
     * 
     */
    @Override
    public void onPrepare(Object model, InterceptorContext context)
            throws InterceptorException {
        if(model instanceof ProductCodeModel) {
            final ProductCodeModel productCode = (ProductCodeModel) model;
            final ProductCodeGenerator generator = new ProductCodeGenerator();
            productCode.setCode(generator.getFormattedNextProductCode());
            productCode.setEan(generator.generateEAN(productCode.getCode()));
            if(!generator.isValidEAN(productCode.getEan())) {
                eventService.publishEvent(new ProductCodeEvent(productCode));
            }
            if(Strings.isNullOrEmpty(productCode.getName())) {
                throw new InvalidProductCodeException("[ProductCode.name should not be null.");
            }
        }
    }


    /**
     * 
     * 
     */
    @Override
    public void onValidate(Object model, InterceptorContext context)
            throws InterceptorException {
        if (model instanceof ProductCodeModel) {
            final ProductCodeModel productCode = (ProductCodeModel) model;
            final ProductCodeGenerator productCodeGenerator = new ProductCodeGenerator();
            final String code = productCodeGenerator.getFormattedNextProductCode();
            final String ean = productCodeGenerator.generateEAN(code);
            if (!productCodeGenerator.isValidEAN(ean)) {
                throw new InvalidProductCodeException("[Product with name: "
                        + productCode.getName() + " has an invalid EAN13.");
            }
            if(Strings.isNullOrEmpty(productCode.getName())) {
                throw new InvalidProductCodeException("[ProductCode.name should not be null.");
            }
        }
    }
}
