package com.pixi.api.importers.impl;

import java.security.InvalidParameterException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
import com.pixi.api.result.ItemInfoResult;

/**
 * Instantiate this class you should not! See {@link com.pixi.api.PixiApiFactory} you must. <br/>
 * <br/>
 * 
 * @author jfelipe
 */
public class ItemInfoPixiApiImporter extends AbstractPixiApiImporter {
    
    private static final Logger LOG = Logger
            .getLogger(ItemInfoPixiApiImporter.class);
    //
    private static final String ITEM_KEY = "ItemKey";
    private static final String SUPPLIER_PRICE = "SupplPrice";
    private static final String ITEM_NAME = "ItemName";
    private static final String VPE = "VPE";
    private static final String MIN_STOCK_QUANTITY = "MinStockQty";
    private static final String MIN_ORDER_QUANTITY = "MinOrderQty";
    private static final String SUPPLIER_NUMBER = "SupplNR";
    private static final String CATEGORY = "Category";
    private static final String ITEM_NUMBER_INTERNAL = "ItemNrInt";
    private static final String ITEM_NUMBER_SUPPLIER = "ItemNrSuppl";
    private static final String QUANTITY = "Quantity";
    private static final String PRICE_VK = "PriceVK";
    private static final String VAT_LEVEL = "VATLevel";
    
    
    /**
     * 
     */
    public ItemInfoPixiApiImporter() {
        super();
        validParameters.add(PixiParameterType.ITEM_KEY);
        validParameters.add(PixiParameterType.ITEM_NUMBER_INTERNAL);
        validParameters.add(PixiParameterType.EAN_UPC);
        validParameters.add(PixiParameterType.ITEM_SUPPLIER_NUMBER);
    }
    
    
    /**
     * Imports the item info from Pixi. <br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#ITEM_KEY}<br/>
     *            - {@link PixiParameterType#ITEM_NUMBER_INTERNAL}<br/>
     *            - {@link PixiParameterType#EAN_UPC}<br/>
     *            - {@link PixiParameterType#ITEM_SUPPLIER_NUMBER}<br/>
     * @return A List of {@link ItemInfoResult} containing the results from Pixi
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
    public List<ItemInfoResult> importData(
            List<PixiFunctionParameter> functionParameters) {
        if (functionParameters == null || functionParameters.isEmpty()) {
            throw new InvalidParameterException(
                    "The PixiAPI function parameter should not be null.");
        }
        return importItemInfos(functionParameters);
    }
    
    
    /**
     * Imports the item info from Pixi. <br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#INVOICE_NUMBER}<br/>
     * @return A List of {@link ItemInfoResult} containing the results from Pixi
     *         API
     * @throws SOAPException
     *             in case errors occurred during the Pixi API request
     * @throws SOAPResponseErrorException
     *             in case errors ocurred during the Pixi API response
     * @author jfelipe
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    private List<ItemInfoResult> importItemInfos(
            List<PixiFunctionParameter> functionParameters) {
        List<ItemInfoResult> results = new ArrayList<ItemInfoResult>();
        checkValidParameters(functionParameters);
        SOAPMessage request;
        try {
            request = getPixiSoapApi().buildMessage(
                    PixiFunction.GET_ITEM_INFO, functionParameters);
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
                        ItemInfoResult result = new ItemInfoResult();
                        for (int j = 0; j < rowTag.getChildNodes().getLength(); j++) {
                            Node currentTag = rowTag.getChildNodes().item(j);
                            String currentTagValue = currentTag.getTextContent()
                                    .trim();
                            switch (currentTag.getNodeName()) {
                                case ITEM_KEY:
                                    int itemKey = Integer.parseInt(currentTagValue);
                                    result.setItemKey(itemKey);
                                    break;
                                case SUPPLIER_PRICE:
                                    double supplierPrice = Double
                                            .parseDouble(currentTagValue);
                                    result.setSupplPrice(supplierPrice);
                                    break;
                                case ITEM_NAME:
                                    result.setItemName(currentTagValue);
                                    break;
                                case VPE:
                                    int vpe = Integer.parseInt(currentTagValue);
                                    result.setVpe(vpe);
                                    break;
                                case MIN_STOCK_QUANTITY:
                                    int minStockQuantity = Integer
                                            .parseInt(currentTagValue);
                                    result.setMinStockQty(minStockQuantity);
                                    break;
                                case MIN_ORDER_QUANTITY:
                                    int minOrderQuantity = Integer
                                            .parseInt(currentTagValue);
                                    result.setMinOrderQty(minOrderQuantity);
                                    break;
                                case SUPPLIER_NUMBER:
                                    result.setSupplNr(currentTagValue);
                                    break;
                                case CATEGORY:
                                    result.setCategory(currentTagValue);
                                    break;
                                case ITEM_NUMBER_INTERNAL:
                                    result.setItemNrInt(currentTagValue);
                                    break;
                                case ITEM_NUMBER_SUPPLIER:
                                    result.setItemNrSuppl(currentTagValue);
                                    break;
                                case QUANTITY:
                                    int quantity = Integer.parseInt(currentTagValue);
                                    result.setQuantity(quantity);
                                    break;
                                case PRICE_VK:
                                    double priceVk = Double
                                            .parseDouble(currentTagValue);
                                    result.setPriceVk(priceVk);
                                    break;
                                case VAT_LEVEL:
                                    result.setVatLevel(currentTagValue);
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
