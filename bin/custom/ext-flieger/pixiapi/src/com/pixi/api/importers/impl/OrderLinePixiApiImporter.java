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
import com.pixi.api.result.OrderLineResult;

/**
 * Instantiate this class you should not! See {@link com.pixi.api.PixiApiFactory} you
 * must. <br/>
 * <br/>
 * 
 * @author jfelipe
 */
public class OrderLinePixiApiImporter extends AbstractPixiApiImporter {

    private static final Logger LOG = Logger.getLogger(OrderLinePixiApiImporter.class);
    //
    private static final String ORDER_LINE_KEY = "OrderlineKey";
    private static final String ART_NAME = "ArtName";
    private static final String RESERVED_COUNT = "reservedCount";
    private static final String ITEM_REF = "itemRef";
    private static final String AVAILABLE_STOCK_ORDER_LINE = "availStockOrderLine";
    private static final String O_ITEM_SPECIAL_NOTE = "oItemSpecialNote";
    private static final String DF_SHIPMENT_TRACKING_ID = "dFShipmentTrackingID";
    private static final String ITEM_NUMBER_INTERNAL = "itemNrInt";
    private static final String VAT_RATE = "vATRate";
    private static final String ORDER_CURRENCY = "orderCurrency";
    private static final String ORDER_NUMBER_EXTERNAL = "OrderNrExternal";
    private static final String ITEM_NUMBER_EXTERNAL = "ItemNrExternal";
    private static final String STATUS_TEXT = "statusText";
    private static final String STATUS = "status";
    private static final String QUANTITY = "Qty";
    private static final String PRICE_NO_VAT = "priceNoVAT";
    private static final String FULL_PRICE = "fullPrice";
    private static final String DISCOUNT_VALUE = "discountValue";
    private static final String PRICE = "price";
    private static final String ORDER_DATE = "orderDate";
    private static final String DF_SHIP_DATE = "DFShipDate";
    private static final String SHIP_DATE = "ShipDate";
    private static final String ESTIMATED_DELIVERY = "estimatedDelivery";


    /**
     * 
     */
    public OrderLinePixiApiImporter() {
        super();
        validParameters.add(PixiParameterType.ORDER_LINE_KEY);
        validParameters.add(PixiParameterType.ORDER_NUMBER);
        validParameters.add(PixiParameterType.ORDER_NUMBER_EXTERNAL);
    }


    /**
     * Imports the order lines bins from Pixi. <br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#ORDER_LINE_KEY}<br/>
     *            - {@link PixiParameterType#ORDER_NUMBER}<br/>
     *            - {@link PixiParameterType#ORDER_NUMBER_EXTERNAL}<br/>
     * @return
     *         A List of {@link OrderLineResult} containing the results from Pixi
     *         API
     * @throws SOAPException
     *             in case errors occurred during the Pixi API request
     * @throws SOAPResponseErrorException
     *             in case errors ocurred during the Pixi API response
     *
     * @author jfelipe
     */
    @Override
    public List<OrderLineResult> importData(List<PixiFunctionParameter> functionParameters)
            throws SOAPResponseErrorException, SOAPException {
        if (functionParameters == null || functionParameters.isEmpty()) {
            throw new InvalidParameterException(
                    "The PixiAPI function parameter should not be null.");
        }
        return importOrderLines(functionParameters);
    }


