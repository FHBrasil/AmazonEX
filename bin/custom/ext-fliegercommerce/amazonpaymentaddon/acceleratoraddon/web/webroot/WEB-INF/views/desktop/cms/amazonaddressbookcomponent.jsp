<p>AddressBook</p>
<div id="addressBookWidgetDiv"></div>
<script>
new OffAmazonPayments.Widgets.AddressBook({
	sellerId: '${sellerId}',
	onOrderReferenceCreate: function(orderReference) {
		orderReference.getAmazonOrderReferenceId();
	},
	onAddressSelect: function(orderReference) {
		// Replace the following code with the action that you want to perform 
		// after the address is selected.
		// The amazonOrderReferenceId can be used to retrieve 
		// the address details by calling the GetOrderReferenceDetails
		// operation. If rendering the AddressBook and Wallet widgets on the
		// same page, you should wait for this event before you render the
		// Wallet widget for the first time.
		// The Wallet widget will re-render itself on all subsequent 
		// onAddressSelect events, without any action from you. It is not 
		// recommended that you explicitly refresh it.
	},
	design: {
		designMode: 'responsive'
	},
	onError: function(error) {
		// your error handling code
	}
}).bind("addressBookWidgetDiv");
</script>