/**
 * 
 */
package br.flieger.storecatalogfeed.xml.template;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jdom.Document;
import org.jdom.Element;

import br.flieger.storecatalogfeed.utils.XMLUtils;
import de.hybris.platform.acceleratorservices.urlresolver.SiteBaseUrlResolutionService;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commerceservices.stock.CommerceStockService;
import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.StandardDateRange;
import de.hybris.platform.variants.model.VariantProductModel;
import de.kpfamily.core.model.BabyartikelSizeVariantProductModel;

/**
 * Builds an default XML file with base products as parent nodes, and variant products as child 
 * nodes, like the following:
 * <code>
 *   <products>
 *     <products>
 *       [ product nodes ]
 *       <variants>
 *         <variant>
 *           [ variant nodes]
 *         </variant>
 *       </variants>
 *     </products>
 *   </products>
 * </code>
 * 
 * @author jfelipe
 *
 */
public class DefaultProductCatalogTemplate implements XMLTemplate<ProductModel> {

    public static final String CODE = "defaultProductCatalogTemplate";
    
    protected static final int PRICE_SCALE = 2;
    protected static final RoundingMode PRICE_ROUND_MODE = RoundingMode.DOWN;
    protected static final BigDecimal DEFAULT_PARCEL_QUANTITY = BigDecimal.valueOf(5);
    protected static final DateFormat GOOGLE_DF = new SimpleDateFormat("yyyy-MM-dd");
    @Resource(name = "productService")
    protected ProductService productService;
    @Resource
    protected StockService stockService;
    @Resource
    protected CommerceStockService commerceStockService;
    @Resource
    protected WarehouseService warehouseService;
    @Resource
    protected UrlResolver<ProductModel> productModelUrlResolver;
    @Resource
    protected SiteBaseUrlResolutionService siteBaseUrlResolutionService;


    // @Resource
    // protected VariantsUtils variantsUtils;
    /**
     * 
     * 
     * @author jfelipe
     */
    @Override
    public Document apply(Set<ProductModel> products) {
        return createXMLDocument(products);
    }


