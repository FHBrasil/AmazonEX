/**
 *
 */
package de.kpfamily.core.model;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

import javax.annotation.Resource;

import de.kpfamily.constants.KpfamilycoreConstants;
import de.kpfamily.core.jalo.JaloUtils;
import de.kpfamily.enums.ArticleState;


/**
 * @author franthescollymaneira
 *
 */
public class ArticleStateDynamicInternalCode implements DynamicAttributeHandler<String, BabyartikelProductModel>
{
	@Resource
	private ModelService modelService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler#get(de.hybris.platform.servicelayer.model
	 * .AbstractItemModel)
	 */
	@Override
	public String get(final BabyartikelProductModel product)
	{
		final ArticleState articleState = product.getArticleState();
		final Item item = modelService.getSource(articleState);

		return JaloUtils.getAttributeSilently(item, KpfamilycoreConstants.Attributes.ArticleState.INTERNALCODE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler#set(de.hybris.platform.servicelayer.model
	 * .AbstractItemModel, java.lang.Object)
	 */
	@Override
	public void set(final BabyartikelProductModel product, final String value)
	{
		throw new UnsupportedOperationException();
	}
}