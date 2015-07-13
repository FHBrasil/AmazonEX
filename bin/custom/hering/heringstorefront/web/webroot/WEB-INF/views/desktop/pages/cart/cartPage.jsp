<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>

<spring:theme text="Your Shopping Cart" var="title" code="cart.page.title" />
<c:url value="/cart/checkout/" var="checkoutUrl" />

<template:page pageTitle="${pageTitle}">
    <spring:theme code="basket.add.to.cart" var="basketAddToCart" />
    <spring:theme code="cart.page.checkout" var="checkoutText" />
    <common:globalMessages />
    <cart:cartRestoration />
    <cart:cartValidation />
    <!-- CART Data -->
    <div class="cartData">
    	<input type="hidden" class="cartCode" value="${cartData.code}" />
    </div>
    <div class="col-xs-12 col-sm-8 col-md-9 cart150529">
    	<h2><spring:theme code="text.fliegercommerce.texto10"/></h2>    
	    <%-- // IF NOT EMPTY CART --%>
	    <c:if test="${not empty cartData.entries}">
	    	<p class="underlinelinks150212">
	    		<spring:theme code="text.fliegercommerce.texto128"/>&nbsp;${cartData.totalUnitCount}&nbsp;<spring:theme code="text.fliegercommerce.texto129"/>&nbsp;
	    		<spring:theme code="text.fliegercommerce.texto130"/>
	    	</p>
	    	<cart:cartItems cartData="${cartData}" product="${product}" />
	        <cart:cartTotals cartData="${cartData}" showTaxEstimate="true" showCalculateDeliveryComponent="true" />	        
	    </c:if>
	    <%-- // IF EMPTY CART --%>
	    <c:if test="${empty cartData.entries}">
            <header>
                <h1><spring:theme code="basket.page.empty" /></h1>
            </header>
            <p><spring:theme code="basket.page.emptyInfoHering" /></p>
            <a href="${request.contextPath}" class="btn btn-default">
				<span class="hidden-xs"><spring:theme code="basket.page.back" />&nbsp;</span>
				<spring:theme code="basket.page.toTheShop" />
			</a>
        </c:if>
    </div>
    <div class="hidden-xs col-sm-4 col-md-3 cart-banner">
		<cms:pageSlot position="SideContent" var="component">
            <cms:component component="${component}" />
        </cms:pageSlot>
	</div>
</template:page>

<%-- OLD CODE
<template:page pageTitle="${pageTitle}">
    <spring:theme code="basket.add.to.cart" var="basketAddToCart" />
    <spring:theme code="cart.page.checkout" var="checkoutText" />
    <common:globalMessages />
    <cart:cartRestoration />
    <cart:cartValidation />
    <div id="main-wrapper">
        <div class="container">
            <!-- CART Data -->
            <div class="cartData">
                <input type="hidden" class="cartCode" value="${cartData.code}" />
            </div>
            <%-- HEADER CART 
            <div chaordic="top"></div>
            <header id="page-header">
                <h1><spring:theme code="text.fliegercommerce.texto10"/></h1>
            </header>--%>
            <%-- SE O CART ESTIVER VAZIO, ADICIONA A CLASSE EMPTY NA DIV 
            <section id="cart" <c:if test="${empty cartData.entries}">class="empty"</c:if>>--%>
                <%-- // SE O CARRINHO nao ESTIVER VAZIO 
                <c:if test="${not empty cartData.entries}">--%>
                    <%-- cartItems.tag 
                    <cart:cartItems cartData="${cartData}" product="${product}" />--%>
                    <%-- cartTotals.tag 
                    <cart:cartTotals cartData="${cartData}" showTaxEstimate="true"
                        showCalculateDeliveryComponent="true" />
                </c:if>--%>
                <%-- // SE O CARRINHO ESTIVER VAZIO 
                <c:if test="${empty cartData.entries}">
                    <div class="container">
                        <header>
                            <h1>
                                <spring:theme code="basket.page.empty" />
                            </h1>
                        </header>
                        <p>
                            <spring:theme code="basket.page.emptyInfoHering" />
                        </p>
                        <a href="${request.contextPath}" class="btn-continuar-navegando btn"><spring:theme code="text.fliegercommerce.texto11"/></a>
                    </div>
                </c:if>
        </div>
    </div>
</template:page>
--%>