/**
 * 
 */
package br.flieger.storecatalogfeed.xml.template;

import de.hybris.platform.acceleratorservices.urlresolver.SiteBaseUrlResolutionService;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commerceservices.stock.CommerceStockService;
import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.StandardDateRange;
import de.hybris.platform.variants.model.VariantProductModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jdom.Document;
import org.jdom.Element;

import br.flieger.storecatalogfeed.utils.XMLUtils;

/**
 * @author franthescolly
 * 
 */
public class AllProductsFromCatalogTemplate implements XMLTemplate<ProductModel> {

    public static final String CODE = "allProducts";
    protected static final int PRICE_SCALE = 2;
    protected static final RoundingMode PRICE_ROUND_MODE = RoundingMode.DOWN;
    protected static final BigDecimal DEFAULT_PARCEL_QUANTITY = BigDecimal.valueOf(5);
    protected static final DateFormat GOOGLE_DF = new SimpleDateFormat("yyyy-MM-dd");
    protected static final DecimalFormat decimalFormat = new DecimalFormat("#0.00",
            new DecimalFormatSymbols(Locale.ENGLISH));
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
//    @Resource
//    protected VariantsUtils variantsUtils;


    /*
     * (non-Javadoc)
     * 
     * @see de.fliegersoftware.catalog.xml.XMLTemplate#apply(java.lang.Object)
     */
    @Override
    public Document apply(Set<ProductModel> products) {
        final Element catalog = new Element("catalog");
        final Document document = new Document(catalog);
//        final XMLUtils xmlUtils = new XMLUtils();
//        addStoreElements(xmlUtils, catalog, products.iterator().next().getCatalogVersion());
//        for (final ProductModel product : products) {
//            try {
//                final Element rootElement = new Element("product");
//                BigDecimal price = getPrice(product.getEurope1Prices());
//                Integer installment = price == null ? new Integer(0) : new Integer(
//                        calculateAvailableInstallments(price.doubleValue()));
//                CatalogModel cv = product.getCatalogVersion().getCatalog();
//                BaseStoreModel store = cv.getBaseStores().iterator().next();
//                rootElement.addContent(xmlUtils.createElement("brand", store.getUid(), false));
//                rootElement.addContent(xmlUtils.createElement("isParentProduct",
//                        String.valueOf(isBaseProduct(product)), false));
//                rootElement.addContent(xmlUtils.createElement("lastmod",
//                        getFormattedModifiedTime(product), true));
//                rootElement.addContent(xmlUtils.createElement("url", getUrl(product), false));
//                rootElement.addContent(xmlUtils.createElement("code", product.getCode(), false));
//                rootElement.addContent(xmlUtils.createElement("baseProductCode",
//                        getBaseProduct(product).getCode(), false));
//                rootElement.addContent(xmlUtils.createElement("name", product.getName(), false));
//                rootElement.addContent(xmlUtils.createElement("description",
//                        product.getDescription(), false));
//                rootElement.addContent(xmlUtils.createElement("categoryCode",
//                        getCategoryCode(product.getSupercategories()), false));
//                rootElement.addContent(xmlUtils.createElement("category",
//                        getCategory(product.getSupercategories()), false));
//                rootElement
//                        .addContent(xmlUtils.createElement("image", getImageUrl(product), false));
//                rootElement.addContent(xmlUtils.createElement("image_dimensions",
//                        getImageDimensions(product.getPicture()), false));
//                rootElement.addContent(xmlUtils.createElement("thumbnail",
//                        getThumbnailUrl(product), false));
//                rootElement.addContent(xmlUtils.createElement("price", formatPrice(price), false));
//                rootElement.addContent(xmlUtils.createElement("oldPrice",
//                        formatPrice(getOldPrice(product)), false));
//                rootElement.addContent(xmlUtils.createElement("parcelQty", installment.toString(),
//                        false));
//                rootElement.addContent(xmlUtils.createElement(
//                        "parcelAmount",
//                        formatPrice(getParcelAmount(price,
//                                BigDecimal.valueOf(installment.intValue()))), false));
//                rootElement.addContent(xmlUtils.createElement("manufacturer",
//                        product.getManufacturerName(), false));
//                rootElement.addContent(xmlUtils.createElement("ean", product.getEan(), false));
//                rootElement.addContent(xmlUtils.createElement("gender", getGender(product), false));
//                rootElement.addContent(xmlUtils.createElement("stock",
//                        getVariantStock(product, store), false));
//                rootElement.addContent(xmlUtils.createElement("size", getSize(product), false));
//                rootElement.addContent(xmlUtils.createElement("color", getColor(product), false));
//                rootElement.addContent(xmlUtils.createElement("weight", getWeight(product)
//                        .toString(), false));
//                rootElement.addContent(xmlUtils.createElement("sale_price", getSalePrice(product),
//                        false));
//                rootElement.addContent(xmlUtils.createElement("sale_date",
//                        getSaleDateRange(product), false));
//                rootElement.addContent(xmlUtils.createElement("firstVariantPrice",
//                        getFirstVariantPrice(product), false));
//                rootElement.addContent(xmlUtils.createElement("productTotalStock",
//                        getProductTotalStock(product), false));
//                final Element additionalImagesElement = new Element("additionalImages");
//                for (MediaModel additionalImage : getAdditionalImages(product)) {
//                    Element imageElement = new Element("image");
//                    imageElement.addContent(xmlUtils.createElement("additionalUrl",
//                            getImageUrl(additionalImage), false));
//                    imageElement.addContent(xmlUtils.createElement("additionalImageDimensions",
//                            getImageDimensions(additionalImage), false));
//                    additionalImagesElement.addContent(imageElement);
//                }
//                rootElement.addContent(additionalImagesElement);
//                StringBuilder colors = new StringBuilder(StringUtils.join(
//                        getAllVariantsAvailableColors(product).toArray(), ","));
//                rootElement.addContent(xmlUtils.createElement("availableVariantColors", colors,
//                        false));
//                StringBuilder sizes = new StringBuilder(StringUtils.join(
//                        getAllVariantsAvailableSizes(product).toArray(), ","));
//                rootElement.addContent(xmlUtils
//                        .createElement("availableVariantSizes", sizes, false));
//                catalog.addContent(rootElement);
//            } catch (final Exception e) {
//                e.printStackTrace();
//                System.out.println(CODE + " Template error: " + e.getMessage());
//            }
//        }
//        products = null;
//        Runtime.getRuntime().gc();
        return document;
    }


