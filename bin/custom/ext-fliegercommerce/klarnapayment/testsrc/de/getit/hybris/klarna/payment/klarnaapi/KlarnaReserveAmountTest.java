/**
 * (c) copyright 2013 by getit GmbH
 *
 * @author Sonja Bouwers, getit GmbH
 */
package de.getit.hybris.klarna.payment.klarnaapi;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.klarna.api.Klarna;
import com.klarna.api.KlarnaAddr;
import com.klarna.api.KlarnaException;
import com.klarna.api.KlarnaPClass;
import com.klarna.api.flags.Address;
import com.klarna.api.flags.Gender;
import com.klarna.api.flags.Reservation;
import com.klarna.api.flags.Status;
import com.klarna.api.intf.IReserveAmount;

/**
 * JUnit test class for testing all variants of method Klarna.reserveAmount()
 * 
 * Technical specification: KLARNA-5 Implement Unit-Test for Klarna API
 * 
 * see https://integration.klarna.com/de/api/advanced-integration/functions/reserveamount
 * 
 * Purpose: To reserve a purchase amount for a specific customer. The reservation is valid, by default, for 7 days.
 * Please note: Activation must be done with activateReservation , i.e. you can't activate through Klarna Online.
 * 
 * Return value: Array - [rno, invoiceStatus] The value of "rno" is the reservation number for the purchase. The value
 * of "invoiceStatus" shows if the invoice can be delivered immediately or requires manual approval (1 = OK, 2 =
 * Pending) or throws an exception with error code and error message.
 * 
 * @author Sonja Bouwers, getit GmbH
 */
@RunWith(value = Parameterized.class)
public class KlarnaReserveAmountTest extends AbstractKlarnaAddArticleTest {

	private final String countryCode;
	private final boolean isApprovedAddr;

	public KlarnaReserveAmountTest(final String countryCode, final boolean isApprovedAddr) {
		this.countryCode = countryCode;
		this.isApprovedAddr = isApprovedAddr;
	}

	/**
	 * Parameterize<br/>
	 * Calls the JUnit-Test reserveAmount() only for the approved addresses for all countries.
	 * 
	 * @return Collection<Object[]>
	 */
	@Parameters
	public static Collection<Object[]> generateTestData() {
		final Object[][] data = new Object[][] { { "SE", true }, { "DE", true }, { "AT", true }, { "DK", true }, { "NL", true },
			{ "FI", true }, { "NO", true } };
		return Arrays.asList(data);
	}

	/**
	 * Test methode - adds an approved address, adds some articles and calls Klarna.reserveAmount() mehtod with
	 * pno/dateofBirth, Gender-Flag and Reservation-Flag. Asserts that reservation number and status object is returned.
	 * 
	 */
	@Test
	public void reserveAmount() {

		final Klarna klarna = prepareKlarnaForReserveAmount(countryCode, isApprovedAddr);

		final String pno_dateofBirth = KlarnaFactory.getPNOOrDateOfBirth(countryCode, isApprovedAddr);
		final Gender gender = KlarnaFactory.getGender(countryCode, isApprovedAddr);
		final EnumSet<Reservation> resflags = EnumSet.of(Reservation.NO_FLAG);
		final double amount = -1.d;

		try {
			final String[] result = callReserveAmountMethod_01(klarna, pno_dateofBirth, gender, amount, resflags, KlarnaPClass.INVOICE);

			assertNotNull(result);

			// Check the order status
			final Status status = Status.getStatus(result[1]);
			assertNotNull(status);
			assertNotNull(status.name());

			// Here we get the reservation number
			final String rno = result[0];

			// Order is complete, store it in a database.
			assertNotNull(rno);

		} catch (final KlarnaException e) {
			fail("reserveAmount() failed (" + countryCode + ") \nErrorcode: " + e.getCode() + "\nErrormessage: " + e.getMessage());
		}
	}

	/**
	 * Before reserveAmount() can be called, the address and the articles must be added first.
	 * 
	 * @param countryIsoCode
	 * @param isApprovedAddr
	 * @return Klarna
	 */
	private Klarna prepareKlarnaForReserveAmount(final String countryIsoCode, final boolean isApprovedAddr) {

		final Klarna klarna = createKlarnaConfig(countryIsoCode);

		// add address as Shipping and Billing-Address
		final KlarnaAddr klarnaAddr = KlarnaFactory.getKlarnaAddr(countryIsoCode, isApprovedAddr);

		try {
			klarna.setAddress(Address.IS_BILLING, klarnaAddr);
		} catch (final KlarnaException e) {
			fail("prepareKlarnaForReserveAmount() failed - Could not set IS_BILLING address for " + countryCode + " and approved = " + isApprovedAddr + ". Errorcode = " + e.getCode()
				+ " ErrorMessage = " + e.getMessage());
		}

		try {
			klarna.setAddress(Address.IS_SHIPPING, klarnaAddr);
		} catch (final KlarnaException e) {
			fail("prepareKlarnaForReserveAmount() failed - Could not set IS_SHIPPING address for " + countryCode + " and approved = " + isApprovedAddr + ". Errorcode = " + e.getCode()
				+ " ErrorMessage = " + e.getMessage());
		}

		// add some configured test articles
		addKlarnaArticleIncVat_01(klarna);
		// add handling and shipping fee
		addShippingFeeArticle(klarna);
		addHandlingFeeArticle(klarna);

		return klarna;
	}

	/**
	 * Call Klarna API {@link IReserveAmount#reserveAmount(String, Gender, double, EnumSet, int)}
	 * 
	 * @return String[] array with reservation number and order status.
	 */
	private String[] callReserveAmountMethod_01(final Klarna klarna, final String pno, final Gender gender, final double amount, final java.util.EnumSet<Reservation> flags, final int pclass)
		throws KlarnaException {

		return klarna.reserveAmount(
			pno, // "410321-9202" or Date of birth for DE
			gender, // null, the customer is male
			amount, // -1, will calculate the amount using the internal goods list.
			flags, // resflags, // no specific behaviour like TEST_MODE
			pclass // -1, note that this is an invoice purchase. For part payment purchase you will have a pclass object
					// which you use getId() from.
			);

	}

}
