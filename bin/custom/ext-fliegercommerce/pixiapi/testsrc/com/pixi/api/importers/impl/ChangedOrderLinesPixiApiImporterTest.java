package com.pixi.api.importers.impl;

import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.pixi.api.core.PixiFunctionParameter;
import com.pixi.api.core.PixiParameterType;
import com.pixi.api.impl.DefaultPixiSoapApi;
import com.pixi.api.result.ChangedOrderLinesResult;

import de.hybris.bootstrap.annotations.UnitTest;

/**
 * @author jfelipe
 *
 */
@UnitTest
public class ChangedOrderLinesPixiApiImporterTest {

    private ChangedOrderLinesPixiApiImporter fixture;
    //
    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    /**
     * 
     */
    @Before
    public void setUp() {
        fixture = new ChangedOrderLinesPixiApiImporter();
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
            PixiFunctionParameter paramDate = new PixiFunctionParameter();
            paramDate.setType(PixiParameterType.SINCE_DATE);
            Calendar testDate = Calendar.getInstance();
            testDate.set(Calendar.DAY_OF_MONTH, 1);
            testDate.set(Calendar.MONTH, 7);
            testDate.set(Calendar.YEAR, 2014);
            String formattedTestDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(testDate
                    .getTime());
            paramDate.setValue(formattedTestDate);
            parameters.add(paramDate);
            PixiFunctionParameter paramWholeHistory = new PixiFunctionParameter();
            paramWholeHistory.setType(PixiParameterType.WHOLE_HISTORY);
            paramWholeHistory.setValue(Boolean.FALSE.toString());
            parameters.add(paramWholeHistory);
            PixiFunctionParameter paramRows = new PixiFunctionParameter();
            paramRows.setType(PixiParameterType.ROWS);
            paramRows.setValue("5");
            parameters.add(paramRows);
            //
            List<ChangedOrderLinesResult> results = fixture.importData(parameters);
            Assert.assertTrue("Results should not be null.", results != null);
            Assert.assertTrue("Results should not be empty.", results.size() > 0);
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
            Assert.assertTrue("Should not have thrown an exception. Type: "
                    + e.getClass().getName() + ". Message: " + e.getMessage(), false);
        }
    }
}
