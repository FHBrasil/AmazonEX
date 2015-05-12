<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="cartData" required="true" type="de.hybris.platform.commercefacades.order.data.CartData" %>
<%@ attribute name="showTaxEstimate" required="false" type="java.lang.Boolean" %>
<%@ attribute name="showTax" required="false" type="java.lang.Boolean" %>
<%@ attribute name="showCalculateDeliveryComponent" required="false" type="java.lang.Boolean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
	<c:if test="${showCalculateDeliveryComponent and not empty cartData.deliveryAddress}">
		<c:set var="showCalculateDeliveryComponent" value="false" />
	</c:if>
</sec:authorize>

<%-- CART TOTALS FOR DZARM STORE --%>
<c:if test="${themeName == 'black'}">

	<table id="orderTotals">
		<thead>
		<tr>
			<td>
				<spring:theme code="order.order.totals"/>
			</td>
			<td></td>
		</tr>
		</thead>
		<tfoot>
		<tr>
			<td>
				<spring:theme code="basket.page.totals.total"/>
			</td>
			<td>
				<ycommerce:testId code="cart_totalPrice_label">
					<c:choose>
						<c:when test="${showTax}">
							<format:price priceData="${cartData.totalPriceWithTax}"/>
						</c:when>
						<c:otherwise>
							<format:price priceData="${cartData.totalPrice}"/>
						</c:otherwise>
					</c:choose>
	
				</ycommerce:testId>
			</td>
		</tr>
		</tfoot>
		<tbody>
		<tr>
			<td>
				<spring:theme code="basket.page.totals.subtotal"/>
			</td>
			<td>
				<ycommerce:testId code="Order_Totals_Subtotal">
					<format:price priceData="${cartData.subTotal}"/>
				</ycommerce:testId>
			</td>
		</tr>
	
		<c:if test="${cartData.totalDiscounts.value > 0}">
			<tr class="savings">
				<td>
					<spring:theme code="basket.page.totals.savings"/>
				</td>
				<td>
					<ycommerce:testId code="Order_Totals_Savings">
						<format:price priceData="${cartData.totalDiscounts}"/>
					</ycommerce:testId>
				</td>
			</tr>
		</c:if>			
				<c:set var="varEconomy" value="0"/>
				<c:set var="varCount" value ="0" />
					<c:forEach items="${cartData.entries}" var="entry">
						<c:set var="varCount" value ="${varCount + 1}" />
						<c:if test="${entry.product.oldPrice gt 0}">
						<c:set var="varQuantity" value="${entry.quantity}" />
	 					<c:set var="varParcialTotal" value="${(varQuantity * entry.product.oldPrice)}" />
	 					<c:set	var="varEconomy" value="${varParcialTotal +  varEconomy}" />
						</c:if>
					</c:forEach>
					<c:choose>
						<c:when test="${varEconomy > cartData.subTotal.value}">
							<c:set var="varSaving" value="${varEconomy - cartData.subTotal.value}"></c:set>
						</c:when>
						<c:otherwise>
								<c:set var="varSaving" value="${0}"></c:set>
						</c:otherwise>
					</c:choose>
	
				<!-- Shipping Line -->
			<c:if test="${showCalculateDeliveryComponent || not empty cartData.deliveryCost}">
				<tr>
					<td>
						<spring:theme code="basket.page.totals.delivery.calculate"/>
					</td>
					<td>
						<div style="float:right">
							<form:form action="${request.contextPath}/cart/calculateDelivery" method="POST" class="calculateDelivery">
								<input type="text" name="postalCode" placeholder="insira o cep" required="" title="Digite o CEP conforme o modelo 99999999" pattern="[0-9]{8}" maxlength="8" />
								<button type="submit">
									<spring:theme code="search.meta.title" />
								</button>
							</form:form>
						</div>					
					</td>
				</tr>
			</c:if>
			
			<c:if test="${not empty cartData.deliveryCost}">
			<tr>
				<td>
					<spring:theme code="basket.page.totals.delivery"/>
				</td>
				<td>
					<format:price priceData="${cartData.deliveryCost}" displayFreeForZero="TRUE"/>
					<c:if test="${not empty cartData.deliveryMode}">
						<br> ${cartData.deliveryMode.description}
					</c:if>
				</td>
			</tr>
			</c:if>
				
				<!-- Economy Line -->
	<%-- 			<c:if test="${varSaving > 0}"> --%>
	<!-- 				<tr> -->
	<!-- 					<td class="total"> -->
	<%-- 						<spring:theme code="basket.page.totals.economy"/>: --%>
	<!-- 					</td> -->
	<!-- 					<td class="totalEconomy"> -->
	<%-- 							<spring:theme code="product.currencynotation"/><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${varSaving}" /> --%>
	<!-- 					</td> -->
	<!-- 				</tr> -->
	<%-- 			</c:if> --%>
				<!-- /Economy Line -->
				
				
		<c:if test="${cartData.net && cartData.totalTax.value > 0 && showTax}">
			<tr>
				<td class="total">
					<spring:theme code="basket.page.totals.netTax"/>
				</td>
				<td class="total">
					<format:price priceData="${cartData.totalTax}"/>
				</td>
			</tr>
		</c:if>
		<cart:taxExtimate cartData="${cartData}" showTaxEstimate="${showTaxEstimate}"/>
		</tbody>
	</table>
	
	<c:if test="${not cartData.net}">
		<div class="realTotals">
			<ycommerce:testId code="cart_taxes_label">
				<p>
					<spring:theme code="basket.page.totals.grossTax" arguments="${cartData.totalTax.formattedValue}" argumentSeparator="!!!!"/>
				</p>
			</ycommerce:testId>
		</div>
	</c:if>
	<c:if test="${cartData.net && not showTax }">
		<div class="realTotals">
			<ycommerce:testId code="cart_taxes_label">
				<p>
					<spring:theme code="basket.page.totals.noNetTax"/>
				</p>
			</ycommerce:testId>
		</div>
	</c:if>

