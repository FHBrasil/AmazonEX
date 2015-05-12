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
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>

<c:if test="${themeName == 'black'}">
	<template:page pageTitle="${pageTitle}">

	<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
	</div>

	<div id="globalMessages">
		<common:globalMessages/>
	</div>
	<nav:accountNav selected="profile" />
	

		<div class="column accountContentPane clearfix">
			<div class="headline"><spring:theme code="text.account.profile" text="Profile"/></div>
			<form:form action="update-profile" method="post" commandName="updateProfileForm">
				<table class="account-profile-data">
					<tr>
						<td>
							<strong>
								<spring:theme code="profile.firstName" text="First name"/>: 
							</strong>
						</td>
						<td>${fn:escapeXml(customerData.firstName)}</td>
					</tr>
					<tr>
						<td>
							<strong>
								<spring:theme code="profile.lastName" text="Last name"/>: 
							</strong>
						</td>
						<td>${fn:escapeXml(customerData.lastName)}</td>
					</tr>
					<tr>
						<td>
							<strong>
								<spring:theme code="profile.gender" text="Gender"/>: 
							</strong>
						</td>
						<td><spring:theme code="profile.gender.${fn:escapeXml(customerData.gender)}" /></td>
					</tr>
					<tr>
						<td>
							<strong>
								Data de nascimento : 
							</strong>
						</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy" value="${customerData.birthday}" /></td>
					</tr>
									
					<tr>
						<td>
							<strong>
								<spring:theme code="profile.cpfcnpj" text="CPF"/>:
							</strong> 
						</td>
						<td>${fn:escapeXml(customerData.cpfcnpj)}</td>
					</tr> 
					
<!-- 					<tr> -->
<!-- 						<td> -->
<!-- 							<strong> -->
<%-- 								<spring:theme code="profile.rgIe" text="Ie"/>: --%>
<!-- 							</strong>  -->
<!-- 						</td> -->
<%-- 						<td>${fn:escapeXml(customerData.rgIe)}</td> --%>
<!-- 					</tr>  -->
					
<!-- 					<tr> -->
<!-- 						<td> -->
<!-- 							<strong> -->
<%-- 								<spring:theme code="profile.ufIe" text="Uf-Ie"/>: --%>
<!-- 							</strong>  -->
<!-- 						</td> -->
<%-- 						<td>${fn:substring(customerData.ufIe, 3, 5)}</td> --%>
<!-- 					</tr>  -->
					
					<tr>
						<td>
							<strong>
								<spring:theme code="profile.email" text="E-mail"/>:
							</strong> 
							</td>
						<td>${fn:escapeXml(customerData.displayUid)}</td>
					</tr>
					<tr>
						<td>
							<strong>
								<spring:theme code="profile.newsletter" text="Receber Newsletter"/>: 
							</strong>
						</td>
						<td><input type="checkbox" ${fn:escapeXml(customerData.newsletterSubscription.receive?'checked':'')} disabled="disabled"></td>
					</tr>
				</table>
				</form:form>
				
				<a class="button" href="update-password"><spring:theme code="text.account.profile.changePassword" text="Change password"/></a>
				<a class="button" href="update-profile"><spring:theme code="text.account.profile.updatePersonalDetails" text="Update personal details"/></a>
				<a class="button" href="update-email"><spring:theme code="text.account.profile.updateEmail" text="Update email"/></a>

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
						<section id="acc-profile">
							<header>
								<h2><spring:theme code="text.account.myProfile" text="My Profile" /></h2>
							</header>
							<form:form action="update-profile" method="post" commandName="updateProfileForm">
							<section id="profile-info">
								<ul>
									<li><span class="label"><spring:theme code="${pf?'profile.firstName':'register.razaoSocial'}" />:</span><span>${fn:escapeXml(customerData.firstName)}</span></li>
									<li><span class="label"><spring:theme code="${pf?'profile.lastName':'register.nomeFantasia'}" />:</span><span>${fn:escapeXml(customerData.lastName)}</span></li>
									<c:if test="${pf}">
										<li><span class="label">Sexo:</span><span><spring:theme code="profile.gender.${fn:escapeXml(customerData.gender)}" /></span></li>
									</c:if>
									<li><span class="label"><spring:theme code="${pf?'profile.birthday':'register.dataFundacao'}" />:</span><span><fmt:formatDate pattern="dd/MM/yyyy" value="${customerData.birthday}" /></span></li>
									<li><span class="label"><spring:theme code="register.${pf?'cpf':'cnpj'}" />:</span><span>${fn:escapeXml(customerData.cpfcnpj)}</span></li>
									<c:if test="${not pf}">
										<li><span class="label">IE:</span><span>${fn:escapeXml(customerData.rgIe)}</span></li>
										<li><span class="label">UF:</span><span>${fn:substring(customerData.ufIe, 3, 5)}</span></li>
									</c:if>
									<li><span class="label">E-mail:</span><span>${fn:escapeXml(customerData.displayUid)}</span></li>
									<li>
										<span class="label">Receber Newsletter:</span>
										<ul class="newsletters">
											<c:forEach var="newsletter" items="${customerData.newsletters}">
												<c:if test="${fn:escapeXml(newsletter.receive)}">
													<li><spring:theme code="profile.subscribeNewsletter.${newsletter.baseStore}" /></li>
												</c:if>
											</c:forEach>
										</ul>
									</li>
								</ul>

								<div class="btn-group">
									<a href="update-password" class="btn">Alterar Senha</a>
									<a href="update-profile" class="btn">Atualizar Dados Pessoais</a>
									<a href="update-email" class="btn">Atualizar seu E-mail</a>
								</div>
							</section>
							</form:form>
						</section>
					</div>
				</section>
			</div>
		</div>
	</template:page>				
</c:if>