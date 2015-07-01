package de.kpfamily.facades.populators;

import java.math.BigDecimal;
import java.util.Collection;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.commercefacades.converter.impl.DefaultConfigurablePopulator;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;

/**
 * 
 * @author jfelipe
 *
 */
public class DefaultKPProductPopulator extends
        DefaultConfigurablePopulator<ProductModel, ProductData, ProductOption> {

    
    /**
     * 
     * @author jfelipe
     */
    @Override
    public void populate(ProductModel source, ProductData target,
            Collection<ProductOption> options) {
        super.populate(source, target, options);
        target.setAverageRating(source.getReviewsAverageRating());
        target.setCode(source.getCode());
        target.setDescription(source.getDescription());
        target.setManufacturer(source.getManufacturerName());
        target.setName(source.getName());
        target.setReviewsTotalCount(source.getReviewsTotalCount());        
        target.setSummary(source.getSummary());
        if (!source.getEurope1Prices().isEmpty()) {
            PriceRowModel priceRow = source.getEurope1Prices().iterator().next();
            BigDecimal price = new BigDecimal(priceRow.getPrice());
            int priceEuro = price.intValue();
            int priceCents = price.remainder(BigDecimal.ONE)
                    .setScale(2, BigDecimal.ROUND_HALF_UP).movePointRight(2).intValue();
            target.setPriceEuro(priceEuro);
            target.setPriceCents(priceCents);
        }
        boolean availableInStock = target.getStock().getStockLevelStatus()
                .equals(StockLevelStatus.INSTOCK);
        target.setAvailableInStock(availableInStock);
    }

}
