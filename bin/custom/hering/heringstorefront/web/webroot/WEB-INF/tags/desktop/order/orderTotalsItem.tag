<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="order" required="true"
    type="de.hybris.platform.commercefacades.order.data.OrderData"%>
<%@ attribute name="orderHistory" required="false"
    type="de.hybris.platform.commercefacades.order.data.OrderHistoryData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/desktop/order"%>
<%@ attribute name="containerCSS" required="false" type="java.lang.String"%>
<section id="values" class="right">
<c:forEach items="${orderHistory.previewEntries}" var="productOrder" begin="0" end="4" varStatus="status">
	<div class="panel-body pos150327">
		<div class="col-sm-3 img150327" 
			style="background-image: url(http://88.198.78.166/medias/sys_master/media/2013-02/011505000082_baby-bottle-270ml-silikon-groesse-2-rosa_mam_t150.jpg)">
			<a href="#">
<%-- 				<product:productPrimaryImage product="${productOrder.product}" format="product"/> --%>
			</a>
		</div>
		<div class="col-sm-9 item150327">
			<a href="${productOrder.product.url}">
				<b>${productOrder.product.name}</b> ${productOrder.product.description}
			</a>
			<br>
			<small>Artikel-Nr.: ${productOrder.product.code}</small>
		</div>
		<div class="col-xs-2 col-sm-1">
			<div class="title150312">Anz.</div>
			<span class="value3150312">${productOrder.quantity}</span>
		</div>
		<div class="col-xs-5 col-sm-2">
			<div class="title150312">Preis</div>
			<span class="value3150312">
				<format:price priceData="${productOrder.basePrice}" /></span>
		</div>
		<div class="col-xs-5 col-sm-2">
			<div class="title150312">Gesamt</div>
			<span class="value3150312"><format:price priceData="${productOrder.totalPrice}" /></span>
		</div>
		<div class="col-xs-12 col-sm-4 linestatus150327">
			<div class="title150312">Status</div>
			<b>
				<span class="value3150312">
					<span class="glyphicon glyphicon glyphicon-stop text-onstock"></span>
					${orderHistory.statusDisplay}
				</span>
			</b>
		</div>
	</div>
</c:forEach>
<!--     <dl> -->
<%--         <dt><spring:theme code="text.fliegercommerce.texto85"/> (${order.totalItems})</dt> --%>
<!--         <dd class="subtotal"> -->
<%--             <strong><format:price priceData="${order.subTotal}" displayFreeForZero="true" /></strong> --%>
<!--         </dd> -->
<%--         <dt class="freight"><spring:theme code="text.fliegercommerce.texto59"/></dt> --%>
<!--         <dd> -->
<%--             <format:price priceData="${order.deliveryCost}" displayFreeForZero="true" /> --%>
<%--             <small><spring:theme code="text.fliegercommerce.texto86"/> ${order.estimatedDeliveryDays} <spring:theme code="text.fliegercommerce.texto87"/></small> --%>
<!--         </dd> -->
<%--         <dt class="method"><spring:theme code="text.fliegercommerce.texto75"/></dt> --%>
<%--         <dd class="method">${orderHistory.paymentMode}</dd> --%>
<!--     </dl> -->
<!--     <dl class="total"> -->
<%--         <dt class="total"><spring:theme code="text.fliegercommerce.texto77"/></dt> --%>
<!--         <dd class="total"> -->
<%--             <strong><format:price priceData="${order.totalPrice}" /></strong> --%>
<!--         </dd> -->
<!--     </dl> -->
</section>
