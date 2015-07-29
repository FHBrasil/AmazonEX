<p>Wallet</p>
<div id="walletWidgetDiv"></div>
<script>
new OffAmazonPayments.Widgets.Wallet({
	sellerId: '${sellerId}',
	onPaymentSelect: function(orderReference) {
		// Replace this code with the action that you want to perform
		// after the payment method is selected.
	},
	design: {
		designMode: 'responsive'
	},
	onError: function(error) {
		// your error handling code
	}
}).bind("walletWidgetDiv");
</script>