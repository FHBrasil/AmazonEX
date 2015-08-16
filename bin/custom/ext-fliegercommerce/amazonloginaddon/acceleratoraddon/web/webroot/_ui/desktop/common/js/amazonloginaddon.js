if(!ACC)
	ACC = {};
if(!ACC.amazon)
	ACC.amazon = {};

$.getScript(ACC.addons.amazonpaymentaddon.amazonWidgetUrl)
	.done(function(script, textStatus){
		window.onAmazonLoginReady = function() {
			amazon.Login.setClientId(ACC.addons.amazonpaymentaddon.clientId);
		};
		if($('#LoginWithAmazon').length) {
			var authRequest;
			var returnUrl = ACC.config.contextPath + 'register/login-with-amazon';
			OffAmazonPayments.Button("LoginWithAmazon", ACC.addons.amazonpaymentaddon.sellerId, {
				type:  "LwA",
				color: "Gold",
				size:  "medium",
				language: "en-GB",

				authorization: function() {
					loginOptions =
						{scope: "profile", popup: "true"};
					// authRequest = amazon.Login.authorize (loginOptions, returnUrl);
					amazon.Login.authorize(loginOptions, function(response){
						if(response.error){
							alert('oauth error'+response.error);
							return;
						}
						amazon.Login.retrieveProfile(response.access_token, function(response){		
							window.location=returnUrl+"?name="+response.profile.Name+"&primaryEmail="+response.profile.PrimaryEmail+"&customerId="+response.profile.CustomerId;		
						});
					});
				},
				onError: function(error) {
					// your error handling code
				}
			});
		}
	})
	.fail(function( jqxhr, settings, exception ) {
		//alert( "Triggered ajaxError handler." );
	});

//window.doLogin = function(){
//	options = {};
//	options.scope = 'profile';
//	amazon.Login.authorize(options, function(response){
//		if(response.error){
//			alert('oauth error'+response.error);
//			return;
//		}
//		amazon.Login.retrieveProfile(response.access_token, function(response){		
//			window.location="${respondetUri}?name="+response.profile.Name+"&primaryEmail="+response.profile.PrimaryEmail+"&customerId="+response.profile.CustomerId;		
//			
//			/* alert('Hello '+response.profile.Name);
//			alert('your e-mail address '+response.profile.PrimaryEmail);
//			alert('your unique id '+response.profile.CustomerId); */
//		});
//	});
//};


//
//window.onAmazonLoginReady = function() {
//	amazon.Login.setClientId(''+ACC.addons.amazonpaymentaddon.clientId+'');
//};
//(function(d) {
//	var a = d.createElement('script'); 
//	a.type = 'text/javascript';
//	a.async = true; 
//	a.id = 'amazon-login-sdk';
//	a.src = 'https://eu.account.amazon.com/lwa/js/sdk/login1.js';
//	d.getElementById('amazon-root').appendChild(a);
//})(document);

//document.getElementById('Logout').onclick = function() {
//	amazon.Login.logout();
//	console.log("logged out");
//	return false;
//};

//document.getElementById('LoginWithAmazon').onclick = function() {
//	setTimeout(window.doLogin, 1);
//	return false;
//};