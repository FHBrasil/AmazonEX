getUrlParam = function(name){
	var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
	if (results==null){
		return null;
	} else {
		return decodeURI(results[1]) || 0;
	}
}

if (ACC.addons.amazonaddon.isAmazonEnabled == 'true') {
	if (ACC.addons.amazonaddon.isAmazonLoginEnabled == 'true') {
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
						color: ACC.addons.amazonaddon.loginButtonColor,
						size:  ACC.addons.amazonaddon.loginButtonSize,
		
						authorization: function() {
							loginOptions =
								{scope: "profile payments:widget payments:shipping_address payments:billing_address", popup: "true"};
							// authRequest = amazon.Login.authorize (loginOptions, returnUrl);
							amazon.Login.authorize(loginOptions, function(response){
								if(response.error){
									alert('oauth error'+response.error);
									return;
								}
								amazon.Login.retrieveProfile(response.access_token, function(response){
									
									redirect(returnUrl, 
											{name: response.profile.Name,
								   			primaryEmail: response.profile.PrimaryEmail,
								   			customerId: response.profile.CustomerId,
								   			CSRFToken: ACC.config.CSRFToken },
								   			'POST');
								});
							});
						},
						onError: function(error) {
							// your error handling code
						}
					});
					if (ACC.addons.amazonaddon.isHiddenButtonsMode == 'true') {
						$("#LoginWithAmazon").hide();
					}
				}
				
				if($('a[href$="/logout"]').length) {
					$('a[href$="/logout"]').click(function() {
						amazon.Login.logout();
					});
				}
				
				if($('a[href$="/unmerge-amazon-account"]').length) {
					$('a[href$="/unmerge-amazon-account"]').click(function() {
						amazon.Login.logout();
					});
				}
				
				if (getUrlParam('amazonLogout') == 'true') {
					amazon.Login.logout();
				}
			})
			.fail(function( jqxhr, settings, exception ) {
				alert( "Triggered ajaxError handler." );
			});
	}	
}
var amazonForm = $("#amazonForm");
if($("#matchAmazonAccount").length) {
	$("#matchAmazonAccount").click(function() {
		var actionForm = amazonForm.attr("action").split("/");
		actionForm.pop();
		actionForm.push('confirm-associate-account');
		amazonForm.attr("action",actionForm.join('/'));
		amazonForm.submit();
	});
}

if($("#noMatchAmazonAccount").length) {
	$("#noMatchAmazonAccount").click(function() {
		var actionForm = amazonForm.attr("action").split("/");
		actionForm.pop();
		actionForm.push('no-merge-account');
		amazonForm.attr("action",actionForm.join('/'));
		amazonForm.submit();
	});
}

function redirect(url, values, method) {
    method = (method && method.toUpperCase() === 'GET') ? 'GET' : 'POST';

    if (!values) {
        var obj = $.parseUrl(url);
        url = obj.url;
        values = obj.params;
    }

    var form = $('<form>')
      .attr("method", method)
      .attr("action", url);

    iterateValues(values, [], form);
    $('body').append(form);
    form[0].submit();
}

//Utility Functions
/**
 * Url and QueryString Parser.
 * @param {string} url - a Url to parse.
 * @returns {object} an object with the parsed url with the following structure {url: URL, params:{ KEY: VALUE }}
 */
$.parseUrl = function (url) {
    if (url.indexOf('?') === -1) {
        return {
            url: url,
            params: {}
        };
    }
    var parts = url.split('?'),
        query_string = parts[1],
        elems = query_string.split('&');
    url = parts[0];

    var i, pair, obj = {};
    for (i = 0; i < elems.length; i+= 1) {
        pair = elems[i].split('=');
        obj[pair[0]] = pair[1];
    }

    return {
        url: url,
        params: obj
    };
};

//Private Functions
var getInput = function (name, value, parent, array) {
    var parentString;
    if (parent.length > 0) {
        parentString = parent[0];
        var i;
        for (i = 1; i < parent.length; i += 1) {
            parentString += "[" + parent[i] + "]";
        }

        if (array) {
          name = parentString + "[]";
        } else {
          name = parentString + "[" + name + "]";
        }
    }

    return $("<input>").attr("type", "hidden")
        .attr("name", name)
        .attr("value", value);
};

var iterateValues = function (values, parent, form, array) {
    var i, iterateParent = [];
    for (i in values) {
        if (typeof values[i] === "object") {
            iterateParent = parent.slice();
            iterateParent.push(i);
            iterateValues(values[i], iterateParent, form, Array.isArray(values[i]));
        } else {
            form.append(getInput(i, values[i], parent, array));
        }
    }
};