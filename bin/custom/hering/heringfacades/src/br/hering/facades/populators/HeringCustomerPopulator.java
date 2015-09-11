/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.hering.facades.populators;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import br.hering.core.wishlist.HeringWishlistService;
import br.hering.facades.wishlist.data.HeringWishlistData;
import de.hybris.platform.commercefacades.user.converters.populator.CustomerPopulator;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.util.Config;
import de.hybris.platform.wishlist2.model.Wishlist2Model;


/**
 *
 * @author Vinicius de Souza
 */
public class HeringCustomerPopulator extends CustomerPopulator
{
	 private Converter<Wishlist2Model, HeringWishlistData> heringWishlistConverter;
	 
	 private HeringWishlistService heringWishlistService;

	@Override
	public void populate(final CustomerModel source, final CustomerData target)
	{
		super.populate(source, target);

		target.setGender(source.getGender());
		target.setBirthday(source.getBirthday());
		
		if (Config.getBoolean("fliegercommerce.feature.enable.cpf", false))
		{	
			target.setCpfcnpj(source.getCpfcnpj());
		}
		
		target.setRgIe(source.getRgIe());
		target.setUfIe(source.getUfIe());
		target.setPrimaryKey(source.getPk().getLongValueAsString());
		
		target.setDefaultPhoneNumber(source.getDefaultPhoneNumber());
		
		Date date = source.getYoungestChildDateOfBirth();		 
		try 
		{
			DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			String dateOfBirth = formatter.format(date);
			target.setYoungestChildDateOfBirth(dateOfBirth);
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
		}
		
		if (source.isScheduledNewsletterEnabled()){
			target.setScheduledNewsletterChecked("checked");
		}
		if (source.isTipsNewsletterEnabled()){
			target.setTipsNewsletterChecked("checked");
			target.setTipsNewsletterDisabled("");
		}
		else{
			target.setTipsNewsletterChecked("");
			target.setTipsNewsletterDisabled("disabled");
		}
		if (source.isReviewShoppingExperienceEnabled()){
			target.setReviewShoppingExperienceChecked("checked");
		}
		if (source.isReviewOrderedProductsEnabled()){
			target.setReviewOrderedProductsChecked("checked");
		}
		

		List<String> orders = new LinkedList<String>();
		
		for (OrderModel model : source.getOrders())
		{
			orders.add(model.getCode());
		}
		//Garante a ordenação
		Collections.sort(orders, Collections.reverseOrder());
		target.setOrders(orders);
		
		populateDefaultWishlist(target);
	}

	private void populateDefaultWishlist(final CustomerData target) 
	{
		Wishlist2Model wishlistModel = null;
		
		try {
			wishlistModel = getHeringWishlistService().getDefaultWishlist();
		} catch (final Exception e) {
		}
		
		if(wishlistModel != null)
		{
			final HeringWishlistData defaultWishlist = getHeringWishlistConverter().convert(wishlistModel);
			target.setDefaultWishlist(defaultWishlist);
		}
	}

	public Converter<Wishlist2Model, HeringWishlistData> getHeringWishlistConverter() 
	{
		return heringWishlistConverter;
	}

	@Required
	public void setHeringWishlistConverter(Converter<Wishlist2Model, HeringWishlistData> heringWishlistConverter) 
	{
		this.heringWishlistConverter = heringWishlistConverter;
	}

	public HeringWishlistService getHeringWishlistService() 
	{
		return heringWishlistService;
	}

	@Required
	public void setHeringWishlistService(HeringWishlistService heringWishlistService) 
	{
		this.heringWishlistService = heringWishlistService;
	}
}
