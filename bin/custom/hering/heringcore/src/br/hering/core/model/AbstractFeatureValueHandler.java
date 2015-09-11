/**
 * 
 */
package br.hering.core.model;

import de.hybris.platform.classification.ClassificationService;
import de.hybris.platform.classification.features.Feature;
import de.hybris.platform.classification.features.FeatureList;
import de.hybris.platform.core.model.product.ProductModel;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

/**
 * @author franthescollymaneira
 *
 */
public abstract class AbstractFeatureValueHandler<T>
{
	protected final Logger LOG = Logger.getLogger(this.getClass());

	@Resource
	private ClassificationService classificationService;
	
	protected T getFeatureValue(final ProductModel product, final String featureName)
	{
		final FeatureList featureList = classificationService.getFeatures(product);

		final Feature feature = featureList.getFeatureByName(featureName);

		if (feature == null || feature.getValue() == null)
		{
			return null;
		}
		
		Object value = feature.getValue().getValue();
		
		if(value == null)
		{
			return null;
		}
		
		return (T) value;
	}
}