<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="register/login-with-amazon" var="respondetUri"/>

<c:set var="clientId" scope="session" value="amzn1.application-oa2-client.15b4ec00f5a446e1b3a4157ade7441f4"/>

<br />

<a href="#" id="LoginWithAmazon">
      <img border="0" alt="Login with Amazon" src="https://images-na.ssl-images-amazon.com/images/G/01/lwa/btnLWA_gold_156x32.png" width="156" height="32" />
</a>
 
 <div id="amazon-root">
</div>
<script type="text/javascript">
 window.onAmazonLoginReady = function() {
 	amazon.Login.setClientId('${clientId}');
 };
 (function(d) {
 	var a = d.createElement('script'); 
 	a.type = 'text/javascript';
 	a.async = true; 
 	a.id = 'amazon-login-sdk';
 	a.src = 'https://eu.account.amazon.com/lwa/js/sdk/login1.js';
 	d.getElementById('amazon-root').appendChild(a);
 })(document);
</script>
 
<script type="text/javascript">
	document.getElementById('Logout').onclick = function() {
		amazon.Login.logout();
		console.log("logged out");
		return false;
	};
</script>

<script type="text/javascript">
	document.getElementById('LoginWithAmazon').onclick = function() {
		setTimeout(window.doLogin, 1);
		return false;
	};
	
	window.doLogin = function(){
		options = {};
		options.scope = 'profile';
		amazon.Login.authorize(options, function(response){
			if(response.error){
				alert('oauth error'+response.error);
				return;
			}
			amazon.Login.retrieveProfile(response.access_token, function(response){		
				window.location="${respondetUri}?name="+response.profile.Name+"&primaryEmail="+response.profile.PrimaryEmail+"&customerId="+response.profile.CustomerId;		
				
				/* alert('Hello '+response.profile.Name);
				alert('your e-mail address '+response.profile.PrimaryEmail);
				alert('your unique id '+response.profile.CustomerId); */
			});
		});
	};
</script>
<br />
