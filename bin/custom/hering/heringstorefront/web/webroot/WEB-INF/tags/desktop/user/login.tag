<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="actionNameKey" required="true" type="java.lang.String" %>
<%@ attribute name="action" required="true" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/desktop/user" %>

<%-- LOGIN TAG FOR DZARM STORE --%>
<c:if test="${themeName == 'black'}">
	<div class="userLogin">
		
		<div class="headline">
			<spring:theme code="login.title"/>
		</div>
		
	<form:form action="${action}" commandName="loginForm" method="post">
			<c:if test="${not empty message}">
				<span class="errors">
					<spring:theme code="${message}"/>
				</span>
			</c:if>
			<c:if test="${loginError}">
				<div class="form_field_error">
			</c:if>
			<div class="form_field-elements">
				<formElement:formInputBox idKey="j_username" labelKey="login.email" path="j_username" inputCSS="text" mandatory="true"/>
				<formElement:formPasswordBox idKey="j_password" labelKey="login.password" path="j_password" inputCSS="text password" mandatory="true"/>

				<div class="form_field_error-message">
					<a href="javascript:void(0)" data-url="<c:url value='/login/pw/request'/>" class="password-forgotten"><spring:theme code="login.link.forgottenPwd"/></a>
				</div>
			</div>
			<input type="hidden" value="${forgotPassword }" id="forgotPassword" />
			<c:if test="${loginError}">
				</div>
			</c:if>
			<c:if test="${expressCheckoutAllowed}">
					<div class="expressCheckoutLogin">
						<div class="headline"><spring:theme text="Express Checkout" code="text.expresscheckout.header"/></div>
	
						<div class="description"><spring:theme text="Benefit from a faster checkout by:" code="text.expresscheckout.title"/></div>
	
						<ul>
							<li><spring:theme text="setting a default Delivery Address in your account" code="text.expresscheckout.line1"/></li>
							<li><spring:theme text="setting a default Payment Details in your account" code="text.expresscheckout.line2"/></li>
							<li><spring:theme text="a default shipping method is used" code="text.expresscheckout.line3"/></li>
						</ul>
	
						<div class="expressCheckoutCheckbox clearfix">
							<label for="expressCheckoutCheckbox"><input id="expressCheckoutCheckbox" name="expressCheckoutEnabled"  type="checkbox" class="form left doExpressCheckout"/>
								<spring:theme text="I would like to Express checkout" code="cart.expresscheckout.checkbox"/></label>
						</div>
					</div>
			</c:if>
	<div class="obrigatorio required"><spring:theme code="form.required"/></div>
			<div class="form-actions clearfix">
				<ycommerce:testId code="login_Login_button">
					<button type="submit" class="positive"><spring:theme code="${actionNameKey}"/></button>
				</ycommerce:testId>
			</div>
			
		<a href="#" class="bu-fb-login facebook-btn fb-size-SMALL facebook-connect-btn" data-scope="" style="display: none;">
			<span class="fb-text"></span><div class="bfb-text">Entre pelo Facebook</div>
		</a>
			
	</form:form>
	</div>
</c:if>

<%-- LOGIN TAG FOR HERING STORE --%>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<section class="col-2 column">
				
		<header>
			<h2><spring:theme code="login.header.title"/></h2>
		</header>
		
		<c:if test="${not empty message}">
			<spring:theme code="${message}"/>
		</c:if>
		
		<%-- FORM LOGIN --%>
		<form:form action="${action}" method="post" commandName="loginForm">
			
			<%-- INPUT TYPE TEXT --%>
			<div class="f-row">
				<formElement:formInputBox idKey="j_username" labelKey="login.email" path="j_username" inputCSS="text required-email" mandatory="true"/>
			</div>
			<%-- RECOVERY EMAIL --%>
			<div class="f-row">
				<a href="<c:url value='/login/email/request'/>" data-url="<c:url value='/login/email/request'/>" data-fancybox-type="iframe" class="fancybox">
					<spring:theme code="login.link.forgottenEmail"/>
				</a>
			</div>
			<%-- INPUT TYPE PASSWORD --%>
			<div class="f-row">
				<formElement:formPasswordBox idKey="j_password" labelKey="login.password" path="j_password" inputCSS="text password required" mandatory="true"/>
			</div>
			
			<input type="hidden" value="${forgotPassword }" id="forgotPassword" />
			<%-- RECOVERY PASSWORD --%>
			<div class="f-row">
				<a href="<c:url value='/login/pw/request'/>" data-url="<c:url value='/login/pw/request'/>" data-fancybox-type="iframe" class="fancybox">
					<spring:theme code="login.link.forgottenPwd"/>
				</a>
			</div>
			
			<%-- MANDATORY INFORMATION --%>
			<small><spring:theme code="login.required.message"/></small>

			<%-- BUTTON SUBMIT --%>
			<ycommerce:testId code="login_Login_button">
				<button type="submit" class="btn btn-registro"><spring:theme code="${actionNameKey}"/></button>
			</ycommerce:testId>
					
		<a href="#" class="btn btn-facebook fb-size-SMALL facebook-connect-btn" data-scope=""  style="display: none;" >
		    <span class="fb-text"></span>
		    Login com o Facebook
		</a>
		
		
		</form:form>
		
		

	</section>
</c:if>