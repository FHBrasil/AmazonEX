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
import com.pixi.api.impl.DefaultPixiSoapApi;

import de.hybris.bootstrap.annotations.UnitTest;

/**
 * @author jfelipe
 *
 */
@UnitTest
public class ItemSupplierPixiApiExporterTest {

    private ItemSupplierPixiApiExporter fixture;
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
        fixture = new ItemSupplierPixiApiExporter();
        fixture.setPixiSoapApi(new DefaultPixiSoapApi());
    }


    /**
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
            PixiFunctionParameter paramItemNumberInternal = new PixiFunctionParameter();
            paramItemNumberInternal.setType(PixiParameterType.ITEM_NUMBER_INTERNAL);
            paramItemNumberInternal.setValue("");
            parameters.add(paramItemNumberInternal);
            PixiFunctionParameter paramSupplierNumber = new PixiFunctionParameter();
            paramSupplierNumber.setType(PixiParameterType.SUPPLIER_NUMBER);
            paramSupplierNumber.setValue("");
            parameters.add(paramSupplierNumber);
            PixiFunctionParameter paramSupplierPrice = new PixiFunctionParameter();
            paramSupplierPrice.setType(PixiParameterType.SUPPLIER_PRICE);
            paramSupplierPrice.setValue("");
            parameters.add(paramSupplierPrice);
            PixiFunctionParameter paramEan = new PixiFunctionParameter();
            paramEan.setType(PixiParameterType.EAN);
            paramEan.setValue("4007923665107");                 // older
            // paramEan.setValue("4045875029899");                 // newer
            parameters.add(paramEan);
            PixiFunctionParameter paramItemNumberSupplier = new PixiFunctionParameter();
            paramItemNumberSupplier.setType(PixiParameterType.ITEM_SUPPLIER_NUMBER);
            paramItemNumberSupplier.setValue("");
            parameters.add(paramItemNumberSupplier);
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
            Assert.assertTrue("Should not have thrown an exception of type "
                    + e.getClass().getName(), false);
        }
    }
}
