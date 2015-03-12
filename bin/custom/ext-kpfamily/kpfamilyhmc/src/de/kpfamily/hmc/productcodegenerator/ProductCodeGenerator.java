package de.kpfamily.hmc.productcodegenerator;

import java.security.InvalidParameterException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import com.google.common.base.Strings;

import de.hybris.platform.core.Registry;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.kpfamily.hmc.model.ProductCodeModel;

/**
 * Generates a 6 characters codes for the Product Type, starting from 100,000 and using a simple 
 * increment counter
 * 
 * @author jfelipe
 *
 */
public class ProductCodeGenerator {
    
    private static final String CODE_FORMAT = "000000";

    /**
     * 
     */
    public  ProductCodeGenerator() {
        // default empty constructor
    }
    

    /**
     * Formats the input parameter to a 6 characters String.
     * 
     * @param input
     *              The counter.
     * @return A 6 characters formatted string.
     * 
     * @author jfelipe
     * 
     */
    private String formatCode(final int input) {
        final NumberFormat numberFormatter = new DecimalFormat(CODE_FORMAT);
        final String formattedProductCode = numberFormatter.format(input);
        return formattedProductCode;
    }


    /**
     * Searches for the maximum Product Code and adds 1.
     * Note: the product code counter should start from 100,000
     * 
     * @return The next product code
     * 
     * @author jfelipe
     * 
     */
    public String getFormattedNextProductCode() {
        FlexibleSearchService flexibleSearchService = (FlexibleSearchService) Registry
                .getApplicationContext().getBean("flexibleSearchService");
        final StringBuilder query = new StringBuilder()
                .append(" SELECT MAX({p:").append(ProductCodeModel.CODE).append("})")
                .append(" FROM {").append(ProductCodeModel._TYPECODE).append(" AS p} ");
        SearchResult<Integer> results = flexibleSearchService.search (
                query.toString());
        int maxProductCode = results.getResult().get(0) == null ? 0 : results.getResult().get(0).intValue();
        maxProductCode += (maxProductCode < 100000) ? 100000+1 : 1;
        return formatCode(maxProductCode);
    }


    /**
     * Generates the product EAN based on the 6 digit Product Code.
     * 
     * @param productCode
     *             The generated Product Code
     * @return The EAN13 for the given Product Code
     * 
     * @author jfelipe
     * 
     */
    public String generateEAN(final String productCode) {
        if (productCode == null || productCode.length() != 6) {
            throw new InvalidParameterException("Product code must have 6 digits exactly: ["
                    + productCode +"]");
        }
        int sum = 0;
        int factor = 3;
        for (char c : productCode.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new InvalidParameterException("Product code must have only digits: [" 
                        + productCode +"]");
            }
            sum += Character.digit(c, 10) * (factor = 4 - factor);
        }
        // TODO: Ver como criar esse ean13 corretamente
        return productCode + ((1000 - sum) % 10);
    }


    /**
     * 
     * @param ean
     * @return boolean
     *
     * @author jfelipe
     */
    public boolean isValidEAN(String ean) {
        return !Strings.isNullOrEmpty(ean) 
                && (ean.equalsIgnoreCase("neu") || ean.matches("(\\d{13})"));
    }
}
