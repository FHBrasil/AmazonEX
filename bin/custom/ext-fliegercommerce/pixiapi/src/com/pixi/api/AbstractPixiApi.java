package com.pixi.api;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.pixi.api.core.PixiFunctionParameter;
import com.pixi.api.core.PixiParameterType;

/**
 * 
 * 
 * @author jfelipe
 */
public abstract class AbstractPixiApi {

    private static final Logger LOG = Logger.getLogger(AbstractPixiApi.class);
    protected final List<PixiParameterType> validParameters = new ArrayList<PixiParameterType>();
    private PixiSOAPAPI pixiSoapApi;


    /**
     * Default Constructor
     */
    public AbstractPixiApi() {
        //
    }


    /**
     * Parses a given string to a {@link java.util.Date} object.
     * 
     * @param dateString
     *            a string containing the date to be parsed.
     * @return
     *         A {@link java.util.Date} object.
     * @author jfelipe
     */
    protected Date parseStringToDate(final String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateToParse = dateString.replaceAll("T", " ");
        Date parsedDate = null;
        try {
            parsedDate = formatter.parse(dateToParse);
        } catch (ParseException pe) {
            LOG.error("Could not parse date: " + dateString, pe);
        }
        return parsedDate;
    }


    /**
     * Check if the right parameters are being passed to the Pixi API function,
     * according to the {@link AbstractPixiApi#validParameters} list.
     * 
     * @param parameters
     *            the list parameters being passed to the Pixi API function
     * @throws InvalidParameterException
     *             in case a invalid parameter is passed
     * @author jfelipe
     */
    protected void checkValidParameters(List<PixiFunctionParameter> parameters)
            throws InvalidParameterException {
        for (PixiFunctionParameter p : parameters) {
            if (!validParameters.contains(p.getType())) {
                throw new InvalidParameterException("The given PixiFunctionParameter list has an "
                        + "invalid parameters: " + p.getType().getValue());
            }
        }
    }


    /**
     * @return the defaultPixiSoapApi
     */
    public PixiSOAPAPI getPixiSoapApi() {
        return pixiSoapApi;
    }


    /**
     * @param pixiSoapApi
     *            the defaultPixiSoapApi to set
     */
    public void setPixiSoapApi(PixiSOAPAPI pixiSoapApi) {
        this.pixiSoapApi = pixiSoapApi;
    }
}
