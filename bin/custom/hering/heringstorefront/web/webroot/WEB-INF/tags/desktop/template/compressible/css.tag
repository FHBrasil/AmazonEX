<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:if test="${themeName == 'black'}">
	<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/dzarm-main.css"/>
	<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/blueprint/screen.css"/>
	<c:forEach items="${addOnCommonCssPaths}" var="addOnCommonCss">
		<link rel="stylesheet" type="text/css" media="all" href="${addOnCommonCss}"/>
	</c:forEach>
	<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/main.css?date=28102014v1"/>
	<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/tooltips.css"/>
	<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/header.css"/>
	<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/footer.css"/>
	<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/miniCart.css"/>
	<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/navigation.css"/>
	<c:if test="${empty pageType || pageType == 'PRODUCTSEARCH' || pageType == 'CATEGORY' || pageType == 'ACCOUNTPAGE'}">
	    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/facetNav.css"/>
	    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/paginationBar.css"/>
	</c:if>
	<c:if test="${empty pageType || pageType == 'PRODUCTSEARCH' || pageType == 'CATEGORY'}">
	    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/productGrid.css?date=28102014v1"/>
	    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/productList.css"/>
	</c:if>
	<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/productDetails.css"/>
	<c:if test="${empty pageType || pageType == 'ORDERCONFIRMATION' || pageType == 'ACCOUNTPAGE'}">
	    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/order.css"/>
	</c:if>
	<c:if test="${empty pageType || pageType == 'ORDERCONFIRMATION' || pageType == 'CART' || pageType == 'ACCOUNTPAGE'}">
	    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/orderTotals.css"/>
	</c:if>
	<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/account.css"/>
	<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/cartItems.css"/>
	<c:if test="${empty pageType || pageType == 'PRODUCT' || pageType == 'CART'}">
	    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/cartItems.css"/>
	</c:if>
	<c:if test="${empty pageType || pageType == 'HOMEPAGE'}">
	    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/landingLayout2Page.css"/>
	</c:if>
	<c:if test="${empty pageType}">
		<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/storeFinder.css"/>
	    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/storeDetail.css"/>	
	</c:if>
	<c:if test="${pageType == 'STOREFINDERPAGE'}">
	    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/storeFinder.css"/>
	</c:if>
	<c:if test="${empty pageType || pageType == 'PRODUCT'}">
	    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/tabMedidas.css"/>
	    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/tabPagamento.css"/>
	</c:if>
	<c:if test="${empty pageType || pageType == 'PRODUCT'}">
	    <link rel="stylesheet" type="text/css" href="${commonResourcePath}/css/cloudzoom.css"/>
	    <link rel="stylesheet" type="text/css" href="${commonResourcePath}/css/thumbelina.css"/>
	</c:if>
	<c:forEach items="${addOnThemeCssPaths}" var="addOnThemeCss">
		<link rel="stylesheet" type="text/css"  media="all" href="${addOnThemeCss}"/>
	</c:forEach>
	
	<c:if test="${pageType == null}">	
	<%-- Blackfriday just on http://www.dzarm.com.br/store/dzarm-black-friday-2014 --%>						  
		<link rel="stylesheet" type="text/css"  media="all" href="/store/_ui/desktop/theme-hering/assets/css/hotsites/dzarm/blackfriday-2014/css/blackfriday-2014.css">
		<link href='https://fonts.googleapis.com/css?family=PT+Sans:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" href="/store/_ui/desktop/theme-hering/assets/fonts/font-awesome.css">
		<link rel="stylesheet" href="/store/_ui/desktop/theme-hering/assets/fonts/museo-sans.css">
		<link rel="stylesheet" href="/store/_ui/desktop/theme-hering/assets/fonts/steelfish.css">
		<link rel="stylesheet" href="/store/_ui/desktop/theme-hering/assets/css/jquery.jscrollpane.css">
		<link rel="stylesheet" href="/store/_ui/desktop/theme-hering/assets/css/style.css">
	</c:if>
</c:if>

