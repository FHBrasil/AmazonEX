/**
 * 
 */
package com.pixi.api.exporters.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.soap.SOAPException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.base.Strings;
import com.opencsv.CSVWriter;
import com.pixi.api.exporters.PixiAPIExporter;
import com.pixi.api.importers.PixiApiImporter;

/**
 * @author jfelipe
 *
 */
public class VedeSupplierSOrderLinesCsvPixiAPIExporter implements PixiAPIExporter {

    private static final Logger LOG = Logger
            .getLogger(VedeSupplierSOrderLinesCsvPixiAPIExporter.class.getName());
    private PixiApiImporter sOrderLineTagPixiApiImporter;
    //
    private static final String CREATE_DATE_TAG = "CreateDate";
    private static final String SORDER_KEY_TAG = "SOrderKey";
    private static final String SUPPLNR_TAG = "SupplNr";
    private static final String SUPPLNR_TO_FIND = "VEDE";
    //
    private final static String[] CSV_HEADER_LINE = new String[] { "ArtNr", "OrderQty",
            "SOrderRef", "ItemNrSuppl" };
    private final static List<String> HEADER_COLUMNS = Arrays.asList("ArtNr", "OrderQty",
            "SOrderRef", "ArtNr"/* "ItemNrSuppl" */);


    /**
     * Exporter the given XML to a Comma Separated Values (CSV) file.
     * 
     * @param sourceXML
     *            The XML to be exported to a CSV file
     * @param targetFileName
     *            The name of the target CSV file
     * @return
     *         A Comma Separated Values (CSV) file
     */
    @Override
    public File exportData(Node sourceXML, String targetFileName) {
        if (Strings.isNullOrEmpty(targetFileName)) {
            targetFileName = getDefaultFileName();
        }
        List<String> vedeSupplierSOrderNumbers = getVedeSupplierSOrderKeysTag(sourceXML);
        List<Node> vedeSOrderLineTags = getVedeSOrderLineTags(vedeSupplierSOrderNumbers);
        File csvFile = createCsvFile(vedeSOrderLineTags, targetFileName);
        return csvFile;
    }


