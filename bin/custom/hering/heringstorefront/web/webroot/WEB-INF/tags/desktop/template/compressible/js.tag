<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="chaordic" tagdir="/WEB-INF/tags/desktop/template"%>
<script>
    var chaordic_meta = new Object();
    var uiExperienceLevel = "${uiExperienceLevel}";
</script>
<%-- LIBRARIES FILES --%>
<script type="text/javascript" src="${themeResourcePath}/assets/js/jquery.js"></script>
<script type="text/javascript" src="${themeResourcePath}/assets/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${themeResourcePath}/assets/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${themeResourcePath}/assets/js/bootstrap-slider.min.js"></script>
<script type="text/javascript" src="${themeResourcePath}/assets/js/jquery.mobile.custom.min.js"></script>
<script type="text/javascript" src="${themeResourcePath}/assets/js/jquery.bxslider.js"></script>
<script type="text/javascript" src="${themeResourcePath}/assets/js/fancybox/jquery.fancybox.js"></script>
<script type="text/javascript" src="${themeResourcePath}/assets/js/trustedshops.js"></script>
<script type="text/javascript" src="${themeResourcePath}/assets/js/fancybox/helpers/jquery.fancybox-media.js"></script>
<%-- For works the datepicker of the newsletter form --%>
<c:if test="${pageType != 'ORDERCONFIRMATION'}">
	<script type="text/javascript" src="${commonResourcePath}/js/jquery-ui-1.9.2.custom.min.js"></script>
</c:if>
<%-- <script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script> --%>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.mask.min.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/waypoints.min.1.1.5.js"></script>
<script type="text/javascript" src="${themeResourcePath}/assets/js/ba.product.js"></script>
<script type="text/javascript" src="${themeResourcePath}/assets/js/ba.searchpage.js"></script>
<c:if test="${pageType != 'PRODUCT'}">
    <%-- TODO: this file is not supposed to be used --%>
    <script type="text/javascript" src="${themeResourcePath}/assets/js/script.js"></script>
    <%-- pinterest --%>
    <script type="text/javascript" async src="//assets.pinterest.com/js/pinit.js"></script>
</c:if>
<%-- Criteo loader 
<script type="text/javascript" src="//static.criteo.net/js/ld/ld.js" async="async"></script>--%>
<%-- ALTERACOES DO THESKO PARA CORRIGIR O ADD TO CART  --%>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.form-3.09.js"></script>
<%-- <script type="text/javascript" src="${commonResourcePath}/js/jquery.colorbox.custom-1.3.16.js"></script> --%>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.jcarousel-0.2.8.min.js"></script>
<%-- SYSTEM VARIABLES --%>
<%-- javaScriptVariables.tag --%>
<template:javaScriptVariables />
<%-- VALIDATIONS --%>
<template:validation />

<%-- COMMON FILES --%>
<%-- Commented the auto complete > giving conflict --%>
<%-- <script type="text/javascript" src="${themeResourcePath}/assets/js/hering.autocomplete.js"></script>--%>
<script type="text/javascript" src="${themeResourcePath}/assets/js/globalMessages.js"></script>
<script type="text/javascript" src="${themeResourcePath}/assets/js/hering.commons.js"></script>
<script type="text/javascript" src="${themeResourcePath}/assets/js/hering.form.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/hering.showcase.js"></script>
<c:if test="${pageType != 'PRODUCT' }">
    <%-- 		<script type="text/javascript" src="${themeResourcePath}/assets/js/acc.productlist.js"></script> --%>
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
    <script type="text/javascript"
        src="${themeResourcePath}/assets/js/hering.product.cross-variables.js"></script>
    <script type="text/javascript" src="${themeResourcePath}/assets/js/hering.product.cross-cart.js"></script>
    <script type="text/javascript" src="${themeResourcePath}/assets/js/pages/produto.js"></script>
    <script type="text/javascript" src="${themeResourcePath}/assets/js/hering.product.js"></script>
    <%-- <script type="text/javascript" src="${themeResourcePath}/assets/js/hering.product.out-of-stock.js"></script> --%>
