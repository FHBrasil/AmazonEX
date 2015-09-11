package de.kpfamily.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;
import de.kpfamily.core.dao.ProductSupplierDao;
import de.kpfamily.core.model.ProductSupplierModel;
import de.kpfamily.core.service.ProductSupplierService;

/**
 * @author jfelipe
 *
 */
public class DefaultProductSupplierService extends AbstractBusinessService implements
        ProductSupplierService {

    @Resource
    ProductSupplierDao defaultProductSupplierDao;


    /**
     * 
     */
    @Override
    public List<ProductSupplierModel> findAll() {
        List<ProductSupplierModel> productSuppliers = defaultProductSupplierDao
                .getAllProductSuppliers();
        return productSuppliers;
    }


    /**
     * 
     */
    @Override
    public ProductSupplierModel findByCode(String code) {
        ProductSupplierModel productSupplier = defaultProductSupplierDao
                .getProductSupplierByCode(code);
        return productSupplier;
    }


    /**
     * 
     */
    @Override
    public List<ProductSupplierModel> findByName(String name) {
        List<ProductSupplierModel> productSuppliers = defaultProductSupplierDao
                .getProductSuppliersByName(name);
        return productSuppliers;
    }
}
