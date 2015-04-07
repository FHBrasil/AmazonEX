package com.pixi.api.exporters.impl;

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

import de.hybris.bootstrap.annotations.UnitTest;

/**
 * @author jfelipe
 *
 */
// @UnitTest
public class StockPixiApiExporterTest {

    private StockPixiApiExporter fixture;
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
        fixture = new StockPixiApiExporter();
    }


    /**
     *
     * @author jfelipe
     */
    @Test
    public void importDataTest() {
        Assert.assertTrue("not implemented yet!", false);
        // !!!!!!!!!! PELO AMOR DE JESUIS, NÃO ME RODE ESSE TESTE !!!!!!!!!!
        // try {
        // List<PixiFunctionParameter> parameters = new
        // ArrayList<PixiFunctionParameter>();
        // PixiFunctionParameter paramEanUpc = new PixiFunctionParameter();
        // paramEanUpc.setType(PixiParameterType.EAN_UPC);
        // paramEanUpc.setValue("1");
        // parameters.add(paramEanUpc);
        // PixiFunctionParameter paramBinName = new PixiFunctionParameter();
        // paramBinName.setType(PixiParameterType.BIN_NAME);
        // paramBinName.setValue("1");
        // parameters.add(paramBinName);
        // PixiFunctionParameter paramNewStockQuantity = new PixiFunctionParameter();
        // paramNewStockQuantity.setType(PixiParameterType.NEW_STOCK_QUANTITY);
        // paramNewStockQuantity.setValue("1");
        // parameters.add(paramNewStockQuantity);
        // //
        // fixture.exportData(parameters);
        // } catch (Exception e) {
        // Assert.assertTrue("Should not have thrown an exception" + e.getMessage(),
        // false);
        // }
    }


    /**
     *
     * @author jfelipe
     */
    @Test
    public void importDataTest_NullParameters() {
        try {
            fixture.exportData(null);
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
            paramDate.setType(PixiParameterType.CUSTOMER_KEY);
            paramDate.setValue("");
            parameters.add(paramDate);
            fixture.exportData(parameters);
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
