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
import com.pixi.api.result.ItemStockBinsResult;

/**
 * Instantiate this class you should not! See {@link com.pixi.api.PixiApiFactory} you
 * must. <br/>
 * <br/>
 * 
 * @author jfelipe
 */
public class ItemStockBinsPixiApiImporter extends AbstractPixiApiImporter {

    private static final Logger LOG = Logger
            .getLogger(ItemStockBinsPixiApiImporter.class);
    //
    private static final String BIN_NAME = "BinName";
    private static final String QUANTITY = "Quantity";
    private static final String BIN_SORT_NUM = "BinSortNum";
    private static final String EAN = "EAN";
    private static final String ITEM_NUMBER_INT = "ItemNRInt";


    /**
     * 
     */
    public ItemStockBinsPixiApiImporter() {
        super();
        validParameters.add(PixiParameterType.ITEM_KEY);
        validParameters.add(PixiParameterType.EAN);
        validParameters.add(PixiParameterType.ITEM_SUPPLIER_NUMBER);
        validParameters.add(PixiParameterType.ITEM_NUMBER_INTERNAL);
    }


    /**
     * Imports the stock bins from Pixi. <br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#ITEM_KEY}<br/>
     *            - {@link PixiParameterType#EAN}<br/>
     *            - {@link PixiParameterType#ITEM_SUPPLIER_NUMBER}<br/>
     *            - {@link PixiParameterType#ITEM_NUMBER_INTERNAL}<br/>
     * @return A List of {@link ItemInfoResult} containing the results from Pixi API
     * @throws SOAPException
     *             in case errors occurred during the Pixi API request
     * @throws SOAPResponseErrorException
     *             in case errors ocurred during the Pixi API response
     * @author jfelipe
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    @Override
    public List<ItemStockBinsResult> importData(
            List<PixiFunctionParameter> functionParameters)
            throws SOAPResponseErrorException, SOAPException,
            KeyManagementException, NoSuchAlgorithmException {
        if (functionParameters == null || functionParameters.isEmpty()) {
            throw new InvalidParameterException(
                    "The PixiAPI function parameter should not be null.");
        }
        return importItemStockBinsResult(functionParameters);
    }


    /**
     * Imports the stock bins from Pixi. <br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#ITEM_KEY}<br/>
     *            - {@link PixiParameterType#EAN}<br/>
     *            - {@link PixiParameterType#ITEM_SUPPLIER_NUMBER}<br/>
     *            - {@link PixiParameterType#ITEM_NUMBER_INTERNAL}<br/>
     * @return A List of {@link ItemInfoResult} containing the results from Pixi API
     * @throws SOAPException
     *             in case errors occurred during the Pixi API request
     * @throws SOAPResponseErrorException
     *             in case errors ocurred during the Pixi API response
     * @author jfelipe
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    private List<ItemStockBinsResult> importItemStockBinsResult(
            List<PixiFunctionParameter> functionParameters)
            throws SOAPResponseErrorException, SOAPException,
            KeyManagementException, NoSuchAlgorithmException {
        List<ItemStockBinsResult> results = new ArrayList<ItemStockBinsResult>();
        checkValidParameters(functionParameters);
        SOAPMessage request = getPixiSoapApi().buildMessage(
                PixiFunction.GET_ITEM_STOCK_BINS, functionParameters);
        SOAPMessage response = getPixiSoapApi().sendPixiWebServiceRequest(
                request);
        if (response != null) {
            Node responseXml = null;
            responseXml = response
                    .getSOAPBody()
                    .getElementsByTagName(
                            PixiApiResponseTags.SQLROWSET1.getValue()).item(0);
            if (responseXml != null) {
                for (int i = 0; i < responseXml.getChildNodes().getLength(); i++) {
                    Node row = responseXml.getChildNodes().item(i);
                    ItemStockBinsResult result = new ItemStockBinsResult();
                    for (int j = 0; j < row.getChildNodes().getLength(); j++) {
                        Node currentTag = row.getChildNodes().item(j);
                        String currentTagValue = currentTag.getTextContent()
                                .trim();
                        switch (currentTag.getNodeName()) {
                            case BIN_NAME:
                                result.setBinName(currentTagValue);
                                break;
                            case QUANTITY:
                                int quantity = Integer.parseInt(currentTagValue);
                                result.setQuantity(quantity);
                                break;
                            case BIN_SORT_NUM:
                                int binSortNum = Integer.parseInt(currentTagValue);
                                result.setBinSortNum(binSortNum);
                                break;
                            case EAN:
                                result.setEan(currentTagValue);
                                break;
                            case ITEM_NUMBER_INT:
                                result.setItemNrInt(currentTagValue);
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
