package com.flieger.payment.commands.impl;

import com.flieger.payment.utils.SubscriptionIDCreator;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.payment.commands.CreateSubscriptionCommand;
import de.hybris.platform.payment.commands.request.CreateSubscriptionRequest;
import de.hybris.platform.payment.commands.result.SubscriptionResult;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;

public class MundipaggCreateSubscriptionCommand extends MundipaggGenericCommand
        implements CreateSubscriptionCommand {

    public SubscriptionResult perform(CreateSubscriptionRequest request) {

        SubscriptionResult result = new SubscriptionResult();

        result.setSubscriptionID(SubscriptionIDCreator.createSubscriptionID(request.getCard().getCardHolderFullName()
                +request.getBillingInfo()));

        result.setTransactionStatus(TransactionStatus.ACCEPTED);
        genericPerform(request, result);

        return result;
    }
}
