<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/desktop/order" %>

<c:if test="${themeName == 'black'}">
<template:page pageTitle="${pageTitle}">

	<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
	</div>
	<div id="globalMessages">
		<common:globalMessages/>
	</div>
	<nav:accountNav/>
	
	<div class="span-20 last customAccount">
		<cms:pageSlot position="TopContent" var="feature" element="div" class="span-20 wide-content-slot cms_disp-img_slot">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
	
		<div class="tile column profile">
			<c:url value="/my-account/profile" var="encodedUrl" />
			<div class="headline"><a href="${encodedUrl}"><spring:theme code="text.account.profile" text="Profile"/></a></div>
			<ul>
				<ycommerce:testId code="myAccount_options_profile_groupbox">
					<li><a href="${encodedUrl}"><spring:theme code="text.account.profile.updatePersonalDetails" text="Update personal details"/></a></li>
					<c:url value="/my-account/update-password" var="encodedUrl" />
					<li><a href="${encodedUrl}"><spring:theme code="text.account.profile.changePassword" text="Change your password"/></a></li>
					<c:url value="/my-account/update-email" var="encodedUrl1" />							
					<li><a href="${encodedUrl1}"><spring:theme code="text.account.profile.updateEmail" text="Change your email"/></a></li>
				</ycommerce:testId>
			</ul>
		</div>
		
		<div class="tile column addressBook">
			<c:url value="/my-account/address-book" var="encodedUrl" />
			<div class="headline"><a href="${encodedUrl}"><spring:theme code="text.account.addressBook" text="Address Book"/></a></div>
			<ul>
				<ycommerce:testId code="myAccount_options_addressBook_groupbox">
					<li><a href="${encodedUrl}"><spring:theme code="text.account.addressBook.manageDeliveryAddresses" text="Manage your delivery addresses"/></a></li>
				</ycommerce:testId>
			</ul>
		</div>
		
		<c:if test="${false}">
		<div class="tile column paymentDetails">
			<c:url value="/my-account/payment-details" var="encodedUrl" />
			<div class="headline"><a href="${encodedUrl}"><spring:theme code="text.account.paymentDetails" text="Payment Details"/></a></div>
			<ul>
				<ycommerce:testId code="myAccount_options_paymentDetails_groupbox">
					<li><a href="${encodedUrl}"><spring:theme code="text.account.paymentDetails.managePaymentDetails" text="Manage your payment details"/></a></li>
				</ycommerce:testId>
			</ul>
		</div>
		</c:if>
		
		<div class="tile column orderHistory">
			<c:url value="/my-account/orders" var="encodedUrl" />
			<div class="headline"><a href="${encodedUrl}"><spring:theme code="text.account.orderHistory" text="Order History"/></a></div>
			<ul>
				<ycommerce:testId code="myAccount_options_orderHistory_groupbox">
					<li><a href="${encodedUrl}"><spring:theme code="text.account.viewOrderHistory" text="View order history"/></a></li>
				</ycommerce:testId>
			</ul>
		</div>
		<div class="tile column lastOrders">
			<c:url value="/my-account/order" var="encodedUrl" />
			<div class="headline"><a href="${encodedUrl}"><spring:theme code="text.account.lastOrders" text="Last Orders"/></a></div>
			<ul>
			    <c:if test="${not empty orderHistoryPreview}"> 
				   <c:forEach items="${orderHistoryPreview}" var="order" begin="0" end="2">
					  <ycommerce:testId code="myAccount_options_orderHistory_groupbox">
						 <li><a href="${encodedUrl}/${order.code}">${order.code}</a></li>
					  </ycommerce:testId>
				   </c:forEach>
				</c:if>
			</ul>
		</div>
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
				<section class="home page with-sidebar">
					<nav:accountNav/>
						<div class="right">
							<section id="welcome">
							 
							<%-- <div id="profile-avatar" class="left">
									<img src="http://placehold.it/124" alt="Imagem de perfil" class="profile-avatar">
									<a href="#" title="alterar foto"><spring:theme code="text.account.changePhoto" text="Change Photo" /></a>
								</div> --%>
								<header class="temporaryWelcome">
									<h2>OLÁ ${customerData.firstName},</h2>

									<p><spring:theme code="text.account.welcomeHering" text="Welcome Hering" /></p>

									<nav id="quick-links">
										<ul>
											<c:url value="/my-account/update-password" var="encodedUrl" />
											<li><a href="${encodedUrl}"><spring:theme code="text.account.profile.changePassword" text="Change your password"/></a></li>
											<c:url value="/my-account/update-email" var="encodedUrl1" />
											<li><a href="${encodedUrl1}"><spring:theme code="text.account.profile.updateEmail" text="Change your email"/></a></li>
											<c:url value="/my-account/address-book" var="encodedUrl" />
											<li><a href="${encodedUrl}"><spring:theme code="text.account.manageShippingAddress" text="Manage your Shipping Address"/></a></li>
											<c:url value="/my-account/orders" var="encodedUrl" />
											<li><a href="${encodedUrl}"><spring:theme code="text.account.orderHistory" text="Order History"/></a></li>
										</ul>
									</nav>
								</header>
							</section>
							<section id="last-orders">
								<header>
									<h2><spring:theme code="text.account.lastOrdersHering" text="Last Orders Hering" /></h2>
								</header>
								<c:if test="${not empty orderHistoryPreview}">
								<table>
									<thead>
										<tr>
											<th><spring:theme code="text.account.lastOrderNumber" text="Last Order Number"/></th>
											<th><spring:theme code="text.account.lastOrderDate" text="Last Order Date"/></th>
											<th><spring:theme code="text.account.lastOrderTotalPrice" text="Last Order Total Price"/></th>
											<th>Forma de Pagamento</th>
											<th><spring:theme code="text.account.lastOrderStatus" text="Last Order Status"/></th>
										</tr>
									</thead>
									<tbody>	
										<c:forEach items="${orderHistoryPreview}" var="order" begin="0" end="2">
											<c:url value="/my-account/order/${order.code}" var="myAccountOrderDetailsUrl"/>
											<tr>
												<td><big><a href="${myAccountOrderDetailsUrl}">${order.code}</a></big></td>
												<td><fmt:formatDate value="${order.placed}" dateStyle="short" type="date"/><br>
													às <fmt:formatDate value="${order.placed}" timeStyle="short" type="time"/>
												</td>
												<td><big>${order.total.formattedValue}</big></td>
												<td>${order.paymentMode}</td>
												<td><spring:theme code="text.account.order.status.display.${order.statusDisplay}"/><br> 
												<c:if test="${order.deliveryMode == 'TFA'}">
													<spring:theme code="text.account.lastOrderTracking" text="Last Order Tracking"/><a class="" href="http://www.transfolha.com.br/outros/pesquisahttpentrega.asp?scliente=000539&schave=6b7e676029&spesquisa=generico&sdado=${order.trackingID}" target="_blank">
														${order.trackingID}
													</a>
												</c:if>
												<c:if test="${order.deliveryMode == 'CORREIOS'}">
													<spring:theme code="text.account.lastOrderTracking" text="Last Order Tracking"/><a class="" href="http://websro.correios.com.br/sro_bin/txect01$.querylist?p_lingua=001&p_tipo=001&p_cod_uni=${order.trackingID}" target="_blank">
														${order.trackingID}
													</a>
												</c:if>
												<c:if test="${order.deliveryMode == 'JTT'}">
													<spring:theme code="text.account.lastOrderTracking" text="Last Order Tracking"/><a class="" href="http://www.jttlog.com.br/consulta_nf.php?nf=${order.trackingID}" target="_blank">
													<%-- ${order.trackingID} --%>
													</a>
												</c:if></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								</c:if>
								<c:if test="${empty orderHistoryPreview}">
									Não há pedidos.
								</c:if>
							</section>
							<section id="general-info">
								<%-- 
								<section id="current-accounts" class="left">
									<header>
										<h2><spring:theme code="text.account.accountCurrentInShop" text="Account Current in Shop"/></h2>
									</header>
									<table class="acc-historical">
										<tbody>
											<%-- add bonusPoints 
										</tbody>
									</table>
								</section> --%>
								<section id="account-addresses" class="right">
									<header>
										<h2>ENDEREÇOS CADASTRADOS</h2>
									</header>
										<c:if test="${not empty addressData}">
											<c:choose>
												<c:when test="${not empty addressData}">
													<c:forEach items="${addressData}" var="address">
														<ul>
															<li>
																<h3>${address.type}
																<ycommerce:testId code="addressBook_addressOptions_label">
																	<ycommerce:testId code="addressBook_editAddress_button">
																		<a class="adressButtonEditAddress"  href="my-account/edit-address/${address.id}">
																			<spring:theme code="text.account.addressEdit" text="Address Edit"/>
																		</a>
																	</ycommerce:testId>
																</ycommerce:testId>									
																</h3>
																<p><strong>Nome:</strong>&nbsp; ${fn:escapeXml(address.firstName)}&nbsp; ${fn:escapeXml(address.lastName)}</p>
																<p><c:if test="${not empty address.receiver}"><strong>Destinatário:</strong>&nbsp; ${fn:escapeXml(address.receiver)}</c:if></p>
																<p><strong>Telefone:</strong>&nbsp; (${fn:escapeXml(address.dddPhone)})${fn:escapeXml(address.phone)}</p>
																<p><c:if test="${not empty address.celPhone}"><strong>Tel Celular:</strong>&nbsp; (${fn:escapeXml(address.dddCelPhone)})${fn:escapeXml(address.celPhone)}</c:if></p>
																<p><strong>Endereço:&nbsp;</strong>
																${fn:escapeXml(address.line1)}, ${fn:escapeXml(address.number)} - ${fn:escapeXml(address.complement)} &nbsp; ${fn:escapeXml(address.district)}<br/>
 																${fn:escapeXml(address.town)}&nbsp;<c:if test="${not empty address.region.name}">-&nbsp;${fn:escapeXml(address.region.isocodeShort)}</c:if> - ${fn:escapeXml(address.postalCode)}</p>
 																<p><c:if test="${not empty address.reference}"><strong>Referência:</strong>&nbsp; ${fn:escapeXml(address.reference)}</c:if><p>
															</li>
														</ul>
													</c:forEach>
												</c:when>
											</c:choose>
										</c:if>
										<c:if test="${empty addressData}">
											Não há endereços cadastrados.
										</c:if>	
								</section>
							</section>
						</div>
					</section>
				</div>
			</div>
		</template:page>
</c:if>