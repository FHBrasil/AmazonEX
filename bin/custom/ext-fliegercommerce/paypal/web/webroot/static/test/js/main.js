/**
 * @author Valentyn Markovych, Gorilla
 */

// Init section
var basePath = "/paypal/";
var apiPath = basePath + "test/api/";
var filePath = apiPath + "test/file/";
var appName = "paypal";
var paypal = {};
var redirectUrl = "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=";
var doubleClickMessage = 'Double click here to<br />show/hide this cell content';


// ======================================================================== send
function send(what, resID, callback) {

	var doCallback = callback;
	var resEl = document.getElementById(resID);
	resEl.innerHTML = "...accessing API, please wait...";
	$.ajax({
	  type : 'POST',
	  url : apiPath,
	  data : what,
	  processData : false,
	  cache : false,
	  success : function(data, textStatus, jqXHR) {

		  paypal.data = data;
		  resEl.innerHTML = "";
		  resEl.appendChild(resultToTable(data));
		  if (typeof doCallback == 'function') {
			  doCallback(data);
		  }
	  },
	  error : function(jqXHR, textStatus, err) {

		  resEl.innerHTML = "Error: " + err;
	  }
	});
}


// =============================================================== resultToTable
function resultToTable(/* JSON obj */data) {

	if (typeof data === 'undefined') {
		var resEl = document.createElement('pre');
		resEl.innerHTML = "No data from server";
		return resEl;
	}

	if ((typeof data.request !== 'undefined')
	  && (typeof data.response !== 'undefined')) {
		// We have request and response, creating table
		var colReqEl = document.createElement('td');
		colReqEl.innerHTML = '<pre>' + JSON.stringify(data.request, null, 2)
		  + '</pre>';
		colReqEl.setAttribute('ondblclick', 'elOnClick(this);');
		colReqEl.setAttribute('swapText', doubleClickMessage);
		elOnClick(colReqEl);

		var colResEl = document.createElement('td');
		colResEl.innerHTML = '<pre>' + JSON.stringify(data.response, null, 2)
		  + '</pre>';
		colResEl.setAttribute('ondblclick', 'elOnClick(this);');
		colResEl.setAttribute('swapText', doubleClickMessage);

		var rawEl = document.createElement('tr');
		rawEl.appendChild(colReqEl);
		rawEl.appendChild(colResEl);

		var rawHeaderEl = document.createElement('tr');
		rawHeaderEl.innerHTML = '<th>Request</th><th>Response</th>';

		var tableEl = document.createElement('table');
		tableEl.setAttribute('border', '1');
		tableEl.setAttribute('width', '100%');
		tableEl.setAttribute('bordercolor', '#FFF0F0');
		tableEl.appendChild(rawHeaderEl);
		tableEl.appendChild(rawEl);
		return tableEl;
	} else {
		var resEl = document.createElement('pre');
		resEl.innerHTML = JSON.stringify(data, null, 2);
		return resEl;
	}
}


// =================================================================== elOnClick
function elOnClick(el) {

	var text = el.getAttribute('swapText');
	el.setAttribute('swapText', el.innerHTML);
	el.innerHTML = text;
}


// ======================================================================== goTo
function goTo(addr) {

	self.location.href = addr;
}


// ===================================================================== openUrl
function openUrl(/* String */url) {

	var win = window.open(url, '_blank');
}


// =========================================================== payPaypalCallback
function setExpressCheckoutCallback(/* Object */obj) {

// if (obj['state'] == "created") {
// alert("created");
// } else {
// alert("not created");
// }
}


// =========================================================== redirectButtClick
function redirectButtClick() {

	if (typeof paypal.data != 'undefined' && paypal.data['response']) {

		// Create redirect URL
		var url = redirectUrl + paypal.data['response']['token'];

		// Do redirect
		goTo(url);

	} else {
		alert("Redirect link not found. Please, do the 'Pay with PayPal account' first");s
	}
}


function redirectToAccountOptionalPayment() {

	// goTo("wiki.hybris.com");
/*
 * if (typeof paypal.data != 'undefined' && paypal.data['links']) { var url =
 * null; // Look for approval link for ( var ind in paypal.data['links']) { var
 * link = paypal.data['links'][ind]; if ("approval_url" == link.rel) { url =
 * link.href; } } // Do redirect goTo(url); } else { alert("Redirect link not
 * found"); }
 */
}


// ============================================== getTransactionDetailsCallback
function getTransactionDetailsCallback(/* Object */obj) {

	var tId = obj['DoExpressCheckoutPayment']['doExpressCheckoutPaymentResponseDetails']['paymentInfo']['transactionID'];
	alert("tId = " + tId);

	$('transactionId').html(tId);

}