    /**
     * Imports the order lines bins from Pixi. <br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#ORDER_LINE_KEY}<br/>
     *            - {@link PixiParameterType#ORDER_NUMBER}<br/>
     *            - {@link PixiParameterType#ORDER_NUMBER_EXTERNAL}<br/>
     * @return
     *         A List of {@link OrderLineResult} containing the results from Pixi
     *         API
     * @throws SOAPException
     *             in case errors occurred during the Pixi API request
     * @throws SOAPResponseErrorException
     *             in case errors ocurred during the Pixi API response
     *
     * @author jfelipe
     */
    private List<OrderLineResult> importOrderLines(List<PixiFunctionParameter> functionParameters)
            throws SOAPResponseErrorException, SOAPException {
        List<OrderLineResult> results = new ArrayList<OrderLineResult>();
        checkValidParameters(functionParameters);
        SOAPMessage request = getPixiSoapApi().buildMessage(PixiFunction.GET_ORDER_LINE,
                functionParameters);
        SOAPMessage response = getPixiSoapApi().sendPixiWebServiceRequest(request);
        Node responseXml = null;
        responseXml = response.getSOAPBody()
                .getElementsByTagName(PixiApiResponseTags.SQLROWSET1.getValue()).item(0);
        if (responseXml != null) {
            for (int i = 0; i < responseXml.getChildNodes().getLength(); i++) {
                Node rowTag = responseXml.getChildNodes().item(i);
                if (rowTag != null) {
                    OrderLineResult result = new OrderLineResult();
                    for (int j = 0; j < rowTag.getChildNodes().getLength(); j++) {
                        Node currentTag = rowTag.getChildNodes().item(j);
                        String currentTagValue = currentTag.getTextContent().trim();
                        switch (currentTag.getNodeName()) {
                            case ORDER_LINE_KEY:
                                result.setOrderlineKey(currentTagValue);
                                break;
                            case ART_NAME:
                                result.setArtName(currentTagValue);
                                break;
                            case RESERVED_COUNT:
                                result.setReservedCount(currentTagValue);
                                break;
                            case ITEM_REF:
                                result.setItemRef(currentTagValue);
                                break;
                            case AVAILABLE_STOCK_ORDER_LINE:
                                result.setAvailStockOrderLine(currentTagValue);
                                break;
                            case O_ITEM_SPECIAL_NOTE:
                                result.setOItemSpecialNote(currentTagValue);
                                break;
                            case DF_SHIPMENT_TRACKING_ID:
                                result.setDFShipmentTrackingID(currentTagValue);
                                break;
                            case ITEM_NUMBER_INTERNAL:
                                result.setItemNrInt(currentTagValue);
                                break;
                            case VAT_RATE:
                                result.setVATRate(currentTagValue);
                                break;
                            case ORDER_CURRENCY:
                                result.setOrderCurrency(currentTagValue);
                                break;
                            case ORDER_NUMBER_EXTERNAL:
                                result.setOrderNrExternal(currentTagValue);
                                break;
                            case ITEM_NUMBER_EXTERNAL:
                                result.setItemNrExternal(currentTagValue);
                                break;
                            case STATUS_TEXT:
                                result.setStatusText(currentTagValue);
                                break;
                            case STATUS:
                                result.setStatus(currentTagValue);
                                break;
                            case QUANTITY:
                                result.setQty(currentTagValue);
                                break;
                            case PRICE_NO_VAT:
                                double priceNoVat = Double.parseDouble(currentTagValue);
                                result.setPriceNoVAT(priceNoVat);
                                break;
                            case FULL_PRICE:
                                double fullPrice = Double.parseDouble(currentTagValue);
                                result.setFullPrice(fullPrice);
                                break;
                            case DISCOUNT_VALUE:
                                double discountValue = Double.parseDouble(currentTagValue);
                                result.setDiscountValue(discountValue);
                                break;
                            case PRICE:
                                double price = Double.parseDouble(currentTagValue);
                                result.setPrice(price);
                                break;
                            case ORDER_DATE:
                                Date orderDate = parseStringToDate(currentTagValue);
                                result.setOrderDate(orderDate);
                                break;
                            case DF_SHIP_DATE:
                                Date dfShipDate = parseStringToDate(currentTagValue);
                                result.setDFShipDate(dfShipDate);
                                break;
                            case SHIP_DATE:
                                Date shipDate = parseStringToDate(currentTagValue);
                                result.setShipDate(shipDate);
                                break;
                            case ESTIMATED_DELIVERY:
                                Date estimatedDelivery = parseStringToDate(currentTagValue);
                                result.setEstimatedDelivery(estimatedDelivery);
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
