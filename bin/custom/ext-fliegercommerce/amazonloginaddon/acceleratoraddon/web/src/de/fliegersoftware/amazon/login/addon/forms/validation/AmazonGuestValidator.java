package de.fliegersoftware.amazon.login.addon.forms.validation;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import de.fliegersoftware.amazon.login.addon.forms.AmazonGuestForm;

@Component("amazonGuestValidator")
public class AmazonGuestValidator implements Validator {

	public static final String EMAIL_REGEX = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";

	@Override
	public boolean supports(Class<?> clazz) {
		return AmazonGuestForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		final AmazonGuestForm guestForm = (AmazonGuestForm) target;
		final String amazonGuestId = guestForm.getAmazonGuestId();
		final String email = guestForm.getAmazonGuestEmail();

		if (StringUtils.isEmpty(amazonGuestId))
		{
			errors.rejectValue("amazonGuestId", "amazon.profile.amazonid.invalid");
		}
		if (StringUtils.isEmpty(email))
		{
			errors.rejectValue("email", "amazon.profile.email.invalid");
		}
		else if (StringUtils.length(email) > 255 || !Pattern.matches(EMAIL_REGEX, email))
		{
			errors.rejectValue("email", "amazon.profile.email.invalid");
		}
	}
}
