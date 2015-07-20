<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="entry" tagdir="/WEB-INF/tags/desktop/wishlist" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>

<style>

/* public wishlist */

#publiclist-pw {
	width: 761px;
	height: 70px;
	float: left;
	border: 1px solid #eee;
	font-family: "Roboto";
	font-weight: 100;
	padding: 10px 0 0 15px;
	margin-bottom: 12px;
	line-height: 20px;
	font-family:"Roboto";
}

.birthday-pw {
	float:right;
	width: 170px;
	padding: 10px 0 0 0;
	border: 1px solid #eee;
	height: 70px;
	background: #eee url("/store/_ui/desktop/common/images/gift-w.png") no-repeat center;
	text-align: center;
	font-family:"Roboto";
}

</style>

<template:page pageTitle="${pageTitle}" >

	<div id="cartItems" style="width: 950px">

		<div id="publiclist-pw">
			<p><strong><spring:theme code="text.account.viewWishlistEntries.page.publicName" text="Public Name"/>:
				<ycommerce:testId code="orderHistory_orderNumber_link">
					${wishlist.publicName}
				</ycommerce:testId>
			</strong></p>
				
			<p><strong><spring:theme code="text.account.viewWishlistEntries.page.description" text="Description"/>:
				<ycommerce:testId code="orderHistory_orderStatus_label">
                    ${wishlist.description}
                </ycommerce:testId>
			</strong></p>
		</div>
		
		<div class="birthday-pw">${daysLeft}&nbsp<spring:theme code="text.account.viewWishlistEntries.page.daysLeft" text="days left to birthday"/></div>
		
		<div class="span-16">
            <nav:pagination top="false" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/w?sort=${searchPageData.pagination.sort}"  numberPagesShown="${numberPagesShown}"/>
        </div>
		
		<div id="wishlistItemsHeader"> 
			<table class="cart">
				<thead>
					<tr>
						<th colspan="2"><spring:theme code="text.fliegercommerce.texto71"/></th>
						<th><spring:theme code="text.account.viewWishlistEntries.page.itemPrice" text="Price"/></th> 
						<th><spring:theme code="text.fliegercommerce.texto72"/></th>
						<th><spring:theme code="text.fliegercommerce.texto73"/></th>
		                <th></th>
					</tr>
				</thead>
				<tbody>
                    <c:forEach items="${searchPageData.results}" var="entry" varStatus="status">
                        <tr class="cartItem">
                        	<entry:publicWishlistListerItem entry="${entry}" wishPK="${wishPK}"/>
                        </tr>
                    </c:forEach>
				</tbody>
			</table>
			
		</div>
	</div>
	<!-- end wishlistItensHeader -->
	
	<div class="span-16">
        <nav:pagination top="false" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/w?sort=${searchPageData.pagination.sort}"  numberPagesShown="${numberPagesShown}"/>
    </div>


</template:page>