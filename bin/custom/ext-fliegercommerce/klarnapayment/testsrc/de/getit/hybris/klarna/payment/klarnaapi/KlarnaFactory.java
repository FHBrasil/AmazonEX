/**
 * (c) copyright 2013 by getit GmbH
 *
 * @author gerald.bornemann, getit GmbH
 */
package de.getit.hybris.klarna.payment.klarnaapi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import com.klarna.api.Klarna;
import com.klarna.api.KlarnaAddr;
import com.klarna.api.KlarnaConfig;
import com.klarna.api.KlarnaCountry;
import com.klarna.api.KlarnaCurrency;
import com.klarna.api.KlarnaException;
import com.klarna.api.KlarnaLanguage;
import com.klarna.api.flags.Gender;
import com.klarna.api.flags.Goods;

import de.getit.hybris.klarna.payment.constants.KlarnapaymentConstants;

/**
 * Factory for country specific Klarna test objects.
 * 
 * Technical specification: KLARNA-5 Implement Unit-Test for Klarna API
 * 
 * @author gerald.bornemann, getit GmbH
 */
public class KlarnaFactory {

	private static final Map<String, KlarnaLanguage> msKlarnaLanguages = new HashMap<String, KlarnaLanguage>();
	private static final Map<String, KlarnaCurrency> msKlarnaCurrencies = new HashMap<String, KlarnaCurrency>();
	private static final Map<KlarnaCountry, Map<String, Object>> msKlarnaAddr_Approved = new HashMap<KlarnaCountry, Map<String, Object>>();
	private static final Map<KlarnaCountry, Map<String, Object>> msKlarnaAddr_Denied = new HashMap<KlarnaCountry, Map<String, Object>>();
	private static final Map<String, Map<String, Object>> msKlarnaArticlesIncVat = new HashMap<String, Map<String, Object>>();
	private static final Map<Goods, Map<String, Object>> msKlarnaArticlesFees = new HashMap<Goods, Map<String, Object>>();

