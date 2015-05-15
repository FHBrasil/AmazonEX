<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component"%>
<c:choose>
    <c:when test="${not empty productReferences and component.maximumNumberProducts > 0}">
        <style>
.caracteristicas {
	width: 50% !important
}
</style>
        <c:set var="referenceType" value="${component.productReferenceTypes[0]}" />
        <c:choose>
            <c:when test="${referenceType == 'CROSSELLING'}">
                <header>
                    <h1>
                        Compre tamb&eacute;m estes produtos e <b>tenha um look completo</b>
                    </h1>
                </header>
            </c:when>
            <c:otherwise>
                <div class="container">
                    <section class="product-section">
                        <header>
                            <h1>${component.title}</h1>
                            <a href="#" class="view-more"><spring:theme
                                    code="storeFinder.see.more" /></a>
                        </header>
                    </section>
                </div>
            </c:otherwise>
        </c:choose>
        <c:if test="${referenceType == 'SIMILAR'}">
            <c:set var="positionClass" value="left" />
        </c:if>
        <c:if test="${referenceType == 'OTHERS'}">
            <c:set var="positionClass" value="" />
        </c:if>
        <div class="section-mini-content ${positionClass}">
            <input type="hidden" id="control" code="" color="" main="" />
            <c:forEach items="${productReferences}" var="productReference">
                <div class="product productDetailsHolder product-mini with-checkbox"
                    id="${productReference.target.code.substring(0,4)}">
                    <input type="checkbox" name="crossProduct"
                        value="${productReference.target.code}" />
                    <product:productPrimaryImage product="${productReference.target}"
                        format="product" />
                    <div class="info">
                        <c:url var="productReferenceUrl" value="${productReference.target.url}" />
                        <h2>
                            <a href="${productReferenceUrl}">${productReference.target.name}</a>
                        </h2>
                        <c:if
                            test="${productReference.target.freeShipping || productReference.target.newProduct || productReference.target.oldPrice > productReference.target.price.value}">
                            <div class="selos">
                                <ul>
                                    <c:if
                                        test="${productReference.target.oldPrice > productReference.target.price.value}">
                                        <li class="promocao"><spring:theme
                                                code="product.attractions.sale.${themeName}" /></li>
                                    </c:if>
                                    <c:if test="${productReference.target.freeShipping}">
                                        <li class="frete"><spring:theme
                                                code="product.attractions.freeshipping" /></li>
                                    </c:if>
                                    <c:if test="${productReference.target.newProduct}">
                                        <li class="lancamento"><spring:theme
                                                code="product.attractions.new" /></li>
                                    </c:if>
                                </ul>
                            </div>
                        </c:if>
                        <div class="precos">
                            <c:choose>
                                <c:when test="${productReference.target.fromPrice}">
                                    <!-- Have OLD PRICE -->
                                    <s> <spring:theme code="product.volumePrices.from" /> <spring:theme
                                            code="product.currencynotation" /> <fmt:formatNumber
                                            value="${productReference.target.oldPrice}"
                                            type="number" minFractionDigits="2" />
                                    </s>
                                    <strong> <spring:theme code="product.volumePrices.to" />
                                        <format:fromPrice
                                            priceData="${productReference.target.price}" />
                                    </strong>
                                </c:when>
                                <c:otherwise>
                                    <strong> <format:fromPrice
                                            priceData="${productReference.target.price}" />
                                    </strong>
                                </c:otherwise>
                            </c:choose>
                            <p>
                                <c:choose>
                                    <c:when test="${productReference.target.priceParcels > 1}">
											${productReference.target.priceParcels}<spring:theme code="product.volumePrices.parcel" />
                                        <b> <spring:theme code="product.currencynotation" /> <fmt:formatNumber
                                                value="${productReference.target.parcelUnitPrice}"
                                                type="number" minFractionDigits="2" />
                                        </b>
                                    </c:when>
                                    <c:otherwise>
                                        <spring:theme code="product.payment.methods.not.parceled" />
                                    </c:otherwise>
                                </c:choose>
                            </p>
                        </div>
                        <c:choose>
                            <c:when
                                test="${productReference.target.purchasable && productReference.target.stock.stockLevelStatus.code != 'outOfStock'}">
                                <product:productVariantSelector product="${productReference.target}"
                                    useSizesProperty="false" />
                            </c:when>
                            <c:otherwise>
                                <div class="btn-group">
                                    <div class="btn esgotado">Produto Esgotado</div>
                                    <a href="#" class="btn avise-me">Avise-me</a>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </c:forEach>
            <div class="comprar-junto hide">
                <div class="left">
                    <div>
                        Valor dos <b id="quantity"></b> juntos
                    </div>
                    <div>
                        fica em <b id="totalPrice"></b>
                    </div>
                    <div id="installmentPrice"></div>
                </div>
                <button class="btn btn-big comprar">Comprar Junto</button>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <component:emptyComponent />
    </c:otherwise>
</c:choose>
