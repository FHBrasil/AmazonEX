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

/**
 * @author jfelipe
 *
 * @deprecated Use this test you must not!
 *
 */
// @UnitTest
@Deprecated
public class EstimatedDeliveryUpdatesPixiApiExporterTest {

	private EstimatedDeliveryUpdatesPixiApiExporter fixture;
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
		fixture = new EstimatedDeliveryUpdatesPixiApiExporter();
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
			PixiFunctionParameter paramOrderNumber = new PixiFunctionParameter();
			paramOrderNumber.setType(PixiParameterType.ORDER_NUMBER);
			paramOrderNumber.setValue("");
			parameters.add(paramOrderNumber);
			PixiFunctionParameter paramShopOrderNumber = new PixiFunctionParameter();
			paramShopOrderNumber.setType(PixiParameterType.SHOP_ORDER_NUMBER);
			paramShopOrderNumber.setValue("");
			parameters.add(paramShopOrderNumber);
			PixiFunctionParameter paramItemRef = new PixiFunctionParameter();
			paramItemRef.setType(PixiParameterType.ITEM_REF);
			paramItemRef.setValue("");
			parameters.add(paramItemRef);
			//
			fixture.exportData(parameters);
			Assert.assertTrue("", false);
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
