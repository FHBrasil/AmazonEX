<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="chaordic" tagdir="/WEB-INF/tags/desktop/template" %>


<%-- JAVASCRIPT FILES FOR DZARM STORE --%>
<c:if test="${themeName == 'black'}">
	<c:set var="localEnvironment" value="${pageContext.request.serverName eq 'dzarm.local'}"/>
	<script>
		var chaordic_meta = new Object();
		var uiExperienceLevel = "${uiExperienceLevel}";
	</script>
	<script type="text/javascript" src="${commonResourcePath}/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/flagship.js"></script>
	<template:javaScriptVariables/>
	<script type="text/javascript" src="${commonResourcePath}/js/jquery.query-2.1.7.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/jquery-ui-1.8.18.min.js"></script> <%-- jquery tabs dependencies --%>
	<script type="text/javascript" src="${commonResourcePath}/js/jquery.jcarousel-0.2.8.min.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/jquery.tmpl-1.0.0pre.min.js"></script> <%-- j query templates --%>
	<script type="text/javascript" src="${commonResourcePath}/js/jquery.blockUI-2.39.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/jquery.colorbox.custom-1.3.16.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/jquery.slideviewer.custom.1.2.js"></script> 
	<script type="text/javascript" src="${commonResourcePath}/js/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/jquery.waitforimages.min.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/jquery.scrollTo-1.4.2-min.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/jquery.ui.stars-3.0.1.min.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/jquery.form-3.09.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/jquery.bgiframe-2.1.2.min.js"></script> <%-- BeautyTips  --%>
	<!--[if IE]><script type="text/javascript" src="${commonResourcePath}/js/excanvas-r3.compiled.js"></script>-->
	<script type="text/javascript" src="${commonResourcePath}/js/jquery.bt-0.9.5-rc1.min.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/jquery.pstrength.custom-1.2.0.js"></script> <%-- PasswordStrength  --%>
	<script type="text/javascript" src="${commonResourcePath}/js/jquery.verticalscrollplus-1.0.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/waypoints.min.1.1.5.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/hering.productpreviewwidget.js"></script>
	<c:if test="${pageType == 'CATEGORY'}">
	    <script type="text/javascript" src="${commonResourcePath}/js/acc.category.js"></script>
	</c:if>
	<c:if test="${pageType == 'PRODUCTSEARCH'}">
	    <script type="text/javascript" src="${commonResourcePath}/js/acc.search.js"></script>
	</c:if>
	<c:if test="${pageType == 'CATEGORY' || pageType == 'PRODUCTSEARCH'}">
		<script type="text/javascript" src="${commonResourcePath}/js/acc.preloadimages.js?date=28102014v2"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/home-hover-produtos.js"></script>
 	</c:if>
	<c:if test="${pageType == 'PRODUCT'}">
	    <script type="text/javascript" src="${commonResourcePath}/js/cloudzoom.js"></script>
	    <script type="text/javascript" src="${commonResourcePath}/js/thumbelina.js"></script>
        <script>
            CloudZoom.quickStart();
            $(function(){
                $('#imageLink').Thumbelina({
                    orientation:'vertical',
                    $bwdBut:$('#slider .top'), 
                    $fwdBut:$('#slider .bottom')
                });
            });
        </script>
	    <script type="text/javascript" src="${commonResourcePath}/js/jquery.highlight.js"></script>
	    <script type="text/javascript" src="${commonResourcePath}/js/glossary.min.js"></script>
	    <script type="text/javascript" src="${commonResourcePath}/js/dzarm-glossary.js"></script>
	</c:if>
	<script src="//crypto-js.googlecode.com/svn/tags/3.0.2/build/rollups/md5.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/acc.common.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/acc.userlocation.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/acc.track.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/acc.cms.js"></script>
	<c:if test="${pageType == 'PRODUCTSEARCH' || pageType == 'PRODUCT' || pageType == 'CATEGORY' || pageType == 'HOMEPAGE'}">
	    <script type="text/javascript" src="${commonResourcePath}/js/acc.product.js"></script>
	</c:if>
	<c:if test="${pageType == 'PRODUCTSEARCH' || pageType == 'CATEGORY'}">
	    <script type="text/javascript" src="${commonResourcePath}/js/acc.refinements.js"></script>
	    <script type="text/javascript" src="${commonResourcePath}/js/acc.paginationsort.js"></script>
	</c:if>
	<c:if test="${pageType == 'STOREFINDERPAGE'}">
	    <script type="text/javascript" src="${commonResourcePath}/js/acc.storefinder.js"></script>
	</c:if>
	<c:if test="${pageType == 'HOMEPAGE'}">
	    <script type="text/javascript" src="${commonResourcePath}/js/acc.carousel.js"></script>
	</c:if>
	<script type="text/javascript" src="${commonResourcePath}/js/acc.autocomplete.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/acc.password.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/acc.pstrength.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/acc.minicart.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/acc.pickupinstore.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/acc.userlocation.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/acc.langcurrencyselector.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/acc.checkout.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/acc.cartremoveitem.js"></script>
	<c:if test="${pageType == 'CART'}">
	    <script type="text/javascript" src="${commonResourcePath}/js/acc.cart.js"></script>
	</c:if>
	<c:if test="${pageType == 'ORDERCONFIRMATION'}">	     
        <script type="text/javascript" src="${commonResourcePath}/js/globalMessages.js"></script>
	</c:if>
	<c:if test="${pageType == 'ACCOUNTPAGE'}">
		<script type="text/javascript" src="${commonResourcePath}/js/acc.account.js"></script>
	</c:if>
	<script type="text/javascript" src="${commonResourcePath}/js/acc.address.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/acc.refresh.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/acc.stars.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/acc.forgotpassword.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/jquery.accessible-tabs-1.9.7.min.js"></script>
	<c:if test="${pageType == 'PRODUCT'}">
	    <script type="text/javascript" src="${commonResourcePath}/js/acc.productDetail.js"></script>
	</c:if>
	<script type="text/javascript" src="${commonResourcePath}/js/acc.producttabs.js"></script>	
	<c:forEach items="${addOnJavaScriptPaths}" var="addOnJavaScript">
	    <script type="text/javascript" src="${addOnJavaScript}"></script>
	</c:forEach>
	<script type="text/javascript" src="${commonResourcePath}/js/acc.skiplinks.js"></script> <%-- Fix for Webkit Browsers (Needs to be loaded last)  --%>
	<script async defer src="//static.chaordicsystems.com/static/loader.js" data-apikey="dzarm" data-initialize="false"></script>
	<!-- <script> -->
	<!-- 	if(chaordic != undefined){ -->
	<!-- 		chaordic.initialize(); -->
	<!-- 	} -->
	<!-- </script>  -->
	<%-- <script type="text/javascript" src="${commonResourcePath}/js/acc.chaordicloader.js"></script>	 --%>
	<script type="text/javascript" src="//static.criteo.net/js/ld/ld.js" async="async"></script> <%-- Criteo loader --%>
	<script type="text/javascript" src="${commonResourcePath}/js/jquery.simplemodal.1.4.4.min.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/instafeed.min.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/dzarm-instagram.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/home-hover-produtos.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/modernizr.custom.min.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/dzarm-geral.js"></script>
	<c:if test="${not localEnvironment}">
	    <script type="text/javascript" src="//maps.google.com/maps/api/js?key=AIzaSyDeh3UsnMeuHKVhkF1e421VZP93aUX18rI&amp;sensor=false"></script>
	    <script type="text/javascript" src="${commonResourcePath}/js/dzarm-lib-gmaps.js"></script>
	    <script type="text/javascript" src="${commonResourcePath}/js/dzarm-maps.js"></script>
	</c:if>	
	<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start': new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0], j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src= '//www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f); })(window,document,'script','dataLayer','GTM-PPGFRW');</script>
	<noscript><iframe src="//www.googletagmanager.com/ns.html?id=GTM-PPGFRW" height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript> 
	<template:validation/>
	
	
	<script type="text/javascript" src="${commonResourcePath}/js/jquery.mask.min.js"></script>
	<script>
	    function enfocus() {
	    	getElementById('j_username').focus()
	        getElementById('forgottenPwdForm').value = getElementById('j_username').value
	        setTimeout("enblur()", 500)
	    }
	    function enblur() {
	    	getElementById('j_username').blur()
	       	setTimeout("enfocus()", 5)
	    }
	</script>
	<script>
	    gotoTop = function () {
	    	"use strict";
	    	function a() {
	            $("body, html").stop(!0, !0).animate({ scrollTop: 0 }, 1e3)
	        }
	        function b() {
	            c.click(function () { a() }),
	            d.scroll(function () {
	                d.scrollTop() < 100 ? c.is(".hide") || c.addClass("hide") : c.is(".hide") && c.removeClass("hide")
	            })
	        }
	        var c = $(".go-to-top"),d = $(window);
	    	return {
	    		init: function () { b() }, start: a() } 
	    }();
	    gotoTop.init();
	</script>
	<c:if test="${newsletterregistration}">
		<script>
	    	$(document).ready(function() {
	    		$.colorbox({inline:true, href:".lightbox_colorbox", width:"545px",height:"270px"});
	    	});
		</script>
	</c:if>
	<c:if test="${notifymeregistration}">
		<script>
	    	$(document).ready(function() {
	    		$.colorbox({inline:true, href:".lightbox_colorbox", width:"545px",height:"270px"});
	    	});
		</script>
	</c:if>
	
	<template:ferramentaRecomendacaoComponent/>
	
	<c:if test="${not localEnvironment}">
	   <script type="text/javascript" src="${commonResourcePath}/js/chaordicComponent.js"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/criteoComponent.js"></script>
		 
	</c:if>
	
	<script src="//configusa.veinteractive.com/tags/32BE7B85/4650/4CFE/8955/08BF141F9CAF/tag.js" type="text/javascript" async></script>
	
	<script type="text/javascript" src="/store/_ui/desktop/theme-hering/assets/css/hotsites/dzarm/blackfriday-2014/js/dzarm.black-friday.js"></script>
	
	<c:if test="${empty pageType}">
		<%-- Blackfriday just on http://www.dzarm.com.br/store/dzarm-black-friday-2014 --%>
