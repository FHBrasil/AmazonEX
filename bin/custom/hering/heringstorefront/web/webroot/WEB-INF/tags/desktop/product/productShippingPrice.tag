<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true"
    type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:url value="${product.url}/calculateDelivery" var="productCalculateDeliveryActionUrl" />
<a class="fancybox prazos-entrega" href="#modal-prazos-entrega"><spring:theme
        code="product.shipping.price.check" /></a>
<div id="modal-prazos-entrega">
    <div class="content">
        <h3>Valor de entrega</h3>
        <form:form id="formCalculateDelivery" action="${productCalculateDeliveryActionUrl}"
            method="POST" class="calculateDelivery">
            <div class="f-row">
                <input id="cep" type="text" required name="zipCode" data-placefocus="Insira seu CEP"
                    maxlength="8">
                <button type="submit" class="btn btn-ok" disabled="disabled">ok</button>
            </div>
        </form:form>
        <div class="response">
            <font color="#3E9C00">Frete:</font>
        </div>
    </div>
</div>