    /**
     * 
     * @param products
     * @return
     *
     * @author jfelipe
     */
    @Override
    public Document createXMLDocument(Set<ProductModel> products) {
        final Element catalog = new Element("productCatalog");
        final Document document = new Document(catalog);
        final XMLUtils xmlUtils = new XMLUtils();
        // Store information
        addDefaultStoreElements(xmlUtils, catalog, products.iterator().next().getCatalogVersion());
        for (final ProductModel product : products) {
            try {
                final Element rootElement = new Element("product");
                // Product information
                rootElement.addContent(xmlUtils.createElement("last_modification",
                        getFormattedModifiedTime(product), true));
                rootElement.addContent(xmlUtils.createElement("is_base_product",
                        isBaseProduct(product)));
                rootElement.addContent(xmlUtils.createElement("code", product.getCode()));
                rootElement.addContent(xmlUtils.createElement("base_product_code",
                        getBaseProductCode(product)));
                rootElement.addContent(xmlUtils.createElement("ean", product.getEan()));
                rootElement.addContent(xmlUtils.createElement("url", getUrl(product)));
                rootElement.addContent(xmlUtils.createElement("name", product.getName()));
                rootElement.addContent(xmlUtils.createElement("description",
                        product.getDescription()));
                // Category / Manufacturer information
                final Element categoriesElement = new Element("categories");
                for(CategoryModel category : product.getSupercategories()) {
                    final Element categoryElement = xmlUtils.createElement("category",
                            category.getName());
                    categoryElement.setAttribute("code", category.getCode());
                    categoriesElement.addContent(categoryElement);
                }
                rootElement.addContent(categoriesElement);
                rootElement.addContent(xmlUtils.createElement("gender", getGender(product)));
                rootElement.addContent(xmlUtils.createElement("manufacturer",
                    product.getManufacturerName()));
                // Image information
                final Element imagesElement = new Element("images");
                imagesElement.addContent(xmlUtils.createElement("main_image_url",
                        getImageUrl(product)));
                imagesElement.addContent(xmlUtils.createElement("main_image_dimensions",
                        getImageDimensions(product.getPicture())));
                imagesElement.addContent(xmlUtils.createElement("thumbnail_image_url",
                        getThumbnailUrl(product)));
                for (MediaModel image : getAdditionalImages(product)) {
                    Element imageElement = new Element("additional_image");
                    imageElement.addContent(xmlUtils.createElement("additional_image_url",
                            getImageUrl(image)));
                    imageElement.addContent(xmlUtils.createElement("additional_image_dimensions",
                            getImageDimensions(image)));
                    imagesElement.addContent(imageElement);
                }
                rootElement.addContent(imagesElement);
                // Price information
                rootElement.addContent(xmlUtils.createElement("price",
                        getProductPrice(product)));
                rootElement.addContent(xmlUtils.createElement("previous_price",
                        getOldPrice(product)));
                rootElement.addContent(xmlUtils.createElement("sale_price", getSalePrice(product)));
                rootElement.addContent(xmlUtils.createElement("sale_date_begin",
                        getSaleDateBegin(product)));
                rootElement.addContent(xmlUtils.createElement("sale_date_end",
                        getSaleDateEnd(product)));
                // TODO: ver como fica o esquema de envio
                // ----
                // Installments information
                // TODO: ver como vai ficar o esquema de parcelas
                // rootElement.addContent(xmlUtils.createElement("installments_quantity",
                // installment.toString()));
                // rootElement.addContent(xmlUtils.createElement("installments_amount",
                // formatPrice(getParcelAmount(firstVariantPrice,
                // BigDecimal.valueOf(installment.intValue())))));
                // Stock information
                rootElement.addContent(xmlUtils.createElement("stock_level",
                        getProductTotalStock(product)));
                rootElement.addContent(xmlUtils.createElement("size", ""));
                rootElement.addContent(xmlUtils.createElement("weight",
                        String.valueOf(product.getWeight())));
                rootElement.addContent(xmlUtils.createElement("color", ""));
                rootElement.addContent(xmlUtils.createElement("hexa_color", ""));
                catalog.addContent(rootElement);
            } catch (final Exception e) {
                e.printStackTrace();
                System.out.println(CODE + " Template error: " + e.getMessage());
            }
        }
        products = null;
        Runtime.getRuntime().gc();
        return document;
    }


    /**
     * 
     * @param product
     *
     */
    protected BaseStoreModel getBaseStore(ProductModel product) {
        CatalogModel catalog = product.getCatalogVersion().getCatalog();
        BaseStoreModel baseStore = catalog.getBaseStores().iterator().next();
        return baseStore;
    }


    /**
     * 
     * @param xmlUtils
     * @param rootElement
     * @param catalogVersion
     * 
     */
    protected void addDefaultStoreElements(XMLUtils xmlUtils, final Element rootElement,
            CatalogVersionModel catalogVersion) {
        BaseStoreModel currentStore = catalogVersion.getCatalog().getBaseStores().iterator().next();
        BaseSiteModel currentSite = currentStore.getCmsSites().iterator().next();
        final String urlForCurrentSite = siteBaseUrlResolutionService.getWebsiteUrlForSite(
                currentSite, false, "");
        String siteName = StringUtils.capitalize(currentSite.getUid());
        String siteTitle = siteName + " store products";
        String siteDescription = siteName + " store product feed for ";
        rootElement.addContent(xmlUtils.createElement("storeUrl", urlForCurrentSite, false));
        rootElement.addContent(xmlUtils.createElement("storeTitle", siteTitle, false));
        rootElement.addContent(xmlUtils.createElement("storeName", siteName, false));
        rootElement.addContent(xmlUtils.createElement("storeDescription", siteDescription, false));
    }


