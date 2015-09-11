package com.pixi.api.importers.impl;

import java.security.InvalidParameterException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.pixi.api.result.ItemStockResult;

/**
 * Instantiate this class you should not! See {@link com.pixi.api.PixiApiFactory} you must. <br/>
 * <br/>
 * 
 * @author jfelipe
 */
public class ChangedItemStockPixiApiImporter extends AbstractPixiApiImporter {
    
    private static final Logger LOG = Logger
            .getLogger(ChangedItemStockPixiApiImporterTest.class);
    //
    private static final int ROWS_TO_RETRIEVE = 20000;
    //
    private static final String ITEM_KEY = "ItemKey";
    private static final String ITEM_NUMBER_INTERNAL = "ItemNrInt";
    private static final String EANUPC = "EANUPC";
    private static final String PHYSICAL_STOCK = "PhysicalStock";
    private static final String AVAILABLE_STOCK = "AvailableStock";
    
    
    /**
     * 
     */
    public ChangedItemStockPixiApiImporter() {
        super();
        validParameters.add(PixiParameterType.SINCE_DATE);
        validParameters.add(PixiParameterType.ROW_COUNT);
    }
    
    
    /**
     * Imports the changed stock items from Pixi <br/>
     * 
     * @param functionParameters
     *            parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#SINCE_DATE}<br/>
     * @return A Set of {@link String} containig the results from Pixi API
     * @author jfelipe
     */
    @Override
    public Set<ItemStockResult> importData(List<PixiFunctionParameter> functionParameters) {
        if (functionParameters == null || functionParameters.isEmpty()) {
            throw new InvalidParameterException(
                    "The PixiAPI function parameter should not be null.");
        }
        return importChangedItemStock(functionParameters);
    }
    
    
    /**
     * Imports the changed stock items from Pixi. <br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#SINCE_DATE}<br/>
     *            - Other needed parameters are added inside this method <br/>
     * @return A Set of {@link String} containig the results from Pixi API
     * @throws SOAPException
     *             in case errors occurred during the Pixi API request
     * @throws SOAPResponseErrorException
     *             in case errors ocurred during the Pixi API response
     * @author jfelipe
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    private Set<ItemStockResult> importChangedItemStock(
            List<PixiFunctionParameter> functionParameters) {
        Set<ItemStockResult> results = new HashSet<ItemStockResult>();
        SOAPMessage request = null;
        SOAPMessage response = null;
        NodeList responseXml = null;
        PixiFunctionParameter rowCountParam = new PixiFunctionParameter();
        rowCountParam.setType(PixiParameterType.ROW_COUNT);
        rowCountParam.setValue(String.valueOf(ROWS_TO_RETRIEVE));
        functionParameters.add(rowCountParam);
        checkValidParameters(functionParameters);
        try {
            request = getPixiSoapApi().buildMessage(
                    PixiFunction.GET_CHANGED_ITEM_STOCK, functionParameters);
            response = getPixiSoapApi().sendPixiWebServiceRequest(request);
            responseXml = response.getSOAPBody().getElementsByTagName(
                    PixiApiResponseTags.SQLROWSET1.getValue());
            Node sqlRowSetTag = responseXml != null ? responseXml.item(0) : null;
            if (sqlRowSetTag != null) {
                for (int i = 0; i < sqlRowSetTag.getChildNodes().getLength(); i++) {
                    Node row = sqlRowSetTag.getChildNodes().item(i);
                    ItemStockResult result = new ItemStockResult();
                    for (int j = 0; j < row.getChildNodes().getLength(); j++) {
                        Node currentTag = row.getChildNodes().item(j);
                        String currentTagValue = currentTag.getTextContent().trim();
                        switch (currentTag.getNodeName()) {
                            case ITEM_KEY:
                                int itemKey = Integer.parseInt(currentTagValue);
                                result.setItemKey(itemKey);
                                break;
                            case ITEM_NUMBER_INTERNAL:
                                result.setItemNrInt(currentTagValue);
                                break;
                            case EANUPC:
                                result.setEanUpc(currentTagValue);
                                break;
                            case PHYSICAL_STOCK:
                                int physicalStock = Integer.parseInt(currentTagValue);
                                result.setPhysicalStock(physicalStock);
                                break;
                            case AVAILABLE_STOCK:
                                int availableStock = Integer.parseInt(currentTagValue);
                                result.setAvailableStock(availableStock);
                                break;
                        }
                    }
                    results.add(result);
                }
            }
        } catch (KeyManagementException | NoSuchAlgorithmException | SOAPException e) {
            // Nothing to do in this case...
        }
        return results;
    }
}
