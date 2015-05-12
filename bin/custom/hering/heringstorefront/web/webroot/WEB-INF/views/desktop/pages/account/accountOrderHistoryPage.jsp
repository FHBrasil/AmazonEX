<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/desktop/order" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:if test="${themeName == 'black'}">
<template:page pageTitle="${pageTitle}">

	<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
	</div>
	<div id="globalMessages">
		<common:globalMessages/>
	</div>
	<nav:accountNav selected="orders" />
	
	
	
	<div class="column accountContentPane clearfix orderList">
		<div class="headline"><spring:theme code="text.account.orderHistory" text="Order History"/></div>
		<c:if test="${not empty searchPageData.results}">
			<nav:pagination top="true"  supportShowPaged="${isShowPageAllowed}"  supportShowAll="${isShowAllAllowed}"  searchPageData="${searchPageData}" searchUrl="/my-account/orders?sort=${searchPageData.pagination.sort}" msgKey="text.account.orderHistory.page"  numberPagesShown="${numberPagesShown}"/>

			<table class="orderListTable">
				<thead>
					<tr>
						<th id="header1"><spring:theme code="text.account.orderHistory.orderNumber" text="Order Number"/></th>
						<th id="header2"><spring:theme code="text.account.orderHistory.orderStatus" text="Order Status"/></th>
						<th id="header3"><spring:theme code="text.account.orderHistory.datePlaced" text="Date Placed"/></th>
						<th id="header4"><spring:theme code="text.account.orderHistory.total" text="Total"/></th>
						<th id="header5"><spring:theme code="text.account.orderHistory.actions" text="Actions"/></th>
						<th id="header5"><spring:theme code="text.account.orderHistory.trackingid" text="Tracking Code"/></th>
						<th id="header5"><spring:theme code="text.account.orderHistory.chaveNfe" text="Chave Nfe"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${searchPageData.results}" var="order">

						<c:url value="/my-account/order/${order.code}" var="myAccountOrderDetailsUrl"/>

						<tr>
							<td headers="header1">
								<ycommerce:testId code="orderHistory_orderNumber_link">
									<a href="${myAccountOrderDetailsUrl}">${order.code}</a>
								</ycommerce:testId>
							</td>
							<td headers="header2">
								<ycommerce:testId code="orderHistory_orderStatus_label">
									<p><spring:theme code="text.account.order.status.display.${order.statusDisplay}"/></p>
								</ycommerce:testId>
							</td>
							<td headers="header3">
								<ycommerce:testId code="orderHistory_orderDate_label">
									<p><fmt:formatDate value="${order.placed}" dateStyle="long" timeStyle="short" type="both"/></p>
								</ycommerce:testId>
							</td>
							<td headers="header4">
								<ycommerce:testId code="orderHistory_Total_links">
									<p>${order.total.formattedValue}</p>
								</ycommerce:testId>
							</td>
							<td headers="header5">
								<ycommerce:testId code="orderHistory_Actions_links">
									<p><a href="${myAccountOrderDetailsUrl}"><spring:theme code="text.view" text="View"/></a></p>
								</ycommerce:testId>
							</td>
							<td headers="header6">
								<ycommerce:testId code="orderHistory_orderNumber_link">
									<c:if test="${order.deliveryMode == 'TFA'}">
										<a class="" href="http://www.transfolha.com.br/outros/pesquisahttpentrega.asp?scliente=000539&schave=6b7e676029&spesquisa=generico&sdado=${order.trackingID}" target="_blank">
											${order.trackingID}
										</a>
									</c:if>
									<c:if test="${order.deliveryMode == 'CORREIOS'}">
										<a class="" href="http://websro.correios.com.br/sro_bin/txect01$.querylist?p_lingua=001&p_tipo=001&p_cod_uni=${order.trackingID}" target="_blank">
											${order.trackingID}
										</a>
									</c:if>
									<c:if test="${order.deliveryMode == 'JTT'}">
										<a class="" href="http://www.jttlog.com.br/consulta_nf.php?nf=${order.trackingID}" target="_blank">
