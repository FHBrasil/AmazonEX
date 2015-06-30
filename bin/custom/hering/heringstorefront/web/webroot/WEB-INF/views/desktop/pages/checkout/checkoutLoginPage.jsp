<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/desktop/user"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<template:page pageTitle="${pageTitle}">
<!-- <div> -->
<!-- 	<div class="col-sm-4"> -->
<!-- 		<h2>Neukunden</h2> -->
<!-- 		<div id="guestForm"> -->
<!-- 		<p>Bestellen Sie als Gast, wenn Sie zum ersten Mal bei uns einkaufen:</p> -->
<!-- 		<form> -->
<!-- 			<a href="#" class="btn btn-primary btn-registerForm">Als Gast bestellen</a> -->
<!-- 		</form> -->
<!-- 		</div> -->
<!-- 		<div class="panel-collapse out collapse in" id="registerForm" name="registerForm" style=""> -->
<!-- 			<h3>Adresse eingeben</h3> -->
<!-- 			<p>Bitte geben Sie Ihre Rechnungsadresse ein. Sie können später eine abweichende Lieferadresse eingeben.</p> -->
<!-- 			<form> -->
<!-- 				<div class="form-group"> -->
<!-- 					<label for="inputInvoiceSalutation">Anrede</label> -->
<!-- 					<select class="form-control" id="inputInvoiceSalutation"> -->
<!-- 						<option>Frau</option> -->
<!-- 						<option>Herr</option> -->
<!-- 						<option>Firma</option> -->
<!-- 					</select> -->
<!-- 				</div> -->
<!-- 				<div class="row"> -->
<!-- 					<div class="form-group col-sm-6"> -->
<!-- 						<label for="inputInvoiceFirstname">Vorname</label> -->
<!-- 						<input type="text" class="form-control" id="inputInvoiceFirstname" required="true"> -->
<!-- 					</div> -->
<!-- 					<div class="form-group col-sm-6"> -->
<!-- 						<label for="inputInvoiceLastname">Nachname</label> -->
<!-- 						<input type="text" class="form-control" id="inputInvoiceLastname" required="true"> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<p><a data-toggle="collapse" href="#toggleInvoiceAdditionalAddress"><span class="glyphicon glyphicon-triangle-right"></span> Adresszusatz (optional)</a></p> -->
<!-- 				<div id="toggleInvoiceAdditionalAddress" class="collapse out form-group"> -->
<!-- 					<input type="text" class="form-control" id="inputInvoiceAdditionalAddress" placeholder="z.B. Mami &amp; Co. GmbH, 2. Stock"> -->
<!-- 				</div> -->
<!-- 				<div class="row"> -->
<!-- 					<div class="form-group col-sm-8"> -->
<!-- 						<label for="inputInvoiceStreet">Straße</label> -->
<!-- 						<input type="text" class="form-control" id="inputInvoiceStreet" required="true"> -->
<!-- 					</div> -->
<!-- 					<div class="form-group col-sm-4"> -->
<!-- 						<label for="inputInvoiceStreetNo">Haus-Nr.</label> -->
<!-- 						<input type="text" class="form-control" id="inputInvoiceStreetNo" required="true"> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="row"> -->
<!-- 					<div class="form-group col-sm-4"> -->
<!-- 						<label for="inputInvoiceZipcode">PLZ</label> -->
<!-- 						<input type="text" class="form-control" id="inputInvoiceZipcode" required="true"> -->
<!-- 					</div> -->
<!-- 					<div class="form-group col-sm-8"> -->
<!-- 						<label for="inputInvoiceCity">Ort</label> -->
<!-- 						<input type="text" class="form-control" id="inputInvoiceCity" required="true"> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="form-group"> -->
<!-- 					<label for="inputInvoiceCountry">Land</label> -->
<!-- 					<select class="form-control" id="inputInvoiceCountry"> -->
<!-- 						<option>Deutschland</option> -->
<!-- 						<option>Brasil</option> -->
<!-- 						<option>more...</option> -->
<!-- 					</select> -->
<!-- 				</div> -->
<!-- 				<p><a data-toggle="collapse" data-target="#togglePhone"><span class=" glyphicon glyphicon-collapse-down"></span> Telefonnummer (optional)</a><small> <a href="#"><span class="glyphicon glyphicon-info-sign"></span> Warum?</a></small></p> -->
<!-- 				<div id="togglePhone" class="form-group collapse out"> -->
<!-- 					<input type="tel" class="form-control" id="inputPhone" placeholder="Telefonnummer"> -->
<!-- 				</div> -->
<!-- 				<div class="form-group"> -->
<!-- 					<label for="inputEmail">E-Mail</label> -->
<!-- 					<input type="email" class="form-control" id="inputEmail" required="true"> -->
<!-- 				</div> -->
<!-- 				<a href="index-oneStepCart.html"><button type="button" class="btn btn-primary btn-lg">Weiter</button></a> -->
<!-- 			</form> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 	<div class="col-sm-4"> -->
<!-- 		<h2>Kunden-Login</h2> -->
<!-- 		<p>Loggen Sie sich ein, wenn Sie bereits ein Kundenkonto besitzen:</p> -->
<!-- 		<div class="panel panel-default panel-secure150203"> -->
<!-- 			<div class="panel-body"> -->
<!-- 				<form> -->
<!-- 					<div class="form-group"> -->
<!-- 						<label for="inputEmail">E-Mail</label> -->
<!-- 						<input type="email" class="form-control" id="inputEmail" placeholder="E-Mail"> -->
<!-- 					</div> -->
<!-- 					<div class="form-group"> -->
<!-- 						<label for="inputPassword">Passwort</label> -->
<!-- 						<input type="password" class="form-control" id="inputPassword" placeholder="Passwort"> -->
<!-- 					</div> -->
<!-- 					<button type="submit" class="btn btn-default">Einloggen</button> -->
<!-- 				</form> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<small><a href="#">Passwort vergessen?</a></small> -->
<!-- 		<h2>Mit 1 Klick einloggen</h2> -->
<!-- 		<p>Loggen Sie sich bequem mit Amazon oder Facebook ein:</p> -->
<!-- 		<p><a href="#"><img src="https://images-na.ssl-images-amazon.com/images/G/01/EP/offAmazonPayments/de/live/prod/image/lwa/gold/small/LwA.png"></a></p> -->
<!-- 		<p><a href="#"><img src="https://www.babyartikel.de/images/fblogin.png"></a></p> -->

