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

    private ProductCodeGenerator fixture;
    private ProductCodeModel productCodeModel;
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
    public void isValidEAN13Test_NullEAN() {
        expectedException.expect(InvalidParameterException.class);
        expectedException.expectMessage("EAN13 must have exactly 13 digits");
        fixture.isValidEAN13(null);
    }


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Test
    public void isValidEAN13Test_EmptyEAN() {
        expectedException.expect(InvalidParameterException.class);
        expectedException.expectMessage("EAN13 must have exactly 13 digits");
        fixture.isValidEAN13("");
    }


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Test
    public void isValidEAN13Test_InvalidEAN() {
        expectedException.expect(InvalidParameterException.class);
        expectedException.expectMessage("EAN13 must have exactly 13 digits");
        fixture.isValidEAN13("000000000000");
    }


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Test
    public void isValidEAN13Test_InvalidEAN_WithLetters() {
        expectedException.expect(InvalidParameterException.class);
        expectedException.expectMessage("EAN13 must have exactly 13 digits");
        fixture.isValidEAN13("A000000000000");
    }
}
