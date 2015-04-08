package com.pixi.api.exporters.impl;

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

import de.hybris.bootstrap.annotations.UnitTest;

/**
 * @author jfelipe
 *
 */
@UnitTest
public class InvoicePaidPixiApiExporterTest {

    private InvoicePaidPixiApiExporter fixture;
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
        fixture = new InvoicePaidPixiApiExporter();
    }


    /**
     *
     * @author jfelipe
     */
    @Test
    public void importDataTest() {
        Assert.assertTrue("not implemented yet!", false);
        try {
            List<PixiFunctionParameter> parameters = new ArrayList<PixiFunctionParameter>();
            PixiFunctionParameter paramInvoiceNumber = new PixiFunctionParameter();
            paramInvoiceNumber.setType(PixiParameterType.INVOICE_NUMBER);
            paramInvoiceNumber.setValue("SHOP00369");
            parameters.add(paramInvoiceNumber);
            PixiFunctionParameter paramPaid = new PixiFunctionParameter();
            paramPaid.setType(PixiParameterType.PAID);
            paramPaid.setValue("1");
            parameters.add(paramPaid);
            PixiFunctionParameter paramPaidDate = new PixiFunctionParameter();
            paramPaidDate.setType(PixiParameterType.PAID_DATE);
            paramPaidDate.setValue("");
            parameters.add(paramPaidDate);
            PixiFunctionParameter paramEventId = new PixiFunctionParameter();
            paramEventId.setType(PixiParameterType.EVENT_ID);
            paramEventId.setValue("PPZ");
            parameters.add(paramEventId);
            //
            fixture.exportData(parameters);
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
    public void importDataTest_WrongParameterList() {
        try {
            List<PixiFunctionParameter> parameters = new ArrayList<PixiFunctionParameter>();
            PixiFunctionParameter paramDate = new PixiFunctionParameter();
            paramDate.setType(PixiParameterType.BIN_NAME);
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
            Assert.assertTrue("Should not have thrown an exception. Type: "
                    + e.getClass().getName() + ". Message: " + e.getMessage(), false);
        }
    }
}
