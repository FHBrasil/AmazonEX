package com.pixi.api;

import com.pixi.api.core.PixiFunction;
import com.pixi.api.exporters.impl.EstimatedDeliveryUpdatesPixiApiExporter;
import com.pixi.api.exporters.impl.InvoicePaidPixiApiExporter;
import com.pixi.api.exporters.impl.ItemSupplierPixiApiExporter;
import com.pixi.api.exporters.impl.StockPixiApiExporter;
import com.pixi.api.importers.impl.ChangedItemStockPixiApiImporter;
import com.pixi.api.importers.impl.ChangedOrderLinesPixiApiImporter;
import com.pixi.api.importers.impl.InvoiceByOrderNumberPixiApiImporter;
import com.pixi.api.importers.impl.InvoiceLinesPixiApiImporter;
import com.pixi.api.importers.impl.InvoicePixiApiImporter;
import com.pixi.api.importers.impl.ItemDeliveryInfoPixiApiImporter;
import com.pixi.api.importers.impl.ItemInfoPixiApiImporter;
import com.pixi.api.importers.impl.ItemSalesStatsPixiApiImporter;
import com.pixi.api.importers.impl.ItemStockBinsPixiApiImporter;
import com.pixi.api.importers.impl.ItemStockPixiApiImporter;
import com.pixi.api.importers.impl.OrderHeaderForCustomerPixiApiImporter;
import com.pixi.api.importers.impl.OrderHeaderPixiApiImporter;
import com.pixi.api.importers.impl.OrderLinePixiApiImporter;
import com.pixi.api.importers.impl.OrderNumberByOrderNumberExternalPixiApiImporter;
import com.pixi.api.importers.impl.PhysicalItemStockPixiApiImporter;
import com.pixi.api.importers.impl.SOrderLinesPixiApiImporter;
import com.pixi.api.importers.impl.SOrdersPixiApiImporter;
import com.pixi.api.importers.impl.SupplierDeliveryDaysPixiApiImporter;
import com.pixi.api.importers.impl.UpdatedInvoicesPixiApiImporter;

/**
 * Factory to expose the implementations of the Pixi API Importers and Exporters.
 * 
 * @author jfelipe
 *
 */
public class PixiApiFactory {

    private static PixiApiFactory INSTANCE;


    /**
     * prevents to intantiaded the class
     */
    private PixiApiFactory() {
        //
    }


    /**
     * @return
     *         The PixiApiFacotry instance
     * @author jfelipe
     */
    public static PixiApiFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PixiApiFactory();
        }
        return INSTANCE;
    }


    /**
     * Returns the respective PixiApiImporter implementation, according to the given
     * function.
     * 
     * @param function
     *            The function to be executed in Pixi API.
     * @return
     *         The Pixi API Importer implementation.
     * @author jfelipe
     */
    public PixiApiImporter getImporter(PixiFunction function) {
        switch (function) {
            case GET_S_ORDER_LINE:
                return new SOrderLinesPixiApiImporter();
            case GET_PHYSICAL_ITEM_STOCK:
                return new PhysicalItemStockPixiApiImporter();
            case GET_CHANGED_ORDER_LINES:
                return new ChangedOrderLinesPixiApiImporter();
            case GET_CHANGED_ITEM_STOCK:
                return new ChangedItemStockPixiApiImporter();
            case GET_INVOICE_LINES:
                return new InvoiceLinesPixiApiImporter();
            case GET_ITEM_SALES_STATS:
                return new ItemSalesStatsPixiApiImporter();
            case GET_SUPPLIERS_DEFAULT_DELIVERY_DAYS:
                return new SupplierDeliveryDaysPixiApiImporter();
            case GET_ORDER_HEADER:
                return new OrderHeaderPixiApiImporter();
            case GET_ORDER_HEADERS_FOR_CUSTOMER:
                return new OrderHeaderForCustomerPixiApiImporter();
            case GET_ORDER_LINE:
                return new OrderLinePixiApiImporter();
            case GET_UPDATED_INVOICES:
                return new UpdatedInvoicesPixiApiImporter();
            case GET_ITEM_DELIVERY_INFO:
                return new ItemDeliveryInfoPixiApiImporter();
            case GET_ITEM_STOCK_BINS:
                return new ItemStockBinsPixiApiImporter();
            case GET_ITEM_STOCK:
                return new ItemStockPixiApiImporter();
            case GET_ORDER_NUMBER_BY_ORDER_NUMBER_EXTERNAL:
                return new OrderNumberByOrderNumberExternalPixiApiImporter();
            case GET_ITEM_INFO:
                return new ItemInfoPixiApiImporter();
            case GET_INVOICE_BY_ORDER_NUMBER:
                return new InvoiceByOrderNumberPixiApiImporter();
            case GET_INVOICES:
                return new InvoicePixiApiImporter();
            case GET_ALL_S_ORDERS:
                return new SOrdersPixiApiImporter();
            default:
                return null;
        }
    }


    /**
     * Returns the respective PixiApiExporter implementation, according to the given
     * function.
     * 
     * @param function
     *            The function to be executed in Pixi API.
     * @return
     *         The Pixi API exporter implementation.
     * @author jfelipe
     */
    public PixiApiExporter getExporter(PixiFunction function) {
        switch (function) {
            case ESTIMATED_DELIVERY_UPDATES:
                return new EstimatedDeliveryUpdatesPixiApiExporter();
            case SET_INVOICE_PAID:
                return new InvoicePaidPixiApiExporter();
            case SET_ITEM_SUPPLIER:
                return new ItemSupplierPixiApiExporter();
            default:
                return null;
        }
    }
}
