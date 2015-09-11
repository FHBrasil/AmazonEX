/**
 * 
 */
package de.fliegersoftware.amazon.payment.util;

import de.hybris.platform.util.Base64;

import java.security.SignatureException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
* This class defines common routines for generating
* authentication signatures for AWS Platform requests.
*/
public class Signature {
    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

    /**
     * Computes RFC 2104-compliant HMAC signature.
     * * @param data
     * The signed data.
     * @param key
     * The signing key.
     * @return
     * The Base64-encoded RFC 2104-compliant HMAC signature.
     * @throws
     * java.security.SignatureException when signature generation fails
     */
    public static String calculateRFC2104HMAC(String data, String key)
    throws java.security.SignatureException
    {
        String result;
        try {

            // Get an hmac_sha256 key from the raw key bytes.
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("UTF8"), HMAC_SHA256_ALGORITHM);

            // Get an hmac_sha256 Mac instance and initialize with the signing key.
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);

            // Compute the hmac on input data bytes.
            byte[] rawHmac = mac.doFinal(data.getBytes("UTF8"));

            // Base64-encode the hmac by using the utility in the SDK
            result = Base64.encodeBytes(rawHmac);
            

        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
        return result;
    }

    public static String getCanonicalUrl(String httpRequestMethod, String  httpHostHeader, String  absolutePath, String queryString ) {
   	 StringBuilder sb = new StringBuilder();
   	 
   	 sb.append(httpRequestMethod).append("\n");
   	 sb.append(httpHostHeader).append("\n");
   	 sb.append(absolutePath).append("\n");
   	 sb.append(queryString);
   	 
   	 System.out.println(sb);
   	 
   	 return sb.toString();
    }
    
    public static void main(String[] args) {
   	 String canonicalValue = getCanonicalUrl("GET", "localhost:9001", "/amazonAPI/finalize.jsp", "AWSAccessKeyId=AKIAIAID5NCSJ53HFN4A&Action=SetOrderReferenceDetails&SignatureMethod=HmacSHA256&SignatureVersion=2&Timestamp=2013-08-27T16%3A15%3A11Z&Version=2013-01-01");
   	 try {
   		 System.out.println(calculateRFC2104HMAC(canonicalValue, "EmprwUsiujc4ATHGWq9/R5JSzd1nt4WBTrTEMGWS"));
   	 } catch (SignatureException e) {
   		 e.printStackTrace();
   	 }
    }
    
}
