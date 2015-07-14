<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="details" tagdir="/WEB-INF/tags/desktop/showcaseproductdetail" %>
<%@ attribute name="className" required="false" type="java.lang.String" %>
<%@ attribute name="products" required="true" type="java.util.List" %>
<%@ taglib prefix="wishlist" tagdir="/WEB-INF/tags/desktop/wishlist" %>

<c:forEach items="${products}" var="product" varStatus="status">
	<c:url var="productUrl" value="${product.url}"></c:url>
	<div class="product ${className}" index="${status.index}">
		<div class="photo">
			<a href="${productUrl}" class="productMainLink">
				<details:image images="${product.previewImages}" format="store"/>
			</a>
		</div>
		<%--<div class="brinde_produto"><img alt="" src="http://placehold.it/72x72"></div>--%>
		<%--<div class="compare"><label><input type="checkbox">Comparar</label></div>--%>
		<div class="info">
			<c:choose>
				<c:when test="${entry.received > 0}"><span class="status-prod-wishlist texto-azul">Comprado</span></c:when>
				<c:when test="${false}"><span class="status-prod-wishlist texto-vermelho">Reservado</span></c:when>
				<c:otherwise><span class="status-prod-wishlist texto-vermelho">Não comprado</span></c:otherwise>
			</c:choose>
			<a class="excluir-prod-wishlist texto-verde" href="/w/${product.code}/remove">
				Excluir
				<!-- <img alt="" src="assets/images/ico-x-wishlist.jpg"> -->
			</a>
			<details:title productName="${product.name}" productUrl="${productUrl}"/>
			<details:stamps product="${product}"/>
			<details:singleColor product="${product}"/>
			<details:singleSize product="${product}"/>
			<details:prices product="${product}"/>
			<c:choose>
			<c:when test="${themeName == 'kids'}">
				<div class="btn-group">
					<wishlist:addToCart product="${product}"/>
					<details:viewmore productUrl="${productUrl}"/>
				</div>
			</c:when>
			<c:otherwise>
				<wishlist:addToCart product="${product}"/>
				<details:viewmore productUrl="${productUrl}"/>
			</c:otherwise>
			</c:choose>
	    </div>
	</div>
</c:forEach>
