package de.kpfamily.core.service;

import java.util.List;

import de.kpfamily.core.model.ProductSupplierModel;

/**
 * @author jfelipe
 *
 */
public interface ProductSupplierService {

    /**
     * 
     * @return
     *
     * @author jfelipe
     */
    List<ProductSupplierModel> findAll();


    /**
     * 
     * @param code
     * @return
     *
     * @author jfelipe
     */
    ProductSupplierModel findByCode(String code);


    /**
     * 
     * @param name
     * @return
     *
     * @author jfelipe
     */
    List<ProductSupplierModel> findByName(String name);
}
