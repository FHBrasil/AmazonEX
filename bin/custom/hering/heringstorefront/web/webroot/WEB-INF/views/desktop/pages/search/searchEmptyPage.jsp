<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>


<%-- EMPTY SEARCH FOR DZARM STORE --%>
<c:if test="${themeName == 'black'}">	
	
	<template:page pageTitle="${pageTitle}">
		<c:if test="${not empty message}">
			<spring:theme code="${message}"/>
		</c:if>
	
		<div id="globalMessages">
			<common:globalMessages/>
		</div>
		<div class="span-24">
			<cms:pageSlot position="SideContent" var="feature" element="div" class="span-4 side-content-slot cms_disp-img_slot">
				<cms:component component="${feature}"/>
			</cms:pageSlot>
	
			<div class="span-20 right last">
				<div class="item_container_holder">
					<div class="title_holder">
					
						<div class="busca-vazio">
							<h2>Desculpe !</h2>
							<h3>Não encontramos resultados para sua busca.</h3>
							<div class="mensagem-carrinho">
								Por favor revise o texto, ele pode ter sido muito específico. <br />
								Ou se preferir, navegue pelo menu de categorias acima.
							</div>
							
					<div class="item_container">
						<nav:searchSpellingSuggestion spellingSuggestion="${searchPageData.spellingSuggestion}" />
					</div>
							
						<div class="bt-voltar">
							<c:if test="${empty cartData.entries}">
								<a class="button-continue" href="${continueShoppingUrl}">Continuar Comprando</a>
							</c:if>
						</div>
						</div>
	<%-- 					<h2><spring:theme code="search.no.results" text="No Results Found"/></h2> --%>
					</div>
	
	<%-- 				<cms:pageSlot position="MiddleContent" var="comp" element="div" class="item_container"> --%>
	<%-- 					<cms:component component="${comp}"/> --%>
	<%-- 				</cms:pageSlot> --%>
	
	
				</div>
	
				<cms:pageSlot position="BottomContent" var="comp" element="div" class="span-20 cms_disp-img_slot right last">
					<cms:component component="${comp}"/>
				</cms:pageSlot>
			</div>
		</div>
	</template:page>
	
</c:if>

<%-- EMPTY SEARCH FOR HERING STORE --%>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<template:page pageTitle="${pageTitle}">
		<div id="main-wrapper">
			<div class="container">
				<c:if test="${not empty message}">
					<spring:theme code="${message}"/>
				</c:if>		
				<div id="globalMessages">
					<common:globalMessages/>
				</div>			
				<header id="page-header" class="nao-encontrado">				
					<div class="breadcrumb">
						<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
					</div>				
					<h1>Sua busca por "<b>${searchText}</b>" não correspondeu a nenhum produto</h1>
					<h2>Por favor, revise o texto ou se preferir, navegue pelas categorias do site.</h2>			
				</header>
			</div>
			<section class="page vertical-sections">
				<c:if test="${themeName == 'dzarm' || themeName == 'hering'}">				
					<div class="container">
						<section>
							<header>
								<h1>Procure em nossas categorias</h1>
							</header>
							<c:choose>
								<c:when test="${themeName == 'dzarm'}">
									<ul class="categorias-tile"> 
										<li><a href="/store/pt/search?q=:relevance:grupo:Acessórios" title="Acessórios"><img alt="" src="/store/_ui/desktop/theme-dzarm/images/campanha/banner_busca_acessories.jpg"></a></li>
										<li><a href="/store/pt/search?q=:relevance:tag:outono_day_2015_dzarm" title="Day"><img alt="" src="/store/_ui/desktop/theme-dzarm/images/campanha/banner_busca_588x182px_day.jpg"></a></li>
										<li><a href="/store/pt/search?q=:relevance:tag:outono_party_2015_dzarm" title="Party"><img alt="" src="/store/_ui/desktop/theme-dzarm/images/campanha/banner_busca_588x182px_party.jpg"></a></li>
										<li><a href="/store/pt/search?q=:relevance:tag:outono_work_2015_dzarm" title="Work"><img alt="" src="/store/_ui/desktop/theme-dzarm/images/campanha/banner_busca_588x182px_work.jpg"></a></li>
									</ul>
								</c:when>
								<c:when test="${themeName == 'hering'}">
									<ul class="categorias-tile">       
										<li><a href="/store/pt/search?q=:relevance:grupo:Acessórios" title="Acessórios"><img alt="" src="/store/_ui/desktop/theme-hering/images/campanha/Busca-nao-encontrada-ACESSORIOS.jpg"></a></li>
										<li><a href="/store/pt/Categorias/Blusas/c/016?q=:relevance:gender:FEMALE" title="Blusas"><img alt="" src="/store/_ui/desktop/theme-hering/images/campanha/Busca-nao-encontrada-BLUSAS.jpg"></a></li>
										<li><a href="/store/pt/Categorias/Camisas/c/038?q=:relevance:gender:MALE" title="Camisas"><img alt="" src="/store/_ui/desktop/theme-hering/images/campanha/Busca-nao-encontrada-CAMISAS.jpg"></a></li>
										<li><a href="/store/pt/Categorias/Vestidos/c/037?q=:relevance:gender:FEMALE" title="Vestidos"><img alt="" src="/store/_ui/desktop/theme-hering/images/campanha/Busca-nao-encontrada-VESTIDOS.jpg"></a></li>
										<li class="join"></li>
									</ul>
								</c:when>
							</c:choose>												
						</section>
					</div>
				</c:if>
				<c:if test="${themeName == 'foryou'}">
					<section class="banners">
						<div class="container">
							<ul class="categorias-tile half">
								<li><a href="/store/pt/search?q=:relevance:subgrupo:Loungewear" title="Loungewear"><img alt="" src="/store/_ui/desktop/theme-foryou/images/campanha/Banner-busca-maior-lounge.jpg"></a></li>
								<li><a href="/store/pt/search?q=:relevance:subgrupo:Sleepwear" title="Sleepwear"><img alt="" src="/store/_ui/desktop/theme-foryou/images/campanha/Banner-busca-maior-sleep.jpg"></a></li>
							</ul>
							<ul class="categorias-tile third">
								<li><a href="/store/pt/search?q=:relevance:subgrupo:Activewear" title="Activewear"><img alt="" src="/store/_ui/desktop/theme-foryou/images/campanha/Banner-busca-menor-active.jpg"></a></li>
								<li><a href="/store/pt/search?q=:relevance:subgrupo:Beachwear" title="Beachwear"><img alt="" src="/store/_ui/desktop/theme-foryou/images/campanha/Banner-busca-menor-beach.jpg"></a></li>
								<li><a href="/store/pt/search?q=:relevance:subgrupo:Underwear" title="Underwear"><img alt="" src="/store/_ui/desktop/theme-foryou/images/campanha/Banner-busca-menor-under.jpg"></a></li>
							</ul>
						</div>
					</section>
				</c:if>
				<section>
					<div class="container">
						<header>
							<%-- <h1>Recomendados para você</h1> --%>
						</header>
					</div>
					<div class="product-infinite-wrapper no-before">
	            		<div class="product-slider">				
							<nav:searchSpellingSuggestion spellingSuggestion="${searchPageData.spellingSuggestion}" />
						</div>		
					</div>
			</section>
		</div>
	</template:page>
</c:if>