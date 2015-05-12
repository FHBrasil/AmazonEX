<%@ tag import="java.util.Calendar"%>
<%@ tag import="java.util.GregorianCalendar"%>
<%@ tag import="java.util.Date"%>
<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${themeName == 'black'}">
<c:choose>
	<c:when test="${empty product.volumePrices}">
	<div class="big-price right">
		<div class="product-price-attractions">
		<table>
		<tr> 
			<td>
				<c:choose>
				     <c:when test="${product.fromPrice}"> 
				     <!-- Have OLD PRICE -->
				     
						<div class="price-from">	<spring:theme code="product.volumePrices.from" /> <spring:theme code="product.currencynotation"/><fmt:formatNumber value="${product.oldPrice}" type="number" minFractionDigits="2" /></div>  <div class="price-to"><spring:theme code="product.volumePrices.to"/> <format:fromPrice priceData="${product.price}" /></div>
					 </c:when>  
					     
        			 <c:otherwise>  
						<div class="price-regular"><format:fromPrice priceData="${product.price}" /></div>
					
					</c:otherwise>
				</c:choose> 
				
			</td>
			<td>
			</td>
		</tr>
		<tr>
			<td>
			<div class="big-price">
					<!-- Parcels -->			
					<div class="price-parcel"> 
						<c:choose>
							<c:when test="${product.priceParcels > 1}">
								${product.priceParcels}
								<spring:theme code="product.volumePrices.parcel"/>
							</c:when>
							<c:otherwise>
								<spring:theme code="product.payment.methods.not.parceled"/>
							</c:otherwise>
						</c:choose>

						
					</div> 
					<div class="price-parcel-price"><spring:theme code="product.currencynotation"/>
						<fmt:formatNumber value="${product.parcelUnitPrice}" type="number" minFractionDigits="2" /> 
					</div>
					<!-- /Parcels -->
			
			<!-- CHAORDIC PARCEL -->
			<div class="chaordicPriceParcel">
				<input type="hidden" class="chaordicNumberParcel" value="${product.priceParcels}" />
				<input type="hidden" class="chaordicPriceParcel" value="${product.parcelUnitPrice}" />
			</div>
			
			</div>
					
			</td>
			<td>
				<c:choose>
										 	<c:when test="${product.blackfriday}">
										 		<!-- Sale Black Friday -->
											     <div class="product-attractions-blackfriday">
											     	BLACK FRIDAY
											     </div>
										 	</c:when>
										 	<c:otherwise>
										 		<c:if test="${product.oldPrice > product.price.value}">
											 		 <!-- Sale Enable -->
												     <div class="product-attractions-sale">
												     	<spring:theme code="product.attractions.sale" />
												     </div>
											     </c:if>
										 	</c:otherwise>
										 </c:choose>
								
				<c:if test="${product.newProduct}">
					<div class="productattractions-new">
						<spring:theme code="product.attractions.new"/>
					</div>
				</c:if>
			</td>
		
		</tr>
		</table>
	</div>
	</div>

	</c:when>
	<c:otherwise>
		<table class="volume-prices" cellpadding="0" cellspacing="0" border="0">
			<thead>
					<th class="volume-prices-quantity"><spring:theme code="product.volumePrices.column.qa"/></th>
					<th class="volume-price-amount"><spring:theme code="product.volumePrices.column.price"/></th>
			</thead>
			<tbody>
				<c:forEach var="volPrice" items="${product.volumePrices}">
					<tr>
						<td class="volume-price-quantity">
							<c:choose>
								<c:when test="${empty volPrice.maxQuantity}">
									${volPrice.minQuantity}+
								</c:when>
								<c:otherwise>
									${volPrice.minQuantity}-${volPrice.maxQuantity}
								</c:otherwise>
							</c:choose>
						</td>
						<td class="volume-price-amount">${volPrice.formattedValue}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:otherwise>
</c:choose>
</c:if>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<section class="clear">
		<div class="precos">
			<c:choose>
				<c:when test="${product.oldPrice > product.price.value}">
					<!-- Have OLD PRICE -->
					<s>
						<spring:theme code="product.volumePrices.from" />
						<spring:theme code="product.currencynotation" />
						<fmt:formatNumber value="${product.oldPrice}" type="number" minFractionDigits="2" />
					</s>
					<strong>
						<spring:theme code="product.volumePrices.to" />
						<format:fromPrice priceData="${product.price}" />
					</strong>
				</c:when>
				<c:otherwise>
					<strong>
						<format:fromPrice priceData="${product.price}" />
					</strong>
				</c:otherwise>
			</c:choose>
			<p>
				<c:choose>
					<c:when test="${product.priceParcels > 1}">
						${product.priceParcels}<spring:theme code="product.volumePrices.parcel" />
						<b><spring:theme code="product.currencynotation"/><fmt:formatNumber value="${product.parcelUnitPrice}" type="number" minFractionDigits="2" /></b> 
					</c:when>
					<c:otherwise>
						<spring:theme code="product.payment.methods.not.parceled" />
					</c:otherwise>
				</c:choose>
			</p>
			<!-- CHAORDIC PARCEL -->
			<div class="chaordicPriceParcel">
				<input type="hidden" class="chaordicNumberParcel" value="${product.priceParcels}" />
				<input type="hidden" class="chaordicPriceParcel" value="${product.parcelUnitPrice}" />
			</div>
		</div>
		<div class="selos">
			<ul>
				<c:choose>
										<c:when test="${product.blackfriday}">
									 		 <!-- Black Friday Enable -->
										     <li class="blackfriday">
										     	BLACK FRIDAY
										     </li>
										</c:when>
										<c:otherwise>
											<c:if test="${product.oldPrice > product.price.value}">
												<!-- Sale Enable -->
											     <li class="promocao">
											     	<spring:theme code="product.attractions.sale.${themeName}" />
											     </li>
											</c:if>
										</c:otherwise>
									</c:choose>
				<c:if test="${product.freeShipping}">
					<li class="frete"><spring:theme code="product.attractions.freeshipping"/></li>
				</c:if>
				<c:if test="${product.newProduct}">
					<li class="lancamento"><spring:theme code="product.attractions.new"/></li>
				</c:if>
			</ul>
		</div>
	</section>
</c:if>