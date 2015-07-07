/**
 * 
 */
package br.hering.storefront.forms.validation;

import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.RegistrationValidator;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.gov.sintegra.ie.InscricaoEstadual;
import br.gov.sintegra.ie.InscricaoEstadualFactory;
import br.hering.storefront.forms.HeringRegisterForm;
import br.hering.storefront.util.ValidaPessoaUtil;


/**
 * @author franthescollymaneira
 *
 */

@Component("heringRegistrationValidator")
public class HeringRegistrationValidator extends RegistrationValidator
{
	private static final String MESSAGE_REGISTER_CNPJ_INVALID = "register.cnpj.invalid";
	private static final String MESSAGE_REGISTER_CPF_INVALID = "register.cpf.invalid";
	
	private static final String MESSAGE_REGISTER_IE_INVALID = "register.rgIe.invalid";
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.acceleratorstorefrontcommons.forms.validation.RegistrationValidator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> aClass)
	{
		return HeringRegisterForm.class.equals(aClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.acceleratorstorefrontcommons.forms.validation.RegistrationValidator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	public void validate(Object object, Errors errors, Model model)
	{
		final HeringRegisterForm heringRegisterForm = (HeringRegisterForm) object;
		final String firstName = heringRegisterForm.getFirstName();
		final String lastName = heringRegisterForm.getLastName();
		final String email = heringRegisterForm.getEmail();
		final String pwd = heringRegisterForm.getPwd();
		final String checkPwd = heringRegisterForm.getCheckPwd();
//		final String cpfCnpj = heringRegisterForm.getCpfcnpj();
//		final String rgIe = heringRegisterForm.getRgIe();
//		final String ufIe = heringRegisterForm.getUfIe();
//		boolean isJuridica = heringRegisterForm.getPessoaFisica()==null?false:"false".equals(heringRegisterForm.getPessoaFisica());
		
//		final String ERROR_MSG = isJuridica ? MESSAGE_REGISTER_CNPJ_INVALID : MESSAGE_REGISTER_CPF_INVALID;
		
//		final String birthday = heringRegisterForm.getBirthday();
		
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
		
//		if(StringUtils.isBlank(cpfCnpj))
//		{
//			errors.rejectValue("cpfcnpj", ERROR_MSG);
//			GlobalMessages.addErrorMessage(model, ERROR_MSG);
//		}else if(StringUtils.length(cpfCnpj) != 11 && StringUtils.length(cpfCnpj) != 14){
//			errors.rejectValue("cpfcnpj", ERROR_MSG);
//			GlobalMessages.addErrorMessage(model, ERROR_MSG);
//		}
//		else
//		{
//			if (cpfCnpj.equals("00000000000") || cpfCnpj.equals("11111111111") || cpfCnpj.equals("22222222222")
//					|| cpfCnpj.equals("33333333333") || cpfCnpj.equals("44444444444") || cpfCnpj.equals("55555555555")
//					|| cpfCnpj.equals("66666666666") || cpfCnpj.equals("77777777777") || cpfCnpj.equals("88888888888")
//					|| cpfCnpj.equals("99999999999") || 
//					(StringUtils.length(cpfCnpj) == 11 && !ValidaPessoaUtil.cpfValid(cpfCnpj)) ||
//					(StringUtils.length(cpfCnpj) == 14 && !ValidaPessoaUtil.isCnpjValido(cpfCnpj)))
//			{
//				errors.rejectValue("cpfcnpj", ERROR_MSG);
//				GlobalMessages.addErrorMessage(model, ERROR_MSG);
//			}
//			
//			if(StringUtils.length(cpfCnpj) == 14){
//				isJuridica = true;
//			}
//		}
		

//		 if(isJuridica)
//		{
//			 //Verifica se o campo de i.e. ou uf está preechido e se estiver faz 
//			 //a valida��o
//			if(!StringUtils.isEmpty(rgIe) && !StringUtils.isEmpty(ufIe))
//			{
//				InscricaoEstadual ie = InscricaoEstadualFactory.getInstance(ufIe.substring(3));
//				boolean valido = ie.validar(rgIe);
//	   		
//	   		if(!valido){
//	      		errors.rejectValue("rgIe", MESSAGE_REGISTER_IE_INVALID);
//	   			GlobalMessages.addErrorMessage(model, MESSAGE_REGISTER_IE_INVALID);
//	   		}
//			}
//   		
//		}

		if (StringUtils.isEmpty(email))
		{
			errors.rejectValue("email", "register.email.invalid");
			GlobalMessages.addErrorMessage(model, "register.email.invalid");
		}
		else if (StringUtils.length(email) > 255 || !validateEmailAddress(email))
		{
			errors.rejectValue("email", "register.email.invalid");
			GlobalMessages.addErrorMessage(model, "register.email.invalid");
		}

		if (StringUtils.isEmpty(pwd))
		{
			errors.rejectValue("pwd", "register.pwd.invalid");
			GlobalMessages.addErrorMessage(model, "register.pwd.invalid");
		}
		else if (StringUtils.length(pwd) < 6 || StringUtils.length(pwd) > 15)
		{
			errors.rejectValue("pwd", "register.pwd.invalid");
			GlobalMessages.addErrorMessage(model, "register.pwd.invalid");
		}

		if (StringUtils.isNotEmpty(pwd) && StringUtils.isNotEmpty(checkPwd) && !StringUtils.equals(pwd, checkPwd))
		{
			errors.rejectValue("checkPwd", "validation.checkPwd.equals");
			GlobalMessages.addErrorMessage(model, "validation.checkPwd.equals");
		}
		else
		{
			if (StringUtils.isEmpty(checkPwd))
			{
				errors.rejectValue("checkPwd", "register.checkPwd.invalid");
				GlobalMessages.addErrorMessage(model, "register.checkPwd.invalid");
			}
		}
		
//		try{
//			boolean containError = false;
//			if(StringUtils.isEmpty(birthday)){
//				containError = true;
//			}
//			else{
//				final int dia = Integer.valueOf(birthday.substring(0,2)).intValue();
//				final int mes = Integer.valueOf(birthday.substring(3,5)).intValue();
//				final int ano = Integer.valueOf(birthday.substring(6,10)).intValue();
//				final boolean anoBissexto = ((ano % 4 == 0) || (ano % 100 == 0) || (ano % 400 == 0)) == true ? true : false;
//				final Date birthdateDate = new SimpleDateFormat("dd/MM/yyyy").parse(birthday);
//				
//				if (birthdateDate.compareTo(new Date()) >= 0 || (birthday.substring(6, 7) != null && Integer.valueOf(birthday.substring(6, 7)) < 1)
//   					|| birthday.length() != 10 || dia > 31)
//   			{
//   				containError = true;
//   			}
//   			if(!containError ){
//               if((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30){
//               	containError = true;
//                }else if(mes == 2){
//               	 if ((!anoBissexto && dia > 28) || dia > 29){
//               		 containError = true;
//                	}
//                }else if(mes > 12){
//               	 containError = true;
//                }else if(ano < 1900){
//               	 containError = true;
//                }
//   			}
//			}
//			if(containError){
//				errors.rejectValue("birthday", "register.birthday.invalid");
//				GlobalMessages.addErrorMessage(model, "register.birthday.invalid");
//			}
//			
//		}catch(Exception e){
//			errors.rejectValue("birthday", "register.birthday.invalid");
//			GlobalMessages.addErrorMessage(model, "register.birthday.invalid");
//		}
	}
}
