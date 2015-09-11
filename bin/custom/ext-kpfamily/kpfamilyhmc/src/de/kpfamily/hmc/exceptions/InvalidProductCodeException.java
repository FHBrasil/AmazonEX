package de.kpfamily.hmc.exceptions;

import de.hybris.platform.servicelayer.interceptor.Interceptor;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;

/**
 * @author jfelipe
 *
 */
public class InvalidProductCodeException extends InterceptorException {

    private Interceptor interceptor;


    /**
     * 
     * @param message
     */
    public InvalidProductCodeException(String message) {
        this(message, null, null);
    }


    /**
     * 
     * @param message
     * @param cause
     */
    public InvalidProductCodeException(String message, Throwable cause) {
        this(message, cause, null);
    }


    /**
     * 
     * @param message
     * @param inter
     */
    public InvalidProductCodeException(String message, Interceptor inter) {
        this(message, null, inter);
    }


    /**
     * 
     * @param message
     * @param cause
     * @param inter
     */
    public InvalidProductCodeException(String message, Throwable cause, Interceptor inter) {
        super(message, cause);
        setInterceptor(inter);
    }

}
