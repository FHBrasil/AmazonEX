<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="hideHeaderLinks" required="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/desktop/common/header"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/desktop/user"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<div class="prehead141211">
    <cms:pageSlot position="TopHeaderSlot" var="component">
        <cms:component component="${component}" />
    </cms:pageSlot>
</div>
<div class="head141217">
    <div class="row">
        <div class="col-sm-4 hidden-xs">
            <cms:pageSlot position="HeaderLinks" var="link">
                <cms:component component="${link}" element="" />
            </cms:pageSlot>
        </div>
        <div class="col-sm-4 text-center hidden-xs" style="margin-top: -20px;">
            <h1>
                <a href="/" title="${siteName}"><img src="/_ui/desktop/theme-babyartikel/assets/images/logo.svg" alt="${siteName}" height="70" width="220"></a>
            </h1>
        </div>
        <div class="col-sm-4 text-right">
            <div class="headicons150519">
                <%-- minicartcomponent.jsp --%> 
                <cms:pageSlot position="MiniCart" var="cart" limit="1">
                    <cms:component component="${cart}" element="div" />
                </cms:pageSlot>
                <a class="active" href="#"><span class="babicon babicon-heart"></span><span class="count150519">12</span></a>
                <a class="" href="<c:url value="/login"/>"><span class="babicon babicon-user"></span></a>
            </div>
            <div class="clearfix"></div>
            <div class="headtext150519 text-center hidden-xs">
                <a href="<c:url value="/cart"/>">Warenkorb</a>
                <a href="#">Wunschliste</a>
                <a href="<c:url value="/my-account"/>">Konto</a>
            </div>
                <a href="/" class="home150312 visible-xs"></a>
            </div>
        </div>
    </div>
</div>

<div class="container head141217">
    <nav role="navigation" class="navbar navbar-default">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <form role="search" class="navbar-xs navbar-form visible-xs">
                <cms:pageSlot position="SearchBox" var="component">
                    <cms:component component="${component}" />
                </cms:pageSlot>
            </form>
        </div>
        <!-- Collection of nav links, forms, and other content for toggling -->
        <div id="navbarCollapse" class="collapse navbar-collapse">
            <cms:pageSlot position="NavigationBar" var="component">
                <cms:component component="${component}" />
            </cms:pageSlot>
            <cms:pageSlot position="SearchBox" var="component">
                <cms:component component="${component}" />
            </cms:pageSlot>
        </div>
    </nav>
</div>