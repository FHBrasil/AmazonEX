/**
 * 
 */
package br.hering.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import br.hering.core.model.ProductMeasurementsModel;
import br.hering.facades.product.data.MeasurementsData;

/**
 * @author franthescollymaneira
 *
 */
public class MeasurementsDataPopulator implements Populator<ProductMeasurementsModel , MeasurementsData>
{

	/* (non-Javadoc)
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(ProductMeasurementsModel source, MeasurementsData target) throws ConversionException
	{
		if(source == null)
		{
			return;
		}
		
		target.setMeasurement(source.getMeasurement().doubleValue());
		target.setSize(source.getSize());
		target.setType(source.getType());
	}
}