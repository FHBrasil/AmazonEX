<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="formElement"	tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${themeName == 'black'}">
	<div class="over-welc2">
		<form:form commandName="loginForm" action="${urlHttpsLoginHeader}" method="POST">
			<fieldset>
				<formElement:formInputBox idKey="j_username" labelKey="login.email" path="j_username" inputCSS="text in-o2" mandatory="true"/>
				<formElement:formPasswordBox idKey="j_password" labelKey="login.password" path="j_password" inputCSS="text password in-o2" mandatory="true"/>
				
				<button class="bu-log" type="submit">
					<spring:theme code="login.login" />
				</button>
				
				<%--<a href="#" class="bu-fb facebook-btn fb-size-SMALL facebook-connect-btn" data-scope="">
					<span class="fb-text"></span><div class="bfb-text">Entre pelo Facebook</div>
				</a>--%>
				
			</fieldset>
		</form:form>
	</div>
</c:if>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<form:form action="${urlHttpsLoginHeader}" commandName="loginForm" method="POST">
		<fieldset>
			<spring:theme code="login.email" var="loginEmail" />
			<spring:theme code="login.password" var="loginPassword" />
			<formElement:formInputBox idKey="j_username" placeholder="${loginEmail}" labelKey="login.email" path="j_username" inputCSS="text in-o2" mandatory="true"/>
			<formElement:formPasswordBox idKey="j_password" placeholder="${loginPassword}" labelKey="login.password" path="j_password" inputCSS="text password in-o2" mandatory="true"/>	
			<input type="submit" value="Entrar">
			<%-- <a href="#" class="facebook facebook-connect-btn" data-scope="">
				<i class="fa fa-facebook"></i> Login com o Facebook
			</a> --%>
		</fieldset>
	</form:form>
</c:if>