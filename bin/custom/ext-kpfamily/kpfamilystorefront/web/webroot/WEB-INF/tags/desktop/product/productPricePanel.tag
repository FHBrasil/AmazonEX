<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true"
    type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="row">
    <div class="col-xs-4 v-bottom">
        <p class="price">
            ${product.priceEuro},<sup>${product.priceCents} &euro;</sup>
        </p>
        <p>
            <small>
                <spring:theme code="product.tax.vat.included" text="inkl. MwSt."/>
                <br/>
                <spring:theme code="product.tax.shipping.excluded" text="zzgl. Versand"/>
            </small>
        </p>
    <%-- Again, leave the empty comment below. --%>
    </div><!--
    --><product:productDetailsShipping product="${product}"/>
</div>

<%-- <c:choose> --%>
<%-- 	<c:when test="${empty product.volumePrices}"> --%>
<!-- 		<div class="big-price right"> -->
<%-- 			<format:fromPrice priceData="${product.price}" /> --%>
<!-- 		</div> -->
<%-- 	</c:when> --%>
<%-- 	<c:otherwise> --%>
<!-- 		<table class="volume-prices" cellpadding="0" cellspacing="0" border="0"> -->
<!-- 			<thead> -->
<%-- 					<th class="volume-prices-quantity"><spring:theme code="product.volumePrices.column.quantity"/></th> --%>
<%-- 					<th class="volume-price-amount"><spring:theme code="product.volumePrices.column.price"/></th> --%>
<!-- 			</thead> -->
<!-- 			<tbody> -->
<%-- 				<c:forEach var="volPrice" items="${product.volumePrices}"> --%>
<!-- 					<tr> -->
<!-- 						<td class="volume-price-quantity"> -->
<%-- 							<c:choose> --%>
<%-- 								<c:when test="${empty volPrice.maxQuantity}"> --%>
<%-- 									${volPrice.minQuantity}+ --%>
<%-- 								</c:when> --%>
<%-- 								<c:otherwise> --%>
<%-- 									${volPrice.minQuantity}-${volPrice.maxQuantity} --%>
<%-- 								</c:otherwise> --%>
<%-- 							</c:choose> --%>
<!-- 						</td> -->
<%-- 						<td class="volume-price-amount">${volPrice.formattedValue}</td> --%>
<!-- 					</tr> -->
<%-- 				</c:forEach> --%>
<!-- 			</tbody> -->
<!-- 		</table> -->
<%-- 	</c:otherwise> --%>
<%-- </c:choose> --%>