<!-- 		<script type="text/javascript" src="/store/_ui/desktop/theme-hering/assets/css/hotsites/dzarm/blackfriday-2014/js/countdown.min.js"></script> -->
<!-- 		<script type="text/javascript" src="/store/_ui/desktop/theme-hering/assets/css/hotsites/dzarm/blackfriday-2014/js/blackfriday-2014.js"></script> -->
	</c:if>
	
	<c:if test="${not localEnvironment && (pageType == 'HOMEPAGE' || pageType == 'PRODUCT' || ( fn:containsIgnoreCase(pageType,'CMS-CLUBE-HERING') ) ) }">
		<script src="//myreks.com/plugin/recommendit/widget/snippet.js" async="true"></script>
	</c:if>
	
</c:if>

<%-- JAVASCRIPT FILES FOR HERING STORE --%>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	
	<script>
		var chaordic_meta = new Object();
		var uiExperienceLevel = "${uiExperienceLevel}";
	</script>
	<%-- LIBRARIES FILES --%>
	<script type="text/javascript" src="${themeResourcePath}/assets/js/jquery.js"></script>
	<script type="text/javascript" src="${themeResourcePath}/assets/js/jquery.bxslider.js"></script>
	<script type="text/javascript" src="${themeResourcePath}/assets/js/fancybox/jquery.fancybox.js"></script>
	<script type="text/javascript" src="${themeResourcePath}/assets/js/fancybox/helpers/jquery.fancybox-media.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/jquery-ui-1.9.2.custom.min.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/jquery.mask.min.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/waypoints.min.1.1.5.js"></script>
	<c:if test="${pageType != 'PRODUCT'}">
		<%-- TODO: this file is not supposed to be used --%>
		<script type="text/javascript" src="${themeResourcePath}/assets/js/script.js"></script>
	</c:if>
	
	 <%-- Criteo loader --%>
	<script type="text/javascript" src="//static.criteo.net/js/ld/ld.js" async="async"></script>
    
    <c:if test="${themeName == 'dzarm' && false}">
	    <script type="text/javascript" src="//maps.google.com/maps/api/js?key=AIzaSyDeh3UsnMeuHKVhkF1e421VZP93aUX18rI&amp;sensor=false"></script>
	    <script type="text/javascript" src="${commonResourcePath}/js/dzarm-lib-gmaps.js"></script>
	    <script type="text/javascript" src="${commonResourcePath}/js/dzarm-maps.js"></script>
	</c:if>
    
   <!-- ALTERACOES DO THESKO PARA CORRIGIR O ADD TO CART  -->
   	<script type="text/javascript" src="${commonResourcePath}/js/jquery.form-3.09.js"></script>
   	<script type="text/javascript" src="${commonResourcePath}/js/jquery.colorbox.custom-1.3.16.js"></script>
   	<script type="text/javascript" src="${commonResourcePath}/js/jquery.jcarousel-0.2.8.min.js"></script>
	

	<%-- SYSTEM VARIABLES --%>
	<%-- javaScriptVariables.tag --%>
	<template:javaScriptVariables/>

    <%-- VALIDATIONS --%>
    <template:validation/>

	<%-- COMMON FILES --%>
	<script type="text/javascript" src="${themeResourcePath}/assets/js/hering.autocomplete.js"></script>
    <script type="text/javascript" src="${themeResourcePath}/assets/js/globalMessages.js"></script>
    <script type="text/javascript" src="${themeResourcePath}/assets/js/hering.commons.js"></script>
    <script type="text/javascript" src="${themeResourcePath}/assets/js/hering.form.js"></script>
    <script type="text/javascript" src="${commonResourcePath}/js/hering.showcase.js"></script>
	<c:if test="${pageType != 'PRODUCT' }">
