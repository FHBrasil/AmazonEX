/**
 *
 */
package br.hering.heringstorefrontcommons.validation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import br.hering.heringstorefrontcommons.forms.HeringGuestForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.GuestValidator;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.util.Config;

/**
 * @author franthescollymaneira
 *
 */
public class HeringGuestValidator extends GuestValidator
{
	public boolean supports(final Class<?> aClass)
	{
		return HeringGuestForm.class.equals(aClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.acceleratorstorefrontcommons.forms.validation.GuestValidator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	public void validate(final Object object, final Errors errors, final Model model)
	{
		final HeringGuestForm heringGuestForm = (HeringGuestForm) object;
		final String title = heringGuestForm.getTitleCode();
		final String firstName = heringGuestForm.getFirstName();
		final String lastName = heringGuestForm.getLastName();
		final String complement = heringGuestForm.getComplement();
		final String street = heringGuestForm.getStreet();
		final String number = heringGuestForm.getNumber();
		final String zipCode = heringGuestForm.getZipCode();
		final String place = heringGuestForm.getPlace();
		final String country = heringGuestForm.getCountry();
		final String telephone = heringGuestForm.getTelephone();
		final String email = heringGuestForm.getEmail();
		
		if (StringUtils.isEmpty(title))
		{
			errors.rejectValue("title", "register.title.invalid");
			GlobalMessages.addErrorMessage(model, "register.title.invalid");
		}
		else if (StringUtils.length(title) > 255)
		{
			errors.rejectValue("title", "register.title.invalid");
			GlobalMessages.addErrorMessage(model, "register.title.invalid");
		}
		
		if (StringUtils.isEmpty(firstName))
		{
			errors.rejectValue("firstName", "register.firstName.invalid");
			GlobalMessages.addErrorMessage(model, "register.firstName.invalid");
		}
		else if (StringUtils.length(firstName) > 255)
		{
			errors.rejectValue("firstName", "register.firstName.invalid");
			GlobalMessages.addErrorMessage(model, "register.firstName.invalid");
		}

		if (StringUtils.isEmpty(lastName))
		{
			errors.rejectValue("lastName", "register.lastName.invalid");
			GlobalMessages.addErrorMessage(model, "register.lastName.invalid");
		}
		else if (StringUtils.length(lastName) > 255)
		{
			errors.rejectValue("lastName", "register.lastName.invalid");
			GlobalMessages.addErrorMessage(model, "register.lastName.invalid");
		}

		if (StringUtils.length(firstName) + StringUtils.length(lastName) > 255)
		{
			errors.rejectValue("lastName", "register.name.invalid");
			errors.rejectValue("firstName", "register.name.invalid");
			GlobalMessages.addErrorMessage(model, "register.name.invalid");
		}
		
		if (StringUtils.isEmpty(complement))
		{
			errors.rejectValue("complement", "register.complement.invalid");
			GlobalMessages.addErrorMessage(model, "register.complement.invalid");
		}
		else if (StringUtils.length(complement) > 255)
		{
			errors.rejectValue("complement", "register.complement.invalid");
			GlobalMessages.addErrorMessage(model, "register.complement.invalid");
		}
		
		if (StringUtils.isEmpty(street))
		{
			errors.rejectValue("street", "register.street.invalid");
			GlobalMessages.addErrorMessage(model, "register.street.invalid");
		}
		else if (StringUtils.length(street) > 255)
		{
			errors.rejectValue("street", "register.street.invalid");
			GlobalMessages.addErrorMessage(model, "register.street.invalid");
		}
		
		if (StringUtils.isEmpty(number))
		{
			errors.rejectValue("number", "register.number.invalid");
			GlobalMessages.addErrorMessage(model, "register.numeber.invalid");
		}
		else if (StringUtils.length(number) > 6)
		{
			errors.rejectValue("number", "register.number.invalid");
			GlobalMessages.addErrorMessage(model, "register.number.invalid");
		}
		
		if (StringUtils.isEmpty(zipCode))
		{
			errors.rejectValue("zipCode", "register.zipCode.invalid");
			GlobalMessages.addErrorMessage(model, "register.zipCode.invalid");
		}
		else if (StringUtils.length(zipCode) > 255)
		{
			errors.rejectValue("zipCode", "register.zipCode.invalid");
			GlobalMessages.addErrorMessage(model, "register.zipCode.invalid");
		}
		
		if (StringUtils.isEmpty(place))
		{
			errors.rejectValue("place", "register.place.invalid");
			GlobalMessages.addErrorMessage(model, "register.place.invalid");
		}
		else if (StringUtils.length(place) > 255)
		{
			errors.rejectValue("place", "register.place.invalid");
			GlobalMessages.addErrorMessage(model, "register.place.invalid");
		}

		if (StringUtils.isEmpty(country))
		{
			errors.rejectValue("country", "register.country.invalid");
			GlobalMessages.addErrorMessage(model, "register.country.invalid");
		}
		else if (StringUtils.length(country) > 255)
		{
			errors.rejectValue("country", "register.country.invalid");
			GlobalMessages.addErrorMessage(model, "register.country.invalid");
		}
		
		if (StringUtils.isEmpty(telephone))
		{
			errors.rejectValue("telephone", "register.telephone.invalid");
			GlobalMessages.addErrorMessage(model, "register.telephone.invalid");
		}
		else if (StringUtils.length(telephone) > 255)
		{
			errors.rejectValue("telephone", "register.telephone.invalid");
			GlobalMessages.addErrorMessage(model, "register.telephone.invalid");
		}
		
		if (StringUtils.isEmpty(email))
		{
			errors.rejectValue("email", "register.email.invalid");
			GlobalMessages.addErrorMessage(model, "register.email.invalid");
		}
		else if (StringUtils.length(email) > 255)
		{
			errors.rejectValue("email", "register.email.invalid");
			GlobalMessages.addErrorMessage(model, "register.email.invalid");
		}
		
//		final HeringGuestForm guestForm = (HeringGuestForm) object;
//
//		final String email = guestForm.getEmail();
//		
//		final String cpfcnpj = guestForm.getCpfcnpj();
//
//
//		final String birthday = guestForm.getBirthday();
//
//		if (StringUtils.isEmpty(email))
//		{
//			errors.rejectValue("email", "profile.email.invalid");
//			GlobalMessages.addErrorMessage(model, "profile.email.invalid");
//		}
//		else if (StringUtils.length(email) > 255 || !Pattern.matches(EMAIL_REGEX, email))
//		{
//			errors.rejectValue("email", "profile.email.invalid");
//			GlobalMessages.addErrorMessage(model, "profile.email.invalid");
//		}
//
//		if (Config.getBoolean("fliegercommerce.feature.enable.cpf", false))
//		{
//			if (StringUtils.isBlank(cpfcnpj))
//			{
//				errors.rejectValue("cpfcnpj", "register.cpfcnpj.invalid");
//				GlobalMessages.addErrorMessage(model, "register.cpfcnpj.invalid");
//			}
//			else
//			{
//				if (cpfcnpj.equals("00000000000") || cpfcnpj.equals("11111111111") || cpfcnpj.equals("22222222222")
//						|| cpfcnpj.equals("33333333333") || cpfcnpj.equals("44444444444") || cpfcnpj.equals("55555555555")
//						|| cpfcnpj.equals("66666666666") || cpfcnpj.equals("77777777777") || cpfcnpj.equals("88888888888")
//						|| cpfcnpj.equals("99999999999") || (cpfcnpj.length() != 11) || !cpfValid(cpfcnpj))
//				{
//					errors.rejectValue("cpfcnpj", "register.cpfcnpj.invalid");
//					GlobalMessages.addErrorMessage(model, "register.cpfcnpj.invalid");
//				}
//			}
//		}
//		
//		try
//		{
//			boolean containError = false;
//			if (StringUtils.isEmpty(birthday))
//			{
//				containError = true;
//			}
//			else
//			{
//				final int dia = Integer.valueOf(birthday.substring(0, 2)).intValue();
//				final int mes = Integer.valueOf(birthday.substring(3, 5)).intValue();
//				final int ano = Integer.valueOf(birthday.substring(6, 10)).intValue();
//				final boolean anoBissexto = ((ano % 4 == 0) || (ano % 100 == 0) || (ano % 400 == 0)) == true ? true : false;
//				final Date birthdateDate = new SimpleDateFormat("dd/MM/yyyy").parse(birthday);
//
//				if (birthdateDate.compareTo(new Date()) >= 0
//						|| (birthday.substring(6, 7) != null && Integer.valueOf(birthday.substring(6, 7)) < 1)
//						|| birthday.length() != 10 || dia > 31)
//				{
//					containError = true;
//				}
//				if (!containError)
//				{
//					if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30)
//					{
//						containError = true;
//					}
//					else if (mes == 2)
//					{
//						if ((!anoBissexto && dia > 28) || dia > 29)
//						{
//							containError = true;
//						}
//					}
//					else if (mes > 12)
//					{
//						containError = true;
//					}
//					else if (ano < 1900)
//					{
//						containError = true;
//					}
//				}
//			}
//			if (containError)
//			{
//				errors.rejectValue("birthday", "register.birthday.invalid");
//				GlobalMessages.addErrorMessage(model, "register.birthday.invalid");
//			}
//
//		}
//		catch (Exception e)
//		{
//			errors.rejectValue("birthday", "register.birthday.invalid");
//			GlobalMessages.addErrorMessage(model, "register.birthday.invalid");
//		}
//	}
//
//	private boolean cpfValid(String strCpf)
//	{
//		int d1, d2;
//		int digito1, digito2, resto;
//		int digitoCPF;
//		String nDigResult;
//
//		d1 = d2 = 0;
//		digito1 = digito2 = resto = 0;
//
//		for (int nCount = 1; nCount < strCpf.length() - 1; nCount++)
//		{
//			digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount)).intValue();
//			d1 = d1 + (11 - nCount) * digitoCPF;
//			d2 = d2 + (12 - nCount) * digitoCPF;
//		}
//
//		resto = (d1 % 11);
//
//		if (resto < 2)
//		{
//			digito1 = 0;
//		}
//		else
//		{
//			digito1 = 11 - resto;
//		}
//
//		d2 += 2 * digito1;
//		resto = (d2 % 11);
//
//		if (resto < 2)
//		{
//			digito2 = 0;
//		}
//		else
//		{
//			digito2 = 11 - resto;
//		}
//
//		String nDigVerific = strCpf.substring(strCpf.length() - 2, strCpf.length());
//		nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
//
//		return nDigVerific.equals(nDigResult);
	}
}
