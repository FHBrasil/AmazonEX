/**
 * 
 */
package br.flieger.storecatalogfeed.xml.template;

import java.util.Set;

import org.jdom.Document;
import org.jdom.Element;

import br.flieger.storecatalogfeed.utils.XMLUtils;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.variants.model.VariantProductModel;


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
public class AllProductCatalogTemplate extends DefaultProductCatalogTemplate {

    public static final String CODE = "allProductCatalogTemplate";
    
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
}