package de.kpfamily.core.translators;

import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.impex.jalo.translators.AbstractValueTranslator;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

public class KPCountryTypeTranslator extends AbstractValueTranslator {


	@Override
	public String exportValue(Object arg0)
			throws JaloInvalidParameterException {
		return String.valueOf(arg0);
	}

	
	@Override
	public Object importValue(String input, Item item)
			throws JaloInvalidParameterException {
		final CommonI18NService commonI18NService = (CommonI18NService)
				Registry.getApplicationContext().getBean("commonI18NService");
		CountryModel country = commonI18NService.getCountry(input
				.toUpperCase());
		return country.getPk();
	}

}