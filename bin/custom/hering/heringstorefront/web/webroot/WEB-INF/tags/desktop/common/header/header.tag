<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="hideHeaderLinks" required="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/desktop/common/header"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/desktop/user" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>

<%-- DZARM STORE HEADER --%>
<c:if test="${themeName == 'black'}">

	<%-- Test if the UiExperience is currently overriden and we should show the UiExperience prompt --%>
	<c:if test="${uiExperienceOverride and not sessionScope.hideUiExperienceLevelOverridePrompt}">
		<c:url value="/_s/ui-experience?level=" var="clearUiExperienceLevelOverrideUrl"/>
		<c:url value="/_s/ui-experience-level-prompt?hide=true" var="stayOnDesktopStoreUrl"/>
		<div class="backToMobileStore">
			<a href="${clearUiExperienceLevelOverrideUrl}"><span class="greyDot">&lt;</span><spring:theme code="text.swithToMobileStore" /></a>
			<span class="greyDot closeDot"><a href="${stayOnDesktopStoreUrl}">x</a></span>
		</div>
	</c:if>
	
	<div id="header" class="clearfix">
	
		<div>
			<cms:pageSlot position="Teste" var="component">
				<cms:component component="${component}"/>
			</cms:pageSlot>
		</div>
		
		<div class="yCmsComponent siteLogo">
			<div class="cmsimage">
				<a href="http://dzarm.com.br/store">
					<img title="Dzarm." src="/store/_ui/desktop/common/images/logo-dzarm.png" alt="Dzarm." width="166" height="39">
				</a>
			</div>
		</div>
		<div class="headerContent ">
			<ul class="nav clearfix">
				<c:if test="${empty hideHeaderLinks}">
					<c:if test="${uiExperienceOverride}">
						<li class="backToMobileLink">
							<c:url value="/_s/ui-experience?level=" var="backToMobileStoreUrl"/>
							<a href="${backToMobileStoreUrl}"><spring:theme code="text.backToMobileStore"/></a>
						</li>
					</c:if>
					<li><ycommerce:testId code="header_myAccount"><a href="<c:url value="/my-account"/>"><spring:theme code="header.link.account"/></a></ycommerce:testId></li>
				</c:if>
				<c:if test="${not empty link}">
				<cms:pageSlot position="HeaderLinks" var="link">
					<cms:component component="${link}" element="li"/>
				</cms:pageSlot>
				</c:if>
			
				<c:if test="${empty hideHeaderLinks}">
					<li class="central">
						<div class="tooltip-sac">
						<a href="javascript:void(0)" class="ai tooltip-link" ><spring:theme code="general.sac" /></a>
						<div class="tooltip-sac-content">
							<span></span>
							<div><spring:theme code="general.sac.time" /></div>
						</div>
						</div>
					</li>
					<li class="flagship"><a href="<c:url value="/flagship"/>"><spring:theme code="general.flagship" /></a></li>
					<li class="lookbook"><a href="<c:url value="/look-book"/>"><spring:theme code="general.lookbook" /></a></li>
					<li class="storefinder"><a href="<c:url value="/store-finder-google"/>"><spring:theme code="general.find.a.store" /></a></li>
				</c:if>
			</ul>
			
			<div class="chat">
				<%-- Test if the UiExperience is currently overriden and we should show the UiExperience prompt --%>
				<c:if test="${uiExperienceOverride and not sessionScope.hideUiExperienceLevelOverridePrompt}">
					<c:url value="/_s/ui-experience?level=" var="clearUiExperienceLevelOverrideUrl"/>
					<c:url value="/_s/ui-experience-level-prompt?hide=true" var="stayOnDesktopStoreUrl"/>
					<div class="backToMobileStore">
						<a href="${clearUiExperienceLevelOverrideUrl}"><span class="greyDot">&lt;</span><spring:theme code="text.swithToMobileStore" /></a>
						<span class="greyDot closeDot"><a href="${stayOnDesktopStoreUrl}">x</a></span>
					</div>
				</c:if>

				<spring:theme code="checkout.duvidas.text" />
				<a href="javascript:void(0)" class="ai">
					<spring:theme code="checkout.duvidas.link" />
				</a>
			</div>
		</div>
		
		<div class="headerContent secondRow">
			<cms:pageSlot position="SearchBox" var="component">
				<cms:component component="${component}"/>
			</cms:pageSlot>
	
			<ul class="language">
				<li><header:languageSelector languages="${languages}" currentLanguage="${currentLanguage}" /></li>
				<li><header:currencySelector currencies="${currencies}" currentCurrency="${currentCurrency}" /></li>
			</ul>
			<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
				<c:set var="maxNumberChars" value="25"/>
				<c:if test="${fn:length(user.firstName) gt maxNumberChars}">
					<c:set target="${user}" property="firstName" value="${fn:substring(user.firstName, 0, maxNumberChars)}..."/>
				</c:if>
				<div class="logged_in">
					<ycommerce:testId code="header_LoggedUser">
						<div class="over-welcome">
							<spring:theme code="header.welcome" arguments="${user.firstName},${user.lastName}" htmlEscape="true"/>
						</div>
						 
						<div class="over-welcome-content">
							<span></span>
							<a href="<c:url value="/my-account"/>"><spring:theme code="header.link.account"/></a>
							<c:url value="/my-account/orders" var="encodedUrl" />			
							<a href="${encodedUrl}"><spring:theme code="header.account.orders" /></a>
							<c:url value="/my-account/address-book" var="encodedUrlAddress" />
	 						<a href="${encodedUrlAddress}"><spring:theme code="text.account.addressBook"/></a>
						</div>
						
					</ycommerce:testId>
				</div>
				<div class="logging_out"><sec:authorize ifNotGranted="ROLE_ANONYMOUS"><ycommerce:testId code="header_signOut"><a href="<c:url value='/logout'/>"><spring:theme code="header.link.logout"/><span></span></a></ycommerce:testId></sec:authorize></div>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
				<div class="logged_in">
					<ycommerce:testId code="header_LoggedUser">
						<div class="over-welcome">
							<a href="<c:url value="/login"/>"><spring:theme code="header.link.login"/></a>
						</div>
					
						<div class="over-welcome-content over-welc-space">
							<span></span>
							<c:if test="true">
								<user:loginHeader />
							</c:if>
							<a class="for-2" href="<c:url value="/login"/>"><spring:theme code="header.regiter"/></a>
							<a class="for-2" href="<c:url value="/login?forgotPassword=true"/>"><spring:theme code="login.link.forgottenPwd"/></a>
							<c:if test="true">
							<!-- <a href="javascript:void(0)" data-url="<c:url value='/login?forgotpasswort=true'/>" class="password-forgotten-over"><spring:theme code="login.link.forgottenPwd"/></a>  -->
							</c:if>
						</div>
					</ycommerce:testId>
				</div>
			</sec:authorize>
			<c:if test="${pageType != 'CART'}">
				<%-- não é visivel mas duplica os ids nas tags html, causando bugs --%>
				<cms:pageSlot position="MiniCart" var="cart" limit="1">
					<cms:component component="${cart}" element="div" class="miniCart" />
				</cms:pageSlot>
			</c:if>
		</div>	
	</div>	