<%-- 		<script type="text/javascript" src="${themeResourcePath}/assets/js/acc.productlist.js"></script> --%>
	</c:if>		
	<c:if test="${themeName == 'dzarm' }">
		<c:if test="${not localEnvironment}">
			<script type="text/javascript" src="//maps.google.com/maps/api/js?key=AIzaSyDeh3UsnMeuHKVhkF1e421VZP93aUX18rI&amp;sensor=false"></script>
	    	<script type="text/javascript" src="${commonResourcePath}/js/dzarm-lib-gmaps.js"></script>
	    	<script type="text/javascript" src="${commonResourcePath}/js/dzarm-maps.js"></script>
		</c:if>
	</c:if>
	
	<%-- PAGE FILES--%>
	<c:if test="${pageType == 'HOMEPAGE'}">
		<script type="text/javascript" src="${themeResourcePath}/assets/js/pages/home.js"></script>
	</c:if>
	
	<c:if test="${pageType == 'PRODUCTSEARCH' || pageType == 'CATEGORY'}">
		<script type="text/javascript" src="${themeResourcePath}/assets/js/jquery.mousewheel.js"></script>
		<script type="text/javascript" src="${themeResourcePath}/assets/js/jquery.jscrollpane.min.js"></script>
	
		<script type="text/javascript" src="${themeResourcePath}/assets/js/pages/busca.js"></script>
		<script type="text/javascript" src="${themeResourcePath}/assets/js/pages/hering.category.js"></script>
	</c:if>
	
	<c:if test="${pageType == 'CHECKOUT'}">
		<script type="text/javascript" src="${themeResourcePath}/assets/js/pages/identificacao.js"></script>
	</c:if>
	
	<c:if test="${pageType == 'PRODUCT'}">
		<script type="text/javascript" src="${themeResourcePath}/assets/js/hering.product.cross-variables.js"></script>
		<script type="text/javascript" src="${themeResourcePath}/assets/js/hering.product.cross-cart.js"></script>
		<script type="text/javascript" src="${themeResourcePath}/assets/js/pages/produto.js"></script>
		<script type="text/javascript" src="${themeResourcePath}/assets/js/hering.product.js"></script>
		<%-- <script type="text/javascript" src="${themeResourcePath}/assets/js/hering.product.out-of-stock.js"></script> --%>		
	</c:if>
	
	<c:if test="${pageType == 'ACCOUNTPAGE'}">
		<script type="text/javascript" src="${themeResourcePath}/assets/js/pages/sua-conta.js"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/customer.js"></script>
		<!-- <script type="text/javascript" src="${themeResourcePath}/assets/js/pages/searchOrderHistory.js"></script> -->
	</c:if>
	
	<c:if test="${pageType == 'CART'}">
	    <script type="text/javascript" src="${commonResourcePath}/js/acc.cart.js"></script>
	    <script type="text/javascript" src="${themeResourcePath}/assets/js/hering.cart.js"></script>
	    <script type="text/javascript" src="${themeResourcePath}/assets/js/pages/sacola.js"></script>
	</c:if>

	<c:if test="${pageType == 'LOGIN'}">
		<script type="text/javascript" src="${themeResourcePath}/assets/js/hering.login.js"></script>
		<script type="text/javascript" src="${themeResourcePath}/assets/js/pages/identificacao.js"></script>
		<script type="text/javascript" src="${themeResourcePath}/assets/js/pages/hering.forgotpassword.js"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/customer.js"></script>
	</c:if>
    
    <c:if test="${pageType == 'ORDERCONFIRMATION'}">
        <script type="text/javascript" src="${themeResourcePath}/assets/js/pages/singleStepCheckout.js"></script>
        <script type="text/javascript" src="${themeResourcePath}/assets/js/pages/acc.silentorderpost.js"></script>
        <script type="text/javascript" src="${themeResourcePath}/assets/js/pages/acc.updatebillingaddress.js"></script>
        <script src="//crypto-js.googlecode.com/svn/tags/3.0.2/build/rollups/md5.js"></script>
    </c:if>
    
    <c:if test="${pageType == 'SINGLESTEPCHECKOUT'}">
        <script type="text/javascript" src="${themeResourcePath}/assets/js/pages/singleStepCheckout.js"></script>
        <script type="text/javascript" src="${themeResourcePath}/assets/js/pages/acc.silentorderpost.js"></script>
        <script type="text/javascript" src="${themeResourcePath}/assets/js/pages/acc.updatebillingaddress.js"></script>
    </c:if>
	
	<c:if test="${fn:contains(pageType,'CMS')}">
		<script type="text/javascript" src="${themeResourcePath}/assets/js/pages/institucional.js"></script>
		
		<%-- Blackfriday just on http://www.hering.com.br/store/hering-black-friday-2014 --%>
