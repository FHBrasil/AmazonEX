/**
 * 
 */
package br.hering.heringstorefrontcommons.validation;

import de.hybris.platform.acceleratorstorefrontcommons.forms.AddressForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.AddressValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import com.google.common.base.Strings;

import br.hering.heringstorefrontcommons.forms.HeringAddressForm;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;


/**
 * @author Antony P
 * @author franthescollymaneira
 * 
 */
public class HeringAddressValidator extends AddressValidator
{
	private static final int MAX_CHARS_MEDIUM_FIELD = 50;
	private static final int MAX_CHARS_LARGE_FIELD = 255;
	private static final int MAX_CHARS_PHONE_FIELD = 11;
	private static final int MAX_CHARS_STREET_NUMBER_FIELD = 6;
	private static final int MAX_POSTCODE_LENGTH = 8;
	private static final int MIN_POSTCODE_LENGTH = 8;
	private static final Logger LOG = Logger.getLogger(HeringAddressValidator.class);
	private static Model model;
	
	private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("[0-9]{10,11}");

	private static final int MAX_FIELD_LENGTH = 255;

	@Override
	public boolean supports(final Class<?> aClass)
	{
		return HeringAddressForm.class.equals(aClass);
	}
	
	public void validate(final Object object, final Errors errors, final Model model) 
	{
		HeringAddressValidator.model = model;
		final HeringAddressForm addressForm = (HeringAddressForm) object;
		validateStandardFields(addressForm, errors);
		validateCountrySpecificFields(addressForm, errors);
		//super.validate(object, errors);
	}
	
	protected void validateStandardFields(final HeringAddressForm addressForm, final Errors errors)
	{
		validateStringField(addressForm.getCountryIso(), BrazilianAddressField.COUNTRY, MAX_FIELD_LENGTH, errors);
		validateStringField(addressForm.getFirstName(), BrazilianAddressField.FIRSTNAME, MAX_FIELD_LENGTH, errors);
		validateStringField(addressForm.getLastName(), BrazilianAddressField.LASTNAME, MAX_FIELD_LENGTH, errors);
		validateStringField(addressForm.getLine1(), BrazilianAddressField.LINE1, MAX_FIELD_LENGTH, errors);
		validateStringField(addressForm.getTownCity(), BrazilianAddressField.TOWN, MAX_FIELD_LENGTH, errors);
		validateStringField(addressForm.getPostcode(), BrazilianAddressField.POSTCODE, MAX_POSTCODE_LENGTH, errors);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.acceleratorstorefrontcommons.forms.validation.AddressValidator#validateCountrySpecificFields
	 * (de.hybris.platform.acceleratorstorefrontcommons.forms.AddressForm, org.springframework.validation.Errors)
	 */
	@Override
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
			validatePostalCode(addressForm.getPostcode(), BrazilianAddressField.POSTCODE, MAX_POSTCODE_LENGTH, MIN_POSTCODE_LENGTH,
					errors);

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

	protected static void validateStringField(final String addressField, final BrazilianAddressField fieldType,
			final int maxFieldLength, final Errors errors)
	{
		if (addressField == null || StringUtils.isEmpty(addressField) || (StringUtils.length(addressField) > maxFieldLength))
		{
			errors.rejectValue(fieldType.getFieldKey(), fieldType.getErrorKey());
			GlobalMessages.addErrorMessage(model, fieldType.getErrorKey());
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

	protected static void validatePhoneNumber(final String addressField,
	        final BrazilianAddressField fieldType, final int maxFieldLength,
	        final Errors errors) 
	{	
		final Matcher matcher = PHONE_NUMBER_PATTERN.matcher(addressField);
		
 		if (Strings.isNullOrEmpty(addressField)
 				|| !matcher.matches()
 				|| (StringUtils.length(addressField) > maxFieldLength)) {
 			errors.rejectValue(fieldType.getFieldKey(), fieldType.getErrorKey());
 			GlobalMessages.addErrorMessage(model, fieldType.getErrorKey());
 		} else {
 			LOG.info("Does phone number field match the pattern?[" + matcher.matches() + "]");
 		}
 		LOG.info("addressField:[" + addressField + "]");
	}

	protected static void validatePostalCode(final String postalCodeField, final BrazilianAddressField fieldType,
			final int maxFieldLength, final int minPostcodeLength, final Errors errors)
	{
		final String pattern = "[0-9]{8}";
		final Pattern r = Pattern.compile(pattern);
		final Matcher m = r.matcher(postalCodeField);
		LOG.info("m.matches():[" + m.matches() + "]");
		LOG.info("addressField:[" + postalCodeField + "]");
		if (postalCodeField == null || StringUtils.isEmpty(postalCodeField))
		{
			//Already verified on hybris Address Validator
		}
		else
		{

			if (!m.matches())
			{
				errors.rejectValue(fieldType.getFieldKey(), fieldType.getErrorKey());
				GlobalMessages.addErrorMessage(model, fieldType.getErrorKey());
			}
		}
	}
	
	

	protected enum BrazilianAddressField
	{
		STREETNUMBER("number", "address.number.invalid"), NEIGHBORHOOD("neighborhood", "address.district.invalid"), PHONE("phone",
				"address.phone1.invalid"), CELLPHONE("celPhone", "address.phone2.invalid"), REFERENCE("reference",
				"address.remarks.invalid"), RECEIVER("receiver", "address.receiver.invalid"), POSTCODE("postcode",
				"address.postcode.invalid.cep"), REGION("regionIso", "address.regionIso.invalid"), COUNTRY("countryIso", "address.country.invalid"),
				FIRSTNAME("firstName", "address.firstName.invalid"), LINE1("line1", "address.line1.invalid"),
				LASTNAME("lastName", "address.lastName.invalid"), TOWN("townCity", "address.townCity.invalid") ;

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