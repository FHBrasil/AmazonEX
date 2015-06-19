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
    <div class="row bluerow">
        <div class="col-sm-4 text-left hidden-xs">Einkaufen ohne Risiko</div>
        <div class="col-sm-4 col-xs-12 text-center">Kostenloser Versand ab 40 &euro; (D)</div>
        <div class="col-sm-4 text-right hidden-xs">Kundenservice: 089/904&nbsp;750&nbsp;6200</div>
    </div>
</div>
<div class="head141217">
    <div class="row">
        <div class="col-sm-4 hidden-xs">
            <cms:pageSlot position="HeaderLinks" var="link">
                <cms:component component="${link}" element="" />
            </cms:pageSlot>
        </div>
        <div class="col-sm-4 text-center hidden-xs">
            <h1>
                <a href="/" title="${siteName}"><img src="../_ui/desktop/theme-babyartikel/assets/images/logo.svg" alt="${siteName}" height="70" width="220"></a>
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
                <a href="<c:url value="/login"/>">Konto</a>
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
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Suche&hellip;">
                    <span class="input-group-btn">
                        <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
                    </span>
                </div>
            </form>
        </div>
        <!-- Collection of nav links, forms, and other content for toggling -->
        <div id="navbarCollapse" class="collapse navbar-collapse">
            <%--<ul class="nav navbar-nav">
                <li><a href="#">Unterwegs</a></li>
                <li><a href="#">F&uuml;ttern &amp; Pflege</a></li>
                <li><a href="#">Wohnen &amp; Schlafen</a></li>
                <li><a href="#">Kleidung</a></li>
                <li><a href="#">Spielen</a></li>
            </ul>--%>
            <cms:pageSlot position="MainMenu" var="component">
                <cms:component component="${component}" />
            </cms:pageSlot>
            <%-- searchboxcomponent.jsp --%> 
            <cms:pageSlot position="SearchBox" var="component">
                <cms:component component="${component}" />
            </cms:pageSlot>
        </div>
    </nav>
</div>