<%-- 											<p>${order.trackingID}</p> --%>
										</a>
									</c:if>
								</ycommerce:testId>
							</td>
							<td headers="header6">
								<ycommerce:testId code="orderHistory_orderNumber_link">
									<c:if test="${not empty order.chaveNfe}">
										<p>
											<a target="_blank" href="http://www.nfe.fazenda.gov.br/portal/consulta.aspx?tipoConsulta=completa&tipoConteudo=XbSeqxE8pl8=">
												 Visualizar NFe
											</a>
										</p>
									</c:if>
								</ycommerce:testId>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<nav:pagination top="false" supportShowPaged="${isShowPageAllowed}"  supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/my-account/orders?sort=${searchPageData.pagination.sort}" msgKey="text.account.orderHistory.page"  numberPagesShown="${numberPagesShown}"/>

		</c:if>
		<c:if test="${empty searchPageData.results}">
			<p><spring:theme code="text.account.orderHistory.noOrders" text="You have no orders"/></p>
		</c:if>
	</div>
	

</template:page>
</c:if>

<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<template:page pageTitle="${pageTitle}">
		<div id="main-wrapper">
			<div class="container">
				<header id="page-header">
					<h1><spring:theme code="text.account.yourAccount" text="Your Account" /></h1>
					<div class="breadcrumb">
						<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
					</div>
				</header>
				<section class="orders page with-sidebar">
					<nav:accountNav/>
					<div class="right">
						<section id="orders-list">
							<header>
								<h2><spring:theme code="text.account.orderHistory.myOrders" text="My Orders"/></h2>
								<div id="search-bar">
									<form:form class="pesquisa-numero">
										<label for="pesquisa-numero">
											<spring:theme code="text.account.orderHistory.searchByNumber" text="Search By Number"/><br>
											<input type="text" id="pesquisa-numero" name="orderSearch" class="required">
											<button type="submit" class="btn btn-ok">ok</button>									
										</label>
									</form:form>									
								</div>
							</header>

							<table>
								<colgroup>
									<col class="col-item-toggle-button">
								</colgroup>
								<thead>
									<tr>
										<th><spring:theme code="text.account.orderHistory.orderNumber" text="Number of Order"/></th>
										<th><spring:theme code="text.account.orderHistory.orderDate" text="Date of Order"/></th>
										<th><spring:theme code="text.account.orderHistory.orderTotalPrice" text="Total Price"/></th>
										<th><spring:theme code="text.account.formOfPayment" text="Form of Payment"/></th>
										<th><spring:theme code="text.account.orderHistory.orderStatus" text="Status"/></th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${not empty searchPageData.results && empty orderFind}">
										<nav:pagination 
											top="true"  
											supportShowPaged="${isShowPageAllowed}"  
											supportShowAll="${isShowAllAllowed}"  
											searchPageData="${searchPageData}" 
											searchUrl="/my-account/orders?sort=${searchPageData.pagination.sort}" 
											msgKey="text.account.orderHistory.page" 
											orderHistory="true" 
											numberPagesShown="${numberPagesShown}"/>
										
										<c:forEach items="${searchPageData.results}" var="order">
											<c:url value="/my-account/order/${order.code}" var="myAccountOrderDetailsUrl"/>
											<tr class="has-sub-item" data-sub-item="${order.code}">
												<td>
													<a href="#" class="sub-item-toggle"></a>
													<big>${order.code}</big>
												</td>
												<td>
													<fmt:formatDate value="${order.placed}" dateStyle="short" type="date"/><br>
													às <fmt:formatDate value="${order.placed}" timeStyle="short" type="time"/>
												</td>
												<td><big>${order.total.formattedValue}</big></td>
												<c:forEach items="${orderData}" var="orderDataCustomer">
													<c:if test="${order.code == orderDataCustomer.code}">
														<td>${order.paymentMode}
															<%-- <order:paymentDetailsItem order="${orderDataCustomer}"/> --%>																	
														</td>
													</c:if>
												</c:forEach>
												<td><spring:theme code="text.account.order.status.display.${order.statusDisplay}"/><br> 
												<c:if test="${order.deliveryMode == 'TFA'}">
													<spring:theme code="text.account.orderHistory.orderTracking" text="Tracking"/><a class="tracking" href="http://www.transfolha.com.br/outros/pesquisahttpentrega.asp?scliente=000539&schave=6b7e676029&spesquisa=generico&sdado=${order.trackingID}" target="_blank">
													${order.trackingID}
													</a>
												</c:if>
												<c:if test="${order.deliveryMode == 'CORREIOS'}">
													<spring:theme code="text.account.orderHistory.orderTracking" text="Tracking"/><a class="tracking" href="http://websro.correios.com.br/sro_bin/txect01$.querylist?p_lingua=001&p_tipo=001&p_cod_uni=${order.trackingID}" target="_blank">
													${order.trackingID}
													</a>
												</c:if>
												<c:if test="${order.deliveryMode == 'JTT'}">
													<spring:theme code="text.account.orderHistory.orderTracking" text="Tracking"/><a class="tracking" href="http://www.jttlog.com.br/consulta_nf.php?nf=${order.trackingID}" target="_blank">
													<%-- ${order.trackingID} --%>
													</a>
												</c:if></td>
											</tr>
											<c:forEach items="${orderData}" var="orderDataCustomer">
												<c:if test="${order.code == orderDataCustomer.code}">
													<order:orderUnconsignedEntries order="${orderDataCustomer}" searchPageData="${order}" page="historyPage"/>				
												</c:if>			
											</c:forEach>
										</c:forEach>
									</c:if>
									<c:if test="${empty searchPageData.results && not empty orderFind && orderFind != 'notFound'}">
										<c:url value="/my-account/order/${orderFind.code}" var="myAccountOrderDetailsUrl"/>
										<tr class="has-sub-item" data-sub-item="${orderFind.code}">
											<td>
												<a href="#" class="sub-item-toggle"></a>
												<big>${orderFind.code}</big>
											</td>
											<td>
												<fmt:formatDate value="${orderFind.placed}" dateStyle="short" type="date"/><br>
												às <fmt:formatDate value="${orderFind.placed}" timeStyle="short" type="time"/>
											</td>
											<td><big>${orderFind.total.formattedValue}</big></td>
											<c:forEach items="${orderData}" var="orderDataCustomer">
												<c:if test="${orderFind.code == orderDataCustomer.code}">
													<td>${orderFind.paymentMode}
														<%-- <order:paymentDetailsItem order="${orderDataCustomer}"/> --%>																	
													</td>
												</c:if>
											</c:forEach>
											<td><spring:theme code="text.account.order.status.display.${orderFind.statusDisplay}"/><br> 
											<c:if test="${orderFind.deliveryMode == 'TFA'}">
												<spring:theme code="text.account.orderHistory.orderTracking" text="Tracking"/><a class="tracking" href="http://www.transfolha.com.br/outros/pesquisahttpentrega.asp?scliente=000539&schave=6b7e676029&spesquisa=generico&sdado=${orderFind.trackingID}" target="_blank">
												${orderFind.trackingID}
												</a>
											</c:if>
											<c:if test="${orderFind.deliveryMode == 'CORREIOS'}">
												<spring:theme code="text.account.orderHistory.orderTracking" text="Tracking"/><a class="tracking" href="http://websro.correios.com.br/sro_bin/txect01$.querylist?p_lingua=001&p_tipo=001&p_cod_uni=${orderFind.trackingID}" target="_blank">
												${orderFind.trackingID}
												</a>
											</c:if>
											<c:if test="${orderFind.deliveryMode == 'JTT'}">
												<spring:theme code="text.account.orderHistory.orderTracking" text="Tracking"/><a class="tracking" href="http://www.jttlog.com.br/consulta_nf.php?nf=${orderFind.trackingID}" target="_blank">
												<%-- ${orderFind.trackingID} --%>
												</a>
											</c:if></td>
										</tr>
										<c:forEach items="${orderData}" var="orderDataCustomer">
											<c:if test="${orderFind.code == orderDataCustomer.code}">
												<order:orderUnconsignedEntries order="${orderDataCustomer}" searchPageData="${orderFind}" page="historyPage"/>				
											</c:if>			
										</c:forEach>
									</c:if>						
								</tbody>
							</table>
							<c:if test="${empty searchPageData.results && orderFind == 'notFound'}">
								<common:globalMessages/>
							</c:if>
							<c:if test="${not empty searchPageData.results && empty orderFind}">
								<div style="margin:3% 0 0 45%;">
									<nav:pagination top="false" supportShowPaged="${isShowPageAllowed}"  supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/my-account/orders?sort=${searchPageData.pagination.sort}" msgKey="text.account.orderHistory.page"  orderHistory="true" numberPagesShown="${numberPagesShown}"/>
								</div>
							</c:if>
						</section>
					</div>
				</section>
			</div>
		</div>
	</template:page>
</c:if>