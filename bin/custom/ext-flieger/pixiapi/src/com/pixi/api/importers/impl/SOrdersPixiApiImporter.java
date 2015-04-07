package com.pixi.api.importers.impl;

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
import com.pixi.api.result.SOrderResult;

/**
 * Instantiate this class you should not! See {@link com.pixi.api.PixiApiFactory} you
 * must. <br/>
 * <br/>
 * 
 * @author jfelipe
 */
public class SOrdersPixiApiImporter extends AbstractPixiApiImporter {

    private static final Logger LOG = Logger.getLogger(SOrdersPixiApiImporter.class.getName());
    //
    public static final String ORDER_NUMBER = "OrderNr";
    public static final String SUPPLIER_NUMBER = "SupplNr";
    public static final String SUPPLIER_GROUP = "SupplGroup";
    public static final String ORDER_DATE = "OrderDate";
    public static final String STATUS = "Status";
    public static final String CREATE_DATE = "CreateDate";
    public static final String CREATE_EMP = "CreateEmp";
    public static final String UPDATE_DATE = "UpdateDate";
    public static final String UPDATE_EMP = "UpdateEmp";
    public static final String S_ORDER_LOCATION = "SOrderLocation";
    public static final String S_ORDER_KEY = "SOrderKey";
    public static final String EXTERNAL_ORDER_NUMBER = "ExtOrderNr";
    public static final String SUPPLIER_NAME = "SupplName";


    /**
     * 
     */
    public SOrdersPixiApiImporter() {
        super();
        validParameters.add(PixiParameterType.LOCATION_ID);
        validParameters.add(PixiParameterType.SHOW_ORDER);
    }


    /**
     * Imports the orders from Pixi. <br/>
     * 
     * @return
     *         A List of {@link Integer} containing the results from Pixi
     *         API
     * @throws SOAPException
     *             in case errors occurred during the Pixi API request
     * @throws SOAPResponseErrorException
     *             in case errors ocurred during the Pixi API response
     *
     * @author jfelipe
     */
    @Override
    public List<SOrderResult> importData(List<PixiFunctionParameter> functionParameters)
            throws SOAPResponseErrorException, SOAPException {
        return importSOrders();
    }


    /**
     * Import the file that has all <SOrderKey> from Pixi API.
     * 
     * Parameters to be passed to Pixi API. Valid parameters are: <br/>
     * - {@link PixiParameterType#LOCATION_ID}<br/>
     * - {@link PixiParameterType#SHOW_ORDER}<br/>
     * 
     * @return
     *         A XML Node containing the <SOrderKey> tags.
     * @throws SOAPException
     *             in case errors occurred during the Pixi API request
     * @throws SOAPResponseErrorException
     *             in case errors ocurred during the Pixi API response
     *
     * @author jfelipe
     */
    private List<SOrderResult> importSOrders() throws SOAPResponseErrorException, SOAPException {
        List<SOrderResult> results = new ArrayList<SOrderResult>();
        SOAPMessage message;
        SOAPMessage response;
        List<PixiFunctionParameter> functionParameters = new ArrayList<PixiFunctionParameter>();
        PixiFunctionParameter paramLocationId = new PixiFunctionParameter();
        paramLocationId.setType(PixiParameterType.LOCATION_ID);
        paramLocationId.setValue("001");
        PixiFunctionParameter paramShowOrder = new PixiFunctionParameter();
        paramShowOrder.setType(PixiParameterType.SHOW_ORDER);
        paramShowOrder.setValue("1");
        functionParameters.add(paramLocationId);
        functionParameters.add(paramShowOrder);
        checkValidParameters(functionParameters);
        message = getPixiSoapApi().buildMessage(PixiFunction.GET_ALL_S_ORDERS,
                functionParameters);
        response = getPixiSoapApi().sendPixiWebServiceRequest(message);
        if (response != null) {
            Node responseXml = response.getSOAPBody()
                    .getElementsByTagName(PixiApiResponseTags.SQLROWSET1.getValue()).item(0);
            if (responseXml != null) {
                for (int i = 0; i < responseXml.getChildNodes().getLength(); i++) {
                    Node rowTag = responseXml.getChildNodes().item(i);
                    SOrderResult result = new SOrderResult();
                    for (int j = 0; j < rowTag.getChildNodes().getLength(); j++) {
                        Node currentTag = rowTag.getChildNodes().item(j);
                        String currentTagValue = currentTag.getTextContent().trim();
                        switch (currentTag.getNodeName()) {
                            case ORDER_NUMBER:
                                result.setOrderNumber(currentTagValue);
                                break;
                            case SUPPLIER_NUMBER:
                                result.setSupplierNumber(currentTagValue);
                                break;
                            case SUPPLIER_GROUP:
                                result.setSupplierGroup(currentTagValue);
                                break;
                            case ORDER_DATE:
                                Date orderDate = parseStringToDate(currentTagValue);
                                result.setOrderDate(orderDate);
                                break;
                            case STATUS:
                                result.setStatus(currentTagValue);
                                break;
                            case CREATE_DATE:
                                Date createDate = parseStringToDate(currentTagValue);
                                result.setCreateDate(createDate);
                                break;
                            case CREATE_EMP:
                                result.setCreateEmp(currentTagValue);
                                break;
                            case UPDATE_DATE:
                                Date updateDate = parseStringToDate(currentTagValue);
                                result.setUpdateDate(updateDate);
                                break;
                            case UPDATE_EMP:
                                result.setUpdateEmp(currentTagValue);
                                break;
                            case S_ORDER_LOCATION:
                                result.setSOrderLocation(currentTagValue);
                                break;
                            case S_ORDER_KEY:
                                int orderKey = Integer.parseInt(currentTagValue);
                                result.setSOrderKey(orderKey);
                                break;
                            case EXTERNAL_ORDER_NUMBER:
                                result.setExternalOrderNumber(currentTagValue);
                                break;
                            case SUPPLIER_NAME:
                                result.setSupplierName(currentTagValue);
                                break;
                        }
                    }
                    results.add(result);
                }
            }
        }
        return results;
    }
}
