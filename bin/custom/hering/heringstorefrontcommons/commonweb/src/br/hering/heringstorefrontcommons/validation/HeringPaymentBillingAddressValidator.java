/**
 *
 */
package br.hering.heringstorefrontcommons.validation;

import de.hybris.platform.acceleratorstorefrontcommons.forms.AddressForm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.hering.heringstorefrontcommons.forms.HeringAddressForm;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;


/**
 * @author Antony P
 *
 */
@Component("heringPaymentBillingAddressValidator")
public class HeringPaymentBillingAddressValidator implements Validator
{
	private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("[0-9]{10,11}");

	private static final int MAX_CHARS_MEDIUM_FIELD = 50;
	private static final int MAX_CHARS_LARGE_FIELD = 255;
	private static final int MAX_CHARS_PHONE_FIELD = 11;
	private static final int MAX_CHARS_STREET_NUMBER_FIELD = 6;
	private static final int MAX_POSTCODE_LENGTH = 8;
	private static Model model;
	
	@Resource
	private HeringAddressValidator addressValidator;	

	@Override
	public boolean supports(final Class<?> aClass)
	{
		return AddressForm.class.equals(aClass);
	}

	public void validate(final Object object, final Errors errors, final Model model)
	{
		HeringPaymentBillingAddressValidator.model = model;
		final AddressForm addressForm = (AddressForm) object;
		validateStandardFields(addressForm, errors);
		//validateCountrySpecificFields(addressForm, errors);
	}

	@Override
	public void validate(final Object arg0, final Errors arg1)
	{
		// YTODO Auto-generated method stub
	}

