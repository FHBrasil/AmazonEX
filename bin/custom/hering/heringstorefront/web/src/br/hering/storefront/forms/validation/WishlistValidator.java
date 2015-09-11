/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.hering.storefront.forms.validation;


import br.hering.storefront.forms.UpdateWishlistForm;
		 
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author flieger
 */
@Component("wishlistValidator")
public class WishlistValidator implements Validator{
    
    @Override
	public boolean supports(final Class<?> aClass)
	{
		return UpdateWishlistForm.class.equals(aClass);
	}

	@Override
	public void validate(final Object object, final Errors errors)
	{
		final UpdateWishlistForm wishlistForm = (UpdateWishlistForm) object;
		final String name = wishlistForm.getName();
		final String publicName = wishlistForm.getPublicName();
		final String description = wishlistForm.getDescription();
                final Date birthday = wishlistForm.getBirthday();

		if (StringUtils.isEmpty(name))
		{
			errors.rejectValue("name", "wishlist.name.invalid");
		}
		else if (StringUtils.length(name) > 255)
		{
			errors.rejectValue("name", "wishlist.name.invalid");
		}

		if (StringUtils.isEmpty(publicName))
		{
			wishlistForm.setPublicName(name);
		}

		if (StringUtils.isEmpty(description))
		{
			errors.rejectValue("description", "Please enter a wishlist description");
		}
		else if (StringUtils.length(description) > 255)
		{
			errors.rejectValue("description", "Please enter a wishlist description");
		}
                if (birthday == null)
		{
			wishlistForm.setBirthday(birthday);
		}
		 
	} 
        
        
  
}
