<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true"
    type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="name" value="${user.firstName} ${user.lastName}" />
<form:form class="esgotado-form" action="${request.contextPath}/notifyme/notifyme-register"
    method="POST">
    <input type="hidden" name="code" value="${product.code}" />
    <input type="hidden" name="baseStore" value="${themeName}" />
    <h3>
        <spring:theme code="notifyme.whenAvailableHering" />
    </h3>
    <input type="text" data-placefocus="Nome" name="nameNotify" id="name_notify"
        value="${name!='Anonymous '? name: ''}" />
    <input type="email" data-placefocus="E-mail" name="emailNotify"
        value="${user.firstName!='Anonymous'?user.uid : ''}" />
    <div class="dias">
        <label><input type="radio" name="daysNotify" value="7" id="days7" checked> <spring:theme
                code="notifyme.7daysHering" /></label> <label><input type="radio" name="daysNotify"
            value="14" id="days14"> <spring:theme code="notifyme.14daysHering" /></label> <label><input
            type="radio" name="daysNotify" value="21" id="days21"> <spring:theme
                code="notifyme.21daysHering" /></label>
    </div>
    <c:set value="btn btn-square btn-transparent-blue-border" var="classBotao" />
    <button class="${classBotao}">
        <spring:theme code="notifyme.confirmRequestHering" text="Confirm Request" />
    </button>
</form:form>