	protected void validateStandardFields(final AddressForm addressForm, final Errors errors)
	{
		validateStringField(addressForm.getCountryIso(), BrazilianAddressField.COUNTRY, MAX_CHARS_LARGE_FIELD, errors);
		validateStringField(addressForm.getFirstName(), BrazilianAddressField.FIRSTNAME, MAX_CHARS_LARGE_FIELD, errors);
		validateStringField(addressForm.getLastName(), BrazilianAddressField.LASTNAME, MAX_CHARS_LARGE_FIELD, errors);
		validateStringField(addressForm.getLine1(), BrazilianAddressField.LINE1, MAX_CHARS_LARGE_FIELD, errors);
		validateStringField(addressForm.getTownCity(), BrazilianAddressField.TOWN, MAX_CHARS_LARGE_FIELD, errors);
		validatePostalCode(addressForm.getPostcode(), BrazilianAddressField.POSTCODE, MAX_POSTCODE_LENGTH, errors);
		
		final HeringAddressForm heringForm = (HeringAddressForm) addressForm;
		validateStringField(heringForm.getNumber(), BrazilianAddressField.STREETNUMBER, MAX_CHARS_STREET_NUMBER_FIELD, errors);		
		if (StringUtils.isBlank(heringForm.getReceiver()))
		{
			heringForm.setReceiver(heringForm.getFirstName() + " " + heringForm.getLastName());
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.acceleratorstorefrontcommons.forms.validation.AddressValidator#validateCountrySpecificFields
	 * (de.hybris.platform.acceleratorstorefrontcommons.forms.AddressForm, org.springframework.validation.Errors)
	 */
	protected void validateCountrySpecificFields(final AddressForm addressForm, final Errors errors)
	{
		if (StringUtils.equalsIgnoreCase("de", addressForm.getCountryIso()))
		{

			final HeringAddressForm heringForm = (HeringAddressForm) addressForm;

			validateStringField(heringForm.getNumber(), BrazilianAddressField.STREETNUMBER, MAX_CHARS_STREET_NUMBER_FIELD, errors);
			validateStringField(heringForm.getNeighborhood(), BrazilianAddressField.NEIGHBORHOOD, MAX_CHARS_MEDIUM_FIELD, errors);
			validatePhoneNumber(heringForm.getPhone(), BrazilianAddressField.PHONE, MAX_CHARS_PHONE_FIELD, errors);

			//validateStringField(heringForm.getReceiver(), BrazilianAddressField.RECEIVER, MAX_CHARS_MEDIUM_FIELD, errors);
			if (StringUtils.isBlank(heringForm.getReceiver()))
			{
				heringForm.setReceiver(heringForm.getFirstName() + " " + heringForm.getLastName());
			}

			validateFieldNotNull(addressForm.getRegionIso(), BrazilianAddressField.REGION, errors);

			if (StringUtils.isNotBlank(heringForm.getReference()))
			{
				validateStringField(heringForm.getReference(), BrazilianAddressField.REFERENCE, MAX_CHARS_LARGE_FIELD, errors);
			}
			if (StringUtils.isNotBlank(heringForm.getCelPhone()))
			{
				validatePhoneNumber(heringForm.getCelPhone(), BrazilianAddressField.CELLPHONE, MAX_CHARS_PHONE_FIELD, errors);
			}
		}
	}

	protected static void validateFieldNotNull(final String addressField, final BrazilianAddressField fieldType,
			final Errors errors)
	{
		if (addressField == null)
		{
			errors.rejectValue(fieldType.getFieldKey(), fieldType.getErrorKey());
			GlobalMessages.addErrorMessage(model, fieldType.getErrorKey());
		}
	}

	protected static void validateStringField(final String addressField, final BrazilianAddressField fieldType,
			final int maxFieldLength, final Errors errors)
	{
		if (addressField == null || StringUtils.isEmpty(addressField) || (StringUtils.length(addressField) > maxFieldLength))
		{
			errors.rejectValue(fieldType.getFieldKey(), fieldType.getErrorKey());
			GlobalMessages.addErrorMessage(model, fieldType.getErrorKey());
		}
	}

	protected static void validatePhoneNumber(final String addressField, final BrazilianAddressField fieldType,
			final int maxFieldLength, final Errors errors)
	{
		final Matcher m = PHONE_NUMBER_PATTERN.matcher(addressField);

		if (!m.matches() || addressField == null || StringUtils.isEmpty(addressField)
				|| (StringUtils.length(addressField) > maxFieldLength))
		{
			errors.rejectValue(fieldType.getFieldKey(), fieldType.getErrorKey());
			GlobalMessages.addErrorMessage(model, fieldType.getErrorKey());
		}
	}

	protected static void validatePostalCode(final String postalCodeField, final BrazilianAddressField fieldType,
			final int maxFieldLength, final Errors errors)
	{

		final String pattern = "[0-9]{8}";
		final Pattern r = Pattern.compile(pattern);
		final Matcher m = r.matcher(postalCodeField);

		if (!m.matches() || postalCodeField == null || StringUtils.isEmpty(postalCodeField)
				|| (StringUtils.length(postalCodeField) > maxFieldLength))
		{
			errors.rejectValue(fieldType.getFieldKey(), fieldType.getErrorKey());
			GlobalMessages.addErrorMessage(model, fieldType.getErrorKey());
		}
	}

	protected enum BrazilianAddressField
	{
		STREETNUMBER("billingAddress.number", "address.number.invalid"), NEIGHBORHOOD("billingAddress.neighborhood",
				"address.district.invalid"), PHONE("billingAddress.phone", "address.phone1.invalid"), CELLPHONE(
				"billingAddress.celPhone", "address.phone2.invalid"), REFERENCE("billingAddress.reference", "address.remarks.invalid"), RECEIVER(
				"billingAddress.receiver", "address.receiver.invalid"), FIRSTNAME("billingAddress.firstName",
				"address.firstName.invalid"), LASTNAME("billingAddress.lastName", "address.lastName.invalid"), LINE1(
				"billingAddress.line1", "address.line1.invalid"), LINE2("billingAddress.line2", "address.line2.invalid"), TOWN(
				"billingAddress.townCity", "address.townCity.invalid"), POSTCODE("billingAddress.postcode",
				"address.postcode.invalid"), REGION("billingAddress.regionIso", "address.regionIso.invalid"), COUNTRY(
				"billingAddress.countryIso", "address.country.invalid");

		private final String fieldKey;
		private final String errorKey;

		private BrazilianAddressField(final String fieldKey, final String errorKey)
		{
			this.fieldKey = fieldKey;
			this.errorKey = errorKey;
		}

		public String getFieldKey()
		{
			return fieldKey;
		}

		public String getErrorKey()
		{
			return errorKey;
		}
	}
}