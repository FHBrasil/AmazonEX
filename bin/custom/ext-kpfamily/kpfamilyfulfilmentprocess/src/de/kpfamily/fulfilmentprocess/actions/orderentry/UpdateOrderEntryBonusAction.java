/**
 *
 */
package de.kpfamily.fulfilmentprocess.actions.orderentry;

import de.hybris.platform.basecommerce.enums.OrderEntryStatus;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction;
import de.hybris.platform.task.RetryLaterException;

import java.util.Date;

import org.apache.log4j.Logger;

import de.kpfamily.core.model.process.OrderEntryStatusProcessModel;


/**
 * @author franthescollymaneira
 *
 */
public class UpdateOrderEntryBonusAction extends AbstractSimpleDecisionAction<OrderEntryStatusProcessModel>
{
	private static final Logger LOG = Logger.getLogger(UpdateOrderEntryBonusAction.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.processengine.action.AbstractSimpleDecisionAction#executeAction(de.hybris.platform.processengine
	 * .model.BusinessProcessModel)
	 */
	@Override
	public Transition executeAction(final OrderEntryStatusProcessModel process) throws RetryLaterException, Exception
	{
		LOG.info("Process: " + process.getCode() + " in step " + getClass().getSimpleName());

		if (process.getOldStatus() == null)
		{
			LOG.info("old status is null, skipping bonus update");
			return Transition.OK;
		}

		final boolean shouldAddBP = shouldAddBonusPoints(process.getOldStatus());
		final boolean shouldCancelBP = shouldCancelBonusPoints(process.getNewStatus());

		final OrderEntryModel orderEntry = process.getOrderEntry();

		if (shouldAddBP && !shouldCancelBP)
		{
			giveBonus(orderEntry);
		}
		else if (!shouldAddBP && shouldCancelBP)
		{
			cancelBonus(orderEntry);
		}

		return Transition.OK;
	}

	private void cancelBonus(final OrderEntryModel orderEntry)
	{
		final int bonus = calculateEntryBonus(orderEntry) * -1;
		final String description = "canceled bonus points for the customer orderline";
		final Date date = new Date(System.currentTimeMillis());
		final UserModel user = orderEntry.getOrder().getUser();

		addBonusSystemLog(user, bonus, description, date);
	}

	private void giveBonus(final OrderEntryModel orderEntry)
	{
		final int bonus = calculateEntryBonus(orderEntry);
		final String description = "orderCode: " + orderEntry.getPk();
		final Date date = new Date(System.currentTimeMillis());
		final UserModel user = orderEntry.getOrder().getUser();

		addBonusSystemLog(user, bonus, description, date);
	}

	/**
	 * @param user
	 * @param bonus
	 * @param description
	 * @param date
	 */
	private void addBonusSystemLog(final UserModel user, final int bonus, final String description, final Date date)
	{
		final String message = "%s | %s BS - adding log with %s points, %s";
		LOG.info(String.format(message, date.toString(), user.getUid(), String.valueOf(bonus), description));
		//			final BonusSystemLog bsLog = this.createBonusSystemLog(KPConstants.BONUS_SYSTEM_TYPES.ORDERLINE_CHARGED_BACK,
		//					description, bonus, date, entryCode);
		//			bs.getLog().add(bsLog);

		//-----

		//			final BonusSystemLog bsLog = this.createBonusSystemLog(KPConstants.BONUS_SYSTEM_TYPES.ORDER_POINTS,
		//					description, bonus, date, entryCode);
		//			bs.getLog().add(bsLog);
	}

	private int calculateEntryBonus(final OrderEntryModel orderEntry)
	{
		final double basePrice = orderEntry.getBasePrice().doubleValue();
		final int orderPoints = ((int) Math.round(basePrice) * 3);

		return orderPoints;
	}

	/**
	 * @param newStatus
	 * @return
	 */
	private boolean shouldCancelBonusPoints(final OrderEntryStatus newStatus)
	{
		if (newStatus == null)
		{
			return false;
		}

		final boolean isOIReturned = newStatus.equals(OrderEntryStatus.RETURNED);
		final boolean isOIChargeback = newStatus.equals(OrderEntryStatus.CHARGEBACK);
		final boolean isOINotAvailable = newStatus.equals(OrderEntryStatus.NOT_AVAILABLE);

		return isOIReturned || isOIChargeback || isOINotAvailable;
	}

	/**
	 * @param oldStatus
	 * @return
	 */
	private boolean shouldAddBonusPoints(final OrderEntryStatus oldStatus)
	{
		if (oldStatus == null)
		{
			return false;
		}

		final boolean isChargeback = oldStatus.equals(OrderEntryStatus.CHARGEBACK);
		final boolean isReturned = oldStatus.equals(OrderEntryStatus.RETURNED);
		final boolean isNotAvailable = oldStatus.equals(OrderEntryStatus.NOT_AVAILABLE);

		return isReturned || isChargeback || isNotAvailable;
	}
}