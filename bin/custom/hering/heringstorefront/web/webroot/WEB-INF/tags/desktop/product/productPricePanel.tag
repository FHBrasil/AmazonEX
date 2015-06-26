<%@ tag import="java.util.Calendar"%>
<%@ tag import="java.util.GregorianCalendar"%>
<%@ tag import="java.util.Date"%>
<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true"
    type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<p class="price"> <format:fromPrice priceData="${product.price}" /><%--<sup>75&nbsp;&euro;</sup>--%></p>
<p>[FIXED]<small>inkl. MwSt.<br />zzgl. Versand</small></p>




<%-- code original
<section class="clear">
    <div class="precos">
        <c:choose>
            <c:when test="${product.oldPrice > product.price.value}">
                <!-- Have OLD PRICE -->
                <s> <spring:theme code="product.volumePrices.from" /> <spring:theme
                        code="product.currencynotation" /> <fmt:formatNumber
                        value="${product.oldPrice}" type="number" minFractionDigits="2" />
                </s>
                <strong> <spring:theme code="product.volumePrices.to" /> <format:fromPrice
                        priceData="${product.price}" />
                </strong>
            </c:when>
            <c:otherwise>
                <strong> <format:fromPrice priceData="${product.price}" />
                </strong>
            </c:otherwise>
        </c:choose>
        <p>
            <c:choose>
                <c:when test="${product.priceParcels > 1}">
                        ${product.priceParcels}<spring:theme code="product.volumePrices.parcel" />
                    <b><spring:theme code="product.currencynotation" />
                        <fmt:formatNumber value="${product.parcelUnitPrice}" type="number"
                            minFractionDigits="2" /></b>
                </c:when>
                <c:otherwise>
                    <spring:theme code="product.payment.methods.not.parceled" />
                </c:otherwise>
            </c:choose>
        </p>
        <!-- CHAORDIC PARCEL -->
        <div class="chaordicPriceParcel">
            <input type="hidden" class="chaordicNumberParcel" value="${product.priceParcels}" /> <input
                type="hidden" class="chaordicPriceParcel" value="${product.parcelUnitPrice}" />
        </div>
    </div>
    <div class="selos">
        <ul>
            <c:choose>
                <c:when test="${product.blackfriday}">
                    <!-- Black Friday Enable -->
                    <li class="blackfriday">BLACK FRIDAY</li>
                </c:when>
                <c:otherwise>
                    <c:if test="${product.oldPrice > product.price.value}">
                        <!-- Sale Enable -->
                        <li class="promocao"><spring:theme
                                code="product.attractions.sale.${themeName}" /></li>
                    </c:if>
                </c:otherwise>
            </c:choose>
            <c:if test="${product.freeShipping}">
                <li class="frete"><spring:theme code="product.attractions.freeshipping" /></li>
            </c:if>
            <c:if test="${product.newProduct}">
                <li class="lancamento"><spring:theme code="product.attractions.new" /></li>
            </c:if>
        </ul>
    </div>
</section>
--%>
