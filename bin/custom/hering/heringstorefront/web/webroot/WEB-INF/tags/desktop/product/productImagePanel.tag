<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="galleryImages" required="true" type="java.util.List" %>

<c:if test="${not empty galleryImages}">
	<ol class="hidden-xs hidden-sm col-sm-6 carouselNav150219">
		<c:forEach items="${galleryImages}" var="container" varStatus="status">
			<li class="col-xs-2" data-target="#mainCarousel" data-slide-to="${status.index}">				
				<img src="${container.thumbnail.url}" alt="${container.thumbnail.altText}" width="100%"/>
			</li>
		</c:forEach>
	</ol>
</c:if>