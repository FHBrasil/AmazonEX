package com.pixi.api.importers.impl;

import java.security.InvalidParameterException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import com.pixi.api.core.PixiApiResponseTags;
import com.pixi.api.core.PixiFunction;
import com.pixi.api.core.PixiFunctionParameter;
import com.pixi.api.core.PixiParameterType;
import com.pixi.api.exceptions.SOAPResponseErrorException;
import com.pixi.api.importers.AbstractPixiApiImporter;
import com.pixi.api.result.ItemDeliveryInfoResult;

/**
 * Instantiate this class you should not! See {@link com.pixi.api.PixiApiFactory} you
 * must. <br/>
 * <br/>
 * 
 * @author jfelipe
 */
public class ItemDeliveryInfoPixiApiImporter extends AbstractPixiApiImporter {
    
    private static final Logger LOG = Logger
            .getLogger(ItemDeliveryInfoPixiApiImporter.class);
    //
    private static final String SUPPLIER_ORDER_NUMBER = "SupplierOrderNr";
    private static final String SUPPLIER_NUMBER = "SupplNr";
    private static final String SUPPLIER_ORDER_QUANTITY = "SupplierOrderQty";
    private static final String ESTIMATED_DELIVERY = "EstimatedDelivery";
    
    
    /**
     * 
     */
    public ItemDeliveryInfoPixiApiImporter() {
        super();
        validParameters.add(PixiParameterType.ITEM_KEY);
        validParameters.add(PixiParameterType.EAN);
        validParameters.add(PixiParameterType.ITEM_SUPPLIER_NUMBER);
        validParameters.add(PixiParameterType.ITEM_NUMBER_INTERNAL);
    }
    
    
    /**
     * Imports the item delivery info from Pixi. <br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#INVOICE_NUMBER}<br/>
     * @return A List of {@link ItemDeliveryInfoResult} containing the results from Pixi
     *         API
     * @throws SOAPException
     *             in case errors occurred during the Pixi API request
     * @throws SOAPResponseErrorException
     *             in case errors ocurred during the Pixi API response
     * @author jfelipe
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    @Override
    public List<ItemDeliveryInfoResult> importData(
            List<PixiFunctionParameter> functionParameters) {
        if (functionParameters == null || functionParameters.isEmpty()) {
            throw new InvalidParameterException(
                    "The PixiAPI function parameter should not be null.");
        }
        return importItemDeliveryInfos(functionParameters);
    }
    
    
    /**
     * Imports the item delivery info from Pixi. <br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#INVOICE_NUMBER}<br/>
     * @return A List of {@link ItemDeliveryInfoResult} containing the results from Pixi
     *         API
     * @throws SOAPException
     *             in case errors occurred during the Pixi API request
     * @throws SOAPResponseErrorException
     *             in case errors ocurred during the Pixi API response
     * @author jfelipe
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    private List<ItemDeliveryInfoResult> importItemDeliveryInfos(
            List<PixiFunctionParameter> functionParameters) {
        List<ItemDeliveryInfoResult> results = new ArrayList<ItemDeliveryInfoResult>();
        checkValidParameters(functionParameters);
        SOAPMessage request;
        try {
            request = getPixiSoapApi().buildMessage(
                    PixiFunction.GET_ITEM_DELIVERY_INFO, functionParameters);
            SOAPMessage response = getPixiSoapApi().sendPixiWebServiceRequest(
                    request);
            Node responseXml = null;
            responseXml = response
                    .getSOAPBody()
                    .getElementsByTagName(PixiApiResponseTags.SQLROWSET1.getValue())
                    .item(0);
            if (responseXml != null) {
                for (int i = 0; i < responseXml.getChildNodes().getLength(); i++) {
                    Node rowTag = responseXml.getChildNodes().item(i);
                    if (rowTag != null) {
                        ItemDeliveryInfoResult result = new ItemDeliveryInfoResult();
                        for (int j = 0; j < rowTag.getChildNodes().getLength(); j++) {
                            Node currentTag = rowTag.getChildNodes().item(j);
                            String currentTagValue = currentTag.getTextContent()
                                    .trim();
                            switch (currentTag.getNodeName()) {
                                case SUPPLIER_ORDER_NUMBER:
                                    result.setSupplierOrderNr(currentTagValue);
                                    break;
                                case SUPPLIER_NUMBER:
                                    result.setSupplNr(currentTagValue);
                                    break;
                                case SUPPLIER_ORDER_QUANTITY:
                                    int supplierorderQuantity = Integer
                                            .parseInt(currentTagValue);
                                    result.setSupplierOrderQty(supplierorderQuantity);
                                    break;
                                case ESTIMATED_DELIVERY:
                                    Date estimatedDeliveryDate = parseStringToDate(currentTagValue);
                                    result.setEstimatedDelivery(estimatedDeliveryDate);
                                    break;
                            }
                        }
                        results.add(result);
                    }
                }
            }
        } catch (KeyManagementException | NoSuchAlgorithmException | SOAPException e) {
            // FIXME: Log this exception and see what we can do about it...
        }
        return results;
    }
}
