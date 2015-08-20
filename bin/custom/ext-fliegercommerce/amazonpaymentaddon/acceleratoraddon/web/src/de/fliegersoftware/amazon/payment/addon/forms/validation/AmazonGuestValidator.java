package de.fliegersoftware.amazon.payment.addon.forms.validation;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import de.fliegersoftware.amazon.payment.addon.forms.AmazonGuestForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.GuestForm;

@Component("amazonGuestValidator")
public class AmazonGuestValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AmazonGuestForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		final AmazonGuestForm guestForm = (AmazonGuestForm) target;
		final String amazonOrderReferenceId = guestForm.getAmazonOrderReferenceId();

		if (StringUtils.isEmpty(amazonOrderReferenceId))
		{
			errors.rejectValue("amazonOrderReferenceId", "amazon.profile.orderreferenceid.invalid");
		}
	}
}