<c:if test="${themeName == 'hering'}">

	<link rel="stylesheet" href="//fonts.googleapis.com/css?family=PT+Sans:400,700,400italic,700italic">
	<link rel="stylesheet" href="${themeResourcePath}/assets/fonts/font-awesome.css">
	<link rel="stylesheet" href="${themeResourcePath}/assets/fonts/museo-sans.css">
	<link rel="stylesheet" href="${themeResourcePath}/assets/fonts/steelfish.css">

	<link rel="stylesheet" type="text/css"  media="all" href="${themeResourcePath}/assets/css/reset.css" />
	<link rel="stylesheet" type="text/css"  media="all" href="${themeResourcePath}/assets/css/base.css" />
	<link rel="stylesheet" type="text/css"  media="all" href="${themeResourcePath}/assets/css/header.css" />
	<link rel="stylesheet" type="text/css"  media="all" href="${themeResourcePath}/assets/css/footer.css" />
	<link rel="stylesheet" type="text/css"  media="all" href="${themeResourcePath}/assets/css/products.css" />
	<link rel="stylesheet" type="text/css"  media="all" href="${themeResourcePath}/assets/css/sliders.css" />
	<link rel="stylesheet" type="text/css"  media="all" href="${themeResourcePath}/assets/css/miniCart.css" />
	
	<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/js/fancybox/jquery.fancybox.css" />
	<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/colorBox.css"/>
	<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/jquery.colorbox-1.3.16.css"/>

	<c:if test="${pageType == 'HOMEPAGE'}">
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/home.css" />
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/textBoxInfoParagraphComponent.css" />
	</c:if>
	
	<c:if test="${pageType == 'PRODUCT'}">
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/produto.css" />
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/productReferencesComponent.css" />
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/productVariantSelector.css" />
	</c:if>
	
	<c:if test="${pageType == 'CART'}">
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/sacola.css" />
	</c:if> 

	<c:if test="${pageType == 'ACCOUNTPAGE'}">
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/sua-conta.css" />
	</c:if>

	<c:if test="${pageType == 'PRODUCTSEARCH' || pageType == 'CATEGORY' || pageType == 'HOMEPAGE'}">
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/busca.css" />
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/heringProductsSearchCategory.css" />
		<link rel="stylesheet" type="text/css"  media="all" href="${themeResourcePath}/assets/css/jquery.jscrollpane.css" />
	</c:if>

	<c:if test="${pageType == 'LOGIN' || pageType == 'CHECKOUT'}">
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/identificacao.css" />	
	</c:if>
	
	<c:if test="${pageType == 'ORDERCONFIRMATION'}">
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/checkout.css" />
	</c:if>
	
	<c:if test="${pageType == 'SINGLESTEPCHECKOUT'}">
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/checkout.css" />
	</c:if>
	
	<c:if test="${fn:contains(pageType,'CMS')}">
       <link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/institucional.css" />
	   
	   <%-- Blackfriday just on http://www.hering.com.br/store/hering-black-friday-2014 --%>
	   <%-- <link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/hotsites/hering/blackfriday-2014/css/blackfriday-2014.css"> --%>
	</c:if>
	
	<%--<link rel="stylesheet" type="text/css"  media="all" href="${themeResourcePath}/assets/css/pages/hotsite-kids.css" />--%>
</c:if>

