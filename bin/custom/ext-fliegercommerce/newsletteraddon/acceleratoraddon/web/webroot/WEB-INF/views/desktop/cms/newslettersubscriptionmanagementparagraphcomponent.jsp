<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="${commonResourcePath}/js/newslettersubscriptionmanagementparagraphcomponent.js"></script>

<c:choose>

      <c:when test="${subscriptionInputType == 'RadioButton'}">   
       
      	<c:choose>
	      	<c:when test="${newsletterChecked == 'checked'}">
		      	<form id="newsletterSubscriptionManagementComponent"  method="GET" action="newsletter/manage-subscription">
				  	<input type="radio" name="subscription" value="true" checked="${newsletterChecked}"></input>
				  	<label for="true">${textSubscribe}</label>
				  	<br/>
				  	<input type="radio" name="subscription" value="false"></input>
				  	<label for="false">${textUnsubscribe}</label>
				  	<br/>
				    <button>Confirm</button>
				</form>      
	      	</c:when>   
	
	      	<c:otherwise>
		      	<form id="newsletterSubscriptionManagementComponent"  method="GET" action="newsletter/manage-subscription">
				  	<input type="radio" name="subscription" value="true"></input>
				  	<label for="true">${textSubscribe}</label>
				  	<br/>
				  	<input type="radio" name="subscription" value="false" checked="${newsletterChecked}"></input>
				  	<label for="false">${textUnsubscribe}</label>
				  	<br/>
				    <button>Confirm</button>
				</form>      
	      	</c:otherwise>
      	</c:choose> 	
      </c:when>


      <c:when test="${subscriptionInputType == 'CheckBox'}">	
      	
      	<c:choose>
	      	<c:when test="${newsletterChecked == 'checked'}">
				<form id="newsletterSubscriptionManagementComponent"  method="GET" action="newsletter/manage-subscription">    			
					<input type="checkbox" name="subscription" value="true" checked="${newsletterChecked}" >
					<label for="true">${textSubscribe}</label>
				  	<br/>
				    <button>Confirm</button>
				</form>
			</c:when>     	
			
	      	<c:otherwise>
		      	<form id="newsletterSubscriptionManagementComponent"  method="GET" action="newsletter/manage-subscription">    			
					<input type="checkbox" name="subscription" value="true" >
					<label for="true">${textSubscribe}</label>
				  	<br/>
				    <button>Confirm</button>
				</form>
			</c:otherwise>
		</c:choose>
		
      </c:when>
      
      
      <c:otherwise>			
		<!-- more logic -->
      </c:otherwise>
      
</c:choose>