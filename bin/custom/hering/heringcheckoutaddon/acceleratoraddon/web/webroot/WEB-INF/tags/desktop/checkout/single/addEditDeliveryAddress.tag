<%@ taglib prefix="single-checkout-hering"
tagdir="/WEB-INF/tags/addons/heringcheckoutaddon/desktop/checkout/single"%>

<div id="addEditPaymentAddress" class="span-7 append-1">
	<single-checkout-hering:addressFormSelector regions="${regions}"
            country="${country}"/>
    <single-checkout-hering:addEditBillingAddress regions="${regions}"
            country="${country}" />
</div> 