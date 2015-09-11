<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ attribute name="product" required="true"
    type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<a class="fancybox formas-pagamento" href="#modal-formas-pagamento"><spring:theme
        code="product.payment.methods" /></a>
<div id="modal-formas-pagamento">
    <div class="content">
        <h3><spring:theme code="text.fliegercommerce.texto16"/></h3>
        <c:set var="allCards"
            value="Visa,MasterCard,Diners,American Express,Boleto,Hering,Hipercard,Elo" />
        <div class="tabs">
            <ul class="tabs-header">
                <li class="vs active"><a href="#">visa</a></li>
                <li class="ms"><a href="#">master</a></li>
                <li class="dn"><a href="#">diners</a></li>
                <li class="amex"><a href="#">amex</a></li>
                <li class="bl"><a href="#">boleto</a></li>
                <li class="her"><a href="#">hering</a></li>
                <li class="hc"><a href="#">hipercard</a></li>
                <li class="elo"><a href="#">elo</a></li>
            </ul>
            <div class="tabs-content">
                <c:forTokens items="${allCards}" delims="," var="cardName">
                    <c:set var="isCard" value="${cardName != 'Boleto'}" />
                    <div class="tab">
                        <table class="formas">
                            <thead>
                                <tr>
                                    <th><spring:theme
                                            code="product.payment.methods.parcel.max.number" />(${product.priceParcels})</th>
                                    <th align="center"><spring:theme
                                            code="product.payment.methods.each.parcel.value" /></th>
                                    <th align="center"><spring:theme code="text.fliegercommerce.texto84"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forTokens items="${product.allParcelsSimpleSplitedArray}"
                                    delims="/" var="parcelas" varStatus="status">
                                    <%-- BOLETO SOMENTE A VISTA --%>
                                    <c:if test="${isCard || status.first}">
                                        <c:set var="parcelsCounter" value="${status.index + 1}" />
                                        <tr>
                                            <c:choose>
                                                <c:when test="${status.first}">
                                                    <th><c:if test="${isCard}">
                                                            <spring:theme
                                                                code='product.payment.methods.card' />
                                                        </c:if>${cardName} <spring:theme
                                                            code="product.payment.methods.not.parceled" /></th>
                                                </c:when>
                                                <c:otherwise>
                                                    <th><c:if test="${isCard}">
                                                            <spring:theme
                                                                code='product.payment.methods.card' />
                                                        </c:if>${cardName} - ${parcelsCounter} - <spring:theme
                                                            code="product.payment.methods.Parceled" />
                                                    </th>
                                                </c:otherwise>
                                            </c:choose>
                                            <td align="center"><spring:theme
                                                    code="product.currencynotation" /> ${parcelas}</td>
                                            <td align="center"><format:fromPrice
                                                    priceData="${product.price}" /></td>
                                        </tr>
                                    </c:if>
                                </c:forTokens>
                            </tbody>
                        </table>
                    </div>
                </c:forTokens>
            </div>
        </div>
    </div>
</div>
