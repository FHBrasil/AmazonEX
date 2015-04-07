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
import com.pixi.api.result.ItemStockBinsResult;

import de.hybris.bootstrap.annotations.UnitTest;

/**
 * @author jfelipe
 *
 */
@UnitTest
public class ItemStockBinsPixiApiImporterTest {

    private ItemStockBinsPixiApiImporter fixture;
    //
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
        fixture = new ItemStockBinsPixiApiImporter();
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
            List<PixiFunctionParameter> parameters = new ArrayList<PixiFunctionParameter>();
            PixiFunctionParameter paramItemKey = new PixiFunctionParameter();
            paramItemKey.setType(PixiParameterType.ITEM_KEY);
            paramItemKey.setValue("");
            parameters.add(paramItemKey);
            PixiFunctionParameter paramEan = new PixiFunctionParameter();
            paramEan.setType(PixiParameterType.EAN);
            paramEan.setValue("");
            parameters.add(paramEan);
            PixiFunctionParameter paramItemSupplNumber = new PixiFunctionParameter();
            paramItemSupplNumber.setType(PixiParameterType.ITEM_SUPPLIER_NUMBER);
            paramItemSupplNumber.setValue("8199");
            parameters.add(paramItemSupplNumber);
            PixiFunctionParameter paramItemNumber = new PixiFunctionParameter();
            paramItemNumber.setType(PixiParameterType.ITEM_NUMBER_INTERNAL);
            paramItemNumber.setValue("2012000028951");
            parameters.add(paramItemNumber);
            //
            List<ItemStockBinsResult> results = fixture.importData(parameters);
            Assert.assertTrue("Results should not be null.", results != null);
            Assert.assertTrue("Results should not be empty.", results.size() > 0);
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
