package com.paypal.hybris.ordercancel;

import com.paypal.hybris.constants.PaypalConstants;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.ordercancel.OrderCancelDenialReason;
import de.hybris.platform.ordercancel.OrderCancelDenialStrategy;
import de.hybris.platform.ordercancel.impl.denialstrategies.AbstractCancelDenialStrategy;
import de.hybris.platform.ordercancel.model.OrderCancelConfigModel;


/**
 * @author Yehor_Tsebro (EPAM Systems)
 * Disallows cancel for PayPal orders for which payment has been captured
 */
public class PayPalOrderCancelCaptureDenialStrategy extends AbstractCancelDenialStrategy implements OrderCancelDenialStrategy {

    @Override
    public OrderCancelDenialReason getCancelDenialReason(OrderCancelConfigModel orderCancelConfigModel, OrderModel orderModel, PrincipalModel principalModel, boolean b, boolean b1) {
        if(orderModel != null && orderModel.getPaymentInfo() != null){
            String paymentCode = orderModel.getPaymentInfo().getCode();
            if((PaypalConstants.PAYPAL_PAYMENT_INFO_CODE.equals(paymentCode) ||
                    PaypalConstants.PAYPAL_CREDIT_PAYMENT_INFO_CODE.equals(paymentCode)) &&
                    OrderStatus.PAYMENT_CAPTURED.equals(orderModel.getStatus())){
                return getReason();
            }
        }
        return null;
    }
}
