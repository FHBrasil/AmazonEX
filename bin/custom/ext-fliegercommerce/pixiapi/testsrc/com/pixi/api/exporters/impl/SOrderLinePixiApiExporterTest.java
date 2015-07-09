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
import com.pixi.api.importers.impl.SOrderLinesPixiApiImporter;
import com.pixi.api.result.SOrderLineResult;

/**
 * @author jfelipe
 *
 */
// @UnitTest
public class SOrderLinePixiApiExporterTest {

	private SOrderLinePixiApiExporter fixture;
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
		fixture = new SOrderLinePixiApiExporter();
		fixture.setPixiSoapApi(new DefaultPixiSoapApi());
	}

	/**
	 *
	 * @author jfelipe
	 */
	@Test
	public void importDataTest() {
		try {
			int quantityNotDelivered = 20;
			List<PixiFunctionParameter> parameters = new ArrayList<PixiFunctionParameter>();
			PixiFunctionParameter paramSOrderLineKey = new PixiFunctionParameter();
			paramSOrderLineKey.setType(PixiParameterType.S_ORDER_LINE_KEY);
			paramSOrderLineKey.setValue("585522");
			parameters.add(paramSOrderLineKey);
			PixiFunctionParameter paramUsername = new PixiFunctionParameter();
			paramUsername.setType(PixiParameterType.USERNAME);
			paramUsername.setValue("");
			parameters.add(paramUsername);
			PixiFunctionParameter paramNote = new PixiFunctionParameter();
			paramNote.setType(PixiParameterType.NOTE);
			paramNote.setValue("");
			parameters.add(paramNote);
			PixiFunctionParameter paramQuantityNotDelivered = new PixiFunctionParameter();
			paramQuantityNotDelivered
					.setType(PixiParameterType.QUANTITY_NOT_DELIVERED);
			paramQuantityNotDelivered.setValue(String
					.valueOf(quantityNotDelivered));
			parameters.add(paramQuantityNotDelivered);
			PixiFunctionParameter paramEstimatedDelivery = new PixiFunctionParameter();
			paramEstimatedDelivery
					.setType(PixiParameterType.ESTIMATED_DELIVERY);
			paramEstimatedDelivery.setValue("");
			parameters.add(paramEstimatedDelivery);
			//
			fixture.exportData(parameters);
			//
			List<PixiFunctionParameter> paramsTest = new ArrayList<PixiFunctionParameter>();
			PixiFunctionParameter paramOrderRef = new PixiFunctionParameter();
			paramOrderRef.setType(PixiParameterType.S_ORDER_REFERENCE);
			paramOrderRef.setValue(""); // TODO: get the order reference
			paramsTest.add(paramOrderRef);
			//
			SOrderLinesPixiApiImporter sorderlinesImporter = new SOrderLinesPixiApiImporter();
			sorderlinesImporter.setPixiSoapApi(new DefaultPixiSoapApi());
			List<SOrderLineResult> results = sorderlinesImporter
					.importData(paramsTest);
			Assert.assertTrue("results should not be null.", results != null);
			Assert.assertFalse("results  should not be empty.",
					results.isEmpty());
			SOrderLineResult result = results.get(0);
			Assert.assertEquals(
					"Results quantity not delivered should be the same as sent.",
					quantityNotDelivered, result.getQuantityNotDelivered());
		} catch (Exception e) {
			Assert.assertTrue("Should not have thrown an exception. Type: "
					+ e.getClass().getName() + ". Message: " + e.getMessage(),
					false);
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
			Assert.assertEquals("Wrong exception message. ",
					"The PixiAPI function parameter " + "should not be null.",
					actualException.getMessage());
		} catch (Exception e) {
			Assert.assertTrue("Should not have thrown an exception. Type: "
					+ e.getClass().getName() + ". Message: " + e.getMessage(),
					false);
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
			Assert.assertEquals("Wrong exception message. ",
					"The given PixiFunctionParameter "
							+ "list has an invalid parameters: "
							+ PixiParameterType.BIN_NAME.getValue(),
					actualException.getMessage());
		} catch (Exception e) {
			Assert.assertTrue("Should not have thrown an exception of type "
					+ e.getClass().getName(), false);
		}
	}
}