    /**
     * 
     * @param product
     *
     */
    protected String getUrl(final ProductModel product) {
        final String productURL = productModelUrlResolver.resolve(product);
        BaseStoreModel store = product.getCatalogVersion().getCatalog().getBaseStores().iterator()
                .next();
        BaseSiteModel site = store.getCmsSites().iterator().next();
        final String urlForSite = siteBaseUrlResolutionService.getWebsiteUrlForSite(site, false,
                productURL);
        return urlForSite;
    }


    /**
     * 
     * @param categories
     *
     */
    protected String getCategory(final Collection<CategoryModel> categories) {
        if (CollectionUtils.isNotEmpty(categories)) {
            Iterator<CategoryModel> categoryIterator = categories.iterator();
            for (; categoryIterator.hasNext();) {
                CategoryModel actualCategory = categoryIterator.next();
                return actualCategory.getName();
            }
        }
        return null;
    }


    /**
     * 
     * @param mediaModel
     *
     */
    protected String getImageUrl(MediaModel mediaModel) {
        if (mediaModel == null) {
            return null;
        }
        final String urlPrefix = Config.getParameter("website.img");
        final String urlForSite = urlPrefix + mediaModel.getURL();
        return urlForSite;
    }


    /**
     * 
     * @param product
     *
     */
    protected String getImageUrl(final ProductModel product) {
        if (product instanceof BabyartikelSizeVariantProductModel) {
            return getImageUrl(((BabyartikelSizeVariantProductModel) product).getBaseProduct()
                    .getPicture());
        }
        return getImageUrl(product.getPicture());
    }


    /**
     * 
     * @param product
     *
     */
    protected String getThumbnailUrl(final ProductModel product) {
        if (product instanceof BabyartikelSizeVariantProductModel) {
            return getImageUrl(((BabyartikelSizeVariantProductModel) product).getBaseProduct()
                    .getThumbnail());
        }
        return getImageUrl(product.getThumbnail());
    }


    /**
     * 
     * @param image
     *
     */
    protected String getImageDimensions(final MediaModel image) {
        if (image != null && image.getMediaFormat() != null
                && image.getMediaFormat().getQualifier() != null) {
            return image.getMediaFormat().getQualifier().replaceAll("(?i)w", "")
                    .replaceAll("(?i)h", "");
        }
        return "";
    }


    /**
     * 
     * @param product
     *
     */
    protected String getProductPrice(final ProductModel product) {
        Collection prices = product.getEurope1Prices();
        if (CollectionUtils.isNotEmpty(prices)) {
            Iterator<PriceRowModel> priceIterator = prices.iterator();
            if (priceIterator.hasNext()) {
                PriceRowModel actualPrice = priceIterator.next();
                BigDecimal price = BigDecimal.valueOf(actualPrice.getPrice().doubleValue());
                return formatPrice(price);
            }
        }
        return null;
    }


    /**
     * 
     * @param price
     *
     */
    protected String formatPrice(BigDecimal price) {
        // English locale because of decimal point
        final DecimalFormat decimalFormat = new DecimalFormat("#0.00", new DecimalFormatSymbols(
                Locale.ENGLISH));
        return price == null ? "" : decimalFormat.format(price);
    }


    /**
     * 
     * @param product
     *
     */
    protected String getOldPrice(final ProductModel product) {
        // English locale because of decimal point
        final DecimalFormat decimalFormat = new DecimalFormat("#0.00", new DecimalFormatSymbols(
                Locale.ENGLISH));
        final double price = product.getPreviousPrice();
        return decimalFormat.format(price);
    }


    /**
     * 
     * @param product
     *
     */
    protected String getFormattedModifiedTime(final ProductModel product) {
        final Date modifiedtime = product.getModifiedtime();
        return modifiedtime != null ? GOOGLE_DF.format(modifiedtime) + "T00:00:00+00:00" : "";
    }


