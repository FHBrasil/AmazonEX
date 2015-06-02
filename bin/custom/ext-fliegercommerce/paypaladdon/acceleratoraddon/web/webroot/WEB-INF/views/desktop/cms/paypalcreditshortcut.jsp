<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/paypal/checkout/hop/creditCartShortcut" var="paypalCreditCartShortcutUrl"/>
<div class="payPalCredit">
	<a href="${paypalCreditCartShortcutUrl}">
		<img src="${media.url}" alt="${media.altText}" />
	</a>
	<br>
	<a href="https://www.securecheckout.billmelater.com/paycapture-content/fetch?hash=AU826TU8&content=/bmlweb/ppwpsiw.html">
		<img class="imgPadding" src="https://www.paypalobjects.com/webstatic/en_US/btn/btn_bml_text.png" />
	</a>
</div>