</c:if>

<%-- HERING STORE HEADER --%>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<nav id="search" class="navbar">
		<div class="container">
			<%-- STORE LOGO --%>
			<h1>
				<span id="menu-toggle">
					<span></span>
				</span>
				<a href="/store" title="${siteName}">${siteName}</a>
			</h1>
			
			<c:if test="${pageType != 'SINGLESTEPCHECKOUT'}">
				
				<%-- NAVIGATION MENU --%>
				<%-- FILE - navigationbarcomponent.jsp --%>
				<ul class="menu" id="main-menu">
					<cms:pageSlot position="NavigationBar" var="component" element="li">
						<cms:component component="${component}"/>
					</cms:pageSlot>
					<li class="join"></li>
				</ul>
				
				<div class="actions">
					<ul>
						<li class="hide-on-desktop"><i class="fa fa-fw fa-search"></i></li>
						<li class="hide-on-desktop"><i class="fa fa-fw fa-shopping-cart"></i></li>
						<li class="input-group show-on-desktop siteSearch">
							
							<span id="sac" style="display: none !important;">
								<i class="fa fa-phone"></i> 
								SAC <b>0800-473114</b> 
								<span>
									de segunda à sexta das 
									<b>08h00</b> às 
									<b>18h00</b>
								</span>
							</span>
							
							<%-- searchboxcomponent.jsp --%>
							<cms:pageSlot position="SearchBox" var="component">
							<cms:component component="${component}"/>
							</cms:pageSlot>
						</li>
					</ul>
				</div>
				
			</c:if>
			
			<c:if test="${pageType == 'SINGLESTEPCHECKOUT'}">
			    <div class= "right">
					<h2>Checkout</h2>
					<p>
					   Precisa de ajuda para finalizar?  
					   <a href="javascript:void(0)" id="NeoassistOpenLink" class="ai tooltip-link">Clique aqui</a>
				   </p> 
				</div>
			</c:if>
		</div>
	</nav>
</c:if>