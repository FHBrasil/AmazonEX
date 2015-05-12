<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>

<c:if test="${themeName == 'black'}">
<template:page pageTitle="${pageTitle}">

	<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
	</div>
	<div id="globalMessages">
		<common:globalMessages/>
	</div>
	<nav:accountNav selected="address-book"/>

	<div class="column accountContentPane clearfix">
		<div class="headline">
			<spring:theme code="text.account.addressBook" text="Address Book"/>
		</div>
		
		<ycommerce:testId code="addressBook_addNewAddress_button">
			<div class="newAddressButton">
				<a href="add-address" class="button positive">
					<spring:theme code="text.account.addressBook.addAddress" text="Add new address"/>
				</a>
			</div>
		</ycommerce:testId>

		<c:choose>
			<c:when test="${not empty addressData}">
				<c:forEach items="${addressData}" var="address">
					<div class="addressItem">
										<c:if test="${address.defaultAddress}">
									<ycommerce:testId code="addressBook_isDefault_label">
										<ul class="adressButtonThisAddress">
											<li>
												<spring:theme code="text.default.address" text="Default" />
											</li>
										</ul>
									</ycommerce:testId>
								</c:if>
								<c:if test="${address.billingAddress}">
									<ycommerce:testId code="addressBook_isDefault_label">
										<span class="adressButtonThisAddress"><spring:theme code="text.billingAddress" text="Bill"/></span>
									</ycommerce:testId>
								</c:if>
								

								
									<c:if test="${not address.defaultAddress}">
										<ul class="setDefaultAddress">
											<li>
												<ycommerce:testId code="addressBook_isDefault_button">
													<a class="button" href="set-default-address/${address.id}">
														<spring:theme code="text.setDefault" text="Set as default"/>
													</a>
												</ycommerce:testId>
											</li>
										</ul>
									</c:if>
									
									<c:if test="${not address.billingAddress}">
										<ul class="setDefaultBillingAddress">
											<li>
												<ycommerce:testId code="addressBook_isDefault_button">
													<a class="button" href="set-default-billing-address/${address.id}">
														<spring:theme code="text.setBilling" text="Set as billing"/>
													</a>
												</ycommerce:testId>
											</li>
										</ul>
									</c:if>
								
					
					
						<ycommerce:testId code="addressBook_address_label">
								
								 <ul class="informacoes-usuario"> 
									<li><strong>Nome:</strong>&nbsp; ${fn:escapeXml(address.firstName)}&nbsp; ${fn:escapeXml(address.lastName)}</li>
									<li><c:if test="${not empty address.receiver}"><strong>Destinatário:</strong>&nbsp; ${fn:escapeXml(address.receiver)}</c:if></li>
								 </ul>
								 
								 <ul class="contato-usuario"> 
									<li><strong>Telefone:</strong>&nbsp; (${fn:escapeXml(address.dddPhone)})${fn:escapeXml(address.phone)}</li>
									<li><c:if test="${not empty address.celPhone}"><strong>Tel Celular:</strong>&nbsp; (${fn:escapeXml(address.dddCelPhone)})${fn:escapeXml(address.celPhone)}</c:if></li>
								</ul>
								
								 <ul class="endereco-usuario"> 
									<li><strong>Endereço:</strong>&nbsp; ${fn:escapeXml(address.line1)}&nbsp; ${fn:escapeXml(address.number)}</li>
									<li><strong>Complemento:</strong>&nbsp; ${fn:escapeXml(address.complement)}</li>
									<li><c:if test="${not empty address.reference}"><strong>Referência:</strong>&nbsp; ${fn:escapeXml(address.reference)}</c:if></li>
									<li><strong>Cep:</strong>&nbsp; ${fn:escapeXml(address.postalCode)} - ${fn:escapeXml(address.town)}<c:if test="${not empty address.region.name}">-${fn:escapeXml(address.region.isocodeShort)}</c:if></li>
									<li><strong>Bairro:</strong>&nbsp; ${fn:escapeXml(address.district)}</li>
								</ul>
						</ycommerce:testId>
						<div class="buttons">
							<ycommerce:testId code="addressBook_addressOptions_label">
	
	
								<ycommerce:testId code="addressBook_editAddress_button">
									<a class="adressButtonEditAddress" href="edit-address/${address.id}">
										<spring:theme code="text.edit" text="Edit"/>
									</a>
								</ycommerce:testId>
								<c:if test="${fn:length(addressData) > 1}">
									<ycommerce:testId code="addressBook_removeAddress_button">
										<a class="adressButtonEraseAddress" data-address-id="${address.id}" href="remove-address/${address.id}"/>
											<spring:theme code="text.remove" text="Remove"/>
										</a>
									</ycommerce:testId>
								</c:if>

							</ycommerce:testId>
						</div>


		
					<c:if test="${fn:length(addressData) > 1}">
						<div style="display:none">
							<div id="popup_confirm_address_removal_${address.id}">
								<div class="addressItem">
									<ul>
										<li>${fn:escapeXml(address.title)}&nbsp;${fn:escapeXml(address.firstName)}&nbsp;${fn:escapeXml(address.lastName)}</li>
										<li>${fn:escapeXml(address.line1)}</li>
										<li>${fn:escapeXml(address.line2)}</li>
										<li>${fn:escapeXml(address.town)}</li>
										<li>${fn:escapeXml(address.region.name)}</li>
										<li>${fn:escapeXml(address.postalCode)}</li>
										<li>${fn:escapeXml(address.country.name)}</li>
									</ul>
	
									<spring:theme code="text.adress.remove.confirmation" text="Are you sure you would like to delete this address?"/></a>
	
									<div class="buttons">
										<a class="adressButtonEraseAddress" data-address-id="${address.id}" href="remove-address/${address.id}">
											<spring:theme code="text.yes" text="Yes"/>
										</a>
										<a class="button closeColorBox" data-address-id="${address.id}">
											<spring:theme code="text.no" text="No"/></a>
									</div>
								</div>
							</div>
						</div>
					</c:if>
					</div>
				</c:forEach>

			</c:when>
			<c:otherwise>
				<p class="emptyMessage">
					<spring:theme code="text.account.addressBook.noSavedAddresses"/>
				</p>
			</c:otherwise>
		</c:choose>




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
				<section class="account-edit-addresses page with-sidebar">
					<nav:accountNav/>
					<div class="right">
						<header>
								<h2>ENDEREÇOS CADASTRADOS</h2>
						</header>
						<section id="account-addresses">
						<div id="globalMessages">
							<common:globalMessages/>
						</div>
						<ycommerce:testId code="addressBook_addNewAddress_button">
							<div class="newAddressButton">
								<a href="add-address" class="btn btn-enderecos">
								<spring:theme code="text.account.addressBook.addAddress" text="Add new address"/>
							</a>
							</div>
						</ycommerce:testId>
							<c:choose>
								<c:when test="${not empty addressData}">
									<ul>
										<c:forEach items="${addressData}" var="address">
											<li>
												<c:if test="${address.defaultAddress}">
													<ycommerce:testId code="addressBook_isDefault_label">
														<h3>Endereço padrão</h3>
													</ycommerce:testId>
												</c:if>
												<c:if test="${address.billingAddress}">
													<ycommerce:testId code="addressBook_isDefault_label">
														<h3>Endereço de cobrança</h3>
													</ycommerce:testId>
												</c:if>												
												<h3>${address.type}
												<ycommerce:testId code="addressBook_addressOptions_label">
													<ycommerce:testId code="addressBook_editAddress_button">
														<a class="adressButtonEditAddress"  href="edit-address/${address.id}">
															<spring:theme code="text.account.addressEdit" text="Address Edit"/>
														</a>
													</ycommerce:testId>
													<c:if test="${fn:length(addressData) > 1}">
														<ycommerce:testId code="addressBook_removeAddress_button">
															<a class="adressButtonEraseAddress" title="Remover endereço" data-address-id="${address.id}" href="remove-address/${address.id}">
																remover
															</a>
														</ycommerce:testId>
													</c:if>
												</ycommerce:testId>									
												</h3>												
												<ycommerce:testId code="addressBook_address_label">
												<p><strong>Nome:</strong>&nbsp; ${fn:escapeXml(address.firstName)}&nbsp; ${fn:escapeXml(address.lastName)}</p>
												<p><c:if test="${not empty address.receiver}"><strong>Destinatário:</strong>&nbsp; ${fn:escapeXml(address.receiver)}</c:if></p>
												<p><strong>Telefone:</strong>&nbsp; (${fn:escapeXml(address.dddPhone)})${fn:escapeXml(address.phone)}</p>
												<p><c:if test="${not empty address.celPhone}"><strong>Tel Celular:</strong>&nbsp; (${fn:escapeXml(address.dddCelPhone)})${fn:escapeXml(address.celPhone)}</c:if></p>
												<p><strong>Endereço:&nbsp;</strong>
												${fn:escapeXml(address.line1)}, ${fn:escapeXml(address.number)} - ${fn:escapeXml(address.complement)} &nbsp; ${fn:escapeXml(address.district)}<br/>
 												${fn:escapeXml(address.town)}&nbsp;<c:if test="${not empty address.region.name}">-&nbsp;${fn:escapeXml(address.region.isocodeShort)}</c:if> - ${fn:escapeXml(address.postalCode)}</p>
 												<p><c:if test="${not empty address.reference}"><strong>Referência:</strong>&nbsp; ${fn:escapeXml(address.reference)}</c:if></p>
												</ycommerce:testId>
												<div class="btns">
													<c:if test="${not address.defaultAddress}">
														<ycommerce:testId code="addressBook_isDefault_button">
															<a class="btn btn-enderecos" style="font-size: 13px;" href="set-default-address/${address.id}">
																Definir como padrão
															</a>
														</ycommerce:testId>													
													</c:if>
													<c:if test="${not address.billingAddress}">												
														<ycommerce:testId code="addressBook_isDefault_button">
															<a class="btn btn-enderecos" style="font-size: 13px;" href="set-default-billing-address/${address.id}">
																Definir como endereço de cobrança
															</a>
													</ycommerce:testId>											
													</c:if>
												</div>
											</li>
										</c:forEach>
									</ul>
								</c:when>
								<c:otherwise>
									<p class="emptyMessage">
										<spring:theme code="text.account.addressBook.noSavedAddresses"/>
									</p>
								</c:otherwise>
							</c:choose>	
						</section>
					</div>
				</section>
			</div>
		</div>
	</template:page>
</c:if>