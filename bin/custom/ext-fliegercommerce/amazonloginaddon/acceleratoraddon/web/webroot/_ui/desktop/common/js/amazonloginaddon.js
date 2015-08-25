if(!ACC)
	ACC = {};
if(!ACC.amazon)
	ACC.amazon = {};

$.getScript(ACC.addons.amazonaddon.amazonWidgetsUrl)
	.done(function(script, textStatus){
		if(window.onAmazonLoginReady) {
			var parent = window.onAmazonLoginReady;
			window.onAmazonLoginReady = function() {
				parent();
				authenticationLoginReadyHandler();
			}
		} else {
			window.onAmazonLoginReady = authenticationLoginReadyHandler;
		}
		function authenticationLoginReadyHandler() {
			amazon.Login.setClientId(ACC.addons.amazonaddon.clientId);
		};
		if($('#LoginWithAmazon').length) {
			var authRequest;
			var returnUrl = ACC.config.contextPath + '/register/login-with-amazon';
			OffAmazonPayments.Button("LoginWithAmazon", ACC.addons.amazonaddon.sellerId, {
				type:  "LwA",
				color: "Gold",
				size:  "medium",

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
		
		if($('a[href="/logout"]').length) {
			$('a[href="/logout"]').click(function() {
				amazon.Login.logout();
			});
		}
	})
	.fail(function( jqxhr, settings, exception ) {
		//alert( "Triggered ajaxError handler." );
	});