    /**
     * @param xmlUtils
     * @param rootElement
     * @param catalogVersion
     */
    protected void addStoreElements(XMLUtils xmlUtils, final Element rootElement,
            CatalogVersionModel catalogVersion) {
        BaseStoreModel store = catalogVersion.getCatalog().getBaseStores().iterator().next();
        BaseSiteModel site = store.getCmsSites().iterator().next();
        final String urlForSite = siteBaseUrlResolutionService
                .getWebsiteUrlForSite(site, false, "");
        String name = StringUtils.capitalize(site.getUid());
        String title = name + " store products";
        String description = name + " store product feed for ";
        rootElement.addContent(xmlUtils.createElement("storeUrl", urlForSite, false));
        rootElement.addContent(xmlUtils.createElement("storeTitle", title, false));
        rootElement.addContent(xmlUtils.createElement("storeName", name, false));
        rootElement.addContent(xmlUtils.createElement("storeDescription", description, false));
    }


    /**
     * 
     * @param product
     * @return
     */
    protected boolean isBaseProduct(final ProductModel product) {
        return false;
//        (product instanceof VariantProductModel && ((VariantProductModel) product)
//                .getBaseProduct() == null) || product instanceof HeringProductModel;
    }


    /**
     * 
     * @param product
     * @return
     */
    protected ProductModel getBaseProduct(final ProductModel product) {
        return null;
//        product instanceof HeringProductModel ? product
//                : getBaseProduct(((VariantProductModel) product).getBaseProduct());
    }


