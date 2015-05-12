/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package com.flieger.payment;

import de.hybris.platform.core.Registry;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.util.RedeployUtilities;
import de.hybris.platform.util.Utilities;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


/**
 * Demonstration of how to write a standalone application that can be run directly from within eclipse or from the
 * commandline.<br>
 * To run this from commandline, just use the following command:<br>
 * <code>
 * java -jar bootstrap/bin/ybootstrap.jar "new com.flieger.payment.MundipaggStandalone().run();"
 * </code> From eclipse, just run as Java Application. Note that you maybe need to add all other projects like
 * ext-commerce, ext-pim to the Launch configuration classpath.
 */
public class MundipaggStandalone
{
	 public static Double fatorial(Double num) {
 
 
        /**
         * Este é o caso base, se o número passado por parametro for 0 ou 1,
         * ele retorna o resultado 1 e finaliza o método.
         */
        if (num <= 1) {
 
            return new Double(1);
 
        } else if (num.toString().length() == 10) {
 
            return num;
 
        } else {
 
            return fatorial(num - 1) * num;
 
        }
 
    }
 
    public static void main(String[] args) {   
	
	// teste do programa. Imprime os 30 primeiros termos       
	Double banana = MundipaggStandalone.fatorial(new Double("0200223322"));
        System.out.print(banana.toString());
    }


}
