/**
 * 
 */
package com.flieger.notificationservices.services.impl;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.flieger.notificationservices.constants.NotificationservicesConstants;
import com.flieger.notificationservices.dao.NotifyMePaymentDao;
import com.flieger.notificationservices.services.NotifyMePaymentService;
import com.flieger.payment.model.BoletoPaymentInfoModel;

/**
 * @author Vinicius de Souza
 *
 */
public class DefaultNotifyMePaymentService extends AbstractNotifyMe implements NotifyMePaymentService
{
	@Resource
	private NotifyMePaymentDao notifyMePaymentDao;
	
	private final Logger LOG = Logger.getLogger(DefaultNotifyMePaymentService.class.getName());
	
	@Override
	public Set<OrderModel> findForDays(final int days) throws Exception
	{
		Date[] dates = createDateFuture(days);
		//Date[] dates = createDatePast(days);
		return findForPeriod(dates[0], dates[1]);
	}

	@Override
	public Set<OrderModel> findForPeriod(final Date from, final Date until) throws Exception
	{		
		if(from == null || until == null || from.after(until))
		{
			throw new Exception("Invalid date!");
		}
		final Set<OrderModel> result = new HashSet<OrderModel>();
		final Set<OrderModel> orders = notifyMePaymentDao.getOrders(OrderStatus.WAITING_PAYMENT_NOTIFICATION);
		
		for (OrderModel order : orders)
		{
			if(order.getPaymentInfo() instanceof BoletoPaymentInfoModel)
			{
				Date expiration = getExpirationDate(order);
				if(expiration != null && expiration.after(from) && expiration.before(until))
				{
					result.add(order);
				}
			}
		}
		
		return result;
	}
	
	@Override
	public Date getExpirationDate(OrderModel order) throws Exception
	{
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final long miliDays = 15 * NotificationservicesConstants.DAYINMILIS;
		
		if(order.getPaymentInfo() instanceof BoletoPaymentInfoModel)
		{
			for (PaymentTransactionEntryModel model : order.getPaymentTransactions().get(0).getEntries())
			{
				if (model.getType().equals(PaymentTransactionType.AUTHORIZATION) && model.getAdyenBoletoExpirationDate() != null)
				{
					try
					{
						//LOG.info("##ORDER:"+model.getCode()+"/"+model.getAdyenBoletoExpirationDate());
   					final Date date = sdf.parse(model.getAdyenBoletoExpirationDate());
   					
   					if(date != null)
   					{
   						return new Date(date.getTime() - miliDays);
   					}
					}
					catch(ParseException e)
					{
						LOG.error("Error parse date "+model.getAdyenBoletoExpirationDate()+" in order "+model.getCode());
					}
				}				
			}
		}
		return null;
	}
}