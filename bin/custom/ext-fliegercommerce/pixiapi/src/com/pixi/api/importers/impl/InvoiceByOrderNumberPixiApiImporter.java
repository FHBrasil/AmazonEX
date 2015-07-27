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
import com.pixi.api.result.InvoiceResult;

/**
 * Instantiate this class you should not! See {@link com.pixi.api.PixiApiFactory} you must. <br/>
 * <br/>
 * 
 * @author jfelipe
 */
public class InvoiceByOrderNumberPixiApiImporter extends
        AbstractPixiApiImporter {
    
    private static final Logger LOG = Logger
            .getLogger(InvoicePixiApiImporter.class);
    public static final String PAYMENT_ADDRESS_ID = "PaymentAddressId";
    public static final String SHIPMENT_ADDRESS_ID = "ShipmentAddressId";
    public static final String TOTAL = "Total";
    public static final String PAYMENT_CODE = "PaymentCode";
    public static final String PAYMENT_TEXT = "PaymentText";
    public static final String INVOICE_DATE = "InvoiceDate";
    public static final String PAYMENT_DATE = "PaymentDate";
    public static final String PAID = "Paid";
    public static final String TOTAL_TO_PAY = "TotalToPay";
    public static final String PAID_SUM = "paidSum";
    public static final String CC_CARD_TYPE = "CcCardType";
    public static final String CC_CARD_NUMBER = "CcCardNr";
    public static final String CC_CARD_NAME = "CcCardName";
    public static final String CC_CARD_EXP = "CcCardExp";
    public static final String ACCOUNT = "Account";
    public static final String BLZ = "Blz";
    public static final String BANK_NAME = "BankName";
    public static final String ACCOUNT_NAME = "AccountName";
    public static final String SHIP_DATE = "ShipDate";
    public static final String ORDER_NUMBER = "OrderNr";
    public static final String ORDER_NUMBER_EXTERNAL = "OrderNrExternal";
    public static final String TRACKING_ID = "TrackingID";
    
    
    /**
     * 
     */
    public InvoiceByOrderNumberPixiApiImporter() {
        super();
        validParameters.add(PixiParameterType.ORDER_NUMBER);
    }
    
    
    /**
     * Imports the invoices from Pixi. <br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#ORDER_NUMBER}<br/>
     *            - Other needed parameters are added inside this method <br/>
     * @return A List of {@link InvoiceResult} containig the results from Pixi
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
    public List<InvoiceResult> importData(List<PixiFunctionParameter> functionParameters) {
        if (functionParameters == null || functionParameters.isEmpty()) {
            throw new InvalidParameterException(
                    "The PixiAPI function parameter should not be null.");
        }
        return importInvoiceByOrderNumber(functionParameters);
    }
    
    
    /**
     * Imports the invoicess from Pixi. <br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#ORDER_NUMBER}<br/>
     *            - Other needed parameters are added inside this method <br/>
     * @return A List of {@link InvoiceResult} containig the results from Pixi
     *         API
     * @throws SOAPException
     *             in case errors occurred during the Pixi API request
     * @throws SOAPResponseErrorException
     *             in case errors ocurred during the Pixi API response
     * @author jfelipe
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    private List<InvoiceResult> importInvoiceByOrderNumber(
            List<PixiFunctionParameter> functionParameters) {
        List<InvoiceResult> results = new ArrayList<InvoiceResult>();
        checkValidParameters(functionParameters);
        SOAPMessage request;
        try {
            request = getPixiSoapApi().buildMessage(
                    PixiFunction.GET_INVOICE_BY_ORDER_NUMBER, functionParameters);
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
                        InvoiceResult result = new InvoiceResult();
                        for (int j = 0; j < row.getChildNodes().getLength(); j++) {
                            Node currentTag = row.getChildNodes().item(j);
                            String currentTagValue = currentTag.getTextContent()
                                    .trim();
                            switch (currentTag.getNodeName()) {
                                case PAYMENT_ADDRESS_ID:
                                    result.setPaymentAddressId(currentTagValue);
                                    break;
                                case SHIPMENT_ADDRESS_ID:
                                    result.setShipmentAddressId(currentTagValue);
                                    break;
                                case TOTAL:
                                    double total = Double.parseDouble(currentTagValue);
                                    result.setTotal(total);
                                    break;
                                case PAYMENT_CODE:
                                    result.setPaymentCode(currentTagValue);
                                    break;
                                case PAYMENT_TEXT:
                                    result.setPaymentText(currentTagValue);
                                    break;
                                case INVOICE_DATE:
                                    Date invoiceDate = parseStringToDate(currentTagValue);
                                    result.setInvoiceDate(invoiceDate);
                                    break;
                                case PAYMENT_DATE:
                                    Date paymentDate = parseStringToDate(currentTagValue);
                                    result.setPaymentDate(paymentDate);
                                    break;
                                case PAID:
                                    result.setPaid(currentTagValue);
                                    break;
                                case TOTAL_TO_PAY:
                                    double totalToPay = Double
                                            .parseDouble(currentTagValue);
                                    result.setTotalToPay(totalToPay);
                                    break;
                                case PAID_SUM:
                                    double paidSum = Double
                                            .parseDouble(currentTagValue);
                                    result.setPaidSum(paidSum);
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
                                case SHIP_DATE:
                                    Date shipDate = parseStringToDate(currentTagValue);
                                    result.setShipDate(shipDate);
                                    break;
                                case ORDER_NUMBER:
                                    result.setOrderNr(currentTagValue);
                                    break;
                                case ORDER_NUMBER_EXTERNAL:
                                    result.setOrderNrExternal(currentTagValue);
                                    break;
                                case TRACKING_ID:
                                    result.setTrackingId(currentTagValue);
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
