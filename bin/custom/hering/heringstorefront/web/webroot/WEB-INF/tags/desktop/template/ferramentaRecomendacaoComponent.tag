<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/desktop/order"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${pageType == 'HOMEPAGE'}">
	<div class="homePageData"></div>
</c:if>

<div class="userDataInfo">
	<input type="hidden" class="userCode" value="${user.uid}"/>
	<input type="hidden" class="userId" value="${user.primaryKey}"/>
	<input type="hidden" class="userFistName" value="${user.firstName}" />
	<input type="hidden" class="userLastName" value="${user.lastName}" />	


</div>



<c:if test="${pageType == 'PRODUCTSEARCH'}">
	
	<div class="searchDataInfo">
		<input type="hidden" class="userCode" value="${searchPageData.freeTextSearch}" />
		<input type="hidden" class="textSearch" value="${searchPageData.freeTextSearch}" />
		<input type="hidden" class="categoryGender" value="${categoryGender}" />
	</div>
	<c:forEach items="${searchPageData.results}" var="product" varStatus="status">
		<div class="searchProductInfoDetails">
			<input type="hidden" class="codeProduct" value="${product.code}"/>
			<input type="hidden" class="priceProduct" value="${product.price.value}"/>
		</div>	
	</c:forEach>	

</c:if>



<c:if test="${pageType == 'CATEGORY'}">

	<div class="categoryDataInfo">
		<input type="hidden" class="userCode" value="${searchPageData.freeTextSearch}" />
		<input type="hidden" class="textSearch" value="${searchPageData.freeTextSearch}" />
		<input type="hidden" class="category" value="${categoryName}" />
	   <input type="hidden" class="categoryGender" value="${categoryGender}" />
		

		<c:if test="${not empty pageData.breadcrumbs}">
			<c:forEach items="${pageData.breadcrumbs}" var="breadcrumb"
				varStatus="status">
				<c:if test="${status.first}">
					<input type="hidden" value="${breadcrumb.facetValueName}"
						class="chaordicSearchCategoryFim" />
				</c:if>
			</c:forEach>
		</c:if>
	</div>

	<c:forEach items="${searchPageData.results}" var="product" varStatus="status">
		<div class="searchProductInfoDetails">
			<input type="hidden" class="codeProduct" value="${product.code}"/>
			<input type="hidden" class="priceProduct" value="${product.price.value}"/>
		</div>	
	</c:forEach>
	
</c:if>

<c:if test="${pageType == 'ORDERCONFIRMATION'}">

	<c:forEach items="${orderData.entries}" var="entry">		
		<div class="orderConfirmationInfoProductDetailData">
			<input type="hidden" class="productPrice"	value="${entry.basePrice.value}" /> 
			<input type="hidden"	class="productQuantity" value="${entry.quantity }" /> 
			<input type="hidden" class="productSku" value="${entry.product.code}" />
		</div>
	</c:forEach>

	<div class="orderConfirmationDataInfo">
		<input type="hidden" class="orderID" value="${orderCode}" /> <input
			type="hidden" class="orderTotalPrice"
			value="${orderData.totalPrice.value}" />
	</div>
	
</c:if>


<c:if test="${pageType == 'CART'}">

		<c:forEach items="${cartData.entries}" var="entry">
			<div class="cartDataItems">
				<input type="hidden" class="productPrice" value="${entry.basePrice.value}" />
				<input type="hidden" class="productSku" value="${entry.product.code}" />
				<input type="hidden" class="productQuantity" value="${entry.quantity}" />

			<c:forEach items="${entry.product.categories}" var="cat">
				<c:choose>
					<c:when test="${categories == ''}">
						<c:set var="categories" value="${cat.name}" />
					</c:when>
					<c:when test="${categories != ''}">
						<c:set var="categories" value="${categories}, ${cat.name}" />
					</c:when>
				</c:choose>
			</c:forEach>
			<input type="hidden" class="productCategories" value="${categories}" />	

		</div>
			
		</c:forEach>
		
		<input type="hidden" class="cartCodeInfo" value="${cartData.code}" />
		
</c:if>

	
<c:if test="${pageType == 'PRODUCT'}">

	<div class="productDataInfo">
		<input type="hidden" class="productCode" value="${product.code}"/>
		<input type="hidden" class="productName" value="${product.name}"/>
		<input type="hidden" class="productDescription" value="${product.description}"/>
		<input type="hidden" class="productValue" value="${product.price.value}"/>
		<input type="hidden" class="productOldPrice" value="${product.oldPrice}"/>
		<input type="hidden" class="productAvailable" value="${product.availableForPickup}"/>
		
		<c:forEach items="${galleryImages}" var="container" varStatus="status">
			<input type="hidden" class="productImg" value="${container.thumbnail.url}"/>			
			<input type="hidden" class="productContainerUrl" value="${container.product.url}"/>			
		</c:forEach>
		
		<input type="hidden" class="cartCodeInfo" value="${cartData.code}" />		
		
		<%-- <input type="hidden" class="productColorsRGB" value="${product.color.RGB}"/>
		<input type="hidden" class="productColorsFullDescription" value="${product.color.fullDescription}"/> --%>		
		
		<%-- <c:forEach items="${product.sizes}" var="size">			
			<input type="hidden" class="productSize" value="${size}"/>
		</c:forEach>
		
		
		<input type="hidden" class="quantidadeAdicionada" value="${quantity}"/>
		<c:forEach items="${cartData.entries}" var="entry">			
			<input type="hidden" class="productCodeProduct" value="${cartData.code}"/>
			<input type="hidden" class="productQuantityProduct" value="${entry.quantity}" />
			
		
		</c:forEach> --%>
		
		<!-- Frete Gratis -->
		<input type="hidden" class="productFreteGratis" value="${product.freeShipping}"/>
		<!-- Lancamento -->
		<input type="hidden" class="productLancamento" value="${product.newProduct}"/>
		<!-- Promocao -->
		<input type="hidden" class="productPromocao" value="${product.oldPrice > product.price.value}"/>
		
		
		
		<c:forEach items="${product.categories}" var="cat">
			<c:choose>
				<c:when test="${categories == ''}">
					<c:set var="categories" value="${cat.name}" />
				</c:when>
				<c:when test="${categories != ''}">
					<c:set var="categories" value="${categories}, ${cat.name}" />
				</c:when>
			</c:choose>
		</c:forEach>
		
		<input type="hidden" class="productCategories" value="${categories}" />
	   <input type="hidden" class="productGender" value="${product.genders[0].name}" />
	</div>
	
	<div class="priceParcelInfo">
		<input type="hidden" class="numberParcelInfo" value="${product.priceParcels}" />
		<input type="hidden" class="priceParcelInfo" value="${product.parcelUnitPrice}" />
	</div>
	
		
</c:if>

<c:if test="${pageType == 'CHECKOUT' || pageType == 'SINGLESTEPCHECKOUT'}">
	<div class="checkoutPage"></div>	
</c:if>


