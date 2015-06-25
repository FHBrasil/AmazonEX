<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="galleryImages" required="true" type="java.util.List" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>

<spring:theme code="text.addToCart" var="addToCartText"/>
<div class="margin-top">
	<div class="col-xs-12 col-sm-6 v-bottom">
		<div id="mainCarousel" class="carousel slide" data-ride="carousel" data-interval="false">
			<product:productBackgroundImage product="${product}" galleryImages="${galleryImages}"/>  
		</div> 
	</div>
	<div class="col-sm-6 v-bottom margin-top" style="float: right;">
			<div class="col-sm-12 text-right hidden-xs"><a href="#">[brand img]</a></div>
			<ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
				<header>
					<h1><b>[BRAND]</b>${product.name}</h1>
					<%--<span><spring:theme code="product.code"/>&nbsp;${product.code}</span>--%>
				</header>
			</ycommerce:testId>
		<%-- [reviews] --%>
		<p><span class="glyphicon stars">&#57350;&#57350;&#57350;&#57350;&#57350;
		<span style="width:90%">&#57350;&#57350;&#57350;&#57350;&#57350;</span></span> 4.8 (17) <a href="#">Bewertung schreiben</a></p>
		
		<p>Small product description. <a href="#tab150219">weiterlesen</a></p>

		<div class="row">
			<div class="col-xs-4 v-bottom">
				<ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
					<product:productPricePanel product="${product}"/>
				</ycommerce:testId>
			</div>
			<div class="col-xs-7 v-bottom">
				<%-- [shipping information] --%>
				<p><b>Am Freitag geliefert!</b><span class="glyphicon glyphicon-stop text-onstock"></span><br />
				<small>Bestellen Sie innerhalb 5:17 Stunden.<br />Kostenlose Lieferung ab 40 &euro;</small></p>
			</div>
		</div>

		<div class="row shadowbox">
			<cms:pageSlot position="AddToCart" var="component" element="div" class="span-8">
				<cms:component component="${component}"/>
			</cms:pageSlot>
		</div>
			
			<c:if test="${product.purchasable && product.stock.stockLevelStatus.code != 'outOfStock'}">
			
			
			
				<%--<product:productReviewSummary product="${product}"/>--%>
		
				<c:if test="${not empty product.summary}">
					<div class="summary">
						${product.summary}
					</div>
				</c:if>
		
				<product:productPromotionSection product="${product}"/>
		
				<c:if test="${not empty product.color}">
					<div class="product-color-RGB">
				<!--  	Cor RGB: ${product.color.RGB}-->
					</div>
				</c:if>
			</c:if>
			
			<%--<cms:pageSlot position="VariantSelector" var="component" element="div">
				<cms:component component="${component}"/>
			</cms:pageSlot>--%>
			
			<%--<product:productDetailsFooter product="${product}" />--%>
	</div>
	<%--<cms:pageSlot position="Section2" var="feature" element="div" class="span-8 section2 cms_disp-img_slot last">
		<cms:component component="${feature}"/>
	</cms:pageSlot>--%>
	<div class="clearfix"></div>
</div>
<div class="margin-top margin-bottom">
		<%-- [thumbs here] --%>
		<product:productImagePanel product="${product}" galleryImages="${galleryImages}"/>	
	<div class="col-sm-6 visible-xs visible-sm"></div>
	<div class="col-sm-6">
		<div class="row">
			<p class="col-xs-4">
				<a href="#"><b class="fox24gif150217">+1496 Punkte</b><small class="hidden-xs hidden-sm"><br />Sammeln und beim n&auml;chsten Einkauf sparen!</small></a>
			</p>
			<p class="col-xs-8">
				<a href="#"><b>Ratenzahlung ab 12,90 &euro;</b><small class="hidden-xs hidden-sm"><br />mit unserem Partner Klarna<br />Mehr zum Thema Ratenkauf</small></a>
			</p>
		</div>
	</div>
</div>	







