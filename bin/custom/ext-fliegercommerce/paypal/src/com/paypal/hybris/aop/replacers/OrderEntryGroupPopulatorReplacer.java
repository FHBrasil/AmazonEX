package com.paypal.hybris.aop.replacers;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.support.MethodReplacer;

import com.paypal.hybris.facade.order.converters.populator.PayPalOrderEntryGroupPopulator;

import de.hybris.platform.acceleratorfacades.order.data.OrderEntryGroupData;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;

public class OrderEntryGroupPopulatorReplacer extends PayPalOrderEntryGroupPopulator
        implements MethodReplacer {
    
    private static final Logger LOG = Logger.getLogger(OrderEntryGroupPopulatorReplacer.class);
    
    
    /**
     * 
     */
    @Override
    public Object reimplement(Object target, Method method, Object[] args) throws Throwable {
        AbstractOrderEntryModel entryModel = (AbstractOrderEntryModel) args[0];
        OrderEntryGroupData groupData = (OrderEntryGroupData) args[1];
        updateGroupTotalPrice(entryModel, groupData);
        updateTotalTax(entryModel, groupData);
        updateGroupTotalPriceWithTax(entryModel, groupData);
        // This method does not need to return anything.
        return null;
    }
}
