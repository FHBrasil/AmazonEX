/**
 *
 */
package br.hering.facades.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.wishlist2.enums.Wishlist2EntryPriority;
import de.hybris.platform.wishlist2.model.Wishlist2EntryModel;

import br.hering.facades.wishlist.data.HeringWishlistEntryData;
import br.hering.facades.wishlist.data.HeringWishlistEntryPriorityData;

/**
 * @author flieger
 *
 */
public class HeringWishlistEntryPopulator implements Populator<Wishlist2EntryModel, HeringWishlistEntryData> {

    private Converter<ProductModel, ProductData> productConverter;
    private Converter<Wishlist2EntryPriority, HeringWishlistEntryPriorityData> heringWishlistEntryPriorityConverter;

    /*
     * (non-Javadoc)
     * 
     * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
     */
    @Override
    public void populate(final Wishlist2EntryModel source, final HeringWishlistEntryData target) throws ConversionException {
        
        target.setAddedDate(source.getAddedDate());
        target.setComment(source.getComment());
        target.setDesired(source.getDesired().intValue());
        target.setProduct(productConverter.convert(source.getProduct()));
        if (source.getReceived() != null) {
            target.setReceived(source.getReceived().intValue());
        }
        target.setPriority(heringWishlistEntryPriorityConverter.convert(source.getPriority()));
        
    }

    /**
     * @return the productConverter
     */
    public Converter<ProductModel, ProductData> getProductConverter() {
        return productConverter;
    }

    /**
     * @param productConverter the productConverter to set
     */
    public void setProductConverter(final Converter<ProductModel, ProductData> productConverter) {
        this.productConverter = productConverter;
    }

    /**
     * @return the heringWishlistEntryPriorityConverter
     */
    public Converter<Wishlist2EntryPriority, HeringWishlistEntryPriorityData> getHeringWishlistEntryPriorityConverter() {
        return heringWishlistEntryPriorityConverter;
    }

    /**
     * @param heringWishlistEntryPriorityConverter the
     * heringWishlistEntryPriorityConverter to set
     */
    public void setHeringWishlistEntryPriorityConverter(
            final Converter<Wishlist2EntryPriority, HeringWishlistEntryPriorityData> heringWishlistEntryPriorityConverter) {
        this.heringWishlistEntryPriorityConverter = heringWishlistEntryPriorityConverter;
    }
}
