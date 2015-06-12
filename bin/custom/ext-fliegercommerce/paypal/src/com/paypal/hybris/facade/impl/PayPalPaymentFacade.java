/**
 *
 */
package com.paypal.hybris.facade.impl;

import com.ebay.api.*;
import com.paypal.hybris.data.*;

import de.hybris.platform.acceleratorfacades.payment.impl.DefaultPaymentFacade;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commerceservices.enums.CustomerType;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.enums.PaymentActionType;
import br.hering.core.model.order.payment.PayPalPaymentInfoModel;
import com.paypal.hybris.service.PaypalPaymentService;

import java.util.List;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
@Component
public class PayPalPaymentFacade extends DefaultPaymentFacade
{
	private final static Logger LOG = Logger.getLogger(PayPalPaymentFacade.class);

	@Resource(name = "paypalPaymentService")
	private PaypalPaymentService paypalPaymentService;

	@Resource(name = "cartService")
	private CartService cartService;

	@Resource(name = "modelService")
	private ModelService modelService;

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "sessionService")
	private SessionService sessionService;

	private ConfigurationService configurationService;

	private Converter<AbstractRequestData, AbstractRequestType> setExprCheckoutReqDataConverter;
	private Converter<AbstractResponseType, AbstractResultData> setExprCheckoutResConverter;
	private Converter<AbstractRequestData, AbstractRequestType> getExprCheckoutDetReqDataConverter;
	private Converter<AbstractResponseType, AbstractResultData> getExprCheckoutDetResConverter;
	private Converter<AbstractRequestData, AbstractRequestType> doExprCheckoutPaymentReqDataConverter;
	private Converter<AbstractResponseType, AbstractResultData> doExprCheckoutPaymentResConverter;
	private Converter<AbstractRequestData, AbstractRequestType> doAuthorizationReqDataConverter;
	private Converter<AbstractResponseType, AbstractResultData> doAuthorizationResConverter;
	private Converter<AbstractRequestData, AbstractRequestType> doCaptureReqDataConverter;
	private Converter<AbstractResponseType, AbstractResultData> doCaptureResConverter;
	private Converter<AbstractRequestData, AbstractRequestType> doRefTransactionReqDataConverter;
	private Converter<AbstractResponseType, AbstractResultData> doRefTransactionResConverter;

	@Resource private Converter<AddressData, AddressModel> addressReverseConverter;

	public SetExpressCheckoutResultData preparePaypalPayment(final SetExpressCheckoutRequestData requestData)
	{
		LOG.debug("Prepare payment for PayPal");

		//convert to req object
		final SetExpressCheckoutRequestType request = (SetExpressCheckoutRequestType) setExprCheckoutReqDataConverter
				.convert(requestData);

		//call service
		final SetExpressCheckoutResponseType response = paypalPaymentService.setExpressCheckout(request);

		//convert to result data object
		final SetExpressCheckoutResultData resultData = (SetExpressCheckoutResultData) setExprCheckoutResConverter.convert(response);

		if (PaypalConstants.STATUS_SUCCESS.equalsIgnoreCase(resultData.getAck()))
		{
			List<PaymentDetailsType> paymentDetails = request.getSetExpressCheckoutRequestDetails().getPaymentDetails();

			final String paymentAction = CollectionUtils.isNotEmpty(paymentDetails) ?
					paymentDetails.get(0).getPaymentAction().value() :
					configurationService.getConfiguration().getString(PaypalConstants.DEFAULT_PAYMENT_ACTION_NAME);

			final PayPalPaymentInfoModel paymentInfo = new PayPalPaymentInfoModel();
			final UserModel user = userService.getCurrentUser();
			paymentInfo.setToken(resultData.getToken());
			paymentInfo.setUser(user);
			paymentInfo.setPaymentAction(PaymentActionType.valueOf(paymentAction.toUpperCase()));
			if(Boolean.TRUE.toString().equalsIgnoreCase(
				configurationService.getConfiguration().getString(PaypalConstants.USE_REFERENCE_TRANSCATION))){
				paymentInfo.setUseReferenceTransaction(Boolean.TRUE);
			} else {
				paymentInfo.setUseReferenceTransaction(Boolean.FALSE);
			}
			Boolean isCredit = sessionService.getAttribute(PaypalConstants.IS_PAYPAL_CREDIT);
			if(isCredit != null && isCredit){
				paymentInfo.setCode(PaypalConstants.PAYPAL_CREDIT_PAYMENT_INFO_CODE);
			} else {
				paymentInfo.setCode(PaypalConstants.PAYPAL_PAYMENT_INFO_CODE);
			}
			modelService.save(paymentInfo);

			final CartModel cart = cartService.getSessionCart();
			cart.setPaymentInfo(paymentInfo);
			modelService.save(cart);
		}

		return resultData;
	}

	public GetExpressCheckoutDetailsResultData getExpressCheckoutDetails(final GetExpressCheckoutDetailsRequestData requestData)
	{
		LOG.debug("Get express checkout details from PayPal");
		GetExpressCheckoutDetailsRequestType request = (GetExpressCheckoutDetailsRequestType) getExprCheckoutDetReqDataConverter
				.convert(requestData);

		final GetExpressCheckoutDetailsResponseType response = paypalPaymentService.getExpressCheckoutDetails(request);

		GetExpressCheckoutDetailsResultData resultData = (GetExpressCheckoutDetailsResultData) getExprCheckoutDetResConverter
				.convert(response);

		if (PaypalConstants.STATUS_SUCCESS.equalsIgnoreCase(resultData.getAck()))
		{
			final CartModel cart = cartService.getSessionCart();
			if (cart.getPaymentInfo() instanceof PayPalPaymentInfoModel)
			{
				final PayPalPaymentInfoModel paymentInfo = (PayPalPaymentInfoModel) cart.getPaymentInfo();
				paymentInfo.setPayerid(resultData.getPayerId());
				paymentInfo.setPayer(resultData.getPayer());

				if (resultData.getBillingAddress() != null)
				{
					AddressModel billingAddress = modelService.create(AddressModel.class);
					billingAddress = addressReverseConverter.convert(resultData.getBillingAddress(), billingAddress);
					final UserModel user = cart.getUser();
					if (user instanceof CustomerModel && ((CustomerModel) user).getType() == CustomerType.GUEST)
					{
						billingAddress.setEmail(user.getUid().substring(user.getUid().indexOf("|") + 1));
					}
					else
					{
						billingAddress.setEmail(resultData.getPayer());
					}
					billingAddress.setOwner(cart);
					modelService.save(billingAddress);

					paymentInfo.setBillingAddress(billingAddress);
				}

				modelService.save(paymentInfo);
				// do we need to update cart?
				modelService.save(cart);
			}
		}

		return resultData;
	}

	/**
	 * Make call to service's doExpressCheckoutPayment method. Additionally converts from dto object to request and from
	 * response to result dto object.
	 *
	 * @param requestData dto object with service call params
	 * @return dto object with service call results
	 */
	public DoExpressCheckoutPaymentResultData doExpressCheckoutPayment(final DoExpressCheckoutPaymentRequestData requestData)
	{

		final DoExpressCheckoutPaymentRequestType request = (DoExpressCheckoutPaymentRequestType) doExprCheckoutPaymentReqDataConverter
				.convert(requestData);
		final DoExpressCheckoutPaymentResponseType response = paypalPaymentService.doExpressCheckoutPayment(request);

		DoExpressCheckoutPaymentResultData resultData = (DoExpressCheckoutPaymentResultData) doExprCheckoutPaymentResConverter
				.convert(response);

		return resultData;
	}

	/**
	 * Make call to service's authorization method. Additionally converts from dto object to request and from response to
	 * result dto object.
	 *
	 * @param requestData dto object with service call params
	 * @return dto object with service call results
	 */
	public DoAuthorizationResultData doAuthorization(final DoAuthorizationRequestData requestData)
	{
		LOG.debug("Do authorization of certain amount with PayPal");

		final DoAuthorizationRequestType request = (DoAuthorizationRequestType) doAuthorizationReqDataConverter.convert(requestData);
		final DoAuthorizationResponseType response = paypalPaymentService.doAuthorization(request);

		final DoAuthorizationResultData resultData = (DoAuthorizationResultData) doAuthorizationResConverter.convert(response);

		return resultData;
	}

	/**
	 * Make call to service's capture method. Additionally converts from dto object to request and from response to
	 * result dto object.
	 *
	 * @param requestData dto object with service call params
	 * @return dto object with service call results
	 */
	public DoCaptureResultData doCapture(final DoCaptureRequestData requestData)
	{
		LOG.debug("Do capture of certain amount with PayPal");

		final DoCaptureRequestType request = (DoCaptureRequestType) doCaptureReqDataConverter.convert(requestData);
		final DoCaptureResponseType response = paypalPaymentService.doCapture(request);

		final DoCaptureResultData resultData = (DoCaptureResultData) doCaptureResConverter.convert(response);

		return resultData;
	}

	public DoReferenceTransactionResultData doReferenceTransaction(final DoReferenceTransactionRequestData requestData)
	{
		LOG.debug("DoReferenceTransaction call with PayPal");
		final DoReferenceTransactionRequestType request = (DoReferenceTransactionRequestType)doRefTransactionReqDataConverter.convert(requestData);
		final DoReferenceTransactionResponseType response = paypalPaymentService.doReferenceTransaction(request);

		final DoReferenceTransactionResultData resultData = (DoReferenceTransactionResultData) doRefTransactionResConverter.convert(response);

		return resultData;
	}

	public void setPaymentInfo(final CartData cartData)
	{
		CCPaymentInfoData ccPaymentInfo = cartData.getPaymentInfo();
		if (ccPaymentInfo == null)
		{
			final CartModel cartModel = cartService.getSessionCart();

			// Check if it's PayPal payment
			if (cartModel.getPaymentInfo() instanceof PayPalPaymentInfoModel)
			{
				// Create CCPaymentInfo data and set accountHolderName as a flag
				ccPaymentInfo = new CCPaymentInfoData();
				ccPaymentInfo.setAccountHolderName("PayPal");
			}
		}
		cartData.setPaymentInfo(ccPaymentInfo);
	}

	/**
	 * Resolves a given URL to a full URL including server and port, etc.
	 *
	 * @param responseUrl
	 *           - the URL to resolve
	 * @param isSecure
	 *           - flag to indicate whether the final URL should use a secure connection or not.
	 * @return a full URL including HTTP protocol, server, port, path etc.
	 */
	@Override
	public String getFullResponseUrl(final String responseUrl, final boolean isSecure)
	{
		final BaseSiteModel currentBaseSite = getBaseSiteService().getCurrentBaseSite();

		final String fullResponseUrl = getSiteBaseUrlResolutionService().getWebsiteUrlForSite(currentBaseSite, isSecure,
				responseUrl);

		return fullResponseUrl == null ? "" : fullResponseUrl;
	}


	public void setPaypalPaymentService(final PaypalPaymentService paypalPaymentService)
	{
		this.paypalPaymentService = paypalPaymentService;
	}

	@Required
	public void setSetExprCheckoutReqDataConverter(
			final Converter<AbstractRequestData, AbstractRequestType> setExprCheckoutReqDataConverter)
	{
		this.setExprCheckoutReqDataConverter = setExprCheckoutReqDataConverter;
	}

	@Required
	public void setSetExprCheckoutResConverter(
			final Converter<AbstractResponseType, AbstractResultData> setExprCheckoutResConverter)
	{
		this.setExprCheckoutResConverter = setExprCheckoutResConverter;
	}

	@Required
	public void setGetExprCheckoutDetReqDataConverter(
			final Converter<AbstractRequestData, AbstractRequestType> getExprCheckoutDetReqDataConverter)
	{
		this.getExprCheckoutDetReqDataConverter = getExprCheckoutDetReqDataConverter;
	}

	@Required
	public void setGetExprCheckoutDetResConverter(
			final Converter<AbstractResponseType, AbstractResultData> getExprCheckoutDetResConverter)
	{
		this.getExprCheckoutDetResConverter = getExprCheckoutDetResConverter;
	}

	@Required
	public void setDoExprCheckoutPaymentReqDataConverter(
			final Converter<AbstractRequestData, AbstractRequestType> doExprCheckoutPaymentReqDataConverter)
	{
		this.doExprCheckoutPaymentReqDataConverter = doExprCheckoutPaymentReqDataConverter;
	}

	@Required
	public void setDoExprCheckoutPaymentResConverter(
			final Converter<AbstractResponseType, AbstractResultData> doExprCheckoutPaymentResConverter)
	{
		this.doExprCheckoutPaymentResConverter = doExprCheckoutPaymentResConverter;
	}

	@Required
	public void setDoAuthorizationReqDataConverter(
			final Converter<AbstractRequestData, AbstractRequestType> doAuthorizationReqDataConverter)
	{
		this.doAuthorizationReqDataConverter = doAuthorizationReqDataConverter;
	}

	@Required
	public void setDoAuthorizationResConverter(
			final Converter<AbstractResponseType, AbstractResultData> doAuthorizationResConverter)
	{
		this.doAuthorizationResConverter = doAuthorizationResConverter;
	}

	@Required
	public void setDoCaptureReqDataConverter(final Converter<AbstractRequestData, AbstractRequestType> doCaptureReqDataConverter)
	{
		this.doCaptureReqDataConverter = doCaptureReqDataConverter;
	}

	@Required
	public void setDoCaptureResConverter(final Converter<AbstractResponseType, AbstractResultData> doCaptureResConverter)
	{
		this.doCaptureResConverter = doCaptureResConverter;
	}

	@Required
	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}

	/**
	 * @param doRefTransactionReqDataConverter
	 *           converter from request data object to stab request
	 */
	@Required
	public void setDoRefTransactionReqDataConverter(final Converter<AbstractRequestData, AbstractRequestType> doRefTransactionReqDataConverter)
	{
		this.doRefTransactionReqDataConverter = doRefTransactionReqDataConverter;
	}


	/**
	 * @param doRefTransactionResConverter
	 *           converter from stab response to result data object
	 */
	@Required
	public void setDoRefTransactionResConverter(final Converter<AbstractResponseType, AbstractResultData> doRefTransactionResConverter)
	{
		this.doRefTransactionResConverter = doRefTransactionResConverter;
	}

	/**
	 * @param sessionService
	 *           service to manage session state
	 */
	@Required
	public void setSessionService(SessionService sessionService)
	{
		this.sessionService = sessionService;
	}
}