<%-- 		<script type="text/javascript" src="${themeResourcePath}/assets/css/hotsites/hering/blackfriday-2014/js/countdown.min.js"></script> --%>
<%-- 		<script type="text/javascript" src="${themeResourcePath}/assets/css/hotsites/hering/blackfriday-2014/js/blackfriday-2014.js"></script> --%>
	</c:if>
	
	<!-- ALTERACOES DO THESKO PARA CORRIGIR O ADD TO CART  -->
	<script type="text/javascript" src="${commonResourcePath}/js/acc.common.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/acc.userlocation.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/acc.track.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/acc.cms.js"></script>

	<c:if test="${pageType == 'HOMEPAGE'}">
	    <script type="text/javascript" src="${commonResourcePath}/js/acc.carousel.js"></script>
	    <script type="text/javascript" src="${themeResourcePath}/assets/js/textBoxInfoParagraphComponent.js"></script>
	    <!-- <script type="text/javascript" src="${themeResourcePath}/assets/js/pages/hering.category.js"></script>  -->
	</c:if>
	
	<c:if test="${pageType == 'CATEGORY'}">
	    <script type="text/javascript" src="${commonResourcePath}/js/acc.category.js"></script>
	</c:if>
	
 	<c:if test="${pageType == 'PRODUCTSEARCH' || pageType == 'PRODUCT' || pageType == 'CATEGORY' || pageType == 'HOMEPAGE'}">
		<script type="text/javascript" src="${commonResourcePath}/js/hering.productpreviewwidget.js"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/acc.preloadimages.js?date=28102014v2"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/home-hover-produtos.js"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/acc.carousel.js"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/acc.product.js"></script>
		<script type="text/javascript" src="${themeResourcePath}/assets/js/acc.product.hering-override.js"></script>
		<c:if test="${pageType != 'PRODUCT'}">
			<script type="text/javascript" src="${commonResourcePath}/js/acc.search.js"></script>
		</c:if>
	</c:if>
	<c:if test="${pageType == 'PRODUCTSEARCH' || pageType == 'CATEGORY'}">
	    <script type="text/javascript" src="${commonResourcePath}/js/acc.refinements.js"></script>
	    <script type="text/javascript" src="${commonResourcePath}/js/acc.paginationsort.js"></script>
	    <script type="text/javascript" src="${commonResourcePath}/js/acc.track.js"></script>
	</c:if>
	<script type="text/javascript" src="${commonResourcePath}/js/acc.minicart.js"></script>
	
	<c:if test="${pageType != 'ORDERCONFIRMATION'}">
		<script type="text/javascript" src="${themeResourcePath}/assets/js/pages/newsletter.js"></script>
	</c:if>
			
		
	
	<!-- Descomentar para utilizar o facebook. -->
	<%-- <c:forEach items="${addOnJavaScriptPaths}" var="addOnJavaScript">
		<c:if test="${not fn:containsIgnoreCase(addOnJavaScript, 'placeorder.js')}">
   			<script type="text/javascript" src="${addOnJavaScript}"></script>	
		</c:if>
	</c:forEach> --%>
	
	<template:ferramentaRecomendacaoComponent/>
	
	<c:if test="${not localEnvironment}"> 
	    <script type="text/javascript" src="${commonResourcePath}/js/vizuryTagHering.js" id="scriptVizury"></script>
	    <script type="text/javascript" src="${commonResourcePath}/js/chaordicComponent.js"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/criteoComponent.js"></script>
	</c:if>
	
	<c:if test="${not localEnvironment && (pageType == 'HOMEPAGE' || pageType == 'PRODUCT' || ( fn:containsIgnoreCase(pageType,'CMS-CLUBE-HERING') ) || ( fn:containsIgnoreCase(pageType,'CMS-CLUBE-DZARM') ) || ( fn:containsIgnoreCase(pageType,'CMS-CLUBE-FORYOU') ) ) }">
		<script src="//myreks.com/plugin/recommendit/widget/snippet.js" async="true"></script>
	</c:if>		
	
	<c:if test="${themeName == 'hering'}">
		<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start': new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0], j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src= '//www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f); })(window,document,'script','dataLayer','GTM-5KLT7Z');</script>
		<noscript><iframe src="//www.googletagmanager.com/ns.html?id=GTM-5KLT7Z" height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
	</c:if>
	<c:if test="${themeName == 'dzarm'}">
		<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start': new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0], j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src= '//www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f); })(window,document,'script','dataLayer','GTM-PPGFRW');</script>
		<noscript><iframe src="//www.googletagmanager.com/ns.html?id=GTM-PPGFRW" height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
	</c:if>
	<c:if test="${themeName == 'foryou'}">
		<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start': new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0], j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src= '//www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f); })(window,document,'script','dataLayer','GTM-NJ5FL6');</script>
		<noscript><iframe src="//www.googletagmanager.com/ns.html?id=GTM-NJ5FL6" height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
	</c:if>
