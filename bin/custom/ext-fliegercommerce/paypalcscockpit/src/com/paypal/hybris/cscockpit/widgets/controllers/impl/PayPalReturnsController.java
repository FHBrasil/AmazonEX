package com.paypal.hybris.cscockpit.widgets.controllers.impl;

import de.hybris.platform.basecommerce.enums.RefundReason;
import de.hybris.platform.basecommerce.enums.ReturnAction;
import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.services.values.ObjectValueContainer;
import de.hybris.platform.commerceservices.impersonation.ImpersonationContext;
import de.hybris.platform.commerceservices.impersonation.ImpersonationService;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.cscockpit.utils.SafeUnbox;
import de.hybris.platform.cscockpit.widgets.controllers.impl.DefaultReturnsController;
import de.hybris.platform.returns.model.RefundEntryModel;
import de.hybris.platform.returns.model.ReturnRequestModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class PayPalReturnsController extends DefaultReturnsController
{
	private static final Logger LOG = Logger.getLogger(PayPalReturnsController.class);

	@Override
	public TypedObject createRefundOrderPreview(List<ObjectValueContainer> refundEntriesValueContainers)
	{
		try
		{
			this.refundDetailsList = new HashMap();

			Map<TypedObject, Long> returnableOrderEntries = getReturnableOrderEntries();
			for (ObjectValueContainer ovc : refundEntriesValueContainers)
			{
				TypedObject orderEntry = (TypedObject)ovc.getObject();
				if (returnableOrderEntries.containsKey(orderEntry))
				{
					long expectedQty = SafeUnbox.toLong((Long)getPropertyValue(ovc, "ReturnEntry.expectedQuantity")
							.getCurrentValue());
					RefundReason reason = (RefundReason)getPropertyValue(ovc, "RefundEntry.reason").getCurrentValue();
					ReturnAction action = (ReturnAction)getPropertyValue(ovc, "ReturnEntry.action").getCurrentValue();
					String notes = (String)getPropertyValue(ovc, "ReturnEntry.notes").getCurrentValue();
					if ((expectedQty > 0L) && (expectedQty <= SafeUnbox.toLong((Long)returnableOrderEntries.get(orderEntry))) && (reason != null) &&
							(action != null))
					{
						this.refundDetailsList.put(
								Long.valueOf(SafeUnbox.toInt(((AbstractOrderEntryModel)orderEntry.getObject()).getEntryNumber())),
								new RefundDetails(expectedQty, reason, action, notes));
					}
				}
			}

			OrderModel orderModel = getOrderModel();

			this.refundOrderPreview = getRefundService().createRefundOrderPreview(orderModel);
			fixOrderEntryNumbers(orderModel, this.refundOrderPreview);
			ReturnRequestModel refundRequest = getReturnService().createReturnRequest(this.refundOrderPreview);

			applyRefunds(this.refundOrderPreview, refundRequest, this.refundDetailsList, true);

			return getCockpitTypeService().wrapItem(this.refundOrderPreview);
		}
		catch (Exception e)
		{
			LOG.error("failed to create refund order", e);


			if (this.refundDetailsList != null)
			{
				this.refundDetailsList.clear();
				this.refundDetailsList = null;
			}

			deleteRefundOrderPreview();
		}

		return null;
	}

	public TypedObject createRefundRequest()
	{
		try
		{
			OrderModel orderModel = getOrderModel();
			ReturnRequestModel refundRequest = getReturnService().createReturnRequest(orderModel);
			String rmaString = getReturnService().createRMA(refundRequest);
			refundRequest.setRMA(rmaString);

			applyRefunds(orderModel, refundRequest, this.refundDetailsList, false);

			TypedObject refundRequestObject = getCockpitTypeService().wrapItem(refundRequest);

			this.refundDetailsList.clear();
			this.refundDetailsList = null;
			deleteRefundOrderPreview();

			return refundRequestObject;
		}
		catch (Exception e)
		{
			LOG.error("Failed to create refund request", e);
		}

		return null;
	}

	@Override
	protected OrderModel applyRefunds(final OrderModel orderModel, ReturnRequestModel request,
			Map<Long, RefundDetails> refundDetailsList, boolean history)
	{
		final List<RefundEntryModel> refundEntries = new ArrayList();
		for (Long p : refundDetailsList.keySet())
		{
			AbstractOrderEntryModel orderEntryModel = getOrderEntryByEntryNumber(orderModel, SafeUnbox.toLong(p));
			if (orderEntryModel != null)
			{
				RefundDetails refundDetails = refundDetailsList.get(p);
				RefundEntryModel refundEntry = getReturnService().createRefund(request, orderEntryModel,
						refundDetails.getNotes(), Long.valueOf(refundDetails.getExpectedQuantity()), refundDetails.getAction(),
						refundDetails.getReason());
				refundEntries.add(refundEntry);
			}
			else
			{
				LOG.error("Failed to find orderEntry with entry number [" + p + "]");
			}
		}

		if (CollectionUtils.isNotEmpty(refundEntries))
		{
			if (!history)
			{
				ImpersonationContext context = createImpersonationContext(orderModel);
				try
				{
					getImpersonationService().executeInContext(context,
							new ImpersonationService.Executor()
							{
								public Object execute()
								{
									PayPalReturnsController.this.getRefundService().apply(refundEntries, orderModel);
									return null;
								}
							});
				}
				catch (Throwable e)
				{
					LOG.error("Failed to call refund service.", e);
				}
			}
		}

		return orderModel;
	}

}
