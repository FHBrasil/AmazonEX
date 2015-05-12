package de.kpfamily.core.dao;

import java.util.List;

import de.kpfamily.core.model.ProductSupplierModel;

/**
 * @author jfelipe
 *
 */
public interface ProductSupplierDao {

    /**
     * Lists all ProductSuppliers from the system.
     * 
     * @param catalogVersion
     * @return
     *
     * @author jfelipe
     */
    List<ProductSupplierModel> getAllProductSuppliers();


    /**
     * Return the ProductSupplier that hsa the given code.
     * 
     * @param code
     * @return
     *
     * @author jfelipe
     */
    ProductSupplierModel getProductSupplierByCode(final String code);


    /**
     * Lists all ProductSuppliers which has the name like the given name.
     * 
     * @param name
     * @return
     *
     * @author jfelipe
     */
    List<ProductSupplierModel> getProductSuppliersByName(final String name);
}
