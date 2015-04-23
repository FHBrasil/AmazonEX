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
import com.pixi.api.result.InvoiceLineResult;

/**
 * Instantiate this class you should not! See
 * {@link com.pixi.api.PixiApiFactory} you must. <br/>
 * <br/>
 * 
 * @author jfelipe
 */
public class InvoiceLinesPixiApiImporter extends AbstractPixiApiImporter {

	private static final Logger LOG = Logger
			.getLogger(InvoiceLinesPixiApiImporter.class);
	//
	private static final String INVOICE_LINE_KEY = "InvoiceLineKey";
	private static final String ORDER_LINE_KEY = "OrderLineKey";
	private static final String ARTICLE_NUMBER = "ArtNr";
	private static final String ARTICLE_NAME = "ArtName";
	private static final String ITEM_TEXT = "ItemText";
	private static final String ORDER_QUANTITY = "OrderQty";
	private static final String ITEM_QUANTITY = "ItemQty";
	private static final String ITEM_PRICE = "ItemPrice";
	private static final String SHIPPING_COST = "ShipCost";
	private static final String ITEM_VAT = "ItemVAT";
	private static final String ITEM_NUMBER = "ItemNr";
	private static final String ITEM_NUMBER_SUPPLIER = "ItemNrSuppl";
	private static final String EAN_UPC = "EanUpc";
	private static final String ORDER_NUMBER = "OrderNr";
	private static final String ORDER_NUMBER_EXTERNAL = "OrderNrExternal";
	private static final String STATUS = "Status";
	private static final String ITEM_PRICE_NO_VAT = "ItemPriceNoVAT";
	private static final String FULL_PRICE = "FullPrice";
	private static final String DISCOUNT_VALUE = "DiscountValue";
	private static final String DISCOUNT_PERC = "DiscountPerc";
	private static final String ITEM_NUMBER_EXTERNAL = "ItemNrExtenal";
	private static final String ITEM_NUMBER_INTERNAL = "ItemNrInt";

	/**
     * 
     */
	public InvoiceLinesPixiApiImporter() {
		super();
		validParameters.add(PixiParameterType.INVOICE_NUMBER);
	}

	/**
	 * Imports the invoice lines from Pixi. <br/>
	 * 
	 * @param functionParameters
	 *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
	 *            - {@link PixiParameterType#INVOICE_NUMBER}<br/>
	 * @return A Set of {@link String} containing the results from Pixi API
	 * @throws SOAPException
	 *             in case errors occurred during the Pixi API request
	 * @throws SOAPResponseErrorException
	 *             in case errors ocurred during the Pixi API response
	 *
	 * @author jfelipe
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	@Override
	public List<InvoiceLineResult> importData(
			List<PixiFunctionParameter> functionParameters)
			throws SOAPResponseErrorException, SOAPException,
			KeyManagementException, NoSuchAlgorithmException {
		if (functionParameters == null || functionParameters.isEmpty()) {
			throw new InvalidParameterException(
					"The PixiAPI function parameter should not be null.");
		}
		return importInvoiceLines(functionParameters);
	}

	/**
	 * Imports the invoice lines from Pixi. <br/>
	 * 
	 * @param functionParameters
	 *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
	 *            - {@link PixiParameterType#INVOICE_NUMBER}<br/>
	 * @return A Set of {@link String} containing the results from Pixi API
	 * @throws SOAPException
	 *             in case errors occurred during the Pixi API request
	 * @throws SOAPResponseErrorException
	 *             in case errors ocurred during the Pixi API response
	 *
	 * @author jfelipe
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	private List<InvoiceLineResult> importInvoiceLines(
			List<PixiFunctionParameter> functionParameters)
			throws SOAPResponseErrorException, SOAPException,
			KeyManagementException, NoSuchAlgorithmException {
		List<InvoiceLineResult> results = new ArrayList<InvoiceLineResult>();
		checkValidParameters(functionParameters);
		SOAPMessage request = getPixiSoapApi().buildMessage(
				PixiFunction.GET_INVOICE_LINES, functionParameters);
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
					Node row = responseXml.getChildNodes().item(0);
					InvoiceLineResult result = new InvoiceLineResult();
					for (int j = 0; j < row.getChildNodes().getLength(); j++) {
						Node currentTag = row.getChildNodes().item(j);
						switch (currentTag.getNodeName()) {
						case INVOICE_LINE_KEY:
							result.setInvoiceLineKey(Integer
									.parseInt(currentTag.getTextContent()
											.trim()));
							break;
						case ORDER_LINE_KEY:
							result.setOrderLineKey(Integer.parseInt(currentTag
									.getTextContent().trim()));
							break;
						case ARTICLE_NUMBER:
							result.setArtNr(currentTag.getTextContent().trim());
							break;
						case ARTICLE_NAME:
							result.setArtName(currentTag.getTextContent()
									.trim());
							break;
						case ITEM_TEXT:
							result.setItemText(currentTag.getTextContent()
									.trim());
							break;
						case ORDER_QUANTITY:
							result.setOrderQty(Integer.parseInt(currentTag
									.getTextContent().trim()));
							break;
						case ITEM_QUANTITY:
							result.setItemQty(Integer.parseInt(currentTag
									.getTextContent().trim()));
							break;
						case ITEM_PRICE:
							result.setItemPrice(Double.parseDouble(currentTag
									.getTextContent().trim()));
							break;
						case SHIPPING_COST:
							result.setShipCost(Double.parseDouble(currentTag
									.getTextContent().trim()));
							break;
						case ITEM_VAT:
							result.setItemVAT(currentTag.getTextContent()
									.trim());
							break;
						case ITEM_NUMBER:
							result.setItemNr(currentTag.getTextContent().trim());
							break;
						case ITEM_NUMBER_SUPPLIER:
							result.setItemNrSuppl(currentTag.getTextContent()
									.trim());
							break;
						case EAN_UPC:
							result.setEanUpc(currentTag.getTextContent().trim());
							break;
						case ORDER_NUMBER:
							result.setOrderNr(currentTag.getTextContent()
									.trim());
							break;
						case ORDER_NUMBER_EXTERNAL:
							result.setOrderNrExternal(currentTag
									.getTextContent().trim());
							break;
						case STATUS:
							result.setStatus(currentTag.getTextContent().trim());
							break;
						case ITEM_PRICE_NO_VAT:
							result.setItemPriceNoVAT(Double
									.parseDouble(currentTag.getTextContent()
											.trim()));
							break;
						case FULL_PRICE:
							result.setFullPrice(Double.parseDouble(currentTag
									.getTextContent().trim()));
							break;
						case DISCOUNT_VALUE:
							result.setDiscountValue(Double
									.parseDouble(currentTag.getTextContent()
											.trim()));
							break;
						case DISCOUNT_PERC:
							result.setDiscountPerc(Double
									.parseDouble(currentTag.getTextContent()
											.trim()));
							break;
						case ITEM_NUMBER_EXTERNAL:
							result.setItemNrExtenal(currentTag.getTextContent()
									.trim());
							break;
						case ITEM_NUMBER_INTERNAL:
							result.setItemNrInt(currentTag.getTextContent()
									.trim());
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
