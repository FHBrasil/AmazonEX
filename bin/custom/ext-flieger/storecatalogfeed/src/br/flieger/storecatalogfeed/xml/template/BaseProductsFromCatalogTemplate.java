/**
 * 
 */
package br.flieger.storecatalogfeed.xml.template;

import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.variants.model.VariantProductModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.jdom.Document;
import org.jdom.Element;

import br.flieger.storecatalogfeed.utils.XMLUtils;

/**
 * @author felipe
 *
 */
public class BaseProductsFromCatalogTemplate extends AllProductsFromCatalogTemplate {

    public static final String CODE = "baseProducts";


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
//                BigDecimal firstVariantPrice = getFirstVariantPrice(product);
//                Integer installment = firstVariantPrice == null ? new Integer(0) : new Integer(
//                        calculateAvailableInstallments(firstVariantPrice.doubleValue()));
//                final Element rootElement = new Element("product");
//                CatalogModel cv = product.getCatalogVersion().getCatalog();
//                BaseStoreModel store = cv.getBaseStores().iterator().next();
//                rootElement.addContent(xmlUtils.createElement("brand", store.getUid(), false));
//                rootElement.addContent(xmlUtils.createElement("isParentProduct",
//                        String.valueOf(isBaseProduct(product)), false));
//                rootElement.addContent(xmlUtils.createElement("lastmod",
//                        getFormattedModifiedTime(product), true));
//                rootElement.addContent(xmlUtils.createElement("url", getUrl(product), false));
//                rootElement.addContent(xmlUtils.createElement("code", product.getCode(), false));
//                rootElement.addContent(xmlUtils.createElement("name", product.getName(), false));
//                rootElement.addContent(xmlUtils.createElement("description",
//                        product.getDescription(), false));
//                rootElement.addContent(xmlUtils.createElement("category",
//                        getCategory(product.getSupercategories()), false));
//                rootElement
//                        .addContent(xmlUtils.createElement("image", getImageUrl(product), false));
//                rootElement.addContent(xmlUtils.createElement("image_dimensions",
//                        getImageDimensions(product.getPicture()), false));
//                rootElement.addContent(xmlUtils.createElement("thumbnail",
//                        getThumbnailUrl(product), false));
//                rootElement.addContent(xmlUtils.createElement("manufacturer",
//                        product.getManufacturerName(), false));
//                rootElement.addContent(xmlUtils.createElement("gender", getGender(product), false));
//                rootElement.addContent(xmlUtils.createElement("firstVariantPrice",
//                        formatPrice(firstVariantPrice), false));
//                rootElement.addContent(xmlUtils.createElement("oldPrice",
//                        formatPrice(getOldPrice(product)), false));
//                rootElement.addContent(xmlUtils.createElement("productTotalStock",
//                        getProductTotalStock(product), false));
//                rootElement.addContent(xmlUtils.createElement("parcelQty", installment.toString(),
//                        false));
//                rootElement.addContent(xmlUtils.createElement(
//                        "parcelAmount",
//                        formatPrice(getParcelAmount(firstVariantPrice,
//                                BigDecimal.valueOf(installment.intValue()))), false));
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
//                Element variantsElement = new Element("variants");
//                List<HeringStyleVariantProductModel> styles = variantsUtils
//                        .getAvailableStyleVariants((HeringProductModel) product);
//                for (HeringStyleVariantProductModel style : styles) {
//                    for (VariantProductModel size : style.getVariants()) {
//                        HeringSizeVariantProductModel sizeVariant;
//                        if (!(size instanceof HeringSizeVariantProductModel)) {
//                            continue;
//                        }
//                        sizeVariant = (HeringSizeVariantProductModel) size;
//                        BigDecimal variantPrice = getPrice(sizeVariant.getEurope1Prices());
//                        Element variantElement = new Element("variant");
//                        variantElement.addContent(xmlUtils.createElement("code",
//                                sizeVariant.getCode(), false));
//                        variantElement.addContent(xmlUtils.createElement("stock",
//                                getVariantStock(sizeVariant, store), false));
//                        variantElement.addContent(xmlUtils.createElement("size",
//                                sizeVariant.getSize(), false));
//                        variantElement.addContent(xmlUtils.createElement("color", style.getStyle(),
//                                false));
//                        variantElement.addContent(xmlUtils.createElement("hexaColor",
//                                sizeVariant.getColorRGB(), false));
//                        variantElement.addContent(xmlUtils.createElement("weight",
//                                getWeight(sizeVariant).toString(), false));
//                        variantElement.addContent(xmlUtils.createElement("price",
//                                formatPrice(variantPrice), false));
//                        variantElement.addContent(xmlUtils.createElement("oldPrice",
//                                formatPrice(getOldPrice(sizeVariant)), false));
//                        variantElement.addContent(xmlUtils.createElement(
//                                "parcelAmount",
//                                formatPrice(getParcelAmount(variantPrice,
//                                        BigDecimal.valueOf(installment.intValue()))), false));
//                        variantElement.addContent(xmlUtils.createElement("sale_price",
//                                getSalePrice(sizeVariant), false));
//                        variantElement.addContent(xmlUtils.createElement("sale_date",
//                                getSaleDateRange(sizeVariant), false));
//                        variantElement.addContent(xmlUtils.createElement("ean",
//                                sizeVariant.getEan(), false));
//                        variantElement.addContent(xmlUtils.createElement("image",
//                                getImageUrl(sizeVariant), false));
//                        variantsElement.addContent(variantElement);
//                    }
//                }
//                rootElement.addContent(variantsElement);
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
}