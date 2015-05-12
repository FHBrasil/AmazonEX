/**
 *
 */
package br.hering.storefront.controllers.misc;

import br.hering.facades.wishlist.data.HeringWishlistData;
import br.hering.facades.wishlist.data.HeringWishlistEntryData;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.hering.facades.wishlist.impl.DefaultHeringWishlistFacade;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import br.hering.storefront.controllers.ControllerConstants;
import br.hering.storefront.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.acceleratorservices.customer.CustomerLocationService;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commerceservices.search.pagedata.PaginationData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.session.SessionService;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author flieger
 *
 */
/**
 * Controller for Add to wishlist.
 */
@Controller
@Scope("tenant")
@RequestMapping(value = "/w")
public class WishListController extends AbstractSearchPageController
{

	protected static final Logger LOG = Logger.getLogger(WishListController.class);
	private static final String PRODUCT_CODE_PATH_VARIABLE_PATTERN = "/{productCode:.*}";
	private static final String WISHLISTPK_PATH_VARIABLE_PATTERN = "/{wishlistPK:.*}";
	private static final String WISHLIST_ENTRIES_CMS_PAGE = "my-wishlist";
	private static final String REDIRECT_LOGIN = REDIRECT_PREFIX + "/login";
	private static final String REDIRECT_MY_ACCOUNT = REDIRECT_PREFIX + "/my-account";
	private static final String WISHLIST_PUBLIC_CMS_PAGE = "public-wishlist";
	
	@Resource(name = "heringWishlistFacade")
	private DefaultHeringWishlistFacade heringWishlistFacade;
	@Resource(name = "productModelUrlResolver")
	private UrlResolver<ProductModel> productModelUrlResolver;
	@Resource(name = "productService")
	private ProductService productService;
	@Resource(name = "accountBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder accountBreadcrumbBuilder;
	@Resource(name = "userFacade")
	protected UserFacade userFacade;
	@Resource(name = "customerLocationService")
	private CustomerLocationService customerLocationService;
	@Resource(name = "sessionService")
	private SessionService sessionService;
	@Resource(name = "customerFacade")
	protected CustomerFacade customerFacade;

	@RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN + "/addToWishlist", method = RequestMethod.GET)
	@RequireHardLogIn
	public String addToWishlist(@PathVariable("productCode") final String code, final HttpServletRequest request,
			final HttpServletResponse response) throws UnsupportedEncodingException
	{


		if (!heringWishlistFacade.hasWishlisEntryForProduct(code) && !userFacade.isAnonymousUser())
		{
			heringWishlistFacade.addWishlistEntry(code);
		}
		else
		{
			request.getSession().setAttribute("productWish", code);
			return REDIRECT_LOGIN;
		}


		final ProductModel productModel = productService.getProductForCode(code);
		final String redirection = checkRequestUrl(request, response, productModelUrlResolver.resolve(productModel));

		return redirection;

	}

	@RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN + "/remove", method = RequestMethod.GET)
	@RequireHardLogIn
	public String removeToWishlist(@PathVariable("productCode") final String code,
			@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "show", defaultValue = "Page") final AbstractSearchPageController.ShowMode showMode,
			@RequestParam(value = "sort", required = false) final String sortCode, final Model model)
			throws UnsupportedEncodingException, CMSItemNotFoundException
	{

		if (heringWishlistFacade.hasWishlisEntryForProduct(code))
		{
			heringWishlistFacade.removeWishlistEntryForProduct(code);
		}

		return REDIRECT_MY_ACCOUNT + "/my-wishlist";

	}

	@RequestMapping(value = WISHLISTPK_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	public String publicWishlist(@PathVariable("wishlistPK") final String wishlistPK,
			@RequestParam(value = "q", required = false) final String searchQuery,
			@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "sort", required = false) final String sortCode, final Model model,
			final HttpServletRequest request, final HttpServletResponse response) throws CMSItemNotFoundException
	{
		// Handle paged search results
		final PaginationData pageableData = createEmptyPagination();
		final SearchPageData<HeringWishlistEntryData> searchPageData = heringWishlistFacade.getPagedWishlistEntriesByPK(wishlistPK);
		final HeringWishlistData wishlist = heringWishlistFacade.getWishlistByPK(wishlistPK);

		pageableData.setTotalNumberOfResults(searchPageData.getResults().size());
		searchPageData.setPagination(pageableData);

		populateModel(model, searchPageData, showMode);

		sessionService.setAttribute("wishlistPK", wishlistPK);

		storeCmsPageInModel(model, getContentPageForLabelOrId(WISHLIST_PUBLIC_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(WISHLIST_PUBLIC_CMS_PAGE));
		//model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.wishlist"));
		model.addAttribute("wishlist", wishlist);
		model.addAttribute("wishPK", wishlistPK);
		model.addAttribute("daysLeft", daysLeft(wishlist.getUser().getBirthday()));
		model.addAttribute("userLocation", customerLocationService.getUserLocation());

		//storeContentPageTitleInModel(model, getPageTitleResolver().resolveContentPageTitle(WISHLIST_PUBLIC_CMS_PAGE));
		model.addAttribute("metaRobots", "no-index,no-follow");
		return ControllerConstants.Views.Pages.Wishlist.PublicWishlistPage;
	}

	protected Integer daysLeft(Date birthday)
	{
		if (birthday == null) {
			return null;
		}
		
		Date currentDate = new Date(System.currentTimeMillis());

		SimpleDateFormat current = new SimpleDateFormat("dd/MM/yyyy");
		String resultCurrent = current.format(currentDate);
		String[] currentDates = resultCurrent.split("/");

		SimpleDateFormat birth = new SimpleDateFormat("dd/MM/yyyy");
		String resultbirthday = birth.format(birthday);
		String[] birthDates = resultbirthday.split("/");


		DateTime begin = new DateTime(Integer.parseInt(currentDates[2]), Integer.parseInt(currentDates[1]),
				Integer.parseInt(currentDates[0]), 0, 0, 0);
		DateTime end = new DateTime(Integer.parseInt(currentDates[2]), Integer.parseInt(birthDates[1]),
				Integer.parseInt(birthDates[0]), 13, 30, 0, 0);

		if (Days.daysBetween(begin, end).getDays() > 0)
		{

			return Days.daysBetween(begin, end).getDays();

		}
		else
		{

			begin = new DateTime(Integer.parseInt(currentDates[2]), Integer.parseInt(currentDates[1]),
					Integer.parseInt(currentDates[0]), 0, 0, 0);
			end = new DateTime(Integer.parseInt(currentDates[2]) + 1, Integer.parseInt(birthDates[1]),
					Integer.parseInt(birthDates[0]), 13, 30, 0, 0);

			return Days.daysBetween(begin, end).getDays();
		}



	}
}