</c:if>	

<%-- CART TOTALS FOR DZARM STORE --%>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">

	<div chaordic="middle">
	</div>	
	<section id="cart-bottom">
		<div class="container">
			<div class="left">
				
				<%-- \\ CALCULE O FRETE --%>
				<form:form action="${request.contextPath}/cart/calculateDelivery" method="POST" class="calculate-freight" id="calculateFrete">
					<h3><spring:theme code="basket.page.totals.delivery.calculate"/></h3>
					<div>
						<label>Insira seu CEP:</label>
						<input type="text" maxlength="5" id="cep-01" name="cep_01" required>-
						<input type="text" maxlength="3" id="cep-02" name="cep_02" required>
						<input type="hidden" id="cepCode" name="postalCode" pattern="[0-9]{8}" maxlength="8"  style="width:110px"/>
						<button type="submit" onclick="cepFunction()" class="btn-ok btn">ok</button>
					</div>
				</form:form>
				
				<%-- CUPOM DE DESCONTO --%>
				<%-- cartVoucher.tag --%>
				<cart:cartVoucher />
				
			</div>
			
			<%-- RESUMO DO PEDIDO // --%>
			<div class="right">
				<ul>
					<li><a href="/store"><i class="fa fa-angle-left"></i> Continuar comprando</a></li>
					<li class="right">Resumo do pedido</li>
				</ul>
				<div>
					<ul class="total-info">
					<!-- -->
					<c:if test="${cartData.totalDiscounts.value > 0}">
						<tr class="savings">
							<td>
								<spring:theme code="basket.page.totals.savings"/>
							</td>
							<td>
								<ycommerce:testId code="Order_Totals_Savings">
									<format:price priceData="${cartData.totalDiscounts}"/>
								</ycommerce:testId>
							</td>
						</tr>
					</c:if>
					<!-- -->
						<li class="total">
							<strong><format:price priceData="${cartData.totalPrice}"/></strong> 
							<c:if test="${not empty cartData.deliveryCost}">
								(Frete 
								<format:price priceData="${cartData.deliveryCost}" displayFreeForZero="TRUE"/>
								<c:if test="${not empty cartData.deliveryMode}">
									<br> ${cartData.deliveryMode.description}
								</c:if>)
							</c:if>
						</li>
						<c:if test="${not empty instalments}">
							<li>${instalments}</li>
						</c:if>
					</ul>
					
					<c:url value="/cart/checkout/" var="checkoutUrl"/>
					<a href="${checkoutUrl}" class="btn-checkout btn"><spring:theme code="checkout.checkout" /></a>
				</div>
			</div>
		</div>
	</section>
</c:if>