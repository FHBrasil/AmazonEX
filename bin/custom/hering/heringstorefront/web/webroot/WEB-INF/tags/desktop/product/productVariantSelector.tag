<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>

<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="simplifiedSelectors" required="false" type="java.lang.Boolean" %>
<%-- ProductData.getSizes() contains all sizes --%>
<%@ attribute name="useSizesProperty" required="false" type="java.lang.Boolean" %>
<%@ attribute name="showVariantSizeGuide" required="false" type="java.lang.Boolean" %>
<%@ variable name-given="showAddToCart" variable-class="java.lang.Boolean" scope="AT_END" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="simplifiedSelectors" value="${(empty simplifiedSelectors) ? false : simplifiedSelectors}"/>
<c:set var="useSizesProperty" value="${(empty useSizesProperty) ? false : useSizesProperty}"/>
<c:set var="showVariantSizeGuide" value="${(empty showVariantSizeGuide) ? true : showVariantSizeGuide}"/>

<c:choose>
<c:when test="${simplifiedSelectors}">
	<div class="cores"> 
		<h3>Cores:</h3>
		<ycommerce:testId code="product_productSize">${product.colors}</ycommerce:testId> 
	</div> 
	<div class="tamanhos sizes-box">
		<h3><spring:theme code="product.size" /> </h3>
		<ul class="sizeVariantEventHandler"> 
			<c:set var="i" value="1"/>
			<c:forEach items="${product.sizes}" var="size">
				<li id="${product.code}-${i}" class="InStock-1" onclick="getSizeProductPerColor($(this))"><a>${size}</a></li>
				<c:set var="i" value="${i + 1}"/>								
			</c:forEach>
		</ul>    
	</div>
