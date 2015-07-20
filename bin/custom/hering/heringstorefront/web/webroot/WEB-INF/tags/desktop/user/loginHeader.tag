<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form:form action="${urlHttpsLoginHeader}" commandName="loginForm" method="POST">
    <fieldset>
        <spring:theme code="login.email" var="loginEmail" />
        <spring:theme code="login.password" var="loginPassword" />
        <formElement:formInputBox idKey="j_username" placeholder="${loginEmail}"
            labelKey="login.email" path="j_username" inputCSS="text in-o2" mandatory="true" />
        <formElement:formPasswordBox idKey="j_password" placeholder="${loginPassword}"
            labelKey="login.password" path="j_password" inputCSS="text password in-o2"
            mandatory="true" />
        <input type="submit" value="<spring:theme code="text.fliegercommerce.texto12"/>">
        <%-- <a href="#" class="facebook facebook-connect-btn" data-scope="">
				<i class="fa fa-facebook"></i> Login com o Facebook
			</a> --%>
    </fieldset>
</form:form>
