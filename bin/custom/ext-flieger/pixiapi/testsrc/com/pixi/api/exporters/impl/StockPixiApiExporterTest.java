package com.pixi.api.exporters.impl;

import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.pixi.api.core.PixiFunctionParameter;
import com.pixi.api.core.PixiParameterType;
import com.pixi.api.exceptions.SOAPResponseErrorException;
import com.pixi.api.impl.DefaultPixiSoapApi;
import com.pixi.api.importers.impl.ChangedItemStockPixiApiImporter;
import com.pixi.api.result.ItemStockResult;

/**
 * @author jfelipe
 *
 * @deprecated Use this test you must not! By Herr Gleichmann words, we shouldn't
 *             update stock data on Pixi.
 *
 */
// @UnitTest
@Deprecated
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
    @Deprecated
    public void setUp() {
        fixture = new StockPixiApiExporter();
        fixture.setPixiSoapApi(new DefaultPixiSoapApi());
    }


    /**
     *
     * @author jfelipe
     */
    @Test
    @Deprecated
    public void importDataTest() {
        try {
            int stockQuantity = new Random(Calendar.getInstance().getTimeInMillis()).nextInt(200);
            //
            List<PixiFunctionParameter> parameters = new ArrayList<PixiFunctionParameter>();
            PixiFunctionParameter paramEanUpc = new PixiFunctionParameter();
            paramEanUpc.setType(PixiParameterType.EAN_UPC);
            paramEanUpc.setValue("9999999990004");
            parameters.add(paramEanUpc);
            PixiFunctionParameter paramBinName = new PixiFunctionParameter();
            paramBinName.setType(PixiParameterType.BIN_NAME);
            paramBinName.setValue("");
            parameters.add(paramBinName);
            PixiFunctionParameter paramNewStockQuantity = new PixiFunctionParameter();
            paramNewStockQuantity.setType(PixiParameterType.NEW_STOCK_QUANTITY);
            paramNewStockQuantity.setValue(String.valueOf(stockQuantity));
            parameters.add(paramNewStockQuantity);
            //
            fixture.exportData(parameters);
            // If the exporting went OK, below tests should pass
            List<PixiFunctionParameter> parametersImporter = new ArrayList<PixiFunctionParameter>();
            PixiFunctionParameter paramDate = new PixiFunctionParameter();
            paramDate.setType(PixiParameterType.SINCE_DATE);
            Calendar testDate = Calendar.getInstance();
            testDate.set(Calendar.DAY_OF_MONTH, 13);
            testDate.set(Calendar.MONTH, 3);
            testDate.set(Calendar.YEAR, 2015);
            String formattedTestDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(testDate
                    .getTime());
            paramDate.setValue(formattedTestDate);
            parametersImporter.add(paramDate);
            ChangedItemStockPixiApiImporter stockImporter = new ChangedItemStockPixiApiImporter();
            stockImporter.setPixiSoapApi(new DefaultPixiSoapApi());
            Set<ItemStockResult> results = stockImporter.importData(parametersImporter);
            ItemStockResult actualResult = null;
            //
            for (ItemStockResult result : results) {
                if ("9999999990004".equals(result.getEanUpc())) {
                    actualResult = result;
                    break;
                }
            }
            Assert.assertFalse("Results should not be empty", results.isEmpty());
            Assert.assertEquals("Stock quantity received should be the same as sent.",
                    stockQuantity, actualResult.getPhysicalStock());
        } catch (Exception e) {
            Assert.assertTrue("Should not have thrown an exception. Type: "
                    + e.getClass().getName() + ". Message: " + e.getMessage(), false);
        }
    }


    @Test
    @Deprecated
    public void importData_EanNotExists() {
        try {
            // int stockQuantity = new Random().nextInt(100);
            String stockQuantity = String.valueOf(20);
            //
            List<PixiFunctionParameter> parameters = new ArrayList<PixiFunctionParameter>();
            PixiFunctionParameter paramEanUpc = new PixiFunctionParameter();
            paramEanUpc.setType(PixiParameterType.EAN_UPC);
            paramEanUpc.setValue("4045875029899");
            parameters.add(paramEanUpc);
            PixiFunctionParameter paramBinName = new PixiFunctionParameter();
            paramBinName.setType(PixiParameterType.BIN_NAME);
            paramBinName.setValue("");
            parameters.add(paramBinName);
            PixiFunctionParameter paramNewStockQuantity = new PixiFunctionParameter();
            paramNewStockQuantity.setType(PixiParameterType.NEW_STOCK_QUANTITY);
            paramNewStockQuantity.setValue(stockQuantity);
            parameters.add(paramNewStockQuantity);
            //
            fixture.exportData(parameters);
        } catch (SOAPResponseErrorException re) {
            Assert.assertEquals("Wrong exception type.",
                    SOAPResponseErrorException.class.getName(), re.getClass().getName());
        } catch (Exception e) {
            Assert.assertTrue("Should not have thrown an exception. Type: "
                    + e.getClass().getName() + ". Message: " + e.getMessage(), false);
        }
    }


    /**
     *
     * @author jfelipe
     */
    @Test
    @Deprecated
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
            Assert.assertTrue("Should not have thrown an exception. Type: "
                    + e.getClass().getName() + ". Message: " + e.getMessage(), false);
        }
    }


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Test
    @Deprecated
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
            Assert.assertEquals("Wrong exception message. ",
                    "The given PixiFunctionParameter " + "list has an invalid parameters: "
                            + PixiParameterType.CUSTOMER_KEY.getValue(),
                    actualException.getMessage());
        } catch (Exception e) {
            Assert.assertTrue("Should not have thrown an exception. Type: "
                    + e.getClass().getName() + ". Message: " + e.getMessage(), false);
        }
    }
}
