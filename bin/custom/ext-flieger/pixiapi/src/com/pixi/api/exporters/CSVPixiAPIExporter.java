/**
 * 
 */
package com.pixi.api.exporters;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.base.Strings;

/**
 * @author jfelipe
 *
 */
public class CSVPixiAPIExporter implements PixiAPIExporter {

    /* (non-Javadoc)
     * @see com.pixi.api.exporters.PixiAPIExporter#exportToFile(org.w3c.dom.Node, java.lang.String)
     */
    @Override
    public File exportToFile(Node source, String targetName) {
        // YTODO Auto-generated method stub
        return null;
    }

//    private static final Logger LOG = Logger.getLogger(CSVPixiAPIExporter.class.getName());
//    //
//    private static final String CREATE_DATE_ORDER_TAG = "CreateDate";
//    private static final String SORDER_KEY_TAG = "SOrderKey";
//    private static final String SUPPLNR_TAG = "SupplNr";
//    private static final String SUPPLNR_TO_FIND = "VEDE";
//    //
//
//    /**
//     * Exporter the given XML to a Comma Separated Values (CSV) file.
//     * 
//     * @param sourceXML
//     *            The XML to be exported to a CSV file
//     * @param targetFileName
//     *            The name of the target CSV file
//     * @return
//     *         A Comma Separated Values (CSV) file
//     */
//    @Override
//    public File exportToFile(Node sourceXML, String targetFileName) {
//        if (Strings.isNullOrEmpty(targetFileName)) {
//            targetFileName = getDefaultTargetFileName();
//        }
//        sourceXML.
//        for(source)
//        return null;
//    }
//
//
//    /**
//     * @return
//     *         A default target file name.
//     * @author jfelipe
//     */
//    private String getDefaultTargetFileName() {
//        String dateString = new SimpleDateFormat("yyyyMMddhhmmss").format(Calendar.getInstance()
//                .getTime());
//        return "KP_380856090_order_" + dateString + ".csv";
//    }
//
//
//    /**
//     * Return a list of all keys in the given node.
//     * 
//     * @param parent
//     * @return
//     *         a list of all keys in the given node.
//     * @author jfelipe
//     */
//    private Map<String, String> getXMLOrderValues(Node parent) {
//        Map<String, String> keys = new HashMap<String, String>();
//        NodeList childs = parent.getChildNodes();
//        for (int i = 0; i < childs.getLength(); i++) {
//            Node child = childs.item(i);
//            if (child.hasChildNodes()) {
//                keys.putAll(getXMLOrderValues(child));
//            }
//            // If it is not a valid date, ignore this entire node
//            if (CREATE_DATE_ORDER_TAG.equals(child.getNodeName())) {
//                if (!isValidDate(child.getTextContent())) {
//                    break;
//                }
//            }
//            // If <childs> have a supplier tag, adds the node into the list
//            
//            for(int j = 0; j < childs.getLength(); j++) {
//                if()
//                    keys.put(child.getNodeName(), child.getTextContent());
//            }
//            
//        }
//        return keys;
//    }
//
//
//    /**
//     * Validates the given date
//     * 
//     * @param date
//     * @return
//     *         true if the date is AFTER yesterday, false otherwise
//     * @author jfelipe
//     */
//    private boolean isValidDate(String date) {
//        try {
//            date = date.replaceAll("T", " ");
//            String format = "yyyy-MM-dd hh:mm:ss" + (date.contains(".") ? ".S" : "");
//            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
//            Date givenDateParsed = dateFormat.parse(date);
//            Calendar dateYesterday = Calendar.getInstance();
//            dateYesterday.add(Calendar.DAY_OF_MONTH, -1);
//            return givenDateParsed.after(dateYesterday.getTime());
//        } catch (ParseException pe) {
//            LOG.error("error parsing date: " + pe.getMessage());
//        }
//        return false;
//    }
}
