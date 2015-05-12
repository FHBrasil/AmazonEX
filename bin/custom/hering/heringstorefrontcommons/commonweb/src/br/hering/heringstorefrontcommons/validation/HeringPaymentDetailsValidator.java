/**
 * 
 */
package br.hering.heringstorefrontcommons.validation;

import java.util.Calendar;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.hering.heringstorefrontcommons.forms.HeringPaymentDetailsForm;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;


/**
 * @author Antony P
 * @author franthescollymaneira
 */
@Component("heringPaymentDetailsValidator")
public class HeringPaymentDetailsValidator implements Validator
{
	@Resource
	private Validator validator;

	@Override
	public boolean supports(final Class<?> aClass)
	{
		return HeringPaymentDetailsForm.class.equals(aClass);
	}
	
	@Override
	public void validate(Object arg0, Errors arg1)
	{
		// YTODO Auto-generated method stub	
	}

	public void validate(final Object object, final Errors errors, final Model model)
	{
		final HeringPaymentDetailsForm paymentDetailsForm = (HeringPaymentDetailsForm) object;

		if ("CreditCard".equalsIgnoreCase(paymentDetailsForm.getPaymentMode()))
		{
			validator.validate(paymentDetailsForm, errors);

			final Calendar start = parseDate(paymentDetailsForm.getStartMonth(), paymentDetailsForm.getStartYear());
			final Calendar expiration = parseDate(paymentDetailsForm.getExpiryMonth(), paymentDetailsForm.getExpiryYear());

			if (start != null && expiration != null && start.after(expiration))
			{
				errors.rejectValue("startMonth", "payment.startDate.invalid");
				GlobalMessages.addErrorMessage(model, "payment.startDate.invalid");
			}
		}

		final boolean editMode = StringUtils.isNotBlank(paymentDetailsForm.getPaymentId());
		if (editMode || Boolean.FALSE.equals(paymentDetailsForm.getNewBillingAddress()))
		{
			final HeringPaymentBillingAddressValidator heringPaymentBillingAddressValidator = new HeringPaymentBillingAddressValidator();
			heringPaymentBillingAddressValidator.validate(paymentDetailsForm.getBillingAddress(), errors, model);
		}
	}

	protected Calendar parseDate(final String month, final String year)
	{
		if (StringUtils.isNotBlank(month) && StringUtils.isNotBlank(year))
		{
			final Integer yearInt = getIntegerForString(year);
			final Integer monthInt = getIntegerForString(month);

			if (yearInt != null && monthInt != null)
			{
				final Calendar date = getCalendarResetTime();
				date.set(Calendar.YEAR, yearInt.intValue());
				date.set(Calendar.MONTH, monthInt.intValue() - 1);
				date.set(Calendar.DAY_OF_MONTH, 1);
				return date;
			}
		}
		return null;
	}

	protected Calendar getCalendarResetTime()
	{
		final Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}

	/**
	 * Common method to convert a String to an Integer.
	 * 
	 * @param value
	 *           - the String value to be converted.
	 * @return - an Integer object.
	 */
	protected Integer getIntegerForString(final String value)
	{
		if (value != null && !value.isEmpty())
		{
			try
			{
				return Integer.valueOf(value);
			}
			catch (final Exception ignore)
			{
				// Ignore
			}
		}

		return null;
	}
}