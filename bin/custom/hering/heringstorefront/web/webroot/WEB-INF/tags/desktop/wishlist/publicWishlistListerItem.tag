<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="entry" required="true" type="br.hering.facades.wishlist.data.HeringWishlistEntryData" %>
<%@ attribute name="wishPK" required="true" type="java.lang.String" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>

<style>

/* product table */

.thumb-w {border-bottom:0;padding-right:10px;width:90px;}
/*.product-w {border-bottom:0;padding-right:10px;width:280px;}
	.itemName-w a{color:#636363}
	
.price-w {border-bottom:0;width:100px;}
.desired-w {border-bottom:0;width:120px;}
.bought-w {text-align:center;border-bottom:0;width:80px;}*/
.button-w {clear: both;float: right;}

/* public wishlist */

.product-pw {border-bottom:0;padding-right:10px;width:340px;}
	.itemName-pw a h2{color:#636363}
.price-pw {border-bottom:0;width:100px;}
.desired-pw {text-align:center;border-bottom:0;width:100px;}
.bought-pw {text-align:center;border-bottom:0;width:100px;}

/*.button-pw {clear: both;float: right;}*/

.comment-pw {width: 180px;}


</style>

<spring:theme code="text.addToCart" var="addToCartText"/>
<c:url value="${entry.product.url}" var="productUrl"/>

<c:set value="${not empty entry.product.potentialPromotions}" var="hasPromotion"/>

<div id=wishlistItems class="prod_list ${hasPromotion ? 'prod_list_has_promotion' : ''}">
	<ycommerce:testId code="test_searchPage_wholeProduct">
			<td class="thumb-w">
				<c:if test="${not empty entry.product.averageRating}">
					<span class="stars large" style="display: inherit;">
						<span style="width: <fmt:formatNumber maxFractionDigits="0" value="${entry.product.averageRating * 24}" />px;"></span>
					</span>
				</c:if>
				<a href="${productUrl}" title="${entry.product.name}" class="productMainLink">
					<product:productPrimaryImage product="${entry.product}" format="thumbnail"/>
				</a>	
				<c:if test="${not empty entry.product.potentialPromotions and not empty entry.product.potentialPromotions[0].productBanner}">
					<img class="promo" src="${entry.product.potentialPromotions[0].productBanner.url}" alt="${entry.product.potentialPromotions[0].description}" title="${entry.product.potentialPromotions[0].description}"/>
				</c:if>
			</td>
			
				<div class="details">
					
					<ycommerce:testId code="searchPage_productName_link_${entry.product.code}">
						<td class="product-pw">
							<div class="itemName-pw">
								<a href="${productUrl}" title="${entry.product.name}" class="productMainLink">
									<h2>${entry.product.name}</h2>
								</a>
							</div>
						</td>
						<td class="price-pw">
							<ycommerce:testId code="searchPage_price_label_${entry.product.code}">
								<p><format:price priceData="${entry.product.price}"/></p>
							</ycommerce:testId>
	                        
                        </td>
                        <td class="desired-pw">
	                        <p>${entry.desired}</p>
                        </td>
                        <td class="bought-pw">
	                        <p>${entry.received}</p>
                        </td>
					</ycommerce:testId>
					<c:if test="${not empty entry.product.summary}">
						<p>${entry.product.summary}</p>
					</c:if>			
					<product:productListerClassifications product="${entry.product}"/>
				</div>
			
		
		<!-- <div class="cart"> -->
		
		<td class="button-w">
			<ycommerce:testId code="searchPage_addToCart_button_${entry.product.code}">
					<c:set var="buttonType">submit</c:set>
					<c:choose>
						<c:when test="${entry.product.stock.stockLevelStatus.code eq 'outOfStock' }">
							<c:set var="buttonType">button</c:set>
							<spring:theme code="text.addToCart.outOfStock" var="addToCartText"/>
						</c:when>
						<c:when test="${entry.product.stock.stockLevelStatus.code eq 'lowStock' }">
							<span class='listProductLowStock mlist-stock'><spring:theme code="product.variants.only.left" arguments="${entry.product.stock.stockLevel}"/></span>
						</c:when>
					</c:choose>
					<form id="addToCartForm${entry.product.code}" action="<c:url value="/cart/add"/>" method="post" class="add_to_cart_form">
						<input type="hidden" name="productCodePost" value="${entry.product.code}"/>
	                    <input type="hidden" name="wishlistPKPost" value="${wishPK}"/>
						<button type="${buttonType}" class="positive <c:if test="${fn:contains(buttonType, 'button')}">out-of-stock</c:if>" <c:if test="${fn:contains(buttonType, 'button')}">disabled aria-disabled="true"</c:if>>
							<span class="icon-cart"></span>${addToCartText}
						</button>				
					</form>
				</td>
				
			</ycommerce:testId>
		</td>
	</ycommerce:testId>	
</div>
