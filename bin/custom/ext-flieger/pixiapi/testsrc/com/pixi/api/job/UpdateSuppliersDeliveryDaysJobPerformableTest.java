/**
 * 
 */
package com.pixi.api.job;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pixi.api.importers.PixiApiImporter;
import com.pixi.api.importers.impl.SupplierDeliveryDaysPixiApiImporter;
import com.pixi.api.model.UpdateSupplierDeliveryDaysCronJobModel;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.internal.model.impl.DefaultModelService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.kpfamily.core.model.ProductSupplierModel;
import de.kpfamily.core.service.ProductSupplierService;
import de.kpfamily.core.service.impl.DefaultProductSupplierService;

/**
 * @author jfelipe
 *
 */
public class UpdateSuppliersDeliveryDaysJobPerformableTest {

    UpdateSuppliersDeliveryDaysJobPerformable fixture;
    // Mocks
    ProductSupplierService defaultProductSupplierService;
    PixiApiImporter supplierDeliveryDaysPixiApiImporter;
    ModelService defaultModelService;
    UpdateSupplierDeliveryDaysCronJobModel updateSupplierDeliveryDaysCronJobModel;
    //
    final List<ProductSupplierModel> suppliersMock = new ArrayList<ProductSupplierModel>();


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Before
    public void setUp() {
        fixture = new UpdateSuppliersDeliveryDaysJobPerformable();
        defaultProductSupplierService = mock(DefaultProductSupplierService.class);
        supplierDeliveryDaysPixiApiImporter = mock(SupplierDeliveryDaysPixiApiImporter.class);
        defaultModelService = mock(DefaultModelService.class);
        updateSupplierDeliveryDaysCronJobModel = mock(UpdateSupplierDeliveryDaysCronJobModel.class);
        //
        ProductSupplierModel supplier1 = new ProductSupplierModel();
        supplier1.setCode("123");
        supplier1.setDropShippingDeliveryDays(1);
        supplier1.setName("Supplier Test 1");
        ProductSupplierModel supplier2 = new ProductSupplierModel();
        supplier2.setCode("123");
        supplier2.setDropShippingDeliveryDays(1);
        supplier2.setName("Supplier Test 1");
        ProductSupplierModel supplier3 = new ProductSupplierModel();
        supplier3.setCode("123");
        supplier3.setDropShippingDeliveryDays(1);
        supplier3.setName("Supplier Test 1");
        suppliersMock.add(supplier1);
        suppliersMock.add(supplier2);
        suppliersMock.add(supplier3);
    }


    /**
     * 
     * 
     *
     * @author jfelipe
     */
    @Test
    public void performTest() {
        when(defaultProductSupplierService.findAll()).thenReturn(suppliersMock);
        when(Integer.valueOf(supplierDeliveryDaysPixiApiImporter.importInteger(anyString())))
                .thenReturn(new Integer(9));
        PerformResult result = fixture.perform(updateSupplierDeliveryDaysCronJobModel);
        Assert.assertTrue("Job Result should not be null. ", result != null);
        Assert.assertEquals("Job Result must be successful. ", result.getResult().getCode(),
                CronJobResult.SUCCESS);
        Assert.assertEquals("Job Result Status must be finished. ", result.getStatus().getCode(),
                CronJobStatus.FINISHED);
    }
}
