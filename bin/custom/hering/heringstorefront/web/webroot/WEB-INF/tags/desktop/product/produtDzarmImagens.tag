<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="imagens" required="true" type="java.util.List" %>

<div id="product-main-img">
	<div class="container"><i class="click-to-zoom-out"></i></div>
</div>


<div class="bx-controls bx-has-pager bx-has-pager-vertical" style="margin: 20% 22px;">
	<div class="loader"></div>
	<div class="bx-pager bx-default-pager">
		<c:forEach items="${imagens}" var="img" varStatus="i">
			<div class="bx-pager-item">
				<a class="bx-pager-link ${i.index eq 0 ? 'active' : ''}" data-slide-index="${i.index}" href="javascript:void(0)">${i.index}</a>
			</div>
		</c:forEach>
	</div>
</div>



<div class="product-images">

	<ul>
		<c:forEach items="${imagens}" var="img" varStatus="i">
		<%-- ${img.product.url} --%>
		
			<c:set var="productZoomImagesUrl" value="${img.zoom.url}"/>
			<li class="photos" >
				<a name="item${i.index}"
				style="background-image: url(${img.superZoom.url});"
					data-image-source="${img.superZoom.url}">
					
				<i class="lupa fa fa-search"></i>
					
				<img id="product-item-img${i.index}" src="${img.superZoom.url}"
						data-zoom-image-source="${img.superZoom.url}"
							 style="width: 100%;"/>
				</a>
			</li>	
		</c:forEach>
	</ul>
	<cms:pageSlot position="Section2" var="feature">
		<cms:component component="${feature}" />
	</cms:pageSlot>
	<!-- RETIRADO POR HORA, NÂO TERA VIDEO.  -->
	<!-- <div class="photos" style="width: 1263px;">
		<a name="item3"
				href="javascript:void(0)"> 
				O VIDEO ESTA HARDCODE ATE O MOMENTO QUE For DEFINIDO. 
				<div class="responsive-video">
					<iframe width="610" height="365" frameborder="0" allowfullscreen=""
						src="http://www.youtube.com/embed/DLXwlZF3kUI?wmode=transparent"></iframe>
				</div>
		</a>
	</div> -->
</div>
