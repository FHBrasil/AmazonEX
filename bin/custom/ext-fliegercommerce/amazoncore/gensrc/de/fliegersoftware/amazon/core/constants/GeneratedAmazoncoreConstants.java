/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 21/07/2016 18:08:31                         ---
 * ----------------------------------------------------------------
 */
package de.fliegersoftware.amazon.core.constants;

/**
 * @deprecated use constants in Model classes instead
 */
@Deprecated
@SuppressWarnings({"unused","cast","PMD"})
public class GeneratedAmazoncoreConstants
{
	public static final String EXTENSIONNAME = "amazoncore";
	public static class TC
	{
		public static final String ACCOUNTMATCHINGSTRATEGYENUM = "AccountMatchingStrategyEnum".intern();
		public static final String AMAZONCONFIG = "AmazonConfig".intern();
		public static final String AMAZONCONFIGCOUNTRYENUM = "AmazonConfigCountryEnum".intern();
		public static final String AMAZONLOG = "AmazonLog".intern();
		public static final String AMAZONPAYMENTPAYMENTINFO = "AmazonPaymentPaymentInfo".intern();
		public static final String AMAZONREFUND = "AmazonRefund".intern();
		public static final String AUTHORIZATIONMODEENUM = "AuthorizationModeEnum".intern();
		public static final String BUTTONCOLORENUM = "ButtonColorEnum".intern();
		public static final String BUTTONSIZEENUM = "ButtonSizeEnum".intern();
		public static final String CAPTUREMODEENUM = "CaptureModeEnum".intern();
		public static final String CHECKOUTSTRATEGYENUM = "CheckoutStrategyEnum".intern();
		public static final String GUESTCHECKOUTSTRATEGYENUM = "GuestCheckoutStrategyEnum".intern();
		public static final String OPERATIONMODEENUM = "OperationModeEnum".intern();
	}
	public static class Attributes
	{
		public static class BaseStore
		{
			public static final String AMAZONCONFIG = "amazonConfig".intern();
		}
		public static class Customer
		{
			public static final String AMAZONCUSTOMERID = "amazonCustomerId".intern();
		}
	}
	public static class Enumerations
	{
		public static class AccountMatchingStrategyEnum
		{
			public static final String NOMATCHING = "NoMatching".intern();
			public static final String EMAILADDRESSBASED = "EmailAddressBased".intern();
		}
		public static class AmazonConfigCountryEnum
		{
			public static final String DE = "DE".intern();
			public static final String UK = "UK".intern();
			public static final String US = "US".intern();
			public static final String OTHER = "Other".intern();
		}
		public static class AuthorizationModeEnum
		{
			public static final String AUTOMATICSYNCHRONOUS = "AutomaticSynchronous".intern();
			public static final String AUTOMATICNONSYNCHRONOUS = "AutomaticNonSynchronous".intern();
			public static final String MANUALNONSYNCHRONOUS = "ManualNonSynchronous".intern();
		}
		public static class ButtonColorEnum
		{
			public static final String GOLD = "Gold".intern();
			public static final String GRAY = "Gray".intern();
			public static final String LIGHTGRAY = "LightGray".intern();
			public static final String INK = "Ink".intern();
		}
		public static class ButtonSizeEnum
		{
			public static final String MEDIUM = "Medium".intern();
			public static final String SMALL = "Small".intern();
			public static final String LARGE = "Large".intern();
			public static final String BIG = "Big".intern();
		}
		public static class CaptureModeEnum
		{
			public static final String IMMEDIATE = "Immediate".intern();
			public static final String SHIPMENT = "Shipment".intern();
			public static final String MANUALCAPTURE = "ManualCapture".intern();
		}
		public static class CheckoutStrategyEnum
		{
			public static final String NORMAL = "Normal".intern();
			public static final String AMAZONONLY = "AmazonOnly".intern();
		}
		public static class GuestCheckoutStrategyEnum
		{
			public static final String ALLOWGUESTCHECKOUT = "AllowGuestCheckout".intern();
			public static final String FORCEACCOUNTCREATION = "ForceAccountCreation".intern();
		}
		public static class OperationModeEnum
		{
			public static final String LOGINANDPAY = "LoginAndPay".intern();
			public static final String PAYONLY = "PayOnly".intern();
			public static final String LOGINONLY = "LoginOnly".intern();
		}
	}
	public static class Relations
	{
		public static final String REFUND2AMAZONPAYMENTRELATION = "Refund2AmazonPaymentRelation".intern();
	}
	
	protected GeneratedAmazoncoreConstants()
	{
		// private constructor
	}
	
	
}
