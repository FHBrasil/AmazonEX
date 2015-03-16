package de.kpfamily.hmc.productcode;

import java.security.InvalidParameterException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Strings;

import de.hybris.platform.core.Registry;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.kpfamily.hmc.model.ProductCodeModel;

/**
 * Generates a 6 characters codes for the Product Type, starting from 100,000 and using a
 * simple
 * increment counter
 * 
 * @author jfelipe
 *
 */
public class ProductCodeGenerator {

    private static final String CODE_FORMAT = "000000000000";
    private static final String REGEX_VALID_EAN = "[0-9]{13}";
    private static final String REGEX_VALID_PRODUCT_CODE = "[0-9]{12}";


    /**
     * 
     */
    public ProductCodeGenerator() {
        // default empty constructor
    }


    /**
     * Formats the input parameter to a 6 characters String.
     * 
     * @param input
     *            The counter.
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
     * 
     * @param code
     * @return
     *
     * @author jfelipe
     */
    private boolean existsProductCode(String code) throws AmbiguousIdentifierException {
        try {
            FlexibleSearchService flexibleSearchService = (FlexibleSearchService) Registry
                    .getApplicationContext().getBean("flexibleSearchService");
            FlexibleSearchQuery flexQuery = new FlexibleSearchQuery(" SELECT {p:" 
                    + ProductCodeModel.PK + "} FROM {" + ProductCodeModel._TYPECODE
                    + " AS p} WHERE {p:" + ProductCodeModel.CODE + "} = ?code");
            flexQuery.addQueryParameter("code", code);
            ProductCodeModel result = flexibleSearchService.<ProductCodeModel> searchUnique(
                    flexQuery);
            return Strings.isNullOrEmpty(result.getCode()) ? Boolean.FALSE.booleanValue() : result
                    .getCode().equals(code);
        } catch(ModelNotFoundException mnfe) {
            return Boolean.FALSE.booleanValue();
        }
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
        FlexibleSearchQuery flexQuery = new FlexibleSearchQuery(" SELECT MAX({p:"
                + ProductCodeModel.CODE + "}) FROM {" + ProductCodeModel._TYPECODE + " AS p} ");
        flexQuery.setResultClassList(Collections.singletonList(Integer.class));
        List<Integer> results = flexibleSearchService.<Integer> search(flexQuery).getResult();
        int maxProductCode = results == null || results.isEmpty() || results.get(0) == null ? 0 
                : results.get(0).intValue();
        if (!existsProductCode(String.valueOf(maxProductCode))) {
            maxProductCode += (maxProductCode < 100000) ? 100000 + 1 : 1;
        }
        return formatCode(maxProductCode);
    }


    /**
     * Generates the product EAN based on the 12 digit Product Code.
     * 
     * @param productCode
     *            The generated Product Code
     * @return The EAN13 for the given Product Code
     * 
     * @author jfelipe
     * 
     */
    public String generateEAN13(final String productCode) {
        if (Strings.isNullOrEmpty(productCode) || !productCode.matches(REGEX_VALID_PRODUCT_CODE)) {
            throw new InvalidParameterException("Product code must have 12 digits exactly: ["
                    + productCode + "]");
        }
        int sum = 0;
        int factor = 3;
        for (char c : productCode.toCharArray()) {
            sum += Character.digit(c, 10) * (factor = 4 - factor);
        }
        return productCode + ((1000 - sum) % 10);
    }


    /**
     * 
     * @param ean13
     * @return boolean
     *
     * @author jfelipe
     */
    public boolean isValidEAN13(String ean13) {
        if (Strings.isNullOrEmpty(ean13) || !ean13.matches(REGEX_VALID_EAN)) {
            throw new InvalidParameterException("EAN13 must have exactly 13 digits: ["+ ean13 +"]");
        }
        int checkDigit = Integer.parseInt(ean13.substring(ean13.length() - 1));
        int sum = 0;
        int factor = 3;
        char[] ean = ean13.toCharArray();
        // validate the last digit we must not.
        for (int i = 0; i < ean.length - 1; i++) {
            char c = ean[i];
            sum += Character.digit(c, 10) * (factor = 4 - factor);
        }
        int validateDigit = ((1000 - sum) % 10);
        return validateDigit == checkDigit;
    }
}
