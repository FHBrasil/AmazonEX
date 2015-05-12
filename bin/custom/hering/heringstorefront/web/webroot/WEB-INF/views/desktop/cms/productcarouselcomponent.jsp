<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="showcase" tagdir="/WEB-INF/tags/desktop/showcase" %>


<c:if test="${themeName == 'black'}">
	<c:choose>
		<c:when test="${not empty productData}">
		<!-- productData not empty -->
			<div class="scroller">
				<h4 class="block-title"><span>${title}</span></h4>
				<c:choose>
					<c:when test="${component.popup}">
						<!-- component.popup -->
						<ul class="carousel jcarousel-skin popup">
							<c:forEach items="${productData}" var="product">
							<div class="price-regular"><format:fromPrice priceData="${product.price}" />
								<c:url value="${product.url}/quickView" var="productQuickViewUrl"/>
								<li>
									<a href="${productQuickViewUrl}" class="popup scrollerProduct">
										<div class="thumb">
											<product:productPrimaryImageCarouselComponent product="${product}" format="store"/>
										</div>
										<div class="priceContainer">
												<div class="product-price-attractions">
												<table>
												<tr> 
													<td>
														<c:choose>
														<c:when test="${product.oldPrice > product.price.value}">
														     <!-- Have OLD PRICE --> 
															     
																<div class="price-from">	
																	<spring:theme code="product.volumePrices.from" /> 
																	<spring:theme code="product.currencynotation"/>
																	<fmt:formatNumber value="${product.oldPrice}" type="number" minFractionDigits="2" />
																</div>  
																<div class="price-to">
																	<spring:theme code="product.volumePrices.to"/> <format:fromPrice priceData="${product.price}" />
																</div>
															 </c:when>  
										        			 <c:otherwise>  
																<div class="price-regular"><format:fromPrice priceData="${product.price}" /></div>
															</c:otherwise>
														</c:choose> 													
													</td>
												</tr>
												<tr>
													<td>
														<c:if test="${product.parcelUnitPrice gt 0}">
															<div class="big-price">
																<div class="price-parcel"> ${product.priceParcels}<spring:theme code="product.volumePrices.parcel"/></div> 
																<div class="price-parcel-price"><spring:theme code="product.currencynotation"/><fmt:formatNumber value="${product.parcelUnitPrice}" type="number" minFractionDigits="2" /> </div>
															</div>
														</c:if>
													</td>
												</tr>
												</table>
												</div>
											</div>
										 
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
								
									     
											<c:if test="${product.freeShipping}">
												<!-- freeShipping  -->
												<div class="product-attractions-free-shipping">
													<spring:theme code="product.attractions.freeshipping"/>
												</div>
											</c:if>
															
											<c:if test="${product.newProduct}">
												<!-- newProduct	 -->
												<div class="productattractions-new">
													<spring:theme code="product.attractions.new"/>
												</div>
											</c:if>
											
											
										</div>
										<div class="priceContainer">
											<format:fromPrice priceData="${product.price}"/>
										</div>
									</a>
								</li>
							</c:forEach>
						</ul>
					</c:when>
					<c:otherwise>
					<!-- not component.popup -->
						<ul class="carousel jcarousel-skin">
							<c:forEach items="${productData}" var="product">
								<c:url value="${product.url}" var="productUrl"/>
								<li>
									<a href="${productUrl}" class="scrollerProduct">
										<div class="thumb">
											<product:productPrimaryImage product="${product}" format="store"/>
											<c:if test="${product.oldPrice > product.price.value}">
										 		 <!-- Sale Enable -->
											     <div class="product-attractions-sale">
											     	<spring:theme code="product.attractions.sale" />
											     </div>
										    </c:if>
											<c:if test="${product.freeShipping}">
												<div class="product-attractions-free-shipping">
													<spring:theme code="product.attractions.freeshipping"/>
												</div>
											</c:if>
											<c:if test="${product.newProduct}">
												<div class="productattractions-new">
													<spring:theme code="product.attractions.new"/>
												</div>
											</c:if>
										</div>
										<!-- Name Price Primary Panel -->
										<div class="name-price-primary-carousel-panel">
											<div class="details">
													${product.name}
											</div>
											<c:choose>
												<c:when test="${product.oldPrice > product.price.value}">
													 <!-- Have OLD PRICE -->
						  							<div class="price-from-search">	
														<spring:theme code="product.volumePrices.from" /> 
														<spring:theme code="product.currencynotation"/>
														<fmt:formatNumber value="${product.oldPrice}" type="number" minFractionDigits="2" />
													</div>  
													<div class="price-to-search">
														<spring:theme code="product.volumePrices.to"/> <format:fromPrice priceData="${product.price}" />
													</div>
												</c:when>  
											    <c:otherwise>  
													<div class="price-regular-search"><format:fromPrice priceData="${product.price}" /></div>
												</c:otherwise>
											</c:choose> 		
											<!-- Parcels -->
											<div class="big-price">
												<div class="price-parcel-search"> 
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
												<div class="price-parcel-price-search"><spring:theme code="product.currencynotation"/>
													<fmt:formatNumber value="${product.parcelUnitPrice}" type="number" minFractionDigits="2" /> 
												</div>
											</div>
											<!-- /Parcels -->
										</div>
										<!-- /Name Price Primary Panel -->
									</a>
									<div class="detalhes-grupo">
										<div class="SuperSearchDesignColorsPriceBuy">
						     				<div class="SearchDesignColorsPriceBuy">
										     	<div class="searchFloatingColors">
											 		<ul>
												 		<c:forTokens items="${product.colors}" delims="," var="itemArray">
												 			<c:if test="${not (itemArray eq 'CORES')}">
													 			<li class="colorbox" style="background-color:${itemArray}; width:12px; height:15px;">
													 			</li>
												 			</c:if>
														</c:forTokens>
													</ul>
												</div>
						 						<c:choose>
													<c:when test="${product.oldPrice > product.price.value}">
														 <!-- Have OLD PRICE -->
							  							<div class="price-from-search">	
															<spring:theme code="product.volumePrices.from" /> 
															<spring:theme code="product.currencynotation"/>
															<fmt:formatNumber value="${product.oldPrice}" type="number" minFractionDigits="2" />
														</div>  
														<div class="price-to-search">
															<spring:theme code="product.volumePrices.to"/> <format:fromPrice priceData="${product.price}" />
														</div>
													</c:when>  
												    <c:otherwise>  
														<div class="price-regular-search"><format:fromPrice priceData="${product.price}" /></div>
													</c:otherwise>
												</c:choose> 		
								
												<!-- Parcels -->
												<div class="big-price">
													<div class="price-parcel-search"> 
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
													<div class="price-parcel-price-search"><spring:theme code="product.currencynotation"/>
														<fmt:formatNumber value="${product.parcelUnitPrice}" type="number" minFractionDigits="2" /> 
													</div>
												</div>
												<!-- /Parcels -->
							    	 			<product:productAddToCartPanelSearch product="${product}" allowAddToCart="${empty showAddToCart ? true : showAddToCart}" isMain="true" />
											</div>	
										</div>
									</div>
								</li>
							</c:forEach>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
		</c:when>
		<c:otherwise>
		<!-- productData empty -->
			<component:emptyComponent/>
		</c:otherwise>
	</c:choose>
</c:if>

<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<c:choose>
		<c:when test="${not empty productData}">
			<c:if test="${title != 'vazio'}">
				<div class="container product-section">
	          	<header>
	            	<h1>${title}</h1>
	            </header>
				</div>
			</c:if>
			<div id="collection-slider" class="product-infinite-wrapper no-before">
		     	<div class="product-slider showcase" data-slider-unique-id="collection-slider">
			   		<showcase:productsToShow 
			   			className="resumed-info" 
			   			products="${productData}" />
			    </div>
		    </div>
		</c:when>
		<c:otherwise>
		<!-- productData empty -->
			<component:emptyComponent/>
		</c:otherwise>
	</c:choose>		          
</c:if>