    /**
     * @param product
     * @return
     */
    protected String getFormattedModifiedTime(final ProductModel product) {
        final Date modifiedtime = product.getModifiedtime();
        return modifiedtime != null ? GOOGLE_DF.format(modifiedtime) + "T00:00:00+00:00" : "";
    }


    /**
     * @param product
     * @return
     */
    protected String getImageUrl(final ProductModel product) {
//        if (product instanceof HeringSizeVariantProductModel) {
//            return getImageUrl(((HeringSizeVariantProductModel) product).getBaseProduct()
//                    .getPicture());
//        }
//        return getImageUrl(product.getPicture());
        return "";
    }


    /**
     * 
     * @param product
     * @return
     */
    protected String getThumbnailUrl(final ProductModel product) {
//        if (product instanceof HeringSizeVariantProductModel) {
//            return getImageUrl(((HeringSizeVariantProductModel) product).getBaseProduct()
//                    .getThumbnail());
//        }
//        return getImageUrl(product.getThumbnail());
        return "";
    }


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
     * 
     * @param product
     * @return
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
     * @return
     */
    protected String getImageDimensions(final MediaModel image) {
        if (image != null && image.getMediaFormat() != null
                && image.getMediaFormat().getQualifier() != null) {
            return image.getMediaFormat().getQualifier().replaceAll("(?i)w", "")
                    .replaceAll("(?i)h", "");
        }
        return "";
    }


    protected String formatPrice(BigDecimal price) {
        return price == null ? "" : decimalFormat.format(price);
    }


    protected BigDecimal getParcelAmount(BigDecimal price, BigDecimal parcelQty) {
        return price == null || price.doubleValue() == 0.0 ? null : price.divide(parcelQty,
                PRICE_SCALE, PRICE_ROUND_MODE);
    }


    protected BigDecimal getPrice(final Collection<PriceRowModel> prices) {
        if (CollectionUtils.isNotEmpty(prices)) {
            Iterator<PriceRowModel> priceIterator = prices.iterator();
            for (; priceIterator.hasNext();) {
                PriceRowModel actualPrice = priceIterator.next();
                BigDecimal price = BigDecimal.valueOf(actualPrice.getPrice().doubleValue());
                return price;
            }
        }
        return null;
    }


    protected BigDecimal getOldPrice(final ProductModel product) {
//        return product.getOldPrice() != null ? product.getOldPrice().setScale(PRICE_SCALE,
//                PRICE_ROUND_MODE) : null;
        return BigDecimal.ZERO;
    }


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
     * @param categories
     * @return
     */
    protected String getCategoryCode(final Collection<CategoryModel> categories) {
        if (CollectionUtils.isNotEmpty(categories)) {
            return categories.iterator().next().getCode();
        }
        return null;
    }


    /**
     * @param product
     * @return urlForSite
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
     * @param product
     * @return
     */
    protected String getGender(final ProductModel product) {
//        String genders = "";
//        HeringProductModel heringProduct = (HeringProductModel) getBaseProduct(product);
//        for (Gender g : heringProduct.getGenders()) {
//            genders += g.name() + "/";
//        }
//        genders = genders.trim().substring(0, genders.length() - 1);
//        return genders;
        return "";
    }


    /**
     * 
     * @param product
     * @return
     */
    protected String getSize(final ProductModel product) {
//        if (product instanceof HeringSizeVariantProductModel) {
//            return ((HeringSizeVariantProductModel) product).getSize();
//        }
        return "";
    }


    /**
     * 
     * @param product
     * @return
     */
    protected String getColor(final ProductModel product) {
//        if (product instanceof HeringSizeVariantProductModel) {
//            return ((HeringSizeVariantProductModel) product).getColorFullDescription() != null ? ((HeringSizeVariantProductModel) product)
//                    .getColorFullDescription().replace(" ", "/") : "";
//        }
        return "";
    }


    /**
     * 
     * @param product
     * @param store
     * @return
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
     * @return
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
     * @return
     */
    protected String getWeight(final ProductModel product) {
//        if (product instanceof HeringSizeVariantProductModel) {
//            return decimalFormat.format(((HeringSizeVariantProductModel) product).getWeight());
//        }
        return "";
    }