</c:when>
<c:otherwise>
<%-- Determine if product is one of Hering style or size variant --%>
		<c:if test="${product.variantType eq 'HeringStyleVariantProduct'}">
			<c:set var="variantStyles" value="${product.variantOptions}" />
		</c:if>
		<c:if test="${(not empty product.baseOptions[0].options) and (product.baseOptions[0].variantType eq 'HeringStyleVariantProduct')}">
			<c:set var="variantStyles" value="${product.baseOptions[0].options}" />
			<c:set var="variantSizes" value="${product.variantOptions}" />
			<c:set var="currentStyleUrl" value="${product.url}" />
		</c:if>
		<c:if test="${(not empty product.baseOptions[1].options) and (product.baseOptions[0].variantType eq 'HeringSizeVariantProduct')}">
			<c:set var="variantStyles" value="${product.baseOptions[1].options}" />
			<c:set var="variantSizes" value="${product.baseOptions[0].options}" />
			<c:set var="currentStyleUrl" value="${product.baseOptions[1].selected.url}" />
		</c:if>
		<c:url value="${currentStyleUrl}" var="currentStyledProductUrl"/>
		<%-- Determine if product is other variant --%>
		<c:if test="${empty variantStyles}">
			<c:if test="${not empty product.variantOptions}">
				<c:set var="variantOptions" value="${product.variantOptions}" />
			</c:if>
			<c:if test="${not empty product.baseOptions[0].options}">
				<c:set  var="variantOptions" value="${product.baseOptions[0].options}" />
			</c:if>
		</c:if>
	
	<!-- SELECT ITEM -->
	<c:set var="selectedSize" value="none" />
	<c:set var="selectedColor" value="none" />

		<c:forEach items="${product.baseOptions}" var="baseOptions">
			<c:forEach items="${baseOptions.selected.variantOptionQualifiers}" var="baseOptionQualifier">
				<c:if test="${baseOptionQualifier.qualifier eq 'style' and not empty baseOptionQualifier.image.url}">
					<c:set var="selectedColor" value="${baseOptionQualifier.value}"/>
				</c:if>
				<c:if test="${baseOptionQualifier.qualifier eq 'size'}">
					<c:set var="selectedSize" value="${baseOptionQualifier.value}"/>
				</c:if>
			</c:forEach>
		</c:forEach>
		<!-- /SELECT ITEM -->

		<c:if test="${not empty variantStyles or not empty variantSizes}">
			<c:choose>
				<c:when test="${product.purchasable and product.stock.stockLevelStatus.code ne 'outOfStock' }">
					<c:set var="showAddToCart"  value="${true}" />
				</c:when>
				<c:otherwise>
					<c:set var="showAddToCart" value="${false}" /> 
				</c:otherwise>
			</c:choose>
			
			<c:if test="${themeName == 'black'}">
					<!-- STYLES -->
				<div class="variant_options">
					<c:if test="${not empty variantStyles}">
						<div class="colour clearfix">
							<div><spring:theme code="product.variants.colour"/> ${currentStyleValue}</div>
							<ul class="colorlist">
								<c:forEach items="${variantStyles}" var="variantStyle">
									<c:forEach items="${variantStyle.variantOptionQualifiers}" var="variantOptionQualifier">
										<c:if test="${variantOptionQualifier.qualifier eq 'style'}">
											<c:set var="styleValue" value="${variantOptionQualifier.value}" />
											<c:set var="imageData" value="${variantOptionQualifier.image}" />
										</c:if>
									</c:forEach>
									<li class="<c:if test="${variantStyle.url eq currentStyleUrl}">selected</c:if>Cor">
										<c:url value="${variantStyle.url}" var="productStyleUrl"/>
										<a href="${productStyleUrl}" class="<c:if test="${selectedColor eq  styleValue}">SELECTED-</c:if>colorVariant" id="${variantStyle.url}">
											<c:if test="${not empty imageData}">
												<img src="${imageData.url}" alt="${styleValue}"/>
											</c:if>
											<c:if test="${empty imageData}">
												<span class="swatch_colour_a"></span>
											</c:if>
										</a>
										<c:if test="${variantStyle.url eq currentStyleUrl}">
										</c:if>
										<span class="corName">${styleValue}</span>
									</li>
								</c:forEach>
							</ul>
						</div>
					</c:if>
					<!-- / STYLES -->
					
					<!-- SIZES -->
					<c:if test="${not empty variantSizes}">
						<label class="select-size"><spring:theme code="product.variants.size"/></label>
						<div class="size clearfix">
							<form>
							
								<c:if test="${empty variantSizes}">
									<spring:theme code="product.variants.select.style"/>
								</c:if>
									<c:if test="${not empty variantSizes}"> 
										<input type="radio" name="select-size-radiogroup" value="${currentStyledProductUrl}" <c:if test="${empty variantParams['size']}">checked</c:if>>
									<br>
													<c:forEach items="${variantSizes}" var="variantSize">
														<c:set var="optionsString" value="" />
														<c:set var="sizesString" value="" />
													<c:forEach items="${variantSize.variantOptionQualifiers}" var="variantOptionQualifier">
														<c:if test="${variantOptionQualifier.qualifier eq 'size'}">
															<c:set var="optionsString">${optionsString}&nbsp;${variantOptionQualifier.name}&nbsp;${variantOptionQualifier.value}, </c:set>
															<c:set var="sizesString">${variantOptionQualifier.value}</c:set>
														</c:if>
													</c:forEach>
													<br><br>
													<c:if test="${(variantSize.stock.stockLevel gt 0) and (variantSize.stock.stockLevelStatus ne 'outOfStock')}">
														<c:set var="stockLevel">
														${variantSize.stock.stockLevel}&nbsp;<spring:theme code="product.variants.in.stock"/>
														</c:set>
													</c:if>
													<c:if test="${(variantSize.stock.stockLevel le 0) and (variantSize.stock.stockLevelStatus eq 'inStock')}">
														<c:set var="stockLevel"><spring:theme code="product.variants.available"/></c:set>
													</c:if>
													<c:if test="${(variantSize.stock.stockLevel le 0) and (variantSize.stock.stockLevelStatus ne 'inStock')}">
														<c:set var="stockLevel"><spring:theme code="product.variants.out.of.stock"/></c:set>
													</c:if>
	
													<c:if test="${(variantSize.url eq product.url)}">
														<c:set var="showAddToCart" value="${true}" />
													</c:if>
	
													<c:url value="${variantSize.url}" var="variantOptionUrl"/>
												 <c:choose>
												 <c:when test="${variantSize.stock.stockLevel  gt 0}"> <!-- IN STOCK -->
												 			
														<label  class="<c:if test="${selectedSize eq  sizesString}">SELECTED-</c:if>InStock-class" >
															<a href="${variantOptionUrl}" id="size-radio-${sizesString}-class">
																${sizesString}
															</a>
														</label>
													
													</c:when>
													<c:otherwise> <!-- Does not Have Stock -->
													
														<label   class="<c:if test="${selectedSize eq  sizesString}">SELECTED-</c:if>OutOfStock-class" >
															<a href="${variantOptionUrl}" id="size-radio-${sizesString}-class">
																${sizesString} 
															</a>
														</label>
													
													</c:otherwise> 
												</c:choose>
												<c:if test="${selectedSize eq  sizesString}">
													<div class="chaordic-size">
														<input type="hidden" class="chaordic-size-selected" value="${sizesString}"/>
													</div>
												</c:if>
												</c:forEach>
											</c:if>
								</form>
							<c:if test="${showVariantSizeGuide}">
								<product:productMeasurements product="${product}" />
							</c:if>
						</div>
					</c:if>
					<!-- / SIZES -->
				</div>
			</c:if>
			<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
				<input type="hidden" id="hdStockLevel" value="${product.stock.stockLevel} " />
				<c:if test="${not empty variantStyles}">
					<div class="cores ${product.stock.stockLevel <= 0 ? 'semEstoque' : ''}">
						<h3><spring:theme code="product.variants.colour"/>: ${currentStyleValue}</h3>
						<ul class="bxslider-cores">
							<c:forEach items="${variantStyles}" var="variantStyle" varStatus="status">
								<c:forEach items="${variantStyle.variantOptionQualifiers}" var="variantOptionQualifier">
									<c:if test="${variantOptionQualifier.qualifier eq 'style'}">
										<c:set var="styleValue" value="${variantOptionQualifier.value}" />
										<c:set var="imageData" value="${variantOptionQualifier.image}" />
									</c:if>
								</c:forEach>
								<c:url value="${variantStyle.url}" var="productStyleUrl"/>
								<li data-variantcode="${variantStyle.code}" color="${variantStyle.color.RGB}" class="${styleValue} variantSelectorHandler<c:if test="${variantStyle.url eq currentStyleUrl}"> active</c:if>" style="background-color:${variantStyle.color.RGB};">
									<input type="hidden" name="codeData" value="${variantStyle.code}-${variantStyle.code.substring(0,4)}${variantStyle.color.RGB}">
									<a href="${productStyleUrl}" <c:if test="${selectedColor eq styleValue}">class="active"</c:if>></a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</c:if>
				<c:set var="classSemEstoque" value="${product.stock.stockLevel <= 0 ? 'semEstoque semEstoqueSizes' : ''}" />
				<c:set var="classDiv70SemEstoque" value="${fn:length(product.sizes) >= 2 ? 'classDiv70SemEstoque' : ''} " />
				<c:set var="classSemEstoque" value="${classSemEstoque} ${classDiv70SemEstoque }" />
				<div class="tamanhos sizes-box ${classSemEstoque}" >
				<c:choose>
				<c:when test="${useSizesProperty}">
					<ul> 
						<c:forEach items="${product.sizes}" var="size" varStatus="status">
							<c:set var="sizesString" value="${size}" />
							<li data-variantcode="${variantSize.code}" class="variantSelectorHandler"><a href="${variantOptionUrl}" <c:if test="${selectedSize eq  sizesString}">class="active"</c:if>>${sizesString}</a></li>
						</c:forEach>
					</ul>
				</c:when>
				<c:otherwise>
					<h3><spring:theme code="product.variants.size"/>:</h3>
					<c:if test="${empty variantSizes}">
						<spring:theme code="product.variants.select.style"/>
					</c:if>
					<c:if test="${not empty variantSizes}">
						<ul> 
							<c:forEach items="${variantSizes}" var="variantSize">
								<c:set var="optionsString" value="" />
								<c:set var="sizesString" value="" />
								<c:forEach items="${variantSize.variantOptionQualifiers}" var="variantOptionQualifier">
									<c:if test="${variantOptionQualifier.qualifier eq 'size'}">
										<c:set var="optionsString">${optionsString}&nbsp;${variantOptionQualifier.name}&nbsp;${variantOptionQualifier.value}, </c:set>
										<c:set var="sizesString">${variantOptionQualifier.value}</c:set>
									</c:if>
								</c:forEach>
								<c:if test="${(variantSize.stock.stockLevel gt 0) and (variantSize.stock.stockLevelStatus ne 'outOfStock')}">
									<c:set var="stockLevel">${variantSize.stock.stockLevel}&nbsp;<spring:theme code="product.variants.in.stock"/></c:set>
								</c:if>
								<c:if test="${(variantSize.stock.stockLevel le 0) and (variantSize.stock.stockLevelStatus eq 'inStock')}">
									<c:set var="stockLevel"><spring:theme code="product.variants.available"/></c:set>
								</c:if>
								<c:if test="${(variantSize.stock.stockLevel le 0) and (variantSize.stock.stockLevelStatus ne 'inStock')}">
									<c:set var="stockLevel"><spring:theme code="product.variants.out.of.stock"/></c:set>
								</c:if>
								<c:if test="${(variantSize.url eq product.url)}">
									<c:set var="showAddToCart" value="${true}" />
								</c:if>
								<c:url value="${variantSize.url}" var="variantOptionUrl"/>
								<c:choose>
									<c:when test="${variantSize.stock.stockLevel gt 0}">
										<li data-variantcode="${variantSize.code}" class="variantSelectorHandler"><a href="${variantOptionUrl}" <c:if test="${selectedSize eq  sizesString}">class="active"</c:if>>${sizesString}</a></li>
									</c:when>
									<c:otherwise> <!-- Does not Have Stock -->
										<li data-variantcode="${variantSize.code}" class="variantSelectorHandler"><a href="${variantOptionUrl}" class="out-of-stock">${sizesString}</a></li>
									</c:otherwise> 
								</c:choose>
							</c:forEach>
						</ul>
						<c:if test="${selectedSize eq  sizesString}">
								<div class="chaordic-size">
									<input type="hidden" class="chaordic-size-selected" value="${sizesString}"/>
								</div>
							</c:if>
						<c:if test="${showVariantSizeGuide and not empty product.measurements}">
							<a class="fancybox tabela-medidas ${product.stock.stockLevel <= 0 ? 'aSemEstoque' : ''}" href="#modal-tabela-medidas">
								<spring:theme code="product.variants.size.guide"/>
							</a>
							<product:productMeasurements product="${product}" />
						</c:if>
					</c:if>
				</c:otherwise>
				</c:choose>
				</div>
			</c:if>	
		</c:if>
</c:otherwise>
</c:choose>