/**
 *
 */
package de.kpfamily.core.model;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;

import javax.annotation.Resource;

import de.kpfamily.core.constants.KpfamilyCoreConstants;
import de.kpfamily.core.enums.ArticleState;
import de.kpfamily.core.util.JaloUtils;


/**
 * @author franthescollymaneira
 *
 */
public class ArticleStateDynamicInternalCode extends AbstractDynamicAttributeHandler<String, ProductModel>
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
	public String get(final ProductModel product)
	{
		final ArticleState articleState = product.getArticleState();
		final Item item = modelService.getSource(articleState);

		return JaloUtils.getAttributeSilently(item, KpfamilyCoreConstants.Attributes.ArticleState.INTERNALCODE);
	}
}