    /**
     * 
     * @param product
     * @return
     */
    protected String getSalePrice(final ProductModel product) {
        PriceRowModel priceModel = product.getEurope1Prices().iterator().hasNext() ? product
                .getEurope1Prices().iterator().next() : null;
        return priceModel != null ? decimalFormat.format(priceModel.getPrice()) : "";
    }


    /**
     * 
     * @param product
     * @return
     */
    protected String getSaleDateRange(final ProductModel product) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        StandardDateRange dateRange = product.getEurope1Prices().iterator().hasNext() ? product
                .getEurope1Prices().iterator().next().getDateRange() : null;
        return dateRange != null ? dateFormat.format(dateRange.getStart()) + "T"
                + timeFormat.format(dateRange.getStart()) + "/"
                + dateFormat.format(dateRange.getEnd()) + "T"
                + timeFormat.format(dateRange.getEnd()) + "-0300" : "";
    }


    /**
     * 
     * @param product
     * @return
     */
    protected WarehouseModel getDefaultWarehouse() {
        return warehouseService.getWarehouseForCode("hering_estoque_default");
    }


    /**
     * 
     * @param product
     * @return
     */
    protected Set<String> getAllVariantsAvailableSizes(final ProductModel product) {
//        Set<String> sizes = new HashSet<String>();
//        if (product instanceof HeringProductModel) {
//            List<HeringStyleVariantProductModel> styles = variantsUtils
//                    .getAvailableStyleVariants((HeringProductModel) product);
//            for (HeringStyleVariantProductModel style : styles) {
//                for (VariantProductModel size : style.getVariants()) {
//                    if (variantsUtils.isAvailable(size)) {
//                        sizes.add(((HeringSizeVariantProductModel) size).getSize());
//                    }
//                }
//            }
//        }
//        return sizes;
        return Collections.EMPTY_SET;
    }


    /**
     * 
     * @param product
     * @return
     */
    protected Set<String> getAllVariantsAvailableColors(final ProductModel product) {
//        Set<String> colors = new HashSet<String>();
//        List<HeringStyleVariantProductModel> variantStyles = new ArrayList<HeringStyleVariantProductModel>();
//        if (product instanceof HeringProductModel) {
//            variantStyles = variantsUtils.getAvailableStyleVariants((HeringProductModel) product);
//            for (HeringStyleVariantProductModel variant : variantStyles) {
//                colors.add(variant.getStyle());
//            }
//        }
//        return colors;
        return Collections.EMPTY_SET;
    }


    /**
     * 
     * @param product
     * @return
     */
    protected BigDecimal getFirstVariantPrice(final ProductModel product) {
        Collection<VariantProductModel> variants = product.getVariants();
        Double price = new Double(0.0);
        for (VariantProductModel variant : variants) {
            Collection<VariantProductModel> variantsSecondLevel = variant.getVariants();
            for (VariantProductModel variantSecondLevel : variantsSecondLevel) {
                price = variantSecondLevel.getEurope1Prices().iterator().hasNext() ? variantSecondLevel
                        .getEurope1Prices().iterator().next().getPrice()
                        : null;
                if (price != null && price.doubleValue() > 0) {
                    return new BigDecimal(price.doubleValue()).setScale(PRICE_SCALE,
                            PRICE_ROUND_MODE);
                }
            }
        }
        return new BigDecimal(0.0);
    }


    protected int calculateAvailableInstallments(final double price) {
        if (price < 60)
            return 1;
        if (price >= 60.00 && price < 90.00)
            return 2;
        if (price >= 90.00 && price < 120.00)
            return 3;
        if (price >= 120.00 && price < 150.00)
            return 4;
        return 5;
    }


    /*
     * (non-Javadoc)
     * 
     * @see de.fliegersoftware.catalog.xml.XMLTemplate#getCode()
     */
    @Override
    public String getCode() {
        return CODE;
    }
}