<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.OrderData" %>
<%@ attribute name="orderHistory" required="false" type="de.hybris.platform.commercefacades.order.data.OrderHistoryData" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/desktop/order" %>

<%@ attribute name="containerCSS" required="false" type="java.lang.String" %>
<c:if test="${themeName == 'black'}">
<table id="orderTotals" class="${containerCSS}">
	<thead>
		<tr>
			<td><spring:theme code="text.account.order.orderTotals" text="Order Totals"/></td>
			<td></td>
		</tr>
	</thead>
	<tfoot>
		<tr>
			<td><spring:theme code="text.account.order.total" text="Total:"/></td>
			<c:choose>
				<c:when test="${order.net}">
					<td><format:price priceData="${order.totalPriceWithTax}"/></td>
				</c:when>
				<c:otherwise>
					<td><format:price priceData="${order.totalPrice}"/></td>
				</c:otherwise>
			</c:choose>
		</tr>
	</tfoot>
	<tbody>
		<tr>
			<td><spring:theme code="text.account.order.subtotal" text="Subtotal:"/></td>
			<td><format:price priceData="${order.subTotal}"/></td>
		</tr>
		<c:if test="${order.totalDiscounts.value > 0}">
			<tr class="savings">
				<td><spring:theme code="text.account.order.savings" text="Savings:"/></td>
				<td><format:price priceData="${order.totalDiscounts}"/></td>
			</tr>
		</c:if>
			<c:set var="varEconomy" value="0"/>
			<c:set var="varCount" value ="0" />
			<c:forEach items="${orderData.deliveryOrderGroups}" var="orderGroup">
					<c:forEach items="${orderGroup.entries}" var="entry">
					<c:set var="varCount" value ="${varCount + 1}" />
					<c:if test="${entry.product.oldPrice gt 0}">
					<c:set var="varQuantity" value="${entry.quantity}" />
 					<c:set var="varParcialTotal" value="${(varQuantity * entry.product.oldPrice)}" />
 					<c:set	var="varEconomy" value="${varParcialTotal +  varEconomy}" />
					</c:if>
				</c:forEach>
				</c:forEach>
				<c:choose>
					<c:when test="${varEconomy > order.subTotal.value}">
						<c:set var="varSaving" value="${varEconomy - order.subTotal.value}"></c:set>
					</c:when>
					<c:otherwise>
							<c:set var="varSaving" value="${0}"></c:set>
					</c:otherwise>
				</c:choose>
		
		<tr>
			<td><spring:theme code="text.account.order.delivery" text="Delivery:"/></td>
			<td><format:price priceData="${order.deliveryCost}" displayFreeForZero="true"/></td>
		</tr>
			<!-- Economy Line -->
<%-- 			<c:if test="${varSaving > 0}"> --%>
<!-- 				<tr> -->
<!-- 					<td class="total"> -->
<%-- 						<spring:theme code="basket.page.totals.economy"/> --%>
<!-- 					</td> -->
<!-- 					<td class="totalEconomy"> -->
<%-- 							<spring:theme code="product.currencynotation"/><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${varSaving}" /> --%>
<!-- 					</td> -->
<!-- 				</tr> -->
<%-- 			</c:if> --%>
			<!-- /Economy Line -->
		
		
		<c:if test="${order.net}" >
			<tr>
				<td><spring:theme code="text.account.order.netTax" text="Tax:"/></td>
				<td><format:price priceData="${order.totalTax}"/></td>
			</tr>
		</c:if>

	</tbody>
	
</table>
</c:if>

<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<section id="values" class="right">
		<dl>						
			<dt>Itens (${order.totalItems})</dt>
			<dd class="subtotal"><strong><format:price priceData="${order.subTotal}" displayFreeForZero="true"/></strong></dd>
			<dt class="freight">Frete</dt>
			<dd>
				<format:price priceData="${order.deliveryCost}" displayFreeForZero="true"/> 
				<small>Entrega em at&eacute; ${order.estimatedDeliveryDays} dias &uacute;teis ap&oacute;s a emiss&atilde;o da nota fiscal</small>
			</dd>
			<dt class="method">Pagamento</dt>
			<dd class="method">${orderHistory.paymentMode}
			</dd>
		</dl>
		<dl class="total">
			<dt class="total">Total</dt>
			<dd class="total"><strong><format:price priceData="${order.totalPrice}"/></strong></dd>
		</dl>		
	</section>
</c:if>