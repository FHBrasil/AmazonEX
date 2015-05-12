/**
 * 
 */
package br.hering.facades.populators;

import de.hybris.platform.catalog.model.classification.ClassAttributeAssignmentModel;
import de.hybris.platform.catalog.model.classification.ClassificationClassModel;
import de.hybris.platform.classification.features.Feature;
import de.hybris.platform.classification.features.FeatureList;
import de.hybris.platform.commercefacades.product.converters.populator.ProductFeatureListPopulator;
import de.hybris.platform.commercefacades.product.data.ClassificationData;
import de.hybris.platform.commercefacades.product.data.FeatureData;
import de.hybris.platform.commercefacades.product.data.FeatureUnitData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author franthescollymaneira
 *
 */
public class HeringProductFeatureListPopulator extends ProductFeatureListPopulator<FeatureList, ProductData>
{
    /* (non-Javadoc)
    * @see de.hybris.platform.commercefacades.product.converters.populator.ProductFeatureListPopulator#populate(de.hybris.platform.classification.features.FeatureList, de.hybris.platform.commercefacades.product.data.ProductData)
    */
   @Override
   public void populate(FeatureList source, ProductData target) throws ConversionException
   {
   	super.populate(source, target);
   	
   	target.setListableClassifications(buildClassificationDataList(source, true));
   }
   
   @Override
	protected List<ClassificationData> buildClassificationDataList(final FeatureList source)
   {
   	return buildClassificationDataList(source, false);
   }
   
   protected List<ClassificationData> buildClassificationDataList(final FeatureList source, boolean onlyListable)
	{
		final List<ClassificationData> result = new ArrayList<ClassificationData>();
		final Map<String, ClassificationData> map = new HashMap<String, ClassificationData>();

		for (final Feature feature : source.getFeatures())
		{
			if (feature.getValues() != null && !feature.getValues().isEmpty())
			{
				ClassAttributeAssignmentModel assignment = feature.getClassAttributeAssignment();
				if(onlyListable && !Boolean.TRUE.equals(assignment.getListable())) 
				{
					continue;
				}
				
				final ClassificationData classificationData;
				final ClassificationClassModel classificationClass = assignment.getClassificationClass();
				final String classificationClassCode = classificationClass.getCode();
				if (map.containsKey(classificationClassCode))
				{
					classificationData = map.get(classificationClassCode);
				}
				else
				{
					classificationData = getClassificationConverter().convert(classificationClass);

					map.put(classificationClassCode, classificationData);
					result.add(classificationData);
				}

				// Create the feature
				final FeatureData newFeature = getFeatureConverter().convert(feature);

				//XXX temporario
				if("Peso".equals(newFeature.getName())) 
				{
					final FeatureUnitData featureUnitData = new FeatureUnitData();
					featureUnitData.setSymbol("Kg");
					newFeature.setFeatureUnit(featureUnitData);
				}
				
				// Add the feature to the classification
				if (classificationData.getFeatures() == null)
				{
					classificationData.setFeatures(new ArrayList<FeatureData>(1));
				}
				classificationData.getFeatures().add(newFeature);
			}
		}

		return result.isEmpty() ? null : result;
	}
}
