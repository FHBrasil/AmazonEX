/**
 * 
 */
package de.kpfamily.hmc.productcode;

import static org.mockito.Mockito.mock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;

import com.google.common.base.Strings;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.internal.model.impl.DefaultModelServiceInterceptorContext;
import de.kpfamily.hmc.events.interceptors.ProductCodeEventInterceptor;
import de.kpfamily.hmc.exceptions.InvalidProductCodeException;
import de.kpfamily.hmc.model.ProductCodeModel;

/**
 * @author jfelipe
 *
 */
@UnitTest
public final class ProductCodeEventInterceptorTest {

    // Test Object
    private ProductCodeEventInterceptor fixture;
    
    // Models
    private ProductCodeModel productCodeModel;
    
    // DAOs
    
    // Services
    
    // Controllers
    
    // Interceptors
    @InjectMocks
    private InterceptorContext interceptorContext;
    
    // Rules
    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Before
    public void setUp() {
        fixture = new ProductCodeEventInterceptor();
        // Creating a dummy, but valid, model
        productCodeModel = new ProductCodeModel();
        productCodeModel.setName("Product JUnit Test");
        // Injecting all annotated mocks
        interceptorContext = mock(DefaultModelServiceInterceptorContext.class);
    }


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Test
    public void onPrepareTest() {
        try {
            fixture.onPrepare(productCodeModel, interceptorContext);
            Assert.assertTrue("ProductCodeModel.CODE should not be null or empty",
                    Strings.isNullOrEmpty(productCodeModel.getCode()));
            Assert.assertTrue("ProductCodeModel.EAN should not be null or empty",
                    Strings.isNullOrEmpty(productCodeModel.getEan()));
        } catch (InterceptorException ie) {
            // nothing to do in this case...
        }
    }


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Test
    public void onValidateTest() {
        try {
            productCodeModel.setCode("100000000001");
            productCodeModel.setEan("1000011000012");
            fixture.onValidate(productCodeModel, interceptorContext);
        } catch (InterceptorException ie) {
            Assert.assertTrue("Should not have thrown an InterceptorException", false);
        }
    }


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Test
    public void onValidateTest_InvalidProductCodeModel_EmptyName() {
        try {
            productCodeModel.setName("");
            productCodeModel.setCode("100000000001");
            productCodeModel.setEan("1000011000012");
            expectedException.expect(InvalidProductCodeException.class);
            expectedException.expectMessage("[Property " + ProductCodeModel._TYPECODE + "."
                    + ProductCodeModel.NAME + " should not be null. " + "[" + ProductModel.CODE
                    + ": " + productCodeModel.getCode() + "]");
            fixture.onValidate(productCodeModel, interceptorContext);
        } catch (InterceptorException ie) {
            Assert.assertTrue("Should not have thrown an InterceptorException", false);
        }
    }


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Test
    public void onValidateTest_InvalidProductCodeModel_EmptyCode() {
        try {
            productCodeModel.setCode("");
            productCodeModel.setEan("1000011000012");
            expectedException.expect(InvalidProductCodeException.class);
            expectedException.expectMessage("[Property " + ProductCodeModel._TYPECODE + "."
                    + ProductCodeModel.CODE + " should not be null.");
            fixture.onValidate(productCodeModel, interceptorContext);
        } catch (InterceptorException ie) {
            Assert.assertTrue("Should not have thrown an InterceptorException", false);
        }
    }


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Test
    public void onValidateTest_InvalidProductCodeModel_InvalidEAN() {
        try {
            productCodeModel.setEan("1000011000014");
            productCodeModel.setCode("100000000001");
            expectedException.expect(InvalidProductCodeException.class);
            expectedException.expectMessage("[Property " + ProductCodeModel._TYPECODE + "."
                    + ProductCodeModel.EAN + " is invalid. " + "[" + ProductModel.CODE + ": "
                    + productCodeModel.getCode() + "]");
            fixture.onValidate(productCodeModel, interceptorContext);
        } catch (InterceptorException ie) {
            Assert.assertTrue("Should not have thrown an InterceptorException", false);
        }
    }
}