/**
 * 
 */
package br.hering.storefront.forms.validation;

import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.ProfileValidator;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.gov.sintegra.ie.InscricaoEstadual;
import br.gov.sintegra.ie.InscricaoEstadualFactory;
import br.hering.storefront.forms.HeringUpdateProfileForm;
import br.hering.storefront.util.ValidaPessoaUtil;


/**
 * @author ghayashi
 * 
 */

@Component("heringProfileValidator")
public class HeringProfileValidator extends ProfileValidator
{
	private static final String MESSAGE_PROFILE_CPF_CNPJ_INVALID = "profile.cpfCnpj.invalid";
	
	private static final String MESSAGE_PROFILE_IE_INVALID = "profile.ie.invalid";
	
	@Override
	public boolean supports(final Class<?> aClass)
	{
		return HeringProfileValidator.class.equals(aClass);
	}

	public void validate(final Object object, final Errors errors, Model model)
	{
		final HeringUpdateProfileForm profileForm = (HeringUpdateProfileForm) object;
		final String firstName = profileForm.getFirstName();
		final String lastName = profileForm.getLastName();
		final String cpfCnpj = profileForm.getCpfcnpj();
		final String rgIe = profileForm.getRgIe();
		final String ufIe = profileForm.getUfIe();
		boolean isJuridica = false;

		final String birthday = profileForm.getBirthday();

		if (StringUtils.isEmpty(firstName))
		{
			errors.rejectValue("firstName", "profile.firstName.invalid");
			GlobalMessages.addErrorMessage(model, "profile.firstName.invalid");
		}
		else if (StringUtils.length(firstName) > 255)
		{
			errors.rejectValue("firstName", "profile.firstName.invalid");
			GlobalMessages.addErrorMessage(model, "profile.firstName.invalid");
		}

		if (StringUtils.isEmpty(lastName))
		{
			errors.rejectValue("lastName", "profile.lastName.invalid");
			GlobalMessages.addErrorMessage(model, "profile.lastName.invalid");
		}
		else if (StringUtils.length(lastName) > 255)
		{
			errors.rejectValue("lastName", "profile.lastName.invalid");
			GlobalMessages.addErrorMessage(model, "profile.lastName.invalid");
		}

		if(StringUtils.isBlank(cpfCnpj))
		{
			errors.rejectValue("cpfcnpj", MESSAGE_PROFILE_CPF_CNPJ_INVALID);
			GlobalMessages.addErrorMessage(model, MESSAGE_PROFILE_CPF_CNPJ_INVALID);
		}else if(StringUtils.length(cpfCnpj) != 11 && StringUtils.length(cpfCnpj) != 14){
			errors.rejectValue("cpfcnpj", MESSAGE_PROFILE_CPF_CNPJ_INVALID);
			GlobalMessages.addErrorMessage(model, MESSAGE_PROFILE_CPF_CNPJ_INVALID);
		}
		else
		{
			if (cpfCnpj.equals("00000000000") || cpfCnpj.equals("11111111111") || cpfCnpj.equals("22222222222")
					|| cpfCnpj.equals("33333333333") || cpfCnpj.equals("44444444444") || cpfCnpj.equals("55555555555")
					|| cpfCnpj.equals("66666666666") || cpfCnpj.equals("77777777777") || cpfCnpj.equals("88888888888")
					|| cpfCnpj.equals("99999999999") || 
					(StringUtils.length(cpfCnpj) == 11 && !ValidaPessoaUtil.cpfValid(cpfCnpj)) ||
					(StringUtils.length(cpfCnpj) == 14 && !ValidaPessoaUtil.isCnpjValido(cpfCnpj)))
			{
				errors.rejectValue("cpfcnpj", MESSAGE_PROFILE_CPF_CNPJ_INVALID);
				GlobalMessages.addErrorMessage(model, MESSAGE_PROFILE_CPF_CNPJ_INVALID);
			}
			
			if(ValidaPessoaUtil.isCnpjValido(cpfCnpj)){
				isJuridica = true;
			}
		}
		

		 if(isJuridica)
		{
			 //Verifica se o campo de i.e. e uf estão preechidos.
			 //Se estiverem preenchidos faz a validação
		 if(!StringUtils.isEmpty(rgIe) && !StringUtils.isEmpty(ufIe))
			{
				InscricaoEstadual ie = InscricaoEstadualFactory.getInstance(ufIe.substring(3));
				boolean valido = ie.validar(rgIe);
	   		
	   		if(!valido){
	      		errors.rejectValue("rgIe", MESSAGE_PROFILE_IE_INVALID);
	   			GlobalMessages.addErrorMessage(model, MESSAGE_PROFILE_IE_INVALID);
	   		}
			}
   		
		}
		
		try
		{
			boolean containError = false;
			if (StringUtils.isEmpty(birthday))
			{
				containError = true;
			}
			else
			{
				final int dia = Integer.valueOf(birthday.substring(0, 2)).intValue();
				final int mes = Integer.valueOf(birthday.substring(3, 5)).intValue();
				final int ano = Integer.valueOf(birthday.substring(6, 10)).intValue();
				final boolean anoBissexto = ((ano % 4 == 0) || (ano % 100 == 0) || (ano % 400 == 0)) == true ? true : false;
				final Date birthdateDate = new SimpleDateFormat("dd/MM/yyyy").parse(birthday);

				if (birthdateDate.compareTo(new Date()) >= 0
						|| (birthday.substring(6, 7) != null && Integer.valueOf(birthday.substring(6, 7)) < 1)
						|| birthday.length() != 10 || dia > 31)
				{
					containError = true;
				}
				if (!containError)
				{
					if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30)
					{
						containError = true;
					}
					else if (mes == 2)
					{
						if ((!anoBissexto && dia > 28) || dia > 29)
						{
							containError = true;
						}
					}
					else if (mes > 12)
					{
						containError = true;
					}
					else if (ano < 1900)
					{
						containError = true;
					}
				}
			}
			if (containError)
			{
				errors.rejectValue("birthday", "register.birthday.invalid");
				GlobalMessages.addErrorMessage(model, "register.birthday.invalid");
			}

		}
		catch (Exception e)
		{
			errors.rejectValue("birthday", "register.birthday.invalid");
			GlobalMessages.addErrorMessage(model, "register.birthday.invalid");
		}
	}
	
}
