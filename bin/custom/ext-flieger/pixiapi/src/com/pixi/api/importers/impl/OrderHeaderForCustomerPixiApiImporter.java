package com.pixi.api.importers.impl;

import java.security.InvalidParameterException;
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
import com.pixi.api.result.OrderHeaderResult;

/**
 * Instantiate this class you should not! See {@link com.pixi.api.PixiApiFactory} you
 * must. <br/>
 * <br/>
 * 
 * @author jfelipe
 */
public class OrderHeaderForCustomerPixiApiImporter extends AbstractPixiApiImporter {

    private static final Logger LOG = Logger.getLogger(OrderHeaderForCustomerPixiApiImporter.class);
    //
    private static final String ORDER_NUMBER = "OrderNr";
    private static final String ORDER_NUMBER_EXTERNAL = "OrderNrExternal";
    private static final String ORDER_HEADER_KEY = "OrderHeaderKey";
    private static final String WHOLE_ORDER_STATE = "WholeOrderState";
    private static final String TRACKING_ID = "TrackingID";
    private static final String SHIP_VENDOR = "ShipVendor";
    private static final String SHOP_ID = "ShopID";
    private static final String PAYMENT_ADDRESS_ID = "PaymentAddressId";
    private static final String SHIPMENT_ADDRESS_ID = "ShipmentAddressId";
    private static final String SHOP_NOTE = "ShopNote";
    private static final String PAYMENT_TYPE = "PaymentType";
    private static final String CC_CARD_TYPE = "CcCardType";
    private static final String CC_CARD_NUMBER = "CcCardNr";
    private static final String CC_CARD_NAME = "CcCardName";
    private static final String CC_CARD_EXP = "ccCardExp";
    private static final String ACCOUNT = "Account";
    private static final String BLZ = "Blz";
    private static final String BANK_NAME = "BankName";
    private static final String ACCOUNT_NAME = "AccountName";
    private static final String ADDRESS_REMARKS = "AddressRemarks";
    private static final String CUST_KEY = "CustKey";
    private static final String CREATE_DATE = "CreateDate";
    private static final String LAST_SHIP_DATE = "LastShipdate";
    private static final String ORDER_DATE = "OrderDate";
    private static final String SHIP_DATE = "Shipdate";
    private static final String SHIP_COST = "ShipCost";
    private static final String ORDER_TOTAL = "OrderTotal";
    private static final String ORDER_LINES_TOTAL = "OrderlinesTotal";


    /**
     * 
     */
    public OrderHeaderForCustomerPixiApiImporter() {
        super();
        validParameters.add(PixiParameterType.CUSTOMER_KEY);
        validParameters.add(PixiParameterType.CUSTOMER_NUMBER_EXTERNAL);
        validParameters.add(PixiParameterType.SHOP_ID);
    }


    /**
     * Imports the order headers bins from Pixi. <br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#CUSTOMER_KEY}<br/>
     *            - {@link PixiParameterType#CUSTOMER_NUMBER_EXTERNAL}<br/>
     *            - {@link PixiParameterType#SHOP_ID}<br/>
     * @return
     *         A List of {@link OrderHeaderResult} containing the results from Pixi
     *         API
     * @throws SOAPException
     *             in case errors occurred during the Pixi API request
     * @throws SOAPResponseErrorException
     *             in case errors ocurred during the Pixi API response
     *
     * @author jfelipe
     */
    @Override
    public List<OrderHeaderResult> importData(List<PixiFunctionParameter> functionParameters)
            throws SOAPResponseErrorException, SOAPException {
        if (functionParameters == null || functionParameters.isEmpty()) {
            throw new InvalidParameterException(
                    "The PixiAPI function parameter should not be null.");
        }
        return importOrderHeaderForCustomer(functionParameters);
    }


