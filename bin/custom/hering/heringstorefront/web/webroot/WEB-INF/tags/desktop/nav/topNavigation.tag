<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/desktop/user"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<nav id="top-navigation">
    <div class="container">
        <cms:pageSlot position="HeaderLinks" var="link">
            <cms:component component="${link}" element="" />
        </cms:pageSlot>
        <c:if test="${pageType != 'SINGLESTEPCHECKOUT'}">
            <ul class="utils">
                <li class="radio"></li>
                <li class="cadastre-se logged-in"><sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
                        <a href="<c:url value="/login"/>"> <i class="sign-in"></i>
                            <div class="hide-on-mobile">
                                <spring:theme code="text.fliegercommerce.texto4" />
                            </div>
                        </a>
                    </sec:authorize> <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
                        <c:url value="/my-account" var="encodedUrl" />
                        <a href="${encodedUrl}"> <c:set var="maxNumberChars" value="25" /> <c:if
                                test="${fn:length(user.firstName) gt maxNumberChars}">
                                <c:set target="${user}" property="firstName"
                                    value="${fn:substring(user.firstName, 0, maxNumberChars)}..." />
                            </c:if> <i class="fa-user fa"></i>
                            <div class="hide-on-mobile">
                                <spring:theme code="text.fliegercommerce.texto1" /><br> <b> <c:choose>
                                        <c:when test="${fn:length(user.firstName) >= 10}">
												${fn:substring(user.firstName,0,7)}...
											</c:when>
                                        <c:otherwise>${user.firstName}</c:otherwise>
                                    </c:choose>
                                </b>
                            </div>
                        </a>
                    </sec:authorize> <!-- CODIGO ADAPTADO DA DZARM ABAIXO --> <sec:authorize
                        ifNotGranted="ROLE_ANONYMOUS">
                        <c:set var="maxNumberChars" value="25" />
                        <c:if test="${fn:length(user.firstName) gt maxNumberChars}">
                            <c:set target="${user}" property="firstName"
                                value="${fn:substring(user.firstName, 0, maxNumberChars)}..." />
                        </c:if>
                        <div class="login-box">
                            <ycommerce:testId code="header_LoggedUser">
                                <div>
                                    <c:url value="/my-account/orders" var="encodedUrl" />
                                    <a href="${encodedUrl}" class="btn"><i class="fa fa-th-list"></i>
                                    <spring:theme code="header.account.orders" /></a>
                                    <c:url value="/my-account" var="encodedUrl" />
                                    <a href="${encodedUrl}" class="btn"><i class="fa fa-user"></i>
                                    <spring:theme code="header.link.account" /></a>
                                </div>
                                <div class="acc-links">
                                    <%-- <a href="#"><i class="fa fa-angle-right"></i> Minhas trocas e devolu��es</a> --%>
                                    <c:url value="/my-account/profile" var="encodedUrlAddress" />
                                    <a href="${encodedUrlAddress}"><i class="fa fa-angle-right"></i>
                                        <spring:theme code="header.account.profile" /></a>
                                    <c:url value="/my-account/address-book" var="encodedUrlAddress" />
                                    <a href="${encodedUrlAddress}"><i class="fa fa-angle-right"></i>
                                        <spring:theme code="header.account.addressbook" /></a>
                                    <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
                                        <ycommerce:testId code="header_signOut">
                                            <a href="<c:url value='/logout'/>"><i
                                                class="fa fa-angle-right"></i> <spring:theme
                                                    code="header.link.logout" /> </a>
                                        </ycommerce:testId>
                                    </sec:authorize>
                                </div>
                            </ycommerce:testId>
                        </div>
                    </sec:authorize> <sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
                        <div class="login-box">
                            <ycommerce:testId code="header_LoggedUser">
                                <div>
                                    <user:loginHeader />
                                </div>
                                <div class="acc-links">
                                    <a href="<c:url value="/login"/>"><i
                                        class="fa fa-angle-right"></i> <spring:theme code="text.fliegercommerce.texto2" /></a> <a
                                        href="<c:url value="/login?forgotPassword=true"/>"><i
                                        class="fa fa-angle-right"></i> <spring:theme code="text.fliegercommerce.texto3" /></a>
                                </div>
                            </ycommerce:testId>
                        </div>
                    </sec:authorize> <!-- CODIGO ORIGINAL DA HERING ABAIXO --> <%-- 	<div class="login-box">
	           				<div>
	           					<form method="post" action="${urlHttpsLoginHeader}">
	           						<input type="email" placeholder="E-mail">
	           						<input type="password" placeholder="Senha">
	           						<input type="submit" value="Entrar">
	           					</form>
	            				<a href="#" style="display:none" class="facebook"><i class="fa fa-facebook"></i> Login com o Facebook</a>
	           				</div>
	           				<div class="acc-links">
	           					<a href="<c:url value="/login"/>"><i class="fa fa-angle-right"></i> crie uma conta</a>
	           					<a class="for-2" href="<c:url value="/login?forgotPassword=true"/>"><i class="fa fa-angle-right"></i> esqueci minha senha</a>
	           				</div>
						</div> --%></li>
            </ul>
        </c:if>
    </div>
</nav>
