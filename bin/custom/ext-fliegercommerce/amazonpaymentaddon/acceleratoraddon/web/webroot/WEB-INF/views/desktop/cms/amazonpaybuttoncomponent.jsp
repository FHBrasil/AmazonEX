<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p>Payment</p>
<c:url value="/checkout/amazon" var="returnUrl" />
<script type='text/javascript'>
	window.onAmazonLoginReady = function() {
		amazon.Login.setClientId('${clientId}');
	};
</script>
<script type="text/javascript" src="${amazonWidgetsUrl} "></script>
<div id="AmazonPayButton"></div>
<script type="text/javascript">
	var authRequest;
	OffAmazonPayments.Button("AmazonPayButton", "${sellerId} ", {
		type:  "PwA",
		color: "Gold",
		size:  "medium",
		language: "en-GB",

		authorization: function() {
			loginOptions =
				{scope: "payments:widget", popup: "true"};
			authRequest = amazon.Login.authorize (loginOptions, "${returnUrl}");
		},
		onError: function(error) {
			// your error handling code
		}
	});
</script>