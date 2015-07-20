/**
 * 
 */
package br.hering.storefront.forms.validation;

import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.PasswordValidator;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import de.hybris.platform.acceleratorstorefrontcommons.forms.UpdatePasswordForm;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.hering.storefront.forms.HeringRegisterForm;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;

/**
 * @author ghayashi
 *
 */
@Component("heringPasswordValidator")
public class HeringPasswordValidator implements Validator
{
	@Override
	public boolean supports(Class<?> aClass)
	{
		return UpdatePasswordForm.class.equals(aClass);
	}
	
	public void validate(final Object object, final Errors errors, Model model)
	{
		final UpdatePasswordForm passwordForm = (UpdatePasswordForm) object;
		final String currPasswd = passwordForm.getCurrentPassword();
		final String newPasswd = passwordForm.getNewPassword();
		final String checkPasswd = passwordForm.getCheckNewPassword();

		if (StringUtils.isEmpty(currPasswd))
		{
			errors.rejectValue("currentPassword", "profile.currentPassword.invalid");
			GlobalMessages.addErrorMessage(model, "profile.currentPassword.invalid");
		}

		if (StringUtils.isEmpty(newPasswd))
		{
			errors.rejectValue("newPassword", "updatePwd.pwd.invalid");
			GlobalMessages.addErrorMessage(model, "updatePwd.pwd.invalid");
		}
		else if (StringUtils.length(newPasswd) < 6 || StringUtils.length(newPasswd) > 15)
		{
			errors.rejectValue("newPassword", "updatePwd.pwd.invalid");
			GlobalMessages.addErrorMessage(model, "updatePwd.pwd.invalid");
		}
		
		if (StringUtils.isNotEmpty(newPasswd) && StringUtils.isNotEmpty(checkPasswd) && !StringUtils.equals(newPasswd, checkPasswd))
		{
			errors.rejectValue("checkNewPassword", "updatePwd.pwd.check.checkPwd.invalid");
			GlobalMessages.addErrorMessage(model, "updatePwd.pwd.check.checkPwd.invalid");
		}
		else if (StringUtils.length(checkPasswd) < 6 || StringUtils.length(checkPasswd) > 15)
		{
			errors.rejectValue("newPassword", "updatePwd.pwd.invalid");
			GlobalMessages.addErrorMessage(model, "updatePwd.pwd.invalid");
		}
		else
		{
			if (StringUtils.isEmpty(checkPasswd))
			{
				errors.rejectValue("checkNewPassword", "updatePwd.pwd.check.pwd.invalid");
				GlobalMessages.addErrorMessage(model, "updatePwd.pwd.check.pwd.invalid");
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object arg0, Errors arg1)
	{
		// YTODO Auto-generated method stub
		
	}
}
