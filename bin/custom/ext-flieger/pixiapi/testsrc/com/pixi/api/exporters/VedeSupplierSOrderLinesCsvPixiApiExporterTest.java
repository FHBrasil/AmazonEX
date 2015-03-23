/**
 * 
 */
package com.pixi.api.exporters;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.net.MalformedURLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.pixi.api.exporters.impl.VedeSupplierSOrderLinesCsvPixiAPIExporter;
import com.pixi.api.importers.impl.SOrderLineTagPixiApiImporter;

/**
 * @author jfelipe
 *
 */
public class VedeSupplierSOrderLinesCsvPixiApiExporterTest {

    // Test Object
    private VedeSupplierSOrderLinesCsvPixiAPIExporter fixture;
    // Models
    // DAOs
    // Services
    // Controllers
    // Misc
    private SOrderLineTagPixiApiImporter sOrderLineTagPixiApiImporter;
    private Element xmlSOrderKeys;
    private Element xmlSOrderLines;


    // Interceptors
    // @Mock
    // private InterceptorContext interceptorContext;
    // Rules
    // @Rule
    // public ExpectedException expectedException = ExpectedException.none();
    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Before
    public void setUp() {
        fixture = new VedeSupplierSOrderLinesCsvPixiAPIExporter();
        //
        sOrderLineTagPixiApiImporter = mock(SOrderLineTagPixiApiImporter.class);
        fixture.setsOrderLineTagPixiApiImporter(sOrderLineTagPixiApiImporter);
        // Initialing dummy objects
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder;
            documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            xmlSOrderKeys = document.createElement("SqlRowSet1");
            Element row = document.createElement("row");
            xmlSOrderKeys.appendChild(row);
            Element orderNr = document.createElement("OrderNr");
            orderNr.setTextContent("70014960");
            row.appendChild(orderNr);
            Element supplrNr = document.createElement("SupplNr");
            supplrNr.setTextContent("VEDE");
            row.appendChild(supplrNr);
            Element supplrGroup = document.createElement("SupplrGroup");
            supplrGroup.setTextContent("Vede");
            row.appendChild(supplrGroup);
            Element orderDate = document.createElement("OrderDate");
            orderDate.setTextContent("2015-03-21T16:11:43.533");
            row.appendChild(orderDate);
            Element status = document.createElement("Status");
            status.setTextContent("CLS");
            row.appendChild(status);
            Element createDate = document.createElement("CreateDate");
            createDate.setTextContent("2015-03-21T15:31:04.573");
            row.appendChild(createDate);
            Element createEmp = document.createElement("CreateEmp");
            createEmp.setTextContent("Voigt");
            row.appendChild(createEmp);
            Element updateDate = document.createElement("UpdateDate");
            updateDate.setTextContent("2015-03-21T10:28:13.973");
            row.appendChild(updateDate);
            Element updateEmp = document.createElement("UpdateEmp");
            updateEmp.setTextContent("Voigt");
            row.appendChild(updateEmp);
            Element sOrderLocation = document.createElement("SOrderLocation");
            sOrderLocation.setTextContent("001");
            row.appendChild(sOrderLocation);
            Element sOrderKey = document.createElement("SOrderKey");
            sOrderKey.setTextContent("14960");
            row.appendChild(sOrderKey);
            Element extOrderNr = document.createElement("ExtOrderNr");
            extOrderNr.setTextContent("88664 - WE 11214");
            row.appendChild(extOrderNr);
            Element supplName = document.createElement("SupplName");
            supplName.setTextContent("Vede");
            row.appendChild(supplName);
            //
            documentBuilder = documentFactory.newDocumentBuilder();
            Document document2 = documentBuilder.newDocument();
            xmlSOrderLines = document2.createElement("SqlRowSet1");
            Element row2 = document2.createElement("row");
            xmlSOrderLines.appendChild(row2);
            Element artNr = document2.createElement("ArtNr");
            artNr.setTextContent("123456");
            row2.appendChild(artNr);
            Element orderQty = document2.createElement("OrderQty");
            orderQty.setTextContent("123456");
            row2.appendChild(orderQty);
            Element sOrderRef = document2.createElement("SOrderRef");
            sOrderRef.setTextContent("14960");
            row2.appendChild(sOrderRef);
            Element artNr2 = document2.createElement("ArtNr");
            artNr2.setTextContent("123456");
            row2.appendChild(artNr2);
            Element itemNrSuppl = document2.createElement("ItemNrSuppl");
            itemNrSuppl.setTextContent("123456");
            row2.appendChild(itemNrSuppl);
        } catch (ParserConfigurationException pe) {
            //
        }
    }


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Test
    public void exportDataTest() {
        try {
            when(sOrderLineTagPixiApiImporter.importXml(any(String.class))).thenReturn(
                    xmlSOrderLines);
            String filename = "file-test.csv";
            File output = fixture.exportData(xmlSOrderKeys, filename);
            //
            Assert.assertTrue("OutputStream should not de null", output != null);
            Assert.assertTrue("Output should have something", output.toString().contains("123456"));
        } catch (MalformedURLException | SOAPException e) {
            Assert.assertTrue("Should not have thrown an Exception: " + e.getMessage(), false);
        }
    }
}
