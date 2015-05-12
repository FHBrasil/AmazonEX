<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="breadcrumb"
	tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement"
	tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="wishUrl" tagdir="/WEB-INF/tags/desktop/wishlist"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>


<template:page pageTitle="${pageTitle}">
	<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
	</div>
	<div id="globalMessages">
		<common:globalMessages />
	</div>
	<nav:accountNav selected="profile" />
	<div class="column accountContentPane clearfix" style="float: left">
		<div class="headline">
			<spring:theme code="text.account.wishlist" text="My Wishlist" />
		</div>
		<div id="how-wish">
			<ul>
				<li class="add-w">1. Adicione produtos</li>
				<li class="share-w">2. Compartilhe com <br>seus amigos
				</li>
				<li class="gift-w">3. Ganhe presentes!</li>
			</ul>
		</div>
		<form:form action="update-wishlist" method="post" commandName="updateWishlistForm">
			<div id="list-form">
				<c:url value="/w/${urlPublicWishlist}" var="publicWishlistUrl" />
				<div class="b-share">
					<a target="_blank" href="http://www.facebook.com/share.php?u=http://www.dzarm.com.br${publicWishlistUrl}"
						title="Compartilharo a lista do evento no Facebook">Compartilhar</a>
				</div>
				<div id="form-w">
					<div class="control-group">
						<formElement:formInputBox idKey="name" labelKey="Nome" path="name" inputCSS="text" mandatory="true"/>
					</div>
					<div class="control-group">
						<formElement:formTextArea idKey="description" labelKey="Mensagem" path="description" areaCSS="textarea" mandatory="true"/>
					</div>
					
					<div class="control-group">
						<formElement:formInputBox idKey="publicName" labelKey="Nome do Evento" path="publicName" inputCSS="text" mandatory="true"/>
					</div>
					<div class="control-group">
						<formElement:formInputDateBox idKey="birthday" labelKey="Data final (dd/mm/aaaa)" path="viewBirthday" inputCSS="text" mandatory="true"/>
					</div>
				</div>
			</div>
			<span style="float:right">
                  <button type="button" class="form" onclick="window.location = '${profileUrl}'"><spring:theme code="text.account.profile.cancel" text="Cancel"/></button>
                   <ycommerce:testId code="profilePage_SaveUpdatesButton">
                       <button style="margin-right:0" class="form" type="submit"><spring:theme code="text.account.profile.saveUpdates" text="Save Updates"/></button>
                   </ycommerce:testId>
              </span>
       </form:form>
		<div id="cartItems" style="clear: both;">
				<c:if test="${not empty searchPageData.results}">
					<table id="order_history" class="cart">
						<thead>
							<tr>
								<th id="header1" colspan="2"><spring:theme
										code="text.account.viewWishlistEntries.page.productName"
										text="Product Name" /></th>
								<th id="header1"><spring:theme
										code="text.account.viewWishlistEntries.page.itemPrice"
										text="Item Price" /></th>
								<th id="header1"><spring:theme
										code="text.account.viewWishlistEntries.page.desired"
										text="Qty" /></th>				
								<th id="header1"><spring:theme
										code="text.account.viewWishlistEntries.page.bought"
										text="Bought" /></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${updateWishlistForm.entriesList}" var="entry"
									varStatus="status">
								<c:url value="${entry.product.url}" var="productUrl" />
								<tr class="cartItem">
									<td class="thumb-w"><product:productPrimaryImage
											product="${entry.product}" format="thumbnail" /></td>
									<td class="product-w itemName-w"><ycommerce:testId
											code="orderHistory_Total_links">
											<p>
												<p>
													<a href="${productUrl}">${entry.product.name}</a>
												</p>
											</p>
										</ycommerce:testId></td>
									<td class="price-w"><ycommerce:testId
											code="orderHistory_Total_links">
											<format:fromPrice priceData="${entry.product.price}" />
										</ycommerce:testId></td>
									<td class="desired-w">
										<div style="float: left;"">
                                            <a id="RemoveProduct_0" class="submitRemoveProduct" title="Remover" style="margin-left: 0px!important;" href="#">Remover</a>
										</div>
										<label class="skip" for="quantity0"></label>
<input id="quantity0" class="qty" type="text" name="quantity" value="${entry.desired}" size="1">
                               <div class="more-less" style="margin-top: 0">
                                    <a id="QuantityProduct_0_more" class="updateQuantityProductMore1" href="#" title="Adicionar 1 item"> +</a>
                                    <a id="QuantityProduct_0_less" class="updateQuantityProductLess1" href="#" title="Remover 1 item"> -</a>
                                </div>
                                        
									</td>
									<td class="bought-w">${entry.received}</td>
									<td>
										<form id="addToCartForm${entry.product.code}" action="<c:url value="/cart/add"/>" method="post" class="add_to_cart_form">
											<input type="hidden" name="productCodePost" value="${entry.product.code}"/>
						                    <input type="hidden" name="wishlistPKPost" value="${wishPK}"/>
											<button type="${buttonType}" class="positive">
												<span class="icon-cart"></span>Adicionar</button>				
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<nav:pagination top="false" supportShowPaged="${isShowPageAllowed}"
						supportShowAll="${isShowAllAllowed}"
						searchPageData="${searchPageData}"
						searchUrl="/my-account/my-wishlist?sort=${searchPageData.pagination.sort}"
						msgKey="text.account.viewWishlistEntries.page"
						numberPagesShown="${numberPagesShown}" />
				</c:if>
				<c:if test="${empty searchPageData.results}">
					<p>
						<spring:theme code="text.account.viewWishlistEntries.noEntries"
							text="You have no entries" />
					</p>
				</c:if>
		</div>
	</div>
</template:page>