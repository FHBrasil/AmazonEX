<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="imagens" required="true" type="java.util.List" %>


<div class="product-main-slider">
		
	<c:set value="${fn:length(imagens)}" var="endFor"/>
	<c:set value="0" var="count" />
		
	<ul class="bxslider" style="width: 615%; position: relative; transition-duration: 0s; ">
		<%-- <c:forEach items="${imagens}" var="item" varStatus="count">
			<li class="template-${count.index}">
				<img src="${item.superZoom.url}">
			</li>
		</c:forEach> --%>
		<c:forEach var="i" begin="0" end="${endFor-1}">
		
			<c:choose>
				<c:when test="${count == 1 || count == 2}">
					<c:set var="templateNumber" value="${count +1}"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="templateNumber" value="${count}"></c:set>
				</c:otherwise>
			</c:choose>
		
			<li class="template-${templateNumber}" style="float: left; list-style: outside none none; position: relative;">
				<c:if test="${count ne 0}">
					<div class="mini-zoom-wrapper active loaded" style="left: auto; right: 0px; display: none;">
						<c:set var="imgURL" value="${imagens[i].product.url}" />
						<img src="${imgURL}" />
						<i class="fa fa-spinner fa-spin"></i>
						<div></div>
					</div>
				</c:if>
				
				<c:set var="imgURL" value="${imagens[i].product.url}" />
<%-- 				SE O COUNT IGUAL A ZERO TEREMOS APENAS UMA FOTO NO COMPONENTE.
					COMO A FOTO PODE VIR COM -B, ENTAO E VERIFICADO ISSO.
					O -B E DE BACKGROUND.--%>
				<c:if test="${count eq 0}">
					<c:set var="imgURL" value="${imagens[i].zoom.url}" />
					<c:set var="urlContemB" value="${fn:contains(imagens[i].zoom.url, 'B.jpg')}" />
					<c:if test="${!urlContemB}">
						<c:set var="imgURL" value="${fn:replace(imagens[i].zoom.url, '.jpg', '-B.jpg')}" />
					</c:if>
				</c:if>
				<%--
					 CASO TENHA APAENAS UMA FOTO, ELA SERA O ZOOM COM O width ABAIXO.
				 --%>
				<a  
					data-zoom-image-source="${imagens[i].superZoom.url}" class="featured ${count eq 0 ? 'mx-big-width' : ''}" href="#">
					<img style="${count eq 0 ? 'width: 100%;' : ''}"
						src="${imgURL}" alt="${fn:escapeXml(imagens[i].product.altText)}">
				</a>
				<!--
					 NA SEQUENCIA TEREMOS 2 OU 3 IMAGENS NO SLIDES...
					 QNDO FOR 2 FOTOS, A SEGUNDA MOSTRA ANTERIOR.<
					 QNDO FOR 3 FOTOS, A 1 MOSTRA A CORRENTE, 2 A ANTERIOR 3 A SUBANTERIOR( I-1, E I-2).
				 -->
				<c:if test="${count ne 0}">
					<c:set value="${ i - 1 >= 0 ? i - 1 : 0}" var="index"/>
					<c:set var="imgURL" value="${imagens[index].product.url}" />
					<c:if test="${count eq 2}">
						<c:set var="imgURL" value="${imagens[index].smallProduct.url}" />
						<c:set var="urlContemB" value="${fn:contains(imagens[index].smallProduct.url, 'B.jpg')}" />
						<c:if test="${!urlContemB}">
							<c:set var="imgURL" value="${fn:replace(imagens[index].smallProduct.url, '.jpg', '-B.jpg')}" />
						</c:if>
					</c:if>
						
					<a class="imgClassDireita" data-zoom-image-source="${imagens[index].superZoom.url}" href="#">
						<img class="${count eq 2 ? 'mx-big-height' : ''}" src="${imgURL}" alt="${fn:escapeXml(imagens[index].product.altText)}">
					</a>
				</c:if>
				<c:if test="${count eq 2}">
					<c:set value="${ i - 2 >= 0 ? i - 2 : 1}" var="index"/>
					<c:set var="imgURL" value="${imagens[index].smallProduct.url}" />
					<c:set var="urlContemB" value="${fn:contains(imagens[index].smallProduct.url, 'B.jpg')}" />
					<c:if test="${!urlContemB}">
						<c:set var="imgURL" value="${fn:replace(imagens[index].smallProduct.url, '.jpg', '-B.jpg')}" />
					</c:if>
					
					<a class="imgClassDireita" data-zoom-image-source="${imagens[index].superZoom.url}" href="#">
						<img class="mx-big-right-img" src="${imgURL}" alt="${fn:escapeXml(imagens[index].product.altText)}" >
					</a>
				</c:if>
			</li>
			<c:set value="${count eq 2 ? 0 : count + 1}" var="count" />
		</c:forEach>
	</ul>
</div>

<script type="text/javascript">
$("div.product-main-slider").bxSlider({
    controls: false,
    pager: true,
    auto: false,
    autoHover: true
});
</script>