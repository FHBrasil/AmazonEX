/**
 * 
 */
package br.hering.core.util;

import java.util.Comparator;
import java.util.Map;

import org.apache.log4j.Logger;

import br.hering.core.model.HeringSizeVariantProductModel;
import de.hybris.platform.commercefacades.product.data.ImageData;

/**
 * Class que ira conter o objetos Comparators para ordenacao 
 * das listas.
 * 
 * @author HERING
 *
 */
public final class HeringComparatorFactory
{
	private static final String PRODUCT = "product";

	private static final Logger LOG = Logger.getLogger(HeringComparatorFactory.class);

	private HeringComparatorFactory(){}
	
	
	/**
	 * Metodo que ira retornar o Comparator
	 * para ordenar pelo index da galeria.
	 * @return Comparator
	 */
	public static Comparator getComparatorImageDataIndex(){
		return new Comparator<ImageData>(){
			@Override
			public int compare(final ImageData imageLeft, final ImageData imageRight)
			{
				return imageLeft.getGalleryIndex().compareTo(imageRight.getGalleryIndex());
			}
		};
	}
	
	public static Comparator getComparatorMapKeyProductImageDataIndex(){
		return new Comparator<Map<String, ImageData>>(){
			
			public int compare(final Map<String, ImageData> imageLeft, final Map<String, ImageData> imageRight)
			{
				ImageData imageDataLeft = imageLeft.get(PRODUCT);
				ImageData imageDataRight = imageRight.get(PRODUCT);
				return imageDataRight.getGalleryIndex().compareTo(imageDataLeft.getGalleryIndex());
			}
		};
	}
	
	public static Comparator getComparatorReverseMapKeyProductImageDataIndex(){
		return new Comparator<Map<String, ImageData>>(){
			
			public int compare(final Map<String, ImageData> imageLeft, final Map<String, ImageData> imageRight)
			{

				ImageData imageDataLeft = imageLeft.get(PRODUCT);
				ImageData imageDataRight = imageRight.get(PRODUCT);
			
				if(imageDataLeft != null && imageDataRight != null)
				{
					String nameLeft = StringUtils.getSubNameImagem(imageDataLeft.getUrl());
					String nameRight = StringUtils.getSubNameImagem(imageDataRight.getUrl());		

					return nameLeft.compareTo(nameRight);
				} 
				
				return 0;
			}
		};
	}
	
	public static Comparator getComparatorReverseImageDataIndex(){
		return new Comparator<ImageData>(){
			@Override
			public int compare(final ImageData imageLeft, final ImageData imageRight)
			{
				return imageRight.getGalleryIndex().compareTo(imageLeft.getGalleryIndex());
			}
		};
	}
	
	public  static Comparator<HeringSizeVariantProductModel> getComparatorHeringSizeVariantProductModel(){
		return new Comparator<HeringSizeVariantProductModel>()
				{
					@Override
					public int compare(HeringSizeVariantProductModel s1, HeringSizeVariantProductModel s2)
					{
						return s1.getSize().compareTo(s2.getSize());
					}
				};
	}
	
}