</c:if>
<c:if test="${pageType == 'ACCOUNTPAGE'}">
    <script type="text/javascript" src="${themeResourcePath}/assets/js/pages/sua-conta.js"></script>
    <script type="text/javascript" src="${commonResourcePath}/js/customer.js"></script>
    <!-- <script type="text/javascript" src="${themeResourcePath}/assets/js/pages/searchOrderHistory.js"></script> -->
	<script type="text/javascript" src="${themeResourcePath}/assets/js/scheduledNewsletter.js"></script>
    <script type="text/javascript" src="${themeResourcePath}/assets/js/tipsNewsletter.js"></script>
    <script type="text/javascript" src="${themeResourcePath}/assets/js/tipsNewsletterDate.js"></script>
    <script type="text/javascript" src="${themeResourcePath}/assets/js/reviewShoppingExperience.js"></script>
    <script type="text/javascript" src="${themeResourcePath}/assets/js/reviewOrderedProducts.js"></script>
</c:if>
<c:if test="${pageType == 'CART'}">
    <script type="text/javascript" src="${commonResourcePath}/js/acc.cart.js"></script>
    <script type="text/javascript" src="${themeResourcePath}/assets/js/hering.cart.js"></script>
    <script type="text/javascript" src="${themeResourcePath}/assets/js/pages/sacola.js"></script>
</c:if>
<c:if test="${pageType == 'LOGIN'}">
    <script type="text/javascript" src="${themeResourcePath}/assets/js/hering.login.js"></script>
    <script type="text/javascript" src="${themeResourcePath}/assets/js/pages/identificacao.js"></script>
    <script type="text/javascript"
        src="${themeResourcePath}/assets/js/pages/hering.forgotpassword.js"></script>
    <script type="text/javascript" src="${commonResourcePath}/js/customer.js"></script>
</c:if>
<c:if test="${pageType == 'ORDERCONFIRMATION'}">
	<%-- For works the datepicker of the newsletter form --%>
	<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
    <script type="text/javascript" src="${themeResourcePath}/assets/js/pages/singleStepCheckout.js"></script>
    <script type="text/javascript" src="${themeResourcePath}/assets/js/pages/acc.silentorderpost.js"></script>
    <script type="text/javascript" src="${themeResourcePath}/assets/js/pages/acc.updatebillingaddress.js"></script>
    <script src="//crypto-js.googlecode.com/svn/tags/3.0.2/build/rollups/md5.js"></script>
</c:if>
<c:if test="${pageType == 'SINGLESTEPCHECKOUT'}">
	<script type="text/javascript" src="${commonResourcePath}/js/acc.cart.js"></script>
    <script type="text/javascript" src="${themeResourcePath}/assets/js/hering.cart.js"></script>
    <script type="text/javascript" src="${themeResourcePath}/assets/js/pages/singleStepCheckout.js"></script>
    <script type="text/javascript" src="${themeResourcePath}/assets/js/pages/acc.silentorderpost.js"></script>
    <script type="text/javascript"
        src="${themeResourcePath}/assets/js/pages/acc.updatebillingaddress.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/js/checkoutbonussystem.js"></script>
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
    <script type="text/javascript"
        src="${themeResourcePath}/assets/js/textBoxInfoParagraphComponent.js"></script>
    <!-- <script type="text/javascript" src="${themeResourcePath}/assets/js/pages/hering.category.js"></script>  -->
</c:if>
<c:if test="${pageType == 'CATEGORY'}">
    <script type="text/javascript" src="${commonResourcePath}/js/acc.category.js"></script>
