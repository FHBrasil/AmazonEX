package de.fliegersoftware.amazon.payment.ipn;

import com.amazonservices.mws.offamazonpaymentsipn.notifications.INotification;

public interface AmazonNotificationHandler<T extends INotification> {

	public void log(T notification);
	public void handle(T notification);

}