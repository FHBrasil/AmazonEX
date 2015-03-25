package com.pixi.api.importers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pixi.api.PixiSOAPAPI;
import com.pixi.api.impl.DefaultPixiSoapApi;
import com.pixi.api.importers.impl.SupplierDeliveryDaysPixiApiImporter;

/**
 * @author jfelipe
 *
 */
public class SupplierDeliveryDaysPixiApiImporterTest {

    // Test Object
    SupplierDeliveryDaysPixiApiImporter fixture;


    /**
     * Response SOAP Message: {@code
     * <SqlRowSet1>
     *   <row>
     *     ...
     *     <SupplNr>SUPPLIER_CODE</SupplNr>
     *     <DefaultDeliveryDays>DEFAULT_DELIVERY_DAYS</DefaultDeliveryDays>
     *     ...
     *   </row>
     * </SqlRowSet1>
     * }
     *
     * @author jfelipe
     */
    @Before
    public void setUp() {
        fixture = new SupplierDeliveryDaysPixiApiImporter();
        fixture.setDefaultPixiSoapApi(new DefaultPixiSoapApi());
    }


    /**
     *
     * @author jfelipe
     */
    @Test
    public void importIntegerTest() {
        int result = fixture.importInteger("HVHA");
        Assert.assertTrue("Result should be greater than -1", result > -1);
    }
}