</c:if>
<c:if
    test="${pageType == 'PRODUCTSEARCH' || pageType == 'PRODUCT' || pageType == 'CATEGORY' || pageType == 'HOMEPAGE'}">
    <script type="text/javascript" src="${commonResourcePath}/js/hering.productpreviewwidget.js"></script>
    <script type="text/javascript"
        src="${commonResourcePath}/js/acc.preloadimages.js?date=28102014v2"></script>
    <script type="text/javascript" src="${commonResourcePath}/js/home-hover-produtos.js"></script>
    <script type="text/javascript" src="${commonResourcePath}/js/acc.carousel.js"></script>
    <script type="text/javascript" src="${commonResourcePath}/js/acc.product.js"></script>
    <script type="text/javascript"
        src="${themeResourcePath}/assets/js/acc.product.hering-override.js"></script>
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
<%-- <c:if test="${pageType != 'ORDERCONFIRMATION'}">
    <script type="text/javascript" src="${themeResourcePath}/assets/js/pages/newsletter.js"></script>
</c:if> --%>


<!-- IMPORT DE JS DE ADDONS -->
<c:forEach items="${addOnJavaScriptPaths}" var="addOnJavaScript">
	<c:if test="${not fn:containsIgnoreCase(addOnJavaScript, 'placeorder.js')}">
  			<script type="text/javascript" src="${addOnJavaScript}"></script>	
	</c:if>
</c:forEach>


<%-- <template:ferramentaRecomendacaoComponent />
<c:if test="${not localEnvironment}">
    <script type="text/javascript" src="${commonResourcePath}/js/vizuryTagHering.js"
        id="scriptVizury"></script>
    <script type="text/javascript" src="${commonResourcePath}/js/chaordicComponent.js"></script>
    <script type="text/javascript" src="${commonResourcePath}/js/criteoComponent.js"></script>
</c:if>
<c:if
    test="${not localEnvironment && (pageType == 'HOMEPAGE' || pageType == 'PRODUCT' || ( fn:containsIgnoreCase(pageType,'CMS-CLUBE-HERING') ) || ( fn:containsIgnoreCase(pageType,'CMS-CLUBE-DZARM') ) || ( fn:containsIgnoreCase(pageType,'CMS-CLUBE-FORYOU') ) ) }">
    <script src="//myreks.com/plugin/recommendit/widget/snippet.js" async="true"></script>
</c:if>
<script>
    (function(w, d, s, l, i) {
        w[l] = w[l] || [];
        w[l].push({
            'gtm.start' : new Date().getTime(),
            event : 'gtm.js'
        });
        var f = d.getElementsByTagName(s)[0], j = d.createElement(s), dl = l != 'dataLayer' ? '&l='
                + l : '';
        j.async = true;
        j.src = '//www.googletagmanager.com/gtm.js?id=' + i + dl;
        f.parentNode.insertBefore(j, f);
    })(window, document, 'script', 'dataLayer', 'GTM-5KLT7Z');
</script>
<noscript>
    <iframe src="//www.googletagmanager.com/ns.html?id=GTM-5KLT7Z" height="0" width="0"
        style="display: none; visibility: hidden"></iframe>
</noscript>
<script async defer src="//static.chaordicsystems.com/static/loader.js" data-apikey="hering-v5"
    data-initialize="false"></script>--%>

<script type="text/javascript">
    (function () { 
    var _tsid = 'XA2EF864014A142CF9EDC2483FE556551'; 
    _tsConfig = { 
        'yOffset': '30', /* offset from page bottom */ 
        'variant': 'reviews' /* text, default, small, reviews */
    };
    var _ts = document.createElement('script');
    _ts.type = 'text/javascript'; 
    _ts.async = true; 
    _ts.charset = 'utf-8'; 
    _ts.src = '//widgets.trustedshops.com/js/' + _tsid + '.js'; 
    var __ts = document.getElementsByTagName('script')[0];
    __ts.parentNode.insertBefore(_ts, __ts);
    })();
</script>