    /**
     * Imports the order headers bins from Pixi. <br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#CUSTOMER_KEY}<br/>
     *            - {@link PixiParameterType#CUSTOMER_NUMBER_EXTERNAL}<br/>
     *            - {@link PixiParameterType#SHOP_ID}<br/>
     * @return
     *         A List of {@link OrderHeaderResult} containing the results from Pixi
     *         API
     * @throws SOAPException
     *             in case errors occurred during the Pixi API request
     * @throws SOAPResponseErrorException
     *             in case errors ocurred during the Pixi API response
     *
     * @author jfelipe
     */
    private List<OrderHeaderResult> importOrderHeaderForCustomer(
            List<PixiFunctionParameter> functionParameters) throws SOAPResponseErrorException,
            SOAPException {
        List<OrderHeaderResult> results = new ArrayList<OrderHeaderResult>();
        checkValidParameters(functionParameters);
        SOAPMessage request = getPixiSoapApi().buildMessage(
                PixiFunction.GET_ORDER_HEADERS_FOR_CUSTOMER, functionParameters);
        SOAPMessage response = getPixiSoapApi().sendPixiWebServiceRequest(request);
        Node responseXml = null;
        responseXml = response.getSOAPBody()
                .getElementsByTagName(PixiApiResponseTags.SQLROWSET1.getValue()).item(0);
        if (responseXml != null) {
            for (int i = 0; i < responseXml.getChildNodes().getLength(); i++) {
                Node rowTag = responseXml.getFirstChild();
                if (rowTag != null) {
                    OrderHeaderResult result = new OrderHeaderResult();
                    for (int j = 0; j < rowTag.getChildNodes().getLength(); j++) {
                        Node currentTag = rowTag.getChildNodes().item(j);
                        String currentTagValue = currentTag.getTextContent().trim();
                        switch (currentTag.getNodeName()) {
                            case ORDER_NUMBER:
                                result.setOrderNr(currentTagValue);
                                break;
                            case ORDER_NUMBER_EXTERNAL:
                                result.setOrderNrExternal(currentTagValue);
                                break;
                            case ORDER_HEADER_KEY:
                                result.setOrderHeaderKey(currentTagValue);
                                break;
                            case WHOLE_ORDER_STATE:
                                result.setWholeOrderState(currentTagValue);
                                break;
                            case TRACKING_ID:
                                result.setTrackingID(currentTagValue);
                                break;
                            case SHIP_VENDOR:
                                result.setShipVendor(currentTagValue);
                                break;
                            case SHOP_ID:
                                result.setShopId(currentTagValue);
                                break;
                            case PAYMENT_ADDRESS_ID:
                                result.setPaymentAddressId(currentTagValue);
                                break;
                            case SHIPMENT_ADDRESS_ID:
                                result.setShipmentAddressId(currentTagValue);
                                break;
                            case SHOP_NOTE:
                                result.setShopNote(currentTagValue);
                                break;
                            case PAYMENT_TYPE:
                                result.setPaymentType(currentTagValue);
                                break;
                            case CC_CARD_TYPE:
                                result.setCcCardType(currentTagValue);
                                break;
                            case CC_CARD_NUMBER:
                                result.setCcCardNr(currentTagValue);
                                break;
                            case CC_CARD_NAME:
                                result.setCcCardName(currentTagValue);
                                break;
                            case CC_CARD_EXP:
                                result.setCcCardExp(currentTagValue);
                                break;
                            case ACCOUNT:
                                result.setAccount(currentTagValue);
                                break;
                            case BLZ:
                                result.setBlz(currentTagValue);
                                break;
                            case BANK_NAME:
                                result.setBankName(currentTagValue);
                                break;
                            case ACCOUNT_NAME:
                                result.setAccountName(currentTagValue);
                                break;
                            case ADDRESS_REMARKS:
                                result.setAddressRemarks(currentTagValue);
                                break;
                            case CUST_KEY:
                                result.setCustKey(currentTagValue);
                                break;
                            case CREATE_DATE:
                                Date createDate = parseStringToDate(currentTagValue);
                                result.setCreateDate(createDate);
                                break;
                            case LAST_SHIP_DATE:
                                Date lastShipDate = parseStringToDate(currentTagValue);
                                result.setLastShipDate(lastShipDate);
                                break;
                            case ORDER_DATE:
                                Date OrderDate = parseStringToDate(currentTagValue);
                                result.setOrderDate(OrderDate);
                                break;
                            case SHIP_DATE:
                                Date shipDate = parseStringToDate(currentTagValue);
                                result.setShipDate(shipDate);
                                break;
                            case SHIP_COST:
                                double shipCost = Double.parseDouble(currentTagValue);
                                result.setShipCost(shipCost);
                                break;
                            case ORDER_TOTAL:
                                double orderTotal = Double.parseDouble(currentTagValue);
                                result.setOrderTotal(orderTotal);
                                break;
                            case ORDER_LINES_TOTAL:
                                double orderLinesTotal = Double.parseDouble(currentTagValue);
                                result.setOrderlinesTotal(orderLinesTotal);
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
