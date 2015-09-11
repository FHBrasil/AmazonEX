/**
 * 
 */
package br.hering.storefront.controllers.pages.tools;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.user.UserManager;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.hering.core.model.HeringProductModel;

/**
 * @author franthescollymaneira
 *
 */
@Controller
@RequestMapping("/tool/product")
public class FixProductDateRangeTool extends AbstractPageController
{
	@Resource
	private FlexibleSearchService flexibleSearchService;
	
	@Resource
	private ModelService modelService;
	
	@RequestMapping(value = "date-range", method = RequestMethod.GET)
	public void fixDateRange(HttpServletResponse response) throws Exception
	{
		try
		{
			JaloSession.getCurrentSession().setUser(UserManager.getInstance().getAdminEmployee());
			
			final List<ProductModel> products = findProducts();

			LOG.info("total: " + products.size());
			
			for (final ProductModel baseProduct : products)
			{
				setBaseOnlineDate(baseProduct);
				setBaseOfflineDate(baseProduct);
			}
		}
		catch (final Exception e)
		{
			LOG.error("error", e);
		} 
		finally 
		{
			JaloSession.getCurrentSession().setUser(UserManager.getInstance().getAnonymousCustomer());
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println("Finished");
		}
	}
	
	/**
	 * @return
	 */
	private List<ProductModel> findProducts()
	{
		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery("SELECT DISTINCT {p:PK}  FROM {HeringProduct AS p} WHERE {p:catalogVersion} LIKE '8796093153881'");
		searchQuery.setResultClassList(Arrays.asList(ProductModel.class));
//		searchQuery.setNeedTotal(true);
//		searchQuery.setStart(0);
//		searchQuery.setCount(10); 

		return flexibleSearchService.<ProductModel> search(searchQuery).getResult();
	}
	
	private void setBaseOnlineDate(final ProductModel product)
	{
		if (CollectionUtils.isEmpty(product.getVariants()))
		{
			return;
		}

		if (product instanceof HeringProductModel)
		{
			for (final VariantProductModel variant : product.getVariants())
			{
				setBaseOnlineDate(variant);
			}
		}

		final Date minOnlineDate = getMinOnlineDate(product.getVariants());
		product.setOnlineDate(minOnlineDate);

		LOG.info("result: " + product.getCode() + " : ON " + minOnlineDate);

		modelService.save(product);
		modelService.refresh(product);
	}

	private void setBaseOfflineDate(final ProductModel product)
	{
		if (CollectionUtils.isEmpty(product.getVariants()))
		{
			return;
		}

		if (product instanceof HeringProductModel)
		{
			for (final VariantProductModel variant : product.getVariants())
			{
				setBaseOfflineDate(variant);
			}
		}

		final Date maxOfflineDate = getMaxOfflineDate(product.getVariants());
		product.setOfflineDate(maxOfflineDate);

		LOG.info("result: " + product.getCode() + " : OFF " + maxOfflineDate);
		
		modelService.save(product);
		modelService.refresh(product);
	}

	/**
	 * @param variants
	 * @return
	 */
	private Date getMinOnlineDate(final Collection<VariantProductModel> variants)
	{
		if (CollectionUtils.isEmpty(variants))
		{
			return null;
		}

		Date min = null;
		for (final VariantProductModel variant : variants)
		{
			final Date onlineDate = variant.getOnlineDate();

			LOG.info(variant.getCode() + " : ON " + onlineDate);
			
			if (onlineDate == null)
			{
				return null;
			}

			if (min == null || onlineDate.before(min))
			{
				min = onlineDate;
			}
		}

		return min;
	}

	/**
	 * @param variants
	 * @return
	 */
	private Date getMaxOfflineDate(final Collection<VariantProductModel> variants)
	{
		if (CollectionUtils.isEmpty(variants))
		{
			return null;
		}

		Date max = null;
		for (final VariantProductModel variant : variants)
		{
			final Date offlineDate = variant.getOfflineDate();
			LOG.info(variant.getCode() + " : OFF " + offlineDate);
			
			if (offlineDate == null)
			{
				return null;
			}

			if (max == null || offlineDate.after(max))
			{
				max = offlineDate;
			}
		}

		return max;
	}
}