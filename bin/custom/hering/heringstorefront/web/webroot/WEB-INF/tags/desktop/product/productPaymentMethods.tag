<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${themeName == 'black'}">
<style>

.p-pay-met {border-bottom: 1px solid #ddd; padding-bottom: 30px;}

.p-pay-met-link {margin: 50px 0 0 0;}

</style>

<div class="product-payment-methods p-pay-met">
	<a class="product-payment-methods-link p-pay-met-link" title="<spring:theme code="product.payment.methods"/>" href="#" > <spring:theme code="product.payment.methods"/> </a>
</div>

<div class="modalPagamento" style="display:none">
	<h4>formas de pagamento</h4>

	<div class="tiposPagamento">
		<ul class="bandeiras">
			<li class="visa"><a href="#" >Visa</a></li>
			<li class="master"><a href="#" >Master</a></li>
			<li class="dinners"><a href="#" >Dinners</a></li>
<!-- 			<li class="hipercard"><a href="#" >Hipercard</a></li> -->
			<li class="american"><a href="#" >American</a></li>
			<li class="boleto"><a href="#" >Boleto</a></li>
		</ul>

		<div class="tabFormas">
		<!-- VISA -->
			<div class="contentTab">
				<table class="formas visa">
					<thead>
						<tr>
							<th><spring:theme code="product.payment.methods.parcel.max.number"/>(${product.priceParcels}) </th>
							<th><spring:theme code="product.payment.methods.each.parcel.value"/></th>
						</tr>
					</thead>

					<tbody>


						<!-- FINANCING -->
						<c:set var="parcelsCounter" value="1" />
						<c:forTokens items="${product.allParcelsSimpleSplitedArray}" delims="/" var="parcelas" >
								<c:choose>
									<c:when test="${parcelsCounter eq 1}">
										<tr>
											<th><spring:theme code="product.payment.methods.card"/> Visa <spring:theme code="product.payment.methods.not.parceled"/></th>
											<td>R$ ${product.price.getValue()}</td>
										</tr>
										<c:set var="parcelsCounter" value="${parcelsCounter + 1}" />
									</c:when>
									<c:otherwise>
										<tr>
				          					<th><spring:theme code="product.payment.methods.card"/> Visa - ${parcelsCounter} -   <spring:theme code="product.payment.methods.Parceled"/> </th>
											<td>
												R$ ${parcelas}
											</td>
											<c:set var="parcelsCounter" value="${parcelsCounter + 1}" />
										</tr>
									</c:otherwise>
									</c:choose>
								</c:forTokens>
					</tbody>
				</table>
			</div>


			<!-- MASTER -->
			<div class="contentTab">
				<table class="formas master">
					<thead>
						<tr>
							<th><spring:theme code="product.payment.methods.parcel.max.number"/> (${product.priceParcels}) </th>
							<th><spring:theme code="product.payment.methods.each.parcel.value"/></th>
						</tr>
					</thead>
					<tbody>
					
						<c:set var="parcelsCounter" value="1" />
						<c:forTokens items="${product.allParcelsSimpleSplitedArray}" delims="/" var="parcelas" >
								<c:choose>
									<c:when test="${parcelsCounter eq 1}">
										<tr>
											<th><spring:theme code="product.payment.methods.card"/> Master <spring:theme code="product.payment.methods.not.parceled"/></th>
											<td>R$ ${product.price.getValue()}</td>
										</tr>
										<c:set var="parcelsCounter" value="${parcelsCounter + 1}" />
									</c:when>
									<c:otherwise>
										<tr>
				          					<th><spring:theme code="product.payment.methods.card"/> Master - ${parcelsCounter} -   <spring:theme code="product.payment.methods.Parceled"/></th>
											<td> 
												<spring:theme code="product.currencynotation"/> ${parcelas}
											</td>
											<c:set var="parcelsCounter" value="${parcelsCounter + 1}" />
										</tr>
									</c:otherwise>
									</c:choose>
								</c:forTokens>
					</tbody>
				</table>
			</div>
			
			<!-- DINERS -->
			<div class="contentTab">
				<table class="formas diners">
					<thead>
						<tr>
							<th><spring:theme code="product.payment.methods.parcel.max.number"/> (${product.priceParcels}) </th>
							<th><spring:theme code="product.payment.methods.each.parcel.value"/></th>
						</tr>
					</thead>
					<tbody>
					
						<c:set var="parcelsCounter" value="1" />
						<c:forTokens items="${product.allParcelsSimpleSplitedArray}" delims="/" var="parcelas" >
								<c:choose>
									<c:when test="${parcelsCounter eq 1}">
										<tr>
											<th><spring:theme code="product.payment.methods.card"/> Diners <spring:theme code="product.payment.methods.not.parceled"/></th>
											<td>R$ ${product.price.getValue()}</td>
										</tr>
										<c:set var="parcelsCounter" value="${parcelsCounter + 1}" />
									</c:when>
									<c:otherwise>
										<tr>
				          					<th><spring:theme code="product.payment.methods.card"/> Diners - ${parcelsCounter} -   <spring:theme code="product.payment.methods.Parceled"/></th>
											<td>
												<spring:theme code="product.currencynotation"/> ${parcelas}
											</td>
											<c:set var="parcelsCounter" value="${parcelsCounter + 1}" />
										</tr>
									</c:otherwise>
									</c:choose>
								</c:forTokens>
					</tbody>
				</table>
			</div>
			
			<!-- Hipercard -->
<!-- 			<div class="contentTab"> -->
<!-- 				<table class="formas hipercard"> -->
<!-- 					<thead> -->
<!-- 						<tr> -->
<%-- 							<th><spring:theme code="product.payment.methods.parcel.max.number"/> (${product.priceParcels}) </th> --%>
<%-- 							<th><spring:theme code="product.payment.methods.each.parcel.value"/></th> --%>
<!-- 						</tr> -->
<!-- 					</thead> -->
<!-- 					<tbody> -->
					
<%-- 						<c:set var="parcelsCounter" value="1" /> --%>
<%-- 						<c:forTokens items="${product.allParcelsSimpleSplitedArray}" delims="/" var="parcelas" > --%>
<%-- 								<c:choose> --%>
<%-- 									<c:when test="${parcelsCounter eq 1}"> --%>
<!-- 										<tr> -->
<%-- 											<th><spring:theme code="product.payment.methods.card"/> Hipercard <spring:theme code="product.payment.methods.not.parceled"/></th> --%>
<%-- 											<td>R$ ${product.price.getValue()}</td> --%>
<!-- 										</tr> -->
<%-- 										<c:set var="parcelsCounter" value="${parcelsCounter + 1}" /> --%>
<%-- 									</c:when> --%>
<%-- 									<c:otherwise> --%>
<!-- 										<tr> -->
<%-- 				          					<th><spring:theme code="product.payment.methods.card"/> Hipercard - ${parcelsCounter} -   <spring:theme code="product.payment.methods.Parceled"/></th> --%>
<!-- 											<td>  -->
<%-- 												<spring:theme code="product.currencynotation"/> ${parcelas} --%>
<!-- 											</td> -->
<%-- 											<c:set var="parcelsCounter" value="${parcelsCounter + 1}" /> --%>
<!-- 										</tr> -->
<%-- 									</c:otherwise> --%>
<%-- 									</c:choose> --%>
<%-- 								</c:forTokens> --%>
<!-- 					</tbody> -->
<!-- 				</table> -->
<!-- 			</div> -->
			
			<!-- American Express -->
			<div class="contentTab">
				<table class="formas american">
					<thead>
						<tr>
							<th><spring:theme code="product.payment.methods.parcel.max.number"/> (${product.priceParcels}) </th>
							<th><spring:theme code="product.payment.methods.each.parcel.value"/></th>
						</tr>
					</thead>
					<tbody>
					
						<c:set var="parcelsCounter" value="1" />
						<c:forTokens items="${product.allParcelsSimpleSplitedArray}" delims="/" var="parcelas" >
								<c:choose>
									<c:when test="${parcelsCounter eq 1}">
										<tr>
											<th><spring:theme code="product.payment.methods.card"/> American Express <spring:theme code="product.payment.methods.not.parceled"/></th>
											<td>R$ ${product.price.getValue()}</td>
										</tr>
										<c:set var="parcelsCounter" value="${parcelsCounter + 1}" />
									</c:when>
									<c:otherwise>
										<tr>
				          					<th><spring:theme code="product.payment.methods.card"/> American Express - ${parcelsCounter} -   <spring:theme code="product.payment.methods.Parceled"/></th>
											<td> 
												<spring:theme code="product.currencynotation"/> ${parcelas}
											</td>
											<c:set var="parcelsCounter" value="${parcelsCounter + 1}" />
										</tr>
									</c:otherwise>
									</c:choose>
								</c:forTokens>
					</tbody>
				</table>
			</div>
			
			<div class="contentTab">
				<table class="formas boleto">
					<thead>

					</thead>
					<tbody>
						<c:set var="parcelsCounter" value="1" />
						<c:forTokens items="${product.allParcelsSimpleSplitedArray}" delims="/" var="parcelas" >
								<c:choose>
									<c:when test="${parcelsCounter eq 1}">
										<tr>
											<th><spring:theme code="product.payment.methods.boleto"/>  <spring:theme code="product.payment.methods.not.parceled"/></th>
											<td>R$ ${product.price.getValue()}</td>
										</tr>
										<c:set var="parcelsCounter" value="${parcelsCounter + 1}" />
									</c:when>
									<c:otherwise>

									</c:otherwise>
									</c:choose>
								</c:forTokens>
					</tbody>
				</table>
			</div>
			
		</div>
	</div>
</div>
</c:if>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
<a class="fancybox formas-pagamento" href="#modal-formas-pagamento"><spring:theme code="product.payment.methods"/></a>
<div id="modal-formas-pagamento">
	<div class="content">
		<h3>Formas de pagamento</h3>

		<c:set var="allCards" value="Visa,MasterCard,Diners,American Express,Boleto,Hering,Hipercard,Elo" />

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
									<th><spring:theme code="product.payment.methods.parcel.max.number"/>(${product.priceParcels})</th>
									<th align="center"><spring:theme code="product.payment.methods.each.parcel.value"/></th>
									<th align="center">Valor total</th>
								</tr>
							</thead>
							<tbody>
								<c:forTokens items="${product.allParcelsSimpleSplitedArray}" delims="/" var="parcelas" varStatus="status">
									<%-- BOLETO SOMENTE A VISTA --%>
									<c:if test="${isCard || status.first}">
									<c:set var="parcelsCounter" value="${status.index + 1}" />
									<tr>
										<c:choose>
											<c:when test="${status.first}">
												<th><c:if test="${isCard}"><spring:theme code='product.payment.methods.card'/> </c:if>${cardName} <spring:theme code="product.payment.methods.not.parceled"/></th>
											</c:when>
											<c:otherwise>
												<th><c:if test="${isCard}"><spring:theme code='product.payment.methods.card'/> </c:if>${cardName} - ${parcelsCounter} - <spring:theme code="product.payment.methods.Parceled"/> </th>
											</c:otherwise>
										</c:choose>
										<td align="center"><spring:theme code="product.currencynotation" /> ${parcelas}</td>
										<td align="center"><format:fromPrice priceData="${product.price}" /></td>
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
</c:if>