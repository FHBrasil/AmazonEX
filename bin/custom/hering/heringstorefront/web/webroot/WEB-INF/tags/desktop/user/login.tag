<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="actionNameKey" required="true" type="java.lang.String"%>
<%@ attribute name="action" required="true" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/desktop/user"%>
<!-- <section class="col-2 column"> -->
<!--     <header> -->
<!--         <h2> -->
<%--             <spring:theme code="login.header.title" /> --%>
<!--         </h2> -->
<!--     </header> -->
<%--     <c:if test="${not empty message}"> --%>
<%--         <spring:theme code="${message}" /> --%>
<%--     </c:if> --%>
<%--     FORM LOGIN --%>
<%--     <form:form action="${action}" method="post" commandName="loginForm"> --%>
<%--         INPUT TYPE TEXT --%>
<!--         <div class="f-row"> -->
<%--             <formElement:formInputBox idKey="j_username" labelKey="login.email" path="j_username" --%>
<%--                 inputCSS="text required-email" mandatory="true" /> --%>
<!--         </div> -->
<%--         RECOVERY EMAIL --%>
<!--         <div class="f-row"> -->
<%--             <a href="<c:url value='/login/email/request'/>" --%>
<%--                 data-url="<c:url value='/login/email/request'/>" data-fancybox-type="iframe" --%>
<%--                 class="fancybox"> <spring:theme code="login.link.forgottenEmail" /> --%>
<!--             </a> -->
<!--         </div> -->
<%--         INPUT TYPE PASSWORD --%>
<!--         <div class="f-row"> -->
<%--             <formElement:formPasswordBox idKey="j_password" labelKey="login.password" --%>
<%--                 path="j_password" inputCSS="text password required" mandatory="true" /> --%>
<!--         </div> -->
<%--         <input type="hidden" value="${forgotPassword }" id="forgotPassword" /> --%>
<%--         RECOVERY PASSWORD --%>
<!--         <div class="f-row"> -->
<%--             <a href="<c:url value='/login/pw/request'/>" --%>
<%--                 data-url="<c:url value='/login/pw/request'/>" data-fancybox-type="iframe" --%>
<%--                 class="fancybox"> <spring:theme code="login.link.forgottenPwd" /> --%>
<!--             </a> -->
<!--         </div> -->
<%--         MANDATORY INFORMATION --%>
<%--         <small><spring:theme code="login.required.message" /></small> --%>
<%--         BUTTON SUBMIT --%>
<%--         <ycommerce:testId code="login_Login_button"> --%>
<!--             <button type="submit" class="btn btn-registro"> -->
<%--                 <spring:theme code="${actionNameKey}" /> --%>
<!--             </button> -->
<%--         </ycommerce:testId> --%>
<!--         <a href="#" class="btn btn-facebook fb-size-SMALL facebook-connect-btn" data-scope="" -->
<!--             style="display: none;"> <span class="fb-text"></span> Login com o Facebook -->
<!--         </a> -->
<%--     </form:form> --%>
<!-- </section> -->


<h2>Kunden-Login</h2>
<p>Loggen Sie sich ein, wenn Sie bereits ein Kundenkonto besitzen:</p>
<div class="panel panel-default panel-secure150203">
	<div class="panel-body">
 		<%-- FORM LOGIN --%> 
		<form:form action="${action}" method="post" commandName="loginForm">
			<div class="form-group">
				<label for="j_password"> E-Mail<span class="mandatory"> *: </span></label>
			    <formElement:formInputBox idKey="j_username" labelKey="login.email" path="j_username"
             		inputCSS="text required-email form-control" mandatory="true" placeholder="E-Mail" /> 
			</div>
			<div class="form-group">
				<!-- INPUT TYPE PASSWORD -->
		        <div class="f-row">
		            <formElement:formPasswordBox idKey="j_password" labelKey="login.password"
		                path="j_password" inputCSS="text password required form-control" mandatory="true" />
		        </div>
		        <input type="hidden" value="${forgotPassword }" id="forgotPassword" />
			</div>
			<%-- BUTTON SUBMIT --%> 
	        <ycommerce:testId code="login_Login_button">
	            <button type="submit" class="btn btn-default">
	                <spring:theme code="${actionNameKey}" />
	            </button>
	        </ycommerce:testId>
		</form:form>
	</div>
</div>
<small>
	<a href="<c:url value='/login/pw/request'/>"
	    data-url="<c:url value='/login/pw/request'/>" data-fancybox-type="iframe"
        class="fancybox"> <spring:theme code="login.link.forgottenPwd" />
    </a>
</small>
<p>
	<cms:pageSlot position="Section0" var="component">
	  	<cms:component component="${component}" />
	</cms:pageSlot>
</p>
<h2>Mit 1 Klick einloggen</h2>
<p>Loggen Sie sich bequem mit Amazon oder Facebook ein:</p>
<p>
	<cms:pageSlot position="Section1" var="component">
	  	<cms:component component="${component}" />
	</cms:pageSlot>
</p>
<p>
	<a href="#">
		<img src="https://www.babyartikel.de/images/fblogin.png">
	</a>
</p>

