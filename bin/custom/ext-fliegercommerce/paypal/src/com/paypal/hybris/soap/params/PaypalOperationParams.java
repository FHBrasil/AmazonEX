package com.paypal.hybris.soap.params;


import java.util.List;
import java.util.Map;

import com.paypal.hybris.soap.gen.AbstractRequestType;
import com.paypal.hybris.soap.gen.AbstractResponseType;
import com.paypal.hybris.soap.gen.ErrorType;


/**
 * Stores PayPal operation input/output parameters, as well as full request and
 * response.
 * 
 * @author Valentyn Markovych, Gorilla
 * 
 */
public interface PaypalOperationParams<TI extends AbstractRequestType, TO extends AbstractResponseType> {


public String getOperationName();


/**
 * Serializes full object to JSON
 * 
 * @return Full object as JSON string
 */
public String toJson();


public TI getRequest();


public String getRequestAsJson();


public void setRequest(TI paypalRequest);


public TO getResponse();


public String getResponseAsJson();


public void setResponse(TO paypalResponse);


/**
 * Set parameters set from a given map.
 * 
 * @param params
 */
public void setParamsFromMap(Map<String, String[]> params);


public String getRequestParam(String key);


public String getResponseParam(String key);


/**
 * Returns "ack" field from the response. Usually "SUCCESS" or "FAILURE"
 * 
 * @return "ack" field from the response as string.
 */
public String getAck();


public List<ErrorType> getErrors();


}
