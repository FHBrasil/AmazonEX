package de.fliegersoftware.amazon.payment.addon.controllers;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceConfig;
import com.amazonservices.mws.offamazonpaymentsipn.INotificationParser;
import com.amazonservices.mws.offamazonpaymentsipn.NotificationsException;
import com.amazonservices.mws.offamazonpaymentsipn.notifications.INotification;
import com.amazonservices.mws.offamazonpaymentsipn.notifications.NotificationType;

import de.fliegersoftware.amazon.core.services.AmazonConfigService;
import de.fliegersoftware.amazon.payment.ipn.AmazonNotificationHandler;
import de.fliegersoftware.amazon.payment.ipn.impl.CustomNotificationParserFactory;

@Controller(value="IpnController")
@SuppressWarnings({"unchecked", "rawtypes"})
public class IpnController {

	private static final Logger LOG = Logger.getLogger(IpnController.class);

	@Resource
	private AmazonConfigService amazonConfigService;

	private Map<NotificationType, AmazonNotificationHandler> handlers;

	protected INotificationParser getIpnParser() {
		OffAmazonPaymentsServiceConfig config;
		INotificationParser service;

		config = new OffAmazonPaymentsServiceConfig(amazonConfigService.getAmazonProperties());
		service = new CustomNotificationParserFactory(config).createNewInstance();

		return service;
//		SnsMessageValidator localSnsMessageValidator = new SnsMessageValidator(null) {
//			@Override
//			public void validateMessageIsTrusted(Message paramMessage) throws NotificationsException {
//				return;
//			}
//		};
//		SnsNotificationParser localSnsNotificationParser = new SnsNotificationParser();
//		IpnNotificationParser localIpnNotificationParser = new IpnNotificationParser();
//		XmlNotificationParser localXmlNotificationParser = new XmlNotificationParser();
//		return new NotificationParser(localSnsNotificationParser, localSnsMessageValidator, localIpnNotificationParser, localXmlNotificationParser);
	}

	@RequestMapping(value = "/amazon/ipnhandler", method = RequestMethod.POST)
	public void servlet(HttpServletRequest request, HttpServletResponse response)
			throws NotificationsException {
		INotification notification = getIpnParser().parseRawMessage(request);
		LOG.info("Received notification of type " + notification.getNotificationType());
		switch (notification.getNotificationType()) {
		case OrderReferenceNotification:
		case AuthorizationNotification:
		case CaptureNotification:
		case RefundNotification:
		case BillingAgreementNotification:
		case ProviderCreditNotification:
		case ProviderCreditReversalNotification:
		case SolutionProviderMerchantNotification:
			if(handlers != null && handlers.containsKey(notification.getNotificationType())) {
				try {
					AmazonNotificationHandler h = handlers.get(notification.getNotificationType());
					h.log(notification);
					h.handle(notification);
				} catch (Exception e) {
					throw new NotificationsException("Error processing notification", e);
				}
			}
			break;
		default:
			String msg = "Unknown IPN Type: " + notification.getNotificationType();
			LOG.error(msg);
			throw new NotificationsException(msg);
		}
	}

	protected Map<NotificationType, AmazonNotificationHandler> getHandlers() {
		return handlers;
	}

	public void setHandlers(
			Map<NotificationType, AmazonNotificationHandler> handlers) {
		this.handlers = handlers;
	}
}