	// configured Testdata
	static {
		msKlarnaLanguages.put("AT", KlarnaLanguage.DE);
		msKlarnaLanguages.put("DE", KlarnaLanguage.DE);
		msKlarnaLanguages.put("NL", KlarnaLanguage.NL);
		msKlarnaLanguages.put("DK", KlarnaLanguage.DA);
		msKlarnaLanguages.put("FI", KlarnaLanguage.FI);
		msKlarnaLanguages.put("NO", KlarnaLanguage.NB);
		msKlarnaLanguages.put("SE", KlarnaLanguage.SV);

		msKlarnaCurrencies.put("AT", KlarnaCurrency.EUR);
		msKlarnaCurrencies.put("DE", KlarnaCurrency.EUR);
		msKlarnaCurrencies.put("NL", KlarnaCurrency.EUR);
		msKlarnaCurrencies.put("DK", KlarnaCurrency.DKK);
		msKlarnaCurrencies.put("FI", KlarnaCurrency.EUR);
		msKlarnaCurrencies.put("NO", KlarnaCurrency.NOK);
		msKlarnaCurrencies.put("SE", KlarnaCurrency.SEK);

		// AT Approved Address data
		final Map<String, Object> msKlarnaAddrDataAT_Approved = new HashMap<String, Object>();
		msKlarnaAddrDataAT_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_PNO_OR_DATEOFBIRTH, "14041960");
		msKlarnaAddrDataAT_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_GENDER, Gender.MALE);
		msKlarnaAddrDataAT_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_FNAME, "Testperson-at");
		msKlarnaAddrDataAT_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_LNAME, "Approved");
		msKlarnaAddrDataAT_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_STREET, "Klarna-Straße 1/2/3");
		msKlarnaAddrDataAT_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_ZIP, "8071");
		msKlarnaAddrDataAT_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_CITY, "Hausmannstätten");
		msKlarnaAddrDataAT_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_CELLNO, "0676 2600000");
		msKlarnaAddrDataAT_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSENO, ""); // nur für DE + NL
		msKlarnaAddrDataAT_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSEEXT, ""); // nur für NL
		msKlarnaAddr_Approved.put(KlarnaCountry.AT, msKlarnaAddrDataAT_Approved);

		// DE Approved Address data
		final Map<String, Object> msKlarnaAddrDataDE_Approved = new HashMap<String, Object>();
		msKlarnaAddrDataDE_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_PNO_OR_DATEOFBIRTH, "07071960");
		msKlarnaAddrDataDE_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_GENDER, Gender.MALE);
		msKlarnaAddrDataDE_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_FNAME, "Testperson-de");
		msKlarnaAddrDataDE_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_LNAME, "Approved");
		msKlarnaAddrDataDE_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_STREET, "Hellersbergstraße");
		msKlarnaAddrDataDE_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_ZIP, "41460");
		msKlarnaAddrDataDE_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_CITY, "Neuss");
		msKlarnaAddrDataDE_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_CELLNO, "01522113356");
		msKlarnaAddrDataDE_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSENO, "14"); // nur für DE + NL
		msKlarnaAddrDataDE_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSEEXT, ""); // nur für NL
		msKlarnaAddr_Approved.put(KlarnaCountry.DE, msKlarnaAddrDataDE_Approved);

		// DK Approved Address data
		final Map<String, Object> msKlarnaAddrDataDK_Approved = new HashMap<String, Object>();
		msKlarnaAddrDataDK_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_PNO_OR_DATEOFBIRTH, "0801363945"); // PNO
		msKlarnaAddrDataDK_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_GENDER, null);
		msKlarnaAddrDataDK_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_FNAME, "Testperson-dk");
		msKlarnaAddrDataDK_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_LNAME, "Approved");
		msKlarnaAddrDataDK_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_STREET, "Sæffleberggate 56,1 mf");
		msKlarnaAddrDataDK_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_ZIP, "6800");
		msKlarnaAddrDataDK_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_CITY, "Varde");
		msKlarnaAddrDataDK_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_CELLNO, "20 123 456");
		msKlarnaAddrDataDK_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSENO, ""); // nur für DE + NL
		msKlarnaAddrDataDK_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSEEXT, ""); // nur für NL
		msKlarnaAddr_Approved.put(KlarnaCountry.DK, msKlarnaAddrDataDK_Approved);

		// FI Approved Address data
		final Map<String, Object> msKlarnaAddrDataFI_Approved = new HashMap<String, Object>();
		msKlarnaAddrDataFI_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_PNO_OR_DATEOFBIRTH, "190122-829F"); // PNO
		msKlarnaAddrDataFI_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_GENDER, null);
		msKlarnaAddrDataFI_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_FNAME, "Testperson-fi");
		msKlarnaAddrDataFI_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_LNAME, "Approved");
		msKlarnaAddrDataFI_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_STREET, "Kiväärikatu 10");
		msKlarnaAddrDataFI_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_ZIP, "28100");
		msKlarnaAddrDataFI_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_CITY, "Pori");
		msKlarnaAddrDataFI_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_CELLNO, "0401234567");
		msKlarnaAddrDataFI_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSENO, ""); // nur für DE + NL
		msKlarnaAddrDataFI_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSEEXT, ""); // nur für NL
		msKlarnaAddr_Approved.put(KlarnaCountry.FI, msKlarnaAddrDataFI_Approved);

		// NL Approved Address data
		final Map<String, Object> msKlarnaAddrDataNL_Approved = new HashMap<String, Object>();
		msKlarnaAddrDataNL_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_PNO_OR_DATEOFBIRTH, "10071970");
		msKlarnaAddrDataNL_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_GENDER, Gender.MALE);
		msKlarnaAddrDataNL_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_FNAME, "Testperson-nl");
		msKlarnaAddrDataNL_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_LNAME, "Approved");
		msKlarnaAddrDataNL_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_STREET, "Neherkade");
		msKlarnaAddrDataNL_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_ZIP, "2521VA");
		msKlarnaAddrDataNL_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_CITY, "Gravenhage");
		msKlarnaAddrDataNL_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_CELLNO, "0612345678");
		msKlarnaAddrDataNL_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSENO, "1"); // nur für DE + NL
		msKlarnaAddrDataNL_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSEEXT, "XI"); // nur für NL
		msKlarnaAddr_Approved.put(KlarnaCountry.NL, msKlarnaAddrDataNL_Approved);

		// NO Approved Address data
		final Map<String, Object> msKlarnaAddrDataNO_Approved = new HashMap<String, Object>();
		msKlarnaAddrDataNO_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_PNO_OR_DATEOFBIRTH, "01121579533"); // PNO
		msKlarnaAddrDataNO_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_GENDER, null);
		msKlarnaAddrDataNO_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_FNAME, "Testperson-no");
		msKlarnaAddrDataNO_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_LNAME, "Approved");
		msKlarnaAddrDataNO_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_STREET, "Sæffleberggate 56");
		msKlarnaAddrDataNO_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_ZIP, "0563");
		msKlarnaAddrDataNO_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_CITY, "Oslo");
		msKlarnaAddrDataNO_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_CELLNO, "40 123 456");
		msKlarnaAddrDataNO_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSENO, ""); // nur für DE + NL
		msKlarnaAddrDataNO_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSEEXT, ""); // nur für NL
		msKlarnaAddr_Approved.put(KlarnaCountry.NO, msKlarnaAddrDataNO_Approved);

		// SE Approved Address data
		final Map<String, Object> msKlarnaAddrDataSE_Approved = new HashMap<String, Object>();
		msKlarnaAddrDataSE_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_PNO_OR_DATEOFBIRTH, "410321-9202"); // PNO
		msKlarnaAddrDataSE_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_GENDER, null);
		msKlarnaAddrDataSE_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_FNAME, "Testperson-se");
		msKlarnaAddrDataSE_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_LNAME, "Approved");
		msKlarnaAddrDataSE_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_STREET, "Stårgatan 1");
		msKlarnaAddrDataSE_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_ZIP, "12345");
		msKlarnaAddrDataSE_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_CITY, "Ankeborg");
		msKlarnaAddrDataSE_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_CELLNO, "0765260000");
		msKlarnaAddrDataSE_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSENO, ""); // nur für DE + NL
		msKlarnaAddrDataSE_Approved.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSEEXT, ""); // nur für NL
		msKlarnaAddr_Approved.put(KlarnaCountry.SE, msKlarnaAddrDataSE_Approved);

		// AT Denied Address data
		final Map<String, Object> msKlarnaAddrDataAT_Denied = new HashMap<String, Object>();
		msKlarnaAddrDataAT_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_PNO_OR_DATEOFBIRTH, "14041980");
		msKlarnaAddrDataAT_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_GENDER, Gender.FEMALE);
		msKlarnaAddrDataAT_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_FNAME, "Testperson-at");
		msKlarnaAddrDataAT_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_LNAME, "Denied");
		msKlarnaAddrDataAT_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_STREET, "Klarna-Straße 1/2/3");
		msKlarnaAddrDataAT_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_ZIP, "8070");
		msKlarnaAddrDataAT_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_CITY, "Hausmannstätten");
		msKlarnaAddrDataAT_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_CELLNO, "0676 2800000");
		msKlarnaAddrDataAT_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSENO, ""); // nur für DE + NL
		msKlarnaAddrDataAT_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSEEXT, ""); // nur für NL
		msKlarnaAddr_Denied.put(KlarnaCountry.AT, msKlarnaAddrDataAT_Denied);

		// DE Denied Address data
		final Map<String, Object> msKlarnaAddrDataDE_Denied = new HashMap<String, Object>();
		msKlarnaAddrDataDE_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_PNO_OR_DATEOFBIRTH, "07071960");
		msKlarnaAddrDataDE_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_GENDER, Gender.MALE);
		msKlarnaAddrDataDE_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_FNAME, "Testperson-de");
		msKlarnaAddrDataDE_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_LNAME, "Denied");
		msKlarnaAddrDataDE_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_STREET, "Hellersbergstraße");
		msKlarnaAddrDataDE_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_ZIP, "41460");
		msKlarnaAddrDataDE_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_CITY, "Neuss");
		msKlarnaAddrDataDE_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_CELLNO, "01522113356");
		msKlarnaAddrDataDE_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSENO, "14"); // nur für DE + NL
		msKlarnaAddrDataDE_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSEEXT, ""); // nur für NL
		msKlarnaAddr_Denied.put(KlarnaCountry.DE, msKlarnaAddrDataDE_Denied);

		// DK Denied Address data
		final Map<String, Object> msKlarnaAddrDataDK_Denied = new HashMap<String, Object>();
		msKlarnaAddrDataDK_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_PNO_OR_DATEOFBIRTH, "0801373501"); // PNO
		msKlarnaAddrDataDK_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_GENDER, null);
		msKlarnaAddrDataDK_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_FNAME, "Testperson-dk");
		msKlarnaAddrDataDK_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_LNAME, "Denied");
		msKlarnaAddrDataDK_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_STREET, "Sæffleberggate 56,1 mf");
		msKlarnaAddrDataDK_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_ZIP, "6800");
		msKlarnaAddrDataDK_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_CITY, "Varde");
		msKlarnaAddrDataDK_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_CELLNO, "20 123 456");
		msKlarnaAddrDataDK_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSENO, ""); // nur für DE + NL
		msKlarnaAddrDataDK_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSEEXT, ""); // nur für NL
		msKlarnaAddr_Denied.put(KlarnaCountry.DK, msKlarnaAddrDataDK_Denied);

		// FI Denied Address data
		final Map<String, Object> msKlarnaAddrDataFI_Denied = new HashMap<String, Object>();
		msKlarnaAddrDataFI_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_PNO_OR_DATEOFBIRTH, "190122-333F"); // PNO
		msKlarnaAddrDataFI_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_GENDER, null);
		msKlarnaAddrDataFI_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_FNAME, "Testperson-fi");
		msKlarnaAddrDataFI_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_LNAME, "Denied");
		msKlarnaAddrDataFI_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_STREET, "Kiväärikatu 10");
		msKlarnaAddrDataFI_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_ZIP, "28100");
		msKlarnaAddrDataFI_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_CITY, "Pori");
		msKlarnaAddrDataFI_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_CELLNO, "0401234567");
		msKlarnaAddrDataFI_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSENO, ""); // nur für DE + NL
		msKlarnaAddrDataFI_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSEEXT, ""); // nur für NL
		msKlarnaAddr_Denied.put(KlarnaCountry.FI, msKlarnaAddrDataFI_Denied);

		// NL Denied Address data
		final Map<String, Object> msKlarnaAddrDataNL_Denied = new HashMap<String, Object>();
		msKlarnaAddrDataNL_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_PNO_OR_DATEOFBIRTH, "10071970");
		msKlarnaAddrDataNL_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_GENDER, Gender.MALE);
		msKlarnaAddrDataNL_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_FNAME, "Testperson-nl");
		msKlarnaAddrDataNL_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_LNAME, "Denied");
		msKlarnaAddrDataNL_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_STREET, "Neherkade");
		msKlarnaAddrDataNL_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_ZIP, "2521VA");
		msKlarnaAddrDataNL_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_CITY, "Gravenhage");
		msKlarnaAddrDataNL_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_CELLNO, "0612345678");
		msKlarnaAddrDataNL_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSENO, "1"); // nur für DE + NL
		msKlarnaAddrDataNL_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSEEXT, "XI"); // nur für NL
		msKlarnaAddr_Denied.put(KlarnaCountry.NL, msKlarnaAddrDataNL_Denied);

		// NO Denied Address data
		final Map<String, Object> msKlarnaAddrDataNO_Denied = new HashMap<String, Object>();
		msKlarnaAddrDataNO_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_PNO_OR_DATEOFBIRTH, "01121598422"); // PNO
		msKlarnaAddrDataNO_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_GENDER, null);
		msKlarnaAddrDataNO_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_FNAME, "Testperson-no");
		msKlarnaAddrDataNO_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_LNAME, "Denied");
		msKlarnaAddrDataNO_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_STREET, "Sæffleberggate 56");
		msKlarnaAddrDataNO_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_ZIP, "0563");
		msKlarnaAddrDataNO_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_CITY, "Oslo");
		msKlarnaAddrDataNO_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_CELLNO, "40 123 456");
		msKlarnaAddrDataNO_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSENO, ""); // nur für DE + NL
		msKlarnaAddrDataNO_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSEEXT, ""); // nur für NL
		msKlarnaAddr_Denied.put(KlarnaCountry.NO, msKlarnaAddrDataNO_Denied);

		// SE Denied Address data
		final Map<String, Object> msKlarnaAddrDataSE_Denied = new HashMap<String, Object>();
		msKlarnaAddrDataSE_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_PNO_OR_DATEOFBIRTH, "411028-8083"); // PNO
		msKlarnaAddrDataSE_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_GENDER, null);
		msKlarnaAddrDataSE_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_FNAME, "Testperson-se");
		msKlarnaAddrDataSE_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_LNAME, "Denied");
		msKlarnaAddrDataSE_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_STREET, "Stårgatan 1");
		msKlarnaAddrDataSE_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_ZIP, "12345");
		msKlarnaAddrDataSE_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_CITY, "Ankeborg");
		msKlarnaAddrDataSE_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_CELLNO, "0765260000");
		msKlarnaAddrDataSE_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSENO, ""); // nur für DE + NL
		msKlarnaAddrDataSE_Denied.put(KlarnapaymentConstants.KLARNA_ADDR_HOUSEEXT, ""); // nur für NL
		msKlarnaAddr_Denied.put(KlarnaCountry.SE, msKlarnaAddrDataSE_Denied);

		// test article 01 with vat
		Map<String, Object> msKlarnaArticel_INC_VAT = new HashMap<String, Object>();
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_ARTNO, "1001");
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_TITLE, "Test-Article 01 inc vat");
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_QUANTITY, Integer.valueOf(10));
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_FLAGS, EnumSet.of(Goods.INC_VAT));
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_PRICE, Double.valueOf(100.d));
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_DISCOUNT, Double.valueOf(0.d));
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_VAT, Double.valueOf(19.d));
		msKlarnaArticlesIncVat.put("01", msKlarnaArticel_INC_VAT);
		// test article 02 with vat and with 20 % Discount
		msKlarnaArticel_INC_VAT = new HashMap<String, Object>();
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_ARTNO, "1002");
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_TITLE, "Test-Article 02 inc vat with 20 % Discount");
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_QUANTITY, Integer.valueOf(2));
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_FLAGS, EnumSet.of(Goods.INC_VAT));
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_PRICE, Double.valueOf(222.d));
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_DISCOUNT, Double.valueOf(20.d));
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_VAT, Double.valueOf(19.d));
		msKlarnaArticlesIncVat.put("02", msKlarnaArticel_INC_VAT);
		// test article 03 with vat
		msKlarnaArticel_INC_VAT = new HashMap<String, Object>();
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_ARTNO, "1003");
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_TITLE, "Test-Article 03 inc vat");
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_QUANTITY, Integer.valueOf(30));
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_FLAGS, EnumSet.of(Goods.INC_VAT));
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_PRICE, Double.valueOf(3.33d));
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_DISCOUNT, Double.valueOf(0.d));
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_VAT, Double.valueOf(19.d));
		msKlarnaArticlesIncVat.put("03", msKlarnaArticel_INC_VAT);
		// test article 04 with vat
		msKlarnaArticel_INC_VAT = new HashMap<String, Object>();
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_ARTNO, "1004");
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_TITLE, "Test-Article 04 inc vat with 4,4 % Discount");
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_QUANTITY, Integer.valueOf(4));
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_FLAGS, EnumSet.of(Goods.INC_VAT));
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_PRICE, Double.valueOf(44.4d));
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_DISCOUNT, Double.valueOf(4.4d));
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_VAT, Double.valueOf(19.d));
		msKlarnaArticlesIncVat.put("04", msKlarnaArticel_INC_VAT);
		// test article 05 with vat
		msKlarnaArticel_INC_VAT = new HashMap<String, Object>();
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_ARTNO, "1005");
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_TITLE, "Test-Article 05 inc vat of 7 %");
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_QUANTITY, Integer.valueOf(15));
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_FLAGS, EnumSet.of(Goods.INC_VAT));
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_PRICE, Double.valueOf(5d));
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_DISCOUNT, Double.valueOf(0.0d));
		msKlarnaArticel_INC_VAT.put(KlarnapaymentConstants.ARTICLE_VAT, Double.valueOf(7.d));
		msKlarnaArticlesIncVat.put("05", msKlarnaArticel_INC_VAT);

		// article is handling fee article
		final Map<String, Object> msKlarnaArticel_IS_HANDLING = new HashMap<String, Object>();
		msKlarnaArticel_IS_HANDLING.put(KlarnapaymentConstants.ARTICLE_ARTNO, "2000");
		msKlarnaArticel_IS_HANDLING.put(KlarnapaymentConstants.ARTICLE_TITLE, "Test-Article is handling fee");
		msKlarnaArticel_IS_HANDLING.put(KlarnapaymentConstants.ARTICLE_QUANTITY, Integer.valueOf(1));
		msKlarnaArticel_IS_HANDLING.put(KlarnapaymentConstants.ARTICLE_FLAGS, EnumSet.of(Goods.INC_VAT, Goods.IS_HANDLING));
		msKlarnaArticel_IS_HANDLING.put(KlarnapaymentConstants.ARTICLE_PRICE, Double.valueOf(1.5d));
		msKlarnaArticel_IS_HANDLING.put(KlarnapaymentConstants.ARTICLE_DISCOUNT, Double.valueOf(0.d));
		msKlarnaArticel_IS_HANDLING.put(KlarnapaymentConstants.ARTICLE_VAT, Double.valueOf(19.d));
		msKlarnaArticlesFees.put(Goods.IS_HANDLING, msKlarnaArticel_IS_HANDLING);

		// article is shipping fee article
		final Map<String, Object> msKlarnaArticel_IS_SHIPMENT = new HashMap<String, Object>();
		msKlarnaArticel_IS_SHIPMENT.put(KlarnapaymentConstants.ARTICLE_ARTNO, "3000");
		msKlarnaArticel_IS_SHIPMENT.put(KlarnapaymentConstants.ARTICLE_TITLE, "Test-Article is shipping fee");
		msKlarnaArticel_IS_SHIPMENT.put(KlarnapaymentConstants.ARTICLE_QUANTITY, Integer.valueOf(1));
		msKlarnaArticel_IS_SHIPMENT.put(KlarnapaymentConstants.ARTICLE_FLAGS, EnumSet.of(Goods.INC_VAT, Goods.IS_SHIPMENT));
		msKlarnaArticel_IS_SHIPMENT.put(KlarnapaymentConstants.ARTICLE_PRICE, Double.valueOf(4.5d));
		msKlarnaArticel_IS_SHIPMENT.put(KlarnapaymentConstants.ARTICLE_DISCOUNT, Double.valueOf(0.d));
		msKlarnaArticel_IS_SHIPMENT.put(KlarnapaymentConstants.ARTICLE_VAT, Double.valueOf(19.d));
		msKlarnaArticlesFees.put(Goods.IS_SHIPMENT, msKlarnaArticel_IS_SHIPMENT);

	}

	private KlarnaFactory() {
	}


	/**
	 * Returns country specific Klarna/KlarnaConfig test object.
	 * 
	 * @param pCountryIsoCode
	 * @return Klarna
	 */
	public static Klarna getConfiguredKlarna(final String pCountryIsoCode, final Integer mMerchantId, final String pSharedSecret )
		throws KlarnaException {

		final KlarnaConfig conf = new KlarnaConfig();
		
		conf.setEid(mMerchantId); // Merchant ID or Estore ID, an Integer above 0.
		conf.setSecret(pSharedSecret); // The shared secret which accompanied your eid.
		conf.setCountry(KlarnaCountry.getCountry(pCountryIsoCode)); // The country of your store.
		conf.setLanguage(msKlarnaLanguages.get(pCountryIsoCode)); // The language of your store.
		conf.setCurrency(msKlarnaCurrencies.get(pCountryIsoCode)); // The currency of your store.
		conf.setMode(Klarna.BETA); // or Klarna.LIVE when you are ready to go live.

		// Define pclass settings:
		conf.setPcStorage("json"); // Storage module. Currently only json is supported.
		if (System.getProperty("os.name").contains("Windows")) {
			conf.setPcURI("C:\\temp\\"+pCountryIsoCode+"pclasses.json"); // Where the json file for the pclasses are stored.
		} else {
			conf.setPcURI("/tmp/"+pCountryIsoCode+"pclasses.json"); // Where the json file for the pclasses are stored.
			// Should we use SSL?
		}

		conf.setSsl(false);

		// Should we error report/status report to klarna.
		conf.setCandice(true); // set to false if your server doesn't support UDP

		// Do we want to see normal debug information?
		conf.setDebug(true); // true to debug, null or false not to debug

		// Set the config object.
		final Klarna klarna = new Klarna();
		klarna.config(conf);

		// Set Client IP
		final String ip = "127.5" + ".5.46";
		klarna.setClientIP(ip);

		return klarna;
	}

	/**
	 * Returns defined test approved or denied address for country depending on isApprovedAddr boolean flag.
	 * 
	 * @param countryIsoCode
	 * @param isApprovedAddr
	 * @return KlarnaAddr
	 */
	public static KlarnaAddr getKlarnaAddr(final String countryIsoCode, final boolean isApprovedAddr) {

		final KlarnaAddr klarnaAddr = new KlarnaAddr();
		final KlarnaCountry klarnaCountry = KlarnaCountry.getCountry(countryIsoCode);
		klarnaAddr.setCountry(klarnaCountry);
		// TODO: Pending status: To be able to test invoices which get the pending status, you will need to use an
		// e-mail address which will set the pending status for 2 minutes until it will turn to either accepted or
		// denied. Use the following addresses:
		// pending_accepted@yourdomain.com
		// pending_denied@yourdomain.com
		// You can change "yourdomain.com" to any domain. It is the first part of the e-mail which will set the pending
		// flag to the invoice in our test case.
		klarnaAddr.setEmail("klarna-team@getit.de");
		if (isApprovedAddr) {
			klarnaAddr.setFirstName((String) msKlarnaAddr_Approved.get(klarnaCountry).get(KlarnapaymentConstants.KLARNA_ADDR_FNAME));
			klarnaAddr.setLastName((String) msKlarnaAddr_Approved.get(klarnaCountry).get(KlarnapaymentConstants.KLARNA_ADDR_LNAME));
			klarnaAddr.setStreet((String) msKlarnaAddr_Approved.get(klarnaCountry).get(KlarnapaymentConstants.KLARNA_ADDR_STREET));
			klarnaAddr.setZipCode((String) msKlarnaAddr_Approved.get(klarnaCountry).get(KlarnapaymentConstants.KLARNA_ADDR_ZIP));
			klarnaAddr.setCity((String) msKlarnaAddr_Approved.get(klarnaCountry).get(KlarnapaymentConstants.KLARNA_ADDR_CITY));
			klarnaAddr.setCellNo((String) msKlarnaAddr_Approved.get(klarnaCountry).get(KlarnapaymentConstants.KLARNA_ADDR_CELLNO));
			klarnaAddr.setHouseNumber((String) msKlarnaAddr_Approved.get(klarnaCountry).get(KlarnapaymentConstants.KLARNA_ADDR_HOUSENO));
			klarnaAddr.setHouseExt((String) msKlarnaAddr_Approved.get(klarnaCountry).get(KlarnapaymentConstants.KLARNA_ADDR_HOUSEEXT));
		} else {
			klarnaAddr.setFirstName((String) msKlarnaAddr_Denied.get(klarnaCountry).get(KlarnapaymentConstants.KLARNA_ADDR_FNAME));
			klarnaAddr.setLastName((String) msKlarnaAddr_Denied.get(klarnaCountry).get(KlarnapaymentConstants.KLARNA_ADDR_LNAME));
			klarnaAddr.setStreet((String) msKlarnaAddr_Denied.get(klarnaCountry).get(KlarnapaymentConstants.KLARNA_ADDR_STREET));
			klarnaAddr.setZipCode((String) msKlarnaAddr_Denied.get(klarnaCountry).get(KlarnapaymentConstants.KLARNA_ADDR_ZIP));
			klarnaAddr.setCity((String) msKlarnaAddr_Denied.get(klarnaCountry).get(KlarnapaymentConstants.KLARNA_ADDR_CITY));
			klarnaAddr.setCellNo((String) msKlarnaAddr_Denied.get(klarnaCountry).get(KlarnapaymentConstants.KLARNA_ADDR_CELLNO));
			klarnaAddr.setHouseNumber((String) msKlarnaAddr_Denied.get(klarnaCountry).get(KlarnapaymentConstants.KLARNA_ADDR_HOUSENO));
			klarnaAddr.setHouseExt((String) msKlarnaAddr_Denied.get(klarnaCountry).get(KlarnapaymentConstants.KLARNA_ADDR_HOUSEEXT));
		}
		assertNotNull(klarnaAddr);
		return klarnaAddr;
	}

	/**
	 * Returns test Klarna/KlarnaConfig object for specific country.
	 * 
	 * @param countryIsoCode
	 * @return Klarna
	 */
	public static Klarna getKlarnaConfig(final String countryIsoCode, final Integer merchantId, final String sharedSecret ) {
		Klarna klarna = null;
		boolean klarnaExceptionThrown = false;
		try {
			klarna = KlarnaFactory.getConfiguredKlarna(countryIsoCode,merchantId,sharedSecret);
		} catch (final KlarnaException e1) {
			klarnaExceptionThrown = true;
		}
		assertFalse("No Klarna configuration for " + countryIsoCode + " available", klarnaExceptionThrown);
		assertNotNull(klarna);

		return klarna;
	}

	/**
	 * Returns data of preconfigured test article incl. VAT, depending on given number ("01" ... "05").
	 * 
	 * @param number
	 *            can be "01", "02", "03", "04", "05"
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> getConfiguredKlarnaArticleIncVat(final String number) {
		return msKlarnaArticlesIncVat.get(number);
	}

	/**
	 * Returns data of preconfigured test article for shipping fee.
	 * 
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> getConfiguredKlarnaArticleForShippingFee() {
		return msKlarnaArticlesFees.get(Goods.IS_SHIPMENT);
	}

	/**
	 * Returns data of preconfigured test article for handling fee.
	 * 
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> getConfiguredKlarnaArticleForHandlingFee() {
		return msKlarnaArticlesFees.get(Goods.IS_HANDLING);
	}

	/**
	 * Returns PNO or Date of Birth, depending on country and depending on approved boolean flag.
	 * 
	 * @param pCountryIsoCode
	 * @param approved
	 * @return String
	 */
	public static String getPNOOrDateOfBirth(final String pCountryIsoCode, final boolean approved) {

		String pno_dateofbirth = null;
		final KlarnaCountry klarnaCountry = KlarnaCountry.getCountry(pCountryIsoCode);
		if (approved) {
			pno_dateofbirth = (String) msKlarnaAddr_Approved.get(klarnaCountry).get(KlarnapaymentConstants.KLARNA_ADDR_PNO_OR_DATEOFBIRTH);
		} else {
			pno_dateofbirth = (String) msKlarnaAddr_Denied.get(klarnaCountry).get(KlarnapaymentConstants.KLARNA_ADDR_PNO_OR_DATEOFBIRTH);
		}
		return pno_dateofbirth;
	}

	/**
	 * Returns Gender of Testaddress, depending on country and depending on approved boolean flag.
	 * 
	 * @param pCountryIsoCode
	 * @param approved
	 * @return String
	 */
	public static Gender getGender(final String pCountryIsoCode, final boolean approved) {

		Gender gender = null;
		final KlarnaCountry klarnaCountry = KlarnaCountry.getCountry(pCountryIsoCode);
		if (approved) {
			if (msKlarnaAddr_Approved.get(klarnaCountry).get(KlarnapaymentConstants.KLARNA_ADDR_GENDER) != null) {
				gender = (Gender) msKlarnaAddr_Approved.get(klarnaCountry).get(KlarnapaymentConstants.KLARNA_ADDR_GENDER);
			}
		} else {
			if (msKlarnaAddr_Denied.get(klarnaCountry).get(KlarnapaymentConstants.KLARNA_ADDR_GENDER) != null) {
				gender = (Gender) msKlarnaAddr_Denied.get(klarnaCountry).get(KlarnapaymentConstants.KLARNA_ADDR_GENDER);
			}
		}
		return gender;
	}
}
