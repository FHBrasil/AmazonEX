<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<template:page pageTitle="${pageTitle}">
    <div id="main-wrapper">
        <div class="container">
            <c:if test="${not empty message}">
                <spring:theme code="${message}" />
            </c:if>
            <div id="globalMessages">
                <common:globalMessages />
            </div>
            <header id="page-header" class="nao-encontrado">
                <div class="breadcrumb">
                    <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
                </div>
                <h1>
                    Sua busca por "<b>${searchText}</b>" n�o correspondeu a nenhum produto
                </h1>
                <h2>Por favor, revise o texto ou se preferir, navegue pelas categorias do site.</h2>
            </header>
        </div>
        <section class="page vertical-sections">
            <div class="container">
                <section>
                    <header>
                        <h1>Procure em nossas categorias</h1>
                    </header>
                    <ul class="categorias-tile">
                        <li><a href="/store/pt/search?q=:relevance:grupo:Acess�rios"
                            title="Acess�rios"><img alt=""
                                src="/store/_ui/desktop/theme-${themeName}/images/campanha/Busca-nao-encontrada-ACESSORIOS.jpg"></a></li>
                        <li><a
                            href="/store/pt/Categorias/Blusas/c/016?q=:relevance:gender:FEMALE"
                            title="Blusas"><img alt=""
                                src="/store/_ui/desktop/theme-${themeName}/images/campanha/Busca-nao-encontrada-BLUSAS.jpg"></a></li>
                        <li><a
                            href="/store/pt/Categorias/Camisas/c/038?q=:relevance:gender:MALE"
                            title="Camisas"><img alt=""
                                src="/store/_ui/desktop/theme-${themeName}/images/campanha/Busca-nao-encontrada-CAMISAS.jpg"></a></li>
                        <li><a
                            href="/store/pt/Categorias/Vestidos/c/037?q=:relevance:gender:FEMALE"
                            title="Vestidos"><img alt=""
                                src="/store/_ui/desktop/theme-${themeName}/images/campanha/Busca-nao-encontrada-VESTIDOS.jpg"></a></li>
                        <li class="join"></li>
                    </ul>
                </section>
            </div>
            <section>
                <div class="container">
                    <header>
                        <%-- <h1>Recomendados para voc�</h1> --%>
                    </header>
                </div>
                <div class="product-infinite-wrapper no-before">
                    <div class="product-slider">
                        <nav:searchSpellingSuggestion
                            spellingSuggestion="${searchPageData.spellingSuggestion}" />
                    </div>
                </div>
            </section>
    </div>
</template:page>
