
package br.hering.facades.checkout.payment;

import java.util.List;

import br.hering.facades.order.data.PaymentModeData;

/**
 * @author Antony P
 *
 */
public interface HeringPaymentModeFacade
{
	PaymentModeData getPaymentModeForCode(final String code);

	List<PaymentModeData> getAllPaymentModes();

	void setPaymentMode(PaymentModeData paymentModeData);
}