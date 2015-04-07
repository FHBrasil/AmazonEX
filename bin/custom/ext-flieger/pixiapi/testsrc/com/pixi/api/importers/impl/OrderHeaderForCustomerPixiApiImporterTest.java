package com.pixi.api.importers.impl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.pixi.api.core.PixiFunctionParameter;
import com.pixi.api.core.PixiParameterType;
import com.pixi.api.impl.DefaultPixiSoapApi;
import com.pixi.api.result.OrderHeaderResult;

import de.hybris.bootstrap.annotations.UnitTest;

/**
 * @author jfelipe
 *
 */
@UnitTest
public class OrderHeaderForCustomerPixiApiImporterTest {

    private OrderHeaderForCustomerPixiApiImporter fixture;
    //
    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    /**
     * 
     */
    @Before
    public void setUp() {
        fixture = new OrderHeaderForCustomerPixiApiImporter();
        fixture.setPixiSoapApi(new DefaultPixiSoapApi());
    }


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Test
    public void importDataTest() {
        try {
            // FIXME: This test is not working because we need to find valid parameters
            List<PixiFunctionParameter> parameters = new ArrayList<PixiFunctionParameter>();
            PixiFunctionParameter paramCustomerKey = new PixiFunctionParameter();
            paramCustomerKey.setType(PixiParameterType.CUSTOMER_KEY);
            paramCustomerKey.setValue("3667");
            parameters.add(paramCustomerKey);
            PixiFunctionParameter paramCustomerNumberExternal = new PixiFunctionParameter();
            paramCustomerNumberExternal.setType(PixiParameterType.CUSTOMER_NUMBER_EXTERNAL);
            paramCustomerNumberExternal.setValue("");
            parameters.add(paramCustomerNumberExternal);
            PixiFunctionParameter paramShopId = new PixiFunctionParameter();
            paramShopId.setType(PixiParameterType.SHOP_ID);
            paramShopId.setValue("1");
            parameters.add(paramShopId);
            //
            List<OrderHeaderResult> results = fixture.importData(parameters);
            Assert.assertTrue("Results should not be null.", results != null);
        } catch (Exception e) {
            Assert.assertTrue("should not have thrown an Exception: " + e.getMessage(), false);
        }
    }


    /**
     *
     * @author jfelipe
     */
    @Test
    public void importDataTest_NullParameters() {
        try {
            fixture.importData(null);
        } catch (InvalidParameterException actualException) {
            InvalidParameterException expectedException = new InvalidParameterException(
                    "The PixiAPI function parameter should not be null.");
            Assert.assertEquals(
                    "Should have thrown an exception of type InvalidParameterException",
                    expectedException.getClass(), actualException.getClass());
            Assert.assertEquals("Wrong exception message. ", "The PixiAPI function parameter "
                    + "should not be null.", actualException.getMessage());
        } catch (Exception e) {
            Assert.assertTrue("Should not have thrown an exception of type "
                    + e.getClass().getName(), false);
        }
    }


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Test
    public void importDataTest_WrongParameterList() {
        try {
            List<PixiFunctionParameter> parameters = new ArrayList<PixiFunctionParameter>();
            PixiFunctionParameter paramDate = new PixiFunctionParameter();
            paramDate.setType(PixiParameterType.BIN_NAME);
            paramDate.setValue("");
            parameters.add(paramDate);
            fixture.importData(parameters);
        } catch (InvalidParameterException actualException) {
            InvalidParameterException expectedException = new InvalidParameterException(
                    "The PixiAPI function parameter should not be null.");
            Assert.assertEquals(
                    "Should have thrown an exception of type InvalidParameterException",
                    expectedException.getClass(), actualException.getClass());
            Assert.assertEquals("Wrong exception message. ", "The given PixiFunctionParameter "
                    + "list has an invalid parameters: " + PixiParameterType.BIN_NAME.getValue(),
                    actualException.getMessage());
        } catch (Exception e) {
            Assert.assertTrue("Should not have thrown an exception of type "
                    + e.getClass().getName(), false);
        }
    }
}
