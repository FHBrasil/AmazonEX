package de.fliegersoftware.amazon.payment.addon.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;  import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceConfig;
import com.amazonservices.mws.offamazonpaymentsipn.INotificationParser;
import com.amazonservices.mws.offamazonpaymentsipn.NotificationsException;
import com.amazonservices.mws.offamazonpaymentsipn.notifications.INotification;
import com.amazonservices.mws.offamazonpaymentsipn.notifications.NotificationType;

import de.fliegersoftware.amazon.core.services.AmazonConfigService;
import de.fliegersoftware.amazon.payment.ipn.AmazonNotificationHandler;
import de.fliegersoftware.amazon.payment.ipn.impl.CustomNotificationParserFactory;
import de.hybris.platform.commercefacades.order.data.CartData;

@Controller(value="IpnController")
@SuppressWarnings({"unchecked", "rawtypes"})
public class IpnController {

	private static final Logger LOG = LoggerFactory.getLogger(IpnController.class);

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
	public void servlet(HttpServletRequest request, HttpServletResponse response) throws NotificationsException {
		final INotification notification = getIpnParser().parseRawMessage(request);
		LOG.info("Received notification of type " + notification.getNotificationType());

		if (handlers != null && handlers.containsKey(notification.getNotificationType())) {
			try {
				AmazonNotificationHandler h = handlers.get(notification.getNotificationType());
				h.log(notification);
				h.handle(notification);
			} catch (Exception e) {
				throw new NotificationsException("Error processing notification", e);
			}
		} else {
			String msg = "Unknown IPN Type: " + notification.getNotificationType();
			LOG.error(msg);
			throw new NotificationsException(msg);
		}

	}
	
	@RequestMapping(value = "/amazon/test", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map<String, String>> test(HttpServletRequest request, HttpServletResponse response) throws NotificationsException {
		LOG.info("/amazon/test ok");
		Map results = new HashMap();
		results.put("test", "ok");
		return new ResponseEntity(results, HttpStatus.OK);
	}

	protected Map<NotificationType, AmazonNotificationHandler> getHandlers() {
		return handlers;
	}

	public void setHandlers(
			Map<NotificationType, AmazonNotificationHandler> handlers) {
		this.handlers = handlers;
	}
}