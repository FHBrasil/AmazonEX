/**
 * 
 */
package br.hering.storefront.forms.validation;

import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.EmailValidator;
import de.hybris.platform.acceleratorstorefrontcommons.forms.UpdateEmailForm;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
/**
 * @author ghayashi
 *
 */
public class HeringEmailValidator extends EmailValidator
{
	public void validate(final Object object, final Errors errors, Model model)
	{
		final UpdateEmailForm updateEmailForm = (UpdateEmailForm) object;
		final String email = updateEmailForm.getEmail();
		final String chkEmail = updateEmailForm.getChkEmail();
		final String password = updateEmailForm.getPassword();

		if (StringUtils.isEmpty(email))
		{
			errors.rejectValue("email", "profile.email.invalid");
			GlobalMessages.addErrorMessage(model, "profile.email.invalid");
		}
		else if (StringUtils.length(email) > 255 || !Pattern.matches(EMAIL_REGEX, email))
		{
			errors.rejectValue("email", "profile.email.invalid");
			GlobalMessages.addErrorMessage(model, "profile.email.invalid");
		}

		if (StringUtils.isEmpty(chkEmail))
		{
			errors.rejectValue("chkEmail", "profile.checkEmail.invalid");
			GlobalMessages.addErrorMessage(model, "profile.checkEmail.invalid");
		}

		if (StringUtils.isEmpty(password))
		{
			errors.rejectValue("password", "profile.pwd.invalid");
			GlobalMessages.addErrorMessage(model, "profile.pwd.invalid");
		}
	}
}
