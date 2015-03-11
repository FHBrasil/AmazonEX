/**
 * 
 */
package br.flieger.storecatalogfeed.xml.template;

import java.util.Collection;
import java.util.Set;

import org.jdom.Document;
import org.jdom.Element;

import br.flieger.storecatalogfeed.utils.XMLUtils;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.variants.model.VariantProductModel;
import de.kpfamily.core.model.BabyartikelSizeVariantProductModel;


/**
 * Builds an XML file, showing every product, including variants, being one node, like the
 * following:
 * <code>
 *   <products>
 *     <product>
 *       [ product or variant nodes ]
 *     </product>
 *   </products>
 * </code>
 * 
 * @author jfelipe
 *
 */
public class BaseProductCatalogTemplate extends DefaultProductCatalogTemplate {

    public static final String CODE = "baseProductCatalogTemplate";
    
    /**
     * 
     * @param products
     *
     * @author jfelipe
     */
    @Override
    public Document createXMLDocument(Set<ProductModel> products) {
        final Element catalog = new Element("productCatalog");
        final Document document = new Document(catalog);
        final XMLUtils xmlUtils = new XMLUtils();
        final BaseStoreModel baseStore = getBaseStore(products.iterator().next());
        // Store information
        addDefaultStoreElements(xmlUtils, catalog, products.iterator().next().getCatalogVersion());
        for (final ProductModel product : products) {
            try {
                final Element rootElement = new Element("product");
                // Product information
                rootElement.addContent(xmlUtils.createElement("last_modification",
                        getFormattedModifiedTime(product), true));
                rootElement.addContent(xmlUtils.createElement("code", product.getCode()));
                rootElement.addContent(xmlUtils.createElement("ean", product.getEan()));
                rootElement.addContent(xmlUtils.createElement("url", getUrl(product)));
                rootElement.addContent(xmlUtils.createElement("name", product.getName()));
                rootElement.addContent(xmlUtils.createElement("description",
                        product.getDescription()));
                // Category / Manufacturer information
                final Element categoriesElement = new Element("categories");
                for(CategoryModel category : product.getSupercategories()) {
                    categoriesElement.addContent(xmlUtils.createElement("category",
                            category.getName()));
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
                // Variants
                Element variantsElement = new Element("variants");
                Collection<VariantProductModel> sizeVariants = product.getVariants();
                for (VariantProductModel variant : sizeVariants) {
                    BabyartikelSizeVariantProductModel sizeVariant;
                    if (!(variant instanceof BabyartikelSizeVariantProductModel)) {
                        continue;
                    }
                    sizeVariant = (BabyartikelSizeVariantProductModel) variant;
                    Element variantElement = new Element("variant");
                    variantElement.addContent(xmlUtils.createElement("variant_code",
                            sizeVariant.getCode()));
                    variantElement.addContent(xmlUtils.createElement("variant_ean",
                            sizeVariant.getEan()));
                    variantElement.addContent(xmlUtils.createElement("variant_size",
                            sizeVariant.getSize()));
                    variantElement.addContent(xmlUtils.createElement("variant_main_image_url",
                            getImageUrl(sizeVariant)));
                    variantElement.addContent(xmlUtils.createElement("variant_price",
                            getProductPrice(sizeVariant)));
                    variantElement.addContent(xmlUtils.createElement("variant_previous_price",
                            getOldPrice(sizeVariant)));
                    variantElement.addContent(xmlUtils.createElement("variant_sale_price",
                            getSalePrice(sizeVariant)));
                    variantElement.addContent(xmlUtils.createElement("variant_sale_date_begin",
                            getSaleDateBegin(sizeVariant)));
                    variantElement.addContent(xmlUtils.createElement("variant_sale_date_end",
                            getSaleDateEnd(sizeVariant)));
                    variantElement.addContent(xmlUtils.createElement("variant_stock_level",
                            getVariantStock(sizeVariant, baseStore)));
                    variantElement.addContent(xmlUtils.createElement("variant_weight",
                            String.valueOf(sizeVariant.getWeight())));
                    variantElement.addContent(xmlUtils.createElement("variant_color", ""));
                    variantElement.addContent(xmlUtils.createElement("variant_color_hexa", ""));
                    final Element variantImagesElement = new Element("variant_images");
                    variantImagesElement.addContent(xmlUtils.createElement("variant_main_image_url",
                            getImageUrl(sizeVariant)));
                    variantImagesElement.addContent(xmlUtils.createElement(
                            "variant_main_image_dimensions",
                            getImageDimensions(sizeVariant.getPicture())));
                    variantImagesElement.addContent(xmlUtils.createElement(
                            "variant_thumbnail_image_url", getThumbnailUrl(sizeVariant)));
                    for (MediaModel variantImage : getAdditionalImages(sizeVariant)) {
                        Element variantImageElement = new Element("variant_additional_image");
                        variantImageElement.addContent(xmlUtils.createElement(
                                "variant_additional_image_url", getImageUrl(variantImage)));
                        variantImageElement.addContent(xmlUtils.createElement(
                                "variant_additional_image_dimensions",
                                getImageDimensions(variantImage)));
                        variantImagesElement.addContent(variantImageElement);
                    }
                    variantElement.addContent(variantImagesElement);
                    variantsElement.addContent(variantElement);
                }
                rootElement.addContent(variantsElement);
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
    
    
}