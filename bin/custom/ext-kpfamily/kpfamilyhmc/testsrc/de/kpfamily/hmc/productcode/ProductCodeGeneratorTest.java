package de.kpfamily.hmc.productcode;

import java.security.InvalidParameterException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.hybris.bootstrap.annotations.UnitTest;
import de.kpfamily.hmc.model.ProductCodeModel;

/**
 * @author jfelipe
 *
 */
@UnitTest
public class ProductCodeGeneratorTest {

    // Test Object
    private ProductCodeGenerator fixture;
    
    // Models
    private ProductCodeModel productCodeModel;
    
    // DAOs
    
    // Services
    
    // Controllers
    
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
        fixture = new ProductCodeGenerator();
        // Creating a dummy, but valid, model
        productCodeModel = new ProductCodeModel();
        productCodeModel.setCode("100001");
        productCodeModel.setEan("1000011000012");
        productCodeModel.setName("Product JUnit Test");
    }


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Test
    public void getFormattedNextProductCodeTest() {
        String code = fixture.getFormattedNextProductCode();
        Assert.assertNotNull("code is null.", code);
        Assert.assertEquals("code length was not the expected.", 6, code.length());
        Assert.assertTrue("code must be greater than 99,999.", Integer.parseInt(code) > 99999);
    }


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Test
    public void generateEAN13Test() {
        String ean = fixture.generateEAN13(productCodeModel.getCode());
        Assert.assertNotNull("EAN must not be null.", ean);
        Assert.assertEquals("EAN must have 13 chars", 13, ean.length());
        Assert.assertEquals("EAN must have only digits", true, "[0-9]+".matches(ean));
    }


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Test
    public void generateEAN13Test_InvalidProductCodeLength() {
        fixture.generateEAN13("0000");
        expectedException.expect(InvalidParameterException.class);
        expectedException.expectMessage("Product code must have 6 digits exactly");
    }


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Test
    public void generateEAN13Test_InvalidProductCodeChars() {
        fixture.generateEAN13("00000A");
        expectedException.expect(InvalidParameterException.class);
        expectedException.expectMessage("Product code must have only digits");
    }


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Test
    public void isValidEAN13Test() {
        boolean result = fixture.isValidEAN13(productCodeModel.getEan());
        Assert.assertEquals("EAN must be valid.", true, result);
    }


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Test
    public void isValidEAN13Test_invalidCheckDigit() {
        boolean result = fixture.isValidEAN13("1000011000013");
        Assert.assertEquals("EAN must be invalid.", false, result);
    }
    
    
    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Test
    public void isValidEAN13Test_EmptyOrInvalidEAN() {
        fixture.isValidEAN13(null);
        expectedException.expect(InvalidParameterException.class);
        expectedException.expectMessage("EAN13 must have exactly 13 digits");
        
        fixture.isValidEAN13("");
        expectedException.expect(InvalidParameterException.class);
        expectedException.expectMessage("EAN13 must have exactly 13 digits");
        
        fixture.isValidEAN13("A000000000000");
        expectedException.expect(InvalidParameterException.class);
        expectedException.expectMessage("EAN13 must have exactly 13 digits");
        
        fixture.isValidEAN13("000000000000");
        expectedException.expect(InvalidParameterException.class);
        expectedException.expectMessage("EAN13 must have exactly 13 digits");
    }
}