</c:if>

<c:if test="${not localEnvironment && themeName == 'foryou'}">
	<script async defer src="//static.chaordicsystems.com/static/loader.js" data-apikey="heringforyou" data-initialize="false"></script>
</c:if>

<c:if test="${not localEnvironment && themeName == 'hering'}">
   <script async defer src="//static.chaordicsystems.com/static/loader.js" data-apikey="hering-v5" data-initialize="false"></script>
	<script type="text/javascript">
		(function() {
			window.NeoAssistTag = {};
			NeoAssistTag.querystring = true;
			NeoAssistTag.pageid = 'hrgnova';
			NeoAssistTag.clientdomain = 'hering.neoassist.com';
			var na = document.createElement('script');
			na.type = 'text/javascript';
			na.async = true;
			na.src = '//cdn.atendimen.to/n.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(na, s);
		})();
	</script>
</c:if>

<c:if test="${not localEnvironment && themeName == 'teste'}">
	<script async defer src="//static.chaordicsystems.com/static/loader.js" data-apikey="dzarm" data-initialize="false"></script>
	<script type="text/javascript">
		(function() {
			window.NeoAssistTag = {};
			NeoAssistTag.querystring = true;
			NeoAssistTag.pageid = 'hrgdzarmlt';
			NeoAssistTag.clientdomain = 'hering.neoassist.com';
			var na = document.createElement('script');
			na.type = 'text/javascript';
			na.async = true;
			na.src = '//cdn.atendimen.to/n.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(na, s);
		})();
	</script>
</c:if>

<c:if test="${not localEnvironment && themeName == 'foryou'}">
	<script type="text/javascript">
		(function() {
			window.NeoAssistTag = {};
			NeoAssistTag.querystring = true;
			NeoAssistTag.pageid = 'hrgnovafy';
			NeoAssistTag.clientdomain = 'hering.neoassist.com';
			var na = document.createElement('script');
			na.type = 'text/javascript';
			na.async = true;
			na.src = '//cdn.atendimen.to/n.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(na, s);
		})();
	</script>
</c:if>