<%@ taglib prefix="spring"		uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" 			uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ycommerce"	uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" 		uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="format" 		tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="form" 		uri="http://www.springframework.org/tags/form"%>

<c:set var="more" value="+"/>

<div class="col-sm-4">
	<c:choose>
		<c:when test="${orderData.guestCustomer && not alreadyHasAccount}">
			<%-- RENDER ONLY if e-mail-address not has customer account --%>
			<div id="accountCreatedSucessfully" style="display: none;">
				<%-- RENDER IF form sent sucessfully --%>
				<h2><spring:theme code="text.fliegercommerce.texto170" /></h2>
				<p>
					<b>${more}${orderPoints}&nbsp;<spring:theme code="text.fliegercommerce.texto161" /></b>&nbsp;
					<spring:theme code="text.fliegercommerce.texto171" />&nbsp;${email}&nbsp;
					<spring:theme code="text.fliegercommerce.texto172" />&nbsp;
					<spring:theme code="text.fliegercommerce.texto173" />
				</p>
				<%-- END RENDER IF --%>
			</div>
			<div id="sectionFormGuest">
				<h2>
					<spring:theme code="text.fliegercommerce.texto157" />&nbsp;
					<span class="fox150706">${more}${orderPoints}</span>&nbsp;
					<spring:theme code="text.fliegercommerce.texto158" />
				</h2>
				<p>
					<spring:theme code="text.fliegercommerce.texto159"/>&nbsp;
					<b><spring:theme code="text.fliegercommerce.texto160"/>&nbsp;${more}${orderPoints}&nbsp;<spring:theme code="text.fliegercommerce.texto161"/></b>,&nbsp;
					<spring:theme code="text.fliegercommerce.texto162"/>
				</p>
				<p><spring:theme code="text.fliegercommerce.texto163"/></p>
				<div class="panel panel-default panel-secure150203">
					<div class="panel-body">
						<form:form id="formRegisterGuest" commandName="guestRegisterForm" method="POST">
							<form:hidden path="uid"/>
							<form:hidden path="orderCode"/>
							<div class="form-group">
								<label for="inputEmail"><spring:theme code="text.fliegercommerce.texto164"/></label>
								<input type="email" class="form-control" id="inputEmail" value="${email}" readonly="true">
							</div>
							<div class="form-group">						
								<formElement:formPasswordBox labelKey="text.fliegercommerce.texto165" idKey="pwd" 
									inputCSS="form-control" path="pwd" placeholder="Passwort" mandatory="true"/>
							</div>
							<div class="form-group">
								<formElement:formPasswordBox labelKey="text.fliegercommerce.texto166" idKey="checkPwd" 
									inputCSS="form-control" path="checkPwd" placeholder="Passwort widerholen" mandatory="true"/>
							</div>				
							<%-- RENDER ONLY if user not has opt-in --%>
							<c:if test="${not alreadyNewsletterSubscription}">
								<div class="checkbox">
									<label><input type="checkbox" name="registerNewsletter" checked="true">
										<spring:theme code="text.fliegercommerce.texto167" />*</label>
								</div>
							</c:if>
							<%-- END RENDER ONLY --%>		
							<input class="btn btn-primary" type="submit" value="${more}${orderPoints} <spring:theme code='text.fliegercommerce.texto168' />">			
						</form:form>				
					</div>
				</div>
				<%-- RENDER ONLY if user not has opt-in --%>
				<p class="small margin-top text-muted"><spring:theme code="text.fliegercommerce.texto169" /></p>
				<%-- END RENDER ONLY --%>
				<%-- END RENDER ONLY --%>
			</div>	
		</c:when>
		<c:when test="${not orderData.guestCustomer && alreadyHasAccount && not alreadyNewsletterSubscription}">
			<%-- RENDER IF (customer has account) and (customer not has birthdate) --%>
			<div id="newsletterSubscriptionSucessfully" style="display: none;">
				<%-- RENDER IF form sent sucessfully --%>
				<h2><spring:theme code="text.fliegercommerce.texto177" /></h2>
				<p><spring:theme code="text.fliegercommerce.texto178" /></p>
				<%-- END RENDER IF --%>
			</div>
			<div id="sectionFormNewsletter">
				<h2><spring:theme code="text.fliegercommerce.texto174" />&nbsp;${customerData.firstName}</h2>
				<p><spring:theme code="text.fliegercommerce.texto175" /></p>
				<div class="panel panel-default panel-secure150203">
					<div class="panel-body">
						<form:form id="newsletterSubscriptionFormComponent" class="orderConfirmation" method="POST" action="/newsletter/newsletter-register">
							<input type="hidden" name="firstName" value="${customerData.firstName}"/> 
							<input type="hidden" name="lastName" value="${customerData.lastName}"/>
							<input type="hidden" name="genderCode" value="${customerData.gender.code}"/>
							<input type="hidden" name="titleCode" value="${customerData.titleCode}"/>
							<div class="form-group">
								<label for="inputEmail"><spring:theme code="text.fliegercommerce.texto164"/></label>
								<input type="email" class="form-control" id="inputEmail" name="email" value="${customerData.uid}">
							</div>
							<div class="form-group">
								<label for="birthday" class="control-label"><spring:theme code="text.fliegercommerce.texto181" /></label>
								<div class="input-group input-append date" id="birthday" data-date="01.01.2015" data-date-format="mm.dd.yyyy">
									<input type="text" size="16" class="form-control span2" name="birthDay" value="01.01.2015">
									<span class="input-group-btn add-on">
										<button type="button" class="btn btn-default"><span class="glyphicon glyphicon-calendar"></span></button>
									</span>
								</div>	
							</div>					
							<button type="submit" class="btn btn-primary"><spring:theme code="text.fliegercommerce.texto176" /></button>
						</form:form>
					</div>
				</div>
				<p class="small margin-top text-muted"><spring:theme code="text.fliegercommerce.texto169" /></p>				
			</div>		
			<%-- END RENDER IF --%>	
		</c:when>
		<c:when test="${(not orderData.guestCustomer && alreadyHasAccount && alreadyNewsletterSubscription) || (orderData.guestCustomer && alreadyHasAccount)}">
			<%-- RENDER IF (customer has account) and (customer has birthdate) --%>
			<h2><spring:theme code="text.fliegercommerce.texto179" /></h2>
			<p><spring:theme code="text.fliegercommerce.texto180" /></p>
			<p class="social150121 becomesocial margin-top">
				<a href="https://www.facebook.com/babyartikel" target="_blank"><span class="babicon babicon-facebook"> </span></a>
				<a href="https://plus.google.com/107938801813595561303" target="_blank"><span class="babicon babicon-google"> </span></a>
				<a href="https://twitter.com/babyartikel" target="_blank"><span class="babicon babicon-twitter"> </span></a>
				<a href="http://www.babyartikel.de/magazin" target="_blank"><span class="babicon babicon-wordpress"> </span></a>
				<a href="http://www.youtube.com/babyartikel" target="_blank"><span class="babicon babicon-youtube"> </span></a>
				<a href="http://instagram.com/babyartikel/" target="_blank"><span class="babicon babicon-instagram"> </span></a>
				<a href="http://www.pinterest.com/babyartikel" target="_blank"><span class="babicon babicon-pinterest"> </span></a>
			</p>	
			<%-- END RENDER IF --%>
		</c:when>
		<c:otherwise>
			<%-- EMPTY --%>
		</c:otherwise>
	</c:choose>	
</div>