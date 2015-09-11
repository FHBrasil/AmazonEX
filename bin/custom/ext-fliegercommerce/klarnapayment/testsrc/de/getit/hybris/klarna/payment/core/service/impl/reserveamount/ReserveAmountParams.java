/**
 * (c) copyright 2013 by getit GmbH
 *
 * @author gerald.bornemann, getit GmbH
 */
package de.getit.hybris.klarna.payment.core.service.impl.reserveamount;

import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.store.BaseStoreModel;

import de.getit.hybris.klarna.payment.enums.KlarnaStatus;

/**
 * Parameter Object for reservceAmount Call.
 * 
 * @author gerald.bornemann, getit GmbH
 */
public class ReserveAmountParams {

	private BaseStoreModel mBaseStore;
	private CountryModel mCountry;
	private UserModel mUser;
	private PaymentModeModel mPaymentMode;
	private DeliveryModeModel mDeliveryMode;
	private Integer mPClassId;
	private KlarnaStatus mKlarnaStatus;

	public BaseStoreModel getBaseStore() {
		return mBaseStore;
	}

	public void setBaseStore(final BaseStoreModel pBaseStore) {
		mBaseStore = pBaseStore;
	}

	public CountryModel getCountry() {
		return mCountry;
	}

	public void setCountry(final CountryModel pCountry) {
		mCountry = pCountry;
	}

	public UserModel getUser() {
		return mUser;
	}

	public void setUser(final UserModel pUser) {
		mUser = pUser;
	}

	public PaymentModeModel getPaymentMode() {
		return mPaymentMode;
	}

	public void setPaymentMode(final PaymentModeModel pPaymentMode) {
		mPaymentMode = pPaymentMode;
	}

	public DeliveryModeModel getDeliveryMode() {
		return mDeliveryMode;
	}

	public void setDeliveryMode(final DeliveryModeModel mDeliveryMode) {
		this.mDeliveryMode = mDeliveryMode;
	}

	public Integer getPClassId() {
		return mPClassId;
	}

	public void setPClassId(final Integer pPClassId) {
		mPClassId = pPClassId;
	}

	public KlarnaStatus getKlarnaStatus() {
		return mKlarnaStatus;
	}

	public void setKlarnaStatus(final KlarnaStatus pKlarnaStatus) {
		mKlarnaStatus = pKlarnaStatus;
	}
}
