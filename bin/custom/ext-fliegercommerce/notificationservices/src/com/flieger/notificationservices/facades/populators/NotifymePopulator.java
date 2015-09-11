/**
 * 
 */
package com.flieger.notificationservices.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.flieger.notificationservices.data.NotifymeData;
import com.flieger.notificationservices.model.NotifymeModel;


/**
 * @author Vinicius de Souza
 * 
 */
public class NotifymePopulator implements Populator<NotifymeModel, NotifymeData>
{
	@Override
	public void populate(final NotifymeModel source, final NotifymeData target) throws ConversionException
	{
		target.setBaseStore(source.getBaseStore().getUid());
		target.setCode(source.getProduct().getCode());
		target.setName(source.getName());
		target.setEmail(source.getEmail());
		target.setCreated(source.getCreated());
		target.setNotified(source.getNotified());
		target.setExpired(source.getExpired());
		target.setDays(source.getDays());
	}
}