<c:if test="${themeName == 'dzarm'}">

	<link rel="stylesheet" href="${themeResourcePath}/assets/css/type.css">
	<link rel="stylesheet" href="${themeResourcePath}/assets/css/style.css">

	<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/colorBox.css"/>
	<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/jquery.colorbox-1.3.16.css"/>
	<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/hering.showcase.css"/>

	<c:if test="${pageType == 'HOMEPAGE'}">
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/home.css" />
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/textBoxInfoParagraphComponent.css" />
	</c:if>
	
	<c:if test="${pageType == 'PRODUCT'}">
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/produto.css" />
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/productReferencesComponent.css" />
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/productVariantSelector.css" />
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/productDzarmImagens.css" />
	</c:if>
	
	<c:if test="${pageType == 'CART'}">
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/sacola.css" />
	</c:if> 

	<c:if test="${pageType == 'ACCOUNTPAGE'}">
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/sua-conta.css" />
	</c:if>

	<c:if test="${pageType == 'PRODUCTSEARCH' || pageType == 'CATEGORY' || pageType == 'HOMEPAGE'}">
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/busca.css" />
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/heringProductsSearchCategory.css" />
<%-- 		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/sliders.css" /> --%>
		<link rel="stylesheet" type="text/css"  media="all" href="${themeResourcePath}/assets/css/jquery.jscrollpane.css" />
	</c:if>

	<c:if test="${pageType == 'LOGIN' || pageType == 'CHECKOUT'}">
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/identificacao.css" />	
	</c:if>
	
	<c:if test="${pageType == 'ORDERCONFIRMATION'}">
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/checkout.css" />
	</c:if>
	
	<c:if test="${pageType == 'SINGLESTEPCHECKOUT'}">
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/checkout.css" />
	</c:if>
	
	<c:if test="${fn:contains(pageType,'CMS')}">
       <link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/institucional.css" />
	   
	   <%-- Blackfriday just on http://www.hering.com.br/store/hering-black-friday-2014 --%>
	   <%-- <link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/hotsites/hering/blackfriday-2014/css/blackfriday-2014.css"> --%>
	</c:if>
	
	<%--<link rel="stylesheet" type="text/css"  media="all" href="${themeResourcePath}/assets/css/pages/hotsite-kids.css" />--%>
</c:if>

<c:if test="${themeName == 'foryou'}">

	<link rel="stylesheet" href="${themeResourcePath}/assets/fonts/ptsans.css">
	<link rel="stylesheet" href="${themeResourcePath}/assets/fonts/font-awesome.css">
	<link rel="stylesheet" href="${themeResourcePath}/assets/fonts/museo-sans.css">
	<link rel="stylesheet" href="${themeResourcePath}/assets/css/style.css">
	
	<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/colorBox.css"/>
	<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/jquery.colorbox-1.3.16.css"/>
	<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/hering.showcase.css"/>

	<c:if test="${pageType == 'HOMEPAGE'}">
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/home.css" />
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/textBoxInfoParagraphComponent.css" />
	</c:if>
	
	<c:if test="${pageType == 'PRODUCT'}">
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/produto.css" />
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/productReferencesComponent.css" />
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/productVariantSelector.css" />
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/productForYouImagens.css" />
	</c:if>
	
	<c:if test="${pageType == 'CART'}">
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/sacola.css" />
	</c:if> 

	<c:if test="${pageType == 'ACCOUNTPAGE'}">
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/sua-conta.css" />
	</c:if>

	<c:if test="${pageType == 'PRODUCTSEARCH' || pageType == 'CATEGORY' || pageType == 'HOMEPAGE'}">
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/busca.css" />
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/heringProductsSearchCategory.css" />
		<link rel="stylesheet" type="text/css"  media="all" href="${themeResourcePath}/assets/css/jquery.jscrollpane.css" />
	</c:if>

	<c:if test="${pageType == 'LOGIN' || pageType == 'CHECKOUT'}">
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/identificacao.css" />	
	</c:if>
	
	<c:if test="${pageType == 'ORDERCONFIRMATION'}">
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/checkout.css" />
	</c:if>
	
	<c:if test="${pageType == 'SINGLESTEPCHECKOUT'}">
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/checkout.css" />
	</c:if>
	
	<c:if test="${fn:contains(pageType,'CMS')}">
       <link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/institucional.css" />
	   
	   <%-- Blackfriday just on http://www.hering.com.br/store/hering-black-friday-2014 --%>
	   <%-- <link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/hotsites/hering/blackfriday-2014/css/blackfriday-2014.css"> --%>
	</c:if>
	
	<%--<link rel="stylesheet" type="text/css"  media="all" href="${themeResourcePath}/assets/css/pages/hotsite-kids.css" />--%>
</c:if>