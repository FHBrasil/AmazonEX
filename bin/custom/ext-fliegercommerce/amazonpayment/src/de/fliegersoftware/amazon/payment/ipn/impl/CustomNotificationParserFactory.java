package de.fliegersoftware.amazon.payment.ipn.impl;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceConfig;
import com.amazonservices.mws.offamazonpayments.certificate.X509CertificateFactoryImpl;
import com.amazonservices.mws.offamazonpaymentsipn.INotificationParser;
import com.amazonservices.mws.offamazonpaymentsipn.NotificationParser;
import com.amazonservices.mws.offamazonpaymentsipn.NotificationParserFactory;
import com.amazonservices.mws.offamazonpaymentsipn.cache.Cache;
import com.amazonservices.mws.offamazonpaymentsipn.cache.ICache;
import com.amazonservices.mws.offamazonpaymentsipn.parsers.IpnNotificationParser;
import com.amazonservices.mws.offamazonpaymentsipn.parsers.SnsNotificationParser;
import com.amazonservices.mws.offamazonpaymentsipn.parsers.XmlNotificationParser;
import com.amazonservices.mws.offamazonpaymentsipn.validators.IVerifyData;
import com.amazonservices.mws.offamazonpaymentsipn.validators.SnsMessageValidator;
import com.amazonservices.mws.offamazonpaymentsipn.validators.SnsSignatureVerification;
import com.amazonservices.mws.offamazonpaymentsipn.validators.VerifyDataJavaImpl;

public class CustomNotificationParserFactory {

	private final ICache cache;
	private final String expectedCertCN;

	public CustomNotificationParserFactory(
			OffAmazonPaymentsServiceConfig paramOffAmazonPaymentsServiceConfig) {
		this(new Cache(), paramOffAmazonPaymentsServiceConfig);
	}

	public CustomNotificationParserFactory(ICache paramICache,
			OffAmazonPaymentsServiceConfig paramOffAmazonPaymentsServiceConfig) {
		cache = paramICache;
		expectedCertCN = paramOffAmazonPaymentsServiceConfig.getCertCN();
	}

	public INotificationParser createNewInstance() {
		SnsMessageValidator localSnsMessageValidator = createNewSnsMessageValidator();
		SnsNotificationParser localSnsNotificationParser = new SnsNotificationParser();
		IpnNotificationParser localIpnNotificationParser = new IpnNotificationParser();
		XmlNotificationParser localXmlNotificationParser = new XmlNotificationParser();
		return new NotificationParser(localSnsNotificationParser,
				localSnsMessageValidator, localIpnNotificationParser,
				localXmlNotificationParser);
	}

	SnsMessageValidator createNewSnsMessageValidator() {
		IVerifyData localVerifyDataJavaImpl = new VerifyDataJavaImpl(expectedCertCN, new CustomJCAAdapterBouncyCastleImpl());
		SnsSignatureVerification localSnsSignatureVerification = new SnsSignatureVerification(
				localVerifyDataJavaImpl, new X509CertificateFactoryImpl(cache));
		return new SnsMessageValidator(localSnsSignatureVerification);
	}
}