<!-- 	</div> -->
<!-- 	<div class="col-sm-4 hidden-xs"> -->
<!-- 		<div class="panel panel-default panel-minicart150203"> -->
<!-- 			<div class="panel-body"> -->
<!-- 				<h3>Ihr Warenkorb</h3> -->
<!-- 				<div class="row"> -->
<!-- 				<div class="col-xs-6"> -->
<!-- 					Warenwert -->
<!-- 				</div> -->
<!-- 				<div class="col-xs-6 text-right"> -->
<!-- 					26,71 &euro; -->
<!-- 				</div> -->
<!-- 				<div class="col-xs-6"> -->
<!-- 					Versand -->
<!-- 				</div> -->
<!-- 				<div class="col-xs-6 text-right"> -->
<!-- 					3,95 &euro; -->
<!-- 				</div> -->
<!-- 				<div class="col-xs-6 sum150203"> -->
<!-- 					<h4>Summe</h4> -->
<!-- 				</div> -->
<!-- 				<div class="col-xs-6 sum150203 text-right"> -->
<!-- 					<h4>33,66 &euro;</h4> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="col-xs-12 row margin-top"> -->
<!-- 				<small class="trusted150203"> -->
<!-- 				inkl. <span class="hidden-sm">kostenloser </span> <a href="http://www.trustedshops.de/guetesiegel/kaeuferschutz.html#fifth_aspect" target="_blank">Geld-Zurück-Garantie</a> -->
<!-- 				</small> -->
<!-- 			</div> -->
<!-- 		</div> -->
	
<!-- 		<div class="col-xs-12 margin-top"> -->
<!-- 			<p><small>Probleme beim Einkauf?</small><br><span class="h3"><span class="glyphicon glyphicon-earphone"></span> 089 904 750 62 00</span></p> -->
<!-- 		</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->


    <div id="globalMessages">
        <common:globalMessages />
    </div>
    <cms:pageSlot position="TopContent" var="feature" element="div"
         class="span-24 cms_disp-img_slot"> 
         <cms:component component="${feature}" />
     </cms:pageSlot> 
    <header id="page-header">
        <h1><spring:theme code="text.fliegercommerce.texto43"/></h1>
    </header>
    <section class="identificacao-checkout page">
        REGISTER
        register.tag
        <c:url value="/login/checkout/register" var="registerAndCheckoutActionUrl" />
        <user:register actionNameKey="checkout.login.registerAndCheckout"
             action="${registerAndCheckoutActionUrl}" /> 
         <section class="col-2 column">
            <div class="container half">
                LOGIN
                login.tag
                <div class="left">
                    <c:url value="/checkout/j_spring_security_check" var="loginAndCheckoutActionUrl" />
                    <user:login actionNameKey="checkout.login.loginAndCheckout"
                         action="${loginAndCheckoutActionUrl}" /> 
                </div>
                GUEST REGISTER
                guestCheckout.tag
                <c:if test="false">
                    <sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
                        <div class="right">
                            <c:url value="/login/checkout/guest" var="guestCheckoutUrl" />
                            <user:guestCheckout actionNameKey="checkout.login.guestCheckout"
                                 action="${guestCheckoutUrl}" /> 
                        </div>
                    </sec:authorize>
                </c:if>
            </div>
            <div class="banner">
                <img src="/store/_ui/desktop/theme-${themeName}/images/identificacao-banner-wide.jpg">
                <p><spring:theme code="text.fliegercommerce.texto44"/></p>
                <p>
                    <strong><spring:theme code="text.fliegercommerce.texto45"/></strong> 
                </p>
            </div>
        </section>
    </section>
</template:page>
