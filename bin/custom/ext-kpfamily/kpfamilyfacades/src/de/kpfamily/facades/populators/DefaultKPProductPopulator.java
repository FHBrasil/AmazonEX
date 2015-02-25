package de.kpfamily.facades.populators;

import java.math.BigDecimal;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.impl.AbstractPopulatingConverter;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.kpfamily.core.model.BabyartikelProductModel;


/**
 * 
 * @author jfelipe
 *
 */
public class DefaultKPProductPopulator
        extends AbstractPopulatingConverter<BabyartikelProductModel, ProductData> {
    
    /**
     * 
     * @author jfelipe
     */
    @Override
    public void populate(BabyartikelProductModel source, ProductData target) {
        super.populate(source, target);
        target.setAverageRating(source.getReviewsAverageRating());
        target.setCode(source.getCode());
        target.setDescription(source.getDescription());
        target.setManufacturer(source.getManufacturerName());
        target.setName(source.getName());
        target.setReviewsTotalCount(source.getReviewsTotalCount());
        target.setShortDescription(source.getShortDescription());
        target.setSummary(source.getSummary());
        if(!source.getEurope1Prices().isEmpty()) {
            PriceRowModel priceRow = source.getEurope1Prices().iterator().next();
            BigDecimal price = new BigDecimal(priceRow.getPrice());
            Integer priceEuro = price.intValue();
            Integer priceCents = price.remainder(BigDecimal.ONE)
                    .setScale(2, BigDecimal.ROUND_HALF_UP).movePointRight(2).intValue();
            target.setPriceEuro(priceEuro);
            target.setPriceCents(priceCents);
        }
    }
    
}
