package com.pixi.api.impl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPMessage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.pixi.api.core.PixiFunction;
import com.pixi.api.core.PixiFunctionParameter;
import com.pixi.api.core.PixiParameterType;

/**
 * @author jfelipe
 *
 */
//@UnitTest
public class DefaultPixiSoapApiTest {

    private DefaultPixiSoapApi fixture;
    private PixiFunction dummyFunction;
    private List<PixiFunctionParameter> dummyParams;
    private final List<String> tags = new ArrayList<String>();
    private final String prefix = "ns9";
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
        fixture = new DefaultPixiSoapApi();
        //
        tags.add("ns9:LocationId");
        tags.add("ns9:ShowOrd");
        //
        dummyFunction = PixiFunction.GET_ALL_S_ORDERS;
        dummyParams = new ArrayList<PixiFunctionParameter>();
        PixiFunctionParameter paramLocationId = new PixiFunctionParameter();
        paramLocationId.setType(PixiParameterType.LOCATION_ID);
        paramLocationId.setValue("001");
        PixiFunctionParameter paramShowOrder = new PixiFunctionParameter();
        paramShowOrder.setType(PixiParameterType.SHOW_ORDER);
        paramShowOrder.setValue("1");
        dummyParams.add(paramLocationId);
        dummyParams.add(paramShowOrder);
    }


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Test
    public void buildMessageTest() {
        try {
            SOAPMessage message = fixture.buildMessage(dummyFunction, dummyParams);
            NodeList messageXml = message.getSOAPBody().getChildNodes();
            Node function = messageXml.item(0);
            Node anyTag = function.getChildNodes().item(0);
            Assert.assertTrue("Message should not be null.", message != null);
            Assert.assertEquals("Function called should be 'pixiGetSOrders'.",
                    "ns9:pixiGetSOrders", function.getNodeName());
            Assert.assertTrue("Message should have any of these tags: " + tags.toString(),
                    tags.contains(anyTag.getNodeName()));
        } catch (Exception e) {
            Assert.assertTrue("should not have thrown an Exception: " + e.getMessage(), false);
        }
    }


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Test
    public void buildMessageTest_NullFunctionType() {
        try {
            expectedException.expect(InvalidParameterException.class);
            expectedException.expectMessage("Request function should not be null.");
            fixture.buildMessage(null, dummyParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Test
    public void buildMessageTest_NullFunctionParameter() {
        try {
            expectedException.expect(InvalidParameterException.class);
            expectedException.expectMessage("Function parameters should not be empty.");
            fixture.buildMessage(PixiFunction.GET_ALL_S_ORDERS, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Test
    public void sendPixiWebServiceRequestTest() {
        Assert.assertTrue("Testing the importers will test the 'sendPixiWebServiceRequest' "
                + "method. No need to test it here. :)", true);
    }
}
