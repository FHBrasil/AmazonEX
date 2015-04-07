package com.pixi.api.importers.impl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.pixi.api.core.PixiApiResponseTags;
import com.pixi.api.core.PixiFunction;
import com.pixi.api.core.PixiFunctionParameter;
import com.pixi.api.core.PixiParameterType;
import com.pixi.api.exceptions.SOAPResponseErrorException;
import com.pixi.api.importers.AbstractPixiApiImporter;
import com.pixi.api.result.IntegerResult;

/**
 * Instantiate this class you should not! See {@link com.pixi.api.PixiApiFactory} you
 * must. <br/>
 * <br/>
 * 
 * @author jfelipe
 */
public class SupplierDeliveryDaysPixiApiImporter extends AbstractPixiApiImporter {

    private static final Logger LOG = Logger.getLogger(SupplierDeliveryDaysPixiApiImporter.class
            .getName());
    private static final String DEFAULT_DELIVERY_DAYS = "DefaultDeliveryDays";


    /**
     * 
     */
    public SupplierDeliveryDaysPixiApiImporter() {
        super();
        validParameters.add(PixiParameterType.SUPPLIER_NUMBER);
    }


    /**
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#SUPPLIER_NUMBER}<br/>
     * @return
     *         A XML Node containing the <SOrderKey> tags.
     * @throws SOAPException
     *             in case errors occurred during the Pixi API request
     * @throws SOAPResponseErrorException
     *             in case errors ocurred during the Pixi API response
     *
     * @author jfelipe
     */
    @Override
    public List<IntegerResult> importData(List<PixiFunctionParameter> functionParameters)
            throws SOAPResponseErrorException, SOAPException {
        if (functionParameters == null || functionParameters.isEmpty()) {
            throw new InvalidParameterException(
                    "The PixiAPI function parameter should not be null.");
        }
        return importDefaultDeliveryDays(functionParameters);
    }


    /**
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#SUPPLIER_NUMBER}<br/>
     * @return
     * 
     * @throws SOAPException
     *             in case errors occurred during the Pixi API request
     * @throws SOAPResponseErrorException
     *             in case errors ocurred during the Pixi API response
     *
     * @author jfelipe
     */
    // FIXME: retirar esses for aninhados e retornar o Integer corretamente
    private List<IntegerResult> importDefaultDeliveryDays(
            List<PixiFunctionParameter> functionParameters) throws SOAPResponseErrorException,
            SOAPException {
        List<IntegerResult> results = new ArrayList<IntegerResult>();
        SOAPMessage request = null;
        SOAPMessage response = null;
        Node responseXml = null;
        checkValidParameters(functionParameters);
        request = getPixiSoapApi().buildMessage(
                PixiFunction.GET_SUPPLIERS_DEFAULT_DELIVERY_DAYS, functionParameters);
        response = getPixiSoapApi().sendPixiWebServiceRequest(request);
        responseXml = response.getSOAPBody()
                .getElementsByTagName(PixiApiResponseTags.SQLROWSET1.getValue()).item(0);
        if (responseXml != null) {
            NodeList rowTags = responseXml.getChildNodes();
            mainloop: for (int i = 0; i < rowTags.getLength(); i++) {
                NodeList row = rowTags.item(i).getChildNodes();
                for (int j = 0; j < row.getLength(); j++) {
                    Node deliveryDaysTag = row.item(j);
                    String deliveryDaysTagName = deliveryDaysTag.getNodeName();
                    if (DEFAULT_DELIVERY_DAYS.equalsIgnoreCase(deliveryDaysTagName)) {
                        IntegerResult result = new IntegerResult(Integer.parseInt(deliveryDaysTag
                                .getTextContent()));
                        results.add(result);
                        break mainloop;
                    }
                }
            }
        }
        return results;
    }
}
