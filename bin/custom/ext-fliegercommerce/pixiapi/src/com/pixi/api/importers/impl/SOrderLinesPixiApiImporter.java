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

import com.pixi.api.core.PixiFunction;
import com.pixi.api.core.PixiFunctionParameter;
import com.pixi.api.core.PixiParameterType;
import com.pixi.api.exceptions.SOAPResponseErrorException;
import com.pixi.api.importers.AbstractPixiApiImporter;
import com.pixi.api.result.SOrderLineResult;

/**
 * Instantiate this class you should not! See {@link com.pixi.api.PixiApiFactory} you must. <br/>
 * <br/>
 * 
 * @author jfelipe
 */
public class SOrderLinesPixiApiImporter extends AbstractPixiApiImporter {
    
    private static final Logger LOG = Logger
            .getLogger(SOrderLinesPixiApiImporter.class.getName());
    //
    public static final String DETAIL = "Detail";
    public static final String DETAILS2 = "Detail2";
    public static final String ARTICLE_NUMBER = "ArtNr";
    public static final String ARTICLE_NAME = "ArtName";
    public static final String ORDER_QUANTITY = "OrderQty";
    public static final String RECEIVE_DATE = "ReceiveDate";
    public static final String STATUS = "Status";
    public static final String CREATE_DATE = "CreateDate";
    public static final String CREATE_EMP = "CreateEmp";
    public static final String UPDATE_DATE = "UpdateDate";
    public static final String UPDATE_EMP = "UpdateEmp";
    public static final String S_ORDER_LINE_KEY = "SOrderlineKey";
    public static final String S_ITEM_REFERENCE = "SitemRef";
    public static final String S_ORDER_REFERENCE = "SOrderRef";
    public static final String ML_CONFIRM = "_x0058_MLconfirm";
    public static final String S_ORDER_LINE_NUMBER = "SOrderlineNr";
    public static final String NOTE = "Note";
    public static final String ESTIMATED_DELIVERY = "EstimatedDelivery";
    public static final String VK_PRICE = "VKPrice";
    public static final String SUMMARY_QUANTITY = "SummaryQuantity";
    public static final String LOCATION_QUANTITY = "LocationQuantity";
    public static final String QUANTITY_RECEIVED = "QtyReceived";
    public static final String QUANTITY_NOT_DELIVERED = "QtyNotDelivered";
    public static final String MIN_ORDER_QUANTITY = "MinOrderQty";
    public static final String MIN_STOCK_QUANTITY = "MinStockQty";
    public static final String ORDER_UNIT = "OrderUnit";
    public static final String ITEM_NUMBER_SUPPLIER = "ItemNrSuppl";
    public static final String ITEM_NUMBER_INTERNAL = "ItemNrInt";
    public static final String VAT_PERC = "VATperc";
    public static final String EK_PRICE = "EKprice";
    public static final String EK_VALUE = "EKValue";
    public static final String HAL_CUST_O = "Hal_Cust_O";
    public static final String OPEN_CUST_O = "Open_Cust_O";
    public static final String OPEN_SUPPLIER_0 = "Open_Suppl_O";
    public static final String L_30 = "L30";
    public static final String L_120 = "L120";
    public static final String L_365 = "L365";
    public static final String STOCK_QUANTITY = "StockQty";
    public static final String PRICE = "Price";
    
    
    /**
     * 
     */
    public SOrderLinesPixiApiImporter() {
        super();
        validParameters.add(PixiParameterType.S_ORDER_REFERENCE);
        validParameters.add(PixiParameterType.LOC_ID);
        validParameters.add(PixiParameterType.OPEN_ORDER_LINES);
    }
    
    
    /**
     * Retrieves a XML Node from Pixi API web service containing a list of
     * <SOrderLine> tags accordingly to the given list of SOrderKey<br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#S_ORDER_REFERENCE}<br/>
     *            - {@link PixiParameterType#LOC_ID}<br/>
     *            - {@link PixiParameterType#OPEN_ORDER_LINES}<br/>
     * @return A XML Node containg the list of SOrderLine accordingly to the
     *         given SOrderKey list
     * @throws SOAPException
     *             in case errors occurred during the Pixi API request
     * @throws SOAPResponseErrorException
     *             in case errors ocurred during the Pixi API response
     * @author jfelipe
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    @Override
    public List<SOrderLineResult> importData(
            List<PixiFunctionParameter> functionParameters) {
        if (functionParameters == null || functionParameters.isEmpty()) {
            throw new InvalidParameterException(
                    "The PixiAPI function parameter should not be null.");
        }
        return importSOrderLines(functionParameters);
    }
    
    
    /**
     * Retrieves a XML Node from Pixi API web service containing a list of
     * <SOrderLine> tags accordingly to the given list of SOrderKey<br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#S_ORDER_REFERENCE}<br/>
     *            - {@link PixiParameterType#LOC_ID}<br/>
     *            - {@link PixiParameterType#OPEN_ORDER_LINES}<br/>
     * @return A XML Node containg the list of SOrderLine accordingly to the
     *         given SOrderKey list
     * @throws SOAPException
     *             in case errors occurred during the Pixi API request
     * @throws SOAPResponseErrorException
     *             in case errors ocurred during the Pixi API response
     * @author jfelipe
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    private List<SOrderLineResult> importSOrderLines(
            List<PixiFunctionParameter> functionParameters) {
        List<SOrderLineResult> results = new ArrayList<SOrderLineResult>();
        SOAPMessage message;
        SOAPMessage response;
        PixiFunctionParameter paramLocId = new PixiFunctionParameter();
        paramLocId.setType(PixiParameterType.LOC_ID);
        paramLocId.setValue("001");
        PixiFunctionParameter paramOpenOrderLines = new PixiFunctionParameter();
        paramOpenOrderLines.setType(PixiParameterType.OPEN_ORDER_LINES);
        paramOpenOrderLines.setValue(null);
        functionParameters.add(paramLocId);
        functionParameters.add(paramOpenOrderLines);
        checkValidParameters(functionParameters);
        try {
            message = getPixiSoapApi().buildMessage(PixiFunction.GET_S_ORDER_LINE,
                    functionParameters);
            response = getPixiSoapApi().sendPixiWebServiceRequest(message);
            if (response != null) {
                Node responseXml = response.getSOAPBody()
                        .getElementsByTagName("SqlRowSet1").item(0);
                if (responseXml != null) {
                    for (int i = 0; i < responseXml.getChildNodes().getLength(); i++) {
                        SOrderLineResult result = new SOrderLineResult();
                        Node rowTag = responseXml.getChildNodes().item(i);
                        for (int j = 0; j < rowTag.getChildNodes().getLength(); j++) {
                            Node currentTag = rowTag.getChildNodes().item(j);
                            String currentTagValue = currentTag.getTextContent()
                                    .trim();
                            switch (currentTag.getNodeName()) {
                                case DETAIL:
                                    int detail = Integer.parseInt(currentTagValue);
                                    result.setDetail(detail);
                                    break;
                                case DETAILS2:
                                    int detail2 = Integer.parseInt(currentTagValue);
                                    result.setDetail2(detail2);
                                    break;
                                case ARTICLE_NUMBER:
                                    result.setArticleNumber(currentTagValue);
                                    break;
                                case ARTICLE_NAME:
                                    result.setArticleName(currentTagValue);
                                    break;
                                case ORDER_QUANTITY:
                                    int orderQuantity = Integer
                                            .parseInt(currentTagValue);
                                    result.setOrderQuantity(orderQuantity);
                                    break;
                                case RECEIVE_DATE:
                                    Date receiveDate = parseStringToDate(currentTagValue);
                                    result.setReceiveDate(receiveDate);
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
                                case S_ORDER_LINE_KEY:
                                    int sOrderLineKey = Integer
                                            .parseInt(currentTagValue);
                                    result.setSOrderLineKey(sOrderLineKey);
                                    break;
                                case S_ITEM_REFERENCE:
                                    int sItemReference = Integer
                                            .parseInt(currentTagValue);
                                    result.setSItemReference(sItemReference);
                                    break;
                                case S_ORDER_REFERENCE:
                                    int sOrderReference = Integer
                                            .parseInt(currentTagValue);
                                    result.setSOrderReference(sOrderReference);
                                    break;
                                case ML_CONFIRM:
                                    boolean mlConfirm = Boolean
                                            .valueOf(currentTagValue).booleanValue();
                                    result.setMlConfirm(mlConfirm);
                                    break;
                                case S_ORDER_LINE_NUMBER:
                                    int sOrderLineNumber = Integer
                                            .parseInt(currentTagValue);
                                    result.setSOrderLineNumber(sOrderLineNumber);
                                    break;
                                case NOTE:
                                    result.setNote(currentTagValue);
                                    break;
                                case ESTIMATED_DELIVERY:
                                    Date estimatedDelivery = parseStringToDate(currentTagValue);
                                    result.setEstimatedDelivery(estimatedDelivery);
                                    break;
                                case VK_PRICE:
                                    double vkPrice = Double
                                            .parseDouble(currentTagValue);
                                    result.setVkPrice(vkPrice);
                                    break;
                                case SUMMARY_QUANTITY:
                                    int summaryQuantity = Integer
                                            .parseInt(currentTagValue);
                                    result.setSummaryQuantity(summaryQuantity);
                                    break;
                                case LOCATION_QUANTITY:
                                    int locationQuantity = Integer
                                            .parseInt(currentTagValue);
                                    result.setLocationQuantity(locationQuantity);
                                    break;
                                case QUANTITY_RECEIVED:
                                    int quantityReceived = Integer
                                            .parseInt(currentTagValue);
                                    result.setQuantityReceived(quantityReceived);
                                    break;
                                case QUANTITY_NOT_DELIVERED:
                                    int quantityNotDelivered = Integer
                                            .parseInt(currentTagValue);
                                    result.setQuantityNotDelivered(quantityNotDelivered);
                                    break;
                                case MIN_ORDER_QUANTITY:
                                    int minOrderQuantity = Integer
                                            .parseInt(currentTagValue);
                                    result.setMinOrderQuantity(minOrderQuantity);
                                    break;
                                case MIN_STOCK_QUANTITY:
                                    int minStockQuantity = Integer
                                            .parseInt(currentTagValue);
                                    result.setMinStockQuantity(minStockQuantity);
                                    break;
                                case ORDER_UNIT:
                                    result.setOrderUnit(currentTagValue);
                                    break;
                                case ITEM_NUMBER_SUPPLIER:
                                    result.setItemNumberSupplier(currentTagValue);
                                    break;
                                case ITEM_NUMBER_INTERNAL:
                                    result.setItemNumberInternal(currentTagValue);
                                    break;
                                case VAT_PERC:
                                    double vatPerc = Double
                                            .parseDouble(currentTagValue);
                                    result.setVatPerc(vatPerc);
                                    break;
                                case EK_PRICE:
                                    double ekPrice = Double
                                            .parseDouble(currentTagValue);
                                    result.setEkPrice(ekPrice);
                                    break;
                                case EK_VALUE:
                                    double ekValue = Double
                                            .parseDouble(currentTagValue);
                                    result.setEkValue(ekValue);
                                    break;
                                case HAL_CUST_O:
                                    int halCustO = Integer.parseInt(currentTagValue);
                                    result.setHalCustO(halCustO);
                                    break;
                                case OPEN_CUST_O:
                                    int openCustO = Integer.parseInt(currentTagValue);
                                    result.setOpenCustO(openCustO);
                                    break;
                                case OPEN_SUPPLIER_0:
                                    int openSupplierO = Integer
                                            .parseInt(currentTagValue);
                                    result.setOpenSupplO(openSupplierO);
                                    break;
                                case L_30:
                                    int l30 = Integer.parseInt(currentTagValue);
                                    result.setL30(l30);
                                    break;
                                case L_120:
                                    int l120 = Integer.parseInt(currentTagValue);
                                    result.setL120(l120);
                                    break;
                                case L_365:
                                    int l365 = Integer.parseInt(currentTagValue);
                                    result.setL365(l365);
                                    break;
                                case STOCK_QUANTITY:
                                    int stockQuantity = Integer
                                            .parseInt(currentTagValue);
                                    result.setStockQuantity(stockQuantity);
                                    break;
                                case PRICE:
                                    double price = Double.parseDouble(currentTagValue);
                                    result.setPrice(price);
                                    break;
                            }
                        }
                        results.add(result);
                    }
                }
            }
        } catch (KeyManagementException | NoSuchAlgorithmException | SOAPException e) {
            // Nothing to do in this case...
        }
        return results;
    }
}