    /**
     * 
     * @param product
     * @param store
     *
     */
    protected Integer getVariantStock(final ProductModel product, BaseStoreModel store) {
        if (commerceStockService.isStockSystemEnabled(store)) {
            return new Integer(commerceStockService.getStockLevelForProductAndBaseStore(product,
                    store).intValue());
        } else {
            return new Integer(0);
        }
    }


    /**
     * 
     * @param product
     *
     */
    protected Set<MediaModel> getAdditionalImages(final ProductModel product) {
        Set<MediaModel> urls = new HashSet<MediaModel>();
        for (MediaContainerModel media : product.getGalleryImages()) {
            urls.addAll(media.getMedias());
        }
        return urls;
    }


    /**
     * 
     * @param product
     * 
     */
    protected String getGender(final ProductModel product) {
        // String genders = "";
        // for (Gender g : product.getGenders()) {
        // genders += g.name() + "/";
        // }
        // genders = genders.trim().substring(0, genders.length() - 1);
        // return genders;
        return "";
    }


    /**
     * 
     * @param product
     * 
     */
    public Integer getProductTotalStock(final ProductModel product) {
        int stock = 0;
        Collection<VariantProductModel> variants = product.getVariants();
        for (VariantProductModel variant : variants) {
            Collection<VariantProductModel> variantsSecondLevel = variant.getVariants();
            for (VariantProductModel variantSecondLevel : variantsSecondLevel) {
                Collection<StockLevelModel> stockLevels = stockService
                        .getAllStockLevels(variantSecondLevel);
                for (StockLevelModel stockLevel : stockLevels) {
                    stock += stockLevel.getAvailable();
                }
            }
        }
        return new Integer(stock);
    }


    /**
     * 
     * @param product
     * 
     */
    protected String getSalePrice(final ProductModel product) {
        // English locale because of decimal point
        final DecimalFormat decimalFormat = new DecimalFormat("#0.00", new DecimalFormatSymbols(
                Locale.ENGLISH));
        PriceRowModel priceModel = product.getEurope1Prices().iterator().hasNext() ? product
                .getEurope1Prices().iterator().next() : null;
        return priceModel != null ? decimalFormat.format(priceModel.getPrice()) : "";
    }


    /**
     * 
     * @param product
     * 
     */
    protected String getSaleDateBegin(final ProductModel product) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        StandardDateRange dateRange = product.getEurope1Prices().iterator().hasNext() ? product
                .getEurope1Prices().iterator().next().getDateRange() : null;
        return dateRange != null ? dateFormat.format(dateRange.getStart()) + "T"
                + timeFormat.format(dateRange.getStart()) : "";
    }
    
    
    /**
     * 
     * @param product
     *
     */
    protected String getSaleDateEnd(final ProductModel product) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        StandardDateRange dateRange = product.getEurope1Prices().iterator().hasNext() ? product
                .getEurope1Prices().iterator().next().getDateRange() : null;
        return dateRange != null ? dateFormat.format(dateRange.getEnd()) + "T"
                + timeFormat.format(dateRange.getEnd()) + "-0300" : "";
    }
    
    
    /**
     * 
     * @param product
     * @return the base product code
     *
     * @author jfelipe
     */
    protected String getBaseProductCode(final ProductModel product) {
        String baseProductCode = "";
        if(product instanceof VariantProductModel) {
            baseProductCode = ((VariantProductModel) product).getBaseProduct().getCode();
        }
        return baseProductCode;
    }
    
    
    /**
     * 
     * @param product
     * @return true if is base product, false otherwise
     *
     * @author jfelipe
     */
    protected String isBaseProduct(final ProductModel product) {
        if(product instanceof VariantProductModel) {
            return String.valueOf(false);
        }
        return String.valueOf(true);
    }


    /**
     * 
     */
    @Override
    public String getCode() {
        return CODE;
    }
}
