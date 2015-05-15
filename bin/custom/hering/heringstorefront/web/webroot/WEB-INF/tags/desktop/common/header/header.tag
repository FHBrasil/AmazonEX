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
<nav id="search" class="navbar">
    <div class="container">
        <%-- STORE LOGO --%>
        <h1>
            <span id="menu-toggle"> <span></span>
            </span> <a href="/store" title="${siteName}">${siteName}</a>
        </h1>
        <c:if test="${pageType != 'SINGLESTEPCHECKOUT'}">
            <%-- NAVIGATION MENU --%>
            <%-- FILE - navigationbarcomponent.jsp --%>
            <ul class="menu" id="main-menu">
                <cms:pageSlot position="NavigationBar" var="component" element="li">
                    <cms:component component="${component}" />
                </cms:pageSlot>
                <li class="join"></li>
            </ul>
            <div class="actions">
                <ul>
                    <li class="hide-on-desktop"><i class="fa fa-fw fa-search"></i></li>
                    <li class="hide-on-desktop"><i class="fa fa-fw fa-shopping-cart"></i></li>
                    <li class="input-group show-on-desktop siteSearch"><span id="sac"
                        style="display: none !important;"> <i class="fa fa-phone"></i> SAC <b>0800-473114</b>
                            <span> de segunda � sexta das <b>08h00</b> �s <b>18h00</b>
                        </span>
                    </span> <%-- searchboxcomponent.jsp --%> <cms:pageSlot position="SearchBox"
                            var="component">
                            <cms:component component="${component}" />
                        </cms:pageSlot></li>
                </ul>
            </div>
        </c:if>
        <c:if test="${pageType == 'SINGLESTEPCHECKOUT'}">
            <div class="right">
                <h2>Checkout</h2>
                <p>
                    Precisa de ajuda para finalizar? <a href="javascript:void(0)"
                        id="NeoassistOpenLink" class="ai tooltip-link">Clique aqui</a>
                </p>
            </div>
        </c:if>
    </div>
</nav>
