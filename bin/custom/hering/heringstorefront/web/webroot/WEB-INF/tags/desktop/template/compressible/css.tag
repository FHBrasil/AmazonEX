<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="//fonts.googleapis.com/css?family=PT+Sans:400,700,400italic,700italic">
<link rel="stylesheet" href="${commonResourcePath}/assets/fonts/font-awesome.css">
<link rel="stylesheet" href="${commonResourcePath}/assets/fonts/museo-sans.css">
<link rel="stylesheet" href="${commonResourcePath}/assets/fonts/steelfish.css">
<%-- <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/assets/css/reset.css" />--%>
<%--<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/assets/css/base.css" />
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/assets/css/header.css" />
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/assets/css/footer.css" />--%>
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/assets/css/products.css" />
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/assets/css/sliders.css" />
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/assets/css/miniCart.css" />
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/assets/js/fancybox/jquery.fancybox.css" />
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/colorBox.css" />
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/jquery.colorbox-1.3.16.css" />
<c:if test="${pageType == 'HOMEPAGE'}">
    <%--<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/assets/css/pages/home.css" />--%>
    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/assets/css/textBoxInfoParagraphComponent.css" />
</c:if>
<c:if test="${pageType == 'PRODUCT'}">
    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/assets/css/pages/produto.css" />
    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/assets/css/pages/productReferencesComponent.css" />
    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/assets/css/productVariantSelector.css" />
</c:if>
<c:if test="${pageType == 'CART'}">
    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/assets/css/pages/sacola.css" />
</c:if>
<c:if test="${pageType == 'ACCOUNTPAGE'}">
    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/assets/css/pages/sua-conta.css" />
</c:if>
<c:if test="${pageType == 'PRODUCTSEARCH' || pageType == 'CATEGORY' || pageType == 'HOMEPAGE'}">
    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/assets/css/pages/busca.css" />
    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/assets/css/pages/heringProductsSearchCategory.css" />
    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/assets/css/jquery.jscrollpane.css" />
</c:if>
<c:if test="${pageType == 'LOGIN' || pageType == 'CHECKOUT'}">
    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/assets/css/pages/identificacao.css" />
</c:if>
<c:if test="${pageType == 'ORDERCONFIRMATION'}">
    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/assets/css/pages/checkout.css" />
</c:if>
<c:if test="${pageType == 'SINGLESTEPCHECKOUT'}">
    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/assets/css/pages/checkout.css" />
</c:if>
<c:if test="${fn:contains(pageType,'CMS')}">
    <link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/assets/css/pages/institucional.css" />
</c:if>
<%--<link rel="stylesheet" type="text/css"  media="all" href="${commonResourcePath}/assets/css/pages/hotsite-kids.css" />--%>

<%-- Babyartikel Customs --%>
<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/css/customs.css" />
<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/fonts/babyartikel-fonts.css" />