    /**
     * 
     * @param sourceXML
     * @param fileName
     *
     * @author jfelipe
     */
    private File createCsvFile(List<Node> rowsToExport, String fileName) {
        File file = new java.io.File(fileName);
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            Writer writer = new OutputStreamWriter(outputStream);
            CSVWriter csvWriter = new CSVWriter(writer, ';', '"', '\\', "\n");
            csvWriter.writeNext(CSV_HEADER_LINE);
            for (Node row : rowsToExport) {
                String[] rowData = new String[row.getChildNodes().getLength()];
                for (int i = 0; i < rowData.length; i++) {
                    if (HEADER_COLUMNS.contains(row.getChildNodes().item(i).getNodeName())) {
                        rowData[i] = row.getChildNodes().item(i).getTextContent();
                    }
                }
                csvWriter.writeNext(rowData);
            }
            csvWriter.close();
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            // Nothing to do in this case, the file can't be written in CSV format.
        }
        return file;
    }


    /**
     * Gets a default file name.
     * 
     * @return
     *         A default target file name.
     * @author jfelipe
     */
    private String getDefaultFileName() {
        String dateString = new SimpleDateFormat("yyyyMMddhhmmss").format(Calendar.getInstance()
                .getTime());
        return "KP_380856090_order_" + dateString + ".csv";
    }


    /**
     * Returns a list with the value of the <SOrderKeys> tag, only if the supplier of the
     * row tag is "vede".
     * 
     * @param root
     * @return
     *         a list of order number from "vede" supplier.
     * @author jfelipe
     */
    private List<String> getVedeSupplierSOrderKeysTag(Node root) {
        List<String> vedeSupplierOrders = new ArrayList<String>();
        NodeList rowTags = root.getChildNodes();
        for (int i = 0; i < rowTags.getLength(); i++) {
            Node currentRowTag = rowTags.item(i);
            NodeList rowTagValues = currentRowTag.getChildNodes();
            int sOrderKeyTagPosition = -1;
            boolean isRowTagValueDateValid = false;
            // Firstly, we need to know if the <CreateDAte> tag is valid for this <row>
            // tag.
            for (int j = 0; j < rowTagValues.getLength(); j++) {
                Node currentRowValue = rowTagValues.item(j);
                if (StringUtils.equalsIgnoreCase(CREATE_DATE_TAG, currentRowValue.getNodeName())) {
                    isRowTagValueDateValid = isValidDate(currentRowValue.getTextContent());
                }
                // Secondly, we keep the <SOrderKey> tag position.
                if (StringUtils.equalsIgnoreCase(SORDER_KEY_TAG, currentRowValue.getNodeName())) {
                    sOrderKeyTagPosition = j;
                }
            }
            // Thirdly, if the date is valid, and the supplier is "vede", we add the
            // <SOrderKey> tag value to the list to be returned.
            if (isRowTagValueDateValid) {
                for (int j = 0; j < rowTagValues.getLength(); j++) {
                    Node currentRowValue = rowTagValues.item(j);
                    if (StringUtils.equalsIgnoreCase(SUPPLNR_TAG, currentRowValue.getNodeName())) {
                        if (StringUtils.equalsIgnoreCase(SUPPLNR_TO_FIND,
                                currentRowValue.getTextContent())) {
                            vedeSupplierOrders.add(rowTagValues.item(sOrderKeyTagPosition)
                                    .getTextContent());
                        }
                    }
                }
            }
        }
        return vedeSupplierOrders;
    }


    /**
     * Validates the given date
     * 
     * @param date
     * @return
     *         true if the date is AFTER yesterday, false otherwise
     * @author jfelipe
     */
    private boolean isValidDate(String date) {
        try {
            date = date.replaceAll("T", " ");
            String format = "yyyy-MM-dd hh:mm:ss" + (date.contains(".") ? ".S" : "");
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            Date givenDateParsed = dateFormat.parse(date);
            Calendar dateYesterday = Calendar.getInstance();
            dateYesterday.add(Calendar.DAY_OF_MONTH, -1);
            return givenDateParsed.after(dateYesterday.getTime());
        } catch (ParseException pe) {
            // nothing to do in this case. The date is just invalid.
        }
        return false;
    }


    /**
     * Retieves the <SOrderLine> tags from another Pixi API XML.
     * 
     * @param keysToImport
     *            A list of keys to import. Each web service request only sends one
     *            SOrderKey to be retrieved
     * @return
     *
     * @author jfelipe
     */
    private List<Node> getVedeSOrderLineTags(List<String> keysToImport) {
        List<Node> vedeSOrderLineTags = new ArrayList<Node>();
        for (String sOrderKey : keysToImport) {
            try {
                Node sOrderLineTag = getsOrderLineTagPixiApiImporter().importXml(sOrderKey);
                // Can't use .addAll() in this case... :/
                if (sOrderLineTag != null) {
                    NodeList childNodes = sOrderLineTag.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        vedeSOrderLineTags.add(childNodes.item(j));
                    }
                }
            } catch (MalformedURLException | SOAPException ex) {
                // nothing to do is this case, just skipping to next loop
                continue;
            }
        }
        return vedeSOrderLineTags;
    }


    /**
     * @return the sOrderLineTagPixiApiImporter
     */
    public PixiApiImporter getsOrderLineTagPixiApiImporter() {
        return sOrderLineTagPixiApiImporter;
    }


    /**
     * @param sOrderLineTagPixiApiImporter
     *            the sOrderLineTagPixiApiImporter to set
     */
    public void setsOrderLineTagPixiApiImporter(PixiApiImporter sOrderLineTagPixiApiImporter) {
        this.sOrderLineTagPixiApiImporter = sOrderLineTagPixiApiImporter;
    }
}
